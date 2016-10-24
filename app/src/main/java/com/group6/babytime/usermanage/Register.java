package com.group6.babytime.usermanage;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.group6.babytime.MainActivity;
import com.group6.babytime.R;
import com.group6.babytime.constant.APPConstants;
import com.group6.babytime.utils.StringUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;



public class Register extends AppCompatActivity implements View.OnClickListener {

    private EditText phone_number;
    private EditText input_password;

    private Button register_btn;
    private Button validate_code_btn;
    private EditText et_code;
    public int TIME_LOOP = 60;

    public BroadcastReceiver smsBroadcastReceiver;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == -9) {
                validate_code_btn.setText("重新发送(" + TIME_LOOP-- + ")");
            } else if (msg.what == -8) {
                validate_code_btn.setText("获取验证码");
                validate_code_btn.setClickable(true);
            } else {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                Log.e("event", "event=" + event);
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 短信注册成功后，返回MainActivity,然后提示新好友
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                        Toast.makeText(getApplicationContext(), "提交验证码成功",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Register.this,
                                MainActivity.class);
                        startActivity(intent);
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Toast.makeText(getApplicationContext(), "验证码已经发送",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        ((Throwable) data).printStackTrace();
                    }
                }
            }
        }
    };
    private void initSDK() {
        SMSSDK.initSDK(Register.this, APPConstants.MOB_APP_KEY, APPConstants.MOB_APP_SECRET);
        EventHandler eventHandler = new EventHandler() {
            /**
             * 在操作之后被触发
             *
             * @param event
             *            参数1
             * @param result
             *            参数2 SMSSDK.RESULT_COMPLETE表示操作成功，为SMSSDK.
             *            RESULT_ERROR表示操作失败
             * @param data
             *            事件操作的结果
             */
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        // 注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initview();//初始化页面
        initSDK();
    }

    /**
     * 初始化页面
     */
    public void initview() {
        phone_number = ((EditText) findViewById(R.id.phone_number));
        input_password = ((EditText) findViewById(R.id.input_password));
        et_code = ((EditText) findViewById(R.id.et_code));
        register_btn = ((Button) findViewById(R.id.register_btn));
        validate_code_btn = ((Button) findViewById(R.id.validate_code_btn));

        validate_code_btn.setOnClickListener(this);
        register_btn.setOnClickListener(this);
        phone_number.setOnClickListener(this);
        input_password.setOnClickListener(this);
        et_code.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String phone = phone_number.getText().toString().trim();
        String code=et_code.getText().toString().trim();
        switch (v.getId()) {
            case R.id.validate_code_btn:
                if(phone.equals("")){
                    Toast.makeText(this,"手机号码不能为空",Toast.LENGTH_SHORT).show();
                }
                if (!StringUtils.isPhoneNumberValid(phone)) {
                    Toast.makeText(this,"手机号码格式有误",Toast.LENGTH_SHORT).show();
                }

                //SDK发送短信进行验证
                SMSSDK.getVerificationCode("86", phone);

                validate_code_btn.setClickable(false);
                validate_code_btn.setText("重新发送(" + TIME_LOOP-- + ")");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 60; i > 0; i--) {
                            handler.sendEmptyMessage(-9);
                            if (i <= 0) {
                                break;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        handler.sendEmptyMessage(-8);
                    }
                }).start();


                break;
            case R.id.register_btn:
                if(Validate()){
                    SMSSDK.submitVerificationCode("86",phone,code);
                    register();
                }
                break;

            case R.id.phone_number:
                phone_number.setText("");
                break;
            case R.id.input_password:
                input_password.setText("");
                break;
            case R.id.et_code:
                et_code.setText("");
                break;
        }
    }

    private boolean Validate(){
        if(!StringUtils.isPhoneNumberValid(phone_number.getText().toString().trim()) || (input_password.getText().toString().length()<8
                || input_password.getText().toString().length()>24)) {
            return false;
        }
        return true;
    }

    private void register() {
        RequestParams params = new RequestParams("http://10.40.5.37:8080/Login/AddUser");
        String userName = phone_number.getText().toString().trim();
        String password = input_password.getText().toString().trim();
        params.addBodyParameter("username", userName);
        params.addBodyParameter("password", password);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (result.equals("用户注册成功")) {
                    Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                } else
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //
        this.unregisterReceiver(smsBroadcastReceiver);
        SMSSDK.unregisterAllEventHandler();
    }

}
