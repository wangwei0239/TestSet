package com.example.wangwei.voicedetect;

import android.app.Application;

import org.xutils.x;

/**
 * Created by wangwei on 16/5/23.
 */
public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);
    }
}
