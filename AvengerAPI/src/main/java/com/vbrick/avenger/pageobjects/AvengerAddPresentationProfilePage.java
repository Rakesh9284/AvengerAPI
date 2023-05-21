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
import com.vbrick.avenger.ObjProperty.AvengerAddPresentationProfilePropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerPresentationProfilesPropertyPage;
import com.vbrick.avenger.dao.AddPresentationprofileBeanPage;
import com.vbrick.avenger.dao.AvengerEventDetailsBeanPage;
import com.vbrick.avenger.funUtil.DateTime;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerAddPresentationProfilePage extends WebElements {

	By namelocator = By.xpath(AvengerAddPresentationProfilePropertyPage.avengeraddpresentationprofilepage_namelocator.getProperty());
	By descriptionlocator = By.xpath(AvengerAddPresentationProfilePropertyPage.avengeraddpresentationprofilepage_descriptionlocator.getProperty());
	By videosourcelocator = By.name(AvengerAddPresentationProfilePropertyPage.avengeraddpresentationprofilepage_videosourcelocator.getProperty());
	By removedestinationdevicelocator = By.xpath(AvengerAddPresentationProfilePropertyPage.avengeraddpresentationprofilepage_removedestinationdevicelocator.getProperty());
	By addanotherdestinationlocator = By.xpath(AvengerAddPresentationProfilePropertyPage.avengeraddpresentationprofilepage_addanotherdestinationlocator.getProperty());
	By createbuttonlocator = By.xpath(AvengerAddPresentationProfilePropertyPage.avengeraddpresentationprofilepage_createbuttonlocator.getProperty());
	By AddaPresentationprofilelocator = By.xpath(AvengerPresentationProfilesPropertyPage.avengerpresentationprofilespage_AddaPresentationprofilelocator.getProperty());
	By updatebuttonlocator = By.xpath(AvengerAddPresentationProfilePropertyPage.avengerpresentationprofilespage_updatebuttonlocator.getProperty());
	By removedeviceerrorlocator = By.xpath(AvengerAddPresentationProfilePropertyPage.avengerpresentationprofilespage_removedeviceerrorlocator.getProperty());
	By destinationdevicelocator = By.name(AvengerAddPresentationProfilePropertyPage.avengerpresentationprofilespage_destinationdevicelocator.getProperty());
	By multipledmeerrorlocator = By.xpath(AvengerAddPresentationProfilePropertyPage.avengerpresentationprofilespage_multipledmeerrorlocator.getProperty());
	By availablestraemsearchboxlocator = By.xpath(AvengerAddPresentationProfilePropertyPage.avengerpresentationprofilespage_availablestreamsearchlocator.getProperty());
	By assignedstreamsearchboxlocator = By.xpath(AvengerAddPresentationProfilePropertyPage.avengerpresentationprofilespage_selectedstreamsearchlocator.getProperty());
	By addstreamlocator = By.xpath(AvengerAddPresentationProfilePropertyPage.avengerpresentationprofilespage_addstreamlocator.getProperty());
	By removestreamlocator = By.xpath(AvengerAddPresentationProfilePropertyPage.avengerpresentationprofilespage_removestreamlocator.getProperty());
	By customizebuttonlocator = By.xpath(AvengerAddPresentationProfilePropertyPage.avengerpresentationprofilespage_customizedevicebuttonlocator.getProperty());
	
	
	DateTime dateTime = new DateTime();
	
	WebDriver driver;
	CustomReport customReport;
	List<String> list ;
	Map<String, String> map;
	private BasePage basePage;

	
	
	private static Logger logger = Logger.getLogger(AvengerAddPresentationProfilePage.class);
	
	public AvengerAddPresentationProfilePage(WebDriver driver,  CustomReport customReport,BasePage basePage)
	{
		super(driver);
		this.driver = driver;
		
		this.customReport = customReport;
		this.basePage = basePage;
		
	}

	public String check_PresentationProfileText(String presentationprofilename)
	{
        By presentationprofiletextlocator=By.xpath("//*[text()='"+presentationprofilename+"']");
		waitForElementPresent(presentationprofiletextlocator);
		return  getText(presentationprofiletextlocator);
		
	}

	public void addPresentationProfile(AddPresentationprofileBeanPage addpresentationprofilebeanpage)
	{
		enterText(namelocator, addpresentationprofilebeanpage.getName());
		logger.info("Presentation profile name is"+addpresentationprofilebeanpage.getName());
		enterText(descriptionlocator, addpresentationprofilebeanpage.getDescription());
	    By statusvalue=By.xpath("//button[contains(text(),'"+addpresentationprofilebeanpage.getStatus()+"')]");
	    click(statusvalue);
	    logger.info("video source"+addpresentationprofilebeanpage.getVideosource());
		selectValuefromDropDown(videosourcelocator, addpresentationprofilebeanpage.getVideosource());
		selectValuefromDropDown(destinationdevicelocator, addpresentationprofilebeanpage.getDestinationdevicename());
		pause(3000);
		click(addstreamlocator);
		pause(3000);
		customReport.reporter("Added presentation profile data", "");
	}
	
	public Map<String, String> getPresentationProfileData()
	{
	  Map<String, String> presentationprofiledata = new HashMap<String, String>();
	  presentationprofiledata.put("presentationname",getAttribute(namelocator, "value"));
	  presentationprofiledata.put("presentationdescription",getAttribute(descriptionlocator, "value"));
	  presentationprofiledata.put("videosource",getCurrentValueFromDropdown(videosourcelocator));
	  presentationprofiledata.put("destinationdevice",getCurrentValueFromDropdown(destinationdevicelocator));
	   return presentationprofiledata; 
	}
	
	public AvengerPresentationProfilesPage click_createButton()
	{
		submit(createbuttonlocator);		
		customReport.reporter("Create button is clicked", "");
	    waitForElementPresent(AddaPresentationprofilelocator);
	    pause(3000);
	    return basePage.avengerPresentationProfilesPage(driver, customReport, basePage);
	}
	
	public boolean check_CreateButtonEnabled()
	{
		return isEnabled(createbuttonlocator);
	}
	
	public String clickRemoveDeviceButton()
	{
		click(removedestinationdevicelocator);
		return getText(removedeviceerrorlocator);
	}
	
	
	 public String clickRemoveDeviceButton(String value)
	{
		By removedeviceerror=By.xpath("//*[contains(text(),'"+value+"')]");
		click(removedestinationdevicelocator);
		return getText(removedeviceerror);
	}
	
	
	public void add_destinaton()
	{
		click(addanotherdestinationlocator);
		customReport.reporter("Added a destination", "");
	}
	
	public void selectNewDestinationDevice(AddPresentationprofileBeanPage addpresentationprofilebeanpage,int index)
	{
		
		selectValuefromDropDown_multiple(destinationdevicelocator, addpresentationprofilebeanpage.getDestinationdevicename(), index);
		customReport.reporter("Dropdown selected value", getDropDownSelectedOptionMultiple(destinationdevicelocator, index));
		pause(5000);
		click(addstreamlocator);
		pause(3000);
	}
	
	
	public String getActualDestinationDevices(int index)
	{
		return getDropDownSelectedOptionMultiple(destinationdevicelocator, index);
	}
	
		
	public String check_MultipleDme()
	{
		
		return getText(multipledmeerrorlocator);
		
	}
	
	public void edit_PresentationProfileDetails(AddPresentationprofileBeanPage addpresentationprofilebeanpage)
	{
		
		addpresentationprofilebeanpage.setName("Presentation"+dateTime.yyyyMMDDHHmmssTime());
		enterText(namelocator, addpresentationprofilebeanpage.getName());
		customReport.reporter("Presentation name changed", getAttribute(namelocator, "value"));
		addpresentationprofilebeanpage.setDescription("Changed description"+dateTime.yyyyMMDDHHmmssTime());
		enterText(descriptionlocator, addpresentationprofilebeanpage.getDescription());
		customReport.reporter("Changed description", getAttribute(descriptionlocator, "value"));
	}
	
	public AvengerPresentationProfilesPage click_updateButton()
	{
		submit(updatebuttonlocator);		
		customReport.reporter("Create button is clicked", "");
		return basePage.avengerPresentationProfilesPage(driver, customReport, basePage);
		
	}
	
	public Map<String, String> check_editPresentationProfileDetails()
	{
		map=new HashMap<String, String>();
		map.put("PPName",getAttribute(namelocator, "value"));
		map.put("PPDescription", getAttribute(descriptionlocator, "value"));
		map.put("DestinationDevice", getDropDownSelectedOption(destinationdevicelocator));
		return map;
	}
	
	
	public boolean isElementDisplayed()
	{
		return isDisplayedWithoutException(videosourcelocator);
	}
	
	public void changePresentationProfileData(AddPresentationprofileBeanPage addpresentationprofilebeanpage)
	{
		By statusvalue=By.xpath("//button[contains(text(),'"+addpresentationprofilebeanpage.getStatus()+"')]");
	    click(statusvalue);
	    click(createbuttonlocator);
	    pause(5000);
	    
	}
	
	public Map<String,String> get_ListforInactivateError(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
	{
		map = new HashMap<String, String>();
		By errortextlocator = By.xpath("//*[@ng-hide='isDeleting']");
		By titlelocator = By.xpath("//*[contains(text(),'"+avengereventdetailsbeanpage.getTitle()+"')]");
		By hostlocator = By.xpath("//*[contains(text(),'"+avengereventdetailsbeanpage.getHost()+"')]");
		
		
		map.put("EventTitle", getText(titlelocator));
		map.put("EventHost", getText(hostlocator));
		map.put("ErrorText", getText(errortextlocator));
		return map;	
	}
	
	public List<String> get_StreamNames()
	{
		By availablestreamlocator = By.xpath("//*[@ng-click='add(stream)']/div/div");
		List<WebElement> allavailablestreamslocator = getAllWebElementValues(availablestreamlocator);
		logger.info("The Size of Webelement is"+allavailablestreamslocator.size());
		List<String> availablestreams = new ArrayList<String>();
		pause(5000);
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
		logger.info("The Stream index before add is"+streamindex);
		streamindex=streamindex+1;
		logger.info("The Stream index after add is"+streamindex);
		By streamlocator = By.xpath("//*[@ng-click='add(stream)']["+streamindex+"]");
		click(streamlocator);
		streamvalues.clear();
		click(customizebuttonlocator);
	}
	
	public void addAvailableToAssignedStream(String streamName)
	{
		click(customizebuttonlocator);
		By availablestreamlocator = By.xpath("//*[@ng-click='add(stream)']/div/div");
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
	}
	
	public void moveAllAssignedStreamsToAvailableStreams()
	{
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
			pause(5000);
			click(assignedstream);
		}
		}
		click(customizebuttonlocator);
	}
	
	
	public void moveAllAvailableStreamsToAssignedStreams()
	{
		By availablestreamlocator = By.xpath("//*[@ng-click='add(stream)']/div/div");
		
		click(customizebuttonlocator);
		
		List<WebElement> allavailablestreamslocator = getAllWebElementValues(availablestreamlocator);
		
		if(allavailablestreamslocator.size()>0)
		{
		for (WebElement element : allavailablestreamslocator) {
			
			element.click();
		}
		
		}
	}
	
	public ArrayList<String> getDestinationDropDownValues()
	{
		return getDropdownValue(destinationdevicelocator);
	}
	
	public ArrayList<String> getVideoSourceDropDownValues()
	{
		return getDropdownValue(videosourcelocator);
	}
	
	public String getInactiveDeviceErrorText()
	{
		By inactivedeviceerrortext = By.xpath("//*[@name='videoSource']/../../../../../..//span[contains(@class,'warning')]/..");
		return getText(inactivedeviceerrortext);
	}
	
	public boolean verifyInactiveDeviceErrorText()
	{
		By inactivedeviceerrortext = By.xpath("//*[@name='videoSource']/../../../../../..//span[contains(@class,'warning')]/..");
		return isDisplayedWithoutException(inactivedeviceerrortext);
	}

	public Map<String, String> verify_Labels() {
		
		By presentationprofileslocator=By.xpath("//div[@class='combined-toolbar responsive']/div/a");
		By removedestination=By.xpath("//button[@ng-click='removeDestination($index)']");
		Map<String, String> labelsmap=new HashMap<String, String>();
		labelsmap.put("presentationprofiles", getText(presentationprofileslocator));
		labelsmap.put("removedestination",getText(removedestination));	
		
		return labelsmap;
	}
}
