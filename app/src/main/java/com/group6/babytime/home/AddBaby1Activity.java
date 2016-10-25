package com.group6.babytime.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.group6.babytime.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AddBaby1Activity extends AppCompatActivity {

    @InjectView(R.id.iv_return)
    ImageView ivReturn;
    @InjectView(R.id.tv_taitou)
    TextView tvTaitou;
    @InjectView(R.id.iv_add)
    ImageView ivAdd;
    @InjectView(R.id.iv_addbaby1)
    ImageView ivAddbaby1;
    @InjectView(R.id.iv_addbaby2)
    ImageView ivAddbaby2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_baby1);
        ButterKnife.inject(this);

        initData();

    }


    public void initData(){
        tvTaitou.setText("添加宝宝");
    }



    @OnClick({R.id.iv_return, R.id.iv_addbaby1, R.id.iv_addbaby2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_return:
                finish();
                break;
            case R.id.iv_addbaby1:
                //男 传一个sex过去,还没写
                Intent intent=new Intent(this,AddBaby2Activity.class);
                intent.putExtra("sexInfo","男");
                startActivity(intent);
                finish();
                break;
            case R.id.iv_addbaby2:
                //女
                Intent intent1=new Intent(this,AddBaby2Activity.class);
                intent1.putExtra("sexInfo","女");
                startActivity(intent1);
                finish();
                break;
        }
    }
}
