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

public class GetVideoThumbnailAPI {
	
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> getvideothumbnailapiresponse = new HashMap<String, String>();
	private static Logger logger = Logger.getLogger(GetVideoThumbnailAPI.class);
	public HashMap<String, String> getVideoThumbnail(HashMap<String, String> apiresponse) {

		String responseFlag = null;

		try {

			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.THUMBNAIL);
			logger.info("API URL @@@@@@@@@@" +client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.THUMBNAIL));;
			response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header("Authorization", "VBrick " + apiresponse.get(IAPIConstantCodes.APIRequestAccessToken)).get(ClientResponse.class);
			Thread.sleep(10000);
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIRESPONSEJSON, responseFlag);
				//getvideothumbnailapiresponse.put(IVideoAccessControlService.videoTitle,JsonPath.parse(responseFlag).read("$.title").toString());
//				getvideothumbnailapiresponse.put(IVideoAccessControlService.videoid,JsonPath.parse(responseFlag).read("$.videoId").toString());
//				getvideothumbnailapiresponse.put(IVideoAccessControlService.videoIsActive,JsonPath.parse(responseFlag).read("$.isActive").toString());
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				getvideothumbnailapiresponse.put(IAPIConstantCodes.INVALIDUSERNAMEPASSWORD, response.getEntity(String.class));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getvideothumbnailapiresponse;

	}

	public HashMap<String, String> getVideoThumbnailwithunauthorizeduser(HashMap<String, String> apiresponse) {

		String responseFlag = null;

		try {

			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.THUMBNAIL);
			logger.info("API URL @@@@@@@@@@" +client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.THUMBNAIL));;
			response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type).get(ClientResponse.class);
					   //removed the authorization token
					   //.header("Authorization", "VBrick " + apiresponse.get(IAPIConstantCodes.APIRequestAccessToken)).get(ClientResponse.class);
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIRESPONSEJSON, responseFlag);
				getvideothumbnailapiresponse.put(IVideoAccessControlService.videoTitle,JsonPath.parse(responseFlag).read("$.title").toString());
				getvideothumbnailapiresponse.put(IVideoAccessControlService.videoid,JsonPath.parse(responseFlag).read("$.videoId").toString());
				getvideothumbnailapiresponse.put(IVideoAccessControlService.videoIsActive,JsonPath.parse(responseFlag).read("$.isActive").toString());
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				getvideothumbnailapiresponse.put(IAPIConstantCodes.INVALIDUSERNAMEPASSWORD, response.getEntity(String.class));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getvideothumbnailapiresponse;

	}

	public HashMap<String, String> getVideoThumbnailwithoutAuthorization(HashMap<String, String> apiresponse) {

		String responseFlag = null;

		try {

			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.THUMBNAIL);
			logger.info("API URL @@@@@@@@@@" +client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.THUMBNAIL));;
			response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   //.header("Authorization", "VBrick " + apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))--> Removing Authorization
					   .get(ClientResponse.class);
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIRESPONSEJSON, responseFlag);
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				getvideothumbnailapiresponse.put(IAPIConstantCodes.INVALIDUSERNAMEPASSWORD, response.getEntity(String.class));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideothumbnailapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getvideothumbnailapiresponse;

	}

}
