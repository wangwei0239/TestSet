package com.example.wangwei.testset;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView ls;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> list;
    private ScrollView sv;
    private RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sv = (ScrollView) findViewById(R.id.sv);
        ls = (ListView) findViewById(R.id.ls);
        rl = (RelativeLayout) findViewById(R.id.rl);
        list = new ArrayList<>();
        list.add("haha");
        list.add("haha");
        list.add("haha");
        list.add("haha");
        list.add("haha");
        list.add("haha432");
        list.add("haha");
        list.add("haha");
        list.add("haha");
        list.add("haha");
        list.add("haha");
        list.add("haha456");
        list.add("haha");
        list.add("haha");
        list.add("haha");
        list.add("haha");
        list.add("haha");
        list.add("haha");
        list.add("haha");
        list.add("haha");
        list.add("haha123");
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        ls.setAdapter(adapter);
//        ls.setSelection(adapter.getCount() - 1);
        ls.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("down");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("move");
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("up");
                        break;
                }
                return false;
            }
        });

        rl.requestDisallowInterceptTouchEvent(true);
//        sv.requestDisallowInterceptTouchEvent(true);
//        ls.requestDisallowInterceptTouchEvent(true);
    }
}
