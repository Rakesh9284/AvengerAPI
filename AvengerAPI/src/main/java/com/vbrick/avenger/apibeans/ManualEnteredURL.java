package com.vbrick.avenger.apibeans;

public class ManualEnteredURL {

	public String title,description,enableComments,enableRatings,enableDownloads,uploader,isActive,
	 videoAccessControl,accessControlEntities;
	public String[]tags;
	public String[]Categories;
	public String[]CategoryIds;
	public String LinkedUrl;
	
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
	public String getEnableComments() {
		return enableComments;
	}
	public void setEnableComments(String enableComments) {
		this.enableComments = enableComments;
	}
	public String getEnableRatings() {
		return enableRatings;
	}
	public void setEnableRatings(String enableRatings) {
		this.enableRatings = enableRatings;
	}
	public String getEnableDownloads() {
		return enableDownloads;
	}
	public void setEnableDownloads(String enableDownloads) {
		this.enableDownloads = enableDownloads;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getVideoAccessControl() {
		return videoAccessControl;
	}
	public void setVideoAccessControl(String videoAccessControl) {
		this.videoAccessControl = videoAccessControl;
	}
	public String getAccessControlEntities() {
		return accessControlEntities;
	}
	public void setAccessControlEntities(String accessControlEntities) {
		this.accessControlEntities = accessControlEntities;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public String[] getCategories() {
		return Categories;
	}
	public void setCategories(String[] categories) {
		Categories = categories;
	}
	public String[] getCategoryIds() {
		return CategoryIds;
	}
	public void setCategoryIds(String[] categoryIds) {
		CategoryIds = categoryIds;
	}
	public String getLinkedUrl() {
		return LinkedUrl;
	}
	public void setLinkedUrl(String linkedUrl) {
		LinkedUrl = linkedUrl;
	}
	
}
