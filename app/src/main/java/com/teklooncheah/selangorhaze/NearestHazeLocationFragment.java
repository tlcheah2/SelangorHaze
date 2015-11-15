package com.teklooncheah.selangorhaze;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.teklooncheah.selangorhaze.adapter.NearestHazeListAdapter;
import com.teklooncheah.selangorhaze.model.HazeReading;
import com.teklooncheah.selangorhaze.util.Cache;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tekloon on 11/15/15.
 */
public class NearestHazeLocationFragment extends Fragment {

    @Bind(R.id.nearest_listview)
    ListView mListView;
    NearestHazeListAdapter mAdapter;

    private ArrayList<HazeReading> mHazeReadingList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Cache.getInstance().getLru()!=null){
            mHazeReadingList = HazeReading.deserializeList(Cache.getInstance().getLru().get("HazeInfo").toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearest_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new NearestHazeListAdapter(getActivity(), mHazeReadingList);
        mListView.setAdapter(mAdapter);
    }
}
