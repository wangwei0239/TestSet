package com.example.wangwei.testgeneralviewholder;

import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private BaseAdapter baseAdapter;
    private ArrayList<People> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        datas = new ArrayList<>();
        datas.add(new People("name1",1));
        datas.add(new People("name2",2));
        datas.add(new People("name3",3));
        datas.add(new People("name4",4));
        datas.add(new People("name5",5));
        datas.add(new People("name6",6));
        datas.add(new People("name7",7));
        ViewCompat.animate(lv);
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return null;
            }
        };
        startActivity(new Intent());
    }

}
