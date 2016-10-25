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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group6.babytime.R;
import com.group6.babytime.pojo.BabyInfoBean;


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

public class AddBaby2Activity extends AppCompatActivity {

    @InjectView(R.id.iv_return)
    ImageView ivReturn;
    @InjectView(R.id.tv_taitou)
    TextView tvTaitou;

    @InjectView(R.id.view11)
    View view11;
    @InjectView(R.id.tv_headimage11)
    TextView tvHeadimage11;
    @InjectView(R.id.iv_head_image11)
    ImageView ivHeadImage11;
    @InjectView(R.id.iv_edit_head_image11)
    ImageView ivEditHeadImage11;
    @InjectView(R.id.rl_edit_head_image11)
    RelativeLayout rlEditHeadImage11;
    @InjectView(R.id.view12)
    View view12;
    @InjectView(R.id.tv_name22)
    TextView tvName22;
    @InjectView(R.id.tv_real_name22)
    TextView tvRealName22;
    @InjectView(R.id.iv_edit_name22)
    ImageView ivEditName22;
    @InjectView(R.id.rl_edit_name22)
    RelativeLayout rlEditName22;
    @InjectView(R.id.view13)
    View view13;
    @InjectView(R.id.tv_birth33)
    TextView tvBirth33;
    @InjectView(R.id.tv_real_birth33)
    TextView tvRealBirth33;
    @InjectView(R.id.iv_edit_birth33)
    ImageView ivEditBirth33;
    @InjectView(R.id.rl_edit_birth33)
    RelativeLayout rlEditBirth33;
    @InjectView(R.id.view14)
    View view14;
    @InjectView(R.id.rl_guanxi)
    RelativeLayout rlGuanxi;
    @InjectView(R.id.btn_guanxi1)
    RadioButton btnGuanxi1;
    @InjectView(R.id.btn_guanxi2)
    RadioButton btnGuanxi2;
    @InjectView(R.id.btn_guanxi3)
    RadioButton btnGuanxi3;
    @InjectView(R.id.ll_guanxi)
    RadioGroup llGuanxi;
    @InjectView(R.id.btn_addbabytrue)
    Button btnAddbabytrue;
    @InjectView(R.id.rl_addbaby_dibu)
    RelativeLayout rlAddbabyDibu;
    @InjectView(R.id.iv_add)
    TextView ivAdd;

    String babysex;
    String babyname;

    RadioButton tempButton;
    BabyInfoBean bib = new BabyInfoBean();


    EditText etbabyname;
    View layout;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_baby2);
        ButterKnife.inject(this);
        initData();
        initEvent();
    }

    private void initEvent() {
        llGuanxi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case R.id.btn_guanxi1://妈妈
                        bib.setUserRelationship(btnGuanxi1.getText().toString());
                        break;

                    case R.id.btn_guanxi2://爸爸
                        bib.setUserRelationship(btnGuanxi2.getText().toString());
                        break;

                    case R.id.btn_guanxi3://其他
                        LayoutInflater inflater = LayoutInflater.from(AddBaby2Activity.this);
                        final View layout = inflater.inflate(R.layout.edit_text, null);
                        AlertDialog dlg = new AlertDialog.Builder(AddBaby2Activity.this)
                                .setTitle("请输入你与宝宝的关系").setView(layout)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog, int whichButton){
                                        Log.i("AddBaby2Activity", "onClick:+DialogInterface dialog ");
                                        etbabyname =(EditText) layout.findViewById(R.id.editText);
                                        babyname = etbabyname.getText().toString();
                                        btnGuanxi3.setText(babyname);
                                    }
                                })
                                .setNegativeButton("取消", null).show();
                        Log.i("AddBaby2Activity", "onCheckedChanged"+btnGuanxi3);
                        bib.setUserRelationship(btnGuanxi3.getText().toString());
                        break;
                }
            }
        });
        Log.i("AddBaby2Activity", "onCheckedChanged"+bib);
    }

    private void initData() {
        tvTaitou.setText("添加宝宝");

        Intent intent = getIntent();
        babysex = intent.getStringExtra("sexInfo");

    }

    protected void showDatePickDlg() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(AddBaby2Activity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                AddBaby2Activity.this.tvRealBirth33.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    @OnClick({R.id.iv_return, R.id.iv_add, R.id.rl_edit_head_image11, R.id.rl_edit_name22, R.id.rl_edit_birth33})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_return:
                finish();
                break;
            case R.id.iv_add:
                Toast.makeText(AddBaby2Activity.this,"保存发布",Toast.LENGTH_SHORT).show();

                RequestParams requestParams=new RequestParams("http://10.40.5.2:8080/Group6/addbabyservlet");//访问网络的url


                bib.setBabyName(tvRealName22.getText().toString());
                bib.setBabySex(babysex);
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                format.setLenient(false);
                //要转换字符串 str_test
                String d = tvRealBirth33.getText().toString();
                Timestamp ts = null;
                try {
                    ts = new Timestamp(format.parse(d).getTime());
                    System.out.println(ts.toString());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                bib.setBabyBirthday(ts);
                bib.setBabyImage(null);
                bib.setUserId(9);


                Gson gson = new GsonBuilder().create();
                String addbaby =gson.toJson(bib);
                try {
                    requestParams.addQueryStringParameter("addbaby", URLEncoder.encode(addbaby,"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                x.http().get(requestParams, new Callback.CommonCallback<String>() {
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
                finish();
                break;
            case R.id.rl_edit_head_image11:
                break;
            case R.id.rl_edit_name22:
                LayoutInflater inflater = LayoutInflater.from(this);
                final View layout = inflater.inflate(R.layout.edit_text, null);
                AlertDialog dlg = new AlertDialog.Builder(this)
                        .setTitle("请输入宝宝的昵称").setView(layout)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int whichButton){
                                Log.i("AddBaby2Activity", "onClick:+DialogInterface dialog ");
                                etbabyname =(EditText) layout.findViewById(R.id.editText);
                                babyname = etbabyname.getText().toString();
                                tvRealName22.setText(babyname);
                            }
                        })
                        .setNegativeButton("取消", null).show();
                break;
            case R.id.rl_edit_birth33:
                rlEditBirth33.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch (View v, MotionEvent event){
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            showDatePickDlg();
                            return true;
                        }
                        return false;
                    }
                });
                rlEditBirth33.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
