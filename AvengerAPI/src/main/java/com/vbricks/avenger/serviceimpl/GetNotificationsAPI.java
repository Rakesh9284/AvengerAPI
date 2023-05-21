package com.vbricks.avenger.serviceimpl;

import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.http.client.params.HttpClientParams;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.gargoylesoftware.htmlunit.javascript.host.fetch.Request;
import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;
//import javax.ws.rs.core.MultivaluedMapImpl;
/** 
 * @author neslavath
 * @functionName : deleteVideos
 * @Description : DeleteVideos API - will delete mention video ID of the Video into the application 
 * @param loginapiresponse (baseUrl,accessToken,VideoID
 * @return deleteVideoapi response(baseUrl,accessToken, apiresponse200OK)
 */
public class GetNotificationsAPI {
	
	private static Logger logger = Logger.getLogger(GetNotificationsAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> getunreadNotificationsapiresponse = new HashMap<String, String>();
	 
	public HashMap<String, String> getunreadNotifications(HashMap<String, String> apiresponse) {
		String responseFlag = null;
		try {

			client = Client.create();
			
			 MultivaluedMap queryParams = new MultivaluedMapImpl();
		     queryParams.add("unread","true");
		     
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					.path(RevbaseAPIURLs.GETNOTIFICATIONS);
				
			logger.info("WebResourec BaseURL :::: "+webResource);
			//Thread.sleep(100000);
			response = webResource
					.queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
	
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Delete Video API JSON  :::: " + responseFlag);

				   String a[]=responseFlag.replaceAll("[\"']*","").split(",");			   
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				   
				   List<String> notificationTypelist = JsonPath.read(responseFlag, "$.notifications[*].notificationType");
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseJSON, notificationTypelist.toString());
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				  // deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.supplementalfilecode,b[1]);
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getunreadNotificationsapiresponse;

	}

	public HashMap<String, String> getAllNotifications(HashMap<String, String> apiresponse) {
		String responseFlag = null;
		try {

			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					.path(RevbaseAPIURLs.GETNOTIFICATIONS);
				
			logger.info("WebResourec BaseURL :::: "+webResource);
			//Thread.sleep(100000);
			response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
	
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Delete Video API JSON  :::: " + responseFlag);

				   String a[]=responseFlag.replaceAll("[\"']*","").split(",");			   
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				   
				   List<String> notificationTypelist = JsonPath.read(responseFlag, "$.notifications[*].notificationType");
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseJSON, notificationTypelist.toString());
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				  // deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.supplementalfilecode,b[1]);
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getunreadNotificationsapiresponse;

	}
	
	public HashMap<String, String> getmultipleunreadNotifications(HashMap<String, String> apiresponse) {
		String responseFlag = null;
		try {

			client = Client.create();
			
			 MultivaluedMap queryParams = new MultivaluedMapImpl();
		     queryParams.add(IAPIConstantCodes.Unread,IAPIConstantCodes.TRUE);
		     
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					.path(RevbaseAPIURLs.GETNOTIFICATIONS);
				
			logger.info("WebResourec BaseURL :::: "+webResource);
			//Thread.sleep(100000);
			response = webResource
					   .queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
	
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Delete Video API JSON  :::: " + responseFlag);

				   String a[]=responseFlag.replaceAll("[\"']*","").split(",");	
				   
				   String a1[]=a[1].split(":");
				   System.out.println(a1[2]);
				   
				   String b1[]=a[7].split(":");
				   System.out.println(b1[1]);
				   
				   String c1[]=a[13].split(":");
				   System.out.println(c1[1]);
				   
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				   getunreadNotificationsapiresponse.put("notificationId", a1[2]);
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				   
				   List<String> notificationTypelist = JsonPath.read(responseFlag, "$.notifications[*].notificationType");
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseJSON, notificationTypelist.toString());
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				  // deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.supplementalfilecode,b[1]);
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getunreadNotificationsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getunreadNotificationsapiresponse;

	}

	

	
}