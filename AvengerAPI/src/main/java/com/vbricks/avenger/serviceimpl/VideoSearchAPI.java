package com.vbricks.avenger.serviceimpl;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;
/** 
 * @author neslavath
 * @functionName : deleteVideos
 * @Description : DeleteVideos API - will delete mention video ID of the Video into the application 
 * @param loginapiresponse (baseUrl,accessToken,VideoID
 * @return deleteVideoapi response(baseUrl,accessToken, apiresponse200OK)
 */
public class VideoSearchAPI {
	
	private static Logger logger = Logger.getLogger(VideoSearchAPI.class);
	public Client client;
	public ClientResponse response;
	ApiUtils apiUtils=new ApiUtils();
	WebResource webResource;
	HashMap<String, String> searchVideoapiresponse = new HashMap<String, String>();
    HashMap<String, ArrayList< String>> uploadedDateapiresponse=new HashMap<String, ArrayList< String>>();
	 
    
	public HashMap<String, String> searchVideo(HashMap<String, String> apiresponse ) {
		String responseFlag = null;
		try {
			client = Client.create();
		  MultivaluedMap queryParams = new MultivaluedMapImpl();
			 if(apiresponse.get(IAPIConstantCodes.Q)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.Q,apiresponse.get(IAPIConstantCodes.Q));
			 }
			 if(apiresponse.get(IAPIConstantCodes.TYPE)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.TYPE, apiresponse.get(IAPIConstantCodes.TYPE));
			 }
			 if(apiresponse.get(IAPIConstantCodes.SEARCHCATEGORIES)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.SEARCHCATEGORIES, apiresponse.get(IAPIConstantCodes.SEARCHCATEGORIES));
			 }
			 if(apiresponse.get(IAPIConstantCodes.STATUS)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.STATUS, apiresponse.get(IAPIConstantCodes.STATUS));
			 }
			 if(apiresponse.get(IAPIConstantCodes.COUNT)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.COUNT, apiresponse.get(IAPIConstantCodes.COUNT));
			 }
			 if(apiresponse.get(IAPIConstantCodes.UploaderIDs)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.UploaderIDs, apiresponse.get(IAPIConstantCodes.UploaderIDs));
			 }
			 if(apiresponse.get(IAPIConstantCodes.Uploaders)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.Uploaders, apiresponse.get(IAPIConstantCodes.Uploaders));
			 }
			 if(apiresponse.get(IAPIConstantCodes.sortField)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.sortField, apiresponse.get(IAPIConstantCodes.sortField));
			 }
			 if(apiresponse.get(IAPIConstantCodes.sortDirection)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.sortDirection, apiresponse.get(IAPIConstantCodes.sortDirection));
			 }
			 if(apiresponse.get(IAPIConstantCodes.ExactMatch)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.ExactMatch, apiresponse.get(IAPIConstantCodes.ExactMatch));
			 }
			 if(apiresponse.get(IAPIConstantCodes.SearchField)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.SearchField, apiresponse.get(IAPIConstantCodes.SearchField));
			 }
			 if(apiresponse.get(IAPIConstantCodes.Unlisted)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.Unlisted, apiresponse.get(IAPIConstantCodes.Unlisted));
			 }
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					       .path(RevbaseAPIURLs.VIDEOSEARCH);
			
			logger.info("WebResourec BaseURL :::: "+webResource);
			logger.info("************************queryParams path.. :::: "+queryParams);
            Thread.sleep(5000);
 	 		response = webResource
	 				   .queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
		
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Video List API JSON  :::: " + responseFlag);
				   String videolength=JsonPath.parse(responseFlag).read("$..videos.length()").toString();
				   searchVideoapiresponse.put(IVideoAccessControlService.totalvideos,videolength.substring(1, videolength.length()-1));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
	 			   String videoTitle=JsonPath.parse(responseFlag).read("$.videos[*].title").toString();
	 			 
	 			  List<String> uploadedlist =JsonPath.parse(responseFlag).read("$.videos[*].uploadedBy");
	 			  searchVideoapiresponse.put("uploadernames", uploadedlist.toString());
                  logger.info("Uploader List API JSON  :::: " + uploadedlist);
                   List<String> durationlist =JsonPath.parse(responseFlag).read("$.videos[*].duration");
                  ArrayList<String> list = new ArrayList<String>();
                  Thread.sleep(5000);
                 for(int i=0;i<durationlist.size();i++)
                 {
                	 String x=durationlist.get(i);
                     String y=x.substring(3,8);
                     {
                    	 list.add(y);
                     }
                  }
                list.toString();
             	String actualList=list.toString().replace("[", "");
        		String actualList1=actualList.replace("]", "");
                searchVideoapiresponse.put("TIMES",actualList1.replace(" ",""));
                logger.info("TIME List API JSON  :::: " + actualList1.toString());
                List<String> uploadedDatelist =JsonPath.parse(responseFlag).read("$.videos[*].whenUploaded");
                ArrayList<String> datelist = new ArrayList<String>();
                for(int j=0;j<uploadedDatelist.size();j++)
                {
                	 String x=uploadedDatelist.get(j);
                     datelist.add(apiUtils.convertUTCtoIST(x));
                }
 
                searchVideoapiresponse.put("whenUploaded", datelist.toString());
                List<String> titlelist =JsonPath.parse(responseFlag).read("$.videos[*].title");
                searchVideoapiresponse.put("title", titlelist.toString());
                logger.info("Videos List API JSON  :::: " + titlelist.toString());
	 			 searchVideoapiresponse.put(IVideoAccessControlService.videoTitle,videoTitle.substring(2, videoTitle.length()-2));
				   searchVideoapiresponse.put(IVideoAccessControlService.videoCount,JsonPath.parse(responseFlag).read("$.totalVideos").toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
					} catch (Exception e) {
			e.printStackTrace();
		}
		return searchVideoapiresponse;

	}
	
	public HashMap<String, ArrayList< String>> searchVideo_UploadedDate(HashMap<String, String> apiresponse ) {
		String responseFlag = null;
		try {
			client = Client.create();
		  MultivaluedMap queryParams = new MultivaluedMapImpl();
			 if(apiresponse.get(IAPIConstantCodes.Q)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.Q,apiresponse.get(IAPIConstantCodes.Q));
			 }
			 if(apiresponse.get(IAPIConstantCodes.TYPE)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.TYPE, apiresponse.get(IAPIConstantCodes.TYPE));
			 }
			 if(apiresponse.get(IAPIConstantCodes.SEARCHCATEGORIES)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.SEARCHCATEGORIES, apiresponse.get(IAPIConstantCodes.SEARCHCATEGORIES));
			 }
			 if(apiresponse.get(IAPIConstantCodes.STATUS)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.STATUS, apiresponse.get(IAPIConstantCodes.STATUS));
			 }
			 if(apiresponse.get(IAPIConstantCodes.COUNT)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.COUNT, apiresponse.get(IAPIConstantCodes.COUNT));
			 }
			 if(apiresponse.get(IAPIConstantCodes.UploaderIDs)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.UploaderIDs, apiresponse.get(IAPIConstantCodes.UploaderIDs));
			 }
			 if(apiresponse.get(IAPIConstantCodes.sortField)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.sortField, apiresponse.get(IAPIConstantCodes.sortField));
			 }
			 if(apiresponse.get(IAPIConstantCodes.sortDirection)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.sortDirection, apiresponse.get(IAPIConstantCodes.sortDirection));
			 }
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					       .path(RevbaseAPIURLs.VIDEOSEARCH);
			
			logger.info("WebResourec BaseURL :::: "+webResource);
			logger.info("************************queryParams path.. :::: "+queryParams);
            Thread.sleep(5000);
 	 		response = webResource
	 				   .queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
		
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Video List API JSON  :::: " + responseFlag);
				   String videolength=JsonPath.parse(responseFlag).read("$..videos.length()").toString();
				   searchVideoapiresponse.put(IVideoAccessControlService.totalvideos,videolength.substring(1, videolength.length()-1));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				  
			        ArrayList<String> responselist = new ArrayList<String>(); 
			       String reponse200= Integer.toString(response.getStatus());
			        String ok=response.getStatusInfo().toString();
			        responselist.add(reponse200);
			        responselist.add(ok);
				    uploadedDateapiresponse.put("response", responselist);
	 			   String videoTitle=JsonPath.parse(responseFlag).read("$.videos[*].title").toString();
	 			 
	 			  List<String> uploadedlist =JsonPath.parse(responseFlag).read("$.videos[*].uploadedBy");
	 			  searchVideoapiresponse.put("uploadernames", uploadedlist.toString());
                  logger.info("Uploader List API JSON  :::: " + uploadedlist);
                   List<String> durationlist =JsonPath.parse(responseFlag).read("$.videos[*].duration");
                  ArrayList<String> list = new ArrayList<String>();
                  Thread.sleep(5000);
                 for(int i=0;i<durationlist.size();i++)
                 {
                	 String x=durationlist.get(i);
                     String y=x.substring(3,8);
                     {
                    	 list.add(y);
                     }
                  }
                list.toString();
             	String actualList=list.toString().replace("[", "");
        		String actualList1=actualList.replace("]", "");
                searchVideoapiresponse.put("TIMES",actualList1.replace(" ",""));
                logger.info("TIME List API JSON  :::: " + actualList1.toString());
                List<String> uploadedDatelist =JsonPath.parse(responseFlag).read("$.videos[*].whenUploaded");
                ArrayList<String> datelist = new ArrayList<String>();
                for(int j=0;j<uploadedDatelist.size();j++)
                {
                	 String x=uploadedDatelist.get(j);
  
                }
                
                uploadedDateapiresponse.put("whenUploaded", datelist);
                
                List<String> titlelist =JsonPath.parse(responseFlag).read("$.videos[*].title");
                searchVideoapiresponse.put("title", titlelist.toString());
                logger.info("Videos List API JSON  :::: " + titlelist.toString());
	 			 searchVideoapiresponse.put(IVideoAccessControlService.videoTitle,videoTitle.substring(2, videoTitle.length()-2));
				   searchVideoapiresponse.put(IVideoAccessControlService.videoCount,JsonPath.parse(responseFlag).read("$.totalVideos").toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
					} catch (Exception e) {
			e.printStackTrace();
		}
		return  uploadedDateapiresponse;

	}
	public HashMap<String, String> searchVideo_OwnerField(HashMap<String, String> apiresponse ) {
		String responseFlag = null;
		try {
			client = Client.create();
		  MultivaluedMap queryParams = new MultivaluedMapImpl();
			 if(apiresponse.get(IAPIConstantCodes.Q)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.Q,apiresponse.get(IAPIConstantCodes.Q));
			 }
			 if(apiresponse.get(IAPIConstantCodes.TYPE)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.TYPE, apiresponse.get(IAPIConstantCodes.TYPE));
			 }
			 if(apiresponse.get(IAPIConstantCodes.SEARCHCATEGORIES)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.SEARCHCATEGORIES, apiresponse.get(IAPIConstantCodes.SEARCHCATEGORIES));
			 }
			 if(apiresponse.get(IAPIConstantCodes.STATUS)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.STATUS, apiresponse.get(IAPIConstantCodes.STATUS));
			 }
			 if(apiresponse.get(IAPIConstantCodes.COUNT)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.COUNT, apiresponse.get(IAPIConstantCodes.COUNT));
			 }
			 if(apiresponse.get(IAPIConstantCodes.UploaderIDs)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.UploaderIDs, apiresponse.get(IAPIConstantCodes.UploaderIDs));
			  
			 }
			 if(apiresponse.get(IAPIConstantCodes.Uploaders)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.Uploaders, apiresponse.get(IAPIConstantCodes.Uploaders));
			 }
			 if(apiresponse.get(IAPIConstantCodes.sortField)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.sortField, apiresponse.get(IAPIConstantCodes.sortField));
			 }
			 if(apiresponse.get(IAPIConstantCodes.sortDirection)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.sortDirection, apiresponse.get(IAPIConstantCodes.sortDirection));
			 }
			 if(apiresponse.get(IAPIConstantCodes.ExactMatch)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.ExactMatch, apiresponse.get(IAPIConstantCodes.ExactMatch));
			 }
			 if(apiresponse.get(IAPIConstantCodes.SearchField)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.SearchField, apiresponse.get(IAPIConstantCodes.SearchField));
			 }
			 if(apiresponse.get(IAPIConstantCodes.Unlisted)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.Unlisted, apiresponse.get(IAPIConstantCodes.Unlisted));
			 }
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					       .path(RevbaseAPIURLs.VIDEOSEARCH);
			
			logger.info("WebResourec BaseURL :::: "+webResource);
			logger.info("************************queryParams path.. :::: "+queryParams);
            Thread.sleep(5000);
 	 		response = webResource
	 				   .queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))				   
					   .get(ClientResponse.class);
 	 	
		
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Video List API JSON  :::: " + responseFlag);
				   String a[]=responseFlag.replaceAll("[\"']*","").split(",");
				   String b[]=a[21].split(":");
				   System.out.println(b[2]);
				   
				   String c[]=a[22].split(":");
				   System.out.println(c[1]);
				   
				   String d[]=a[23].split(":");
				   System.out.println(d[1]);
					
				   String videolength=JsonPath.parse(responseFlag).read("$..videos.length()").toString();
				   searchVideoapiresponse.put(IVideoAccessControlService.totalvideos,videolength.substring(1, videolength.length()-1));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseOWNERID,b[2]);
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseOWNERNAME,c[1]);
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseOWNERFULLNAME,d[1]);
	 			   String videoTitle=JsonPath.parse(responseFlag).read("$.videos[*].title").toString();
	 			  
	 			 
	 			  List<String> uploadedlist =JsonPath.parse(responseFlag).read("$.videos[*].uploadedBy");
	 			  searchVideoapiresponse.put("uploadernames", uploadedlist.toString());
                  logger.info("Uploader List API JSON  :::: " + uploadedlist);
                   List<String> durationlist =JsonPath.parse(responseFlag).read("$.videos[*].duration");
                  ArrayList<String> list = new ArrayList<String>();
                  Thread.sleep(5000);
                 for(int i=0;i<durationlist.size();i++)
                 {
                	 String x=durationlist.get(i);
                     String y=x.substring(3,8);
                     {
                    	 list.add(y);
                     }
                  }
                list.toString();
             	String actualList=list.toString().replace("[", "");
        		String actualList1=actualList.replace("]", "");
                searchVideoapiresponse.put("TIMES",actualList1.replace(" ",""));
                logger.info("TIME List API JSON  :::: " + actualList1.toString());
                List<String> uploadedDatelist =JsonPath.parse(responseFlag).read("$.videos[*].whenUploaded");
                ArrayList<String> datelist = new ArrayList<String>();
                for(int j=0;j<uploadedDatelist.size();j++)
                {
                	 String x=uploadedDatelist.get(j);
                     datelist.add(apiUtils.convertUTCtoIST(x));
                }
 
                searchVideoapiresponse.put("whenUploaded", datelist.toString());
                List<String> titlelist =JsonPath.parse(responseFlag).read("$.videos[*].title");
                searchVideoapiresponse.put("title", titlelist.toString());
                logger.info("Videos List API JSON  :::: " + titlelist.toString());
	 			 searchVideoapiresponse.put(IVideoAccessControlService.videoTitle,videoTitle.substring(2, videoTitle.length()-2));
				   searchVideoapiresponse.put(IVideoAccessControlService.videoCount,JsonPath.parse(responseFlag).read("$.totalVideos").toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
					} catch (Exception e) {
			e.printStackTrace();
		}
		return searchVideoapiresponse;

	}

	public HashMap<String, String> VideoSearchAPIwith_UserName_AsParameters(HashMap<String, String> apiresponse) {
		String responseFlag = null;
		try {
			
			
			client = Client.create();
			 MultivaluedMap queryParams = new MultivaluedMapImpl();
		     queryParams.add(IAPIConstantCodes.APIResponseOWNERS,apiresponse.get("ownerName")+","+apiresponse.get("ownerName2"));
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					.path(RevbaseAPIURLs.VIDEOSEARCH);
	
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		
			response = webResource
					.queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
	
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Delete Video API JSON  :::: " + responseFlag);

				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchVideoapiresponse;

	}
	
	public HashMap<String, String> VideoSearchAPIwith_UserId_AsParameters(HashMap<String, String> apiresponse) {
		String responseFlag = null;
		try {
			
			
			client = Client.create();
			 MultivaluedMap queryParams = new MultivaluedMapImpl();
		     queryParams.add(IAPIConstantCodes.APIResponseOWNERIDS,apiresponse.get("ownerId")+","+apiresponse.get("ownerId2"));
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					.path(RevbaseAPIURLs.VIDEOSEARCH);

			logger.info("WebResourec BaseURL :::: "+webResource);
	 		
			response = webResource
					.queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
	
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Delete Video API JSON  :::: " + responseFlag);

				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchVideoapiresponse;

	}
	public HashMap<String, String> VideoSearchAPIwith_InvalidUserName_AsParameters(HashMap<String, String> apiresponse) {
		String responseFlag = null;
		try {
			
			
			client = Client.create();
			 MultivaluedMap queryParams = new MultivaluedMapImpl();
		     queryParams.add(IAPIConstantCodes.APIResponseOWNERS,apiresponse.get("ownerName")+"21");
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					.path(RevbaseAPIURLs.VIDEOSEARCH);

			logger.info("WebResourec BaseURL :::: "+webResource);
	 		
			response = webResource
					.queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
			   logger.info("Delete Video API JSON  :::: " + responseFlag);
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);

				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchVideoapiresponse;

	}
	
	public HashMap<String, String> VideoSearchAPIwith_InvalidUserId_AsParameters(HashMap<String, String> apiresponse) {
		String responseFlag = null;
		try {
			
			client = Client.create();
			 MultivaluedMap queryParams = new MultivaluedMapImpl();
		     queryParams.add(IAPIConstantCodes.APIResponseOWNERIDS,apiresponse.get("ownerId")+"231");
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					.path(RevbaseAPIURLs.VIDEOSEARCH);
	
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		
			response = webResource
					.queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .get(ClientResponse.class);
	
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Delete Video API JSON  :::: " + responseFlag);
				   String a[]=responseFlag.replaceAll("[\"']*","").split(",");
				   String b[]=a[0].split(":");
				   
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseVIDEOS,b[1]);
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   searchVideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchVideoapiresponse;

	}
	/*public JSONObject searchVideo_OwnerUpdated(HashMap<String, String> apiresponse ) throws InterruptedException {
		String responseFlag = null;
			client = Client.create();
		  MultivaluedMap queryParams = new MultivaluedMapImpl();
			 if(apiresponse.get(IAPIConstantCodes.Q)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.Q,apiresponse.get(IAPIConstantCodes.Q));
			 }
			 if(apiresponse.get(IAPIConstantCodes.TYPE)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.TYPE, apiresponse.get(IAPIConstantCodes.TYPE));
			 }
			 if(apiresponse.get(IAPIConstantCodes.SEARCHCATEGORIES)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.SEARCHCATEGORIES, apiresponse.get(IAPIConstantCodes.SEARCHCATEGORIES));
			 }
			 if(apiresponse.get(IAPIConstantCodes.STATUS)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.STATUS, apiresponse.get(IAPIConstantCodes.STATUS));
			 }
			 if(apiresponse.get(IAPIConstantCodes.COUNT)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.COUNT, apiresponse.get(IAPIConstantCodes.COUNT));
			 }
			 if(apiresponse.get(IAPIConstantCodes.UploaderIDs)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.UploaderIDs, apiresponse.get(IAPIConstantCodes.UploaderIDs));
			  
			 }
			 if(apiresponse.get(IAPIConstantCodes.Uploaders)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.Uploaders, apiresponse.get(IAPIConstantCodes.Uploaders));
			 }
			 if(apiresponse.get(IAPIConstantCodes.sortField)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.sortField, apiresponse.get(IAPIConstantCodes.sortField));
			 }
			 if(apiresponse.get(IAPIConstantCodes.sortDirection)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.sortDirection, apiresponse.get(IAPIConstantCodes.sortDirection));
			 }
			 if(apiresponse.get(IAPIConstantCodes.ExactMatch)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.ExactMatch, apiresponse.get(IAPIConstantCodes.ExactMatch));
			 }
			 if(apiresponse.get(IAPIConstantCodes.SearchField)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.SearchField, apiresponse.get(IAPIConstantCodes.SearchField));
			 }
			 if(apiresponse.get(IAPIConstantCodes.Unlisted)!=null)
			 {
			  queryParams.add(IAPIConstantCodes.Unlisted, apiresponse.get(IAPIConstantCodes.Unlisted));
			 }
		webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					       .path(RevbaseAPIURLs.VIDEOSEARCH);
			
			logger.info("WebResourec BaseURL :::: "+webResource);
			logger.info("************************queryParams path.. :::: "+queryParams);
            Thread.sleep(5000);
 	 		response = webResource
	 				   .queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))				   
					   .get(ClientResponse.class);
 	 	
 	 	   responseFlag = response.getEntity(String.class);
 	 		Object obj=JSONValue.parse(responseFlag);
 	 		JSONObject jsonobject=(JSONObject)obj;
 	 	//JSONArray newobj=(JSONArray) jsonobject.get("video");
 	 	 
 		 	JSONArray jsonarray=(JSONArray) jsonobject.get("videos");
 		 	 
 		 	 JSONObject explobject = (JSONObject) jsonarray.get(0);
 		    JSONObject videoreportapiresponse=null;
 		         if(explobject.containsKey("owner"))
 	            {
 		        	JSONObject ownerobjects=(JSONObject) explobject.get("owner");
 	                System.out.println("conditon satisfied");
 	                videoreportapiresponse=ownerobjects;
 	                
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
 		        
 		        
 		        
 		        logger.info("#########Owner body  RESPONSE CONTAINS::::::::::::"+videoreportapiresponse);
 		        return videoreportapiresponse;
 		}

	*/
	}


	 
				
				
		





