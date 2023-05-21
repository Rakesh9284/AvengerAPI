package com.vbricks.avenger.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbrick.avenger.apibeans.PUTMigrateVideoBean;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IMegratVideoService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;

public class MigratVideoAPI {

	private static Logger logger = Logger.getLogger(MigratVideoAPI.class);

	public Client client;
	public ClientResponse response;
	WebResource webResource;

	HashMap<String, String> migratvideoapiresponse = new HashMap<String, String>();
 
	
	public HashMap<String, String> migratVideo(HashMap<String, String> apiresponse,PUTMigrateVideoBean migratevideobean) {

		String responseFlag = null;

		try {

			client = Client.create();
			webResource = client.resource(apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
								.path(RevbaseAPIURLs.GETVIDEOURL + apiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) + RevbaseAPIURLs.Migrat);
			logger.info("BaseUrl is   :::: " + webResource);
			response = webResource
								.accept(ApiResources.Content_Type)
								.type(ApiResources.Content_Type)
								.header("Authorization", "VBrick " + apiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
								.put(ClientResponse.class,migratVideoJSON(migratevideobean).toString());
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				migratvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				migratvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				migratvideoapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, (response.getStatusInfo().toString()));
				migratvideoapiresponse.put(IAPIConstantCodes.APIRESPONSEJSON, responseFlag);
		 
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				migratvideoapiresponse.put(IAPIConstantCodes.INVALIDUSERNAMEPASSWORD, response.getEntity(String.class));
				migratvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				migratvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				migratvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				migratvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				migratvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				migratvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				migratvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				migratvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				migratvideoapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, apiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
				migratvideoapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return migratvideoapiresponse;

	}


	public JSONObject migratVideoJSON(PUTMigrateVideoBean migratevideobean){
	   
	JSONObject migratJson=new JSONObject();
	//1.username
	if(migratevideobean.getUserName()!=null)
	migratJson.put(IAPIConstantCodes.UserName,migratevideobean.getUserName());
	 
	//2.Owner
	JSONObject ownerJson=new JSONObject();
	
	if(migratevideobean.getOwner()=="Yes")
	
	{
		//2.1 userId
	if(migratevideobean.getUserId()!=null)
		ownerJson.put(IAPIConstantCodes.APIResponseUSERID,migratevideobean.getUserId());

	//2.2 username
	if(migratevideobean.getownerUserName()!=null)
		ownerJson.put(IAPIConstantCodes.APIUSERNAME, migratevideobean.getownerUserName());

    //2.3 email
	if(migratevideobean.getEmail()!=null)
		ownerJson.put(IAPIConstantCodes.APIResponseEMAIL, migratevideobean.getEmail());
 		
    migratJson.put(IAPIConstantCodes.owner,ownerJson);
   }
   
	//3.uploadedby
	if(migratevideobean.getUploadedby()!=null)
		ownerJson.put(IAPIConstantCodes.uploadedBy, migratevideobean.getUploadedby());

	//4.whenuploaded
	if(migratevideobean.getWhenUploaded()!=null)
	migratJson.put(IAPIConstantCodes.WHENUPLOADED,migratevideobean.getWhenUploaded());	
	 
	//5.publishdate
	if(migratevideobean.getPublishDate()!=null)
		migratJson.put(IAPIConstantCodes.publishDate, migratevideobean.getPublishDate());
 
	if(migratevideobean.getLegacyViewCount()!=null)
		migratJson.put(IAPIConstantCodes.legacyViewCount, migratevideobean.getLegacyViewCount());
	 
	System.out.println(migratJson);
	return migratJson;
	}
}
