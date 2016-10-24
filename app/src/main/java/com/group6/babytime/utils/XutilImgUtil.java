package com.group6.babytime.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.group6.babytime.R;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by Administrator on 2016/10/20.
 */
public class XutilImgUtil {
    public static void display(ImageView imageView, String iconUrl) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setIgnoreGif(false)//是否忽略gif图。false表示不忽略。不写这句，默认是true
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setFailureDrawableId(R.drawable.ic_timeline_oper_lol_nor)
                .setLoadingDrawableId(R.drawable.put_sw_photo)
                .setUseMemCache(true) // 内存缓存
                .setConfig(Bitmap.Config.RGB_565)//设置最低配置


                .build();
        x.image().bind(imageView, iconUrl,imageOptions);
    }
}
