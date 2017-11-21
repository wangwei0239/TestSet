package com.jackwang.testrxjava10;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "RX";

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn);

//        Observer<String> observer = new Observer<String>() {
//            @Override
//            public void onCompleted() {
//                Log.i(TAG, "onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.i(TAG, "onError");
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.i(TAG, "onNext:"+s);
//            }
//        };
//
//        final Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("Hello");
//                subscriber.onNext("Hi");
//                subscriber.onNext("Wangwei");
////                subscriber.onCompleted();
//            }
//        });
//
//        final Action1<String> action1 = new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.i(TAG, "action call:"+s);
//            }
//        };
//
//        observable.subscribe(observer);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });

//        String[] names = {"wang","huang","jiao"};
//        Observable.from(names).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.i(TAG, "action call:"+s);
//            }
//        });

        Course course1 = new Course("chinese");
        Course course2 = new Course("math");
        Course course3 = new Course("english");
        Course course4 = new Course("physical");

        ArrayList<Course> courses1 = new ArrayList<>();
        courses1.add(course1);
        courses1.add(course2);
        courses1.add(course4);

        ArrayList<Course> courses2 = new ArrayList<>();
        courses2.add(course2);
        courses2.add(course3);

        Student student1 = new Student("wangwei",courses1);
        Student student2 = new Student("jiao",courses2);

        Student[] students = {student1,student2};

        Subscriber<Course> subscriber = new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course s) {
                Log.i(TAG, "name:"+s.getCourseName());
            }
        };

        Observable.from(students).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                return Observable.from(student.getCourses());
            }
        }).subscribe(subscriber);
    }
}
