package com.example.wangwei.testshowkeyboard;

import android.app.Application;

/**
 * Created by xy on 15/12/23.
 */
public class GlobalContext extends Application{
    private static GlobalContext context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        SharedPreferencesUtils.config(this);
    }
    public static GlobalContext getInstance() {
        return context;
    }
}
