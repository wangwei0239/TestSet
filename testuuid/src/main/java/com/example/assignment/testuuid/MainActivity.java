package com.example.assignment.testuuid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        tv.setText("UUID:"+getDeviceUUID());
    }

    /**
     * @return uuid, or empty string("").
     */
    public static String getDeviceUUID() {
        try {
            Class<?> classImpl = Class.forName(
                    "com.freeme.internal.server.INativeMiscService$Impl");
            Method getInstance = classImpl.getMethod("getInstance");
            Object objectService = getInstance.invoke(classImpl);

            Class<?> classSerivce = objectService.getClass();
            Method getDeviceUUID = classSerivce.getMethod("getDeviceUUID");
            return (String) getDeviceUUID.invoke(objectService);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
