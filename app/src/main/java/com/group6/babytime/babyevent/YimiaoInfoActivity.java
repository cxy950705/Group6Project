package com.group6.babytime.babyevent;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.group6.babytime.R;
import com.group6.babytime.pojo.ListActivityBean;
import com.group6.babytime.titlebar.TitleBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class YimiaoInfoActivity extends AppCompatActivity {
    @InjectView(R.id.tv_zhuangtai)
    TextView tvZhuangtai;
    @InjectView(R.id.Spinner_ztai)
    Spinner SpinnerZtai;
    @InjectView(R.id.zhuangtai)
    LinearLayout zhuangtai;
    @InjectView(R.id.tv_riqi)
    TextView tvRiqi;
    @InjectView(R.id.edit_riqi)
    EditText editRiqi;
    @InjectView(R.id.ll_riqi)
    LinearLayout llRiqi;
    @InjectView(R.id.tv_jieshao)
    TextView tvJieshao;
    @InjectView(R.id.llyimiao)
    LinearLayout llyimiao;
    private EditText mEditText;
    private List<String> list = new ArrayList<String>();
    private TextView myTextView;
    private Spinner mySpinner;
    private ArrayAdapter<String> adapter;
    private TitleBar titlebar;

    ListActivityBean.YimiaoInfo yimaio;//疫苗信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yimiao_info);
        ButterKnife.inject(this);

        titlebar = ((TitleBar) findViewById(R.id.titlebar));

        titlebar.setTitle("疫苗详情");
        titlebar.setImgLeftRes(R.drawable.arro_left);
        titlebar.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(YimiaoInfoActivity.this, "你点击了疫苗详情", Toast.LENGTH_SHORT).show();
            }
        });


        //第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项
        list.add("未接种");
        list.add("已接种");
        myTextView = (TextView) findViewById(R.id.tv_zhuangtai);
        mySpinner = (Spinner) findViewById(R.id.Spinner_ztai);
        mEditText = (EditText) findViewById(R.id.edit_riqi);

        //第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mySpinner.setAdapter(adapter);
        //第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                /* 将所选mySpinner 的值带入myTextView 中*//*
                myTextView.setText("您选择的是："+ adapter.getItem(arg2));*/
                /* 将mySpinner 显示*/
                arg0.setVisibility(View.VISIBLE);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                myTextView.setText("NONE");
                arg0.setVisibility(View.VISIBLE);
            }
        });
 /*       *//*下拉菜单弹出的内容选项触屏事件处理*//*
        mySpinner.setOnTouchListener(new Spinner.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                *//**
         *
         *//*
                return false;
            }
        });
        *//*下拉菜单弹出的内容选项焦点改变事件处理*//*
        mySpinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

            }
        });
*/
        mEditText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlg();
                    return true;
                }
                return false;
            }
        });
        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickDlg();
                }
            }
        });
    }



    protected void showDatePickDlg() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(YimiaoInfoActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                YimiaoInfoActivity.this.mEditText.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }
}

