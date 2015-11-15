package com.teklooncheah.selangorhaze.util;


import com.koushikdutta.async.util.LruCache;

/**
 * Created by tekloon on 11/15/15.
 */
public class Cache {

    private static Cache instance;
    private LruCache<Object, Object> lruCache;

    private Cache(){
        lruCache = new LruCache<>(2048);
    }

    public static Cache getInstance() {

        if (instance == null) {

            instance = new Cache();
        }

        return instance;

    }

    public LruCache<Object, Object> getLru() {
        return lruCache;
    }
}
