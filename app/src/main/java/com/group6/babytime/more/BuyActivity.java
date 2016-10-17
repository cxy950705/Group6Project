package com.group6.babytime.more;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.group6.babytime.R;
import com.group6.babytime.constant.APPConstants;

public class BuyActivity extends AppCompatActivity {
    private WebView myWebView;
    private TextView title_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_buy);
        title_bar = ((TextView) findViewById(R.id.buy_title_bar));
        myWebView=(WebView)findViewById(R.id.wv_shopping);

        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(APPConstants.TAOBAO_WEBSITE);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            myWebView.goBack();
            return true;
        }
        return false;
    }


}


