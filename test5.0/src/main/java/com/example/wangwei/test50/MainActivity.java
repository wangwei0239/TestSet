package com.example.wangwei.test50;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View tv1 = (View) findViewById(R.id.text1);
        TextView tv2 = (TextView) findViewById(R.id.text2);
        EditText et = (EditText) findViewById(R.id.et);

        ViewOutlineProvider viewOutlineProvider1 = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0,0,view.getWidth(),view.getHeight(),50);
            }
        };

        tv1.setOutlineProvider(viewOutlineProvider1);

        Drawable drawable = new Drawable() {

            Rect rect = new Rect(0,0,5000,4);

            @Override
            public int getMinimumHeight() {
                return rect.height();
            }

            @Override
            public int getMinimumWidth() {
                return rect.width();
            }

            @Override
            public void draw(Canvas canvas) {
                Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                mPaint.setColor(Color.RED);

                canvas.drawRect(rect,mPaint);
            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(ColorFilter colorFilter) {

            }

            @Override
            public int getOpacity() {
                return 0;
            }
        };
        Log.i(TAG, "drawable.getIntrinsicWidth():"+drawable.getMinimumWidth()+" drawable.getIntrinsicHeight():"+drawable.getMinimumHeight());
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        et.setCompoundDrawables(null,null,null,drawable);


    }

    private String TAG = "TAG";
}
