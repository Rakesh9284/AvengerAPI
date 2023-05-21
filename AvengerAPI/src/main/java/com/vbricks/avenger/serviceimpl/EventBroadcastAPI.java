package com.vbricks.avenger.serviceimpl;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;

public class EventBroadcastAPI{
	
	private ClientResponse response;
	private Client client;
	private WebResource webResource;
	private static Logger logger = Logger.getLogger(EventBroadcastAPI.class);
    
	
	/*public JSONObject StartEventBroadcastAPI(HashMap<String, String> apiresponse,String eventid) 
	{
		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				     .path(RevbaseAPIURLs.DELETEEVENT+eventid+RevbaseAPIURLs.BROADCAST);
		logger.info("WebResourec BaseURL :::: "+webResource);
		response = webResource
				   .accept(ApiResources.Content_Type)
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .put(ClientResponse.class," ");
		
		
		logger.info("LoginAPI Response HTTP Code :::: " + response.getStatus());
		 responseFlag = response.getEntity(String.class);
	       Object obj=JSONValue.parse(responseFlag);    
	       JSONObject jsonObject = (JSONObject) obj;
	       jsonObject.put("statusInfo", Integer.toString(response.getStatus()));
	       logger.info("Get Response API JSON  :::: " + jsonObject);
	       return jsonObject;
	}
	
	public JSONObject StopEventBroadcastAPI(HashMap<String, String> apiresponse,String eventid) 
	{
		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				     .path(RevbaseAPIURLs.DELETEEVENT+eventid+RevbaseAPIURLs.BROADCAST);
		logger.info("WebResourec BaseURL :::: "+webResource);
		response = webResource
				   .accept(ApiResources.Content_Type)
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .delete(ClientResponse.class);
		
		
		logger.info("LoginAPI Response HTTP Code :::: " + response.getStatus());
		 responseFlag = response.getEntity(String.class);
	       Object obj=JSONValue.parse(responseFlag);    
	       JSONObject jsonObject = (JSONObject) obj;
	       jsonObject.put("statusInfo", Integer.toString(response.getStatus()));
	       logger.info("Get Response API JSON  :::: " + jsonObject);
	       return jsonObject;
	}*/
}
