package com.group6.babytime.application;

import android.app.Application;

import com.group6.babytime.constant.APPConstants;
import com.group6.babytime.pojo.LogDiary;

import org.xutils.x;

import cn.smssdk.SMSSDK;

/**
 * Created by user on 2016/9/24.
 */
public class MyApplication extends Application{
    private LogDiary babyId=new LogDiary(1);//设置一个默认的用户：id=1

    public LogDiary getBabyId() {
        return babyId;
    }

    public void setBabyId(LogDiary babyId) {
        this.babyId = babyId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);

        SMSSDK.initSDK(this, APPConstants.MOB_APP_KEY,APPConstants.MOB_APP_SECRET);
    }
}
