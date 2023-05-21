package com.vbricks.avenger.serviceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.vbrick.avenger.apibeans.CreateEventBean;
import com.vbrick.avenger.pageobjects.AvengerEventDetailsPage;
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


public class GetEventStatusAPI {
	
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> geteventapiresponse = new HashMap<String, String>();
	private static Logger logger = Logger.getLogger(GetEventStatusAPI.class);
	public HashMap<String, String> getEventDetails(HashMap<String, String> apiresponse) {

		String responseFlag = null;

		try {

			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID) + RevbaseAPIURLs.STATUSURL);
			response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header("Authorization", "VBrick " + apiresponse.get(IAPIConstantCodes.APIRequestAccessToken)).get(ClientResponse.class);
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				geteventapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
				geteventapiresponse.put(IAPIConstantCodes.APIRESPONSEJSON, responseFlag);
				geteventapiresponse.put(ICreateEventService.Title,JsonPath.parse(responseFlag).read("$.title").toString());
				geteventapiresponse.put(ICreateEventService.StartDate,JsonPath.parse(responseFlag).read("$.startDate").toString());
				geteventapiresponse.put(ICreateEventService.EndDate,JsonPath.parse(responseFlag).read("$.endDate").toString());
				geteventapiresponse.put(ICreateEventService.Status,JsonPath.parse(responseFlag).read("$.status").toString());
				//geteventapiresponse.put(ICreateEventService.slideUrl,JsonPath.parse(responseFlag).read("$.slideUrl").toString());
				geteventapiresponse.put(ICreateEventService.isPreProduction,JsonPath.parse(responseFlag).read("$.isPreProduction").toString());
				
				
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				geteventapiresponse.put(IAPIConstantCodes.INVALIDUSERNAMEPASSWORD, response.getEntity(String.class));
				geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return geteventapiresponse;

	}
	public JSONObject starteventjsonobject(HashMap<String, String> apiresponse){
		String responseFlag = null;
			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					.path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.START);
			
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .put(ClientResponse.class," ");
			
	 		  responseFlag = response.getEntity(String.class);
	 		 Object obj=JSONValue.parse(responseFlag);   
	 		JSONObject geteventapiresponse = (JSONObject) obj; 
	 		geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
	 		geteventapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
	 		return geteventapiresponse;		
	}
	 
	public HashMap<String, String> startevent(HashMap<String, String> apiresponse){
		String responseFlag = null;
		try {
			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					.path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.START);
			
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header("Authorization", "VBrick " + apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .put(ClientResponse.class," ");

			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Start Video API JSON  :::: " + responseFlag);
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return geteventapiresponse;

	}
	
	public HashMap<String, String> deleteusers(HashMap<String, String> apiresponse ) {
		String responseFlag = null;
		try {
			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.GETUSERDETAILSURL+apiresponse.get(IAPIConstantCodes.APIResponseUSERID));
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .delete(ClientResponse.class);
		
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Delete Video API JSON  :::: " + responseFlag);
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return geteventapiresponse;

	}
	
	public JSONObject broadcasteventjsonobject(HashMap<String, String> apiresponse){
		String responseFlag = null;
			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					.path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.BROADCAST);
			
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .put(ClientResponse.class," ");
			
	 		  responseFlag = response.getEntity(String.class);
	 		 Object obj=JSONValue.parse(responseFlag);   
	 		JSONObject geteventapiresponse = (JSONObject) obj;
	 		geteventapiresponse.put("statusInfo", response.getStatus());   
	 	
	 		return geteventapiresponse;		
	}	
	
	public JSONObject Stopbroadcastevent(HashMap<String, String> apiresponse) 
	{
		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				     .path(RevbaseAPIURLs.DELETEEVENT+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.BROADCAST);
		logger.info("WebResourec BaseURL :::: "+webResource);
		response = webResource
				   .accept(ApiResources.Content_Type)
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .delete(ClientResponse.class);
		
		
		logger.info("LoginAPI Response HTTP Code :::: " + response.getStatus());
		 responseFlag = response.getEntity(String.class);
	       Object obj=JSONValue.parse(responseFlag);    
	       JSONObject jsonObject = (JSONObject) obj;
	       jsonObject.put("statusInfo", Integer.toString(response.getStatus()));
	       logger.info("Get Response API JSON  :::: " + jsonObject);
	       return jsonObject;
	}	
		public HashMap<String, String> broadcastevent(HashMap<String, String> apiresponse){
			String responseFlag = null;
			try {
				client = Client.create();
				webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
						.path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.BROADCAST);
				
				logger.info("WebResourec BaseURL :::: "+webResource);
		 		response = webResource
						   .accept(ApiResources.Content_Type)
						   .type(ApiResources.Content_Type)
						   .header("Authorization", "VBrick " + apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
						   .put(ClientResponse.class," ");

				if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				    responseFlag = response.getEntity(String.class);
					   logger.info("Start Video API JSON  :::: " + responseFlag);
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else {
				    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				   }
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return geteventapiresponse;

		}
		
		public HashMap<String, String> endevent(HashMap<String, String> apiresponse){
			String responseFlag = null;
			try {
				client = Client.create();
				webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
						.path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.START);
				
				logger.info("WebResourec BaseURL :::: "+webResource);
		 		response = webResource
						   .accept(ApiResources.Content_Type)
						   .type(ApiResources.Content_Type)
						   .header("Authorization", "VBrick " + apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
						   .delete(ClientResponse.class," ");

				if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				    responseFlag = response.getEntity(String.class);
					   logger.info("Start Video API JSON  :::: " + responseFlag);
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   geteventapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else {
				    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				   }
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return geteventapiresponse;

		}

		public JSONObject getWebcastComments(HashMap<String, String> apiresponse) throws Exception {
		
			String responseFlag = null;
				client = Client.create();
				MultivaluedMap queryParams = new MultivaluedMapImpl();
				if(apiresponse.get(IAPIConstantCodes.runNumber)!=null)
				{
					queryParams.add(RevbaseAPIURLs.runNumber,apiresponse.get(IAPIConstantCodes.runNumber));
				}
				else if(apiresponse.get(IAPIConstantCodes.SIZE)!=null)
				{
					queryParams.add(RevbaseAPIURLs.size,apiresponse.get(IAPIConstantCodes.SIZE));
				}
				else if(apiresponse.get(IAPIConstantCodes.scrollId)!=null)
				{
					queryParams.add(RevbaseAPIURLs.scrollId,apiresponse.get(IAPIConstantCodes.scrollId));
				}
				webResource = client
									.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
									.path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID) + RevbaseAPIURLs.COMMENTSURL);
				
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
	       JSONArray jsonarray = (JSONArray) obj;
	       
	       //this is for the 0th element
	       JSONObject commentobject=(JSONObject)jsonarray.get(0);
	       String date=removemillisecondsfromcurrentutctime(commentobject.get("date").toString()); 
	       commentobject.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
	       commentobject.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
	       commentobject.put("date", date);
	       
	       
	       logger.info("@@@@@Objects present    :::: " + commentobject);
	       return commentobject;			
		}


		public  JSONArray getWebcastCommentsasempty(HashMap<String, String> apiresponse) throws Exception {
			
			String responseFlag = null;
				client = Client.create();
				MultivaluedMap queryParams = new MultivaluedMapImpl();
				if(apiresponse.get(IAPIConstantCodes.runNumber)!=null)
				{
					queryParams.add(RevbaseAPIURLs.runNumber,apiresponse.get(IAPIConstantCodes.runNumber));
				}
				else if(apiresponse.get(IAPIConstantCodes.SIZE)!=null)
				{
					queryParams.add(RevbaseAPIURLs.size,apiresponse.get(IAPIConstantCodes.SIZE));
				}
				else if(apiresponse.get(IAPIConstantCodes.scrollId)!=null)
				{
					queryParams.add(RevbaseAPIURLs.scrollId,apiresponse.get(IAPIConstantCodes.scrollId));
				}
				webResource = client
									.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
									.path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID) + RevbaseAPIURLs.COMMENTSURL);
				
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
	       JSONArray jsonarray = (JSONArray) obj;
	       jsonarray.add(0,Integer.toString(response.getStatus()));
	       jsonarray.add(1,response.getStatusInfo().toString());
	       return jsonarray;			
		}

		
		
		
		
		
		public String removemillisecondsfromcurrentutctime(String time) { 
			HashMap<String, String> dates = new HashMap<String, String>();
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			formatter.setTimeZone(TimeZone.getTimeZone(time));
			Date d = new Date();
			DateTime dateTimeUtc = new DateTime(d, DateTimeZone.UTC);
			int milliseconds=dateTimeUtc.getMillisOfSecond();
			DateTime currentdate= dateTimeUtc.plusDays(0).minusMillis(milliseconds);
			return currentdate.toString();	
			
			}




}

		
		

