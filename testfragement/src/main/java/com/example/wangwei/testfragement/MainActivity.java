package com.example.wangwei.testfragement;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private FrameLayout holder;
    public MyFragment myFragment;
    public FragmentManager fragmentManager;
    private String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        checkOrientation();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }

    private void checkOrientation() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 横屏
            fragmentManager = getFragmentManager();
            setFragment(R.id.holder);
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            removeFragment();
        }
    }

    private void setFragment(int viewId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(myFragment == null){
            myFragment = new MyFragment();
            transaction.replace(viewId, myFragment);
        }else {
            transaction.show(myFragment);
        }
        transaction.commit();
    }

    private void removeFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(myFragment != null){
            transaction.remove(myFragment);
        }
        transaction.commit();
    }


}
