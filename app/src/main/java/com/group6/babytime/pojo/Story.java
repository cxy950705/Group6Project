package com.group6.babytime.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Story implements Parcelable {

	private Integer story_id;
	private String story_name;
	private String story_type;
	private String story_cover;
	private Integer story_clicknum;

	
	public Story(Integer story_id, String story_name, String story_type,
			String story_cover, Integer story_clicknum) {
		super();
		this.story_id = story_id;
		this.story_name = story_name;
		this.story_type = story_type;
		this.story_cover = story_cover;
		this.story_clicknum = story_clicknum;
	}
	public Integer getStory_id() {
		return story_id;
	}
	public void setStory_id(Integer story_id) {
		this.story_id = story_id;
	}
	public String getStory_name() {
		return story_name;
	}
	public void setStory_name(String story_name) {
		this.story_name = story_name;
	}
	public String getStory_type() {
		return story_type;
	}
	public void setStory_type(String story_type) {
		this.story_type = story_type;
	}
	public String getStory_cover() {
		return story_cover;
	}
	public void setStory_cover(String story_cover) {
		this.story_cover = story_cover;
	}
	public Integer getStory_clicknum() {
		return story_clicknum;
	}
	public void setStory_clicknum(Integer story_clicknum) {
		this.story_clicknum = story_clicknum;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(this.story_id);
		dest.writeString(this.story_name);
		dest.writeString(this.story_type);
		dest.writeString(this.story_cover);
		dest.writeValue(this.story_clicknum);
	}

	protected Story(Parcel in) {
		this.story_id = (Integer) in.readValue(Integer.class.getClassLoader());
		this.story_name = in.readString();
		this.story_type = in.readString();
		this.story_cover = in.readString();
		this.story_clicknum = (Integer) in.readValue(Integer.class.getClassLoader());
	}

	public static final Creator<Story> CREATOR = new Creator<Story>() {
		@Override
		public Story createFromParcel(Parcel source) {
			return new Story(source);
		}

		@Override
		public Story[] newArray(int size) {
			return new Story[size];
		}
	};
}
