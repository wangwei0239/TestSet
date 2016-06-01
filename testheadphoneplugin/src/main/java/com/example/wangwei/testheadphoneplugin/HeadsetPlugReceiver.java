package com.example.wangwei.testheadphoneplugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by wangwei on 16/6/1.
 */
public class HeadsetPlugReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        if(intent.hasExtra("state")){
            if(intent.getIntExtra("state", 0)==0){
                Toast.makeText(context, "headset not connected", Toast.LENGTH_LONG).show();
            }
            else if(intent.getIntExtra("state", 0)==1){
                Toast.makeText(context, "headset  connected", Toast.LENGTH_LONG).show();
            }
        }
    }

}
