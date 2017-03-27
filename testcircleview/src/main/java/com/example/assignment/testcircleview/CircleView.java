package com.example.assignment.testcircleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by wangwei on 17/3/13.
 */

public class CircleView extends View {

    private int radius = 150;


    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(300,300);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint iconCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        iconCirclePaint.setColor(Color.WHITE);
        iconCirclePaint.setStyle(Paint.Style.FILL);
        iconCirclePaint.setStrokeWidth(3);
        iconCirclePaint.setShader(new SweepGradient(radius, radius, Color.YELLOW, Color.RED));
        canvas.save();
        canvas.rotate(45f, 150, 150);
        canvas.drawCircle(radius, radius, radius-5, iconCirclePaint);
        canvas.restore();
    }
}
