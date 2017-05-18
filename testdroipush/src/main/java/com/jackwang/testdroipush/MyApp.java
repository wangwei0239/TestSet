package com.jackwang.testdroipush;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.droi.sdk.push.DroiMessageHandler;
import com.droi.sdk.push.DroiPush;

import static android.R.id.message;

/**
 * Created by wangwei on 17/4/7.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        Core.initialize(this);
        DroiPush.initialize(this);
        DroiPush.setMessageHandler(new DroiMessageHandler() {
            @Override
            public void onHandleCustomMessage(Context context, String s) {
                Toast.makeText(context, "Demo Custom Msg:" + message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
