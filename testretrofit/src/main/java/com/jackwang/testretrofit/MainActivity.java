package com.jackwang.testretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        EmotibotAPI.Factory.get().getUserString(Constants.APP_ID, URLConstants.PARAM_VALUE_CMD_REGISTRATION).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("TAG","Content:"+response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("TAG", "onFailure:"+t.getMessage());
            }
        });
    }
}
