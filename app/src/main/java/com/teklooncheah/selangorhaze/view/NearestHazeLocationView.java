package com.teklooncheah.selangorhaze.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teklooncheah.selangorhaze.R;
import com.teklooncheah.selangorhaze.model.HazeReading;

import java.math.BigDecimal;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tekloon on 11/15/15.
 */
public class NearestHazeLocationView extends LinearLayout {

    @Bind(R.id.index)
    TextView mIndex;
    @Bind(R.id.location)
    TextView mLocation;
    @Bind(R.id.distance)
    TextView mDistance;

    public NearestHazeLocationView(Context context) {
        super(context);
        init();
    }

    public NearestHazeLocationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NearestHazeLocationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.nearest_haze_listview, this);
        ButterKnife.bind(this);
    }

    public void update(HazeReading hazeReading){
        if(hazeReading.getLokasi()!=null)
            mLocation.setText(hazeReading.getLokasi());
        if(hazeReading.getTerkini()!=null) {
            mIndex.setText(hazeReading.getTerkini());
            setIndexColor(hazeReading.getTerkini());
        }
        if(hazeReading.getLocation().getCoordinates()!=null) {
            float oriNum = hazeReading.getTempDistance()/1000;
            BigDecimal result;
            result=round(oriNum,2);
            mDistance.setText(result + "km, " + hazeReading.getNegeri());
//            mDistance.setText(hazeReading.getNegeri());
        }
    }

    public void setIndexColor(String latestIndex){
        if(!TextUtils.isEmpty(latestIndex)) {
            int index = Integer.parseInt(latestIndex);
            if (index <= 50)
                mIndex.setBackgroundColor(getResources().getColor(R.color.index_good));
            else if (index > 50 && index <= 100)
                mIndex.setBackgroundColor(getResources().getColor(R.color.index_moderate));
            else if (index > 100 && index <= 200)
                mIndex.setBackgroundColor(getResources().getColor(R.color.index_unhealthy));
            else if (index > 200 && index <= 300)
                mIndex.setBackgroundColor(getResources().getColor(R.color.index_very_unhealthy));
            else if (index > 300)
                mIndex.setBackgroundColor(getResources().getColor(R.color.index_hazardous));
        }
    }

    public static BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;
    }
}
