package com.group6.babytime.fragment;

import android.support.v4.app.Fragment;

import com.group6.babytime.R;


/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class FragmentFactory {

    private static Fragment fragment;

    public static Fragment createFragment(int checkedId){

            switch (checkedId){

                case R.id.btn_growth://主页
                    fragment=new HomeFragment();
                    break;

                case R.id.btn_album://云相册
                    fragment=new AlbumFragment();
                    break;

                case R.id.btn_babyevent://宝宝事件
                    fragment=new BabyEventFragment();
                    break;

                case R.id.btn_me://我的
                    fragment=new MoreFragment();
                    break;
            }

        return fragment;
    }
}
