package com.example.wangwei.wechatvoice;


import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    private ListView mListView;
    private ArrayAdapter<Recorder> mAdapter;
    private List<Recorder> mDatas = new ArrayList<>();

    private RecordBtn mRecoderBtn;
    private View animView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView1);
        mRecoderBtn = (RecordBtn) findViewById(R.id.recordBtn1);
        mRecoderBtn.setAudioFinishRecorderListener(new RecordBtn.AudioFinishRecorderListener() {

            @Override
            public void onFinish(float seconds, String filePath) {
                Recorder recorder = new Recorder(seconds, filePath);
                mDatas.add(recorder);
                mAdapter.notifyDataSetChanged();
                mListView.setSelection(mDatas.size() - 1);
            }

        });

        mAdapter = new RecorderAdapter(this, mDatas);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (animView != null) {
                    animView.setBackgroundResource(R.drawable.adj);
                    animView = null;
                }
                animView = view.findViewById(R.id.id_recorder_anim);
                animView.setBackgroundResource(R.drawable.play_anim);
                AnimationDrawable anim = (AnimationDrawable) animView.getBackground();
                anim.start();
                //播放音频
                MediaManager.playSound(mDatas.get(position).filePath, new MediaPlayer.OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        animView.setBackgroundResource(R.drawable.adj);
                    }
                });
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        MediaManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaManager.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaManager.release();
    }


}

