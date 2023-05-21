package com.vbrick.avenger.funUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ReadPropertiesFile {
	private static Logger logger = Logger.getLogger(WriteJson.class);

	/**
	 * Read properties from Filepath.
	 * 
	 * @param sKey
	 * @return
	 */
	public String readData(String sKey, String sFileName) {
		Properties prop = new Properties();
		try {
			// load a properties file
			File file = new File(sFileName);
			if (file.exists()) {
				prop.load(new FileReader(file));
				logger.info("The Key value is" + prop.getProperty(sKey));
			}

		} catch (IOException ex) {
			System.err.println("File does not exist");
			ex.printStackTrace();
		}
		return prop.getProperty(sKey);
	}
}
