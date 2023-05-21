package com.vbricks.avenger.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;
/** 
 * @author neslavath
 * @functionName : deleteVideos
 * @Description : DeleteVideos API - will delete mention video ID of the Video into the application 
 * @param loginapiresponse (baseUrl,accessToken,VideoID
 * @return deleteVideoapi response(baseUrl,accessToken, apiresponse200OK)
 */
public class SearchSuggestionsAPI {
	
	private static Logger logger = Logger.getLogger(SearchSuggestionsAPI.class);
	public Client client;
	public ClientResponse response;
	ApiUtils apiUtils=new ApiUtils();
	WebResource webResource;
	HashMap<String, String> searchSuggestionsapiresponse = new HashMap<String, String>();
 
    public HashMap<String, String> SearchSuggestionsAPI (HashMap<String, String> apiresponse) {
		String responseFlag = null;
		try {
			
			
			client = Client.create();
			 MultivaluedMap queryParams = new MultivaluedMapImpl();
		     queryParams.add(IAPIConstantCodes.Q,apiresponse.get("q"));
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					.path(RevbaseAPIURLs.VIDEOSEARCHSUGGESTIONS);

			logger.info("WebResourec BaseURL :::: "+webResource);
	 		
			response = webResource
					   .queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))					   
					   .post(ClientResponse.class,IAPIConstantCodes.Q.getBytes());
	
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Delete Video API JSON  :::: " + responseFlag);
				   String videolength=JsonPath.parse(responseFlag).read("$..videos.length()").toString();

				   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				   searchSuggestionsapiresponse.put("apivideolist", responseFlag);
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchSuggestionsapiresponse;

	}
    
   /* public JSONObject SearchSuggestionsAPIwithoutauthorizationjsonobject(HashMap<String, String> apiresponse) {
    	
    	String responseFlag = null;
    	client = Client.create();
		 MultivaluedMap queryParams = new MultivaluedMapImpl();
	     queryParams.add(IAPIConstantCodes.Q,apiresponse.get("q"));
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				.path(RevbaseAPIURLs.VIDEOSEARCHSUGGESTIONS);

		logger.info("WebResourec BaseURL :::: "+webResource);
		
		response = webResource
				   .queryParams(queryParams)
				   .accept(ApiResources.Content_Type)
				   .type(ApiResources.Content_Type)
				 //  .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))					   
				   .post(ClientResponse.class," ");
    	
		responseFlag = response.getEntity(String.class);
	    
		Object obj=JSONValue.parse(responseFlag);
		JSONObject searchSuggestionsapiresponse= (JSONObject) obj;
		searchSuggestionsapiresponse.put("apivideolist", responseFlag);
		
		searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode,Integer.toString(response.getStatus()));
		
		System.out.println("RESPONSE :::::::::::::::"+searchSuggestionsapiresponse);
		return searchSuggestionsapiresponse;
		
		
		
    }*/

    

public HashMap<String, String> SearchSuggestionsAPIwithoutauthorization (HashMap<String, String> apiresponse) {
	String responseFlag = null;
	try {
		
		
		client = Client.create();
		 MultivaluedMap queryParams = new MultivaluedMapImpl();
	     queryParams.add(IAPIConstantCodes.Q,apiresponse.get("q"));
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				.path(RevbaseAPIURLs.VIDEOSEARCHSUGGESTIONS);

		logger.info("WebResourec BaseURL :::: "+webResource);
 		
		response = webResource
				   .queryParams(queryParams)
				   .accept(ApiResources.Content_Type)
				   .type(ApiResources.Content_Type)
				 //  .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))					   
				   .post(ClientResponse.class," ");


		if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
		    responseFlag = response.getEntity(String.class);
			   logger.info("Delete Video API JSON  :::: " + responseFlag);
			   String videolength=JsonPath.parse(responseFlag).read("$..videos.length()").toString();

			   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			   searchSuggestionsapiresponse.put("apivideolist", responseFlag);
		   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
			   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
		   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
			   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
		   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
			   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
		   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
			   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
		   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
			   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			   searchSuggestionsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
		   } else {
		    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		   }
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return searchSuggestionsapiresponse;

}
}
	 