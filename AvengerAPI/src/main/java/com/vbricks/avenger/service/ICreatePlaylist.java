package com.vbricks.avenger.service;
 

import java.util.HashMap;

import org.json.simple.JSONObject;

public interface ICreatePlaylist {
	
	public HashMap<String, String>   createPlaylist(HashMap<String, String> apiresponse, JSONObject categortJSON);
 	

}
