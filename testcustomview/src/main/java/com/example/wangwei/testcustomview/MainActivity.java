package com.example.wangwei.testcustomview;

import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private String TAG = "TAG";
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.btn);
        final ViewGroup vg = (ViewGroup) findViewById(android.R.id.content);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getLayoutInflater().inflate(R.layout.layout_header,vg);
            }
        });

        int widthSpec = View.MeasureSpec.makeMeasureSpec((1<<30)-1, View.MeasureSpec.AT_MOST);
        int heightSpec = View.MeasureSpec.makeMeasureSpec((1<<30)-1, View.MeasureSpec.AT_MOST);
        tv.measure(widthSpec,heightSpec);
        Log.i(TAG, "onCreate: tv width="+tv.getMeasuredWidth()+" height="+tv.getMeasuredHeight());
        tv.measure(0,0);
        Log.i(TAG, "onCreate2: tv width="+tv.getMeasuredWidth()+" height="+tv.getMeasuredHeight());
        tv.post(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "onCreate3: tv width="+tv.getMeasuredWidth()+" height="+tv.getMeasuredHeight());
            }
        });

        TransitionDrawable td = (TransitionDrawable) tv.getBackground();
        td.startTransition(3000);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
