package com.group6.babytime.babyevent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.group6.babytime.R;
import com.group6.babytime.titlebar.TitleBar;

public class AddFirstActivity extends Activity {
    private TitleBar titlebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_first);

        titlebar = ((TitleBar) findViewById(R.id.titlebar));

        titlebar.setTitle("宝宝第一次");
        titlebar.setTvRight("保存");
        titlebar.setImgLeftRes(R.drawable.arro_left);
        titlebar.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), FirstActivity.class);
                startActivity(intent);
                //Toast.makeText(YimiaoInfoActivity.this, "你点击了疫苗详情", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
