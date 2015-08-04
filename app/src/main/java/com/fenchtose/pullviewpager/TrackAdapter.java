package com.fenchtose.pullviewpager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jay on 4/8/15.
 */
public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder> {

    private int[] mItems;
    private LayoutInflater mInflater;

    public TrackAdapter(Context context, int[] items) {
        mInflater = LayoutInflater.from(context);
        mItems = items;
    }

    @Override
    public TrackAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrackAdapter.ViewHolder viewHolder, int i) {
        int bikeNum = mItems[i*2];
        int walkNum = mItems[i*2 + 1];

        viewHolder.bikeView.setText(bikeNum + " Mins");
        viewHolder.walkView.setText(walkNum + " Mins");
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.length/2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView bikeView;
        protected TextView walkView;

        public ViewHolder(View itemView) {
            super(itemView);
            bikeView = (TextView)itemView.findViewById(R.id.textview_bike);
            walkView = (TextView)itemView.findViewById(R.id.textview_walk);
        }
    }
}
