package com.group6.babytime.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public  class Photo implements Parcelable {
	//[{"photoId":10,"photoUploadDate":"Oct 3, 2016 3:00:00 AM",
	// "photoName":"r","photoUrl":"upload/photo3.png",
	// "albumId":1,"albumName":"宝宝两岁","content":"aaaa"}
	private String albumName;
	private Integer photoId;
	//public static List<String> photosurls=new ArrayList<>();



	@Override
	public String toString() {
		return "Photo{" +
				"albumName='" + albumName + '\'' +
				", photoId=" + photoId +
				", photoUploadDate=" + photoUploadDate +
				", photoName='" + photoName + '\'' +
				", photoUrl='" + photoUrl + '\'' +
				", albumId=" + albumId +
				", content='" + content + '\'' +
				'}';
	}

	private Date photoUploadDate;
	private String photoName;
	public String photoUrl;
	private Integer albumId;
	private String content;
	
	public Photo(){}
	
	public Photo(String albumName, Integer photoId, Date photoUploadDate,
			String photoName, String photoUrl, Integer albumId,
			String content) {
		super();
		this.albumName = albumName;
		this.photoId = photoId;
		this.photoUploadDate = photoUploadDate;
		this.photoName = photoName;
		this.photoUrl = photoUrl;
		this.albumId = albumId;
		this.content = content;
	}
	

	public String getContent() {
		return content;
	}

	public void setContent(String photoContent) {
		this.content = content;
	}

	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public Integer getPhotoId() {
		return photoId;
	}
	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}
	public Date getPhotoUploadDate() {
		return photoUploadDate;
	}
	public void setPhotoUploadDate(Date photoUploadDate) {
		this.photoUploadDate = photoUploadDate;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public Integer getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.albumName);
		dest.writeValue(this.photoId);
		dest.writeSerializable(this.photoUploadDate);
		dest.writeString(this.photoName);
		dest.writeString(this.photoUrl);
		dest.writeValue(this.albumId);
		dest.writeString(this.content);
	}

	protected Photo(Parcel in) {
		this.albumName = in.readString();
		this.photoId = (Integer) in.readValue(Integer.class.getClassLoader());
		this.photoUploadDate = (Date) in.readSerializable();
		this.photoName = in.readString();
		this.photoUrl = in.readString();
		this.albumId = (Integer) in.readValue(Integer.class.getClassLoader());
		this.content = in.readString();
	}

	public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
		@Override
		public Photo createFromParcel(Parcel source) {
			return new Photo(source);
		}

		@Override
		public Photo[] newArray(int size) {
			return new Photo[size];
		}
	};
}
