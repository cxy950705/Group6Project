package com.group6.babytime.pojo;


import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;

public class Baby implements Parcelable {
	private Integer babyId;
	private String babyName;
	private String babySex;
	private Timestamp babyBirthday;
	private String babyImage;
	
	public Baby(){}

	public Baby(Integer babyId, String babyName, String babySex, Timestamp babyBirthday) {
		this.babyId = babyId;
		this.babyName = babyName;
		this.babySex = babySex;
		this.babyBirthday = babyBirthday;
	}

	public Baby(Integer babyId, String babyName, String babySex,
				Timestamp babyBirthday, String babyImage) {
		super();
		this.babyId = babyId;
		this.babyName = babyName;
		this.babySex = babySex;
		this.babyBirthday = babyBirthday;
		this.babyImage = babyImage;
	}
	public Integer getBabyId() {
		return babyId;
	}
	public void setBabyId(Integer babyId) {
		this.babyId = babyId;
	}
	public String getBabyName() {
		return babyName;
	}
	public void setBabyName(String babyName) {
		this.babyName = babyName;
	}
	public String getBabySex() {
		return babySex;
	}
	public void setBabySex(String babySex) {
		this.babySex = babySex;
	}
	public Timestamp getBabyBirthday() {
		return babyBirthday;
	}
	public void setBabyBirthday(Timestamp babyBirthday) {
		this.babyBirthday = babyBirthday;
	}
	public String getBabyImage() {
		return babyImage;
	}
	public void setBabyImage(String babyImage) {
		this.babyImage = babyImage;
	}
	@Override
	public String toString() {
		return "Baby [babyId=" + babyId + ", babyName=" + babyName
				+ ", babySex=" + babySex + ", babyBirthday=" + babyBirthday
				+ ", babyImage=" + babyImage + "]";
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(this.babyId);
		dest.writeString(this.babyName);
		dest.writeString(this.babySex);
		dest.writeSerializable(this.babyBirthday);
		dest.writeString(this.babyImage);
	}

	protected Baby(Parcel in) {
		this.babyId = (Integer) in.readValue(Integer.class.getClassLoader());
		this.babyName = in.readString();
		this.babySex = in.readString();
		this.babyBirthday = (Timestamp) in.readSerializable();
		this.babyImage = in.readString();
	}

	public static final Parcelable.Creator<Baby> CREATOR = new Parcelable.Creator<Baby>() {
		@Override
		public Baby createFromParcel(Parcel source) {
			return new Baby(source);
		}

		@Override
		public Baby[] newArray(int size) {
			return new Baby[size];
		}
	};
}
