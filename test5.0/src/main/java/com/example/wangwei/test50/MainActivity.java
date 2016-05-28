package com.example.wangwei.test50;

import android.graphics.Outline;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View tv1 = (View) findViewById(R.id.text1);
        TextView tv2 = (TextView) findViewById(R.id.text2);

        ViewOutlineProvider viewOutlineProvider1 = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0,0,view.getWidth(),view.getHeight(),50);
            }
        };

        tv1.setOutlineProvider(viewOutlineProvider1);
    }
}
