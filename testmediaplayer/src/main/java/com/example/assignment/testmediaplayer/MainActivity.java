package com.example.assignment.testmediaplayer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MediaPlayer mediaPlayer = new MediaPlayer();
//        try {
//            mediaPlayer.setDataSource(getResources().openRawResourceFd(R.raw.green_sky).getFileDescriptor());
//            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    Log.i("LOG","prepared");
//                }
//            });
//            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    Log.i("LOG","completion");
//                }
//            });
//            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//                @Override
//                public boolean onError(MediaPlayer mp, int what, int extra) {
//                    Log.i("LOG","error");
//                    return false;
//                }
//            });
//            mediaPlayer.prepareAsync();
//            mediaPlayer.pause();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        final TextView tv = (TextView) findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator showPlayerAnim = ObjectAnimator.ofFloat(tv, "translationX", -900, -100, -900, 0).setDuration(2600);

                showPlayerAnim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Log.i("AudioPlayer","onAnimationEnd:showplayer chu");
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        Log.i("AudioPlayer","onAnimationStart:showplayer chu");
                    }
                });
                showPlayerAnim.start();
                final ObjectAnimator showPlayerAnim2 = ObjectAnimator.ofFloat(tv, "translationX", -900).setDuration(2600);

                showPlayerAnim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Log.i("AudioPlayer","onAnimationEnd:showplayer");
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        Log.i("AudioPlayer","onAnimationStart:showplayer");
                    }
                });

                tv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showPlayerAnim2.start();
                    }
                },300);
            }
        });



    }
}
