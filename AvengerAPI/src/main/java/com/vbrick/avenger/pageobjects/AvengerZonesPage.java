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
import com.vbrick.avenger.ObjProperty.AvengerCategoriesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerZonesPropertyPage;
import com.vbrick.avenger.dao.AvengerAddZoneBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerZonesPage extends WebElements{

	private WebDriver driver;
	private CustomReport customReport;
	
	private BasePage basePage;
	
	
	By addzonelocator = By.xpath(AvengerZonesPropertyPage.avengerzonespage_addzonebuttonlocator.getProperty());
	By zonenamelocator = By.xpath(AvengerAddZonePropertyPage.avengeraddzonepage_zonenamelocator.getProperty());
	
	private static Logger logger = Logger.getLogger(AvengerZonesPage.class);
	
	public AvengerZonesPage(WebDriver driver,  
			CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport = customReport;
		this.basePage=basePage;
	}

	
	public AvengerAddZonePage clickAddZone()
	{
		click(addzonelocator);
		customReport.reporter("Add Zone Button is clicked", "");
		return basePage.avengerAddZonePage(driver,  customReport, basePage);
	}
	
	public boolean verify_AddZone()
	{
		return locatorsVisibilityAsPerRoles(addzonelocator);
	}
	public String verifyCreatedZone(AvengerAddZoneBeanPage avengeraddzonebeanpage)
	{
		By zonesearch = By.xpath(AvengerZonesPropertyPage.avengerzonespage_zonesearch.getProperty());
		enterText(zonesearch,  avengeraddzonebeanpage.getZonename());
		clickEnter(zonesearch);
		By createdzone = By.xpath("//*[contains(text(),'"+avengeraddzonebeanpage.getZonename()+"')]");
		return getText(createdzone);
	}
	

	 public void clearZoneSearchbox()
	 {
		 By zonesearchboxclear=By.xpath("//*[contains(@class,'glyphicons remove_2')]/..");
		 click(zonesearchboxclear);
	 }
	
	public AvengerAddZonePage clickCreatedZone(AvengerAddZoneBeanPage avengeraddzonebeanpage)
	{
		By createdzone = By.xpath("//*[contains(text(),'"+avengeraddzonebeanpage.getZonename()+"')]");
		click(createdzone);
		waitForElementPresent(zonenamelocator);
		pause(10000);
		return basePage.avengerAddZonePage(driver,  customReport, basePage);
	}
	
	public void clickAddSubZone(AvengerAddZoneBeanPage addzonebeanpage)
	{
		By subzonebuttonlocator = By.xpath("//*[contains(text(),'"+addzonebeanpage.getZonename()+"')]/../../div[4]/button");
		click(subzonebuttonlocator);
		customReport.reporter("Clicked on SubZone", "");
	}
	
	public String verifySubZoneCreation(String parentzonename)
	{
		By createdsubzone = By.xpath("//*[contains(text(),'"+parentzonename+"')]/../../../../div[4]//a");
		return getText(createdsubzone);
	}
	
	
	public void moveZoneHierarchy(String sourceName,String destinationName)
	 {
	  By sourceZonelocator = By.xpath("//*[text()='"+sourceName+"']/../../div[2]/span/span");
	  By destinationZonelocator = By.xpath("//*[text()='"+destinationName+"']/../../div[2]/span/span");
	  dragAndDrop(sourceZonelocator, destinationZonelocator);
	 }

	 public String verifyZoneHierarchy(String sourceName,String destinationName)
	 {
	  By zoneMovedlocator = By.xpath("//*[text()='"+destinationName+"']/../../../../div[4]//*[text()='"+sourceName+"']");
	  return getText(zoneMovedlocator);
	 }
	 
	 public AvengerAddZonePage clickDefaultZone(String defaultZoneName)
	 {
	    By defaultZonelocator = By.xpath("//a[text()='"+defaultZoneName+"']");
	    click(defaultZonelocator);
	    customReport.reporter("Avneger Add Zone Page is Opened","");
	    return basePage.avengerAddZonePage(driver,  customReport, basePage);
	 }
	 
	 public boolean verifyDefaultZoneOnTheTop(String defaultZoneName)
		{
		    By defaultZonelocator = By.xpath("(//div[@class='child-row'])[1]//a[contains(text(),'"+defaultZoneName+"')]");
		    return isDisplayedWithoutException(defaultZonelocator);
		}

	 public boolean zoneVisibility(String zonename)
	 {
		 By zonenamelocator = By.xpath("//*[contains(text(),'"+zonename+"')]");
		 return isDisplayedWithoutException(zonenamelocator);
	 }
	 
	 public void clickExpandAll()
	 {
		By expandalllocator = By.xpath(AvengerZonesPropertyPage.avengerzonespage_expandallbuttonlocator.getProperty());
		click(expandalllocator);
	 }
	
	public void clickCollapseAll()
	 {
		By collapsealllocator = By.xpath(AvengerZonesPropertyPage.avengerzonespage_collapseallbuttonlocator.getProperty());
		click(collapsealllocator);
	 }
	public void deleteZone(AvengerAddZoneBeanPage avengeraddzonebeanpage){

		  By deletingzone=By.xpath("//*[contains(text(),'"+avengeraddzonebeanpage.getZonename()+"')]/../..//*[@ng-click='removeZone(zone)']");
		  clickUsingSwitch(deletingzone);
		 }
	public void confirmDelete(String command){
		By deletezonecancelpopuplocator=By.xpath(AvengerCategoriesPropertyPage.deletecategorypopupnolocator.getProperty());
		By deletezonepopuplocator=By.xpath(AvengerCategoriesPropertyPage.deletecategorypopuplocator.getProperty());
		pause(3000);
		if(command.equals("Y"))
		{
			click(deletezonepopuplocator);
			pause(3000);
			customReport.reporter("Zone deleted succesfully!!!!", "");
		}
		else
		{
			click(deletezonecancelpopuplocator);

		}

	}
	
	public String verifyDeletionOfZone(AvengerAddZoneBeanPage avengeraddzonebeanpage){
		  By createdzone = By.xpath("//*[contains(text(),'"+avengeraddzonebeanpage.getZonename()+"')]");
		  return String.valueOf(elements(createdzone));
		  
		 }
	public String verifyingtextondeletingzonepopup(String text){
		By deletingzonetextxpath=By.xpath("//div[contains(text(),'"+text+"')]");
		  
		  return getText(deletingzonetextxpath);
		 }
	
	public String verifyDeletionOfSubZone(String parentzonename){
		  
		  By createdsubzone = By.xpath("//*[contains(text(),'"+parentzonename+"')]/../../../../div[4]//a");
		  return String.valueOf(elements(createdsubzone));
		 }
	
	public String getDevicesCount(String zonename)
	{
		By devicecountlocator = By.xpath("//*[contains(text(),'"+zonename+"')]/../span");
		return getText(devicecountlocator);
	}
	
	public List<String> getAllZones()
	{
		List<String> zoneslist = new ArrayList<String>();
		By allZonesLocator = By.xpath("//*[contains(@ui-sref,'edit-zone')]/a");
		List<WebElement> elements = getAllWebElementValues(allZonesLocator);
		for(WebElement ele:elements)
		{
			logger.info("The Zones Before sorting are"+ele.getText());
			zoneslist.add(ele.getText());
		}
	return zoneslist;
	}
	
	public String getInactiveDeviceErrorMessage(String zonename)
	{
		By inactivemessagelocator = By.xpath("//*[contains(text(),'"+zonename+"')]/../..//*[contains(@class,'warning')]/..");
		return getText(inactivemessagelocator);
	}
	
	public boolean verifyInactiveStatusRemoved(String zonename)
	{
		By inactivemessagelocator = By.xpath("//*[contains(text(),'"+zonename+"')]/../..//*[contains(@class,'warning')]/..");
		return isDisplayed(inactivemessagelocator);
	}


	public Map<String, String> getlables() {
		By addchildlocator=By.xpath("//button[contains(@ui-sref, 'add-zone')]");
	    By deletezone=By.xpath("//button[@ng-click='removeZone(zone)']");
		Map<String,String> labelsmap=new HashMap<String, String>();
		
		labelsmap.put("addzone", getText(addzonelocator));
		labelsmap.put("addchild",getText(addchildlocator));
		labelsmap.put("deletezone", getText(deletezone));
		return labelsmap;
	}
}
