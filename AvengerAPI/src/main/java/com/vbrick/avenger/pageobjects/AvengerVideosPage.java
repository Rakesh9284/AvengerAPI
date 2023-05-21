package com.vbrick.avenger.pageobjects;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerMediaPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerUploadPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerVideoCommentsPropertyPage;
import com.vbrick.avenger.dao.AvengerEditVideoSettingsBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerVideosPage extends WebElements{

	private WebDriver driver;
	private CustomReport customReport;
	
	private BasePage basePage;

	By videostablocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_videostablocator.getProperty());
	By videossearchlocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_videossearchlocator.getProperty());
	By videossearchbuttonlocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_videossearchbuttonlocator.getProperty());
	By noofvideoslocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_noofvideoslocator.getProperty());
	By tileViewButtonLocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_tileViewButtonLocator.getProperty());
	By tileViewsLocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_tileViewsLocator.getProperty());
	By listViewButtonLocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_listViewButtonLocator.getProperty());
	By titleNameLocatyor=By.xpath(AvengerMediaPropertyPage.avengerMediaPage_titleNameText.getProperty());
	By tileviewstarlocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_tileviewstarslocator.getProperty());
	By totalnoofvideoslocator=By.xpath("//*[contains(@class,'type-bold type-14 padding-left-5')]");
	By bulkEditoptionlocator=By.xpath(AvengerUploadPropertyPage.uploadpg_bulkeditlocator.getProperty());
	By selectallvideocheckboxlocator=By.xpath(AvengerUploadPropertyPage.uploadpg_selectallchkboxlocator.getProperty());
	By pendingApprovalTabLink=By.xpath(AvengerMediaPropertyPage.avengermediapage_pendingApprovalTabLink.getProperty());
	private static Logger logger = Logger.getLogger(AvengerVideosPage.class);

	public AvengerVideosPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport = customReport;
		this.basePage=basePage;
	}

	public AvengerVideoCommentsPage clickVideo(String svideoName)
	{ 
		
		By mediasearchtextboxlocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_mediasearchtextboxlocator.getProperty());
		By mediasearchbuttonlocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_mediasearchbuttonlocator.getProperty());

		enterText(mediasearchtextboxlocator,  svideoName);
		submit(mediasearchbuttonlocator);
		customReport.reporter("Clicked on Media Search Button","");
		By fileName = By.xpath("//a[text()='"+svideoName+"']");
	    waitForElementPresent(fileName);
		clickUsingSwitch(fileName);
        pause(3000);
		return basePage.avengerVideoCommentsPage(driver,  customReport, basePage);
	}

	public void enterVideoSearchData(String videotitle)
	{
		enterText(videossearchlocator,  videotitle); 
		customReport.reporter("Video Search Data Entered is", videotitle);
	}
	public void click_SearchButton()
	{
		click(videossearchbuttonlocator);	
		customReport.reporter("Clicked on Search Button", "");
		pause(5000);
	}

	public String verify_Video(String svideoName)
	{
		By mediasearchtextboxlocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_mediasearchtextboxlocator.getProperty());
		By mediasearchbuttonlocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_mediasearchbuttonlocator.getProperty());
		enterText(mediasearchtextboxlocator,  svideoName);
		submit(mediasearchbuttonlocator);
		customReport.reporter("Clicked on Media Search Button","");
		By fileName = By.xpath("//a[text()='"+svideoName+"']");
		pause(2000);
		logger.info("file name searched is"+getText(fileName));
		return getText(fileName);
	}
	public String verify_label(String label)
	{
	By fileName = By.xpath("//a[contains(text()='"+label+"')]");
		return getText(fileName);
	}
	
	public String get_NoofVideos(String videoname)
	{
		
		By videolocator = By.xpath("//a[text()='"+videoname+"']");
		logger.info("No Of videos in the videos page are"+elements(videolocator));
		return String.valueOf(elements(videolocator));
	}
	public ArrayList<String> get_AllVideosName()
	{
		pause(5000);
		By allvideosnamelocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_allvideoslocator.getProperty());
		List<WebElement> webelements= getAllWebElementValues(allvideosnamelocator);
		ArrayList<String> videonamelist =  new ArrayList<String>();
		for (WebElement webelement : webelements) {
			videonamelist.add(webelement.getText());
		}
		return videonamelist;
	}
	public LinkedList<String> get_AllVideosDate()
	{
		pause(5000);
		By allvideosdatelocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_allvideosdatelocator.getProperty());
		List<WebElement> webelements= getAllWebElementValues(allvideosdatelocator);
		LinkedList<String> videonamelist =  new LinkedList<String>();
		for (WebElement webelement : webelements) {
			videonamelist.add(webelement.getText());
		}
		return videonamelist;
	}
	public ArrayList<String> get_AllVideosUploadedByName()
	{
		pause(5000);
		By allvideosnamelocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_allvideosnamelocator.getProperty());
		List<WebElement> webelements= getAllWebElementValues(allvideosnamelocator);
		ArrayList<String> videonamelist =  new ArrayList<String>();
		for (WebElement webelement : webelements) {
			videonamelist.add(webelement.getText());
		}
		return videonamelist;
	}

	
	public void searchByCategoryFilter(String categoryName)
	 {
	  By categoryFilerlocator = By.xpath("//*[@id='catFilter']/div/div[1]");
	  By selectTextlocator = By.xpath("//*[@id='catFilter']/div/div[2]/div//div[contains(text(),'"+categoryName+"')]");
	  
	  click(categoryFilerlocator);
	  waitForElementPresent(selectTextlocator);
	  click(selectTextlocator); 
	  pause(3000);
	 }
	public void click_Categories(String videoname)
	{
		By categorylocator =By.xpath("//*[text()='"+videoname+"']/../../a");
		click(categorylocator);
		customReport.reporter("Clicked on Categories", "");
	}
	
	public Map<String, String> get_VideoBasicMetaData(String videoname)
	{
		By broadcastdatelocator=By.xpath("//a[(text()='"+videoname+"')]/../../../..//p");
		By categorylocator=By.xpath("//a[(text()='"+videoname+"')]/../../a ");
		By librarylocator=By.xpath("//a[(text()='"+videoname+"')]/../../a");
		By tagslocator=By.xpath("");
		Map<String,String> videometadata=new HashMap<String, String>();
		videometadata.put("broadcastdate", getText(broadcastdatelocator));
		videometadata.put("category", getText(categorylocator));
		videometadata.put("library", getText(librarylocator));
		videometadata.put("tags", getText(tagslocator));
		return videometadata;
	}
	
	
	public Map<String, String> get_videoDetails(AvengerEditVideoSettingsBeanPage avengereditvideosettingsbeanpage)
	{
		By categorylocator=By.xpath("//a[contains(text(),'"+avengereditvideosettingsbeanpage.getCategories()+"')]");
		By descriptionlocator=By.xpath("//*[contains(text(),'"+avengereditvideosettingsbeanpage.getDescription()+"')]");
		By titlelocator=By.xpath("//a[contains(text(),'"+avengereditvideosettingsbeanpage.getTitle()+"')]");
		Map<String,String> videodetailslist= new HashMap<String,String>();
		videodetailslist.put("description",getText(descriptionlocator));
		videodetailslist.put("title",getText(titlelocator));
		return videodetailslist;
	}
	

	public void clickStatus(String status) {
		By clickonStatusDropDown=By.xpath("//*[@ng-model='$parent.statusFilter']");
		waitForElement(clickonStatusDropDown);
		clickUsingSwitch(clickonStatusDropDown);
		By defaultactivelocator=By.xpath("//*[text()='"+status+"']");
		waitForElement(defaultactivelocator);
		click(defaultactivelocator);
}

	public int get_RecordedVideosData(String label)
	{
     By recordedeventdata=By.xpath("//a[contains(text(),'"+label+"')]");
	return elements(recordedeventdata);
    }
	
	public AvengerVideoCommentsPage videoclickwithoutsearch(String svideoName) {

		By videostatus = By.xpath("//a[text()='" + svideoName+ "']/../..//span[contains(@ng-show,'Ready')]");
		waitForElementPresent(videostatus);
		By fileName = By.xpath("//a[text()='" + svideoName + "']");
		click(fileName);
		pause(3000);
		return basePage.avengerVideoCommentsPage(driver, 
				customReport, basePage);
	}

	public void click_videoTileView() {
		waitForElement(tileViewButtonLocator);
		clickUsingSwitch(tileViewButtonLocator);
		waitForElement(tileViewsLocator);
	}

	public int check_videoTileView() {
		return elements(tileviewstarlocator);
	}

	public void click_videoListView() {
		click(listViewButtonLocator);

	}
	
	public int check_videoTileViewInGuest() {
		return elements(tileViewsLocator);
	}
	
	public void click_pendingApprovalTab()
	{
		click(pendingApprovalTabLink);
		customReport.reporter("Pending Approval Tab is Clicked","");

	}
	
	public ArrayList<String> getAll_pendingApprovalVideos() throws InterruptedException
	{
		{
			By videosLocator = By.xpath("//*[contains(text(),'Pending Approval')]/../../../../../..//*[contains(@class,'title')]");
			JavascriptExecutor jse1 = (JavascriptExecutor) driver;
			int j;
			
			for(j=0;j<=5;j++) {
			jse1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(5000);
			}
			ArrayList<String> webelementslist=getAllWebElementValueslist(videosLocator);
		  	return webelementslist;
		}
		
	}
	
	public String getVideoApprovedorRejectedVideoStatus(String videoname,String videoapprovalstatus)
	{
		By videostatus=By.xpath("//a[text()='"+videoname+"']/../../../..//*[contains(text(),'"+videoapprovalstatus+"')]");
	    return getText(videostatus);
	
	}
	/**
	 * It return the total no of videos in the list in the upper right corner
	 * @return It will return the total no of videos in videos page
	 */
	public String get_totalvideosInAllVideosPage()
	{
	  pause(5000);
	  return getText(totalnoofvideoslocator);
	}
	
	public AvengerVideoBulkEditPage clickBulkEditoption(){
		click(bulkEditoptionlocator);
		customReport.reporter("Bulk Edit Option is Clicked","");
		pause(5000);
		return basePage.avengervideobulkeditpage(driver, customReport, basePage);
	}
	
	public AvengerVideoBulkEditPage checkselectallcheckbox(){
		click(selectallvideocheckboxlocator);
		customReport.reporter("Select all checkbox is Clicked","");
		pause(5000);
		return basePage.avengervideobulkeditpage(driver, customReport, basePage);
	}	
	}



