package com.vbricks.avenger.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.vbrick.avenger.apibeans.CreatePushContentBean;
import com.vbrick.avenger.apibeans.CreateRealTimeAtteendesBean;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateEmbeddedContentService;
import com.vbricks.avenger.service.ICreatePushContentService;
import com.vbricks.avenger.service.ICreateRealTimeAtteendes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.ApiUtils;

public class HidingLevelAnalyticsApi{
	
	private ClientResponse response;
	private Client client;
	private WebResource webResource;
	private static Logger logger = Logger.getLogger(HidingLevelAnalyticsApi.class);
	
	
	public JSONObject GetVideoReport(HashMap<String, String> apiresponse) {
		String responseFlag = null;
		ApiUtils apiutils=new ApiUtils();
		client = Client.create();
		 MultivaluedMap queryParams = new MultivaluedMapImpl();
		

		 HashMap<String, String> dates=apiutils.UTCdatetimeformatEventdays("6", "10");
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
 	   
 		logger.info("@@@@@Status    :::: " + response.getStatus());
 	   responseFlag = response.getEntity(String.class);
 	  
       Object obj=JSONValue.parse(responseFlag);    
       JSONObject jsonObject = (JSONObject) obj;
       
       jsonObject.put(IAPIConstantCodes.APIResponseStatusInfo, Integer.toString(response.getStatus()));
       JSONArray contentlink=(JSONArray) jsonObject.get("contentLinks");
       //System.out.println("CONTENT LINK PRESENT ::::::::::::::"+contentlink.get(0));
       
      
 
       return jsonObject ;
		
	}
	
	
	public  ArrayList<String>  GetVideoReportinvalid(HashMap<String, String> apiresponse) 
	{
		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
			     .path(RevbaseAPIURLs.GETVideo+RevbaseAPIURLs.Report);
		logger.info("WebResourec BaseURL :::: "+webResource);
		response = webResource
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .get(ClientResponse.class);
		
 		System.out.println("Response status of Get Video Report Api::::::::::::"+response.getStatus());
		 responseFlag = response.getEntity(String.class);
	 	    int status=response.getStatus();
	 	   String Httpstatus = Integer.toString(status);
	 	  ArrayList<String> GetVideoReportinvalidresponse= new ArrayList<>( Arrays.asList(Httpstatus));
	        return GetVideoReportinvalidresponse;
		}
	
	
	
	/*public  ArrayList<String>  GetVideoWatchReportinvalid(HashMap<String, String> apiresponse) 
	{
		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
			     .path(RevbaseAPIURLs.EDITVIDEOCOMMENTURL+apiresponse.get(IAPIConstantCodes.APIVIDEOID)+RevbaseAPIURLs.USER+apiresponse.get(IAPIConstantCodes.APIResponseUSERID)+RevbaseAPIURLs.Status);
		logger.info("WebResourec BaseURL :::: "+webResource);
		response = webResource
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .get(ClientResponse.class);
		
 		System.out.println("Response status of Get Video Watch Report Api:::::"+response.getStatus());
		 responseFlag = response.getEntity(String.class);
	 	    //int status=response.getStatus();
	 	   //String Httpstatus = Integer.toString(response.getStatus());
	 	  ArrayList<String> GetVideoWatchReportinvalidresponse= new ArrayList<>( Arrays.asList(Integer.toString(response.getStatus())));
	        return GetVideoWatchReportinvalidresponse;
	}*/
	
	

	public  ArrayList<String>  GetUsersByLoginDateInvalid(HashMap<String, String> apiresponse) 
	{
		String responseFlag = null;
		client = Client.create();
		 MultivaluedMap queryParams = new MultivaluedMapImpl();
		 
		 if(apiresponse.get("sortfield").equals("Yes"))
		 {
			 if(IAPIConstantCodes.sortField.equals(IAPIConstantCodes.LastLogin))
			 queryParams.add(IAPIConstantCodes.sortField, IAPIConstantCodes.LastLogin); 
		  
		    if(IAPIConstantCodes.sortField.equals(IAPIConstantCodes.Username))
			 queryParams.add(IAPIConstantCodes.sortField, IAPIConstantCodes.Username); 
		  }
		 else  if(apiresponse.get("sortfield").equals("No"))
			 queryParams.remove(IAPIConstantCodes.sortField);
		 
		 if(apiresponse.get("sortorder").equals("Yes"))
		 {
			 if(IAPIConstantCodes.sortOrder.equals(IAPIConstantCodes.asc))
			 queryParams.add(IAPIConstantCodes.sortOrder, IAPIConstantCodes.asc); 
		  
		    if(IAPIConstantCodes.sortOrder.equals(IAPIConstantCodes.desc))
			 queryParams.add(IAPIConstantCodes.sortOrder, IAPIConstantCodes.desc); 
		      
		  }
		 else if(apiresponse.get("sortorder").equals("No"))
			 queryParams.remove(IAPIConstantCodes.sortOrder);
		 
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
			     .path(RevbaseAPIURLs.GETUSERSBYLOGINDATE);
		logger.info("WebResourec BaseURL :::: "+webResource);
		response = webResource
				   .queryParams(queryParams)
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .get(ClientResponse.class);
		
 		System.out.println("Response status of Get Users By Login Date Api::::::::"+response.getStatus());
		 responseFlag = response.getEntity(String.class);
	 	    //int status=response.getStatus();
	 	   //String Httpstatus = Integer.toString(response.getStatus());
	 	  ArrayList<String> GetVideoWatchReportinvalidresponse= new ArrayList<>( Arrays.asList(Integer.toString(response.getStatus())));
	        return GetVideoWatchReportinvalidresponse;
	}
	
	
	public  ArrayList<String>  GetWebcastAttendeesReportDeprecatedinvalid(HashMap<String, String> apiresponse) 
	{
		String responseFlag = null;
		client = Client.create();
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
			     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.Report);
		logger.info("WebResourec BaseURL :::: "+webResource);
		response = webResource
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .get(ClientResponse.class);
		
 		System.out.println("Response status of Get Webcast Attendees Report Deprecated Api::::::"+response.getStatus());
		 responseFlag = response.getEntity(String.class);
	 	    int status=response.getStatus();
	 	   String Httpstatus = Integer.toString(status);
	 	  ArrayList<String> GetWebcastAttendeesinvalidresponse= new ArrayList<>( Arrays.asList(Httpstatus));
	        return GetWebcastAttendeesinvalidresponse;
		}
	
	public JSONObject GetWebcastAttendeesReport(HashMap<String, String> apiresponse)
	{
		String responseFlag = null;
		client = Client.create();
		
  MultivaluedMap queryParams = new MultivaluedMapImpl();
		 
		 if(apiresponse.get("runnumber").equals("Yes"))		
			 queryParams.add(IAPIConstantCodes.runNumber,apiresponse.get(IAPIConstantCodes.runNumber)); 
	 
		 else  if(apiresponse.get("runnumber").equals("No"))
			 queryParams.remove(IAPIConstantCodes.runNumber);
		 
		 if(apiresponse.get("scrollid").equals("Yes"))
			 queryParams.add(IAPIConstantCodes.scrollId,apiresponse.get( IAPIConstantCodes.scrollId)); 
		  
		 else if(apiresponse.get("scrollid").equals("No"))
			 queryParams.remove(IAPIConstantCodes.scrollId);
		
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
			     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.Posteventreport);
		logger.info("WebResourec BaseURL :::: "+webResource);
		response = webResource
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .get(ClientResponse.class);
		
 		System.out.println("Response status of Get Webcast Attendees Report Api::::::"+response.getStatus());
		 responseFlag = response.getEntity(String.class);
		 Object obj=JSONValue.parse(responseFlag);
		 JSONObject webcastattendesreportapiresponse=(JSONObject)obj;
		 webcastattendesreportapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
		 webcastattendesreportapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
		 
		 System.out.println("Response body Contains:::::"+webcastattendesreportapiresponse);
		 return webcastattendesreportapiresponse;
	}
	
	public JSONObject WebcastAttendeesrealtime(HashMap<String, String> apiresponse,CreateRealTimeAtteendesBean createreattimeattendesBean)
	{
		String responseFlag = null;
		client = Client.create();
			
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
			     .path(RevbaseAPIURLs.Event1+apiresponse.get(IAPIConstantCodes.APIEVENTID)+RevbaseAPIURLs.Realtimeattendees);
		logger.info("WebResourec BaseURL :::: "+webResource);
		response = webResource
				   .type(ApiResources.Content_Type)
				   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
				   .post(ClientResponse.class,WebcastAttendeesrealtimejson(createreattimeattendesBean));
		
 		System.out.println("Response status of Get Webcast Attendees Report Api::::::"+response.getStatus());
		 responseFlag = response.getEntity(String.class);
		 Object obj=JSONValue.parse(responseFlag);
		 JSONObject webcastattendesreportapiresponse=(JSONObject)obj;
		 webcastattendesreportapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
		 webcastattendesreportapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
		 
		 System.out.println("Response body Contains:::::"+webcastattendesreportapiresponse);
		 return webcastattendesreportapiresponse;
	}
	
	@SuppressWarnings("unchecked")
	private String WebcastAttendeesrealtimejson(CreateRealTimeAtteendesBean createreattimeattendesBean )
	{
		JSONObject createRealTimeAtteendesJson = new JSONObject();
	 
		try {
		   //CreateRealTimeAtteendesBean createreattimeattendesBean
		// 1.sortField
				if ( createreattimeattendesBean.getSortField() != null)
					createRealTimeAtteendesJson.put(ICreateRealTimeAtteendes.SortField, createreattimeattendesBean.getSortField());
								
		// 2.sortDirection
				if ( createreattimeattendesBean.getSortDirection() != null)
					createRealTimeAtteendesJson.put(ICreateRealTimeAtteendes.SortDirection, createreattimeattendesBean.getSortDirection());
			  
	  // 3.count
				if (createreattimeattendesBean.getCount()!=null)
					createRealTimeAtteendesJson.put(ICreateRealTimeAtteendes.Count, createreattimeattendesBean.getCount());
				 
				
	 // 4.status
				if (createreattimeattendesBean.getStatus() != null)
					createRealTimeAtteendesJson.put(ICreateRealTimeAtteendes.Status, createreattimeattendesBean.getStatus());
				
  //5.attendeeDetails
				if (createreattimeattendesBean.getAttendeeDetails() != null)
					createRealTimeAtteendesJson.put(ICreateRealTimeAtteendes.AttendeeDetails,createreattimeattendesBean.getAttendeeDetails());	
							
	 //6.scrollId
				if (createreattimeattendesBean.getScrollId() != null)
					createRealTimeAtteendesJson.put(ICreateRealTimeAtteendes.ScrollId, createreattimeattendesBean.getScrollId());
				
				//else if (createreattimeattendesBean.getScrollId().isEmpty() || createreattimeattendesBean.getScrollId().contentEquals(null) )
					//createRealTimeAtteendesJson.put(ICreateRealTimeAtteendes.ScrollId,createreattimeattendesBean.getScrollId());
			 
	//7.Q
				if (createreattimeattendesBean.getQ() != null)
					createRealTimeAtteendesJson.put(ICreateRealTimeAtteendes.Q, createreattimeattendesBean.getQ());
			
				 
	//8.searchField			
				
				if (createreattimeattendesBean.getSearchField() != null)
					createRealTimeAtteendesJson.put(ICreateRealTimeAtteendes.SearchField, createreattimeattendesBean.getSearchField());
				
		
	// 9.runNumber
				if (createreattimeattendesBean.getRunNumber() != null)
					createRealTimeAtteendesJson.put(ICreateRealTimeAtteendes.RunNumber, createreattimeattendesBean.getRunNumber());
				
			 
				
		}catch (Exception e) {
			e.printStackTrace();

		}//
		
		logger.info("API REQUEST BODY OF REAL TIME ATTEENDES::::::::::::::"+createRealTimeAtteendesJson);
	return createRealTimeAtteendesJson.toString();
	}
	
}
