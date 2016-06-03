package com.example.wangwei.voiceinteractive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialogListener;


public class MainActivity extends AppCompatActivity {

    private String TAG = "TAG";
    private Button button;
    private SpeechRecognizer mIat;
    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            Log.i(TAG, "mRecogi  dialog  onResult: ");
        }

        @Override
        public void onError(SpeechError speechError) {
            Log.i(TAG, "mRecogi  dialog  onError: ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=574fdc88");
        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecord();
            }
        });
    }

    public void startRecord(){
        mIat= SpeechRecognizer.createRecognizer(this, null);
        mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin ");
        //3.开始听写
        mIat.startListening(mRecoListener);
        //听写监听器

    }

    private RecognizerListener mRecoListener = new RecognizerListener(){
        //听写结果回调接口(返回Json格式结果,用户可参见附录13.1);
        // 一般情况下会通过onResults接口多次返回结果,完整的识别内容是多次结果的累加;
        // 关于解析Json的代码可参见Demo中JsonParser类;
        //isLast等于true时会话结束。
        public void onResult(RecognizerResult results, boolean isLast) { Log.d(TAG, "result:" + results.getResultString ());}
        //会话发生错误回调接口
        public void onError(SpeechError error) {
//打印错误码描述
            Log.d(TAG, "error:" + error.getPlainDescription(true)); } //开始录音
        public void onBeginOfSpeech() {
            Log.i(TAG, "onBeginOfSpeech: ");
        } //volume音量值0~30,data音频数据
        public void onVolumeChanged(int volume, byte[] data){
            Log.i(TAG, "onVolumeChanged: ");
        } //结束录音
        public void onEndOfSpeech() {
            Log.i(TAG, "onEndOfSpeech: ");
        }
        //扩展用接口
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            Log.i(TAG, "onEvent: ");
        }
    };

//    public void showDialog(){
//        //1.创建RecognizerDialog对象
//        RecognizerDialog mDialog = new RecognizerDialog(this, mInitListener); //2.设置accent、language等参数
//        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
//        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin"); //若要将UI控件用于语义理解,必须添加以下参数设置,设置之后onResult回调返回将是语义理解 //结果
//        // mDialog.setParameter("asr_sch", "1");
//        // mDialog.setParameter("nlp_version", "2.0");
//        //3.设置回调接口
//        mDialog.setListener(mRecognizerDialogListener); //4.显示dialog,接收语音输入
//        mDialog.show();
//    }
}
