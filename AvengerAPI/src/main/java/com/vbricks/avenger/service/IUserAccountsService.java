package com.vbricks.avenger.service;

import java.util.HashMap;

 
public interface IUserAccountsService {
  /**
	/api/v1/users
	/api/v1/users/{userId}
	/api/v1/users/{username}?type=username
	/api/v1/users/roles
  */
 
	//User Details 
	String FIRSTNAME="firstname";
	String LASTNAME="lastname";
	String USERNAME="username";
	String PHONENO="phonenumber";
	String USERROLE="roles";
	String CONTACTEMAIL="contactemail";
	String LANGUAGE="language"; 
	String GROUPS="groups";
	String TITLE="title";
	String VIDEOSOURCETYPE="videoSourceType";
	String USERID="userId";
	String aadminuser="apiaadmin1";
	String madminuser="apimadmin21";
	String eadminuser="apiaadmin41";
	String mconstributoruser="apimideaconstrubutor61";
	String mediavieweruser="apimviewer81";
	String Name = "name"; 
	String EVENTID = "eventId";
	String VIDEOID = "videotId";
	String estimatedattendees = "estimatedAttendees";
	String INVALIDUSERID="b78f91b4-cd28-40bc-be76-397d558808888";
	String ruleName = "ruleName";
	String numberOfDays = "numberOfDays";
	String expiryRuleType = "expiryRuleType";
	String deleteOnExpiration = "deleteOnExpiration";
	String isDefault = "isDefault";
	String description = "description";	
	String apigridpwd="Password@123";
 public abstract HashMap<String, String>  createUserAPI(HashMap<String, String> getUserRoleApiResponse, String datatype,String fileds,HashMap<String, String> createuserdetailsbean);
 
	
	
}
