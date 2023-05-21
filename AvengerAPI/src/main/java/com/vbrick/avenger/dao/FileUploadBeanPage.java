package com.vbrick.avenger.dao;

import java.util.ArrayList;

public class FileUploadBeanPage {
	
	private ArrayList<String> sfilename;
	private String filename;
	private String filesourcepath;
	private String videoUrl;
	private String videoType;
	private String encodingType;
	private String videoStatus;
	private String validstreamurl;
	
	
	
	
	public String getValidstreamurl() {
		return validstreamurl;
	}
	public void setValidstreamurl(String validstreamurl) {
		this.validstreamurl = validstreamurl;
	}
	public String getVideoStatus() {
		return videoStatus;
	}
	public void setVideoStatus(String videoStatus) {
		this.videoStatus = videoStatus;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getVideoType() {
		return videoType;
	}
	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}
	public String getEncodingType() {
		return encodingType;
	}
	public void setEncodingType(String encodingType) {
		this.encodingType = encodingType;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public ArrayList<String> getSfilename() {
		return sfilename;
	}
	public void setSfilename(ArrayList<String> sfilename) {
		this.sfilename = sfilename;
	}
	public String getFilesourcepath() {
		return filesourcepath;
	}
	public void setFilesourcepath(String filesourcepath) {
		this.filesourcepath = filesourcepath;
	}

}
