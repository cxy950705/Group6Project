package com.group6.babytime.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public  class QueryPhotoItemBean {

	private Timestamp photoUploadDate;//上传日期
	private String photoContent;//上传照片时的描述
	private Integer number;//同一时间段传的照片数量

	
	
	@Override
	public String toString() {
		return "QueryPhotoItemBean [number=" + number + ", photoContent="
				+ photoContent + ", photoUploadDate=" + photoUploadDate
				+ ", photoUrl=" +  "]";
	}
	public QueryPhotoItemBean(Timestamp photoUploadDate, String photoContent,
			Integer number) {
		super();
		this.photoUploadDate = photoUploadDate;
		this.photoContent = photoContent;
		this.number = number;
		;
	}
	public Timestamp getPhotoUploadDate() {
		return photoUploadDate;
	}
	public void setPhotoUploadDate(Timestamp photoUploadDate) {
		this.photoUploadDate = photoUploadDate;
	}
	public String getPhotoContent() {
		return photoContent;
	}
	public void setPhotoContent(String photoContent) {
		this.photoContent = photoContent;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}

	
	

}
