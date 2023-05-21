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
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;
/** 
 * @author neslavath
 * @functionName : deleteVideos
 * @Description : DeleteVideos API - will delete mention video ID of the Video into the application 
 * @param loginapiresponse (baseUrl,accessToken,VideoID
 * @return deleteVideoapi response(baseUrl,accessToken, apiresponse200OK)
 */
public class VideoListAPI {
	
	private static Logger logger = Logger.getLogger(VideoListAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> videolistapiresponse = new HashMap<String, String>();
	HashMap<String, String> videolistapiresponse1 = new HashMap<String, String>();
	 
	public HashMap<String, String> videoList(HashMap<String, String> apiresponse ) {
		String responseFlag = null;
		try {
			client = Client.create();
			  MultivaluedMap queryParams = new MultivaluedMapImpl();
			  
			  if(apiresponse.get(IAPIConstantCodes.VIDEOLISTCATEGORY)!=null)
				 {
				  queryParams.add(IAPIConstantCodes.VIDEOLISTCATEGORY, apiresponse.get(IAPIConstantCodes.VIDEOLISTCATEGORY));
				 }
			  
			  if(apiresponse.get(IAPIConstantCodes.GETINACTIVE)!=null)
				 {
				  queryParams.add(IAPIConstantCodes.GETINACTIVE, apiresponse.get(IAPIConstantCodes.GETINACTIVE));
				 }
			  
		
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					       .path(RevbaseAPIURLs.VIDEOLISTURL);
			
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
	 				   .queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
		
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Video List API JSON  :::: " + responseFlag);
				   videolistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   videolistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   videolistapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				   String videoTitle=JsonPath.parse(responseFlag).read("$.videos[*].title").toString();
				   System.out.println("videoTitle: "+videoTitle);
				   if(videoTitle.length()>2)
				   {
					   videolistapiresponse.put(IAPIConstantCodes.APIResponseJSON,videoTitle.substring(2, videoTitle.length()-2));
				   }else{
					   throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				   }
				   
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   videolistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   videolistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   videolistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   videolistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   videolistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   videolistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			}else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   videolistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   videolistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   videolistapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   videolistapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videolistapiresponse;

	}

	}
