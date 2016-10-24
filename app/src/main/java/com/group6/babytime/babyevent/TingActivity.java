package com.group6.babytime.babyevent;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.group6.babytime.R;
import com.group6.babytime.titlebar.TitleBar;

import java.util.ArrayList;
import java.util.List;

public class TingActivity extends FragmentActivity {
    private TextView PaihangTv;
    private TextView TongHuastoryTv;
    private TextView ErgeTv;
    private ImageView TabLineIv;
    private ViewPager PageVp;

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;

    /**
     * Fragment
     */
    private PaihangFragment mPaihangFg;
    private TongHuaFragment mTonghauFg;
    private ErGeFragment mErgeFg;
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
        setContentView(R.layout.activity_ting);
        titlebar = ((TitleBar) findViewById(R.id.titlebar));
        titlebar.setTitle("宝宝听听");
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
        PaihangTv = ((TextView) findViewById(R.id.id_paihang_tv));
        TongHuastoryTv = ((TextView) findViewById(R.id.id_tonghuastory_tv));
        ErgeTv = ((TextView) findViewById(R.id.id_erge_tv));
        TabLineIv = ((ImageView) findViewById(R.id.id_tab_line_iv));
        PageVp = ((ViewPager) findViewById(R.id.id_page_vp));

    }
    private void init() {
        mPaihangFg = new PaihangFragment();
        mTonghauFg = new TongHuaFragment();
        mErgeFg =new ErGeFragment();
        mFragmentList.add(mPaihangFg);
        mFragmentList.add(mTonghauFg);
        mFragmentList.add(mErgeFg);


        mFragmentAdapter =new FragmentAdapter(this.getSupportFragmentManager(),mFragmentList);
        PageVp.setAdapter(mFragmentAdapter);
        PageVp.setCurrentItem(0);

        PageVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }

            /**
             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
             * offsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float offset,
                                       int offsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) TabLineIv
                        .getLayoutParams();

                Log.e("offset:", offset + "");
                /**
                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
                 * 设置mTabLineIv的左边距 滑动场景：
                 * 记3个页面,
                 * 从左到右分别为0,1,2
                 * 0->1; 1->2; 2->1; 1->0
                 */

                if (currentIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));

                } else if (currentIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                }
                TabLineIv.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        PaihangTv.setTextColor(Color.BLUE);
                        break;
                    case 1:
                        TongHuastoryTv.setTextColor(Color.BLUE);
                        break;
                    case 2:
                        ErgeTv.setTextColor(Color.BLUE);
                        break;
                }
                currentIndex = position;
            }
        });
    }
    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) TabLineIv
                .getLayoutParams();
        lp.width = screenWidth / 3;
        TabLineIv.setLayoutParams(lp);
    }

    /**
     * 重置颜色
     */
    private void resetTextView() {
        PaihangTv.setTextColor(Color.BLACK);
        TongHuastoryTv.setTextColor(Color.BLACK);
        ErgeTv.setTextColor(Color.BLACK);
    }


}
