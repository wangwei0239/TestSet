package com.jackwang.testdroipush;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.droi.sdk.push.DroiPush;
import com.droi.sdk.push.utils.GetDeviceIdCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(
                MainActivity.this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.WAKE_LOCK,
                        Manifest.permission.VIBRATE,
                        Manifest.permission.RECEIVE_BOOT_COMPLETED
                }, 1);

        DroiPush.getDeviceId(this, new GetDeviceIdCallback(){
            @Override
            public void onGetDeviceId(String deviceId) {
//                Toast.makeText(this,deviceId,Toast.LENGTH_LONG).show();
                Log.i("DROI_PUSH",deviceId);
            }
        });




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
