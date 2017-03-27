package com.example.assignment.testmarquee;

import android.content.Context;
import android.view.LayoutInflater;

import com.gongwen.marqueen.MarqueeFactory;
import com.rockerhieu.emojicon.EmojiconTextView;

/**
 * Created by wangwei on 17/3/10.
 */

public class NoticeMF extends MarqueeFactory<EmojiconTextView, String> {

    private LayoutInflater inflater;

    public NoticeMF(Context mContext) {
        super(mContext);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public EmojiconTextView generateMarqueeItemView(String data) {

        EmojiconTextView mView = (EmojiconTextView) inflater.inflate(R.layout.notice_item, null);
        mView.setText(data);
        return mView;
    }
}
