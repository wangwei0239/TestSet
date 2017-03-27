package com.example.wangwei.testdb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.xutils.DbManager;
import org.xutils.common.util.LogUtil;
import org.xutils.ex.DbException;
import org.xutils.x;

/**
 * Created by wangwei on 17/3/8.
 */

public class MusicActivity extends AppCompatActivity {

    private AppApplication app;
    private DbManager db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (AppApplication) getApplication();
        db = x.getDb(app.daoConfig);
//        Music music = new Music();
//        music.setName("王为");
//        music.setSinger("wangiwei");
//        music.setType("rock,classic,blue");
//        try {
//            db.save(music);
//            li("finish");
//        } catch (DbException e) {
//            e.printStackTrace();
//            li("error");
//        }

        try {
            Music m = db.selector(Music.class).findFirst();
            li("finish");
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void li(String log){
        LogUtil.i(log);
    }
}
