package com.vbricks.avenger.service;

import java.util.HashMap;

public interface IUserService {


	public HashMap<String, String>   doLogin(String username,String authenticationapiresponse);
	public HashMap<String, String> getRolesApi(HashMap<String,String> authenticationapiresponse, String roleType);
	
	
}
