package com.group6.babytime.babyevent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.group6.babytime.R;
/**
 * Created by Administrator on 2016/10/21 0021.
 */
public class PaihangFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View paihangView = inflater.inflate(R.layout.activity_tab_paihang, container,false);
        return paihangView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
}
