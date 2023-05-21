package com.vbricks.avenger.serviceimpl;

import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.http.client.params.HttpClientParams;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.gargoylesoftware.htmlunit.javascript.host.fetch.Request;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;
//import javax.ws.rs.core.MultivaluedMapImpl;
/** 
 * @author neslavath
 * @functionName : deleteVideos
 * @Description : DeleteVideos API - will delete mention video ID of the Video into the application 
 * @param loginapiresponse (baseUrl,accessToken,VideoID
 * @return deleteVideoapi response(baseUrl,accessToken, apiresponse200OK)
 */
public class DeleteSupplementalFilesAPI {
	
	private static Logger logger = Logger.getLogger(DeleteSupplementalFilesAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> deleteVideosupplementalfileapiresponse = new HashMap<String, String>();
	 
	public HashMap<String, String> deleteVideosupplementalfile(HashMap<String, String> apiresponse ) {
		String responseFlag = null;
		try {
			client = Client.create();
			logger.info(client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				     .path(RevbaseAPIURLs.DELETESUPPLEMENTALFILES+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID)+RevbaseAPIURLs.SUPPLEMENTAL_FILES));
			
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.DELETESUPPLEMENTALFILES+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID)+RevbaseAPIURLs.SUPPLEMENTAL_FILES);
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .delete(ClientResponse.class);
		
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
			    
				   
				   logger.info("Delete Video API JSON  :::: " + responseFlag);
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteVideosupplementalfileapiresponse;

	}
	
	public HashMap<String, String> deleteVideosupplementalfilewhenfilenotuploaded(HashMap<String, String> apiresponse ) {
		String responseFlag = null;
		try {
			client = Client.create();
			logger.info(client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				     .path(RevbaseAPIURLs.DELETESUPPLEMENTALFILES+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID)+RevbaseAPIURLs.SUPPLEMENTAL_FILES));
			
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.DELETESUPPLEMENTALFILES+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID)+RevbaseAPIURLs.SUPPLEMENTAL_FILES);
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .delete(ClientResponse.class);
		
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
			    
			    String a[]=responseFlag.replaceAll("[\"']*","").split(",");
				   String b[]=a[0].split(":");
				   System.out.println(b[1]);
				   
				   logger.info("Delete Video API JSON  :::: " + responseFlag);
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.supplementalfilecode,b[1]);
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteVideosupplementalfileapiresponse;

	}
	
	private static Logger logger1 = Logger.getLogger(DeleteSupplementalFilesAPI.class);
	public Client client1;
	public ClientResponse response1;
	WebResource webResource1;
	HashMap<String, String> deleteVideosupplementalfilewithID = new HashMap<String, String>();
	 
	public HashMap<String, String> deleteVideosupplementalfilewithID(HashMap<String, String> apiresponse,String fileid) {
		String responseFlag = null;
		try {

			client = Client.create();
			 MultivaluedMap queryParams = new MultivaluedMapImpl();
		     queryParams.add("fileIds",apiresponse.get("fileIds"));
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					.path(RevbaseAPIURLs.DELETESUPPLEMENTALFILES+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID)+RevbaseAPIURLs.SUPPLEMENTAL_FILES);
				
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		
			response = webResource
					.queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .delete(ClientResponse.class,fileid.toString());
	
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Delete Video API JSON  :::: " + responseFlag);

				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteVideosupplementalfilewithID;

	}
	
	public JSONObject fileid(String fileid ) {
		JSONObject fileJson1=new JSONObject();
		fileJson1.put("fileIds",  new String(fileid));
		return fileJson1;
}
	
	public HashMap<String, String> deletemultipleVideocommentswithID(HashMap<String, String> apiresponse,String fileids) {
		String responseFlag = null;
		try {
			client = Client.create();
			 MultivaluedMap queryParams = new MultivaluedMapImpl();
		     queryParams.add(apiresponse.get("fileId1"),apiresponse.get("fileId2"));
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					.path(RevbaseAPIURLs.DELETESUPPLEMENTALFILES+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID)+RevbaseAPIURLs.SUPPLEMENTAL_FILES);
			
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		
			response = webResource
					.queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .delete(ClientResponse.class,fileids.toString());
	
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Delete Video API JSON  :::: " + responseFlag);

				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteVideosupplementalfilewithID;

	}
	public JSONObject fileids(String fileid1) {
		JSONObject fileJson1=new JSONObject();
		fileJson1.put("fileIds",  new String(fileid1));
		return fileJson1;
}
	public HashMap<String, String> deleteVideosupplementalfilewithInvalidFileID(HashMap<String, String> apiresponse) {
		String responseFlag = null;
		try {
			

			client = Client.create();
			 MultivaluedMap queryParams = new MultivaluedMapImpl();
		     queryParams.add("fileIds",apiresponse.get("InvalidfileId"));
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					.path(RevbaseAPIURLs.DELETESUPPLEMENTALFILES+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID)+RevbaseAPIURLs.SUPPLEMENTAL_FILES);

			logger.info("WebResourec BaseURL :::: "+webResource);
	 		
			response = webResource
					.queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .delete(ClientResponse.class);
	
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Delete Video API JSON  :::: " + responseFlag);

				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideosupplementalfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteVideosupplementalfilewithID;

	}
}

