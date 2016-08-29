package com.example.wangwei.testsingleton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by wangwei on 16/8/18.
 */
public class SecondActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageResource(R.drawable.popo);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton singleton = Singleton.getInstance(SecondActivity.this);
                finish();
            }
        });

        findViewById(R.id.gc_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.gc();

            }
        });
    }
}
