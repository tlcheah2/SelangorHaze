package com.teklooncheah.selangorhaze.util;

import android.location.Location;

/**
 * Created by tekloon on 11/15/15.
 */
public class LocationHelper {

    private static Location location = null;

    public static Location getLocation() {
        return location;
    }

    public static void setLocation(Location location) {
        LocationHelper.location = location;
    }
}
