package com.vbricks.avenger.serviceimpl;

import java.util.HashMap;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddUserApiBean;
import com.vbrick.avenger.apibeans.CreateEventBean;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateEventService;
import com.vbricks.avenger.service.IUserAccountsService;
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
public class CreateEventsAPI {
	
	private static Logger logger = Logger.getLogger(CreateEventsAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> createEventapiresponse = new HashMap<String, String>();
	 
	
	
	public JSONObject createEvent_Jsonobject(HashMap<String, String> apiresponse,CreateEventBean addUserApiBean ) {
		
		String responseFlag = null;
			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.EVENTURL);
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .post(ClientResponse.class,createEventJSON(addUserApiBean));
			
	 		responseFlag = response.getEntity(String.class);
	 	 	 Object obj=JSONValue.parse(responseFlag);    
	 	     JSONObject createEventapiresponse = (JSONObject) obj;
	 	    createEventapiresponse.put("statusInfo", response.getStatus());   
	 	    
	 	   logger.info(" Response API JSON  :::: " + createEventapiresponse);
	 	     return createEventapiresponse;
	
	
		}
	

	public HashMap<String, String> createEvent(HashMap<String, String> apiresponse,CreateEventBean addUserApiBean ) {
		String responseFlag = null;
		try {
			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.EVENTURL);
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .post(ClientResponse.class,createEventJSON(addUserApiBean));
	
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Create Event API JSON  :::: " + responseFlag);
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   createEventapiresponse.put(IUserAccountsService.EVENTID,  JsonPath.parse(responseFlag).read("$.eventId").toString());
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				    responseFlag = response.getEntity(String.class);
					   logger.info("Create Event API JSON  :::: " + responseFlag);
				   createEventapiresponse.put(IAPIConstantCodes.APIResponse, responseFlag);
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseJsonErrorCode, JsonPath.parse(responseFlag).read("$.code").toString());
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseJsonErrorDetail, JsonPath.parse(responseFlag).read("$.detail").toString());
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createEventapiresponse;

	}
	
	@SuppressWarnings("unchecked")
	public String createEventJSON(CreateEventBean createteamApiBean) {

		JSONObject createseventJson = new JSONObject();

		try {
			// 1.Title
			if (!createteamApiBean.getTitle().isEmpty() && createteamApiBean.getTitle() != null)
				createseventJson.put(ICreateEventService.Title, createteamApiBean.getTitle());

			else
				createseventJson.remove(ICreateEventService.Title);

			// 2.Description
			if (!createteamApiBean.getDescription().isEmpty() && createteamApiBean.getDescription() != null)
				createseventJson.put(ICreateEventService.Description, createteamApiBean.getDescription());

			else
				createseventJson.remove(ICreateEventService.Description);
			
			// 3.Estimated Attendees
			if (!createteamApiBean.getEstimatedAttendees().isEmpty() && createteamApiBean.getEstimatedAttendees() != null)
			createseventJson.put(ICreateEventService.EstimatedAttendees, createteamApiBean.getEstimatedAttendees());

			else
			createseventJson.remove("estimatedAttendees");
			
			// 3.Lobby time
			if (createteamApiBean.getLobbytime() != "No")
			createseventJson.put(ICreateEventService.Lobbytime, createteamApiBean.getLobbytime());

			else
			createseventJson.remove("lobbyTimeMinutes");
						
			// 4.StartDate
			if (!createteamApiBean.getStartDate().isEmpty() && createteamApiBean.getStartDate() != null)
				createseventJson.put(ICreateEventService.StartDate, createteamApiBean.getStartDate());

			else
				createseventJson.remove(ICreateEventService.StartDate);

			// 5.End Date
			if (!createteamApiBean.getEndDate().isEmpty() || createteamApiBean.getEndDate() != null)
				createseventJson.put(ICreateEventService.EndDate, createteamApiBean.getEndDate());

			else
				createseventJson.remove(ICreateEventService.EndDate);
			
			JSONArray jsonarryTag = new JSONArray();
			
			if (createteamApiBean.getTags() != null && createteamApiBean.getTags().length != 0) {

				for (String tag : createteamApiBean.getTags()) {
					jsonarryTag.add(tag);
				}
				createseventJson.put("tags", jsonarryTag);
			}
			
			//6. CategoryIds
			JSONArray jsonarryCategoryIds = new JSONArray();
			if ( createteamApiBean.getCategoryIds() != null) {

				for (String categoriesId : createteamApiBean.getCategoryIds()) {
					jsonarryCategoryIds.add(categoriesId);
				}
				createseventJson.put("categories", jsonarryCategoryIds);
			}
			
		
			// 7.EventAdminId
		
			JSONArray jsonEventAdminIds = new JSONArray();
			if (createteamApiBean.getEventAdminId() != null) {
				
				for (String EventAdminIds : createteamApiBean.getEventAdminId()) {
					jsonEventAdminIds.add(EventAdminIds);
				}
			
				createseventJson.put(ICreateEventService.CreateEventAdminIds, jsonEventAdminIds);
				
			}

			else
				createseventJson.remove("eventAdminIds");

			// 8.PresentationProfileId
			if (!createteamApiBean.getPresentationProfileId().isEmpty() && createteamApiBean.getPresentationProfileId() != null)
				createseventJson.put(ICreateEventService.PresentationProfileId, createteamApiBean.getPresentationProfileId());

			else
				createseventJson.remove(ICreateEventService.PresentationProfileId);
			
			// 8. Video Address as video source
			if (!createteamApiBean.getVideoAddress().isEmpty() && createteamApiBean.getVideoAddress() != null)
				createseventJson.put(ICreateEventService.VideoAddress, createteamApiBean.getVideoAddress());

			else
				createseventJson.remove(ICreateEventService.VideoAddress);

			// 7.AnonymousQuestionsEnabled
			if (!createteamApiBean.getAnonymousQuestionsEnabled().isEmpty() && createteamApiBean.getAnonymousQuestionsEnabled() != null)
				createseventJson.put(ICreateEventService.AnonymousQuestionsEnabled, createteamApiBean.getAnonymousQuestionsEnabled());

			else
				createseventJson.remove(ICreateEventService.AnonymousQuestionsEnabled);

			// 9.IsPublic
			 if (!createteamApiBean.getIsPublic().isEmpty() && createteamApiBean.getIsPublic() != null)
				 createseventJson.put(ICreateEventService.IsPublic, createteamApiBean.getIsPublic());

			else
				createseventJson.remove(ICreateEventService.IsPublic);
			 
			// 9.Unlisted
			 if (!createteamApiBean.getUnlisted().isEmpty() && createteamApiBean.getUnlisted() != null)
				 createseventJson.put(ICreateEventService.Unlisted, createteamApiBean.getUnlisted());

			else
				createseventJson.remove(ICreateEventService.Unlisted);
			 
			// 10.Shortcut
			 if (!createteamApiBean.getShortcut().isEmpty() && createteamApiBean.getShortcut() != null)
				 createseventJson.put(ICreateEventService.Shortcut, createteamApiBean.getShortcut());

			else
				createseventJson.remove(ICreateEventService.Shortcut);
			 
			// 9.PollsEnabled
			if (!createteamApiBean.getPollsEnabled().isEmpty() && createteamApiBean.getPollsEnabled() != null)
				createseventJson.put(ICreateEventService.PollsEnabled, createteamApiBean.getPollsEnabled());

			else
				createseventJson.remove(ICreateEventService.PollsEnabled);
			
			// 10.AutomatedWebcast
			if (!createteamApiBean.getAutomatedWebcast().isEmpty() && createteamApiBean.getAutomatedWebcast() != null)
				createseventJson.put(ICreateEventService.AutomatedWebcast, createteamApiBean.getAutomatedWebcast());

			else
				createseventJson.remove(ICreateEventService.AutomatedWebcast);
			
			// 11.ChatEnabled
			if (!createteamApiBean.getChatEnabled().isEmpty() && createteamApiBean.getChatEnabled() != null)
				createseventJson.put(ICreateEventService.ChatEnabled, createteamApiBean.getChatEnabled());

			else
				createseventJson.remove(ICreateEventService.ChatEnabled);
			
			// 12.QuestionAndAnswerEnabled
			if (!createteamApiBean.getQuestionAndAnswerEnabled().isEmpty() && createteamApiBean.getQuestionAndAnswerEnabled() != null)
				createseventJson.put(ICreateEventService.QuestionAndAnswerEnabled, createteamApiBean.getQuestionAndAnswerEnabled());

			else
				createseventJson.remove(ICreateEventService.QuestionAndAnswerEnabled);
						
		

			JSONArray jsonarryID = new JSONArray();
			//13. user ID

			if (createteamApiBean.getUserIds() != null) {

				for (String UserIds : createteamApiBean.getUserIds()) {
					jsonarryID.add(UserIds);
				}
				createseventJson.put(ICreateEventService.CreteEventUserIds, jsonarryID);
			}

			else
				createseventJson.remove("UserIds");

			
			// 14.ModeratorIds
			JSONArray jsonModeratorIds = new JSONArray();
			if (createteamApiBean.getModeratorIds() != null) {

				for (String ModeratorIds : createteamApiBean.getModeratorIds()) {
					jsonModeratorIds.add(ModeratorIds);
				}
				createseventJson.put(ICreateEventService.CreteEventModeratorIds, jsonModeratorIds);
			}

			else

					createseventJson.remove("ModeratorIds");

			
			// 15.GroupId
			JSONArray jsonGroupIds = new JSONArray();
			if (createteamApiBean.getGroupIds() != null) {

				for (String GroupIds : createteamApiBean.getGroupIds()) {
					jsonGroupIds.add(GroupIds);
				}
				createseventJson.put(ICreateEventService.CreteEventGroupIds, jsonGroupIds);
			}

			else
				createseventJson.remove("GroupIds");
			
			//16. PreProduction settings
			
			if (createteamApiBean.getPreproduction()!= null && ! createteamApiBean.getPreproduction().isEmpty()) {
				
										
					if(createteamApiBean.getPreproduction().equals("Yes")) {
						
					
													
					// Set duration,userids and groupids for preproduction
													
					JSONObject preproduction = new JSONObject();
					JSONArray puserIds = new JSONArray();
					JSONArray pgroupIds = new JSONArray();
							
					
					if (createteamApiBean.getPUserIds() != null) {

						for (String PUserIds : createteamApiBean.getPUserIds()) {
							puserIds.add(PUserIds);
						}
						preproduction.put(ICreateEventService.CreteEventPUserIds, puserIds);
					}

					else
						preproduction.remove("UserIds");
					
					
					if (createteamApiBean.getPGroupIds() != null) {

						for (String PGroupIds : createteamApiBean.getPGroupIds()) {
							pgroupIds.add(PGroupIds);
						}
						preproduction.put(ICreateEventService.CreteEventPGroupIds, pgroupIds);
					}

					else
						preproduction.remove("GroupIds");
					
					
					if (createteamApiBean.getDuration() != "No")
						preproduction.put(ICreateEventService.duration, createteamApiBean.getDuration());

					else
						preproduction.remove("duration");			
					
					
					// Adding Main JSON
					//preproduction.put("duration",createteamApiBean.getDuration());
					createseventJson.put("preProduction", preproduction);	
									
			}		
			}


			// 17.accessControl
			if (!createteamApiBean.getAccessControl().isEmpty() && createteamApiBean.getAccessControl() != null)
			createseventJson.put(ICreateEventService.CreteEventaccessControl, createteamApiBean.getAccessControl());

			else
			createseventJson.remove(ICreateEventService.CreteEventaccessControl);
		
			//18.Push Content 
			if (createteamApiBean.getPushContent()!= null && ! createteamApiBean.getPushContent().isEmpty()) {
				
				
				if(createteamApiBean.getPushContent().equals("Yes")) {
					
				
												
				// Setpush
												
				JSONObject pushcontent = new JSONObject();
				JSONArray contentlink = new JSONArray();
						
				
				if (createteamApiBean.getIsEnabled()!= null && ! createteamApiBean.getIsEnabled().isEmpty() ) {

					pushcontent.put("isEnabled", createteamApiBean.getIsEnabled());
					
											
				}
				/*if ((createteamApiBean.getContentLinks().length != 0 && createteamApiBean.getContentLinks() != null) ) {

					pushcontent.put(contentlink, createteamApiBean.getContentLinks());
											
				}*/
				
									
				// Adding Main JSONS
				pushcontent.put("isEnabled",createteamApiBean.getIsEnabled());
				createseventJson.put("pushcontent", pushcontent);
				
								
		}
		}

			//19.Embedded Content
 
			if (createteamApiBean.getEmbeddedContent()!= null && ! createteamApiBean.getEmbeddedContent().isEmpty()) {
				
				
				if(createteamApiBean.getEmbeddedContent().equals("Yes")) {
					
				
												
				// Setpush
												
				JSONObject embeddedcontent = new JSONObject();
				JSONArray contentlink = new JSONArray();
						
				
				if (createteamApiBean.getIsEnabled()!= null && ! createteamApiBean.getIsEnabled().isEmpty() ) {

					embeddedcontent.put("isEnabled", createteamApiBean.getIsEnabled());
					
											
				}
				/*if ((createteamApiBean.getContentLinks().length != 0 && createteamApiBean.getContentLinks() != null) ) {

					embeddedcontent.put("contentLinks", createteamApiBean.getContentLinks());
					}*/
											
				
				
									
				// Adding Main JSON
				embeddedcontent.put("isEnabled",createteamApiBean.getIsEnabled());
				createseventJson.put("embeddedcontent", embeddedcontent);
			}	
								
		}
		
			
			System.out.println("Created Event   JSON :::: " + createseventJson);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return createseventJson.toString();

	}
	public HashMap<String, String> createEvent1(HashMap<String, String> apiresponse,CreateEventBean addUserApiBean ) {
		String responseFlag = null;
		try {
			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.EVENTURL);
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .post(ClientResponse.class,createEventJSON1(addUserApiBean));
	
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Create Event API JSON  :::: " + responseFlag);
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));		
				   createEventapiresponse.put(IUserAccountsService.EVENTID,  JsonPath.parse(responseFlag).read("$.eventId").toString());
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				    responseFlag = response.getEntity(String.class);
					   logger.info("Create Event API JSON  :::: " + responseFlag);
				   createEventapiresponse.put(IAPIConstantCodes.APIResponse, responseFlag);
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseJsonErrorCode, JsonPath.parse(responseFlag).read("$.code").toString());
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseJsonErrorDetail, JsonPath.parse(responseFlag).read("$.detail").toString());
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   createEventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createEventapiresponse;

	}
	
	@SuppressWarnings("unchecked")
	public String createEventJSON1(CreateEventBean createteamApiBean) {

		JSONObject createseventJson1 = new JSONObject();

		try {
			// 1.Title
			if (!createteamApiBean.getTitle().isEmpty() && createteamApiBean.getTitle() != null)
				createseventJson1.put(ICreateEventService.Title, createteamApiBean.getTitle());

			else
				createseventJson1.remove(ICreateEventService.Title);

			// 2.Description
			if (!createteamApiBean.getDescription().isEmpty() && createteamApiBean.getDescription() != null)
				createseventJson1.put(ICreateEventService.Description, createteamApiBean.getDescription());

			else
				createseventJson1.remove(ICreateEventService.Description);
			
			// 3.Estimated Attendees
			if (!createteamApiBean.getEstimatedAttendees().isEmpty() && createteamApiBean.getEstimatedAttendees() != null)
				createseventJson1.put(ICreateEventService.EstimatedAttendees, createteamApiBean.getEstimatedAttendees());

			else
				createseventJson1.remove("estimatedAttendees");
			
			// 3.Lobby time
			if (createteamApiBean.getLobbytime() != "No")
				createseventJson1.put(ICreateEventService.Lobbytime, createteamApiBean.getLobbytime());

			else
				createseventJson1.remove("lobbyTimeMinutes");
						
			// 4.StartDate
			if (!createteamApiBean.getStartDate().isEmpty() && createteamApiBean.getStartDate() != null)
				createseventJson1.put(ICreateEventService.StartDate, createteamApiBean.getStartDate());

			else
				createseventJson1.remove(ICreateEventService.StartDate);

			// 5.End Date
			if (!createteamApiBean.getEndDate().isEmpty() || createteamApiBean.getEndDate() != null)
				createseventJson1.put(ICreateEventService.EndDate, createteamApiBean.getEndDate());

			else
				createseventJson1.remove(ICreateEventService.EndDate);
			
			JSONArray jsonarryTag = new JSONArray();
			
			if (createteamApiBean.getTags() != null && createteamApiBean.getTags().length != 0) {

				for (String tag : createteamApiBean.getTags()) {
					jsonarryTag.add(tag);
				}
				createseventJson1.put("tags", jsonarryTag);
			}
			
			//6. CategoryIds
			JSONArray jsonarryCategoryIds = new JSONArray();
			if ( createteamApiBean.getCategoryIds() != null) {

				for (String categoriesId : createteamApiBean.getCategoryIds()) {
					jsonarryCategoryIds.add(categoriesId);
				}
				createseventJson1.put("categories", jsonarryCategoryIds);
			}
			
		
			// 7.EventAdminId
		
			JSONArray jsonEventAdminIds = new JSONArray();
			if (createteamApiBean.getEventAdminId() != null) {
				
				for (String EventAdminIds : createteamApiBean.getEventAdminId()) {
					jsonEventAdminIds.add(EventAdminIds);
				}
			
				createseventJson1.put(ICreateEventService.CreateEventAdminIds, jsonEventAdminIds);
				
			}

			else
				createseventJson1.remove("eventAdminIds");

			// 8. Video Address as video source
			if (!createteamApiBean.getVideoAddress().isEmpty() && createteamApiBean.getVideoAddress() != null)
				createseventJson1.put(ICreateEventService.VideoAddress, createteamApiBean.getVideoAddress());

			else
				createseventJson1.remove(ICreateEventService.VideoAddress);

			// 7.AnonymousQuestionsEnabled
			if (!createteamApiBean.getAnonymousQuestionsEnabled().isEmpty() && createteamApiBean.getAnonymousQuestionsEnabled() != null)
				createseventJson1.put(ICreateEventService.AnonymousQuestionsEnabled, createteamApiBean.getAnonymousQuestionsEnabled());

			else
				createseventJson1.remove(ICreateEventService.AnonymousQuestionsEnabled);

			// 9.IsPublic
			 if (!createteamApiBean.getIsPublic().isEmpty() && createteamApiBean.getIsPublic() != null)
				 createseventJson1.put(ICreateEventService.IsPublic, createteamApiBean.getIsPublic());

			else
				createseventJson1.remove(ICreateEventService.IsPublic);
			 
			// 9.Unlisted
			 if (!createteamApiBean.getUnlisted().isEmpty() && createteamApiBean.getUnlisted() != null)
				 createseventJson1.put(ICreateEventService.Unlisted, createteamApiBean.getUnlisted());

			else
				createseventJson1.remove(ICreateEventService.Unlisted);
			 
			// 10.Shortcut
			 if (!createteamApiBean.getShortcut().isEmpty() && createteamApiBean.getShortcut() != null)
				 createseventJson1.put(ICreateEventService.Shortcut, createteamApiBean.getShortcut());

			else
				createseventJson1.remove(ICreateEventService.Shortcut);
			 
			// 9.PollsEnabled
			if (!createteamApiBean.getPollsEnabled().isEmpty() && createteamApiBean.getPollsEnabled() != null)
				createseventJson1.put(ICreateEventService.PollsEnabled, createteamApiBean.getPollsEnabled());

			else
				createseventJson1.remove(ICreateEventService.PollsEnabled);
			
			// 10.AutomatedWebcast
			if (!createteamApiBean.getAutomatedWebcast().isEmpty() && createteamApiBean.getAutomatedWebcast() != null)
				createseventJson1.put(ICreateEventService.AutomatedWebcast, createteamApiBean.getAutomatedWebcast());

			else
				createseventJson1.remove(ICreateEventService.AutomatedWebcast);
			
			// 11.ChatEnabled
			if (!createteamApiBean.getChatEnabled().isEmpty() && createteamApiBean.getChatEnabled() != null)
				createseventJson1.put(ICreateEventService.ChatEnabled, createteamApiBean.getChatEnabled());

			else
				createseventJson1.remove(ICreateEventService.ChatEnabled);
			
			
			// 12.QuestionAndAnswerEnabled
			if (!createteamApiBean.getQuestionAndAnswerEnabled().isEmpty() && createteamApiBean.getQuestionAndAnswerEnabled() != null)
				createseventJson1.put(ICreateEventService.QuestionAndAnswerEnabled, createteamApiBean.getQuestionAndAnswerEnabled());

			else
				createseventJson1.remove(ICreateEventService.QuestionAndAnswerEnabled);
						
		

			JSONArray jsonarryID = new JSONArray();
			//13. user ID

			if (createteamApiBean.getUserIds() != null) {

				for (String UserIds : createteamApiBean.getUserIds()) {
					jsonarryID.add(UserIds);
				}
				createseventJson1.put(ICreateEventService.CreteEventUserIds, jsonarryID);
			}

			else
				createseventJson1.remove("UserIds");

			
			// 14.ModeratorIds
			JSONArray jsonModeratorIds = new JSONArray();
			if (createteamApiBean.getModeratorIds() != null) {

				for (String ModeratorIds : createteamApiBean.getModeratorIds()) {
					jsonModeratorIds.add(ModeratorIds);
				}
				createseventJson1.put(ICreateEventService.CreteEventModeratorIds, jsonModeratorIds);
			}

			else

				createseventJson1.remove("ModeratorIds");

			
			// 15.GroupId
			JSONArray jsonGroupIds = new JSONArray();
			if (createteamApiBean.getGroupIds() != null) {

				for (String GroupIds : createteamApiBean.getGroupIds()) {
					jsonGroupIds.add(GroupIds);
				}
				createseventJson1.put(ICreateEventService.CreteEventGroupIds, jsonGroupIds);
			}

			else
				createseventJson1.remove("GroupIds");
			
			//16. PreProduction settings
			
			if (createteamApiBean.getPreproduction()!= null && ! createteamApiBean.getPreproduction().isEmpty()) {
				
										
					if(createteamApiBean.getPreproduction().equals("Yes")) {
						
					
													
					// Set duration,userids and groupids for preproduction
													
					JSONObject preproduction = new JSONObject();
					JSONArray userIds = new JSONArray();
					JSONArray groupIds = new JSONArray();
							
					
					if ((createteamApiBean.getPUserIds().length != 0 && createteamApiBean.getPUserIds() != null) ) {

						preproduction.put("userIds", createteamApiBean.getPUserIds());
						
												
					}
					
					if ((createteamApiBean.getPGroupIds().length != 0 && createteamApiBean.getPGroupIds() != null) ) {

						preproduction.put("groupIds", createteamApiBean.getPGroupIds());
												
					}
					
					if (createteamApiBean.getDuration() != "No")
						createseventJson1.put(ICreateEventService.duration, createteamApiBean.getDuration());

					else
						createseventJson1.remove("duration");			
					
					
					// Adding Main JSON
					preproduction.put("duration",createteamApiBean.getDuration());
					createseventJson1.put("preProduction", preproduction);
					
									
			}
			}


			// 17.accessControl
			if (!createteamApiBean.getAccessControl().isEmpty() && createteamApiBean.getAccessControl() != null)
				createseventJson1.put(ICreateEventService.CreteEventaccessControl, createteamApiBean.getAccessControl());

			else
				createseventJson1.remove(ICreateEventService.CreteEventaccessControl);
		

			// 11.Video Source
			if (!createteamApiBean.getVideoSourceType().isEmpty() && createteamApiBean.getVideoSourceType() != null)
				createseventJson1.put(ICreateEventService.CreteEventvideosource, createteamApiBean.getVideoSourceType());

			else
				createseventJson1.remove(ICreateEventService.CreteEventvideosource);
			
			// 5.End Date
						if (!createteamApiBean.getPresenterId().isEmpty() || createteamApiBean.getPresenterId() != null)
							createseventJson1.put(ICreateEventService.PresenterId, createteamApiBean.getPresenterId());

						else
							createseventJson1.remove(ICreateEventService.PresenterId);
						
			
			System.out.println("Created Event   JSON :::: " + createseventJson1);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return createseventJson1.toString();

	}
	
}


