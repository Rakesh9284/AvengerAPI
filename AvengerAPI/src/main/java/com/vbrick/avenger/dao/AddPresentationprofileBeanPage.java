package com.vbrick.avenger.dao;

public class AddPresentationprofileBeanPage {

	private String name;
	private String description;
	private String status;
	private String videosource;
	private String destinationdevicename;
	private String streamname;

	public String getStreamname() {
		return streamname;
	}
	public void setStreamname(String streamname) {
		this.streamname = streamname;
	}
	public String getDestinationdevicename() {
		return destinationdevicename;
	}
	public void setDestinationdevicename(String destinationdevicename) {
		this.destinationdevicename = destinationdevicename;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getVideosource() {
		return videosource;
	}
	public void setVideosource(String videosource) {
		this.videosource = videosource;
	}
	
}
