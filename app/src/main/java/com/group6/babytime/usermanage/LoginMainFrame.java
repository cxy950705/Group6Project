package com.group6.babytime.usermanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.group6.babytime.R;
import com.group6.babytime.constant.APPConstants;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

public class LoginMainFrame extends AppCompatActivity {

    private Button login_btn;
    private Button register_btn;
    private Button qq_btn;
    private Button weibo_btn;
    private Button weixin_btn;

    APPConstants constants=new APPConstants();
    //微博部分
    private SsoHandler mSsoHandler;
    private AuthInfo mAuthInfo;
    private Oauth2AccessToken mAccessToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login_main_frame);

        login_btn = (Button) findViewById(R.id.login);
        register_btn = (Button) findViewById(R.id.register);

        qq_btn = ((Button) findViewById(R.id.qq_btn));
        weibo_btn = ((Button) findViewById(R.id.weibo_btn));
        weixin_btn = ((Button) findViewById(R.id.weixin_btn));


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginMainFrame.this,Login.class);
                startActivity(intent);
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginMainFrame.this,Register.class);
                startActivity(intent);
            }
        });


    }


}
