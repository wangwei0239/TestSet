package com.example.wangwei.createlayerswitchlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

/**
 * Created by wangwei on 16/8/26.
 */
public class SwithLayerListView extends ListView{
    public SwithLayerListView(Context context) {
        this(context,null);
    }

    public SwithLayerListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwithLayerListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected boolean isLastItemVisible() {
        final Adapter adapter = getAdapter();

        if (null == adapter || adapter.isEmpty()) {
            return true;
        }

        final int lastItemPosition = adapter.getCount() - 1;
        final int lastVisiblePosition = getLastVisiblePosition();


        if (lastVisiblePosition >= lastItemPosition - 1) {
            final int childIndex = lastVisiblePosition - getFirstVisiblePosition();
            final int childCount = getChildCount();
            final int index = Math.min(childIndex, childCount - 1);
            final View lastVisibleChild = getChildAt(index);
            if (lastVisibleChild != null) {
                return lastVisibleChild.getBottom() <= getBottom();
            }
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_MOVE){
            if(isLastItemVisible()){
                return false;
            }
        }
        return super.onTouchEvent(ev);
    }
}
