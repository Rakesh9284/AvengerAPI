package com.vbricks.avenger.service;

import java.util.HashMap;

import org.json.simple.JSONObject;

public interface IEditTeam {

		public HashMap<String, String>   editTeam(HashMap<String, String> apiresponse, JSONObject createteamJSON);

}
