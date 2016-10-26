package com.group6.babytime.home;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.group6.babytime.R;
import com.group6.babytime.babyevent.xUtilsImageUtils;
import com.group6.babytime.pojo.LogDiary;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ReadDiaryActivity extends AppCompatActivity {

    @InjectView(R.id.iv_return)
    ImageView ivReturn;
    @InjectView(R.id.tv_taitou)
    TextView tvTaitou;

    @InjectView(R.id.tv_zhengwen1)
    TextView tvZhengwen1;
    @InjectView(R.id.iv_riji1)
    ImageView ivRiji1;
    @InjectView(R.id.iv_riji2)
    ImageView ivRiji2;
    @InjectView(R.id.iv_riji3)
    ImageView ivRiji3;
    @InjectView(R.id.iv_riji4)
    ImageView ivRiji4;
    @InjectView(R.id.tv_time2)
    TextView tvTime2;

    List<String> popContents = new ArrayList<String>();
    LogDiary logs;
    @InjectView(R.id.iv_add)
    TextView ivAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_diary);
        ButterKnife.inject(this);
        initView();
        initData();
        initEvent();
    }

    public void initView() {
    }

    public void initData() {
        tvTaitou.setText("日志");
        ivAdd.setText("编辑");
//        popContents.add("分享");
//        popContents.add("编辑日志");

        Intent intent = getIntent();
        logs = intent.getParcelableExtra("logsInfo");

        if (logs != null) {

            tvZhengwen1.setText(logs.getLogContent());
            int year = logs.getLogDate().getYear() + 1900;
            int month = logs.getLogDate().getMonth() + 1;
            int day = logs.getLogDate().getDate();
            tvTime2.setText("" + String.valueOf(year) + "年" + String.valueOf(month) + "月" + String.valueOf(day) + "日");
            xUtilsImageUtils.display(ivRiji1,"http://10.40.5.2:8080/Group6/upload/"+logs.getLogPhoto(),false);
        }
    }

    public void initEvent() {
    }


    private void initPopupWindow(final View v) {
        //content
        View view = LayoutInflater.from(this).inflate(R.layout.lv_tanchuang, null);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, 200);

        //listview设置数据源
        ListView lv = (ListView) view.findViewById(R.id.lv_tanchuang);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.lv_item_tanchuang, popContents);
        lv.setAdapter(arrayAdapter);

        //popupwiondow外面点击，popupwindow消失
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        //显示在v的下面
        popupWindow.showAsDropDown(v);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //关闭popupwidow


                popupWindow.dismiss();
                if (position == 0) {
                    Toast.makeText(ReadDiaryActivity.this, "分享", Toast.LENGTH_SHORT).show();
                } else if (position == 1) {
                    Intent intent = new Intent(v.getContext(), WriteDiaryActivity.class);
                    intent.putExtra("logsInfo", logs);
                    v.getContext().startActivity(intent);
                }


            }
        });
    }


    @OnClick({R.id.iv_return, R.id.iv_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_return:
                finish();
                break;
            case R.id.iv_add:
                Intent intent = new Intent(this, WriteDiaryActivity.class);
                intent.putExtra("logsInfo", logs);
                startActivity(intent);
                finish();
                break;
        }
    }
}
