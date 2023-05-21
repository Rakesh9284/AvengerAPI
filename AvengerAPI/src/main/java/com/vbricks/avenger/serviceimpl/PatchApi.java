package com.vbricks.avenger.serviceimpl;

import java.util.HashMap;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbrick.avenger.apibeans.CreateEventBean;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateEventService;
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
public class PatchApi {
	
	private static Logger logger = Logger.getLogger(PatchApi.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> EditEventapiresponse = new HashMap<String, String>();
	 
	public HashMap<String, String> EditEvent(HashMap<String, String> apiresponse,CreateEventBean addeventApiBean) {
		String responseFlag = null;
		try {
			client = Client.create();
			webResource = client.resource(apiresponse.get("baseurl"))
					     .path(RevbaseAPIURLs.Event1+apiresponse.get("eventId"));
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get("accesstoken"))
					   .put(ClientResponse.class,createEventJSON(addeventApiBean));
	 	
	 		
	 		if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Edit Event API JSON  :::: " + responseFlag);
				   EditEventapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   EditEventapiresponse.put("httpCode", Integer.toString(response.getStatus()));
			       EditEventapiresponse.put("statusInfo", response.getStatusInfo().toString());
			       
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   responseFlag = response.getEntity(String.class);
				   logger.info("Edit Event API JSON  :::: " + responseFlag);
				   EditEventapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   EditEventapiresponse.put("httpCode", Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   responseFlag = response.getEntity(String.class);
				   logger.info("Edit Event API JSON  :::: " + responseFlag);
				   EditEventapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   EditEventapiresponse.put("httpCode", Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   responseFlag = response.getEntity(String.class);
				   logger.info("Edit Event API JSON  :::: " + responseFlag);
				   EditEventapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   EditEventapiresponse.put("httpCode", Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   responseFlag = response.getEntity(String.class);
				   logger.info("Edit Event API JSON  :::: " + responseFlag);
				   EditEventapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   EditEventapiresponse.put("httpCode", Integer.toString(response.getStatus()));
				   EditEventapiresponse.put(IAPIConstantCodes.APIResponse, responseFlag);
				   EditEventapiresponse.put(IAPIConstantCodes.APIResponseJsonErrorCode, JsonPath.parse(responseFlag).read("$.code").toString());
				   EditEventapiresponse.put(IAPIConstantCodes.APIResponseJsonErrorDetail, JsonPath.parse(responseFlag).read("$.detail").toString());
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   responseFlag = response.getEntity(String.class);
				   logger.info("Edit Event API JSON  :::: " + responseFlag);
				   EditEventapiresponse.put("baseurl", apiresponse.get("baseurl"));
				   EditEventapiresponse.put("httpCode", Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EditEventapiresponse;

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
					JSONArray userIds = new JSONArray();
					JSONArray groupIds = new JSONArray();
							
					
					if ((createteamApiBean.getPUserIds().length != 0 && createteamApiBean.getPUserIds() != null) ) {

						preproduction.put("userIds", createteamApiBean.getPUserIds());
						
												
					}
					
					if ((createteamApiBean.getPGroupIds().length != 0 && createteamApiBean.getPGroupIds() != null) ) {

						preproduction.put("groupIds", createteamApiBean.getPGroupIds());
												
					}
					
										
					// Adding Main JSON
					preproduction.put("duration",createteamApiBean.getDuration());
					createseventJson.put("preProduction", preproduction);
					
									
			}
			}


			// 17.accessControl
			if (!createteamApiBean.getAccessControl().isEmpty() && createteamApiBean.getAccessControl() != null)
			createseventJson.put(ICreateEventService.CreteEventaccessControl, createteamApiBean.getAccessControl());

			else
			createseventJson.remove(ICreateEventService.CreteEventaccessControl);
		

			System.out.println("Created Event   JSON :::: " + createseventJson);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return createseventJson.toString();

	}

}
