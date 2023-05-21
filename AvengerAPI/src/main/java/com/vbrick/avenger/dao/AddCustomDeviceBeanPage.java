package com.vbrick.avenger.dao;

public class AddCustomDeviceBeanPage {

	private String devicename;
	private String ipaddress;
	private String streamname;
	private String streamurl;
	private String streamencodingtype;
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDevicename() {
		return devicename;
	}
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
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
	public String getStreamencodingtype() {
		return streamencodingtype;
	}
	public void setStreamencodingtype(String streamencodingtype) {
		this.streamencodingtype = streamencodingtype;
	}
}
