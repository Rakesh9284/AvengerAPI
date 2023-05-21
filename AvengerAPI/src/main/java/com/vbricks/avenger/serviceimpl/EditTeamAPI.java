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
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IEditTeam;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;

import net.sf.json.JSONObject;
/** 
 * @author neslavath
 * @functionName : editTeam
 * @Description :EditTeam API - Updated Team Details
 * @param loginapiresponse (baseUrl,accessToken,Team Id)
 * @return deleteVideoapi response(baseUrl,accessToken, apiresponse200OK)
 */
public class EditTeamAPI implements IEditTeam{
	
	private static Logger logger = Logger.getLogger(EditTeamAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> editteamapiresponse = new HashMap<String, String>();
	 
	public HashMap<String, String> editTeam(HashMap<String, String> apiresponse,org.json.simple.JSONObject createteamJSON)  {
		String responseFlag = null;
		try {
			client = Client.create();
			webResource = client.resource(apiresponse.get("baseurl"))
					     .path(RevbaseAPIURLs.EDITTEAM+apiresponse.get("channelId"));
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get("accesstoken"))
					   .put(ClientResponse.class,createteamJSON.toString());
	 	  
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Edit Event API JSON  :::: " + responseFlag);
				   editteamapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   editteamapiresponse.put("httpCode", Integer.toString(response.getStatus()));
			
				   editteamapiresponse.put("statusInfo", response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   responseFlag = response.getEntity(String.class);
				   logger.info("Edit Event API JSON  :::: " + responseFlag);
				   editteamapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   editteamapiresponse.put("httpCode", Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   responseFlag = response.getEntity(String.class);
				   logger.info("Edit Event API JSON  :::: " + responseFlag);
				   editteamapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   editteamapiresponse.put("httpCode", Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   responseFlag = response.getEntity(String.class);
				   logger.info("Edit Event API JSON  :::: " + responseFlag);
				   editteamapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   editteamapiresponse.put("httpCode", Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   responseFlag = response.getEntity(String.class);
				   logger.info("Edit Event API JSON  :::: " + responseFlag);
				   editteamapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   editteamapiresponse.put("httpCode", Integer.toString(response.getStatus()));
				   editteamapiresponse.put(IAPIConstantCodes.APIResponseJsonErrorCode, JsonPath.parse(responseFlag).read("$.code").toString());
				   editteamapiresponse.put(IAPIConstantCodes.APIResponseJsonErrorDetail, JsonPath.parse(responseFlag).read("$.detail").toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   responseFlag = response.getEntity(String.class);
				   logger.info("Edit Event API JSON  :::: " + responseFlag);
				   editteamapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   editteamapiresponse.put("httpCode", Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return editteamapiresponse;

	}

	 
}
	