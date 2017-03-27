package com.example.assignment.testemojirain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.luolc.emojirain.EmojiRainLayout;

public class MainActivity extends AppCompatActivity {

    EmojiRainLayout emojiRainLayout;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emojiRainLayout = (EmojiRainLayout) findViewById(R.id.er);

        emojiRainLayout.addEmoji(R.mipmap.ic_launcher);

        // set emojis per flow, default 6
        emojiRainLayout.setPer(10);

        // set total duration in milliseconds, default 8000
        emojiRainLayout.setDuration(7200);

        // set average drop duration in milliseconds, default 2400
        emojiRainLayout.setDropDuration(2400);

        // set drop frequency in milliseconds, default 500
        emojiRainLayout.setDropFrequency(500);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emojiRainLayout.startDropping();
            }
        });
    }
}
