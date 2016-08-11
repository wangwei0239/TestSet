package com.example.wangwei.testfragment2;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String tag = "fg";

    TextView tv;

    FrameLayout content1;
    FrameLayout content2;
    FragmentManager fragmentManager;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        content1 = (FrameLayout) findViewById(R.id.content1);
        content2 = (FrameLayout) findViewById(R.id.content2);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = fragmentManager.findFragmentByTag(tag);
//               Fragment fragment = new FragmentContent();
                Toast.makeText(MainActivity.this, "idStr:" + ((FragmentContent) fragment).getIdStr() + " ID:" + fragment.getId() + " XML:" + String.valueOf(R.layout.fragment_1) + " Container:" + String.valueOf(R.id.content1), Toast.LENGTH_LONG).show();
            }
        });

        fragmentManager = getSupportFragmentManager();

        addFragment();

//        tv = (TextView) findViewById(R.id.sampletext);
//        String html = "「非著名程序员」可能是东半球最好的技术分享公众号。每天，每周定时推送一些有关移动开发的原创文章和教程。 不信你可以\n";
//        html += "<a href='http://www.baidu.com'>百度一下</a> 哈哈，有意思吧！记住微信号是：smart_android 哦";
//        CharSequence charSequence = Html.fromHtml(html);
//        SpannableStringBuilder builder = new SpannableStringBuilder(
//                charSequence);
//        URLSpan[] urlSpans = builder.getSpans(0, charSequence.length(),
//                URLSpan.class);
//        SpannableStringBuilder style = new SpannableStringBuilder(charSequence);
////        tv.setText(charSequence);
////        Spannable sp = (Spannable) tv.getText();
//        for (URLSpan span : urlSpans) {
//            MyURLSpan myURLSpan = new MyURLSpan(span.getURL());
////            style.setSpan(myURLSpan, sp.getSpanStart(span), sp.getSpanEnd(span), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
//        }
////        tv.setLinksClickable(true);
////        tv.setMovementMethod(LinkMovementMethod.getInstance());
//        tv.setText(charSequence);

        this.tv = (TextView)findViewById(R.id.sampletext);

        CharSequence text = tv.getText();
        if (text instanceof Spannable) {
            int end = text.length();
            Spannable sp = (Spannable) tv.getText();
            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            style.clearSpans();// should clear old spans
            for (URLSpan url : urls) {
                MyURLSpan myURLSpan = new MyURLSpan(url.getURL());
                style.setSpan(myURLSpan, sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
            tv.setText(style);
        }
    }

    public void addFragment() {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.content1, new FragmentContent(), tag);
        ft.addToBackStack(null);
        ft.commit();
    }

    private  class MyURLSpan extends ClickableSpan {

        private String mUrl;

        MyURLSpan(String url) {
            mUrl = url;
        }

        @Override
        public void onClick(View widget) {
            Toast.makeText(MainActivity.this, mUrl, Toast.LENGTH_LONG).show();
            widget.setBackgroundColor(Color.parseColor("#00000000"));
        }
    }
}
