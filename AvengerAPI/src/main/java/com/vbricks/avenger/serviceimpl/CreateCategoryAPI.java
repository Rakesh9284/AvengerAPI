package com.vbricks.avenger.serviceimpl;

import java.util.HashMap;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbrick.avenger.apibeans.AddCategoryBean;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateCategoryAPI;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;


public class CreateCategoryAPI {
	private static Logger logger = Logger.getLogger(CreateCategoryAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;	 
	HashMap<String, String> createcategoryapiresponse = new HashMap<String, String>();
 
	/** 
	 * @author neslavath
	 * @functionName : createcategory
	 * @Description : createcategory API - will able give ratings on mention video ID of the Video into the application 
	 * @param loginapiresponse (baseUrl,accessToken,VideoID
	 * @return createcategoryapi  response(baseUrl,accessToken, apiresponse200OK)
	 */
	
	public HashMap<String, String> createcategory(HashMap<String, String> apiresponse, JSONObject categortJSON) {

		String responseFlag = null;
 		try {

			client = Client.create();
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.CREATECATEGORYURL);
			 logger.info("API Request as URL    :::: "+ webResource);
			response = webResource
								.accept(ApiResources.Content_Type)
								.type(ApiResources.Content_Type)
								.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.post(ClientResponse.class,categortJSON.toString());
			 
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Create Category API JSON Response :::: " + responseFlag);
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   createcategoryapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				   createcategoryapiresponse.put("categoryId", JsonPath.parse(responseFlag).read("$.categoryId").toString());
				   createcategoryapiresponse.put("name", JsonPath.parse(responseFlag).read("$.name").toString());
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
				   logger.info("statusInfo ::: "+response.getStatusInfo().toString());
				   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createcategoryapiresponse;
		 
}	
	/**
	 * @author neslavath
	 * @functionName : createCategoryJSON 
	 * @Description - Create JSON object for createCategory with Name
	 * @param  AddCategroryBean 
	 * @return JSONObject
	 */
	public JSONObject createCategoryJson(AddCategoryBean page){
	JSONObject categoryJson=new JSONObject();
	categoryJson.put("name",  page.getName());	
	logger.info("createCategory JSON is ::: "+categoryJson);
	return categoryJson;
	}
		
	public JSONObject createChildCategoryJson(HashMap<String, String> childCategroyinputs){
	JSONObject categoryJson=new JSONObject();
	categoryJson.put(ICreateCategoryAPI.name,  childCategroyinputs.get(ICreateCategoryAPI.name));	
	categoryJson.put(ICreateCategoryAPI.parentCategoryId,  childCategroyinputs.get(ICreateCategoryAPI.parentCategoryId));
	logger.info("createCategory JSON is ::: "+categoryJson);
	return categoryJson;
	}
	
	public HashMap<String, String> createrestrictedcategory(HashMap<String, String> apiresponse, JSONObject categoryJsonforrestricted) {

		String responseFlag = null;
 		try {

			client = Client.create();
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.CREATECATEGORYURL);
			 logger.info("API Request as URL    :::: "+ webResource);
			response = webResource
								.accept(ApiResources.Content_Type)
								.type(ApiResources.Content_Type)
								.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.post(ClientResponse.class,categoryJsonforrestricted.toString());
			 
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Create Category API JSON Response :::: " + responseFlag);
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   createcategoryapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				   createcategoryapiresponse.put("categoryId", JsonPath.parse(responseFlag).read("$.categoryId").toString());
				   createcategoryapiresponse.put("name", JsonPath.parse(responseFlag).read("$.name").toString());
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
				   logger.info("statusInfo ::: "+response.getStatusInfo().toString());
				   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createcategoryapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createcategoryapiresponse;
		 
}	
	public JSONObject createCategoryJsonforrestrictedcategory(AddCategoryBean page){
		JSONObject categoryJsonforrestricted=new JSONObject();
		categoryJsonforrestricted.put("name",  page.getName());	
		categoryJsonforrestricted.put("restricted",  true);	
		logger.info("createCategory JSON is ::: "+categoryJsonforrestricted);
		return categoryJsonforrestricted;
		}

}



