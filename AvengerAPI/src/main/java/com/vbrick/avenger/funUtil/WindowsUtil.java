package com.vbrick.avenger.funUtil;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;


public class WindowsUtil {
	
	private static Logger logger = Logger.getLogger(WindowsUtil.class);
	 public final String CONHOST="/src/test/resources/TaskKillBrowsers/conhost.bat";
	     
	public  void runBatchFile(String filePath) 
	{
		try {
			Runtime.getRuntime().exec("cmd /c start "+getFilePath(filePath));
			Runtime.getRuntime().exec("cmd /c start "+getFilePath(CONHOST));
			logger.info("Batch file for Task kill is Run");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getFilePath(String sFilepath) {
		char cforwardslash = (char) 47;
		char cbackslash = (char) 92;
		logger.info("File path is "+sFilepath);
		String sPath = System.getProperty("user.dir").replace(cbackslash,
				cforwardslash)
				+ sFilepath;
		
		File file = new File(sPath);
		if (file.exists()) {
			sPath = file.getAbsolutePath();
			logger.info("The File Path is " + sPath);
		} else {
		}
		return sPath;
	}

}
