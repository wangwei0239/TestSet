package com.example.wangwei.testcustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        int widthSpec = View.MeasureSpec.makeMeasureSpec((1<<30)-1, View.MeasureSpec.AT_MOST);
        int heightSpec = View.MeasureSpec.makeMeasureSpec((1<<30)-1, View.MeasureSpec.AT_MOST);
        tv.measure(widthSpec,heightSpec);
        Log.i(TAG, "onCreate: tv width="+tv.getMeasuredWidth()+" height="+tv.getMeasuredHeight());
//        tv.post(new Runnable() {
//            @Override
//            public void run() {
//                Log.i(TAG, "onCreate: tv width="+tv.getMeasuredWidth()+" height="+tv.getMeasuredHeight());
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
