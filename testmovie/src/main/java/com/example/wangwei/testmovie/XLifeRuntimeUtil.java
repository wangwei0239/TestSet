package com.example.wangwei.testmovie;

import android.os.Bundle;

import com.baidu.xlife.ConfigConstant;
import com.baidu.xlife.ILifeCallback;
import com.baidu.xlife.LifeClient;
import com.baidu.xlife.LifeException;
import com.baidu.xlife.LifeRuntimeConfig;

public class XLifeRuntimeUtil {
    
    public static void openHtmlProgressBar(LifeClient lifeClient, ILifeCallback callback) {
        Bundle bundle = new Bundle();
        bundle.putString(ConfigConstant.KEY_ACTION, ConfigConstant.ACTION_OPEN_RUNTIME_CONFIG);
        bundle.putInt(LifeRuntimeConfig.KEY_LAYOUT_ID, R.layout.progress_bar);
        bundle.putInt(LifeRuntimeConfig.KEY_VIEW_ID, R.id.progressbar);
        bundle.putString(LifeRuntimeConfig.KEY_CONFIG_ITEM, LifeRuntimeConfig.OPTION_HTML_PROGRESS_BAR);
        try {
            lifeClient.execute("runtime", bundle, callback);
        } catch (LifeException e) {
            e.printStackTrace();
        }
    }

    public static void closeHtmlProgressBar(LifeClient lifeClient, ILifeCallback callback) {
        Bundle bundle = new Bundle();
        bundle.putString(ConfigConstant.KEY_ACTION, ConfigConstant.ACTION_CLOSE_RUNTIME_CONFIG);
        bundle.putString(LifeRuntimeConfig.KEY_CONFIG_ITEM, LifeRuntimeConfig.OPTION_HTML_PROGRESS_BAR);
        try {
            lifeClient.execute("runtime", bundle, callback);
        } catch (LifeException e) {
            e.printStackTrace();
        }
    }
    
    public static void openHtmlExitView(LifeClient lifeClient, ILifeCallback callback) {
        Bundle param = new Bundle();
        param.putString(ConfigConstant.KEY_ACTION, ConfigConstant.ACTION_OPEN_RUNTIME_CONFIG);
        param.putString(LifeRuntimeConfig.KEY_CONFIG_ITEM, LifeRuntimeConfig.OPTION_HTML_QUICK_EXIT_VIEW);
        param.putInt(LifeRuntimeConfig.KEY_LAYOUT_ID, R.layout.html_exit_icon_view);
        param.putInt(LifeRuntimeConfig.KEY_VIEW_ID, R.id.iamge);
        try {
            lifeClient.execute("runtime", param, callback);
        } catch (LifeException e) {
            e.printStackTrace();
        }
    }
    
    public static void closeHtmlExitView(LifeClient lifeClient, ILifeCallback callback) {
        Bundle param = new Bundle();
        param.putString(ConfigConstant.KEY_ACTION, ConfigConstant.ACTION_CLOSE_RUNTIME_CONFIG);
        param.putString(LifeRuntimeConfig.KEY_CONFIG_ITEM, LifeRuntimeConfig.OPTION_HTML_QUICK_EXIT_VIEW);
        try {
            lifeClient.execute("runtime", param, callback);
        } catch (LifeException e) {
            e.printStackTrace();
        }
    }
    
    public static void openWebActivityLoadingView(LifeClient lifeClient, ILifeCallback callback) {
        Bundle param = new Bundle();
        param.putString(ConfigConstant.KEY_ACTION, ConfigConstant.ACTION_OPEN_RUNTIME_CONFIG);
        param.putString(LifeRuntimeConfig.KEY_CONFIG_ITEM, LifeRuntimeConfig.OPTION_WEBACTIVITY_LOAD_DIALOG);
        // 帧动画
//        param.putInt(LifeRuntimeConfig.KEY_ANIM_ID, R.anim.load_dialog);
        
        // 补间动画
//        param.putInt(LifeRuntimeConfig.KEY_ANIM_ID, R.anim.load_dialog_tween);
//        param.putInt("drawableResource", R.drawable.load1);
        try {
            lifeClient.execute("runtime", param, callback);
        } catch (LifeException e) {
            e.printStackTrace();
        }
    }
    
    public static void closeWebActivityLoadingView(LifeClient lifeClient, ILifeCallback callback) {
        Bundle param = new Bundle();
        param.putString(ConfigConstant.KEY_ACTION, ConfigConstant.ACTION_CLOSE_RUNTIME_CONFIG);
        param.putString(LifeRuntimeConfig.KEY_CONFIG_ITEM, LifeRuntimeConfig.OPTION_WEBACTIVITY_LOAD_DIALOG);
        try {
            lifeClient.execute("runtime", param, callback);
        } catch (LifeException e) {
            e.printStackTrace();
        }
    }
    
    public static void openModifyStatusBar(LifeClient lifeClient, ILifeCallback callback) {
        Bundle param = new Bundle();
        param.putString(ConfigConstant.KEY_ACTION, ConfigConstant.ACTION_OPEN_RUNTIME_CONFIG);
        param.putString(LifeRuntimeConfig.KEY_CONFIG_ITEM, LifeRuntimeConfig.OPTION_STATUS_BAR);
//        param.putInt(LifeRuntimeConfig.KEY_STATUSBAR_RESOURCE_ID, R.color.white);
        param.putFloat(LifeRuntimeConfig.KEY_STATUSBAR_ALPHA, 1f);
        try {
            lifeClient.execute("runtime", param, callback);
        } catch (LifeException e) {
            e.printStackTrace();
        }
    }
    
    public static void closeModifyStatusBar(LifeClient lifeClient, ILifeCallback callback) {
        Bundle param = new Bundle();
        param.putString(ConfigConstant.KEY_ACTION, ConfigConstant.ACTION_CLOSE_RUNTIME_CONFIG);
        param.putString(LifeRuntimeConfig.KEY_CONFIG_ITEM, LifeRuntimeConfig.OPTION_STATUS_BAR);
        try {
            lifeClient.execute("runtime", param, callback);
        } catch (LifeException e) {
            e.printStackTrace();
        }
    }
    
    public static void openHtmlLoadingView(LifeClient lifeClient, ILifeCallback callback) {
        Bundle param = new Bundle();
        param.putString(ConfigConstant.KEY_ACTION, ConfigConstant.ACTION_OPEN_RUNTIME_CONFIG);
        param.putString(LifeRuntimeConfig.KEY_CONFIG_ITEM, LifeRuntimeConfig.OPTION_HTML_LOAD_DIALOG);
        try {
            lifeClient.execute("runtime", param, callback);
        } catch (LifeException e) {
            e.printStackTrace();
        }
    }
    
    public static void closeHtmlLoadingView(LifeClient lifeClient, ILifeCallback callback) {
        Bundle param = new Bundle();
        param.putString(ConfigConstant.KEY_ACTION, ConfigConstant.ACTION_CLOSE_RUNTIME_CONFIG);
        param.putString(LifeRuntimeConfig.KEY_CONFIG_ITEM, LifeRuntimeConfig.OPTION_HTML_LOAD_DIALOG);
        try {
            lifeClient.execute("runtime", param, callback);
        } catch (LifeException e) {
            e.printStackTrace();
        }
    }
    
}
