package com.example.wangwei.testset;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jack on 2016/5/29.
 */
public class TouchButton extends Button{

    public static String TAG = "TAG";
    private boolean isLongClick = false;

    public TouchButton(Context context) {
        this(context,null);
    }

    public TouchButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TouchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i(TAG,"longclick");
                return false;
            }
        });
//        super.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        Log.i(TAG,"super down");
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        Log.i(TAG, "suprer move");
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        Log.i(TAG, "super up");
//                        break;
//                }
//                return false;
//            }
//        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "onTouchEvent: down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "onTouchEvent: move");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "onTouchEvent: up");
//                if(!isLongClick){
//                    boolean result = super.onTouchEvent(event);
//                    Log.i(TAG, "super result bl:"+result);
//                    return result;
//                }
                break;
        }
        boolean result = super.onTouchEvent(event);
        Log.i(TAG, "super result al:"+result);
        return result;
    }
}
