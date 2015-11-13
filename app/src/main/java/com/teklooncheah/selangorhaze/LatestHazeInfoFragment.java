package com.teklooncheah.selangorhaze;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teklooncheah.selangorhaze.adapter.LatestHazeListAdapter;
import com.teklooncheah.selangorhaze.event.OnGetHazeDataEvent;
import com.teklooncheah.selangorhaze.job.GetHazeDataJob;
import com.teklooncheah.selangorhaze.widget.EndlessListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by tekloon on 11/13/15.
 */
public class LatestHazeInfoFragment extends Fragment {

    @Bind(R.id.list_view)
    EndlessListView mListView;
    @Bind(R.id.pull_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.listContainer)
    View listContainer;

    private LatestHazeListAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().getJobManager().addJob(new GetHazeDataJob(getActivity()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest_haze_info, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void onEventMainThread(OnGetHazeDataEvent event){
        mAdapter.addAll(event.getmHazeReading());
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
