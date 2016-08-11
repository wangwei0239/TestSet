package com.example.wangwei.testfragment2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangwei on 16/7/29.
 */
public class FragmentContent extends Fragment{

    private String id = "fragment1";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_1,null);
    }

    public String getIdStr(){
        return id;
    }
}
