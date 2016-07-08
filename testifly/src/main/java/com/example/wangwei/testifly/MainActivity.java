package com.example.wangwei.testifly;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iflytek.business.speech.RecognitionListener;
import com.iflytek.business.speech.RecognizerResult;
import com.iflytek.business.speech.SpeechIntent;
import com.iflytek.business.speech.SpeechServiceUtil;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private SpeechServiceUtil mService;
    private final int RESULT_MESSAGE = 0;
    private String TAG = "TAG";
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.contentHolder);
        button = (Button) findViewById(R.id.btn);

        Intent serviceIntent = new Intent();
        serviceIntent.putExtra(SpeechIntent.SERVICE_LOG_ENABLE, true);
//        mService = new SpeechServiceUtil(this, mInitListener, serviceIntent);
        mSharedPreferences = getSharedPreferences("TTS", Activity.MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecord();
            }
        });
    }

    SpeechServiceUtil.ISpeechInitListener mInitListener = new SpeechServiceUtil.ISpeechInitListener(){
        @Override
        public void onSpeechInit(int arg0) {
            Log.d(TAG, "onSpeechInit start----");

            Intent iatIntent = new Intent();
            Bundle mBundle = new Bundle();
            mBundle.putInt(SpeechIntent.ARG_RES_TYPE,
                    SpeechIntent.RES_FROM_ASSETS);

            iatIntent.putExtra(SpeechIntent.ENGINE_LOCAL_DEC, mBundle);

            mService.initRecognitionEngine(iatListener, iatIntent);
            Log.d(TAG, "onSpeechInit end-----------");
        }

        @Override
        public void onSpeechUninit() {
            Log.d(TAG, "onSpeechUninit");
        }


    };

    RecognitionListener.Stub iatListener = new RecognitionListener.Stub(){

        @Override
        public void onEnd(Intent arg0) throws RemoteException {
            Log.d(TAG, "onEnd----arg0= " + arg0);
        }

        @Override
        public void onError(int arg0) throws RemoteException {
            Log.d(TAG, "onError" + arg0);
        }

        @Override
        public void onGrammarResult(int arg0, String arg1, int arg2)
                throws RemoteException {
            Log.d(TAG, "onGrammarResult----arg0= " + arg0);
        }

        @Override
        public void onInit(int arg0) throws RemoteException {
            Log.d(TAG, "onInit");
        }

        @Override
        public void onPartialResult(RecognizerResult arg0)
                throws RemoteException {
            Log.d(TAG, "onPartialResult----arg0= " + arg0);
        }

        @Override
        public void onRecordEnd() throws RemoteException {
            Log.d(TAG, "onRecordEnd");
        }

        @Override
        public void onRecordStart() throws RemoteException {
            Log.d(TAG, "onRecordStart");
        }

        @Override
        public void onResult(RecognizerResult arg0) throws RemoteException {
            Log.d(TAG, "onResult---" + arg0);
            Message msg = new Message();
            msg.what = RESULT_MESSAGE;
            msg.obj = arg0;
            mHandler.sendMessage(msg);
        }

        @Override
        public void onSpeechEnd() throws RemoteException {
            Log.d(TAG, "onSpeechEnd");
        }

        @Override
        public void onSpeechStart() throws RemoteException {
            Log.d(TAG, "onSpeechStart");
        }

        @Override
        public void onVolumeGet(int arg0) throws RemoteException {
            Log.d(TAG, "onVolumeGet----arg0= " + arg0);
        }
    };

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case RESULT_MESSAGE:
                    textView.setText(msg.obj.toString());
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

    };

    private void startRecord(){
        Intent intent1 = new Intent();
        // VAD前端点时间
        if(mSharedPreferences.getString("iat_engine", "2").equals("2")){
            intent1.putExtra(SpeechIntent.EXT_ENGINE_TYPE, SpeechIntent.ENGINE_LOCAL| SpeechIntent.ENGINE_WEB);
        }else if(mSharedPreferences.getString("iat_engine", "2").equals("1")){
            intent1.putExtra(SpeechIntent.EXT_ENGINE_TYPE, SpeechIntent.ENGINE_WEB);
        }else if(mSharedPreferences.getString("iat_engine", "2").equals("0")){
            intent1.putExtra(SpeechIntent.EXT_ENGINE_TYPE, SpeechIntent.ENGINE_LOCAL);
        }
        // VAD后端点时间

        intent1.putExtra(SpeechIntent.EXT_VAD_FRONT_TIME,
                Integer.valueOf(mSharedPreferences.getString("vad_front_time", "3000")));

        // VAD后端点时间

        intent1.putExtra(SpeechIntent.EXT_VAD_END_TIME,
                Integer.valueOf(mSharedPreferences.getString("vad_end_time", "2000")));



        // 识别引擎结果得分
        intent1.putExtra(SpeechIntent.EXT_LOCAL_RESULT_SCORE,
                Integer.valueOf(mSharedPreferences.getString("iat_result_score", "60")));
        // 识别交互的最长时间
        intent1.putExtra(SpeechIntent.SESSION_TIMEOUT,
                Integer.valueOf(mSharedPreferences.getString("session_timeout", "8000")));

        intent1.putExtra(SpeechIntent.EXT_LOCAL_SCENE, "sms.irf");
        // 识别语音数据保存
        intent1.putExtra(SpeechIntent.EXT_PCM_LOG, mSharedPreferences.getBoolean("ivp_save", false));
        mService.startRecognize(intent1);
    }
}
