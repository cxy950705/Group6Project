package com.group6.babytime.pojo;

import java.sql.Timestamp;

public class BabyInfoBean {
	private Integer babyId;
	private String babyName;
	private String babySex;
	private Timestamp babyBirthday;
	private String babyImage;
	private Integer userId;
	private String userRelationship;
	
	public BabyInfoBean(){}

	public BabyInfoBean(String babyName, String babySex, Timestamp babyBirthday, String babyImage, Integer userId, String userRelationship) {
		this.babyName = babyName;
		this.babySex = babySex;
		this.babyBirthday = babyBirthday;
		this.babyImage = babyImage;
		this.userId = userId;
		this.userRelationship = userRelationship;
	}

	public BabyInfoBean(Integer babyId, String babyName, String babySex,
						Timestamp babyBirthday, String babyImage, Integer userId,
						String userRelationship) {
		super();
		this.babyId = babyId;
		this.babyName = babyName;
		this.babySex = babySex;
		this.babyBirthday = babyBirthday;
		this.babyImage = babyImage;
		this.userId = userId;
		this.userRelationship = userRelationship;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserRelationship() {
		return userRelationship;
	}
	public void setUserRelationship(String userRelationship) {
		this.userRelationship = userRelationship;
	}

	@Override
	public String toString() {
		return "BabyInfoBean [babyId=" + babyId + ", babyName=" + babyName
				+ ", babySex=" + babySex + ", babyBirthday=" + babyBirthday
				+ ", babyImage=" + babyImage + ", userId=" + userId
				+ ", userRelationship=" + userRelationship + "]";
	}
	
}
