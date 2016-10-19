package com.group6.babytime.more;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.group6.babytime.R;

public class ChangeUsernameActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView bar_title;
    private TextView btn_save;
    private TextView back_to_menu;
    private EditText et_new_nickname;
    private TextView tv_nickname_info;

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
            View actionbarLayout = LayoutInflater.from(this).inflate(R.layout.nickname_title, null);
            actionBar.setCustomView(actionbarLayout);
            back_to_menu = ((TextView) actionbarLayout.findViewById(R.id.backtomenu));
            bar_title = (TextView)actionbarLayout.findViewById(R.id.nickname_title);
            btn_save = (TextView)actionbarLayout.findViewById(R.id.btn_save);
        }
        setContentView(R.layout.activity_change_username);
        back_to_menu.setOnClickListener(this);

        initView();

    }

    private void initView(){
        et_new_nickname = ((EditText) findViewById(R.id.et_new_nickname));
        tv_nickname_info = ((TextView) findViewById(R.id.tv_nickname_info));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backtomenu:
                finish();
        }
    }
}
