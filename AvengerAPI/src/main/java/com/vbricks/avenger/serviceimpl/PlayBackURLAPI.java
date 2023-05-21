package com.vbricks.avenger.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;

public class PlayBackURLAPI {

	private static Logger logger = Logger.getLogger(PlayBackURLAPI.class);

	public Client client;
	public ClientResponse response;
	WebResource webResource;

	HashMap<String, String> getvideoplaybackurlapiresponse = new HashMap<String, String>();

	public HashMap<String, String> getVideoPlayback(HashMap<String, String> apiresponse) {

		String responseFlag = null;

		
		try {

			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.GETVIDEOURL + apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.PLAYBACKURL);
			logger.info("BaseUrl is   :::: " + webResource);
			response = webResource
								.accept(ApiResources.Content_Type)
								.type(ApiResources.Content_Type)
								.header("Authorization", "VBrick " + apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.get(ClientResponse.class);
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				getvideoplaybackurlapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				getvideoplaybackurlapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideoplaybackurlapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
                getvideoplaybackurlapiresponse.put(IAPIConstantCodes.APIRESPONSEJSON, responseFlag);
		 
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				getvideoplaybackurlapiresponse.put(IAPIConstantCodes.INVALIDUSERNAMEPASSWORD, response.getEntity(String.class));
				getvideoplaybackurlapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideoplaybackurlapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				getvideoplaybackurlapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideoplaybackurlapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				getvideoplaybackurlapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideoplaybackurlapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				getvideoplaybackurlapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideoplaybackurlapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				getvideoplaybackurlapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				getvideoplaybackurlapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getvideoplaybackurlapiresponse;

	}

}
