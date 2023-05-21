package com.vbrick.avenger.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerContentRestrictionPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerUserPasswordParamatersPropertyPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerContentRestrictionPage extends WebElements {
	private static Logger logger = Logger.getLogger(AvengerContentRestrictionPage.class);
	
	 By allowsharingofmetadataprivatevideoslocator=By.xpath("//*[contains(@name,'sharingAndEmbeddingSettingsEnabled')]");
	 By savebuttonlocator=By.xpath( AvengerContentRestrictionPropertyPage.avengercontentrestrictionpage_savebuttonlocator.getProperty()); 
	By allowembeddedcontent=By.xpath("//*[contains(@name,'embedWhitelistedUrlsSettingsEnabled')]");
    By acceptbutttonforembeddedcontentlocator=By.xpath("//*[contains(text(),' Accept ')]");	 
    By hidinglevelanalyticslocator=By.xpath("//*[contains(@name,'hideUserLevelAnalytics')]");
	 
    private WebDriver driver;
	private CustomReport customReport;
	private BasePage basePage;

	public AvengerContentRestrictionPage(WebDriver driver, CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;

		// TODO Auto-generated constructor stub
	} 
	
		public void checkAllowSharingofmetadataprivateVideos()
		{
			check_Checkbox(allowsharingofmetadataprivatevideoslocator);
		}
		
		public void unCheckAllowSharingofmetadataprivateVideos()
		{
			uncheck_Checkbox(allowsharingofmetadataprivatevideoslocator);
		
		}
	
		public void checkAllowEmbeddedContent()
		{
			check_Checkbox(allowembeddedcontent);
			waitForElementPresent(acceptbutttonforembeddedcontentlocator);
		    click(acceptbutttonforembeddedcontentlocator);
		    logger.info("CLicked on Accept");
		}
		
		public void uncheckAllowEmbeddedContent()
		{
			uncheck_Checkbox(allowembeddedcontent);
		}
		
		
	public void click_save(){
		submit(savebuttonlocator);
		 customReport.reporter("Save Button is Clicked",""); 
	}
	 
	 public void check_hidinglevelanalytics() {
		 check_Checkbox(hidinglevelanalyticslocator);
		 logger.info("Hide User Level Analytics Check box checked");
	 }

	 public void uncheck_hidinglevelanalytics() {
		 uncheck_Checkbox(hidinglevelanalyticslocator);
		 logger.info("Hide User Level Analytics Check box unchecked");
	 }
	
}
