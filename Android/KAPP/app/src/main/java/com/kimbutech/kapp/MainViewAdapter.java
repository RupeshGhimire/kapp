package com.kimbutech.kapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * Created by lognod on 4/21/16.
 */
public class MainViewAdapter extends RecyclerView.Adapter<MainViewAdapter.RecyclerViewHolder>{
    private String[] departmentName;
    private int lastPosition = -1;

    private String[] departmentNumber;
    private Context context;
    Activity blocksActivity;



    //private String departmentDescription;
    class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView depatementName;
        private TextView departmentNumber;

        //private TextView departmentDescription;
        public RecyclerViewHolder(final View itemView) {
            super(itemView);
            this.depatementName=(TextView) itemView.findViewById(R.id.departmentName);
            this.departmentNumber=(TextView) itemView.findViewById(R.id.departmentNumber);

            itemView.setClickable(true);

        }
        public TextView getDepartmentname(){
            return depatementName;
        }
        public TextView getDepartmentNumber(){
            return departmentNumber;
        }

       /* public TextView getDepartmentDescription(){
            return departmentDescription;
        }*/
       public void clearAnimation()
       {
           itemView.clearAnimation();
       }
    }

    public MainViewAdapter(String[] departmentName, String[] departmentNumber, Context context){
        this.departmentName=departmentName;
        this.departmentNumber=departmentNumber;

        this.context=context;

        //this.departmentDescription="Kathmandu University";

    }
    @Override
    public MainViewAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_block,parent,false);
        RecyclerViewHolder holder = new RecyclerViewHolder(v);

        return holder;

    }



    @Override
    public void onBindViewHolder(MainViewAdapter.RecyclerViewHolder holder, final int position) {
        holder.getDepartmentname().setText(departmentName[position]);
        holder.getDepartmentNumber().setText(departmentNumber[position]);
        animation(holder.itemView,position);


        holder.itemView.setClickable(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispDepartmentInfo(position);
            }
        });
        //holder.getDepartmentDescription().setText(departmentDescription[position]);


    }

    @Override
    public int getItemCount() {
        return departmentName.length;
    }




    @Override
    public void onViewDetachedFromWindow(final RecyclerViewHolder holder)
    {
        holder.clearAnimation();
    }

    public void dispDepartmentInfo(int position){

        String [] key;
        key = new String[2];
        Intent intent = new Intent(context, ScrollingActivity.class);
        Bundle mBundle = new Bundle();

        key[0]="MainActivity";
        switch (position) {
            case 0:
                key[1]="1";
                break;
            case 1:
                key[1]="2";
                break;
            case 2:
                key[1]="3";
                break;
            case 3:
                key[1]="4";
                break;
            case 4:
                key[1]="5";
                break;

            case 5:
                key[1]="6";
                break;
            case 6:
                key[1]="7";
                break;
            case 7:
                key[1]="8";
                break;
            case 8:
                key[1]="9";
                break;
            case 9:
                key[1]="10";
                break;
            case 10:
                key[1]="11";
                break;
            case 11:
                key[1]="12";
                break;
            case 12:
                key[1]="13";
                break;
            case 13:
                key[1]="14";
                break;
            case 14:
                key[1]="15";
                break;
            case 15:
                key[1]="16";
                break;
            case 16:
                key[1]="17";
                break;
            case 17:
                key[1]="18";
                break;
            case 18:
                key[1]="19";
                break;
            case 19:
                key[1]="20";
                break;
            case 20:
                key[1]="21";
                break;
            case 21:
                key[1]="22";
                break;
            case 22:
                key[1]="23";
                break;
            case 23:
                key[1]="24";
                break;
            case 24:
                key[1]="25";
                break;
            case 25:
                key[1]="26";
                break;
            case 26:
                key[1]="27";
                break;
            case 27:
                key[1]="28";
                break;
            case 28:
                key[1]="29";
                break;
            case 29:
                key[1]="30";
                break;
            case 30:
                key[1]="31";
                break;
            case 31:
                key[1]="32";
                break;
            case 32:
                key[1]="33";
                break;
            case 33:
                key[1]="34";
                break;

        }
        mBundle.putStringArray("data", key);
        intent.putExtras(mBundle);
        //context.startActivity(intent);
        blocksActivity=(Activity) context;

        blocksActivity.startActivity(intent);
        blocksActivity.finish();
        //((this)context).finish();

    }
    public void animation(View view, int position){
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            view.startAnimation(animation);
            lastPosition = position;
        }

    }


}

