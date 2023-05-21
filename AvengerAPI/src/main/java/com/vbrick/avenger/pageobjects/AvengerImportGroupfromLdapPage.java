package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerImportGroupfromLdapPage  extends WebElements{
	
	private WebDriver driver;
	private CustomReport customReport;
	
	private BasePage basePage;
	
	By availablegroups=By.xpath("//*[@ng-click='add(group)']");
	By importbutton=By.xpath("//*[@ng-click='saveLdapGroupsToSynchronize()']");		
	private static Logger logger = Logger.getLogger(AvengerImportGroupfromLdapPage.class);

	public AvengerImportGroupfromLdapPage(WebDriver driver,  
			CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport = customReport;
		this.basePage=basePage;
	}


	
	
	public AvengerGroupsPage click_Import()
	{
		click(importbutton);
		customReport.reporter("Clicked on Import Group Button","");
		return basePage.avengerGroupsPage(driver,  customReport, basePage);

	}
	
	public void selectGroup(String groupname)
	{
	
		ArrayList<String> groupnames=get_allGroupNames();
		logger.info("Groupname index is"+groupnames.indexOf(groupname));
		int groupindex=groupnames.indexOf(groupname)+1;
		logger.info("Index of the Group is"+groupindex);
		By groupnamelocator =By.xpath("//div/div['"+groupindex+"']/div/div/div[2]/div[1]/button[1]");
        click(groupnamelocator);
        pause(5000);
       customReport.reporter("Clicked on Group" ,groupname);
	}
	
	public ArrayList<String> get_allGroupNames()
	{
		By allgroupsxpath=By.xpath("//*[@class='glyphicons group']/..");
		List<WebElement> webelements=getAllWebElementValues(allgroupsxpath);
			ArrayList<String> groups =  new ArrayList<String>();
			for (WebElement webelement : webelements) {
			logger.info("Groups in Ldap are "+webelement.getText());
			groups.add(webelement.getText());
			}
			return groups;
	}
	
	public void clickSelectAllButton()
	{
		By selectalllocator=By.xpath("//*[@ng-click='selectAll()']");
		click(selectalllocator);
	}
}
