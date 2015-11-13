package com.teklooncheah.selangorhaze.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.teklooncheah.selangorhaze.model.HazeReading;

import java.util.ArrayList;

/**
 * Created by YeeKokMing on 11/14/15.
 */
public class LatestHazeListAdapter extends ArrayAdapter<HazeReading> {

    private ArrayList<HazeReading> mHazeReadingList = new ArrayList<>();

    public LatestHazeListAdapter(Context context, int resource, ArrayList<HazeReading> mList) {
        super(context, 0, mList);
        this.mHazeReadingList = mList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){

        }
        return convertView;
    }
}
