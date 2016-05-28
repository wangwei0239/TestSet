package com.example.wangwei.testalarmmanager;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NotificationManager ntmanager;
    private static final int NOTIFICATION_FLAG = 1;
    private String TAG = "testalarmmanager";
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(!isTopActivity("com.example.wangwei.testalarmmanager",MainActivity.this)){
                sendNotification(MainActivity.this, "haha");
//                System.out.println("noti");
                Log.i(TAG,"noti");
            }else {
                Log.i(TAG, "not noti");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ntmanager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        setContentView(R.layout.activity_main);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
//        PendingIntent pi = PendingIntent.getActivity(this,0,new Intent(this,JumpedActivity.class),0);
//        PendingIntent pi2 = PendingIntent.getActivity(this,0,new Intent(this,Jumped2.class),0);
//        am.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + 5000,pi);
//        am.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + 8000,pi2);
        new Thread(){
            @Override
            public void run() {
                long des = System.currentTimeMillis() + 3000;
                long start = System.currentTimeMillis();
                while(System.currentTimeMillis() <= des)
                {
                    System.out.println("DES: " + (System.currentTimeMillis() - start)/1000);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    private boolean isTopActivity(String packageName,Context mContext)
    {
        //_context是一个保存的上下文
        ActivityManager am = (ActivityManager) mContext.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list = am.getRunningAppProcesses();
        if(list.size() == 0) return false;
        for(ActivityManager.RunningAppProcessInfo process:list)
        {
            if(process.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND &&
                    process.processName.equals(packageName))
            {
                if(isScreenOn(this))
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

    public void sendNotification(Context mContext,String text){
        PendingIntent pendingIntent2 = PendingIntent.getActivity(mContext, 0,
                new Intent(mContext, MainActivity.class), 0);
        Notification notify2 = new Notification.Builder(mContext)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("小影:" + text)// 设置在status
                        // bar上显示的提示文字
                .setContentTitle("小影")// 设置在下拉status
                        // bar后Activity，本例子中的NotififyMessage的TextView中显示的标题
                .setContentText(text)// TextView中显示的详细内容
                .setContentIntent(pendingIntent2) // 关联PendingIntent
                .setNumber(1)// 在TextView的右方显示的数字，可放大图片看，在最右侧。这个number同时也起到一个序列号的左右，如果多个触发多个通知（同一ID），可以指定显示哪一个。
                .setDefaults(Notification.DEFAULT_SOUND)
                .getNotification(); // 需要注意build()是在API level
        // 16及之后增加的，在API11中可以使用getNotificatin()来代替
        notify2.flags |= Notification.FLAG_AUTO_CANCEL;
        if(ntmanager == null){
            ntmanager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        ntmanager.notify(NOTIFICATION_FLAG, notify2);
        lightScreen(mContext);
    }

    public void lightScreen(Context mContext){
        PowerManager pm=(PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "bright");
        wl.acquire();
        wl.release();
    }

    public boolean isScreenOn(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (pm.isScreenOn()) {
            return true;
        }
        return false;
    }
}
