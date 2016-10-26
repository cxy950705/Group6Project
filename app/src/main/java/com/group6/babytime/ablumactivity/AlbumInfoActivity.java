package com.group6.babytime.ablumactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.group6.babytime.R;
import com.group6.babytime.entity.Album;
import com.group6.babytime.entity.PhotosUrls;
import com.group6.babytime.utils.CommontAdapter;
import com.group6.babytime.utils.NetUtil;
import com.group6.babytime.utils.ViewHolder;
import com.group6.babytime.utils.XutilImgUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AlbumInfoActivity extends AppCompatActivity  {

    private static final String TAG = "AlbumInfoActivity";
    @InjectView(R.id.lv_head)
    ListView lvHead;
    @InjectView(R.id.iv_album_back)
    ImageView ivAlbumBack;
    @InjectView(R.id.iv_album_dian)
    ImageView ivAlbumDian;
    @InjectView(R.id.tv_albumname)
    TextView tvAlbumname;
    @InjectView(R.id.tv_albummiaoshu)
    TextView tvAlbummiaoshu;
    @InjectView(R.id.tv_up_photo_btn)
    Button tvUpPhotoBtn;
    @InjectView(R.id.ll_up)
    RelativeLayout llUp;
    @InjectView(R.id.albumpage_second)
    RelativeLayout albumpageSecond;

    public static final int UPLOAD_PHOTO = 21;

    Integer albumId;
    Album album;
    String file;//上传照片的文件

    private CommontAdapter<PhotosUrls> gridview_adapter2;

    public  List<PhotosUrls> photosurls2=new ArrayList<>();//存放照片url
    private GridView gv_album_list_gridview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//出去标题

        setContentView(R.layout.activity_album_info);
        ButterKnife.inject(this);
        initview();
        initData();

    }

    private void initview() {

    }


    //初始化页面
    public void initData() {
        //获取传过来的albumInfo
        Intent intent = getIntent();
        Album album = intent.getParcelableExtra("albumInfo");
         albumId=album.getAlbumId();

        System.out.println("相册里面的："+albumId);

        if (album != null) {
            //获取相册名称，在上面找到tv_albumname对应的字段
            tvAlbumname.setText(album.getAlbumName());
            //获取相册的描述
            tvAlbummiaoshu.setText(album.getAlbum_description());
        }


        gv_album_list_gridview = ((GridView) findViewById(R.id.gv_album_list_gridview));

        //获取网络数据
        String url = NetUtil.url + "QueryPhotosUrlsServlet";//还没写

        RequestParams requestParams = new RequestParams(url);

        //设置传给服务器的参数
        requestParams.addQueryStringParameter("albumId",albumId+"");
        Log.i("albumInfoActivity", "onSuccess: " + "=======xxxx=======");
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //将json转换成List<PhotosUrls>
                Gson gson=new Gson();
                Type type = new TypeToken<List<PhotosUrls>>() {
                }.getType();
                Log.i("albumInfoActivity", "onSuccess: " + "=============="+result);
                List<PhotosUrls> newphotosurls2=new ArrayList<PhotosUrls>();
                newphotosurls2=gson.fromJson(result,type);

                //清空原来的数据
                photosurls2.clear();
                photosurls2.addAll(newphotosurls2);
                //gridview设置adapter
                if(gridview_adapter2==null){
                    gridview_adapter2=new CommontAdapter<PhotosUrls>(AlbumInfoActivity.this,photosurls2,R.layout.iv_liebiao_gridview_image) {
                        private ImageView iv_gridview1_image;
                        private GridView iv_gridview1_imag;

                        @Override
                        public void convert(ViewHolder viewHolder, PhotosUrls photosUrls, int position) {
                            //取出控件，赋值
                            //iv_gridview1_imag = ((GridView) viewHolder.getViewById(R.id.iv_gridview1_image));
                            iv_gridview1_image = ((ImageView) viewHolder.getViewById(R.id.iv_gridview1_image));
                            for(int j=0;j<photosurls2.size();j++){
                                String strUrls1=String.valueOf(photosurls2.get(j));
                                System.out.println("******************"+strUrls1);
                                x.image().bind(iv_gridview1_image,NetUtil.url+photosurls2.get(j).getLiebiaoUrl());
                            }
                        }
                    };

                    gv_album_list_gridview.setAdapter(gridview_adapter2);
                }else{
                    //有数据源直接更新
                    gridview_adapter2.notifyDataSetChanged();
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







    @OnClick(R.id.tv_up_photo_btn)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_up_photo_btn:
                Intent intent1=new Intent(AlbumInfoActivity.this,Album_Upload_Activity.class);
                Log.i("上传照片", "tv_up_photo_btn:============== "+albumId);
                intent1.putExtra("albumId",String.valueOf(albumId));
                startActivityForResult(intent1,UPLOAD_PHOTO);


                break;
        }
    }

    //上传照片抽出的方法
   /* private void sendImg() {
        String url = NetUtil.url + "upload";//upload是你要访问的servlet
        RequestParams params = new RequestParams(url);

        //params设置要传给服务器的参数
        params.addBodyParameter("file", file);
        params.addBodyParameter("fileName", "fileName");

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

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
        }*/




}
