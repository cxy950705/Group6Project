package com.group6.babytime.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by xzj on 2016/10/10.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    Context context;
    List<T> lists;
    int layoutId;
    public CommonAdapter(Context context, List<T> lists,int layoutId){

        this.context=context;
        this.lists=lists;
        this.layoutId=layoutId;

    }

    @Override
    public int getCount() {
        return (lists!=null)?lists.size():0;//这样写就不用考虑数据源是否为null
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
        ViewHolder viewHolder=ViewHolder.get(context,layoutId,convertView,parent);//第一步都是获取viewHolder
        convert(viewHolder,lists.get(position),position);//在这边调一下方法
        return viewHolder.getConvertView();//最后也都一样
    }

    //取出控件，赋值 中间用什么样的控件，赋什么样的值是不一样的，所以弄一个抽象方法
    public abstract void  convert(ViewHolder viewHolder,T t,int position);
}
