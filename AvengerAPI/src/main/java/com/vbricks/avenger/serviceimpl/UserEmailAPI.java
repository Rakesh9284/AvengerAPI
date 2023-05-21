package com.vbricks.avenger.serviceimpl;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;
/** 
 * @author neslavath
 * @functionName : userName
 * @Description : userName API - will delete mention video ID of the Video into the application 
 * @param loginapiresponse (baseUrl,accessToken,VideoID
 * @return userNameapi response(baseUrl,accessToken, apiresponse200OK)
 */
public class UserEmailAPI {
	
	private static Logger logger = Logger.getLogger(UserEmailAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> useremailapiresponse = new HashMap<String, String>();
	 

	public JSONArray getUserEmailUpdated(String baseurl,String value,String accesstoken)
	{
		System.out.println("base url "+baseurl);
        System.out.println("value is"+value);
        System.out.println("access token is"+accesstoken);
        String responseFlag = null;
        
		client = Client.create();
		 MultivaluedMap queryParams = new MultivaluedMapImpl();
	     queryParams.add(IAPIConstantCodes.type,IAPIConstantCodes.APIUSEREMAIL);
	
		webResource = client.resource(baseurl)
				     .path(RevbaseAPIURLs.GETUSERDETAILSURL+value);
		logger.info("WebResoure BaseURL :::: "+webResource);
		response = webResource
				   .queryParams(queryParams)
				   .accept(ApiResources.Content_Type)
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+accesstoken)
				   .get(ClientResponse.class);
		
		logger.info("Get user detail API Response HTTP Code :::: " + response.getStatus() );
        responseFlag = response.getEntity(String.class);
        
        Object obj=JSONValue.parse(responseFlag);    
        JSONArray jsonArray = (JSONArray) obj;  
return     jsonArray; 
		
	}
	
	
	
	
	
	public HashMap<String, String> getUserEmail(HashMap<String, String> apiresponse ) {
		String responseFlag = null;
		try {
			client = Client.create();
			 MultivaluedMap queryParams = new MultivaluedMapImpl();
		     queryParams.add(IAPIConstantCodes.type,IAPIConstantCodes.APIUSEREMAIL);
		
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.GETUSERDETAILSURL+apiresponse.get(IAPIConstantCodes.APIUSEREMAIL1));
			logger.info("WebResoure BaseURL :::: "+webResource);
	 		response = webResource
	 				   .queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
		
	 		if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("User Name API JSON  :::: " + responseFlag);
				   useremailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   useremailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   useremailapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				   useremailapiresponse.put(IAPIConstantCodes.APIResponseJSON, responseFlag ); 
				   responseFlag= responseFlag.substring(1, responseFlag.length()-1);
				   List<String> roles = JsonPath.read(responseFlag, "$.roles[*].name");
				   useremailapiresponse.put(IAPIConstantCodes.APIResponseJSON1, roles.toString() );
				   
				   List<String> groups = JsonPath.read(responseFlag, "$.groups[*].name");
				   useremailapiresponse.put(IAPIConstantCodes.APIResponseJSON2, groups.toString() );
				   
				   List<String> teams = JsonPath.read(responseFlag, "$.channels[*].name");
				   useremailapiresponse.put(IAPIConstantCodes.APIResponseJSON3, teams.toString() );
				   
				   useremailapiresponse.put(IUserAccountsService.USERID, JsonPath.parse(responseFlag).read("$.userId").toString());
				   useremailapiresponse.put(IUserAccountsService.FIRSTNAME, JsonPath.parse(responseFlag).read("$.firstname").toString());
				   useremailapiresponse.put(IUserAccountsService.LASTNAME, JsonPath.parse(responseFlag).read("$.lastname").toString());
				   useremailapiresponse.put(IUserAccountsService.USERNAME, JsonPath.parse(responseFlag).read("$.username").toString());
				   useremailapiresponse.put(IUserAccountsService.CONTACTEMAIL, JsonPath.parse(responseFlag).read("$.email").toString());
				   if(JsonPath.parse(responseFlag).read("$.profileImageUri")!=null)
				   useremailapiresponse.put(IAPIConstantCodes.profileImageUri,JsonPath.parse(responseFlag).read("$.profileImageUri").toString());
				    
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   useremailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   useremailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   useremailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   useremailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   useremailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   useremailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   useremailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   useremailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   useremailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   useremailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return useremailapiresponse;

	}
	

}
