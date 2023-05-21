package com.vbricks.avenger.serviceimpl;


import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.vbrick.avenger.funUtil.DateTime;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;


public class GetEventListAPI {
	private static Logger logger = Logger.getLogger(GetEventListAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;	 
	HashMap<String, String> eventlistapiresponse = new HashMap<String, String>();
 
	/** 
	 * @author neslavath
	 * @functionName : editVideoRating
	 * @Description : editVideoRating API - will able give ratings on mention video ID of the Video into the application 
	 * @param loginapiresponse (baseUrl,accessToken,VideoID
	 * @return editvideoratingapi  response(baseUrl,accessToken, apiresponse200OK)
	 */
	
	public HashMap<String, String> getEventList(HashMap<String, String> apiresponse,String Date) {


		String responseFlag = null;
 		try {
 			ApiUtils apiutils=new ApiUtils();
 			client=apiutils.getClient();
			  MultivaluedMap queryParams = new MultivaluedMapImpl();
			  
			  //String afterDate = DateTime.addDaysToSystemTime(5)+"T00:00:00+05:30";
			  //String beforeDate = DateTime.addDaysToSystemTime(-10)+"T00:00:00+05:30";
			 HashMap<String, String> dates=apiutils.UTCdatetimeformatEventdays("4", "10");
			  String afterDate= dates.get("afterdate").toString();
			  String beforeDate =dates.get("beforedate").toString();
				System.out.println("After Date :::::::::"+afterDate);
				System.out.println("After Date :::::::::"+beforeDate);
				
				if(Date.equals(IAPIConstantCodes.DATELIMIT)){
				  queryParams.add(IAPIConstantCodes.BEFORE,beforeDate);
				  queryParams.add(IAPIConstantCodes.AFTER,afterDate);
				  
				}
				
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.EVENTLISTURL);
			 logger.info("API Request as URL    :::: "+ webResource);
			 
			 logger.info("API Request as URL with parameter   :::: "+ webResource.queryParams(queryParams));
			response = webResource
					            .queryParams(queryParams)
								.accept(ApiResources.Content_Type)
						        .type(ApiResources.Content_Type)
								.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.get(ClientResponse.class);
			 
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Event List API JSON Response :::: " + responseFlag);
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   eventlistapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				   List<String> eventlist = JsonPath.read(responseFlag, "$.[*].title");
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseJSON, eventlist.toString() );
				   eventlistapiresponse.put("afterdate", afterDate);
				   eventlistapiresponse.put("beforedate", beforeDate);
				    logger.info("statusInfo ::: "+response.getStatusInfo().toString());
				   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
 		
		return eventlistapiresponse;
	}
	
	
	public HashMap<String, String> getEventListID(HashMap<String, String> apiresponse,String Date) {

		String responseFlag = null;
 		try {
 			ApiUtils apiutils=new ApiUtils();
 			client=apiutils.getClient();
			  MultivaluedMap queryParams = new MultivaluedMapImpl();
			  
			  String afterDate = DateTime.addDaysToSystemTime(-5)+"T00:00:00+05:30";
			  String beforeDate = DateTime.addDaysToSystemTime(+10)+"T00:00:00+05:30";
			
				if(Date.equals(IAPIConstantCodes.DATELIMIT)){
				  queryParams.add(IAPIConstantCodes.AFTER, afterDate);
				  queryParams.add(IAPIConstantCodes.BEFORE, beforeDate);
				}
				
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.EVENTLISTURL);
			 logger.info("API Request as URL    :::: "+ webResource);
			 
			 logger.info("API Request as URL with parameter   :::: "+ webResource.queryParams(queryParams));
			response = webResource
					            .queryParams(queryParams)
								.accept(ApiResources.Content_Type)
						        .type(ApiResources.Content_Type)
								.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.get(ClientResponse.class);
			 
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Event List API JSON Response :::: " + responseFlag);
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   eventlistapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				   List<String> eventidlist = JsonPath.read(responseFlag, "$.[*].id");
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseJSON, eventidlist.toString() );
				    logger.info("statusInfo ::: "+response.getStatusInfo().toString());
				   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   eventlistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eventlistapiresponse;
	}
	
}