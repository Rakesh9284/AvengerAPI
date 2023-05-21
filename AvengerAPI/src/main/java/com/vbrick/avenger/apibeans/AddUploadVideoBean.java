package com.vbrick.avenger.apibeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class AddUploadVideoBean {
	public String title,description,enableComments,enableRatings,enableDownloads,uploader,isActive,
	 videoAccessControl,teamid,groupId,userId,is360,unlisted,customFieldValue,customFieldId,expirationAction,publishDate,expirationDate,whenUploaded,legacyViewCount;

	public String accesscontrolforuser;
	public String accesscontrolforgroup;
	public String accesscontrolforteam;
	public String[]tags;
	public String[]Categories;
	public String[]CategoryIds;
	public String accesscontrolentities;
	
	

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getWhenUploaded() {
		return whenUploaded;
	}

	public void setWhenUploaded(String whenUploaded) {
		this.whenUploaded = whenUploaded;
	}

	public String getCustomFieldValue() {
		return customFieldValue;
	}

	public void setCustomFieldValue(String customFieldValue) {
		this.customFieldValue = customFieldValue;
	}

	public String getCustomFieldId() {
		return customFieldId;
	}

	public void setCustomFieldId(String customFieldId) {
		this.customFieldId = customFieldId;
	}

	public String getExpirationAction() {
		return expirationAction;
	}

	public void setExpirationAction(String expirationAction) {
		this.expirationAction = expirationAction;
	}

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getAccesscontrolentities() {
		return accesscontrolentities;
	}

	public void setAccesscontrolentities(String accesscontrolentities) {
		this.accesscontrolentities = accesscontrolentities;
	}

	public String getTeamid() {
		return teamid;
	}

	public void setTeamid(String teamid) {
		this.teamid = teamid;
	}

	public String getAccesscontrolforuser() {
		return accesscontrolforuser;
	}

	public void setAccesscontrolforuser(String accesscontrolforuser) {
		this.accesscontrolforuser = accesscontrolforuser;
	}

	public String getAccesscontrolforgroup() {
		return accesscontrolforgroup;
	}

	public void setAccesscontrolforgroup(String accesscontrolforgroup) {
		this.accesscontrolforgroup = accesscontrolforgroup;
	}

	public String getAccesscontrolforteam() {
		return accesscontrolforteam;
	}

	public void setAccesscontrolforteam(String accesscontrolforteam) {
		this.accesscontrolforteam = accesscontrolforteam;
	}

	public String[] getCategories() {
		return Categories;
	}

	public void setCategories(String[] categories) {
		this.Categories = categories;
	}
	

	public String[] getCategoryIds() {
		return CategoryIds;
	}

	public void setCategoryIds(String[] categoryIds) {
		this.CategoryIds = categoryIds;
	}
 

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
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
	
	public String getIs360() {
		return is360;
	}

	public void setIs360(String is360) {
		this.is360 = is360;
	}
 
	public String getVideoAccessControl() {
		return videoAccessControl;
	}

	public void setVideoAccessControl(String videoAccessControl) {
		this.videoAccessControl = videoAccessControl;
	}

	public String getUnlisted() {
		return unlisted;
	}

	public void setUnlisted(String unlisted) {
		this.unlisted = unlisted;
	}

	public void setLegacyViewCount(String legacyviewcount) {
		this.legacyViewCount = legacyviewcount;
	}
	public String getLegacyViewCount() {
		return legacyViewCount;
	} 	

}
