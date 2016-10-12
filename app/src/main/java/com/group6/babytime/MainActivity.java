package com.group6.babytime;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.group6.babytime.fragment.AlbumFragment;
import com.group6.babytime.fragment.BabyEventFragment;
import com.group6.babytime.fragment.HomeFragment;
import com.group6.babytime.fragment.MoreFragment;

public class MainActivity extends FragmentActivity {

    private FragmentTabHost fragmentTabHost;
    private String texts[] = { "成长", "云相册", "宝宝事件", "更多" };
    private int imageButton[] = { R.drawable.bt_home_selector,
                                      R.drawable.bt_album_selector,
                                      R.drawable.bt_babyevent_selector,
                                      R.drawable.bt_more_selector};
    private Class fragmentArray[] = {HomeFragment.class, AlbumFragment.class,BabyEventFragment.class,MoreFragment.class};

               @Override
        protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
               setContentView(R.layout.activity_main);
               // 实例化tabhost
                 fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
                 fragmentTabHost.setup(this, getSupportFragmentManager(),
                              R.id.maincontent);

                for (int i = 0; i < texts.length; i++) {
                        TabHost.TabSpec spec=fragmentTabHost.newTabSpec(texts[i]).setIndicator(getView(i));
                       fragmentTabHost.addTab(spec, fragmentArray[i], null);

                     }

            }

             private View getView(int i) {
                 //取得布局实例
                View view=View.inflate(MainActivity.this, R.layout.tab_content, null);

               //取得布局对象
                 ImageView imageView=(ImageView) view.findViewById(R.id.image);
                 TextView textView=(TextView) view.findViewById(R.id.text);

                 //设置图标
                imageView.setImageResource(imageButton[i]);
                //设置标题
                textView.setText(texts[i]);
                return view;
             }
}
