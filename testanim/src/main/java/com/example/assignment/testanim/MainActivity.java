package com.example.assignment.testanim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("Hello World!");
//                final ObjectAnimator animator = ObjectAnimator.ofFloat(tv,"rotationX", 0,360).setDuration(5000);
//                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                        Log.i("Anim","AnimValue:"+animation.getAnimatedValue()+" Fraction:"+animation.getAnimatedFraction());
//                        float curValue = (float)animation.getAnimatedValue();
//                        if(curValue > 90 && curValue < 270){
//                            tv.setRotationX(270);
//                            tv.setText("Emotibot");
//                        }
//                    }
//                });
//
////                animator.addListener(new AnimatorListenerAdapter() {
////                    @Override
////                    public void onAnimationEnd(Animator animation) {
////                        super.onAnimationEnd(animation);
////                        tv.setText("Emotibot");
////                        ObjectAnimator newAnim = ObjectAnimator.ofFloat(tv,"rotationX", 270,360).setDuration(500);
////                        newAnim.start();
////                    }
////                });
//                Log.i("Anim","Test");
//                animator.start();

                AnimatorSet set = new AnimatorSet();
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(tv,"rotationX",0,90);
                anim1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        tv.setText("Emotibot");
                    }
                });
                ObjectAnimator anim2 = ObjectAnimator.ofFloat(tv,"rotationX",270,360);
                set.playSequentially(anim1,anim2);
                set.start();
            }
        });
    }
}
