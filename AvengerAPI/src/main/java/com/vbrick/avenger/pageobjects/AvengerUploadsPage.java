package com.vbrick.avenger.pageobjects;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerUploadPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerVideoCommentsPropertyPage;
import com.vbrick.avenger.dao.AvengerEditVideoSettingsBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerUploadsPage extends WebElements {

	private static Logger logger = Logger.getLogger(AvengerUploadsPage.class);
		   
	private WebDriver driver;
	
	private CustomReport customReport;
	private BasePage basePage;
	By listviewbuttonlocator=By.xpath(AvengerUploadPropertyPage.uploadpg_listviewbuttonlocator.getProperty());
	By ratingslocator=By.xpath(AvengerUploadPropertyPage.uploadpg_ratingslocator.getProperty());
	By settingslinklocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_settingslinklocator.getProperty());
	By webexvideolocator=By.xpath(AvengerUploadPropertyPage.uploadpg_webeximportedfirstvideo.getProperty());
	By totalvideoslocator=By.xpath(AvengerUploadPropertyPage.uploadpg_totalvideoslocator.getProperty());
	By tileviewbuttonlocator=By.xpath(AvengerUploadPropertyPage.uploadpg_tileviewbuttonlocator.getProperty());
	By bulkEditoptionlocator=By.xpath(AvengerUploadPropertyPage.uploadpg_bulkeditlocator.getProperty());
	public AvengerUploadsPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
	}
	
	public AvengerVideoCommentsPage clickVideo(String svideoName)
	{ 
		logger.info("the file name is"+svideoName);
		By fileName = By.xpath("//a[contains(text(),'"+svideoName+"')]");
		waitForElementPresent(fileName);
		click(fileName);
		return basePage.avengerVideoCommentsPage(driver,  customReport, basePage);
	}
	
	public AvengerEditVideoSettingsPage click_newVideoLink(AvengerEditVideoSettingsBeanPage editvideosettingsbean)
	{

		By videotitle=By.xpath("//h2[contains(@class,'bold')]//*[text()='"+editvideosettingsbean.getTitle()+"']");	
       click(videotitle);
       customReport.reporter("Clicked on video title is",editvideosettingsbean.getTitle());
       return basePage.avengerEditVideoSettingsPage(driver,  customReport, basePage);
	}
	
	public AvengerVideoCommentsPage click_newVideoLinkForComments(AvengerEditVideoSettingsBeanPage editvideosettingsbean)
	{
		logger.info("The value is "+editvideosettingsbean.getTitle());
		By videotitle=By.xpath("//*[text()='"+editvideosettingsbean.getTitle()+"']");
       click(videotitle);
       customReport.reporter("Clicked on video title is",editvideosettingsbean.getTitle());
       waitForElementPresent(videotitle);
       return basePage.avengerVideoCommentsPage(driver,  customReport, basePage);
	}
	
		public void click_ListView()
	{
		click(listviewbuttonlocator);
		customReport.reporter("List View is Clicked","");
		pause(5000);
	}
	
	public Map<String, String> get_ListViewVideoData(AvengerEditVideoSettingsBeanPage avengereditvideosettingsbeanpage)
	{
		By usernamelocator=By.xpath("//*[(text()='"+avengereditvideosettingsbeanpage.getTitle()+"')]/../../p[1]");
		By uploadtimelocator=By.xpath("//*[(text()='"+avengereditvideosettingsbeanpage.getTitle()+"')]/../../p[2]");
		By viewslocator=By.xpath("//*[(text()='"+avengereditvideosettingsbeanpage.getTitle()+"')]/../../div/div[2]/a/span[2]");
		By commentslocator=By.xpath("//*[(text()='"+avengereditvideosettingsbeanpage.getTitle()+"')]/../../div/div[3]/a/span[2]");
		By descriptionlocator=By.xpath("//*[(text()='"+avengereditvideosettingsbeanpage.getTitle()+"')]/../../p[3]");
		By videodurationlocator=By.xpath("//*[(text()='"+avengereditvideosettingsbeanpage.getTitle()+"')]/../../../../div[1]/a/div/span");
		Map<String, String> videodetails = new HashMap<String, String>();
		videodetails.put("username",getText(usernamelocator));
		videodetails.put("uploadtime",getText(uploadtimelocator));
		videodetails.put("views",getText(viewslocator));
		videodetails.put("comments",getText(commentslocator));
		videodetails.put("description",getText(descriptionlocator));
		videodetails.put("videoduration",getText(videodurationlocator));
		
		return videodetails;
	 }
	
	public String get_Allratings()
	{
		return String.valueOf(elements(ratingslocator));
		
	}
     
	public void clickStatus(String status) {
		By clickonStatusDropDown=By.xpath("//*[@ng-model='$parent.statusFilter']");
		click(clickonStatusDropDown);
		By defaultactivelocator=By.xpath("//*[text()='"+status+"']");
		click(defaultactivelocator);
		logger.info("Clicked on Status"+status);
	}

	public boolean check_enableRatingsDisplayed()
	{
		return isDisplayed(ratingslocator);
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
	
public String verifyVideoVisualIndicator(){
		By visualindicator=By.xpath("//*[contains(@class,'circle_minus')]");
		return String.valueOf(elements(visualindicator));
	}

	public String getVideoStatus(String svideoName)
	{ 
		logger.info("the file name is"+svideoName);
		By videoStausLocator = By.xpath("(//a[contains(text(),'"+svideoName+"')]/../../../../div[1]//span)[2]");
		return getText(videoStausLocator); 
}
	public AvengerVideoCommentsPage clickWebeximportedfirstvideo(){
		pause(3000);
		clickUsingSwitch(webexvideolocator);
		logger.info("Clicked on the Webex video uploaded");
		return basePage.avengerVideoCommentsPage(driver,  customReport, basePage);
		
	}
	
	public AvengerVideoCommentsPage clickVideoInMyVideosTab(String svideoName){
		By videoname=By.xpath("//*[@class='bold']//*[contains(text(),'"+svideoName+"')]");
		
		clickInElements(videoname);
		By videoTitlelocator = By.xpath("(//h1[contains(text(),'"+svideoName+"')])[last()]");
		isDisplayed(videoTitlelocator);
		return basePage.avengerVideoCommentsPage(driver,  customReport, basePage);
	}
	
	/**
	 * These method will return the total no of videos in the list in the upper right corner. 
	 * @return String value of total no of videos.
	 */
	public String getTotalNoofvideos()
	{
		pause(5000);
		return getText(totalvideoslocator);
	}
	
	public void click_tileView()
	{
		click(tileviewbuttonlocator);
		customReport.reporter("Tile View is Clicked","");
		pause(5000);
	}
	
	public AvengerVideoBulkEditPage clickBulkEditoption(){
		click(bulkEditoptionlocator);
		customReport.reporter("Bulk Edit Option is Clicked","");
		pause(5000);
		return basePage.avengervideobulkeditpage(driver, customReport, basePage);
	}
	
	public String checktileview(){
		
		return getAttribute(tileviewbuttonlocator,"class");
	}
}
