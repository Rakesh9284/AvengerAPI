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
import com.vbrick.avenger.ObjProperty.AvengerMediaPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerUploadPropertyPage;
import com.vbrick.avenger.dao.AvengerEditVideoSettingsBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerMediaPage extends WebElements{

	private WebDriver driver;
	private CustomReport customReport;
	
	private BasePage basePage;
	private static Logger logger = Logger.getLogger(AvengerMediaPage.class);

	By uploadtablink=By.xpath(AvengerUploadPropertyPage.uploadpg_Uploadtab.getProperty());
	By filterOffButton=By.xpath(AvengerUploadPropertyPage.uploadpg_filterOff.getProperty());
	By videostablocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_videostablocator.getProperty());
	By categoriestablocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_categoriestablocator.getProperty());
	By videossearchlocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_videossearchlocator.getProperty());
	By videossearchbuttonlocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_videossearchbuttonlocator.getProperty());
    By playlisttablocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_playlisttablocator.getProperty());
	By pendingApprovalTabLink=By.xpath(AvengerMediaPropertyPage.avengermediapage_pendingApprovalTabLink.getProperty());
	By currentsortvaluelocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_currentsortlocator.getProperty());
	By allvideostablocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_allvideostablocator.getProperty());
	By browservideostablocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_browsevideostablocator.getProperty());
	
	
	public AvengerMediaPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport = customReport;
		this.basePage=basePage;
	}


	public AvengerVideosPage clickVideosTab()
	{
		click(videostablocator);
		customReport.reporter("videos tab is clicked", "");
		pause(10000);
		waitForElement(currentsortvaluelocator);
		logger.info("Sort with Title filter is visible on the page");
		return basePage.avengerVideosPage(driver,  customReport, basePage);
	}

	String sName=null;
	public String verify_textInMediaPage(String videotitle)
	{
		By videolocator = By.xpath("//*[(text()='"+videotitle+"')]");
		sName=getText(videolocator);
		customReport.reporter("The Playlist is Visible ", sName);
		return sName;
	}

	public AvengerUploadsPage click_MyVideosTab()
	{
		click(uploadtablink);
		customReport.reporter("Upload Tab is Clicked","");
		return  basePage.avengerUploadsPage(driver,  customReport, basePage);
	}

	public void clickCategoriesTab()
	{
		click(categoriestablocator);
		customReport.reporter("Libraries tab is clicked", "");
		
	}
	
	public String verify_libraryInMediaPage(String libraryname)
	{
		By librarylocator = By.xpath("//a[contains(text(),'"+libraryname+"')]");
		return getText(librarylocator);
	}
	
	public AvengerVideosInLibrariesPage click_Createdlibrary(String libraryname)
	{
		By librarylocator = By.xpath("(//*[contains(text(),'"+libraryname+"')])[last()]");
		clickUsingSwitch(librarylocator);
		pause(3000);
		customReport.reporter("Clicked on Created Teams",libraryname );
		return  basePage.avengerVideosInLibrariesPage(driver,  customReport, basePage);
	}

	public AvengerVideoCommentsPage click_Video(String videoname)
	{
		By librarylocator = By.xpath("//a[contains(text(),'"+videoname+"')]");
		click(librarylocator);
		customReport.reporter("Clicked on Created Video",videoname );
		return  basePage.avengerVideoCommentsPage(driver,  customReport, basePage);
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
	}


	public String verify_publicLibraryInMediaPage()
	{
		By publiclibrary=By.xpath("//*[@ng-show='publicLibrary']");
		return String.valueOf(elements(publiclibrary));
		
	}

	public void verify_VideoDeleted(String videotitle)
	{
		By mediasearchtextboxlocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_mediasearchtextboxlocator.getProperty());
		By mediasearchbuttonlocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_mediasearchbuttonlocator.getProperty());
		enterText(mediasearchtextboxlocator,  videotitle);
		customReport.reporter("Clicked on Media Search Button",videotitle);
		submit(mediasearchbuttonlocator);
		customReport.reporter("Clicked on Media Search Button","");
	}	
	
	public Map<String, String> get_categoryDetails(String libraryname)
	{
		Map<String,String> librarydetails= new HashMap<String, String>();
		By noofvideos=By.xpath("//*[contains(text(),'"+libraryname+"')]/../../h4");
		pause(5000);
		logger.info("No of Videos"+getText(noofvideos));
		logger.info("No of Videos values are "+getText(noofvideos).split(" ")[0]);
		librarydetails.put("noofvideos",getText(noofvideos).split(" ")[0]);
			return librarydetails;
	}
	
	public Map<String, String> zeroVideosDisplayed(String libraryname)
	{
		Map<String,String> librarydetails= new HashMap<String, String>();
		By noofvideos=By.xpath("//*[contains(text(),'"+libraryname+"')]/../../h4");
		pause(5000);
		logger.info("The displayed element is"+isDisplayed(noofvideos));
	    if(isDisplayed(noofvideos)==false)
	    {
	    librarydetails.put("noofvideos","novideosinlibrary");
	    }
        return librarydetails;
	}
	public AvengerPlaylistPage clickPlaylistTab()
	{
		click(playlisttablocator);
		customReport.reporter("Play List tab is clicked", "");
//		By playlistlocator=By.xpath("//div[@class='subnav-header margin-right-10 padding-top-5 mobile-hidden-medium']/span");
//		waitForElementPresent(playlistlocator);
		return basePage.avengerPlaylistPage(driver,  customReport, basePage);
	}	
	
	public  AvengerPlaylistPage click_playlist(String libraryname)
	{
		By  playlistlocator = By.xpath("//*[contains(text(),'"+libraryname+"')]/../../h4");
		click(playlistlocator);
		customReport.reporter("Clicked on Child Account Link","");
		return null;
		
	}
	
	public ArrayList<String> get_allLibrariesName()
	{
		pause(10000);
		By alllibrariesnamelocator=By.xpath("//*[@class='tile-item']/div[2]/div/h2");
		List<WebElement> alllibraries=getAllWebElementValues(alllibrariesnamelocator);
	   ArrayList<String> alllibrariesname = new ArrayList<String>();
		for (WebElement webElement : alllibraries) {
		 logger.info("Libraries Page is"+webElement.getText());
		 alllibrariesname.add(webElement.getText());
	}	
		return alllibrariesname;
	}
	
	public String verifyVideoPresent(AvengerEditVideoSettingsBeanPage avengerEditVideoSettingsPage){
		
		By videosearch=By.xpath("//h2[contains(@class,'bold')]//*[contains(text(),'"+avengerEditVideoSettingsPage.getTitle()+"')]");
		return String.valueOf(elements(videosearch));
	}
		
	public Boolean verifyCategory(String text)
	{
		By CategorySearch =By.xpath("//a[contains(text(),'"+text+"')]");
		return wait( CategorySearch);
		}

	public void click_pendingApprovalTab()
	{
		click(pendingApprovalTabLink);
		customReport.reporter("Pending Approval Tab is Clicked","");
	}
	
	
	public String verify_PendingApprovalTab()
	{
		return String.valueOf(elements(pendingApprovalTabLink));
	}
	
	public AvengerUploadsPage click_AllVideosTab()
	{
		click(allvideostablocator);
		logger.info("Clicked on All Videos tab locator");
		return basePage.avengerUploadsPage(driver,  customReport, basePage);	
	}
	
	public AvengerVideosPage click_AllVideos()
	{
		click(allvideostablocator);
		logger.info("Clicked on All Videos locator");
		return basePage.avengerVideosPage(driver,  customReport, basePage);	
	}
	
	public void click_BrowseVideos()
	{
		click(browservideostablocator);
	}
}
