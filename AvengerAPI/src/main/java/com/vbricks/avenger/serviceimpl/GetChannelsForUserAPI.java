package com.vbricks.avenger.serviceimpl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;


public class GetChannelsForUserAPI {
	private static Logger logger = Logger.getLogger(GetChannelsForUserAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;	 
	HashMap<String, String> channeldetailsapiresponse = new HashMap<String, String>();
 
	/** 
	 * @author neslavath
	 * @functionName : editVideoRating
	 * @Description : editVideoRating API - will able give ratings on mention video ID of the Video into the application 
	 * @param loginapiresponse (baseUrl,accessToken,VideoID
	 * @return editvideoratingapi  response(baseUrl,accessToken, apiresponse200OK)
	 */
	
	public JSONObject channelDetailsforvideocountUpdated(HashMap<String, String> apiresponse,String libraryname) {

	       String responseFlag = null;
	        JSONArray jsonarray = null;
	        try {
	           client = Client.create();
	           webResource = client
	                    .resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
	                    .path(RevbaseAPIURLs.SEARCHACCESSENTITYURL+RevbaseAPIURLs.CHANNELS);
	            logger.info("API Request as URL    :::: "+ webResource);

	           response = webResource
	                    .accept(ApiResources.Content_Type)
	                    .type(ApiResources.Content_Type)
	                    .header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
	                    .get(ClientResponse.class);

	           if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
	                responseFlag = response.getEntity(String.class);
	            }
	           
	             JSONParser jsonParser = new JSONParser();
	             jsonarray = (JSONArray) jsonParser.parse(responseFlag);
	        } catch (Exception e) {
	            e.printStackTrace();
	      }
	        System.out.println("MY ARRAY::::::::::::::::"+jsonarray);
	        return array(jsonarray,libraryname);
	    }

	    public JSONObject array(JSONArray jsonarray,String value)
	    {
	        JSONObject channeldetailsapiresponse = null;
	        for (int i = 0; i < jsonarray.size(); i++) {

	           System.out.println("text is"+jsonarray.get(i));
	            JSONObject explrObject = (JSONObject) jsonarray.get(i);
	           //System.out.println("explrObject"+explrObject+"i");
	            explrObject.get("name");
	            System.out.println("@@@@@channel name is"+explrObject.get("name"));
	            if(explrObject.get("name").equals(value))
	            {
	                System.out.println("conditon satisfied");
	                channeldetailsapiresponse=explrObject;
	            }
	            
	            System.out.println("channelName"+explrObject.get("name"));
	            // store each object in JSONObject  
	            // JSONObject explrObject = jsonarray.get(i);  

	           // get field value from JSONObject using get() method  
	            //System.out.println(explrObject.get("address"));  
	        }
	        System.out.println("new object value is"+channeldetailsapiresponse);
	        return channeldetailsapiresponse;
	    }
	    
		public JSONObject channelDetailsJsonObject(HashMap<String, String> apiresponse) {

			String responseFlag = null;
	 	
				client = Client.create();
				 
				webResource = client
									.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
									.path(RevbaseAPIURLs.SEARCHACCESSENTITYURL+RevbaseAPIURLs.CHANNELS);
				 logger.info("API Request as URL    :::: "+ webResource);
				 
				    		response = webResource
						            .accept(ApiResources.Content_Type)
							        .type(ApiResources.Content_Type)
									.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
									.get(ClientResponse.class);
				    		
				    		responseFlag = response.getEntity(String.class);
						
				    System.out.println("@@@@@@@@@@@@Status:::::::"+response.getStatus());
				    Object obj=JSONValue.parse(responseFlag);
				    JSONArray jsonarray=(JSONArray)obj;
				    JSONObject channeldetailsapiresponse = null;
			        for (int i = 0; i < jsonarray.size(); i++) {

			           //System.out.println("text is"+jsonarray.get(i));
			            JSONObject explrObject = (JSONObject) jsonarray.get(i);
			            System.out.println("@@@@@channel name is"+explrObject.get("name"));
			            if(explrObject.get("name").equals(apiresponse.get("name")))
			            {
			                System.out.println("conditon satisfied");
			                channeldetailsapiresponse=explrObject;
			            }
			            //System.out.println("channelName"+explrObject.get("name"));
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
	    
	
	public HashMap<String, String> channelDetails(HashMap<String, String> apiresponse) {

		String responseFlag = null;
 		try {

			client = Client.create();
			 
			webResource = client
								.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.SEARCHACCESSENTITYURL+RevbaseAPIURLs.CHANNELS);
			 logger.info("API Request as URL    :::: "+ webResource);
			 
			    		response = webResource
					            .accept(ApiResources.Content_Type)
						        .type(ApiResources.Content_Type)
								.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.get(ClientResponse.class);
			 
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
			    String a[]=responseFlag.replaceAll("[\"']*","").split(",");
			    
				   logger.info("Event Detai API JSON Response :::: " + responseFlag);
				   channeldetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   channeldetailsapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				   channeldetailsapiresponse.put(IAPIConstantCodes.HEADERBACKGROUND, JsonPath.parse(responseFlag).read("$.headerBackGroundColor").toString());
				   channeldetailsapiresponse.put(IAPIConstantCodes.HEADERFONTCOLOR,  JsonPath.parse(responseFlag).read("$.headerFontColor").toString());
				    logger.info("statusInfo ::: "+response.getStatusInfo().toString());
				   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
				   
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
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
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return channeldetailsapiresponse;
	}


public HashMap<String, String> channelDetailwithoutauthorization(HashMap<String, String> apiresponse) {

	String responseFlag = null;
		try {

		client = Client.create();
		 
		webResource = client
							.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
							.path(RevbaseAPIURLs.SEARCHACCESSENTITYURL+RevbaseAPIURLs.CHANNELS);
		 logger.info("API Request as URL    :::: "+ webResource);
		 
		    		response = webResource
				            .accept(ApiResources.Content_Type)
					        .type(ApiResources.Content_Type)
							.header(ApiResources.Authorization, ApiResources.VBrick+"")
							.get(ClientResponse.class);
		 
		if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
		    responseFlag = response.getEntity(String.class);
		    String a[]=responseFlag.replaceAll("[\"']*","").split(",");
			   logger.info("Event Detai API JSON Response :::: " + responseFlag);
			   channeldetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   channeldetailsapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
			   channeldetailsapiresponse.put(IAPIConstantCodes.HEADERBACKGROUND, JsonPath.parse(responseFlag).read("$.headerBackGroundColor").toString());
			   channeldetailsapiresponse.put(IAPIConstantCodes.HEADERFONTCOLOR,  JsonPath.parse(responseFlag).read("$.headerFontColor").toString());
			    logger.info("statusInfo ::: "+response.getStatusInfo().toString());
			   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
			   
		   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
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
		   } else {
		    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		   }
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return channeldetailsapiresponse;
}

public HashMap<String, String> channelDetailsforvideocount(HashMap<String, String> apiresponse) {

	String responseFlag = null;
		try {

		client = Client.create();
		 
		webResource = client
							.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
							.path(RevbaseAPIURLs.SEARCHACCESSENTITYURL+RevbaseAPIURLs.CHANNELS);
		 logger.info("API Request as URL    :::: "+ webResource);
		 
		    		response = webResource
				            .accept(ApiResources.Content_Type)
					        .type(ApiResources.Content_Type)
							.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
							.get(ClientResponse.class);
		 
		if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
		    responseFlag = response.getEntity(String.class);
		    String a[]=responseFlag.replaceAll("[\"']*","").split(",");
		    String b[] = a[6].split(":");;
		    logger.info("videocountplace:::: " + a[6]);
			   logger.info("Event Detai API JSON Response :::: " + responseFlag);
			   channeldetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   channeldetailsapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
			   channeldetailsapiresponse.put(IAPIConstantCodes.VIDEOCOUNT,b[1]);
			   logger.info("statusInfo ::: "+response.getStatusInfo().toString());
			   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
			   
		   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
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
		   } else {
		    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		   }
	
	} catch (Exception e) {
		e.printStackTrace();
	}
		System.out.println("REsponse@@@@@"+channeldetailsapiresponse);
	return channeldetailsapiresponse;
}

public HashMap<String, String> channelDetailsforeditrights(HashMap<String, String> apiresponse) {

	String responseFlag = null;
		try {

		client = Client.create();
		 
		webResource = client
							.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
							.path(RevbaseAPIURLs.SEARCHACCESSENTITYURL+RevbaseAPIURLs.CHANNELS);
		 logger.info("API Request as URL    :::: "+ webResource);
		 
		    		response = webResource
				            .accept(ApiResources.Content_Type)
					        .type(ApiResources.Content_Type)
							.header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
							.get(ClientResponse.class);
		 
		if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
		    responseFlag = response.getEntity(String.class);
		    String a[]=responseFlag.replaceAll("[\"']*","").split(",");
		    String b[] = a[6].split(":");;
		    String c[] = a[7].split(":");;
		    String d[] = a[8].split(":");;
		    logger.info("videocountplace:::: " + a[6]);
			   logger.info("Event Detai API JSON Response :::: " + responseFlag);
			   channeldetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   channeldetailsapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
			   channeldetailsapiresponse.put(IAPIConstantCodes.VIDEOCOUNT,b[1]);
			   channeldetailsapiresponse.put(IAPIConstantCodes.canedit,c[1]);
			   channeldetailsapiresponse.put(IAPIConstantCodes.canassign,d[1]);
			   logger.info("statusInfo ::: "+response.getStatusInfo().toString());
			   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
			   
		   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
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
		   } else {
		    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		   }
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return channeldetailsapiresponse;
}

}