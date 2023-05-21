package com.vbricks.avenger.service;
 

import java.util.HashMap;

import org.json.simple.JSONObject;

public interface ICreateGroup {
	
	public HashMap<String, String>   createGroup(HashMap<String, String> apiresponse, JSONObject categortJSON);
 	

}
