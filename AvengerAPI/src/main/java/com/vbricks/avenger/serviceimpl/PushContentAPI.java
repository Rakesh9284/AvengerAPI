package com.vbricks.avenger.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbrick.avenger.apibeans.CreatePushContentBean;
import com.vbrick.avenger.apibeans.CreatePushContentBean;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateEmbeddedContentService;
import com.vbricks.avenger.service.ICreatePushContentService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;

public class PushContentAPI {
	private ClientResponse response;
	private Client client;
	private WebResource webResource;
	private static Logger logger = Logger.getLogger(PushContentAPI.class);
	public HashMap<String, String> loginUser;
	public HashMap<String, String> loginapiresponse;
	private ArrayList<String> pushcontentapiresponse;
	private ArrayList<String> deletepushcontentapiresponse;
	
	
	
	
	public ArrayList<String> PushContentWebCastAPI(HashMap<String, String> apiresponse,CreatePushContentBean addpushcontentbean) 
	{
		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.PushcontentLink);
		logger.info("WebResourec BaseURL :::: "+webResource);
 		response = webResource
				   .accept(ApiResources.Content_Type)
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .post(ClientResponse.class,createPushContentJson(addpushcontentbean));
 	
 	responseFlag = response.getEntity(String.class);
 	  int status=response.getStatus();
	   String Httpstatus = Integer.toString(status);
	   pushcontentapiresponse = new ArrayList<>( Arrays.asList(responseFlag,Httpstatus));
  
     logger.info("POST Response API JSON  :::: " + pushcontentapiresponse);
     return pushcontentapiresponse;
	}
	
	
	
	/*public  ArrayList<String> PostEmbeddedContentAPIdisable(HashMap<String, String> apiresponse,CreatePushContentBean addembbedcontentbean) 
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
	  embeddedcontentapiresponse = new ArrayList<>( Arrays.asList(Httpstatus));

     return embeddedcontentapiresponse;
	}
	*/
	public ArrayList<String> Put_PushContentAPI_Status(HashMap<String, String> apiresponse,CreatePushContentBean addpushcontentbean) 
	{
		 

		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.Pushcontentlink+apiresponse.get(IAPIConstantCodes.SharedURLID)+RevbaseAPIURLs.Status);
		logger.info("WebResourec BaseURL :::: "+webResource);
		 
		response = webResource
				   .accept(ApiResources.Content_Type)
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .put(ClientResponse.class,putPushContentJson_status(addpushcontentbean));
 		
 		  logger.info("@@@@@@@@REsponse status present in api ::::"+response.getStatus());
 	    responseFlag = response.getEntity(String.class);
 	    int status=response.getStatus();
 	   String Httpstatus = Integer.toString(status);
 	  pushcontentapiresponse = new ArrayList<>( Arrays.asList(Httpstatus,responseFlag));
        return pushcontentapiresponse;
	}
	
	 
	public JSONObject GET_PushContentAPI(HashMap<String, String> apiresponse) {
		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
			     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.GetPushcontentLinks);
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
       jsonObject.put("id",contentlinkobject.get("id"));
       jsonObject.put("name",contentlinkobject.get("name"));
       jsonObject.put("link",contentlinkobject.get("link"));
       jsonObject.put("isEnabled",contentlinkobject.get("isEnabled"));
       jsonObject.put("pushMethod",contentlinkobject.get("pushMethod"));
       
      
       contentlinkobject.put(IAPIConstantCodes.APIResponseStatusInfo, Integer.toString(response.getStatus()));
       logger.info("Get Response API JSON  :::: " + contentlinkobject);
       return contentlinkobject;
		
	}
	
	public JSONObject GET_PushContentAPI_invalid(HashMap<String, String> apiresponse) {
		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
			     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.GetPushcontentLinks);
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
	
	
	/*public JSONObject GETMultipleEmbeddedContentAPI(HashMap<String, String> apiresponse) {
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
        jsonObject.put("id",contentlinkobject.get("id"));
       jsonObject.put("name",contentlinkobject.get("name"));
       jsonObject.put("link",contentlinkobject.get("link"));
       jsonObject.put("isEnabled",contentlinkobject.get("isEnabled"));
       jsonObject.put("pushMethod",contentlinkobject.get("pushMethod"));
       
      //for 2nd array 
       JSONObject contentlinkobject1=(JSONObject) contentlink.get(1);  
       jsonObject.put("id",contentlinkobject1.get("id"));
       jsonObject.put("name",contentlinkobject1.get("name"));
       jsonObject.put("link",contentlinkobject1.get("link"));
       jsonObject.put("isEnabled",contentlinkobject1.get("isEnabled"));
       jsonObject.put("pushMethod",contentlinkobject1.get("pushMethod"));
       contentlinkobject.put(IAPIConstantCodes.APIResponseStatusInfo, Integer.toString(response.getStatus()));
       logger.info("Get Response API JSON  :::: " + contentlinkobject);
       return contentlinkobject;
		
	}*/
	public JSONObject Put_PushContentWebCastAPI_Id(HashMap<String, String> apiresponse,CreatePushContentBean addembeddedcontentbean) 
	{
		 

		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.Pushcontentlink+apiresponse.get(IAPIConstantCodes.SharedURLID));
		logger.info("WebResourec BaseURL :::: "+webResource);
		response = webResource
				   .accept(ApiResources.Content_Type)
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .put(ClientResponse.class,putPushContentJson_id(addembeddedcontentbean));
 		
 		 
		logger.info("@@@@@Status    :::: " + response.getStatus());
	 	   responseFlag = response.getEntity(String.class);
	 	  
	       Object obj=JSONValue.parse(responseFlag);    
	       System.out.println("coverted resomse:::::::"+obj);
	       JSONObject pushcontentapiresponse = (JSONObject) obj;
	 	 
	       pushcontentapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo,Integer.toString(response.getStatus()));
	       
	     //logger.info("##############SHARED URL ID:::::"+Id);
	     logger.info("POST Response API JSON  :::: " + pushcontentapiresponse);
	     return pushcontentapiresponse;
 	   
	}
	
	public ArrayList<String> Put_PushContentWebCastAPI_IdInvalid(HashMap<String, String> apiresponse,CreatePushContentBean addembeddedcontentbean) 
	{
		 

		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.Pushcontentlink+apiresponse.get(IAPIConstantCodes.SharedURLID));
		logger.info("WebResourec BaseURL :::: "+webResource);
		response = webResource
				   .accept(ApiResources.Content_Type)
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .put(ClientResponse.class,putPushContentJson_id(addembeddedcontentbean));
 		
 		 
		logger.info("@@@@@@@@REsponse status present in api ::::"+response.getStatus());
 	    responseFlag = response.getEntity(String.class);
 	    int status=response.getStatus();
 	   String Httpstatus = Integer.toString(status);
 	  pushcontentapiresponse = new ArrayList<>( Arrays.asList(Httpstatus));
        return pushcontentapiresponse;
	}
	
	
	 
		
	public  ArrayList<String>  Delete_PushContentAPI(HashMap<String, String> apiresponse) 
	{
		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.Pushcontentlink+apiresponse.get(IAPIConstantCodes.SharedURLID));
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
	 	  deletepushcontentapiresponse = new ArrayList<>( Arrays.asList(Httpstatus));
	        return deletepushcontentapiresponse;
		}
	
	
	
	

	@SuppressWarnings("unchecked")
	private String putPushContentJson_id(CreatePushContentBean createpushContentApiBean)
	{
		JSONObject createpushcontentJson = new JSONObject();
		
		try {
		// 1.Name
				if (!createpushContentApiBean.getName().isEmpty() && createpushContentApiBean.getName() != null)
					createpushcontentJson.put(ICreatePushContentService.Name, createpushContentApiBean.getName());
				
				else if(createpushContentApiBean.getName().isEmpty())	
					createpushcontentJson.put(ICreatePushContentService.Name, createpushContentApiBean.getName());
				else
					createpushcontentJson.remove(ICreatePushContentService.Name);
		//2.Link
				if (!createpushContentApiBean.getLink().isEmpty() && createpushContentApiBean.getLink() != null)
					createpushcontentJson.put(ICreatePushContentService.Link, createpushContentApiBean.getLink());
				
				else if(createpushContentApiBean.getLink().isEmpty())	
					createpushcontentJson.put(ICreatePushContentService.Link, createpushContentApiBean.getLink());
				else
					createpushcontentJson.remove(ICreatePushContentService.Link);
		//3.PushMethod
				if (!createpushContentApiBean.getPushMethod().isEmpty() && createpushContentApiBean.getPushMethod() != null)
					createpushcontentJson.put(ICreatePushContentService.PushMethod, createpushContentApiBean.getPushMethod());
				else if(createpushContentApiBean.getLink().isEmpty())	
					createpushcontentJson.put(ICreatePushContentService.PushMethod, createpushContentApiBean.getPushMethod());
				else
			    createpushcontentJson.remove(ICreatePushContentService.PushMethod);
		//4.IsEnabled
				if (!createpushContentApiBean.getIsEnabled().isEmpty() && createpushContentApiBean.getIsEnabled() != null)
					createpushcontentJson.put(ICreatePushContentService.IsEnabled, createpushContentApiBean.getIsEnabled());
				else if(createpushContentApiBean.getLink().isEmpty())	
					createpushcontentJson.put(ICreatePushContentService.IsEnabled, createpushContentApiBean.getIsEnabled());
				else
					createpushcontentJson.remove(ICreatePushContentService.IsEnabled);		
				
	
		}catch (Exception e) {
			e.printStackTrace();

		}
	return createpushcontentJson.toString();
	}
	
	
	@SuppressWarnings("unchecked")
	private String createPushContentJson(CreatePushContentBean createpushContentApiBean)
	{
		JSONObject createpushcontentJson = new JSONObject();
		
		try {
		// 1.Name
				if (!createpushContentApiBean.getName().isEmpty() && createpushContentApiBean.getName() != null)
					createpushcontentJson.put(ICreatePushContentService.Name, createpushContentApiBean.getName());
				else
					createpushcontentJson.remove(ICreatePushContentService.Name);
		//2.Link
				if (!createpushContentApiBean.getLink().isEmpty() && createpushContentApiBean.getLink() != null)
					createpushcontentJson.put(ICreatePushContentService.Link, createpushContentApiBean.getLink());
				else
					createpushcontentJson.remove(ICreatePushContentService.Link);
		//3.PushMethod
				if (!createpushContentApiBean.getPushMethod().isEmpty() && createpushContentApiBean.getPushMethod() != null)
					createpushcontentJson.put(ICreatePushContentService.PushMethod, createpushContentApiBean.getPushMethod());
				else
			    createpushcontentJson.remove(ICreatePushContentService.PushMethod);
		//4.IsEnabled
				if (!createpushContentApiBean.getIsEnabled().isEmpty() && createpushContentApiBean.getIsEnabled() != null)
					createpushcontentJson.put(ICreatePushContentService.IsEnabled, createpushContentApiBean.getIsEnabled());
				else
					createpushcontentJson.remove(ICreatePushContentService.IsEnabled);		
				
	
		}catch (Exception e) {
			e.printStackTrace();

		}
	return createpushcontentJson.toString();
	}
	
	@SuppressWarnings("unchecked")
	private String putPushContentJson_status(CreatePushContentBean createpushContentApiBean1)
	{
		JSONObject createpushcontentJson1 = new JSONObject();
		try {//IsEnabled
		if (!createpushContentApiBean1.getIsEnabled().isEmpty() && createpushContentApiBean1.getIsEnabled() != null)
			createpushcontentJson1.put(ICreatePushContentService.IsEnabled, createpushContentApiBean1.getIsEnabled());
		else
			createpushcontentJson1.remove(ICreatePushContentService.IsEnabled);
	}catch (Exception e) {
		e.printStackTrace();

	}
return createpushcontentJson1.toString();
}
	
}


