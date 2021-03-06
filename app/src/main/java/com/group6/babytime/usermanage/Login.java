package com.group6.babytime.usermanage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.group6.babytime.MainActivity;
import com.group6.babytime.R;
import com.group6.babytime.fragment.MoreFragment;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class Login extends AppCompatActivity {
    private TextView create_user;
    private Button login_btn;
    private EditText et_username;
    private EditText et_password;

    private FragmentManager manager;
    private FragmentTransaction transaction;


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

        final String userName = et_username.getText().toString().trim();
        final String password = et_password.getText().toString().trim();
        params.addBodyParameter("username",userName);
        params.addBodyParameter("password",password);
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if(result.equals("登录成功")){

                    SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("password",password);
                    editor.putString("username",userName);
                    editor.commit();
                    Intent login_intent=new Intent(Login.this, MainActivity.class);
                    startActivity(login_intent);
                    Toast.makeText(x.app(), "登录成功", Toast.LENGTH_SHORT).show();

//                    manager=getSupportFragmentManager();
//                    transaction=manager.beginTransaction();
//
//                    MoreFragment moreFragment=new MoreFragment();
//                    Bundle bundle=new Bundle();
//                    String user_value=et_username.getText().toString().trim();
//                    bundle.putString("user_value",user_value);
//                    moreFragment.setArguments(bundle);
//                    transaction.replace(R.id.rl_userinfo,moreFragment);
//                    transaction.commit();

                }

                if(result.equals("登录失败")){
                    Toast.makeText(x.app(), "登录失败", Toast.LENGTH_SHORT).show();
                }

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





