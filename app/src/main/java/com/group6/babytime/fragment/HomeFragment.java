package com.group6.babytime.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.group6.babytime.R;

import cn.smssdk.gui.DefaultContactViewItem;


/**
 * Created by user on 2016/10/12.
 */
public class HomeFragment extends Fragment{




    private BaseAdapter adapter;
    private ListView lv_test;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment,null);


        return v;

    }

    public void initView() {

    }


    public void initData() {
        getData();
    }


    public void initEvent() {

    }
    public void getData(){

    }
    public void initItemView(DefaultContactViewItem.ViewHolder viewHolder){

    }
}
