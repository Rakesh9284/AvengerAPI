package com.vbrick.avenger.pageobjects;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerApiKeysPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerCategoriesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerDevicesPropertyPage;
import com.vbrick.avenger.dao.AddApikeyBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerApiKeysPage extends WebElements{
	private static Logger logger = Logger.getLogger(AvengerSystemSettingsPage.class);

	By addkeylink=By.xpath(AvengerApiKeysPropertyPage.avengerapikeyspage_addkeylinkbuttonlocator.getProperty());
	By newapikeynamelocator=By.xpath(AvengerApiKeysPropertyPage.avengerapikeyspage_newapikeynamelocator.getProperty());
	By newapikeylocator=By.xpath(AvengerApiKeysPropertyPage.avengerapikeyspage_newapikeylocator.getProperty());
	By createbuttonlocator=By.xpath(AvengerApiKeysPropertyPage.avengerapikeyspage_createbuttonlocator.getProperty());
	By cancelbuttonlocator=By.xpath(AvengerApiKeysPropertyPage.avengerapikeyspage_cancelbuttonlocator.getProperty());
	By deletecategorypopuplocator=By.xpath(AvengerCategoriesPropertyPage.deletecategorypopuplocator.getProperty());
	By AddaDevicebuttonlocator=By.xpath(AvengerDevicesPropertyPage.avengerdevicespage_AddaDevicebuttonlocator.getProperty());

	private WebDriver driver;
	private CustomReport customReport;
	private BasePage basePage;

	public AvengerApiKeysPage(WebDriver driver, CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;

		// TODO Auto-generated constructor stub
	}


	public void create_ApiKeys(AddApikeyBeanPage addapikeybeanpage)
	{
		click(addkeylink);
		enterText(newapikeynamelocator,  addapikeybeanpage.getApiname());
		enterText(newapikeylocator,  addapikeybeanpage.getApikey());
		submit(createbuttonlocator);
	}

	public boolean verify_apikeycreation(AddApikeyBeanPage addapikeybeanpage)
	{
		boolean sflag=false; 
		By newapilink=By.xpath("//*[contains(text(),'"+addapikeybeanpage.getApiname()+"')]");
		if(waitForElementPresent(newapilink)==true)
		{
			sflag=true;
			customReport.reporter("API Key Created Successfully :",getText(newapilink));
			logger.info("Created user value is"+sflag);
		}
		return sflag;
	}

	public String apiKeyDisplayed(String apiname)
	{
		By deleteapikeylocator=By.xpath("//*[text()='"+apiname+"']/..//button");	
		return String.valueOf(elements(deleteapikeylocator));
	}

	public Map<String, String> delete_apikeyCreation(String apiname)
	{
		Map<String,String> labelsmap =new HashMap<String, String>();
		By deleteapikeylocator=By.xpath("//*[text()='"+apiname+"']/..//button");	
		labelsmap.put("DeleteText", getText(deleteapikeylocator));
		click(deleteapikeylocator);
		waitForElement(deleteapikeylocator);
		customReport.reporter("Pop up displayed for deleting API KEY","");
		By deletepopup = By.xpath("//*[contains(@class,'modal-body')]");
		labelsmap.put("DeleteConfirm", getText(deletepopup).split(":")[0]);
		click(deletecategorypopuplocator);
		customReport.reporter("API KEY Deleted","");
		return labelsmap;
	}	

	public void clickAddKey()
	{
		click(addkeylink);
		logger.info("Add Key button is clicked");
	}

	public Map<String, String> Verify_Labels() {

		By nameplaceholder=By.xpath("//*[@name='form']//*[@name='name']");
		By keyplaceholder=By.xpath("//*[@name='form']//*[@name='key']");
		By addkeytext = By.xpath("//*[contains(@vb-authorization-key,'apiKey.add')]");
		Map<String,String> labelsmap=new HashMap<String, String>();
		labelsmap.put("ApiName", getAttribute(nameplaceholder,"placeholder"));
		labelsmap.put("ApiKey", getAttribute(keyplaceholder, "placeholder"));	
		labelsmap.put("AddKey", getText(addkeytext));	
		return labelsmap;
	}
}