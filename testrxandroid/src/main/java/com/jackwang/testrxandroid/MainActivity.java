package com.jackwang.testrxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Button async;
    private Button sync;
    private TextView textView;

    private Observable<String> observable;
    private Observer<String> observer;

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        async = (Button) findViewById(R.id.async);
        sync = (Button) findViewById(R.id.sync);
        textView = (TextView) findViewById(R.id.textView);


        final Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                Thread.sleep(3000);
                Log.i(TAG, "subscribe - "+Thread.currentThread().getName());
                e.onNext("Hello RxJava2");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        final Subscriber subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.i(TAG, "onSubscribe - "+Thread.currentThread().getName());
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext:"+s+" - "+Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete - "+Thread.currentThread().getName());
            }
        };

        async.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
            }
        });

        Flowable.just("String").subscribe();

    }

}
