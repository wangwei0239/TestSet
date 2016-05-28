package com.example.wangwei.testshowkeyboard;

import android.hardware.input.InputManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String TAG = "TAG";
    ListView lv;
    Button sendBtn;
    InputMethodManager inputManager;
    static ArrayList<String> list = new ArrayList<>();
    static {
        for(int i = 0; i < 20; i++){
            list.add("测试内容"+i);
        }
    }
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        lv = (ListView) findViewById(R.id.lv);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);
        sendBtn = (Button) findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sendBtn:
                Log.i(TAG,"KeyBoard:"+isKeyShows());
                break;
        }
    }

    public boolean isKeyShows(){
        return true;
    }
}
