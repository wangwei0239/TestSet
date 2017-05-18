package com.jackwang.testopengl;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView mySurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySurfaceView = (GLSurfaceView)findViewById(R.id.my_surface_view);
        mySurfaceView.setEGLContextClientVersion(2);


    }
}
