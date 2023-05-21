package com.vbricks.avenger.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONArray;
import org.json.simple.JSONObject;

import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;

import groovy.json.JsonSlurper;

public class ApiUtils {
	
	private static Logger logger = Logger.getLogger(ApiUtils.class);
	public Client client;

	public String dateStamp() {

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
		String timeStamp = sdf.format(date);
		System.out.println("Date format-->" + timeStamp);
		return timeStamp;

	}

	/**
	 * @functionName: hmac_sha256
	 * @Description : To generate HMAC-SHA256 are specific MAC algorithms
	 * @author nageswar
	 * @param secretKey
	 * @param data
	 * @return String authenicateCode
	 */
	public String hmac_sha256(String secretKey, String data) {

		String authCode = null;

		try {

			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
			sha256_HMAC.init(secret_key);

			authCode = new String(Base64.encodeBase64(sha256_HMAC.doFinal(data.getBytes())));

			logger.info("Signature  ------------->" + authCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authCode;

	}

	public Map convertStringJsonToMap(String jsonText) {
		String str = "{\"comments\":" + jsonText + "}";

		JsonSlurper jsonSlurper = new JsonSlurper();
		Object result = jsonSlurper.parseText(jsonText);

		Map mapJson = (Map) result;

		return mapJson;
	}

	public String userJson(String username) {
		JSONObject userjson = new JSONObject();
		userjson.put("username", username);
		userjson.put("password", "Password@123");
		return userjson.toString();
	}
	
	public String userJson1(String username) {
		JSONObject userjson = new JSONObject();
		userjson.put("username", username);
		userjson.put("password", "Password@123");
		return userjson.toString();
	}
	
	public String userJsonlogout(String userid) {
		JSONObject userjson = new JSONObject();
		userjson.put("userId", userid);
		return userjson.toString();
	}
	
	public String userJson(String username, String password) {
		JSONObject userjson = new JSONObject();
		userjson.put("username", username);
		userjson.put("password", password);
		return userjson.toString();
	}

	public String JsonParsing(String json, String path) {

		return JsonPath.parse(json).read(path).toString();
	}

	
	public boolean containsAll(List<String> listA, List<String> listB) {
		System.out.println("list value is"+listA);
		System.out.println("list value is"+listB);

		boolean found = false;

		for (String a : listA)
		{
		   found = false;
		   for (String b : listB)
		   {

		      if (b.equals(a))
		      {
		         found = true;
		         System.out.println("Break...................");
		         break;
		      }
		   }

		   if (!found)
		      return false;
		}

		return true;
    }
	
	
	public String formatingapiresponse(String json) {

		System.out.println(json);
		String actuallist2 = "";
		
		if (!json.contains(",")) {

			actuallist2 = json;
			return actuallist2.replace("[", "").replace("]", "").replace("\"", "");

		} else if (json.contains(",")) {

			actuallist2 = json;
			return actuallist2.replace("[", "").replace("]", "").replace("\"", "");

		}
		return actuallist2;

	}
	
	public   String convertUTCtoIST(String json) throws ParseException{
		try{
		DateFormat pstFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date date = pstFormat.parse(json);
		DateFormat pstFormat1 = new SimpleDateFormat("MMM dd, YYYY h:mm aaa");
		pstFormat1.setTimeZone(TimeZone.getTimeZone("IST"));
		System.out.println(pstFormat1.format(date));
		String x=pstFormat1.format(date);
		return x;
		}
		catch(ParseException e){
			DateFormat pstFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSS'Z'");
			Date date = pstFormat.parse(json);
			DateFormat pstFormat1 = new SimpleDateFormat("MMM dd, YYYY h:mm aaa");
			pstFormat1.setTimeZone(TimeZone.getTimeZone("IST"));
			System.out.println(pstFormat1.format(date));
			String x=pstFormat1.format(date);
			return x;
		}
		
	}
	
	public   String convertUTCtoIST1(String json) throws ParseException{
		try{
		DateFormat pstFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date date = pstFormat.parse(json);
		DateFormat pstFormat1 = new SimpleDateFormat("MMM dd, YYYY h:mm aaa");
		pstFormat1.setTimeZone(TimeZone.getTimeZone("IST"));
		System.out.println(pstFormat1.format(date));
		String x=pstFormat1.format(date);
		return x;
		}
		catch(ParseException e){
			DateFormat pstFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSS'Z'");
			Date date = pstFormat.parse(json);
			DateFormat pstFormat1 = new SimpleDateFormat("MMM dd, YYYY h:mm aaa");
			pstFormat1.setTimeZone(TimeZone.getTimeZone("IST"));
			System.out.println(pstFormat1.format(date));
			String x=pstFormat1.format(date);
			return x;
		}
		
	}
	
	
	public HashMap<String, String> UTCdatetimeformatEvent(String days, String mins) {
		HashMap<String, String> dates = new HashMap<String, String>();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date d = new Date();
		DateTime dateTimeUtc = new DateTime(d, DateTimeZone.UTC);
		String startparts[] = splitDate(dateTimeUtc.plusDays(Integer.parseInt(days)).toString());

		String startDate = startparts[0] + "-00:00";

		logger.info("Strat Date  ::::" + startDate);

		dates.put("eventstartdate", startDate);

		logger.info(
				" Adding 1 hr : " + dateTimeUtc.plusDays(Integer.parseInt(days)).plusMinutes(Integer.parseInt(mins)));

		String endparts[] = splitDate(
				dateTimeUtc.plusDays(Integer.parseInt(days)).plusMinutes(Integer.parseInt(mins)).toString());

		String endDate = endparts[0] + "-00:00";

		logger.info("End Date  ::::" + endDate);
		dates.put("eventenddate", endDate);
		return dates;

	}
	//adding minutes for start date
	public HashMap<String, String> UTCdatetimeformatEvent1(String days, String mins) {
		HashMap<String, String> dates = new HashMap<String, String>();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date d = new Date();
		DateTime dateTimeUtc = new DateTime(d, DateTimeZone.UTC);
		String startparts[] = splitDate(
				dateTimeUtc.plusDays(Integer.parseInt(days)).plusMinutes(Integer.parseInt(mins)).toString());

		String startDate = startparts[0] + "-00:00";

		logger.info("Strat Date  ::::" + startDate);

		dates.put("eventstartdate", startDate);

		logger.info(
				" Adding 1 hr : " + dateTimeUtc.plusDays(Integer.parseInt(days)).plusMinutes(Integer.parseInt(mins)));

		String endparts[] = splitDate(
				dateTimeUtc.plusDays(Integer.parseInt("1")).plusMinutes(Integer.parseInt(mins)).toString());

		String endDate = endparts[0] + "-00:00";

		logger.info("End Date  ::::" + endDate);
		dates.put("eventenddate", endDate);
		return dates;

	}
	 
	public HashMap<String, String> UTCdatetimeformatEventdays(String afterdate,String beforedate) {
		HashMap<String, String> dates = new HashMap<String, String>();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date d = new Date();
		DateTime dateTimeUtc = new DateTime(d, DateTimeZone.UTC);
		String startparts[] = splitDate(
				dateTimeUtc.minusDays(Integer.parseInt(afterdate)).toString());

		String afterdates = startparts[0] ;

		logger.info("After Date ::::" + afterdates);

		dates.put("afterdate", afterdates);

		String endparts[] = splitDate(
				dateTimeUtc.plusDays(Integer.parseInt(beforedate)).toString());

		String beforedates = endparts[0] ;

		logger.info("Before Date  ::::" + beforedates);
		dates.put("beforedate", beforedates);
		return dates;

	}
	 
	
	
	
	
	
	public String UTCdatetimeformatMigrateVideo(String hour){
   	   DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	   Date d=new Date();
	   DateTime dateTimeUtc = new DateTime( d, DateTimeZone.UTC );
       
          
       logger.info(" Adding 1 hr : "+ dateTimeUtc.plusDays(0).plusHours(1));
          
       DateTime dates=dateTimeUtc.plusDays(0).plusHours(Integer.parseInt(hour));
	 return dates.toString();

	}
	
	
	public String minusdaysUTCformat(String days){
   	   DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	   Date d=new Date();
	   DateTime dateTimeUtc = new DateTime( d, DateTimeZone.UTC );
      
       logger.info(" Adding 1 hr : "+ dateTimeUtc.minusDays(1));
          
       DateTime dates=dateTimeUtc.minusDays(Integer.parseInt(days));
    
	 return dates.toString();

	}
	
	
	public String UTCdatetimeformataddminutes(int minutues){
	   	   DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		   Date d=new Date();
		   DateTime dateTimeUtc = new DateTime( d, DateTimeZone.UTC );
	       
	          
	       logger.info(" Adding minutes : "+ dateTimeUtc.plusDays(0).plusMinutes(minutues));
	          
	       DateTime dates=dateTimeUtc.plusDays(0).plusMinutes(minutues);
		 return dates.toString();
		}
	
 
	public  String[] splitDate(String date){
		String[] parts = date.toString().split("\\.");
		logger.info("Date parts"+parts[0]);
	return parts;
    	
	}
	
	public String randomString(int n){
		
		return   new RandomStringUtils().randomAlphabetic(n);
	}
	
	public String sliptBaseURL(String baseurl){
		 String[] baseURLArray =  baseurl.split(":");
		return baseURLArray[0];
			
	}
	
	public List<String> jsonArrayParse(String jsonresponse) {
		
		  List<String> teamlist=new ArrayList<String>();
		  try{
		   JSONArray jsonarray = new JSONArray(jsonresponse);
		   for (int i = 0; i < jsonarray.length(); i++) {
		       org.json.JSONObject jsonobject = jsonarray.getJSONObject(i);
		      System.out.println(jsonobject.getString("name"));
		       teamlist.add( jsonobject.getString("name"));
		   }
		  }
		    catch(Exception e){
		    	
		    }
 
		return teamlist;
	}
	
	public Client getClient()
	{
		client = Client.create();
		try {
			client.setConnectTimeout(60000);
			client.setReadTimeout(60000);
				  }catch(Exception e){
			 e.printStackTrace();
			 }
		return client;
	}
	
	public List<String> jsonArrayParse1(String jsonresponse) {
		
		  List<String> teamlist=new ArrayList<String>();
		  try{
		   JSONArray jsonarray = new JSONArray(jsonresponse);
		   for (int i = 0; i < jsonarray.length(); i++) {
		       org.json.JSONObject jsonobject = jsonarray.getJSONObject(i);
		      System.out.println(jsonobject.getString("admins"));
		       teamlist.add( jsonobject.getString("admins"));
		   }
		  }
		    catch(Exception e){
		    	
		    }

		return teamlist;
	}
	public String randomAlphabetic(int a) {
 		
		return   RandomStringUtils.randomAlphanumeric(a + 1);
   }
	public String submiteventcomment() {
		String comment="APIEventComment_"+randomAlphabetic(5);
		return comment;
	}
	
public String randomNumericals() {
 		
    String count=RandomStringUtils.randomNumeric(2);
	String	newcount = null;
	if(count.startsWith("0"))
	{
	newcount=count.replaceFirst("0", "1");
	}
	if(count.contains("23"))
	{
		newcount=count.replaceAll("23", "29");
	}
	if(count.contains("00"))
	{
		newcount=count.replaceAll("00", "31");
	}
	else
		return count;
	logger.info("Added Views Are:::::::"+count);
	logger.info("Added Views Are:::::::"+newcount);
	return newcount;
	  
   }
	
	public static void main(String args[]) {

	}

}
