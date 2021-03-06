package com.group6.babytime.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import android.widget.TextView;

import com.group6.babytime.R;
import com.group6.babytime.more.AboutProductActivity;
import com.group6.babytime.more.BuyActivity;
import com.group6.babytime.more.ChangeUsernameActivity;
import com.group6.babytime.more.ConsumateUserInfoActivity;
import com.group6.babytime.more.FeedBackActivity;
import com.group6.babytime.more.SettingActivity;
import com.group6.babytime.usermanage.LoginMainFrame;

import java.util.ArrayList;
import java.util.HashMap;



/**
 * Created by user on 2016/10/12.
 */
public class MoreFragment extends Fragment implements View.OnClickListener{
    private ListView lv_userinfo;
    private ArrayList<HashMap<String, Object>> listItems;

    public Button btn_feedback;
    public Button btn_settings;
    public Button btn_buy;
    public Button btn_favorites;
    public Button btn_recommend;
    public Button btn_about;

    private String username="2333";// 用户名
    private int user_icon=R.id.user_icon;//用户头像
    private String infoIsComplete="亲你的信息还没有完善哦";//判断用户信息是否完善

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.more_fragment,null);
        lv_userinfo=(ListView)view.findViewById(R.id.lv_userinfo) ;

        btn_feedback=(Button)view.findViewById(R.id.btn_feedback);
        btn_settings=(Button)view.findViewById(R.id.btn_settings);
        btn_buy=(Button)view.findViewById(R.id.btn_buy);
        btn_favorites=(Button)view.findViewById(R.id.btn_favorites);

        btn_recommend=(Button)view.findViewById(R.id.btn_recommend);
        btn_about=(Button)view.findViewById(R.id.btn_about);

        btn_buy.setOnClickListener(this);
        btn_feedback.setOnClickListener(this);
        btn_about.setOnClickListener(this);
        btn_settings.setOnClickListener(this);

        ListViewAdapter mAdapter=new ListViewAdapter(getContext());
        lv_userinfo.setAdapter(mAdapter);

        return view;
    }

    private ArrayList<HashMap<String, Object>> getData() {
        ArrayList<HashMap<String, Object>> listItems = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();


        map.put("username",username);
        map.put("user_icon",user_icon);
        map.put("infoIsComplete",infoIsComplete);
        listItems.add(map);
        return listItems;

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.btn_buy:
                Intent buy_intent=new Intent(this.getContext(), BuyActivity.class);
                startActivity(buy_intent);
                break;
            case R.id.btn_feedback:
                Intent feedback_intent=new Intent(this.getContext(), FeedBackActivity.class);
                startActivity(feedback_intent);
                break;
            case R.id.btn_about:
                Intent about_intent=new Intent(this.getContext(), AboutProductActivity.class);
                startActivity(about_intent);
                break;

            case R.id.btn_settings:
                Intent setting_intent=new Intent(this.getContext(), SettingActivity.class);
                startActivity(setting_intent);
                break;


        }
    }




    private class ListViewAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;

        public ListViewAdapter(Context context) {
            this.mLayoutInflater= LayoutInflater.from(context);
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
                convertView=mLayoutInflater.inflate(R.layout.listview_userinfo,null);
                holder=new ViewHolder();
                holder.title_bar=(TextView)convertView.findViewById(R.id.title_bar) ;
                holder.user_Icon=(ImageView) convertView.findViewById(R.id.user_icon);
                holder.Username=(TextView)convertView.findViewById(R.id.tv_username);
                holder.tv_info=(TextView)convertView.findViewById(R.id.isComplete);

                convertView.setTag(holder);
            }else {
                holder=(ViewHolder)convertView.getTag();
            }

              SharedPreferences sp=getActivity().getSharedPreferences("user",Context.MODE_PRIVATE);
                String username=sp.getString("username","");
                holder.Username.setText(username);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getContext(),ConsumateUserInfoActivity.class);
                    startActivity(intent);

                }
            });
            holder.tv_info.setText("亲，您还没有完善个人信息呢");
            return convertView;
        }

    }




    public final class ViewHolder{
        public ImageView user_Icon;
        public TextView title_bar;
        public TextView Username;
        public TextView tv_info;


    }
}