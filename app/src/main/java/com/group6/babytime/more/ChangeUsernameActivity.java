package com.group6.babytime.more;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.group6.babytime.R;
import com.group6.babytime.fragment.MoreFragment;
import com.group6.babytime.usermanage.Login;
import com.group6.babytime.utils.DataCleanManager;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

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
        btn_save.setOnClickListener(this);

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
                break;

            case R.id.btn_save:
                changeNickname();
                break;
        }
    }

    public void changeNickname(){
        RequestParams params=new RequestParams("http://10.40.5.37:8080/Login/AddNickname");

        SharedPreferences sp=getSharedPreferences("user", Context.MODE_PRIVATE);
        String username=sp.getString("username","");
        String nickname=et_new_nickname.getText().toString().trim();

        params.addQueryStringParameter("nickname", nickname);
        params.addQueryStringParameter("username",username);

        x.http().get(params, new Callback.CommonCallback<String>(){

            @Override
            public void onSuccess(String result) {
                SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("username",result);
                editor.commit();
                Toast.makeText(ChangeUsernameActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ChangeUsernameActivity.this,MoreFragment.class);
                startActivity(intent);


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
}
