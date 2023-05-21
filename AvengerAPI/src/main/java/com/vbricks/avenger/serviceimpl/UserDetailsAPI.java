package com.vbricks.avenger.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;
/** 
 * @author neslavath
 * @functionName : userDetails
 * @Description : userDetails API - will delete mention video ID of the Video into the application 
 * @param loginapiresponse (baseUrl,accessToken,VideoID
 * @return userDetailsapi response(baseUrl,accessToken, apiresponse200OK)
 */
public class UserDetailsAPI {
	
	private static Logger logger = Logger.getLogger(UserDetailsAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> userdetailsapiresponse = new HashMap<String, String>();
	 
public JSONObject getUserDetailsUpdated(String baseurl,String value,String accesstoken) {
        
        System.out.println("base url "+baseurl);
        System.out.println("value is"+value);
        System.out.println("access token is"+accesstoken);
        String responseFlag = null;
        
            client = Client.create();
            webResource = client.resource(baseurl)
                         .path(RevbaseAPIURLs.GETUSERDETAILSURL+value);
            logger.info("WebResoure BaseURL :::: "+webResource);
             response = webResource
                       .accept(ApiResources.Content_Type)
                       .type(ApiResources.Content_Type)
                       .header(ApiResources.Authorization, ApiResources.VBrick+ accesstoken)
                       .get(ClientResponse.class);
             
             logger.info("Get user detail API Response HTTP Code :::: " + response.getStatus() );
                responseFlag = response.getEntity(String.class);
                
                Object obj=JSONValue.parse(responseFlag);    
                JSONObject jsonObject = (JSONObject) obj;  
        return     jsonObject;    
        }	
	
	public HashMap<String, String> getUserDetails(HashMap<String, String> apiresponse ) {
		String responseFlag = null;
		try {
			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.GETUSERDETAILSURL+apiresponse.get(IAPIConstantCodes.APIResponseUSERID1));
			logger.info("WebResoure BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
		
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("User Detail API JSON  :::: " + responseFlag);
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseJSON, responseFlag );
				   List<String> roles = JsonPath.read(responseFlag, "$.roles[*].name");
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseJSON1, roles.toString() );
				   List<String> groups = JsonPath.read(responseFlag, "$.groups[*].name");
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseJSON2, groups.toString() );
				   List<String> teams = JsonPath.read(responseFlag, "$.channels[*].name");
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseJSON3, teams.toString() );
				   userdetailsapiresponse.put(IUserAccountsService.USERID, JsonPath.parse(responseFlag).read("$.userId").toString());
				   userdetailsapiresponse.put(IUserAccountsService.FIRSTNAME, JsonPath.parse(responseFlag).read("$.firstname").toString());
				   userdetailsapiresponse.put(IUserAccountsService.LASTNAME, JsonPath.parse(responseFlag).read("$.lastname").toString());
				   userdetailsapiresponse.put(IUserAccountsService.USERNAME, JsonPath.parse(responseFlag).read("$.username").toString());
				   userdetailsapiresponse.put(IUserAccountsService.CONTACTEMAIL, JsonPath.parse(responseFlag).read("$.email").toString());
				   userdetailsapiresponse.put(IAPIConstantCodes.profileImageUri,JsonPath.parse(responseFlag).read("$.profileImageUri").toString());
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userdetailsapiresponse;

	}
	
	public HashMap<String, String> CheckForUploadRightsInUserAPI(HashMap<String, String> apiresponse ) {
		String responseFlag = null;
		try {
			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.GETUSERDETAILSURL+apiresponse.get(IAPIConstantCodes.APIResponseUSERID));
			logger.info("WebResoure BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
	 		
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("User Detail API JSON  :::: " + responseFlag);
				  /*JSONObject jsonobject= JSONObject(responseFlag);
				  ArrayList<String> Permissions=(ArrayList<String>) jsonobject.get("permissions");
				  userdetailsapiresponse.put("canUpload", Permissions.get(0).toString());
				  userdetailsapiresponse.put("canCreateEvents", Permissions.get(1).toString());*/
				   
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseJSON, responseFlag );
				   List<String> roles = JsonPath.read(responseFlag, "$.roles[*].name");
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseJSON1, roles.toString() );
				   userdetailsapiresponse.put(IUserAccountsService.USERID, JsonPath.parse(responseFlag).read("$.userId").toString());
				   userdetailsapiresponse.put(IUserAccountsService.FIRSTNAME, JsonPath.parse(responseFlag).read("$.firstname").toString());
				   userdetailsapiresponse.put(IUserAccountsService.LASTNAME, JsonPath.parse(responseFlag).read("$.lastname").toString());
				   userdetailsapiresponse.put(IUserAccountsService.USERNAME, JsonPath.parse(responseFlag).read("$.username").toString());
				   userdetailsapiresponse.put(IAPIConstantCodes.CANUPLOAD, JsonPath.parse(responseFlag).read("$.canUpload").toString());
				   userdetailsapiresponse.put(IAPIConstantCodes.CANCREATEEVENTS, JsonPath.parse(responseFlag).read("$.canCreateEvents").toString());
				   userdetailsapiresponse.put(IUserAccountsService.CONTACTEMAIL, JsonPath.parse(responseFlag).read("$.email").toString());
				   List<String> groups = JsonPath.read(responseFlag, "$.groups[*].name");
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseJSON2, groups.toString() );
				   List<String> teams = JsonPath.read(responseFlag, "$.channels[*].name");
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseJSON3, teams.toString() );
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   userdetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userdetailsapiresponse;

	}

	
	

}
