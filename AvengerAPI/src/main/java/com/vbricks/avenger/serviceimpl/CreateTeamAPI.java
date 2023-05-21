package com.vbricks.avenger.serviceimpl;

import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbrick.avenger.apibeans.AddTeamBean;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateTeam;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;
 
public class CreateTeamAPI implements ICreateTeam{
	private static Logger logger = Logger.getLogger(CreateTeamAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;	 
	HashMap<String, String> createteamapiresponse = new HashMap<String, String>();
 
	public HashMap<String, String> createTeam(HashMap<String, String> apiresponse, JSONObject categortJSON) {
	
		HashMap<String, String> createteamapiresponse = new HashMap<String, String>();
 
			String responseFlag = null;
	 		try {

				client = Client.create();
				webResource = client
									.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
									.path(RevbaseAPIURLs.CREATETEAMURL);
				 logger.info("API Request as URL      :::: "+ webResource);
				response = webResource
									.accept(ApiResources.Content_Type)
									.type(ApiResources.Content_Type)
									.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
									.post(ClientResponse.class,categortJSON.toString());
				 
				if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				    responseFlag = response.getEntity(String.class);
					   logger.info("Create Team API JSON Response :::: " + responseFlag);
					   createteamapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					   createteamapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
					   createteamapiresponse.put("channelId", JsonPath.parse(responseFlag).read("$.channelId").toString());
					   createteamapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   createteamapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
					   logger.info("statusInfo ::: "+response.getStatusInfo().toString());
					   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
					   
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
					   createteamapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   createteamapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					   
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
					   createteamapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   createteamapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
					   createteamapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   createteamapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
					   
					   responseFlag = response.getEntity(String.class);
					   logger.info("Edit Team API JSON  :::: " + responseFlag);
					   createteamapiresponse.put(IAPIConstantCodes.APIResponseJsonErrorCode, JsonPath.parse(responseFlag).read("$.code").toString());
					   createteamapiresponse.put(IAPIConstantCodes.APIResponseJsonErrorDetail, JsonPath.parse(responseFlag).read("$.detail").toString());
					   createteamapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   createteamapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
					   createteamapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   createteamapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else {
				    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				   }
			
			} catch (Exception e) {
				e.printStackTrace();
				  logger.info("Exceptions ::: " + e.getMessage());
			}
			return createteamapiresponse;
			 
 
	}

	//For creating a team with teammembers option and with user as team contributor and group as team admin
	@SuppressWarnings("unchecked")
	public JSONObject createTeamJson(AddTeamBean page) 
	{
		JSONObject teamJson = new JSONObject();
		
		//v2 changes so removing this as userids and groupids are deprecated

		//if (page.getUserids() != null && page.getUserids().length > 0)
			//teamJson.put("UserIds", createArrayJson(page.getUserids()));

		//if (page.getGroupids() != null && page.getGroupids().length > 0)
			//teamJson.put("GroupIds", createArrayJson(page.getGroupids()));

		if (page.getName() != "") {
			teamJson.put("Name", page.getName());
		}

		if (page.getDescription() != "") {
			teamJson.put("Description", page.getDescription());
		}
		
		// team enhancement changes for role types as part of 7.26 AV-20285
		
		if(page.isTeamMembers() == true) {
			
			JSONObject teammemberasuser = new JSONObject();
			JSONObject teammemberasgroup = new JSONObject();
			JSONArray teammembersArray = new JSONArray();
						
			JSONArray roleTypes = new JSONArray();

			if ((!page.getTeammemberasuser().isEmpty() && page.getTeammemberasuser() != null) ) {
				
						
				{
					
					teammemberasuser.put("id", page.getTeammemberasuser());
					teammemberasuser.put("type", "User");
					teammemberasuser.put("roleTypes", roleTypes.put("Contributor"));
					teammembersArray.put(teammemberasuser);

				}
				
						
			}
			
			
			if ((!page.getTeammemberasgroup().isEmpty() && page.getTeammemberasgroup() != null) ) {
				
			
			
				{
				
				roleTypes = new JSONArray();
				teammemberasgroup.put("id", page.getTeammemberasgroup());
				teammemberasgroup.put("type", "Group");
				//teammemberasgroup.put("isAdmin", "true");
				teammemberasgroup.put("roleTypes",roleTypes.put("Admin"));
				teammembersArray.put(teammemberasgroup);
				} 
			}
											
			// Adding Main JSON
			teamJson.put("members", teammembersArray);
						
		} 
		
		else if(page.isTeamMembers() == false) {
			
			teamJson.remove("members");
		}
		
		logger.info("createTeam JSON is ::: " +teamJson);

		return teamJson;
	}
	
	//For creating a team with teammembers option and with user as team admin and group as team contributor
	@SuppressWarnings("unchecked")
	public JSONObject createTeamJson2(AddTeamBean page) 
	{
		JSONObject teamJson = new JSONObject();

		if (page.getName() != "") {
			teamJson.put("Name", page.getName());
		}

		if (page.getDescription() != "") {
			teamJson.put("Description", page.getDescription());
		}
		
		// team enhancement changes for role types as part of 7.26 AV-20285
		
		if(page.isTeamMembers() == true) {
			
			JSONObject teammemberasuser = new JSONObject();
			JSONObject teammemberasgroup = new JSONObject();
			JSONArray teammembersArray = new JSONArray();
						
			JSONArray roleTypes = new JSONArray();

			if ((!page.getTeammemberasuser().isEmpty() && page.getTeammemberasuser() != null) ) {
				
						
				{
					
					teammemberasuser.put("id", page.getTeammemberasuser());
					teammemberasuser.put("type", "User");
					teammemberasuser.put("roleTypes",roleTypes.put("Admin"));
					teammembersArray.put(teammemberasuser);

				}
				
						
			}
			
			
			if ((!page.getTeammemberasgroup().isEmpty() && page.getTeammemberasgroup() != null) ) {
				
			
			
				{
				
				roleTypes = new JSONArray();
				teammemberasgroup.put("id", page.getTeammemberasgroup());
				teammemberasgroup.put("type", "Group");
				teammemberasgroup.put("roleTypes", roleTypes.put("Contributor"));
				teammembersArray.put(teammemberasgroup);
				} 

			}
											
			// Adding Main JSON
			teamJson.put("members", teammembersArray);
						
		} 
		
		else if(page.isTeamMembers() == false) {
			
			teamJson.remove("members");
		}
		
		logger.info("createTeam JSON is ::: " +teamJson);

		return teamJson;
	}
	
	//For creating a team with teammembers option and with only 1 user as team member
	@SuppressWarnings("unchecked")
	public JSONObject createTeamJson3(AddTeamBean page) 
	{
		JSONObject teamJson = new JSONObject();
		
		if (page.getName() != "") {
		teamJson.put("Name", page.getName());
		}

		if (page.getDescription() != "") {
		teamJson.put("Description", page.getDescription());
		}
			
		// team enhancement changes for role types as part of 7.26 AV-20285
			
		if(page.isTeamMembers() == true) {
				
			JSONObject teammemberasuser = new JSONObject();
			JSONObject teammemberasgroup = new JSONObject();
			JSONArray teammembersArray = new JSONArray();
							
			JSONArray roleTypes = new JSONArray();

			if ((!page.getTeammemberasuser().isEmpty() && page.getTeammemberasuser() != null) ) {
					
							
			{
						
			teammemberasuser.put("id", page.getTeammemberasuser());
			teammemberasuser.put("type", "User");
			teammemberasuser.put("roleTypes", roleTypes.put("Member"));
			teammembersArray.put(teammemberasuser);

			}
					
							
			}
				
															
			// Adding Main JSON
			teamJson.put("members", teammembersArray);
							
			} 
			
			else if(page.isTeamMembers() == false) {
				
				teamJson.remove("members");
			}
			
			logger.info("createTeam JSON is ::: " +teamJson);

			return teamJson;
		}
	
			
	@SuppressWarnings("unchecked")
	public JSONObject createTeamJson1(AddTeamBean page,String[] userIds) 
	{
		JSONObject teamJson = new JSONObject();

		//if (page.getUserids() != null && page.getUserids().length > 0)
			//teamJson.put("UserIds", createArrayJson(page.getUserids()));

		//if (page.getGroupids() != null && page.getGroupids().length > 0)
			//teamJson.put("GroupIds", createArrayJson(page.getGroupids()));

		if (page.getName() != "") {
			teamJson.put("Name", page.getName());
		}

		if (page.getDescription() != "") {
			teamJson.put("Description", page.getDescription());
		}
		
		// team enhancement changes for role types as part of 7.26 AV-20285
		
		if(page.isTeamMembers() == true) {
			
			JSONObject teammemberasuser = new JSONObject();
			JSONObject teammemberasgroup = new JSONObject();
			JSONArray teammembersArray = new JSONArray();
						
			JSONArray roleTypes = new JSONArray();

			if ((!page.getTeammemberasuser().isEmpty() && page.getTeammemberasuser() != null) ) {
				
						
				{
					//Condition with only roleType - TC (i.e no isAdmin)
					teammemberasuser.put("id", userIds[0]); //AA
					teammemberasuser.put("type", "User");
					teammemberasuser.put("roleTypes", roleTypes.put("Contributor"));
					teammembersArray.put(teammemberasuser);
				};
				
				{
					//initializing the same objects here instead of using different object to avoid performance issues
					//Condition with both roleType and isAdmin - RoleType should be given priority when both are set
					teammemberasuser = new JSONObject();
					roleTypes = new JSONArray();
					teammemberasuser.put("id", userIds[1]); //MV
					teammemberasuser.put("type", "User");
					//teammemberasuser.put("isAdmin", "true");
					teammemberasuser.put("roleTypes", roleTypes.put("Admin"));
					teammembersArray.put(teammemberasuser);

				};
				
				{
					//Condition with only isAdmin (true) and roleType Empty
					teammemberasuser = new JSONObject();
					roleTypes = new JSONArray();
					teammemberasuser.put("id", userIds[2]); //MA
					teammemberasuser.put("type", "User");
					//teammemberasuser.put("isAdmin","true");
					teammemberasuser.put("roleTypes", roleTypes.put("Member"));
					teammembersArray.put(teammemberasuser);
				}; 
				
				{
					//Condition with only roleType - TA (i.e no isAdmin)
					teammemberasuser = new JSONObject();
					roleTypes = new JSONArray();
					teammemberasuser.put("id", userIds[3]); //EH
					teammemberasuser.put("type", "User");
					teammemberasuser.put("roleTypes",roleTypes.put("Admin"));
					teammembersArray.put(teammemberasuser);
				}; 
				
							
			}
			
			
			if ((!page.getTeammemberasgroup().isEmpty() && page.getTeammemberasgroup() != null) ) {
				
						
				{
				
				//Condition with only isAdmin (false) , here remove roleType 
				roleTypes = new JSONArray();
				teammemberasgroup.put("id", page.getTeammemberasgroup());
				teammemberasgroup.put("type", "Group");
				//teammemberasgroup.put("isAdmin","false");
				teammemberasgroup.put("roleTypes",roleTypes);
				teammembersArray.put(teammemberasgroup);
				} 
			}
											
			// Adding Main JSON
			teamJson.put("members", teammembersArray);
						
		} 
		
		else if(page.isTeamMembers() == false) {
			
			teamJson.remove("members");
		}
		
		logger.info("createTeam JSON is ::: " +teamJson);

		return teamJson;
	}

		
		private JSONArray createArrayJson(String[] page){
		JSONArray teamarryJson=new JSONArray();
		for(int i=0;i<page.length;i++)
		{
			teamarryJson.put(page[i]);
		}
		return teamarryJson;
		 
		}
	
	 
	 
}
