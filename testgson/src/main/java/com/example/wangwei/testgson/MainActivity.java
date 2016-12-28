package com.example.wangwei.testgson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

        String json = "{\"nae\":\"wangwei\", \"ag\":23, \"score\":99}";
        Gson gson = new Gson();
        Model model = gson.fromJson(json, Model.class);

        if(model != null){
            tv.setText("name is "+model.getName()+" age is "+model.getAge());
        }else {
            tv.setText("parse error");
        }

    }


    public static class Model{
        private int age;
        private String name;
        private String pare;

        public String getPare() {
            return pare;
        }

        public void setPare(String pare) {
            this.pare = pare;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
