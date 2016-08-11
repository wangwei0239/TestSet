package com.example.wangwei.newtextlink;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by wangwei on 16/8/3.
 */
public class HyperLinkText {

    private CharSequence[] linkStrings;
    private String sourceStr = "";
    public HyperLinkText(CharSequence... linkStrings){
        this.linkStrings = linkStrings;
        for(CharSequence linkStr : linkStrings){
            sourceStr += linkStr.toString();
        }
    }

    public SpannableString toSpannableString(){
        SpannableString spanableInfo = new SpannableString(sourceStr);
        int curLength = 0;
        for(CharSequence linkStr : linkStrings){
            if(linkStr instanceof LinkString){
                spanableInfo.setSpan(linkStr,curLength,curLength+linkStr.toString().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            curLength += linkStr.toString().length();
        }
        return spanableInfo;
    }


    public static class LinkString extends ClickableSpan implements CharSequence{
        private String str = null;
        private String link = null;

        protected OnClickListener onClickListener;

        @Override
        public String toString() {
            return str;
        }

        @Override
        public int length() {
            return str.length();
        }

        @Override
        public char charAt(int index) {
            return str.charAt(index);
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            return str.subSequence(start,end);
        }

        public interface OnClickListener{
            void onClick(LinkString linkString, View view);
        }

        public LinkString(String str,String link,OnClickListener onClickListener){
            this.str = str;
            this.link = link;
            this.onClickListener = onClickListener;
        }

        public String getStr() {
            return str;
        }

        public String getLink() {
            return link;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(Color.BLUE);
            ds.setUnderlineText(true);
        }

        @Override
        public void onClick(View widget) {
            if(onClickListener != null){
                onClickListener.onClick(this,widget);
            }
        }
    }
}
