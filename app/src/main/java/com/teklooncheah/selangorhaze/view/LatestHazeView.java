package com.teklooncheah.selangorhaze.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.teklooncheah.selangorhaze.R;
import com.teklooncheah.selangorhaze.model.HazeReading;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tekloon on 11/14/15.
 */
public class LatestHazeView extends RelativeLayout {

    @Bind(R.id.location)
    TextView location;
    @Bind(R.id.index)
    TextView index;


    public LatestHazeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LatestHazeView(Context context) {
        super(context);
        init();
    }

    public LatestHazeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.latest_haze_listview, this);
        ButterKnife.bind(this);
    }

    public void update(HazeReading hazeReading){
        if(hazeReading.getLokasi()!=null)
            location.setText(hazeReading.getLokasi());
        if(hazeReading.getTerkini()!=null)
            index.setText(hazeReading.getTerkini());
    }
}
