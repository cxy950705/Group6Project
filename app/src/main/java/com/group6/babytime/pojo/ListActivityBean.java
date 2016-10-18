package com.group6.babytime.pojo;

import java.util.List;

/**
 * Created by 博博 on 2016/9/22.
 */
public class ListActivityBean {
    public int status;
    public List<YimiaoInfo> dongtailist;
    public static class YimiaoInfo{
        public int Id;
        public String name;
        public String status;
        public int month;

        @Override
        public String toString() {
            return "YimiaoInfo{" +
                    "vc_id=" + Id +
                    ", vc_name='" + name + '\'' +
                    ", vc_status='" + status + '\'' +
                    ", vc_month=" + month +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ListActivityBean{" +
                "status=" + status +
                ", dongtailist=" + dongtailist +
                '}';
    }
}
