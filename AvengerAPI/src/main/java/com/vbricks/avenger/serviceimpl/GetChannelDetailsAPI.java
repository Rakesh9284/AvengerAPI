package com.vbricks.avenger.serviceimpl;


import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.vbrick.avenger.apibeans.AddTeamBean;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;


public class GetChannelDetailsAPI {
	private static Logger logger = Logger.getLogger(GetChannelDetailsAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;	 
	HashMap<String, String> channeldetailapiresponse = new HashMap<String, String>();
 
	/** 
	 * @author neslavath
	 * @functionName : editVideoRating
	 * @Description : editVideoRating API - will able give ratings on mention video ID of the Video into the application 
	 * @param loginapiresponse (baseUrl,accessToken,VideoID
	 * @return editvideoratingapi  response(baseUrl,accessToken, apiresponse200OK)
	 */
	
	  
			public JSONObject channelDetailsJsonObject(HashMap<String, String> apiresponse) {

				String responseFlag = null;
		 	
					client = Client.create();
					 MultivaluedMap queryParams = new MultivaluedMapImpl();
					
					if(!apiresponse.get("size").equals("0")) 
						queryParams.add("size",apiresponse.get("size"));
					else
						queryParams.remove(apiresponse.get("size"));
					
					webResource = client
										.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
										.path(RevbaseAPIURLs.GETTEAMDETAILS);
					 logger.info("API Request as URL    :::: "+ webResource);
					 
					    		response = webResource
					    				.queryParams(queryParams)
							            .accept(ApiResources.Content_Type)
								        .type(ApiResources.Content_Type)
										.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
										.get(ClientResponse.class);
					    		
					    		responseFlag = response.getEntity(String.class);
							
					    
					    Object obj=JSONValue.parse(responseFlag);
					    JSONArray jsonarray=(JSONArray)obj;
					    JSONObject channeldetailsapiresponse = null;
				        for (int i = 0; i < jsonarray.size(); i++) {

				           //System.out.println("text is"+jsonarray.get(i));
				            JSONObject explrObject = (JSONObject) jsonarray.get(i);
				            //System.out.println("@@@@@channel name is"+explrObject.get("name"));
				            if(explrObject.get("name").equals(apiresponse.get("teamname")))
				            {
				                System.out.println("conditon satisfied");
				                channeldetailsapiresponse=explrObject;
				            }
				            System.out.println("channelName"+explrObject.get("name"));
				        }
				        
				        
				        if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200)
				        channeldetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus())); 
				      
				       
				    else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
					   channeldetailsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   channeldetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
							   
				      } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				    	  channeldetailsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				    	  channeldetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
						   channeldetailsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
						   channeldetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
						   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
							   channeldetailsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
							   channeldetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
						   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
							  channeldetailsapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
							  channeldetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				        
						   }			        
				   System.out.println("Channel contains::::::::::::::::::::"+channeldetailsapiresponse);
				 
						return channeldetailsapiresponse;
		    
		 		}
		    
		public HashMap<String, String> channelDetail(HashMap<String, String> apiresponse) {

		String responseFlag = null;
 		try {

			client = Client.create();
			 
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.GETTEAMDETAILS);
			 logger.info("API Request as URL    :::: "+ webResource);
			 
			    		response = webResource
					            .accept(ApiResources.Content_Type)
						        .type(ApiResources.Content_Type)
								.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.get(ClientResponse.class);
			 
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
			    String a[]=responseFlag.replaceAll("[\"']*","").split(",");
			    
			    String b[]=a[11].split(":");
				   System.out.println(b[1]);
				   
				   String c[]=a[12].split(":");
				   System.out.println(c[1]);
				   
				   logger.info("Event Detai API JSON Response :::: " + responseFlag);
				   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				   channeldetailapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				   channeldetailapiresponse.put(IAPIConstantCodes.HEADERBACKGROUND, b[1]);
				   channeldetailapiresponse.put(IAPIConstantCodes.HEADERFONTCOLOR,  c[1]);
				    logger.info("statusInfo ::: "+response.getStatusInfo().toString());
				   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
 		logger.info("@@@@@@@@@Channel details API Response::::::::"+channeldetailapiresponse);
		return channeldetailapiresponse;
	}


public HashMap<String, String> channelDetailwithoutauthorization(HashMap<String, String> apiresponse) {

	String responseFlag = null;
		try {

		client = Client.create();
		 
		webResource = client
							.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
							.path(RevbaseAPIURLs.GETTEAMDETAILS);
		 logger.info("API Request as URL    :::: "+ webResource);
		 
		    		response = webResource
				            .accept(ApiResources.Content_Type)
					        .type(ApiResources.Content_Type)
							//.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
							.get(ClientResponse.class);
		 
		if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
		    responseFlag = response.getEntity(String.class);
		    String a[]=responseFlag.replaceAll("[\"']*","").split(",");
			   logger.info("Event Detai API JSON Response :::: " + responseFlag);
			   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   channeldetailapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
			   channeldetailapiresponse.put(IAPIConstantCodes.HEADERBACKGROUND, JsonPath.parse(responseFlag).read("$.headerBackGroundColor").toString());
			   channeldetailapiresponse.put(IAPIConstantCodes.HEADERFONTCOLOR,  JsonPath.parse(responseFlag).read("$.headerFontColor").toString());
			    logger.info("statusInfo ::: "+response.getStatusInfo().toString());
			   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
			   
		   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
			   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   
		   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
			   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
		   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
			   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
		   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
			   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
		   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
			   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
			   channeldetailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
		   } else {
		    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		   }
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return channeldetailapiresponse;
}
}