package com.group6.babytime.ablumactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.group6.babytime.R;
import com.group6.babytime.entity.Album;
import com.group6.babytime.utils.NetUtil;

import org.xutils.http.RequestParams;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.iwf.photopicker.widget.MultiPickResultView;

public class Album_Upload_Activity extends Activity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.rl_top)
    RelativeLayout rlTop;
    @InjectView(R.id.add_content)
    EditText addContent;
   /* @InjectView(R.id.recycler_view)
    me.iwf.photopicker.widget.MultiPickResultView recyclerView;*/
    @InjectView(R.id.btn_publish)
    Button btnPublish;
    @InjectView(R.id.photo_publish)
    RelativeLayout photoPublish;



    String albumid;
    String photoCentent="";
    //存放上传的照片
    List list_upload_photo=new ArrayList();
    private MultiPickResultView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album__upload_);

        ButterKnife.inject(this);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        recycler_view = ((MultiPickResultView) findViewById(R.id.recycler_view));
        recycler_view.init(this,MultiPickResultView.ACTION_SELECT,null);
    }
    private void initData() {

        Intent intent2=getIntent();
        albumid=intent2.getStringExtra("albumId");
        Log.i("Album_Upload_Activity", "onSuccess: ===="+albumid);


    }
    private void initEvent() {
    }
    //图片选择器，下面这行onActivityResult代码回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        recycler_view.onActivityResult(requestCode,resultCode,data);

        list_upload_photo=recycler_view.getPhotos();

        for(int i=0;i<list_upload_photo.size();i++){
            System.out.println(list_upload_photo.get(i));

        }

    }

    @OnClick({R.id.iv_back, R.id.button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.button:
                //选完照片，后上传
                Toast.makeText(Album_Upload_Activity.this,"上传发布",Toast.LENGTH_SHORT).show();
                RequestParams rs=new RequestParams(NetUtil.url+"");//还没写
                Album album=new Album();
                Timestamp d = new Timestamp(System.currentTimeMillis());
                album.setAlbumCreatime(d);
                photoCentent=addContent.getText().toString().trim();
                album.setAlbum_description(photoCentent);




                break;
            case R.id.add_content:
                //上传照片时添加描述信息
                photoCentent=addContent.getText().toString().trim();
                Toast.makeText(Album_Upload_Activity.this,"内容："+photoCentent,Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
