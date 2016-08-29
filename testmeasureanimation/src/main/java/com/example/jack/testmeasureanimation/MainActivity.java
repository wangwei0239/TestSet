package com.example.jack.testmeasureanimation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout ly;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ly = (LinearLayout) findViewById(R.id.ly);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ly.setVisibility(View.VISIBLE);
                System.out.println("before height:"+ly.getMeasuredHeight());
                int widthSpec = View.MeasureSpec.makeMeasureSpec((1<<30)-1, View.MeasureSpec.AT_MOST);
                int heightSpec = View.MeasureSpec.makeMeasureSpec((1<<30)-1, View.MeasureSpec.AT_MOST);
                ly.measure(widthSpec,heightSpec);
                int height = ly.getMeasuredHeight();
                System.out.println("height:"+height);
                ValueAnimator animator = ValueAnimator.ofInt(0,height);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();
                        ViewGroup.LayoutParams lp = ly.getLayoutParams();
                        lp.height = value;
                        ly.setLayoutParams(lp);
                    }
                });
                animator.setDuration(2000);
                animator.start();
//                ObjectAnimator objectAnimator =  ObjectAnimator.ofFloat(ly,"Bottom",10,1000);
//                objectAnimator.setDuration(2000);
//                objectAnimator.start();
            }
        });
    }
}
