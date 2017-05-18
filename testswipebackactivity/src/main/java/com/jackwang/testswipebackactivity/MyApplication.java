package com.jackwang.testswipebackactivity;

import android.app.Application;

/**
 * Created by wangwei on 17/4/13.
 */

public class MyApplication extends Application {

    private ActivityLifeCycleHelper mHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        mHelper = new ActivityLifeCycleHelper();
        registerActivityLifecycleCallbacks(mHelper);
    }

    public ActivityLifeCycleHelper getHelper() {
        return mHelper;
    }
}
