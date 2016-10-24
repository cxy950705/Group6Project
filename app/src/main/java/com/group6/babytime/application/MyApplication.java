package com.group6.babytime.application;

import android.app.Application;
import android.graphics.Bitmap;

import com.group6.babytime.R;
import com.group6.babytime.constant.APPConstants;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_timeline_oper_lol_nor)//// 加载中显示的默认图片
                .showImageOnFail(R.drawable.put_sw_photo)// 设置加载失败的默认图片
                .cacheInMemory(true)//内存缓存
                .cacheOnDisk(true)//sd卡缓存
                .bitmapConfig(Bitmap.Config.RGB_565)//最低配置
                .build();

        ImageLoaderConfiguration config=new ImageLoaderConfiguration
                .Builder(getApplicationContext())
                .discCacheSize(50*1024*1024)
                .diskCacheFileCount(100)//缓存一百张照片
                .writeDebugLogs()//
                .build();
                ImageLoader.getInstance().init(config);


    }
}
