package com.example.wangwei.newtextlink;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * Created by wangwei on 16/8/3.
 */
public class MainActivity2 extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.text);
        HyperLinkText hlt = new HyperLinkText(new HyperLinkText.LinkString("haha","link:haha",onClickListener),"heihei",new HyperLinkText.LinkString("haha2","link:haha2",onClickListener));
        Gson gson = new Gson();
        String json = gson.toJson(hlt);
        textView.setText(hlt.toSpannableString());
        textView.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public HyperLinkText.LinkString.OnClickListener onClickListener = new HyperLinkText.LinkString.OnClickListener() {
        @Override
        public void onClick(HyperLinkText.LinkString linkString, View view) {
            Toast.makeText(MainActivity2.this,"String:"+linkString.getStr()+" link:"+linkString.getLink(),Toast.LENGTH_LONG).show();
        }
    };
}
