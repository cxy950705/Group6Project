package com.group6.babytime.home;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group6.babytime.R;
import com.group6.babytime.pojo.Baby;

import org.w3c.dom.Text;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ChangeBabyActivity extends AppCompatActivity {

    @InjectView(R.id.iv_return)
    ImageView ivReturn;
    @InjectView(R.id.tv_taitou)
    TextView tvTaitou;

    @InjectView(R.id.view)
    View view;
    @InjectView(R.id.tv_headimage)
    TextView tvHeadimage;
    @InjectView(R.id.iv_head_image)
    ImageView ivHeadImage;
    @InjectView(R.id.iv_edit_head_image)
    ImageView ivEditHeadImage;
    @InjectView(R.id.rl_edit_head_image)
    RelativeLayout rlEditHeadImage;
    @InjectView(R.id.view2)
    View view2;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_real_name)
    TextView tvRealName;
    @InjectView(R.id.iv_edit_name)
    ImageView ivEditName;
    @InjectView(R.id.rl_edit_name)
    RelativeLayout rlEditName;
    @InjectView(R.id.view3)
    View view3;
    @InjectView(R.id.tv_sex)
    TextView tvSex;
    @InjectView(R.id.tv_real_sex)
    TextView tvRealSex;
    @InjectView(R.id.iv_edit_sex)
    ImageView ivEditSex;
    @InjectView(R.id.rl_edit_sex)
    RelativeLayout rlEditSex;
    @InjectView(R.id.view4)
    View view4;
    @InjectView(R.id.tv_birth)
    TextView tvBirth;
    @InjectView(R.id.tv_real_birth)
    TextView tvRealBirth;
    @InjectView(R.id.iv_edit_birth)
    ImageView ivEditBirth;
    @InjectView(R.id.rl_edit_birth)
    RelativeLayout rlEditBirth;
    @InjectView(R.id.iv_add)
    TextView ivAdd;

    String babysex;
    String babyname;
    EditText etbabyname;

    String babyid;
    Integer babyid1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_baby);
        ButterKnife.inject(this);
        initData();


    }

    private void initData() {

        tvTaitou.setText("修改宝宝的信息");
        Intent intent = getIntent();
        babyid = intent.getStringExtra("babyId");
        babyid1 = Integer.parseInt(babyid);
        Log.i("ChangeBabyActivity", "initData"+babyid1);
    }






    protected void showDatePickDlg() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(ChangeBabyActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ChangeBabyActivity.this.tvRealBirth.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    @OnClick({R.id.iv_return, R.id.iv_add, R.id.rl_edit_head_image, R.id.rl_edit_name, R.id.rl_edit_sex, R.id.rl_edit_birth})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_return:
                finish();
                break;
            case R.id.iv_add:
                Toast.makeText(ChangeBabyActivity.this,"保存修改",Toast.LENGTH_SHORT).show();
                RequestParams requestParams=new RequestParams("http://10.40.5.2:8080/Group6/changebabyservlet");//访问网络的url
                Baby baby =new Baby();
                baby.setBabyId(babyid1);
                baby.setBabyName(tvRealName.getText().toString());
                baby.setBabySex(tvRealSex.getText().toString());
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                format.setLenient(false);
                //要转换字符串 str_test
                String d = tvRealBirth.getText().toString();
                Timestamp ts = null;
                try {
                    ts = new Timestamp(format.parse(d).getTime());
                    System.out.println(ts.toString());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                baby.setBabyBirthday(ts);
                baby.setBabyImage(null);



                Gson gson = new GsonBuilder().create();
                String changebaby =gson.toJson(baby);
                try {
                    requestParams.addQueryStringParameter("changebaby", URLEncoder.encode(changebaby,"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.i("ChangeBabyActivity", "onSuccess: ");

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
                finish();
                break;
            case R.id.rl_edit_head_image:
                break;
            case R.id.rl_edit_name:
                LayoutInflater inflater = LayoutInflater.from(this);
                final View layout = inflater.inflate(R.layout.edit_text, null);
                AlertDialog dlg = new AlertDialog.Builder(this)
                        .setTitle("请输入修改后宝宝的昵称").setView(layout)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int whichButton){
                                Log.i("ChangeBabyActivity", "onClick:+DialogInterface dialog ");
                                etbabyname =(EditText) layout.findViewById(R.id.editText);
                                babyname = etbabyname.getText().toString();
                                tvRealName.setText(babyname);
                            }
                        })
                        .setNegativeButton("取消", null).show();
                break;
            case R.id.rl_edit_sex:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("请选择宝宝性别");
                final String[] sex = {"男", "女"};
                builder.setSingleChoiceItems(sex, 0, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        tvRealSex.setText(sex[which]);
                    }
                });
                builder.setPositiveButton("确定", null);
                builder.setNegativeButton("取消", null);
                builder.show();
                break;
            case R.id.rl_edit_birth:
                rlEditBirth.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch (View v, MotionEvent event){
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            showDatePickDlg();
                            return true;
                        }
                        return false;
                    }
                });
                rlEditBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange (View v,boolean hasFocus){
                        if (hasFocus) {
                            showDatePickDlg();
                        }
                    }
                });
                break;
        }
    }
}
