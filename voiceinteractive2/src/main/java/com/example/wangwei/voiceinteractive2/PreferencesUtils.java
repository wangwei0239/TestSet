package com.example.wangwei.voiceinteractive2;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hector on 16/3/28.
 */
public class PreferencesUtils {
    private SharedPreferences mPrefrences;

    public static final String NEWFEEDBACKNUM = "newFeedbackNum";
    public static final String SPLASH_FILENAME = "splashfilename";
    public static final String SPLASH_FILESIZE = "splashfilesize";
    public static final String CHAT_BACKGROUND = "chatbackground";
    public static final String UID = "userId";

    public PreferencesUtils(Context context) {
        mPrefrences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public void setInt(String key, int value) {
        mPrefrences.edit().putInt(key, value).commit();
    }

    public int getInt(String key) {
        return mPrefrences.getInt(key, -1);
    }

    public void setString(String key, String value) {
        mPrefrences.edit().putString(key, value).commit();
    }

    public String getString(String key) {
        return mPrefrences.getString(key, "");
    }

    public boolean getBoolean(String key) {
        return mPrefrences.getBoolean(key, false);
    }

    public void setBoolean(String key, boolean value) {
        mPrefrences.edit().putBoolean(key, value).commit();
    }
}
