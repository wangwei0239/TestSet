package com.example.wangwei.imageprocess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout svCover;
    private View picRect;
    private ViewTreeObserver observer;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        svCover = (RelativeLayout) findViewById(R.id.sv_cover);
        picRect = findViewById(R.id.pic_rect);
        image = (ImageView) findViewById(R.id.image);

//        image.setImageDrawable(draw);

        observer = svCover.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            int svCoverWidth = svCover.getWidth();
            int svCoverHeight = svCover.getHeight();

            int holderTop = 0;
            int holderBottom = 0;
            int holderLeft = 0;
            int holderRight = 0;


            @Override
            public void onGlobalLayout() {

                //1.测量控件宽高
                measureView();
                //2.改变背景图片尺寸
                Bitmap bg = changePicSize();
                //3.抠图
                bg = ImageUtils.cutoutPic(bg,holderLeft,holderTop,holderBottom,holderRight);
                //4.设置图片
                svCover.setBackground(new BitmapDrawable(bg));
                svCover.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }

            private Bitmap changePicSize() {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.bg_face_scanning);
                Bitmap draw = ImageUtils.resizeImage(bitmap,svCoverWidth,svCoverHeight);
                return draw;
            }

            private void measureView() {
                svCoverWidth = svCover.getWidth();
                svCoverHeight = svCover.getHeight();
                holderTop = picRect.getTop();
                holderBottom = picRect.getBottom();
                holderLeft = picRect.getLeft();
                holderRight = picRect.getRight();
                System.out.println("svCoverHeight:"+svCoverHeight+" svCoverWidth:"+svCoverWidth+" holderTop:"+holderTop+" holderBottom:"+holderBottom+" holderLeft:"+holderLeft+" holderRight:"+holderRight);
            }
        });
    }
}
