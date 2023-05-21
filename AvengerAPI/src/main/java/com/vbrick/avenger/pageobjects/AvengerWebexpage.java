package com.vbrick.avenger.pageobjects;

import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerWebexPropertyPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerWebexpage extends WebElements {
	private WebDriver driver;
	private CustomReport customReport;
	private BasePage basePage;
	
	private static Logger logger = Logger.getLogger(AvengerWebexpage.class);

	By webexvideocheckboxlocator=By.xpath(AvengerWebexPropertyPage.avengerwebexpage_selectvideocheckbox.getProperty());
	By webexvideotitle=By.xpath(AvengerWebexPropertyPage.avengerwebexpage_nameofthevideo.getProperty());
	By webexvideoimportedstatuslocator=By.xpath(AvengerWebexPropertyPage.avengerwebexpage_Videoimportedstatus.getProperty());
	By webexvideodate=By.xpath(AvengerWebexPropertyPage.avengerwebexpage_Videocreateddate.getProperty());
	By webexvideoduration=By.xpath(AvengerWebexPropertyPage.avengerwebexpage_Videodurationtimelocator.getProperty());
	By webexvideofilesize=By.xpath(AvengerWebexPropertyPage.avengerwebexpage_Videodufilesizelocator.getProperty());
	By webexsecondvideocheckbox=By.xpath(AvengerWebexPropertyPage.avengerwebexpage_selectvideocheckboxforsecondvideo.getProperty());
	By webexthirdvideocheckbox=By.xpath(AvengerWebexPropertyPage.avengerwebexpage_selectvideocheckboxforthirdvideo.getProperty());
	By webexforthvideocheckbox=By.xpath(AvengerWebexPropertyPage.avengerwebexpage_selectvideocheckboxforforthvideo.getProperty());
	By webexvideoimportbutton=By.xpath(AvengerWebexPropertyPage.avengerwebexpage_webeximportbuttonlocator.getProperty());
	By webexvideoimportvideoslist=By.xpath(AvengerWebexPropertyPage.avengerwebexpage_webeximportedvideosverification.getProperty());
	By webexvideosellalluploadsbutton=By.xpath(AvengerWebexPropertyPage.avengerwebexpage_webexseealluploadsbutton.getProperty());
	By webexcancelbutton=By.xpath(AvengerWebexPropertyPage.avengerwebexpage_webexcancelbutton.getProperty());
	By webexstatuswithoutimport=By.xpath(AvengerWebexPropertyPage.avengerwebexpage_Videoimportedstatuswithoutok.getProperty());
	
	public AvengerWebexpage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
		// TODO Auto-generated constructor stub
	}
	
	public LinkedHashMap<String, String> verify_webexvideodetails(){
		
		LinkedHashMap<String, String> webexvideodetails= new LinkedHashMap<String, String>();
		webexvideodetails.put("webexvideocheckbox",String.valueOf(isSelected(webexvideocheckboxlocator)));
		webexvideodetails.put("webexvideoimportstatus", getAttribute(webexstatuswithoutimport));
		webexvideodetails.put("webexvideoname", getText(webexvideotitle));
		webexvideodetails.put("webexvideodate", getText(webexvideodate));
		webexvideodetails.put("webexvideoduration", getText(webexvideoduration));
		webexvideodetails.put("webexvideofilesize", getText(webexvideofilesize));
		return webexvideodetails;
	}
	
	public void selectvideoforwebex(int times){
		
		for(int i=1;i<=times;i++)
		{
		By webexvideoimportbutton=By.xpath("(//*[contains(@ng-show,'recording')]/..)["+i+"]");
		check_Checkbox(webexvideoimportbutton);
		}
		click(webexvideoimportbutton);
	}
	
	public String verifywebexuplodedvideos(){
		return String.valueOf(elements(webexvideoimportvideoslist));
	}
	
	public AvengerUploadsPage click_webeximportedvideos(){
		pause(5000);
		click(webexvideosellalluploadsbutton);
		logger.info("Clicked on the see all uploaded videos link");
		return basePage.avengerUploadsPage(driver, customReport, basePage);
	}
	 
	public AvengerVideoCommentsPage click_webeximportcancelbutton(){
		click(webexcancelbutton);
		logger.info("Clicked on the webex cancel button");
		return basePage.avengerVideoCommentsPage(driver, customReport, basePage);
	}
	

}
