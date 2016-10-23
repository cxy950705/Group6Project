package com.group6.babytime.more;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.group6.babytime.R;

public class FeedBackActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView bar_title;
    private TextView btn_send;
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
        btn_send = (TextView)actionbarLayout.findViewById(R.id.btn_send);
        }
        setContentView(R.layout.activity_feed_back);

        back_to_menu.setOnClickListener(this);
        btn_send.setOnClickListener(this);

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
        }
    }
}
