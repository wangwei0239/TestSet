package com.example.wangwei.testdb;

import android.app.Application;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * Created by wangwei on 16/6/15.
 */
public class AppApplication extends Application {

    public DbManager.DaoConfig daoConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
        daoConfig = new DbManager.DaoConfig()
                .setDbName("testdb")
                .setDbVersion(1)
                .setDbUpgradeListener(new DbManager.DbUpgradeListener(){
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                    }
                });
    }
}
