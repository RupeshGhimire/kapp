package com.bitcakecodes.kapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by lognod on 4/21/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{
    private String[] departmentName;
    private String[] departmentNumber;
    private Context context;



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
    }

    public RecyclerViewAdapter(String[] departmentName, String[] departmentNumber,Context context){
        this.departmentName=departmentName;
        this.departmentNumber=departmentNumber;
        this.context=context;
        //this.departmentDescription="Kathmandu University";

    }
    @Override
    public RecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_block,parent,false);
        RecyclerViewHolder holder = new RecyclerViewHolder(v);

        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.RecyclerViewHolder holder, final int position) {
        holder.getDepartmentname().setText(departmentName[position]);
        holder.getDepartmentNumber().setText(departmentNumber[position]);
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



    public void dispDepartmentInfo(int position){

        String [] key;
        key = new String[2];
        Intent intent = new Intent(context, ScrollingActivity.class);
        Bundle mBundle = new Bundle();
        key[0]="BlocksActivity";
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
            /*case R.id.eight:
                key[1]="8";
                break;
            case R.id.nine:
                key[1]="10";
                break;
            case R.id.ten:
                key[1]="10";
                break;
            case R.id.eleven:
                key[1]="9";
                break;
            case R.id.twelve:
                key[1]="12";
                break;
            case R.id.thirteen:
                key[1]="13";
                break;
            case R.id.fourteen:
                key[1]="14";
                break;
            case R.id.fifteen:
                key[1]="15";
                break;
            case R.id.sixteen:
                key[1]="16";
                break;
            case R.id.seventeen:
                key[1]="17";
                break;
            case R.id.eighteen:
                key[1]="18";
                break;
            case R.id.nineteen:
                key[1]="19";
                break;
            case R.id.twenty:
                key[1]="20";
                break;
            case R.id.twentyone:
                key[1]="21";
                break;
            case R.id.twentytwo:
                key[1]="22";
                break;
            case R.id.twentythree:
                key[1]="23";
                break;
            case R.id.twentyfour:
                key[1]="24";
                break;
            case R.id.twentyfive:
                key[1]="25";
                break;
            case R.id.twentysix:
                key[1]="26";
                break;
            case R.id.twentyseven:
                key[1]="27";
                break;
            case R.id.twentyeight:
                key[1]="28";
                break;
            case R.id.twentynine:
                key[1]="29";
                break;
            case R.id.thirty:
                key[1]="30";
                break;
            case R.id.thirtyone:
                key[1]="31";
                break;
            case R.id.thirtytwo:
                key[1]="32";
                break;
            case R.id.thirtythree:
                key[1]="33";
                break;
            case R.id.thirtyfour:
                key[1]="34";
                break;
*/
        }
        mBundle.putStringArray("data", key);
        intent.putExtras(mBundle);
        context.startActivity(intent);
        
        //((this)context).finish();

    }


}

