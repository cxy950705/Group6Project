package com.group6.babytime.more;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.group6.babytime.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    //ActionBar
    private TextView setting_title;
    private TextView back_to_menu;
    private TextView tv_empty;

    //控件
    private Button btn_changephone;
    private Button btn_changepassword;
    private Button btn_clearcache;
    private Button btn_logout;
    private ListView lv_enableWIFI;

    private TextView tv_cellular_data;
    private Switch switch_cellular;

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
            View actionbarLayout2 = LayoutInflater.from(this).inflate(R.layout.setting_title, null);
            actionBar.setCustomView(actionbarLayout2);
            setting_title = (TextView)actionbarLayout2.findViewById(R.id.about_title);
            back_to_menu = ((TextView) actionbarLayout2.findViewById(R.id.backtomenu));
            tv_empty = (TextView)actionbarLayout2.findViewById(R.id.tv_empty);

        }
        setContentView(R.layout.activity_setting);
        back_to_menu.setOnClickListener(this);

        initView();
    };

    private void initView(){
        btn_changephone = ((Button) findViewById(R.id.btn_changephone));
        btn_changepassword = ((Button) findViewById(R.id.btn_changepassword));
        lv_enableWIFI = ((ListView) findViewById(R.id.lv_enableWIFI));
        btn_clearcache = ((Button) findViewById(R.id.clear_cache));
        btn_logout = ((Button) findViewById(R.id.btn_logout));


        btn_changepassword.setOnClickListener(this);

        ListViewAdapter mAdapter=new ListViewAdapter(this.getApplicationContext());
        lv_enableWIFI.setAdapter(mAdapter);

    }


    private ArrayList<HashMap<String, Object>> getData() {
        ArrayList<HashMap<String, Object>> listItems = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();


        map.put("tv_cellular_data",tv_cellular_data);
        map.put("switch_cellular",switch_cellular);
        listItems.add(map);
        return listItems;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backtomenu:
                finish();
            case R.id.btn_changepassword:
                Intent intent1=new Intent(this.getApplicationContext(),ChangePasswordActivity.class);
                startActivity(intent1);
                break;
        }
    }

    private class ListViewAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;

        public ListViewAdapter(Context context) {
            this.mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return getData().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.listview_enable_wifi, null);
                holder = new ViewHolder();
                holder.tv_cellular_data = (TextView) convertView.findViewById(R.id.tv_cellular_data);
                holder.switch_cellular = (Switch) convertView.findViewById(R.id.switch_cellular);


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }

    }
    public final class ViewHolder{
        public TextView tv_cellular_data;
        public Switch switch_cellular;

    }
}
