package com.vbricks.avenger.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbrick.avenger.apibeans.CreateEmbeddedContentBean;
import com.vbrick.avenger.apibeans.CreateEventBean;
import com.vbrick.avenger.apibeans.CreatePushContentBean;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateEmbeddedContentService;
import com.vbricks.avenger.service.ICreateEventService;
//import com.vbricks.avenger.service.ICreatePushContentService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;

public class EmbeddedContentAPI {
	private ClientResponse response;
	private Client client;
	private WebResource webResource;
	private static Logger logger = Logger.getLogger(EmbeddedContentAPI.class);
	public HashMap<String, String> loginUser;
	public HashMap<String, String> loginapiresponse;
	private ArrayList<String> embeddedcontentapiresponse;
	private ArrayList<String> deleteembeddedcontentapiresponse;
	
	public JSONObject PostEmbeddedContentAPI(HashMap<String, String> apiresponse,CreateEmbeddedContentBean addembbedcontentbean) 
	{
		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.Embeddedcontentlink);
		logger.info("WebResourec BaseURL :::: "+webResource);
 		response = webResource
				   .accept(ApiResources.Content_Type)
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .post(ClientResponse.class,createEmbeddedContentJson(addembbedcontentbean));
 	
 	responseFlag = response.getEntity(String.class);
 	 Object obj=JSONValue.parse(responseFlag);    
     JSONObject embeddedcontentapiresponse = (JSONObject) obj;
     embeddedcontentapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatus());   
     
     logger.info("POST Response API JSON  :::: " + embeddedcontentapiresponse);
     return embeddedcontentapiresponse;
	}
	
	public  ArrayList<String> PostEmbeddedContentAPIdisable(HashMap<String, String> apiresponse,CreateEmbeddedContentBean addembbedcontentbean) 
	{
		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.Embeddedcontentlink);
		logger.info("WebResourec BaseURL :::: "+webResource);
 		response = webResource
				   .accept(ApiResources.Content_Type)
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .post(ClientResponse.class,createEmbeddedContentJson(addembbedcontentbean));
 	
 		 responseFlag = response.getEntity(String.class);
	 	 int status=response.getStatus();
	  String Httpstatus = Integer.toString(status);
	  embeddedcontentapiresponse = new ArrayList<>(Arrays.asList(Httpstatus,responseFlag));

	 logger.info("Embedded Content API Response::"+embeddedcontentapiresponse);
     return embeddedcontentapiresponse;
	}
	public ArrayList<String> PutEmbeddedContentAPI_Status(HashMap<String, String> apiresponse,CreateEmbeddedContentBean addpushcontentbean) 
	{
		 

		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.Embeddedcontentlinks+apiresponse.get(IAPIConstantCodes.SharedURLID)+RevbaseAPIURLs.Status);
		logger.info("WebResourec BaseURL :::: "+webResource);
		/*JSONParser parser = new JSONParser();
		JSONObject Isenabled = (JSONObject) parser.parse(addpushcontentbean);*/
		response = webResource
				   .accept(ApiResources.Content_Type)
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .put(ClientResponse.class,putEmbeddedContentJson_status(addpushcontentbean));
 		
 		  logger.info("@@@@@@@@REsponse status present in api ::::"+response.getStatus());
 	    responseFlag = response.getEntity(String.class);
 	    int status=response.getStatus();
 	   String Httpstatus = Integer.toString(status);
 	  embeddedcontentapiresponse = new ArrayList<>( Arrays.asList(Httpstatus));
        return embeddedcontentapiresponse;
	}
	
	 
	public JSONObject GET_EmbeddedContentAPI(HashMap<String, String> apiresponse) {
		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
			     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.GETEmbeddedcontentlinks);
		logger.info("WebResourec BaseURL :::: "+webResource);
 		response = webResource
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .get(ClientResponse.class);
 	   
 		logger.info("@@@@@Status    :::: " + response.getStatus());
 	   responseFlag = response.getEntity(String.class);
 	  
       Object obj=JSONValue.parse(responseFlag);    
       JSONObject jsonObject = (JSONObject) obj;
       jsonObject.put(IAPIConstantCodes.APIResponseStatusInfo, Integer.toString(response.getStatus()));
      
       
       JSONArray contentlink=(JSONArray) jsonObject.get("contentLinks");
       //System.out.println("CONTENT LINK PRESENT ::::::::::::::"+contentlink.get(0));
      
       JSONObject contentlinkobject=(JSONObject) contentlink.get(0);  
   
       contentlinkobject.put(IAPIConstantCodes.APIResponseStatusInfo, Integer.toString(response.getStatus()));
       logger.info("Get Response API JSON  :::: " + contentlinkobject);
       return contentlinkobject;	
       
       
	}
	
	
	public JSONObject GET_EmbeddedContentAPI_invalid(HashMap<String, String> apiresponse) {
		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
			     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.GETEmbeddedcontentlinks);
		logger.info("WebResourec BaseURL :::: "+webResource);
 		response = webResource
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .get(ClientResponse.class);
 	   
 		logger.info("@@@@@Status    :::: " + response.getStatus());
 	   responseFlag = response.getEntity(String.class);
 	  
       Object obj=JSONValue.parse(responseFlag);    
       JSONObject jsonObject = (JSONObject) obj;
       jsonObject.put(IAPIConstantCodes.APIResponseStatusInfo, Integer.toString(response.getStatus()));
	return jsonObject;
       
	}
	public JSONObject GETMultipleEmbeddedContentAPI(HashMap<String, String> apiresponse) {
		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
			     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.GETEmbeddedcontentlinks);
		logger.info("WebResourec BaseURL :::: "+webResource);
 		response = webResource
				   .accept(ApiResources.Content_Type)
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .get(ClientResponse.class);
 	   
 		logger.info("@@@@@Status    :::: " + response.getStatus());
 	   responseFlag = response.getEntity(String.class);
 	  
       Object obj=JSONValue.parse(responseFlag);    
       JSONObject jsonObject = (JSONObject) obj;
       jsonObject.put(IAPIConstantCodes.APIResponseStatusInfo, Integer.toString(response.getStatus()));
       JSONArray contentlink=(JSONArray) jsonObject.get("contentLinks");
       //System.out.println("CONTENT LINK PRESENT ::::::::::::::"+contentlink.get(0));
      //for 1st array
       JSONObject contentlinkobject=(JSONObject) contentlink.get(0);  
       jsonObject.put("id",contentlinkobject.get("id").toString());
       jsonObject.put("name",contentlinkobject.get("name").toString());
       jsonObject.put("code",contentlinkobject.get("code").toString());
       jsonObject.put("isEnabled",contentlinkobject.get("isEnabled").toString());
       jsonObject.put("icon",contentlinkobject.get("icon").toString());
       jsonObject.put("resourceUri",contentlinkobject.get("resourceUri").toString());
      
      //for 2nd array 
       JSONObject contentlinkobject1=(JSONObject) contentlink.get(1);  
       contentlinkobject.put("id1",contentlinkobject1.get("id").toString());
       contentlinkobject.put("name1",contentlinkobject1.get("name").toString());
       contentlinkobject.put("code1",contentlinkobject1.get("code").toString());
       contentlinkobject.put("isEnabled1",contentlinkobject1.get("isEnabled").toString());
       contentlinkobject.put("icon1",contentlinkobject1.get("icon").toString());
       contentlinkobject.put("resourceUri1",contentlinkobject1.get("resourceUri").toString());
      
       contentlinkobject.put(IAPIConstantCodes.APIResponseStatusInfo, Integer.toString(response.getStatus()));
       logger.info("Get Response API JSON  :::: " + contentlinkobject);
       return contentlinkobject;
		
	}
	public JSONObject Put_EmbeddedContentAPI_Id(HashMap<String, String> apiresponse,CreateEmbeddedContentBean addembeddedcontentbean) 
	{
		 

		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.Embeddedcontentlinks+apiresponse.get(IAPIConstantCodes.SharedURLID));
		logger.info("WebResourec BaseURL :::: "+webResource);
		response = webResource
				   .accept(ApiResources.Content_Type)
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .put(ClientResponse.class,putEmbeddedContentJson_id(addembeddedcontentbean));
 		
 		 
		responseFlag = response.getEntity(String.class);
	 	 Object obj=JSONValue.parse(responseFlag);        
	     JSONObject embeddedcontentapiresponse = (JSONObject) obj;
	     embeddedcontentapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatus());
	       
	     //logger.info("##############SHARED URL ID:::::"+Id);
	     logger.info("POST Response API JSON  :::: " + embeddedcontentapiresponse);
	     return embeddedcontentapiresponse;
 	   
	}		
	public  ArrayList<String>  Delete_EmbeddedContentAPI(HashMap<String, String> apiresponse) 
	{
		 
    
		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.Embeddedcontentlinks+apiresponse.get(IAPIConstantCodes.SharedURLID));
		logger.info("WebResourec BaseURL :::: "+webResource);
		response = webResource
				   .accept(ApiResources.Content_Type)
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .delete(ClientResponse.class);
		
 		System.out.println("Response status of delete Api"+response.getStatus());
		 responseFlag = response.getEntity(String.class);
	 	    int status=response.getStatus();
	 	   String Httpstatus = Integer.toString(status);
	 	   deleteembeddedcontentapiresponse = new ArrayList<>( Arrays.asList(Httpstatus));
	        return deleteembeddedcontentapiresponse;
		}
	
	@SuppressWarnings("unchecked")
	private String createEmbeddedContentJson(CreateEmbeddedContentBean createEmbeddedContentApiBean)
	{
		JSONObject createsembeddedJson = new JSONObject();
		
		try {
			// 1.Name
				if (!createEmbeddedContentApiBean.getName().isEmpty() && createEmbeddedContentApiBean.getName() != null)
					createsembeddedJson.put(ICreateEmbeddedContentService.Name, createEmbeddedContentApiBean.getName());
				else
					createsembeddedJson.remove(ICreateEmbeddedContentService.Name);
			//2 Code
				if (!createEmbeddedContentApiBean.getCode().isEmpty() && createEmbeddedContentApiBean.getCode() != null)
					createsembeddedJson.put(ICreateEmbeddedContentService.Code, createEmbeddedContentApiBean.getCode());
				else
					createsembeddedJson.remove(ICreateEmbeddedContentService.Code);
		//icon
				if (!createEmbeddedContentApiBean.getIcon().isEmpty() && createEmbeddedContentApiBean.getIcon() != null)
					createsembeddedJson.put(ICreateEmbeddedContentService.Icon, createEmbeddedContentApiBean.getIcon());
				else
					createsembeddedJson.remove(ICreateEmbeddedContentService.Icon);
				
	
		}catch (Exception e) {
			e.printStackTrace();

		}
	return createsembeddedJson.toString();
	}
		
	
	@SuppressWarnings("unchecked")
	private String putEmbeddedContentJson_status(CreateEmbeddedContentBean createEmbeddedContentApiBean1)
	{
		JSONObject createsembeddedJson1 = new JSONObject();
		try {//IsEnabled
		if (!createEmbeddedContentApiBean1.getIsEnabled().isEmpty() && createEmbeddedContentApiBean1.getIsEnabled() != null)
			createsembeddedJson1.put(ICreateEmbeddedContentService.IsEnabled, createEmbeddedContentApiBean1.getIsEnabled());
		else
			createsembeddedJson1.remove(ICreateEmbeddedContentService.IsEnabled);
	}catch (Exception e) {
		e.printStackTrace();

	}
return createsembeddedJson1.toString();
}
	
	@SuppressWarnings("unchecked")
	private String putEmbeddedContentJson_id(CreateEmbeddedContentBean createEmbeddedContentApiBean)
	{
		JSONObject createsembeddedJson = new JSONObject();
		
		try {
			// 1.Name
				if (!createEmbeddedContentApiBean.getName().isEmpty() && createEmbeddedContentApiBean.getName() != null)
					createsembeddedJson.put(ICreateEmbeddedContentService.Name, createEmbeddedContentApiBean.getName());

				else if (createEmbeddedContentApiBean.getName().isEmpty())
					createsembeddedJson.put(ICreateEmbeddedContentService.Name, createEmbeddedContentApiBean.getName().isBlank());
				else 
					createsembeddedJson.remove(ICreateEmbeddedContentService.Name);
			//2 Code
				if (!createEmbeddedContentApiBean.getCode().isEmpty() && createEmbeddedContentApiBean.getCode() != null)
					createsembeddedJson.put(ICreateEmbeddedContentService.Code, createEmbeddedContentApiBean.getCode());
				else if (createEmbeddedContentApiBean.getCode().isEmpty())
					createsembeddedJson.put(ICreateEmbeddedContentService.Code, createEmbeddedContentApiBean.getCode().isBlank());
				else 
					createsembeddedJson.remove(ICreateEmbeddedContentService.Code);
		//icon
				if (!createEmbeddedContentApiBean.getIcon().isEmpty() && createEmbeddedContentApiBean.getIcon() != null)
					createsembeddedJson.put(ICreateEmbeddedContentService.Icon, createEmbeddedContentApiBean.getIcon());
	
				else if (createEmbeddedContentApiBean.getIcon().isEmpty())
					createsembeddedJson.put(ICreateEmbeddedContentService.Icon, createEmbeddedContentApiBean.getIcon().isBlank());
				else
					createsembeddedJson.remove(ICreateEmbeddedContentService.Icon);
				//Isenabled
				if (!createEmbeddedContentApiBean.getIsEnabled().isEmpty() && createEmbeddedContentApiBean.getIsEnabled() != null)
					createsembeddedJson.put(ICreateEmbeddedContentService.IsEnabled, createEmbeddedContentApiBean.getIsEnabled());
				else if (createEmbeddedContentApiBean.getIsEnabled().isEmpty())
					createsembeddedJson.put(ICreateEmbeddedContentService.Icon, createEmbeddedContentApiBean.getIsEnabled().isBlank());
				else
					createsembeddedJson.remove(ICreateEmbeddedContentService.IsEnabled);
				
	
		}catch (Exception e) {
			e.printStackTrace();

		}
	return createsembeddedJson.toString();
	}
	 
}
