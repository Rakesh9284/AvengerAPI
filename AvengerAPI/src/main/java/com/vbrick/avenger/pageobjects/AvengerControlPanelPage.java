package com.vbrick.avenger.pageobjects;

import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerControlPanelPage extends WebElements{
	private static Logger logger = Logger.getLogger(AvengerControlPanelPage.class);
	By multinodearuntimelocator = By.xpath("(//*[contains(@ng-show,'runtime.runTime.isOnline')]/button)[1]");
	By multinodebruntimelocator = By.xpath("(//*[contains(@ng-show,'runtime.runTime.isOnline')]/button)[2]");
	By multinodecruntimelocator = By.xpath("(//*[contains(@ng-show,'runtime.runTime.isOnline')]/button)[3]");
	
	

	private WebDriver driver;
	private CustomReport customReport;
	private BasePage basePage;

	public AvengerControlPanelPage(WebDriver driver, CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;
		this.customReport = customReport;
		this.basePage=basePage;
		// TODO Auto-generated constructor stub
	}

	public LinkedHashMap<String, String> verify_nodeStatus()
	{
		LinkedHashMap<String,String> status=new LinkedHashMap<String, String>();
		status.put("multinodea", getText(multinodearuntimelocator));
		status.put("multinodeb", getText(multinodebruntimelocator));
		status.put("multinodec", getText(multinodecruntimelocator));
		return status;
	}

	
	public String verify_servicesInstalled(int value)
     {
    	 By serviceslocator=By.xpath("(//*[contains(@ng-model,'group.isInstalled')])["+value+"]");
    	 return getAttribute(serviceslocator, "class");
     }
	
	public void click_servicesInstalled(int value)
    {
   	 By serviceslocator=By.xpath("(//*[contains(@ng-model,'group.isInstalled')])["+value+"]");
   	 click(serviceslocator);
    logger.info("Clicked on the service"+value);
    }
	
	public void click_ApplyAllbutton()
	{
		By applyallbuttonlocator=By.xpath("(//*[contains(@ng-click,'applyRuntimeSettings')])[2]");
		click(applyallbuttonlocator);
		logger.info("Clicked on Apply all button");
	}
}