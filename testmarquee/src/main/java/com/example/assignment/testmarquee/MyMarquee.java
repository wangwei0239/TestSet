package com.example.assignment.testmarquee;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by wangwei on 17/3/10.
 */

public class MyMarquee extends FrameLayout{

    private TextView firstView;
    private TextView secondView;
    private boolean isRunning = false;

    public MyMarquee(@NonNull Context context) {
        this(context,null);
    }

    public MyMarquee(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyMarquee(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(getChildCount() == 2){
            firstView = (TextView) getChildAt(0);
            secondView = (TextView) getChildAt(1);
        }else {
            throw new InflateException("You must have 2 child views.");
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        firstView.layout(left,top,right,bottom);
        secondView.layout(left,bottom+1,right,bottom * 2);
    }

    public void startAnim(String input){
        if(isRunning){
            return;
        }
        isRunning = true;
        secondView.setText(input);
        AnimatorSet set1 = new AnimatorSet();
        final AnimatorSet set2 = new AnimatorSet();
        produceAnimator(set1);
        produceAnimator(set2);
        set2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                resetView();
                isRunning = false;
            }
        });
        set1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                resetView();
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        set2.start();
                    }
                }, 2000);
            }
        });
        set1.start();
    }

    public void produceAnimator(AnimatorSet set){
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(firstView,View.TRANSLATION_Y,-getHeight());
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(secondView,View.TRANSLATION_Y,-getHeight());
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(firstView,View.ALPHA,1.0f,0.0f);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(secondView,View.ALPHA,0.0f,1.0f);

        set.playTogether(animator1,animator2,animator3,animator4);
        set.setDuration(1000);
    }

    public void resetView(){
        firstView.setTranslationY(0);
        secondView.setTranslationY(0);
        firstView.setAlpha(1.0f);
        String content1 = firstView.getText().toString();
        String content2 = secondView.getText().toString();
        firstView.setText(content2);
        secondView.setText(content1);
    }
}
