package com.vbricks.avenger.serviceimpl;


import java.util.HashMap;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateDevice;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;


public class CreateDME_DeviceAPI {
	private static Logger logger = Logger.getLogger(CreateDME_DeviceAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;	 
	HashMap<String, String> createDeviceapiresponse = new HashMap<String, String>();
 
	/** 
	 * @author neslavath
	 * @param userName 
	 * @functionName : editVideoRating
	 * @Description : editVideoRating API - will able give ratings on mention video ID of the Video into the application 
	 * @param loginapiresponse (baseUrl,accessToken,VideoID
	 * @return editvideoratingapi  response(baseUrl,accessToken, apiresponse200OK)
	 */
	
	public HashMap<String, String> CreatingaDevice(HashMap<String, String> apiresponse,org.json.simple.JSONObject createDevice) {

		String responseFlag = null;
 		try {

			client = Client.create();
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.ADDDMEURL);
			 logger.info("API Request as URL    :::: "+ webResource);
			
			response = webResource
								.accept(ApiResources.Content_Type)
								.type(ApiResources.Content_Type)
							
								.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.post(ClientResponse.class,createDevice.toString());
			
			
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
			    String a[]=responseFlag.replaceAll("[\"']*","").split(",");
				 
				   String a1[]=a[0].split(":");
				   System.out.println(a1[1]);
				   
				   String deviceid = a1[1].substring(0, a1[1].length()-1 );
				   logger.info("Create DME API JSON Response :::: " + responseFlag);	   
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createDeviceapiresponse.put(IAPIConstantCodes.DEVICEID, deviceid);
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseuserName, apiresponse.get(IAPIConstantCodes.APIUSERNAME));  
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseFirstName, apiresponse.get(IAPIConstantCodes.APIUSERNAME));  
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseLastName, apiresponse.get(IAPIConstantCodes.APIUSERNAME));  
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createDeviceapiresponse;
		 
}	
	public JSONObject createDevice(String name,String macAddress,boolean isActive ) {
		JSONObject commentJson1=new JSONObject();
		commentJson1.put("name",  new String(name));
		commentJson1.put("macAddress", new String(macAddress));
		commentJson1.put("isActive", "true");
		return commentJson1;
}
	
	public HashMap<String, String> CreatingaDevicewithInvalidMacAddress(HashMap<String, String> apiresponse,org.json.simple.JSONObject createDevicewithInvalidMacAddress) {

		String responseFlag = null;
 		try {

			client = Client.create();
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.ADDDMEURL);
			 logger.info("API Request as URL    :::: "+ webResource);
			
			response = webResource
								.accept(ApiResources.Content_Type)
								.type(ApiResources.Content_Type)
							
								.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.post(ClientResponse.class,createDevicewithInvalidMacAddress.toString());
			
			
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
			    String a[]=responseFlag.replaceAll("[\"']*","").split(",");
				 
				   String a1[]=a[0].split(":");
				   System.out.println(a1[1]);
				   
				   logger.info("Edit VideoComment API JSON Response :::: " + responseFlag);	   
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createDeviceapiresponse.put(IAPIConstantCodes.DEVICEID, a1[1]);
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseuserName, apiresponse.get(IAPIConstantCodes.APIUSERNAME));  
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseFirstName, apiresponse.get(IAPIConstantCodes.APIUSERNAME));  
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseLastName, apiresponse.get(IAPIConstantCodes.APIUSERNAME));  
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				  // createDeviceapiresponse.put(IAPIConstantCodes.APIResponseJsonErrorDetail, JsonPath.parse(responseFlag).read("$.detail").toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createDeviceapiresponse;
		 
}	
	public JSONObject createDevicewithInvalidMacAddress(String name,String macAddress,boolean isActive ) {
		JSONObject commentJson1=new JSONObject();
		commentJson1.put("name",  new String(name));
		commentJson1.put("macAddress", ICreateDevice.invalidMacAddress);
		commentJson1.put("isActive", "true");
		return commentJson1;
}
	
	public HashMap<String, String> CreatingaDevicewithoutAuthorization(HashMap<String, String> apiresponse,org.json.simple.JSONObject createDevice) {

		String responseFlag = null;
 		try {

			client = Client.create();
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.ADDDMEURL);
			 logger.info("API Request as URL    :::: "+ webResource);
			
			response = webResource
								.accept(ApiResources.Content_Type)
								.type(ApiResources.Content_Type)
							
								//.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.post(ClientResponse.class,createDevice.toString());
			
			
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
			    String a[]=responseFlag.replaceAll("[\"']*","").split(",");
				 
				   String a1[]=a[0].split(":");
				   System.out.println(a1[1]);
				   
				   logger.info("Edit VideoComment API JSON Response :::: " + responseFlag);	   
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createDeviceapiresponse.put(IAPIConstantCodes.DEVICEID, a1[1]);
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseuserName, apiresponse.get(IAPIConstantCodes.APIUSERNAME));  
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseFirstName, apiresponse.get(IAPIConstantCodes.APIUSERNAME));  
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseLastName, apiresponse.get(IAPIConstantCodes.APIUSERNAME));  
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createDeviceapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createDeviceapiresponse;
		 
}	
	public JSONObject createDevicewithoutAuthorization(String name,String macAddress,boolean isActive ) {
		JSONObject commentJson1=new JSONObject();
		commentJson1.put("name",  new String(name));
		commentJson1.put("macAddress", new String(macAddress));
		commentJson1.put("isActive", "true");
		return commentJson1;
}
}

