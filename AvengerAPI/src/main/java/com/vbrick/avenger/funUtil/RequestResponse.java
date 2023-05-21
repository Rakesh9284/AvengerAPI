package com.vbrick.avenger.funUtil;

import org.openqa.selenium.firefox.FirefoxProfile;

public class RequestResponse {

   public void captureRequestResponse(FirefoxProfile profile,String sTestCaseName)
   {
       profile.setPreference("extensions.firebug.netexport.defaultLogDir", "C:\\Downloads\\_har\\"+sTestCaseName);
	   
   }
}
