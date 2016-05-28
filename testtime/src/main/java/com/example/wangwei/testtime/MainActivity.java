package com.example.wangwei.testtime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String time = "2016-05-09 09:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(time);
            System.out.println("----------------"+date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
