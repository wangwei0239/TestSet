package com.example.wangwei.testset;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by wangwei on 16/5/30.
 */
public class MyListView extends ListView {
    public MyListView(Context context) {
        this(context,null);
    }

    public MyListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if(getAdapter()!=null)
        {
            if(getAdapter().getCount()-1 == getLastVisiblePosition())
            {
                return false;
            }
        }

        return super.onTouchEvent(ev);
    }
}
