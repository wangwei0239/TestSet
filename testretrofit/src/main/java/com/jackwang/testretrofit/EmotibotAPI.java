package com.jackwang.testretrofit;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wangwei on 17/4/12.
 */

public interface EmotibotAPI {

    @FormUrlEncoded
    @POST(URLConstants.API_PATH)
    Call<User> getUser(@Field(URLConstants.PARAM_NAME_APPID)String appid, @Field(URLConstants.PARAM_NAME_CMD)String cmd);

    @FormUrlEncoded
    @POST(URLConstants.API_PATH)
    Call<String> getUserString(@Field(URLConstants.PARAM_NAME_APPID)String appid, @Field(URLConstants.PARAM_NAME_CMD)String cmd);

    @FormUrlEncoded
    @POST(URLConstants.API_PATH)
    Observable<String> getUserStringRX(@Field(URLConstants.PARAM_NAME_JSON)String appid);

    class Factory{
        private static EmotibotAPI api;
        public static EmotibotAPI get(){
            if(api == null){
                Retrofit retrofit = new Retrofit.Builder().baseUrl(URLConstants.HOST).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(ScalarsConverterFactory.create()).build();
                api = retrofit.create(EmotibotAPI.class);
            }
            return api;
        }
    }

}
