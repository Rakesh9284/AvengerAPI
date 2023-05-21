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

public class VideoDetailsAPI {
	
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> getvideoapiresponse = new HashMap<String, String>();
	private static Logger logger = Logger.getLogger(VideoDetailsAPI.class);
	public HashMap<String, String> getVideoDetails(HashMap<String, String> apiresponse) {

		String responseFlag = null;

		try {

			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.DETAILSURL);
			logger.info("Video Detail API URL :::: " + webResource);
			response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header("Authorization", "VBrick " + apiresponse.get(IAPIConstantCodes.APIRequestAccessToken)).get(ClientResponse.class);


			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
                getvideoapiresponse.put(IAPIConstantCodes.APIRESPONSEJSON, responseFlag);
                getvideoapiresponse.put(IVideoAccessControlService.videoTitle,JsonPath.parse(responseFlag).read("$.title").toString());
                getvideoapiresponse.put(IVideoAccessControlService.videoid,JsonPath.parse(responseFlag).read("$.id").toString());
                getvideoapiresponse.put(IVideoAccessControlService.sourcetype,JsonPath.parse(responseFlag).read("$.sourceType").toString());
                getvideoapiresponse.put(IVideoAccessControlService.totalviews,JsonPath.parse(responseFlag).read("$.totalViews").toString());
                getvideoapiresponse.put(IAPIConstantCodes.canedit,JsonPath.parse(responseFlag).read("$.canEdit").toString());
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				getvideoapiresponse.put(IAPIConstantCodes.INVALIDUSERNAMEPASSWORD, response.getEntity(String.class));
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getvideoapiresponse;

	}
	
	public HashMap<String, String> getVideoDetails1(HashMap<String, String> apiresponse) {

		String responseFlag = null;

		try {

			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					     .path(RevbaseAPIURLs.GETVIDEOURL+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.DETAILSURL);
			response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type).get(ClientResponse.class);
					   //removed the authorization token
					  // .header("Authorization", "VBrick " + apiresponse.get(IAPIConstantCodes.APIRequestAccessToken)).get(ClientResponse.class);
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
                getvideoapiresponse.put(IAPIConstantCodes.APIRESPONSEJSON, responseFlag);
                getvideoapiresponse.put(IVideoAccessControlService.videoTitle,JsonPath.parse(responseFlag).read("$.title").toString());
                getvideoapiresponse.put(IVideoAccessControlService.videoid,JsonPath.parse(responseFlag).read("$.id").toString());
                getvideoapiresponse.put(IVideoAccessControlService.sourcetype,JsonPath.parse(responseFlag).read("$.sourceType").toString());
                getvideoapiresponse.put(IVideoAccessControlService.totalviews,JsonPath.parse(responseFlag).read("$.totalViews").toString());
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				getvideoapiresponse.put(IAPIConstantCodes.INVALIDUSERNAMEPASSWORD, response.getEntity(String.class));
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getvideoapiresponse;

	}

	 

	 

}
