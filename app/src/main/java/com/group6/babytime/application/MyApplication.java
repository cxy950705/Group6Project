package com.group6.babytime.application;

import android.app.Application;

import com.group6.babytime.constant.APPConstants;

import org.xutils.x;

import cn.smssdk.SMSSDK;

/**
 * Created by user on 2016/9/24.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);

        SMSSDK.initSDK(this, APPConstants.MOB_APP_KEY,APPConstants.MOB_APP_SECRET);
    }
}
