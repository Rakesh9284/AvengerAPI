package com.vbricks.avenger.serviceimpl;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.simple.JSONObject;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbrick.avenger.apibeans.AddGroupBean;
import com.vbrick.avenger.apibeans.AddTeamBean;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateGroup;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;
 
public class CreateGroupAPI implements ICreateGroup{
	private static Logger logger = Logger.getLogger(CreateGroupAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;	 
	/*HashMap<String, String> creategroupapirespone = new HashMap<String, String>();
 
	/** 
	 * @author rajitha
	 * @functionName : createGroup
	 * @Description : createGroup API - will create Group through API 
	 */
	
	public HashMap<String, String> createGroup(HashMap<String, String> apiresponse, JSONObject categortJSON) {
	
		HashMap<String, String> creategroupapiresponse = new HashMap<String, String>();
 
			String responseFlag = null;
	 		try {

				client = Client.create();
				webResource = client
									.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
									.path(RevbaseAPIURLs.CREATEGROUPURL);
				 logger.info("API Request as URL      :::: "+ webResource);
				response = webResource
									.accept(ApiResources.Content_Type)
									.type(ApiResources.Content_Type)
									.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
									.post(ClientResponse.class,categortJSON.toString());
				 
				if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				    responseFlag = response.getEntity(String.class);
					   logger.info("Create Group API JSON Response :::: " + responseFlag);
					   creategroupapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					   creategroupapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
					   creategroupapiresponse.put("groupId", JsonPath.parse(responseFlag).read("$.groupId").toString());
					   creategroupapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   creategroupapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
					   logger.info("statusInfo ::: "+response.getStatusInfo().toString());
					   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
					   
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
					   creategroupapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   creategroupapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					   
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
					   creategroupapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   creategroupapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
					   creategroupapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   creategroupapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
					   creategroupapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   creategroupapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
					   creategroupapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   creategroupapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else {
				    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				   }
			
			} catch (Exception e) {
				e.printStackTrace();
				  logger.info("Exceptions ::: " + e.getMessage());
			}
			return creategroupapiresponse;
			 
 
	}
	/**
	 * @author Rajitha
	 * @functionName : createCategoryJSON 
	 * @Description - Create JSON object for createCategory with Name
	 * @param  AddCategroryBean 
	 * @return JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject createGroupJson(AddGroupBean page){
	JSONObject groupJson=new JSONObject();
	
	if (page.getUserids() != null && page.getUserids().length > 0)
	groupJson.put("UserIds", createArrayJson(page.getUserids()));
	
	if (page.getRoleids() != null && page.getRoleids().length > 0)
	groupJson.put("RoleIds", createArrayJson(page.getRoleids()));
	
	if (page.getName() != "") {
	groupJson.put("Name", page.getName());
	
	}

	logger.info("createGroup JSON is ::: "+groupJson);
	
	return groupJson;
	}
		
	private JSONArray createArrayJson(String[] page){
	JSONArray grouparryJson=new JSONArray();
	
	for(int i=0;i<page.length;i++)
	{
		grouparryJson.put(page[i]);
	}
	return grouparryJson;
	 
	}
 
}
