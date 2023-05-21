package com.vbricks.avenger.service;

import java.util.HashMap;

import org.json.simple.JSONObject;

public interface IEditGroup {
		
		public HashMap<String, String>   editGroup(HashMap<String, String> apiresponse, JSONObject createteamJSON);
	 	
}
