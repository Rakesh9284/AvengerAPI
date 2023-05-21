package com.vbricks.avenger.serviceimpl;
 

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;

public class DownloadVideoAPI {

	
	private static Logger logger = Logger.getLogger(DownloadVideoAPI.class);
	 
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	
	HashMap<String, String> downloadVideoaApiresponse = new HashMap<String, String>();
	

	/** 
	 * @author neslavath
	 * @functionName : editVideoRating
	 * @Description : editVideoRating API - will able give ratings on mention video ID of the Video into the application 
	 * @param loginapiresponse (baseUrl,accessToken,VideoID
	 * @return editvideoratingapi  response(baseUrl,accessToken, apiresponse200OK)
	 */
	
	public HashMap<String, String> getVideoDownload(HashMap<String, String> apiresponse) {

		String responseFlag = null;

		try {

			  client = Client.create();
			  webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					        .path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.VIDEODOWNLOAD);
	          logger.info("BaseUrl is   :::: "+webResource);		
			  response = webResource
					     .accept(ApiResources.Content_Type)
					     .type(ApiResources.Content_Type)
					     .header("Authorization", "VBrick " + apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					     .get(ClientResponse.class);
			  if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			     responseFlag = response.getEntity(String.class);	   
			     logger.info("Video Download Response JSON  :::: " + responseFlag);
			     downloadVideoaApiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				 downloadVideoaApiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
				 logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
				 logger.info("statusInfo ::: "+response.getStatusInfo().toString());
 			    }
			  else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) 
			  {
				   downloadVideoaApiresponse.put("InvalidUserNamePassword", response.getEntity(String.class));
				   downloadVideoaApiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   downloadVideoaApiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } 
			  else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) 
			  {
				   downloadVideoaApiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   downloadVideoaApiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } 
			  else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) 
			  {
				   downloadVideoaApiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   downloadVideoaApiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
			  else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) 
			  {
				   downloadVideoaApiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   downloadVideoaApiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } 
			  else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) 
			  {
				   downloadVideoaApiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   downloadVideoaApiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } 
			  else 
			  {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			  }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return downloadVideoaApiresponse;

	}
}
