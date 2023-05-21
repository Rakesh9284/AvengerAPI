package com.vbricks.avenger.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbrick.avenger.apibeans.AddVideoLinkBean;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IAddVideoLinkService;
import com.vbricks.avenger.service.ICreateEventService;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;
import com.vbricks.avenger.utils.VideoUploaderClient;

 

public class AddVideoLinkAPI implements IAddVideoLinkService{
	private ClientResponse response;
	private Client client;
	private WebResource webResource;
 
	public HashMap<String, String> loginUser;
 
	 

	private static Logger logger = Logger.getLogger(AddVideoLinkAPI.class);
	String charset = "UTF-8";
	HashMap<String, String> addvideolinkapiresponse;

	/**
	 * - AV-6015 /api/uploads/videos - This method will Upload Video File
	 * 
	 * @param loginapiresponseMap
	 *            
	 * @param addvideolinkbean  
	 *            
	 * @return HashMap -which consist of API Response and HTTPStatusCode and BaseURL
	 *         
	 */
	 	
	public HashMap<String, String> addVideolink(HashMap<String, String> apiresponse,AddVideoLinkBean addvideolinkbean)  {
		String responseFlag = null;

		try {

			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl)).path(RevbaseAPIURLs.GETVideo);
            logger.info("API Request as URL    :::: "+ webResource);
            
            response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .post(ClientResponse.class,AddvideolinkJSON(addvideolinkbean));
          
            addvideolinkapiresponse =new  HashMap<String, String>();
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				 Object obj=JSONValue.parse(responseFlag); 
                 JSONObject jsonobject=(JSONObject)obj;
               
				addvideolinkapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				addvideolinkapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				addvideolinkapiresponse.put(IAPIConstantCodes.APIVIDEOID,jsonobject.get(IAPIConstantCodes.APIVIDEOID).toString());
				addvideolinkapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				addvideolinkapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				 
				
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				addvideolinkapiresponse.put("InvalidUserNamePassword", response.getEntity(String.class));
				addvideolinkapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				addvideolinkapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				addvideolinkapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				addvideolinkapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				addvideolinkapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				addvideolinkapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			}else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				addvideolinkapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				addvideolinkapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				addvideolinkapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				addvideolinkapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Response contains:::::::::"+addvideolinkapiresponse);
		return addvideolinkapiresponse;

	}

		
	@SuppressWarnings("unchecked")
	private String AddvideolinkJSON(AddVideoLinkBean addvideolinkbean) {

		JSONObject videolinkJson = new JSONObject();
		
		
		try {
			// 1.Title
			if (addvideolinkbean.getTitle() != null && !addvideolinkbean.getTitle().isEmpty())
				videolinkJson.put("title", addvideolinkbean.getTitle());


			// 2.Description
			if (addvideolinkbean.getDescription() != null && !addvideolinkbean.getDescription().isEmpty())
				videolinkJson.put("description", addvideolinkbean.getDescription());


			// 3.EnableComments
			if (addvideolinkbean.getEnableComments() != null && !addvideolinkbean.getEnableComments().isEmpty())
				videolinkJson.put("enableComments", addvideolinkbean.getEnableComments());


			// 4.EnableDownloads
			if (addvideolinkbean.getEnableDownloads() != null && ! addvideolinkbean.getEnableDownloads().isEmpty())
				videolinkJson.put("enableDownloads", addvideolinkbean.getEnableDownloads());


			// 5.EnableRating
			if (addvideolinkbean.getEnableRatings() != null && ! addvideolinkbean.getEnableRatings().isEmpty())
				videolinkJson.put("enableRatings", addvideolinkbean.getEnableRatings());


			// 6.VidoeUploader
			if (addvideolinkbean.getUploader() != null && ! addvideolinkbean.getUploader().isEmpty())
				videolinkJson.put("uploader", addvideolinkbean.getUploader());


			// 7.IsActive
			if (addvideolinkbean.getIsActive() != null && ! addvideolinkbean.getIsActive().isEmpty())
				videolinkJson.put("isActive", addvideolinkbean.getIsActive());


			// 8.VideosAccessControl and access control entities
			if (addvideolinkbean.getVideoAccessControl()!= null && ! addvideolinkbean.getVideoAccessControl().isEmpty()) {
				
				videolinkJson.put("VideoAccessControl", addvideolinkbean.getVideoAccessControl());
				
				if(addvideolinkbean.getVideoAccessControl().equals("Channels")) {
					
					
					// Set atleast one team through AccessControlEntities
										
					JSONObject accesscontrolforteam = new JSONObject();
					
					if (!addvideolinkbean.getAccesscontrolforteam().isEmpty() && addvideolinkbean.getAccesscontrolforteam() != null) {

						accesscontrolforteam.put("name", addvideolinkbean.getAccesscontrolforteam());
						accesscontrolforteam.put("type", "Channel");
						accesscontrolforteam.put("canEdit", "true");
						//accesscontrolforteam.put("id", addvideolinkbean.getTeamid());
					
					}
					
														
					// Adding Single ArrayJSON
					JSONArray AccessControlEntitiesArray = new JSONArray();
					AccessControlEntitiesArray.add(accesscontrolforteam);

					// Adding Main JSON
					videolinkJson.put("AccessControlEntities", AccessControlEntitiesArray);
								
				}

				
				else	if(addvideolinkbean.getVideoAccessControl().equals("Channels1")) {
						
						// Set atleast one team through AccessControlEntities
						videolinkJson.put("VideoAccessControl","Channels");			
						JSONObject accesscontrolforteam = new JSONObject();
						JSONObject accesscontrolforgroup = new JSONObject();
						JSONArray AccessControlEntitiesArray = new JSONArray();
						
						if (!addvideolinkbean.getAccesscontrolforteam().isEmpty() && addvideolinkbean.getAccesscontrolforteam() != null) {

							accesscontrolforteam.put("name", addvideolinkbean.getAccesscontrolforteam());
							accesscontrolforteam.put("type", "Channel");
							accesscontrolforteam.put("canEdit", "true");
							AccessControlEntitiesArray.add(accesscontrolforteam);
													   			
						}
						
						if(addvideolinkbean.getAccesscontrolforgroup() != null && !addvideolinkbean.getAccesscontrolforgroup().isEmpty()) {

							accesscontrolforgroup.put("name", addvideolinkbean.getAccesscontrolforgroup());
							accesscontrolforgroup.put("type", "Channel");
							accesscontrolforgroup.put("canEdit", "true");
							AccessControlEntitiesArray.add(accesscontrolforgroup);
						} 
						
						// Adding Main JSON
						videolinkJson.put("AccessControlEntities", AccessControlEntitiesArray);
									
					}
				
				else if(addvideolinkbean.getVideoAccessControl().equals("Public")) {
					
					videolinkJson.remove("AccessControlEntities");
								
		
				}
				
				else if(addvideolinkbean.getVideoAccessControl().equals("Public1")) {
					
					videolinkJson.put("VideoAccessControl","Public");			
					videolinkJson.put("Password", "pwdispwd");
								
				}
					
						
				else if(addvideolinkbean.getVideoAccessControl().equals("Private")) {
					
					//both user and group have canedit as true and we are going by name here
					
					JSONObject accesscontrolforuser = new JSONObject();
					JSONObject accesscontrolforgroup = new JSONObject();
					JSONArray AccessControlEntitiesArray = new JSONArray();
					
					
					if ((!addvideolinkbean.getAccesscontrolforuser().isEmpty() && addvideolinkbean.getAccesscontrolforuser() != null) ) {

						accesscontrolforuser.put("name", addvideolinkbean.getAccesscontrolforuser());
						accesscontrolforuser.put("type", "User");
						accesscontrolforuser.put("canEdit", "true");
						//accesscontrolforuser.put("id", addvideolinkbean.getAccesscontrolforuser());
						AccessControlEntitiesArray.add(accesscontrolforuser);
						
					}
					
					if ((!addvideolinkbean.getAccesscontrolforgroup().isEmpty() && addvideolinkbean.getAccesscontrolforgroup() != null) ) {
						
						accesscontrolforgroup.put("name", addvideolinkbean.getAccesscontrolforgroup());
						accesscontrolforgroup.put("type", "Group");
						accesscontrolforgroup.put("canEdit", "true");
						//accesscontrolforuser.put("id", addvideolinkbean.getTeamid());
						AccessControlEntitiesArray.add(accesscontrolforgroup);
					} 
					
										
					// Adding Main JSON
					videolinkJson.put("AccessControlEntities", AccessControlEntitiesArray);
								
				
					
				}
				
				else if(addvideolinkbean.getVideoAccessControl().equals("Private1")) {
					
					//both user and group have canedit as true and we are going by id here
					
					videolinkJson.put("VideoAccessControl","Private");
					JSONObject accesscontrolforuser = new JSONObject();
					JSONObject accesscontrolforgroup = new JSONObject();
					JSONArray AccessControlEntitiesArray = new JSONArray();
					
					
					if ((!addvideolinkbean.getAccesscontrolforuser().isEmpty() && addvideolinkbean.getAccesscontrolforuser() != null) ) {

						//accesscontrolforuser.put("name", addvideolinkbean.getAccesscontrolforuser());
						accesscontrolforuser.put("type", "User");
						accesscontrolforuser.put("canEdit", "true");
						accesscontrolforuser.put("id", addvideolinkbean.getUserId());
						AccessControlEntitiesArray.add(accesscontrolforuser);
						
					}
					
					if ((!addvideolinkbean.getAccesscontrolforgroup().isEmpty() && addvideolinkbean.getAccesscontrolforgroup() != null) ) {
						
						//accesscontrolforgroup.put("name", addvideolinkbean.getAccesscontrolforgroup());
						accesscontrolforgroup.put("type", "Group");
						accesscontrolforgroup.put("canEdit", "true");
						accesscontrolforgroup.put("id", addvideolinkbean.getGroupId());
						AccessControlEntitiesArray.add(accesscontrolforgroup);
					} 

					
					// Adding Main JSON
					videolinkJson.put("AccessControlEntities", AccessControlEntitiesArray);
													
				
				}
					
				else if(addvideolinkbean.getVideoAccessControl().equals("Private2")) {
					
					//both user and group have canedit as false and we are going by name here
					
					videolinkJson.put("VideoAccessControl","Private");
					JSONObject accesscontrolforuser = new JSONObject();
					JSONObject accesscontrolforgroup = new JSONObject();
					JSONArray AccessControlEntitiesArray = new JSONArray();
	
					
					if ((!addvideolinkbean.getAccesscontrolforuser().isEmpty() && addvideolinkbean.getAccesscontrolforuser() != null) ) {
						
						accesscontrolforuser.put("name", addvideolinkbean.getAccesscontrolforuser());
						accesscontrolforuser.put("type", "User");
						accesscontrolforuser.put("canEdit", "false");
						//accesscontrolforuser.put("id", addvideolinkbean.getTeamid());
						AccessControlEntitiesArray.add(accesscontrolforuser);
						
					}
					else if ((!addvideolinkbean.getAccesscontrolforgroup().isEmpty() && addvideolinkbean.getAccesscontrolforgroup() != null) ) {
						
						accesscontrolforgroup.put("name", addvideolinkbean.getAccesscontrolforgroup());
						accesscontrolforgroup.put("type", "Group");
						accesscontrolforgroup.put("canEdit", "false");
						//accesscontrolforuser.put("id", addvideolinkbean.getTeamid());
						AccessControlEntitiesArray.add(accesscontrolforgroup);
					} 
					
														
					// Adding Main JSON
					videolinkJson.put("AccessControlEntities", AccessControlEntitiesArray);
							
													
				}

				
				else if(addvideolinkbean.getVideoAccessControl().equals("All Users")) {
					
					videolinkJson.put("VideoAccessControl","AllUsers");
					videolinkJson.remove("AccessControlEntities");
					
					}
					
			}
			
						
			//9. Tags
			JSONArray jsonarryTag = new JSONArray();
			
			if (addvideolinkbean.getTags() != null && addvideolinkbean.getTags().length != 0) {

				for (String tag : addvideolinkbean.getTags()) {
					jsonarryTag.add(tag);
				}
				videolinkJson.put("tags", jsonarryTag);
			}


			//10.Categories
			JSONArray jsonarrycategories = new JSONArray();
			if ( addvideolinkbean.getCategories() != null) {

				for (String categorie : addvideolinkbean.getCategories()) {
					jsonarrycategories.add(categorie);
				}
				videolinkJson.put("Categories", jsonarrycategories);
			}


			//11. CategoryIds
			JSONArray jsonarryCategoryIds = new JSONArray();
			if ( addvideolinkbean.getCategoryIds() != null) {

				for (String categoriesId : addvideolinkbean.getCategoryIds()) {
					jsonarryCategoryIds.add(categoriesId);
				}
				videolinkJson.put("CategoryIds", jsonarryCategoryIds);
			}


			// end CategoryIds
			
			// 12.Is360
			if (addvideolinkbean.getIs360() != null && ! addvideolinkbean.getIs360().isEmpty()) {
			videolinkJson.put("is360", addvideolinkbean.getIs360());
			}
			
			// 12.IsUnlisted
			if (addvideolinkbean.getUnlisted() != null && ! addvideolinkbean.getUnlisted().isEmpty()) {
			videolinkJson.put("unlisted", addvideolinkbean.getUnlisted());
			}
			
			// 13. CustomField
			if (addvideolinkbean.getCustomFieldId() != null && !addvideolinkbean.getCustomFieldId().isEmpty()) {
				
				JSONObject customFieldValue = new JSONObject();
				JSONArray customFieldArray = new JSONArray();
				
				customFieldValue.put("id",addvideolinkbean.getCustomFieldId() );
				customFieldValue.put("value",addvideolinkbean.getCustomFieldValue());
				
				customFieldArray.add(customFieldValue);
			
			videolinkJson.put("customFields", customFieldArray);
			
			}
			
			//end custom field
			
			
			//13.legacyViewCount
			if(addvideolinkbean.getLegacyViewCount()!=null)
			videolinkJson.put(IAPIConstantCodes.legacyViewCount,addvideolinkbean.getLegacyViewCount());
				
			//14.Owner
			JSONObject ownerJson=new JSONObject();
			
			if(addvideolinkbean.getOwner()=="Yes")
			
			{
			//14.1 userId
			if(addvideolinkbean.getownerUserId()!=null)
				ownerJson.put(IAPIConstantCodes.APIResponseUSERID,addvideolinkbean.getownerUserId());

			//14.2 username
			if(addvideolinkbean.getownerUserName()!=null)
				ownerJson.put(IAPIConstantCodes.APIUSERNAME, addvideolinkbean.getownerUserName());

		    //14.3 email
			if(addvideolinkbean.getownerEmail()!=null)
				ownerJson.put(IAPIConstantCodes.APIResponseEMAIL, addvideolinkbean.getownerEmail());
		 		
			videolinkJson.put(IAPIConstantCodes.owner,ownerJson);
		   }
		   
			//15.linkedurl
			JSONObject linkedurlJson=new JSONObject();
			
			if(addvideolinkbean.getlinkedurl()=="Yes")
			
			{
			//15.1 url
			if(addvideolinkbean.getUrl()!=null)
				linkedurlJson.put(url,addvideolinkbean.getUrl());

			//15.2 encodingType
			if(addvideolinkbean.getEncodingType()!=null)
				linkedurlJson.put(encodingtype, addvideolinkbean.getEncodingType());

		    //15.3 type
			if(addvideolinkbean.getType()!=null)
				linkedurlJson.put(type, addvideolinkbean.getType());
		 	
			//15.4 isMulticast
			if(addvideolinkbean.getIsMulticast()!=null)
			  linkedurlJson.put(isMulticast, addvideolinkbean.getIsMulticast());
		 	
			videolinkJson.put(linkedUrl,linkedurlJson);
		
		   }
		   
			
			
			System.out.println("Created Upload Video  JSON with access control entities:::: " + videolinkJson);

			
		} catch (Exception e) {
			e.printStackTrace();

		}
		return videolinkJson.toString();

	}
	
	 		
}
