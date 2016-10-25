package com.group6.babytime.ablumactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.group6.babytime.R;
import com.group6.babytime.entity.Album;
import com.group6.babytime.utils.NetUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

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


    Album album;
    String file;//上传照片的文件

   /* private List<String> mList = null;
    //图片缓存用来保存GridView中每个Item的图片，以便释放
    public static Map<String, Bitmap> gridviewBitmapCaches = new HashMap<String, Bitmap>();
    private GridView album_list_gridview;*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_info);
        ButterKnife.inject(this);
        initview();
        initData();

    }

    private void initview() {
        //album_list_gridview = ((GridView) findViewById(R.id.gv_album_list_gridview));
    }


    //初始化页面
    public void initData() {
        //获取传过来的albumInfo
        Intent intent = getIntent();
        Album album = intent.getParcelableExtra("albumInfo");

        if (album != null) {
            //获取相册名称，在上面找到tv_albumname对应的字段
            tvAlbumname.setText(album.getAlbumName());
            //获取相册的描述
            tvAlbummiaoshu.setText(album.getAlbum_description());


        }

    }







    @OnClick(R.id.tv_up_photo_btn)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_up_photo_btn:
                sendImg();//抽出一个上传照片的方法
                break;
        }
    }

    //上传照片抽出的方法
    private void sendImg() {
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


    }

}
