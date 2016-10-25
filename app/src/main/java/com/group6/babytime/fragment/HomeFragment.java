package com.group6.babytime.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.group6.babytime.R;
import com.group6.babytime.application.MyApplication;
import com.group6.babytime.home.AddBaby1Activity;
import com.group6.babytime.home.ChangeBabyActivity;
import com.group6.babytime.home.ChooseBabyActivity;
import com.group6.babytime.home.ReadDiaryActivity;
import com.group6.babytime.home.WriteDiaryActivity;
import com.group6.babytime.pojo.Baby;
import com.group6.babytime.pojo.LogDiary;
import com.group6.babytime.utils.CommonAdapter;
import com.group6.babytime.utils.ViewHolder;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * Created by user on 2016/10/12.
 */
public class HomeFragment extends Fragment {

    List<LogDiary> logs = new ArrayList<LogDiary>();//存放商品信息

    List<LogDiary> newLogs = new ArrayList<LogDiary>();
  //  List<Baby> babys = new ArrayList<Baby>();
    CommonAdapter<Baby> babyAdapter;


    CommonAdapter<LogDiary> diaryAdapter;
    @InjectView(R.id.tv_choose)
    TextView tvChoose;
    @InjectView(R.id.iv_addbaby)
    ImageView ivAddbaby;

    @InjectView(R.id.lv_head)
    ListView lvHead;
    @InjectView(R.id.iv_babyhead)
    ImageView ivBabyhead;
    @InjectView(R.id.tv_babyname)
    TextView tvBabyname;
    @InjectView(R.id.tv_famliy)
    TextView tvFamliy;
    @InjectView(R.id.tv_xieriji)
    Button tvXieriji;
    @InjectView(R.id.tv_qupai)
    Button tvQupai;
    @InjectView(R.id.tv_shipin)
    Button tvShipin;
    @InjectView(R.id.ll_gongju)
    RelativeLayout llGongju;
    @InjectView(R.id.homepage_second)
    RelativeLayout homepageSecond;
    @InjectView(R.id.lv_test)
    ListView lvTest;

    int likeNum;
    @InjectView(R.id.tv_babybirth)
    TextView tvBabybirth;

    private ArrayList<String> list = new ArrayList<String>();
    ImageView ivedit;
    int babyid3;
    int babyid2;
    int babyid1;
    // ReadDiaryActivity 11  WriteDiaryActivity 12  ChangeBabyActivity 13  ChooseBabyActivity 14  AddBabyActivity 15
    public static final int READ_DIARY = 11;
    public static final int WRITE_DIARY = 12;
    public static final int CHANGE_BABY = 13;
    public static final int CHOOSE_BABY = 14;
    public static final int ADD_BABY = 15;
    public static final int QUPAI = 16;
    public static final int SHIPIN = 17;
    int flag = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, null);
        ButterKnife.inject(this, v);

        initData();
        initEvent();

        return v;

    }


    public void initData() {
        getBabyData();
        switchBabyId();


    }

    private void switchBabyId() {
        Baby baby1 = null;


        try {
            baby1 = getArguments().getParcelable("babyInfo");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(baby1 == null){
            Log.i("1111", "initData---------" + baby1);

            getData();
        }else {
            Log.i("1111", "initData+++++++++" + baby1);
            babyid1 = baby1.getBabyId();
            int year = baby1.getBabyBirthday().getYear();
            int month = baby1.getBabyBirthday().getMonth() + 1;
            int day = baby1.getBabyBirthday().getDate();
            tvBabyname.setText("宝宝：" + baby1.getBabyName());
            tvBabybirth.setText("宝宝生日：" + String.valueOf(year) + "年" + String.valueOf(month) + "月" + String.valueOf(day) + "日");
            getData();
        }
        Log.i("1111", "initData+++++++++" + babyid1);
    }

    public void getBabyData() {
        //192.168.1.10
        //10.40.5.2
        String url = "http://10.40.5.2:8080/Group6/querybabybyidservlet";//访问网络的url

        RequestParams requestParams = new RequestParams(url);

        requestParams.addQueryStringParameter("babyId", ((MyApplication) getActivity().getApplication()).getBabyId().getBabyId() + "");

        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("1111", "onSuccess---------" + result);
                final GsonBuilder gsonBuilder = new GsonBuilder();
                final Gson gson = gsonBuilder.create();
                Baby[] babys = gson.fromJson(result, Baby[].class);
                Log.i("111", "babys" + babys);

                int year = babys[0].getBabyBirthday().getYear();
                int month = babys[0].getBabyBirthday().getMonth() + 1;
                int day = babys[0].getBabyBirthday().getDate();
                tvBabyname.setText("宝宝：" + babys[0].getBabyName());
                tvBabybirth.setText("宝宝生日：" + String.valueOf(year) + "年" + String.valueOf(month) + "月" + String.valueOf(day) + "日");

                babyid1 = babys[0].getBabyId();
                switchBabyId();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("1111---HomeFragment","onError---------"+ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    public void initEvent() {
        //lvTest的item点击事件
        lvTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(getActivity(), ReadDiaryActivity.class);

                //点击item的
                intent.putExtra("logsInfo", logs.get(position));
                //startActivity(intent);
                //这样写跳转到Activity，过后能跳到mainactivity
                startActivityForResult(intent, READ_DIARY);
            }
        });

    }

    public void getData() {
        String url = "http://10.40.5.2:8080/Group6/querylogservlet";//访问网络的url

        RequestParams requestParams = new RequestParams(url);

        requestParams.addQueryStringParameter("babyId", babyid1 + "");

        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<LogDiary>>() {
                }.getType();
                Log.i("1111", "onSuccess---------" + result);

                newLogs = gson.fromJson(result, type);//解析成List
                Log.i("111", "newLogs" + newLogs);
                logs.clear();//清空原来的数据
                logs.addAll(newLogs);


                diaryAdapter = new CommonAdapter<LogDiary>(getActivity(), logs, R.layout.layout) {


                    @Override
                    public void convert(ViewHolder viewHolder, final LogDiary logDiary, final int position) {

                        //获得日志内容
                        TextView tvcontent = viewHolder.getViewById(R.id.tv_zhengwen);
                        tvcontent.setText(logDiary.getLogContent());

                        //获得日志时间
                        TextView tvtime = viewHolder.getViewById(R.id.tv_time1);
                        tvtime.setText(String.valueOf(logDiary.getLogDate()));

                        int month = logDiary.getLogDate().getMonth() + 1;
                        int day = logDiary.getLogDate().getDate();
                        TextView tvdate = viewHolder.getViewById(R.id.tv_date);
                        tvdate.setText(String.valueOf(month) + "月" + String.valueOf(day) + "日");

                        //获得点赞数
                        final TextView tvlike = viewHolder.getViewById(R.id.tv_collect);
                        tvlike.setText(String.valueOf(logDiary.getLogLikenum()));
                        likeNum = logDiary.getLogLikenum();

                        //如果图片为0，图片gone

                        //点击“编辑”跳转
                        ImageView ivedit = viewHolder.getViewById(R.id.iv_edit);
                        ivedit.setTag(position);

                        ivedit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), WriteDiaryActivity.class);
                                intent.putExtra("logsInfo", logs.get(position));
                                startActivityForResult(intent, WRITE_DIARY);
                            }
                        });

                        //点击“删除”删除
                        ImageView ivdelete = viewHolder.getViewById(R.id.iv_delete);
                        ivdelete.setTag(position);

                        ivdelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "删除", Toast.LENGTH_SHORT).show();
                            }
                        });

                        //点击“分享”分享
                        ImageView ivshare = viewHolder.getViewById(R.id.iv_share);
                        ivshare.setTag(position);

                        ivshare.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "分享", Toast.LENGTH_SHORT).show();
                            }
                        });

                        //点击“评论”评论
                        TextView tvdianjipinglun = viewHolder.getViewById(R.id.tv_dianjipinglun);
                        tvdianjipinglun.setTag(position);

                        tvdianjipinglun.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "评论", Toast.LENGTH_SHORT).show();
                            }
                        });

                        //点赞，按钮变色，点赞数量+-
                        final ImageButton ibcollect = viewHolder.getViewById(R.id.btn_collect);
                        ibcollect.setFocusable(false);
                        ibcollect.setTag(position);


                        ibcollect.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (flag == 0) {
                                    ibcollect.setBackgroundResource(R.drawable.collect_selected);
                                    tvlike.setText(String.valueOf(logDiary.getLogLikenum() + 1));
                                    flag = 1;
                                } else {
                                    ibcollect.setBackgroundResource(R.drawable.collect);
                                    tvlike.setText(String.valueOf(logDiary.getLogLikenum()));
                                    flag = 0;
                                }
                            }
                        });

                        //如果评论数为0，评论gone
                    }

                };
                lvTest.setAdapter(diaryAdapter);

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


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @OnClick({R.id.tv_choose, R.id.iv_addbaby, R.id.iv_babyhead, R.id.tv_famliy, R.id.tv_xieriji, R.id.tv_qupai, R.id.tv_shipin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_choose:
                Intent intent1 = new Intent(getActivity(), ChooseBabyActivity.class);
                startActivityForResult(intent1, CHOOSE_BABY);
                break;
            case R.id.iv_addbaby:
                Intent intent2 = new Intent(getActivity(), AddBaby1Activity.class);
                startActivityForResult(intent2, ADD_BABY);
                break;
            case R.id.iv_babyhead:
                Intent intent3 = new Intent(getActivity(), ChangeBabyActivity.class);
                babyid3 = babyid1;
                Log.i("1111", "onClick" + babyid3);
                intent3.putExtra("babyId",String.valueOf(babyid3));
                startActivityForResult(intent3, CHANGE_BABY);
                break;
            case R.id.tv_famliy:
                Toast.makeText(getActivity(), "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_xieriji:
                Intent intent = new Intent(getActivity(), WriteDiaryActivity.class);
                babyid2 = babyid1;
                Log.i("1111", "onClick" + babyid2);
                intent.putExtra("babyId",String.valueOf(babyid2));
                startActivityForResult(intent, WRITE_DIARY);
                break;
            case R.id.tv_qupai:
                break;
            case R.id.tv_shipin:
                break;
        }
    }
}
