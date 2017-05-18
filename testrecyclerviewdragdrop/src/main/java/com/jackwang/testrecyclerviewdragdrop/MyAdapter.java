package com.jackwang.testrecyclerviewdragdrop;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by wangwei on 17/4/13.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> implements OnItemCallbackListener{

    Random random;

    private List<String> mData;
    private Context mContext;

    public MyAdapter(Context mContext) {
        random = new Random();
        this.mContext = mContext;
        mData = new ArrayList<>();
        mData.add("one");
        mData.add("two");
        mData.add("three");
        mData.add("four");
        mData.add("five");
        mData.add("six");
        mData.add("seven");
        mData.add("eight");
        mData.add("nine");
        mData.add("ten");
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.tv.setText(mData.get(position));
        holder.tv.setBackgroundColor(Color.rgb(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        /**
         * 在这里进行给原数组数据的移动
         */
        Collections.swap(mData, fromPosition, toPosition);
        /**
         * 通知数据移动
         */
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onSwipe(int position) {
        /**
         * 原数据移除数据
         */
        mData.remove(position);
        /**
         * 通知移除
         */
        notifyItemRemoved(position);
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView tv;

        public Holder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
