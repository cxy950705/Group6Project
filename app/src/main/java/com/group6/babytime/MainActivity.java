package com.group6.babytime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.group6.babytime.fragment.BaseActivity;
import com.group6.babytime.fragment.FragmentFactory;
import com.group6.babytime.fragment.HomeFragment;
import com.group6.babytime.pojo.Baby;
import com.group6.babytime.usermanage.LoginMainFrame;

public class MainActivity extends BaseActivity {
    private FragmentManager fragmentManager;
    private RadioGroup mRadioGroup;
    HomeFragment homeFragment;

    public boolean isFirstRun;
    private long exitTime = 0;
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
            editor.apply();
        }
        setContentView(R.layout.activity_main);

        //检查网络是否连通
        if (!isNetWorkAvailable(MainActivity.this))
        {
            Toast.makeText(getApplicationContext(), "当前没有可用网络！", Toast.LENGTH_LONG).show();
        }

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

        getBabyId();
    }

    private void getBabyId() {
        homeFragment = new HomeFragment();
        Intent intent= getIntent();
        Baby babys;

        babys= intent.getParcelableExtra("babyInfo");
        Log.i("1111","getBabyId---------"+babys);

        Bundle bundle = new Bundle();

        bundle.putParcelable("babyInfo",babys);
        Log.i("1111","getBabyId---------"+bundle);
        homeFragment.setArguments(bundle);

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.frameLayout, homeFragment);
        transaction.commit();
    }




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


    public boolean isNetWorkAvailable(Activity activity){
        Context context=activity.getApplicationContext();
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager==null){
            return false;
        }

        else{
            NetworkInfo networkInfo[]=connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0)
            {
                for (NetworkInfo aNetworkInfo : networkInfo) {
                    // 判断当前网络状态是否为连接状态
                    if (aNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}