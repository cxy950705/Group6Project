package com.group6.babytime.babyevent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group6.babytime.R;

/**
 * Created by 博博 on 2016/10/24.
 */
public class ShengaoFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        Log.i("sg", "onCreateView:sg ");
        View shengaoView = inflater.inflate(R.layout.layout_shengao, container,false);
        return shengaoView;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
}
