package com.group6.babytime.more;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.group6.babytime.R;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{
    //ActionBar
    private TextView setting_title;
    private TextView back_to_menu;
    private TextView tv_empty;

    //控件
    private Button btn_changephone;
    private Button btn_changepassword;
    private Button btn_enable_cellular;
    private Button btn_clearcache;
    private Button btn_logout;
    private Switch switch_cellular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowCustomEnabled(true);
            View actionbarLayout2 = LayoutInflater.from(this).inflate(R.layout.setting_title, null);
            actionBar.setCustomView(actionbarLayout2);
            setting_title = (TextView)actionbarLayout2.findViewById(R.id.about_title);
            back_to_menu = ((TextView) actionbarLayout2.findViewById(R.id.backtomenu));
            tv_empty = (TextView)actionbarLayout2.findViewById(R.id.tv_empty);

        }
        setContentView(R.layout.activity_setting);
        back_to_menu.setOnClickListener(this);

        initView();
    };

    private void initView(){
        btn_changephone = ((Button) findViewById(R.id.btn_changephone));
        btn_changepassword = ((Button) findViewById(R.id.btn_changepassword));
        btn_enable_cellular = ((Button) findViewById(R.id.enable_cellular_data));
        btn_clearcache = ((Button) findViewById(R.id.clear_cache));
        btn_logout = ((Button) findViewById(R.id.btn_logout));

        switch_cellular = ((Switch) findViewById(R.id.switch_cellular));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backtomenu:
                finish();
        }
    }
}
