package com.example.wangwei.testgson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

        String json = "{\"name\":\"wangwei\", \"ae\":0, \"score\":99}";
        Gson gson = new Gson();
        Model model = gson.fromJson(json, Model.class);

        if(model != null){
            tv.setText("name is "+model.getName()+"; age is "+model.getAge()+"; pare is"+model.getPare());
        }else {
            tv.setText("parse error");
        }

//        ArrayList<String> list = new ArrayList<>();
//        list.add("wang-");
//        list.add(null);
//        list.add("-wei");
//        String str = "";
//        for(String s : list){
//            str+=s;
//        }
//        tv.setText(str);
//        tv.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.i("touch","Event:"+event.getAction());
//                return false;
//            }
//        });


//        list.add("");
//        list.add("");
//        list.add("");
//        list.add("");
//        list.add("");
//        list.add("");
//        list.add("");
//        list.add("");
//        list.add("");
//        list.add("");
//        list.add("");
//        list.add("");
//        list.add("");
//        list.add("");
//        list.add("");
//        list.add("");

//        rv = (RecyclerView) findViewById(R.id.rv);
//        FunctionAdapter adapter = new FunctionAdapter(this, list);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
//        rv.setLayoutManager(layoutManager);
//        rv.setAdapter(adapter);

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
