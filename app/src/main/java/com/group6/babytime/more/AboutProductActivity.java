package com.group6.babytime.more;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.group6.babytime.R;

public class AboutProductActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView about_title;
    private TextView back_to_menu;
    private TextView tv_empty;

    private ImageView icon_launcher;
    private TextView version_code;
    private Button update_version;
    private TextView copyright_info1;
    private TextView copyright_info2;


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
            View actionbarLayout2 = LayoutInflater.from(this).inflate(R.layout.new_version_title, null);
            actionBar.setCustomView(actionbarLayout2);
            about_title = (TextView)actionbarLayout2.findViewById(R.id.about_title);
            back_to_menu = ((TextView) actionbarLayout2.findViewById(R.id.backtomenu));
            tv_empty = (TextView)actionbarLayout2.findViewById(R.id.tv_empty);

        }
        setContentView(R.layout.activity_new_version);
        back_to_menu.setOnClickListener(this);
        initView();
    }

    private void initView(){
        icon_launcher = ((ImageView) findViewById(R.id.icon_launcher));
        version_code = ((TextView) findViewById(R.id.tv_versioncode));

        update_version = ((Button) findViewById(R.id.update_version));
        copyright_info1 = ((TextView) findViewById(R.id.copyright_info1));
        copyright_info2 = ((TextView) findViewById(R.id.copyright_info2));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backtomenu:
                finish();
        }
    }
}
