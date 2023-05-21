/*package com.vbrick.avenger.setup;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.vbrick.avenger.funUtil.FileOperation;

public class LocalDriverFactory implements Setup{
	
	public WebDriver createInstance(String browserName) throws MalformedURLException {
		
        DesiredCapabilities capabilities= new DesiredCapabilities();;
        if (browserName.toLowerCase().contains("firefox")) {
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/plain");
			firefoxProfile.setPreference("security.mixed_content.block_active_content", false);
			firefoxProfile.setPreference("security.mixed_content.block_display_content", true);
			firefoxProfile.setPreference("browser.download.folderList", 2);
			firefoxProfile.setPreference("browser.download.dir",new FileOperation().getFilePath(DOWNLOADSPATH));
			firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"image/jpg,text/csv,text/xml,text/plain,application/xml,application/json,application/vnd.ms-excel,application/octet-stream, application/x-excel,application/x-msexcel,application/excel,application/pdf");
            capabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
			capabilities.setPlatform(Platform.WINDOWS);
			capabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
        }
        else if (browserName.toLowerCase().contains("IE")) {
        	capabilities.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
			capabilities.setPlatform(Platform.WINDOWS);
        }
        
        else if (browserName.toLowerCase().contains("chrome")) {
        	capabilities.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
			capabilities.setPlatform(Platform.WINDOWS);
        
        
        
        }
        else if (browserName.toLowerCase().contains("safari")) {
           	capabilities.setBrowserName(DesiredCapabilities.safari().getBrowserName());
			capabilities.setPlatform(Platform.WINDOWS);
        }
       
     		return new RemoteWebDriver(new URL("http://localhost:3000/wd/hub"),capabilities);
     }

}
*/