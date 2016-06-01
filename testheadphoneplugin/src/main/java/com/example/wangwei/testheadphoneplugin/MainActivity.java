package com.example.wangwei.testheadphoneplugin;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private HeadsetPlugReceiver headsetPlugReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerHeadsetPlugReceiver();
    }

    private void registerHeadsetPlugReceiver(){
        headsetPlugReceiver  = new HeadsetPlugReceiver ();
        IntentFilter  filter = new IntentFilter();
        filter.addAction("android.intent.action.HEADSET_PLUG");
        registerReceiver(headsetPlugReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver();  //注销监听
    }
    private void unregisterReceiver(){
        this.unregisterReceiver(headsetPlugReceiver);
    }
}
