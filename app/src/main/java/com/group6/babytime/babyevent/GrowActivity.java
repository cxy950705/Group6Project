package com.group6.babytime.babyevent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.group6.babytime.R;
import com.group6.babytime.titlebar.TitleBar;

import java.util.ArrayList;
import java.util.List;

public class GrowActivity extends FragmentActivity {

    private TextView ShengaoTv;
    private TextView TizhongTv;

    private ImageView GrowLineIv;
    private ViewPager GrowPageVp;

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;


    /**
     * Fragment
     */
    private ShengaoFragment mShengaoFg;
    private TizhongFragment mTizhongFg;

    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;
    /**
     * 屏幕的宽度
     */
    private int screenWidth;
    private TitleBar titlebar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grow);
        titlebar = ((TitleBar) findViewById(R.id.titlebar));
        titlebar.setTitle("成长记录");
        titlebar.setImgLeftRes(R.drawable.arro_left);
        titlebar.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });



        findByid();
        init();
        initTabLineWidth();




    }



    private void findByid() {
        //找控件
        ShengaoTv = ((TextView) findViewById(R.id.id_shengao_tv));
        TizhongTv = ((TextView) findViewById(R.id.id_tizhong_tv));
        GrowLineIv = ((ImageView) findViewById(R.id.id_grow_line_iv));
        GrowPageVp = ((ViewPager) findViewById(R.id.grow_page_vp));

    }
    private void init() {
        //初始化
        mShengaoFg = new ShengaoFragment();
        mTizhongFg = new TizhongFragment();


        mFragmentList.add(mShengaoFg);
        mFragmentList.add(mTizhongFg);


        mFragmentAdapter =new FragmentAdapter(this.getSupportFragmentManager(),mFragmentList);
        GrowPageVp.setAdapter(mFragmentAdapter);
        Log.i("grow", "init: ");
       GrowPageVp.setCurrentItem(0);

        GrowPageVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

              /*state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。*/

            @Override
            public void onPageScrollStateChanged(int state) {

            }


             /* position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
             offsetPixels:当前页面偏移的像素位置*/

            @Override
            public void onPageScrolled(int position, float offset,
                                       int offsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) GrowLineIv
                        .getLayoutParams();

                Log.e("offset:", offset + "");

                 /*利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
                  设置mTabLineIv的左边距 滑动场景：
                 记3个页面,
                  从左到右分别为0,1,
                 0->1; 1->0*/


                if (currentIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 2) + currentIndex
                            * (screenWidth / 2));

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 2) + currentIndex
                            * (screenWidth / 2));
                }
                GrowLineIv.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        ShengaoTv.setTextColor(Color.BLUE);
                        break;
                    case 1:
                        TizhongTv.setTextColor(Color.BLUE);
                        break;
                }
                currentIndex = position;
            }
        });
    }
    /**
     * 设置滑动条的宽度为屏幕的1/2(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) GrowLineIv
                .getLayoutParams();
        lp.width = screenWidth / 2;
        GrowLineIv.setLayoutParams(lp);
    }

    /**
     * 重置颜色
     */
    private void resetTextView() {
        ShengaoTv.setTextColor(Color.BLACK);
        TizhongTv.setTextColor(Color.BLACK);
    }

}
