package com.group6.babytime.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import com.group6.babytime.MainActivity;
import com.group6.babytime.R;
import com.group6.babytime.fragment.HomeFragment;
import com.group6.babytime.pojo.Baby;
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

public class ChooseBabyActivity extends AppCompatActivity {

    @InjectView(R.id.iv_return)
    ImageView ivReturn;
    @InjectView(R.id.tv_taitou)
    TextView tvTaitou;
    @InjectView(R.id.iv_add)
    ImageView ivAdd;
    @InjectView(R.id.lv_cb)
    ListView lvCb;


    List<Baby> babys = new ArrayList<Baby>();//存放baby信息
    List<Baby> newBabys = new ArrayList<Baby>();
    CommonAdapter<Baby> babyAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_baby);
        ButterKnife.inject(this);
        initData();
        initEvent();
    }

    private void initEvent() {
        lvCb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ChooseBabyActivity.this, MainActivity.class);
                intent.putExtra("babyInfo", babys.get(position));
                Log.i("1111","onItemClick---------"+babys);
                startActivity(intent);
                finish();


            }
        });
    }

    private void initData() {
        tvTaitou.setText("选择想看的宝宝");
        getData();
    }

    private void getData() {
        String url = "http://10.40.5.2:8080/Group6/queryallbabyservlet";

        RequestParams requestParams = new RequestParams(url);

        x.http().get(requestParams, new Callback.CommonCallback<String>(){
            @Override
            public void onSuccess(String result) {

                Gson gson = new Gson();

                Type type = new TypeToken<List<Baby>>() {}.getType();
                Log.i("1111","onSuccess---------"+result);
                babys = gson.fromJson(result, type);
                Log.i("111", "babys" + babys);

//                newBabys = gson.fromJson(result, type);//解析成List
//                Log.i("111", "newBabys" + newBabys);
//                babys.clear();//清空原来的数据
//                babys.addAll(newBabys);

                babyAdapter = new CommonAdapter<Baby>(ChooseBabyActivity.this,babys,R.layout.choosebaby_listview) {
                    @Override
                    public void convert(ViewHolder viewHolder, Baby baby, int position) {

                        ImageView ivbh1 = viewHolder.getViewById(R.id.iv_babyhead1);

                        TextView tvcb1 = viewHolder.getViewById(R.id.tv_cb1);
                        tvcb1.setText(baby.getBabyName());

                        TextView tvcb2 = viewHolder.getViewById(R.id.tv_cb2);
                        int year = baby.getBabyBirthday().getYear()+1900;
                        int month = baby.getBabyBirthday().getMonth()+1;
                        int day = baby.getBabyBirthday().getDate();
                        tvcb2.setText("宝宝生日："+String.valueOf(year)+"年"+String.valueOf(month)+"月"+String.valueOf(day)+"日");


//                        TextView tvcb3 = viewHolder.getViewById(R.id.tv_cb3);
//                        tvcb3.setText(baby.getBabyName());

                    }
                };
                lvCb.setAdapter(babyAdapter);
                babyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("1111---Choose","onError---------"+ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @OnClick(R.id.iv_return)
    public void onClick() {
        finish();
    }
}
