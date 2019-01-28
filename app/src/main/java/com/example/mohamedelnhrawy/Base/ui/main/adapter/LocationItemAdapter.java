package com.example.mohamedelnhrawy.Base.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohamedelnhrawy.Base.R;
import com.example.mohamedelnhrawy.Base.data.db.model.Location;

import java.util.List;

/**
 * Created by mohamedelnhrawy on 1/28/19.
 */

public class LocationItemAdapter extends RecyclerView.Adapter<LocationItemAdapter.ListItemViewHolder>{

    private List<Location> list;
    private onCardClicked callback;
    private Context context;

    public LocationItemAdapter(List<Location> list, onCardClicked callback, Context context) {
        this.list = list;
        this.callback = callback;
        this.context = context;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.location_item, parent, false);
        return new ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int i) {
        holder.tv_lat.setText(""+list.get(i).getLocation_latitude());
        holder.tv_lng.setText(""+list.get(i).getLocation_longitude());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onItemClicked(i,list.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public interface onCardClicked{
        void onItemClicked(int position,Location marvel);
    }
    public static class ListItemViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_lat,tv_lng;

        private CardView card;


        public ListItemViewHolder(View itemView) {
            super(itemView);
            tv_lat = (TextView) itemView.findViewById(R.id.tv_lat);
            tv_lng = (TextView) itemView.findViewById(R.id.tv_lng);
            card = (CardView) itemView.findViewById(R.id.cv_parent);
        }
    }

    public void updateList(List<Location> marvelList){
        this.list=marvelList;
        notifyDataSetChanged();
    }

    public void setItemClickedCallback(onCardClicked callback){
        this.callback=callback;
    }
}
