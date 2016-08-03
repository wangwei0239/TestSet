package com.example.jack.testview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private String TAG = "TAG";

    private int x = 0;
    private int y = 0;

    private int tx = 0;

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setTranslationX(tx += 30);
                Log.i(TAG, "onCreate: translationX:"+tv.getTranslationX()+" x:"+tv.getX()+" left:"+tv.getLeft()+" scrollX:"+tv.getScrollX());
            }
        });

        Log.i(TAG, "onCreate: translationX:"+tv.getTranslationX()+" x:"+tv.getX()+" left:"+tv.getLeft()+" scrollX:"+tv.getScrollX());

        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int vx = (int) event.getRawX();
                int vy = (int) event.getRawY();

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:

                        int deltaX = vx - x;
                        int deltaY = vy - y;

                        tv.layout(tv.getLeft() + deltaX,tv.getTop() + deltaY,tv.getRight() + deltaX,tv.getBottom() + deltaY);

                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                x = vx;
                y = vy;

                Log.i(TAG, "onCreate: translationX:"+tv.getTranslationX()+" x:"+tv.getX()+" left:"+tv.getLeft()+" scrollX:"+tv.getScrollX());

                return true;
            }
        });


    }
}
