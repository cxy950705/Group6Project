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
import android.widget.TextView;

import com.group6.babytime.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ConsumateUserInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView lv_usericon;
    private TextView title;
    private TextView back_to_menu;
    private TextView tv_empty;

    private TextView tv_icon;
    private ImageView user_icon_img;
    private Button btn_changeNickName;

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
            title = (TextView)actionbarLayout2.findViewById(R.id.userinfo_title);
            back_to_menu = ((TextView) actionbarLayout2.findViewById(R.id.backtomenu));
            tv_empty = (TextView)actionbarLayout2.findViewById(R.id.tv_empty);

        }


        setContentView(R.layout.activity_consumate_user_info);
        back_to_menu.setOnClickListener(this);
        initView();
    }

    private void initView(){
        lv_usericon=(ListView)findViewById(R.id.lv_usericon) ;
        btn_changeNickName = ((Button) findViewById(R.id.btn_change_nickname));
        btn_changeNickName.setOnClickListener(this);

        ListViewAdapter mAdapter=new ListViewAdapter(this.getApplicationContext());
        lv_usericon.setAdapter(mAdapter);
    }

    private ArrayList<HashMap<String, Object>> getData() {
        ArrayList<HashMap<String, Object>> listItems = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();


        map.put("tv_icon",tv_icon);
        map.put("user_icon",user_icon_img);
        listItems.add(map);
        return listItems;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backtomenu:
                finish();
                break;
            case R.id.btn_change_nickname:
                Intent intent1=new Intent(this.getApplicationContext(),ChangeUsernameActivity.class);
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
                convertView = mLayoutInflater.inflate(R.layout.listview_usericon, null);
                holder = new ViewHolder();
                holder.user_icon_img = (ImageView) convertView.findViewById(R.id.user_icon_img);
                holder.tv_icon = (TextView) convertView.findViewById(R.id.tv_icon);


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }

    }
        public final class ViewHolder{
            public ImageView user_icon_img;
            public TextView tv_icon;

        }
}
