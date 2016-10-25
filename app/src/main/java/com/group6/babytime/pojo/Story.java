package com.group6.babytime.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Story implements Parcelable {
	private Integer story_id;
	private String story_name;
	private String story_type;
	private String story_cover;
	private Integer story_clicknum;
	private String story_introduction;
	private Integer story_episode;
	private String story_content;
	
	public Story(){
		
	}
	
	
	
	public Story(Integer story_id, String story_name, String story_type,
			String story_cover, Integer story_clicknum,
			String story_introduction, Integer story_episode,
			String story_content) {
		super();
		this.story_id = story_id;
		this.story_name = story_name;
		this.story_type = story_type;
		this.story_cover = story_cover;
		this.story_clicknum = story_clicknum;
		this.story_introduction = story_introduction;
		this.story_episode = story_episode;
		this.story_content = story_content;
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
	public String getStory_introduction() {
		return story_introduction;
	}
	public void setStory_introduction(String story_introduction) {
		this.story_introduction = story_introduction;
	}
	public Integer getStory_episode() {
		return story_episode;
	}
	public void setStory_episode(Integer story_episode) {
		this.story_episode = story_episode;
	}
	public String getStory_content() {
		return story_content;
	}
	public void setStory_content(String story_content) {
		this.story_content = story_content;
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
		dest.writeString(this.story_introduction);
		dest.writeValue(this.story_episode);
		dest.writeString(this.story_content);
	}

	protected Story(Parcel in) {
		this.story_id = (Integer) in.readValue(Integer.class.getClassLoader());
		this.story_name = in.readString();
		this.story_type = in.readString();
		this.story_cover = in.readString();
		this.story_clicknum = (Integer) in.readValue(Integer.class.getClassLoader());
		this.story_introduction = in.readString();
		this.story_episode = (Integer) in.readValue(Integer.class.getClassLoader());
		this.story_content = in.readString();
	}

	public static final Parcelable.Creator<Story> CREATOR = new Parcelable.Creator<Story>() {
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
