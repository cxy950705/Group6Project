package com.group6.babytime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.group6.babytime.fragment.BaseActivity;
import com.group6.babytime.fragment.FragmentFactory;
import com.group6.babytime.usermanage.LoginMainFrame;

public class MainActivity extends BaseActivity {
    private FragmentManager fragmentManager;
    private RadioGroup mRadioGroup;

    public boolean isFirstRun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //第一次启动APP判断
        SharedPreferences sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE);
        isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (isFirstRun)
        {
            Intent intent=new Intent(MainActivity.this,LoginMainFrame.class);
            startActivity(intent);
            editor.putBoolean("isFirstRun", false);
            editor.commit();
        }
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment fragment = FragmentFactory.createFragment(checkedId);
                transaction.replace(R.id.frameLayout, fragment);
                transaction.commit();
            }
        });
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}