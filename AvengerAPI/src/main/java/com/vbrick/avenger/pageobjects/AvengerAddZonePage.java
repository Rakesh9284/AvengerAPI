package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerAddZonePropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerZonesPropertyPage;
import com.vbrick.avenger.dao.AvengerAddZoneBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerAddZonePage extends WebElements{

	private WebDriver driver;
	private CustomReport customReport;
	private BasePage basePage;
	
	By zonenamelocator = By.xpath(AvengerAddZonePropertyPage.avengeraddzonepage_zonenamelocator.getProperty());
	By multicastlocator = By.xpath(AvengerAddZonePropertyPage.avengeraddzonepage_supportsmulticastlocator.getProperty());
	By ipaddresslocator = By.name(AvengerAddZonePropertyPage.avengeraddzonepage_ipaddresslocator.getProperty());
	By devicenamelocator = By.name(AvengerAddZonePropertyPage.avengeraddzonepage_devicenamelocator.getProperty());
	By createbuttonlocator = By.xpath(AvengerAddZonePropertyPage.avengeraddzonepage_createbuttonlocator.getProperty());
	By addzonelocator = By.xpath(AvengerZonesPropertyPage.avengerzonespage_addzonebuttonlocator.getProperty());
	By removedevicelocator = By.xpath(AvengerAddZonePropertyPage.avengeraddzonepage_removedevicebuttonlocator.getProperty());
	By removedeviceerrorlocator = By.xpath(AvengerAddZonePropertyPage.avengeraddzonepage_nodeviceerrorlocator.getProperty());
	By addanotherdevicelocator = By.xpath(AvengerAddZonePropertyPage.avengeraddzonepage_addanotherdevicelocator.getProperty());
	By uniquedeviceerrorlocator = By.xpath(AvengerAddZonePropertyPage.avengeraddzonepage_samedeviceerrorlocator.getProperty());
	By uniquezoneerrorlocator = By.xpath(AvengerAddZonePropertyPage.avengeraddzonepage_uniquezoneerrorlocator.getProperty());
	By supportsmulticast=By.xpath("//*[@ng-model='zone.supportsMulticast']");
	By devices=By.name("device");
	By availablestraemsearchboxlocator = By.xpath(AvengerAddZonePropertyPage.avengeeaddzonepage_availablestreamsearchlocator.getProperty());
	By assignedstreamsearchboxlocator = By.xpath(AvengerAddZonePropertyPage.avengeeaddzonepage_selectedstreamsearchlocator.getProperty());
	By addstreamlocator = By.xpath(AvengerAddZonePropertyPage.avengeeaddzonepage_addstreamlocator.getProperty());
	By removestreamlocator = By.xpath(AvengerAddZonePropertyPage.avengeeaddzonepage_removestreamlocator.getProperty());
	By customizebuttonlocator = By.xpath(AvengerAddZonePropertyPage.avengeraddzonepage_customizedevicebuttonlocator.getProperty());
	
	
	private static Logger logger = Logger.getLogger(AvengerZonesPage.class);
	
	public AvengerAddZonePage(WebDriver driver, 
			CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		this.customReport = customReport;
		this.basePage = basePage;
	}
	
	public boolean verifyAdminAccessToAddZone()
	{
		return isDisplayed(zonenamelocator);
	}
	
	/*public void addNewZone(AvengerAddZoneBeanPage avengeraddzonebeanpage)
	{
		enterText(zonenamelocator,  avengeraddzonebeanpage.getZonename());
		customReport.reporter("Zone name is ", getAttribute(zonenamelocator, "value"));
		check_Checkbox(multicastlocator);
		customReport.reporter("Supprots Multicast checkbox is checked", "");
		enterText(ipaddresslocator,  avengeraddzonebeanpage.getIpaddress());
		customReport.reporter("Ip address is ", getAttribute(ipaddresslocator, "value"));
		selectValuefromDropDown(devicenamelocator,  avengeraddzonebeanpage.getDevicename());
		pause(3000);
		logger.info("This is Akamai Device"+avengeraddzonebeanpage.getFlag());
		if(avengeraddzonebeanpage.getFlag().contains("AkamaiDevice"))
		{
			logger.info("This is Akamai Device"+avengeraddzonebeanpage.getFlag());
		}
		else {
			click(customizebuttonlocator);
			clickUsingSwitch(addstreamlocator);
			customReport.reporter("Addedzone profile data", "");
		}
			
	}*/
	
	public void addNewZone(AvengerAddZoneBeanPage avengeraddzonebeanpage)
	 {
	  enterText(zonenamelocator,  avengeraddzonebeanpage.getZonename());
	  customReport.reporter("Zone name is ", getAttribute(zonenamelocator, "value"));
	  check_Checkbox(multicastlocator);
	  customReport.reporter("Supprots Multicast checkbox is checked", "");
	  enterText(ipaddresslocator,  avengeraddzonebeanpage.getIpaddress());
	  customReport.reporter("Ip address is ", getAttribute(ipaddresslocator, "value"));
	  selectValuefromDropDown(devicenamelocator,  avengeraddzonebeanpage.getDevicename());
	  //enterText(availablestraemsearchboxlocator,  avengeraddzonebeanpage.getStreamname());
	  //pause(5000);
	  if(!avengeraddzonebeanpage.getDevicename().contains("AkamaiDevice"))
	  {
	 click(addstreamlocator);
	 click(customizebuttonlocator);
	  }
	  pause(3000);
	  customReport.reporter("Addedzone profile data", "");
	  
	 }
	
	public Map<String, String> getZoneDetails()
	{
		HashMap<String, String> zonedetails = new HashMap<String, String>();
		Map<String,String> zonemap = new HashMap<String, String>();
		zonemap.put("ZoneName", getAttribute(zonenamelocator, "value"));
		zonemap.put("ZoneIP", getAttribute(ipaddresslocator, "value"));
		pause(3000);
		zonemap.put("DeviceName",getCurrentValueFromDropdown(devicenamelocator));
		zonemap.put("supportsmulticast",String.valueOf(isSelected(multicastlocator)));
		return zonemap;

		
		
	}
	public AvengerZonesPage clickCreateButton()
	{
		submit(createbuttonlocator);
		waitForElementPresent(addzonelocator);
		return basePage.avengerZonesPage(driver,  customReport, basePage);
	}
	
	public void clickRemoveDevice()
	{
		click(removedevicelocator);
		customReport.reporter("Remove device is clicked", "");
		/*return getText(removedeviceerrorlocator);*/
	}
	
	public void addAnotherDevice()
	{
		click(addanotherdevicelocator);
		customReport.reporter("Another device is added", "");
	}
	
	public void addNewDevice(AvengerAddZoneBeanPage avengeraddzonebeanpage,int index)
	{
		selectValuefromDropDown_multiple(devicenamelocator,  avengeraddzonebeanpage.getDevicename(), index);
		pause(5000);
		click(addstreamlocator);
	}
	
	public String getDevices(int index)
	{
		
		return getDropDownSelectedOptionMultiple(devicenamelocator,  index);
		
	}
	
	public String uniqueDeviceError()
	{
		return getText(uniquedeviceerrorlocator);
	}
	
	public String uniqueZoneError()
	{	
		submit(createbuttonlocator);
		return getText(uniquezoneerrorlocator);
	}
	
	
	public void addIPAddess(String ipadress)
	{
		enterText(ipaddresslocator,  ipadress);
	}
	
	public String getIPAddressText()
	{
		return getAttribute(ipaddresslocator, "value");
	}
	
	
	public void clickCustomizeDeviceButton()
	{
		By customizedevicebuttonlocator = By.xpath(AvengerAddZonePropertyPage.avengeraddzonepage_customizedevicebuttonlocator.getProperty());
		By updatebuttonlocator=By.xpath("//button[@ng-disabled='form.$invalid']");
		waitForElementEnable(customizedevicebuttonlocator);
		isEnabled(customizedevicebuttonlocator);
		waitForElementEnable(updatebuttonlocator);
		pause(3000);
		clickUsingSwitch(customizedevicebuttonlocator);
		customReport.reporter("Customized button is clicked", "");
	}
	public void addDevice(){
		By addbuttonlocator=By.xpath("//*[@ng-click='addDevice()']");
		
		click(addbuttonlocator);
	}
	
	public void addStreamToAssigned(AvengerAddZoneBeanPage avengeraddzonebeanpage)
	{
		enterText(availablestraemsearchboxlocator,  avengeraddzonebeanpage.getStreamname());
		pause(5000);
		click(addstreamlocator);
		click(customizebuttonlocator);
		pause(3000);
	}
	
	public String getSelectedStream()
	{
		By removestreamlocator = By.xpath(AvengerAddZonePropertyPage.avengeeaddzonepage_removestreamlocator.getProperty());
		return getText(removestreamlocator);
	}
	public void edit_DefaultZone(String deviceName)
	{
		check_Checkbox(supportsmulticast);
		selectValuefromDropDown(devices,  deviceName);
		
	}
	
	public ArrayList<String> get_DefaultZoneData()
	{
		ArrayList<String> zonedata= new ArrayList<String>();
		zonedata.add(String.valueOf(isSelected(supportsmulticast)));
		zonedata.add(getDropDownSelectedOption(devices));
		return zonedata;
	}
	
	public String updateDefaultZoneName(String defaultZoneName)
	{
		  By defaultZonelocator = By.xpath("//label[text()='"+defaultZoneName+"']");
		  return getAttribute(defaultZonelocator, "tagName");
	}
	
	public boolean verify_IpAddressNotPresentInDefaultZone()
	{
		return isDisplayedWithoutException(ipaddresslocator);
	}
	
	public List<String> get_StreamNames()
	{
		By availablestreamlocator = By.xpath("//*[@ng-click='add(stream)']/div/div");
		List<WebElement> allavailablestreamslocator = getAllWebElementValues(availablestreamlocator);
		logger.info("The Size of Webelement is"+allavailablestreamslocator.size());
		List<String> availablestreams = new ArrayList<String>();
		pause(20000);
		for(int i=1;i<=allavailablestreamslocator.size();i++)
		{
			By streamlocator=By.xpath("(//*[@ng-click='add(stream)']/div/div)["+i+"]");
			availablestreams.add(getText(streamlocator));
		}
		logger.info("The available text is"+availablestreams);
	    return availablestreams;
	}
	
	public void addAvailableStreamToAssignedStream(String Streamname)
	{
		click(customizebuttonlocator);
		List<String>streamvalues=get_StreamNames();
		int streamindex =streamvalues.indexOf(Streamname);
		++streamindex;
		By streamlocator = By.xpath("//*[@ng-click='add(stream)']["+streamindex+"]");
		click(streamlocator);
		streamvalues.clear();
		click(customizebuttonlocator);
	}
	

	public void addAvailableToAssignedStream(String streamName)
	{
		By availablestreamlocator = By.xpath("//*[@ng-click='add(stream)']/div/div");
		
		if(!isDisplayedWithoutException(availablestreamlocator))
		{
		click(customizebuttonlocator);
		}
		
		List<WebElement> allavailablestreamslocator = getAllWebElementValues(availablestreamlocator);
		List<String> availablestreams = new ArrayList<String>();
		for (WebElement element : allavailablestreamslocator) {
			logger.info("Stream values are"+element.getText());
			availablestreams.add(element.getText());
		}
		
		int streamindex = availablestreams.indexOf(streamName);
		++streamindex;
		By streamlocator = By.xpath("//*[@ng-click='add(stream)']["+streamindex+"]");
		click(streamlocator);
		/*if(isDisplayed(availablestreamlocator))
		{
            pause(5000);
			click(customizebuttonlocator);
		}	*/
		}
	
	public void moveAllAssignedStreamsToAvailableStreams()
	{
		/*By assignedstreamlocator = By.xpath("//*[@ng-click='remove(stream)']/div/div");
		By availablestreamlocator = By.xpath("//*[@ng-click='add(stream)']/div/div");
		
		if(!isDisplayed(availablestreamlocator))
		{
		click(customizebuttonlocator);
		}
		List<WebElement> allavailablestreamslocator = getAllWebElementValues(assignedstreamlocator);
		
		if(allavailablestreamslocator.size()>0)
		{
		for (WebElement element : allavailablestreamslocator) {
			
			element.click();
		}
		
		}
		
		if(isDisplayed(availablestreamlocator))
		{
		click(customizebuttonlocator);
		}
		*/
		By assignedstreamlocator = By.xpath("//*[@ng-click='remove(stream)']/div/div");

		click(customizebuttonlocator);
		List<WebElement> allavailablestreamslocator = getAllWebElementValues(assignedstreamlocator);
		int elementslist=allavailablestreamslocator.size();
		logger.info("the size of the element is"+elementslist);
		if(allavailablestreamslocator.size()>0)
		{
			for(int i=1;i<=elementslist;i++)
			{
				By assignedstream=By.xpath("//*[@ng-click='remove(stream)'][1]");
				click(assignedstream);
			}
		}
		click(customizebuttonlocator);
		
	}
	
	public ArrayList<String> getDestinationDropDownValues()
	{
		return getDropdownValue(devicenamelocator);
	}
	
	public String getInactiveDeviceErrorText()
	{
		By inactivedeviceerrortext = By.xpath(" //span[contains(@class,'warning')]");
		return getText(inactivedeviceerrortext);
	}
	
	public boolean verifyInactiveDeviceErrorText()
	{
		By inactivedeviceerrortext = By.xpath(" //span[contains(@class,'warning')]");
		return isDisplayed(inactivedeviceerrortext);
	}

	public String verifyAvailableSteams() {
		By streamlocator=By.xpath("//div[contains(@ng-repeat, 'availableOptions')]");
		//div[contains(@ng-repeat, 'selectedOptions')]
		return getText(streamlocator);
	}
	public String verifyselectedStream(){
		
		By selectedstreamlocator=By.xpath("//*[contains(@class,'hover-wrap')]");
		return getText(selectedstreamlocator);
	}
	
	public String verifyLabel(String labelName)
	{
		By labelLocator = By.xpath("//*[contains(text(),'"+labelName+"')]");
	    return getText(labelLocator);
	}

	public Map<String, String> verify_labels() {
		By zonesbuttonlocator=By.xpath("//div[@class='combined-toolbar responsive']/div/a");
		By removedestination=By.xpath("//button[contains(@ng-click,'removeDevice')]");
		By addanother=By.xpath("//button[@ng-click='addDevice()']");
		Map<String, String> labelsmap=new HashMap<String, String>();
		labelsmap.put("zones", getText(zonesbuttonlocator));
		labelsmap.put("removedestination", getText(removedestination));
		labelsmap.put("addanother", getText(addanother));
		return labelsmap;
	}

	
}
