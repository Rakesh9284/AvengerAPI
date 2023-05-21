package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerCategoriesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerDashboardPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerHomePropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerMediaPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerUploadPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerVideoCommentsPropertyPage;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.dao.AvengerEditVideoSettingsBeanPage;
import com.vbrick.avenger.dao.AvengerManageCustomFieldsBeanPage;
import com.vbrick.avenger.dao.AvengerVideoCommentsBeanPage;
import com.vbrick.avenger.funUtil.DateTime;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerVideoCommentsPage extends WebElements {

	private static Logger logger = Logger.getLogger(AvengerVideoCommentsPage.class);

	By startAt= By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_startAtlocator.getProperty());
	By commentarealocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_commentarealocator.getProperty());	
	By postcommentbuttonlocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_postcommentbuttonlocator.getProperty());
	By uploadbuttonlocator=By.xpath("//a[contains(.,'Uploads')]");
	By commenterrortextlocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_commenterrortextlocator.getProperty());
	By alltagslocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_alltagslocator.getProperty()); 
	By replycommenttextboxlocator=By.xpath("(//*[contains(@name,'commentForm')]/textarea)[2]");
	By replysubmitbuttonlocator=By.xpath("(//*[contains(@ng-click,'addComment')])[2]");
	By mediatablink = By.xpath(AvengerDashboardPropertyPage.Avengerdashboardpage_mediatablocator.getProperty());
	By settingslinklocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_settingslinklocator.getProperty());
	By addtoplaylistlocator = By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_addtoplaylistbuttonlocator.getProperty());
	By savebuttonlocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_savebuttonlocator.getProperty());
	By deletebuttonlocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_deletebuttonlocator.getProperty());
	By addNewPlaylistlocator =By.xpath(AvengerVideoCommentsPropertyPage.avengeraddplaylist_addnewplaylistbuttonlocator.getProperty());
	By addnewplaylisttextlocator = By.name(AvengerVideoCommentsPropertyPage.avengeraddplaylist_addnewplaylisttextlocator.getProperty());
	By createnewplaylistsavebuttonlocator =By.xpath(AvengerVideoCommentsPropertyPage.avengercreateplaylist_savebuttonlocator.getProperty());
	By openplaylistModaltextlocator =By.xpath(AvengerVideoCommentsPropertyPage.avengeropenplaylistmodal_textlocator.getProperty()); 
	By deletecategorypopuplocator=By.xpath(AvengerCategoriesPropertyPage.deletecategorypopuplocator.getProperty());
	By settingsdropdownlocator=By.xpath("//*[contains(@class,'dropDownButton')]");
	By ratingslocator=By.xpath(AvengerUploadPropertyPage.uploadpg_ratingslocator.getProperty());
	By commentsbuttonlocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_commentsbuttonlocator.getProperty());
	By signInLocator= By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_signInLocator.getProperty());
	By submitforapprovalbutton= By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_submitforapprovalbutton.getProperty());
	By chooseapprovallocator= By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_chooseapprovallocator.getProperty());
	By approveButtonLocator= By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_approveButtonLocator.getProperty());
	By rejectButtonLocator= By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_rejectButtonLocator.getProperty());
	By approveRejectCommentTextboxLocator = By.name(AvengerVideoCommentsPropertyPage.avengervideocommentspage_approveRejectCommentTextboxLocator.getProperty());
	By approveRejectCommentOKButtonLocator = By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_approveRejectCommentOKButtonLocator.getProperty());
	By approveRejectCommentCancelButtonLocator = By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_approveRejectCommentCancelButtonLocator.getProperty());
	By contentApprovalTextboxLocator= By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_contentApprovalTextboxLocator.getProperty());
	By embedsizelocator=By.id(AvengerVideoCommentsPropertyPage.avengervideocommentspage_embedsizelocator.getProperty());
    By subtitlelocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_videosubtitlebuttonlocator.getProperty());
    By subtitlelanguagelist=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_videosubtitlelanguagelist.getProperty());
	By vidoePlay= By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_videoplaybutton.getProperty());
	By logoutlocator = By.xpath(AvengerHomePropertyPage.homepg_logoutlocator.getProperty());    
    By sharingbuttonlocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_sharingbuttonlocator.getProperty());
    By sharetoSparklocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_sharetosparklocator.getProperty());   
    By sparklogin=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_sparklogin.getProperty());
    By webExEmail=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_webExEmailAddresses.getProperty());
    By webExpassword=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_webExPassword.getProperty());
    By webExSubmitbutton=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_webExSubmit.getProperty());
    By webExnext=By.id(AvengerVideoCommentsPropertyPage.avengervideocommentspage_webExNext.getProperty());
    By webExsignin=By.id(AvengerVideoCommentsPropertyPage.avengervideocommentspage_webExSignin.getProperty());
    By webExsigninAccept=By.name(AvengerVideoCommentsPropertyPage.avengervideocommentspage_webExSigninAccept.getProperty());
    By selectSparkSampleRoom=By.name(AvengerVideoCommentsPropertyPage.avengervideocommentspage_selectSparkRoom.getProperty());
    By sharetoSparkMessage=By.name(AvengerVideoCommentsPropertyPage.avengervideocommentspage_sharetoSparkmessage.getProperty());
    By ciscoSparkEmail=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_ciscoSparkEmail.getProperty());
    By ciscoSparknextButton=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_ciscoSparknextButton.getProperty());
    By cisocSparkSkip=By.id(AvengerVideoCommentsPropertyPage.avengervideocommentspage_ciscoSparkSkip.getProperty());
    By ciscoSparkChatSampleRoom=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_ciscoSparkchatSampleroom.getProperty());
    By sharetoSparkSend=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_sharetoSparkSend.getProperty());
    By optionsbuttonlocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_optionsbuttonlocator.getProperty());
    By webexvideouploader=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_uploader.getProperty());
	By webexvideouploadtime=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_uploadtime.getProperty());
	By embedwidthlocator=By.name(AvengerVideoCommentsPropertyPage.avengervideocommentspage_embedwidthlocator.getProperty());
	By embedheightlocator=By.id(AvengerVideoCommentsPropertyPage.avengervideocommentspage_embedheightlocator.getProperty());
	By embedautoplaylocator=By.id(AvengerVideoCommentsPropertyPage.avengervideocommentspage_embedautoplaylocator.getProperty());
	By videoURL=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_videoUrl.getProperty());
	By replytext=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_replytext.getProperty());
	By childcomment=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_childcomment.getProperty());
	By submitforapprovalbuttondropdown= By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_submitforapprovaldropdownbutton.getProperty());
	By approvaldropdown=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_approvaldropdown.getProperty());
	By playlocator=By.xpath("//*[@ng-click='$ctrl.API.playPause()']");
	By videoplaylocator=By.xpath("//videogular");
	By clickcsv=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_clickcsv.getProperty());
	By fullscreenlocator=By.xpath(AvengerVideoCommentsPropertyPage.avengerfullscreen.getProperty());
	By replaceVideoLocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_replaceVideo.getProperty());
	By replaceVideoFile=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_replaceVideoFile.getProperty());
	By confirmReplacevideoOkButtonLocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_confirmReplaceVideoOkButton.getProperty());
	By uploadDateLocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_uploaddatelocator.getProperty());
	By videoProcessCompleteLocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_videoprocesscompeltetextlocator.getProperty());
	By processCompelteOkButton=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_processcompleteOkButton.getProperty());
	By videoReplacevisualIndicatorLocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_videoReplacevisualIndicator.getProperty());
	By restoreVideoButtonLocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_restorevideoLocator.getProperty());
	By restoreVideoOkButtonLocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_restorevideoOkButtonLocator.getProperty());
	By noHoursErrorMessageLocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_nohourserrormessage.getProperty());
	By playbackratelocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_playbackratelocator.getProperty());
	By playbackratebuttonlocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_playbackratebuttonlocator.getProperty());
	By embednoHoursErrorMessageLocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_embednohourserrormessage.getProperty());
	By EditVideoSettingsDropDownLocator=By.xpath("//*[contains(@ng-if,'video.legalHold') and contains(text(),'Edit Video Settings')]");
	By videoflagbuttonlocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_videoflagbuttonlocator.getProperty());
	By inappropriatetextboxlocator=By.name(AvengerVideoCommentsPropertyPage.avengervideocommentspage_inappropriatetextboxlocator.getProperty());
	By inappropriatesubmitbuttonlocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_inappropriatesubmitbuttonlocator.getProperty());
	By Allvideostablocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_allvideostablocator.getProperty());
	By browsevideoslocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_browsevideoslocator.getProperty());
	By playlisttablocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_playlisttablocator.getProperty());
	By myvideosbutton=By.xpath(AvengerVideoCommentsPropertyPage.uploadpg_myvideos.getProperty());
	By transcodeglyphiconbutton=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_transcodeglyphiconbutton.getProperty());
	By transcodingvalues=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_transcodingvalues.getProperty());
	By vidoeoptionsavailabledropdown=By.xpath(AvengerMediaPropertyPage.avengermediapage_videooptionsavaiable.getProperty());
	By bulkeditbuttonlocator=By.xpath(AvengerUploadPropertyPage.uploadpg_bulkeditlocator.getProperty());
	By ownernamelocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_ownername.getProperty());
	By totalviewslcator=By.xpath(AvengerVideoCommentsPropertyPage.avngervideocommentpage_totalviews.getProperty());
	By totalviewstextlocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentpage_totalviewstext.getProperty());
	By downloadbuttonlocator=By.xpath("//*[contains(@href,'downloads')]/span[1]");
	By deletevideorejected=By.xpath("//*[contains(@class,'resubmit-section')]/div/button[1]");
	By resubmitrejectedvideo=By.xpath("//*[contains(@class,'resubmit-section')]/div/button[2]");
	By basicsettingslocator=By.xpath("//span[contains(text(),' Basic Settings ')]");//("(//ol/li//*[contains(@uisref,'video-settings')])[last()]");
	By advancedsettingslocator=By.xpath("//span[contains(text(),'Advanced Settings')]");//("//*[contains(@heading,'Advanced Settings')]/a");
	By activestarslocators = By.xpath("//*[contains(@class,'bs-rating-star active')]");
	By showembedcodetextarealocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_showembedcodetextarealocator.getProperty());
	By noofratings =By.xpath("//*[contains(@class,'ratingCountText')]");
	By allcategorieslocator = By.xpath("//a[@uisref='portal.media.category-detail']");
	By CancelButtonLocator= By.xpath("(//*[contains(text(),'Cancel')])[2]");
	By totalviews=By.xpath("(//*[contains(@ng-show,'totalViewsSinceUpload')])[1]");
	By uploadername=By.xpath("//*[contains(text(),'Uploader')]/../div");
	By uploaddate=By.xpath("//*[contains(text(),'Upload Date')]/../div");
	By videoDuration=By.xpath("//vg-time-display");
	By publicPassword=By.xpath("//span[@ng-model='$ctrl.password']");
	By ownernameoninformationlocator=By.xpath("(//*[contains(@class,'SawMy__sectionBodyBlock KoiNW__sectionBody')])[1]");
	By uploadernameoninformationlocator=By.xpath("(//*[contains(@class,'SawMy__sectionBodyBlock KoiNW__sectionBody')])[5]");
	
	
	private WebDriver driver;

	
	private CustomReport customReport;
	private BasePage basePage;
	
	public AvengerVideoCommentsPage getDriverContorl(WebDriver driver,  CustomReport customReport)
	{
		return basePage.avengerVideoCommentsPage(driver,  customReport, basePage);
	}
	public String setVideoStatus(String sFunctionName)
	{
		String videoPlayer = videoPlayerType();
		return videoUtilFunctions(videoPlayer,sFunctionName);
	}
	
	public float get_videoRunningDuration() {
		String videoPlayer = videoPlayerType();
		Object object = null;
		object = ((JavascriptExecutor) driver)
				.executeScript(" return document.getElementById('" + videoPlayer + "').getCurrentTime()");
		
		logger.info("Vidoe Running Duration Value "+object.toString());
		customReport.reporter("The Running Duration is", object.toString());
		String sData[]=object.toString().split("\\.");
		pause(3000);
	/*	logger.info("Lenght--"+sData.length);
		logger.info("000000"+sData[0]);
		logger.info("11111111111"+sData[1]);*/
		logger.info("@@@"+(float)Integer.parseInt(sData[0])/100);
		return (float)Integer.parseInt(sData[0])/100;
	}
	public void clickPlayButton()
	{
		wait(vidoePlay);
		mouseOver(vidoePlay);
		click(vidoePlay);
	}
	public String getVideoStatus()
	{
		waitForElement(vidoePlay);
	    return getAttribute(vidoePlay, "class");
	}
	
	public void clickvideofullscreenbutton(){
		mouseOver(fullscreenlocator);
		click(fullscreenlocator);
	}
	
	
	public String getVideoButton()
	{
		mouseOver(vidoePlay);
		waitForElementPresent(vidoePlay);
		return getAttribute(vidoePlay, "class");
	}
	
	
	
	 public boolean getVideoStatus(String expectedStatus)
     { 
		int flag = 0;
		String videoPlayer = videoPlayerType();
		Object object = null;
		for (int i = 1; i <=20; i++) {
			try {
				object = ((JavascriptExecutor) driver)
						.executeScript(" return document.getElementById('" + videoPlayer + "').getState()");
				if (!object.toString().equals(expectedStatus)) {
					logger.info("Waiting-- expected and actual not equal"+"--->"+object.toString()+"-----"+(expectedStatus));
					pause(100*i);
				} else {
					logger.info("expected and actual are equal"+object.toString()+"-----"+(expectedStatus));
					break;
				}
				logger.info("The expected status is" + expectedStatus);
			} catch (org.openqa.selenium.WebDriverException e) {
				pause(100*i);
				logger.info("I am in Exception block of playing video");
			}
		}
		return object.toString().contains(expectedStatus);
     }

	 public String thumbnailforVideo()
	 {
		 By thumbNail= By.xpath("//vb-overlay-play-controls//button[contains(@aria-label,'Play (k)')]");
		 return String.valueOf(elements(thumbNail));
	 }
	 
	 public String getThumbnailImageData()
	    {
			By imagelocator=By.xpath("//video-thumbnail//img");  //("//vg-poster/img")
	    	return getAttribute(imagelocator,"outerHTML");
	    } 	
	 
	public String videoPlayerTypevlc()
	{
		By videoPlayer= By.xpath("//*[@type='application/x-vlc-plugin']");
		String videoName=getAttribute(videoPlayer, "nodeValue");
		logger.info("Video Player is"+videoName);
		return getAttribute(videoPlayer, "nodeValue");

	}

	/**
	 * Gets the name of the player in the current page
	 * @return Player name
	 */
	public String videoPlayerType()
	{
		Object playerName=null ;
		for(int i=0;i<=10;i++){
		Object pageState=((JavascriptExecutor) driver).executeScript(" return document.readyState");
		pause(5000);
		logger.info("Page state"+pageState.toString());
		if(pageState.toString().equalsIgnoreCase("complete"))
		{		 playerName = ((JavascriptExecutor) driver)
				.executeScript(" return document.getElementsByTagName('object')[0].getAttribute('name')");
		logger.info("Video Player is-----"+playerName .toString());
		break;
		}
		}
		return playerName.toString();
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




	public AvengerVideoCommentsPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {

		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
	}
	
	

	public void addComment(AvengerVideoCommentsBeanPage avengervideocommentsbeanpage)
	{
		click(commentsbuttonlocator);
		enterText(commentarealocator, avengervideocommentsbeanpage.getComment());
		customReport.reporter("Comment added is",getAttribute(commentarealocator, "value"));
		click(postcommentbuttonlocator);
		customReport.reporter("The Post Comment Button is Clicked","");
		waitForElement(replytext);
		logger.info("Reply link text is visible on the page");
	}
	
	public void click_commentsLink()
	
	{
		
		click(commentsbuttonlocator);
		
	}

	public String get_commentText(AvengerVideoCommentsBeanPage avengervideocommentsbeanpage)
	{
		By commenttextlocator=By.xpath("//*[text()='"+avengervideocommentsbeanpage.getComment()+"']");
		return getText(commenttextlocator);

	}

	public String get_commentErrorText(AvengerVideoCommentsBeanPage avengervideocommentsbeanpage)
	{
		click(commentsbuttonlocator);
		enterText(commentarealocator, avengervideocommentsbeanpage.getComment());
		return getText(commenterrortextlocator);
	}

	public String get_commentUserName(AddVideoCommentBean addvideocommentbean)
	{
		By commentusernamelocator=By.xpath("//*[@class='ssEQ3__author theme-accent-border']");
		String commetuploadername = getText(commentusernamelocator);
		logger.info("Comment User Name is"+getText(commentusernamelocator));
		String[] firstname=commetuploadername.split("\\s+");
		return firstname[0];
		 
	}

	public String get_commentDate(AvengerVideoCommentsBeanPage avengervideocommentsbeanpage)
	{
		By commentDatelocator=By.xpath("//*[contains(text(),'"+avengervideocommentsbeanpage.getComment()+"')]/../../../../header/div[2]/div[2]/div");
		logger.info("Comment Date is"+getText(commentDatelocator));
		return getText(commentDatelocator);
	}

	public String verify_commentAreaAvailability()
	{
		pause(5000);
		return String.valueOf(elements(commentarealocator));
	}

	public String deleteCommentText(AvengerVideoCommentsBeanPage avengervideocommentsbeanpage)
	{
		By deletecommenttextlocator=By.xpath("//*[contains(text(),'"+avengervideocommentsbeanpage.getComment()+"')]/../../../..//*[contains(@ng-click,'eventTypes.Delete')]");
		logger.info("Deleting Comment Value is"+getText(deletecommenttextlocator));
		click(deletecommenttextlocator);
		By commenttextlocator=By.xpath("//*[text()='"+avengervideocommentsbeanpage.getComment()+"']");
		return String.valueOf(elements(commenttextlocator));
	} 


	public ArrayList<String> get_Tags()
	{
		List<WebElement> alltags= getAllWebElementValues(alltagslocator);
		ArrayList<String> alltagslist= new ArrayList<String>();
		for (WebElement tag : alltags) {
			alltagslist.add(tag.getText());
		}
		return alltagslist;

	}

	public Map<String, String> get_videoDetails(AvengerEditVideoSettingsBeanPage avengereditvideosettingsbeanpage)
	{
		By categorylocator=By.xpath("//*[text()='"+avengereditvideosettingsbeanpage.getCategories()+"']");
		By descriptionlocator=By.xpath("//*[text()='"+avengereditvideosettingsbeanpage.getDescription()+"']");
		By titlelocator=By.xpath("//*[text()='"+avengereditvideosettingsbeanpage.getTitle()+"']");
		Map<String,String> videodetailslist= new HashMap<String,String>();
		videodetailslist.put("category",getText(categorylocator));
		videodetailslist.put("description",getText(descriptionlocator));
		videodetailslist.put("title",getText(titlelocator));
		return videodetailslist;
	}
	
	public String get_videostatus()
	{
		By videostatusxpath=By.xpath("//*[contains(@id,'Status')]//button[contains(@class,'Active')]");
		return getText(videostatusxpath);
	}
	
	public void clickBasicSettings()
	{
		click(basicsettingslocator);
	}
	
	public void reply_Comment(AvengerVideoCommentsBeanPage avengervideocommentsbeanpage)
	{
		By replycommentxpath=By.xpath("//*[contains(text(),'"+avengervideocommentsbeanpage.getComment()+"')]/../../../..//*[contains(@ng-click,'eventTypes.Reply')]");
		clickUsingSwitch(replycommentxpath);
		logger.info("Clicked on reply comment button");
		avengervideocommentsbeanpage.setComment(RandomStringUtils.randomAlphabetic(10));
		enterText(replycommenttextboxlocator, avengervideocommentsbeanpage.getComment());
		clickUsingSwitch(replysubmitbuttonlocator);
		logger.info("Clicked on reply comment submitt button");
		waitForElement(childcomment);
		logger.info("reply comment is succefully sumitted");
	}

	public void ratingVideo(int nooftimes)
	{
		By ratinglocator = By.xpath("//*[contains(@class,'nav-pills')]//*[@id='tab_ratings']/a");
		if(isDisplayed(ratinglocator))
		{
			click(ratinglocator);
			pause(3000);
		}
		logger.info("Ratings Video method");
		By ratingsxpathlocator=By.xpath("//*[contains(@ng-model,'rating')]/i["+nooftimes+"]");
		logger.info("Click element displayed"+isDisplayed(ratingsxpathlocator));
		click(ratingsxpathlocator);
		logger.info("clicked on ratings locator");

	}
	
	public AvengerVideoCommentsPage clickCancel()
	{
		By settingslinklocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_settingslinklocator.getProperty());
		click(CancelButtonLocator);
		customReport.reporter("Cancel Button Clicked", "");
		customReport.reporter("Video Comments Page is displayed", "");
		By alertlocator=By.xpath("//*[contains(@class,'modal-footer')]//*[contains(@type,'submit')]");
		click(alertlocator);
		return basePage.avengerVideoCommentsPage(driver,  customReport, basePage);
	}

	public void overWritingExistingRating(int nooftimes)
	{
		By ratinglocator = By.xpath("//*[contains(@class,'nav-pills')]//*[@id='tab_ratings']/a");
		if(isDisplayed(ratinglocator))
		{
			click(ratinglocator);
			pause(3000);
		}
		logger.info("Ratings Video method");
		By ratingsxpathlocator=By.xpath("//*[contains(@ng-model,'rating')]/i["+nooftimes+"]");
		logger.info("Click element displayed"+isDisplayed(ratingsxpathlocator));
		click(ratingsxpathlocator);
		logger.info("clicked on ratings locator");

	}

	public String getRatingofExistingUser()
	{
		logger.info("Size of Active stars are"+elements(activestarslocators));
		return String.valueOf(elements(activestarslocators)); 
	}

	public String getAverageRating()
	{
		By averageratinglocator = By.xpath("//*[contains(@class,'glyphicons star active')]");
		logger.info("Size of Active stars are"+elements(averageratinglocator));
		return String.valueOf(elements(averageratinglocator));  	
	}

	public int check_RatingsScale()
	{
		By ratinglocator = By.xpath("//*[contains(@class,'nav-pills')]//*[@id='tab_ratings']/a");
		if(isDisplayed(ratinglocator))
		{
			click(ratinglocator);
			pause(3000);
		}

		By ratingsscale=By.xpath("//*[contains(@ng-model,'rating')]/i");
		logger.info("Rating Scale in range of"+elements(ratingsscale));
		return  elements(ratingsscale);

	}
	
	public void clickVideoBasicinformationButton()
	{
		By videosidebuttonlocator=By.xpath("(//*[contains(@class,'sidebarButton ')])[1]");
		click(videosidebuttonlocator);
	}

	public AvengerUserDashboardPage clickoncategorypicker()
	{
		By categorypicker=By.xpath("//*[@data-placeholder='Pick a Category']");
		click(categorypicker);
		return basePage.avengerUserDashBoardPage(driver,  customReport, basePage);
	}
	public AvengerEditVideoSettingsPage click_settingsLink()
	{
	 	{
			pause(2000);
			customReport.reporter("Video Settings link is visible on the page","");
			click(settingsdropdownlocator);
			customReport.reporter("Clicked on video Settings Link","");
			return basePage.avengerEditVideoSettingsPage(driver,  customReport, basePage);
			}
	}


	public boolean verifyRatingAvailability()
	{
		By ratingbarlocator = By.xpath("//*[@class='rating ng-binding']/../..");
		pause(5000);
		return isDisplayedWithoutException(ratingbarlocator);
	}
	
	public boolean verifyIfVideoIs360()
	{
		By is360locator = By.xpath("//*[@class='vb-icon vb-icon-360-indicator']");
		pause(5000);
		return isDisplayedWithoutException(is360locator);
	}
	

	public boolean verifyIfVideoIsUnlisted()
	{
		By isunlistedlocator = By.xpath("//*[@class='vb-icon vb-icon-unlisted-video']");
		pause(5000);
		return isDisplayedWithoutException(isunlistedlocator);
	}
	
    public void click_CancelButton()
    {
	 click(CancelButtonLocator);
    }

	public String getNOOfRatings()
	{
		pause(4000);
		logger.info("The no of ratings is"+getText(noofratings));
		return getText(noofratings);
	}

	
	public void playwithOutAPI()
	{
		By videoprocessinglocator = By.xpath("//*[contains(text(),'Video is still processing, check back later.')]");
		By videouploadinglocator = By.xpath("//*[contains(text(),'Video is still uploading, check back later.')]");

		for (int i=0; i<5;i++)
		{
			if(elements(videoprocessinglocator)>0 || elements(videouploadinglocator)>0)
			{
				logger.info("In video "+i+"nd processing loop");
				pause(30000);
			}
			else
			{
				logger.info("In break");
				break;
			}
		
		}
	
		waitForElement(vidoePlay);
		click(vidoePlay);
		customReport.reporter("Play Button is clicked", "");
	}
	
	public void clickPlayVideoButton()
	{
		By videoprocessinglocator = By.xpath("//*[contains(text(),'Video is still processing, check back later.')]");
		By videouploadinglocator = By.xpath("//*[contains(text(),'Video is still uploading, check back later.')]");
		for (int i=0; i<5;i++)
		{
			if(elements(videoprocessinglocator)>0 || elements(videouploadinglocator)>0)
			{
				logger.info("In video "+i+"nd processing loop");
				pause(30000);
			}
			else
			{
				logger.info("In break");
				break;
			}
		
		}
		customReport.reporter("Play Video Button is Clicked",videoUtilFunctions(videoPlayerType(), "play") );
		pause(3000);
	}
	public boolean verify_settingLink()
	{
		return locatorsVisibilityAsPerRoles(settingslinklocator);
	}

	public boolean verify_AddToPlaylistButton()
	{
		return locatorsVisibilityAsPerRoles(addtoplaylistlocator);
	}


	public boolean clickSave()
	{   
		waitForElementPresent(savebuttonlocator);
		customReport.reporter("Playlist Save button is visible on the page", "");
		click(savebuttonlocator);
		customReport.reporter("Save button clicked::", "PlayList");
		return waitForElementEnable(addtoplaylistlocator);
	}
	
	public String verify_FeaturePlaylistSelected(String playlistName)
	{
		By featureplaylistcheckboxlocator = By.xpath("//span[contains(text(),'"+playlistName+"')]/../button");
		pause(3000);
		return getAttribute(featureplaylistcheckboxlocator,"class");
	}
	public boolean verify_deletebuttonlocator()
	{
		return locatorsVisibilityAsPerRoles(deletebuttonlocator);
	}

	public String verifyPlaylistCreation(String playlistName)
	{
		By playlistNamelocator = By.xpath("//*[text()='"+playlistName+"']");
		String playListName=getText(playlistNamelocator);
		customReport.reporter("Created Playlist Name", playlistName);
		return playListName;
	}

	public void clickAddToPlaylistButton()
	{ 
		waitForElementPresent(addtoplaylistlocator);
		customReport.reporter("Add to playlist button is visible on the page", "");
		clickUsingSwitch(addtoplaylistlocator);
		customReport.reporter("Add to playlist button is clicked", "");
		waitForElementPresent(addNewPlaylistlocator);
		customReport.reporter("Added to playlist pop-up is displayed", "");
	}

	public boolean verify_FeaturedVideo_PlaylistOnTop(String playlistName)
	{
		By allplaylistlcoator = By.xpath("//*[@class='file-name table-cell flex-fill']");
		List<WebElement> listOfPlaylist = getAllWebElementValues(allplaylistlcoator); 
		WebElement element = listOfPlaylist.get(1);
		customReport.reporter("The Video Playlist with the Name"+playlistName+"is Present at the Top of the Playlist Table","");
		return element.getAttribute("textContent").contains(playlistName);
	}

	public void check_checkBox_AddVideoToPlaylist(String playlistName)
	{
		By check_Boxlocator = By.xpath("//*[contains(text(),'"+playlistName+"')]/../button");
		waitForElementPresent(check_Boxlocator);
		customReport.reporter("Playlist Video check box is present on the page" , "");
		check_Checkbox(check_Boxlocator);
		customReport.reporter("Clicked on Featured Vidoes Check box in Playlist" , "");
	}

	public void uncheck_AddtoPlaylist(String playlistName)
	{
		By check_Boxlocator = By.xpath("//*[contains(text(),'"+playlistName+"')]/../button");
		uncheck_CheckboxButton(check_Boxlocator,getAttribute(check_Boxlocator,"class"));
		customReport.reporter("Unchecked the playlist",playlistName);
	}
	
	public void addNewPlaylist_UncheckCheckBox(String playlistName)
	{
		By uncheck_checkboxlocator = By.xpath("//input[@name='playlistName']/../..//*[@class='glyphicons ok_2']");


		click(addNewPlaylistlocator);
		customReport.reporter("The Add New Playlist Button is CLicked","");
		enterText(addnewplaylisttextlocator,  playlistName);
		customReport.reporter("The Playlist name in Text Box::", playlistName);
		click(uncheck_checkboxlocator);
		customReport.reporter("The Check Box is Unchecked","");
		click(createnewplaylistsavebuttonlocator);
		customReport.reporter("The Save Button is Click to Create the New Playlist","");
	}

	public boolean verifyCheckBox_IsChecked(String playlistName)
	{
		By playlistNamelocator = By.xpath("//*[text()='"+playlistName+"']/../div");
		return isSelected(playlistNamelocator);
	}

	public String getcheckBoxAttribute(String playlistName){
		By playlistNamelocator = By.xpath("//*[text()='"+playlistName+"']/../button");
		String value= getAttribute(playlistNamelocator,"class");
        return value;
	}

	public void addNewPlaylist_CheckBoxChecked(String playlistName)
	{
		By playlistNamelocator = By.xpath("//*[text()='"+playlistName+"']");
		click(addNewPlaylistlocator);
		logger.info("The Add New Playlist Button is Clicked");
		customReport.reporter("The Add New Playlist Button is Clicked","");
		enterText(addnewplaylisttextlocator,  playlistName);
		customReport.reporter("Play List Name ::", playlistName);
		logger.info("Play List Name ::"+playlistName);
		click(createnewplaylistsavebuttonlocator);
		customReport.reporter("The Save Button is Clicked to Create the New Playlist","");
		logger.info("The Save Button is Clicked to Create the New Playlist");
		waitForElementPresent(playlistNamelocator);
		customReport.reporter("Playlist Created sucessfully", "");
		logger.info("Playlist Created sucessfully");
	}

	public String verify_PlaylistModalText()
	{
		return getText(openplaylistModaltextlocator);
	}

	public boolean verify_Playlist_IsPresent(String playlistName)
	{
		By playlistNamelocator = By.xpath("//*[text()='"+playlistName+"']");
		return isDisplayed(playlistNamelocator);
	}

	public String verify_Count(String playlistName)
	{
		pause(5000);

		By playlistCountlocator = By.xpath("//*[contains(text(),'"+playlistName+"')]/../../div[2]/span");
		logger.info("The value for the No of Videos are"+getText(playlistCountlocator));
		return getText(playlistCountlocator);
	}
	
	public String getPlaylistDate(String playlistName)
	{
		By playlistDatelocator = By.xpath("//*[text()='"+playlistName+"']/../../div[3]");
		By playlistnamelocator = By.xpath("//*[text()='"+playlistName+"']");

		waitForElementPresent(playlistnamelocator);
		return getText(playlistDatelocator);
	}

	public boolean isDateTimeModified(String createdDate,String modifiedDate)
	{
		DateTime dateTime = new DateTime();
		int timeDiff = dateTime.timeDifferenceMMddyyyyHHmm(createdDate,modifiedDate);
		if(timeDiff>0 || timeDiff<0)
		{
			customReport.reporter("The Playlist is Being Modified","");
			return true;
		}
		return false;
	}


	public boolean verify_VideoTitleText(String title)
	{
		By videoTitlelocator = By.xpath("//h1[contains(text(),'"+title+"')]");
		logger.info("The Title is Present"+videoTitlelocator);
		return isDisplayed(videoTitlelocator);
	}
	
	public boolean verify_playbuttonisPresent()
	{
		By videoplaybuttonlocator = By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentpg_playbuttonlocator.getProperty());
		return isDisplayed(videoplaybuttonlocator);
	}
	public String checkPlayListAvailability(String playlistName)
	{
		By playlistNamelocator = By.xpath("//*[text()='"+playlistName+"']");
		return String.valueOf(elements(playlistNamelocator));
	}
	public String verify_videotitleEdited(AvengerEditVideoSettingsBeanPage editvideosettingsbean)
	{
		By editedvideotitle=By.xpath("//h1[contains(@class,'hidden-sm')][text()='"+editvideosettingsbean.getTitle()+"']");
		return getText(editedvideotitle);
	}

	public AvengerMediaPage click_deleteButton()
	{ 
		click(deletebuttonlocator);
		pause(3000);
		click(deletecategorypopuplocator);
		customReport.reporter("Clicked on Video Delete Button","");
		pause(15000);
		return basePage.avengerMediaPage(driver,  customReport, basePage);
	}
	
	public AvengerEditVideoSettingsPage clickBasic_Settings()
	{
		click(basicsettingslocator);
		return basePage.avengerEditVideoSettingsPage(driver, customReport, basePage);
	}
	
	public AvengerEditVideoSettingsPage clickAdvanced_Settings()
	{
		click(advancedsettingslocator);
		return basePage.avengerEditVideoSettingsPage(driver, customReport, basePage);
	}

	public int verify_deleteVideoPopUp()
	{
		By videoprocessinglocator = By.xpath("//*[contains(text(),'Video is still processing, check back later.')]");
		By videouploadinglocator = By.xpath("//*[contains(text(),'Video is still uploading, check back later.')]");

		for (int i=0; i<5;i++)
		{
			if(elements(videoprocessinglocator)>0 || elements(videouploadinglocator)>0)
			{
				logger.info("In video "+i+"nd processing loop");
				pause(10000);
			}
			else
			{
				logger.info("In break");
				break;
			}
		
		}
		click(deletebuttonlocator);
		pause(3000);
		return elements(deletecategorypopuplocator);
	}

	public String check_enableRatingsDisplayed()
	{
		return String.valueOf(elements(ratingslocator));
	}
	public String getvideoID()
	{
		pause(5000);
		String[] currenturl = getCurrentURL().split("/");
		pause(25000);
		logger.info("Video id is"+currenturl[currenturl.length-1]);
		return currenturl[currenturl.length-1];
	}
	
	public ArrayList<String> get_AllCategoriesOfVideo()
	{

		ArrayList<String> catNames= new ArrayList<String>();
		for (WebElement categoryname : getAllWebElementValues(allcategorieslocator)) 
		{
			logger.info("Category  text is"+categoryname.getText());
			catNames.add(categoryname.getText());	
		}
		return catNames;
	}

	public void clickcategoryLink(String categoryname) {

		By categorylocator=By.xpath("//a[contains(text(),'"+categoryname+"')]");
		pause(5000);
		click(categorylocator);
	}

	public String verifyvideosforCategory(String title) {

		By titlelocator=By.xpath("//*[contains(text(),'"+title+"')]");


		return getText(titlelocator);
	}

	public void clickStatus(String status,String videotitle ) {

		By videotitlelocator=By.xpath("//*[text()='"+videotitle+"']");
		By defaultactivelocator=By.xpath("//*[text()='"+status+"']");
		waitForElementPresent(defaultactivelocator);
		click(defaultactivelocator);
		waitForElement(videotitlelocator);
	}

	public String verifycategory(String newcategory) {
		By categorytextlocator=By.xpath("(//*[contains(text(),'"+newcategory+"')])");
		return getText(categorytextlocator);
	}
	
	public Integer VerifyElementVisibilty(String element)
	{
		By by = By.xpath("//*[contains(text(),'"+element+"')]");
		return elements(by);
	}

	public void clickdate(String datesstatus) {
		By dateslocator=By.xpath("//*[text()='"+datesstatus+"']");

		click(dateslocator);

	}

	public String getsystemdate() {

		DateTime date=new DateTime();

		String value=	date.videodate();
		logger.info("date is"+value);
		//	7/3/2014
		String[] datevalue=value.split("/");

		String date1=datevalue[1];


		return date1;
	}

	public String verifydate(String title) {

		By datelocator=By.xpath(" //a[contains(text(),'"+title+"')]/../../../../div[@class='tile-footer']/span[contains(@class,'pull-right')]");
		String dateyeartime=getText(datelocator);
		//	Mar 27, 2608 5:14 AM

		String[] date=dateyeartime.split(",");

		String date1=date[0];

		String[] datenum=date1.split(" ");    	
		String exactdate=datenum[1];

		return exactdate;
	}

	public String getyear() {
		DateTime date=new DateTime();

		String value=	date.videodate();
		logger.info("date is"+value);
		//		7/3/2014
		String[] datevalue=value.split("/");

		String date1=datevalue[2];
		return date1;

	}

	public String verifyyear(String title) {

		By datelocator=By.xpath(" //a[contains(text(),'"+title+"')]/../../../../div[@class='tile-footer']/span[contains(@class,'pull-right')]");
		String dateyeartime=getText(datelocator);
		//		Mar 27, 2608 5:14 AM

		String[] date=dateyeartime.split(",");

		String date1=date[1];

		String[] datenum=date1.split(" ");    	
		String year=datenum[1];
		logger.info("year is :"+year);   
		return year;
	}

	public void clickStatusFilter(String status)
	 {
	  By selectinputlocator = By.xpath("(//*[contains(@class,'selectize-input')])[4]");
	  waitForElementPresent(selectinputlocator);
	  logger.info("Status filter is visible on the page");
	  click(selectinputlocator);
	  By clickStatusFilterlocator = By.xpath("//*[@id='ng-app']//*[contains(text(),'"+status+"')]/..");
	  click(clickStatusFilterlocator);
	  logger.info("clicked on status filter");
	 }
	
	public void click_Sharing()
	{
		click(sharingbuttonlocator);
		customReport.reporter("Click on Sharing Button","");
	}
	
	public boolean VerifySharingTab()
	{
		return isDisplayedWithoutException(sharingbuttonlocator);
	}
	
	public void click_Startat()
	{
		click(startAt);
			pause(10000);
	}
	
	public boolean verify_StartAtOptionAvailable()
	{
		return isDisplayedWithoutException(startAt);
	}
	
	
	public String get_VideoURL()
	{
		return getAttribute(videoURL, "value");
	}

	public void click_Copy()
	{
		By copyButton = By.xpath("//button[contains(text(),'Copy')]");
		click(copyButton);
	}
	
	
	public String verify_pastedValue()
	{
		By searchBox = By.xpath(AvengerMediaPropertyPage.avengermediapage_mediasearchtextboxlocator.getProperty());
		click(searchBox);
		pasteCopiedContent(searchBox);
		return getAttribute(searchBox, "value");
	}
	
	public void set_startAtTime(AvengerVideoCommentsBeanPage avengerVideoCommentsBeanPage)
	{
		By startAt = By.xpath("//input[contains(@ng-model,'startAt')]");
		enterText(startAt, avengerVideoCommentsBeanPage.getStartAt());
	}
	By embedlocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_embedbuttonlocator.getProperty());
	
	public void click_Embed()
	{
		click(embedlocator);
		customReport.reporter("Clicked on Embed Button","");
		waitForElementPresent(optionsbuttonlocator);
		customReport.reporter("Embed Pop Up is Displayed","");
	}
     public int verify_EmbedButton()
     {
  return  elements(embedlocator);
    	 }
     
     public void click_showCode()
     {
   /* 	By showcodebuttonlocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_showcodebuttonlocator.getProperty());
    	click(showcodebuttonlocator);*/
     }
     
     public String get_showembedcodetextarea()
     {
    	 By showembedcodetextarealocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_showembedcodetextarealocator.getProperty());
    	 String embedcodetext=getAttribute(showembedcodetextarealocator, "value");
    	 customReport.reporter("The Embed Code Text is", embedcodetext);
    	 return embedcodetext; 
     }
     public void click_EmbedCloseButton()
     {
    	 By embedclosebuttonlocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_embedclosebuttonlocator.getProperty());
    	 click(embedclosebuttonlocator);
    	 customReport.reporter("Embed Close Button is CLicked","");
    	 waitForElementPresent(settingslinklocator);
     }

    public String verify_Label(String label)
    {
    	pause(5000);
    	By labellocator=By.xpath("(//*[contains(text(),'"+label+"')])[2]");//*[contains(text(),'apimvu7')][2]
       return getText(labellocator);
    }
    
    public String verify_Label1(String label)
    {
    	pause(5000);
    	By labellocator=By.xpath("//*[contains(text(),'"+label+"')]");
       return getText(labellocator);
    }
	

    public String verify_Label2(String label)
    {
    	pause(5000);
    	By labellocator=By.xpath("(//*[contains(text(),'"+label+"')])[1]");//*[contains(text(),'apimvu7 apimvu7')][1]
       return getText(labellocator);
    }
    

    public String verify_Label3(String label)
    {
    	pause(5000);
    	By labellocator=By.xpath("(//span[contains(text(),'"+label+"')])[1]");//span[contains(text(),'apimvu7 apimvu7')][1]
       return getText(labellocator);
    }
    
    
	public boolean verify_labelPresent(String label)
	{
		By labelPresent=By.xpath("//*[contains(text(),'"+label+"')]");
		return isDisplayed(labelPresent);
	}
	
	public int get_countOfElements()
	{
		int elementCount=driver.findElements(By.xpath("(//*[@class='cdk-virtual-scroll-content-wrapper'])//*[@class='BSVHj__root']")).size();
		return elementCount;
	}
     
    public void enter_embedVideoData(AvengerEditVideoSettingsBeanPage avengereditvideosettingsbeanpage)
    {

    	By embedautoplaylocator=By.id(AvengerVideoCommentsPropertyPage.avengervideocommentspage_embedautoplaylocator.getProperty());
    	selectValuefromDropDown(embedsizelocator,  avengereditvideosettingsbeanpage.getEmbedsize());
    	customReport.reporter("Entered Embed Video Data Size", avengereditvideosettingsbeanpage.getEmbedsize());
    	enterText(embedwidthlocator,  avengereditvideosettingsbeanpage.getEmbedvideowidth());
    	customReport.reporter("Entered Embed Video Data Width", avengereditvideosettingsbeanpage.getEmbedvideowidth());
    	if(avengereditvideosettingsbeanpage.isAutoplay())
    	{
    		check_CheckboxButton(embedautoplaylocator,getAttribute(embedautoplaylocator, "class"));   
    		customReport.reporter("Checked Checkbox", "Embed Autoplay");
    	}
    	else
    		uncheck_CheckboxButton(embedautoplaylocator,getAttribute(embedautoplaylocator, "class")); 	
    	customReport.reporter("Uncheck Checkbox", "Embed Autoplay");
    }

    public void selectEmbedSize(String embedsize)
    {
    	selectValuefromDropDown(embedsizelocator,embedsize);
    }
    
    public Map<String, Boolean> verify_embedWidthHeightAvailability()
    {
    	Map<String,Boolean> heightwidthavailability=new HashMap<String, Boolean>();
    	heightwidthavailability.put("width", isDisplayedWithoutException(embedwidthlocator));
    	heightwidthavailability.put("height", isDisplayedWithoutException(embedheightlocator));
        return 	heightwidthavailability;
    }
    
    
    public Map<String, String> get_embedVideoData()
    {
        Map<String, String>	embedvideodata=new HashMap<String, String>();
        embedvideodata.put("size",getDropDownSelectedOption(embedsizelocator));
        embedvideodata.put("width",getAttribute(embedwidthlocator,"value"));
        embedvideodata.put("height",getAttribute(embedheightlocator,"value"));
        embedvideodata.put("autoplay",getAttribute(embedautoplaylocator, "class"));
        return  embedvideodata;      
    }

    public String verifyCommentAvailability(String comment)
    {
    	By commentlocator = By.xpath("//*[text()='"+comment+"']");
    	return String.valueOf(elements(commentlocator));
    }
    
    
    public void clickVideoCommentTab()
    {
    	By videocommenttab = By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_commentstablocator.getProperty());
    	click(videocommenttab);
    }
    
    public void clickVideoRatingTab()
    {
    	By videoratingtab = By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_ratingstablocator.getProperty());
    	click(videoratingtab);
    }
    
public boolean verifyDownloadButton() {
		
		By downloadbuttonlocator=By.xpath("//*[contains(@class,'glyphicons circle_arrow_down')]");
		return isDisplayedWithoutException(downloadbuttonlocator);
		
	}
	public void clickVideoSupplement(String supplementfilename){
		
		By videoSupplementlocator= By.xpath("//*[contains(text(),'"+supplementfilename+"')]");
		click(videoSupplementlocator);
	}
	
	public String verify_videosInPlaylist(String videoname)
	{
		By videoslocator=By.xpath("//*[contains(@ng-repeat,'playlistVideo')]//*[contains(text(),'"+videoname+"')]");
		return getText(videoslocator);
	}
	
	public String verify_videosSelectedInPlaylist(String videoname)
	{
		By videoslocator=By.xpath("//*[contains(@ng-repeat,'playlistVideo')]//*[contains(text(),'"+videoname+"')]/../..");
		return getAttribute(videoslocator, "class");
	}

	public  String verifydeletecategory(String newcategory) {

		By categorytextlocator=By.xpath("//*[contains(text(),'"+newcategory+"')]");
		return String.valueOf(elements(categorytextlocator));
	}

	public void clickSignIn()
	{
		click(signInLocator);
	}
	
	public void clickVideoPlayButton(){
		
		By videoplaybuttonlocator=By.xpath("//*[@class='glyphicons play']");
		click(videoplaybuttonlocator);
	}
	
	public void clickSubmitForApprovalButton()
	{
		waitForElementPresent(submitforapprovalbutton);
		click(submitforapprovalbutton);
		}
	
	public void clickSubmitdropdownapproval()
	{
		click(submitforapprovalbuttondropdown);
		}
	public void clickvideoreports(){
		By videoratingtablocator=By.xpath("//*[@id='tab_reports']/a");
		click(videoratingtablocator);
	}
	
	public String verifyVideoReportsCount(int i){
		By videoratingcountlocator = By.xpath("//h1[text()="+i+"]");
		return getAttribute(videoratingcountlocator,"innerHTML");
	}
	
	public void ChooseApprovalProcess(String approvalprocess)
	{
		selectValuefromDropDown(chooseapprovallocator,  approvalprocess);
		logger.info("The Approval Process selected is"+approvalprocess);
	}
	
	public int verifyChooseApprovalProcessDropDown() {
		return elements(chooseapprovallocator);
		
	}

	public void approveRequest(String Comment){
		click(approveButtonLocator);
		enterText(approveRejectCommentTextboxLocator,  Comment);
		click(approveRejectCommentOKButtonLocator);		
	}
	
	public int approveRequestButtonAvailability(){
		return elements(approveButtonLocator);
	}
	
	public boolean verify_VideoRejected(){
		By rejectlocator=By.xpath("(//span[contains(text(),'Rejected')])[2]");
		return isDisplayed(rejectlocator);
		
	}
	
	public void rejectApprovalRequest(String Comment){
		click(rejectButtonLocator);
		enterText(approveRejectCommentTextboxLocator,  Comment);
		click(approveRejectCommentOKButtonLocator);		
	}
	public int verify_settingsLink()
	{
		return elements(settingslinklocator);
	}
	
	public String verifyPendingApprovalLabel()
	{
		By LabelTextlocator = By.xpath("//*[contains(@ng-show,'userId')]");
		return getText(LabelTextlocator);
	}
	
	public String getPublicWebcastPwd()
	{
		By publicpwd = By.xpath("//input[@name='password']");
		String publicpwdtext = getAttribute(publicpwd,"value");
		return publicpwdtext;
				
	}
	
	public ArrayList<String> getApprovalProcessDropDownList()
	{
	  return  getDropdownValue(chooseapprovallocator);
	}
  
   public void deleteVideoRejected()
   {
	   click(deletevideorejected);
	   logger.info("Clicked on Delete Button for Video Rejected");
   }
   
   public void reSubmitVideoRejected()
   {
	   click(resubmitrejectedvideo);
	   logger.info("Clicked on Resubmit Rejected video Button");
   }

     public void deleteVideoRejectedPopUP()
     {
    	 By deletevideorejectedpopup =By.xpath("//*[contains(@ng-show,'actionText')]");
    	 click(deletevideorejectedpopup);
    	 logger.info("Delete Video Pop Up Ok Clicked");
     }
   
     public void mousehoveronvideoplaybutton(){
    	 mouseOver(vidoePlay);
     }
     public void clickVideoSubtitlesButton(){
    	 click(subtitlelocator);
     }
     
     public LinkedList<String> get_allsubtitlesLanguage()
     {
    	 List<WebElement>subtitlelanguage=getAllWebElementValues(subtitlelanguagelist);
    	 LinkedList<String> subtitlelanguagevalues= new LinkedList<String>(); 
    	 logger.info("THe Sub title Language");
    	 for (WebElement webElement : subtitlelanguage) {
			logger.info("The value of text is"+ webElement.getText());
			subtitlelanguagevalues.add(webElement.getText());
    		 
		}
    	return subtitlelanguagevalues; 
     }
     public String get_allsubtitlesLanguage(String language)
     {
        
         By subtitlelanguagevalues=By.xpath("//div[contains(text(),'"+language+"')]");
    
        return getText(subtitlelanguagevalues) ;
     }
     
     public AvengerDashboardPage avengerDashboardPage()
 	{
 		customReport.reporter("Avenger Dashboard Page is Clicked","");
 		waitForElementPresent(logoutlocator);
 		return basePage.avengerDashboardPage(driver,  customReport, basePage);
 	}
     
     public LinkedHashMap<String, String> getwebeximportedvideodetails(){
    	 LinkedHashMap<String, String> webeximportedvideodetails= new LinkedHashMap<String, String>();
    	 webeximportedvideodetails.put("videouploader", getText(webexvideouploader));
    	 webeximportedvideodetails.put("videouploadtime", getText(webexvideouploadtime));
    	// webeximportedvideodetails.put("videoOwnername",getText(ownernamelocator));
    	 //webeximportedvideodetails.put("videoTotalViews", getText(totalviewslcator));
 
		return webeximportedvideodetails;
     }
     public LinkedHashMap<String, String> getinformationvideodetails(){
    	 LinkedHashMap<String, String> webeximportedvideodetails= new LinkedHashMap<String, String>();
    	 webeximportedvideodetails.put("videouploader", getText(uploadernameoninformationlocator));
    	 webeximportedvideodetails.put("videoOwnername",getText(ownernameoninformationlocator));
    	 //webeximportedvideodetails.put("videoTotalViews", getText(totalviewslcator));

		return webeximportedvideodetails;
     }
     
     public void click_Option()
 	{
 		clickUsingSwitch(optionsbuttonlocator);
 		waitForElementPresent(embedwidthlocator);
 		customReport.reporter("Clicked on Option Button","");
 	}
     
     public String verify_customfieldvaluesdisplayyes(String label){
    	 
    	 By labellocator=By.xpath("//*[contains(text(),'"+label+"')]/..");
    	 return getAttribute(labellocator, "innerHTML");
     }
     
     public boolean verify_customfieldvaluesNotdisplayed(String label){
    	 By labellocator=By.xpath("//*[contains(text(),'"+label+"')]");
    	return isDisplayedWithoutException(labellocator);
     }
     
     public String getpicklistoptionvalue(AvengerManageCustomFieldsBeanPage customfieldsbean){
		 By picklistoptionvalue=By.xpath("//*[contains(@name,'"+customfieldsbean.getCustomfieldname()+"')]/../select");
		 return getAttribute(picklistoptionvalue);
	 }
     public String verify_videodescription(String label){
    	  By description = By.xpath("(//*[contains(text(),'"+label+"')])");
   	  logger.info("the value is"+description);
   	  return getText(description);
     }
     
     public String verify_videodescription1(String label){
    	 
 	WebElement descFrame = driver.findElement(By.xpath("//*[contains(@class,'cke_wysiwyg_frame cke_reset')]"));
 	driver.switchTo().frame(descFrame);
   	By description = By.xpath("(//*[contains(text(),'"+label+"')])");
  	logger.info("the value is"+description);
  	return getText(description);
    }
     
      
     public LinkedHashMap<String, String> videodetails(String label){
   	  LinkedHashMap<String, String> details = new LinkedHashMap<String, String>();
   	  By description = By.xpath("//*[contains(@class,'SawMy__sectionBodyBlock KoiNW__sectionBody HJ8N2__description')]"); 
   	  details.put("description", getText(description));
   	  details.put("videoviews", getDisplayedElementText(totalviews));
   	  details.put("videouploadername", getText(uploadername));
   	  details.put("videouploaddate", getText(uploaddate));
   	  details.put("videoduration", getText(videoDuration));
   	  logger.info("Map Value is"+details);
   	  return details;
     }
      
      public String verify_ApprovalProcessSelected(){
    	 return getCurrentValueFromDropdown(chooseapprovallocator);
      }
      
      public void clickcategoryBrowseallLink() {

  		By categoryBrowsealllocator=By.xpath("//*[contains(text(),'Category:')]//../../..//div//small");
  		pause(5000);
  		click(categoryBrowsealllocator);
  	}
      public String getNoofTotalViews(){
    	  By nooftotalViews = By.xpath("//span[text()='Total Views:']/..");
    	  String totalViews = getText(nooftotalViews).replaceAll("[^0-9]", "");
    	  logger.info("Number of total views are"+totalViews);
    	  return totalViews;
      }
    
      public LinkedHashMap<String, String> videodetailsReportsTab(){
    	  LinkedHashMap<String, String> details = new LinkedHashMap<String, String>();
    	  By totalviewssinceupload = By.xpath("//*[contains(@data,'videoSummary.totalViewsSinceUpload')]/h2");  
    	  By viewlast30days = By.xpath("//*[contains(@data,'videoSummary.totalViewsLast30Days')]/h2");  
    	  By completionrate = By.xpath("//*[contains(@data,'videoSummary.completionRate')]/h2");  
    	  By averageviewingtime = By.xpath("//*[contains(@data,'videoSummary.averageTime')]/h2");  
    	  
    	  details.put("TOTALVIEWSSINCEUPLOAD", getText(totalviewssinceupload));
    	  details.put("VIEWSLAST30DAYS(UTC)", getText(viewlast30days));
    	  details.put("COMPLETIONRATE", getText(completionrate));
    	  details.put("AVERAGEVIEWINGTIME", getText(averageviewingtime));
    	  logger.info("Map Value is"+details);
    	  return details;
      }
      
      public boolean VerifySharetoSpark()
  	{
  		return isDisplayedWithoutException(sharetoSparklocator);
  	}
      public void click_SharetoSpark()
  	{
  		click(sharetoSparklocator);
  		customReport.reporter("Clicked on Share to Spark Button","");
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
 }
    public void EntersharetoSparkMessage(String message)
    {
    	    enterText(sharetoSparkMessage,  message);
    }
    public void Selectsampleroom(String selectroom)
    {
    	selectValuefromDropDown(selectSparkSampleRoom, selectroom);
    }
    
    
    public void click_sharetoSparkSendButton()
  	{
  		click(sharetoSparkSend);
  		customReport.reporter("Clicked on share to spark send Button","");
  	}
    //Cisco Spark
    public void EnterciscoSparkEmail(String Email)
    {
    	    enterText(ciscoSparkEmail,  Email);
    }
    public void click_ciscoSparkNext()
  	{
  		click(ciscoSparknextButton);
  		customReport.reporter("Clicked on next Button","");
  	}
  
    public void click_CisocSparkSkip()
  	{
  		click(cisocSparkSkip);
  		customReport.reporter("Clicked on Skip Button","");
  	}
    public void click_CiscoSampleChatRoom()
  	{
  		click(ciscoSparkChatSampleRoom);
  		customReport.reporter("Clicked on Sample Chat Room","");
  	}
    public String get_CiscoSparkMessage(String Message)
  	{ 
    	 By CiscoSparkMessage=By.xpath("//*[contains(text(),'"+Message+"')]");
    	 String CiscoSparkMes= getText(CiscoSparkMessage);
    	 String SparksharedMessage[] = CiscoSparkMes.split("\\r?\\n");
    	 logger.info("Spark Message"+SparksharedMessage[0]);
    	 return SparksharedMessage[0];
  		
  	}
    public String get_CiscoSparkURL(String Message)
  	{ 
    	 By CiscoSparkURL=By.xpath("//*[contains(text(),'"+Message+"')]/a");
    	 String SharedSparkURL=getAttribute(CiscoSparkURL,"href");
    	 logger.info("Shared Spark URL is: "+SharedSparkURL);
    	 return SharedSparkURL;
  		
  	}
    public String get_CiscoSparkNewVideoaddedMessage(String Message)
  	{ 
    	 By CiscoSparkMessage=By.xpath("//*[contains(text(),'"+Message+"')]");
    	 String CiscoSparkMes= getText(CiscoSparkMessage);
    	 String SparksharedMessage[] = CiscoSparkMes.split("-");
    	 logger.info("Spark Message"+SparksharedMessage[0]);
    	 return SparksharedMessage[0];
  		
  	}
    
    public String verify_VideoPlay()
    {
    	click(vidoePlay);
    	pause(2000);
    	By playlocator =By.xpath("//*[contains(@aria-label,'Time scrub')]");
    	return getAttribute(playlocator,"aria-valuenow");
    }
    
    /**
     * These method clicks on the csv button for the reports page. 
     */
     public void clickReportsCSV()
     {
         click(clickcsv);
         logger.info("Clicked on the CSV Button");      
 
    }
     
     public String getVideoAttribute(){
    	 pause(2000);
    	 mouseOver(fullscreenlocator);
    	 return getAttribute(fullscreenlocator);
    	 
     }
     public void click_ReplaceVideobutton() {
    	 click(replaceVideoLocator);
    	 logger.info("Clicked on Replace Video Button");
    	 pause(3000);
     }
     
     public void uploadReplaceVideoFile(String filePath){
    	 enterText_FileUpload(replaceVideoFile, filePath);
     }
     
     public void click_confirmReplaceOkButton() {
    	 click(confirmReplacevideoOkButtonLocator);
    	 logger.info("Clicked on Confirm Video Replace OK Button");
     }
     
     public void clickProcessCompleteOkButton() {
    	waitForElementPresent(videoProcessCompleteLocator);
        click(processCompelteOkButton);
        logger.info("Clicked on Process Complete OK Button");
     }
     
     public boolean videoReplacevisualindicator(){
    	 
    	 return isDisplayedWithoutException(videoReplacevisualIndicatorLocator);
     }
     
     public void restoreVideo(){
    	 
    	 click(restoreVideoButtonLocator);
    	logger.info("Clicked on restore video option");
    	pause(2000);
    	click(restoreVideoOkButtonLocator);
    	logger.info("Clicked on restore video Ok option");
    	
     }
     
     public String getNoHoursWarningMessage(){
    	return  getText(noHoursErrorMessageLocator);
     }
     
     //These method returns the playback rates for a video such as 1x,1.25x,1.5x,2x
     public ArrayList<String> getPlaybackRates()
     {

    	 ArrayList<String> playbackrateslist=getAllWebElementValueslist(playbackratelocator);
    	 logger.info("THe playback rates value is"+playbackrateslist);
    	 return playbackrateslist;
    	 
     }
     
    /**
     * This method will click on the playbackrate button and returns the default value for playbackrate.
     * @return 
     */
     public String click_Playbackratebutton()
     {
    	 String defaultplaybackrate= getText(playbackratebuttonlocator);
    	 waitForElementPresent(playbackratebuttonlocator);
    	 click(playbackratebuttonlocator);
         return defaultplaybackrate;
     }
    /**
     * These method will verify that playback rate button is visible on videos page or not. 
     * @return
     */
     public boolean verify_Playbackratebutton()
     {
    	return  isDisplayedWithoutException(playbackratebuttonlocator);
     }
     
     public void enableAutoPlay(){
    	 
    	 check_Checkbox(embedautoplaylocator);
    	 logger.info("AutoPlay Enabled ");
     }
     public String getEmbedNoHoursWarningMessage(){
    	 logger.info("embednoHoursErrorMessage :::::: "+getText(embednoHoursErrorMessageLocator));
    	return  getText(embednoHoursErrorMessageLocator);
     }
     
     public String click_FlagButton()
     {
    	click(videoflagbuttonlocator);
    	logger.info("Clicked on Flag Locator");
    	return getAttribute(inappropriatetextboxlocator, "placeholder");
     }
     
     public void click_inappropriatevideosubmitbutton()
     {
    	click(inappropriatesubmitbuttonlocator);
    	pause(5000);
    	logger.info("Clicked on Inappropriate video submit button");
     }

     public void enter_inappropriatevideocontenttextbox(String value)
     {
    	 enterText(inappropriatetextboxlocator, value);
    	 logger.info("Entered inappropriate content "+value);
         pause(3000);
     }
     
     public String getvideoinactivetextembed()
     {
    	 By videoinactivetext=By.xpath("//*[contains(@class,'share-inactive')]");
    	 return getText(videoinactivetext);
     }
     
     public void click_AllVideosTab()
     {
       click(Allvideostablocator);    
       logger.info("Click on All Videos tab");
     }
     public void click_BrowseVideosTab()
     {
       click(browsevideoslocator);    
       logger.info("Click on Browse Videos tab");
     }
     
     public void click_PlaylistTab()
     {
       click(playlisttablocator);    
       logger.info("Click on Playlist tab");
     }
     
     public AvengerUploadsPage click_MyVideos()
     {
    	 mouseOver(mediatablink);
    	 pause(3000);
    	 customReport.reporter("Media Tab is clicked", "");
       click(myvideosbutton);    
       logger.info("Click on My Videos tab");
       return  basePage.avengerUploadsPage(driver,  customReport, basePage);
     }
     
     public boolean verify_TrancodingGlyphiconButtonPresent(){
    	 return isDisplayedWithoutException(transcodeglyphiconbutton);
     }
     
     public void click_TrancodingGlyphiconButtonPresent(){
    	 waitForElementPresent(transcodeglyphiconbutton);
    	 logger.info("Transcoding Glyphicon Button is visible on the page");
    	 click(transcodeglyphiconbutton);
    	 logger.info("Clicked on Transcoding Glyphicon Button");
     }
     
     public boolean verify_EditVideoSettingsDropDown()
 	{
 		click(settingslinklocator);
 		pause(2000);
 		return isDisplayedWithoutException(EditVideoSettingsDropDownLocator);
 		
 	}
     
    
     public ArrayList<String> get_TranscodingValues(){
    	return getAllWebElementValueslist(transcodingvalues);
     }
     public void clickDownloadsButton()
 	{
 		By downloadbuttonlocator=By.xpath("//*[contains(@class,'arrow_down')]");
 		click(downloadbuttonlocator);
 		
 	}
     public AvengerEditVideoSettingsPage clickLegalholdButton()
  	{
  		By legalholdbuttonlocator=By.xpath("//*[contains(text(),' Apply Legal Hold ')]");
  		click(legalholdbuttonlocator);
  		By okbutton=By.xpath("(//*[contains(text(),'OK')])[2]");
  		click(okbutton);
  		return basePage.avengerEditVideoSettingsPage(driver, customReport, basePage);
  	}
     
     public AvengerEditVideoSettingsPage clickremoveLegalholdButton()
   	{
   		By removelegalholdbuttonlocator=By.xpath("//*[contains(text(),' Remove Legal Hold ')]");
   		click(removelegalholdbuttonlocator);
   		By okbutton=By.xpath("(//*[contains(text(),'OK')])");
   		click(okbutton);
   		return basePage.avengerEditVideoSettingsPage(driver, customReport, basePage);
   	}
     
     public String ConfirmCommentDeletion()
     {
     	By CommentDeleted = By.xpath("(//*[@class='ATt5r__comment'])[2]");
     	return getText(CommentDeleted);
     }
  public AvengerEditVideoSettingsPage click_details() {
	  By detailslocator=By.xpath("//*[contains(text(),' Details ')]");
	  click(detailslocator);
	  return basePage.avengerEditVideoSettingsPage(driver, customReport, basePage);
  }
     
     
}

