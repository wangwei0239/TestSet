package com.example.wangwei.createplusrotateanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout rl_image;
    private ImageView iv;
    private ImageView iv1;
    private ImageView iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rl_image = (RelativeLayout) findViewById(R.id.morely);
        iv = (ImageView) findViewById(R.id.iv);
        iv1 = (ImageView) findViewById(R.id.iv_1);
        iv2 = (ImageView) findViewById(R.id.iv_2);
        unfoldanimator = ValueAnimator.ofFloat(DensityUtil.dip2px(this,50), DensityUtil.dip2px(this,170));
        foldanimator = ValueAnimator.ofFloat(DensityUtil.dip2px(this,170), DensityUtil.dip2px(this,50));
        unfoldanimator.addUpdateListener(listener);
        foldanimator.addUpdateListener(listener);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(iv.getRotation() == 0){
                    ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(iv,"rotation",135);
                    rotateAnim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            iv1.setVisibility(View.VISIBLE);
                            iv2.setVisibility(View.VISIBLE);
                            ObjectAnimator.ofFloat(iv1,"rotation",-180,0).start();
                            ObjectAnimator.ofFloat(iv2,"rotation",-180,0).start();
                            unfoldanimator.start();
                        }
                    });
                    rotateAnim.start();


                }else {
                    ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(iv,"rotation",0);
                    rotateAnim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            iv1.setVisibility(View.GONE);
                            iv2.setVisibility(View.GONE);
                        }
                    });
                    rotateAnim.start();
                    ObjectAnimator.ofFloat(iv1,"rotation",0,-180).start();
                    ObjectAnimator.ofFloat(iv2,"rotation",0,-180).start();
                    foldanimator.start();
                }

            }
        });
    }

    private ValueAnimator unfoldanimator;
    private ValueAnimator foldanimator;

    private ValueAnimator.AnimatorUpdateListener listener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float value = (float) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams lp = rl_image.getLayoutParams();
            lp.height = (int) value;
            rl_image.setLayoutParams(lp);
        }
    };


    public static class DensityUtil {

        /**
         * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
         */
        public static int dip2px(Context context, float dpValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }

        /**
         * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
         */
        public static int px2dip(Context context, float pxValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (pxValue / scale + 0.5f);
        }
    }
}
