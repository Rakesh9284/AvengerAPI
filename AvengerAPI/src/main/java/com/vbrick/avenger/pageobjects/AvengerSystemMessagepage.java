
package com.vbrick.avenger.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerSystemMessagePropertyPage;
import com.vbrick.avenger.dao.AvengerSystemMessageBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerSystemMessagepage extends WebElements{


	private WebDriver driver;
	private CustomReport customReport;
	private BasePage basePage;
	private static Logger logger = Logger.getLogger(AvengerSystemMessagepage.class);
	By systemMessagePage_StartDateLocator=By.xpath(AvengerSystemMessagePropertyPage.systemMessagepage_startDate.getProperty());
	By systemMessagePage_EndDateLocator=By.xpath(AvengerSystemMessagePropertyPage.systemMessagepage_endDate.getProperty());
	By systemMessagePage_StartTimeLocator=By.xpath(AvengerSystemMessagePropertyPage.systemMessagepage_startTime.getProperty());
	By systemMessagePage_EndtimeLocator=By.xpath(AvengerSystemMessagePropertyPage.systemMessagepage_endDtime.getProperty());
	By systemMessagePage_englishtextlocator=By.xpath(AvengerSystemMessagePropertyPage.systemMessagepage_englishTextArealocator.getProperty());
	By systemMessagePage_SaveButtonLocator=By.xpath(AvengerSystemMessagePropertyPage.systemMessagepage_saveButton.getProperty());
	By systemMessagePage_messageAlertOkbuttonlocator=By.xpath(AvengerSystemMessagePropertyPage.systemMessage_alertOkbuttonLocator.getProperty());

	public AvengerSystemMessagepage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;
		this.customReport = customReport;
		this.basePage = basePage;
	}


	public void enterSystemMessageData(AvengerSystemMessageBeanPage systemMessageBean) {

		enterText(systemMessagePage_StartDateLocator, systemMessageBean.getStartdate());
		enterText(systemMessagePage_StartTimeLocator, systemMessageBean.getStarttime());
		enterText(systemMessagePage_EndDateLocator, systemMessageBean.getEnddate());
		enterText(systemMessagePage_EndtimeLocator, systemMessageBean.getEndtime());
		enterText(systemMessagePage_englishtextlocator, systemMessageBean.getMessageText());
	}

	public void click_systemMessagePageSavebutton(){
		pause(3000);
		click(systemMessagePage_SaveButtonLocator);
		pause(3000);
		logger.info("Clicked on Save button");

	}

	public void click_systemMessageAlertokbutton(){
		click(systemMessagePage_messageAlertOkbuttonlocator);
		pause(3000);
		logger.info("Clicked on System Message Alert ok button");
	}

	public boolean verifyStartandEndTime(String timeparam){

		By timeLocator=By.xpath("(//*[@name='timeInput'])["+timeparam+"]");
		return isDisplayedWithoutException(timeLocator);

	}

}
