package com.vbricks.avenger.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import javax.ws.rs.core.MultivaluedMap;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class GetUserProfileImageAPI {
	
	private ClientResponse response;
	private Client client;
	private WebResource webResource;
	private static Logger logger = Logger.getLogger(GetUserProfileImageAPI.class);
	String charset = "UTF-8";
	HashMap<String, String> getUserProfileapiresponse=new HashMap<String, String>();;
 
	
	public HashMap<String, String>  GetUserProfile(HashMap<String, String> apiresponse) throws Exception 
	{
		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				 .path(apiresponse.get(IAPIConstantCodes.profileImageUri));
		logger.info("WebResourec BaseURL :::: "+webResource);
		response = webResource
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .get(ClientResponse.class);
		
		logger.info("@@@@@@@@@@@ STATUS :::: " + response.getStatus());
		Thread.sleep(10000);
		 
		if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			responseFlag = response.getEntity(String.class);
			//logger.info("LoginApi Response JSON  :::: " + responseFlag);
			getUserProfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			getUserProfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			getUserProfileapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
		}
		    else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
		    	getUserProfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	getUserProfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					   
		      } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
		    	  getUserProfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	  getUserProfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				  getUserProfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				  getUserProfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
					getUserProfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					getUserProfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   }  
	        
	        
	        
	        logger.info("#########GET User Image Profile  RESPONSE CONTAINS::::::::::::"+getUserProfileapiresponse);
	        return getUserProfileapiresponse;
	}







}
	        	
		 
	  	 
		
	       
	
