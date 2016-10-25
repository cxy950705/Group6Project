package com.group6.babytime.fragment;

import android.os.Bundle;
import android.provider.Contacts;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.group6.babytime.R;
import com.group6.babytime.entity.Photo;
import com.group6.babytime.entity.QueryPhotoItemBean;

import com.group6.babytime.utils.MyGridView;
import com.group6.babytime.utils.NetUtil;
import com.group6.babytime.widget.NoScrollGridView;

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
public class FragmentPage2 extends Fragment {

    //相册名称
    String photoContent;
    int orderFlag = 0;
    int pageNo = 1;
    int pageSize = 5;

   // GridViewAdapter photosAdapter;
    //List<QueryPhotoItemBean> queryPhotoItemBeens=new ArrayList<>();
    List<Photo> photos= new ArrayList<>();//存放照片信息
    public static List<String> photosurls=new ArrayList<>();
    @InjectView(R.id.ListVIew2)
    ListView ListVIew2;
    private NoScrollGridView gridview;
    private GridView gv_gridview;
private BaseAdapter gv_adapter;
    private BaseAdapter lv_adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lv_zuijin_xiangqing_zhu, null);
        ButterKnife.inject(this, view);

        initView();
        initEvent();

        gv_gridview = ((GridView) view.findViewById(R.id.gridview));
        gv_adapter=new BaseAdapter() {
            private ImageView iv_image;

            @Override
            public int getCount() {
                return photosurls.size();
            }

            @Override
            public Object getItem(int i) {
                return photosurls.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                view=View.inflate(getActivity(),R.layout.lv_zuijin_xiangqing,null);
                iv_image = ((ImageView) view.findViewById(R.id.iv_image));

                /*for(int j=0;j<photo_url_list.size();j++) {*/
                    String strUrl = photosurls.get(i);
                    System.out.println("******************"+strUrl);
                    x.image().bind(iv_image,NetUtil.url+strUrl);

                //}
                return view;
            }
        };

        lv_adapter=new BaseAdapter() {
            private MyGridView gridview1;
            private TextView tv_phomiaoshu;
            private TextView tv_time;

            @Override
            public int getCount() {
                return photos.size();
            }

            @Override
            public Object getItem(int i) {
                return photos.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                view=View.inflate(getActivity(),R.layout.photo_list_item_zuijin,null);
                tv_time = ((TextView) view.findViewById(R.id.tv_time));
                tv_phomiaoshu = ((TextView) view.findViewById(R.id.tv_phomiaoshu));
                gridview1 = ((MyGridView) view.findViewById(R.id.gridview));

                int month =photos.get(i).getPhotoUploadDate().getMonth()+1;
                int day=photos.get(i).getPhotoUploadDate().getDate();

                tv_time.setText(String.valueOf(month)+"月"+String.valueOf(day)+"日");
                   //tv_time.setText(photos.get(i).getPhotoUploadDate().toString());
                tv_phomiaoshu.setText(photos.get(i).getContent());
                gridview1.setAdapter(gv_adapter);
                gv_adapter.notifyDataSetChanged();
                return view;
            }
        };

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDate();


    }

    private void initDate() {


        getData();
    }

    private void getData() {
        //xutils获取网络数据
        //访问网络的url
        Log.e("GGGGGGGGG","访问服务器");
        String url = NetUtil.url + "QueryphotoServlet";

        RequestParams requestParams = new RequestParams(url);
        //设置传给服务器的参数

        requestParams.addQueryStringParameter("photoContent", photoContent);
        requestParams.addQueryStringParameter("orderFlag", orderFlag + "");//排序标记
        requestParams.addQueryStringParameter("pageNo", pageNo + "");
        requestParams.addQueryStringParameter("pageSize", pageSize + "");
        Log.i("photoFragment", "onSuccess: " + "==============");

        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                //json-->List<Photo>
                Log.e("GGGGGGGGG","拿到数据");
                Gson gson = new Gson();
                Type type = new TypeToken<List<Photo>>() {
                }.getType();


                //List<Photo> newItems = new ArrayList<Photo>();
                Log.e("GGGGGGGGG","解析数据");
                photos=gson.fromJson(result,type);
                for(int i=0;i<photos.size();i++){
                    photosurls.add(photos.get(i).getPhotoUrl());
                }
                ListVIew2.setAdapter(lv_adapter);
                lv_adapter.notifyDataSetChanged();
                Log.e("GGGGGGGGG","newItems:"+photos.toString());
                Log.e("GGGGGGGGG","解析数据成功");
                //photos.clear();
               // photos.addAll(photos);
                Log.e("GGGGGGGGG","photos:"+photos.toString());





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

    private void initView() {
    }

    private void initEvent() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
