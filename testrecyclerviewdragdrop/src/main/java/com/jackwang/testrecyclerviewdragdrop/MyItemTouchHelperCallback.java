package com.jackwang.testrecyclerviewdragdrop;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by wangwei on 17/4/13.
 */

public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private OnItemCallbackListener listener;

    public MyItemTouchHelperCallback(OnItemCallbackListener listener){
        this.listener = listener;
    }


    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        int swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeMovementFlags(dragFlag, swipeFlag);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        listener.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwipe(viewHolder.getAdapterPosition());
    }
}
