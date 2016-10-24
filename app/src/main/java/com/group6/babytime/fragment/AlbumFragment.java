package com.group6.babytime.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.group6.babytime.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/10/21.
 */
public class AlbumFragment extends Fragment implements View.OnClickListener {
    @InjectView(R.id.lv_biaoti)
    FrameLayout lvBiaoti;
    @InjectView(R.id.id_album_left)
    TextView idAlbumLeft;
    @InjectView(R.id.id_album_liebiao)
    RelativeLayout idAlbumLiebiao;
    @InjectView(R.id.id_album_right)
    TextView idAlbumRight;
    @InjectView(R.id.id_album_zuijin)
    RelativeLayout idAlbumZuijin;
    @InjectView(R.id.id_album_1)
    LinearLayout idAlbum1;
    @InjectView(R.id.id_prod_list_sort_line1)
    View idProdListSortLine1;
    @InjectView(R.id.lv_album)
    ViewPager lvAlbum;
    private View view;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private ViewPager mPaper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.album_fragment, container, false);
        initLayout();
        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mPaper.setAdapter(mAdapter);
        mPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int currentIndex;

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageSelected(int position) {
                resetColor();
                switch (position) {
                    case 0:

                        idAlbumLeft.setTextColor(Color.rgb(87,153,8));
                        break;
                    case 1:
                        idAlbumRight.setTextColor(Color.rgb(87, 153, 8));
                        break;

                    default:
                        idAlbumLeft.setTextColor(Color.rgb(87, 153, 8));
                        break;
                }
                currentIndex = position;

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        ButterKnife.inject(this, view);
        return view;
    }

    /**
     * 初始化控件
     */
    public void initLayout() {
        idAlbumLeft = (TextView) view.findViewById(R.id.id_album_left);
        idAlbumRight = (TextView) view.findViewById(R.id.id_album_right);

        mPaper = (ViewPager) view.findViewById(R.id.lv_album);

        idAlbumLeft.setOnClickListener(this);
        idAlbumRight.setOnClickListener(this);


        FragmentPage1 f1 = new FragmentPage1();
        FragmentPage2 f2 = new FragmentPage2();

        mFragments.add(f1);
        mFragments.add(f2);

    }

    public void resetColor() {
        idAlbumLeft.setTextColor(Color.rgb(56, 56, 56));
        idAlbumRight.setTextColor(Color.rgb(56, 56, 56));

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_album_left:
                resetColor();
                idAlbumLeft.setTextColor(Color.rgb(87, 153, 8));
                mPaper.setCurrentItem(0);
                break;

            case R.id.id_album_right:
                resetColor();
                idAlbumRight.setTextColor(Color.rgb(87, 153, 8));
                mPaper.setCurrentItem(1);
                break;


            default:
                break;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /**
     * ViewPager适配器
     */
    public class MyPagerAdapter extends PagerAdapter {
        public List<Activity> mListViews;

        public MyPagerAdapter(List<Activity> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public int getCount() {
            return mListViews.size();

        }

        @Override
        public void finishUpdate(View arg0) {

        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }
    }
}
