package com.vbrick.avenger.pageobjects;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerAddNewCustomDevicePropertyPage;
import com.vbrick.avenger.dao.AddCustomDeviceBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerAddCustomDevicePage extends WebElements{

	private WebDriver driver;
	
	CustomReport customReport;
	private BasePage basePage;
	private static Logger logger = Logger.getLogger(AvengerAddCustomDevicePage.class);

	By devicenamelocator = By.xpath(AvengerAddNewCustomDevicePropertyPage.avengeraddnewcustomdevicepage_devicenamelocator.getProperty());
	By ipaddresslocator = By.name(AvengerAddNewCustomDevicePropertyPage.avengeraddnewcustomdevicepage_ipaddresslocator.getProperty());
	By videosourcelocator = By.name(AvengerAddNewCustomDevicePropertyPage.avengeraddnewcustomdevicepage_videosourcelocator.getProperty());
	By viewingdestinationlocator = By.name(AvengerAddNewCustomDevicePropertyPage.avengeraddnewcustomdevicepage_viewingdestinationlocator.getProperty());
	By streamnamelocator = By.name(AvengerAddNewCustomDevicePropertyPage.avengeraddnewcustomdevicepage_streamnamelocator.getProperty());
	By streamurllocator = By.xpath(AvengerAddNewCustomDevicePropertyPage.avengeraddnewcustomdevicepage_streamurllocator.getProperty());
	By encodingtypelocator = By.xpath(AvengerAddNewCustomDevicePropertyPage.avengeraddnewcustomdevicepage_streamencodingtypelocator.getProperty());
	By ismulticastlocator = By.xpath(AvengerAddNewCustomDevicePropertyPage.avengeraddnewcustomdevicepage_streamismulticastlocator.getProperty());
	By createbuttonlocator = By.xpath(AvengerAddNewCustomDevicePropertyPage.avengeraddnewcustomdevicepage_createbuttonlocator.getProperty());
	By addurllocator=By.xpath(AvengerAddNewCustomDevicePropertyPage.avengeraddnewcustomdevicepage_addurl.getProperty());
	By deviceNamemadatory=By.xpath(AvengerAddNewCustomDevicePropertyPage.deviceNamemadatory.getProperty());
	By isDestinationmandatory=By.xpath(AvengerAddNewCustomDevicePropertyPage.isDestinationmandatory.getProperty());
	By ipaddressmandatory=By.xpath(AvengerAddNewCustomDevicePropertyPage.ipaddressmandatory.getProperty());
	By removedevice = By.xpath(AvengerAddNewCustomDevicePropertyPage.removedevice.getProperty());
	By alldevicesbuttonlocator=By.xpath(AvengerAddNewCustomDevicePropertyPage.alldevicesbuttonlocator.getProperty());
	By  videostreamsource=By.xpath(AvengerAddNewCustomDevicePropertyPage.videostreamsource.getProperty());
	By viewingdestinion=By.xpath(AvengerAddNewCustomDevicePropertyPage.viewingdestinion.getProperty());
	By removestream=By.xpath(AvengerAddNewCustomDevicePropertyPage.removestream.getProperty());
	By getstreamname=By.xpath(AvengerAddNewCustomDevicePropertyPage.removestream.getProperty());
    
	public AvengerAddCustomDevicePage(WebDriver driver,CustomReport customReport, BasePage basePage) {
		super(driver);
		this.driver = driver;
		this.customReport= customReport;
		this.basePage=basePage;
	}
	
	public Map<String,String> validatedevicenamelocator(AddCustomDeviceBeanPage addcustomdevicebeanpage)
	{
		customReport.reporter("In Custom Device Page", "");
		By statuslocator = By.xpath("//button[contains(text(),'"+addcustomdevicebeanpage.getStatus()+"')]");
		Map<String, String> requiredVal = new HashMap<String, String>();
		customReport.reporter("View Destination checkbox clicked", "");
		check_Checkbox(viewingdestinationlocator);
		enterText(devicenamelocator,addcustomdevicebeanpage.getDevicename());
		customReport.reporter("Text entered in Device name", "");
		clearWebElementTextusingbackspace(devicenamelocator,addcustomdevicebeanpage.getDevicename().length());
		customReport.reporter("cleared Text entered in Device name", "");
		requiredVal.put("DeviceName", getText(deviceNamemadatory));
		click(statuslocator);
		enterText(ipaddresslocator, addcustomdevicebeanpage.getIpaddress());
		customReport.reporter("Text entered in IP Address", "");
		clearWebElementTextusingbackspace(ipaddresslocator,addcustomdevicebeanpage.getDevicename().length());
		customReport.reporter("cleared Text entered in IP Address", "");
		requiredVal.put("IpAddress", getText(ipaddressmandatory));
		click(viewingdestinationlocator);
		customReport.reporter("View Destination checkbox unchecked", "");

		requiredVal.put("Destination", getText(isDestinationmandatory));
		return requiredVal;
	}

	public void createNewCustomDevice(AddCustomDeviceBeanPage addcustomdevicebeanpage)
	{
		pause(5000);
		enterText(devicenamelocator, addcustomdevicebeanpage.getDevicename());
		customReport.reporter("Entered device name", getAttribute(devicenamelocator, "value"));
		By statuslocator = By.xpath("//button[contains(text(),'"+addcustomdevicebeanpage.getStatus()+"')]");
		click(statuslocator);
		enterText(ipaddresslocator, addcustomdevicebeanpage.getIpaddress());
		customReport.reporter("Entered ip address", getAttribute(ipaddresslocator, "value"));
		check_Checkbox(videosourcelocator);
		check_Checkbox(viewingdestinationlocator);
		waitForElementPresent(streamnamelocator);
		enterText(streamnamelocator, addcustomdevicebeanpage.getStreamname());
		customReport.reporter("Entered stream name", getAttribute(streamnamelocator, "value"));
		enterText(streamurllocator, addcustomdevicebeanpage.getStreamurl());
		customReport.reporter("Entered stream url", getAttribute(streamurllocator, "value"));
		selectValuefromDropDown(encodingtypelocator, addcustomdevicebeanpage.getStreamencodingtype());
		customReport.reporter("Selected encoding type is", getDropDownSelectedOption(encodingtypelocator));
		check_Checkbox(ismulticastlocator);
	}
	
	public void checkcheckboxDestination(){
		
		check_Checkbox(viewingdestinationlocator);
	}
	
	
	public AvengerDevicesPage click_CreateButton(AddCustomDeviceBeanPage addcustomdevicebeanpage)
	{
		submit(createbuttonlocator);
		logger.info("Clicked on create custom device");
		By createdcustomdevicelocator = By.xpath("//*[contains(text(),'"+addcustomdevicebeanpage.getDevicename()+"')]");
		pause(5000);
		return basePage.avengerDevicesPage(driver, customReport, basePage);
	}

	public Map<String,String> getAllCustomDeviceData()
	{
		Map<String,String> customdevicedata= new HashMap<String, String>();
		pause(1000);
		customdevicedata.put("devicename",getAttribute(devicenamelocator,"value"));
		customdevicedata.put("ipAddress",getAttribute(ipaddresslocator,"value"));
		customdevicedata.put("streamName",getAttribute(getstreamname,"value"));
		customdevicedata.put("streamUrl",getAttribute(streamurllocator,"value"));
		logger.info("selected drop down value is"+getDropDownSelectedOption(encodingtypelocator));
		customdevicedata.put("encodingType",getDropDownSelectedOption(encodingtypelocator));
		return customdevicedata;
	}

	public void editCreatedCustomDevice(AddCustomDeviceBeanPage addcustomdevicebeanpage)
	{
		
		enterText(devicenamelocator, addcustomdevicebeanpage.getDevicename());
		customReport.reporter("Entered device name", getAttribute(devicenamelocator, "value"));
		enterText(ipaddresslocator, addcustomdevicebeanpage.getIpaddress());
		customReport.reporter("Entered ip address", getAttribute(ipaddresslocator, "value"));
		customReport.reporter("Entered stream name", getAttribute(getstreamname, "value"));
		enterText(streamurllocator, addcustomdevicebeanpage.getStreamurl());
		customReport.reporter("Entered stream url", getAttribute(streamurllocator, "value"));
		selectValuefromDropDown(encodingtypelocator, addcustomdevicebeanpage.getStreamencodingtype());
		customReport.reporter("Selected encoding type is", getDropDownSelectedOption(encodingtypelocator));
		check_Checkbox(ismulticastlocator);
		logger.info("Checked check box for multicast check box");

	}

	public boolean checkVideoSourceEnabled()
	{
		return isEnabled(videosourcelocator);
	}

	public boolean checkVideoDestinationeEnabled()
	{
		return isEnabled(viewingdestinationlocator);
	}
	public void clickAddURL()
	{
		click(addurllocator);
		customReport.reporter("Add url button is clicked", "");
	}
	public void addmultipleURLtoCustomDevice(AddCustomDeviceBeanPage addcustomdevicebeanpage,int index){

		enterTextMultiple(streamnamelocator, addcustomdevicebeanpage.getStreamname(), index);
		enterTextMultiple(streamurllocator, addcustomdevicebeanpage.getStreamurl(), index);
		selectValuefromDropDown_multiple(encodingtypelocator,addcustomdevicebeanpage.getStreamencodingtype(), index);
		customReport.reporter("Entered valid data for adding multiple url", "");
	}
	public Map<String,String> getDeviceStreamInformation(int index)
	{
		Map<String, String>    dmeurlinfo= new HashMap<String, String>();
		dmeurlinfo.put("streamname", getAttributeMultiple(streamnamelocator, "value", index));
		dmeurlinfo.put("streamurl", getAttributeMultiple(streamurllocator, "value", index));
		dmeurlinfo.put("encodingtype", getDropDownSelectedOptionMultiple(encodingtypelocator, index));

		return dmeurlinfo;
	} 

	public void activateOrInactivateDevice(AddCustomDeviceBeanPage addcustomdevicebeanpage)
	{
		By statuslocator = By.xpath("//button[contains(text(),'"+addcustomdevicebeanpage.getStatus()+"')]");
		click(statuslocator);
	}

	  public int clickRemoveDevice()
      {
    	  click(removedevice);
    	  pause(2000);
    	  return elements(streamnamelocator);
      }

	  public String verify_label(String label)
	  {
		  By labellocator =By.xpath("//*[contains(text(),'"+label+"')]");
	  return  getText(labellocator);
	  }

	  public Map<String, String> getAllLabels() {
			
			Map<String, String> labelsmap=new HashMap<String, String>();
			labelsmap.put("addurl", getText(addurllocator));
			labelsmap.put("alldevices", getText(alldevicesbuttonlocator));
			labelsmap.put("videostream", getText(videostreamsource));
			labelsmap.put("videodestinion", getText(viewingdestinion)); 	
			labelsmap.put("removestream", getText(removestream));
			
			return labelsmap;
	  }
}
