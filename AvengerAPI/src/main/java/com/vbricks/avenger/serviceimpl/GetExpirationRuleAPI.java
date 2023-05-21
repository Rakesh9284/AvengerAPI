package com.vbricks.avenger.serviceimpl;


import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetExpirationRuleAPI {
	private static Logger logger = Logger.getLogger(GetExpirationRuleAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;	 
	HashMap<String, String> expirationdetailsapiresponse = new HashMap<String, String>();
 
	/** 
	 * @author neslavath
	 * @functionName : editVideoRating
	 * @Description : editVideoRating API - will able give ratings on mention video ID of the Video into the application 
	 * @param loginapiresponse (baseUrl,accessToken,VideoID
	 * @return editvideoratingapi  response(baseUrl,accessToken, apiresponse200OK)
	 */
	
	public JSONObject getexpirationrules(HashMap<String, String> apiresponse)  {
     
		String responseFlag = null;
	     
 client = Client.create();
	             
	            webResource = client
	                                .resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
	                                .path(RevbaseAPIURLs.GETEXPIRATIONRULES);
	             logger.info("API Request as URL    :::: "+ webResource);
	             
	                        response = webResource
	                                .accept(ApiResources.Content_Type)
	                                .type(ApiResources.Content_Type)
	                                .header(ApiResources.Authorization, ApiResources.VBrick+apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
	                                .get(ClientResponse.class);
	                        JSONObject object = null;
	             
	            if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
	                responseFlag = response.getEntity(String.class);
	                   logger.info("Event Detai API JSON Response :::: " + responseFlag);
	                   expirationdetailsapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
	                   expirationdetailsapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, apiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
	                                  
	                   JSONParser jsonParser = new JSONParser();
	                   JSONArray jsonarray = null;
	                try {
	                    jsonarray = (JSONArray) jsonParser.parse(responseFlag);
	                } catch (ParseException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	                   
	                   System.out.println("@@@@@json array size is"+jsonarray.size());
	                    object = (JSONObject) jsonarray.get(0);
	                   System.out.println("json array is"+object);
	                   System.out.println("Rule ID value is"+object.get("ruleId"));
	                   System.out.println("Rule ID value is"+object.get("ruleName"));
	                   System.out.println("@@@@@json array value is"+jsonarray.indexOf(0));
	                   
	    }
	            return object;
	    }
}