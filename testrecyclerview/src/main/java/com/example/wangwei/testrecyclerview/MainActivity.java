package com.example.wangwei.testrecyclerview;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity
{
//    private RecyclerView mRecyclerView;
//    private List<String> mDatas;
//    private HomeAdapter mAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        initData();
//        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
//
//    }
//
//    protected void initData()
//    {
//        mDatas = new ArrayList<String>();
//        for (int i = 'A'; i < 'z'; i++)
//        {
//            mDatas.add("" + (char) i);
//        }
//    }
//
//    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
//    {
//
//        @Override
//        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
//        {
//            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
//                    HomeActivity.this).inflate(R.layout.item_home, parent,
//                    false));
//            return holder;
//        }
//
//        @Override
//        public void onBindViewHolder(MyViewHolder holder, int position)
//        {
//            holder.tv.setText(mDatas.get(position));
//        }
//
//        @Override
//        public int getItemCount()
//        {
//            return mDatas.size();
//        }
//
//        class MyViewHolder extends ViewHolder
//        {
//
//            TextView tv;
//
//            public MyViewHolder(View view)
//            {
//                super(view);
//                tv = (TextView) view.findViewById(R.id.id_num);
//            }
//        }
//    }

}
