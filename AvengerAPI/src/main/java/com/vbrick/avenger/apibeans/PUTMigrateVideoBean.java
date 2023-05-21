package com.vbrick.avenger.apibeans;

public class PUTMigrateVideoBean {
	 String owner,userId,username,email;
	 String userName,uploadedBy,whenUploaded,publishDate,legacyViewCount;
 
	public void setUserName(String name) {
		this.userName = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwner() {
		return owner;
	}
	public void setUserId(String userid) {
		this.userId = userid;
	}
	public String getUserId() {
		return userId;
	}

	public void setownerUserName(String name) {
		this.username = name;
	}
	public String getownerUserName() {
		return username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setUploadedby(String uploadedby) {
		this.uploadedBy = uploadedby;
	}
	public String getUploadedby() {
		return uploadedBy;
	}
	public void setWhenUploaded(String whenuploaded) {
		this.whenUploaded = whenuploaded;
	}
	public String getWhenUploaded() {
		return whenUploaded;
	}
	
	public void setPublishDate(String publishdate) {
		this.publishDate = publishdate;
	}
	public String getPublishDate() {
		return publishDate;
	} 
	public void setLegacyViewCount(String legacyviewcount) {
		this.legacyViewCount = legacyviewcount;
	}
	public String getLegacyViewCount() {
		return legacyViewCount;
	} 	

}
