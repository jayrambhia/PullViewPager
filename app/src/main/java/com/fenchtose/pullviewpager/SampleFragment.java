package com.fenchtose.pullviewpager;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jay on 3/8/15.
 */
public class SampleFragment extends Fragment {

    public static SampleFragment newInstance() {
        return new SampleFragment();
    }

    public SampleFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sample_layout, parent, false);

        TextView headerView = (TextView)view.findViewById(R.id.header_view);

        Bundle args = getArguments();
        int day = args.getInt("day");
        String month = args.getString("month");
        int bike = args.getInt("bike");
        int walk = args.getInt("walk");

        headerView.setText(day + " " + month);

        RecyclerView mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        int[] items = new int[10];
        for (int i=0; i<5; i++) {
            items[i*2] = bike + 2 * i;
            items[i*2 + 1] = walk - 2 * i;
        }

        TrackAdapter mAdapter = new TrackAdapter(getActivity(), items);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}
