package com.example.wangwei.testlistviewlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        ArrayList<String> strings = new ArrayList<>();
        for(int i = 0 ; i< 20; i++){
            strings.add("hha");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.test_layout,strings){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.test_layout,null);
                }
                return convertView;
            }
        };
        lv.setAdapter(adapter);
    }
}
