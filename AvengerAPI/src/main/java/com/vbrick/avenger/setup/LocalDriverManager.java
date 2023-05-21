package com.vbrick.avenger.setup;
import org.openqa.selenium.WebDriver;

public class LocalDriverManager {
	
	  private  ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	    public WebDriver getDriver() {
	        return webDriver.get();
	    }

	    public void setWebDriver(WebDriver driver) {
	        webDriver.set(driver);
	    }

}
