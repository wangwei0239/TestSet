package com.example.assignment.cutimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final int COVER_HEIGHT = 54*36;
    public static final int COVER_WIDTH = 195*36;
    public static final int COVER_RADIUS = 20*36;

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog);

        iv.setImageBitmap(toRoundRectBitmap(bitmap));
    }

    public static Bitmap toRoundRectBitmap(Bitmap bitmap_in){
//        int width = COVER_WIDTH;
        int width = bitmap_in.getWidth();
//        int height = COVER_HEIGHT;
        int height = bitmap_in.getHeight();

//        int radius = Math.min(width, height) / 2;

//        Matrix matrix = new Matrix();
//        matrix.postScale(width, height);
//        bitmap_in = Bitmap.createBitmap(bitmap_in,0,0,bitmap_in.getWidth(), bitmap_in.getHeight(), matrix, true);

//        bitmap_in = big(bitmap_in, width, height);

        Bitmap output = Bitmap.createBitmap( width*2,height*2,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();

        final Rect src = new Rect(0, 0, width, height);
        final Rect dst = new Rect(0, 0, width*2, height*2);

        paint.setAntiAlias(true);

        canvas.drawARGB(0,0,0,0);

        paint.setColor(Color.parseColor("#ff0000"));

        canvas.drawRoundRect(new RectF(0,0,COVER_WIDTH,COVER_HEIGHT), COVER_RADIUS, COVER_RADIUS, paint);
        canvas.drawRect(0,COVER_RADIUS,COVER_WIDTH,COVER_HEIGHT,paint);
//        canvas.drawRect(0, 0, width*2,height*2,paint);
//        canvas.drawRoundRect();
//        canvas.drawRoundRect();

        BitmapShader shader = new BitmapShader(bitmap_in, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        paint.setShader(shader);


        canvas.drawBitmap(bitmap_in, src, dst, paint);
        return output;
    }

    public static Bitmap big(Bitmap b,float x,float y)
    {
        int w=b.getWidth();
        int h=b.getHeight();
        float sx=(float)x/w;//要强制转换，不转换我的在这总是死掉。
        float sy=(float)y/h;
        Matrix matrix = new Matrix();
        matrix.postScale(sx, sx); // 长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(b, 0, 0, w,
                h, matrix, true);
        return resizeBmp;
    }
}
