package com.example.wangwei.testtouchconflict2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv1,lv2,lv3;
    private ArrayList<String> datas = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv1 = (ListView) findViewById(R.id.lv1);
        lv2 = (ListView) findViewById(R.id.lv2);
        lv3 = (ListView) findViewById(R.id.lv3);
        datas.add("Test1");
        datas.add("Test2");
        datas.add("Test3");
        datas.add("Test4");
        datas.add("Test5");
        datas.add("Test6");
        datas.add("Test7");
        datas.add("Test8");
        datas.add("Test9");
        datas.add("Test0");
        datas.add("Test1");
        datas.add("Test2");
        datas.add("Test3");
        datas.add("Test4");
        datas.add("Test5");
        datas.add("Test6");
        datas.add("Test7");
        datas.add("Test8");
        datas.add("Test9");
        datas.add("Test0");
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);
        lv1.setAdapter(adapter);
        lv2.setAdapter(adapter);
        lv3.setAdapter(adapter);

    }
}
