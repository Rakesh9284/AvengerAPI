package com.vbrick.avenger.dao;

import java.util.ArrayList;

public class AvengerBulkEditBeanPage {
	
	
	private ArrayList<String> Status;
	private ArrayList<String> categories;
	
	public ArrayList<String> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}
	public ArrayList<String> getAccessControlList() {
		return AccessControlList;
	}
	public void setAccessControlList(ArrayList<String> accessControlList) {
		AccessControlList = accessControlList;
	}
	public ArrayList<String> getTags() {
		return Tags;
	}
	public void setTags(ArrayList<String> tags) {
		Tags = tags;
	}
	public ArrayList<String> getDownloads() {
		return Downloads;
	}
	public void setDownloads(ArrayList<String> downloads) {
		Downloads = downloads;
	}
	public ArrayList<String> getComments() {
		return Comments;
	}
	public void setComments(ArrayList<String> comments) {
		Comments = comments;
	}
	public ArrayList<String> getRating() {
		return Rating;
	}
	public void setRating(ArrayList<String> rating) {
		Rating = rating;
	}
	private ArrayList<String> Accesscontrol;
	private ArrayList<String> AccessControlList;
	private ArrayList<String> Tags;
	private ArrayList<String> Downloads;
	private ArrayList<String> Comments;
	private ArrayList<String> Rating;
	
	
	public ArrayList<String> getStatus() {
		return Status;
	}
	public void setStatus(ArrayList<String> status) {
		Status = status;
	}

	public ArrayList<String> getAccesscontrol() {
		return Accesscontrol;
	}
	public void setAccesscontrol(ArrayList<String> accesscontrol) {
		Accesscontrol = accesscontrol;
	}

}
