package com.group6.babytime.application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by user on 2016/9/24.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
