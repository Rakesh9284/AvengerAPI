package com.vbricks.avenger.serviceimpl;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.setup.Setup;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateEventService;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;
import java.util.HashMap;
import org.apache.log4j.Logger;

public class ReplaceVideoAPI {
	
	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, String> replacevideoresponse = new HashMap<String, String>();
	private static Logger logger = Logger.getLogger(ReplaceVideoAPI.class);
	
	public  HashMap<String, String> getvideoreplace(HashMap<String, String> apiresponse) {
		
		String responseFlag1 = null;
		try {
			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
					.path(RevbaseAPIURLs.ReplaceVideo+apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
			
			logger.info("WebResourec BaseURL :::: "+webResource);
	 		response = webResource
					   .accept(ApiResources.Content_Type)
					   .type(ApiResources.Content_Type)
					   .header("Authorization", "VBrick " + apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
					   .put(ClientResponse.class,Setup.NOTIFICATION2PATH);

			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag1 = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag1);
				replacevideoresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				replacevideoresponse.put(IUserAccountsService.VIDEOID,  JsonPath.parse(responseFlag1).read("$.videoId").toString());
				replacevideoresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				replacevideoresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
				replacevideoresponse.put(IAPIConstantCodes.APIRESPONSEJSON, responseFlag1);
				replacevideoresponse.put(ICreateEventService.Title,JsonPath.parse(responseFlag1).read("$.title").toString());
				replacevideoresponse.put(ICreateEventService.StartDate,JsonPath.parse(responseFlag1).read("$.startDate").toString());
				replacevideoresponse.put(ICreateEventService.EndDate,JsonPath.parse(responseFlag1).read("$.endDate").toString());
				replacevideoresponse.put(ICreateEventService.Status,JsonPath.parse(responseFlag1).read("$.status").toString());
				replacevideoresponse.put(ICreateEventService.slideUrl,JsonPath.parse(responseFlag1).read("$.slideUrl").toString());
				replacevideoresponse.put(ICreateEventService.isPreProduction,JsonPath.parse(responseFlag1).read("$.isPreProduction").toString());
				
				
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				replacevideoresponse.put(IAPIConstantCodes.INVALIDUSERNAMEPASSWORD, response.getEntity(String.class));
				replacevideoresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				replacevideoresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				replacevideoresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				replacevideoresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				replacevideoresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				replacevideoresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				replacevideoresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				replacevideoresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				replacevideoresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				replacevideoresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return replacevideoresponse;

	}	
}


 		


	
