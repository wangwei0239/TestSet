package com.example.assignment.testwechatcamera;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.cjt2325.cameralibrary.CheckPermissionsUtil;
import com.cjt2325.cameralibrary.JCameraView;

public class MainActivity extends AppCompatActivity {

    JCameraView jCameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        setContentView(R.layout.activity_main);
        jCameraView = (JCameraView) findViewById(R.id.cameraview);

        //(0.1.4+)动态权限获取
        CheckPermissionsUtil checkPermissionsUtil = new CheckPermissionsUtil(this);
        checkPermissionsUtil.requestAllPermission(this);

        jCameraView = (JCameraView) findViewById(R.id.cameraview);
//(0.0.7+)设置视频保存路径（如果不设置默认为Environment.getExternalStorageDirectory().getPath()）
        jCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath());
//(0.0.8+)设置手动/自动对焦，默认为自动对焦
        jCameraView.setAutoFoucs(false);
        jCameraView.setCameraViewListener(new JCameraView.CameraViewListener() {
            @Override
            public void quit() {
                //返回按钮的点击时间监听
                MainActivity.this.finish();
            }
            @Override
            public void captureSuccess(Bitmap bitmap) {
                //获取到拍照成功后返回的Bitmap
            }
            @Override
            public void recordSuccess(String url) {
                //获取成功录像后的视频路径
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        jCameraView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        jCameraView.onPause();
    }
}
