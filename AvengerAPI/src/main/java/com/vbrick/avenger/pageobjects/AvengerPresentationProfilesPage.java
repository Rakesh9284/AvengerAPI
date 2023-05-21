package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerAddPresentationProfilePropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerCategoriesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerHomePropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerPresentationProfilesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerUploadPropertyPage;
import com.vbrick.avenger.dao.AddPresentationprofileBeanPage;
import com.vbrick.avenger.dao.AvengerEventDetailsBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerPresentationProfilesPage extends WebElements {

	WebDriver driver;
	CustomReport customReport;
	private BasePage basePage;
	List<String> list ;
	Map<String,String> map;
	private static Logger logger = Logger.getLogger(AvengerPresentationProfilesPage.class);
	
	By AddaPresentationprofilelocator = By.xpath(AvengerPresentationProfilesPropertyPage.avengerpresentationprofilespage_AddaPresentationprofilelocator.getProperty());
	By dashboardpagelocator = By.xpath(AvengerHomePropertyPage.homepg_dashboardlink.getProperty());
	By namelocator = By.xpath(AvengerAddPresentationProfilePropertyPage.avengeraddpresentationprofilepage_namelocator.getProperty());
	By uploadtablink=By.linkText(AvengerUploadPropertyPage.uploadpg_Uploadtab.getProperty());
	By createbuttonlocator = By.xpath(AvengerAddPresentationProfilePropertyPage.avengeraddpresentationprofilepage_createbuttonlocator.getProperty());
	By deletecategorypopuplocator = By.xpath(AvengerCategoriesPropertyPage.deletecategorypopuplocator.getProperty());
	
	
	public AvengerPresentationProfilesPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport = customReport;
		this.basePage=basePage;
	}
	
	public AvengerAddPresentationProfilePage click_AddPresentationprofile()
	{
		click(AddaPresentationprofilelocator);
		customReport.reporter("Add Presentation Profile  Button is Clicked","");
		waitForElementPresent(namelocator);
		return basePage.avengerAddPresentationProfilePage(driver,  customReport, basePage);
		
	}
	
	 public boolean verify_AddPresentationprofile()
	 {
			return locatorsVisibilityAsPerRoles(AddaPresentationprofilelocator);
	 }
	public String check_PresentationProfile(AddPresentationprofileBeanPage addpresentationprofilebeanpage)
    {
		By presentationprofile=By.xpath("//*[contains(text(),'"+addpresentationprofilebeanpage.getName()+"')]");
        return getText(presentationprofile);
	
    }
	
	public AvengerDashboardPage click_DashBoardLink()
	{
		click(dashboardpagelocator);
		customReport.reporter("DashBoard Page is clicked", "");
		return basePage.avengerDashboardPage(driver,  customReport, basePage);
	}
	
	public AvengerAddPresentationProfilePage click_PresentationProfile(AddPresentationprofileBeanPage addpresentationprofilebeanpage)
    {
		By presentationprofile=By.xpath("//*[contains(text(),'"+addpresentationprofilebeanpage.getName()+"')]");
		click(presentationprofile);
		waitForElementPresent(namelocator);
		pause(5000);
        return basePage.avengerAddPresentationProfilePage(driver,  	customReport, basePage);
       
    }
	public AvengerPresentationProfilesPage delete_PresentationProfile(AddPresentationprofileBeanPage addpresentationprofilebeanpage)
	{
		By deletebuttonlocator = By.xpath("//*[contains(text(),'"+addpresentationprofilebeanpage.getName()+"')]/../..//*[contains(@ng-click,'removePresentationProfile')]");
		waitForElementPresent(deletebuttonlocator);
		click(deletebuttonlocator);
		customReport.reporter("Delete button is clicked", "");
		waitForElementPresent(deletecategorypopuplocator);
		click(deletecategorypopuplocator);
		customReport.reporter("Deleted Successfully", "");
		pause(5000);
		return basePage.avengerPresentationProfilesPage(driver,  customReport, basePage);
		
	}
	
	public boolean verify_deletePresentationProfile(AddPresentationprofileBeanPage addpresentationprofilebeanpage)
	{
		By deletepresentationbuttonlocator = By.xpath("//*[@ng-click='removePresentationProfile(presentationProfile)']");
		waitForElementPresent(deletepresentationbuttonlocator);
		return locatorsVisibilityAsPerRoles(deletepresentationbuttonlocator);

	}
	public int check_DeletedPresentationProfile(AddPresentationprofileBeanPage addpresentationprofilebeanpage)
	{
		By deletedpresentationprofile = By.xpath("//*[contains(text(),'"+addpresentationprofilebeanpage.getName()+"')]");
		return elements(deletedpresentationprofile);
	}
	
	public Map<String, String> get_ListforDeleteFutureEventError(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
	{
		map = new HashMap<String, String>();
		By errortextlocator = By.xpath("//*[@class='modal-body conflicted-bg ng-scope']/p");
		By titlelocator = By.xpath("//*[contains(text(),'"+avengereventdetailsbeanpage.getTitle()+"')]");
		By hostlocator = By.xpath("//*[contains(text(),'"+avengereventdetailsbeanpage.getHost()+"')]");
		map.put("EventTitle", getText(titlelocator));
		map.put("EventHost", getText(hostlocator));
		map.put("ErrorText", getText(errortextlocator));	
		return map;
		
	}
	
	public String getInactiveDeviceErrorMessage(String profilename)
	{
		By inactivemessagelocator = By.xpath("//*[contains(text(),'"+profilename+"')]/../..//*[contains(@class,'warning')]/..");
		return getText(inactivemessagelocator);
	}
	
	public boolean verifyInactiveStatusRemoved(String profilename)
	{
		By inactivemessagelocator = By.xpath("//*[contains(text(),'"+profilename+"')]/../..//*[contains(@class,'warning')]/..");
		return isDisplayedWithoutException(inactivemessagelocator);
	}
	
	public void clickDeletePresentationProfile(String profileName)
	{
		By deletebuttonlocator = By.xpath("//*[contains(text(),'"+profileName+"')]/../..//*[contains(@ng-click,'removePresentationProfile')]");
		
		waitForElementPresent(deletebuttonlocator);
		click(deletebuttonlocator);
		
	}

	public  Map<String, String>  getLabels() {
		
		Map<String,String> labelsmap=new HashMap<String, String>();
		
		labelsmap.put("addpresentationprofile", getText(AddaPresentationprofilelocator));
		
		return labelsmap;
		
	}
	
	//API
		public ArrayList<String> get_allpresentaionProfiles()
		{
			By presenationprofilelocator=By.xpath("//a[contains(@href,'presentation-profile')]");
			return getAllWebElementValueslist(presenationprofilelocator);
		}

}
