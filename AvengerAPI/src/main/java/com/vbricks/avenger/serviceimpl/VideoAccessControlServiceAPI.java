package com.vbricks.avenger.serviceimpl;


import java.util.HashMap;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbrick.avenger.apibeans.VideoAccessControlBean;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;


public class VideoAccessControlServiceAPI {
	private static Logger logger = Logger.getLogger(VideoAccessControlServiceAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;	 
	HashMap<String, String> videoaccesscontrolapiresponse = new HashMap<String, String>();
 
	/** 
	 * @author neslavath
	 * @functionName : VideoAccessControlService
	 * @Description : editVideoRating API - will able give ratings on mention video ID of the Video into the application 
	 * @param loginapiresponse (baseUrl,accessToken,VideoID
	 * @return editvideoratingapi  response(baseUrl,accessToken, apiresponse200OK)
	 */
	
	public HashMap<String, String> videoAccessControl(HashMap<String, String> apiresponse,org.json.simple.JSONObject accessControlEntities) {

		String responseFlag = null;
 		try {

			client = Client.create();
			webResource = client
								.resource(apiresponse.get("baseurl"))
								.path(RevbaseAPIURLs.VIDEOACCESSCONTROL1+apiresponse.get("videoId") + RevbaseAPIURLs.VIDEOACCESSCONTROL2);
			 logger.info("API Request as URL    :::: "+ webResource);
			response = webResource
								.accept(ApiResources.Content_Type)
								.type(ApiResources.Content_Type)
								.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get("accesstoken"))
								.put(ClientResponse.class,accessControlEntities.toString());
			
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
	 			   logger.info("Video AccessControl   API JSON Response :::: " + responseFlag);
				   videoaccesscontrolapiresponse.put("httpCode", Integer.toString(response.getStatus()));
				   videoaccesscontrolapiresponse.put("accesstoken", apiresponse.get("accesstoken"));
				   videoaccesscontrolapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   videoaccesscontrolapiresponse.put("videoId", apiresponse.get("videoId"));
				   videoaccesscontrolapiresponse.put("apiresponse", responseFlag);
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   responseFlag = response.getEntity(String.class);
		 		   logger.info("Delete Video API JSON Response :::: " + responseFlag);
				   videoaccesscontrolapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   videoaccesscontrolapiresponse.put("httpCode", Integer.toString(response.getStatus()));
				   videoaccesscontrolapiresponse.put("apiresponse", responseFlag);
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   responseFlag = response.getEntity(String.class);
		 		   logger.info("Delete Video API JSON Response :::: " + responseFlag);
				   videoaccesscontrolapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   videoaccesscontrolapiresponse.put("httpCode", Integer.toString(response.getStatus()));
				   videoaccesscontrolapiresponse.put("apiresponse", responseFlag);

			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) { 
				   responseFlag = response.getEntity(String.class);
	 		       logger.info("Delete Video API JSON Response :::: " + responseFlag);
				   
				   videoaccesscontrolapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   videoaccesscontrolapiresponse.put("httpCode", Integer.toString(response.getStatus()));
				   videoaccesscontrolapiresponse.put("apiresponse", responseFlag);

			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   responseFlag = response.getEntity(String.class);
		 		   logger.info("Delete Video API JSON Response :::: " + responseFlag);
				   videoaccesscontrolapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   videoaccesscontrolapiresponse.put("httpCode", Integer.toString(response.getStatus()));
				   videoaccesscontrolapiresponse.put("apiresponse", responseFlag);

			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   responseFlag = response.getEntity(String.class);
		 		   logger.info("Delete Video API JSON Response :::: " + responseFlag);
				   videoaccesscontrolapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   videoaccesscontrolapiresponse.put("httpCode", Integer.toString(response.getStatus()));
				   videoaccesscontrolapiresponse.put("apiresponse", responseFlag);

			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videoaccesscontrolapiresponse;
		 
}	
	/**
	 * @Description - Create JSON object Based on  VideoAccessControl permission  ..
	 * @param jsonFlag ,accesscontrolbeanPage
	 * @return JSONObject 
	 */
 
    @SuppressWarnings("unchecked")
    public JSONObject createJsonVideoAccessControl(VideoAccessControlBean accesscontrolbeagPage, String jsonFlag){
          
           JSONObject jsonaccesscontrol=new JSONObject();
           JSONArray accessControl=new JSONArray();
           JSONObject jsobobjectUser =new JSONObject();
           JSONObject jsobobjectGroup =new JSONObject();
           JSONObject jsobobjectTeam =new JSONObject();
           if(jsonFlag.equalsIgnoreCase("User")){
                  jsobobjectUser.put("canEdit", accesscontrolbeagPage.getUsercanEdit());
                  jsobobjectUser.put("id",  accesscontrolbeagPage.getUserid());
                  jsobobjectUser.put("type",  accesscontrolbeagPage.getUsertype());
                  accessControl.add(jsobobjectUser);
                  jsonaccesscontrol.put("AccessControlEntities", accessControl);
           }
           else if(jsonFlag.equalsIgnoreCase("Group")){
                  jsobobjectGroup.put("canEdit", accesscontrolbeagPage.getGroupcanEdit());
                  jsobobjectGroup.put("id", accesscontrolbeagPage.getGroupid());
                  jsobobjectGroup.put("type", accesscontrolbeagPage.getGrouptype());
                  accessControl.add(jsobobjectGroup);
                  jsonaccesscontrol.put("AccessControlEntities", accessControl);
           }
           else if(jsonFlag.equalsIgnoreCase("Team")){
                  jsobobjectTeam.put("canEdit", accesscontrolbeagPage.getTeamcanEdit());
                  jsobobjectTeam.put("id",accesscontrolbeagPage.getTeamid());
                  jsobobjectTeam.put("name",accesscontrolbeagPage.getTeamName());
                  jsobobjectTeam.put("type", accesscontrolbeagPage.getTeamtype());
                  accessControl.add(jsobobjectTeam);
                  jsonaccesscontrol.put("AccessControlEntities", accessControl);
           }
           else if(jsonFlag.equalsIgnoreCase("All")){

                  jsobobjectUser.put("canEdit", accesscontrolbeagPage.getUsercanEdit());
                  jsobobjectUser.put("id",  accesscontrolbeagPage.getUserid());
                  jsobobjectUser.put("type",  accesscontrolbeagPage.getUsertype());
                  
                  jsobobjectGroup.put("canEdit", accesscontrolbeagPage.getGroupcanEdit());
                  jsobobjectGroup.put("id", accesscontrolbeagPage.getGroupid());
                  jsobobjectGroup.put("type", accesscontrolbeagPage.getGrouptype());
                  
                  jsobobjectTeam.put("canEdit", accesscontrolbeagPage.getTeamcanEdit());
                  jsobobjectTeam.put("id",accesscontrolbeagPage.getTeamid());
                  jsobobjectTeam.put("type", accesscontrolbeagPage.getGrouptype());
                  
                  accessControl.add(jsobobjectUser);
                  accessControl.add(jsobobjectGroup);
                  accessControl.add(jsobobjectTeam);
                  jsonaccesscontrol.put("AccessControlEntities", accessControl);
                  
           }
           
           else{
                  logger.info("Invalid Json Falg parameter !!!");
           }
           logger.info("createdJsonVideoAccessControl :::: "+jsonaccesscontrol);
           return jsonaccesscontrol;
    }
           

		
		 
}

