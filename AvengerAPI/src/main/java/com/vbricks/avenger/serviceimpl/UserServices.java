package com.vbricks.avenger.serviceimpl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUserService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;
import com.vbricks.avenger.utils.HttpStatusCode;

public class UserServices implements IUserService {
	private ClientResponse response;
	private Client client;
	private WebResource webResource;
	private static Logger logger = Logger.getLogger(UserServices.class);
	public HashMap<String, String> loginUser;
	public HashMap<String, String> loginapiresponse;
	public HashMap<String, String> getrolesapiresponse;
 
	/**
	 * @MethodName :doLogin
	 * @description :This methods will do login into Application through Api  
	 * @param API baseurl
	 * @param userlogindetail - username & password
	 * @author neslavath 
	 * 
	 * -Login User (POST) - /api/v1/user/login
	 */
	public JSONObject doLoginUpdate(String loginuser, String baseurl) {
	    
	       String responseFlag = null;
	            client = Client.create();
	            
	            //https://autoapi.rev-qa.vbrick.com/+api/v2/user/login
	            webResource = client.resource(baseurl).path(RevbaseAPIURLs.LOGINUSERURL);
	             logger.info("API Request as URL    :::: "+ webResource);
	        
	            response = webResource
	                    .accept(ApiResources.Content_Type)
	                    .type(ApiResources.Content_Type)
	                    .post(ClientResponse.class, loginuser);



	           logger.info("LoginAPI Response HTTP Code :::: " + response.getStatus());
	            loginapiresponse = new HashMap<String, String>();
	                responseFlag = response.getEntity(String.class);
	                
	                Object obj=JSONValue.parse(responseFlag); 
	                
	                JSONObject jsonObject = (JSONObject) obj;  
	                jsonObject.put("Statuscode", response.getStatus());
	             
	                String token = (String) jsonObject.get("token");  
	                String email = (String) jsonObject.get("email");
	                
	                jsonObject.put(IAPIConstantCodes.APIRequestAccessToken, jsonObject.get("token").toString());
	                jsonObject.put(IAPIConstantCodes.APIResponseBaseUrl, baseurl);
	                
	        return     jsonObject;    
	        }
	
	public HashMap<String, String> doLogin(String loginuser, String baseurl) {

		String responseFlag = null;
		try {
			client = Client.create();
			webResource = client.resource(baseurl).path(RevbaseAPIURLs.LOGINUSERURL);
             logger.info("API Request as URL    :::: "+ webResource);
        
			response = webResource
					.accept(ApiResources.Content_Type)
					.type(ApiResources.Content_Type)
					.post(ClientResponse.class, loginuser);

			logger.info("LoginAPI Response HTTP Code :::: " + response.getStatus() );
			loginapiresponse = new HashMap<String, String>();
			if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				responseFlag = response.getEntity(String.class);
				logger.info("LoginApi Response JSON  :::: " + responseFlag);
				logger.info("Access token :::: " + JsonPath.parse(responseFlag).read("$.token"));
				loginapiresponse.put(IAPIConstantCodes.APIUSERNAME, JsonPath.parse(responseFlag).read("$.username").toString());
				loginapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, JsonPath.parse(responseFlag).read("$.token").toString());
				loginapiresponse.put(IAPIConstantCodes.APIResponseUSERID, JsonPath.parse(responseFlag).read("$.id").toString());
				loginapiresponse.put(IAPIConstantCodes.APIResponseEMAIL, JsonPath.parse(responseFlag).read("$.email").toString());
				loginapiresponse.put(IAPIConstantCodes.APIResponseFirstName, JsonPath.parse(responseFlag).read("$.firstName").toString());
				loginapiresponse.put(IAPIConstantCodes.APIResponseLastName, JsonPath.parse(responseFlag).read("$.lastName").toString());
				loginapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, baseurl);
				loginapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
				loginapiresponse.put(IAPIConstantCodes.INVALIDUSERNAMEPASSWORD, response.getEntity(String.class));
				loginapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, baseurl);
				loginapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
				loginapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, baseurl);
				loginapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
				loginapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, baseurl);
				loginapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			}else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
				loginapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, baseurl);
				loginapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
				loginapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, baseurl);
				loginapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
			} else {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return loginapiresponse;
	}

	
					   
	// ("@API : /api/v1/users/roles")
	public HashMap<String, String> getRolesApi(HashMap<String, String> authenticationapiresponse, String roleType) {
		getrolesapiresponse = new HashMap<String, String>();
		String responseFlag = null;
		String Token = null;

		if (roleType.equalsIgnoreCase(IAPIConstantCodes.ACCOUNTADMIN)) {

			try {

				client = Client.create();
				webResource = client.resource(authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
						.path(RevbaseAPIURLs.USERROLEURL);

				response = webResource.accept(ApiResources.Content_Type).type(ApiResources.Content_Type)
						.header("Authorization", "VBrick " + authenticationapiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
						.get(ClientResponse.class);

				if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				    responseFlag = response.getEntity(String.class);
					   logger.info("Get Roles API JSON Response :::: " + responseFlag);
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					   getrolesapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, authenticationapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
					   List<String> rolenamelist = JsonPath.read(responseFlag, "$.[*].name");
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseJSON, rolenamelist.toString() );
					   getrolesapiresponse.put(IAPIConstantCodes.ROLEID, (String) JsonPath.parse(responseFlag).read("$.[0].id"));
					   getrolesapiresponse.put(IAPIConstantCodes.ROLETYPE, roleType);
					   logger.info("statusInfo ::: "+response.getStatusInfo().toString());
					   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
					   
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
	  
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else {
				    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				   }
			
			}

			catch (Exception e) {
				e.printStackTrace();
			}

		} else if (roleType.equalsIgnoreCase(IAPIConstantCodes.MEDIAADMIN)) {

			try {
				client = Client.create();
				webResource = client.resource(authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
						.path(RevbaseAPIURLs.USERROLEURL);
				response = webResource.accept(ApiResources.Content_Type).type(ApiResources.Content_Type)
						.header("Authorization", "VBrick " + authenticationapiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
						.get(ClientResponse.class);
				if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				    responseFlag = response.getEntity(String.class);
					   logger.info("DME devices API JSON Response :::: " + responseFlag);
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					   getrolesapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, authenticationapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
					   List<String> rolenamelist = JsonPath.read(responseFlag, "$.[*].name");
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseJSON, rolenamelist.toString() );
					   getrolesapiresponse.put(IAPIConstantCodes.ROLEID, (String) JsonPath.parse(responseFlag).read("$.[1].id"));
					   getrolesapiresponse.put(IAPIConstantCodes.ROLETYPE, roleType);
					   logger.info("statusInfo ::: "+response.getStatusInfo().toString());
					   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
					   
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
	  
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else {
				    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				   }
			
			}

			catch (Exception e) {
				e.printStackTrace();
			}

		} else if (roleType.equalsIgnoreCase(IAPIConstantCodes.MEDIAVIEWER)) {
			try {
				client = Client.create();
				webResource = client.resource(authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
						.path(RevbaseAPIURLs.USERROLEURL);
				response = webResource.accept(ApiResources.Content_Type).type(ApiResources.Content_Type)
						.header("Authorization", "VBrick " + authenticationapiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
						.get(ClientResponse.class);

				if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				    responseFlag = response.getEntity(String.class);
					   logger.info("DME devices API JSON Response :::: " + responseFlag);
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					   getrolesapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, authenticationapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
					   List<String> rolenamelist = JsonPath.read(responseFlag, "$.[*].name");
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseJSON, rolenamelist.toString() );
					   getrolesapiresponse.put(IAPIConstantCodes.ROLEID, (String) JsonPath.parse(responseFlag).read("$.[7].id"));
					   getrolesapiresponse.put(IAPIConstantCodes.ROLETYPE, roleType);
					   logger.info("statusInfo ::: "+response.getStatusInfo().toString());
					   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
					   
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
	  
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else {
				    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				   }
			
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		} else if (roleType.equalsIgnoreCase(IAPIConstantCodes.EVENTADMIN)) {
			try {
				client = Client.create();
				webResource = client.resource(authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
						.path(RevbaseAPIURLs.USERROLEURL);
				response = webResource.accept(ApiResources.Content_Type).type(ApiResources.Content_Type)
						.header("Authorization", "VBrick " + authenticationapiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
						.get(ClientResponse.class);
				if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				    responseFlag = response.getEntity(String.class);
					   logger.info("DME devices API JSON Response :::: " + responseFlag);
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					   getrolesapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, authenticationapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
					   List<String> rolenamelist = JsonPath.read(responseFlag, "$.[*].name");
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseJSON, rolenamelist.toString() );
					   getrolesapiresponse.put(IAPIConstantCodes.ROLEID, (String) JsonPath.parse(responseFlag).read("$.[2].id"));
					   getrolesapiresponse.put(IAPIConstantCodes.ROLETYPE, roleType);
					   logger.info("statusInfo ::: "+response.getStatusInfo().toString());
					   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
					   
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
	  
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else {
				    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				   }
			
			}

			catch (Exception e) {
				e.printStackTrace();
			}
		} else if (roleType.equalsIgnoreCase(IAPIConstantCodes.MEDIACONTRIBUTOR)) {
			try {
				client = Client.create();
				webResource = client.resource(authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
						.path(RevbaseAPIURLs.USERROLEURL);
				response = webResource.accept(ApiResources.Content_Type).type(ApiResources.Content_Type)
						.header("Authorization", "VBrick " + authenticationapiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
						.get(ClientResponse.class);
				if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				    responseFlag = response.getEntity(String.class);
					   logger.info("DME devices API JSON Response :::: " + responseFlag);
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					   getrolesapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, authenticationapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
					   List<String> rolenamelist = JsonPath.read(responseFlag, "$.[*].name");
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseJSON, rolenamelist.toString() );
					   getrolesapiresponse.put(IAPIConstantCodes.ROLEID, (String) JsonPath.parse(responseFlag).read("$.[5].id"));
					   getrolesapiresponse.put(IAPIConstantCodes.ROLETYPE, roleType);
					   logger.info("statusInfo ::: "+response.getStatusInfo().toString());
					   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
					   
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
	  
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else {
				    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				   }
			
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		} 		else if (roleType.equalsIgnoreCase(IAPIConstantCodes.EVENTHOST)) {
			try {
				client = Client.create();
				webResource = client.resource(authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl))
						.path(RevbaseAPIURLs.USERROLEURL);
				response = webResource.accept(ApiResources.Content_Type).type(ApiResources.Content_Type)
						.header("Authorization", "VBrick " + authenticationapiresponse.get(IAPIConstantCodes.APIRequestAccessToken))
						.get(ClientResponse.class);
				if (response.getStatus() == HttpStatusCode.httpStatusCodeInt200) {
				    responseFlag = response.getEntity(String.class);
					   logger.info("DME devices API JSON Response :::: " + responseFlag);
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
					   getrolesapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, authenticationapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
					   List<String> rolenamelist = JsonPath.read(responseFlag, "$.[*].name");
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseStatusInfo, response.getStatusInfo().toString());
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseJSON, rolenamelist.toString() );
					   getrolesapiresponse.put(IAPIConstantCodes.ROLEID, (String) JsonPath.parse(responseFlag).read("$.[3].id"));
					   getrolesapiresponse.put(IAPIConstantCodes.ROLETYPE, roleType);
					   logger.info("statusInfo ::: "+response.getStatusInfo().toString());
					   logger.info("httpcode ::: "+Integer.toString(response.getStatus()));
					   
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt401) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
	  
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt400) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt404) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   }else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt500) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else if (response.getStatus() == HttpStatusCode.httpStatusCodeInt503) {
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseBaseUrl, authenticationapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
					   getrolesapiresponse.put(IAPIConstantCodes.APIResponseHttpCode, Integer.toString(response.getStatus()));
				   } else {
				    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				   }
			
			}

			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return getrolesapiresponse;

	}

}
