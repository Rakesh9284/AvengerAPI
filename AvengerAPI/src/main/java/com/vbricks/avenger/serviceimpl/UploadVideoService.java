package com.vbricks.avenger.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.json.simple.JSONArray;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateEventService;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;
import com.vbricks.avenger.utils.VideoUploaderClient;

import net.sf.json.JSONObject;

public class UploadVideoService implements IUploadVideoService {
	private ClientResponse response;
	private Client client;
	private WebResource webResource;
 
	public HashMap<String, String> loginUser;
	public HashMap<String, String> editvideoapiresponse;
	public HashMap<String, String> editvideoapiresponse1;
	public HashMap<String, String> editvideoapiresponse2;
	public HashMap<String, String> getrolesapiresponse;
	HashMap<String, String> replaceVideo;

	private static Logger logger = Logger.getLogger(UploadVideoService.class);
	String charset = "UTF-8";
	HashMap<String, String> uploadvidoeapiresponse;

	/**
	 * - AV-6015 /api/uploads/videos - This method will Upload Video File
	 * 
	 * @param loginapiresponseMap
	 *            
	 * @param AddUploadVideoBean  
	 *            
	 * @return HashMap -which consist of API Response and HTTPStatusCode and BaseURL
	 *         
	 */
	public HashMap<String, String> uploadVideos(HashMap<String, String> loginapiresponse, String path,
			AddUploadVideoBean adduploadvideobean) {

		String projectDir = System.getProperty("user.dir");
		String requestURL = loginapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl) + RevbaseAPIURLs.UPLOADVIDEOURL;
		logger.info("loginapiresponse API URL :::: " + loginapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		logger.info("UPLOADVIDEOURL   URL :::: " + RevbaseAPIURLs.UPLOADVIDEOURL);
		logger.info("VideoUpload API URL :::: " + requestURL);
		String responseFlag = null;

		
		try {
			logger.info("File Location ###### :::: " + projectDir + path);
			File uploadFile = new File(projectDir + path);
			String fileName2 = uploadFile.getAbsolutePath();
			logger.info("@@@ file name is"+fileName2);
			logger.info("@@@@@@@ file name is"+uploadFile.getName());
			String filename = uploadFile.getName();
			
			VideoUploaderClient multipart = new VideoUploaderClient(requestURL, charset,loginapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
			if(loginapiresponse.get("Mandatory").equalsIgnoreCase("Yes")) {
			multipart.addFormField("Video", createVideoUploadJSONMandatory(adduploadvideobean));
			}
			else 
			{
			multipart.addFormField("Video", createVideoUploadJSON(adduploadvideobean));	
			}
			
			multipart.addFilePart("VideoFile", uploadFile,"video");
			logger.info(" file uploading ..........." );
			
			
			List<String> response = multipart.finish();
			uploadvidoeapiresponse = new HashMap<String, String>();
			logger.info("UploadVideo API HTTP status Code ::: "+response.get(0));

			logger.info("UploadVidoe API JSON Response ::: "+response.get(1));
			if (response.get(0).equals("HTTP StatusCode :200")) {
				logger.info("Video Upload Response ..@@@@@@::::");
				for (String line : response) {
					logger.info("Video uploaed API response :::" + line);

				}
				uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, loginapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				uploadvidoeapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, response.get(0));
				uploadvidoeapiresponse.put("videoId", VideoUploaderClient.parseJsonObject(response.get(1)));
				
				if (loginapiresponse.get("fileName").equalsIgnoreCase("Yes"))
				{
				uploadvidoeapiresponse.put("videoTitle", filename);
				}
				else
				{
					uploadvidoeapiresponse.put("videoTitle", adduploadvideobean.getTitle());	
				}
				
			} else {
				
				uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, response.get(0));
				uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseJsonErrorCode, VideoUploaderClient.parseJsonObject1(response.get(1)));
			}
			

		} catch (IOException ex) {
			System.out.println(ex);
			logger.info("while uploading video file Exception is :::: "+ex);

		} catch (JSONException e) {

			e.printStackTrace();
		}
		return uploadvidoeapiresponse;

	}

	
	public HashMap<String, String> editVideo(HashMap<String, String> apiresponse,AddUploadVideoBean addUploadVideoBean) {
		String responseFlag = null;

		try {

			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl)).path(RevbaseAPIURLs.UPDATEVIDEOURL+apiresponse.get("videoId"));
            logger.info("API Request as URL    :::: "+ webResource);
            
            response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .put(ClientResponse.class,createVideoUploadJSON1(addUploadVideoBean));
          
            editvideoapiresponse =new  HashMap<String, String>();
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				editvideoapiresponse.put("InvalidUserNamePassword", response.getEntity(String.class));
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseJsonErrorCode, JsonPath.parse(responseFlag).read("$.code").toString());
				
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			}else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return editvideoapiresponse;

	}

		
	@SuppressWarnings("unchecked")
	public String createVideoUploadJSON(AddUploadVideoBean adduploadvideobean) {

		JSONObject uploadvideoJson = new JSONObject();
		
		
		try {
			// 1.Title
			if (adduploadvideobean.getTitle() != null && !adduploadvideobean.getTitle().isEmpty())
				uploadvideoJson.put("title", adduploadvideobean.getTitle());


			// 2.Description
			if (adduploadvideobean.getDescription() != null && !adduploadvideobean.getDescription().isEmpty())
				uploadvideoJson.put("description", adduploadvideobean.getDescription());


			// 3.EnableComments
			if (adduploadvideobean.getEnableComments() != null && !adduploadvideobean.getEnableComments().isEmpty())
				uploadvideoJson.put("enableComments", adduploadvideobean.getEnableComments());


			// 4.EnableDownloads
			if (adduploadvideobean.getEnableDownloads() != null && ! adduploadvideobean.getEnableDownloads().isEmpty())
				uploadvideoJson.put("enableDownloads", adduploadvideobean.getEnableDownloads());


			// 5.EnableRating
			if (adduploadvideobean.getEnableRatings() != null && ! adduploadvideobean.getEnableRatings().isEmpty())
				uploadvideoJson.put("enableRatings", adduploadvideobean.getEnableRatings());


			// 6.VidoeUploader
			if (adduploadvideobean.getUploader() != null && ! adduploadvideobean.getUploader().isEmpty())
				uploadvideoJson.put("uploader", adduploadvideobean.getUploader());


			// 7.IsActive
			if (adduploadvideobean.getIsActive() != null && ! adduploadvideobean.getIsActive().isEmpty())
				uploadvideoJson.put("isActive", adduploadvideobean.getIsActive());


			// 8.VideosAccessControl and access control entities
			if (adduploadvideobean.getVideoAccessControl()!= null && ! adduploadvideobean.getVideoAccessControl().isEmpty()) {
				
				uploadvideoJson.put("VideoAccessControl", adduploadvideobean.getVideoAccessControl());
				
				if(adduploadvideobean.getVideoAccessControl().equals("Channels")) {
					
					
					// Set atleast one team through AccessControlEntities
										
					JSONObject accesscontrolforteam = new JSONObject();
					
					if (!adduploadvideobean.getAccesscontrolforteam().isEmpty() && adduploadvideobean.getAccesscontrolforteam() != null) {

						accesscontrolforteam.put("name", adduploadvideobean.getAccesscontrolforteam());
						accesscontrolforteam.put("type", "Channel");
						accesscontrolforteam.put("canEdit", "true");
						//accesscontrolforteam.put("id", adduploadvideobean.getTeamid());
					
					}
					
														
					// Adding Single ArrayJSON
					JSONArray AccessControlEntitiesArray = new JSONArray();
					AccessControlEntitiesArray.add(accesscontrolforteam);

					// Adding Main JSON
					uploadvideoJson.put("AccessControlEntities", AccessControlEntitiesArray);
								
				}

				
				else	if(adduploadvideobean.getVideoAccessControl().equals("Channels1")) {
						
						// Set atleast one team through AccessControlEntities
						uploadvideoJson.put("VideoAccessControl","Channels");			
						JSONObject accesscontrolforteam = new JSONObject();
						JSONObject accesscontrolforgroup = new JSONObject();
						JSONArray AccessControlEntitiesArray = new JSONArray();
						
						if (!adduploadvideobean.getAccesscontrolforteam().isEmpty() && adduploadvideobean.getAccesscontrolforteam() != null) {

							accesscontrolforteam.put("name", adduploadvideobean.getAccesscontrolforteam());
							accesscontrolforteam.put("type", "Channel");
							accesscontrolforteam.put("canEdit", "true");
							AccessControlEntitiesArray.add(accesscontrolforteam);
													   			
						}
						
						if(adduploadvideobean.getAccesscontrolforgroup() != null && !adduploadvideobean.getAccesscontrolforgroup().isEmpty()) {

							accesscontrolforgroup.put("name", adduploadvideobean.getAccesscontrolforgroup());
							accesscontrolforgroup.put("type", "Channel");
							accesscontrolforgroup.put("canEdit", "true");
							AccessControlEntitiesArray.add(accesscontrolforgroup);
						} 
						
						// Adding Main JSON
						uploadvideoJson.put("AccessControlEntities", AccessControlEntitiesArray);
									
					}
				
				else if(adduploadvideobean.getVideoAccessControl().equals("Public")) {
					
					uploadvideoJson.remove("AccessControlEntities");
								
		
				}
				
				else if(adduploadvideobean.getVideoAccessControl().equals("Public1")) {
					
					uploadvideoJson.put("VideoAccessControl","Public");			
					uploadvideoJson.put("Password", "pwdispwd");
								
				}
					
						
				else if(adduploadvideobean.getVideoAccessControl().equals("Private")) {
					
					//both user and group have canedit as true and we are going by name here
					
					JSONObject accesscontrolforuser = new JSONObject();
					JSONObject accesscontrolforgroup = new JSONObject();
					JSONArray AccessControlEntitiesArray = new JSONArray();
					
					
					if ((!adduploadvideobean.getAccesscontrolforuser().isEmpty() && adduploadvideobean.getAccesscontrolforuser() != null) ) {

						accesscontrolforuser.put("name", adduploadvideobean.getAccesscontrolforuser());
						accesscontrolforuser.put("type", "User");
						accesscontrolforuser.put("canEdit", "true");
						//accesscontrolforuser.put("id", adduploadvideobean.getAccesscontrolforuser());
						AccessControlEntitiesArray.add(accesscontrolforuser);
						
					}
					
					if ((!adduploadvideobean.getAccesscontrolforgroup().isEmpty() && adduploadvideobean.getAccesscontrolforgroup() != null) ) {
						
						accesscontrolforgroup.put("name", adduploadvideobean.getAccesscontrolforgroup());
						accesscontrolforgroup.put("type", "Group");
						accesscontrolforgroup.put("canEdit", "true");
						//accesscontrolforuser.put("id", adduploadvideobean.getTeamid());
						AccessControlEntitiesArray.add(accesscontrolforgroup);
					} 
					
										
					// Adding Main JSON
					uploadvideoJson.put("AccessControlEntities", AccessControlEntitiesArray);
								
				
					
				}
				
				else if(adduploadvideobean.getVideoAccessControl().equals("Private1")) {
					
					//both user and group have canedit as true and we are going by id here
					
					uploadvideoJson.put("VideoAccessControl","Private");
					JSONObject accesscontrolforuser = new JSONObject();
					JSONObject accesscontrolforgroup = new JSONObject();
					JSONArray AccessControlEntitiesArray = new JSONArray();
					
					
					if ((!adduploadvideobean.getAccesscontrolforuser().isEmpty() && adduploadvideobean.getAccesscontrolforuser() != null) ) {

						//accesscontrolforuser.put("name", adduploadvideobean.getAccesscontrolforuser());
						accesscontrolforuser.put("type", "User");
						accesscontrolforuser.put("canEdit", "true");
						accesscontrolforuser.put("id", adduploadvideobean.getUserId());
						AccessControlEntitiesArray.add(accesscontrolforuser);
						
					}
					
					if ((!adduploadvideobean.getAccesscontrolforgroup().isEmpty() && adduploadvideobean.getAccesscontrolforgroup() != null) ) {
						
						//accesscontrolforgroup.put("name", adduploadvideobean.getAccesscontrolforgroup());
						accesscontrolforgroup.put("type", "Group");
						accesscontrolforgroup.put("canEdit", "true");
						accesscontrolforgroup.put("id", adduploadvideobean.getGroupId());
						AccessControlEntitiesArray.add(accesscontrolforgroup);
					} 

					
					// Adding Main JSON
					uploadvideoJson.put("AccessControlEntities", AccessControlEntitiesArray);
													
				
				}
					
				else if(adduploadvideobean.getVideoAccessControl().equals("Private2")) {
					
					//both user and group have canedit as false and we are going by name here
					
					uploadvideoJson.put("VideoAccessControl","Private");
					JSONObject accesscontrolforuser = new JSONObject();
					JSONObject accesscontrolforgroup = new JSONObject();
					JSONArray AccessControlEntitiesArray = new JSONArray();
	
					
					if ((!adduploadvideobean.getAccesscontrolforuser().isEmpty() && adduploadvideobean.getAccesscontrolforuser() != null) ) {
						
						accesscontrolforuser.put("name", adduploadvideobean.getAccesscontrolforuser());
						accesscontrolforuser.put("type", "User");
						accesscontrolforuser.put("canEdit", "false");
						//accesscontrolforuser.put("id", adduploadvideobean.getTeamid());
						AccessControlEntitiesArray.add(accesscontrolforuser);
						
					}
					else if ((!adduploadvideobean.getAccesscontrolforgroup().isEmpty() && adduploadvideobean.getAccesscontrolforgroup() != null) ) {
						
						accesscontrolforgroup.put("name", adduploadvideobean.getAccesscontrolforgroup());
						accesscontrolforgroup.put("type", "Group");
						accesscontrolforgroup.put("canEdit", "false");
						//accesscontrolforuser.put("id", adduploadvideobean.getTeamid());
						AccessControlEntitiesArray.add(accesscontrolforgroup);
					} 
					
														
					// Adding Main JSON
					uploadvideoJson.put("AccessControlEntities", AccessControlEntitiesArray);
							
													
				}

				
				else if(adduploadvideobean.getVideoAccessControl().equals("All Users")) {
					
					uploadvideoJson.put("VideoAccessControl","AllUsers");
					uploadvideoJson.remove("AccessControlEntities");
					
					}
					
			}
			
						
			//9. Tags
			JSONArray jsonarryTag = new JSONArray();
			
			if (adduploadvideobean.getTags() != null && adduploadvideobean.getTags().length != 0) {

				for (String tag : adduploadvideobean.getTags()) {
					jsonarryTag.add(tag);
				}
				uploadvideoJson.put("tags", jsonarryTag);
			}


			//10.Categories
			JSONArray jsonarrycategories = new JSONArray();
			if ( adduploadvideobean.getCategories() != null) {

				for (String categorie : adduploadvideobean.getCategories()) {
					jsonarrycategories.add(categorie);
				}
				uploadvideoJson.put("Categories", jsonarrycategories);
			}


			//11. CategoryIds
			JSONArray jsonarryCategoryIds = new JSONArray();
			if ( adduploadvideobean.getCategoryIds() != null) {

				for (String categoriesId : adduploadvideobean.getCategoryIds()) {
					jsonarryCategoryIds.add(categoriesId);
				}
				uploadvideoJson.put("CategoryIds", jsonarryCategoryIds);
			}


			// end CategoryIds
			
			// 12.Is360
			if (adduploadvideobean.getIs360() != null && ! adduploadvideobean.getIs360().isEmpty()) {
			uploadvideoJson.put("is360", adduploadvideobean.getIs360());
			}
			
			// 12.IsUnlisted
			if (adduploadvideobean.getUnlisted() != null && ! adduploadvideobean.getUnlisted().isEmpty()) {
			uploadvideoJson.put("unlisted", adduploadvideobean.getUnlisted());
			}
			
			// 13. CustomField
			if (adduploadvideobean.getCustomFieldId() != null && !adduploadvideobean.getCustomFieldId().isEmpty()) {
				
				JSONObject customFieldValue = new JSONObject();
				JSONArray customFieldArray = new JSONArray();
				
				customFieldValue.put("id",adduploadvideobean.getCustomFieldId() );
				customFieldValue.put("value",adduploadvideobean.getCustomFieldValue());
				
				customFieldArray.add(customFieldValue);
			
			uploadvideoJson.put("customFields", customFieldArray);
			
			}
			
			//end custom field
			
			// 13.PublishDate
			if (!adduploadvideobean.getPublishDate().isEmpty() && adduploadvideobean.getPublishDate() != null)
			uploadvideoJson.put("publishDate", adduploadvideobean.getPublishDate());
						
			// 14.ExpirationDate
			if (!adduploadvideobean.getExpirationDate().isEmpty() && adduploadvideobean.getExpirationDate() != null)
			uploadvideoJson.put("expirationDate", adduploadvideobean.getExpirationDate());

			// 15.ExpirationAction
			if (!adduploadvideobean.getExpirationAction().isEmpty() && adduploadvideobean.getExpirationAction() != null)
			uploadvideoJson.put("expirationAction", adduploadvideobean.getExpirationAction());

			// 16.WhenUploaded
			if (!adduploadvideobean.getWhenUploaded().isEmpty() && adduploadvideobean.getWhenUploaded() != null)
			uploadvideoJson.put("whenUploaded", adduploadvideobean.getWhenUploaded());
			
			//17.legacyViewCount
			if(adduploadvideobean.getLegacyViewCount()!=null)
			uploadvideoJson.put(IAPIConstantCodes.legacyViewCount,adduploadvideobean.getLegacyViewCount());
				
			System.out.println("Created Upload Video  JSON with access control entities:::: " + uploadvideoJson);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return uploadvideoJson.toString();

	}
	
	@SuppressWarnings("unchecked")
	public String createVideoUploadJSONMandatory(AddUploadVideoBean adduploadvideobean) {

		JSONObject uploadvideoJson = new JSONObject();
		
		
		try {
			// 1.Title
			if (adduploadvideobean.getTitle() != null && !adduploadvideobean.getTitle().isEmpty())
				uploadvideoJson.put("title", adduploadvideobean.getTitle());
			
			// 2.Description
			if (adduploadvideobean.getDescription() != null && !adduploadvideobean.getDescription().isEmpty())
				uploadvideoJson.put("description", adduploadvideobean.getDescription());

			// 3.EnableComments
			if (adduploadvideobean.getEnableComments() != null && !adduploadvideobean.getEnableComments().isEmpty())
				uploadvideoJson.put("enableComments", adduploadvideobean.getEnableComments());


			// 4.EnableDownloads
			if (adduploadvideobean.getEnableDownloads() != null && ! adduploadvideobean.getEnableDownloads().isEmpty())
				uploadvideoJson.put("enableDownloads", adduploadvideobean.getEnableDownloads());


			// 5.EnableRating
			if (adduploadvideobean.getEnableRatings() != null && ! adduploadvideobean.getEnableRatings().isEmpty())
				uploadvideoJson.put("enableRatings", adduploadvideobean.getEnableRatings());


			// 6.VidoeUploader
			if (adduploadvideobean.getUploader() != null && ! adduploadvideobean.getUploader().isEmpty())
				uploadvideoJson.put("uploader", adduploadvideobean.getUploader());


			// 7.IsActive
			if (adduploadvideobean.getIsActive() != null && ! adduploadvideobean.getIsActive().isEmpty())
				uploadvideoJson.put("isActive", adduploadvideobean.getIsActive());


			// 8.VideosAccessControl and access control entities
			if (adduploadvideobean.getVideoAccessControl()!= null && ! adduploadvideobean.getVideoAccessControl().isEmpty()) {
				
				uploadvideoJson.put("VideoAccessControl", adduploadvideobean.getVideoAccessControl());
				
				if(adduploadvideobean.getVideoAccessControl().equals("Channels")) {
					
					// Set atleast one team through AccessControlEntities
										
					JSONObject accesscontrolforteam = new JSONObject();
					
					if (adduploadvideobean.getAccesscontrolforteam() != null && !adduploadvideobean.getAccesscontrolforteam().isEmpty()) {

						//accesscontrolforteam.put("name", adduploadvideobean.getAccesscontrolforteam());
						accesscontrolforteam.put("type", "Channel");
						accesscontrolforteam.put("canEdit", "true");
						accesscontrolforteam.put("id", adduploadvideobean.getTeamid());
					
					}
									
					// Adding Single ArrayJSON
					JSONArray AccessControlEntitiesArray = new JSONArray();
					AccessControlEntitiesArray.add(accesscontrolforteam);

					// Adding Main JSON
					uploadvideoJson.put("AccessControlEntities", AccessControlEntitiesArray);
								
				}
				
				else	if(adduploadvideobean.getVideoAccessControl().equals("Channels1")) {
					
					// Set atleast one team through AccessControlEntities
					uploadvideoJson.put("VideoAccessControl","Channels");			
					JSONObject accesscontrolforteam = new JSONObject();
					JSONObject accesscontrolforgroup = new JSONObject();
					JSONArray AccessControlEntitiesArray = new JSONArray();
					
					if (!adduploadvideobean.getAccesscontrolforteam().isEmpty() && adduploadvideobean.getAccesscontrolforteam() != null) {

						accesscontrolforteam.put("name", adduploadvideobean.getAccesscontrolforteam());
						accesscontrolforteam.put("type", "Channel");
						accesscontrolforteam.put("canEdit", "true");
						AccessControlEntitiesArray.add(accesscontrolforteam);
												   			
					}
					
					if(adduploadvideobean.getAccesscontrolforgroup() != null && !adduploadvideobean.getAccesscontrolforgroup().isEmpty()) {

						accesscontrolforgroup.put("name", adduploadvideobean.getAccesscontrolforgroup());
						accesscontrolforgroup.put("type", "Channel");
						accesscontrolforgroup.put("canEdit", "true");
						AccessControlEntitiesArray.add(accesscontrolforgroup);
					} 
					
					// Adding Main JSON
					uploadvideoJson.put("AccessControlEntities", AccessControlEntitiesArray);
								
				}

				
				else if(adduploadvideobean.getVideoAccessControl().equals("Public")) {
					
					uploadvideoJson.remove("AccessControlEntities");
								
				}
				
				else if(adduploadvideobean.getVideoAccessControl().equals("Public1")) {
					
					uploadvideoJson.put("VideoAccessControl","Public");
					uploadvideoJson.put("Password", "password");
								
				}
					
				

				
				else if(adduploadvideobean.getVideoAccessControl().equals("Private")) {
					
					JSONObject accesscontrolforuser = new JSONObject();
					JSONObject accesscontrolforgroup = new JSONObject();
					JSONArray AccessControlEntitiesArray = new JSONArray();
					
					if (adduploadvideobean.getAccesscontrolforuser() != null && !adduploadvideobean.getAccesscontrolforuser().isEmpty()) {

						accesscontrolforuser.put("name", adduploadvideobean.getAccesscontrolforuser());
						accesscontrolforuser.put("type", "User");
						accesscontrolforuser.put("canEdit", "true");
						//accesscontrolforuser.put("id", adduploadvideobean.getTeamid());
						AccessControlEntitiesArray.add(accesscontrolforuser);
					}
					
					else if (adduploadvideobean.getAccesscontrolforgroup() != null && !adduploadvideobean.getAccesscontrolforgroup().isEmpty())	{
						
						accesscontrolforgroup.put("name", adduploadvideobean.getAccesscontrolforgroup());
						accesscontrolforgroup.put("type", "Group");
						accesscontrolforgroup.put("canEdit", "true");
						//accesscontrolforuser.put("id", adduploadvideobean.getTeamid());
						AccessControlEntitiesArray.add(accesscontrolforgroup);
					} 
					
							
					// Adding Main JSON
					uploadvideoJson.put("AccessControlEntities", AccessControlEntitiesArray);
								
				}
				
				else if(adduploadvideobean.getVideoAccessControl().equals("Private1")) {
					
					uploadvideoJson.put("VideoAccessControl","Private");
					JSONObject accesscontrolforuser = new JSONObject();
					JSONObject accesscontrolforgroup = new JSONObject();
					JSONArray AccessControlEntitiesArray = new JSONArray();
					
					if (adduploadvideobean.getAccesscontrolforuser() != null && !adduploadvideobean.getAccesscontrolforuser().isEmpty()) {
						
						//accesscontrolforuser.put("name", adduploadvideobean.getAccesscontrolforuser());
						accesscontrolforuser.put("type", "User");
						accesscontrolforuser.put("canEdit", "true");
						accesscontrolforuser.put("id", adduploadvideobean.getUserId());
						AccessControlEntitiesArray.add(accesscontrolforuser);
						
					}
					else if (adduploadvideobean.getAccesscontrolforgroup() != null && !adduploadvideobean.getAccesscontrolforgroup().isEmpty())	{
						
						//accesscontrolforgroup.put("name", adduploadvideobean.getAccesscontrolforgroup());
						accesscontrolforgroup.put("type", "Group");
						accesscontrolforgroup.put("canEdit", "true");
						accesscontrolforuser.put("id", adduploadvideobean.getGroupId());
						AccessControlEntitiesArray.add(accesscontrolforgroup);
					} 
					
														
					// Adding Main JSON
					uploadvideoJson.put("AccessControlEntities", AccessControlEntitiesArray);
													
				}
				
				else if(adduploadvideobean.getVideoAccessControl().equals("Private2")) {
					
					uploadvideoJson.put("VideoAccessControl","Private");
					JSONObject accesscontrolforuser = new JSONObject();
					JSONObject accesscontrolforgroup = new JSONObject();
					JSONArray AccessControlEntitiesArray = new JSONArray();
					
				
					if (adduploadvideobean.getAccesscontrolforuser() != null && !adduploadvideobean.getAccesscontrolforuser().isEmpty()) {
						
						accesscontrolforuser.put("name", adduploadvideobean.getAccesscontrolforuser());
						accesscontrolforuser.put("type", "User");
						accesscontrolforuser.put("canEdit", "false");
						//accesscontrolforuser.put("id", adduploadvideobean.getTeamid());
						AccessControlEntitiesArray.add(accesscontrolforuser);
						
					}
					else if (adduploadvideobean.getAccesscontrolforgroup() != null && !adduploadvideobean.getAccesscontrolforgroup().isEmpty())	{
						
						accesscontrolforgroup.put("name", adduploadvideobean.getAccesscontrolforgroup());
						accesscontrolforgroup.put("type", "Group");
						accesscontrolforgroup.put("canEdit", "false");
						//accesscontrolforuser.put("id", adduploadvideobean.getTeamid());
						AccessControlEntitiesArray.add(accesscontrolforgroup);
					} 
					
											
					// Adding Main JSON
					uploadvideoJson.put("AccessControlEntities", AccessControlEntitiesArray);
					
																		
				}

					

				
				else if(adduploadvideobean.getVideoAccessControl().equals("All Users")) {	
					
				uploadvideoJson.put("VideoAccessControl","AllUsers");
				uploadvideoJson.remove("AccessControlEntities");}
		
			}
			
			
			//9. Tags
			JSONArray jsonarryTag = new JSONArray();
			
			if (adduploadvideobean.getTags() != null && adduploadvideobean.getTags().length != 0) {

				for (String tag : adduploadvideobean.getTags()) {
					jsonarryTag.add(tag);
				}
				uploadvideoJson.put("tags", jsonarryTag);
			}

		
			//10.Categories
			JSONArray jsonarrycategories = new JSONArray();
			if ( adduploadvideobean.getCategories() != null) {

				for (String categorie : adduploadvideobean.getCategories()) {
					jsonarrycategories.add(categorie);
				}
				uploadvideoJson.put("Categories", jsonarrycategories);
			}

			//11. CategoryIds
			JSONArray jsonarryCategoryIds = new JSONArray();
			if ( adduploadvideobean.getCategoryIds() != null) {

				for (String categoriesId : adduploadvideobean.getCategoryIds()) {
					jsonarryCategoryIds.add(categoriesId);
				}
				uploadvideoJson.put("CategoryIds", jsonarryCategoryIds);
			}

			// end CategoryIds
			
			// 12.Is360
			if (adduploadvideobean.getIs360() != null && ! adduploadvideobean.getIs360().isEmpty())
			uploadvideoJson.put("is360", adduploadvideobean.getIs360());
										
			System.out.println("Created Upload Video  JSON with access control entities:::: " + uploadvideoJson);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return uploadvideoJson.toString();

	}

	
	
	@SuppressWarnings("unchecked")
	public String createVideoUploadJSON1(AddUploadVideoBean adduploadvideobean) {

	JSONObject uploadvideoJson1 = new JSONObject();

	try {
		// 1.Title
		if (adduploadvideobean.getTitle() != null && !adduploadvideobean.getTitle().isEmpty())
			uploadvideoJson1.put("title", adduploadvideobean.getTitle());

		else
			uploadvideoJson1.put("title", "");

		// 2.Description
		if (adduploadvideobean.getDescription() != null && !adduploadvideobean.getDescription().isEmpty())
			uploadvideoJson1.put("description", adduploadvideobean.getDescription());

		else
			uploadvideoJson1.remove("description");

		// 3.EnableComments
		if (adduploadvideobean.getEnableComments() != null && !adduploadvideobean.getEnableComments().isEmpty())
			uploadvideoJson1.put("enableComments", adduploadvideobean.getEnableComments());

		else
			uploadvideoJson1.remove("enableComment");

		// 4.EnableDownloads
		if (adduploadvideobean.getEnableDownloads() != null && ! adduploadvideobean.getEnableDownloads().isEmpty())
			uploadvideoJson1.put("enableDownloads", adduploadvideobean.getEnableDownloads());

		else
			uploadvideoJson1.remove("enableDownloads");

		// 5.EnableRating
		if (adduploadvideobean.getEnableRatings() != null && ! adduploadvideobean.getEnableRatings().isEmpty())
			uploadvideoJson1.put("enableRatings", adduploadvideobean.getEnableRatings());

		else
			uploadvideoJson1.remove("enableRatings");

		// 6.VidoeUploader
		if (adduploadvideobean.getUploader() != null && ! adduploadvideobean.getUploader().isEmpty())
			uploadvideoJson1.put("uploader", adduploadvideobean.getUploader());

		else
			uploadvideoJson1.remove("uploader");

		// 7.IsActive
		if (adduploadvideobean.getIsActive() != null && ! adduploadvideobean.getIsActive().isEmpty())
			uploadvideoJson1.put("isActive", adduploadvideobean.getIsActive());

		else
			uploadvideoJson1.remove("isActive");
		

		// 8.VideosAccessControl and access control entities
		if (adduploadvideobean.getVideoAccessControl()!= null && ! adduploadvideobean.getVideoAccessControl().isEmpty()) {
						
			uploadvideoJson1.put("VideoAccessControl", adduploadvideobean.getVideoAccessControl());
		
				if(adduploadvideobean.getVideoAccessControl().equals("Channels")) {
							
				// Set atleast one team through AccessControlEntities
											
				JSONObject accesscontrolforteam = new JSONObject();
							
				if (adduploadvideobean.getAccesscontrolforteam() != null && !adduploadvideobean.getAccesscontrolforteam().isEmpty()) {

					//accesscontrolforteam.put("name", adduploadvideobean.getAccesscontrolforteam());
					accesscontrolforteam.put("type", "Channel");
					accesscontrolforteam.put("canEdit", "true");
					accesscontrolforteam.put("id", adduploadvideobean.getTeamid());
							
				}
											
				// Adding Single ArrayJSON
				JSONArray AccessControlEntitiesArray = new JSONArray();
				AccessControlEntitiesArray.add(accesscontrolforteam);

				// Adding Main JSON
				uploadvideoJson1.put("AccessControlEntities", AccessControlEntitiesArray);
										
				}
				
				else	if(adduploadvideobean.getVideoAccessControl().equals("Channels1")) {
					
					// Set atleast one team through AccessControlEntities
					uploadvideoJson1.put("VideoAccessControl","Channels");			
					JSONObject accesscontrolforteam = new JSONObject();
					JSONObject accesscontrolforgroup = new JSONObject();
					JSONArray AccessControlEntitiesArray = new JSONArray();
					
					if (!adduploadvideobean.getAccesscontrolforteam().isEmpty() && adduploadvideobean.getAccesscontrolforteam() != null) {

						accesscontrolforteam.put("name", adduploadvideobean.getAccesscontrolforteam());
						accesscontrolforteam.put("type", "Channel");
						accesscontrolforteam.put("canEdit", "true");
						AccessControlEntitiesArray.add(accesscontrolforteam);
												   			
					}
					
					if(adduploadvideobean.getAccesscontrolforgroup() != null && !adduploadvideobean.getAccesscontrolforgroup().isEmpty()) {

						accesscontrolforgroup.put("name", adduploadvideobean.getAccesscontrolforgroup());
						accesscontrolforgroup.put("type", "Channel");
						accesscontrolforgroup.put("canEdit", "true");
						AccessControlEntitiesArray.add(accesscontrolforgroup);
					} 
					
					// Adding Main JSON
					uploadvideoJson1.put("AccessControlEntities", AccessControlEntitiesArray);
								
				}

						
				else if(adduploadvideobean.getVideoAccessControl().equals("Public")) {
							
				uploadvideoJson1.remove("AccessControlEntities");
										
				}
						
				else if(adduploadvideobean.getVideoAccessControl().equals("Public1")) {
							
				uploadvideoJson1.put("VideoAccessControl","Public");
				uploadvideoJson1.put("Password", "password");
										
				}
							
				else if(adduploadvideobean.getVideoAccessControl().equals("Private")) {
							
				JSONObject accesscontrolforuser = new JSONObject();
				JSONObject accesscontrolforgroup = new JSONObject();
				JSONArray AccessControlEntitiesArray = new JSONArray();
							
				if (adduploadvideobean.getAccesscontrolforuser() != null && !adduploadvideobean.getAccesscontrolforuser().isEmpty()) {

				accesscontrolforuser.put("name", adduploadvideobean.getAccesscontrolforuser());
				accesscontrolforuser.put("type", "User");
				accesscontrolforuser.put("canEdit", "true");
				//accesscontrolforuser.put("id", adduploadvideobean.getTeamid());
				AccessControlEntitiesArray.add(accesscontrolforuser);
				}
							
				else if (adduploadvideobean.getAccesscontrolforgroup() != null && !adduploadvideobean.getAccesscontrolforgroup().isEmpty())	{
								
				accesscontrolforgroup.put("name", adduploadvideobean.getAccesscontrolforgroup());
				accesscontrolforgroup.put("type", "Group");
				accesscontrolforgroup.put("canEdit", "true");
				//accesscontrolforuser.put("id", adduploadvideobean.getTeamid());
				AccessControlEntitiesArray.add(accesscontrolforgroup);
				} 
							
				// Adding Main JSON
				uploadvideoJson1.put("AccessControlEntities", AccessControlEntitiesArray);
										
				}
						
				else if(adduploadvideobean.getVideoAccessControl().equals("Private1")) {
						
				uploadvideoJson1.put("VideoAccessControl","Private");
				JSONObject accesscontrolforuser = new JSONObject();
				JSONObject accesscontrolforgroup = new JSONObject();
				JSONArray AccessControlEntitiesArray = new JSONArray();
							
				if (adduploadvideobean.getAccesscontrolforuser() != null && !adduploadvideobean.getAccesscontrolforuser().isEmpty()) {
								
				//accesscontrolforuser.put("name", adduploadvideobean.getAccesscontrolforuser());
				accesscontrolforuser.put("type", "User");
				accesscontrolforuser.put("canEdit", "true");
				accesscontrolforuser.put("id", adduploadvideobean.getUserId());
				AccessControlEntitiesArray.add(accesscontrolforuser);
								
				}
				else if (adduploadvideobean.getAccesscontrolforgroup() != null && !adduploadvideobean.getAccesscontrolforgroup().isEmpty())	{
								
				//accesscontrolforgroup.put("name", adduploadvideobean.getAccesscontrolforgroup());
				accesscontrolforgroup.put("type", "Group");
				accesscontrolforgroup.put("canEdit", "true");
				accesscontrolforuser.put("id", adduploadvideobean.getGroupId());
				AccessControlEntitiesArray.add(accesscontrolforgroup);
				} 
							
				// Adding Main JSON
				uploadvideoJson1.put("AccessControlEntities", AccessControlEntitiesArray);
															
				}
						
				else if(adduploadvideobean.getVideoAccessControl().equals("Private2")) {
							
				uploadvideoJson1.put("VideoAccessControl","Private");
				JSONObject accesscontrolforuser = new JSONObject();
				JSONObject accesscontrolforgroup = new JSONObject();
				JSONArray AccessControlEntitiesArray = new JSONArray();
							
				if (adduploadvideobean.getAccesscontrolforuser() != null && !adduploadvideobean.getAccesscontrolforuser().isEmpty()) {
								
				accesscontrolforuser.put("name", adduploadvideobean.getAccesscontrolforuser());
				accesscontrolforuser.put("type", "User");
				accesscontrolforuser.put("canEdit", "false");
				//accesscontrolforuser.put("id", adduploadvideobean.getTeamid());
				AccessControlEntitiesArray.add(accesscontrolforuser);
								
				}
				else if (adduploadvideobean.getAccesscontrolforgroup() != null && !adduploadvideobean.getAccesscontrolforgroup().isEmpty())	{
								
				accesscontrolforgroup.put("name", adduploadvideobean.getAccesscontrolforgroup());
				accesscontrolforgroup.put("type", "Group");
				accesscontrolforgroup.put("canEdit", "false");
				//accesscontrolforuser.put("id", adduploadvideobean.getTeamid());
				AccessControlEntitiesArray.add(accesscontrolforgroup);
				} 
							
				// Adding Main JSON
				uploadvideoJson1.put("AccessControlEntities", AccessControlEntitiesArray);
							
				}

				else if(adduploadvideobean.getVideoAccessControl().equals("All Users")) {
				
					uploadvideoJson1.put("VideoAccessControl","AllUsers");
					uploadvideoJson1.remove("AccessControlEntities");
										
				}  
				
				
		}
		
			else
				uploadvideoJson1.remove("VideoAccessControl");


		//9. Tags
		JSONArray jsonarryTag = new JSONArray();
		if (adduploadvideobean.getTags() != null) {

			for (String tag : adduploadvideobean.getTags()) {
				jsonarryTag.add(tag);
			}
			uploadvideoJson1.put("tags", jsonarryTag);
		}

		else
			uploadvideoJson1.remove("tags");

		//10.Categories
		JSONArray jsonarrycategories = new JSONArray();
		if ( adduploadvideobean.getCategories() != null) {

			for (String categorie : adduploadvideobean.getCategories()) {
				jsonarrycategories.add(categorie);
			}
			uploadvideoJson1.put("Categories", jsonarrycategories);
		}

		else
			uploadvideoJson1.remove("Categories");

		//11. CategoryIds
		JSONArray jsonarryCategoryIds = new JSONArray();
		if ( adduploadvideobean.getCategoryIds() != null) {

			for (String categoriesId : adduploadvideobean.getCategoryIds()) {
				jsonarryCategoryIds.add(categoriesId);
			}
			uploadvideoJson1.put("CategoryIds", jsonarryCategoryIds);
		}

		else
			uploadvideoJson1.remove("CategoryIds");

		// end CategoryIds
		
		// 12.Is360
		if (adduploadvideobean.getIs360() != null && ! adduploadvideobean.getIs360().isEmpty())
		uploadvideoJson1.put("is360", adduploadvideobean.getIs360());
		
		else
			uploadvideoJson1.remove("is360");
		
		// 13.PublishDate
		if (!adduploadvideobean.getPublishDate().isEmpty() && adduploadvideobean.getPublishDate() != null)
			uploadvideoJson1.put("publishDate", adduploadvideobean.getPublishDate());

		else
			uploadvideoJson1.remove("publishDate","");
		
		// 14.ExpirationDate
		if (!adduploadvideobean.getExpirationDate().isEmpty() && adduploadvideobean.getExpirationDate() != null)
			uploadvideoJson1.put("expirationDate", adduploadvideobean.getExpirationDate());

		else
			uploadvideoJson1.remove("expirationDate", "");
		
		// 15.ExpirationAction
		if (!adduploadvideobean.getExpirationAction().isEmpty() && adduploadvideobean.getExpirationAction() != null)
		uploadvideoJson1.put("expirationAction", adduploadvideobean.getExpirationAction());

		else
		uploadvideoJson1.remove("expirationAction", "");
		
		// 16.WhenUploaded
		if (!adduploadvideobean.getWhenUploaded().isEmpty() && adduploadvideobean.getWhenUploaded() != null)
		uploadvideoJson1.put("whenUploaded", adduploadvideobean.getWhenUploaded());

		else
		uploadvideoJson1.remove("whenUploaded", "");
		
		
		System.out.println("Edit Upload Video  JSON with access control entities:::: " + uploadvideoJson1);

	} catch (Exception e) {
		e.printStackTrace();

	}
	return uploadvideoJson1.toString();

}
	public HashMap<String, String> replaceVideo(HashMap<String, String> apiresponse,AddUploadVideoBean addUploadVideoBean) {
		String responseFlag = null;

		try {

			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl)).path(RevbaseAPIURLs.UPDATEVIDEOURL+apiresponse.get("videoId"));
            logger.info("API Request as URL    :::: "+ webResource);
            
            response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .put(ClientResponse.class,createVideoUploadJSON1(addUploadVideoBean));
          
            editvideoapiresponse =new  HashMap<String, String>();
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				editvideoapiresponse.put("InvalidUserNamePassword", response.getEntity(String.class));
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseJsonErrorCode, JsonPath.parse(responseFlag).read("$.code").toString());
				
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			}else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				editvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return editvideoapiresponse;

	}

		
}
