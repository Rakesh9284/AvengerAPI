package com.vbricks.avenger.serviceimpl;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.ManualEnteredURL;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;

import net.sf.json.JSONObject;

public class ManualEnteredURLAPI   {

	private static Logger logger = Logger.getLogger(ManualEnteredURLAPI.class);
	 
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	
	HashMap<String, String> manualenteredurlApiresponse = new HashMap<String, String>();
	
	public HashMap<String, String> manualEnteredURL(HashMap<String, String> apiresponse,ManualEnteredURL manualEnteredURL) {

		String responseFlag = null;

		try {

			  client = Client.create();
			  webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					        .path(RevbaseAPIURLs.GETVIDEOURL);
	          logger.info("BaseUrl is   :::: "+webResource);		
			  response = webResource
					     .accept(ApiResources.Content_Type)
					     .type(ApiResources.Content_Type)
					     .header("Authorization", "VBrick " + apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					     .post(ClientResponse.class,createmanualURLJSON(manualEnteredURL));
			    
			  if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			     responseFlag = response.getEntity(String.class);
			     logger.info("Video Download Response JSON  :::: " + responseFlag);
			     manualenteredurlApiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			     manualenteredurlApiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
			     manualenteredurlApiresponse.put(IAPIConstantCodes.APIResponseVIDEOID, JsonPath.parse(responseFlag).read("$.videoId").toString());
			     manualenteredurlApiresponse.put(IAPIConstantCodes.STATUSURI, JsonPath.parse(responseFlag).read("$.statusUri").toString());
				 logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
				 logger.info("statusInfo ::: "+response.getStatusInfo().toString());
 			    }
			  else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) 
			  {
				  manualenteredurlApiresponse.put(IAPIConstantCodes.INVALIDUSERNAMEPASSWORD, response.getEntity(String.class));
				  manualenteredurlApiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				  manualenteredurlApiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   
			   } 
			  else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) 
			  {
				  manualenteredurlApiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				  manualenteredurlApiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   responseFlag = response.getEntity(String.class);
				     logger.info("Video Download Response JSON  :::: " + responseFlag);
			   } 
			  else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) 
			  {
				  manualenteredurlApiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   manualenteredurlApiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   responseFlag = response.getEntity(String.class);
				     logger.info("Video Download Response JSON  :::: " + responseFlag);
			   }
			  else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) 
			  {
				  manualenteredurlApiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				  manualenteredurlApiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   responseFlag = response.getEntity(String.class);
				     logger.info("Video Download Response JSON  :::: " + responseFlag);
			   } 
			  else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) 
			  {
				  manualenteredurlApiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				  manualenteredurlApiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   responseFlag = response.getEntity(String.class);
				     logger.info("Video Download Response JSON  :::: " + responseFlag);
			   } 
			  else 
			  {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			  }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return manualenteredurlApiresponse;

	}
	
	@SuppressWarnings("unchecked")
	public String createmanualURLJSON(ManualEnteredURL manualEnteredURL) {

		JSONObject manualenteredJson = new JSONObject();

		try {
			// 1.Title
			if (!manualEnteredURL.getTitle().equals("") || manualEnteredURL.getTitle() != null)
				manualenteredJson.put(IAPIConstantCodes.Title, manualEnteredURL.getTitle());

			else
				manualenteredJson.put(IAPIConstantCodes.Title, "");

			// 2.Description
			if (!manualEnteredURL.getDescription().equals("") || manualEnteredURL.getDescription() != null)
				manualenteredJson.put(IAPIConstantCodes.Description, manualEnteredURL.getDescription());

			else
				manualenteredJson.put(IAPIConstantCodes.Description, "");

			// 3.EnableComments
			if (!manualEnteredURL.getEnableComments().equals("") || manualEnteredURL.getEnableComments() != null)
				manualenteredJson.put(IAPIConstantCodes.ENABLECOMMENTS, manualEnteredURL.getEnableComments());

			else
				manualenteredJson.put(IAPIConstantCodes.ENABLECOMMENTS, "");

			//4.EnableRating
						if (!manualEnteredURL.getEnableRatings().equals("") || manualEnteredURL.getEnableRatings() != null)
							manualenteredJson.put(IAPIConstantCodes.ENABLERATING, manualEnteredURL.getEnableRatings());

						else
							manualenteredJson.put(IAPIConstantCodes.ENABLERATING, "");
						
			// 5.EnableDownloads
			if (!manualEnteredURL.getEnableDownloads().equals("") || manualEnteredURL.getEnableDownloads() != null)
				manualenteredJson.put(IAPIConstantCodes.ENABLEDOWNLOADS, manualEnteredURL.getEnableDownloads());

			else
				manualenteredJson.put(IAPIConstantCodes.ENABLEDOWNLOADS, "");

			

			// 6.VidoeUploader
			if (!manualEnteredURL.getUploader().equals("") || manualEnteredURL.getUploader() != null)
				manualenteredJson.put(IAPIConstantCodes.UPLOADER, manualEnteredURL.getUploader());

			else
				manualenteredJson.put(IAPIConstantCodes.UPLOADER, "");

			// 7.IsActive
			if (!manualEnteredURL.getIsActive().equals("") || manualEnteredURL.getIsActive() != null)
				manualenteredJson.put(IAPIConstantCodes.ISACTIVE, manualEnteredURL.getIsActive());

			else
				manualenteredJson.put(IAPIConstantCodes.ISACTIVE, "");
			
			JSONArray jsonarryTag = new JSONArray();
			// start tags
			// 9.Tags
						if (manualEnteredURL.getTags().length != 0 || manualEnteredURL.getTags() != null) {

							for (String tag : manualEnteredURL.getTags()) {
								jsonarryTag.add(tag);
							}
							manualenteredJson.put(IAPIConstantCodes.TAGS, jsonarryTag);
						}

						else
							manualenteredJson.put(IAPIConstantCodes.TAGS, "");

						// end tags
						
						JSONArray jsonarrycategories = new JSONArray();
						// start Categories
						// 10.Categories
						if ( manualEnteredURL.getCategories() != null) {

							for (String categorie : manualEnteredURL.getCategories()) {
								jsonarrycategories.add(categorie);
							}
							manualenteredJson.put(IAPIConstantCodes.CATEGORIES, jsonarrycategories);
						}

						else
							manualenteredJson.put(IAPIConstantCodes.CATEGORIES, "");

						// end Categories

						JSONArray jsonarryCategoryIds = new JSONArray();
						// Start CategoryIds
						// 11.CategoryIds
						if (manualEnteredURL.getCategoryIds().length != 0 || manualEnteredURL.getCategoryIds() != null) {

							for (String categoriesId : manualEnteredURL.getCategoryIds()) {
								jsonarryCategoryIds.add(categoriesId);
							}
							manualenteredJson.put(IAPIConstantCodes.CATEGORIESIDS, jsonarryCategoryIds);
						}

						else
							manualenteredJson.put(IAPIConstantCodes.CATEGORIESIDS, "");

						// end CategoryIds			

			// 8.VideosAccessControl
			if (!manualEnteredURL.getVideoAccessControl().equals("")
					|| manualEnteredURL.getVideoAccessControl() != null)
				manualenteredJson.put(IAPIConstantCodes.VIDEOACCESSCONTROL, manualEnteredURL.getVideoAccessControl());

			else
				manualenteredJson.put(IAPIConstantCodes.VIDEOACCESSCONTROL, "");
       
			JSONObject main = new JSONObject(); 
			JSONObject obj = new JSONObject(); 
			obj.put(IAPIConstantCodes.APIURL , IAPIConstantCodes.GOOGLEADDRESS);
			obj.put(IAPIConstantCodes.ENCODINGTYPE, IAPIConstantCodes.H264);
			obj.put(IAPIConstantCodes.TYPE, IAPIConstantCodes.LIVE);
			obj.put(IAPIConstantCodes.ISMULTICAST , IAPIConstantCodes.APITRUE);
	 
			
			//main.put("LinkedUrl", obj);
			
			manualenteredJson.put(IAPIConstantCodes.LINKEDURL, obj);

			
		

			System.out.println("Created Upload Video  JSON :::: " + manualenteredJson);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return manualenteredJson.toString();

	}
}
