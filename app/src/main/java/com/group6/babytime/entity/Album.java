package com.group6.babytime.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;

public class Album implements Parcelable {
	private Integer babyId;//宝宝id
	private String albumName;
	private String album_description;
	private String albumPicurl;//相册封面
	private Integer albumId;
	private Timestamp albumCreatime;
	private Integer photoNumber;
	
	
	public Integer getPhotoNumber() {
		return photoNumber;
	}
	public void setPhotoNumber(Integer photoNumber) {
		this.photoNumber = photoNumber;
	}
	public Album(){}
	
	public Album(Integer babyId, String albumName, String albumDescription,
			String albumPicurl, Integer albumId, Timestamp albumCreatime,
			Integer photoNumber) {
		super();
		this.babyId = babyId;
		this.albumName = albumName;
		album_description = albumDescription;
		this.albumPicurl = albumPicurl;
		this.albumId = albumId;
		this.albumCreatime = albumCreatime;
		this.photoNumber = photoNumber;
	}
	public Integer getBabyId() {
		return babyId;
	}
	public void setBabyId(Integer babyId) {
		this.babyId = babyId;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public String getAlbum_description() {
		return album_description;
	}
	public void setAlbum_description(String albumDescription) {
		album_description = albumDescription;
	}
	public String getAlbumPicurl() {
		return albumPicurl;
	}
	public void setAlbumPicurl(String albumPicurl) {
		this.albumPicurl = albumPicurl;
	}
	public Integer getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}
	public Timestamp getAlbumCreatime() {
		return albumCreatime;
	}
	public void setAlbumCreatime(Timestamp albumCreatime) {
		this.albumCreatime = albumCreatime;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(this.babyId);
		dest.writeString(this.albumName);
		dest.writeString(this.album_description);
		dest.writeString(this.albumPicurl);
		dest.writeValue(this.albumId);
		dest.writeSerializable(this.albumCreatime);
		dest.writeValue(this.photoNumber);
	}

	protected Album(Parcel in) {
		this.babyId = (Integer) in.readValue(Integer.class.getClassLoader());
		this.albumName = in.readString();
		this.album_description = in.readString();
		this.albumPicurl = in.readString();
		this.albumId = (Integer) in.readValue(Integer.class.getClassLoader());
		this.albumCreatime = (Timestamp) in.readSerializable();
		this.photoNumber = (Integer) in.readValue(Integer.class.getClassLoader());
	}

	public static final Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>() {
		@Override
		public Album createFromParcel(Parcel source) {
			return new Album(source);
		}

		@Override
		public Album[] newArray(int size) {
			return new Album[size];
		}
	};
}
