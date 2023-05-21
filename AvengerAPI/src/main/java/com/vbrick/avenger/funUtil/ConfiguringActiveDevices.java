package com.vbrick.avenger.funUtil;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.EnviromentAccounts;

public class ConfiguringActiveDevices extends WebElements implements EnviromentAccounts {

	private WebDriver driver;
	CustomReport customReport;
	private BasePage basePage;
	private static Logger logger = Logger.getLogger(ConfiguringActiveDevices.class);

	public ConfiguringActiveDevices(WebDriver driver,CustomReport customReport, BasePage basePage) {
		super(driver);
		this.driver = driver;
		this.customReport= customReport;
		this.basePage=basePage;
	}
    	By dmeusername=By.name("qtssusername");
    	By dmepassword=By.name("qtsspassword");
    	By submitbutton=By.cssSelector("input[type=\"submit\"]");
    	By systemconfiguration=By.xpath("//a[contains(text(),'System Configuration')]");
    	By revinterface=By.xpath("//a[contains(text(),'Rev Interface')]");
    	By revenabledcheckbox=By.name("vbrfAvengerEnabled");
    	By revserverurl=By.name("vbrfAvengerURL");
        By dmeapikey=By.name("vbrfAvengerAPIKey");
        By macaddresslocator=By.xpath("//*[contains(text(),'MAC Address')]/following-sibling::td");
        By logoutbutton=By.xpath("//a[contains(text(),'Log Out')]");
        By encoderusername=By.name("username");
        By encoderpassword=By.name("password");
        By encodersubmitbutton=By.name("goto");
        By encoderdashboard=By.xpath("//a[contains(text(),'Dashboard')]");
   		By encoderprogramname=	By.xpath("//td/a[contains(text(),'Program 1')]/../p/span[2]");
   		By general=By.xpath("//a[contains(text(),'General')]");
   		By editbutton=By.xpath("//*[@onclick='setEdit();']");
   		By vbrickrevinterfaceenable=By.name("vbrickREMEnableTmp");
   		By host=By.name("vbrickREMHostTmp");
   		By encoderapikey=By.name("vbrickREMAuthorizationKeyTmp");
   		By encoderapplybutton=By.xpath("//tbody/tr/td[1]/input[1]");
   		By encodersavebutton=By.xpath("//tbody/tr/td[1]/input[4]");
   		By programonelocator=By.xpath("//td/a[contains(text(),'Program 1')]");
   		By vbrickprogramname=By.xpath("//*[contains(@name,'vbrickProgramName')]");
   		
   
        		
	public Map<String,String> configureActiveDME()
	{
		Map<String, String> dmedetailsmap = new HashMap<String, String>();
		dmedetailsmap.put("ApiKey","12345");
		enterText(dmeusername, "admin");
		enterText(dmepassword, "admin");
		click(submitbutton);
		pause(10000);
		logger.info("In frame");
		driver.switchTo().frame(driver.findElement(By.name("nav")));
		if(!isDisplayedWithoutException(revinterface))
	     click(systemconfiguration);
	    	click(revinterface);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.name("content")));
		check_Checkbox(revenabledcheckbox);
        enterText(revserverurl, REVDMEURL);
        enterText(dmeapikey, dmedetailsmap.get("ApiKey"));
       
        //getting the mac address for the DME.
		
        String dmemacaddress=getText(macaddresslocator);
		dmedetailsmap.put("dmemacaddress", dmemacaddress);
		click(submitbutton);
		pause(10000);	
		try
		{
		Alert alert = driver.switchTo().alert(); 
		if(alert.getText()!=null)
			alert.accept();
		else
			logger.info("Displayed alert is"+ alert.getText());
		}
		catch(NoAlertPresentException e){
			logger.info("Alert not opened");
		}
		pause(10000);	
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.name("nav")));
		//driver.findElement(By.xpath("//a[contains(text(),'System Configuration')]")).click();
	     click(logoutbutton);
		return dmedetailsmap;	
	}

	public Map<String,String> configureActiveEncoder()  
	{
		pause(5000);
		Map<String, String> encoderdetails = new HashMap<String, String>();
		encoderdetails.put("ApiKey", "12345");
		enterText(encoderusername, "admin");
		enterText(encoderpassword, "admin");
		click(encodersubmitbutton);
		pause(10000);
		driver.switchTo().frame(driver.findElement(By.name("navigation_win")));
		logger.info("In frame");
		
		//Getting program
		click(encoderdashboard);
		driver.switchTo().frame(driver.findElement(By.name("config")));
		encoderdetails.put("ProgramName", getText(encoderprogramname));
		logger.info("ProgramName is "+getText(encoderprogramname));
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(driver.findElement(By.name("navigation_win")));
		if(!isDisplayedWithoutException(general))
		     click(systemconfiguration);
	/*	
		By openxpath=By.xpath("//a[contains(text(),'System Configuration')]/../..");
		if(driver.findElement(openxpath).getAttribute("class").equals("closed"))
		driver.findElement(By.xpath("//a[contains(text(),'System Configuration')]")).click();
	*/	click(general);
		pause(5000);
		
		driver.switchTo().frame(driver.findElement(By.name("buttons")));
		String attribute = getAttribute(editbutton,"value");
		logger.info("Attribute is "+attribute);
		attribute=attribute.trim();
		logger.info("Attribute after trim is "+attribute);
		if(!attribute.contains("Finish"))
		{
		click(editbutton);
		pause(5000);
		}
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.name("navigation_win")));
		driver.switchTo().frame(driver.findElement(By.name("config")));
        check_Checkbox(vbrickrevinterfaceenable);
        enterText(host, REVDMEURL.split("//")[1].replace("/", ""));
        enterText(encoderapikey,encoderdetails.get("ApiKey"));
        driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.name("navigation_win")));
		driver.switchTo().frame(driver.findElement(By.name("buttons")));
		click(encoderapplybutton);
		pause(5000);
		click(encoderapplybutton);
		click(encodersavebutton);
		pause(5000);
		Alert alert = driver.switchTo().alert();
		try
		{
		if(alert.getText()!=null)
			alert.accept();
		else
			logger.info("Displayed alert is"+ alert.getText());
		}
		catch(NoAlertPresentException e)
		{
			logger.info("Alert not opened");
		}
		pause(10000);
		//driver.findElement(By.xpath("//tr/td[2]/input")).click();
		return encoderdetails;	
	}
	
	public Map<String, String> changeEncoderProgram(WebDriver driver,String programname) 
	{
		pause(5000);
		Map<String, String> programdetails = new HashMap<String, String>();
		programdetails.put("ChangedEncoderProgram",programname);
		enterText(encoderusername, "admin");
		enterText(encoderpassword, "admin");
		click(encodersubmitbutton);
		pause(10000);
		driver.switchTo().frame(driver.findElement(By.name("navigation_win")));
		logger.info("In frame");
		
		//Getting program
		click(encoderdashboard);
		driver.switchTo().frame(driver.findElement(By.name("config")));
		click(encoderdashboard);
		click(programonelocator);
		driver.switchTo().defaultContent();
		
		pause(10000);
		driver.switchTo().frame(driver.findElement(By.name("navigation_win")));
		driver.switchTo().frame(driver.findElement(By.name("buttons")));
		String attribute = getAttribute(editbutton,"value");
		logger.info("Attribute is "+attribute);
		attribute=attribute.trim();
		logger.info("Attribute after trim is "+attribute);
		if(!attribute.contains("Finish"))
		{
		click(editbutton);
		pause(5000);
		}
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(driver.findElement(By.name("navigation_win")));
		driver.switchTo().frame(driver.findElement(By.name("config")));
		programdetails.put("EncoderProgramName", getAttribute(vbrickprogramname,"value"));
		logger.info("Program Actual @@@@@"+getAttribute(vbrickprogramname,"value")+"Program Chnaged @@@@@"+programname);
		enterText(vbrickprogramname, programname);
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(driver.findElement(By.name("navigation_win")));
		driver.switchTo().frame(driver.findElement(By.name("buttons")));
	    click(encoderapplybutton);
		pause(10000);
	    click(encodersavebutton);
		pause(10000);
		try
		{
		Alert alert = driver.switchTo().alert();
		if(alert.getText()!=null)
			alert.accept();
		else
			logger.info("Displayed alert is"+ alert.getText());
		}
		catch(NoAlertPresentException e)
		{
			logger.info("Alert not opened");
		}
		pause(10000);
		//driver.findElement(By.xpath("//tr/td[2]/input")).click();
		
		//alert1.accept();
		click(editbutton);
		logger.info("Clicked on Finish Edit Button");
		pause(10000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.name("navigation_win")));
        click(logoutbutton);
		pause(10000);
		try
		{
		Alert alert = driver.switchTo().alert();
		if(alert.getText()!=null)
		{
			logger.info("The Alert Opened with text");
			alert.accept();
		}
		else
			logger.info("Displayed alert is"+ alert.getText());
		}
		catch(NoAlertPresentException e)
		{
			logger.info("Alert not opened");
		}
		
		return programdetails;	
		
		
	}
	
	public void changeDMEApikey(WebDriver driver,String apikey) 
	{
		enterText(dmeusername, "admin");
		enterText(dmepassword, "admin");
		click(submitbutton);
		pause(10000);
		driver.switchTo().frame(driver.findElement(By.name("nav")));

		if(!isDisplayedWithoutException(revinterface))
		click(systemconfiguration);
		
		click(revinterface);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.name("content")));
	
		enterText(dmeapikey, apikey);
		
		/*driver.findElement(By.name("vbrfAvengerURL")).clear();
		driver.findElement(By.name("vbrfAvengerURL")).sendKeys(url);
	*/	
		click(submitbutton);
		
		try
		{
		Alert alert = driver.switchTo().alert();
		if(alert.getText()!=null)
			alert.accept();
		else
			logger.info("Displayed alert is"+ alert.getText());
		}
		catch(NoAlertPresentException e){
			logger.info("Alert not opened");
		}
		pause(10000);	
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.name("nav")));
		//driver.findElement(By.xpath("//a[contains(text(),'System Configuration')]")).click();
        click(logoutbutton);
		try
		{
		Alert alert = driver.switchTo().alert();
		if(alert.getText()!=null)
			alert.accept();
		else
			logger.info("Displayed alert is"+ alert.getText());
		}
		catch(NoAlertPresentException e)
		{
			logger.info("Alert not opened");
		}
	}
	
	public void changeEncoderApiKey(WebDriver driver,String apikey) 
	{
		pause(5000);
		enterText(encoderusername, "admin");
		enterText(encoderpassword, "admin");
		click(encodersubmitbutton);
    	pause(10000);
		driver.switchTo().frame(driver.findElement(By.name("navigation_win")));
		if(!isDisplayedWithoutException(general))
		     click(systemconfiguration);
		click(general);
		pause(5000);
		
		driver.switchTo().frame(driver.findElement(By.name("buttons")));
		String attribute = getAttribute(editbutton,"value");
		logger.info("Attribute is "+attribute);
		attribute=attribute.trim();
		logger.info("Attribute after trim is "+attribute);
		if(!attribute.contains("Finish"))
		{
	    click(editbutton);
	    pause(5000);
		}
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(driver.findElement(By.name("navigation_win")));
		driver.switchTo().frame(driver.findElement(By.name("config")));
		enterText(encoderapikey, apikey);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.name("navigation_win")));
		driver.switchTo().frame(driver.findElement(By.name("buttons")));
		click(encoderapplybutton);
		pause(5000);
		click(encodersavebutton);
		try
		{
		Alert alert = driver.switchTo().alert();
		if(alert.getText()!=null)
			alert.accept();
		else
			logger.info("Displayed alert is"+ alert.getText());
		}
		catch(NoAlertPresentException e)
		{
			logger.info("Alert not opened");
		}
		pause(10000);
		driver.findElement(By.xpath("//tr/td[2]/input")).click();
		pause(10000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.name("navigation_win")));
	    click(logoutbutton);
		try
		{
		Alert alert = driver.switchTo().alert();
		if(alert.getText()!=null)
			alert.accept();
		else
			logger.info("Displayed alert is"+ alert.getText());
		}
		catch(NoAlertPresentException e)
		{
			logger.info("Alert not opened");
		}
	}
}
