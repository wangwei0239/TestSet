//package com.example.wangwei.testbackgroundcolorselector;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.ActivityManager;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.IBinder;
//import android.support.v7.widget.ViewUtils;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.EditText;
//
//import org.xutils.view.annotation.ContentView;
//
//import java.util.logging.Logger;
//
///**
// * Created by wangwei on 16/8/11.
// */
//@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface", "NewApi" })
//@ContentView(R.layout.base)
//public class BaseActivity extends Activity {
//    public Context mContext;
//    public LoadingDialog loadingDialog;
//    public ActivityManager activityManager;
//    public Activity context;
//    public static final Logger _log = LoggerFactory.getLogger(BaseActivity.class);
//    private final static String TAG = BaseActivity.class.getSimpleName();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        ViewUtils.inject(this);
//        this.context = this;
//        //获取屏幕像素
//        ScreenUtils.initScreen(this);
//        activityManager = ActivityManager.getInstance();
//        activityManager.getInstance().pushOneActivity(this);
//        mContext = this;
//        loadingDialog = new LoadingDialog(this,R.style.LoadingDialog);
//
//        if (hasKitKat() && !hasLollipop()) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏 getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        } else if (hasLollipop()) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);//
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
//    }
//    public void showLoadingDialog(boolean isCanceledOnTouch){
//        if (loadingDialog != null && !loadingDialog.isShowing()) {
//            loadingDialog.setCanceledOnTouchOutside(isCanceledOnTouch);
//            loadingDialog.show();
//        }
//    }
//    public void showLoadingDialog(boolean isCanceledOnTouch,String title){
//        if (loadingDialog != null && !loadingDialog.isShowing()) {
//            loadingDialog.setCanceledOnTouchOutside(isCanceledOnTouch);
//            loadingDialog.setTitle(title);
//            loadingDialog.show();
//        }
//    }
//    public void dismissLoadingDialog(){
//        if (loadingDialog != null && loadingDialog.isShowing()) {
//            loadingDialog.dismiss();
//        }
//    }
//    public void hideSoftInput(IBinder token) {
//        if (token != null) {
//            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            im.hideSoftInputFromWindow(token,InputMethodManager.HIDE_NOT_ALWAYS);
//        }
//    }
//
//    public boolean isShouldHideInput(View v, MotionEvent event) {
//        if (v != null && (v instanceof EditText)) {
//            int[] l = { 0, 0 };
//            v.getLocationInWindow(l);
//            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
//            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
//                return false;
//            } else {
//                return true;
//            }
//        }
//        return false;
//    }
//    /* 隐藏键盘 */
//    public static void hideSoftInput(Activity mContext, View view) {
//        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (imm.isActive()) {
//            if(view!=null){
//                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//            }
//        }
//    }
//    /**
//     * 跳转到网络设置界面
//     */
//    protected void gotoNetworkSet() {
//        Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
//        startActivity(intent);
//    }
//    /* 弹出键盘 */
//    public static void showSoftInput(Activity mContext, View view) {
//        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (imm.isActive()) {
//            if(view!=null){
//                imm.showSoftInput(view, 0);
//            }
//        }
//    }
//    public static boolean hasKitKat() {
//        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
//    }
//
//    public static boolean hasLollipop() {
//        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
//}