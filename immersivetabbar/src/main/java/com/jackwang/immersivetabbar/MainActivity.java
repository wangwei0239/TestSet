package com.jackwang.immersivetabbar;

import android.app.Activity;
import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        StatusBarUtil.setColor(this, Color.RED);
//        StatusBarUtil.setTranslucent(this);
        StatusBarUtil.setTransparent(this);
    }
}
