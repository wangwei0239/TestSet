package com.example.wangwei.testlaterdismissdialog;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private Dialog dialog;
    private Button btn;
    private static String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialog.show();
//                handler.sendEmptyMessageDelayed(1,2000);
                Message msg = Message.obtain(handler, new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("----------我是msg callback");
                    }
                });
                handler.sendMessage(msg);
//                handler.sendMessage(Message.obtain());

            }
        });
        dialog = new Dialog(this,R.style.Theme_AudioDialog);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();

    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    }){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };


    private static class WeekReferenceHandler extends Handler {

        private final WeakReference<MainActivity> mActivity;

        public WeekReferenceHandler(MainActivity mActivity) {

            this.mActivity = new WeakReference<>(mActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            System.out.println("--------我是handler handle msg");
//            if(msg.what == 0){
//                mActivity.get().dialogDismiss();
//            }else {
//                mActivity.get().finish();
//            }
        }
    }

    private void dialogDismiss(){
        Log.i(TAG, "handleMessage: ");

        if(this.isDestroyed()){
            return;
        }

        Log.i(TAG, "handleMessage: return");

        if(dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    @Override
    public void finish() {
        super.finish();
        handler.sendEmptyMessageDelayed(0,2000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        dialogDismiss();
    }

    @Override
    protected void onDestroy() {
        dialogDismiss();
        super.onDestroy();

    }
}
