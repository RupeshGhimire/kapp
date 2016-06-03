package com.kimbutech.kapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lognod on 5/21/16.
 */
public class EventsViewAdapter extends RecyclerView.Adapter<EventsViewAdapter.RecyclerViewHolder> {
    String  eventName;
    String  eventDescription;
    String  eventDate;

    public EventsViewAdapter(String name,String date,String description){
        this.eventDate=date;
        this.eventDescription=description;
        this.eventName=name;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_events,parent,false);
        RecyclerViewHolder holder = new RecyclerViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.getEventName().setText(eventName);
        holder.getEventDescription().setText(eventDescription);
        holder.getEventDate().setText(eventDate);


    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView eventName;
        private TextView eventDescription;
        private TextView eventDate;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            this.eventDate=(TextView) itemView.findViewById(R.id.eventDate);
            this.eventName=(TextView) itemView.findViewById(R.id.eventTitle);
            this.eventDescription=(TextView) itemView.findViewById(R.id.eventDescription);
        }
        public TextView getEventName(){
            return eventName;
        }
        public TextView getEventDescription(){
            return eventDescription;
        }
        public TextView getEventDate(){
            return eventDate;
        }
    }
}
