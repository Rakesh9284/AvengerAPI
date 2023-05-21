package com.vbricks.avenger.service;

public interface ICreateRealTimeAtteendes {
	 
	String SortField="sortField";
	String SortDirection="sortDirection";
	String ScrollId="scrollId";
	String Q="q";
	String SearchField="searchField";
	String Status="status";
	String AttendeeDetails="attendeeDetails";
	String Count="count";
	String RunNumber="runNumber";
	
	
	String[] sortField= {"FullName", "Email", "ZoneName", "StreamType", "IpAddress","Browser", "OsFamily","StreamAccessed", "PyerDevice", "OsName","UserType","Username", "AttendeeType" };
    String[] sortDirection= {"asc","desc"};
    String[] searchField= {"FullName", "Email", "ZoneName", "StreamType", "IpAddress","Browser", "OsFamily","StreamAccessed", "PyerDevice", "OsName","UserType","Username", "AttendeeType" };
	String[] status= {"All","Online","Offline"};
	String[] attendeeDetails= {"All", "Base", "Counts"};
	
}
