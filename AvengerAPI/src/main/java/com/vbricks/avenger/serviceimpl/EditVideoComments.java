package com.vbricks.avenger.serviceimpl;


import java.util.HashMap;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;


public class EditVideoComments {
	private static Logger logger = Logger.getLogger(EditVideoComments.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;	 
	HashMap<String, String> editVideoCommentsapiresponse = new HashMap<String, String>();
 
	/** 
	 * @author neslavath
	 * @param userName 
	 * @functionName : editVideoRating
	 * @Description : editVideoRating API - will able give ratings on mention video ID of the Video into the application 
	 * @param loginapiresponse (baseUrl,accessToken,VideoID
	 * @return editvideoratingapi  response(baseUrl,accessToken, apiresponse200OK)
	 */
	
	public HashMap<String, String> editVideoComments(HashMap<String, String> apiresponse,org.json.simple.JSONObject commentJson) {

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
							
								.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.post(ClientResponse.class,commentJson.toString());
			
			
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Delete Video API JSON Response :::: " + responseFlag);
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseVIDEOID, apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));  
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return editVideoCommentsapiresponse;
		 
}	
	
	public JSONObject usercomment(String comment) {
		JSONObject commentJson=new JSONObject();
		commentJson.put("Comment",  new String(comment));
		return commentJson;
	}
	public HashMap<String, String> editVideoCommentsonbehalfofanotheruser(HashMap<String, String> apiresponse,org.json.simple.JSONObject commentJson, Object userName) {

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
								.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.post(ClientResponse.class,userName.toString());
			
			
			
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Delete Video API JSON Response :::: " + responseFlag);
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseVIDEOID, apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));  
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return editVideoCommentsapiresponse;
		 
}	
	
	
	public JSONObject usercommentonbehalf(String comment) {
		JSONObject userName=new JSONObject();
		userName.put(userName, "apimvu52");
		return userName;
}
	public HashMap<String, String> editVideoCommentsonbehalfofanotheruser(HashMap<String, String> apiresponse,org.json.simple.JSONObject commentJson1) {

		String responseFlag = null;
 		try {

			client = Client.create();
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.EDITVIDEOCOMMENTURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.COMMENTSURL);
			 logger.info("API Request as URL    :::: "+ webResource);
			
			response = webResource
								.accept(ApiResources.Content_Type)
								.type(ApiResources.Content_Type)						
								.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.post(ClientResponse.class,commentJson1.toString());
			
			
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Edit VideoComment API JSON Response :::: " + responseFlag);
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseVIDEOID, apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));  
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseuserName, apiresponse.get(IAPIConstantCodes.APIUSERNAME));  
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseFirstName, apiresponse.get(IAPIConstantCodes.APIUSERNAME));  
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseLastName, apiresponse.get(IAPIConstantCodes.APIUSERNAME));  
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return editVideoCommentsapiresponse;
		 
}	
	public JSONObject usercomment1(String comment,String userName ) {
		JSONObject commentJson1=new JSONObject();
		commentJson1.put("Comment",  new String(comment));
		commentJson1.put("userName", new String(userName));
		return commentJson1;
}
	
	public HashMap<String, String> addingachildComment(HashMap<String, String> apiresponse,org.json.simple.JSONObject childComment) {

		String responseFlag = null;
 		try {

			client = Client.create();
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.EDITVIDEOCOMMENTURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.COMMENTSURL);
			 logger.info("API Request as URL    :::: "+ webResource);
			
			response = webResource
								.accept(ApiResources.Content_Type)
								.type(ApiResources.Content_Type)
							
								.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.post(ClientResponse.class,childComment.toString());
			
			
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Edit VideoComment API JSON Response :::: " + responseFlag);	   
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseVIDEOID, apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseuserName, apiresponse.get(IAPIConstantCodes.APIUSERNAME));  
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseFirstName, apiresponse.get(IAPIConstantCodes.APIUSERNAME));  
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseLastName, apiresponse.get(IAPIConstantCodes.APIUSERNAME));  
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   editVideoCommentsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return editVideoCommentsapiresponse;
		 
}	
	public JSONObject childComment(String comment,String userName,String commentId ) {
		JSONObject commentJson1=new JSONObject();
		commentJson1.put("Comment",  new String(comment));
		commentJson1.put("userName", new String(userName));
		commentJson1.put("commentId", new String(commentId));
		return commentJson1;
}
}

