package com.example.wangwei.testvoice;

import android.app.Application;

import org.xutils.x;

/**
 * Created by wangwei on 16/7/2.
 */
public class AppApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
