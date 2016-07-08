package com.example.wangwei.lockscreendemo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by wangwei on 16/6/28.
 */
public class AppApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext());
    }
}
