package com.example.wangwei.newtextlink;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.text);

        SpannableString spanableInfo = new SpannableString(
                "点击确认即表示您同意并签署《管理服务协议》及《风险提示书》");
        int firsStar = spanableInfo.toString().indexOf("《");
        int firstEnd = spanableInfo.toString().indexOf("》");
        int end = spanableInfo.length();
        //  1-管理服务协议页面； 2-金风险提示书页面
        spanableInfo.setSpan(new Clickable( 1), firsStar, firstEnd+1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanableInfo.setSpan(new Clickable(2), firstEnd + 2, end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spanableInfo);
        //setMovementMethod()该方法必须调用，否则点击事件不响应
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    class Clickable extends ClickableSpan {
        // 1-跳转到投资咨询及管理服务协议页面； 2-挑战到投资资金风险提示书页面
        private int type;
        public Clickable(int type) {
            super();
            this.type = type;
        }
        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(getResources().getColor(android.R.color.holo_green_dark));
            ds.setUnderlineText(false);
        }
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this,"haha",Toast.LENGTH_LONG).show();
        }
    }
}
