package com.example.wangwei.testscrollbyto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button upBtn,downBtn,leftBtn,rightBtn;
    private TextView content;
    private static String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upBtn = (Button) findViewById(R.id.upBtn);
        downBtn = (Button) findViewById(R.id.downBtn);
        leftBtn = (Button) findViewById(R.id.leftBtn);
        rightBtn = (Button) findViewById(R.id.rightBtn);
        upBtn.setOnClickListener(this);
        downBtn.setOnClickListener(this);
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);

        content = (TextView) findViewById(R.id.content);
        content.requestLayout();
    }

    //scroll by to 只改变内容位置  不改变背景

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.upBtn:
                Log.i(TAG, "up - before - scrollX:"+content.getScrollX()+" scrollY:"+content.getScrollY());
                content.scrollBy(0,10);
                Log.i(TAG, "up - after - scrollX:"+content.getScrollX()+" scrollY:"+content.getScrollY());
                break;

            case R.id.downBtn:
                Log.i(TAG, "down - before - scrollX:"+content.getScrollX()+" scrollY:"+content.getScrollY());
                content.scrollBy(0,-10);
                Log.i(TAG, "down - after -  scrollX:"+content.getScrollX()+" scrollY:"+content.getScrollY());
                break;

            case R.id.leftBtn:
                Log.i(TAG, "left - before - scrollX:"+content.getScrollX()+" scrollY:"+content.getScrollY());
                content.scrollBy(10,0);
                Log.i(TAG, "left - after - scrollX:"+content.getScrollX()+" scrollY:"+content.getScrollY());
                break;

            case R.id.rightBtn:
                Log.i(TAG, "right - before - scrollX:"+content.getScrollX()+" scrollY:"+content.getScrollY());
                content.scrollBy(-10,0);
                Log.i(TAG, "right - after - scrollX:"+content.getScrollX()+" scrollY:"+content.getScrollY());
                break;
        }
    }
}
