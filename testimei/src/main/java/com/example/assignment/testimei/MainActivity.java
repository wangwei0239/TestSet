package com.example.assignment.testimei;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String id = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
                .getDeviceId();
        System.out.println("IMEI:"+id);
        ((TextView)findViewById(R.id.tv)).setText("IMEI:"+id);
    }
}
