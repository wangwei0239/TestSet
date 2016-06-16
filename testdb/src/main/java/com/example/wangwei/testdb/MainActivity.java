package com.example.wangwei.testdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

public class MainActivity extends AppCompatActivity {

    private AppApplication app;
    private DbManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (AppApplication) getApplication();
        db = x.getDb(app.daoConfig);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.save(new People("wang",12));
                    System.out.println("chenggong-----");
                } catch (DbException e) {
                    System.out.println("shibai-----");
                    e.printStackTrace();
                }
            }
        });
    }
}
