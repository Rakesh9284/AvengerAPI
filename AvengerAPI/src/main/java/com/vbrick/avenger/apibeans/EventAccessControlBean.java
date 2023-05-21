package com.vbrick.avenger.apibeans;

public class EventAccessControlBean {

	String[] UserIds;
	String[] Usernames;
	String[] GroupIds;
	public String[] getUserIds() {
		return UserIds;
	}
	public void setUserIds(String[] userIds) {
		UserIds = userIds;
	}
	public String[] getUsernames() {
		return Usernames;
	}
	public void setUsernames(String[] usernames) {
		Usernames = usernames;
	}
	public String[] getGroupIds() {
		return GroupIds;
	}
	public void setGroupIds(String[] groupIds) {
		GroupIds = groupIds;
	}
	
	
}
