package com.group6.babytime.titlebar;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.group6.babytime.R;


/**
 * Created by 博博 on 2016/10/17.
 */

public class TitleBar extends RelativeLayout {

    private TextView title;
    private ImageView imgLeft;

    public TitleBar(Context context) {
        super(context);
    }

    //配置文件
    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_title_bar, this, true);

        title = ((TextView) view.findViewById(R.id.tv_title));
        imgLeft = ((ImageView) view.findViewById(R.id.iv_left));

    }
    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTitle(String title_str){
        title.setText(title_str);
    }
    public void setImgLeftRes(@DrawableRes int resId){

        imgLeft.setImageResource(resId);

    }
    public void setImgLeftOnClickListener(@Nullable OnClickListener l){
        imgLeft.setOnClickListener(l);
    }
}

