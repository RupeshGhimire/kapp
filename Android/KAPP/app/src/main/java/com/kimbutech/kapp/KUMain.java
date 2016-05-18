package com.kimbutech.kapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

public class KUMain extends Fragment {
    MainViewAdapter recyclerAdapter;
    String [] key;
    private String[] departmentName;
    private String[] departmentNumber;
    private String[] departmentImage;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public KUMain() {

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static KUMain newInstance(int sectionNumber) {
        KUMain fragment = new KUMain();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.content_main, container, false);

        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager recyclerViewLayout= new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(recyclerViewLayout);
        mRecyclerView.setHasFixedSize(true);

        getDepartmentInfo();

        recyclerAdapter=new MainViewAdapter(departmentName,departmentNumber,getContext());
        mRecyclerView.setAdapter(recyclerAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            LinearLayout searchView =(LinearLayout) rootView.findViewById(R.id.searchView);
            FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);

            Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            TabLayout tabs = (TabLayout) getActivity().findViewById(R.id.tabs);
            AppBarLayout appbar=(AppBarLayout)getActivity().findViewById(R.id.appBar);


            int initialPadding;
            int scrolledDistance=0;
            private static final int HIDE_THRESHOLD = 20;
            boolean controlsVisible=true;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                    if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
                        initialPadding = recyclerView.getPaddingTop();

                        recyclerView.setPadding(0, 0, 0, 0);

                        searchView.animate().translationY(-searchView.getHeight() - 200).setInterpolator(new AccelerateInterpolator(2));
                        //toolbar.animate().translationY(-toolbar.getHeight() - 200).setInterpolator(new AccelerateInterpolator(2));
                        //toolbar.setAlpha(0.0f);
                        toolbar.animate().alpha(0.0f);
                        //tabs.animate().translationY(-tabs.getHeight()).setInterpolator(new AccelerateInterpolator(2));
                        fab.animate().translationY(fab.getHeight() + 100).setInterpolator(new AccelerateInterpolator(2));


                        controlsVisible = false;
                        scrolledDistance = 0;
                    } else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
                        recyclerView.setPadding(0, initialPadding, 0, 0);
                        searchView.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
                       //toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
                        //tabs.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
                       toolbar.animate().alpha(1.0f);

                        fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));


                        controlsVisible = true;
                        scrolledDistance = 0;
                    }

                    if ((controlsVisible && dy > 0) || (!controlsVisible && dy < 0)) {
                        scrolledDistance += dy;
                    }
                }


        });





        //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }

    public void getDepartmentInfo() {

        departmentName =new String[34];
        departmentNumber =new String[34];
        departmentImage = new String[34];
        //departmentDescription =new String[32];
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getContext());
        databaseAccess.open();
        int a;
        for(int i=0;i<34;i++){
            a=i;
            //title
            departmentName[i]= databaseAccess.databaseToString(++a, 2);
            departmentImage[i]=databaseAccess.databaseToString(a,7);
            a=i;
            //description
            //departmentDescription[i]= databaseAccess.databaseToString(++i, 6);
            //blockno
            if(i<9){
                departmentNumber[i]="0"+String.valueOf(++a);


            } else{
                departmentNumber[i]=String.valueOf(++a);
            }

        }

        //close database
        databaseAccess.close();

    }
}