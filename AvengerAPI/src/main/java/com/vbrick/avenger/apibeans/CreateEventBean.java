package com.vbrick.avenger.apibeans;

public class CreateEventBean {

	public String Title,Description,StartDate,EndDate,PresentationProfileId,presenterId,videoSourceType,estimatedAttendees,lobbytime,AnonymousQuestionsEnabled,IsPublic,PollsEnabled,AutomatedWebcast,ChatEnabled,Unlisted,QuestionAndAnswerEnabled,accessControl,shortcut,preproduction,VideoAddress,PreProductionEnabled,WebcastStream,EmbeddedContent,PushContent,IsEnabled;
	public String days,hours,minutes,duration;
	public String[]tags;
	public String[]CategoryIds;

	String[] UserIds;
	String[] EventAdminId;
	String[] ModeratorIds;
	String[] GroupIds;
	String[] ContentLink;
	String[] PUserIds;
	String[] PGroupIds;

	
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}

    public String getMinutes() {
    return hours;
    }
    
    public void setMinutes(String minutes) {
    this.minutes = minutes;
    }

    
	public String getShortcut() {
		return shortcut;
	}
	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}
	
	public String getLobbytime() {
		return lobbytime;
	}
	public void setLobbytime(String lobbytime) {
		this.lobbytime = lobbytime;
	}
	public String getAccessControl() {
		return accessControl;
	}
	public void setAccessControl(String accessControl) {
		this.accessControl = accessControl;
	}
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getEstimatedAttendees() {
		return estimatedAttendees;
	}
	public void setEstimatedAttendees(String estimatedAttendees) {
		this.estimatedAttendees = estimatedAttendees;
	}

	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	public String getPresentationProfileId() {
		return PresentationProfileId;
	}
	public void setPresentationProfileId(String presentationProfileId) {
		PresentationProfileId = presentationProfileId;
	}
	public String getAnonymousQuestionsEnabled() {
		return AnonymousQuestionsEnabled;
	}
	public void setAnonymousQuestionsEnabled(String anonymousQuestionsEnabled) {
		AnonymousQuestionsEnabled = anonymousQuestionsEnabled;
	}
	public String getIsPublic() {
		return IsPublic;
	}
	public void setIsPublic(String isPublic) {
		IsPublic = isPublic;
	}
	public String getPollsEnabled() {
		return PollsEnabled;
	}
	public void setPollsEnabled(String pollsEnabled) {
		PollsEnabled = pollsEnabled;
	}
	public String getAutomatedWebcast() {
		return AutomatedWebcast;
	}
	public void setAutomatedWebcast(String automatedWebcast) {
		AutomatedWebcast = automatedWebcast;
	}
	public String getChatEnabled() {
		return ChatEnabled;
	}
	public void setChatEnabled(String chatEnabled) {
		ChatEnabled = chatEnabled;
	}
	public String getQuestionAndAnswerEnabled() {
		return QuestionAndAnswerEnabled;
	}
	public void setQuestionAndAnswerEnabled(String questionAndAnswerEnabled) {
		QuestionAndAnswerEnabled = questionAndAnswerEnabled;
	}
	public String[] getUserIds() {
		return UserIds;
	}
	public void setUserIds(String[] userIds) {
		UserIds = userIds;
	}
	public String[] getModeratorIds() {
		return ModeratorIds;
	}
	public void setModeratorIds(String[] moderatorIds) {
		ModeratorIds = moderatorIds;
	}
	public String[] getGroupIds() {
		return GroupIds;
	}
	public void setGroupIds(String[] groupIds) {
		GroupIds = groupIds;
	}
	
	public String[] getEventAdminId() {
		return EventAdminId;
	}
	
	public void setEventAdminId(String[] eventAdminId) {
		EventAdminId = eventAdminId;
	}
	
	public String[] getCategoryIds() {
		return CategoryIds;
	}

	public void setCategoryIds(String[] categoryIds) {
		this.CategoryIds = categoryIds;
	}
 

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public String getPreproduction() {
		return preproduction;
	}
	public void setPreproduction(String preproduction) {
		this.preproduction = preproduction;
	}
	
	public String[] getPUserIds() {
		return PUserIds;
	}
	public void setPUserIds(String[] pUserIds) {
		PUserIds = pUserIds;
	}
	public String[] getPGroupIds() {
		return PGroupIds;
	}
	public void setPGroupIds(String[] pGroupIds) {
		PGroupIds = pGroupIds;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getVideoAddress() {
		return VideoAddress;
	}
	public void setVideoAddress(String videoAddress) {
		VideoAddress = videoAddress;
	}
	public String getUnlisted() {
		return Unlisted;
	}
	public void setUnlisted(String unlisted) {
		Unlisted = unlisted;
	}
	
	public String getVideoSourceType() {
		return videoSourceType;
	}
	public void setVideoSourceType(String videoSourceType) {
		this.videoSourceType = videoSourceType;
	}

	public String getPresenterId() {
		return presenterId;
	}
	public void setPresenterId(String presenterId) {
		this.presenterId = presenterId;
	}
	
	public String getPushContent() {
		return PushContent;
	}
	public void setPushContent(String pushcontent) {
		this.PushContent = pushcontent;
	}
	public String getEmbeddedContent() {
		return EmbeddedContent;
	}
	public void setEmbeddedContent(String embeddedcontent) {
		this.EmbeddedContent = embeddedcontent;
	}
	
	public void setIsEnabled(String isenabled) {
		this.IsEnabled = isenabled;
	}
	public String getIsEnabled() {
		return IsEnabled;
	}
	public String[] getContentLinks() {
		return ContentLink;
	}
	public void setContentLinks(String[] contentLink) {
		ContentLink = contentLink;
	}
	
	
	}
	


