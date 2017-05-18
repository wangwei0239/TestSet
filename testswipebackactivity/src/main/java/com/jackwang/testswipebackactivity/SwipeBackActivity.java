package com.jackwang.testswipebackactivity;

import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

/**
 * Created by wangwei on 17/4/13.
 */

public class SwipeBackActivity extends AppCompatActivity {

    private TouchHelper mTouchHelepr;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(mTouchHelepr==null)
            mTouchHelepr=new TouchHelper(getWindow());
        boolean consume=mTouchHelepr.processTouchEvent(ev);
        if(!consume) return super.dispatchTouchEvent(ev);
        return false;
        //return super.dispatchTouchEvent(ev)||mTouchHelepr.processTouchEvent(ev);
    }

}
