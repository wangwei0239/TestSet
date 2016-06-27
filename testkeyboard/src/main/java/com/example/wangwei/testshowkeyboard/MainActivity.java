package com.example.wangwei.testshowkeyboard;

import android.graphics.Rect;
import android.hardware.input.InputManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG = "TAG";
    ListView lv;
    Button sendBtn;
    InputMethodManager inputManager;
    int keyBoardHeight = 0;
    FrameLayout fl;
    EditText et;
    static ArrayList<String> list = new ArrayList<>();

    static {
        for (int i = 0; i < 20; i++) {
            list.add("测试内容" + i);
        }
    }

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.textSpace);
        et.setOnClickListener(this);
        inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        lv = (ListView) findViewById(R.id.lv);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
        sendBtn = (Button) findViewById(R.id.sendBtn);
        fl = (FrameLayout) findViewById(R.id.fl);
        sendBtn.setOnClickListener(this);

        final RelativeLayout myLayout =
                (RelativeLayout) findViewById(R.id.rootView);
//        myLayout.getViewTreeObserver().
//                addOnGlobalLayoutListener(new
//                                                  ViewTreeObserver.OnGlobalLayoutListener() {
//                                                      /**
//                                                       * the result is pixels
//                                                       */
//                                                      @Override
//                                                      public void onGlobalLayout() {
//
//                                                          Rect r = new Rect();
//                                                          myLayout.getWindowVisibleDisplayFrame(r);
//
//                                                          int screenHeight = myLayout.getRootView().getHeight();
//                                                          Log.i("TAG", "onGlobalLayout: bottom:" + r.bottom+" top:"+r.top);
//                                                          int heightDifference = screenHeight - (r.bottom - r.top);
//                                                          Log.i("TAG", "Keyboard Size, Size:  " + heightDifference);
//                                                          if(heightDifference > keyBoardHeight){
//                                                              keyBoardHeight = heightDifference;
//                                                          }
//                                                          //boolean visible = heightDiff > screenHeight / 3;
//                                                      }
//                                                  });
    }

    @Override
    public void onClick(View v) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) fl.getLayoutParams();
        switch (v.getId()) {
            case R.id.sendBtn:
                Log.i(TAG, "KeyBoard:" + isKeyShows());
                Log.i(TAG, "KeyBoardHeight:" + keyBoardHeight);
                lp.height = keyBoardHeight;
//                fl.setLayoutParams(lp);
                hideKeyboard();
                break;
            case R.id.textSpace:
                keyBoardHeight = SystemUtils.getKeyboardHeight(this);
                lp.height = 0;
//                fl.setLayoutParams(lp);
                break;
        }
    }

    private void hideKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                inputManager.hideSoftInputFromWindow(getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public boolean isKeyShows() {
        return true;
    }
}
