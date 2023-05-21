package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerHomePropertyPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerVideoNotificationPage extends WebElements{
	
	private WebDriver driver;
	private CustomReport customReport;
	
	private BasePage basePage;

	
	private static Logger logger = Logger.getLogger(AvengerVideoNotificationPage.class);

	By markallasreadbuttonlocator=By.xpath("//*[@ng-click='markAllAsRead()']");
	By allnotificationitemslocator=By.xpath("//*[contains(@class,'time-stamp')]");
	By notificationcountlocator=By.xpath("//*[@ng-show='notificationMenu.count']/..");
	By dismissbuttonlocator=By.xpath("//*[@ng-click='markAsRead()']");
	By notificationbuttonlocator=By.xpath(AvengerHomePropertyPage.homepg_notificationbuttonlocator.getProperty());
	  
	public AvengerVideoNotificationPage(WebDriver driver,  
			CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport = customReport;
		this.basePage=basePage;
	}

	
	public Boolean verify_videoUploadedNotification(FileUploadBeanPage fileuploadbeanpage,String datevalue)
	{
		
		By uploadnotificationlocator=By.xpath("//*[text()='"+datevalue+"']/..");
		boolean sflag=false;
		logger.info("Upload notification xpath is"+uploadnotificationlocator);
		if(getText(uploadnotificationlocator).contains(FilenameUtils.removeExtension(fileuploadbeanpage.getFilename()))==true&&
				getText(uploadnotificationlocator).contains("Upload completed."))
		{
			customReport.reporter("File Uploaded Successfully",fileuploadbeanpage.getFilename());
			sflag=true;
		}
			else
			{
				customReport.reporter("File  Not Uploaded Successfully",fileuploadbeanpage.getFilename());
				sflag=false;
			}
				return sflag;
	}
	
	public int get_dismissButtonForAllNotificationsSize()
	{
		List<WebElement> webelements=	getAllWebElementValues(dismissbuttonlocator);
      logger.info("No fo dismiss Button available are"+webelements.size());
		return  webelements.size();
	}
	
	
	
	public void click_MarkAllAsRead()
	{

		if(isDisplayed(markallasreadbuttonlocator))
		{
		click(markallasreadbuttonlocator);
		customReport.reporter("Mark All As Read Button is Clicked","");
		}

	}
	
	public ArrayList<String> get_allNotifications()
	{
		 	List<WebElement> webelements=	getAllWebElementValues(allnotificationitemslocator);
			ArrayList<String> notificationitems=new ArrayList<String>();
		 	for (WebElement webelement : webelements) {
				logger.info("Notification Items are"+webelement.getText());
	  	        notificationitems.add(webelement.getText());
				}
			return notificationitems;
	}
	
  public int get_notificationCount()
  {
	logger.info("Notification Values are"+ getText(notificationcountlocator));
	return  Integer.parseInt(getText(notificationcountlocator));
  }
  
  public int click_dismissnotification(FileUploadBeanPage fileuploadbeanpage,String datevalue)
  {
	  ArrayList<String> notifications =get_allNotifications();
		logger.info("the text for notification value is"+get_allNotifications().get(0).replace("Agile - Upload completed. ", ""));
	 
		By dismissnotificationlocator=By.xpath("//*[text()='"+get_allNotifications().get(0).replace("Agile - Upload completed. ", "")+"']/../../../../../div/div/button");
	  click(dismissnotificationlocator);
	  pause(5000);
	  return  elements(dismissnotificationlocator);
  }
	
	
  public List<String> getAllNotificationName()
  {
	  By getAllNotificationName = By.xpath("//p[@class='notification-msg ng-binding']");
	  
	  List<String> nameList = new ArrayList<String>();
	  List<WebElement> list = getAllWebElementValues(getAllNotificationName);
	  
	  for(WebElement element : list)
	  {
		 logger.info("notifications are "+element.getText());
		  nameList.add(element.getText());
	  }
	return nameList;  
  }

  public String getVideoUploadTime()
  {
	 By getvideouploadTimelocator = By.xpath("//*[(contains(@class,'time-stamp'))]");
	 
	 List<WebElement> list = getAllWebElementValues(getvideouploadTimelocator);
	 String time = list.get(0).getText();
	return time;
  }
  
  public String verify_Label(String labelname)
	{
		By labellocator = By.xpath("//*[contains(text(),'"+labelname+"')]");
	  return  getText(labellocator);
	}
  
}
