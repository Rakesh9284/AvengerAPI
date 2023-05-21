package com.vbrick.avenger.funUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WriteJson {

	JSONObject obj;
	JSONArray list;
	private static Logger logger = Logger.getLogger(WriteJson.class);

	/**
	 * To write value to a .Json file
	 * 
	 * @param sKey
	 *            : Pass the key to the json object
	 * @param sValue
	 *            : Pass the value to the json object
	 */
	@SuppressWarnings("unchecked")
	public void WritetoJson(String sKey, String sValue, String sFileName) {
		obj = new JSONObject();
		
		obj.put(sKey, sValue);
		try {

			FileWriter file = new FileWriter(sFileName, true);
			BufferedWriter bufferWritter = new BufferedWriter(file);
			bufferWritter.write(obj.toJSONString());
			bufferWritter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			logger.info("The values in the json file" + obj);
		}
	}

	/*public static void main(String ar[]) {
		ReadPropertiesFile file= new ReadPropertiesFile();
		WriteJson json = new WriteJson();
	//	json.WritetoJson("Test1", "Test2",file.readData("jsonwrite", "FilePath.properties"));

		logger.info("Path=="+json.getpath("/src/test/resources/FilePath.properties"));
	}*/

	public String getpath(String sFilepath) {
		File file = new File(System.getProperty("user.dir")+sFilepath); 
		if(file.exists())
			return file.getPath();
		else
			return "File not Found";
			 
	}
}
