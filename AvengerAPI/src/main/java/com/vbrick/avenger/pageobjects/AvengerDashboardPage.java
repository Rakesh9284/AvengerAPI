package com.vbrick.avenger.pageobjects;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerCategoriesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerDashboardPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerEventsPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerMediaPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerVideoCommentsPropertyPage;
import com.vbrick.avenger.dao.AvengerEventDetailsBeanPage;
import com.vbrick.avenger.funUtil.DateTime;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerDashboardPage extends WebElements{

	private WebDriver driver;

	private static Logger logger = Logger.getLogger(AvengerDashboardPage.class);
	CustomReport customReport;
	private BasePage basePage;

	By eventstablink=By.xpath(AvengerDashboardPropertyPage.avengerdashboardpage_eventstablocator.getProperty());
	By mediatablink = By.xpath(AvengerDashboardPropertyPage.Avengerdashboardpage_mediatablocator.getProperty());
	By mediatablink1 = By.xpath(AvengerDashboardPropertyPage.Avengerdashboardpage_mediatablocator1.getProperty());
	By errortextlocator = By.xpath(AvengerEventsPropertyPage.avengereventspage_errortext.getProperty());
	By videostablocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_videostablocator.getProperty());
	By mediasearchtextboxlocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_mediasearchtextboxlocator.getProperty());
	By mediasearchbuttonlocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_mediasearchbuttonlocator.getProperty());
	By firstVideoInRecentlyAddedVideosLocator=By.xpath(AvengerMediaPropertyPage.avengerMediaPage_firstVideoInRecentlyAddedVideosLocator.getProperty());
	By tileViewButtonLocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_tileViewButtonLocator.getProperty());
	By tileviewstarratinglocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_tileviewstarslocator.getProperty());
	By playlisttablocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_playlisttablocator.getProperty());
	By calenderlocator = By.xpath(AvengerDashboardPropertyPage.avengerdashboardpage_eventpageclanderlocator.getProperty());
	By deletebuttonlocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_deletebuttonlocator.getProperty());
	By deletecategorypopuplocator=By.xpath(AvengerCategoriesPropertyPage.deletecategorypopuplocator.getProperty());
	By featureVideoecarousellocator=By.xpath(AvengerDashboardPropertyPage.avengerdashboardpage_feaurecarousellocator.getProperty());
	By listviewsvideolocator=By.xpath(AvengerDashboardPropertyPage.avengerdashboardpage_listviewvideolocator.getProperty());
	By statusfilterbutton=By.xpath(AvengerDashboardPropertyPage.avengerdashboardpage_statusfilterbutton.getProperty());
	By statusfilterdropdown=By.xpath(AvengerDashboardPropertyPage.avengerdashboardpage_statusfilterdropdown.getProperty());
	By filterbuttonlocator=By.xpath(AvengerDashboardPropertyPage.avengerdashboardpage_filterbuttonlocator.getProperty());
	By filtersdropdowns=By.xpath(AvengerDashboardPropertyPage.avengerdashboardpage_filtersdropdowns.getProperty());
	By datefilterlocator=By.xpath(AvengerDashboardPropertyPage.avengerdashboardpage_datefilterbutton.getProperty());
	By datefilterdropdownlocator=By.xpath(AvengerDashboardPropertyPage.avengerdashboardpage_datefilterdropdowns.getProperty());
	By fromdatepickerlocator=By.xpath(AvengerDashboardPropertyPage.avengerdashboardpage_fromdatepicker.getProperty());
	By todatepickerlocator=By.xpath(AvengerDashboardPropertyPage.avengerdashboardpage_todatepicker.getProperty());
	By fromdatecalenderlocator=By.xpath(AvengerDashboardPropertyPage.avengerdashboardpage_fromdatecalender.getProperty());
	By fromdatetextboxlocator=By.name(AvengerDashboardPropertyPage.avengerdashboardpage_fromdatetextboxlocator.getProperty());
	By todatetextboxlocator=By.name(AvengerDashboardPropertyPage.avengerdashboardpage_todatetextboxlocator.getProperty());
	By firstVideoCountlistviewlocator=By.xpath(AvengerDashboardPropertyPage.avengerdashboardpage_firstvideolocator.getProperty());
	By allvideosautosuggestlocator=By.xpath("//typeahead-container//a");
	By createnewteam=By.xpath("//*[contains(@uisref,'team-edit')]");
	By viewallchannelslocator=By.xpath("//*[contains(@href,'#/teams')]");
	By channelsearchtextboxlocator=By.xpath("//*[contains(@placeholder,'Search Channels')]");
	By firstVideoCounttileviewlocator=By.xpath(AvengerDashboardPropertyPage.avengerdashboardpage_firstvideotileviewlocator.getProperty());
    By allviewscountlocator=By.xpath("//*[contains(@col-id,'viewCount')]//a");
	

	public AvengerDashboardPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		this.customReport= customReport;
		this.basePage=basePage;
	}



	public AvengerMediaPage clickMediaDropDown()
	{
		click(mediatablink);
		pause(3000);
		customReport.reporter("Media Tab is clicked", "");
		return basePage.avengerMediaPage(driver,  customReport, basePage);
	}
	public AvengerEventsPage click_EventsTab()
	{
		click(eventstablink);
		customReport.reporter("Events Tab is  Clicked","");
		waitForElement(calenderlocator);
		logger.info("The element is visible after clicking events tab");
		pause(10000);
		return basePage.avengerEventsPage(driver,  customReport, basePage);

	}

	public String click_EventsTabForErrorMessage()
	{
		click(eventstablink);
		customReport.reporter("Events Tab is Clicked","");
		waitForElementPresent(errortextlocator);
		return getText(errortextlocator);

	}

	public AvengerMediaPage clickMediaTab()
	{
		click(mediatablink);
		pause(3000);
		customReport.reporter("Media Tab is clicked", "");
		waitForElementPresent(playlisttablocator);
		return basePage.avengerMediaPage(driver,  customReport, basePage);
	}

	By listViewButtonLocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_listViewButtonLocator.getProperty());
	By titleNameLocator=By.xpath(AvengerMediaPropertyPage.avengerMediaPage_titleNameText.getProperty());
	/*
	 * Click the list view
	 */
	public void click_videoListView() {
		click(listViewButtonLocator);
		logger.info("Clicked on List View Button");
		waitForElement(titleNameLocator);
		logger.info("video is visible with title");
	}
	
	
	
		
		public ArrayList<String> getAllUploaderlist() 
		{
			pause(6000);
			ArrayList<String> webelementslist=getAllWebElementValueslist(By.xpath("//*[contains(@col-id,'ownerName')]//a"));
			webelementslist.remove("OWNER");
		    logger.info("UploadersList from UI"+webelementslist);
			return webelementslist;
	      }
		public ArrayList<String> getAllTimelist() 
		{
			pause(6000);
			ArrayList<String> webelementslist=getAllWebElementValueslist(By.xpath("//*[contains(@col-id,'duration')]//a"));
		    webelementslist.remove("TIME");
		    logger.info("TimeList from UI"+webelementslist);
			return webelementslist;
	      }
		
		public void click_UploaderSort()
		{
			pause(5000);
			By uploaderlocator=By.xpath("//*[contains(text(),'Owner')]");
			click(uploaderlocator);
			customReport.reporter("Clicked on Uploader Sort ", "");
			pause(5000);
		}
		
		public ArrayList<String> getAllVideolist() 
		{
			pause(6000);
			ArrayList<String> webelementslist=getAllWebElementValueslist(By.xpath("//*[contains(@col-id,'title')]/video-title-cell-renderer/a/span"));
		   return webelementslist;
	      }
		
		public ArrayList<String> getAllUploadedDatelist() 
		{
			pause(5000);
			waitForElementPresent(By.xpath("//*[contains(@col-id,'whenUploaded')]//a"));
			ArrayList<String> webelementslist=getAllWebElementValueslist(By.xpath("//*[contains(@col-id,'whenUploaded')]//a"));
			 webelementslist.remove("UPLOAD DATE");
			 logger.info("Uploaded Date from UI"+webelementslist);
			 return webelementslist;
	      }
	
		public void click_TimeSort()
		{
			pause(5000);
			By timelocator=By.xpath("//*[contains(text(),'Duration')]");
			click(timelocator);
			customReport.reporter("Clicked on Time Sort ", "");
			pause(5000);
		}
		public void click_TitleSort()
		{
			pause(8000);
			By namelocator=By.xpath("//*[contains(text(),'Title')]");
			click(namelocator);
			customReport.reporter("Clicked on Title  Sort ", "");
			pause(5000);
		}

		public void click_UploadedDateSort()
		{
			pause(8000);
			By uploadeddatelocator=By.xpath("//*[contains(text(),'Upload Date')]");
			click(uploadeddatelocator);
			customReport.reporter("Clicked on Uploaded Date Sort ", "");
			pause(5000);
		}
	public void searchMedia(String searchvideovalue)
	{
		logger.info("The value for the Url to be Search is"+searchvideovalue);
		enterText(mediasearchtextboxlocator,  searchvideovalue);
		pause(5000);
		clickEnterButton(mediasearchtextboxlocator);
		By videovalue = By.xpath("//*[contains(text(),'"+searchvideovalue+"')]");
		customReport.reporter("Clicked on Media Search Button","");
	}
	
	public String verify_Video(String svideoName)
	{
		By fileName = By.xpath("//a[text()='"+svideoName+"']");
		return getText(fileName);
	}

	public String verify_Label(String svideoName)
	{
		By fileName = By.xpath("//*[text()='"+svideoName+"']");
		return getText(fileName);
	}

	public void click_CurrentSortValue()
	{
		By currentsortvaluelocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_currentsortlocator.getProperty());
		click(currentsortvaluelocator);
		customReport.reporter("Clicked on Current Sort Value Drop Down", "");
	}

	public void selectSortValue()
	{
		By sortlocator =By.xpath("//*[contains(@ng-model,'sortField')]");
		selectValuefromDropDown(sortlocator, "Title");
	}
	public void click_Title()
	{
		By titlelocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_titlelocator.getProperty());
		click(titlelocator);
		pause(5000);
		customReport.reporter("Clicked on Title Drop Down", "");
	}

	public void click_UploadDate()
	{
		By uploaddatelocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_uploaddatelocator.getProperty());
		click(uploaddatelocator);
		customReport.reporter("Clicked on Upload Date ", "");
	}
	public void click_UploadedBy()
	{
		By uploadbylocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_uploadbylocator.getProperty());
		click(uploadbylocator);
		customReport.reporter("Clicked on Uploaded By Date ", "");
	}
	public String get_Video(String svideoName)
	{
		By fileName = By.xpath("//*[text()='"+svideoName+"']");
		return String.valueOf(elements(fileName));
	}

	public  void clickUpcomingEventsRightSlider() {
		By rightsliderlocator=By.xpath(AvengerDashboardPropertyPage.avengerdashboardpage_eventrightsliderlocator.getProperty());
		click(rightsliderlocator);
	}

	public  void clickUpcomingEventsLeftSlider() {
		By leftliderlocator=By.xpath(AvengerDashboardPropertyPage.	avengerdashboardpage_eventleftliderlocator.getProperty());
		click(leftliderlocator);
	}


	public List<String> getAllEventsInSlider(){
		By totaleventslocator=By.xpath(AvengerDashboardPropertyPage.avengerdashboardpage_totaleventslocator.getProperty());
		List<WebElement> webelement = getAllWebElementValues(totaleventslocator);
		List<String> eventsnameslist = new ArrayList<String>();

		for(WebElement ele:webelement)
		{
			logger.info("EVENt DATA:-" +ele.getText());
			eventsnameslist.add(ele.getText());
		}
		logger.info("The size is"+eventsnameslist.size());
		return eventsnameslist;
	}

	public String get_allSearchVideoDetailsTableView(String videoName,String videoType)
	{

		By videodetailslocator=By.xpath("//*[contains(text(),'"+videoName+"')]/../..//span[text()='"+videoType+"']");
		return getText(videodetailslocator);	  

	}

	public AvengerVideoCommentsPage clickVideo(String svideoName) 
	{
	    
		By fileName = By.xpath("(//*[contains(text(),'"+svideoName+"')])[last()]");
		pause(3000);
		click(fileName);
		customReport.reporter("Video Clicked",svideoName);
		return basePage.avengerVideoCommentsPage(driver,  customReport, basePage);

	}
	public AvengerVideoCommentsPage clickOnFirstVideoinRecentlyAddedVideos() {
		// TODO Auto-generated method stub
		click(firstVideoInRecentlyAddedVideosLocator);
		customReport.reporter("Clicked on first video in recently added videos","");
		return basePage.avengerVideoCommentsPage(driver,  customReport, basePage);
	}
	
	public AvengerLibraryInformationPage click_CreateNewTeam()
	{
		click(createnewteam);
		logger.info("Clicked on create team");
		return basePage.avengerLibraryInformationPage(driver, customReport, basePage);

	}

	public AvengerLibraryInformationPage click_ViewAllChannels()
	{
		click(viewallchannelslocator);
		logger.info("Clicked on view all channels");
		return basePage.avengerLibraryInformationPage(driver, customReport, basePage);

	}
	public AvengerLibraryInformationPage searchchannel()
	{
		click(channelsearchtextboxlocator);
		logger.info("Searching in channel");
		return basePage.avengerLibraryInformationPage(driver, customReport, basePage);

	}
	
	public void click_videoTileView() {
		click(tileViewButtonLocator);
		waitForElement(tileviewstarratinglocator);
	}

	public Map<String,String> verifyupcomingEventCreation(AvengerEventDetailsBeanPage avengereventdetailsbeanpage){
		By upcomingneweventdatexpath=By.xpath("//h4[contains(text(),'"+avengereventdetailsbeanpage.getTitle()+"')]/../h1");
		By upcomingneweventxpath=By.xpath("//h4[contains(text(),'"+avengereventdetailsbeanpage.getTitle()+"')]");
		By upcomingneweventtime=By.xpath("//h4[contains(text(),'"+avengereventdetailsbeanpage.getTitle()+"')]/../../h5");
		By eventinupcomimgevents=By.xpath("//*[contains(text(),'Upcoming Events')]/../../../../../..//*[contains(text(),'"+avengereventdetailsbeanpage.getTitle()+"')]");
		int i=0;
		logger.info("The size of the Event in upcoming events"+isDisplayed(upcomingneweventxpath));
		while(!isDisplayedWithoutException(upcomingneweventxpath))
		{
			i++;
			logger.info("The Event Value of i is"+i);
			clickUpcomingEventsRightSlider();
			if(i==10)
				break;
		}
		Map<String,String> map=new HashMap<String,String>();

		map.put("eventname", getText(upcomingneweventxpath));

		map.put("Date", getText(upcomingneweventdatexpath));

		map.put("EventTime", getText(upcomingneweventtime));
		map.put("eventinupcomimgevents", getText(eventinupcomimgevents));
		return map;
	}

	public String verifyVideoDeleted(String svideoName)
	{
		By fileName = By.xpath("//a[text()='"+svideoName+"']");
		return String.valueOf(elements(fileName));		
	}


	public LinkedHashMap<String, String> verifyvideodata(String label){
		LinkedHashMap<String, String> videouploaddetails = new LinkedHashMap<String, String>();
		By videostatus=By.xpath("//a[contains(text(),'"+label+"')]/..//span[contains(@class,'circle_minus')]");
		By uploaddate=By.xpath("//a[contains(text(),'"+label+"')]/../../div[2]/span[1]");
		By uploader=By.xpath("//a[contains(text(),'"+label+"')]/../../div[3]/span[1]");
		By timeduration=By.xpath("//a[contains(text(),'"+label+"')]/../../div[4]/span[2]");
		By viewers=By.xpath("//a[contains(text(),'"+label+"')]/../../div[5]");
		videouploaddetails.put("videostatus", String.valueOf(elements(videostatus)));
		videouploaddetails.put("uploaddate", getText(uploaddate));
		videouploaddetails.put("uploader", getText(uploader));
		videouploaddetails.put("time", getText(timeduration));
		videouploaddetails.put("viewercount", getText(viewers));
		logger.info("Map Value is"+videouploaddetails);
		return videouploaddetails;
	}

	public ArrayList<String> getvideoTVChannelsSlider(){
		By videointvchannel = By.xpath("//*[contains(text(),'Live Videos')]/../../../../../..//*[@class='carousel-inner']//*[contains(@class,'col-sm-3')]//h4/a");
		return getAllWebElementValueslist(videointvchannel);
	}


	public void verify_deleteVideonamebydavid(){
		searchMedia("david");
		By videotitle=By.xpath("(//a[contains(text(),'David De Hora')])[1]");
		while(!(elements(videotitle)==0)) {
			pause(2000);
			click(videotitle);
			click(deletebuttonlocator);
			logger.info("clicked on delete button for video");
			pause(3000);
			click(deletecategorypopuplocator);
			logger.info("Clicked on Video Delete Button");
			pause(3000);	
			searchMedia("david");
		}
		logger.info("The Video name with David is not visible on the page");
	}


	public String verify_featureCaroselPresent()
	{
		return String.valueOf(elements(featureVideoecarousellocator));
	}

	public String get_viewsforVideoInlistView()
	{
		return getText(listviewsvideolocator);
	}

	public void clickFilterbutton(){
		waitForElementPresent(filterbuttonlocator);
		customReport.reporter("Filter button is viisble on the page", "");
		click(filterbuttonlocator);
		customReport.reporter("Clicked on filter button", "");
		waitForElementPresent(filtersdropdowns);
		customReport.reporter("Filter dropdowns are visible on the page after clicking on filter button", "");
	}

	public void clickStatusfilter(){
		waitForElementPresent(statusfilterbutton);
		customReport.reporter("Status filter is visible on the page", "");
		click(statusfilterbutton);
		customReport.reporter("Clicked on status filter button", "");
		waitForElement(statusfilterdropdown);
		customReport.reporter("status filter drop down is visible on the page after clicking on clicking status filter", "");
	}

	public void select_ActiveOrInactiveFromStatusFilterDropDown(String option){
		click(statusfilterdropdown);
		customReport.reporter("Clicked on status filter dropdown", "");
		By activeInactiveoprtion=By.xpath("//*[contains(@name,'status')]//*[contains(text(),'"+option+"')]");
		click(activeInactiveoprtion);
		customReport.reporter("Selected option from status filter dropdown", option);
		pause(5000);
	}

	public void click_datefilter(){
		waitForElementPresent(datefilterlocator);
		customReport.reporter("date filter is visible on the page", "");
		click(datefilterlocator);
		customReport.reporter("Clicked on date filter button", "");
		waitForElement(datefilterdropdownlocator);
		customReport.reporter("date filter drop down is visible on the page after clicking on clicking date filter", "");
	}

	public void enter_FromDate(){
		By activedate=By.xpath("//*[contains(@class,'today')]");
		waitForElementPresent(fromdatepickerlocator);
		customReport.reporter("From Date picker is visible on the page", "");
		click(fromdatepickerlocator);
		customReport.reporter("Clicked on the From date picker to select date", "");
		waitForElementPresent(fromdatecalenderlocator);
		click(activedate);
		customReport.reporter("Selected active date", "");
	}

	public String get_EnteredValueFromDate(){
		return getAttribute(fromdatetextboxlocator,"value");
	}

	public String get_EnteredValueToDate(){
		return getAttribute(todatetextboxlocator,"value");
	}

	public void enter_ToDate(String currentdate){
		waitForElementPresent(todatetextboxlocator);
		customReport.reporter("To date textbox is visible on the page", "");
		enterText(todatetextboxlocator,currentdate);
	}

	public String getirstVideoNameinListView(){
		return getText(firstVideoCountlistviewlocator);
	}
	public String getirstVideoNameinTileView(){
		return getText(firstVideoCounttileviewlocator);
	}
	
	public void searchSuggestions(String searchvideovalue)
	{

		logger.info("The value for the Url to be Search is"+searchvideovalue);
		enterText(mediasearchtextboxlocator,  searchvideovalue);
		pause(5000);
	}
	
	 public ArrayList<String> get_allassignedgroups()
	 {
		 pause(3000);
	    List<WebElement> webelements= getAllWebElementValues(allvideosautosuggestlocator);
		ArrayList<String> suggestions =  new ArrayList<String>();
		for (WebElement webelement : webelements) {
		logger.info("Assigned  Groups are "+webelement.getText());
		suggestions.add(webelement.getText());
		}
		return suggestions;
	 }
	public String getviews_searchvideoPage(String viewscount) {
		By viewslocator=By.xpath("//a[contains(text(),'"+viewscount+"')]");
	
		String views=getText(viewslocator);
		 
		logger.info("Views Count of search page from UI::::::::"+views);
		return  views;
		
	}

}
