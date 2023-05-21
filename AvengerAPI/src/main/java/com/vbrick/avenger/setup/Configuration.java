
package com.vbrick.avenger.setup;

import org.apache.log4j.Logger;

import com.vbrick.avenger.funUtil.CustomReporter;

public class Configuration implements Setup,EnviromentAccounts{

	private static Logger logger = Logger.getLogger(Configuration.class);
	int urlFlag=1;
	    
	public static boolean isEmpty(String item) {
		if (item == null) {
			return true;
		}

		else if (item.trim().length() == 0) {
			return true;
		}

		return false;
	}
	
	public static String randomData()
	{
		String param = System.getProperty("Random");
		if (isEmpty(param)) {
			return sRandomData;
		}
		logger.info("The parameter for random data is" + param);
		logger.info("Random Value" + sRandomData);
		return param;
	}

	public static String getEnvironment() {
		String param = System.getProperty("environment");
		if (isEmpty(param)) {
			return sEnvironment;
		}
		logger.info("The parameter of Username from pom" + param);
		logger.info("The Automation URL is not from Pom" + sEnvironment);
		return param;
	}

	public static String getSeleniumJar() {
		String param = System.getProperty("seleniumjar");
		if (isEmpty(param)) {
			return seleniumjar;
		}
		logger.info("The parameter of Username from pom" + param);
		logger.info("The Automation URL is not from Pom" + seleniumjar);
		return param;
	}

	
	public static String getMutipleEnv() {
		String param = System.getProperty("mutipleEnv");
		if (isEmpty(param)) {
			return AccountsFlag;
		}	
		logger.info("The parameter of Username from pom" + param);
		return param;
	}
	
	public static String getAutomationURL() {
		String param = System.getProperty("envurl");
		
		if (isEmpty(param)) {
				return AutomationURL;
		}
		logger.info("The parameter of Username from pom" + param);
//		logger.info("The Automation URL is not from Pom" + AutomationURL_);
		return param;
	}

	public static String getAutomationUsername() {
		String param = System.getProperty("username");
		if (isEmpty(param)) {
			return AutomationUserName;
		}
		logger.info("The parameter of username from Pom" + param);
		logger.info("The username paramter is not from pom"
				+ AutomationUserName);
		return param;
	}

	public static String getAutomationPassword() {
		String param = System.getProperty("password");
		if (isEmpty(param)) {
			return AutomationPassword;
		}
		logger.info("The paramter of Password from Pom" + param);
		logger.info("The Password paramter is not from pom"
				+ AutomationPassword);

		return param;
	}

	public static String getAutomationBrowser() {
		String param = System.getProperty("browser");
	     logger.info("Browser parameter is"+param);
		if (isEmpty(param)) {
			return browser;
		}
		logger.info("The browser Parameter from Pom" + param);
		return param;
	}

	public static String getGrid() {
		String param = System.getProperty("grid");
	     logger.info("Browser parameter is"+param);
		if (isEmpty(param)) {
			return grid;
		}
		logger.info("The grid Parameter from Pom" + param);
		return param;
	}
	
	public static String getLanguage() {
		String param = System.getProperty("language");
	     logger.info("Language is"+param);
		if (isEmpty(param)) {
			return language;
		}
		logger.info("The language Parameter from Pom" + param);
		return param;
	}
	
	public static String getTestCaseFile() {
		String param = System.getProperty("testcasefile");
	     logger.info("Test Case File is"+param);
		if (isEmpty(param)) {
			return testcasefile;
		}
		logger.info("The test case file path" + param);
		return param;
	}
	 
			public static String getAllExecutionStatusFilePath() {
			        String param = System.getProperty("executionstatusfile");
			         logger.info("Test Case File is"+param);
			        if (isEmpty(param)) {
			            return CustomReporter.getFilePath(EXECUTIONSTATUS);
			        }
			        logger.info("The test case file path" + param);
			        return param;
			    }
			public static String getEmailIdS() {
				String param = System.getProperty("email");
				if (isEmpty(param)) {
					return defaultEmailID;
				}
				logger.info("The Paramter for email is " + param);
				logger.info("The username paramter is not from pom"
						+ defaultEmailID);
				return param;
			}
	
}
