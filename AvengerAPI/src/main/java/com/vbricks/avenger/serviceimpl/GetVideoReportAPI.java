package com.vbricks.avenger.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import javax.ws.rs.core.MultivaluedMap;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class GetVideoReportAPI {
	
	private ClientResponse response;
	private Client client;
	private WebResource webResource;
	private static Logger logger = Logger.getLogger(GetVideoReportAPI.class);
	
	/*public JSONObject  GetVideoReport(HashMap<String, String> apiresponse,String videoTitle)  
	{
		String responseFlag = null;
		ApiUtils apiutils=new ApiUtils();
		client = Client.create();
		 MultivaluedMap queryParams = new MultivaluedMapImpl();
			

		 HashMap<String, String> dates=apiutils.UTCdatetimeformatEventdays("0", "1");
		  String afterDate= dates.get("afterdate").toString();
		  String beforeDate =dates.get("beforedate").toString();
		  
		 if("videoIds".equals("Yes"))
			 queryParams.add("videoIds",apiresponse.get(IAPIConstantCodes.APIVIDEOID));
		 
		 else if("videoIds".equals("No"))
			 queryParams.remove("videoIds");
		  
		 else if("after".equals("Yes"))
			  queryParams.add("after", afterDate);
		 else if("after".equals("No"))
			 queryParams.remove("after");
		 
		 else if("before".equals("Yes"))
			  queryParams.add("after", beforeDate); 
		 else if("before".equals("No"))
			 queryParams.remove("before");
			 
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
			     .path(RevbaseAPIURLs.GETVideo+RevbaseAPIURLs.Report);
		logger.info("WebResourec BaseURL :::: "+webResource);
		response = webResource
				   .queryParams(queryParams)
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .get(ClientResponse.class);
	
 		System.out.println("Response status of Get Video Report Api::::::::::::"+response.getStatus());
		 responseFlag = response.getEntity(String.class);
	 	Object obj=JSONValue.parse(responseFlag);
	 	JSONArray jsonarray=(JSONArray)obj;	
	 	 JSONObject videoreportapiresponse = null;
	        for (int i = 0; i < jsonarray.size(); i++) {
	        	 
	        JSONObject explrObject = (JSONObject) jsonarray.get(i);
	        //System.out.println("@@@@@video title is"+explrObject.get("title"));
	         if(explrObject.get("title").equals(videoTitle))
            {
                System.out.println("conditon satisfied");
                videoreportapiresponse=explrObject;
            }	   	
	      }
	        if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200)
	        	videoreportapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus())); 
		 
		    else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
		    	videoreportapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	videoreportapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					   
		      } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
		    	  videoreportapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		    	  videoreportapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				  videoreportapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				  videoreportapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
					videoreportapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					videoreportapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   }  
	        
	        
	        
	        logger.info("#########GET VIDEO REPORT RESPONSE CONTAINS::::::::::::"+videoreportapiresponse);
	        return videoreportapiresponse;
	}*/







}
	        	
		 
	  	 
		
	       
	
