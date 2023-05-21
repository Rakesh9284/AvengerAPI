package com.vbrick.avenger.apibeans;

public class CreatePushContentBean {
	String Name,Link,PushMethod,IsEnabled;
	
	public void setName(String name) {
		this.Name = name;
	}
	public String getName() {
		return Name;
	}

	public void setLink(String link) {
		this.Link = link;
	}
	public String getLink() {
		return Link;
	}

	public void setPushMethod(String pushmethod) {
		this.PushMethod = pushmethod;
	}
	public String getPushMethod() {
		return PushMethod;
	}
	public void setIsEnabled(String isenabled) {
		this.IsEnabled = isenabled;
	}
	public String getIsEnabled() {
		return IsEnabled;
	}

}
