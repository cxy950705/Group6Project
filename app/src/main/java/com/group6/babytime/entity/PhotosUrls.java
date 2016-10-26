package com.group6.babytime.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/10/25.
 */
public class PhotosUrls implements Parcelable {
    //查询的url
    private String liebiaoUrl;

    @Override
    public String toString() {
        return "PhotosUrls{" +
                "liebiaoUrl='" + liebiaoUrl + '\'' +
                '}';
    }

    public String getLiebiaoUrl() {
        return liebiaoUrl;
    }

    public void setLiebiaoUrl(String liebiaoUrl) {
        this.liebiaoUrl = liebiaoUrl;
    }
    public PhotosUrls(){}
    public PhotosUrls(String liebiaoUrl) {
        super();
        this.liebiaoUrl = liebiaoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.liebiaoUrl);
    }

    protected PhotosUrls(Parcel in) {
        this.liebiaoUrl = in.readString();
    }

    public static final Parcelable.Creator<PhotosUrls> CREATOR = new Parcelable.Creator<PhotosUrls>() {
        @Override
        public PhotosUrls createFromParcel(Parcel source) {
            return new PhotosUrls(source);
        }

        @Override
        public PhotosUrls[] newArray(int size) {
            return new PhotosUrls[size];
        }
    };
}
