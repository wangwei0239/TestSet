package com.example.wangwei.testvoiceinteractive;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.RecognitionService;
import android.util.Log;

public class SimpleVoiceService extends RecognitionService {

    private String TAG = "TAG";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: start");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: start");
    }

    @Override
    protected void onStartListening(Intent recognizerIntent, Callback listener) {
        Log.i(TAG, "onStartListening: start");
    }

    @Override
    protected void onCancel(Callback listener) {
        Log.i(TAG, "onCancel: start");
    }

    @Override
    protected void onStopListening(Callback listener) {
        Log.i(TAG, "onStopListening: start");
    }

}
