package com.jackwang.testswipebackactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by wangwei on 17/4/13.
 */

public class SecondActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}
