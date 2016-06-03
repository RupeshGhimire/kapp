package com.kimbutech.kapp;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {
    private String[] eventName;
    private String[] eventDate;
    private String[] eventDescripton;
    boolean controlsVisible;

    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.eventsRecyclerView);
        RecyclerView.LayoutManager recyclerViewLayout= new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(recyclerViewLayout);
        mRecyclerView.setHasFixedSize(true);


        EventsViewAdapter  recyclerAdapter=new EventsViewAdapter("Event","01","This is a samPle event");
        mRecyclerView.setAdapter(recyclerAdapter);

/*
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }






            int initialPadding;
            int scrolledDistance=0;
            private static final int HIDE_THRESHOLD = 20;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
                    initialPadding = recyclerView.getPaddingTop();



                    controlsVisible = false;
                    scrolledDistance = 0;
                } else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {

                    controlsVisible = true;
                    scrolledDistance = 0;
                }

                if ((controlsVisible && dy > 0) || (!controlsVisible && dy < 0)) {
                    scrolledDistance += dy;
                }
            }


        });*/
        return rootView;


    }

}
