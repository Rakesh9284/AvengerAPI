package com.vbricks.avenger.serviceimpl;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;
import java.util.HashMap;
import org.apache.log4j.Logger;

public class GetVideoTranscriptionFile {
	
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> getvideotranscriptionfilesapiresponse = new HashMap<String, String>();
	private static Logger logger = Logger.getLogger(GetVideoTranscriptionFile.class);
	public HashMap<String, String> getVideoTranscriptionFiles(HashMap<String, String> apiresponse) {

		String responseFlag = null;

		try {

			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.TRANSCRIPTIONFILE);
			response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header("Authorization", "VBrick " + apiresponse.get(IAPIConstantCodes.APIRequestAccessToken)).get(ClientResponse.class);
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				Thread.sleep(5000);
				 String a[]=responseFlag.replaceAll("[\"']*","").split(",");
				 logger.info("LoginApi Response JSON  :::: " + responseFlag);
				   String a1[]=a[0].split(":");
				   System.out.println(a1[2]);
				   
				   String b[]=a[1].split(":");
				   System.out.println(b[1]);
		   
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
				getvideotranscriptionfilesapiresponse.put("filename", b[1]);
				getvideotranscriptionfilesapiresponse.put("transcriptionfileid", a1[2]);
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.INVALIDUSERNAMEPASSWORD, response.getEntity(String.class));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getvideotranscriptionfilesapiresponse;

	}
	
	public HashMap<String, String> getVideomultipleTranscriptionFiles(HashMap<String, String> apiresponse) {

		String responseFlag = null;

		try {

			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.TRANSCRIPTIONFILE);
			response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header("Authorization", "VBrick " + apiresponse.get(IAPIConstantCodes.APIRequestAccessToken)).get(ClientResponse.class);
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				 String a[]=responseFlag.replaceAll("[\"']*","").split(",");
				 
				   String a1[]=a[0].split(":");
				   System.out.println(a1[2]);
				   
				   String b[]=a[4].split(":");
				   System.out.println(b[1]);
				   
				   String c[]=a[8].split(":");
				   System.out.println(c[1]);
		   
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
				getvideotranscriptionfilesapiresponse.put("filename", b[1]);

				getvideotranscriptionfilesapiresponse.put("transcriptionfileid1", a1[2]);
				getvideotranscriptionfilesapiresponse.put("transcriptionfileid2", b[1]);
				getvideotranscriptionfilesapiresponse.put("transcriptionfileid3", c[1]);
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.INVALIDUSERNAMEPASSWORD, response.getEntity(String.class));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getvideotranscriptionfilesapiresponse;

	}
	
	public HashMap<String, String> getVideotranscriptionfilewhenfilenotuploaded(HashMap<String, String> apiresponse ) {
		String responseFlag = null;

		try {

			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.TRANSCRIPTIONFILE);
			response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header("Authorization", "VBrick " + apiresponse.get(IAPIConstantCodes.APIRequestAccessToken)).get(ClientResponse.class);
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				 String a[]=responseFlag.replaceAll("[\"']*","").split(",");
				   String b[]=a[0].split(":");
				   System.out.println(b[1]);
				   
				   logger.info("Delete Video API JSON  :::: " + responseFlag);
				   getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
				   getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.supplementalfilecode,b[1]);
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				   getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				   getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				   getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				   getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				   getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				   getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			   } else {
			    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			   }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getvideotranscriptionfilesapiresponse;

	}
	
	public HashMap<String, String> getVideotripleTranscriptionFiles(HashMap<String, String> apiresponse) {

		String responseFlag = null;

		try {

			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.TRANSCRIPTIONFILE);
			response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header("Authorization", "VBrick " + apiresponse.get(IAPIConstantCodes.APIRequestAccessToken)).get(ClientResponse.class);
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				 String a[]=responseFlag.replaceAll("[\"']*","").split(",");
				 
				   String a1[]=a[0].split(":");
				   System.out.println(a1[2]);
				   
				   String a2[]=a[1].split(":");
				   System.out.println(a2[1]);
				   
				   String a3[]=a[2].split(":");
				   System.out.println(a3[1]);
				   
				   String b1[]=a[4].split(":");
				   System.out.println(b1[1]);
				   
				   String b2[]=a[5].split(":");
				   System.out.println(b2[1]);
				   
				   String b3[]=a[6].split(":");
				   System.out.println(b3[1]);
				   
				   String c1[]=a[8].split(":");
				   System.out.println(c1[1]);
				   
				   String c2[]=a[9].split(":");
				   System.out.println(c2[1]);
				   
				   String c3[]=a[10].split(":");
				   System.out.println(c3[1]);
		   
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
				
				getvideotranscriptionfilesapiresponse.put("Filename:1", a1[2]);
				getvideotranscriptionfilesapiresponse.put("FileType1:", a2[1]);
				getvideotranscriptionfilesapiresponse.put("FileSize1:", a3[1]);
				
				getvideotranscriptionfilesapiresponse.put("Filename:2", b1[1]);
				getvideotranscriptionfilesapiresponse.put("FileType2:", b2[1]);
				getvideotranscriptionfilesapiresponse.put("FileSize2:", b3[1]);
				
				getvideotranscriptionfilesapiresponse.put("Filename:3", c1[1]);
				getvideotranscriptionfilesapiresponse.put("FileType3:", c2[1]);
				getvideotranscriptionfilesapiresponse.put("FileSize3:", c3[1]);

			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.INVALIDUSERNAMEPASSWORD, response.getEntity(String.class));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideotranscriptionfilesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getvideotranscriptionfilesapiresponse;

	}
	
}