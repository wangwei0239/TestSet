package com.example.wangwei.lockscreendemo;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private WindowManager wm;
    private WindowManager.LayoutParams params;
    private WindowManager.LayoutParams taxiParams;
    private int startX = 0;
    private View view;
    private View taxiView;
    private int screenWidth = 1080;
    private int screenHeight = 1920;
    private ViewPager viewPager;
    private List<View> viewList;
    private ImageView picIcon;

    private View clockResult;
    private View mainPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPages);
        viewPager.setOnTouchListener(viewPageListener);
        initViewPage();
        viewPager.setAdapter(pagerAdapter);
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        view = this.getLayoutInflater().inflate(R.layout.pager_clock_page, null);
        taxiView = getLayoutInflater().inflate(R.layout.pager_taxi,null);
        screenWidth = wm.getDefaultDisplay().getWidth();
        screenHeight = wm.getDefaultDisplay().getHeight();
        if (params == null) {
            params = initViewParams(params);
            wm.addView(view, params);
            view.setOnTouchListener(myListener);
        }

        if (taxiParams == null) {
            taxiParams = initViewParams(taxiParams);
            taxiParams.height = 600;
//            wm.addView(taxiView, taxiParams);
        }


    }

    private PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }
    };

    private void initViewPage(){
        clockResult = getLayoutInflater().inflate(R.layout.pager_clock_result,null);
        mainPage = getLayoutInflater().inflate(R.layout.pager_main,null);
        if(viewList == null){
            viewList = new ArrayList<>();
        }
        viewList.add(clockResult);
        viewList.add(mainPage);
    }

    private WindowManager.LayoutParams initViewParams(WindowManager.LayoutParams params) {
        params = new WindowManager.LayoutParams();
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        params.format = PixelFormat.TRANSLUCENT;
        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        return params;
    }

    private View.OnTouchListener myListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {


            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getRawX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int endX = (int) event.getRawX();
                    int dx = endX - startX;
                    if (v.getLeft() + dx >= 0) {
                        v.layout(v.getLeft() + dx, v.getTop(), v.getRight() + dx, v.getBottom());
                    }
                    startX = endX;
                    break;
                case MotionEvent.ACTION_UP:
                    if (v.getLeft() <= (screenWidth / 2)) {
                        v.layout(0, v.getTop(), screenWidth, v.getBottom());
                    } else {
                        if (wm != null) {
                            wm.removeView(v);
                        }
                    }
                    break;
                default:
                    break;
            }

            return true;
        }
    };

    private int viewPageStartY = 0;
    private int viewPageStartX = 0;

    private View.OnTouchListener viewPageListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    viewPageStartY = (int) event.getRawY();
                    viewPageStartX = (int) event.getRawX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int endY = (int) event.getRawY();
                    int dy = endY - viewPageStartY;

                    if(dy < 0){
                        viewPager.layout(viewPager.getLeft(), viewPager.getTop() + dy, viewPager.getRight(), viewPager.getBottom() + dy);
                    }else {
                        WindowManager.LayoutParams params = (WindowManager.LayoutParams) taxiView.getLayoutParams();
//                    params.height = endY;
//                    System.out.println(params.height);
//                    taxiView.setLayoutParams(params);
                        taxiView.layout(taxiView.getLeft(),0, taxiView.getRight(), dy);
                        viewPageStartY = endY;
                    }


                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return false;
        }
    };
}
