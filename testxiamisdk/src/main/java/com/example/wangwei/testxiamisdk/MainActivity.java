package com.example.wangwei.testxiamisdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.xiami.core.api.ApiResponse;
import com.xiami.core.exceptions.AuthExpiredException;
import com.xiami.core.exceptions.ResponseErrorException;
import com.xiami.sdk.XiamiSDK;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String XIAMI_KEY = "ef82054a41b7f6be7cd0b7f5da564061";

    public static final String XIAMI_SECRET = "78c9c3ea75b22d05a9febb2ef6c373ec";

    private AudioPlayer mAudioPlayer;

    private Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XiamiSDK.init(getApplicationContext(),
                XIAMI_KEY, XIAMI_SECRET);
        mGson = new Gson();
        mAudioPlayer = AudioPlayer.getInstance(this);
        new Thread(){
            @Override
            public void run() {
                try {
                    searchSongWithArtist("稻香","周杰伦");
                } catch (AuthExpiredException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (ResponseErrorException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private void searchSongWithArtist(final String song, final String artist) throws AuthExpiredException, NoSuchAlgorithmException, ResponseErrorException, IOException {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("key", song);
        String result = XiamiSDK.xiamiSDKRequest(RequestMethods.SEARCH_SONGS, map);
        ApiResponse apiResponse = mGson.fromJson(result, ApiResponse.class);
        if (apiResponse.getState() == 0) {
            JsonElement jsonElement = apiResponse.getData();
            List<BaseSong> songsArray = mGson.fromJson(jsonElement.getAsJsonObject().get("songs"),
                    new TypeToken<List<BaseSong>>() {
                    }.getType());
            boolean finded = false;
            BaseSong selectedSong = songsArray.get(0);
            for (int index = 0; index < songsArray.size(); index++) {
                BaseSong item = songsArray.get(index);
                if (item.getSingers().equals(artist)) {
                    selectedSong = item;
                    finded = true;
                    break;
                }
            }
            String url = getSongsById(selectedSong.getSong_id());
            mAudioPlayer.play(url);
//            if (finded) {
//                if (!dealMusic(selectedSong)) {
//                    showMessage(PERMISSION_ERROR);
//                }
//            }
//            else {
//                int index = 0;
//                selectedSong = songsArray.get(index);
//                while (index < songsArray.size() && !dealMusic(selectedSong)) {
//                    selectedSong = songsArray.get(index);
//                    index++;
//                }
//                if (index >= songsArray.size()) {
//                    showMessage(PERMISSION_ERROR);
//                }
//            }
            Log.i("LOG","have song");
        } else {
            Log.i("LOG","no song");
//            showMessage(NO_SONG_ERROR);
        }
    }


    private String getSongsById(int songId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("song_id", songId);
        String targetListenFile = null;
        try {
            String result = XiamiSDK.xiamiSDKRequest(RequestMethods.METHOD_SONG_DETAIL, map);
            ApiResponse apiResponse = mGson.fromJson(result, ApiResponse.class);
            if (apiResponse.getState() == 0) {
                OnlineSong onlineSong = mGson.fromJson(apiResponse.getData(), OnlineSong.class);
                targetListenFile = onlineSong.getListen_file();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Log.i("LOG","network error");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("LOG","network error");
        } catch (AuthExpiredException e) {
            e.printStackTrace();
            Log.i("LOG","network error");
        } catch (ResponseErrorException e) {
            e.printStackTrace();
            Log.i("LOG","network error");
        } finally {
            return targetListenFile;
        }
    }


    private boolean hasPermission(BaseSong song) {
        if (song.getPurview_roles().get(0).getOperation_list().get(0).getUpgrade_role() != 0) {
            return false;
        }
        return true;
    }
}
