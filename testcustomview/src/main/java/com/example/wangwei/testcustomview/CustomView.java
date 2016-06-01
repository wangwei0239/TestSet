package com.example.wangwei.testcustomview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangwei on 16/5/31.
 */
public class CustomView extends View {
    public CustomView(Context context) {
        this(context,null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
