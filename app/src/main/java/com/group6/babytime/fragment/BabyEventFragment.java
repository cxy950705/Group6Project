package com.group6.babytime.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group6.babytime.R;
import com.group6.babytime.babyevent.FirstActivity;
import com.group6.babytime.babyevent.GrowActivity;
import com.group6.babytime.babyevent.TingActivity;
import com.group6.babytime.babyevent.YimiaoActivity;


import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by user on 2016/10/12.
 */
public class BabyEventFragment extends Fragment {

    @InjectView(R.id.iv_yici)
    ImageView ivYici;
    @InjectView(R.id.tv_fir)
    TextView tvFir;
    @InjectView(R.id.iv_yimiao)
    ImageView ivYimiao;
    @InjectView(R.id.tv_sec)
    TextView tvSec;
    @InjectView(R.id.iv_czjl)
    ImageView ivCzjl;
    @InjectView(R.id.tv_thir)
    TextView tvThir;
    @InjectView(R.id.iv_tt)
    ImageView ivTt;
    @InjectView(R.id.tv_fur)
    TextView tvFur;
    private TextView tv_sec;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.baby_event_fragment, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_fir, R.id.tv_sec, R.id.tv_thir, R.id.tv_fur})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_fir:
                Intent inten1 = new Intent(getActivity(), FirstActivity.class);
                startActivity(inten1);
                break;
            case R.id.tv_sec:
                Intent inten2 = new Intent(getActivity(), YimiaoActivity.class);
                startActivity(inten2);
                break;
            case R.id.tv_thir:
                Intent inten3 = new Intent(getActivity(), GrowActivity.class);
                startActivity(inten3);
                break;
            case R.id.tv_fur:
                Intent inten4 = new Intent(getActivity(), TingActivity.class);
                startActivity(inten4);
                break;
        }
    }
}