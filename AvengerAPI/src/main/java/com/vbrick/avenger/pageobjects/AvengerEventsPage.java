package com.vbrick.avenger.pageobjects;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.interactions.touch.Scroll;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerEventDetailsPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerEventsPropertyPage;
import com.vbrick.avenger.dao.AvengerEventDetailsBeanPage;
import com.vbrick.avenger.funUtil.DateTime;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerEventsPage extends WebElements {

	private static Logger logger = Logger.getLogger(AvengerEventsPage.class);
	By startdatelocator=By.xpath(AvengerEventDetailsPropertyPage.avengereventsdetailspage_startdatelocator.getProperty());
	By monthvaluelocator=By.xpath(AvengerEventsPropertyPage.avengereventspage_monthvaluelocator.getProperty());
	By monthviewlocator=By.xpath(AvengerEventsPropertyPage.avengereventspage_monthviewlocator.getProperty());
	By weekviewlocator=By.xpath(AvengerEventsPropertyPage.avengereventspage_weekviewlocator.getProperty());
	By dayviewlocator=By.xpath(AvengerEventsPropertyPage.avengereventspage_dayviewlocator.getProperty());
	By changebuttonlocator=By.xpath(AvengerEventDetailsPropertyPage.avengereventsdetailspage_changepresentationprofilelocator.getProperty());
	By titlelocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_titlelocator.getProperty());
	By errortextlocator = By.xpath(AvengerEventsPropertyPage.avengereventspage_errortext.getProperty());
	By locklocator = By.xpath(AvengerEventsPropertyPage.avengereventspage_locklocator.getProperty());
	By daylocator = By.xpath(AvengerEventsPropertyPage.avengereventspage_weekpagedaylocator.getProperty());
	By hourslocator = By.xpath(AvengerEventsPropertyPage.avengereventspage_daypagehourslocator.getProperty());
	
	private WebDriver driver;
	
	private CustomReport customReport;
	private BasePage basePage;
	
	
	
	public AvengerEventsPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage = basePage;
	}

	public String get_Month()
	{
		logger.info("Month value is"+getText(monthvaluelocator));
		return getText(monthvaluelocator);
	}
	
	public void click_monthView()
	{
		click(monthviewlocator);
		customReport.reporter("Clicked on Month View ","");
	}
	
	public void click_weekView()
	{
		click(weekviewlocator);
		customReport.reporter("Clicked on Week View ","");
		customReport.reporter("Day's are visible after clicking week tab","");
	}

    public void click_dayView()
   {
	click(dayviewlocator);
	customReport.reporter("Clicked on Day View" ,"");
	waitForElement(hourslocator);
	customReport.reporter("Time slots are are visible on the page","");
   }
    
    public String get_dateFormatViews(String dayformat)
    {
    	By dayview=By.xpath("(//h2[contains(text(),'"+dayformat+"')])[2]");
    	String date=getText(dayview);
    	logger.info("Day value is"+ date);
    	return date;
    }
    
    public String get_dateFormatMonthViews(String dayformat)
    {
    	By dayview=By.xpath("//span[contains(text(),'"+dayformat+"')]");
    	String date=getText(dayview);
    	logger.info("Day value is"+ date);
    	return date;
    }
    public String get_highlightedSystemDate()
    {
    	By currentdatexpath=By.xpath("(//*[@data-date='"+DateTime.yyyyMMDD()+"'])[1]");
    	return getAttribute(currentdatexpath, "class");
    	
    }
    
    public AvengerEventDetailsPage  click_Date(String date)
    {
    	By futuredatexpath = By.xpath("(//*[contains(@data-date,'"+date+"')])[2]");
    	
    	if(driver.toString().contains("InternetExplorer")){
    	logger.info("Date value is"+date);
    	scrollClick(futuredatexpath);
    	customReport.reporter("Clicked on Date"+date,"");
         waitForElementPresent(titlelocator);
        
    }
    else {
    	logger.info("Date value is"+date);
    	waitForElementPresent(futuredatexpath);
    	clickWithoutJavaScript(futuredatexpath);
    	customReport.reporter("Clicked on Date"+date,"");
         waitForElementPresent(titlelocator);
    }
    	return basePage.avengerEventDetailsPage(driver,  customReport, basePage);
    }
 
    public String verifyEventCreation(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
    {
    	pause(5000);
        By createdeventxpath=By.xpath("//*[text()='"+avengereventdetailsbeanpage.getTitle()+"']");
    	logger.info("Event name in events page is"+avengereventdetailsbeanpage.getTitle());
    	return getText(createdeventxpath);
     
    }
    
    
    
    public AvengerEventDetailsPage clickCreatedEvent(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
    {
    	basePage.avengerDashboardPage(driver, customReport, basePage).click_EventsTab();
    	pause(5000);
    	By createdeventxpath=By.xpath("(//*[contains(text(),'"+avengereventdetailsbeanpage.getTitle()+"')])[last()]");
    	logger.info("The Event Title is:::"+avengereventdetailsbeanpage.getTitle());	
    	clickusingjavascript(createdeventxpath);
      	By eventform = By.xpath("//*[contains(text(),'Title')]");
        return basePage.avengerEventDetailsPage(driver,  customReport, basePage);
    }
    

    
    public int check_PublicOrPrivateEvent(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
    {
    	By createdeventlocator=By.xpath("//*[contains(text(),'"+avengereventdetailsbeanpage.getTitle()+"')]/..//*[contains(@class,'glyphicons lock')]");
    	return elements(createdeventlocator);
    }
    
    public int return_LockElements()
    {
    	return elements(locklocator);
    }
    
       
    public boolean verify_AddEventClickEnabled(String date)
    {
    	logger.info("Date value is"+date);
    	By futuredatexpath = By.xpath("//*[@data-date='"+date+"']");
    	click(futuredatexpath);
    	return isDisplayed(changebuttonlocator);
    }
    
    public int check_EventDeletionOnCalender(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
    {
    	By deletedeventlocator=By.xpath("//*[contains(text(),'"+avengereventdetailsbeanpage.getTitle()+"')])[last()]");
    	return elements(deletedeventlocator);
    }
    
    public String getEventStartTimeOnCalender(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
    {
    	
    	By eventstarttime = By.xpath("//*[contains(text(),'"+avengereventdetailsbeanpage.getTitle()+"')]/../span[2]");
    	String eventtime = getText(eventstarttime);
    	if(eventtime.endsWith("a"))
       	{
       		eventtime = eventtime.replace("a", " AM");
       	}
       	else{
       		eventtime = eventtime.replace("p", " PM");
       	}
    	return eventtime;
    }
    public Map<String,String> verifyupcomingEventCreation(AvengerEventDetailsBeanPage avengereventdetailsbeanpage){
        By upcomingneweventdatexpath=By.xpath("//h4[contains(text(),'"+avengereventdetailsbeanpage.getTitle()+"')]/../h1");
        By upcomingneweventxpath=By.xpath("//h4[contains(text(),'"+avengereventdetailsbeanpage.getTitle()+"')]");
        By upcomingneweventtime=By.xpath("//h4[contains(text(),'"+avengereventdetailsbeanpage.getTitle()+"')]/../../h5");
        By eventinupcomimgevents=By.xpath("//*[contains(text(),'Upcoming Events')]/../../../../../..//*[contains(text(),'"+avengereventdetailsbeanpage.getTitle()+"')]");
        Map<String,String> map=new HashMap<String,String>();
        map.put("eventname", getText(upcomingneweventxpath));  
        map.put("Date", getText(upcomingneweventdatexpath));        
        map.put("EventTime", getText(upcomingneweventtime));
        map.put("eventinupcomimgevents", getText(eventinupcomimgevents));
        return map;
        
       }
    
    public int verify_EventPresent(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
    {
    	By createdeventxpath=By.xpath("//*[text()='"+avengereventdetailsbeanpage.getTitle()+"']");
    	logger.info("Event name in events page is"+avengereventdetailsbeanpage.getTitle());
        return elements(createdeventxpath);
    }
    
    public boolean verify_EventAvailable(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
    {
    	By createdeventxpath=By.xpath("//*[text()='"+avengereventdetailsbeanpage.getTitle()+"']");
    	logger.info("Event name in events page is"+avengereventdetailsbeanpage.getTitle());
        return isDisplayedWithoutException(createdeventxpath);
    }
    
    public Integer eventCounts()
    {
    	By eventCount=By.xpath("//span[@title='fc-title']");
    	return elements(eventCount);
    }
    
    public String verify_eventpublicevent(String eventname){
    	By publiceventicon = By.xpath("//*[contains(@class,'fc-event-inner')]//*[contains(text(),'"+eventname+"')]/..//*[contains(@class,'global')]");
    	waitForElementPresent(publiceventicon);
    	return String.valueOf(elements(publiceventicon));
    }
    //API
    public ArrayList<String> get_allEventList()
    {
       	pause(3000);
    	By eventTitlelocator=By.xpath("//div[contains(@class,'souoC__fcTitle')]/.");
    	ArrayList<String> eventlist= getAttributeMultiple1(eventTitlelocator,"innerHTML");
  
    	return eventlist;
     }
    
	public String verify_text(String name) {
	By pushcontentname=	By.xpath("//*[contains(text(),'"+name+"')]");
	return getText(pushcontentname);
		
	}

	public void scroll_embeddedcontent()
	{
		By scrolllocator= By.xpath("//*[contains(@id,'ewPushContentEnabled')]");
		scrollClick(scrolllocator);
		
	}
 public String getlinkUI() {
	 By linktext=By.xpath("//*[contains(@class,'bulleted-items padding-top-15')]//a");
	String link=getAttribute(linktext, "href");
	 return link; 
 }
	
 public String embeddedcontentenabledordisabledUI() {
	 By embeddedcontent= By.xpath("//*[contains(@aria-labelledby,'ewEmbeddedContentEnabled')]//button[1]");
	 String attribute=getAttribute(embeddedcontent, "aria-checked");
	 return attribute;
 }
 
 public String pushcontentenabledordisabledUI() {
	 By embeddedcontent= By.xpath("//*[contains(@aria-labelledby,'ewPushContentEnabled')]//button[1]");
	 String attribute=getAttribute(embeddedcontent, "aria-checked");
	 return attribute;
 }
 
}
             