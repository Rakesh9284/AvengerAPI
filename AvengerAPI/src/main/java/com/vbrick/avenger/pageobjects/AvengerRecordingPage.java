package com.vbrick.avenger.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerRecordingPropertyPage;
import com.vbrick.avenger.dao.AvengerRecordingBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerRecordingPage extends WebElements{

	WebDriver driver;
	CustomReport customReport;
	private BasePage basePage;
	private static Logger logger = Logger.getLogger(AvengerRecordingPage.class);
	
	
	public AvengerRecordingPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport = customReport;
		this.basePage=basePage;
	}
	
	public void selectPrimaryDme(AvengerRecordingBeanPage avengerrecordingbeanpage)
	{
		By primarydmelocator = By.xpath(AvengerRecordingPropertyPage.avengerrecordingpage_primarydmelocator.getProperty());
		selectValuefromDropDown(primarydmelocator,  avengerrecordingbeanpage.getPrimarydmedevice());
		customReport.reporter("Selected dme from drop down is", getDropDownSelectedOption(primarydmelocator));
	}
	
	public void selectSecondaryDme(AvengerRecordingBeanPage avengerrecordingbeanpage)
	{
		By secondarydmelocator = By.name(AvengerRecordingPropertyPage.avengerrecordingpage_secondarydmelocator.getProperty());
		selectValuefromDropDown(secondarydmelocator,  avengerrecordingbeanpage.getSecondarydmedevice());
		customReport.reporter("Selected dme from drop down is", getDropDownSelectedOption(secondarydmelocator));
	}
	
	public void clickSaveButton()
	{
		By savebuttonlocator = By.xpath(AvengerRecordingPropertyPage.avengerrecordingpage_savebuttonlocator.getProperty());
		click(savebuttonlocator);
		customReport.reporter("Save Button is Clicked", "");
		pause(5000);
	}
	
	public String getSelectedPrimaryDevice()
	{
		By primarydmelocator = By.xpath(AvengerRecordingPropertyPage.avengerrecordingpage_primarydmelocator.getProperty());
		return getDropDownSelectedOption(primarydmelocator);
	}
	
	public List<String> getPopulatedDevicesList()
	{
		By primarydmelocator = By.xpath(AvengerRecordingPropertyPage.avengerrecordingpage_primarydmelocator.getProperty());
		return getDropdownValue(primarydmelocator);
	}
	
	public List<String> getPopulatedSecondaryDeviceList()
	{
		By secondarydmelocator = By.name(AvengerRecordingPropertyPage.avengerrecordingpage_secondarydmelocator.getProperty());
		return getDropdownValue(secondarydmelocator);
	}
	
	public String verify_Label(String label)
	{
		By labellocator =By.xpath("//*[contains(text(),'"+label+"')]");
		return getText(labellocator);
	}
}
