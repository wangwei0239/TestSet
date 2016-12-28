package com.example.wangwei.testvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRequestQueue();
        loadGetStr("http://www.12306.cn/mormhweb/");
    }

    private void initRequestQueue() {
        mQueue = Volley.newRequestQueue(getApplicationContext());
    }

    private void loadGetStr(String url) {

        StringRequest srReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("-----------------------------------response:"+response);
                    }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("-----------------------------------networkResponse:"+error.getMessage());
            }
        });
        srReq.setShouldCache(true); // 控制是否缓存
        startVolley(srReq);
    }

    // 添加及开始请求
    private void startVolley(Request req) {

        // 设置超时时间
        // req.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        // 将请求加入队列
        mQueue.add(req);
        // 开始发起请求
        mQueue.start();
    }
}
