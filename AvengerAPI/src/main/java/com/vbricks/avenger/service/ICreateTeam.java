package com.vbricks.avenger.service;
 

import java.util.HashMap;

import org.json.simple.JSONObject;

public interface ICreateTeam {
	
	public HashMap<String, String>   createTeam(HashMap<String, String> apiresponse, JSONObject categortJSON);
 	

}
