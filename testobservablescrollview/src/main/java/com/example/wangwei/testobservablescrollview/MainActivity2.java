package com.example.wangwei.testobservablescrollview;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements ObservableScrollViewCallbacks{

    private ObservableListView lv;
    private ArrayList<String> datas = new ArrayList<>();
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ObservableListView) findViewById(R.id.lv);
        btn = (Button) findViewById(R.id.btn);

        initData();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);
        lv.setAdapter(adapter);
        lv.setScrollViewCallbacks(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionBar ab = getSupportActionBar();
                    if (ab.isShowing()) {
                        ab.hide();
                    }else {
                        ab.show();
                    }
                }
        });
    }

    private void initData(){
        for(char i = 'A'; i < 'z'; i++){
            datas.add(String.valueOf(i));
        }
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        ActionBar ab = getSupportActionBar();
        if (scrollState == ScrollState.UP) {
            if (ab.isShowing()) {
                ab.hide();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (!ab.isShowing()) {
                ab.show();
            }
        }
    }
}
