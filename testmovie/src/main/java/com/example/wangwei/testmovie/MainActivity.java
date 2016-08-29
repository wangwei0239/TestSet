package com.example.wangwei.testmovie;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.baidu.xlife.ConfigConstant;
import com.baidu.xlife.ILifeCallback;
import com.baidu.xlife.LifeClient;
import com.baidu.xlife.LifeException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyRecyclerAdapter recycleAdapter;
    private List<String> mDatas;
    private int cityId = 136;
    private LifeClient mLifeClient;
    private Context mContext;
    private ProgressDialog mLoadingDlg;
    private String TAG = "TAG";
    private static final String MODULAR_MOVIE = "movie";
    private int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1;
    private int READ_PHONE_STATE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mLifeClient = LifeClient.getInstance();

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }


        recyclerView = (RecyclerView) findViewById(R.id.rv);
//
//        initData();
//
//        recycleAdapter= new MyRecyclerAdapter(MainActivity.this , mDatas);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this );
////设置布局管理器
//        recyclerView.setLayoutManager(layoutManager);
////设置为垂直布局，这也是默认的
//        layoutManager.setOrientation(OrientationHelper. HORIZONTAL);
////设置Adapter
//        recyclerView.setAdapter(recycleAdapter);
//        //设置分隔线
////        recyclerView.addItemDecoration( new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST));
////设置增加或删除条目的动画
//        recyclerView.setItemAnimator( new DefaultItemAnimator());




//        Bundle bundle = new Bundle();
//        bundle.putString(ConfigConstant.KEY_ACTION, MovieConfigConstant.ACTION_OPERATE_MOVIE_DETAIL);
//        bundle.putString(ConfigConstant.KEY_CITY_ID, "289");
//        bundle.putString(MovieConfigConstant.KEY_MOVIE_ID, "10156");
//        bundle.putString(ConfigConstant.KEY_PROVIDER_ID, "baidu_movie");
//        bundle.putString(ConfigConstant.KEY_OPTION, ConfigConstant.OPTION_SHOW_VIEW);
//        try {
//            mLifeClient.execute(MODULAR_MOVIE, bundle, mLifeCallback);
//        } catch (LifeException e) {
//            e.printStackTrace();
//        }

//        Gson gson = new Gson();
//
//        String json = "{\"news\":{\"articles\":[{\"picurl\":\"https:\\/\\/mmbiz.qlogo.cn\\/mmbiz\\/4UgDk0zQEIiahl7oPbzNK8cP2ibUABk9S4icE2MPIJUBuPiclLRVImt02r1aogibGPnqhiaHLic4FELyaSlZEO7NGJEdA\\/0\",\"description\":\"\",\"title\":\" 创建提醒成功\"},{\"picurl\":\"https:\\/\\/mmbiz.qlogo.cn\\/mmbiz\\/4UgDk0zQEIiahl7oPbzNK8cP2ibUABk9S4vA47fKy94Y15icvLFQ8J0LlRAy1nwZu6DusDuuGYNr0mJYXExvsypjA\\/0\",\"description\":\"\",\"title\":\"时间: 2016年7月19日  周二 13:49\"},{\"picurl\":\"https:\\/\\/mmbiz.qlogo.cn\\/mmbiz\\/4UgDk0zQEIiahl7oPbzNK8cP2ibUABk9S4HYZgdvLZxYJ4TUxibWklS09Dkqv6kibenQ0jiaeLtaT6BQVCYsfwTzIGw\\/0\",\"description\":\"DESCRIPTION\",\"title\":\"事件: 回家\"},{\"picurl\":\"\",\"description\":\"\",\"title\":\"对小影说「查看提醒事项」,小影可以为您展现最近的提醒事项哦\"}]},\"msgtype\":\"news\",\"touser\":\"OPENID\"}";
//        ReminderModel reminderModel = gson.fromJson(json,ReminderModel.class);
//        System.out.println("JSON SIZE:"+reminderModel.getNews().getArticles().size());


//        Bundle bundle = new Bundle();
//        bundle.putString(ConfigConstant.KEY_ACTION, MovieConfigConstant.ACTION_OPERATE_MOVIE_PAGE);
//        bundle.putString(ConfigConstant.KEY_CITY_ID, String.valueOf(cityId));
//        bundle.putString(ConfigConstant.KEY_OPTION, ConfigConstant.OPTION_SHOW_VIEW);
//        try {
//            mLifeClient.execute(MODULAR_MOVIE, bundle, mLifeCallback);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    private void initData() {
        mDatas = new ArrayList<String>();
        for ( int i=0; i < 40; i++) {
            mDatas.add( "item"+i);
        }
    }

    private ILifeCallback mLifeCallback = new ILifeCallback() {

        @Override
        public void onError(final JSONObject arg0) {
            Log.e(TAG, "error-->" + arg0);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    mLoadingDlg.dismiss();
                    System.out.println("dismiss");
                    Toast.makeText(mContext, "error-->" + arg0, Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public void onFinish(final JSONObject data) {
            System.out.println("onFinish JSON DATA:"+data);
            File contentFile = new File(Environment.getExternalStorageDirectory()+"/content.txt");
            BufferedWriter fw = null;
            try {
                fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(contentFile),"UTF-8"));
                fw.write(data.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(fw != null){
                    try {
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
//            mLoadingDlg.dismiss();
//            String action = data.optString(ConfigConstant.KEY_ACTION);
//            if (action.equals(CategorysConfigConstant.ACTION_GET_CITIES)) {
//                Log.d("getCities", "getCities:" + data.toString());
//                Message msg = new Message();
//                msg.what = MSG_GET_CITY;
//                msg.obj = data;
//                mHandler.sendMessage(msg);
//            } else if (action.equals(MovieConfigConstant.ACTION_OPERATE_ONSHOW_MOVIE_LIST)) {
//                Message msg = new Message();
//                msg.what = MSG_GET_ONSHOW_MOVIE;
//                msg.obj = data;
//                mHandler.sendMessage(msg);
//            } else if (action.equals(MovieConfigConstant.ACTION_OPERATE_UPCOME_MOVIE_LIST)) {
//                Message msg = new Message();
//                msg.what = MSG_GET_UPCOME_MOVIE;
//                msg.obj = data;
//                mHandler.sendMessage(msg);
//            } else if (action.equals(ConfigConstant.ACTION_GET_CITY_ID_BY_LAT)) {
//                Log.d("getCityByLatLng", "getCityByLatLng:" + data.toString());
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            mGetCityResultTv.setText(data.getString("city_name") + ":" + data.getInt("city_id"));
//                            mGetCityResultTv.setVisibility(View.VISIBLE);
//                            mCityId = data.getInt("city_id") + "";
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            } else if (action.equals(MovieConfigConstant.ACTION_OPERATE_CINEMA_LIST)) {
//                Message msg = new Message();
//                msg.what = MSG_GET_CINEMA_LIST;
//                msg.obj = data;
//                mHandler.sendMessage(msg);
//            } else if (action.equals(MovieConfigConstant.ACTION_OPERATE_MOVIE_ORDER)) {
//                Message msg = new Message();
//                msg.what = MSG_GET_MOVIE_ORDER;
//                msg.obj = data;
//                mHandler.sendMessage(msg);
//            } else if (action.equals(ConfigConstant.ACTION_OPEN_RUNTIME_CONFIG)) {
//                Log.i("", data.toString());
//            } else if (action.equals(ConfigConstant.ACTION_CLOSE_RUNTIME_CONFIG)) {
//                Log.i("", data.toString());
//            }

        }

        @Override
        public void onUpdate(JSONObject arg0) {
            try {
                int state = arg0.getInt("state");
                Log.i(TAG, "state-->" + state);
                if (state == ConfigConstant.STATE_ON_PAGE_START) {
//                    mLoadingDlg.dismiss();
                    System.out.println("dismiss");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onPreExecute(final JSONObject arg0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    mLoadingDlg.dismiss();
                    System.out.println("dismiss");
                    Toast.makeText(mContext, "onPreExecute-->" + arg0, Toast.LENGTH_LONG).show();
                }
            });
        }
    };



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode,grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {

        boolean result = true;
        for(int i : grantResults){
            if(i != PackageManager.PERMISSION_GRANTED){
                result = false;
            }
        }

        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (result) {
                initClient();
                requestInfo();
                // Permission Granted

            } else {
                // Permission Denied
                Toast.makeText(this,"Permission denied",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void initClient(){
        Bundle bundle1=new Bundle();
        bundle1.putString("debug","true");
        try {
            mLifeClient.init(this, bundle1);
//            XLifeRuntimeUtil.openWebActivityLoadingView(mLifeClient, mLifeCallback);
//            XLifeRuntimeUtil.openHtmlLoadingView(mLifeClient, mLifeCallback);
        } catch (LifeException e) {
            e.printStackTrace();
        }
    }

    public void requestInfo(){
        Bundle bundle = new Bundle();
        bundle.putString(ConfigConstant.KEY_ACTION, MovieConfigConstant.ACTION_OPERATE_ONSHOW_MOVIE_LIST);
        bundle.putString(MovieConfigConstant.KEY_PAGE_NO, "1");
        bundle.putString(MovieConfigConstant.KEY_PAGE_SIZE, "2");
        bundle.putString(ConfigConstant.KEY_CITY_ID, String.valueOf(cityId));
        bundle.putString(ConfigConstant.KEY_OPTION, ConfigConstant.OPTION_GET_DATA);
        try {
            mLifeClient.execute(MODULAR_MOVIE, bundle, mLifeCallback);
        } catch (LifeException e) {
            e.printStackTrace();
        }
    }
}
