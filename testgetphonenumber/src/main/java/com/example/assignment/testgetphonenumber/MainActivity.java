package com.example.assignment.testgetphonenumber;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TelephonyManager tm = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceid = tm.getDeviceId();//获取智能设备唯一编号
        String tel  = tm.getLine1Number();//获取本机号码
        String imei = tm.getSimSerialNumber();//获得SIM卡的序号
        String imsi = tm.getSubscriberId();//得到用户Id
        StringBuilder sb = new StringBuilder();
        sb.append("IMEI:"+deviceid+"\n");
        sb.append("Phone Number:"+tel+"\n");
        sb.append("SimId:"+imei+"\n");
        sb.append("用户id:"+imsi+"\n");
        ((TextView)findViewById(R.id.tv)).setText(sb.toString());
    }
}
