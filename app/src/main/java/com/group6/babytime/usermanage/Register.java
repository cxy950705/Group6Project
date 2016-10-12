package com.group6.babytime.usermanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.group6.babytime.R;
import com.group6.babytime.utils.StringUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;



public class Register extends AppCompatActivity implements View.OnClickListener{

    private EditText phone_number;
    private EditText input_password;

    private Button register_btn;
    private Button validate_code_btn;

    public boolean isChanged=false;
    private boolean tag=true;
    private int TIME_LOOP=60;//设定短信60S一次发送
    Thread thread=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initview();//初始化页面

    }

    /**
     * 初始化页面
     */
    public void initview(){
        phone_number = ((EditText) findViewById(R.id.phone_number));
        input_password = ((EditText) findViewById(R.id.input_password));
        register_btn = ((Button) findViewById(R.id.register_btn));
        validate_code_btn = ((Button) findViewById(R.id.validate_code_btn));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.validate_code_btn:
                if(!isVaildate())
                    break;
                validate_code_btn.setText("获取验证码");
                validate_code_btn.setClickable(true);
                break;

        }
    }



    private  boolean isVaildate(){
       String username=phone_number.getText().toString().trim();
       if(username.equals("")){
           Toast.makeText(this,"手机号码不能为空！",Toast.LENGTH_SHORT).show();
           return false;
       }

       if(!StringUtils.isPhoneNumberValid(username)){
           Toast.makeText(this,"手机号有误！",Toast.LENGTH_SHORT).show();
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

}
