
package com.vbrick.avenger.beansfactory;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DateUtil;

import com.vbrick.avenger.dao.AddAkamaiDeviceBeanPage;
import com.vbrick.avenger.dao.AddApikeyBeanPage;
import com.vbrick.avenger.dao.AddCustomDeviceBeanPage;
import com.vbrick.avenger.dao.AddExistingUserBean;
import com.vbrick.avenger.dao.AddLdapDeviceBeanPage;
import com.vbrick.avenger.dao.AddNewDmeBeanPage;
import com.vbrick.avenger.dao.AddNewPlaylistBeanPage;
import com.vbrick.avenger.dao.AddNewVbrickDeviceBeanPage;
import com.vbrick.avenger.dao.AddPresentationprofileBeanPage;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.AvengerAddZoneBeanPage;
import com.vbrick.avenger.dao.AvengerBulkEditBeanPage;
import com.vbrick.avenger.dao.AvengerCategoryBeanPage;
import com.vbrick.avenger.dao.AvengerEditVideoSettingsBeanPage;
import com.vbrick.avenger.dao.AvengerEventDetailsBeanPage;
import com.vbrick.avenger.dao.AvengerEventInviteURLGuestUserLoginBeanPage;
import com.vbrick.avenger.dao.AvengerFeaturesBeanPage;
import com.vbrick.avenger.dao.AvengerForgotPasswordBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.AvengerHomeBeanPage;
import com.vbrick.avenger.dao.AvengerManageCustomFieldsBeanPage;
import com.vbrick.avenger.dao.AvengerPlayerSettingsBeanPage;
import com.vbrick.avenger.dao.AvengerSystemMessageBeanPage;
import com.vbrick.avenger.dao.AvengerTranscodingGlobalSettingsBeanPage;
import com.vbrick.avenger.dao.AvengerVideoCommentsBeanPage;
import com.vbrick.avenger.dao.CreateChildAccountBeansPage;
import com.vbrick.avenger.dao.CreatePasswordBeanPage;
import com.vbrick.avenger.dao.EditRootAccountBeanPage;
import com.vbrick.avenger.dao.EventPollBeanPage;
import com.vbrick.avenger.dao.FieldValidations;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.LibraryBeanPage;
import com.vbrick.avenger.dao.LoginBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.dao.RootAccountBeanPage;
import com.vbrick.avenger.dao.RootAccountBeanPage2;
import com.vbrick.avenger.dao.TranscodingBeanPage;
import com.vbrick.avenger.dao.UserPasswordParametersBean;
import com.vbrick.avenger.funUtil.DateTime;
import com.vbrick.avenger.setup.Setup;


public  class BeanFactory extends FieldValidations implements Setup{
	private static Logger logger = Logger.getLogger(BeanFactory.class);


	// CreateAccountBeansPage beansPage;
	private static final String SPL_CHARS   = "!@#$%^&*_=+-/";  
		
	public void createChildAccountBeans(CreateChildAccountBeansPage beansPage) {
		
		String domainname;
		
		if (MAILINATOR_URL.contains("mailinator"))
		domainname = "@mailinator.com";
		else
		domainname = "@sharklasers.com";
		
		
		String val = yyyyMMDDHHmmssTime();
		String accountname = randomAlphabetic();
		String accounthostname = "hostname123" + val;
		String firstname = "firstname123" + val;
		String lastname = "lastname123" + val;
		String contactemail = randomAlphabetic()+domainname;
		String addressline1 = "addressline123"+val;
		String addressline2 = "addressline2123"+val;
		String country = "Albania";
		String state = "state123"+val;
		String city = "city123"+val;
		String postalcode = "postalcode123"+val;
		String phoneno = "12324234"+val;
		String preferredlanguage="English";
		String maximumactiveusers="5";
		
		beansPage.setAccountname(accountname);
		beansPage.setAccounthostname(accounthostname);
		beansPage.setFirstname(firstname);
		beansPage.setLastname(lastname);
		beansPage.setContactemail(contactemail);
		beansPage.setAddressline1(addressline1);
		beansPage.setAddressline2(addressline2);
		beansPage.setCountry(country);
		beansPage.setState(state);
		beansPage.setPostalcode(postalcode);
		beansPage.setCity(city);
		beansPage.setPhoneno(phoneno);
        beansPage.setPreferredlanguage(preferredlanguage);
        beansPage.setMaximumactiveusers(maximumactiveusers);
	}

	public void LibraryBeanByPage(LibraryBeanPage libraryBeanPage) {
		
		String val = yyyyMMDDHHmmssTime();
		String slibraryname = randomAlphabetic();
		String sublibraryname ="sub"+randomAlphabetic();
		String librarydescription = "librarydescripton" + val;
		libraryBeanPage.setSublibraryname(sublibraryname);
		libraryBeanPage.setLibraryname(slibraryname);
		libraryBeanPage.setLibrarydescription(librarydescription);
	}

	
	
	public void AvengerEditVideoSettingsBeanByPage(AvengerEditVideoSettingsBeanPage editvideosettings) {
		String value= RandomStringUtils.randomNumeric(3);
		
		String title = randomAlphabetic();
		String description = randomAlphabetic();
		String categories = "";
		String libraries = "";
		String tags = "";
		String videoduration="00:00:26.5680000";
		String embedvideowidth=randomNumeric();
		String embedsize="Small";
		String publishdate="";
		boolean autoplay=true;
		boolean anonymousviewing=false;
		ArrayList<String> tagsarray=new ArrayList<String>();
		for(int i=0;i<5;i++)
		{
		tagsarray.add(randomAlphabetic());
		}
		String customfieldpicklistdata=value;
		
       editvideosettings.setTitle(title);
	   editvideosettings.setDescription(description);
	   editvideosettings.setCategories(categories);
	   editvideosettings.setLibraries(libraries);
	   editvideosettings.setTags(tags);
	   editvideosettings.setTagsarray(tagsarray);
	   editvideosettings.setVideoduration(videoduration);
	   editvideosettings.setStatus("Active");
	   editvideosettings.setEmbedvideowidth(embedvideowidth);
	   editvideosettings.setEmbedsize(embedsize);
	   editvideosettings.setAutoplay(autoplay);
	   editvideosettings.setAnonymousviewing(anonymousviewing);
	   editvideosettings.setCustomfieldtextbox(customfieldpicklistdata);
	   editvideosettings.setPublishdate(publishdate);
	}
	
	public void AvengerHomePageByBeans(AvengerHomeBeanPage homebeanpage) {

		String active = "Active";
		String suspended = "Suspended";
		String deleted = "Deleted";
		homebeanpage.setStatusAsActive(active);
		homebeanpage.setStatusAsSuspended(suspended);
		homebeanpage.setStatusAsDeleted(deleted);
	}

	public void AddLdapDeviceBeanPage(AddLdapDeviceBeanPage beanpage) {
		String val = yyyyMMDDHHmmssTime();
		String ldapconnectornamelocator = "ldapconnectornamelocator"+ val;
		String ldapconnectormacaddresslocator = randomNumeric_12();
		String directorytypelocator = "Active Directory";
		String hostlocator = "10.0.0.210";
		String syncintervallocator = "24 Hours";
		String username="ldap";
		String password="ldap";
		String rootscope="ou=Mega Corporation, DC=ec2, DC=vb, DC=loc";
		String groupobjectscope="group";
		String membership="membership";
		String userobjectscope="person";
		String userusername="sAMAccountName";
		String usertitle="title";
		String userfirstname="givenName";
		String userlastname="sn";
		String useremailaddress="mail";
		String usertelephonenumber="telephoneNumber";
		String status="Active";
		String sslenabled="true";
		String directconnectionstatus="Active";
		String connectionnodemacaddress=randomNumeric_12();
		beanpage.setLdapconnectornamelocator(ldapconnectornamelocator);
		beanpage.setLdapconnectormacaddresslocator(ldapconnectormacaddresslocator);
		beanpage.setDirectorytypelocator(directorytypelocator);
		beanpage.setHostlocator(hostlocator);
		beanpage.setSyncintervallocator(syncintervallocator);
		beanpage.setUsername(username);
		beanpage.setPassword(password);
		beanpage.setRootscope(rootscope);
		beanpage.setGroupobjectscope(groupobjectscope);
		beanpage.setMembership(membership);
		beanpage.setUserobjectscope(userobjectscope);
		beanpage.setUserusername(userusername);
		beanpage.setUsertitle(usertitle);
		beanpage.setUserfirstname(userfirstname);
		beanpage.setUserlastname(userlastname);
		beanpage.setUseremailaddress(useremailaddress);
		beanpage.setUsertelephonenumber(usertelephonenumber);
		beanpage.setStatus(status);
		beanpage.setSslenabled(sslenabled);
		beanpage.setDirectconnectionstatus(directconnectionstatus);
        beanpage.setConnectionnodemacaddress(connectionnodemacaddress);
	}

	public void EditLdapDeviceBeanPage(AddLdapDeviceBeanPage beanpage) {
		String val = yyyyMMDDHHmmssTime();
		String ldapconnectornamelocator = "ldapconnectornamelocator"+ val;
		String ldapconnectormacaddresslocator = randomNumeric_12();
		String directorytypelocator = "Active Directory";
		String hostlocator = "hostlocator" + val;
		String syncintervallocator = "24 Hours";
		String username="editusername";
		String password="password";
		String rootscope="editrootscope";
		beanpage.setLdapconnectornamelocator(ldapconnectornamelocator);
		beanpage.setLdapconnectormacaddresslocator(ldapconnectormacaddresslocator);
		beanpage.setDirectorytypelocator(directorytypelocator);
		beanpage.setHostlocator(hostlocator);
		beanpage.setSyncintervallocator(syncintervallocator);
		beanpage.setUsername(username);
		beanpage.setPassword(password);
		beanpage.setRootscope(rootscope);
	}
	
	
	public void AddApikeyPage(AddApikeyBeanPage beanpage) {
		String val = yyyyMMDDHHmmssTime();
		String apiname = "apiname" + val;
		String apikey = "apikey" + val;

		beanpage.setApiname(apiname);
		beanpage.setApikey(apikey);

	}

	
	public void LoginPageByBeans(LoginBeanPage beansPage) {

		String Username = "arjun1@cg.com";
		String Password = "Password1";
		String helpTextUsername = "User name";
		String helpTextPassword = "Password";
		String errormessage = "Your Account is Locked";
		String Booleanvalue = "true";
		String invalidUsername = "invaliduser";
		String invalidPassword = "invalidpassword123456789";
		beansPage.setUsername(Username);
		beansPage.setPassword(Password);
		beansPage.setHelpTextUsername(helpTextUsername);
		beansPage.setHelpTextPassword(helpTextPassword);
		beansPage.setErrorMessage(errormessage);
		beansPage.setBooleanvalue(Booleanvalue);
		beansPage.setInvalidUsername(invalidUsername);
		beansPage.setInvalidPassword(invalidPassword);
	}

	public void feildvalidationData() {
		setFieldValidateData(validateField());
	}
	
	
	public void RootAccountByBeans(RootAccountBeanPage beansPage) {

		String accountName = "RootAccount";
		String accountHostName = "ec2-54-234-19-105.compute-1.amazonaws.com";
		String userName = "qarootuser";
		String password = "root1234";
		String confirmPassword = "root1234";
		String emailAddress = "david.dehora@vbrick.com";
		String emailServerAddress = "mail2.iwasdot.com";
		String port = "25";
		String emailServerUserName = "";
		String emailServerPassword = "";
		String lastName="dehora";
		String fromaddress = "system@vbrick.com";
		beansPage.setAccountName(accountName);
		beansPage.setLastName(lastName);
		beansPage.setAccountHostName(accountHostName);
		beansPage.setUserName(userName);
		beansPage.setPassword(password);
		beansPage.setConfirmPassword(confirmPassword);
		beansPage.setEmailAddress(emailAddress);
		beansPage.setEmailServerAddress(emailServerAddress);
		beansPage.setPort(port);
		beansPage.setEmailServerUserName(emailServerUserName);
		beansPage.setEmailServerPassword(emailServerPassword);
		beansPage.setFromAddress(fromaddress);
	}

	public void RootAccountByBeans2(RootAccountBeanPage2 beansPage) {

		String firstName = "David";
		String lastName = "De Hora";
		String emailAddress = "david.dehora@vbrick.com";
		String addressLine1 = "addressline1";
		String addressLine2 = "addressline2";
		String country = "United States";
		String state = "Virginia";
		String city = "Herndon";
		String postalCode = "20171";
		String phoneNumber = "203-265-0044";

		beansPage.setFirstName(firstName);
		beansPage.setLastName(lastName);
		beansPage.setEmailAddress(emailAddress);
		beansPage.setAddressLine1(addressLine1);
		beansPage.setAddressLine2(addressLine2);
		beansPage.setCountry(country);
		beansPage.setState(state);
		beansPage.setCity(city);
		beansPage.setPostalCode(postalCode);
		beansPage.setPhoneNumber(phoneNumber);
	}

	public void AddUserBean(AddUserBeanPage beansPage) {
		
		String domainname;
		if (MAILINATOR_URL.contains("mailinator"))
		domainname = "@mailinator.com";
		else
		domainname = "@sharklasers.com";
		
		String val = yyyyMMDDHHmmssTime();
		String firstname = randomAlphabetic();
		String lastname = randomAlphabetic();
		String contactemail = val + domainname;
		String title = "title" + val;
		String phoneno = "phoneno";
		String language = "English";
		String username = randomAlphabetic();
		String userrole = "Event Admin";
		String userdefaultrole = "Media Viewer";
		beansPage.setFirstname(firstname);
		beansPage.setLastname(lastname);
		beansPage.setContactemail(contactemail);
		beansPage.setTitle(title);
		beansPage.setPhoneno(phoneno);
		beansPage.setLanguage(language);
		beansPage.setUsername(username);
		beansPage.setUserrole(userrole);
		beansPage.setUserdefaultrole(userdefaultrole);
		beansPage.setDomainname(domainname);
	}

	public void EditRootAccountBean(EditRootAccountBeanPage beansPage) {
		
		String domainname;
		if (MAILINATOR_URL.contains("mailinator"))
		domainname = "@mailinator.com";
		else
		domainname = "@sharklasers.com";
		
		String val = yyyyMMDDHHmmssTime();
		String firstname = "editfirstname" + val;
		String lastname = "editlastname" + val;
		String contactemail = val + domainname;
		String title = "edittitle" + val;
		String phoneno = "editphoneno";
		String language = "None";
		beansPage.setFirstname(firstname);
		beansPage.setLastname(lastname);
		beansPage.setContactemail(contactemail);
		beansPage.setTitle(title);
		beansPage.setPhonenumber(phoneno);
		beansPage.setLanguage(language);
	}

	public void CreatePasswordBean(CreatePasswordBeanPage beansPage) {
		String password = "vbqavbqa";
		String confirmpassword = "vbqavbqa";
		String securityquestion = "What is your favorite book?";
		String typeyouranswer = "YoucanWIn";
		String invalidpassword = "invalidpassword";
		beansPage.setPassword(password);
		beansPage.setConfirmpassword(confirmpassword);
		beansPage.setSecurityquestion(securityquestion);
		beansPage.setTypeyouranswer(typeyouranswer);
		beansPage.setInvalidpassword(invalidpassword);

	}

	public void ForgotPassword(AvengerForgotPasswordBeanPage beansPage) {
		String password = "Password123456789@";
		String confirmpassword = "Password123456789@";
		beansPage.setPassword(password);
		beansPage.setConfirmpassword(confirmpassword);
	}

	public void CommentBean(AvengerVideoCommentsBeanPage commentbeanpage) {
		String comment = randomAlphabetic();
    	 commentbeanpage.setComment(comment);
    	 commentbeanpage.setStartAt("00:07");
	}
	
	public void MailinatorBean(MailinatorBeanPage beansPage) {
		String welcomelink = "Welcome";
		String resetlink = "Please reset your";
		beansPage.setWelcomelink(welcomelink);
		beansPage.setResetlink(resetlink);
	}

	public void TranscodingBean(TranscodingBeanPage beansPage) {
		String val =yyyyMMDDHHmmssTime();
		String presetname = "Transcoding" + val;
		String description = "Transcoding Description" + val;
		String presetstatus = "Active";
		String outputtype = "H.264";
		String preventupscaling = "Baseline";// Yes
		String keyframeinterval = "100";
		String framerate = "5";
		String width = "40";
		String height = "";
		String audiobitrate = "128"; // 5
		String audiosamplerate = "48";
		String transcodingprofile = "Baseline";
		String bitrate = "6";
		String videobitrate = "Constant";
		beansPage.setPresetname(presetname);
		beansPage.setDescription(description);
		beansPage.setOutputtype(outputtype);
		beansPage.setPreventupscaling(preventupscaling);
		beansPage.setKeyframeinterval(keyframeinterval);
		beansPage.setFramerate(framerate);
		beansPage.setWidth(width);
		beansPage.setHeight(height);
		beansPage.setAudiobitrate(audiobitrate);
		beansPage.setAudiosamplerate(audiosamplerate);
		beansPage.setTranscodingprofile(transcodingprofile);
		beansPage.setBitrate(bitrate);
		beansPage.setVideobitrate(videobitrate);
		beansPage.setPresetstatus(presetstatus);

	}

	public void TranscodingBean_Edit(TranscodingBeanPage beansPage) {
		String val = yyyyMMDDHHmmssTime();
		String presetname = "Transcoding Edit" + val;
		String description = "Transcoding Description Edit" + val;
		String preventupscaling = "Main";// Yes
		String keyframeinterval = "150";
		String framerate = "12";
		String width = "40";
		String height = "";
		String audiobitrate = "160"; // 5
		String audiosamplerate = "96";
		String transcodingprofile = "Baseline";
		String bitrate = "10";
		String videobitrate = "Constant";
		beansPage.setPresetname(presetname);
		beansPage.setDescription(description);
		beansPage.setPreventupscaling(preventupscaling);
		beansPage.setKeyframeinterval(keyframeinterval);
		beansPage.setFramerate(framerate);
		beansPage.setWidth(width);
		beansPage.setHeight(height);
		beansPage.setAudiobitrate(audiobitrate);
		beansPage.setAudiosamplerate(audiosamplerate);
		beansPage.setTranscodingprofile(transcodingprofile);
		beansPage.setBitrate(bitrate);
		beansPage.setVideobitrate(videobitrate);

	}

	
	public void BuildTranscodingGlobalSettings(AvengerTranscodingGlobalSettingsBeanPage beansPage) {
	
	   String presetname="High Definition 1080p";
	   beansPage.setPresetname(presetname);
	}
	
	public void AvengergroupsBean(AvengerGroupsBeanPage beansPage) {
		String newgroupvalue = randomAlphabetic();
		String renamegroup = newgroupvalue + "renamed";
		String grouprole = "Event Admin";
		String subgroupname = newgroupvalue + "sub";
		beansPage.setNewgroup(newgroupvalue);
		beansPage.setRenamegroup(renamegroup);
		beansPage.setGrouprole(grouprole);
		beansPage.setSubgroupname(subgroupname);

	}

	public void AvengerCategoryBean(AvengerCategoryBeanPage beansPage) {
		String newcategoryvalue = randomAlphabetic();
		beansPage.setNewcategory(newcategoryvalue);
		beansPage.setUpdatecategory(randomNumeric());
		

	}

	public void AddNewDmeBean(AddNewDmeBeanPage beanpage) {
				
		String val = yyyyMMDDHHmmssTime();
		String devicename = "devicename" + val;
		String macaddress = randomNumeric_12();
		String streamname = "Stream"+val;
		String streamurl= "http://qa"+val;
		String encodingtype = "Mpeg4";
		String devicestatus = "Active";
		beanpage.setDevicename(devicename);
		beanpage.setMacaddress(macaddress);
		beanpage.setDevicestatus(devicestatus);
		beanpage.setStreamname(streamname);
		beanpage.setStreamurl(streamurl);
		beanpage.setEncodingtype(encodingtype);
		beanpage.setVideostreamstab("Advanced");
	}

	public void AddNewVbrickDeviceBean(AddNewVbrickDeviceBeanPage beanpage) {
		String val = yyyyMMDDHHmmssTime();
		String devicename = "vbrickdevicename" + val;
		String macaddress = randomNumeric_12();
		String streamname = "Stream"+val;
		String streamurl= "http://qa"+val;
		String encodingtype = "Mpeg4";
		String devicestatus = "Active";
		beanpage.setDevicename(devicename);
		beanpage.setMacaddress(macaddress);
		beanpage.setDevicestatus(devicestatus);
		beanpage.setStreamname(streamname);
		beanpage.setStreamurl(streamurl);
		beanpage.setEncodingtype(encodingtype);
	}

	public void UserpasswordparameterBean(UserPasswordParametersBean beansPage) {
		String minnoofchars = "10";
		String invalidloginattemptsallowed = "3";
		String sessioninactivitytimeout = "15";
		String sessioninactivitytimeoutdefault = "30";
		String lockoutTimePeriod="5";
		String invalidattemptperiodminutes="0";
		String testMessage="accept terms and condition message";
		beansPage.setMinimumnoofcharacters(minnoofchars);
		beansPage.setInvalidloginattemptsallowed(invalidloginattemptsallowed);
		beansPage.setSessioninactivitytimeout(sessioninactivitytimeout);
		beansPage.setSessioninactivitytimeoutdefault(sessioninactivitytimeoutdefault);
		beansPage.setLockoutTimePeriod(lockoutTimePeriod);
		beansPage.setInvalidattemptperiodminutes(invalidattemptperiodminutes);
		beansPage.setUserAgreementMessage(testMessage);
	}

	public void  AvengerEventDetailsBean(AvengerEventDetailsBeanPage avengereventdetailsbeanpage) {
			
			
		Date date = new Date();
		DateFormat formatter = new SimpleDateFormat("h:mm a");
		String val= randomNumeric();
		String title=randomAlphabetic();
		String askaquestion=randomAlphabetic();
		String description="Event will be on "+val;
		String startdate="Jan 31, 2014";
		String enddate="Feb 28, 2014";
		String starttime=formatter.format(date);
		String host="";
		String listingtype="All Users";
		String pptpath = "";
		String endtime=DateTime.addMinutesToSystemTime(20);
		String listingpassword="Password@123";
		avengereventdetailsbeanpage.setAskaquestion(askaquestion);
		avengereventdetailsbeanpage.setStarttime(starttime);
		avengereventdetailsbeanpage.setEnddate(enddate);
		avengereventdetailsbeanpage.setDescription(description);
		avengereventdetailsbeanpage.setTitle(title);
		avengereventdetailsbeanpage.setHost(host);
		avengereventdetailsbeanpage.setListingtype(listingtype);
		avengereventdetailsbeanpage.setEndtime(endtime);
		avengereventdetailsbeanpage.setStartdate(startdate);
		avengereventdetailsbeanpage.setListingtype(listingtype);
		avengereventdetailsbeanpage.setPPTPath(pptpath);
		avengereventdetailsbeanpage.setEnablecomments("Enabled");
		avengereventdetailsbeanpage.setAnonymouscomments("Enabled");
		avengereventdetailsbeanpage.setEnableQA("Enabled");
		avengereventdetailsbeanpage.setListingpassword(listingpassword); 
	}
	
	
	public List<String> getSecurityQuestions() {

		List<String> ActualList = new ArrayList<String>();
		ActualList.add("Please select a question");
		ActualList.add("What is the name of your favorite childhood friend?");
		ActualList.add("What street name did you grow up on?");
		ActualList.add("What was your first car?");
		ActualList.add("What is your favorite food?");
		ActualList.add("What is your father's middle name?");
		ActualList.add("What is your mother's middle name?");
		ActualList.add("What is your oldest sibling's middle name?");//sibling’s 
		ActualList.add("What is your oldest sibling's birth month?");
		ActualList.add("What is your oldest cousin's first name?");
		Collections.sort(ActualList);
		return ActualList;
	}
	
	public void createPresentationProfile(AddPresentationprofileBeanPage beanspage)
	
	{
		String val= randomNumeric();
		String name = RandomStringUtils.randomAlphabetic(6);
		String description = "Presentation on something" + val;
		String videosource = "";
		String status="Active";
		String destinationdevice="";
		String streamname = "";
		beanspage.setDestinationdevicename(destinationdevice);
		beanspage.setName(name);
		beanspage.setDescription(description);
		beanspage.setVideosource(videosource);
		beanspage.setStatus(status);
		beanspage.setStreamname(streamname);
		
		
		}

		public List<String> getCountryMap() {
		Map<String, String> countryMap = new HashMap<String, String>();
		countryMap.put("blank", " ");
		countryMap.put("AX", "Åland Islands");
		countryMap.put("AF", "Afghanistan");
		countryMap.put("AL", "Albania");
		countryMap.put("DZ", "Algeria");
		countryMap.put("AS", "American Samoa");
		countryMap.put("AD", "Andorra");
		countryMap.put("AO", "Angola");
		countryMap.put("AI", "Anguilla");
		countryMap.put("AQ", "Antarctica");
		countryMap.put("AG", "Antigua and Barbuda");
		countryMap.put("AR", "Argentina");
		countryMap.put("AM", "Armenia");
		countryMap.put("AW", "Aruba");
		countryMap.put("AU", "Australia");
		countryMap.put("AT", "Austria");
		countryMap.put("AZ", "Azerbaijan");
		countryMap.put("BS", "Bahamas");
		countryMap.put("BH", "Bahrain");
		countryMap.put("BD", "Bangladesh");
		countryMap.put("BB", "Barbados");
		countryMap.put("BY", "Belarus");
		countryMap.put("BE", "Belgium");
		countryMap.put("BZ", "Belize");
		countryMap.put("BJ", "Benin");
		countryMap.put("BM", "Bermuda");
		countryMap.put("BT", "Bhutan");
		countryMap.put("BO", "Bolivia, Plurinational State of");
		countryMap.put("BQ", "Bonaire, Sint Eustatius and Saba");
		countryMap.put("BA", "Bosnia and Herzegovina");
		countryMap.put("BW", "Botswana");
		countryMap.put("BV", "Bouvet Island");
		countryMap.put("BR", "Brazil");
		countryMap.put("IO", "British Indian Ocean Territory");
		countryMap.put("BN", "Brunei Darussalam");
		countryMap.put("BG", "Bulgaria");
		countryMap.put("BF", "Burkina Faso");
		countryMap.put("BI", "Burundi");
		countryMap.put("KH", "Cambodia");
		countryMap.put("CM", "Cameroon");
		countryMap.put("CA", "Canada");
		countryMap.put("CV", "Cape Verde");
		countryMap.put("KY", "Cayman Islands");
		countryMap.put("CF", "Central African Republic");
		countryMap.put("TD", "Chad");
		countryMap.put("CL", "Chile");
		countryMap.put("CN", "China");
		countryMap.put("CX", "Christmas Island");
		countryMap.put("CC", "Cocos (Keeling) Islands");
		countryMap.put("CO", "Colombia");
		countryMap.put("KM", "Comoros");
		countryMap.put("CG", "Congo");
		countryMap.put("CD", "Congo, the Democratic Republic of the");
		countryMap.put("CK", "Cook Islands");
		countryMap.put("CR", "Costa Rica");
		countryMap.put("CI", "Côte d'Ivoire");
		countryMap.put("HR", "Croatia");
		countryMap.put("CU", "Cuba");
		countryMap.put("CW", "Curaçao");
		countryMap.put("CY", "Cyprus");
		countryMap.put("CZ", "Czech Republic");
		countryMap.put("DK", "Denmark");
		countryMap.put("DJ", "Djibouti");
		countryMap.put("DM", "Dominica");
		countryMap.put("DO", "Dominican Republic");
		countryMap.put("EC", "Ecuador");
		countryMap.put("EG", "Egypt");
		countryMap.put("SV", "El Salvador");
		countryMap.put("GQ", "Equatorial Guinea");
		countryMap.put("ER", "Eritrea");
		countryMap.put("EE", "Estonia");
		countryMap.put("ET", "Ethiopia");
		countryMap.put("FK", "Falkland Islands (Malvinas)");
		countryMap.put("FO", "Faroe Islands");
		countryMap.put("FJ", "Fiji");
		countryMap.put("FI", "Finland");
		countryMap.put("FR", "France");
		countryMap.put("GF", "French Guiana");
		countryMap.put("PF", "French Polynesia");
		countryMap.put("TF", "French Southern Territories");
		countryMap.put("GA", "Gabon");
		countryMap.put("GM", "Gambia");
		countryMap.put("GE", "Georgia");
		countryMap.put("DE", "Germany");
		countryMap.put("GH", "Ghana");
		countryMap.put("GI", "Gibraltar");
		countryMap.put("GR", "Greece");
		countryMap.put("GL", "Greenland");
		countryMap.put("GD", "Grenada");
		countryMap.put("GP", "Guadeloupe");
		countryMap.put("GU", "Guam");
		countryMap.put("GT", "Guatemala");
		countryMap.put("GG", "Guernsey");
		countryMap.put("GN", "Guinea");
		countryMap.put("GW", "Guinea-Bissau");
		countryMap.put("GY", "Guyana");
		countryMap.put("HT", "Haiti");
		countryMap.put("HM", "Heard Island and McDonald Islands");
		countryMap.put("VA", "Holy See (Vatican City State)");
		countryMap.put("HN", "Honduras");
		countryMap.put("HK", "Hong Kong");
		countryMap.put("HU", "Hungary");
		countryMap.put("IS", "Iceland");
		countryMap.put("IN", "India");
		countryMap.put("ID", "Indonesia");
		countryMap.put("IR", "Iran, Islamic Republic of");
		countryMap.put("IQ", "Iraq");
		countryMap.put("IE", "Ireland");
		countryMap.put("IM", "Isle of Man");
		countryMap.put("IL", "Israel");
		countryMap.put("IT", "Italy");
		countryMap.put("JM", "Jamaica");
		countryMap.put("JP", "Japan");
		countryMap.put("JE", "Jersey");
		countryMap.put("JO", "Jordan");
		countryMap.put("KZ", "Kazakhstan");
		countryMap.put("KE", "Kenya");
		countryMap.put("KI", "Kiribati");
		countryMap.put("KP", "Korea, Democratic People's Republic of");
		countryMap.put("KR", "Korea, Republic of");
		countryMap.put("KW", "Kuwait");
		countryMap.put("KG", "Kyrgyzstan");
		countryMap.put("LA", "Lao People's Democratic Republic");
		countryMap.put("LV", "Latvia");
		countryMap.put("LB", "Lebanon");
		countryMap.put("LS", "Lesotho");
		countryMap.put("LR", "Liberia");
		countryMap.put("LY", "Libya");
		countryMap.put("LI", "Liechtenstein");
		countryMap.put("LT", "Lithuania");
		countryMap.put("LU", "Luxembourg");
		countryMap.put("MO", "Macao");
		countryMap.put("MK", "Macedonia, the former Yugoslav Republic of");
		countryMap.put("MG", "Madagascar");
		countryMap.put("MW", "Malawi");
		countryMap.put("MY", "Malaysia");
		countryMap.put("MV", "Maldives");
		countryMap.put("ML", "Mali");
		countryMap.put("MT", "Malta");
		countryMap.put("MH", "Marshall Islands");
		countryMap.put("MQ", "Martinique");
		countryMap.put("MR", "Mauritania");
		countryMap.put("MU", "Mauritius");
		countryMap.put("YT", "Mayotte");
		countryMap.put("MX", "Mexico");
		countryMap.put("FM", "Micronesia, Federated States of");
		countryMap.put("MD", "Moldova, Republic of");
		countryMap.put("MC", "Monaco");
		countryMap.put("MN", "Mongolia");
		countryMap.put("ME", "Montenegro");
		countryMap.put("MS", "Montserrat");
		countryMap.put("MA", "Morocco");
		countryMap.put("MZ", "Mozambique");
		countryMap.put("MM", "Myanmar");
		countryMap.put("NA", "Namibia");
		countryMap.put("NR", "Nauru");
		countryMap.put("NP", "Nepal");
		countryMap.put("NL", "Netherlands");
		countryMap.put("NC", "New Caledonia");
		countryMap.put("NZ", "New Zealand");
		countryMap.put("NI", "Nicaragua");
		countryMap.put("NE", "Niger");
		countryMap.put("NG", "Nigeria");
		countryMap.put("NU", "Niue");
		countryMap.put("NF", "Norfolk Island");
		countryMap.put("MP", "Northern Mariana Islands");
		countryMap.put("NO", "Norway");
		countryMap.put("OM", "Oman");
		countryMap.put("PK", "Pakistan");
		countryMap.put("PW", "Palau");
		countryMap.put("PS", "Palestine, State of");
		countryMap.put("PA", "Panama");
		countryMap.put("PG", "Papua New Guinea");
		countryMap.put("PY", "Paraguay");
		countryMap.put("PE", "Peru");
		countryMap.put("PH", "Philippines");
		countryMap.put("PN", "Pitcairn");
		countryMap.put("PL", "Poland");
		countryMap.put("PT", "Portugal");
		countryMap.put("PR", "Puerto Rico");
		countryMap.put("QA", "Qatar");
		countryMap.put("RE", "Réunion");
		countryMap.put("RO", "Romania");
		countryMap.put("RU", "Russian Federation");
		countryMap.put("RW", "Rwanda");
		countryMap.put("BL", "Saint Barthélemy");
		countryMap.put("SH", "Saint Helena, Ascension and Tristan da Cunha");
		countryMap.put("KN", "Saint Kitts and Nevis");
		countryMap.put("LC", "Saint Lucia");
		countryMap.put("MF", "Saint Martin (French part)");
		countryMap.put("PM", "Saint Pierre and Miquelon");
		countryMap.put("VC", "Saint Vincent and the Grenadines");
		countryMap.put("WS", "Samoa");
		countryMap.put("SM", "San Marino");
		countryMap.put("ST", "Sao Tome and Principe");
		countryMap.put("SA", "Saudi Arabia");
		countryMap.put("SN", "Senegal");
		countryMap.put("RS", "Serbia");
		countryMap.put("SC", "Seychelles");
		countryMap.put("SL", "Sierra Leone");
		countryMap.put("SG", "Singapore");
		countryMap.put("SX", "Sint Maarten (Dutch part)");
		countryMap.put("SK", "Slovakia");
		countryMap.put("SI", "Slovenia");
		countryMap.put("SB", "Solomon Islands");
		countryMap.put("SO", "Somalia");
		countryMap.put("ZA", "South Africa");
		countryMap.put("GS", "South Georgia and the South Sandwich Islands");
		countryMap.put("SS", "South Sudan");
		countryMap.put("ES", "Spain");
		countryMap.put("LK", "Sri Lanka");
		countryMap.put("SD", "Sudan");
		countryMap.put("SR", "Suriname");
		countryMap.put("SJ", "Svalbard and Jan Mayen");
		countryMap.put("SZ", "Swaziland");
		countryMap.put("SE", "Sweden");
		countryMap.put("CH", "Switzerland");
		countryMap.put("SY", "Syrian Arab Republic");
		countryMap.put("TW", "Taiwan, Province of China");
		countryMap.put("TJ", "Tajikistan");
		countryMap.put("TZ", "Tanzania, United Republic of");
		countryMap.put("TH", "Thailand");
		countryMap.put("TL", "Timor-Leste");
		countryMap.put("TG", "Togo");
		countryMap.put("TK", "Tokelau");
		countryMap.put("TO", "Tonga");
		countryMap.put("TT", "Trinidad and Tobago");
		countryMap.put("TN", "Tunisia");
		countryMap.put("TR", "Turkey");
		countryMap.put("TM", "Turkmenistan");
		countryMap.put("TC", "Turks and Caicos Islands");
		countryMap.put("TV", "Tuvalu");
		countryMap.put("UG", "Uganda");
		countryMap.put("UA", "Ukraine");
		countryMap.put("AE", "United Arab Emirates");
		countryMap.put("GB", "United Kingdom");
		countryMap.put("US", "United States");
		countryMap.put("UM", "United States Minor Outlying Islands");
		countryMap.put("UY", "Uruguay");
		countryMap.put("UZ", "Uzbekistan");
		countryMap.put("VU", "Vanuatu");
		countryMap.put("VE", "Venezuela, Bolivarian Republic of");
		countryMap.put("VN", "Viet Nam");
		countryMap.put("VG", "Virgin Islands, British");
		countryMap.put("VI", "Virgin Islands, U.S.");
		countryMap.put("WF", "Wallis and Futuna");
		countryMap.put("EH", "Western Sahara");
		countryMap.put("YE", "Yemen");
		countryMap.put("ZM", "Zambia");
		countryMap.put("ZW", "Zimbabwe");
		List<String> countrylist = new ArrayList<String>(countryMap.values());
		for (String s : countrylist) {
			logger.info("values in country list is" + s);
		}
		Collections.sort(countrylist);
		return countrylist;

	}

	public void addZoneBean(AvengerAddZoneBeanPage beanpage)
	{
		String val=randomNumeric();
		Random rand=new Random();
		int num1, num2, num3 ,num4;
		num1 = rand.nextInt (11) + 10;
	    num2 = rand.nextInt (22) + 10;
	    num3 = rand.nextInt (33) + 10;
	    num4 = rand.nextInt (44) + 10;
		
		String zonename= "Zone"+val;
		String ipaddress =num1+"."+num2+"."+num3+"."+num4;
		String devicename = "";
		String streamname = "";
		beanpage.setZonename(zonename);
		beanpage.setIpaddress(ipaddress);
		beanpage.setDevicename(devicename);
		beanpage.setStreamname(streamname);
		beanpage.setFlag("abcd");
	}
	
	public void addCustomDeviceBean(AddCustomDeviceBeanPage beanpage)
	
	{
			String val=randomNumeric();
			Random rand=new Random();
			int num1, num2, num3 ,num4;
			num1 = rand.nextInt (11) + 10;
		    num2 = rand.nextInt (22) + 10;
		    num3 = rand.nextInt (33) + 10;
		    num4 = rand.nextInt (44) + 10;
		    
		    String devicename = RandomStringUtils.randomAlphabetic(5);
		    String ipaddress = num1+"."+num2+"."+num3+"."+num4;
		    String streamname = "Stream"+val;
		    String streamurl = "http://qa"+val;
		    String streamencodingtype = "Mpeg4";
		    beanpage.setDevicename(devicename);
		    beanpage.setIpaddress(ipaddress);
		    beanpage.setStreamname(streamname);
		    beanpage.setStreamurl(streamurl);
		    beanpage.setStreamencodingtype(streamencodingtype);
		    beanpage.setStatus("Active");
		
	}
	
	public void editPlayerPreferencesBean(AvengerPlayerSettingsBeanPage beanpage)
	
	{
		String winfirstpreference = "Flash";
		String winsecondpreference = "VBrick";
		String winthirdpreference = "Quicktime";
		String macfirstpreference = "Flash";
		String macsecondpreference = "VBrick";
		String macthirdpreference = "Quicktime";
		beanpage.setWindowsfirstpreference(winfirstpreference);
		beanpage.setWindowssecondpreference(winsecondpreference);
		beanpage.setWindowsthirdpreference(winthirdpreference);
		beanpage.setMacfirstpreference(macfirstpreference);
		beanpage.setMacsecondpreference(macsecondpreference);
		beanpage.setMacthirdpreference(macthirdpreference);
	}
	
	public void addVideoByUrlBean(FileUploadBeanPage fileUploadBeanPage)
	  
	{
		  fileUploadBeanPage.setVideoUrl("rtmp://"+randomAlphabetic()+"/live/"); 
	      fileUploadBeanPage.setEncodingType("Mpeg4");	   
	      fileUploadBeanPage.setVideoType("Live");
	      fileUploadBeanPage.setVideoStatus("Active");
	      fileUploadBeanPage.setValidstreamurl("rtmp://webcasting.thefloridachannel.org/live/tvweb1");
	      
	      
	   }	   
	   public void addNewPlaylistBean(AddNewPlaylistBeanPage newPlaylistBeanPage)
	   {
		   newPlaylistBeanPage.setName("PLAYLIST"+randomNumeric());
	   }	 
	   
	   
	   public void activeDMEBean(AddNewDmeBeanPage beanpage)
		  {
		   
			beanpage.setDevicename("ActiveDmeDevice");
		    beanpage.setEncodingtype("H264");
			beanpage.setMacaddress("000C29357E1F");
			beanpage.setStreamname("S1");
			beanpage.setStreamurl("rtmp://webcasting.thefloridachannel.org/live/tvweb1");
			beanpage.setSecondstreamname("S2");
			beanpage.setSecondstreamingurl("rtmp://10.10.6.43:1935/live/SS43");
			beanpage.setThirdstreamname("S3");
			beanpage.setThirdstreamingurl("rtmp://10.200.2.98:1935/live/Stream114");
			beanpage.setFourthstreamname("rtspstream");
			beanpage.setFourthstreamingurl("rtsp://10.10.6.131:5544/QA1");
			beanpage.setMulticaststreamname("multicaststream");
			beanpage.setMulticaststreamurl("http://10.10.6.43/FMout/db832a0e-8836-4030-bfc0-fae4a3b84c08.f4m");
			beanpage.setDevicestatus("Active");
		  }
	   
		  
		  public void activeEncoderBean(AddNewVbrickDeviceBeanPage beanpage)
		  
		  {
			  beanpage.setDevicename("ActiveEncoder");
			  beanpage.setEncodingtype("Mpeg4");
			  beanpage.setMacaddress("00:07:df:01:cc:95");
			  beanpage.setStreamname("S1");
			  beanpage.setStreamurl("rtmp://27.254.84.3:1935/live/ttyud.stream");
			  beanpage.setDevicestatus("Active");
		  }
		  
		  
		  public void firstActiveZoneBean(AvengerAddZoneBeanPage addZoneBeanPage)
		 
		  {
			  addZoneBeanPage.setZonename("ActiveZone");
			  addZoneBeanPage.setIpaddress(getIpaddress());
			  addZoneBeanPage.setStreamname("S1");
			  addZoneBeanPage.setDevicename("");
			  addZoneBeanPage.setStreamname("");
		  }
		  
		  public void avengerFeaturesPage(AvengerFeaturesBeanPage avengerfeaturespage)
		 
		  {
			  avengerfeaturespage.setHostedwebsite("https://vbrick-test.webex.com");
			  avengerfeaturespage.setSiteid("867742");
			  avengerfeaturespage.setPartnerid("6OJ9We0HeKut8ke_vYJ9QA");
			  avengerfeaturespage.setAdminusername("rajat.jindal@vbrick.com");
			  avengerfeaturespage.setAdminpassword("Welcome123&");
			  avengerfeaturespage.setNbrstorageserviceurl("https://nva1wss.webex.com/nbr/services/NBRStorageService");
			  avengerfeaturespage.setWebexusername("ddehora");
			  avengerfeaturespage.setWebexpassword("Welcome123#@<>!@#$%^&*()");
			  avengerfeaturespage.setSparkclientid("Cf9d6b732e006ae25539a1cb9a00e81201428f09d783a8a68828921c45cd46ffe");
			  avengerfeaturespage.setSparkclientsecret("cab8ca8d4e38049d4c5918fb3b4abd710b3c10f46d426be5cfbcdcedccb34646");
			  avengerfeaturespage.setVoicebaseapikey("788ba2a8a5C110a54476e8A0CC8-B72BD7C7c5c5816793825844076030FBbc55627E73b43b6-056D54112F");
			  avengerfeaturespage.setVoicebasepassword("vbrick01");
		  }
		  
		  
		  public void secondActiveZoneBean(AvengerAddZoneBeanPage addZoneBeanPage)
		  {
			  addZoneBeanPage.setZonename("ActiveSubZone");
			  addZoneBeanPage.setIpaddress(getIpaddress());
			  addZoneBeanPage.setStreamname("S2");
			  addZoneBeanPage.setDevicename("");
			  addZoneBeanPage.setStreamname("");
		  }
		  
		  public String getIpaddress()
		  {
			  String ipaddress = null;
			 try {
				 ipaddress= InetAddress.getLocalHost().getHostAddress();
			  logger.info("ip address is"+ipaddress);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 return ipaddress; 
		  }
		  
		  public void EventPollBeanPage(EventPollBeanPage eventpollbeanpage)
			{
			  String enabled="yes";
			  String pollquestion="What is your favourite food ?"+RandomStringUtils.random(1,SPL_CHARS);
			  String pollanswer1="Pizza"+RandomStringUtils.random(1,SPL_CHARS);
			  String pollanswer2="Burger"+RandomStringUtils.random(1,SPL_CHARS);
			  String pollanswer3="Sandwich"+RandomStringUtils.random(1,SPL_CHARS);
			  String pollanswer4="HotDog"+RandomStringUtils.random(1,SPL_CHARS);
			  String allowmultipleanswer="yes";
			  eventpollbeanpage.setEnabled(enabled);
			  eventpollbeanpage.setPollquestion(pollquestion);
			  eventpollbeanpage.setPollanswer1(pollanswer1);
			  eventpollbeanpage.setPollanswer2(pollanswer2);
			  eventpollbeanpage.setPollanswer3(pollanswer3);
			  eventpollbeanpage.setPollanswer4(pollanswer4);
			  eventpollbeanpage.setAllowmultipleanswer(allowmultipleanswer);
			}  
		  
		  public void AddExistingUserBeanforAccount(AddExistingUserBean beansPage) {
			  Random r = new Random();
			  int Low = 1;
			  int High = 5;
			  int R = r.nextInt(High-Low) + Low;	
			  logger.info("Random number generated is "+R);
			  String domainname;
			  if (MAILINATOR_URL.contains("mailinator"))
			  		domainname = "@mailinator.com";
			  		else
			  		domainname = "@sharklasers.com";
			  		String firstname = "griduser"+R;
			  		String lastname = "griduser"+R;
			  		String username = "griduser"+R;
			  		String password = "Password@123";
			  		String userrole = "Event Admin";
			  		String userdefaultrole = "Media Viewer";
			  		beansPage.setFirstname(firstname);
			  		beansPage.setLastname(lastname);;
			  		beansPage.setUsername(username);
			  		beansPage.setPassword(password);
			  		beansPage.setUserrole(userrole);
			  		beansPage.setUserdefaultrole(userdefaultrole);
			  		beansPage.setDomainName(domainname);
			  	}
		 	
		  public void AddMediaViewerExistingUserBeanforAccount(AddUserBeanPage beansPage) {
			  Random r = new Random();
			  int Low = 1;
			  int High = 5;
			  int R = r.nextInt(High-Low) + Low;
			  String maildomain=null;
			  logger.info("Random number generated is "+R);
			  		if (MAILINATOR_URL.contains("mailinator"))
			  			maildomain = "@mailinator.com";
			  		else
			  			maildomain = "@sharklasers.com";
			  		String firstname = "mediaviewer"+R;
			  		String lastname = "mediaviewer"+R;
			  		String username = "mediaviewer"+R;
			  		String password = "Password@123";
			  		String userdefaultrole = "Media Viewer";
			  		beansPage.setFirstname(firstname);
			  		beansPage.setLastname(lastname);;
			  		beansPage.setUsername(username);
			  		beansPage.setPassword(password);
			  		beansPage.setUserdefaultrole(userdefaultrole);
			  		beansPage.setDomainname(maildomain);
			  		
			  	}

		
		  public void AddExistingUserBeanPart1(AddExistingUserBean beansPage) {
			  Random r = new Random();
			  int Low = 700;
			  int High = 705;
			  int R = r.nextInt(High-Low) + Low;	
			  logger.info("Random number generated is "+R);
			  String domainname;
			  if (MAILINATOR_URL.contains("mailinator"))
			  		domainname = "@mailinator.com";
			  		else
			  		domainname = "@sharklasers.com";
			  		String firstname = "griduser"+R;
			  		String lastname = "griduser"+R;
			  		String username = "griduser"+R;
			  		String password = "Password@123";
			  		String userrole = "Event Admin";
			  		String userdefaultrole = "Media Viewer";
			  		beansPage.setFirstname(firstname);
			  		beansPage.setLastname(lastname);;
			  		beansPage.setUsername(username);
			  		beansPage.setPassword(password);
			  		beansPage.setUserrole(userrole);
			  		beansPage.setUserdefaultrole(userdefaultrole);
			  		beansPage.setDomainName(domainname);
			  	}
		
		  public void AddExistingUserBeanPart2(AddExistingUserBean beansPage) {
			  Random r = new Random();
			  int Low = 706;
			  int High = 720;
			  int R = r.nextInt(High-Low) + Low;	
			  logger.info("Random number generated is "+R);
			  String domainname;
			  if (MAILINATOR_URL.contains("mailinator"))
			  		domainname = "@mailinator.com";
			  		else
			  		domainname = "@sharklasers.com";
			  		String firstname = "griduser"+R;
			  		String lastname = "griduser"+R;
			  		String username = "griduser"+R;
			  		String password = "Password@123";
			  		String userrole = "Event Admin";
			  		String userdefaultrole = "Media Viewer";
			  		beansPage.setFirstname(firstname);
			  		beansPage.setLastname(lastname);;
			  		beansPage.setUsername(username);
			  		beansPage.setPassword(password);
			  		beansPage.setUserrole(userrole);
			  		beansPage.setUserdefaultrole(userdefaultrole);
			  		beansPage.setDomainName(domainname);
			  	}
		  
		  public void AddExistingUserBeanPart3(AddExistingUserBean beansPage) {
			  Random r = new Random();
			  int Low = 720;
			  int High = 730;
			  int R = r.nextInt(High-Low) + Low;	
			  logger.info("Random number generated is "+R);
			  String domainname;
			  if (MAILINATOR_URL.contains("mailinator"))
			  		domainname = "@mailinator.com";
			  		else
			  		domainname = "@sharklasers.com";
			  		String firstname = "griduser"+R;
			  		String lastname = "griduser"+R;
			  		String username = "griduser"+R;
			  		String password = "Password@123";
			  		String userrole = "Event Admin";
			  		String userdefaultrole = "Media Viewer";
			  		beansPage.setFirstname(firstname);
			  		beansPage.setLastname(lastname);;
			  		beansPage.setUsername(username);
			  		beansPage.setPassword(password);
			  		beansPage.setUserrole(userrole);
			  		beansPage.setUserdefaultrole(userdefaultrole);
			  		beansPage.setDomainName(domainname);
			  	}
		
		  
		  public void AddExistingUserBeanPart4(AddExistingUserBean beansPage) {
			  Random r = new Random();
			  int Low = 730;
			  int High = 740;
			  int R = r.nextInt(High-Low) + Low;	
			  logger.info("Random number generated is "+R);
			  String domainname;
			  if (MAILINATOR_URL.contains("mailinator"))
			  		domainname = "@mailinator.com";
			  		else
			  		domainname = "@sharklasers.com";
			  		String firstname = "griduser"+R;
			  		String lastname = "griduser"+R;
			  		String username = "griduser"+R;
			  		String password = "Password@123";
			  		String userrole = "Event Admin";
			  		String userdefaultrole = "Media Viewer";
			  		beansPage.setFirstname(firstname);
			  		beansPage.setLastname(lastname);;
			  		beansPage.setUsername(username);
			  		beansPage.setPassword(password);
			  		beansPage.setUserrole(userrole);
			  		beansPage.setUserdefaultrole(userdefaultrole);
			  		beansPage.setDomainName(domainname);
			  	}
		  
		  
		  public void AddMediaViewerExistingUserBean(AddUserBeanPage beansPage) {
			  Random r = new Random();
			  int Low = 1;
			  int High = 10;
			  int R = r.nextInt(High-Low) + Low;	
			  logger.info("Random number generated is "+R);
			  String domainname;
			  		if (MAILINATOR_URL.contains("mailinator"))
			  		domainname = "@mailinator.com";
			  		else
			  		domainname = "@sharklasers.com";
			  		String firstname = "mediaviewer"+R;
			  		String lastname = "mediaviewer"+R;
			  		String username = "mediaviewer"+R;
			  		String password = "Password@123";
			  		String userdefaultrole = "Media Viewer";
			  		beansPage.setFirstname(firstname);
			  		beansPage.setLastname(lastname);;
			  		beansPage.setUsername(username);
			  		beansPage.setPassword(password);
			  		beansPage.setUserdefaultrole(userdefaultrole);
			  		beansPage.setDomainname(domainname);
			  		
			  	}
		  
		  public List<String> getCaptionLanguage(){
			 List<String> CaptionlanguageList = new ArrayList<String>(); 
			 CaptionlanguageList.add("-- Select Language --");
			 CaptionlanguageList.add("Chinese");
			 CaptionlanguageList.add("Dutch");
			 CaptionlanguageList.add("English");
			 CaptionlanguageList.add("Finnish");
			 CaptionlanguageList.add("French");
			 CaptionlanguageList.add("German");
			 CaptionlanguageList.add("Greek");
			 CaptionlanguageList.add("Italian");
			 CaptionlanguageList.add("Japanese");
			 CaptionlanguageList.add("Norwegian");
			 CaptionlanguageList.add("Polish");
			 CaptionlanguageList.add("Portuguese");
			 CaptionlanguageList.add("Russian");
			 CaptionlanguageList.add("Spanish-LatinAmerica");
			 CaptionlanguageList.add("Spanish-Spain");
			 CaptionlanguageList.add("Swedish");
			 return CaptionlanguageList;
			  
		  }
	
		public List<String> getCaptionLanguagesForSpanish(){
			List<String> SpanishCaptionLanguageList= new ArrayList<String>();
			SpanishCaptionLanguageList.add("-- Seleccionar idioma --");
			SpanishCaptionLanguageList.add("Alemán");
			SpanishCaptionLanguageList.add("Chino");
			SpanishCaptionLanguageList.add("Español de España");
			SpanishCaptionLanguageList.add("Español de Latinoamérica");
			SpanishCaptionLanguageList.add("Finés");
			SpanishCaptionLanguageList.add("Francés");
			SpanishCaptionLanguageList.add("Griego");
			SpanishCaptionLanguageList.add("Holandés");
			SpanishCaptionLanguageList.add("Inglés");
			SpanishCaptionLanguageList.add("Italiano");
			SpanishCaptionLanguageList.add("Japonés");
			SpanishCaptionLanguageList.add("Noruego");
			SpanishCaptionLanguageList.add("Polaco");
			SpanishCaptionLanguageList.add("Portugués");
			SpanishCaptionLanguageList.add("Ruso");
			SpanishCaptionLanguageList.add("Sueco");
			return SpanishCaptionLanguageList;
			
		}
		
		public void addAkamaiDeviceBean(AddAkamaiDeviceBeanPage addakamaidevicebean){
			
			String akamaidevicename = "AkamaiDevice"+RandomStringUtils.randomAlphanumeric(6);
			String akamaidevicehostname = "AkamaiDeviceHost"+RandomStringUtils.randomAlphanumeric(6);
			
			addakamaidevicebean.setAkamaidevicename(akamaidevicename);
			addakamaidevicebean.setAkamaidevicehostname(akamaidevicehostname);
		}
		
		public void customfieldsBean(AvengerManageCustomFieldsBeanPage customfieldsbean){
			String value = RandomStringUtils.randomNumeric(3);
			String customfieldname = "Customfield"+value;
			String widgetvalueTextinput="Text";
			String widgetoptiontext=RandomStringUtils.randomAlphanumeric(5);
			String customfielsvaluevideocommentspage=RandomStringUtils.randomAlphabetic(5);
			
			customfieldsbean.setCustomfieldname(customfieldname);
			customfieldsbean.setWidgetvalue(widgetvalueTextinput);
			customfieldsbean.setWidgetoptionvalue(widgetoptiontext);
			customfieldsbean.setTextvalueinvideocommentspage(customfielsvaluevideocommentspage);
		}
		
		public void eventinviteurlguestuserloginBean(AvengerEventInviteURLGuestUserLoginBeanPage eventguestuserloginbeanpage){
			String guestusername="guestuser"+RandomStringUtils.randomNumeric(3);
			String guestemail=guestusername+"@sharklasers.com";
			
			eventguestuserloginbeanpage.setGuestusername(guestusername);
			eventguestuserloginbeanpage.setGuestemail(guestemail);
		}
		
		public String selectedAccount(String url)
		{
			String account;
			if(url.contains("31"))
				account="Account31";
			else if(url.contains("22"))
				account="Account22";
			else
				account="Account207";
		  return account;
		}
		
		public int randomNumberGenerationWithinRange(int low,int high)
		{
			Random Hours = new Random();
			int randomnumber=Hours.nextInt(high-low) + low;	
		    logger.info("The Random Number Generated is"+randomnumber);
			return randomnumber;
		}
		  
		
		public void avengerSystemMessage(AvengerSystemMessageBeanPage systemMessageBean) {
			
			String currentdate=DateTime.dateFormatMMDDYY();
			systemMessageBean.setEnddate(currentdate);
			systemMessageBean.setStartdate(currentdate);
			systemMessageBean.setStarttime(DateTime.getSystemTime());
			systemMessageBean.setEndtime(DateTime.addMinutesToSystemTime(2));
			systemMessageBean.setMessageText("System Maintenance message");
			
		}
		
		public void avengerBulkeditvideo(AvengerBulkEditBeanPage bulkeditbeanpage)
		{
			ArrayList<String> Status=new ArrayList<String>();
			Status.add("");
			Status.add("Active");
			Status.add("Inactive");
			
			ArrayList<String> AccessControl=new ArrayList<String>();
			AccessControl.add("");
			AccessControl.add("Public");
			AccessControl.add("All Users");
			AccessControl.add("Private");
			
			ArrayList<String> AccessControlList=new ArrayList<String>();
			AccessControlList.add("");
			AccessControlList.add("Append");
			AccessControlList.add("Replace");
			AccessControlList.add("Remove");
			
			ArrayList<String> Tags=new ArrayList<String>();
			Tags.add("");
			Tags.add("Append");
			Tags.add("Replace");
			Tags.add("Remove");
			
			ArrayList<String> Downloads=new ArrayList<String>();
			Downloads.add("");
			Downloads.add("Enabled");
			Downloads.add("Disabled");
		
			ArrayList<String> Comments=new ArrayList<String>();
			Comments.add("");
			Comments.add("Enabled");
			Comments.add("Disabled");
			
			ArrayList<String> Rating=new ArrayList<String>();
			Rating.add("");
			Rating.add("Enabled");
			Rating.add("Disabled");
			
			ArrayList<String> categories=new ArrayList<String>();
			categories.add("");
			categories.add("Append");
			categories.add("Replace");
			categories.add("Remove");
			
			Collections.sort(Status);
			Collections.sort(AccessControl);
			Collections.sort(AccessControlList);
			Collections.sort(Tags);
			Collections.sort(Downloads);
			Collections.sort(Comments);
			
			Collections.sort(Rating);
			Collections.sort(categories);
			
			
			bulkeditbeanpage.setStatus(Status);
			bulkeditbeanpage.setAccesscontrol(AccessControl);
			bulkeditbeanpage.setAccessControlList(AccessControlList);
			bulkeditbeanpage.setTags(Tags);
			bulkeditbeanpage.setDownloads(Downloads);
			bulkeditbeanpage.setComments(Comments);
			bulkeditbeanpage.setRating(Rating);
			bulkeditbeanpage.setCategories(categories);
			
			
		}
		
		 public String getHostName()
		  {
			  String hostname = null;
			 try {
				 hostname= InetAddress.getLocalHost().getHostName();
			  logger.info("ip address is"+hostname);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 return hostname; 
		  }
}
