package com.group6.babytime.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/10.
 */
public abstract class CommontAdapter<T> extends BaseAdapter {
    Context context;
    List<T> lists;
    int layoutId;
    public CommontAdapter(Context context, List<T> lists, int layoutId){//要显示集合的条数所以传一个List<T>

        this.context=context;
        this.lists=lists;
        this.layoutId=layoutId;

    }

    @Override
    public int getCount() {
        return (lists!=null)?lists.size():0;//getcount返回的是list条数，判断一下数据源是否为null
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);//每个item的数据源
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //找到控件，赋值
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=ViewHolder.get(context,layoutId,convertView,parent);
        convert(viewHolder,lists.get(position),position);
        return viewHolder.getConvertView();
    }
    //取出控件，赋值
    public abstract void  convert(ViewHolder viewHolder,T t,int position);
}
