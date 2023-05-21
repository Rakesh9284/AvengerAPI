package com.vbrick.avenger.pageobjects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerTranscodingGlobalSettingsPropertyPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerTranscodingGlobalSettingsPage extends WebElements{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private static Logger logger = Logger.getLogger(AvengerTranscodingGlobalSettingsPage.class);
  
By addvideodefaultvaluelocator=By.xpath(AvengerTranscodingGlobalSettingsPropertyPage.avengertranscodingglobalsettingspage_addvideodefaultvaluelocator.getProperty());
By recorddefaultvaluelocator=By.xpath(AvengerTranscodingGlobalSettingsPropertyPage.avengertranscodingglobalsettingspage_recorddefaultvaluelocator.getProperty());
By schedulerecorddefaultvaluelocator=By.xpath(AvengerTranscodingGlobalSettingsPropertyPage.avengertranscodingglobalsettingspage_schedulerecorddefaultvaluelocator.getProperty());
By webcastrecorddefaultvaluelocator=By.xpath(AvengerTranscodingGlobalSettingsPropertyPage.avengertranscodingglobalsettingspage_webcastrecorddefaultvaluelocator.getProperty());
By savechangesbuttonlocator=By.xpath(AvengerTranscodingGlobalSettingsPropertyPage.avengertranscodingglobalsettingspage_savechangesbuttonlocator.getProperty());
By addvideolabellocator=By.name(AvengerTranscodingGlobalSettingsPropertyPage.avengertranscodingglobalsettingspage_addvideolabellocator.getProperty());
By recordlabellocator=By.name(AvengerTranscodingGlobalSettingsPropertyPage.avengertranscodingglobalsettingspage_recordlabellocator.getProperty());
By schedulerecordlabellocator=By.name(AvengerTranscodingGlobalSettingsPropertyPage.avengertranscodingglobalsettingspage_schedulerecordlabellocator.getProperty());
By webcastrecordlabellocator=By.name(AvengerTranscodingGlobalSettingsPropertyPage.avengertranscodingglobalsettingspage_webcastrecordlabellocator.getProperty());
By addvideoeditbuttonlocator=By.xpath(AvengerTranscodingGlobalSettingsPropertyPage.avengertranscodingglobalsettingspage_addvideoeditbuttonlocator.getProperty());
By allassignedpresetslocatorforaddvideo=By.xpath(AvengerTranscodingGlobalSettingsPropertyPage.avengertranscodingglobalsettingspage_assignedpresetslocatorforaddvideo.getProperty());
By allavailablepresetslocator=By.xpath(AvengerTranscodingGlobalSettingsPropertyPage.avengertranscodingglobalsettingspage_allavailablepresetslocator.getProperty());
By recordeditbuttonlocator=By.xpath(AvengerTranscodingGlobalSettingsPropertyPage.avengertranscodingglobalsettingspage_recordeditbuttonlocator.getProperty());
By scheduledrecordeditbuttonlocator=By.xpath(AvengerTranscodingGlobalSettingsPropertyPage.avengertranscodingglobalsettingspage_schedulerecordeditbuttonlocator.getProperty());
By webcastrecordeditbuttonlocator=By.xpath(AvengerTranscodingGlobalSettingsPropertyPage.avengertranscodingglobalsettingspage_webcastrecordeditbuttonlocator.getProperty());
By allassignedpresetslocatorforrecord=By.xpath(AvengerTranscodingGlobalSettingsPropertyPage.avengertranscodingglobalsettingspage_assignedpresetslocatorforrecord.getProperty());
By allavailablepresetslocatorforaddvideo=By.xpath(AvengerTranscodingGlobalSettingsPropertyPage.avengertranscodingglobalsettingspage_allavailablepresetslocatorforaddvideo.getProperty());
By allavailablepresetslocatorforrecord=By.xpath(AvengerTranscodingGlobalSettingsPropertyPage.avengertranscodingglobalsettingspage_allavailablepresetslocatorforrecord.getProperty());
   
private WebDriver driver;
	
	private CustomReport customReport;
	private BasePage basePage;
	
	public AvengerTranscodingGlobalSettingsPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
		// TODO Auto-generated constructor stub
	}
	
	
	public String getDefaultValueforAddVideo()
	{
		customReport.reporter("Default Value for Add Video is",getText(addvideodefaultvaluelocator));
		return getText(addvideodefaultvaluelocator);
	}
	
	public ArrayList<String> get_DefaultSettingsForAddVideo()
	{
		getAllWebElementValues(addvideodefaultvaluelocator);
		ArrayList<String> defaultsettingsforaddvideo= new ArrayList<String>();
		
		for (WebElement element : getAllWebElementValues(addvideodefaultvaluelocator)) {
			defaultsettingsforaddvideo.add(element.getText());
			logger.info("All values are"+element.getText());
		}
		defaultsettingsforaddvideo.removeAll(Arrays.asList(null,""));
		Collections.sort(defaultsettingsforaddvideo);
		return defaultsettingsforaddvideo;
	}
	
	public String getDefaultValueforRecord()
	{
		customReport.reporter("Default Value for Record  is",getText(recorddefaultvaluelocator));
		return getText(recorddefaultvaluelocator);
	}

	public String getDefaultValueforScheduleRecord()
	{
		customReport.reporter("Default Value for Schedule Record Video is",getText(schedulerecorddefaultvaluelocator));
		return getText(schedulerecorddefaultvaluelocator);
	}
	
	public String getDefaultValueforWebcastRecord()
	{
		customReport.reporter("Default Value for Webcast Record  is",getText(webcastrecorddefaultvaluelocator));
		return getText(webcastrecorddefaultvaluelocator);
	}
	
	public boolean verify_SaveChangesButtonExists()
	{
		if(isEnabled(savechangesbuttonlocator)==true)
			customReport.reporter("Save Changes Button Exists in Transcoding Settings Page","");
		return isEnabled(savechangesbuttonlocator);
	}
	
	public boolean get_addVideoLabelText(String containsvalue)
	{
		logger.info("Add Video Label text is"+getText(addvideolabellocator));
		return getText(addvideolabellocator).contains(containsvalue);
	}
	
	public boolean get_recordlabelText(String containsvalue)
	{
		logger.info("Record Label text is"+getText(recordlabellocator));
		return getText(recordlabellocator).contains(containsvalue);
	}
	public boolean get_schedulerecordlabelText(String containsvalue)
	{
		logger.info(" Schedule Record Label text is"+getText(recordlabellocator));
		return getText(schedulerecordlabellocator).contains(containsvalue);
	}
	public boolean get_webcastrecordlabelText(String containsvalue)
	{
		logger.info("WebCase Record Label text is"+getText(recordlabellocator));
		return getText(webcastrecordlabellocator).contains(containsvalue);
	}
	
	public void click_AddVideoEditButton()
	{
		click(addvideoeditbuttonlocator);
		waitForElementPresent(allavailablepresetslocator);
		customReport.reporter("Add Video Edit Button is Clicked","");
	}
	
	public boolean verify_AddVideoEditButton()
	{
		return locatorsVisibilityAsPerRoles(addvideoeditbuttonlocator);
	}
	
	public void click_recordEditButton()
	{
		click(recordeditbuttonlocator);
		customReport.reporter("Record Edit Button is Clicked","");
	}
	public void click_scheduledRecordEditButton()
	{
		click(scheduledrecordeditbuttonlocator);
		customReport.reporter("Schedule Record Edit Button is Clicked","");
	}
	public void click_webcastRecordEditButton()
	{
		click(webcastrecordeditbuttonlocator);
		customReport.reporter("Webcast Record Edit Button is Clicked","");
	}
	
	public void click_SaveChangesButton()
	{
		click(savechangesbuttonlocator);
		pause(5000);
		customReport.reporter("Save Changes Button is Clicked","");
	}
	public ArrayList<String> get_allAssignedpresetsforAddVideo()
	{
		List<WebElement> allwebelements =getAllWebElementValues(allassignedpresetslocatorforaddvideo); 
		ArrayList<String> assignedvalues = new ArrayList<String>();
		   logger.info("ArrayList is sorted");
		   for (WebElement temp : allwebelements) {
				assignedvalues.add(temp.getText());
			}
			Collections.sort(assignedvalues);
			assignedvalues.removeAll(Arrays.asList(null,""));
		return  assignedvalues;
	}
	public ArrayList<String> get_allAssignedpresetsforRecord()
	{
		List<WebElement> allwebelements =getAllWebElementValues(allassignedpresetslocatorforrecord); 
		ArrayList<String> assignedvalues = new ArrayList<String>();
		   logger.info("ArrayList is sorted");
		   for (WebElement temp : allwebelements) {
				assignedvalues.add(temp.getText());
			}
			Collections.sort(assignedvalues);
			assignedvalues.removeAll(Arrays.asList(null,""));
		return  assignedvalues;
	}

	public ArrayList<String> get_allAvailablepresetsRecord()
	{
		List<WebElement> allwebelements =getAllWebElementValues(allavailablepresetslocatorforrecord); 
		ArrayList<String> assignedvalues = new ArrayList<String>();
		   logger.info("ArrayList is sorted");
		   for (WebElement temp : allwebelements) {
			if(temp.getText()!="")
			   assignedvalues.add(temp.getText());
			}
		    assignedvalues.removeAll(Arrays.asList(null,""));
			Collections.sort(assignedvalues);
			for (String string : assignedvalues) {
				logger.info("Array values are"+string);
			}
		return  assignedvalues;
	}

	
	public void click_assignedPreset(String assignedpreset)
	{
	
		   for (WebElement temp : getAllWebElementValues(allassignedpresetslocatorforaddvideo)) {
			   logger.info("Size of list is"+getAllWebElementValues(allassignedpresetslocatorforaddvideo).size());
				logger.info("The Presets are"+temp.getText());
			   if(temp.getText().equals(assignedpreset))
				{
				   logger.info("Temp value is"+temp.getText());
				   pause(5000);
				   temp.click();
				   customReport.reporter(assignedpreset, "is Clicked");
				}
			   
			   else
			   {
				   logger.info("Element not visible");
			   }
			}
		   
		 }
	
	
	public void click_availablePreset(String availablepreset)
	{
		List<WebElement> allwebelements =getAllWebElementValues(allavailablepresetslocator); 
		   for (WebElement temp : allwebelements) {
			   logger.info("The values of preset are "+temp.getText());
			  if(temp.getText().equals(availablepreset))
				{
				 logger.info("Clicking on the Available Preset"+availablepreset);
				 pause(5000); 
				 temp.click();
				  pause(5000);
	             customReport.reporter(availablepreset, "is Clicked");
	             break;
				}
			}
	 }
	
	
	
	 public boolean verify_presetInAssignedpresets(String checkassignedpreset)
	 {
		 if(get_allAssignedpresetsforAddVideo().contains(checkassignedpreset)==true)
		 	 customReport.reporter(checkassignedpreset,"is Available in Assigned Presets");
		 else
			 customReport.reporter(checkassignedpreset,"is not Available in Assigned Presets");
		 return get_allAssignedpresetsforAddVideo().contains(checkassignedpreset);
		 
	 }
	 
	 public boolean verify_presetInAvailablePresets(String checkassignedpreset)
	 {
	  
		 return get_allAvailablepresetsRecord().contains(checkassignedpreset);
		 
	 }
	
	public String verify_Label(String labelname)
	{
		By labellocator=By.xpath("//*[text()='"+labelname+"']");
		return getText(labellocator);
	}
	
	public ArrayList<String> get_allAvailablepresetsAddVideo()
	{
		List<WebElement> allwebelements =getAllWebElementValues(allavailablepresetslocatorforaddvideo); 
		ArrayList<String> assignedvalues = new ArrayList<String>();
		   logger.info("ArrayList is sorted");
		   for (WebElement temp : allwebelements) {
			if(temp.getText()!="")
			   assignedvalues.add(temp.getText());
			}
		    assignedvalues.removeAll(Arrays.asList(null,""));
			Collections.sort(assignedvalues);
			for (String string : assignedvalues) {
				logger.info("Array values are"+string);
			}
		return  assignedvalues;
	}

	
}
	
	