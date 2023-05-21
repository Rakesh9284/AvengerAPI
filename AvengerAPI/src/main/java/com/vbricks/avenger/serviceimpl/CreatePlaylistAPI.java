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
import com.vbrick.avenger.apibeans.AddPlaylistBean;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreatePlaylist;
import com.vbricks.avenger.service.ICreateTeam;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;

/*
 * @author rajitha
 * @functionName : CreatePlaylist
 * @Description : CreatePlaylist API - will create a playlist through API 
 */

public class CreatePlaylistAPI implements ICreatePlaylist{
	
	private static Logger logger = Logger.getLogger(CreatePlaylistAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> createPlaylistAPIresponse = new HashMap<String, String>();
	

	
	public HashMap<String, String> createPlaylist(HashMap<String, String> apiresponse,JSONObject categortJSON ) {
		String responseFlag = null;
		try {
			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.PLAYLISTURL);
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .post(ClientResponse.class,categortJSON.toString());
	
	 		if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Create Playlist API JSON Response :::: " + responseFlag);
				   createPlaylistAPIresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   createPlaylistAPIresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				   createPlaylistAPIresponse.put("playlistId", JsonPath.parse(responseFlag).read("$.playlistId").toString());
				   createPlaylistAPIresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createPlaylistAPIresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
				   logger.info("statusInfo ::: "+response.getStatusInfo().toString());
				   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   createPlaylistAPIresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createPlaylistAPIresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   createPlaylistAPIresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createPlaylistAPIresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   createPlaylistAPIresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createPlaylistAPIresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   createPlaylistAPIresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createPlaylistAPIresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   createPlaylistAPIresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createPlaylistAPIresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
			  logger.info("Exceptions ::: " + e.getMessage());
		}
		return createPlaylistAPIresponse;

	}
	
	@SuppressWarnings("unchecked")
	public JSONObject createPlaylistJson(AddPlaylistBean page){
	JSONObject playlistJson=new JSONObject();
	playlistJson.put("Name",  page.getName());	
		
	if(page.getVideoids()!="")
	playlistJson.put("VideoIds",  createArrayJson(page.getVideoids()));	
	
	logger.info("createPlaylist JSON is ::: "+playlistJson);
	
	return playlistJson;
	}
	
		
	public JSONArray createArrayJson(String page1){
	JSONArray playlistarryJson=new JSONArray();
	playlistarryJson.put(page1);
	return playlistarryJson;
	 
	}

}
