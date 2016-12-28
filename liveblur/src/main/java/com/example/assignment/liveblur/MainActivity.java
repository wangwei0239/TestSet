package com.example.assignment.liveblur;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import jp.wasabeef.blurry.Blurry;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TranslateAnimation animation = new TranslateAnimation(0, 300,0,300);
        animation.setRepeatCount(TranslateAnimation.INFINITE);
        animation.setRepeatMode(TranslateAnimation.REVERSE);
        animation.setDuration(2000);
        ImageView iv = (ImageView) findViewById(R.id.iv);
//        iv.startAnimation(animation);
        ViewGroup cover = (ViewGroup) findViewById(R.id.cover);

        Blurry.with(MainActivity.this)
                .radius(10)
                .sampling(8)
                .async()
                .capture(iv)
                .into(iv);


//        Blurry.with(this).radius(20).sampling(2).async().animate().capture(iv).into(iv);
//        Blurry.with(MainActivity.this)
//                .radius(25)
//                .sampling(2)
//                .async()
//                .animate(500)
//                .onto((ViewGroup) findViewById(R.id.content));
    }
}
