package com.group6.babytime.more;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
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


import java.io.ByteArrayOutputStream;
import java.io.File;
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

    //拍照
    public final String itemList[]={"拍照","本地相册"};
    private static final String IMAGE_UNSPECIFIED = "image/*";
    private static final int ALBUM_REQUEST_CODE = 1;
    private static final int CAMERA_REQUEST_CODE = 2;
    private static final int CROP_REQUEST_CODE = 4;

    private Uri imageUri;

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

            holder.user_icon_img.setOnClickListener(new ImageView.OnClickListener(){

                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(ConsumateUserInfoActivity.this);
                    builder.setTitle("设置头像");
                    builder.setItems(itemList, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case 0:
                                    Intent take_photo=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    take_photo.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new File
                                            (Environment.getExternalStorageDirectory(),"temp.jpg")));

                                    startActivityForResult(take_photo,CAMERA_REQUEST_CODE);
                                    break;
                                case 1:
                                    Intent album_pick=new Intent(Intent.ACTION_PICK,null);
                                    album_pick.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,IMAGE_UNSPECIFIED);
                                    startActivityForResult(album_pick,ALBUM_REQUEST_CODE);
                                    break;

                            }
                        }
                    }).show();
                }

            });
            return convertView;
        }

    }
        public final class ViewHolder{
            public ImageView user_icon_img;
            public TextView tv_icon;

        }

    //照片裁剪
    private void startCrop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");//调用Android系统自带的一个图片剪裁页面,
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");//进行修剪
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 60);
        intent.putExtra("outputY", 60);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        ViewHolder holder=new ViewHolder();
        switch (requestCode){
            case ALBUM_REQUEST_CODE:
                if(data==null){
                    return;
                }
                startCrop(data.getData());
                break;
            case CAMERA_REQUEST_CODE:
                File picture=new File(Environment.getExternalStorageDirectory()+"/temp.jpg");
                startCrop(Uri.fromFile(picture));
                break;
            case CROP_REQUEST_CODE:
                if(data==null){
                    return;
                }
                Bundle extras=data.getExtras();
                if (extras != null) {
                    Bitmap photo=extras.getParcelable("data");
                    ByteArrayOutputStream stream=new ByteArrayOutputStream();
                    if (photo != null) {
                        photo.compress(Bitmap.CompressFormat.JPEG,75,stream);
                    }
                    holder.user_icon_img.setImageBitmap(photo);

                }
                break;
            default:
                break;
        }
    }
}
