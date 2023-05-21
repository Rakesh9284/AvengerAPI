package com.vbricks.avenger.serviceimpl;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IDeleteEvent;
import com.vbricks.avenger.service.IDeleteTeam;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;
/** 
 * @author neslavath
 * @functionName : deleteVideos
 * @Description : DeleteVideos API - will delete mention video ID of the Video into the application 
 * @param loginapiresponse (baseUrl,accessToken,VideoID
 * @return deleteVideoapi response(baseUrl,accessToken, apiresponse200OK)
 */
public class DeleteEventAPI implements IDeleteEvent {
	
	private static Logger logger = Logger.getLogger(DeleteEventAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> deleteeventapiresponse = new HashMap<String, String>();
	  
	
	public HashMap<String, String> deleteEvents(HashMap<String, String> apiresponse ) {
		String responseFlag = null;
		try {
			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.DELETEEVENT+apiresponse.get(IAPIConstantCodes.APIEVENTID));
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .delete(ClientResponse.class);
		
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Delete Event API JSON  :::: " + responseFlag);
				   deleteeventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteeventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   deleteeventapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   deleteeventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteeventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   deleteeventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteeventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   deleteeventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteeventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   deleteeventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteeventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   deleteeventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteeventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteeventapiresponse;

	}
}
