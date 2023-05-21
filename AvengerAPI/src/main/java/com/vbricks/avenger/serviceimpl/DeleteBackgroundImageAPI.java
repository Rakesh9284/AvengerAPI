package com.vbricks.avenger.serviceimpl;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;

public class DeleteBackgroundImageAPI {
	
	private static Logger logger = Logger.getLogger(DeleteBackgroundImageAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> deletebackgroundimageapiresponse = new HashMap<String, String>();
	 
	public HashMap<String, String> deletebackgroundimage(HashMap<String, String> apiresponse ) {
		String responseFlag = null;
		try {
			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.DELETEBACKGROUNDIMAGE+apiresponse.get("eventId")+RevbaseAPIURLs.DELETEBACKGROUNDIMAGE1);
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .delete(ClientResponse.class);
		
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Delete background image for webcast API JSON  :::: " + responseFlag);
				   deletebackgroundimageapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deletebackgroundimageapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   deletebackgroundimageapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   deletebackgroundimageapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deletebackgroundimageapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   deletebackgroundimageapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deletebackgroundimageapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   deletebackgroundimageapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deletebackgroundimageapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   deletebackgroundimageapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deletebackgroundimageapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   deletebackgroundimageapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deletebackgroundimageapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code :  " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deletebackgroundimageapiresponse;

	}
	
}
