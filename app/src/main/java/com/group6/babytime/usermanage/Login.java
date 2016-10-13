package com.group6.babytime.usermanage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.group6.babytime.MainActivity;
import com.group6.babytime.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class Login extends AppCompatActivity {
    private TextView create_user;
    private Button login_btn;
    private EditText et_username;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = ((EditText) findViewById(R.id.et_username));
        et_password = ((EditText) findViewById(R.id.et_password));

        create_user = (TextView) findViewById(R.id.new_user);

        login_btn = ((Button) findViewById(R.id.login_btn));
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginIsSuccess()){
                    login();
                }
            }
        });

        create_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
    }


    //判断登录是否成功
    private boolean loginIsSuccess() {
        String userName = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if ("".equals(userName)) {
            // 用户输入用户名为空
            Toast.makeText(Login.this, "用户名不能为空!", Toast.LENGTH_SHORT)
                    .show();
            return false;
        } else if ("".equals(password)) {
            // 密码不能为空
            Toast.makeText(Login.this, "密码不能为空!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void login(){
         RequestParams params=new RequestParams("http://10.40.5.37:8080/Login/CheckLogin");

        String userName = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        params.addBodyParameter("username",userName);
        params.addBodyParameter("password",password);
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if(result.equals("登录成功")){
                    Toast.makeText(x.app(), "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }

                else
                    Toast.makeText(x.app(), "登录失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), "登录超时，请检查你的网络设置", Toast.LENGTH_SHORT).show();
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





