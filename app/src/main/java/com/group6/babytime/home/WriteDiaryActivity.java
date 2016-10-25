package com.group6.babytime.home;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group6.babytime.R;
import com.group6.babytime.pojo.LogDiary;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class WriteDiaryActivity extends AppCompatActivity {

    @InjectView(R.id.iv_return)
    ImageView ivReturn;
    @InjectView(R.id.tv_taitou)
    TextView tvTaitou;
    @InjectView(R.id.iv_add)
    TextView ivAdd;
    @InjectView(R.id.tv_zhengwen1)
    EditText tvZhengwen1;
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


    List<String> popContents=new ArrayList<String>();
    LogDiary logs;
    String babyid;
    Integer babyid1;
    String logcontent="";
    int year;
    int month;
    int day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);
        ButterKnife.inject(this);
        initView();
        initData();
        initEvent();
    }

    public void initView(){}

    public void initData(){
        tvTaitou.setText("写日记");
//        popContents.add("保存发布");
//        popContents.add("删除日志");

        Intent intent1 = getIntent();
        babyid = intent1.getStringExtra("babyId");
        Log.i("WriteDiaryActivity", "onSuccess: "+babyid);

        if (babyid!=null){
            babyid1 = Integer.parseInt(babyid);
        }

        Intent intent= getIntent();
        logs= intent.getParcelableExtra("logsInfo");

        if(logs!=null) {

            tvZhengwen1.setText(logs.getLogContent());
            year = logs.getLogDate().getYear()+1900;
            month = logs.getLogDate().getMonth()+1;
            day = logs.getLogDate().getDate();
            tvTime2.setText("写日记的日期："+String.valueOf(year)+"年"+String.valueOf(month)+"月"+String.valueOf(day)+"日");
            babyid1 = logs.getBabyId();
        }else {

            year = Calendar.getInstance().get(Calendar.YEAR);
            month = Calendar.getInstance().get(Calendar.MONTH)+1;
            day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            tvTime2.setText("今天是："+String.valueOf(year)+"年"+String.valueOf(month)+"月"+String.valueOf(day)+"日");
        }


    }

    public void initEvent(){}


    @OnClick({R.id.iv_return, R.id.iv_add, R.id.tv_zhengwen1, R.id.iv_riji1, R.id.iv_riji2, R.id.iv_riji3, R.id.iv_riji4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_return:
                finish();
                break;
            case R.id.iv_add:
                Toast.makeText(WriteDiaryActivity.this,"保存发布",Toast.LENGTH_SHORT).show();
                RequestParams requestParams=new RequestParams("http://10.40.5.2:8080/Group6/insertlogdiaryservlet");//访问网络的url
                LogDiary logDiary = new LogDiary();

                logDiary.setUserId(9);
                Timestamp d = new Timestamp(System.currentTimeMillis());
                logDiary.setLogDate(d);
                logcontent = tvZhengwen1.getText().toString().trim();
                logDiary.setLogContent(logcontent);
                logDiary.setLogPhoto(null);
                logDiary.setLogLikenum(0);
                logDiary.setBabyId(babyid1);

                Gson gson = new GsonBuilder().create();
                String logInfo =gson.toJson(logDiary);
                try {
                    requestParams.addQueryStringParameter("logInfo", URLEncoder.encode(logInfo,"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.i("WriteDiaryActivity", "onSuccess: ");
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });



                finish();
                break;
            case R.id.tv_zhengwen1:
                logcontent = tvZhengwen1.getText().toString().trim();
                Toast.makeText(WriteDiaryActivity.this,"内容:"+logcontent,Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_riji1:
                break;
            case R.id.iv_riji2:
                break;
            case R.id.iv_riji3:
                break;
            case R.id.iv_riji4:
                break;
        }
    }



}
