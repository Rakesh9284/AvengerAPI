package com.vbricks.avenger.serviceimpl;


import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;


public class DMEdeviceslistAPI {
	private static Logger logger = Logger.getLogger(DMEdeviceslistAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;	 
	HashMap<String, String> DMEdeviceslistapiresponse = new HashMap<String, String>();
 
	/** 
	 * @author neslavath
	 * @functionName : getdmedevices
	 * @Description : dmedevices API - will able give ratings on mention video ID of the Video into the application 
	 * @param loginapiresponse (baseUrl,accessToken,VideoID
	 * @return dmedevicesapi  response(baseUrl,accessToken, apiresponse200OK,responseJSON)
	 */
	
	public HashMap<String, String> getdmedevices(HashMap<String, String> apiresponse) {

		String responseFlag = null;
		boolean dmeslistnull=false;
 		try {

			client = Client.create();
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.DMEDEVICESURL);
			 logger.info("API Request as URL    :::: "+ webResource);
			response = webResource
								.accept(ApiResources.Content_Type)
								.type(ApiResources.Content_Type)
								.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.get(ClientResponse.class);
			 
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("DME devices API JSON Response :::: " + responseFlag);
				   if(responseFlag.isEmpty()){
					   logger.info("API is returning NO DMES Found");
					   dmeslistnull=true;
				   }else{
				
				   List<String> dmeslist = JsonPath.read(responseFlag, "$.devices[*].name");
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseJSON, dmeslist.toString() );
				   }
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseDMESListISNull, String.valueOf(dmeslistnull));
				   logger.info("statusInfo ::: "+response.getStatusInfo().toString());
				   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
								   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
  
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DMEdeviceslistapiresponse;
	}
	
	public HashMap<String, String> getdmedevicesID(HashMap<String, String> apiresponse) {

		String responseFlag = null;
		boolean dmeslistnull=false;
 		try {

			client = Client.create();
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.DMEDEVICESURL);
			 logger.info("API Request as URL    :::: "+ webResource);
			response = webResource
								.accept(ApiResources.Content_Type)
								.type(ApiResources.Content_Type)
								.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.get(ClientResponse.class);
			 
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("DME devices API JSON Response :::: " + responseFlag);
				   if(responseFlag.isEmpty()){
					   logger.info("API is returning NO DMES Found");
					   dmeslistnull=true;
				   }else{
				
				   List<String> dmeslist = JsonPath.read(responseFlag, "$.devices[*].id");
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseJSON, dmeslist.toString() );
				   }
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseDMESListISNull, String.valueOf(dmeslistnull));
				   logger.info("statusInfo ::: "+response.getStatusInfo().toString());
				   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
								   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
  
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   DMEdeviceslistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DMEdeviceslistapiresponse;
	}
	
	
}