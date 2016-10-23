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
import com.group6.babytime.usermanage.Login;
import com.group6.babytime.utils.DataCleanManager;
import com.group6.babytime.utils.StringUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

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
        btn_save.setOnClickListener(this);

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
                break;
            case R.id.btn_save:
                submitPassword();
                break;
        }
    }

    private boolean EditextIsEmpty(){
        String new_password=et_new_password.getText().toString().trim();
        String old_password=et_old_password.getText().toString().trim();
        String confirm_password=et_comfirm_password.getText().toString().trim();
        return !(old_password.equals("") || new_password.equals("") || confirm_password.equals(""));
    }

    private boolean isSamePassword(){
        String new_password=et_new_password.getText().toString().trim();
        String old_password=et_old_password.getText().toString().trim();
        return old_password.equals(new_password);
    }

    private boolean isConfirmedPassword(){
        String new_password=et_old_password.getText().toString().trim();
        String confirm_password=et_comfirm_password.getText().toString().trim();
        return new_password.equals(confirm_password);
    }

    private void submitPassword(){
        RequestParams params=new RequestParams("http://10.40.5.37:8080/Login/ChangePasswordServlet");

        SharedPreferences sp=getSharedPreferences("user", Context.MODE_PRIVATE);
        String username=sp.getString("username","");
        String confirm_password=et_comfirm_password.getText().toString().trim();

        params.addQueryStringParameter("username",username);
        params.addQueryStringParameter("new_password", confirm_password);

        x.http().get(params, new Callback.CommonCallback<String>(){

            @Override
            public void onSuccess(String result) {
                Toast.makeText(getApplicationContext(),"修改成功，请重新登录",Toast.LENGTH_SHORT).show();
                DataCleanManager.cleanSharedPreference(getApplicationContext());
                Intent intent=new Intent(ChangePasswordActivity.this,Login.class);
                finish();
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
