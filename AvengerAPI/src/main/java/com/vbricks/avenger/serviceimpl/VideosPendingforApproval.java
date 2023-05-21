package com.vbricks.avenger.serviceimpl;


import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;


public class VideosPendingforApproval {
	private static Logger logger = Logger.getLogger(VideosPendingforApproval.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;	 
	HashMap<String, String> pendingvideoslistapiresponse = new HashMap<String, String>();
 
	/** 
	 * @author neslavath
	 * @functionName : editVideoRating
	 * @Description : editVideoRating API - will able give ratings on mention video ID of the Video into the application 
	 * @param loginapiresponse (baseUrl,accessToken,VideoID
	 * @return editvideoratingapi  response(baseUrl,accessToken, apiresponse200OK)
	 */
	
	public HashMap<String, String> getVideosPendingList(HashMap<String, String> apiresponse) {

	String responseFlag = null;
 		try {

			client = Client.create();
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.APPROVALPENDING);
			 logger.info("API Request as URL    :::: "+ webResource);
                            response = webResource
					            .accept(ApiResources.Content_Type)
						        .type(ApiResources.Content_Type)
								.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.get(ClientResponse.class);
			 
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Videos Pending for Approval API JSON Response :::: " + responseFlag);
				   pendingvideoslistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   pendingvideoslistapiresponse.put("jsonteamResponse", responseFlag);
				   
				   List<String> videopendinglist =JsonPath.parse(responseFlag).read("$.*.title");
				   pendingvideoslistapiresponse.put("pendingvideonames", videopendinglist.toString());
				   pendingvideoslistapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				   pendingvideoslistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   pendingvideoslistapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
				   logger.info("statusInfo ::: "+response.getStatusInfo().toString());
				   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   pendingvideoslistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   pendingvideoslistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   pendingvideoslistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   pendingvideoslistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   pendingvideoslistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   pendingvideoslistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   pendingvideoslistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   pendingvideoslistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   pendingvideoslistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   pendingvideoslistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pendingvideoslistapiresponse;
	}
}