package com.vbricks.avenger.serviceimpl;
import java.util.Date;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreatePushContentService;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;


public class VideoCommentsAPI {

	private static Logger logger = Logger.getLogger(VideoCommentsAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> getvideocommentapiresponse;
	HashMap<String, String> putsubmitcommentsapiresponse ;
	
	/**
	 * @author neslavath
	 * @functionName : getVideoCommentsAPI
	 * @Description : getVideoCommentsAPI - will get specific video details based on VideoID
	 * @param loginapiresponse
	 * @return getVideoCommentsAPi Response (httpcode,statusInfo,Video relation info)
	 * @throws Exception 
	 */
	
	public  HashMap<String, String> getVideoCommmentsQueryparam(HashMap<String, String> apiresponse) throws Exception {
		
		String responseFlag = null;
			client = Client.create();
			MultivaluedMap queryParams = new MultivaluedMapImpl();
				queryParams.add(IAPIConstantCodes.showAll,apiresponse.get(IAPIConstantCodes.showAll));
		
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.COMMENTSURL);
	
			
			logger.info("API Request as URL    :::: "+ webResource);
			response = webResource
					   .queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
			getvideocommentapiresponse =new HashMap<String, String>();
	 
			logger.info("@@@@@Status    :::: " + response.getStatus());
	        responseFlag = response.getEntity(String.class);
if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401||response.getStatus() == HttpStatusCode.httpStatusCodeInt400||response.getStatus() == HttpStatusCode.httpStatusCodeInt404||response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
			     
	getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
	getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
	 }


logger.info("@@@@@Response Body contains    :::: " + getvideocommentapiresponse);
			return getvideocommentapiresponse;
	
	
	}
	
	public JSONObject getVideoCommmentsQueryparams(HashMap<String, String> apiresponse) throws Exception {
	
		String responseFlag = null;
			client = Client.create();
			MultivaluedMap queryParams = new MultivaluedMapImpl();
				queryParams.add(IAPIConstantCodes.showAll,apiresponse.get(IAPIConstantCodes.showAll));
		
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.COMMENTSURL);
	
			
			logger.info("API Request as URL    :::: "+ webResource);
			response = webResource
					   .queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
		 	 
 	logger.info("@@@@@Status    :::: " + response.getStatus());
 	 responseFlag = response.getEntity(String.class);
     Object obj=JSONValue.parse(responseFlag);   
     JSONObject jsonObject = (JSONObject) obj;
       //JsonObject is getting coverted into json array
       JSONArray comments=(JSONArray) jsonObject.get("comments");
       
       //Jsonarray is getting coverted into json object
       JSONObject commentsobject=(JSONObject) comments.get(0);//In this line we are getting the index 0 comments
     
   	    DateTime dateTimeUtc = new DateTime(commentsobject.get("date"), DateTimeZone.UTC);
   	    int milliseconds=dateTimeUtc.getMillisOfSecond();
   	    DateTime currentdate= dateTimeUtc.plusDays(0).minusMillis(milliseconds);
   	    commentsobject.put(IVideoAccessControlService.date, currentdate);
   	    
   	    DateTime dateTimeUtc1 = new DateTime(commentsobject.get("date"), DateTimeZone.UTC);
	    int milliseconds1=dateTimeUtc1.getMillisOfSecond();
	    DateTime currentdate1= dateTimeUtc1.plusDays(0).minusMillis(milliseconds1);
	    commentsobject.put(IVideoAccessControlService.deletedWhen, currentdate1);
   	  
	    Thread.sleep(1000);
        commentsobject.put(IAPIConstantCodes.APIResponseVIDEOID,jsonObject.get("id").toString());
        commentsobject.put(IVideoAccessControlService.title, jsonObject.get(IVideoAccessControlService.title).toString());
        commentsobject.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
        logger.info("@@@@@Objects present    :::: " + commentsobject);
         
 	
 
 	return commentsobject;
       
		
	}
	
	public HashMap<String, String> getVideoComments(HashMap<String, String> apiresponse) {

		String responseFlag = null;
		try {
			client = Client.create();
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.COMMENTSURL);
			logger.info("API Request as URL    :::: "+ webResource);
			response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
					getvideocommentapiresponse =new HashMap<String, String>();
			 if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				logger.info(" Get Video Commit Response HTTP Code  :::: " + response.getStatus());
				logger.info(" Get Video Commit JSON  ::::   " + responseFlag);

				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject) parser.parse(responseFlag);
				for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
					logger.info("Key :::: " + entry.getKey());
					logger.info("Value :::: " + entry.getValue());
					getvideocommentapiresponse.put(entry.getKey(), entry.getValue().toString());
					
				}
				getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			     
				getvideocommentapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideocommentapiresponse.put(IVideoAccessControlService.title,JsonPath.parse(responseFlag).read("$.title").toString());
				getvideocommentapiresponse.put(IVideoAccessControlService.commenttext,JsonPath.parse(responseFlag).read("$.comments[0].text").toString());
				getvideocommentapiresponse.put(IVideoAccessControlService.firstname,JsonPath.parse(responseFlag).read("$.comments[0].firstName").toString());
				getvideocommentapiresponse.put(IVideoAccessControlService.lastname,JsonPath.parse(responseFlag).read("$.comments[0].lastName").toString());
			 } 
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
		     
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } 
		     else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getvideocommentapiresponse;

	}

	
	/**
	 * @author neslavath
	 * @functionName : SubmitVideoCommentsAPI
	 * @Description : SubmitVideoCommentsAPI - will user can provide video comment based on VideoID
	 * @param loginapiresponse
	 * @return SubmitVideoCommentsAPi Response (httpcode,statusInfo,Video relation info)
	 */
	public HashMap<String, String> putsubmitComments(HashMap<String, String> apiresponse,AddVideoCommentBean addvideocommentbean) {

		String responseFlag = null;

		try {

			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.COMMENTURL);
			logger.info("API Request as URL    :::: "+ webResource);
			 response = webResource
								.accept(ApiResources.Content_Type)
								.type(ApiResources.Content_Type)
								.header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.put(ClientResponse.class,createCommentJSON(addvideocommentbean).toString());
		
	
			    String	date=removemillisecondsfromcurrentutctime(); 
			    
			 putsubmitcommentsapiresponse = new HashMap<String, String>();
			  if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				     responseFlag = response.getEntity(String.class);
				     
				     logger.info("Submit API response "+responseFlag);
				     putsubmitcommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				     putsubmitcommentsapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
					 logger.info(" Submit Commet HTTP Code :::: " + Integer.toString(response.getStatus()));
					 logger.info(" Submit Commet Response JSON :::: " + response.getStatusInfo().toString());
					 putsubmitcommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					 putsubmitcommentsapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
					 putsubmitcommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					 putsubmitcommentsapiresponse.put(IAPIConstantCodes.APIResponseVIDEOID, apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
					 putsubmitcommentsapiresponse.put("date",date);
				   } 
			     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
			    	 putsubmitcommentsapiresponse.put(IAPIConstantCodes.INVALIDUSERNAMEPASSWORD, response.getEntity(String.class));
			    	 putsubmitcommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			    	 putsubmitcommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   }
			     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
			    	 putsubmitcommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			    	 putsubmitcommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   }
			     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
			    	 putsubmitcommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			    	 putsubmitcommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   }
			     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
			    	 putsubmitcommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			    	 putsubmitcommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   }
			     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
			    	 putsubmitcommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			    	 putsubmitcommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } 
			     else {
				    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return putsubmitcommentsapiresponse;

	}
	
	@SuppressWarnings("unchecked")
	public JSONObject createCommentJSON(AddVideoCommentBean addvideocommentbean)  {

		 JSONObject CommentJson = new JSONObject(); 
				 		 
		 try {
				// 1.Name
					if (!addvideocommentbean.getComment().isEmpty() && addvideocommentbean.getComment() != null)
						CommentJson.put(IAPIConstantCodes.COMMENT, addvideocommentbean.getComment());
					else
						CommentJson.remove(IAPIConstantCodes.COMMENT);
				//2 Link
					if (!addvideocommentbean.getUserName().isEmpty() && addvideocommentbean.getUserName() != null)
						CommentJson.put(IAPIConstantCodes.APIResponseUserName, addvideocommentbean.getUserName());
					else
						CommentJson.remove(IAPIConstantCodes.APIResponseUserName);
			 
           logger.info("Created comment JSon is::::"+CommentJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return CommentJson;
		 
}
	
	
	
	public HashMap<String, String> getVideoCommentid(HashMap<String, String> apiresponse) {

		String responseFlag = null;
		try {
			client = Client.create();
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.COMMENTSURL);
			logger.info("API Request as URL    :::: "+ webResource);
			response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
					getvideocommentapiresponse =new HashMap<String, String>();
			 if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				logger.info(" Get Video Commit Response HTTP Code  :::: " + response.getStatus());
				logger.info(" Get Video Commit JSON  ::::   " + responseFlag);
				 String a[]=responseFlag.replaceAll("[\"']*","").split(",");
				   String b[]=a[2].split(":");
				   System.out.println(b[2]);

				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject) parser.parse(responseFlag);
				for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
					logger.info("Key :::: " + entry.getKey());
					logger.info("Value :::: " + entry.getValue());
					getvideocommentapiresponse.put(entry.getKey(), entry.getValue().toString());
					
				}
				getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				getvideocommentapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideocommentapiresponse.put(IVideoAccessControlService.title,JsonPath.parse(responseFlag).read("$.title").toString());
				getvideocommentapiresponse.put(IVideoAccessControlService.commenttext,JsonPath.parse(responseFlag).read("$.comments[0].text").toString());
				getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseCOMMENTID,b[2]);
				getvideocommentapiresponse.put(IVideoAccessControlService.firstname,JsonPath.parse(responseFlag).read("$.comments[0].firstName").toString());
				getvideocommentapiresponse.put(IVideoAccessControlService.lastname,JsonPath.parse(responseFlag).read("$.comments[0].lastName").toString());
			 } 
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
		     
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } 
		     else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getvideocommentapiresponse;

	}
	public HashMap<String, String> getVideoChildCommentid(HashMap<String, String> apiresponse) {

		String responseFlag = null;
		try {
			client = Client.create();
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.COMMENTSURL);
			logger.info("API Request as URL    :::: "+ webResource);
			response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
					getvideocommentapiresponse =new HashMap<String, String>();
			 if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				logger.info(" Get Video Commit Response HTTP Code  :::: " + response.getStatus());
				logger.info(" Get Video Commit JSON  ::::   " + responseFlag);
				 String a[]=responseFlag.replaceAll("[\"']*","").split(",");
				   String b[]=a[9].split(":");
			
				   System.out.println(b[1]);
				  

				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject) parser.parse(responseFlag);
				for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
					logger.info("Key :::: " + entry.getKey());
					logger.info("Value :::: " + entry.getValue());
					getvideocommentapiresponse.put(entry.getKey(), entry.getValue().toString());
					
				}
				getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				getvideocommentapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideocommentapiresponse.put(IVideoAccessControlService.title,JsonPath.parse(responseFlag).read("$.title").toString());
				getvideocommentapiresponse.put(IVideoAccessControlService.commenttext,JsonPath.parse(responseFlag).read("$.comments[0].text").toString());
				getvideocommentapiresponse.put(IVideoAccessControlService.firstname,JsonPath.parse(responseFlag).read("$.comments[0].firstName").toString());
				getvideocommentapiresponse.put(IVideoAccessControlService.lastname,JsonPath.parse(responseFlag).read("$.comments[0].lastName").toString());
			 } 
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
		     
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } 
		     else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getvideocommentapiresponse;

	}

	public HashMap<String, String> getVideomultipleCommentids(HashMap<String, String> apiresponse) {

		String responseFlag = null;
		try {
			client = Client.create();
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.COMMENTSURL);
			logger.info("API Request as URL    :::: "+ webResource);
			response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
					getvideocommentapiresponse =new HashMap<String, String>();
			 if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				logger.info(" Get Video Commit Response HTTP Code  :::: " + response.getStatus());
				logger.info(" Get Video Commit JSON  ::::   " + responseFlag);
				 String a[]=responseFlag.replaceAll("[\"']*","").split(",");
				   String b[]=a[2].split(":");
				   String b1[]=a[10].split(":");
				   String b2[]=a[18].split(":");
				   System.out.println(b[2]);

				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject) parser.parse(responseFlag);
				for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
					logger.info("Key :::: " + entry.getKey());
					logger.info("Value :::: " + entry.getValue());
					getvideocommentapiresponse.put(entry.getKey(), entry.getValue().toString());
					
				}
				getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				getvideocommentapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideocommentapiresponse.put(IVideoAccessControlService.title,JsonPath.parse(responseFlag).read("$.title").toString());
				getvideocommentapiresponse.put(IVideoAccessControlService.commenttext,JsonPath.parse(responseFlag).read("$.comments[0].text").toString());
				getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseCOMMENTID,b[2]);
				getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseCOMMENTID2,b1[1]);
				getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseCOMMENTID3,b2[1]);
				getvideocommentapiresponse.put(IVideoAccessControlService.firstname,JsonPath.parse(responseFlag).read("$.comments[0].firstName").toString());
				getvideocommentapiresponse.put(IVideoAccessControlService.lastname,JsonPath.parse(responseFlag).read("$.comments[0].lastName").toString());
			 } 
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
		     
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }
		     else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	 getvideocommentapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } 
		     else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getvideocommentapiresponse;

	}

	public String removemillisecondsfromcurrentutctime( ) { 
		HashMap<String, String> dates = new HashMap<String, String>();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date d = new Date();
		DateTime dateTimeUtc = new DateTime(d, DateTimeZone.UTC);
		int milliseconds=dateTimeUtc.getMillisOfSecond();
		DateTime currentdate= dateTimeUtc.plusDays(0).minusMillis(milliseconds);
		return currentdate.toString();	

	}








}
