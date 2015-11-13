package com.teklooncheah.selangorhaze.job;

import android.net.Uri;

/**
 * Created by tekloon on 11/14/15.
 */
public class URLConst {
    
    private static String url = "http://malaysiahazewatch.appspot.com/data";

    public static String getHazeData(){
        return Uri.parse(url).buildUpon()
                .toString();
    }
}
