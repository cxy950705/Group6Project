package com.group6.babytime.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.group6.babytime.R;
import com.group6.babytime.ablumactivity.AlbumInfoActivity;
import com.group6.babytime.entity.Album;
import com.group6.babytime.utils.CommontAdapter;
import com.group6.babytime.utils.NetUtil;
import com.group6.babytime.utils.ViewHolder;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/10/21.
 */
public class FragmentPage1 extends Fragment {

    //相册名称
    String albumName;
    int orderFlag = 0;
    int pageNo = 1;
    int pageSize = 5;
    CommontAdapter<Album> albumsAdapter;

    List<Album> albums = new ArrayList<Album>();//存放的相册信息
    @InjectView(R.id.lv_liebiao_xianshi)
    ListView lvLiebiaoXianshi;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lv_liebiao_xiangqing, null);
        ButterKnife.inject(this, view);
        initView();
        initData();
        initEvent();
        return view;

    }

    public void initView() {


    }

    public void initData() {
        getData();//获取网络数据，显示在listview上

    }

    public void getData() {
        //xutils获取网络数据
        //访问网络的url
        String url = NetUtil.url + "QueryAlbumServlet";

        RequestParams requestParams = new RequestParams(url);


        //设置传给服务器的参数

        requestParams.addQueryStringParameter("albumName", albumName);
        requestParams.addQueryStringParameter("orderFlag", orderFlag + "");//排序标记
        requestParams.addQueryStringParameter("pageNo", pageNo + "");
        requestParams.addQueryStringParameter("pageSize", pageSize + "");
        Log.i("albumFragment", "onSuccess: " + "==============");

        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {


                //json转换成List<Album>
                Gson gson = new Gson();
                Type type = new TypeToken<List<Album>>() {
                }.getType();

                List<Album> newalbums = new ArrayList<Album>();
                newalbums = gson.fromJson(result, type);//解析成List<Album
                //products=gson.fromJson(result,type);//解析成List<Album>

                //清空原来的数据
                albums.clear();
                albums.addAll(newalbums);

                //设置ListView的adapter
                if (albumsAdapter == null) {
                    albumsAdapter = new CommontAdapter<Album>(getActivity(), albums, R.layout.photo_list_item) {
                        @Override
                        public void convert(ViewHolder viewHolder, Album album, int position) {
                            //取出控件，赋值
                            TextView tv = viewHolder.getViewById(R.id.photo_item_name_tv1);
                            tv.setText(album.getAlbumName());
                            TextView tvNumber = viewHolder.getViewById(R.id.photo_item_number_tv2);
                            tvNumber.setText(album.getPhotoNumber() + "");
                            //其他控件的赋值


                        }
                    };
                    lvLiebiaoXianshi.setAdapter(albumsAdapter);

                } else {
                    //有数据源更新的话，直接更新界面
                    albumsAdapter.notifyDataSetChanged();
                }


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
        });
    }

    public void initEvent() {
        //lvLiebiaoXianshi的item的点击事件
        lvLiebiaoXianshi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //跳转到相册的详情页面
                Intent intent = new Intent(getActivity(), AlbumInfoActivity.class);
                //获取相册信息
                intent.putExtra("albumInfo", albums.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
