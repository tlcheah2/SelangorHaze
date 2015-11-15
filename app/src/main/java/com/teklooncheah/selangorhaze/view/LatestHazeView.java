package com.teklooncheah.selangorhaze.view;

import android.content.Context;
import android.text.TextUtils;
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
    @Bind(R.id.state)
    TextView state;

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
        if(hazeReading.getTerkini()!=null) {
            index.setText(hazeReading.getTerkini());
            setIndexColor(hazeReading.getTerkini());
        }
        if(hazeReading.getNegeri()!=null) {
            state.setText(hazeReading.getNegeri());
        }
    }

    public void setIndexColor(String latestIndex){
        if(!TextUtils.isEmpty(latestIndex)) {
            int mIndex = Integer.parseInt(latestIndex);
            if (mIndex <= 50)
                index.setBackgroundColor(getResources().getColor(R.color.index_good));
            else if (mIndex > 50 && mIndex <= 100)
                index.setBackgroundColor(getResources().getColor(R.color.index_moderate));
            else if (mIndex > 100 && mIndex <= 200)
                index.setBackgroundColor(getResources().getColor(R.color.index_unhealthy));
            else if (mIndex > 200 && mIndex <= 300)
                index.setBackgroundColor(getResources().getColor(R.color.index_very_unhealthy));
            else if (mIndex > 300)
                index.setBackgroundColor(getResources().getColor(R.color.index_hazardous));
        }

    }
}
