package com.teklooncheah.selangorhaze.job;

import android.content.Context;
import android.util.Log;

import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;
import com.teklooncheah.selangorhaze.event.OnGetHazeDataEvent;
import com.teklooncheah.selangorhaze.model.HazeReading;

import org.apache.http.HttpStatus;

import java.util.concurrent.atomic.AtomicInteger;

import de.greenrobot.event.EventBus;

/**
 * Created by tekloon on 11/14/15.
 */
public class GetHazeDataJob extends Job {

    private int id;
    private AtomicInteger jobCounter = new AtomicInteger(0);
    private Context context;

    public GetHazeDataJob(Context context) {
        super(new Params(Priority.HIGH).requireNetwork());
        this.id = jobCounter.incrementAndGet();
        this.context = context;
    }


    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        if(id!=jobCounter.get()){
            return;
        }

        String url = URLConst.getHazeData();

        Response<String> response = Ion.with(context)
                .load(url)
                .asString()
                .withResponse()
                .get();

        String json = response.getResult();

        if(response.getHeaders().code()!= HttpStatus.SC_OK){
            Log.d("tekloon","Get Data Failed");
        }
        EventBus.getDefault().post(new OnGetHazeDataEvent(HazeReading.deserializeList(json)));

    }

    @Override
    protected void onCancel() {

    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        return false;
    }
}
