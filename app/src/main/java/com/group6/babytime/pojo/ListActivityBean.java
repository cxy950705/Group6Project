package com.group6.babytime.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by 博博 on 2016/9/22.
 */
public class ListActivityBean implements Parcelable {
    public int status;
    public List<YimiaoInfo> dongtailist;
    public static class YimiaoInfo implements Parcelable {
        public int Id;
        public String name;
        public String status;
        public int month;
        public String description;
//        public Timestamp date;

        @Override
        public String toString() {
            return "YimiaoInfo{" +
                    "Id=" + Id +
                    ", name='" + name + '\'' +
                    ", status='" + status + '\'' +
                    ", month=" + month +
                    ", description='" + description + '\'' +

                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.Id);
            dest.writeString(this.name);
            dest.writeString(this.status);
            dest.writeInt(this.month);
            dest.writeString(this.description);
        }

        public YimiaoInfo() {
        }

        protected YimiaoInfo(Parcel in) {
            this.Id = in.readInt();
            this.name = in.readString();
            this.status = in.readString();
            this.month = in.readInt();
            this.description = in.readString();
        }

        public static final Parcelable.Creator<YimiaoInfo> CREATOR = new Parcelable.Creator<YimiaoInfo>() {
            @Override
            public YimiaoInfo createFromParcel(Parcel source) {
                return new YimiaoInfo(source);
            }

            @Override
            public YimiaoInfo[] newArray(int size) {
                return new YimiaoInfo[size];
            }
        };
    }

    @Override
    public String toString() {
        return "ListActivityBean{" +
                "status=" + status +
                ", dongtailist=" + dongtailist +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeTypedList(this.dongtailist);
    }

    public ListActivityBean() {
    }

    protected ListActivityBean(Parcel in) {
        this.status = in.readInt();
        this.dongtailist = in.createTypedArrayList(YimiaoInfo.CREATOR);
    }

    public static final Parcelable.Creator<ListActivityBean> CREATOR = new Parcelable.Creator<ListActivityBean>() {
        @Override
        public ListActivityBean createFromParcel(Parcel source) {
            return new ListActivityBean(source);
        }

        @Override
        public ListActivityBean[] newArray(int size) {
            return new ListActivityBean[size];
        }
    };
}
