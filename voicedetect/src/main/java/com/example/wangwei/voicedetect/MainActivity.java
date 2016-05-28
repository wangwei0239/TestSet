package com.example.wangwei.voicedetect;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener{

    private TextView contentView;
    private Button btn;
    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;
    private String PATH_NAME;
    private Button play;
    private boolean countTime = false;
    private int sec;
    private String TAG = "TAG";
    private RelativeLayout pbly;
    private String url = "http://mail.emotibot.com.cn:8009/Emotibot/api/APP/testVoice.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        Log.i(TAG,getFilesDir().getAbsolutePath());
    }


    private void initData() {
        PATH_NAME = Environment.getExternalStorageDirectory().getAbsolutePath()+"/voice.amr";
        btn.setOnTouchListener(this);
        play.setOnClickListener(this);
    }

    private void initView() {
        contentView = (TextView) findViewById(R.id.content);
        btn = (Button) findViewById(R.id.btn);
        play = (Button) findViewById(R.id.play);
        pbly = (RelativeLayout) findViewById(R.id.pbly);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                playVoice();
                break;
        }
    }

    public void playVoice(){
        mPlayer = new MediaPlayer();
        try{
            mPlayer.setDataSource(PATH_NAME);
            mPlayer.prepare();
            mPlayer.start();
        }catch(IOException e){
            Log.e("TAG","播放失败");
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startRecordListener();
                break;
            case MotionEvent.ACTION_UP:
                releaseBtn();
                break;
            case MotionEvent.ACTION_CANCEL:
                releaseBtn();
                break;
        }
        return false;
    }

    public void startRecordListener(){
        setTimeCount();
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
        mRecorder.setOutputFile(PATH_NAME);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e("TAG", "prepare() failed");
        }
        mRecorder.start();
    }

    public void stopRecordListener() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    public void releaseBtn(){


        stopRecordListener();
        if(sec > 1){
            stopTimeCount("请说话...");
            contentView.setVisibility(View.INVISIBLE);
            pbly.setVisibility(View.VISIBLE);
            btn.setEnabled(false);
            sendVoiceToWeb();
        }else {
            stopTimeCount("录音时间过短");
        }

    }

    public void finishUpload(){
        contentView.setVisibility(View.VISIBLE);
        pbly.setVisibility(View.INVISIBLE);
        btn.setEnabled(true);
    }

    public void sendVoiceToWeb(){
        RequestParams rp = new RequestParams(url);
        rp.setMultipart(true);
        rp.addBodyParameter("voice",new File(PATH_NAME));
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                contentView.setText(parseJson(result));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                contentView.setText("上传文件失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                contentView.setText("上传文件被取消");
            }

            @Override
            public void onFinished() {
                finishUpload();
            }
        });

    }

    public String parseJson(String data){
        String resultString = null;
        try {
            JSONObject jsonObject = new JSONObject(data);
            int resultCode = jsonObject.getInt("return");
            if(resultCode == 0){
                resultString = jsonObject.getString("msg");
            }else {
                resultString = "服务器返回错误";
            }
        } catch (JSONException e) {
            resultString = "服务器返回错误";
            e.printStackTrace();
        }finally {
            return resultString;
        }

    }





    public void setTimeCount(){
        new Thread(){
            @Override
            public void run() {
                if(countTime){
                    return;
                }
                countTime = true;
                while (countTime){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            contentView.setText("已录音:"+sec+"秒");
                            sec++;
                        }
                    });
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void stopTimeCount(String tip){
        countTime = false;
        sec = 0;
        contentView.setText(tip);
    }
}
