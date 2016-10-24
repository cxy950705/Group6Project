package com.group6.babytime.more;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.group6.babytime.R;
import com.group6.babytime.fragment.MoreFragment;
import com.group6.babytime.utils.StringUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class FeedBackActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView bar_title;
    private TextView btn_send_feedback;
    private TextView back_to_menu;

    private TextView tv_suggestion;
    private EditText et_suggestion;
    private TextView tv_contact;
    private EditText et_contact;
    private TextView tv_feedbackinfo;


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
        View actionbarLayout = LayoutInflater.from(this).inflate(R.layout.feedback_title, null);
        actionBar.setCustomView(actionbarLayout);
            back_to_menu = ((TextView) actionbarLayout.findViewById(R.id.backtomenu));
        bar_title = (TextView)actionbarLayout.findViewById(R.id.feedback_title);
        btn_send_feedback = (TextView)actionbarLayout.findViewById(R.id.btn_send_feedback);
        }
        setContentView(R.layout.activity_feed_back);

        back_to_menu.setOnClickListener(this);
        btn_send_feedback.setOnClickListener(this);

        initView();
    }

    private void initView(){
        tv_suggestion = ((TextView) findViewById(R.id.tv_suggestion));
        et_suggestion = ((EditText) findViewById(R.id.et_suggestion));
        tv_contact = ((TextView) findViewById(R.id.tv_contact));
        et_contact = ((EditText) findViewById(R.id.et_contact));
        tv_feedbackinfo = ((TextView) findViewById(R.id.tv_feedbackinfo));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backtomenu:
                finish();
                break;
            case R.id.btn_send_feedback:
                String contact=et_contact.getText().toString().trim();
                if(StringUtils.isQQValidate(contact) || StringUtils.isPhoneNumberValid(contact)){
                    sendFeedBack();
                    finish();
                }else {
                    Toast.makeText(FeedBackActivity.this, "您输入的QQ号或者电话号码格式有误", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    public void sendFeedBack(){
        RequestParams params=new RequestParams("http://10.40.5.37:8080/Login/AddFeedBackServlet");

        SharedPreferences sp=getSharedPreferences("user", Context.MODE_PRIVATE);
        String username=sp.getString("username","");
        String comments=et_suggestion.getText().toString();
        String contact=et_contact.getText().toString().trim();


        params.addQueryStringParameter("username",username);
        params.addQueryStringParameter("comments", comments);
        params.addQueryStringParameter("contact", contact);

        x.http().post(params, new Callback.CommonCallback<String>(){

            @Override
            public void onSuccess(String result) {

                Toast.makeText(FeedBackActivity.this, "提交成功，感谢您的反馈", Toast.LENGTH_SHORT).show();
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
