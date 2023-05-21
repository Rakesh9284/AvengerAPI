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
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerRolesPage extends WebElements{
private static Logger logger = Logger.getLogger(AvengerSystemSettingsPage.class);

      private WebDriver driver;
	
	private CustomReport customReport;
	private BasePage basePage;
	
	public AvengerRolesPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
		
		// TODO Auto-generated constructor stub
	}
	
	By roleslocatorxpath=By.xpath("//ol[contains(@class,'role-table')]/li/div[1]");
	
	public ArrayList<String> get_RolesText()
	{
	 
	   List<WebElement> webelements= getAllWebElementValues(roleslocatorxpath);
			ArrayList<String> allroles =  new ArrayList<String>();
			for (WebElement webelement : webelements) {
			logger.info("all roles  are "+webelement.getText());
			allroles.add(webelement.getText());
			}
			return allroles;
	}

	public Map<String, String> getLabels(){
		
		By permissionslocator=By.xpath("//div[text()='Permissions']");
		Map<String,String> lablesmap= new HashMap<String, String>();
		
		lablesmap.put("permissions", getText(permissionslocator));
		
		
		return lablesmap;
		
	}

	public String rolespermissionstext(String text){
		
		By permissionslocator=By.xpath("//*[contains(text(),'"+text+"')]/../div[2]");		
		logger.info("account admin text is:"+getText(permissionslocator));
		return getText(permissionslocator);
		
	}
		
	public String rolesdescriptiontext(String text) {
		By descriptionlocator=By.xpath("//*[contains(text(),'"+text+"')]/../div[3]");
				
				logger.info("account admin text is:"+getText(descriptionlocator));
				return  getText(descriptionlocator);
			}
}