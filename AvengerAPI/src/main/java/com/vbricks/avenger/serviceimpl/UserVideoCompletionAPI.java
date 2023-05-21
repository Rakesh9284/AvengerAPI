package com.vbricks.avenger.serviceimpl;


import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;

public class UserVideoCompletionAPI {

	
	private static Logger logger = Logger.getLogger(UserVideoCompletionAPI.class);
	 
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	
	HashMap<String, String> userVideoCompletionApiresponse = new HashMap<String, String>();
	
	public HashMap<String, String> videoCompletion(HashMap<String, String> apiresponse) {

		String responseFlag = null;

		try {

			client = Client.create();
			webResource =client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					    .path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) +RevbaseAPIURLs.USER+apiresponse.get(IAPIConstantCodes.APIResponseUSERID)+ RevbaseAPIURLs.STATUS );
	        logger.info("BaseUrl is   :::: "+webResource);		
			response = webResource
					    .accept(ApiResources.Content_Type)
					    .type(ApiResources.Content_Type)
					    .header("Authorization", "VBrick " + apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					    .get(ClientResponse.class);
			
		    responseFlag = response.getEntity(String.class);
		    logger.info("UserVideoCompletion Response JSON  :::: " + responseFlag);
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200)
			{	   
			    
			        userVideoCompletionApiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			    	userVideoCompletionApiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			        userVideoCompletionApiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
					logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
					logger.info("statusInfo ::: "+response.getStatusInfo().toString());
					userVideoCompletionApiresponse.put("userId", JsonPath.parse(responseFlag).read("$.userId").toString());
					userVideoCompletionApiresponse.put("videoId", JsonPath.parse(responseFlag).read("$.videoId").toString());
					userVideoCompletionApiresponse.put("completed", JsonPath.parse(responseFlag).read("$.completed").toString());
					userVideoCompletionApiresponse.put("whenCompleted", JsonPath.parse(responseFlag).read("$.whenCompleted").toString());
 					
				}
			
		    else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) 
		    {
			 //  userVideoCompletionApiresponse.put("InvalidUserNamePassword", response.getEntity(String.class));
		       userVideoCompletionApiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			   userVideoCompletionApiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
		    } 
		    else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) 
		    {
			   userVideoCompletionApiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			   userVideoCompletionApiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
		    } 
		    else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) 
		    {
			    userVideoCompletionApiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			    userVideoCompletionApiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			}
		    else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500)
		    {
			   userVideoCompletionApiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			   userVideoCompletionApiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
		    } 
		    else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503)
		    {
			   userVideoCompletionApiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			   userVideoCompletionApiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} 
		    else
		    {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		    }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVideoCompletionApiresponse;

	}
}
