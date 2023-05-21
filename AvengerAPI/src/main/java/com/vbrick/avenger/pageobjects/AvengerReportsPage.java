package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerReportsPage extends WebElements{

	WebDriver driver;
	CustomReport customReport;
	private BasePage basePage;

	private static Logger logger = Logger.getLogger(AvengerReportsPage.class);
	By publicwebcastusage=By.xpath("//*[contains(@ng-repeat,'licenseBucket')]/div[2]");
	By publichoursusageleft=By.xpath("//*[contains(@ng-repeat,'licenseBucket')]/span");
	By videoanalyticsCSVbutton=By.xpath("//a[contains(@href,'analytics')]");
	
	
	public AvengerReportsPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport = customReport;
		this.basePage=basePage;
	}
	
	public String verify_label(String label)
	{
		By labellocator =By.xpath("//*[contains(text(),'"+label+"')]");
		return getText(labellocator);
	}
	
	public void click_VideoAnalyticsCSV(){
		 
		  click(videoanalyticsCSVbutton);
		 }
	
	
	public ArrayList<String> getPublicWebcastUsage()
	{
		ArrayList<String> publicwebcastusagelist=getAllWebElementValueslist(publicwebcastusage);
	logger.info("The public webcastusage list is"+publicwebcastusagelist);
		return publicwebcastusagelist;
	}
	
	public ArrayList<String> getHoursLeft()
	{
		ArrayList<String> publicwebcastusageleft=getAllWebElementValueslist(publichoursusageleft);
	   logger.info("The public webcastusage list is"+publicwebcastusageleft);
		return publicwebcastusageleft;
	
	}
	
}
