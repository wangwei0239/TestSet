package com.example.wangwei.testlaterdismissdialog;

import android.content.Context;

/**
 * Created by wangwei on 16/8/18.
 */
public class Singleton {
    private static Singleton mInstance;
    private Context context;

    private Singleton(Context context){
        this.context = context;
    }

    public static Singleton getInstance(Context context){
        if(mInstance == null){
            mInstance = new Singleton(context);
        }
        return mInstance;
    }
}
