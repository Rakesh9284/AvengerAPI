package com.vbricks.avenger.serviceimpl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;


public class SubscriptionsAPI {
	private static Logger logger = Logger.getLogger(SubscriptionsAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;	 
	HashMap<String, String> subscribingapiresponse = new HashMap<String, String>();;
 
	/** 
	 * @author neslavath
	 * @param userName 
	 * @functionName : editVideoRating
	 * @Description : editVideoRating API - will able give ratings on mention video ID of the Video into the application 
	 * @param loginapiresponse (baseUrl,accessToken,VideoID
	 * @return editvideoratingapi  response(baseUrl,accessToken, apiresponse200OK)
	 */
	
	public HashMap<String, String> subscribingtoaChannel(HashMap<String, String> apiresponse,org.json.simple.JSONObject subscribingaChannel) {

		String responseFlag = null;
 		try {

			client = Client.create();
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.ADDSUBSCRIPTION);
			 logger.info("API Request as URL    :::: "+ webResource);
			
			response = webResource
								.accept(ApiResources.Content_Type)
								.type(ApiResources.Content_Type)							
								.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.post(ClientResponse.class,subscribingaChannel.toString());			
			
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("subscribing to a Channel API JSON Response :::: " + responseFlag);	   
				   subscribingapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   subscribingapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   subscribingapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   subscribingapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   subscribingapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   subscribingapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   subscribingapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   subscribingapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   subscribingapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   subscribingapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   subscribingapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   subscribingapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subscribingapiresponse;
		 
}	
	public JSONObject subscribingaChannel(String id,String type) {
		JSONObject subscribingJson=new JSONObject();
		subscribingJson.put("id",  new String(id));
		subscribingJson.put("type", new String(type));
		return subscribingJson;
}
	
	public HashMap<String, String> getSubscriptions(HashMap<String, String> apiresponse) {

		String responseFlag = null;
		boolean subscriptionsarenull=false;
		try {
			client = Client.create();
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.GETSUBSCRIPTIONS);
			logger.info("API Request as URL    :::: "+ webResource);
			response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
			subscribingapiresponse =new HashMap<String, String>();
			 if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				logger.info(" Get Subscriptions Response HTTP Code  :::: " + response.getStatus());
				logger.info(" Get Subscriptions JSON  ::::   " + responseFlag);
			
				if(responseFlag.isEmpty()){
					   logger.info("API is returning NO DMES Found");
					   subscriptionsarenull=true;
				   }else{
				
				   List<String> subscriptionTOChannelslist = JsonPath.read(responseFlag, "channels");
				   subscribingapiresponse.put(IAPIConstantCodes.APIResponseJSON, subscriptionTOChannelslist.toString() );
				   
				   List<String> subscriptionTOCategorylist = JsonPath.read(responseFlag, "categories");
				   subscribingapiresponse.put(IAPIConstantCodes.APIResponseJSON1, subscriptionTOCategorylist.toString() );
				   }
			    subscribingapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			    subscribingapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
//			    subscribingapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
//			    subscribingapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
//			    subscribingapiresponse.put(IVideoAccessControlService.title,JsonPath.parse(responseFlag).read("$.title").toString());
//			    subscribingapiresponse.put(IVideoAccessControlService.commenttext,JsonPath.parse(responseFlag).read("$.comments[0].text").toString());
//			    subscribingapiresponse.put(IVideoAccessControlService.firstname,JsonPath.parse(responseFlag).read("$.comments[0].firstName").toString());
//			    subscribingapiresponse.put(IVideoAccessControlService.lastname,JsonPath.parse(responseFlag).read("$.comments[0].lastName").toString());
			 } 
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
		     
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } 
		     else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subscribingapiresponse;

	}

	public HashMap<String, String> getSubscriptionswithoutAuthorization(HashMap<String, String> apiresponse) {

		String responseFlag = null;
		boolean subscriptionsarenull=false;
		try {
			client = Client.create();
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.GETSUBSCRIPTIONS);
			logger.info("API Request as URL    :::: "+ webResource);
			response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					  // .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
			subscribingapiresponse =new HashMap<String, String>();
			 if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				logger.info(" Get Subscriptions Response HTTP Code  :::: " + response.getStatus());
				logger.info(" Get Subscriptions JSON  ::::   " + responseFlag);
			
			    subscribingapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			    subscribingapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
		 } 
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
		     
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 subscribingapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } 
		     else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subscribingapiresponse;

	}

}

