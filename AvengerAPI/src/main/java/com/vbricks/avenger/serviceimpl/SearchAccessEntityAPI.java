package com.vbricks.avenger.serviceimpl;


import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;

import net.sf.json.JSON;


public class SearchAccessEntityAPI {
	private static Logger logger = Logger.getLogger(SearchAccessEntityAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;	 
	HashMap<String, String> searchAccessapiresponse = new HashMap<String, String>();
	HashMap<String, String> searchAccessapiresponse1 = new HashMap<String, String>();
	/** 
	 * @author neslavath
	 * @functionName : searchAccessEntity
	 * @Description : searchAccessEntity API - will able give ratings on mention video ID of the Video into the application 
	 * @param loginapiresponse (baseUrl,accessToken,VideoID
	 * @return searchAccessEntityapi  response(baseUrl,accessToken, apiresponse200OK)
	 */
	
	
	public JSONObject searchAccessEntitywithoutqueryparams(HashMap<String, String> apiresponse) {
		String responseFlag = null;
		client = Client.create();
		
		webResource = client
				.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				.path(RevbaseAPIURLs.SEARCHACCESSENTITYURL+RevbaseAPIURLs.ACCESSSENTITYURL);
		logger.info("API Request as URL    :::: "+ webResource);
		 response = webResource
							.accept(ApiResources.Content_Type)
					        .type(ApiResources.Content_Type)
							.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
							.get(ClientResponse.class);
		 
		  responseFlag = response.getEntity(String.class);
		 Object obj=JSONValue.parse(responseFlag);
		 JSONObject searchAccessapiresponse=(JSONObject)obj;
		  
		 if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200)
		 { 
		   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
		   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
		 }
		 
		 logger.info("Search Access Entity Response Body::::"+searchAccessapiresponse);
		return searchAccessapiresponse;
		
	}
	
		public HashMap<String, String> searchAccessEntity(HashMap<String, String> apiresponse,String parameters,String qParams,String count) {

		String responseFlag = null;
 		try {

			client = Client.create();
			
			webResource = client
					.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					.path(RevbaseAPIURLs.SEARCHACCESSENTITYURL+RevbaseAPIURLs.ACCESSSENTITYURL);
			logger.info("API Request as URL    :::: "+ webResource);
			
			
			 if (parameters=="Yes")
			 {
			  MultivaluedMap queryParams = new MultivaluedMapImpl();
			  
				if(apiresponse.get(IAPIConstantCodes.QUERY).equals(IAPIConstantCodes.group)){
					
					 queryParams.add(IAPIConstantCodes.TYPE, IAPIConstantCodes.group);
					 
					 if (qParams=="Group"){
						 
				  queryParams.add(IAPIConstantCodes.Q, apiresponse.get(IAPIConstantCodes.groupname));
				  
					 }
					 
					 if (count == "Yes"){
						 
						 queryParams.add(IAPIConstantCodes.COUNT, apiresponse.get(IAPIConstantCodes.COUNT));
					 }
				}
				else if(apiresponse.get(IAPIConstantCodes.QUERY).equals(IAPIConstantCodes.user)){
					  queryParams.add(IAPIConstantCodes.TYPE, IAPIConstantCodes.user);
					  if (qParams=="User"){
					  
					  queryParams.add(IAPIConstantCodes.Q, apiresponse.get(IAPIConstantCodes.APIUSERNAME));
					  
					  }
					  
					  if (count == "Yes"){
							 
							 queryParams.add(IAPIConstantCodes.COUNT, apiresponse.get(IAPIConstantCodes.COUNT));
						 }
					  
					}
				
			 logger.info("API Request as URL with parameter   :::: "+ webResource.queryParams(queryParams));
			 response = webResource
					            .queryParams(queryParams)
								.accept(ApiResources.Content_Type)
						        .type(ApiResources.Content_Type)
								.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.get(ClientResponse.class);
			 
			 if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				    responseFlag = response.getEntity(String.class);
					   logger.info("Search Access Entity API JSON Response :::: " + responseFlag);
					   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					   searchAccessapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
					   
					   if(responseFlag.length()<65) {
						   
						   searchAccessapiresponse.put("Zerorecords","Zero");
					   }
					   
					   else {
					   if(responseFlag.contains("Email")){
						   List<String> useraccessentitiylist = JsonPath.read(responseFlag, "$.accessEntities[*].UserName");
						   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseJSON, useraccessentitiylist.toString());
					   }
					   
					   else {
						   List<String> groupaccessentitiylist = JsonPath.read(responseFlag, "$.accessEntities[*].Name");
						   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseJSON1, groupaccessentitiylist.toString());
					   }
					   }
					   
					   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
					    logger.info("statusInfo ::: "+response.getStatusInfo().toString());
					   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
					   
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
					   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					   
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
					   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
					   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
					   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
					   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else {
				    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				   }
			
			}
			 
			
			 
			 else if (parameters=="No")
			 {
				 
				 
				 response = webResource
				            .accept(ApiResources.Content_Type)
					        .type(ApiResources.Content_Type)
							.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
							.get(ClientResponse.class);
						 
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Search Access Entity API JSON Response :::: " + responseFlag);
				   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   searchAccessapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				   
				   List<String> useraccessentitiylist = JsonPath.read(responseFlag, "$.accessEntities[*].UserName");
				   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseJSON, useraccessentitiylist.toString());
				   
					List<String> groupaccessentitiylist = JsonPath.read(responseFlag, "$.accessEntities[*].Name");
					searchAccessapiresponse.put(IAPIConstantCodes.APIResponseJSON1, groupaccessentitiylist.toString());
				   
				   
				   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
				    logger.info("statusInfo ::: "+response.getStatusInfo().toString());
				   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchAccessapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		}  }catch (Exception e) {
			e.printStackTrace();
		}
		return searchAccessapiresponse;
	}
		
		
	}