package com.vbrick.avenger.dao;

import java.util.ArrayList;

public class AvengerEditVideoSettingsBeanPage {
	
	
	private String title;
	private String description;
	private String categories;
	private String libraries;
	private String tags;
	private String videoduration;
	private ArrayList<String> tagsarray;
	private String status;
	private String embedvideowidth;
	private String embedsize;
	private boolean autoplay;
	private boolean anonymousviewing;
	private String filesourcepath;
	private String customfieldtextbox;
	private String publishdate;
	
	public String getCustomfieldtextbox() {
		return customfieldtextbox;
	}
	public void setCustomfieldtextbox(String customfieldtextbox) {
		this.customfieldtextbox = customfieldtextbox;
	}
	public String getFilesourcepath() {
		return filesourcepath;
	}
	public void setFilesourcepath(String filesourcepath) {
		this.filesourcepath = filesourcepath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public String getLibraries() {
		return libraries;
	}
	public void setLibraries(String libraries) {
		this.libraries = libraries;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public ArrayList<String> getTagsarray() {
		return tagsarray;
	}
	public void setTagsarray(ArrayList<String> tagsarray) {
		this.tagsarray = tagsarray;
	}
	public String getVideoduration() {
		return videoduration;
	}
	public void setVideoduration(String videoduration) {
		this.videoduration = videoduration;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmbedvideowidth() {
		return embedvideowidth;
	}
	public void setEmbedvideowidth(String embedvideowidth) {
		this.embedvideowidth = embedvideowidth;
	}
	public String getEmbedsize() {
		return embedsize;
	}
	public void setEmbedsize(String embedsize) {
		this.embedsize = embedsize;
	}
	public boolean isAutoplay() {
		return autoplay;
	}
	public void setAutoplay(boolean autoplay) {
		this.autoplay = autoplay;
	}
	public boolean isAnonymousviewing() {
		return anonymousviewing;
	}
	public void setAnonymousviewing(boolean anonymousviewing) {
		this.anonymousviewing = anonymousviewing;
	}
	public String getPublishdate() {
		return publishdate;
	}
	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}
	

}
