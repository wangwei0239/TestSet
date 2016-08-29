package com.example.wangwei.testxutils;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final String BASEURL = "http://mail.emotibot.com.cn:8009/api/APP/";
    public static final String LOGIN   = BASEURL + "register.php";
    public static final String CHAT    = BASEURL + "chat.php";

    private Button btn;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestParams rp = new RequestParams(CHAT);
                rp.addBodyParameter("haha","haha");

                System.out.println("Detail:"+rp.getUri());

                x.http().get(rp, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("Success:"+result);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("Error:"+ex.getMessage());
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {
                        System.out.println("Finish");
                    }
                });
            }
        });

        iv = (ImageView) findViewById(R.id.iv);
        String file = Environment.getExternalStorageDirectory() + "/flyme1.jpg";
        if(new File(file).exists()){
            System.out.println("exist");
        }else {
            System.out.println("not exist");
        }
        System.out.println(file);
        x.image().bind(iv,file);
    }
}
