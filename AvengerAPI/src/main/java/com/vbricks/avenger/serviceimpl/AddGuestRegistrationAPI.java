package com.vbricks.avenger.serviceimpl;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbrick.avenger.apibeans.CreateRealTimeAtteendesBean;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateRealTimeAtteendes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;

public class AddGuestRegistrationAPI {
	
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> guestregistrationapiresponse = new HashMap<String, String>();
	private static Logger logger = Logger.getLogger(AddGuestRegistrationAPI.class);
	 
	public JSONObject guestregistrationjsonobject(HashMap<String, String> apiresponse){
		String responseFlag = null;
			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					.path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.Registrations);
			
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .post(ClientResponse.class,Guestuserjson());
			
	 	responseFlag = response.getEntity(String.class);
	 	Object obj=JSONValue.parse(responseFlag);   
	 	JSONObject guestregistrationapiresponse = (JSONObject) obj;
	 	guestregistrationapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
	 	guestregistrationapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
	 	return guestregistrationapiresponse;		
	}	


	@SuppressWarnings("unchecked")
	private String Guestuserjson()
	{
		JSONObject addguestuser=new JSONObject();
	    
	    addguestuser.put("name","guest1");
	    addguestuser.put("email","guestuser1@gmail.com");
		 
		
		return addguestuser.toString();
		
	}
}
