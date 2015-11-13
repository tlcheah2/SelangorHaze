package com.teklooncheah.selangorhaze.event;

import com.teklooncheah.selangorhaze.model.HazeReading;

import java.util.ArrayList;

/**
 * Created by tekloon on 11/14/15.
 */
public class OnGetHazeDataEvent {

    private ArrayList<HazeReading> mHazeReading;

    public OnGetHazeDataEvent(ArrayList<HazeReading> mList){
        this.mHazeReading = mList;
    }

    public ArrayList<HazeReading> getmHazeReading() {
        return mHazeReading;
    }
}
