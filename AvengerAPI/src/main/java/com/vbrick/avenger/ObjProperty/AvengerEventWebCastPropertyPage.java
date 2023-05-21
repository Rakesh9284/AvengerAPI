package com.vbrick.avenger.ObjProperty;


    public enum AvengerEventWebCastPropertyPage {

	
	avengereventwebcastpage_startbroadcastlocator("//webcast-broadcast-button/button[3]"),
	avengereventwebcastpage_stopbroadcastlocator("//webcast-broadcast-button/button[2]"),
	avengereventwebcastpage_okbuttonlocator("//button[contains(text(),'OK')]"),
	avengereventwebcastpage_leavewebcastlocator("//*[contains(@class,'__webcastStateControls')]/button"),
	avengereventwebcastpage_leaveconfirmationtext("//button[@ng-click = 'dismiss()']/../../div[2]"),
	avengereventwebcastpage_hostnotbroadcastingtextlocator("//*[contains(@class,'event-not-started ng-binding ng-scop')]//*[contains(@ng-show,'.webcastStatus.isInProgress')]"),
	avengereventwebcastpage_eventnotstartedtextlocator("//*[@class='event-not-started ng-binding ng-scope']/h2[1]"),
	avengereventwebcastpage_eventendedtextlocator("//*[@ng-show='webcast.webcastStatus.isEnded']"),
	avengereventwebcastpage_eventdescriptionlocator("//*[contains(@ng-bind-html,'webcast.description')]"),
	avengereventwebcastpage_eventtimelocator("//*[contains(@ng-show,'isNotStarted')]/.."),
	avengereventwebcastpage_noofattendees("//*[@ng-click='showAttendees()']"),
	avengereventwebcastpage_anonymouscommentlocator("//*[@ng-model='isAnonymous']"),
	avengereventwebcastpage_usercommentnamelocator("//*[contains(@class,'chat-username')]"),
	avengereventwebcastpage_usercommenttimelocator("//*[contains(@class,'chat-time')]"),
	avengereventwebcastpage_usercommentlocator("//*[contains(@class,'chat-time')]/../p"),
	avengereventwebcastpage_commentarealocator("//*[@class='glyphicons TDXuE__btnIcon chat']/.."),
	avengereventwebcastpage_eventhostdisconnectedlocator("//*[contains(@ng-show,'Disconnected')]"),
	avengereventwebcastpage_swapbuttonlocator("//*[@ng-click='toggleLayout()']"),
	avengereventwebcastpage_pptslide("(//*[contains(@class,'webcast-toolbar')])[2]/div[1]"),
	avengereventwebcastpage_nextpollslidelocator("//*[contains(@ng-click,'next()')]/span"),
	avengereventwebcastpage_previouspptslidelocator("//*[contains(@ng-click,'movePreviousSlide()')]"),
	avengereventwebcastpage_nextpptslidelocator("//*[contains(@ng-click,'moveNextSlide()')]"),
	avengereventwebcastpage_commentingastext("//*[contains(@ng-show,'anonymousCommentsAllowed')]//span[1]"),
	avengereventwebcastpage_commentingusername("//*[contains(@ng-show,'anonymousCommentsAllowed')]//span[2]"),
	avengereventwebcastpage_commentinganonymous("//*[contains(@ng-show,'anonymousCommentsAllowed')]//span[3]"),
	avengereventwebcastpage_closebuttonlocator("//*[@ng-click='close()']"),
	avengereventwebcastpage_pollquestion("//*[@ng-model='poll.question']"),
	avengereventwebcastpage_pollanswer1("answer1"),
	avengereventwebcastpage_pollanswer2("answer2"),
	avengereventwebcastpage_pollanswer3("answer3"),
	avengereventwebcastpage_pollanswer4("answer4"), 
	avengereventwebcastpage_allowmultipleansweryes("//*[@ng-model='poll.multipleChoice']/../button[1]"),
	avengereventwebcastpage_allowmultipleanswerno("//*[@ng-model='poll.multipleChoice']/../button[2]"),
	avengereventwebcastpage_openpoll("(//*[@ng-click='poll.open()'])"),
	avengereventwebcastpage_closepoll("//*[@ng-click='poll.close()']"),
	avengereventwebcastpage_nextpoll("//*[@ng-click='next()']/span"),
	avengereventwebcastpage_prevpoll("//*[@ng-click='prev()']/span"),
	avengereventwebcastpage_publishresults("//*[@ng-click='poll.publish()']"),
	avengereventwebcastpage_hideresultslocator("//*[@ng-click='poll.unpublish()']"),
	avengereventwebcastpage_commentsonlytohosttext("//*[contains(@ng-show,'commentsToHostOnly')]"),
	avengereventwebcastpage_totalresponseslocator("//*[@class='poll-sub-footer ng-binding']"),
	avengereventwebcastpage_attendeeslogo("//*[contains(@ui-sref,'view.attendees')]"),
	avengereventwebcastpage_commenttextboxlocator("//*[@ng-model='commentText']"),
	avengereventwebcastpage_commentsubmitbuttonlocator("//*[contains(@class,'comments')]"),
	avengereventwebcastpage_questionviewdropdownlocator("//*[contains(@class,'caret')]/.."),
	avengereventwebcastpage_addpollforeventmoderator("//button[contains(text(),'Add Poll')]"),
	avengereventwebcastpage_eventmoderatorpollcreatebutton("//*[contains(@ng-click,'onSave')]"),
	avengereventwebcastpage_inviteothers("//*[contains(@href,'invite')]"),
	avengereventwebcastpage_closebuttoneventreportpage("//*[contains(text(),'Close')]"),
	avengereventwebcastpage_manualcontrolbutton("//*[contains(@ng-click,'cancelAutomatedWebcast')]"),
	avengereventwebcastpage_pollheadingtext("//*[contains(@class,'right-sidebar')]/header"),
	avengereventwebcastspage_defaultthumbnail("//img[contains(@ng-src,'default-thumbnail')]"),
	avengereventwebcastspage_uploadBackgroundImage("backgroundImageFileUpload"),
	avengereventwebcastspage_removeBackgroundImagebutton("//button[@ng-click='removeBackgroundImage()']"),
	avengereventwebcastspage_imagestartwebcast("//div[@class='video-height-container ng-isolate-scope']"),
	avengereventwebcastspage_clickaskbutton("//*[contains(@aria-label,'Message text')]"),
	avengereventwebcastspage_questiontextbox("//*[@name='question']"),
	avengereventwebcastspage_questionsubmitbutton("//vb-webcast-ask-question//div[contains(@class,'box-inline')]/*[contains(text(),'Submit')]"),
	avengereventwebcastspage_postanonymouslycheckbox("//*[contains(@ng-model,'isAnonymous')]"),
	avengereventwebcastspage_publishtoAllcheckbox("//*[contains(@name,'isPublic')]/label/button"),
	avengereventwebcastspage_replysubmitbutton("(//*[contains(@class,'modal-footer')]//*[contains(@type,'submit')])[2]"),
	avengereventwebcastspage_replycanceltbutton("//*[contains(@class,'modal-footer')]//*[contains(@ng-click,'cancel')]"),
	avengereventwebcastspage_groupQuestionsLink("//*[contains(text(),'Group Questions')]"),
	avengereventwebcastspage_myQuestionsLink("//*[contains(text(),'My Questions')]"),
	avengereventwebcastspage_enablePresentaionDownload("presentationFileDownloadAllowed"),
	avengereventwebcastpage_addToMyCalender("*[download='event.ics']"),
	avengereventwebcastpage_attendeesrefreshbutton("//*[contains(@class,'refresh')]/.."),
	avengereventwebcastpage_recordbutton("//*[contains(@ng-click,'toggleSetToRecord')]");
	
    	
    private String property;
	private AvengerEventWebCastPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
