package com.group6.babytime.more;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.group6.babytime.R;
import com.group6.babytime.constant.APPConstants;

public class BuyActivity extends AppCompatActivity implements View.OnClickListener{
    //ActionBar
    private TextView buy_title;
    private TextView back_to_menu;
    private TextView tv_empty;

    private WebView myWebView;
    private TextView title_bar;

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
            View actionbarLayout2 = LayoutInflater.from(this).inflate(R.layout.buy_title, null);
            actionBar.setCustomView(actionbarLayout2);
            buy_title = (TextView)actionbarLayout2.findViewById(R.id.buy_title);
            back_to_menu = ((TextView) actionbarLayout2.findViewById(R.id.backtomenu));
            tv_empty = (TextView)actionbarLayout2.findViewById(R.id.tv_empty);

        }
        setContentView(R.layout.activity_buy);
        myWebView=(WebView)findViewById(R.id.wv_shopping);

        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(APPConstants.TAOBAO_WEBSITE);

        back_to_menu.setOnClickListener(this);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            finish();
            return true;
        }
        return false;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backtomenu:
                finish();
        }
    }

}


