
package com.vbrick.avenger.funUtil;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.IRiakObject;
import com.basho.riak.client.RiakException;
import com.basho.riak.client.RiakFactory;
import com.basho.riak.client.bucket.Bucket;
import com.jayway.restassured.path.json.JsonPath;
import com.vbrick.avenger.setup.Setup;

public class RiakQuery implements Setup{

		
	public static IRiakClient getRiakClient() throws RiakException
	{
		IRiakClient riakClient = RiakFactory.httpClient(RIAK_URL);
		return  riakClient;
	}


	public static String getObject(String bucketName, String key) throws RiakException{
		IRiakClient riakClient =getRiakClient();
		Bucket myBucket = riakClient.fetchBucket(bucketName).execute();
		IRiakObject myObject = myBucket.fetch(key).execute();
		System.out.println("Value from riak"+myObject.getValueAsString());
		return myObject.getValueAsString();
	}

	public static Map<String, String> getVideoPlayBackURLs(String bucketName, String key) throws RiakException
	{
		
		Map<String, String> urldetailsmap = new HashMap<String, String>();
		String playbackurljson = getObject(bucketName, key);
		JsonPath jsonPath = new JsonPath(playbackurljson);
		String[] playbackurlarray = jsonPath.getString("VideoInstances.Playbacks.Url").replace("[[", "").replace("]]", "").split(", ");
		for(int i=0;i<playbackurlarray.length;i++){

			System.out.println(playbackurlarray[i]);
		}
		
		urldetailsmap.put("FlashType", playbackurlarray[0]);
		urldetailsmap.put("RTPType", playbackurlarray[1]);
		urldetailsmap.put("DownloadType", playbackurlarray[2]);

		return urldetailsmap;

	}
	
	public  static Map<String, String> getVideoPlayBackURLsfromBrowser( WebDriver driver,String bucketName, String videoid) throws RiakException
	{
			driver.navigate().to(RIAK_URL+"/types/consistent/buckets/"+bucketName+"/keys/"+videoid);
            By playbackurlxpath=By.xpath("//body/pre");
			String playbackurltext=driver.findElement(playbackurlxpath).getText();
	        System.out.println("the play back url text is"+playbackurltext);
	        JsonPath jsonPath = new JsonPath(playbackurltext);
	        String[] playbackurlarray = jsonPath.getString("VideoInstances.Playbacks.Url").replace("[[", "").replace("]]", "").split(", ");
			for(int i=0;i<playbackurlarray.length;i++){

				System.out.println(playbackurlarray[i]);
			}
			Map<String, String> urldetailsmap = new HashMap<String, String>();
			urldetailsmap.put("FlashType", playbackurlarray[0]);
			urldetailsmap.put("RTPType", playbackurlarray[1]);
			urldetailsmap.put("DownloadType", playbackurlarray[2]);
			return urldetailsmap;
	}
	
	
	/*public static void main(String[] args) throws RiakException {
		
		String playbackurljson = getObject("VBrickPlatform_Devices_Views_DmeDevices_DmeVideoInstancesView", "6b910aca-03b0-4041-9d18-b122696c7f14");
		JsonPath jsonPath = new JsonPath(playbackurljson);
		String[] playbackurlarray = jsonPath.getString("VideoInstances.Playbacks.Url").replace("[[", "").replace("]]", "").split(", ");
		for(int i=0;i<playbackurlarray.length;i++){

			System.out.println(playbackurlarray[i]);
		}
		
	}*/
}

