package com.vbricks.avenger.serviceimpl;
import java.util.HashMap;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

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
public class DeleteTranscriptionFileAPI {
	
	private static Logger logger = Logger.getLogger(DeleteTranscriptionFileAPI.class);
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> deleteVideotranscriptionfileapiresponse = new HashMap<String, String>();
	 
	public HashMap<String, String> deleteVideotranscriptionfile(HashMap<String, String> apiresponse ) {
		String responseFlag = null;
		try {
			client = Client.create();
			logger.info(client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
				     .path(RevbaseAPIURLs.DELETETRANSCRIPTIONFILES+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID)+RevbaseAPIURLs.TRANSCRIPTIONFILE));
			
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.DELETETRANSCRIPTIONFILES+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID)+RevbaseAPIURLs.TRANSCRIPTIONFILE);
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .delete(ClientResponse.class);
		
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
			    
				   Thread.sleep(6000);
				   logger.info("Delete Video API JSON  :::: " + responseFlag);
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteVideotranscriptionfileapiresponse;

	}
	
	public HashMap<String, String> deleteVideotranscriptionfilewhenfilenotuploaded(HashMap<String, String> apiresponse ) {
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
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.supplementalfilecode,b[1]);
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfileapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteVideotranscriptionfileapiresponse;

	}
	
	private static Logger logger1 = Logger.getLogger(DeleteSupplementalFilesAPI.class);
	public Client client1;
	public ClientResponse response1;
	WebResource webResource1;
	HashMap<String, String> deleteVideotranscriptionfilewithID = new HashMap<String, String>();
	 
	public HashMap<String, String> deleteVideotranscriptionfilewithID(HashMap<String, String> apiresponse) {
		String responseFlag = null;
		try {
        	client = Client.create();
			 MultivaluedMap queryParams = new MultivaluedMapImpl();
		     queryParams.add(IAPIConstantCodes.APIResponseFILEIDs,apiresponse.get("fileid"));
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					.path(RevbaseAPIURLs.DELETETRANSCRIPTIONFILES+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID)+RevbaseAPIURLs.TRANSCRIPTIONFILE);
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		
			response = webResource
					.queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .delete(ClientResponse.class);
	
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Delete Video Transcription File API JSON  :::: " + responseFlag);

				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteVideotranscriptionfilewithID;
}
	
	public HashMap<String, String> deleteVideotranscriptionfileswithmultipleIDs(HashMap<String, String> apiresponse) {
		String responseFlag = null;
		try {
        	client = Client.create();
			 MultivaluedMap queryParams = new MultivaluedMapImpl();
		     queryParams.add(IAPIConstantCodes.APIResponseFILEIDs,apiresponse.get("fileid1")+","+apiresponse.get("fileid2"));
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					.path(RevbaseAPIURLs.DELETETRANSCRIPTIONFILES+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID)+RevbaseAPIURLs.TRANSCRIPTIONFILE);
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		
			response = webResource
					.queryParams(queryParams)
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header(ApiResources.Authorization, ApiResources.VBrick+ apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .delete(ClientResponse.class);
	
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
			    responseFlag = response.getEntity(String.class);
				   logger.info("Delete Video Transcription File API JSON  :::: " + responseFlag);

				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   deleteVideotranscriptionfilewithID.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteVideotranscriptionfilewithID;

	}
}