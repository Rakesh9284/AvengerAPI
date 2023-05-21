package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerApprovalProcessPropertyPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerApprovalProcessQueuePage extends WebElements{
	
	private WebDriver driver;
	private CustomReport customReport;
	private BasePage basePage;
	
	By createapprovalprocessbuttonlocator=By.xpath(AvengerApprovalProcessPropertyPage.avengerapprovalprocess_createapporvalprocessbuttonlocator.getProperty());
	
			
	public AvengerApprovalProcessQueuePage(WebDriver driver, CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
		// TODO Auto-generated constructor stub
	}
	
	
    public ArrayList<String> getALLApprovalVideos(){
	By videosLocator = By.xpath("//a[@class='video-link']");
	waitForElementPresent(videosLocator);
	ArrayList<String> webelementslist=getAllWebElementValueslist(videosLocator);
  	return webelementslist;
}

}
