package com.vbricks.avenger.serviceimpl;


import java.util.HashMap;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbrick.avenger.apibeans.AddUserApiBean;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IEditGroup;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;

import net.sf.json.JSONObject;
/** 
 * @author rajithad
 * @functionName : editGroup
 * @Description :EditGroup API - Updated Group Details
 * @param loginapiresponse (baseUrl,accessToken,Group Id)
 */
public class EditGroupAPI implements IEditGroup{
	
	private static Logger logger = Logger.getLogger(EditGroupAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> editgroupapiresponse = new HashMap<String, String>();
	 
	public HashMap<String, String> editGroup(HashMap<String, String> apiresponse,org.json.simple.JSONObject creategroupJSON)  {
		String responseFlag = null;
		try {
			client = Client.create();
			webResource = client.resource(apiresponse.get("baseurl"))
					     .path(RevbaseAPIURLs.EDITGROUP+apiresponse.get("groupId"));
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get("accesstoken"))
					   .put(ClientResponse.class,creategroupJSON.toString());
	 	  
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Edit Group API JSON  :::: " + responseFlag);
				   editgroupapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   editgroupapiresponse.put("httpCode", Integer.toString(response.getStatus()));
			
				   editgroupapiresponse.put("statusInfo", response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   responseFlag = response.getEntity(String.class);
				   logger.info("Edit Group API JSON  :::: " + responseFlag);
				   editgroupapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   editgroupapiresponse.put("httpCode", Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   responseFlag = response.getEntity(String.class);
				   logger.info("Edit Group API JSON  :::: " + responseFlag);
				   editgroupapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   editgroupapiresponse.put("httpCode", Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   responseFlag = response.getEntity(String.class);
				   logger.info("Edit Group API JSON  :::: " + responseFlag);
				   editgroupapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   editgroupapiresponse.put("httpCode", Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   responseFlag = response.getEntity(String.class);
				   logger.info("Edit Group API JSON  :::: " + responseFlag);
				   editgroupapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   editgroupapiresponse.put("httpCode", Integer.toString(response.getStatus()));
				   editgroupapiresponse.put(IAPIConstantCodes.APIResponseJsonErrorCode, JsonPath.parse(responseFlag).read("$.code").toString());
				   editgroupapiresponse.put(IAPIConstantCodes.APIResponseJsonErrorDetail, JsonPath.parse(responseFlag).read("$.detail").toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   responseFlag = response.getEntity(String.class);
				   logger.info("Edit Group API JSON  :::: " + responseFlag);
				   editgroupapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   editgroupapiresponse.put("httpCode", Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return editgroupapiresponse;

	}

	 
}