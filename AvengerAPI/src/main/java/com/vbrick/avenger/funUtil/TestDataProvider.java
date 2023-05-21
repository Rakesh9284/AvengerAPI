/*package com.vbrick.avenger.funUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;


public class TestDataProvider  {
	public  final static  String testDateFile = "/src/test/resources/DataSource/TestData.json";
	
	
	public static  String getFilePath(String sFilepath) {
		char cforwardslash = (char) 47;
		char cbackslash = (char) 92;
		String sPath = System.getProperty("user.dir").replace(cbackslash,
				cforwardslash)
				+ sFilepath;

		File file = new File(sPath);
		if (file.exists()) {
			sPath = file.getAbsolutePath();
			System.out.println("The File Path is " + sPath);
		} else {
			System.err.println("File not Found");
		}
		return sPath;
	}
	*//**
	 * JasonTestProvider method is used to get the data as data provider
	 * @return
	 *//*
	@DataProvider(name="JSONProvider",parallel=true)
	public static Object[][] JasonTestProvider() {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			Object obj = parser
					.parse(new FileReader(getFilePath(testDateFile)));
			jsonObject = (JSONObject) obj;
					} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Object[][] { { jsonObject } };
	}
}
*/