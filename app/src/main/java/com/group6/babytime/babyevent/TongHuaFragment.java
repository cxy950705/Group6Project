package com.group6.babytime.babyevent;

import android.content.Context;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.group6.babytime.R;
import com.group6.babytime.babyevent.xUtilsImageUtils;
import com.group6.babytime.pojo.Story;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;



public class TongHuaFragment extends Fragment {


    private GridView gridview;


    List<Story> storyList = new ArrayList<>();

    private BaseAdapter baseAdapter;
    /*@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ctivity_tab_tonghuastory);

        gridview = ((GridView) findViewById(R.id.gridview));
        initData();
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View tonghuaView = inflater.inflate(R.layout.ctivity_tab_tonghuastory, container,false);
        gridview = ((GridView) tonghuaView.findViewById(R.id.gridview));
        initData();
        return tonghuaView;
    }

    private void initData() {
        RequestParams params = new RequestParams("http://10.40.5.43:8080/babytime/querystoryservlet");

        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();

                Type type = new TypeToken<List<Story>>(){}.getType();

                storyList = gson.fromJson(result,type);

                Log.i("111", "onSuccess: 数据传输："+result);

                baseAdapter = new BaseAdapter() {

                    private TextView tv_storyname;
                    private ImageView iv_storycover;

                    @Override
                    public int getCount() {
                        return storyList.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return position;
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = View.inflate(getActivity(), R.layout.storycover_item,null);
                        iv_storycover = ((ImageView)view.findViewById(R.id.iv_storycover));
                        tv_storyname = ((TextView) view.findViewById(R.id.tv_storyname));
                        Story story = storyList.get(position);
                        Log.i("222", "getView: story对象"+story);
                        xUtilsImageUtils.display(iv_storycover,"http://10.40.5.43:8080/babytime/upload/"+story.getStory_cover()+"",false);
                        tv_storyname.setText(story.getStory_name());
                        //iv_storycover.setImageResource();
                        return view;
                    }


                };
                gridview.setAdapter(baseAdapter);

            }


            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }



}