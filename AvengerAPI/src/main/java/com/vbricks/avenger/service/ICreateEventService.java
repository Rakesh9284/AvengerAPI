package com.vbricks.avenger.service;

public interface ICreateEventService {

	
	 String UserIds[] = null;
	 String ModeratorIds[] = null;
	 String GroupIds[] = null;
	 String EventAdminIds[] = null;
	 String PUserIds[]=null;
	 String PGroupIds[]=null;
	 String presenterId = "";
	 String videoSourceType[] = {"PresentationProfile","WebrtcSinglePresenter",};
	 String accessControl = "Public";
	 String UnListed = "";
 
	 String attendees = "";
	 String Ispublic = "";
	 String shortcut = "";
	 String pollsenabled = "";
	 String automatedwebcast = ""; 
	 String chatenabled = "";
	 String preproductionenabled = "";
	 String questionAndanswerenabled = "";
	 String anonymousQuestionsEnabled = "";
	 String lobbytime = "No";
	 String videoaddress = "";
	 String setDay_00 ="0";
	 String setDay_01 ="1";
	 String setDay_02 ="2";
	 String setDay_03 ="3";
	 String setDay_04 ="4";
	 String setDay_05 ="5";
	 String setDay_06 ="6";
	 String setDay_07 ="7";
	 
	 String setMinutes_10 ="10";
	 String setMinutes_15 ="15";
	 String setMinutes_20 ="20";
	 String setMinutes_30 ="30";
	 String setMinutes_40 ="40";
	 String setMinutes_50 ="50";
	 String setMinutes_60 ="60";
	 String setMinutes_80 ="80";
	
	 
	 String Title = "Title";
	 String Description = "Description";
	 String EstimatedAttendees = "estimatedAttendees";
	 String Lobbytime = "lobbyTimeMinutes";
	 String StartDate = "StartDate";
	 String EndDate = "EndDate";
	 String PresentationProfileId = "PresentationProfileId";
	 String VideoAddress = "VcSipAddress";
	 String AnonymousQuestionsEnabled = "AnonymousQuestionsEnabled";
	 String IsPublic = "IsPublic";
	 String Unlisted = "unlisted";
	 String Shortcut = "shortcutName";
	 String PollsEnabled = "PollsEnabled";
	 String AutomatedWebcast = "AutomatedWebcast"; 
	 String ChatEnabled = "ChatEnabled";
	 String QuestionAndAnswerEnabled = "QuestionAndAnswerEnabled";
	 String CreteEventUserIds = "UserIds";
	 String CreteEventModeratorIds = "ModeratorIds";
	 String CreteEventGroupIds = "GroupIds";
	 String CreateEventAdminIds = "eventAdminIds";
	 String CreteEventaccessControl = "accessControl";
	 String PresenterId = "presenterId";
	 String CreteEventvideosource = "videoSourceType";
	 String lobbyTime="lobbyTimeMinutes";
     String BACKGROUNDIMAGE="image";
     String Status="status";
     String slideUrl="slideUrl";
     String isPreProduction="isPreProduction";
     String CreteEventPUserIds="UserIds";
     String CreteEventPGroupIds="GroupIds";
     String duration="duration";
	
}
