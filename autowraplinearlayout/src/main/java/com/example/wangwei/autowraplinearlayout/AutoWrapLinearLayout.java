package com.example.wangwei.autowraplinearlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by wangwei on 16/7/27.
 */
public class AutoWrapLinearLayout extends FrameLayout{
    public AutoWrapLinearLayout(Context context) {
        this(context,null);
    }

    public AutoWrapLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AutoWrapLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

//        if(widthMode == MeasureSpec.UNSPECIFIED)


    }
}
