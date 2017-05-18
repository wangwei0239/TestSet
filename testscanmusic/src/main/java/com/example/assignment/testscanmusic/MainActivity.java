package com.example.assignment.testscanmusic;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Music> musics = getMusicFile(this);
        for(Music music : musics){
            Log.i("TAG",music.toString());
        }
    }

    /**
     * 获取SD卡中的音乐文件
     *
     * @param context
     * @return
     */
    public static ArrayList<Music> getMusicFile(Context context) {
        //ArrayList<Music>存放音乐
        ArrayList<Music> MusicFiles = new ArrayList<>();
        //查询媒体数据库
        ContentResolver resolver = context.getContentResolver();
        /**
         * Uri：这个Uri代表要查询的数据库名称加上表的名称。
         这个Uri一般都直接从MediaStore里取得，例如我要取所有歌的信息，
         就必须利用MediaStore.Audio.Media. EXTERNAL _CONTENT_URI这个Uri。
         *
         */
        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        //遍历媒体数据库
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                //歌曲编号MediaStore.Audio.Media._ID
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));

                //歌曲标题
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));

                //歌曲的专辑名MediaStore.Audio.Media.ALBUM
                String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));

                //歌曲的歌手名MediaStore.Audio.Media.ARTIST
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));

                //歌曲文件的路径MediaStore.Audio.Media.DATA
                String url = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));

                //歌曲的总播放时长MediaStore.Audio.Media.DURATION
                int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));

                //歌曲文件的大小MediaStore.Audio.Media.SIZE
                long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));

                if (size > 1024 * 800) {     //是否大于800K
                    if (title.equals("<unknown>") || title.equals("")) {
                        title = "未知";
                    }
                    if ("<unknown>".equals(artist) || "".equals(artist)) {
                        artist = "未知";
                    }

                    Music music = new Music(id, title, artist,
                            url, album, duration, size);
                    MusicFiles.add(music);
                }
                cursor.moveToNext();
            }
        }
        return MusicFiles;
    }
}
