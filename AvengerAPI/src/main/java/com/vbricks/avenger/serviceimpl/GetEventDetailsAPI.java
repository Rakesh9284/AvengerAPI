	package com.vbricks.avenger.serviceimpl;


import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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


public class GetEventDetailsAPI {
	private static Logger logger = Logger.getLogger(GetEventDetailsAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;	 
	HashMap<String, String> eventdetailapiresponse = new HashMap<String, String>();
 
	/** 
	 * @author neslavath
	 * @functionName : editVideoRating
	 * @Description : editVideoRating API - will able give ratings on mention video ID of the Video into the application 
	 * @param loginapiresponse (baseUrl,accessToken,VideoID
	 * @return editvideoratingapi  response(baseUrl,accessToken, apiresponse200OK)
	 */
	
	
	
	public JSONObject eventDetail_jsonbject(HashMap<String, String> apiresponse) {

		String responseFlag = null;
		client = Client.create();
		webResource = client
							.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
							.path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID));
		 logger.info("API Request as URL    :::: "+ webResource);
		 
		    		response = webResource
				            .accept(ApiResources.Content_Type)
					        .type(ApiResources.Content_Type)
							.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
							.get(ClientResponse.class);
		 
		
		    responseFlag = response.getEntity(String.class);
		    Object obj=JSONValue.parse(responseFlag);  
		    JSONObject eventdetailapiresponse=(JSONObject) obj;
		    eventdetailapiresponse.put("statusInfo", response.getStatus()); 
	     	JSONObject embeddedcontent=(JSONObject) eventdetailapiresponse.get("embeddedContent");
	     	eventdetailapiresponse.put("embeddedContent", embeddedcontent);
		    JSONArray contentlink_EC=(JSONArray)embeddedcontent.get("contentLinks");
		    JSONObject link=(JSONObject)contentlink_EC.get(0);
		    eventdetailapiresponse.put("EmbeddedContentName", link.get("name").toString());
		    System.out.println("GET Webcast API response "+eventdetailapiresponse);
			return eventdetailapiresponse;
		    
	}
	
	
	
	public HashMap<String, String> eventDetail(HashMap<String, String> apiresponse) {

		String responseFlag = null;
 		try {

			client = Client.create();
			 
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID));
			 logger.info("API Request as URL    :::: "+ webResource);
			 
			    		response = webResource
					            .accept(ApiResources.Content_Type)
						        .type(ApiResources.Content_Type)
								.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.get(ClientResponse.class);
			 
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Event Detai API JSON Response :::: " + responseFlag);
				   eventdetailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   eventdetailapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				   eventdetailapiresponse.put(IAPIConstantCodes.APITITLE, JsonPath.parse(responseFlag).read("$.title").toString());
				   eventdetailapiresponse.put(IUserAccountsService.EVENTID,  JsonPath.parse(responseFlag).read("$.eventId").toString());
				   eventdetailapiresponse.put(IUserAccountsService.estimatedattendees, JsonPath.parse(responseFlag).read("$.estimatedAttendees").toString());
				   eventdetailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   eventdetailapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
				    logger.info("statusInfo ::: "+response.getStatusInfo().toString());
				   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   eventdetailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   eventdetailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   eventdetailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   eventdetailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   eventdetailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   eventdetailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   eventdetailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   eventdetailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   eventdetailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   eventdetailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eventdetailapiresponse;
	}
}