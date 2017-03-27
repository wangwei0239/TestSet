package com.example.assignment.testmarquee;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gongwen.marqueen.MarqueeView;

public class MainActivity extends AppCompatActivity {

    MarqueeView tv;
    EditText et;
    Button btn;

    boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        final List<String> datas = Arrays.asList("《赋得古原草送别》", "离离原上草，一岁一枯荣。", "野火烧不尽，春风吹又生。", "远芳侵古道，晴翠接荒城。", "又送王孙去，萋萋满别情。");
//        final List<String> datas2 = Arrays.asList("Emotibot","表情");
//
//        et = (EditText) findViewById(R.id.et);
//        btn = (Button) findViewById(R.id.btn);
//        tv = (MarqueeView) findViewById(R.id.tv);
//
//        final MarqueeFactory<EmojiconTextView,String> marqueeFactory = new NoticeMF(this);
//        tv.setMarqueeFactory(marqueeFactory);
//        tv.startFlipping();
//        marqueeFactory.setData(datas);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clicked = !clicked;
//                if(clicked){
//                    marqueeFactory.setData(datas2);
//                }else {
//                    marqueeFactory.setData(datas);
//                }
//            }
//        });

        setContentView(R.layout.new_layout);
        final MyMarquee marquee = (MyMarquee) findViewById(R.id.mymq);
        Button btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marquee.startAnim("\uD83D\uDE19\uD83D\uDE1D\uD83D\uDE1C\uD83E\uDD17\uD83D\uDE0D \uD83D\uDE18\uD83D\uDE02\uD83D\uDE07\uD83D\uDE0C\uD83D\uDE43");
            }
        });

    }
}
