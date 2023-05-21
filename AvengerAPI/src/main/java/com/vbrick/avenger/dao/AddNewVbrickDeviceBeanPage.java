package com.vbrick.avenger.dao;

public class AddNewVbrickDeviceBeanPage {
	
	private String devicename;
	private String macaddress;
	private String devicestatus;
	private String streamname;
	private String streamurl;
	private String encodingtype;
	
	public String getStreamname() {
		return streamname;
	}
	public void setStreamname(String streamname) {
		this.streamname = streamname;
	}
	public String getStreamurl() {
		return streamurl;
	}
	public void setStreamurl(String streamurl) {
		this.streamurl = streamurl;
	}
	public String getEncodingtype() {
		return encodingtype;
	}
	public void setEncodingtype(String encodingtype) {
		this.encodingtype = encodingtype;
	}
	public String getDevicename() {
		return devicename;
	}
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	public String getMacaddress() {
		return macaddress;
	}
	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}
	public String getDevicestatus() {
		return devicestatus;
	}
	public void setDevicestatus(String devicestatus) {
		this.devicestatus = devicestatus;
	}
	

}
