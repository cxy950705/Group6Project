package com.group6.babytime.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;



public class LogDiary implements Parcelable {
	private Integer logId;
	private Integer userId;
	private Timestamp logDate;
	private String logContent;
	private String logPhoto;
	private Integer logLikenum;
	private Integer babyId;
	
	public LogDiary(){}

	public LogDiary(Integer userId, Timestamp logDate, String logContent, String logPhoto, Integer logLikenum, Integer babyId) {
		this.userId = userId;
		this.logDate = logDate;
		this.logContent = logContent;
		this.logPhoto = logPhoto;
		this.logLikenum = logLikenum;
		this.babyId = babyId;
	}

	public LogDiary(Integer babyId){this.babyId = babyId;}
	public LogDiary(Integer logId, Integer userId, Timestamp logDate,
			String logContent, String logPhoto, Integer logLikenum, Integer babyId) {
		super();
		this.logId = logId;
		this.userId = userId;
		this.logDate = logDate;
		this.logContent = logContent;
		this.logPhoto = logPhoto;
		this.logLikenum = logLikenum;
		this.babyId = babyId;
	}


	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Timestamp getLogDate() {
		return logDate;
	}
	public void setLogDate(Timestamp logDate) {
		this.logDate = logDate;
	}
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	public String getLogPhoto() {
		return logPhoto;
	}
	public void setLogPhoto(String logPhoto) {
		this.logPhoto = logPhoto;
	}
	public Integer getLogLikenum() {
		return logLikenum;
	}
	public void setLogLikenum(Integer logLikenum) {
		this.logLikenum = logLikenum;
	}

	public Integer getBabyId() {
		return babyId;
	}


	public void setBabyId(Integer babyId) {
		this.babyId = babyId;
	}


	@Override
	public String toString() {
		return "Log [logContent=" + logContent + ", logDate=" + logDate
				+ ", logId=" + logId + ", logLikenum=" + logLikenum
				+ ", logPhoto=" + logPhoto + ", userId=" + userId + "]";
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.logId);
		dest.writeInt(this.userId);
		dest.writeSerializable(this.logDate);
		dest.writeString(this.logContent);
		dest.writeString(this.logPhoto);
		dest.writeInt(this.logLikenum);
		dest.writeInt(this.babyId);
	}

	protected LogDiary(Parcel in) {
		this.logId = in.readInt();
		this.userId = in.readInt();
		this.logDate = (Timestamp) in.readSerializable();
		this.logContent = in.readString();
		this.logPhoto = in.readString();
		this.logLikenum = in.readInt();
		this.babyId = in.readInt();
	}

	public static final Parcelable.Creator<LogDiary> CREATOR = new Parcelable.Creator<LogDiary>() {
		@Override
		public LogDiary createFromParcel(Parcel source) {
			return new LogDiary(source);
		}

		@Override
		public LogDiary[] newArray(int size) {
			return new LogDiary[size];
		}
	};
}
