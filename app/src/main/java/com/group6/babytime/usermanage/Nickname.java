package com.group6.babytime.usermanage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.group6.babytime.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class Nickname extends AppCompatActivity {

    private EditText et_nickname;
    private Button btn_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);

        et_nickname = ((EditText) findViewById(R.id.et_nickname));
        btn_nickname = ((Button) findViewById(R.id.btn_nickname));

        btn_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNickname();
            }
        });
    }

    public void addNickname(){
        RequestParams params=new RequestParams("http://10.40.5.37:8080/Login/addNickname");
        String nickname=et_nickname.getText().toString().trim();
        params.addBodyParameter("nickname",nickname);

        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

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
