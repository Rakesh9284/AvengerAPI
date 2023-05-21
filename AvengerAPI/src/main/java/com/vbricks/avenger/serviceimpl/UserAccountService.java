package com.vbricks.avenger.serviceimpl;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.json.simple.JSONArray;
 

public class UserAccountService implements IUserAccountsService {

	public ClientResponse response;
	public Client client;
	public WebResource webResource;
	private static Logger logger = Logger.getLogger(UserAccountService.class);
 
	public HashMap<String, String> createUserAPI(HashMap<String, String> getuserroleapiresponse, String datatype,String fileds, HashMap<String, String>  createuserdetailsbean) {
		String userId = null;
		String validData = "validData";
		String responseFlag = null;
		HashMap<String, String> responeapicreateuser = new HashMap<String, String>();
	 
		getuserroleapiresponse.put("datatype",datatype);
		getuserroleapiresponse.put("datafileds", fileds);
		if (datatype.equals(validData)) {

			try {

				client = Client.create();
				webResource = client.resource(getuserroleapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl)).path(RevbaseAPIURLs.CREATEUSERRURL);
				logger.info("API URL is ::::"+webResource);
					response = webResource
										   .accept(ApiResources.Content_Type)
										   .type(ApiResources.Content_Type)
										   .header("Authorization", "VBrick "+ getuserroleapiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
										   .post(ClientResponse.class,createUserJSON(createuserdetailsbean,getuserroleapiresponse).toString() );
				
					if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
						responseFlag = response.getEntity(String.class);
						logger.info("AddNewUser API Response JSON  :::: " + responseFlag);
						userId = JsonPath.parse(responseFlag).read("$.userId");
						responeapicreateuser.put(IUserAccountsService.USERID, userId);
						responeapicreateuser.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
						responeapicreateuser.put(IAPIConstantCodes.APIResponseBaseUrl, getuserroleapiresponse.get("baseurl"));
						responeapicreateuser.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
						responeapicreateuser.put(IAPIConstantCodes.APIResponseJSON, responseFlag);
				 
					} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
						responeapicreateuser.put("InvalidUserNamePassword", response.getEntity(String.class));
						responeapicreateuser.put(IAPIConstantCodes.APIResponseBaseUrl, getuserroleapiresponse.get("baseurl"));
						responeapicreateuser.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
						responeapicreateuser.put(IAPIConstantCodes.APIResponseBaseUrl, getuserroleapiresponse.get("baseurl"));
						responeapicreateuser.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
						responeapicreateuser.put(IAPIConstantCodes.APIResponseBaseUrl, getuserroleapiresponse.get("baseurl"));
						responeapicreateuser.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
						responseFlag = response.getEntity(String.class);
						logger.info("LoginApi Response JSON  :::: " + responseFlag);
						responeapicreateuser.put(IAPIConstantCodes.APIResponseJsonErrorCode, JsonPath.parse(responseFlag).read("$.code").toString());
						responeapicreateuser.put(IAPIConstantCodes.APIResponseJsonErrorDetail, JsonPath.parse(responseFlag).read("$.detail").toString());
						responeapicreateuser.put(IAPIConstantCodes.APIResponseBaseUrl, getuserroleapiresponse.get("baseurl"));
						responeapicreateuser.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
						responeapicreateuser.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
					
					} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
						responeapicreateuser.put(IAPIConstantCodes.APIResponseBaseUrl, getuserroleapiresponse.get("baseurl"));
						responeapicreateuser.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					} else {
						throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				return responeapicreateuser;

			}
			
			
		  else if (datatype.equalsIgnoreCase("invalidData")) {

			try {

				client = Client.create();
				webResource = client.resource(getuserroleapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl)).path(RevbaseAPIURLs.CREATEUSERRURL);
				response = webResource
									.accept(ApiResources.Content_Type)
									.type(ApiResources.Content_Type)
									.header("Authorization", "VBrick " + getuserroleapiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
									.post(ClientResponse.class, createUserJSON(createuserdetailsbean,getuserroleapiresponse));
				responseFlag = response.getEntity(String.class);
				logger.info("User not Created Details :::" + responseFlag);
				logger.info("jsonErrorcode ::: "+ JsonPath.parse(responseFlag).read("$.code"));
				logger.info("JsonErrordetail ::: "+ JsonPath.parse(responseFlag).read("$.detail"));
				if (response.getStatus() != 500) {
					throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				}

				responeapicreateuser.put(IAPIConstantCodes.APIRequestAccessToken, getuserroleapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				String responseHttpCode = Integer.toString(response.getStatus());
				responeapicreateuser.put(IAPIConstantCodes.APIResponseHttpCode, responseHttpCode);
				responeapicreateuser.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				responeapicreateuser.put(IAPIConstantCodes.APIResponseJsonErrorCode, JsonPath.parse(responseFlag).read("$.code").toString());
				responeapicreateuser.put(IAPIConstantCodes.APIResponseJsonErrorDetail, JsonPath.parse(responseFlag).read("$.detail").toString());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return responeapicreateuser;

	}

 
	public JSONObject createUserJSON(HashMap<String, String> createUserDetails, HashMap<String, String> getUserRoleApiResponse )  {

		 JSONObject userDetailsJson = new JSONObject(); 
		
		try {
			if (getUserRoleApiResponse.get("datafileds").equalsIgnoreCase("validData")) {
			 
	 
				userDetailsJson.put("PhoneNumber", createUserDetails.get("phoneno"));
				userDetailsJson.put("firstName", createUserDetails.get("firstname"));
				userDetailsJson.put("email", createUserDetails.get("contactemail"));
				userDetailsJson.put("language", createUserDetails.get("language"));

				userDetailsJson.put("lastName",createUserDetails.get("lastname"));

				userDetailsJson.put("title", createUserDetails.get("title"));
 
				userDetailsJson.put("username", createUserDetails.get("username"));
		 
				JSONArray myArray = new JSONArray();
				myArray.add(getUserRoleApiResponse.get("roleId"));
				userDetailsJson.put("roleIds", myArray);
				
				System.out.println("Create user JSON---->" + userDetailsJson);
				
			} 
			
			else if (getUserRoleApiResponse.get("datafileds").equalsIgnoreCase(ConstantsValue.validDatawithgrp)) {
				userDetailsJson.put("PhoneNumber", createUserDetails.get("phoneno"));
				userDetailsJson.put("firstName", createUserDetails.get("firstname"));
				userDetailsJson.put("email", createUserDetails.get("contactemail"));
				userDetailsJson.put("language", createUserDetails.get("language"));
				userDetailsJson.put("lastName",createUserDetails.get("lastname"));

				userDetailsJson.put("title", createUserDetails.get("title"));
 
				userDetailsJson.put("username", createUserDetails.get("username"));
		 
				JSONArray myArray = new JSONArray();
				myArray.add(getUserRoleApiResponse.get("roleId"));
				userDetailsJson.put("roleIds", myArray);
				
				JSONArray grpArray = new JSONArray();
				grpArray.add(getUserRoleApiResponse.get("groupId"));
				userDetailsJson.put("GroupIds", grpArray);

				System.out.println("Create user JSON---->" + userDetailsJson);
			}

			else if (getUserRoleApiResponse.get("datafileds").equalsIgnoreCase(ConstantsValue.validDatawithgrp1)) {
				userDetailsJson.put("PhoneNumber", createUserDetails.get("phoneno"));
				userDetailsJson.put("firstName", createUserDetails.get("firstname"));
				userDetailsJson.put("email", createUserDetails.get("contactemail"));
				userDetailsJson.put("language", createUserDetails.get("language"));
				userDetailsJson.put("lastName",createUserDetails.get("lastname"));

				userDetailsJson.put("title", createUserDetails.get("title"));
 
				userDetailsJson.put("username", createUserDetails.get("username"));
		 
				JSONArray grpArray = new JSONArray();
				grpArray.add(getUserRoleApiResponse.get("groupId"));
				userDetailsJson.put("GroupIds", grpArray);

				System.out.println("Create user JSON---->" + userDetailsJson);
			}

			else if (getUserRoleApiResponse.get("datafileds").equalsIgnoreCase(ConstantsValue.blankUserName)) {
				userDetailsJson.put("PhoneNumber", createUserDetails.get("phoneno"));
				userDetailsJson.put("firstName", createUserDetails.get("firstname"));
				userDetailsJson.put("email", createUserDetails.get("contactemail"));
				userDetailsJson.put("language", createUserDetails.get("language"));

				userDetailsJson.put("lastName",createUserDetails.get("lastname"));

				userDetailsJson.put("title", createUserDetails.get("title"));
 
				userDetailsJson.put("username", "");
		 
				JSONArray myArray = new JSONArray();
				myArray.add(getUserRoleApiResponse.get("roleId"));
				userDetailsJson.put("roleIds", myArray);

				System.out.println("Create user JSON---->" + userDetailsJson);
			}
			
			else if (getUserRoleApiResponse.get("datafileds").equalsIgnoreCase(ConstantsValue.mandatoryFields)) {
				 
				

				userDetailsJson.put("lastName",createUserDetails.get("lastname"));
				userDetailsJson.put("username", createUserDetails.get("username"));
							
				JSONArray myArray = new JSONArray();
				myArray.add(getUserRoleApiResponse.get("roleId"));
				userDetailsJson.put("roleIds", myArray);

				System.out.println("Create user JSON---->" + userDetailsJson);
			}
			
			else if (getUserRoleApiResponse.get("datafileds").equalsIgnoreCase(ConstantsValue.mandatoryFieldswithoutrole)) {
				 
				

				userDetailsJson.put("lastName",createUserDetails.get("lastname"));
				userDetailsJson.put("username", createUserDetails.get("username"));

				System.out.println("Create user JSON---->" + userDetailsJson);
			}
			
			
			else if (getUserRoleApiResponse.get("datafileds").equalsIgnoreCase(ConstantsValue.mandatoryFieldswithmv)) {
				 
				

				userDetailsJson.put("lastName",createUserDetails.get("lastname"));
				userDetailsJson.put("username", createUserDetails.get("username"));
		 

				System.out.println("Create user JSON---->" + userDetailsJson);
			}


			else if (getUserRoleApiResponse.get("datafileds").equalsIgnoreCase(ConstantsValue.blankLastName)) {
			 
				userDetailsJson.put("PhoneNumber", createUserDetails.get("phoneno"));
				userDetailsJson.put("firstName", createUserDetails.get("firstname"));
				userDetailsJson.put("email", createUserDetails.get("contactemail"));
				userDetailsJson.put("language", createUserDetails.get("language"));

				userDetailsJson.remove("lastName");

				userDetailsJson.put("title", createUserDetails.get("title"));
 
				userDetailsJson.put("username", createUserDetails.get("username"));
		 
				JSONArray myArray = new JSONArray();
				myArray.add(getUserRoleApiResponse.get("roleId"));
				userDetailsJson.put("roleIds", myArray);
				System.out.println("Create user JSON---->" + userDetailsJson);
				
			} 
		}

		catch (JSONException jsonexception) {
			jsonexception.printStackTrace();
		}
		return userDetailsJson;
	}

 
 

}
