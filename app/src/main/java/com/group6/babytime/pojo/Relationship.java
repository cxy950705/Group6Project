package com.group6.babytime.pojo;

public class Relationship {
	private Integer babyId;
	private Integer userId;
	private String userRelationship;
	
	public Relationship(){}
	
	public Relationship(Integer babyId, Integer userId, String userRelationship) {
		super();
		this.babyId = babyId;
		this.userId = userId;
		this.userRelationship = userRelationship;
	}
	public Integer getBabyId() {
		return babyId;
	}
	public void setBabyId(Integer babyId) {
		this.babyId = babyId;
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
		return "Relationship [babyId=" + babyId + ", userId=" + userId
				+ ", userRelationship=" + userRelationship + "]";
	}
	
}
