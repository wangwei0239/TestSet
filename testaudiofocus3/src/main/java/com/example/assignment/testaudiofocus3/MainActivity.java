package com.example.assignment.testaudiofocus3;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer1;
    private MediaPlayer mediaPlayer2;

    private Button play1;
    private Button play2;
    private Button pause1;
    private Button pause2;

    private AudioManager audioManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer1 = MediaPlayer.create(MainActivity.this,R.raw.green_sky);
        mediaPlayer2 = MediaPlayer.create(MainActivity.this,R.raw.trent);

        play1 = (Button) findViewById(R.id.btn_play);
        play2 = (Button) findViewById(R.id.btn_play2);
        pause1 = (Button) findViewById(R.id.btn_pause);
        pause2 = (Button) findViewById(R.id.btn_pause2);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = audioManager.requestAudioFocus(listener1,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer1.start();
                }
            }
        });

        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = audioManager.requestAudioFocus(listener2,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer2.start();
                }
            }
        });

        pause1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = audioManager.abandonAudioFocus(listener1);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer1.pause();
                }
            }
        });

        pause2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = audioManager.abandonAudioFocus(listener2);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer2.pause();
                }
            }
        });
    }

    private AudioManager.OnAudioFocusChangeListener listener1 = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_LOSS:
                    Log.d("Test", "1111-------AudioFocus: received AUDIOFOCUS_LOSS");
                    mediaPlayer1.pause();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    Log.d("Test", "1111-------AudioFocus: received AUDIOFOCUS_LOSS_TRANSIENT");
                    mediaPlayer1.pause();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    Log.d("Test", "1111-------AudioFocus: received AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    Log.d("Test", "1111-------AudioFocus: received AUDIOFOCUS_GAIN");
                    mediaPlayer1.start();
                    break;
                default:
                    Log.d("Test", "1111-------Unknown audio focus change code");
            }
        }
    };


    private AudioManager.OnAudioFocusChangeListener listener2 = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_LOSS:
                    Log.d("Test", "2222-------AudioFocus: received AUDIOFOCUS_LOSS");
                    mediaPlayer2.pause();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    Log.d("Test", "2222-------AudioFocus: received AUDIOFOCUS_LOSS_TRANSIENT");
                    mediaPlayer2.pause();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    Log.d("Test", "2222-------AudioFocus: received AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    Log.d("Test", "2222-------AudioFocus: received AUDIOFOCUS_GAIN");
                    mediaPlayer2.start();
                    break;
                default:
                    Log.d("Test", "2222-------Unknown audio focus change code");
            }
        }
    };
}
