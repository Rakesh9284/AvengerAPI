package com.sample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class testing {

	
	
	@Test
	public void testing() {
		/*
		 * System.out.println(" iam into testing metod");
		 * System.setProperty("webdriver.chrome.driver",
		 * "C:\\Users\\TemkarNa\\git\\Avenger-Automation-API\\AvengerAPI\\src\\test\\resources\\Browsers_Binaries\\Chrome\\chromedriver.exe"
		 * ); ChromeOptions options = new ChromeOptions(); options.
		 * setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		 * System.out.println("option is set"); WebDriver driver =new
		 * ChromeDriver(options); System.out.println("driver is set");
		 * driver.get("https://www.google.co.in/"); driver.quit();
		 * System.out.println("@@@ quitted the driver");
		 */
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.google.co.in/");
		
	}
	
	
}
