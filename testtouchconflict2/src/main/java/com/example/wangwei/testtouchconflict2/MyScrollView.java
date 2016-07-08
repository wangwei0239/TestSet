package com.example.wangwei.testtouchconflict2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by wangwei on 16/6/28.
 */
public class MyScrollView extends HorizontalScrollView{

    private String TAG = "TAG";
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;

    public MyScrollView(Context context) {
        this(context,null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;
                if(Math.abs(deltaX) > Math.abs(deltaY)){
                    intercepted = true;
                    Log.i(TAG, "dispatchTouchEvent: > move"+intercepted);
                }else {
                    intercepted = false;
                    Log.i(TAG, "dispatchTouchEvent: < move"+intercepted);
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
        }

        mLastXIntercept = x;
        mLastYIntercept = y;


//        boolean result = super.dispatchTouchEvent(ev);
        Log.i(TAG, "dispatchTouchEvent: "+intercepted);
        return intercepted;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = super.onInterceptTouchEvent(ev);
        Log.i(TAG, "onInterceptTouchEvent: "+result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean result = super.onTouchEvent(ev);
        Log.i(TAG, "onTouchEvent: "+result);
        return result;
    }
}
