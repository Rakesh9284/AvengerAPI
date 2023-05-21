package com.vbrick.avenger.ObjProperty;

    public enum AvengerEventDetailsPropertyPage {
	
	avengereventsdetailspage_startdatelocator("(//*[@name='dateInput'])[1]"),
	avengereventsdetailspage_starttimelocator("(//*[contains(@aria-label,'hours')])[1]"),
	avengereventsdetailspage_enddatelocator("(//*[@name='dateInput'])[2]"),
	avengereventsdetailspage_endtimelocator("(//*[contains(@aria-label,'hours')])[2]"),
	avengereventsdetailspage_changepresentationprofilelocator("//*[contains(@uib-btn-radio,'presentation')]"),
	avengereventdetailspage_titlelocator("//input[@name='title']"),
	avengereventdetailspage_descriptionlocator("//textarea[@name='description']"),
	avengereventdetailspage_hostidlocator("//*[contains(@name,'eventAdmins')]//access-entity-assigned-row//*[contains(@class,'description')]/span[1]"),
	avengereventdetailspage_savebuttonlocator("((//*[contains(text(),'Cancel')])[last()]/..//button)[2]"),
	avengereventdetailspage_disabledsavebuttonlocator("//button[@ng-disabled='eventForm.$invalid' and @disabled='disabled']"),
	avengereventsdetailspage_deleteeventlocator("//*[@ng-click='deleteEvent()']"),
	avengereventsdetailspage_enableguestaccesslocator("guestAccessEnabled"),	
	avengereventsdetailspage_passwordlocator("password"),
	avengereventdetailspage_presentationrequirederrorlocator("//label[@ng-show='eventForm.presentationProfileId.$error.required']"),
	avengereventdetailspage_eventtitleerrorlocator("//label[@ng-show='eventForm.title.$error.required']"),
	avengereventdetailspage_hostiderrorlocator("//label[@ng-show='eventForm.hostId.$error.required']"),
	avengereventdetailspage_presentationprofilelocator("//*[contains(@name,'videoSourceOptions')]"),
	avengereventdetailspage_videosourcelocator("//*[contains(@class,'selected')]//th[2]"),
	avengereventdetailspage_presentationconflicterror("//label[@validation='scheduleConflict']"),
	avengereventdetailspage_startwebcastbuttonlocator("//*[contains(@uisref,'portal.scheduledEvents.webcast.view')]"),
	avengereventdetailspage_cancelbuttonlocator("//*[@ng-show='editMode']/../a"),
	avengereventdetailspage_inprogresstextlocator("//*[contains(text(),'IN PROGRESS')]"),
	avengereventdetailspage_joinwebcastbuttonlocator("//*[contains(@ng-bind,'Join Webcast')]"),
	avengereventdetailspage_webcamandscreensharelocator("//*[contains(@ng-model,'$ctrl.webcast.videoSource')]"),
	avengereventdetailpage_viewinvitationtextlocator("//label[contains(text(),'Webcast Link')]/../a"),
	avengereventdetailpage_okbuttonlocator("//*[contains(@ng-show,'actionText')]"),
	avengereventdetailpage_pptuploadlocator("//*[@name='presentationFile']/..//input[@type='file']"),
	avengereventdetailpage_ppttextlocator("//*[@name='presentationFile']/../div[contains(@ng-show,'presentationFile')]"),
	avengereventdetailpage_inactivedeviceerrormessage("//*[contains(@ng-show,'Inactive')]//div[contains(@class,'danger')]/.."),
	avengereventdetailpage_allavailablegroupslocator("//*[contains(@class,'glyphicons group')]/../.."),
	avengereventdetailpage_allavailableuserslocator("(//*[contains(@class,'glyphicons user')]/../..)[4]"),
	avengereventdetailpage_allprivateusersandgroups("//*[contains(@name,'accessEntities')]//access-entity-assigned-row//*[contains(@class,'description')]/span[1]"),
	avengereventdetailpage_addpollbutton("//button[@ng-click='addPoll()']"),
	avengereventdetailpage_pollquestion("//*[@ng-model='poll.question']"),
	avengereventdetailpage_pollanswer1("//*[contains(@ng-model,'poll.answers[0]')]"),
	avengereventdetailpage_pollanswer2("//*[contains(@ng-model,'poll.answers[1]')]"),
	avengereventdetailpage_pollanswer3("//*[contains(@ng-model,'poll.answers[2]')]"),
	avengereventdetailpage_pollanswer4("//*[contains(@ng-model,'poll.answers[3]')]"),
	avengereventdetailpage_allowmultipleansweryes("(//*[contains(text(),'Allow multiple answers?')]/../div/button)[1]"),
	avengereventdetailpage_allowmultipleanswerno("(//*[contains(text(),'Allow multiple answers?')]/../div/button)[2]"),
	avengereventdetailpage_enablepollbutton("//button[@ng-model='webcast.pollsEnabled']/../button[1]"),
	avengereventdetailpage_disablepollbutton("//button[@ng-model='webcast.pollsEnabled']/../button[2]"),
	avengereventdetailpage_deletepollbutton("//*[@ng-click='removePoll()']"),
	avengereventdetailpage_pollquestionerrorrequired("//*[@ng-show='poll.form.pollQuestion.$error.required']"),
	avengereventdetailpage_pollanswererrorrequired("//*[@ng-show='poll.form.answer1.$error.required']"),
	avengereventdetailpage_pollanswerfields("//*[@ng-form='poll.form']//input"),
	avengereventdetailspage_allowcommentstohost("//*[@name='commentsToHostOnly']"),
	avengereventdetailspage_allowanonymouscomments("//*[@name='anonymousCommentsAllowed']"),
	avengereventdetailspage_availableuserorgroupsearch("//*[contains(@ng-model,'availableFilterTxt')]"),
	groupuserteampagetextboxlocator("//*[contains(@name,'accessEntities')]//*[@name='query']"),
	groupuserteampluslocator("(//*[contains(@aria-label,'Add')])[1]"),
	donelocator("//*[contains(@name,'accessEntities')]//*[@name='query']/..//button"), 
	groupuserteammiminuslocator("//*[contains(@name,'accessEntities')]//*[contains(@aria-label,'Remove')]"),
	avengereventdetailspage_listingpasswordlocator("//*[@name='password']"),
	avengereventdetailspage_reportsbutton("//*[contains(@ui-sref,'webcast.reports')]"),
	avengereventdetailspage_downloadbutton("//*[contains(@class,'dropdown-downloads')]"),
	avengereventdetailspage_questionanswercsvdownload("//*[contains(@download,'questions.csv')]"),
	avengereventdetailspage_accepteventcreationpopupokbutton("//*[contains(@class,'btn btn-primary ng-binding')]"),
	avengereventdetailspage_listingprivateentities("//div[@ng-model='webcast.accessEntities']//div[@class='flex-fill text-ellipsis ng-binding']"),
	avengereventdetailspage_lobbytimelocator("(//*[contains(@class,'btn dropdown-toggle')])[2]"),
	avengereventdetailspage_videosourcetypelocator("//div[contains(@class,'A9jR9__customSelect')]"),
	avengereventdetailspage_presenterlocator("//*[contains(@class,'selected')]"),
	avengereventdetailspage_shortcutlocator("//input[@name='shortcutName']"),
	avengereventdetailspage_vcsipaddress("//*[@name='videoSourceSipAddress']"),
	avengereventdetailspage_categoriestags("//div[@class='category-item ng-scope']"),
	avengereventdetailspage_preprodusersandgroups("(//*[@class='cdk-virtual-scroll-content-wrapper'])[1]//div[@class='pP8HT__descriptionCell']/span[1]"),
	avengereventdetailpage_endEventButtonLocator("//button[@ng-click='$ctrl.endEvent()']"),
	avengereventdetailpage_endEventOKButtonLocator("//*[contains(@ng-show,'actionText')]"), 
	avengereventdetailpage_enablepreproduction("//*[@name='preProductionEnabled']"), 
	avengereventdetailpage_preproductiontime("//*[contains(@name,'preProdDuration')]"),
	avengereventdetailspage_accepteventchanges("//*[contains(@class,'btn btn-primary')]"),
	avengereventdetailspage_startpreproductionbutton("//*[contains(text(),' Start Pre-Production ')]"),//("//*[contains(@ng-if,'$ctrl.canJoinPreProduction')]"),
	avengereventdetailspage_Broadcast("//*[contains(@disabled,'disabled')]");
	
	
 	private String property;
	private AvengerEventDetailsPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
