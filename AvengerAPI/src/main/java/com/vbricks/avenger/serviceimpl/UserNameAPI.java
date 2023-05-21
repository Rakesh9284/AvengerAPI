package com.vbricks.avenger.serviceimpl;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;

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
public class UserNameAPI {
	
	private static Logger logger = Logger.getLogger(UserNameAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> usernameapiresponse = new HashMap<String, String>();
	 
	public HashMap<String, String> getUserName(HashMap<String, String> apiresponse ) {
		String responseFlag = null;
		try {
			client = Client.create();
			 MultivaluedMap queryParams = new MultivaluedMapImpl();
		     queryParams.add(IAPIConstantCodes.type,IAPIConstantCodes.APIUSERNAME);
		
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.GETUSERDETAILSURL+apiresponse.get(IAPIConstantCodes.APIResponseUserName1));
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
				   usernameapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   usernameapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   usernameapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				   usernameapiresponse.put(IAPIConstantCodes.APIResponseJSON, responseFlag );
				   List<String> roles = JsonPath.read(responseFlag, "$.roles[*].name");
				   usernameapiresponse.put(IAPIConstantCodes.APIResponseJSON1, roles.toString() );
				   
				   List<String> groups = JsonPath.read(responseFlag, "$.groups[*].name");
				   usernameapiresponse.put(IAPIConstantCodes.APIResponseJSON2, groups.toString() );
				   
				   List<String> teams = JsonPath.read(responseFlag, "$.channels[*].name");
				   usernameapiresponse.put(IAPIConstantCodes.APIResponseJSON3, teams.toString() );
				   usernameapiresponse.put(IUserAccountsService.USERID, JsonPath.parse(responseFlag).read("$.userId").toString());
				   usernameapiresponse.put(IUserAccountsService.FIRSTNAME, JsonPath.parse(responseFlag).read("$.firstname").toString());
				   usernameapiresponse.put(IUserAccountsService.LASTNAME, JsonPath.parse(responseFlag).read("$.lastname").toString());
				   usernameapiresponse.put(IUserAccountsService.USERNAME, JsonPath.parse(responseFlag).read("$.username").toString());
				   usernameapiresponse.put(IUserAccountsService.CONTACTEMAIL, JsonPath.parse(responseFlag).read("$.email").toString());
				   if(JsonPath.parse(responseFlag).read("$.profileImageUri")!=null)
				   usernameapiresponse.put(IAPIConstantCodes.profileImageUri,JsonPath.parse(responseFlag).read("$.profileImageUri").toString());
				  
				  				  
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   usernameapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   usernameapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   usernameapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   usernameapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   usernameapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   usernameapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   usernameapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   usernameapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   usernameapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   usernameapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usernameapiresponse;

	}
	

}
