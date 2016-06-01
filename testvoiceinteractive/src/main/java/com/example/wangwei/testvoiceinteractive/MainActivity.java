package com.example.wangwei.testvoiceinteractive;

import android.content.Intent;
import android.speech.RecognitionService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, SimpleVoiceService.class));
    }
}
