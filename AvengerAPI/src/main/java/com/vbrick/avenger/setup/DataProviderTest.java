

package com.vbrick.avenger.setup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//This class is not used
public class DataProviderTest extends TestBase {

	private static Logger logger = Logger.getLogger(DataProviderTest.class);
	private String sFilepath = "/src/test/TestDataJSON";

	/**
	 * 
	 * @return : Json object associated with the pages.
	 */
	public Hashtable<String, JSONObject> GetJasonObjectFromFileDirectory(
			String... names) {

		File folder = new File(new TestBase().getFilePath(sFilepath));
		File[] listOfFiles = folder.listFiles();
	    logger.info("The values are in the File"+listOfFiles.toString());
	//	List<JSONObject> mylist = new ArrayList<JSONObject>();
		Hashtable<String, JSONObject> mapper = new Hashtable<String, JSONObject>();
		//logger.info("value of names" + names.length);
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				JSONParser parser = new JSONParser();
				try {
					Object obj = parser.parse(new FileReader(listOfFiles[i]
							.getAbsolutePath()));
					JSONObject jsonObject = (JSONObject) obj;
					if (names.length == 0) {
						logger.info("listOFfilenames for object is getting created"
								+ listOfFiles[i].getName());
						mapper.put(listOfFiles[i].getName()
								.replace(".json", ""), jsonObject);
					} else {
						for (String arg : names) {
							
							logger.info("values are "+arg);
							logger.info("values are "+names);
							
							logger.info("value of arguments " + arg);
							logger.info("The List of Files are------"+listOfFiles[i].getName());
							if (listOfFiles[i].getName().replace(".json", "")
									.equalsIgnoreCase(arg)) {
								logger.info("listOFfilenames for object is getting created"
										+ listOfFiles[i].getName());
								mapper.put(
										listOfFiles[i].getName().replace(
												".json", ""), jsonObject);
							}
						}
					}
				} catch (NullPointerException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					logger.error("***********FILE NOT FOUND*********************");
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else if (listOfFiles[i].isDirectory()) {
				logger.info("Directory " + listOfFiles[i].getName());
			}
		}
		return (Hashtable<String, JSONObject>) mapper;
	}

}
