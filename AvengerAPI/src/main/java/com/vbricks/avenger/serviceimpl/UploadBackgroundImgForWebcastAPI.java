package com.vbricks.avenger.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.HttpStatusCode;
import com.vbricks.avenger.utils.UploaderClient;
import com.vbricks.avenger.utils.VideoUploaderClient;

public class UploadBackgroundImgForWebcastAPI {
	private ClientResponse response;
	private Client client;
	private WebResource webResource;
 
	private static Logger logger = Logger.getLogger(CreateEventsAPI.class);
	String charset = "UTF-8";
	HashMap<String, String> uploadbackgroundimageapiresponse;

	/**
	 * - AV-18674	/api/uploads/background-image/{id} - This method will Upload background image file to an existing webcast
    */
	public HashMap<String, String> uploadBackgroundImageForWebcast(HashMap<String, String> loginapiresponse, String path, String inputJson) {

		String projectDir = System.getProperty("user.dir");
		String requestURL = loginapiresponse.get("baseurl") + RevbaseAPIURLs.WEBCASTBACKGROUNDIMG+loginapiresponse.get("eventId");
		logger.info("loginapiresponse API URL :::: " + loginapiresponse.get("baseurl"));
		logger.info("Upload Background Image to the  wwebcast API URL :::: " + requestURL);
		try {
			logger.info("File Location ###### :::: " + projectDir + path);
			File uploadFile = new File(projectDir + path);
			UploaderClient multipart = new UploaderClient(requestURL, charset,loginapiresponse.get("accesstoken"));
			
			multipart.addFilePart("BImgFile", uploadFile,inputJson);
			List<String> response = multipart.finish();
			uploadbackgroundimageapiresponse = new HashMap<String, String>();
			logger.info("Upload Background Image API's HTTP status Code ::: "+response.get(0));
			
			if (response.get(0).equals("HTTP StatusCode :200")) {

				
					logger.info("Background Image API HTTP status Code ::: "+response.get(0));
					
					logger.info("Background Image API Upload Response ..@@@@@@::::");
					for (String line : response)  
						logger.info("Background Image Upload API response :::" + line);
						 
					uploadbackgroundimageapiresponse.put("baseurl", loginapiresponse.get("baseurl"));
					uploadbackgroundimageapiresponse.put("accesstoken", loginapiresponse.get("accesstoken"));
					uploadbackgroundimageapiresponse.put("httpCode", response.get(0));

					}
			
	
		  else {

					uploadbackgroundimageapiresponse.put("httpCode", response.get(0));
				 }
			       
	  
		}
		 catch (Exception ex) {
			System.out.println(ex);
			logger.info("while uploading background Image Exception is :::: "+ex);

		 }  
		return uploadbackgroundimageapiresponse;

	}

	
	 
}
