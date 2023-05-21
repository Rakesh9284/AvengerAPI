package com.vbrick.avenger.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerVideoCommentsPropertyPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerVideosInLibrariesPage extends WebElements{
	
	private WebDriver driver;
	private CustomReport customReport;
	
	private BasePage basePage;
	private static Logger logger = Logger.getLogger(AvengerVideosInLibrariesPage.class);
	By sparklogin=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_sparklogin.getProperty());
    By webExEmail=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_webExEmailAddresses.getProperty());
    By webExpassword=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_webExPassword.getProperty());
    By webExSubmitbutton=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_webExSubmit.getProperty());
    By webExnext=By.id(AvengerVideoCommentsPropertyPage.avengervideocommentspage_webExNext.getProperty());
    By webExsignin=By.id(AvengerVideoCommentsPropertyPage.avengervideocommentspage_webExSignin.getProperty());
    By webExsigninAccept=By.name(AvengerVideoCommentsPropertyPage.avengervideocommentspage_webExSigninAccept.getProperty());
    By selectSparkSampleRoom=By.name(AvengerVideoCommentsPropertyPage.avengervideocommentspage_selectSparkRoom.getProperty());
    By sharetoSparkMessage=By.name(AvengerVideoCommentsPropertyPage.avengervideocommentspage_sharetoSparkmessage.getProperty());
    By ciscoSparkEmail=By.id(AvengerVideoCommentsPropertyPage.avengervideocommentspage_ciscoSparkEmail.getProperty());
    By ciscoSparknextButton=By.id(AvengerVideoCommentsPropertyPage.avengervideocommentspage_ciscoSparknextButton.getProperty());
    By cisocSparkSkip=By.id(AvengerVideoCommentsPropertyPage.avengervideocommentspage_ciscoSparkSkip.getProperty());
    By ciscoSparkChatSampleRoom=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_ciscoSparkchatSampleroom.getProperty());
    By subscribeSpark=By.xpath("//button[@ng-click='subscribe()']");
    By unsubscribeSpark=By.xpath("//button[@ng-click='unsubscribe()']");
    By sparkpopupMessage=By.xpath("//div[@ng-bind-html='message']");
	By sparkpopupAccept=By.xpath("//*[@class='modal-footer']//*[@type='submit']");
	By sharetoSparkSave=By.xpath("//*[text()='Save']");
	By filterbuttonlocator=By.xpath("//*[@class='glyphicons filter']/..");
	By filtersdropdowns=By.xpath("//search-filters-sidebar");
	public AvengerVideosInLibrariesPage(WebDriver driver,  
			CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport = customReport;
		this.basePage=basePage;
	}

	
	public String verify_videoInLibrariesPage(String videoname)
	{
		By videolocator = By.xpath("//a[(text()='"+videoname+"')]");
		return getText(videolocator);
	}

	public  AvengerVideoCommentsPage click_videoInLibrariesPage(String videoname)
	{
		By videolocator = By.xpath("//a[(text()='"+videoname+"')]");
		click(videolocator);
		
		return basePage.avengerVideoCommentsPage(driver,  customReport, basePage);
	}

	public void selectDateFilter(String dateFilter, String selectedDatefilter)
	{
		By datefilter = By.xpath("//*[@id='ng-app']//*[contains(text(),'"+dateFilter+"')]/..");
		By sDatefilter = By.xpath("//*[contains(text(),'"+selectedDatefilter+"')]");
		click(datefilter);
		logger.info("Clicked on Date filter");
		click(sDatefilter);
	}

	public String getVideoUploadedDate()
	{
		By videoDateLocator = By.xpath("(//*[@class='tile-footer']/span[2])[1]");
		return getText(videoDateLocator);
	}

	public String verifyVideoPresent(String title){

		By videoTitlelocator = By.xpath("//*[.='"+title+"']");
		return String.valueOf(elements(videoTitlelocator));	}

	public String clickSubscribeSpark() {
		
		click(subscribeSpark);
		customReport.reporter("Subscribe button is present and Clicked","");
		return getText(subscribeSpark);
	}	
	public void clickStatus(String status) {
		By clickonStatusDropDown=By.xpath("(//*[contains(@ng-model,'statusFilter')])[1]");
		click(clickonStatusDropDown);
		By defaultactivelocator=By.xpath("//*[text()='"+status+"']");
		waitForElement(defaultactivelocator);
		click(defaultactivelocator);
	}
	
	public void clickFilterbutton(){
		waitForElementPresent(filterbuttonlocator);
		customReport.reporter("Filter button is viisble on the page", "");
		click(filterbuttonlocator);
		customReport.reporter("Clicked on filter button", "");
		waitForElementPresent(filtersdropdowns);
		customReport.reporter("Filter dropdowns are visible on the page after clicking on filter button", "");
	}
	
	public boolean verify_filterdropdown(){

	return isDisplayedWithoutException(filtersdropdowns);
	}
	
	 public void click_sparkLogin()
	    {
	    		click(sparklogin);
	    		customReport.reporter("Clicked on Share to Spark login Button","");
	    }
	    public void EnterWebExEmail(String Email)
	    {
	    	    enterText(webExEmail,  Email);
	    }
	    public void click_webexNext()
	  	{
	  		click(webExnext);
	  		customReport.reporter("Clicked on next Button","");
	  	}
	    public void EnterWebExpassword(String Email)
	    {
	    	    enterText(webExpassword,  Email);
	    }
	    public void click_Signin()
	  	{
	  		click(webExsignin);
	  		customReport.reporter("Clicked on Signin Button","");
	  	}
	    public void click_SparkAccept()
	  	{
	  		click(webExsigninAccept);
	  		customReport.reporter("Clicked on Accept Button","");
	  	}
	   
	    public void Selectsampleroom(String selectroom)
	    {
	    	selectValuefromDropDown(selectSparkSampleRoom, selectroom);
	    }
	    
	    public void click_sharetoSparkSaveButton()
	  	{	
	    	
	  		click(sharetoSparkSave);
	  		customReport.reporter("Clicked on share to spark send Button","");
	  	}

	    public String clickUnSubscribeSpark() {
			String unsubscribesparkmsg=getText(unsubscribeSpark);
			click(unsubscribeSpark);
			customReport.reporter("UnSubscribe button is present and Clicked","");
			return unsubscribesparkmsg;
		}
	    
	    public String click_SparkpopupAccept() {
	    	String sparkpopupmsg=getText(sparkpopupMessage);
			click(sparkpopupAccept);
			customReport.reporter("Accepted Spark pop up ",sparkpopupmsg);
			return sparkpopupmsg;
		}
	    
}
