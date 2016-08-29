package com.example.wangwei.testlayoutanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        list = new ArrayList<>();
        for(char i = 'A'; i < 'z'; i++){
            list.add(String.valueOf(i));
        }
        adapter.addAll(list);
        lv.setAdapter(adapter);

    }
}
