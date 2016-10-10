package com.group6.babytime.usermanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.group6.babytime.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    private EditText phone_number;
    private EditText input_password;
    private Button register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        phone_number = ((EditText) findViewById(R.id.phone_number));
        input_password = ((EditText) findViewById(R.id.input_password));
        register_btn = ((Button) findViewById(R.id.register_btn));

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Validate()){
                    register();
                }else {
                    Toast.makeText(Register.this, "注册信息不合法", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private boolean Validate(){
        if(!isMobileNum(phone_number.getText().toString().trim()) || (input_password.getText().toString().length()<8
                || input_password.getText().toString().length()>24)) {
            return false;
        }
        return true;
    }

    private void register() {
        RequestParams params=new RequestParams("http://10.40.5.37:8080/Login/AddUser");
        String userName = phone_number.getText().toString().trim();
        String password =input_password.getText().toString().trim();
        params.addBodyParameter("username",userName);
        params.addBodyParameter("password",password);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if(result.equals("用户注册成功")) {
                    Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Register.this,Nickname.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(Register.this, "注册失败，该用户已经存在", Toast.LENGTH_SHORT).show();
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
    public static boolean isMobileNum(String telNum){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(telNum);
        return m.matches();
    }
}
