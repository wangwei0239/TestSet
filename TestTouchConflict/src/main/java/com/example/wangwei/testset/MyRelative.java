package com.example.wangwei.testset;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by wangwei on 16/5/30.
 */
public class MyRelative extends RelativeLayout {

    private String TAG = "TAG";

    public MyRelative(Context context) {
        this(context,null);
    }

    public MyRelative(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyRelative(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "rl onTouchEvent: start");
        boolean result = super.onTouchEvent(event);
        Log.i(TAG, "rl onTouchEvent: return:"+result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "rl onInterceptTouchEvent: start");
        boolean result = super.onInterceptTouchEvent(ev);
        Log.i(TAG, "rl onInterceptTouchEvent: end "+result);
        return result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "rl dispatchTouchEvent: start");
        boolean result = super.dispatchTouchEvent(ev);
        Log.i(TAG, "rl dispatchTouchEvent: end "+result);
        return result;
    }
}
