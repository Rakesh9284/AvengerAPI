package com.vbricks.avenger.serviceimpl;

import java.util.HashMap;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddUserApiBean;
import com.vbrick.avenger.apibeans.CreateEventBean;
import com.vbrick.avenger.apibeans.EventAccessControlBean;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;

import net.sf.json.JSONObject;
/** 
 * @author neslavath
 * @functionName : deleteVideos
 * @Description : DeleteVideos API - will delete mention video ID of the Video into the application 
 * @param loginapiresponse (baseUrl,accessToken,VideoID
 * @return deleteVideoapi response(baseUrl,accessToken, apiresponse200OK)
 */
public class EditAccessControlEntitiesforeventAPI {
	
	private static Logger logger = Logger.getLogger(EditAccessControlEntitiesforeventAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> EditEventapiresponse = new HashMap<String, String>();
	 
	public HashMap<String, String> EditAccessControl(HashMap<String, String> apiresponse,EventAccessControlBean eventAccessControlBean ) {
		String responseFlag = null;
		try {
			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.VIDEOACCESSCONTROL2);
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .put(ClientResponse.class,EditEventJSON(eventAccessControlBean));
	 	  
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Edit Access ControlEntities for Event API JSON  :::: " + responseFlag);
				   EditEventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   EditEventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			
				   EditEventapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   EditEventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   EditEventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   EditEventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   EditEventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   EditEventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   EditEventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   EditEventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   EditEventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   EditEventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   EditEventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EditEventapiresponse;

	}
	
	@SuppressWarnings("unchecked")
	public String EditEventJSON(EventAccessControlBean eventAccessControlBean) {

		JSONObject EditAccessControleventJson = new JSONObject();

		try {
			
			JSONArray jsonarryID = new JSONArray();
			// user ID
			
			if (eventAccessControlBean.getUserIds().length != 0 || eventAccessControlBean.getUserIds() != null) {

				for (String UserIds : eventAccessControlBean.getUserIds()) {
					jsonarryID.add(UserIds);
				}
				EditAccessControleventJson.put("UserIds", jsonarryID);
			}

			else
				EditAccessControleventJson.put("UserIds", "");

			// end tags

			JSONArray jsonUserName = new JSONArray();
			
			// 2 Usernames
			if (eventAccessControlBean.getUsernames().length != 0 || eventAccessControlBean.getUsernames() != null) {

				for (String Usernames : eventAccessControlBean.getUsernames()) {
					jsonUserName.add(Usernames);
				}
				EditAccessControleventJson.put("Username", jsonUserName);
			}

			else
				EditAccessControleventJson.put("Username", "");

			// end tags
			
			JSONArray jsonGroupIds = new JSONArray();
			
				// 3.GroupId
			if (eventAccessControlBean.getGroupIds().length != 0 || eventAccessControlBean.getGroupIds() != null) {

				for (String GroupIds : eventAccessControlBean.getGroupIds()) {
					jsonGroupIds.add(GroupIds);
				}
				EditAccessControleventJson.put("GroupIds", jsonGroupIds);
			}

			else
				EditAccessControleventJson.put("GroupIds", "");

			
		

			System.out.println("Created Event   JSON :::: " + EditAccessControleventJson);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return EditAccessControleventJson.toString();

	}

}
