package com.example.wangwei.testtouchconflict2;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String TAG = "TAG";
    private ViewPager vp;
    private ArrayList<ImageView> views;
    private ArrayList<RelativeLayout> lys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = (ViewPager) findViewById(R.id.vp);
        views = new ArrayList<>();
        lys = new ArrayList<>();
        ImageView iv = new ImageView(this);
        ImageView iv2 = new ImageView(this);
        ImageView iv3 = new ImageView(this);
        ImageView iv4 = new ImageView(this);
        ViewPager.LayoutParams params = new ViewPager.LayoutParams();
        params.height = 200;
        params.width = ViewPager.LayoutParams.MATCH_PARENT;

        iv.setBackgroundColor(Color.RED);
        iv2.setBackgroundColor(Color.BLUE);
        iv3.setBackgroundColor(Color.YELLOW);
        iv4.setBackgroundColor(Color.GREEN);

        iv.setLayoutParams(params);
        iv2.setLayoutParams(params);
        iv3.setLayoutParams(params);
        iv4.setLayoutParams(params);

        views.add(iv);
        views.add(iv2);
        views.add(iv3);
        views.add(iv4);


        RelativeLayout rly = new RelativeLayout(this);
        RelativeLayout rly2 = new RelativeLayout(this);
        RelativeLayout rly3 = new RelativeLayout(this);

        LayoutParams lyparams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);

        rly.setLayoutParams(lyparams);
        rly2.setLayoutParams(lyparams);
        rly3.setLayoutParams(lyparams);

        rly.setBackgroundColor(Color.GRAY);
        rly2.setBackgroundColor(Color.BLACK);
        rly3.setBackgroundColor(Color.WHITE);


        ViewPager viewPager = new ViewPager(this);
        LayoutParams lyvpparams = new LayoutParams(LayoutParams.MATCH_PARENT,300);
        viewPager.setLayoutParams(lyvpparams);
        viewPager.setAdapter(pagerAdapter);
        rly2.addView(viewPager);

        lys.add(rly);
        lys.add(rly2);
        lys.add(rly3);

        vp.setAdapter(lyPagerAdapter);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(TAG, "onPageScrolled: position:"+position+" positionOffset:"+positionOffset+" positionOffsetPixels:"+positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "onPageSelected: "+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i(TAG, "onPageScrollStateChanged: "+state);
            }
        });
    }

    private PagerAdapter lyPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return lys.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(lys.get(position));

            return lys.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(lys.get(position));
        }
    };

    private PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(views.get(position));

            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
    };
}
