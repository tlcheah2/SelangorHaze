package com.teklooncheah.selangorhaze.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.teklooncheah.selangorhaze.model.HazeReading;
import com.teklooncheah.selangorhaze.view.LatestHazeView;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by tekloon on 11/14/15.
 */
public class LatestHazeListAdapter extends ArrayAdapter<HazeReading> {

    private Context context;
    private ArrayList<HazeReading> mHazeReadingList;

    public LatestHazeListAdapter(Context context, ArrayList<HazeReading> mList) {
        super(context, 0, mList);
        this.mHazeReadingList = mList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = new LatestHazeView(getContext());
        }
        ((LatestHazeView) convertView).update(getItem(position));
        return convertView;
    }

    @Override
    public void addAll(Collection<? extends HazeReading> collection) {
        super.addAll(collection);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
