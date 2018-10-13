package com.example.lenovoz51.oruprograma;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Lenovo Z51 on 2018-05-05.
 */

public class AppControler extends Application {
    public static final String TAG = AppControler.class.getSimpleName();
    private com.android.volley.RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static AppControler mInstance;
    @Override
    public void onCreate(){
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppControler getmInstance(){
        return mInstance;
    }

    public com.android.volley.RequestQueue getmRequestQueue(){
        if(mRequestQueue==null){
            mRequestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public ImageLoader getmImageLoader(){
        getmRequestQueue();
        if(mImageLoader==null){
            mImageLoader= new ImageLoader(this.mRequestQueue,new BitmapCache());
        }
        return this.mImageLoader;
    }
    public <T> void addToRequestQueue(Request<T> request){
        request.setTag(TAG);
        getmRequestQueue().add(request);
    }

    public void cancelPendingRequest(Object tag){
        if(mRequestQueue!=null)
            mRequestQueue.cancelAll(tag);
    }


}
