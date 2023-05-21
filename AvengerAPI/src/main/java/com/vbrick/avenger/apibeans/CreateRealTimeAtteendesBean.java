package com.vbrick.avenger.apibeans;

public class CreateRealTimeAtteendesBean {
 String SortField,SortDirection,ScrollId,Q,SearchField,Status,AttendeeDetails,RunNumber,Count;
 
 public void setSortField(String sortField) {
		this.SortField = sortField;
	}
	public String getSortField() {
		return SortField;
	}
	
	public void setSortDirection(String sortDirection) {
		this.SortDirection = sortDirection;
	}
	public String getSortDirection() {
		return SortDirection;
	}
	
	public void setCount(String count) {
		this.Count = count;
	}
	public String getCount() {
		return Count;
	}
   
	public void setScrollId(String scrollId) {
		this.ScrollId = scrollId;
	}
	public String getScrollId() {
		return ScrollId;
	}

	public void setQ(String q) {
		this.Q= q;
	}
	public String getQ() {
		return Q;
	}
	public void setSearchField(String searchField) {
		this.SearchField= searchField;
	}
	public String getSearchField() {
		return SearchField;
	}
	public void setRunNumber(String runNumber) {
		this.RunNumber= runNumber;
	}
	public String getRunNumber() {
		return RunNumber;
	}
	public void setStatus(String status) {
		this.Status= status;
	}
	public String getStatus() {
		return Status;
	}
	public void setAttendeeDetails(String attendeeDetails) {
		this.AttendeeDetails= attendeeDetails;
	}
	public String getAttendeeDetails() {
		return AttendeeDetails;
	}
}
