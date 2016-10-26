package com.group6.babytime.babyevent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.group6.babytime.R;
import com.group6.babytime.fragment.BabyEventFragment;
import com.group6.babytime.titlebar.TitleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstActivity extends Activity {


    private ListView lv_first_listview;
    List<String> data ;
    private TimelineAdapter timelineAdapter;
    private TitleBar titlebar;
    private TextView tv_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        titlebar = ((TitleBar) findViewById(R.id.titlebar));
        titlebar.setTitle("宝宝第一次");
        titlebar.setTvRight("添加");
        //找到titlebar 右边的保存控件
        tv_right = ((TextView) findViewById(R.id.tv_right));
        //设置点击事件
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AddFirstActivity.class);
               startActivity(intent);
            }
        });
        titlebar.setImgLeftRes(R.drawable.arro_left);
        titlebar.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                Intent intent=new Intent(getApplicationContext(), BabyEventFragment.class);
//                startActivity(intent);
                //Toast.makeText(YimiaoInfoActivity.this, "你点击了疫苗详情", Toast.LENGTH_SHORT).show();
            }
        });

        lv_first_listview = (ListView) this.findViewById(R.id.lv_first_listview);
        lv_first_listview.setDividerHeight(0);
        timelineAdapter = new TimelineAdapter(this, getData());
        lv_first_listview.setAdapter(timelineAdapter);
         //lv_first_listview中的每个item的点击事件
        lv_first_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),AddFirstActivity.class);
                startActivity(intent);
            }
        });

    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "这是第1行测试数据");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "这是第2行测试数据");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "这是第3行测试数据");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "这是第4行测试数据");
        list.add(map);
        return list;
    }
}
