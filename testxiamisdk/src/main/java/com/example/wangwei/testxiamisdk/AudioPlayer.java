package com.example.wangwei.testxiamisdk;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.xiami.sdk.utils.Encryptor;

import java.io.IOException;

/**
 * Created by Hector on 16/8/15.
 */
public class AudioPlayer {

    private static final String TAG = AudioPlayer.class.getSimpleName();
    private static final String THREAD_SYSTEM_PLAYER = "system_player";

    public static final int MEDIA_XIMALAYA = 1;
    public static final int MEDIA_SYSTEM = 2;

    private static AudioPlayer ourInstance  = null;

    private int mSelectedMedia = MEDIA_XIMALAYA;

    private MediaPlayer mSystemMediaPlayer;

    private AudioPlayerCallback mCallback;

    private Context context;

    public static AudioPlayer getInstance(Context context) {
        if(ourInstance == null){
            ourInstance = new AudioPlayer(context);
        }
        return ourInstance;
    }

    private AudioPlayer(Context context) {
        systemPlayerSetup();
        this.context = context;
    }

    public void setCallback(AudioPlayerCallback callback) {
        this.mCallback = callback;
    }

    public void pause() {
        checkPlayerIsNull();
        if (mSelectedMedia == MEDIA_SYSTEM) {
            mSystemMediaPlayer.pause();
        }
    }

    public void stop() {
        checkPlayerIsNull();
        if (mSelectedMedia == MEDIA_SYSTEM) {
            mSystemMediaPlayer.stop();
        }
    }

    public void play(String url) {
        checkPlayerIsNull();
        playXiamiMusic(url);
    }

    public void release() {
        if (mSystemMediaPlayer != null) {
            mSystemMediaPlayer.release();
            mSystemMediaPlayer = null;
        }
    }

    private void playXiamiMusic(String url) {
            final String targetListenFile;
            try {
                targetListenFile = Encryptor.decryptUrl(url);
            } catch (Throwable throwable) {
                Log.i("LOG","no so file");
                return;
            }
            mSystemMediaPlayer.reset();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mSystemMediaPlayer.setDataSource(context.getApplicationContext(),
                                Uri.parse(targetListenFile));
                        mSystemMediaPlayer.prepare();
                        mSystemMediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.i("LOG","no so file");
                    }

                }
            }).start();
    }

    private void checkPlayerIsNull() {
        if (mSystemMediaPlayer == null) {
            systemPlayerSetup();
        }
    }


    private void systemPlayerSetup() {
        mSystemMediaPlayer = new MediaPlayer();

    }

    public interface AudioPlayerCallback {
        void updateViewStatus();
        void displayMessageProducedByAudioPlayer(String msg);
    }

}
