package com.teklooncheah.selangorhaze.adapter;

import android.content.Context;
import android.location.Location;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.teklooncheah.selangorhaze.model.HazeReading;
import com.teklooncheah.selangorhaze.util.LocationHelper;
import com.teklooncheah.selangorhaze.view.NearestHazeLocationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by tekloon on 11/15/15.
 */
public class NearestHazeListAdapter extends ArrayAdapter<HazeReading> {

    private Context context;
    private ArrayList<HazeReading> mFullList = new ArrayList<>();
    private ArrayList<HazeReading> mFilterList = new ArrayList<>();

    public NearestHazeListAdapter(Context context, ArrayList<HazeReading> objects) {
        super(context, 0, objects);
        this.context = context;
        mFullList = objects;
        calculateDistance();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = new NearestHazeLocationView(context);
        }
        ((NearestHazeLocationView) convertView).update(mFilterList.get(position));
        return convertView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }


    public void calculateDistance() {
        if (LocationHelper.getLocation() != null) {
            for (int i = 0; i < mFullList.size(); i++) {
                HazeReading mHazeInfo = mFullList.get(i);
                Location loc = new Location("");
                loc.setLatitude(mHazeInfo.getLocation().getCoordinates().getLatitude());
                loc.setLongitude(mHazeInfo.getLocation().getCoordinates().getLongitude());
                float distance = Math.round((LocationHelper.getLocation().distanceTo(loc)));
                mHazeInfo.setTempDistance(distance);
                mFilterList.add(mHazeInfo);
            }
            Collections.sort(mFilterList, new DistanceCompare());
        }
    }

    static class DistanceCompare implements Comparator<HazeReading> {

        @Override
        public int compare(HazeReading o1, HazeReading o2) {
            int a = (int) o1.getTempDistance();
            int b = (int) o2.getTempDistance();
            return a - b;
        }
    }
}
