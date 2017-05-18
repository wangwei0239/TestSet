package com.jackwang.testrecyclerviewdragdrop;

/**
 * Created by wangwei on 17/4/13.
 */

public interface OnItemCallbackListener {

    void onMove(int fromPosition, int toPosition);
    void onSwipe(int position);

}
