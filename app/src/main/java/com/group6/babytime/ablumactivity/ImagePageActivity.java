package com.group6.babytime.ablumactivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.os.Bundle;
import android.widget.TextView;

import com.group6.babytime.R;

import java.util.ArrayList;

/**
 * 图片查看器
 * Created by Administrator on 2016/10/21.
 */

public class ImagePageActivity extends FragmentActivity {
    private static final String STATE_POSITION = "STATE_POSITION";
    public static final String EXTRA_IMAGE_INDEX = "photo_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";

    private int pagerPosition;
    private HackyViewPage mpage;
    private TextView indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail_pager);

        pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
        ArrayList<String> urls=getIntent().getStringArrayListExtra(EXTRA_IMAGE_URLS);
        mpage = ((HackyViewPage) findViewById(R.id.page));
        ImagePagerAdapter mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), urls);
        mpage.setAdapter(mAdapter);
        indicator = ((TextView) findViewById(R.id.indicator));

    }
    private class ImagePagerAdapter extends FragmentStatePagerAdapter {
        public ArrayList<String> fileList;
        public ImagePagerAdapter(FragmentManager fm, ArrayList<String> fileList) {
            super(fm);
            this.fileList = fileList;
        }
        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return fileList == null ? 0 : fileList.size();
        }
    }
}
