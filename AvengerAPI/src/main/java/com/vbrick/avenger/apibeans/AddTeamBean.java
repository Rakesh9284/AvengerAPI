package com.vbrick.avenger.apibeans;

public class AddTeamBean {

	String name, description;
	String[] userids, groupids;
	String teammemberasuser;
	String teammemberasgroup;
	String teammembers;
	boolean isTeamMembers;
	String[] teamMembers;

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

	public String[] getUserids() {
		return userids;
	}

	public void setUserids(String[] userids) {
		this.userids = userids;
	}

	public String[] getGroupids() {
		return groupids;
	}

	public void setGroupids(String[] groupids) {
		this.groupids = groupids;
	}
		
	
	public String getTeammemberasuser() {
		return teammemberasuser;
	}

	public void setTeammemberasuser(String teammemberasuser) {
		this.teammemberasuser = teammemberasuser;
	}

	public String getTeammemberasgroup() {
		return teammemberasgroup;
	}

	public void setTeammemberasgroup(String teammemberasgroup) {
		this.teammemberasgroup = teammemberasgroup;
	}

	public String getTeammembers() {
		return teammembers;
	}

	public void setTeammembers(String teammembers) {
		this.teammembers = teammembers;
	}

	public boolean isTeamMembers() {
		return isTeamMembers;
	}

	public void setTeamMembers(boolean isTeamMembers) {
		this.isTeamMembers = isTeamMembers;
	}

	public String[] getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(String[] teamMembers) {
		this.teamMembers = teamMembers;
	}
	
			
}
