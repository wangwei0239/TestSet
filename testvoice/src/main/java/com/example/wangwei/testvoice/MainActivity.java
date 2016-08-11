package com.example.wangwei.testvoice;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private String url = "http://192.168.3.2:11801/speaker-verification";
    private String tts = "http://192.168.1.140:11803/text-to-speech";
    private String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(ttsListener);
    }

    private View.OnClickListener ttsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RequestParams rp = new RequestParams(tts+"?t=test");
//            rp.addBodyParameter("t","test");
            x.http().get(rp, new Callback.CommonCallback<File>() {
                @Override
                public void onSuccess(File result) {
                    System.out.println(result.length());
                    System.out.println("chenggong");
                    System.out.println(Environment.getExternalStorageDirectory()+"/test.mp3");
                    result.renameTo(new File(Environment.getExternalStorageDirectory(),"test.mp3"));
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    System.out.println(ex.getCause().toString());
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {
                    System.out.println("finish");
                }
            });
        }
    };

    private View.OnClickListener voiceRecognition = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RequestParams rp = new RequestParams(url);
            rp.addBodyParameter("type","clean");
            rp.addBodyParameter("subject","clean");
            x.http().post(rp, new Callback.CommonCallback<JSONObject>() {

                @Override
                public void onSuccess(JSONObject result) {
                    Log.i(TAG, "onSuccess: "+result.toString());
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.i(TAG, "onError: "+ex.getMessage()+" msg:"+ex.toString());
                    ex.printStackTrace();
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {
                    Log.i(TAG, "onFinished: finish");
                }
            });
        }
    };

    public static URLConnection sendPostRequest(String url,
                                                Map<String, String> params, Map<String, String> headers)
            throws Exception {
        StringBuilder buf = new StringBuilder();
        Set<Map.Entry<String, String>> entrys = null;
        // 如果存在参数，则放在HTTP请求体，形如name=aaa&age=10
        if (params != null && !params.isEmpty()) {
            entrys = params.entrySet();
            for (Map.Entry<String, String> entry : entrys) {
                buf.append(entry.getKey()).append("=")
                        .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                        .append("&");
            }
            buf.deleteCharAt(buf.length() - 1);
        }
        URL url1 = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        OutputStream out = conn.getOutputStream();
        out.write(buf.toString().getBytes("UTF-8"));
        if (headers != null && !headers.isEmpty()) {
            entrys = headers.entrySet();
            for (Map.Entry<String, String> entry : entrys) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        System.out.println(conn.getResponseCode()); // 为了发送成功
        return conn;
    }


}
