package com.vbricks.avenger.service;

import java.util.HashMap;

public interface IDeleteVideoService {
	

	public HashMap<String, String> deleteVideos(HashMap<String, String> apiresponse );
	String invalidVideoId="d9372c33-cdcb-447f-a4d8-9f01dd508888";
	
	String TRUE = "true";
	String FALSE = "false";
	String PRIVATE ="Private";
	String USERID ="userId";
	String USER ="User";
}
