package com.vbrick.avenger.ObjProperty;

 

public enum AvengerVideoCommentsPropertyPage {

 

    avengervideocommentspage_commentarealocator("//add-video-comment"),
    avengervideocommentspage_postcommentbuttonlocator("(//*[contains(@name,'commentForm')]/..//button)[2]"),
    avengervideocommentspage_commenterrortextlocator("//*[@name='commentForm']/div"),
    avengervideocommentspage_alltagslocator("//*[contains(@class,'tag-item')]"),
    avengervideocommentspage_settingslinklocator("//span[contains(@ng-show,'status.savingSettings')]/.."),
    avengervideocommentspage_addtoplaylistbuttonlocator("//*[@class='glyphicons list']/.."),
    avengeraddplaylist_savebuttonlocator("//h3/div/button[@type='submit']"),
    avengeraddplaylist_cancelbuttonlocator("//*[@name='playlistForm']/div[3]/div/a"),
    avengervideocommentspage_playbuttonlocator("//*[@ng-click='play()']/div/span"),
    avengervideocommentspage_savebuttonlocator("//*[contains(text(),'Save')]"),
    avengervideocommentspage_deletebuttonlocator("//*[contains(text(),'Delete')]"),
    avengeraddplaylist_addnewplaylistbuttonlocator("//video-playlist-header//*[contains(text(),'New')]"),
    avengeraddplaylist_addnewplaylisttextlocator("playlistName"),
    avengercreateplaylist_savebuttonlocator("//*[(@aria-label='Create Playlist')]"),
    avengeropenplaylistmodal_textlocator("//*[@name='playlistForm']//h3"),
    avengervideocommentpg_playbuttonlocator("//span[@class='glyphicons play']"),
    avengervideocommentspage_embedbuttonlocator("(//*[contains(@vbuiradiobtn,'Embed')])"),
    avengervideocommentspage_showcodebuttonlocator("//*[@class='glyphicons notes']"),
    avengervideocommentspage_showembedcodetextarealocator("//*[contains(@aria-label,'Embed')]"),
    avengervideocommentspage_embedclosebuttonlocator("//*[contains(text(),'Close')]"),
    avengervideocommentspage_embedsizelocator("//select[contains(@ng-model,'size')]"),
    avengervideocommentspage_embedwidthlocator("width"),
    avengervideocommentspage_embedheightlocator("//*[@placeholder='height']"),
    avengervideocommentspage_embedautoplaylocator("//*[contains(@ng-model,'autoplay')]"),
    avengervideocommentspage_ratingstablocator("//*[@id='tab_ratings']/a"),
    avengervideocommentspage_commentstablocator("//*[contains(@class,'glyphicons chat')]/.."),
    avengervideocommentspage_commentsbuttonlocator("//span[contains(@class,'chat')]/.."), 
    avengervideocommentspage_signInLocator("//*[contains(@ng-click,'loginRedirect')]/span[2]/.."),
    avengervideocommentspage_submitforapprovalbutton("(//*[contains(@ng-click,'approvalSubmit')])[1]"),
    avengervideocommentspage_submitforapprovaldropdownbutton("(//*[contains(@ng-click,'approvalSubmit')])[2]"),
    avengervideocommentspage_chooseapprovallocator("(//*[contains(text(),'Choose Approval Process')]/..)[1]"), 
    avengervideocommentspage_approveButtonLocator("//*[contains(@ng-click,'approveVideo')]"),
    avengervideocommentspage_rejectButtonLocator("//*[contains(@ng-click,'rejectVideo')]"),
    avengervideocommentspage_approveRejectCommentTextboxLocator("reason"),
    avengervideocommentspage_approveRejectCommentOKButtonLocator("//*[contains(@ng-click,'cancel')]/../button[2]"),
    avengervideocommentspage_approveRejectCommentCancelButtonLocator("//*[contains(@ng-click,'cancel')]"), 
    avengervideocommentspage_contentApprovalTextboxLocator("//div[@ng-show='canApproveVideo']"),
    avengervideocommentspage_videosubtitlebuttonlocator("//*[contains(@class,'subtitles')]/.."),
    avengervideocommentspage_videosubtitlelanguagelist("//*[contains(@class,'subtitles')]/../../ul/li/a"),
    avengervideocommentspage_videoplaybutton("//vg-play-pause-button/button"),//
    avengervideocommentspage_sharingbuttonlocator("//*[contains(@aria-label,'Sharing')]"),//("//span[contains(@class,'share')]"),
    avengervideocommentspage_sharetosparklocator("//*[contains(@ng-click,'shareToSparkInternal()')]"),
    avengervideocommentspage_sparklogin("//*[contains(@ng-show,'sparkAuthenticationRequired')]//a"),
    avengervideocommentspage_webExEmailAddresses(".//*[@id='IDToken1']"),
    avengervideocommentspage_webExPassword("//input[@placeholder='Password ']"),
    avengervideocommentspage_webExSubmit("//input[@name='accept']"),
    avengervideocommentspage_webExNext("IDButton2"),
    avengervideocommentspage_webExSignin("Button1"),
    avengervideocommentspage_webExSigninAccept("accept"),
    avengervideocommentspage_ciscoSparkEmail("//*[contains(@placeholder,'Email address')]"),
    avengervideocommentspage_ciscoSparknextButton("//*[contains(@aria-label,'Next')]"),
    avengervideocommentspage_ciscoSparkSkip("guide-skip"),
    avengervideocommentspage_ciscoSparkchatSampleroom("//div[contains(text(),'Sample Room')]"),
    avengervideocommentspage_sharetoSparkmessage("message"),
    avengervideocommentspage_selectSparkRoom("roomId"),
    avengervideocommentspage_sharetoSparkSend("//*[text()='Send']"),
    avengervideocommentspage_optionsbuttonlocator("//*[contains(@class,'bxIAT__embedOptionCategory')]"),
    avengervideocommentspage_uploader("(//*[contains(@class,'SawMy__sectionBodyBlock KoiNW__sectionBody')])[6]"),
    avengervideocommentspage_uploadtime("//video-basic-info/section/section[4]/div"),
    avengervideocommentspage_startAtlocator("//*[@name='position']/../vb-checkbox"), 
    avengervideocommentspage_videoUrl("//*[contains(@ng-model,'linkUrl')]"),
    avengervideocommentspage_replytext("//*[@class='glyphicons share']/.."),
    avengervideocommentspage_childcomment("//*[@class='comment']"),
    avengervideocommentspage_approvaldropdown("(//*[@ng-model='video.approvalProcessTemplateId'])[1]"),
    avengervideocommentspage_clickcsv("//*[contains(@href,'csv')]"),
    avengerfullscreen("//*[contains(@ng-click,'toggleFullScreen')]"),
    avengervideocommentspage_replaceVideo("//*[contains(@class,'glyphicons roundabout')]/.."),
    avengervideocommentspage_replaceVideoFile("videoFile"),
    avengervideocommentspage_confirmReplaceVideoOkButton("//*[contains(text(),'Continue Replace Video')]"),
    avengervideocommentspage_uploaddatelocator("//*[contains(text(),'Upload Date:')]/.."),
    avengervideocommentspage_videoprocesscompeltetextlocator("//*[contains(@ng-click,'onOk')]"),
    avengervideocommentspage_processcompleteOkButton("//replace-video-dialog//*[contains(text(),'OK')]"),
    avengervideocommentspage_videoReplacevisualIndicator("//*[contains(@value,'video.uploadProgress')]"),
    avengervideocommentspage_restorevideoLocator("//*[contains(@ng-show,'canRevert')]"),
    avengervideocommentspage_restorevideoOkButtonLocator("//*[contains(@ng-click,'onOk')]"),
    avengervideocommentspage_nohourserrormessage("//*[contains(@ng-switch-when,'HoursNotAvailable')]"),
    avengervideocommentspage_playbackratelocator("//vb-playback-rate-button/div/ul/li/a"), 
    avengervideocommentspage_playbackratebuttonlocator("//vb-playback-rate-button/div/button"),
    avengervideocommentspage_embednohourserrormessage("//*[contains(@class,'glyphicons warning_sign')]/.."),
    avengervideocommentspage_videoflagbuttonlocator("//*[contains(@ng-click,'reportInappropiateVideo')]"),
    avengervideocommentspage_inappropriatetextboxlocator("reason"),
    avengervideocommentspage_inappropriatesubmitbuttonlocator("//*[contains(@class,'modal-footer')]/div/button[2]"),
    uploadpg_myvideos("//*[contains(@class,'media-box-list')]//*[contains(@href,'media/uploads')]"),
    avengervideocommentspage_transcodeglyphiconbutton("//span[@class='glyphicon glyphicon-cog']/.."),
    avengervideocommentspage_transcodingvalues("//*[contains(@ng-repeat,'playbackOption')]/a"),
    avengervideocommentspage_videoplaylocator("//videogular"),
    avengervideocommentspage_playlocator("//*[@ng-click='onClickPlayPause()']"),
	avengervideocommentspage_ownername("(//*[contains(@class,'SawMy__sectionBodyBlock KoiNW__sectionBody')])[2]"),
	avngervideocommentpage_totalviews("//*[contains(@class,'MClkd__sectionBodyInline KoiNW__sectionBody')]/span/.."),
	avengervideocommentpage_totalviewstext("//*[contains(@class,'MClkd__sectionBodyInline KoiNW__sectionBody')]/span/.");
   
	private String property;
    private AvengerVideoCommentsPropertyPage(String property) {
        this.setProperty(property);
    }

 

    public String getProperty() {
        return property;
    }

 

    public void setProperty(String property) {
        this.property = property;
    }

 


}
 