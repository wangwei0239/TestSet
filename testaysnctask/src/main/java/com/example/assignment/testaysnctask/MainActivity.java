package com.example.assignment.testaysnctask;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

    TextView view;
    TextView view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (TextView) findViewById(R.id.tv);
        view2 = (TextView) findViewById(R.id.tv2);

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 300);
        animator.setDuration(1000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "";
                try {
                    result = new MyAsync().execute().get(2, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    result = "InterruptedException";
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    result = "ExecutionException";
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    result = "TimeoutException";
                    e.printStackTrace();

                }
                view.setText(result);
            }
        });
    }

    class MyAsync extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Finish at " + new Date().getTime();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            view2.setText("onPostExecute at "+new Date().getTime());
        }
    };
}
