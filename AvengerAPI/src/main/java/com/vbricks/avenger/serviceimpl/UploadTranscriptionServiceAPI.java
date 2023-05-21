package com.vbricks.avenger.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.UploaderClient;
import com.vbricks.avenger.utils.VideoUploaderClient;

public class UploadTranscriptionServiceAPI {
	private ClientResponse response;
	private Client client;
	private WebResource webResource;
 
	public HashMap<String, String> loginUser;
	public HashMap<String, String> editvideoapiresponse;
	public HashMap<String, String> getrolesapiresponse;

	private static Logger logger = Logger.getLogger(UploadVideoService.class);
	String charset = "UTF-8";
	HashMap<String, String> uploadvidoeapiresponse;
	/**
	 * - AV-10646	/api/uploads/transcription-files/{videoId} - This method will Upload transcription-files File
	 * 
	 * @param loginapiresponseMap
	 *            
	 * @param AddUploadVideoBean  
	 *            
	 * @return HashMap -which consist of API Response and HTTPStatusCode and BaseURL
	 *         
	 */
	public HashMap<String, String> uploadTranscriptionFile(HashMap<String, String> loginapiresponse, String path,HashMap<String,String> inputJson) {

		String projectDir = System.getProperty("user.dir");
		String requestURL = loginapiresponse.get("baseurl") + RevbaseAPIURLs.TRANSCRIPTIONFILES+loginapiresponse.get("videoId");
		logger.info("loginapiresponse API URL :::: " + loginapiresponse.get("baseurl"));
		logger.info("UPLOADVIDEOURL   URL :::: " + RevbaseAPIURLs.TRANSCRIPTIONFILES);
		logger.info("VideoUpload API URL :::: " + requestURL);
		try {
			logger.info("File Location ###### :::: " + projectDir + path);
			File uploadFile = new File(projectDir + path);
			UploaderClient multipart = new UploaderClient(requestURL, charset,loginapiresponse.get("accesstoken"));
			multipart.addFormField("TranscriptionFiles", inputJson.get("inputJson"));
			
			multipart.addFilePart("SFile", uploadFile,inputJson.get("fileType"));
			List<String> response = multipart.finish();
			uploadvidoeapiresponse = new HashMap<String, String>();
			logger.info("UploadVideo API HTTP status Code ::: "+response.get(0));
			
			if (response.get(0).equals("HTTP StatusCode :200")) {

				if (inputJson.get("fileType").equalsIgnoreCase("video")) {
					logger.info("UploadVideo API HTTP status Code ::: "+response.get(0));
					
					logger.info("Video Upload Response ..@@@@@@::::");
					for (String line : response)  
						logger.info("Video uploaed API response :::" + line);
						 
					uploadvidoeapiresponse.put("baseurl", loginapiresponse.get("baseurl"));
					uploadvidoeapiresponse.put("accesstoken", loginapiresponse.get("accesstoken"));
					uploadvidoeapiresponse.put("httpCode", response.get(0));
					uploadvidoeapiresponse.put("videoId", VideoUploaderClient.parseJsonObject(response.get(1)));
					}
				else if(inputJson.get("fileType").equalsIgnoreCase("transcription")){
					logger.info("UploadVideo API HTTP status Code ::: "+response.get(0));
				 	logger.info("Video Upload Response ..@@@@@@::::");
						for (String line : response)  
							logger.info("Video uploaed API response :::" + line);

				 	uploadvidoeapiresponse.put("baseurl", loginapiresponse.get("baseurl"));
					uploadvidoeapiresponse.put("accesstoken", loginapiresponse.get("accesstoken"));
					uploadvidoeapiresponse.put("httpCode", response.get(0));

				}else if(inputJson.get("fileType").equalsIgnoreCase("supplemental")){
					logger.info("UploadVideo API HTTP status Code ::: "+response.get(0));
			 
					 
					logger.info("Video Upload Response ..@@@@@@::::");
					for (String line : response) 
						logger.info("Video uploaed API response :::" + line);
					
					uploadvidoeapiresponse.put("baseurl", loginapiresponse.get("baseurl"));
					uploadvidoeapiresponse.put("accesstoken", loginapiresponse.get("accesstoken"));
					uploadvidoeapiresponse.put("httpCode", response.get(0));
				 
				}
				
				else {

					uploadvidoeapiresponse.put("httpCode", response.get(0));
					
				 }
			       
			}
			else{
				uploadvidoeapiresponse.put("httpCode", response.get(0));

			}
		}
		 catch (Exception ex) {
			System.out.println(ex);
			logger.info("while uploading video file Exception is :::: "+ex);

		 }  
		return uploadvidoeapiresponse;

	}

	
	
	public HashMap<String,String>createJsonforTranscriptionFiles( String filename,String language){

		String jsoninput="{\"files\" : [{\"fileName\" : \""+filename+"\",\"language\" : \""+language+"\"} ]}";
		HashMap<String ,String> json=new HashMap<String,String>();
		json.put("inputJson", jsoninput);
	 	json.put("fileType", "transcription");
		return json;
		
	}
	
	 
}
