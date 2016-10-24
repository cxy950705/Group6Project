package com.group6.babytime.ablumactivity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * 实现相册图片的左右滑动，放大缩小
 * Created by Administrator on 2016/10/22.
 */
public class HackyViewPage extends ViewPager {
    private static final String TAG ="HackyViewPage" ;

    public HackyViewPage(Context context) {
        super(context);
    }

    public HackyViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            // 不理会
            Log.e(TAG, "hacky viewpager error1");
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            // 不理会
            Log.e(TAG, "hacky viewpager error2");
            return false;
        }
    }
}
