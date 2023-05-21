package com.vbrick.avenger.pageobjects;

 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerCategoriesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerDashboardPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerEventDetailsPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerEventWebCastPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerHomePropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerMediaPropertyPage;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.AvengerEventDetailsBeanPage;
import com.vbrick.avenger.dao.EventPollBeanPage;
import com.vbrick.avenger.funUtil.TypeCasting;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;
import com.vbricks.avenger.service.IAPIConstantCodes;

public class AvengerEventWebCastPage extends WebElements {

	private static Logger logger = Logger.getLogger(AvengerEventsPage.class);


	private WebDriver driver;

	private CustomReport customReport;
	private Map<String, String> eventdetailsmap;
	private BasePage basePage;

	By startbroadcastlocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_startbroadcastlocator.getProperty());
	By stopbroadcastlocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_stopbroadcastlocator.getProperty());
	By leavewebcastlocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_leavewebcastlocator.getProperty());
	By exiteventoklocator = By.xpath(AvengerCategoriesPropertyPage.deletecategorypopuplocator.getProperty());
	By exiteventnolocator = By.xpath(AvengerCategoriesPropertyPage.deletecategorypopupnolocator.getProperty());
	By inprogresstextlocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_inprogresstextlocator.getProperty());
	By leaveconfirmationtext = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_leaveconfirmationtext.getProperty());
	By hostnotbroadcastingtextlocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_hostnotbroadcastingtextlocator.getProperty());
	By eventdescription = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_eventdescriptionlocator.getProperty());
	By eventtime=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_eventtimelocator.getProperty());
	By eventnotstartedtextlocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_eventnotstartedtextlocator.getProperty());
	By eventendedtextlocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_eventendedtextlocator.getProperty());
	By eventhostdisconnectedlocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_eventhostdisconnectedlocator.getProperty());
	By startwebcastbuttonlocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_startwebcastbuttonlocator.getProperty());
	By pollquestion=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_pollquestion.getProperty());
	By pollanswer1=By.name(AvengerEventWebCastPropertyPage.avengereventwebcastpage_pollanswer1.getProperty());
	By pollanswer2=By.name(AvengerEventWebCastPropertyPage.avengereventwebcastpage_pollanswer2.getProperty());
	By pollanswer3=By.name(AvengerEventWebCastPropertyPage.avengereventwebcastpage_pollanswer3.getProperty());
	By pollanswer4=By.name(AvengerEventWebCastPropertyPage.avengereventwebcastpage_pollanswer4.getProperty());
	By allowmultipleansweryes=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_allowmultipleansweryes.getProperty());
	By allowmultipleanswerno=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_allowmultipleanswerno.getProperty());
	By publishresults=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_publishresults.getProperty());
	By openpolllocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_openpoll.getProperty());
	By closepolllocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_closepoll.getProperty());
	By hideresultslocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_hideresultslocator.getProperty());
	By editbutton = By.xpath("//*[@ng-click='setEditView(activePoll)']");
	By attendeeslogo = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_attendeeslogo.getProperty());
	By questionclick=By.xpath("//*[contains(@aria-label,'Questions & Answers')]");
	By attendeesxpath = By.xpath("//*[@title='Attendees:']");
	By usercommentntimelocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_usercommenttimelocator.getProperty());
	By chatlocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_commentarealocator.getProperty());
	By commenttextboxlocator= By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_commenttextboxlocator.getProperty());
	By commentsumitbuttonlocator= By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_commentsubmitbuttonlocator.getProperty());
	By nextpoll = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_nextpoll.getProperty());
	By questionviewdropdown=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_questionviewdropdownlocator.getProperty());
	By inviteothersbutton=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_inviteothers.getProperty());
	By manualcontrolbutton=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_manualcontrolbutton.getProperty());
	By pptlocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_pptslide.getProperty());
	By eventstablink=By.xpath(AvengerDashboardPropertyPage.avengerdashboardpage_eventstablocator.getProperty());
	By mediatablink = By.xpath(AvengerDashboardPropertyPage.Avengerdashboardpage_mediatablocator.getProperty());
	By videostablocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_videostablocator.getProperty());
	By mediasearchtextboxlocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_mediasearchtextboxlocator.getProperty());
	By upload=By.name("upload");
	By notificationbuttonlocator=By.xpath(AvengerHomePropertyPage.homepg_notificationbuttonlocator.getProperty());
	By logoutlocator = By.cssSelector(AvengerHomePropertyPage.homepg_logoutlocator.getProperty());    
	By pollheadingtext=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_pollheadingtext.getProperty());
	By videobackgroundimage=By.xpath("//*[contains(@class,'webcast-wrap')]");
	By askquestionbuttonlocator=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastspage_clickaskbutton.getProperty());
	By askaquestiontextboxlocator=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastspage_questiontextbox.getProperty());
	By questionsubmitbutton=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastspage_questionsubmitbutton.getProperty());
	By postanonymouslocator=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastspage_postanonymouslycheckbox.getProperty());
	By publishToAllLocator=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastspage_publishtoAllcheckbox.getProperty());
	By replysubmitsavebutton=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastspage_replysubmitbutton.getProperty());
	By replycancelbutton=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastspage_replycanceltbutton.getProperty());
	By groupQuestionslinkLocator=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastspage_groupQuestionsLink.getProperty());
	By myQuestionslinkLocator=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastspage_myQuestionsLink.getProperty());
	By commentextlocator=By.xpath("//*[@download='chat.txt']");
	By downloadsbutton=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_downloadbutton.getProperty());
	By questioncsvlocator=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_questionanswercsvdownload.getProperty());
	By attendeesrefreshbutton = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_attendeesrefreshbutton.getProperty());
	By recordbuttonlocator=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_recordbutton.getProperty());
    By Okbuttonlocaor=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_okbuttonlocator.getProperty());



	public AvengerEventWebCastPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {

		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
	}

	public String verify_EventTitlePresent(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
	{
		By eventtitlelocator = By.xpath("//*[contains(text(),'"+avengereventdetailsbeanpage.getTitle()+"')]");
		customReport.reporter("The displayed event title is ", getText(eventtitlelocator));
		return getText(eventtitlelocator);
	}

	public void clickStartBroadCast()
	{
		click(startbroadcastlocator);
		customReport.reporter("Start Broadcast button is clicked", "");
	}
	public void click_uablestartrecordingwebcast()
	{
		click(Okbuttonlocaor);
		customReport.reporter("OK button is clicked", "");
	}

	
	public String getEventTime(String eventdescription)
	{
		By eventtime=By.xpath("//*[contains(text(),'"+eventdescription+"')]/../..");
		logger.info("THe Event time is"+getText(eventtime));
		return getText(eventtime);
	}

	public AvengerEventWebCastPage getDriverContorl(WebDriver driver,  CustomReport customReport)
	{
		return basePage.avengerEventWebCastPage(driver,  customReport, basePage);
	}

	public void clickStopBroadcast()
	{
		click(stopbroadcastlocator);
		customReport.reporter("Stop Broadcast button is clicked", "");
	}

	public String clickExit(String command)
	{
		customReport.reporter("Exit button is clicked", "");
		performBackOperation();
		pause(5000);
		String alertText=null;
		if(isAlertPresent())
		{
			Alert alert= driver.switchTo().alert();
			if(command.equals("Y"))
			{
				/*	click(exiteventoklocator);
				 */	 alert= driver.switchTo().alert();
				 if(alert!=null)
				 {
					 logger.info("Alert Text"+alert.getText());
					 alertText = alert.getText();
					 alert.accept();
				 }
			}
			else if (command.equals("N")) {
				alert.dismiss();
			}
		}
		return alertText;

	}	
	public String clickExitPopUP(String command)
	{
		customReport.reporter("Exit button is clicked", "");
		pause(5000);
		String alertText=null;
		if(isAlertPresent())
		{
			Alert alert= driver.switchTo().alert();
			if(command.equals("Y"))
			{
				alert= driver.switchTo().alert();
				 if(alert!=null)
				 {
					 logger.info("Alert Text"+alert.getText());
					 alertText = alert.getText();
					 alert.accept();
				 }

			}
			else if (command.equals("N")) {
				alert.dismiss();
				waitForElementPresent(leavewebcastlocator);
			}
		}
		return alertText;

	}	

	public void closeRecordingpopup()
	{
		pause(5000);
		click(exiteventoklocator);
		pause(3000);
	}

	public void verify_RecordingPopupandClose(){

		if(isDisplayedWithoutException(exiteventoklocator)){
			pause(5000);
			click(exiteventoklocator);
			logger.info("Recording Popup is visible on the page and clicked Ok");
			pause(3000);
		}

		else {
			logger.info("Recording Popup is Not visible on the page");
		}

	}

	public AvengerEventDetailsPage clickEndEventButton()
	{
		pause(7000);
		click(leavewebcastlocator);
		return basePage.avengerEventDetailsPage(driver,  customReport, basePage);
		
	}

	public Map<String,String> getHostNotBroadCastingData()
	{

		eventdetailsmap =new HashMap<String, String>();
		eventdetailsmap.put("EventRelatedText", getText(hostnotbroadcastingtextlocator));
		eventdetailsmap.put("EventDescription", getText(eventdescription));
		eventdetailsmap.put("EventTime", getText(eventtime));
		return eventdetailsmap;

	}

	public Map<String,String> getEventEndedData()
	{

		eventdetailsmap =new HashMap<String, String>();
		eventdetailsmap.put("EventRelatedText", getText(eventendedtextlocator));
		eventdetailsmap.put("EventDescription", getText(eventdescription));
		eventdetailsmap.put("EventTime", getText(eventtime));
		return eventdetailsmap;

	}

	public Map<String,String> getEventNotStartedData()
	{

		eventdetailsmap =new HashMap<String, String>();
		eventdetailsmap.put("EventRelatedText", getText(eventnotstartedtextlocator));
		eventdetailsmap.put("EventDescription", getText(eventdescription));
		eventdetailsmap.put("EventTime", getText(eventtime));
		return eventdetailsmap;

	}

	public void navigateToAnotherUrl(String url)
	{
		navigateBrowser(url);
	}

	public boolean moveToAlertBoxText(String alerttext)
	{
		return switchTOAlert(alerttext);
	}

	public void performBackOperation()
	{
		navigateBackward();
	}

	public void closeEventAttendeeBrowser()
	{
		quitBrower();
	}

	public void acceptAlertLeaveEvent()
	{
		acceptAlert();
	}

	public void rejectAlertStayInEvent()
	{
		dismissAlert();
	}

	public void clickOnNoOfAttendees()
	{
		By noofattendees = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_noofattendees.getProperty());
		click(noofattendees);
		pause(3000);
	}

	public Map<String, String> getEventAttendeeData(AddUserBeanPage adduserbeanpage)
	{
		Map<String, String > attendeemap = new HashMap<String, String>();
		By username = By.xpath("//*[text()='"+adduserbeanpage.getFirstname()+" "+adduserbeanpage.getLastname()+"']");
		By email = By.xpath("//*[contains(text(),'"+adduserbeanpage.getContactemail()+"')]");
		attendeemap.put("EventUser", getText(username));
		attendeemap.put("EventEmail", getText(email));
		return attendeemap;

	}

	public Map<String, String> verifyAttendeeDataPresentOrNot(AddUserBeanPage adduserbeanpage)
	{
		Map<String, String > attendeemap = new HashMap<String, String>();
		By username = By.xpath("//*[contains(text(),'"+adduserbeanpage.getFirstname()+" "+adduserbeanpage.getLastname()+"')]");
		By email = By.xpath("//*[contains(text(),'"+adduserbeanpage.getContactemail()+"')]");
		attendeemap.put("EventUser", String.valueOf(elements(username)));
		attendeemap.put("EventEmail", String.valueOf(elements(email)));
		return attendeemap;

	}

	public Map<String, String> getUserCommentDetails(int commentnumber)
	{
		pause(10000);
		Map<String, String> commentdetails = new HashMap<String, String>();
		By usercommentnamelocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_usercommentnamelocator.getProperty());
		By usercommentntimelocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_usercommenttimelocator.getProperty());
		By usercommentlocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_usercommentlocator.getProperty());
		commentdetails.put("CommentUserName", getTextMultiple(usercommentnamelocator,  commentnumber));
		commentdetails.put("Comment", getTextMultiple(usercommentlocator,  commentnumber));
		commentdetails.put("CommentTime",getTextMultiple(usercommentntimelocator,  commentnumber));
		return commentdetails;
	}


	public void enterCommentToHost(String comment)
	{
		By commenttextboxlocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_commentarealocator.getProperty());
		enterText(commenttextboxlocator,  comment);
		clickEnter(commenttextboxlocator);
		pause(10000);

	}


	public Map<String, String> getUserToHostCommentDetails(int commentnumber)
	{

		Map<String, String> commentdetails = new HashMap<String, String>();

		By usercommentnamelocator = By.xpath("(//vb-webcast-chat-sidebar//vb-profile-picture/../span)["+commentnumber+"]");
		By usercommentntimelocator = By.xpath("//*[@vb-tail='comments.length']//div[contains(@class,'hostPM')]/span[4]");
		By usercommentlocator = By.xpath("//*[@vb-tail='comments.length']//div[contains(@class,'hostPM')]/p");
		commentdetails.put("CommentUserName", getTextMultiple(usercommentnamelocator,  commentnumber));
		commentdetails.put("Comment", getTextMultiple(usercommentlocator,  commentnumber));
		commentdetails.put("CommentTime",getTextMultiple(usercommentntimelocator,  commentnumber));
		return commentdetails;
	}

	public Map<String, String> getHostCommentDetails(int commentnumber)
	{

		Map<String, String> commentdetails = new HashMap<String, String>();

		By usercommentnamelocator = By.xpath("//*[@vb-tail='comments.length']//div[contains(@class,'hostPM')]/span[2]");
		By usercommentntimelocator = By.xpath("(//vb-webcast-chat-sidebar//*[contains(@class,'type-italic')])["+commentnumber+"]");
		By usercommentlocator = By.xpath("(//vb-webcast-chat-sidebar//*[contains(@class,'type-italic')]/../p)["+commentnumber+"]");
		commentdetails.put("CommentUserName", getTextMultiple(usercommentnamelocator,  commentnumber));
		commentdetails.put("Comment", getTextMultiple(usercommentlocator,  commentnumber));
		commentdetails.put("CommentTime",getTextMultiple(usercommentntimelocator,  commentnumber));
		return commentdetails;
	}

	public void checkPostAnonymouslycheckbox()
	{
		By postanonymouslylocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_anonymouscommentlocator.getProperty());
		check_Checkbox(postanonymouslylocator);
		customReport.reporter("Clicked on post anonymously checkbox", "");
	}

	public void uncheckPostAnonymouslycheckbox()
	{
		By postanonymouslylocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_anonymouscommentlocator.getProperty());
		uncheck_Checkbox(postanonymouslylocator);
		customReport.reporter("Clicked on post anonymously checkbox", "");
	}
	
	public Map<String, String> enterComment(String comment)
	{
		By framelocator=By.xpath("//*[contains(@class,'frame')]");
		switchFrame(framelocator);
		By commenttextboxlocator=By.cssSelector("body");
		enterText(commenttextboxlocator,  comment);
		pause(5000);
	    frameDefaultContent();
		click(commentsumitbuttonlocator);
		pause(10000);
		Map<String, String> commentdetails = new HashMap<String, String>();
		commentdetails.put(IAPIConstantCodes.COMMENT, comment);
		By usercommenttimelocator=By.xpath("//*[contains(text(),'"+comment+"')]/../span[2]");
		String commenttime = getText(usercommenttimelocator);
		commentdetails.put("commentdate", removemillisecondsfromcurrentutctime());
		customReport.reporter("Comment entered", comment);

		return commentdetails;
	}

	public String removemillisecondsfromcurrentutctime( ) { 
		HashMap<String, String> dates = new HashMap<String, String>();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date d = new Date();
		DateTime dateTimeUtc = new DateTime(d, DateTimeZone.UTC);
		int milliseconds=dateTimeUtc.getMillisOfSecond();
		DateTime currentdate= dateTimeUtc.plusDays(0).plusSeconds(1).minusMillis(milliseconds);
		return currentdate.toString();	

	}
	public String enterCommentwithfonts(String comment,String font)
	{
		By framelocator=By.xpath("//*[contains(@class,'frame')]");
		switchFrame(framelocator);
		By commenttextboxlocator=By.cssSelector("body");
		enterText(commenttextboxlocator,  comment);
		pause(5000);
		WebElement l=driver.findElement(commenttextboxlocator);
	    // enter text then ctrl+a with Keys.chord()
	    String s = Keys.chord(Keys.CONTROL, "a");
	    l.sendKeys(s);
		By boldlocator=By.xpath("//*[contains(@title,'"+font+"')]");
		frameDefaultContent();
		click(boldlocator);
		click(commentsumitbuttonlocator);
		pause(10000);
		By usercommenttimelocator=By.xpath("//*[contains(@class,'_nQPu__comment ng-star-inserted')]//p");
		String commenttime = getAttribute(usercommenttimelocator,"innerHTML");
		customReport.reporter("html Comment entered", comment);
		return commenttime;
	}
	
	public void clickChatButton(){
		click(chatlocator);
		//waitForElement(pollheadingtext);
		logger.info("Chat header is visible after clicking chat button");
	}

	public void clickHostComment()
	{
		By hostcommentlocator = By.xpath("//label[2]/input");
		click(hostcommentlocator);
	}

	public void clickEveryoneComment()
	{
		By everyonecommentlocator = By.xpath("//label[1]/input");
		click(everyonecommentlocator);	
	}

	public Map<String,String> getEventHostDisconnectedData()
	{

		eventdetailsmap =new HashMap<String, String>();
		eventdetailsmap.put("EventRelatedText", getText(eventhostdisconnectedlocator));
		eventdetailsmap.put("EventDescription", getText(eventdescription));
		eventdetailsmap.put("EventTime", getText(eventtime));
		return eventdetailsmap;

	}

	public String verifyCommentsBoxIsVisible()
	{
		By commenttextboxlocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_commentarealocator.getProperty());
		return String.valueOf(elements(commenttextboxlocator));
	}

	public void clickSwapButton()
	{
		By swapbuttonlocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_swapbuttonlocator.getProperty());
		click(swapbuttonlocator);
	}

	public boolean verify_SwapButtonAvailability()
	{
		By swapbuttonlocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_swapbuttonlocator.getProperty());
		return isDisplayedWithoutException(swapbuttonlocator);
	}

	public String get_swapstatus()
	{
		By swapvaluelocator=By.xpath("//*[contains(@class,'webcast-main-content no-slides')]");
		pause(5000);
		return getAttribute(swapvaluelocator,"class");
	}

	public String verifyPPTVisibleOrNot()
	{
		By pptlocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_pptslide.getProperty());
		return String.valueOf(elements(pptlocator));
	}

	public String getPPTSlideNumberFromAttendee()
	{
		String pptslide =getText(pptlocator);
		String[] slides = pptslide.split("/");
		logger.info("The slide value is"+slides[0]);
		logger.info("The slide value is"+slides[1]);
		logger.info("Total slides are "+slides);
		logger.info("Slide number from attendee is "+slides[slides.length-1]);
		String slidenumber = slides[slides.length-1];
		logger.info("Slide number in attendee page is"+slidenumber.charAt(0));
		return String.valueOf(slides[0]);

	}

	public String getPPTSlideNumberFromHost()
	{
		By slidecounter = By.xpath("//*[contains(@class,'new_window')]/../../../div[1]");
		String slidenumber = getText(slidecounter);
		logger.info("Slide number from host is "+slidenumber.split("/")[0]);
		return slidenumber.split("/")[0];
	}

	public void clickPollNextSlide()
	{
		By nextslidelocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_nextpollslidelocator.getProperty());
		click(nextslidelocator);
		pause(10000);
	}

	public void clickPPTSlide(int slidenumber)
	{
		By slidelocator = By.xpath("//*[@class='slides-box']/img["+slidenumber+"]");
		click(slidelocator);
		logger.info("clicked slide no is "+getPPTSlideNumberFromHost());
		pause(10000);
	}

	public void clickPPTPreviousSlide()
	{
		By previousslidelocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_previouspptslidelocator.getProperty());
		click(previousslidelocator);
		pause(10000);
	}

	public String verifyPPTNextSlide()
	{
		By nextslidelocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_nextpptslidelocator.getProperty());
		return String.valueOf(elements(nextslidelocator));
	}

	public void clickPPTNextSlide(){
		By nextslidelocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_nextpptslidelocator.getProperty());
		click(nextslidelocator);
		pause(10000);

	}

	public Map<String, String> verifyVideoAndPlayer()
	{
		Map<String, String> videodetails = new HashMap<String, String>();
		By videoplaydetailslocator = By.xpath("//*[contains(@playbacks,'webcast.playbacks')]/div");

		videodetails.put("VideoURL", getAttribute(videoplaydetailslocator, "video-url"));
		videodetails.put("VideoPlayer", getAttribute(videoplaydetailslocator, "vb-player"));

		return videodetails;
	}

	public void waitForStartWebcastPresent()
	{
		waitForElementPresent(startwebcastbuttonlocator);
		pause(5000);
	}

	public String getNoOfAttendeesShown(String noofattendee)
	{
		By noofattendees=By.xpath("//*[contains(text(),'"+noofattendee+"')]");
		String noofattendeetext=getAttribute(noofattendees, "outerHTML");
		logger.info("The no of attendee is "+noofattendeetext);
		return noofattendeetext;
	}

	public AvengerViewAttendeePage clickShowAttenddeButton_SwitchToAnotherWindow()
	{
		waitForElementPresent(attendeeslogo);
		click(attendeeslogo);
		logger.info("clicked on the attendees logo in host browser");
		pause(8000);
		return basePage.avengerViewAttendeePage(driver,  customReport, basePage);
	}	
	public boolean verifyRecordButtonVisibility()
	{
		By recordbuttonlocator = By.xpath("(//span[@class='glyphicons record'])[1]/..");
		return isDisplayedWithoutException(recordbuttonlocator);
	}

	public void clickRecordButton()
	{
		By recordbuttonlocator = By.xpath("//*[contains(@ng-click,'toggleSetToRecord')]");
		click(recordbuttonlocator);
		logger.info("Clicked on Record Button");
	}

	public void clickRecordButtonwebcastpage(){
		click(recordbuttonlocator);
		logger.info("Clicked on Record Button");
	}

	public void clickStopRecording()
	{
		By stoprecordinglocator=By.xpath("//*[@ng-click='stopRecording()']");
		click(stoprecordinglocator);
	}

	public String callFlashObject(final String functionName,
			final String... args) {
		final Object result = ((JavascriptExecutor) driver).executeScript(
				makeJsFunction(functionName, args), new Object[0]);

		return result != null ? result.toString() : null;
	}

	private String makeJsFunction(final String functionName,
			final String... args) {
		final StringBuffer functionArgs = new StringBuffer();

		if (args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				if (i > 0) {
					functionArgs.append(",");
				}
				functionArgs.append(String.format("'%1$s'", args[i]));
				logger.info("functionArgs video -> " + functionArgs);
			}
		}
		if (functionName.equals("seek")) {
			logger.info("The value is"+String.format("return $('object')[0].seek(2)"));
			return String.format("return $('object')[0].seek(2);");
		}
		logger.info("the value---->"+String.format("return $('object')[0].%1$s(%2$s);", functionName,
				functionArgs));

		return String.format("return $('object')[0].%1$s(%2$s);", functionName,
				functionArgs);
	}

	public String videoPlayerType()
	{
		By videoPlayer= By.xpath("//*[@type='application/x-shockwave-flash']");
		String videoName=getAttribute(videoPlayer, "name");
		logger.info("Video Player is"+videoName);
		return getAttribute(videoPlayer, "name");

	}


	public void clickPlayVideoButton()
	{
		videoUtilFunctions(videoPlayerType(), "play");
		customReport.reporter("Play Video Button is Clicked",videoUtilFunctions(videoPlayerType(), "play") );
		pause(3000);

	}

	public String verify_label(String label)
	{
		By labellocator=By.xpath("//*[contains(text(),'"+label+"')]");
		return getText(labellocator);
	}

	public String verifyCommentDisplayed(String comment)
	{
		By commentlocator = By.xpath("//*[contains(text(),'"+comment+"')]");
		return String.valueOf(elements(commentlocator));
	}

	public boolean hostCommentDisplayedToHost()
	{
		By hostcommentlocator = By.xpath("//label[2]/input");
		return isDisplayed(hostcommentlocator);

	}

	public boolean everyoneCommentDisplayedToHost()
	{
		By everyonecommentlocator = By.xpath("//label[1]/input");
		return isDisplayed(everyonecommentlocator);

	}

	public Map<String, String> getCommentingUserdetails()
	{
		Map<String, String> commentmap = new HashMap<String, String>();
		By postanonymouslylocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_anonymouscommentlocator.getProperty());
		By commentingaslocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_commentingastext.getProperty());
		By commentingusernamelocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_commentingusername.getProperty());
		By commentinganonymouslocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_commentinganonymous.getProperty());

		pause(5000);
		commentmap.put("CommentingAs", getText(commentingaslocator));

		if(isSelected(postanonymouslylocator))
		{
			commentmap.put("CommentingUserName", getText(commentinganonymouslocator));
		}
		else{

			commentmap.put("CommentingUserName", getText(commentingusernamelocator));
		}

		return commentmap;
	}

	public String verifyPollsCount()
	{
		By pollcount = By.xpath("//span[@ng-show='webcast.polls.length']");
		return driver.findElement(pollcount).getText();
	}

	public void click_Comments()
	{
		pause(4000);
		click(downloadsbutton);
		pause(4000);
		click(commentextlocator);
	}

	public void create_Poll()
	{
		By createpollbutton=By.xpath("//*[@ng-click='setCreateView()']//*[@class='glyphicons plus']");
		click(createpollbutton);
	}

	public void addPoll(EventPollBeanPage eventpollbeanpage,int index)
	{
		enterTextMultiple(pollquestion,  eventpollbeanpage.getPollquestion(), index);
		enterTextMultiple(pollanswer1,  eventpollbeanpage.getPollanswer1(), index);
		enterTextMultiple(pollanswer2,  eventpollbeanpage.getPollanswer2(), index);
		enterTextMultiple(pollanswer3,  eventpollbeanpage.getPollanswer3(), index);
		enterTextMultiple(pollanswer4,  eventpollbeanpage.getPollanswer4(), index);
		if(eventpollbeanpage.getAllowmultipleanswer().equals("yes"))
			clickMultiple(allowmultipleansweryes,  index);	
		else
			clickMultiple(allowmultipleanswerno,  index);	
	}

	public void click_createButton()
	{
		By createbutton=By.xpath("//*[@ng-click='onSave()']");
		click(createbutton);
		pause(8000);
	}
	public void click_Polls()
	{
		pause(5000);
		By pollcount = By.xpath("//span[@title='Polls']/..");
		click(pollcount);
		waitForElement(pollheadingtext);
		logger.info("Poll heading is visble after clicking on polls");
	}

	public void click_EditButton()
	{
		By editbutton = By.xpath("//*[contains(text(),' Edit Settings ')]");
		click(editbutton);

	}
	public void click_deletePoll()
	{
		By deletepoll = By.xpath("//*[@ng-click='deletePoll(activePoll)']");
		click(deletepoll);
		logger.info("Poll is deleted");
		pause(10000);
	}

	public void openPoll()
	{
		clickDisplayedElement(openpolllocator);
		pause(3000);
		logger.info("Opened poll no ");
		waitForElement(closepolllocator);
		logger.info("close poll button is visible after clicking open poll button");
	}

	public void closePoll()
	{
		clickDisplayedElement(closepolllocator );
		pause(3000);
		logger.info("Closed poll no ");
	}

	public void clickNextPoll()
	{

		click(nextpoll);
		pause(3000);
		logger.info("Clicked on next poll");
	}

	public void clickPrevPoll()
	{
		By prevpoll = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_prevpoll.getProperty());
		click(prevpoll);
		pause(3000);
		logger.info("Clicked on prev poll");
	}

	public void respondToPoll(EventPollBeanPage eventPollBeanPage,int option)
	{
		By singleanswer = By.xpath("(//*[text()='"+eventPollBeanPage.getPollquestion()+"']/ancestor::*[contains(@class,'viewport')][1]//button[@ng-model='answer.checked'])["+option+"]");
		click(singleanswer);
		pause(8000);
		logger.info("Answer number for single select question is"+option);
	}

	public String getPollAnswerCount(EventPollBeanPage eventPollBeanPage, int option)
	{
		logger.info("The Event Question is"+eventPollBeanPage.getPollquestion());
		By pollanswercount = By.xpath("(//*[text()='"+eventPollBeanPage.getPollquestion()+"']/ancestor::*[contains(@class,'scroll')]//span)["+option+"]");
		logger.info("The Poll answer locator"+pollanswercount);
		return getText(pollanswercount);
	}

	public String getTotalResponses(String totalresponsestext)
	{
		By totalresponses=By.xpath("//*[@class='poll-sub-footer ng-binding']");
		logger.info("the total response is"+getText(totalresponses));
		return getText(totalresponses);
	}

	public void clickPublishResults()
	{
		pause(5000);
		click(publishresults);
		logger.info("Publish Results is clicked"); 
	}

	public Map<String, Boolean> verify_ButtonsAvailabilityinScreen()
	{
		Map<String, Boolean> buttonsavailability= new HashMap<String, Boolean>();
		buttonsavailability.put("publishresults",isDisplayedWithoutException(publishresults));
		buttonsavailability.put("openpoll",isDisplayedWithoutException(openpolllocator));
		buttonsavailability.put("closepoll",isDisplayedWithoutException(closepolllocator));
		buttonsavailability.put("editbutton",isDisplayedWithoutException(editbutton));
		return buttonsavailability;
	}

	public void clickHideResults()
	{
		click(hideresultslocator);
		logger.info("Hide Results Button is clicked");
	}

	public String getCommentsOnlyToHostText()
	{
		By commentstohosttext = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_commentsonlytohosttext.getProperty());
		return getText(commentstohosttext);
	}

	public void click_Attendees()
	{
		click(attendeesxpath);
		logger.info("Click on attendees in event");
	}

	public boolean verifyAttendeeslogo(){
		waitForElement(attendeeslogo);
		return isDisplayed(attendeeslogo);
	}
	public String  verifyNumberOfAttendees(String attendeecount){

		By noOfAttendee = By.xpath("//*[contains(text(),'"+attendeecount+"')]/..");
		logger.info("The value of the text for no of attendee is"+getText(noOfAttendee));
		return getText(noOfAttendee);
	}

	public void clickQuestion()
	{
		pause(10000);	
		click(questionclick);
		logger.info("clicked on question button");
	}

	public void addQuestion(String question)
	{
		click(askquestionbuttonlocator);
		logger.info("Clicked on Ask button");
		waitForElement(askaquestiontextboxlocator);
		logger.info("Question text box is visible on the page");
		pause(3000);
		enterText(askaquestiontextboxlocator, question);
	}

	public void clickQuestionSubmitButton()
	{
		clickUsingSwitch(questionsubmitbutton);
	}

	public void checkPostAnonymouslyQuestion()
	{
		check_Checkbox(postanonymouslocator);
	}

	public String getQuestionTimeStamp(String question)
	{
		By questiontimestampxpath=By.xpath("//*[contains(text(),'"+question+"')]/../..//*[contains(@class,'when-asked')]");
		return getText(questiontimestampxpath);
	}

	public String get_QAButtonStatus(String buttonstatus)
	{
		By QAButtonStatus=By.xpath("//*[contains(text(),'"+buttonstatus+"')]");
		return getAttribute(QAButtonStatus, "class");
	}

	public void clickQAButtonStatus(String buttonstatus)
	{
		By QAButtonStatus=By.xpath("//button[contains(text(),'"+buttonstatus+"')]");
		click(QAButtonStatus);
		pause(2000);
	}

	public boolean verifyNextPollSliderLocator(){

		return isDisplayedWithoutException(nextpoll);
	}

	public void OpennewWindow()
	{
		By opennewwindowlocator=By.xpath("//*[contains(@title,'new window')]/..");
		click(opennewwindowlocator);
	}

	public void clickQAButtonCSVDownload()
	{
		pause(4000);
		click(downloadsbutton);
		pause(4000);
		waitForElementPresent(questioncsvlocator);
		click(questioncsvlocator);
	}

	public void clickQuestionResponse(String Question,String Questionresponse)
	{
		pause(2000);
		By questionResponse=By.xpath("(//*[contains(text(),'"+Question+"')]/../../../..//*[contains(text(),'"+Questionresponse+"')]/..)[last()]");
		pause(3000);
		click(questionResponse);
		pause(3000);
	}

	public void replyQuestion(String replyanswer)
	{
		By replylocator=By.xpath("//*[contains(@name,'replyText')]");
		enterText(replylocator, replyanswer);
	}




	public void click_replyQuestionSaveButton()
	{
		click(replysubmitsavebutton);
		logger.info("Clicked on savebutton for Question reply popup ");
		pause(2000);
	}

	public String click_newQuestionsLink()
	{
		By newquestionlink=By.xpath("//*[contains(@class,'new-questions')]");
		String questionsno=getText(newquestionlink);
		click(newquestionlink);
		return questionsno;
	}

	public HashMap<String, String> getQuestionStatus(String Question,String replytext)
	{
		HashMap<String, String> questionstatusmap=new HashMap<String, String>();
		By questionreplylocator=By.xpath("//*[contains(text(),'"+Question+"')]/../..//*[contains(text(),'"+replytext+"')]");
		By questionreplyusernamelocator=By.xpath("//*[contains(text(),'"+Question+"')]/../..//*[contains(text(),'"+replytext+"')]/span");
		questionstatusmap.put("questionreply", getText(questionreplylocator));
		questionstatusmap.put("username",getText(questionreplyusernamelocator));

		return questionstatusmap;
	}

	public void click_questiondropdownmenu(String Question)
	{
		By questionreplylocator=By.xpath("//*[contains(text(),'"+Question+"')]/../../..//*[contains(@class,'more-icon')]/..");
		click(questionreplylocator);
	}

	public void click_QuestionDropDown(String button, String questionnumber)
	{
		By questiondropdown=By.xpath("(//*[contains(@ng-click,'addToQueue')]/../../li["+button+"])["+questionnumber+"]/a");
		click(questiondropdown);
		pause(2000);
	}

	public String get_replyText()
	{
		By replytext=By.xpath("//*[@ng-model='replyText']");
		return getAttribute(replytext, "value");
	}

	public AvengerDashboardPage avengerDashboardPage()
	{
		customReport.reporter("Avenger Dashboard Page is Clicked","");
		return basePage.avengerDashboardPage(driver,  customReport, basePage);
	} 

	public Map<String, String> get_QuestionDetails(int number)
	{
		Map<String, String> questiondetails=new HashMap<String, String>();
		By questiontext= By.xpath("//*[contains(@class,'question-qtext')]");
		questiondetails.put("questiontext", getTextMultiple(questiontext, number));
	    return questiondetails;	
	}

	public String get_QuestionNumber(String question)
	{
		By questionnumber=By.xpath("//*[contains(text(),'"+question+"')]/..//*[contains(@class,'question-number')]");
		return getText(questionnumber);
	}

	public String get_QuestionStatus(String question){
		By getquestionstautsinclosed= By.xpath("//*[contains(text(),'"+question+"')]/../../..//*[contains(@class,'question-status')]");
		return getText(getquestionstautsinclosed);
	}

	public void select_QuestionResponse(String question, String questionreply){

		By getreplystatus = By.xpath("//*[contains(text(),'"+question+"')]/../../..//*[contains(@class,'dropdown')]/ul/li//*[contains(text(),'"+questionreply+"')]");

		click(getreplystatus);
	}

	public String get_AnsweredBySpeakerText(String question, String replyspeakerstatus){

		By getspeakerreplystatus = By.xpath("//*[contains(text(),'"+question+"')]/../..//*[contains(text(),'"+replyspeakerstatus+"')]");
		return getText(getspeakerreplystatus);
	}

	public void click_QuestionViewDropDown(){
		click(questionviewdropdown); 
	}

	public void selectvalueQuestionViewDropDown(String dropdownvalue){
		By selectquestionviewdropdown=By.xpath("//*[contains(@class,'caret')]/../../ul//*[contains(text(),'"+dropdownvalue+"')]");
		click(selectquestionviewdropdown);
	}

	public String get_attendeequestionresponse(String question, String questionreply){
		By attendeviewhostreply=By.xpath("//*[contains(text(),'"+question+"')]/../..//*[contains(text(),'"+questionreply+"')]");
		return getText(attendeviewhostreply);

	}

	public void clickinviteothers(){
		click(inviteothersbutton);
	}

	public boolean verifyManualcontrolforautomatedwebcast(){

		return wait(manualcontrolbutton);
	}

	public List<String> getAttendeeData()
	{
		By attendees=By.xpath("//*[contains(@class,'attendee-name')]/..");
		List<String> attendeeslist=new ArrayList<String>();
		List<WebElement> attendeesvalues=getAllWebElementValues(attendees);
		for (WebElement webElement : attendeesvalues) {
			logger.info("The attendee value is"+webElement.getText());
			attendeeslist.add(webElement.getText());
		}
		return attendeeslist;	
	}

	public void clickmanualcontrolforautomatedwebcast(){
		click(manualcontrolbutton);
		logger.info("Clicked on Manual control button");
	}

	public boolean verify_endeventbutton(){
		return wait(eventendedtextlocator);
	}


	public LinkedHashMap<String, String> verifyeventguestfields(){

		LinkedHashMap<String,String> eventGuestLabels = new LinkedHashMap<String, String>();

		eventGuestLabels.put("eventstabforguest", TypeCasting.booleantoString(verifyElementClick(eventstablink)));
		eventGuestLabels.put("medialinkforguest", TypeCasting.booleantoString(verifyElementClick(mediatablink)));
		eventGuestLabels.put("videostabforguest", TypeCasting.booleantoString(verifyElementClick(videostablocator)));
		eventGuestLabels.put("mediasearchboxforguest", TypeCasting.booleantoString(verifyElementClick(mediasearchtextboxlocator)));
		eventGuestLabels.put("videouploadforguest", TypeCasting.booleantoString(verifyElementClick(upload)));
		eventGuestLabels.put("notificationforguest", TypeCasting.booleantoString(verifyElementClick(notificationbuttonlocator)));
		eventGuestLabels.put("logoutforguest", TypeCasting.booleantoString(verifyElementClick(logoutlocator)));
		return eventGuestLabels;
	}

	public String verifyBroadCastlabel(String labelname){
		By labellocator = By.xpath("//button[contains(text(),'"+labelname+"')]");
		return  getText(labellocator);
	}

	public String verifySetToRecordText(){
		
		return getText(recordbuttonlocator);
	}
	public String verify_BackgroundImageBroadCasting()
	{
		pause(10000);
		logger.info(getAttribute(videobackgroundimage,"class"));
		return getAttribute(videobackgroundimage,"class");
	}

	public String getAttendeCount() {
		By noofAttendeecount=By.xpath("//*[contains(text(),'Number Of Attendees :')]");
		return	getText(noofAttendeecount);
	}

	public void click_AttendeeRemoveButton(String value){
		By attendeeremovebutton = By.xpath("//*[contains(text(),'"+value+"')]/..//*[contains(@ng-click,'removeAttendee')]");
		waitForElementPresent(attendeeremovebutton);
		logger.info("The Object is visible on the page");
		click(attendeeremovebutton);
		logger.info("Clicked on Attendee remove button");
	}

	public void Click_AttendeeRestoreButton(String value){
		By attendeerestorebutton=By.xpath("//*[contains(text(),'"+value+"')]/..//*[contains(@ng-click,'restoreAttendee')]");
		click(attendeerestorebutton);
		logger.info("Clicked on Attendee Restore button");
	}

	public String verify_AttendeeRemovedtext(String value){
		By attendeeremoved = By.xpath("//*[contains(text(),'"+value+"')]/..");
		logger.info("The text for attendee is"+getText(attendeeremoved));
		return getText(attendeeremoved);
	}

	public void click_AcceptOrCancelAttendeeRemoveWarningPopup(String option){
		By cancelOrOkbuttonlocator=By.xpath("//*[@class='modal-footer']//*[contains(@ng-show,'"+option+"')]");
		click(cancelOrOkbuttonlocator);
		logger.info("selected option for warning alert");
	}

	public String verify_AttendeeRemoveErrorText(){
		By guesterrormsg= By.xpath("//div[contains(@ng-show,'isRemovedFromWebcast')]//div[contains(@class,'alert')]");
		return getText(guesterrormsg);
	}

	public String verify_unauthorizedtext(){
		By unauthorizedtext=By.xpath("//h1");
		return getText(unauthorizedtext);
	}

	public boolean verify_RemoveButton(String value){
		By attendeeremoved = By.xpath("//*[contains(text(),'"+value+"')]/../td[1]");
		return isDisplayedWithoutException(attendeeremoved);
	}

	public void check_publishtoall() {
		check_Checkbox(publishToAllLocator);
		logger.info("selected publish to all checkbox");
	}
	public void uncheck_publishtoall() {
		uncheck_CheckboxButton(publishToAllLocator,getAttribute(publishToAllLocator,"class"));
		logger.info("unchecked publish to all checkbox");
	}

	public boolean verify_publishtoallCheckbox(){
		logger.info("Public to all Check box is visible on the page");
		return isDisplayedWithoutException(publishToAllLocator);

	}
	public void click_replyQuestionCancelButton()
	{
		click(replycancelbutton);
		logger.info("Clicked on Cancel button for question reply popup");
		pause(2000);
	}

	public boolean verify_publishtoallIndicator(String question){
		By publishToAllIndicator=By.xpath("//*[contains(text(),'"+question+"')]/../../..//*[contains(@class,'glyphicons group')]");
		logger.info("Public to all Indicator is visible below the question");
		return isDisplayedWithoutException(publishToAllIndicator);

	}
	public void click_myQuestionlink(){
		click(myQuestionslinkLocator);
	}

	public void click_groupQuestionlink(){
		click(groupQuestionslinkLocator);
	}

	public boolean verifyUsernameDisplayed(String question){

		By getUsrNameofQuestionLocator=By.xpath("//*[contains(text(),'"+question+"')]/../../*[contains(@class,'question-info')]/p");
		return isDisplayedWithoutException(getUsrNameofQuestionLocator);

	}

	public String getSideBarQuestionsNotificationCount(){

		By sideBarNotificationLocator=By.xpath("//span[contains(@class,'glyphicons circle_question_mark')]/../span[3]");
		return getText(sideBarNotificationLocator);

	}
	public String getMyQuestionsNotificationCount(){


		return getText(myQuestionslinkLocator);

	}
	public String getGroupQuestionsNotificationCount(){


		return getText(groupQuestionslinkLocator);

	}


	public HashMap<String, String> getQuestionCardDetails(String questionNumber) {

		HashMap<String,String> map=new HashMap<String,String>();

		By questionLocator=By.xpath("//*[contains(@class,'card-holder')]["+questionNumber+"]//*[contains(@class,'question-qtext')]");

		By answerLocator=By.xpath("//*[contains(@class,'card-holder')]["+questionNumber+"]//*[contains(@class,'question-reply-by')]/..");

		By usernameLocator=By.xpath("//*[contains(@class,'card-holder')]["+questionNumber+"]//*[contains(@class,'question-reply-by')]");

		map.put("question", getText(questionLocator));
		map.put("answer", getText(answerLocator));
		map.put("username",getText(usernameLocator));

		return map;
	}

	public void clickPPTdownloadlink(String filename){
		By pptDownloadLocator=By.xpath("//*[contains(text(),'"+filename+"')]/../..");
		click(pptDownloadLocator);
	}

	public void click_attendeerefreshbutton()
	{
		click(attendeesrefreshbutton);
		pause(8000);
		logger.info("clicked on refresh button in events");
	}
	public String getEventTimeDetails(String eventName){
		By eventtimedetail=By.xpath("(//*[contains(text(),'"+eventName+"')])[2]/../h3");
		return getText(eventtimedetail);
	}

	public void verify_eventlabel(String eventname) {
		By eventlabel=	By.xpath("//*[contains(text(),'"+eventname+"')][contains(@tabindex,'0')]");
		
	}

  public void click_Embeddedcontent() {
	  By embeddedcontent=By.xpath("//button[contains(@aria-label,'Embedded Content')]");
	  click(embeddedcontent);
	  logger.info("Clicked on Embedded content");
  }

  public void click_sendalinkurltoallattendes() {
	  By embeddedcontent=By.xpath("//button[contains(@aria-label,'Send a Link / URL to all Attendees')]");
	  click(embeddedcontent);
	  logger.info("Clicked on  the button of Send a Link / URL to all Attendees");
  }
  
  public void click_Eventdetailsbutton() {
	  By embeddedcontent=By.xpath("//button[contains(@aria-label,'Event Details')]");
	  click(embeddedcontent);
	  logger.info("Clicked on Event details button");
  }

 public ArrayList<String> verify_embeddedlabel() {
	 By label=By.xpath("//*[contains(@name,'enableEmbeddedToggle')]//input");
	 ArrayList<String> List=getAttributeMultiple1(label, "aria-checked");
	 return List;
	 
 }
 
 public ArrayList<String> verify_pushlabel() {
	 By label=By.xpath("//*[contains(@name,'enablePushContentToggle')]//input");
	 ArrayList<String> List=getAttributeMultiple1(label, "aria-checked");
	 return List;
	 
 }
 

 public void click_AttendeesEngagements() {
	 By attendesengaementlocator=By.xpath("//*[@class='ihj6i__title'][contains(text(),' Attendee Engagements ')]");
	  click(attendesengaementlocator);
	  logger.info("Clicked on AttendesEngagement");
}

public void click_previewofsharelinkurl() {
	By previewlinklocator=By.xpath("//*[contains(@name,'ewPushContentLinks')]//*[contains(@aria-label,'Preview')]");
	click(previewlinklocator);
	logger.info("Clikced on preview link ");
	
}
public void click_endEvent() {
	By endeventbuttonlocator=By.xpath("//*[contains(@class,'__webcastStateControls')]/button");
	waitForElementPresent(endeventbuttonlocator);
	click(endeventbuttonlocator);
	logger.info("Clicked on End Event Button");
	pause(5000);
	click(exiteventoklocator);
	pause(2000);
	logger.info("Clicked on End event Ok Button");
}

}
 