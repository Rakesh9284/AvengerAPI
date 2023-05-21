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
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.UploaderClient;
import com.vbricks.avenger.utils.VideoUploaderClient;

public class UserProfileImageAPI {
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
	public HashMap<String, String> uploaduserprofileimage(HashMap<String, String> loginapiresponse, String path,
			String inputJson) {

		String projectDir = System.getProperty("user.dir");
		String requestURL = loginapiresponse.get("baseurl") + RevbaseAPIURLs.ProfileIMAGEFILES+loginapiresponse.get("userId");
		logger.info("loginapiresponse API URL :::: " + loginapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		logger.info("UPLOADVIDEOURL   URL :::: " + RevbaseAPIURLs.ProfileIMAGEFILES);
		logger.info("VideoUpload API URL :::: " + requestURL);
		String responseFlag = null;

		
		try {
			logger.info("File Location ###### :::: " + projectDir + path);
			File uploadFile = new File(projectDir + path);
			UploaderClient multipart = new UploaderClient(requestURL, charset,loginapiresponse.get("accesstoken"));
			multipart.addFilePart("ImageFile", uploadFile,inputJson);
			List<String> response = multipart.finish();
			uploadvidoeapiresponse = new HashMap<String, String>();
			logger.info("UploadVideo API HTTP status Code ::: "+response.get(0));
			
			if (response.get(0).equals("HTTP StatusCode :200")) {
				logger.info("Video Upload Response ..@@@@@@::::");
				for (String line : response) {
					logger.info("Video uploaed API response :::" + line);

				}
				uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, loginapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				uploadvidoeapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
				uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, response.get(0));
			}
			  else {
				
				uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, response.get(0));
				uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseJsonErrorCode, VideoUploaderClient.parseJsonObject1(response.get(1)));
			}
			

		} catch (IOException ex) {
			System.out.println(ex);
			logger.info("while uploading video file Exception is :::: "+ex);

		} catch (JSONException e) {

			e.printStackTrace();
		}
		return uploadvidoeapiresponse;

	}
}
