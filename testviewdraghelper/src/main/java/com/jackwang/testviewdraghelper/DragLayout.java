package com.jackwang.testviewdraghelper;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by wangwei on 17/4/13.
 */

public class DragLayout extends LinearLayout {

    private ViewDragHelper helper;
    private ViewDragHelper.Callback callback;

    private View v1;
    private View v2;

    public DragLayout(Context context) {
        this(context,null);
    }

    public DragLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        callback = new DaggerCallback();
        helper = ViewDragHelper.create(this,1.0f,callback);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        v1 = getChildAt(0);
        v2 = getChildAt(1);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return helper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        helper.processTouchEvent(event);
        return true;
    }

    class DaggerCallback extends ViewDragHelper.Callback{

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            if(child == v1){
                return true;
            }
            return false;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.i("TAG","Left:"+left+" DX:"+dx);
            int leftBound = getPaddingLeft();
            int rightBound = getWidth() - getPaddingRight() - child.getWidth();

            return Math.min(Math.max(left, leftBound), rightBound);
        }
    }


}
