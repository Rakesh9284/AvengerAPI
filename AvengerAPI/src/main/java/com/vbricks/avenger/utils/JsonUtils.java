package com.vbricks.avenger.utils;

import java.util.ArrayList;
import java.util.Iterator;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.google.gson.Gson;
import com.jayway.restassured.path.json.JsonPath;

public class JsonUtils {
	
	public static String removeElement(String jsonString,String element)
	{
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(jsonString);
		jsonObject.remove(element);
		return jsonObject.toString();
	}
	
	public static String toJsonString(Object obj)
	{
		Gson gson = new Gson();
		String jsonString = gson.toJson(obj);
		return jsonString;
	}
	
	 
	public static String getElementFromResponse(String element , String response)
	{
		JsonPath jsonPath = new JsonPath(response);
		String elementValue = jsonPath.getString(element);
		return elementValue;
	}
	
	public static Object toBean(String jsonString , Object object){
		object = new Gson().fromJson(jsonString, object.getClass()); 
		return object;
	}
	
	public static boolean subJson(String mainJson , String childJson){
		JSONObject mainJsonPath = (JSONObject)JSONSerializer.toJSON(mainJson);
		JSONObject childJsonPath = (JSONObject)JSONSerializer.toJSON(childJson);
		
        Iterator iterator = childJsonPath.keys();
        
        while(iterator.hasNext()){
        	String key = (String)iterator.next();
        	if(mainJsonPath.containsKey(key)){
        		
        	}
        }
		return true;
	}
	
	
}