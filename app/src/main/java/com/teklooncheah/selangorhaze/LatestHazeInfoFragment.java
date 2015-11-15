package com.teklooncheah.selangorhaze;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teklooncheah.selangorhaze.adapter.LatestHazeListAdapter;
import com.teklooncheah.selangorhaze.model.HazeReading;
import com.teklooncheah.selangorhaze.util.Cache;
import com.teklooncheah.selangorhaze.widget.EndlessListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tekloon on 11/13/15.
 */
public class LatestHazeInfoFragment extends Fragment {

    @Bind(R.id.list_view)
    EndlessListView mListView;
    @Bind(R.id.pull_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.text)
    TextView mText;
//    @Bind(R.id.listContainer)
//    View listContainer;

    private LatestHazeListAdapter mAdapter;
    private ArrayList<HazeReading> mList = new ArrayList<>();

    public static Fragment newInstance(){
        LatestHazeInfoFragment fragment = new LatestHazeInfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Cache.getInstance().getLru().get("HazeInfo")!=null){
            mList = HazeReading.deserializeList(Cache.getInstance().getLru().get("HazeInfo").toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest_haze_info, container, false);
        ButterKnife.bind(this, view);
        mAdapter = new LatestHazeListAdapter(getActivity(), new ArrayList<HazeReading>());
        mAdapter.addAll(mList);
        mListView.hasMorePages(false);
        mListView.setAdapter(mAdapter);
        mListView.onLoadMoreComplete();
        return view;
    }

}
