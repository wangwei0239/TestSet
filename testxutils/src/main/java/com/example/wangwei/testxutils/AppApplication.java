package com.example.wangwei.testxutils;

import android.app.Application;

import org.xutils.x;

/**
 * Created by wangwei on 16/8/24.
 */
public class AppApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
