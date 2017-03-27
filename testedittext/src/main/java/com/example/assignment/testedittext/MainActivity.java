package com.example.assignment.testedittext;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private Button btn;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.et);
        btn = (Button) findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.tv);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    protected void showDialog() {
//        MDialogFragment dialogFragment = new MDialogFragment();
//        dialogFragment.setDismssListener(dismissListener);
//        dialogFragment.show(getFragmentManager(), "DIALOG");

        long duration = 10000;
        Display display = this.getWindowManager().getDefaultDisplay();
        TextView frameLayout = tv;
        float[] scale = new float[2];
        scale[0] = 1.0f;
        scale[1] = 0.8f;
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(frameLayout, "scaleX", scale).setDuration(duration);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(frameLayout, "scaleY", scale).setDuration(duration);
        float[] rotation = new float[]{0,10,0};
        ObjectAnimator rotationX = ObjectAnimator.ofFloat(frameLayout, "rotationX", rotation).setDuration(duration);

        float[] translation = new float[1];
        translation[0] = -display.getWidth() * 0.2f / 2;
        ObjectAnimator translationY = ObjectAnimator.ofFloat(frameLayout, "translationY",translation).setDuration(duration);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX,scaleY,rotationX,translationY);
        animatorSet.setTarget(frameLayout);
        animatorSet.start();
    }
}
