package com.vbricks.avenger.utils;

public class  HttpStatusCode {

	
    
	//Create User API
	public static final String errorcode = "UserCannotBeCreated";
	public static final String errorcode1 = "InternalServerError";
	public static final String detailErrorUserName = "VBrickPlatform.IssueDetail = RequiredFieldMissing = Following required field is found missing in the request : UserName";
	public static final String detailErrorLastName = "VBrickPlatform.IssueDetail = RequiredFieldMissing = Following required field is found missing in the request : LastName";
	public static final String detailErrorExistingEmail = "EmailInUse";

	//Upload Vidoe APi
	public static final String httpsStatusCode = "HTTP StatusCode :200";
	public static final String httpsStatusCode200OK="200OK";
	public static final String httpStatuscode500InternalServerError="500 Internal Server Error";
	public static final String httpsStatusCode401 = "HTTP StatusCode :401";
	public static final String httpsStatusCode400 = "HTTP StatusCode :400";
	public static final String httpsStatusCode404 = "HTTP StatusCode :404";
	public static final String httpsStatusCode415 = "HTTP StatusCode :415";
	public static final String httpsStatusCode200 = "200";
	public static final String httpsStatusCode500 = "500";
	public static final String httpsstatuscode500 = "HTTP StatusCode :500";
	public static final String httpsStatus401 = "401";
	public static final String httpsStatus500 = "500";
	public static final String httpsStatus404 = "404";
	public static final String httpsStatus400 = "400";
	public static final int httpStatusCodeInt200=200;
	public static final int httpStatusCodeInt500=500;
	public static final int httpStatusCodeInt401=401;
	public static final int httpStatusCodeInt400=400;
	public static final int httpStatusCodeInt503=503;
	public static final int httpStatusCodeInt404=404;
	public static final String httpStatusCodeInt401null="401null";
	public static final String httpStatusCodeInt500null="500null";
	
	public static final String APIResponseBaseUrl="baseurl";
	public static final String APIResponseHttpCode="httpCode";
	public static final String APIResponseStatusInfo="statusInfo";
	
	//Team API
	public static final String code = "InternalServerError";
	public static final String detail = "ChannelNameInUse";
	public static final String detail1 = "GroupNameInUse";
	public static final String detail2 = "MalformedRequest = InvalidGroupIds";
	public static final String detail3 = "MalformedRequest = InvalidUserIds";
	
	//Event API
	
	public static final String eventCode1 = "OneOrMoreIssuesFoundInRequest";
	public static final String eventErrorDetail1 = "VBrickPlatform.IssueDetail = LobbyTime_PreProd_Invalid = Lobby Time must not start before the pre-production time window";
	public static final String eventErrorDetail2 = "MalformedRequest = This Webcast Shortcut Name is in use by another event and its Lobby Time.  Please enter a different Webcast Shortcut or change the time of the webcast.";
	public static final String eventErrorDetail3 = "VBrickPlatform.IssueDetail = No more than 3 event admins are allowed";
	public static final String eventErrorDetail4 = "MalformedRequest = This Webcast Shortcut Name is in use by another event and its Lobby Time.  Please enter a different Webcast Shortcut or change the time of the webcast.";
	public static final String eventErrorDetail5 = "VideoSourceNotAvailable";
	public static final String eventCode2 = "UnsupportedMediaType";
	
	public static final String missingCode = "MalformedRequest";
	public static final String missingDetail = "MalformedRequest = Following required field is found missing in the request : Name";
	public static final String invalidCategory = "OneOrMoreInvalidCategoriesFoundInRequest";
		
	//Upload Video
	
	public static final String code1 = "UploaderUserDoesNotExist";
	
	//Edit Groups
	
	public static final String errordetail = "InvalidUserIds";
	

	
}
