package com.example.wangwei.lockscreendemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by Jack on 2016/6/27.
 */
public class PicResultActivity extends Activity{

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_pic_result);
        imageView = (ImageView) findViewById(R.id.image);
        Bitmap bitmap = (Bitmap) getIntent().getBundleExtra("pic").get("data");
        imageView.setImageBitmap(bitmap);
    }
}
