package com.jackwang.testretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        EmotibotAPI.Factory.get().getUser(Constants.APP_ID, URLConstants.PARAM_VALUE_CMD_REGISTRATION).enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                Log.i("TAG","Content:"+response.body());
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Log.i("TAG", "onFailure:"+t.getMessage());
//            }
//        });
//        EmotibotAPI.Factory.get().getUserString(Constants.APP_ID, URLConstants.PARAM_VALUE_CMD_REGISTRATION).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("TAG","Content:"+response.body());
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.i("TAG", "onFailure:"+t.getMessage());
//                throw new NullPointerException();
//            }
//        });

        EmotibotAPI.Factory.get().getUserStringRX("{\"return\":0, \"msg\":\"it's ok\"}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.i("RXLOG","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                            Log.i("RXLOG","onError:"+e.getMessage());
//                        throw new NullPointerException();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                            Log.i("RXLOG","Content:"+s);
                            throw new RuntimeException("my error");
                    }
                });
    }
}
