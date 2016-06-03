package com.example.wangwei.voiceinteractive2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangwei.voiceinteractive2.Model.ResultModel;
import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpManagerImpl;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final int STATE_NORMAL = 0x00;
    private static final int STATE_LISTENING = 0x01;
    private static final int STATE_SERVER_PROCESS = 0x02;
    private static final int STATE_SYNTHESIZER = 0x03;
    private static final int STATE_PLAYING = 0x04;
    private boolean runing = false;
    private String URL = "http://mail.emotibot.com.cn:808/api/APP/chat.php";
    private int userid = 141;
    private Gson gson = new Gson();
    private StringBuilder sb;
    private String TAG = "TAG";
    private Button button;
    private SpeechRecognizer mIat;
    private ResultModel rm;
    private SpeechSynthesizer mTts;
    private LinearLayout chatCotnent;
    private LinearLayout loadingLy;
    private TextView qContent;
    private TextView aContent;
    private TextView loadingContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=574fdc88");
        sb = new StringBuilder();
        chatCotnent = (LinearLayout) findViewById(R.id.chatContent);
        loadingLy = (LinearLayout) findViewById(R.id.loadingLy);
        qContent = (TextView) findViewById(R.id.qcontent);
        aContent = (TextView) findViewById(R.id.acontent);
        loadingContent = (TextView) findViewById(R.id.loadContent);
        button = (Button) findViewById(R.id.btn);
        changeState(STATE_NORMAL,"","");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runing = !runing;
                if(runing){
                    button.setText("点击停止");
                    startRecord();
                }else {
                    stopAll();
                    button.setText("点击开始对话");
                    changeState(STATE_NORMAL,"","");
                }
            }
        });
    }

    public void stopAll(){
        mIat.stopListening();
        mTts.stopSpeaking();
        runing = false;

    }

    public void changeState(int state,String q,String a){
        switch (state){
            case STATE_NORMAL:
                qContent.setText("Q:");
                aContent.setText("A:");
                loadingContent.setText("状态:等待说话...");
                break;
            case STATE_LISTENING:
                loadingContent.setText("状态:语音识别中...");
                break;
            case STATE_SERVER_PROCESS:
                qContent.setText("Q:"+q);
                aContent.setText("A:");
                loadingContent.setText("状态:服务器处理中...");
                break;
            case STATE_SYNTHESIZER:
                aContent.setText("A:"+a);
                loadingContent.setText("状态:语音合成中...");
                break;
            case STATE_PLAYING:
                loadingContent.setText("状态:语音播放中...");
                break;
        }
    }


//---------------------------连接服务器----------------------------------

    public void connectToServer(String input){
        if(!runing){
            return;
        }
        changeState(STATE_SERVER_PROCESS,input,"");
        String checkSum = CHKUtil.getCHKNum(String.valueOf(userid));
        String time = CHKUtil.getTime();
        try {
            input = URLEncoder.encode(input, "UTF-8");
            System.out.println("@@@@@@编码成功@@@@@@@@");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("@@@@@@编码错误@@@@@@@@");
        }
        String url = URL+"?UserID="+userid+"&chk="+checkSum+"&time="+time+"&text="+input;

        RequestParams params = new RequestParams(url);
        Log.i(TAG, "connectToServer: "+url);

        x.http().get(params, callback);



    }

    Callback.CommonCallback<String> callback = new Callback.CommonCallback<String>() {
        @Override
        public void onSuccess(String result) {
            if(runing){
                Log.i(TAG, "onSuccess: http");
                String resultString = handleChatResult(result);
                startSynthesizer(resultString);
            }
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            Log.i(TAG, "onError: http"+ex.getMessage());
        }

        @Override
        public void onCancelled(Callback.CancelledException cex) {
            Log.i(TAG, "onCancelled: http");
        }

        @Override
        public void onFinished() {

        }
    };


    //---------------------------------------语音合成-----------------------------------

    private void startSynthesizer(String content) {
        if(!runing)
            return;
        changeState(STATE_SYNTHESIZER,"",content);
        mTts = SpeechSynthesizer.createSynthesizer(this, null);
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
        //设置发音人
         mTts.setParameter(SpeechConstant.SPEED, "50");
        //设置语速
         mTts.setParameter(SpeechConstant.VOLUME, "80");
        // 设置音量,范围 0~100
         mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置云端
        // 设置合成音频保存位置(可自定义保存位置),保存在“./sdcard/iflytek.pcm”
        //保存在 SD 卡需要在AndroidManifest.xml 添加写 SD 卡权限
        // 仅支持保存为 pcm 和 wav 格式,如果不需要保存合成音频,注释该行代码
         mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
        // 3.开始合成
        mTts.startSpeaking(content, mSynListener);

    }

    private SynthesizerListener mSynListener = new SynthesizerListener(){
        //会话结束回调接口,没有错误时,error为null
        public void onCompleted(SpeechError error) {
            Log.i(TAG, "onCompleted: synthesizer");
            startRecord();
        }
        //缓冲进度回调
        // percent为缓冲进度0~100,beginPos为缓冲音频在文本中开始位置,endPos表示缓冲音频在文本中结束位置,info为附加信息。
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {}
        //开始播放
        public void onSpeakBegin() {changeState(STATE_PLAYING,"","");}
        //暂停播放
        public void onSpeakPaused() {}
        //播放进度回调.
        //percent为播放进度0~100,beginPos为播放音频在文本中开始位置,endPos表示播放音频在文本中结束位置.
        public void onSpeakProgress(int percent, int beginPos, int endPos) {} //恢复播放回调接口
        public void onSpeakResumed() {}
        //会话事件回调接口
        public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {}
    };


    //----------------------------识别--------------------------------

    public void startRecord() {
        if (!runing)
            return;
        mIat = SpeechRecognizer.createRecognizer(this, null);
        mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin ");
        //3.开始听写
        mIat.startListening(mRecoListener);
        //听写监听器


    }

    private RecognizerListener mRecoListener = new RecognizerListener() {
        public void onResult(RecognizerResult results, boolean isLast) {
            String content = parseJsonToString(results);
            if(content.length()>1){
                connectToServer(content);
            }
            mIat.stopListening();


        }

        public void onError(SpeechError error) {
            Log.i(TAG, "error:" + error.getErrorCode());
            if(error.getErrorCode() == 10118){
                startRecord();
            }
        } //开始录音

        public void onBeginOfSpeech() {
            Log.i(TAG, "onBeginOfSpeech: ");
            changeState(STATE_LISTENING,"","");
        } //volume音量值0~30,data音频数据

        public void onVolumeChanged(int volume, byte[] data) {
        }

        public void onEndOfSpeech() {
            Log.i(TAG, "onEndOfSpeech: ");
        }

        //扩展用接口
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            Log.i(TAG, "onEvent: evetType "+eventType);
        }
    };

    private String parseJsonToString(RecognizerResult results) {
        if (sb.length() > 0)
            sb.delete(0, sb.length());
        rm = gson.fromJson(results.getResultString(), ResultModel.class);
        final ArrayList<ResultModel.WSModel> ws = rm.getWs();
        for (ResultModel.WSModel wsm : ws) {
            final ArrayList<ResultModel.WSModel.CWModel> cw = wsm.getCw();
            for (ResultModel.WSModel.CWModel cwm : cw) {
                sb.append(cwm.getW());
            }
        }
        Log.i(TAG, "Question:" + sb.toString());
        return sb.toString();
    }
    //--------------------------utils----------------------------
    private String handleChatResult(String input)
    {

        try {
            JSONObject jsonObject = new JSONObject(input);
            int resultCode = jsonObject.getInt("return");
            switch (resultCode)
            {
                case 0:
                    return jsonObject.getString("answer");
                case -1:
                    return "服务器错误";
                default:
                    return "代码错误";

            }
        } catch (JSONException e) {
            e.printStackTrace();
            System.err.println("ChatActivity---handleChatResult---JSON解析错误");
            return "JSON解析错误";
        }
    }

}

