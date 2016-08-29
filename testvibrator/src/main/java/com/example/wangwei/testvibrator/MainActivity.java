package com.example.wangwei.testvibrator;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
    private Vibrator vibrator = null;
    private ToggleButton tb1 = null, tb2 = null;
    private TextView tv1 = null, tv2 = null;
    private NotificationManager notificationManager;
    private static final int NOTIFICATION_FLAG = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注意模拟器是模拟不了震动的，得真机测试哦
        //创建vibrator对象
        vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tb1 = (ToggleButton) findViewById(R.id.tb1);
        tb2 = (ToggleButton) findViewById(R.id.tb2);
        tb1.setOnCheckedChangeListener(listener);
        tb2.setOnCheckedChangeListener(listener);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    OnCheckedChangeListener listener = new OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            ToggleButton toggleButton = (ToggleButton) buttonView;
            switch (toggleButton.getId()) {
                case R.id.tb1:
                    if (isChecked) {
                        //根据指定的模式进行震动
                        //第一个参数：该数组中第一个元素是等待多长的时间才启动震动，
                        //之后将会是开启和关闭震动的持续时间，单位为毫秒
                        //第二个参数：重复震动时在pattern中的索引，如果设置为-1则表示不重复震动
                        vibrator.vibrate(new long[]{1000, 50, 50, 100, 50}, -1);
                        tv1.setText("振动已启动");
                    } else {
                        //关闭震动
                        vibrator.cancel();
                        tv1.setText("震动已关闭");
                    }
                    break;
                case R.id.tb2:
                    if (isChecked) {
                        sentNotif();
                        //启动震动，并持续指定的时间
                        vibrator.vibrate(3500);
                        tv2.setText("振动已启动");
                    } else {
                        //关闭启动
                        vibrator.cancel();
                        tv2.setText("震动已关闭");
                    }
                    break;
            }
        }

    };

    public void sentNotif(){
        Notification notify2 = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("小影:")// 设置在status
                // bar上显示的提示文字
                .setContentTitle("小影")// 设置在下拉status
                // bar后Activity，本例子中的NotififyMessage的TextView中显示的标题
                .setSound(Uri.parse("android.resource://" + getPackageName() + "/"+ R.raw.notif))
                .setNumber(1)// 在TextView的右方显示的数字，可放大图片看，在最右侧。这个number同时也起到一个序列号的左右，如果多个触发多个通知（同一ID），可以指定显示哪一个。
//                .setDefaults(Notification.DEFAULT_SOUND)
                .getNotification(); // 需要注意build()是在API level
        // 16及之后增加的，在API11中可以使用getNotificatin()来代替
        notify2.flags |= Notification.FLAG_AUTO_CANCEL;
        if (notificationManager == null) {
            notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        notificationManager.notify(NOTIFICATION_FLAG, notify2);
    }
}
