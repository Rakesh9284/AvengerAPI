package com.vbricks.avenger.api.verifications;

import org.apache.log4j.Logger;
import org.testng.Assert;

 

public class AssertionAPIResponse {
	private static Logger logger = Logger.getLogger(AssertionAPIResponse.class);
	
	public void verifyAssert_httpCode(String actualValue,String expectedValue){
		
		logger.info("API Response Actual value       @@@    :::: "+actualValue);
		logger.info("API Response Excepted value     @@@    :::: "+expectedValue);
		Assert.assertEquals(actualValue, expectedValue);
	}
}
