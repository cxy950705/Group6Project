package com.group6.babytime.more;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.group6.babytime.R;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView bar_title;
    private TextView btn_save;
    private TextView back_to_menu;


    //控件
    private TextView tv_old_password;
    private TextView tv_new_password;
    private TextView tv_comfirm_password;
    private EditText et_old_password;
    private EditText et_new_password;
    private EditText et_comfirm_password;


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
            View actionbarLayout = LayoutInflater.from(this).inflate(R.layout.password_title, null);
            actionBar.setCustomView(actionbarLayout);
            back_to_menu = ((TextView) actionbarLayout.findViewById(R.id.backtomenu));
            bar_title = (TextView)actionbarLayout.findViewById(R.id.password_title);
            btn_save = (TextView)actionbarLayout.findViewById(R.id.btn_save);
        }
        setContentView(R.layout.activity_change_password);
        back_to_menu.setOnClickListener(this);

        initView();

    }

    private void initView(){
        et_old_password = ((EditText) findViewById(R.id.et_old_password));
        tv_old_password = ((TextView) findViewById(R.id. tv_old_password));
        et_new_password = ((EditText) findViewById(R.id.et_new_password));
        tv_new_password = ((TextView) findViewById(R.id. tv_new_password));
        et_comfirm_password = ((EditText) findViewById(R.id.et_confirm_password));
        tv_comfirm_password = ((TextView) findViewById(R.id.tv_confirm_password));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backtomenu:
                finish();
        }
    }
}
