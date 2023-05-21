package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerDMENetworkStatisticsPropertyPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerDMENetworkStatisticsPage extends WebElements {

	private static Logger logger = Logger.getLogger(AvengerDMENetworkStatisticsPage.class);
	
	By adddmebuttonlocator=By.xpath(AvengerDMENetworkStatisticsPropertyPage.dmenetworkstatisticspage_adddmebuttonlocator.getProperty());
	By dmedeviceslocator=By.xpath("//*[contains(@class,'primary')]/h6");
	
	 private WebDriver driver;
		private CustomReport customReport;
		private BasePage basePage;
	
	public AvengerDMENetworkStatisticsPage(WebDriver driver,CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
	}
	
	public AvengerAddNewDmeDevicePage click_addDME(){
		click(adddmebuttonlocator);
		logger.info("Clicked on Add DME Button");
		return basePage.avengerAddDmeDevicePage(driver, customReport, basePage);
		
	}
	

	//API
	public ArrayList<String> get_dmedevices()
	{
		return getAllWebElementValueslist(dmedeviceslocator);
	}
}
