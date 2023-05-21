package com.vbrick.avenger.setup;



/**
 * Hold all the Constants value for the Entire Project
 */
public interface Setup extends DeviceSetup{
	  final String MONGOUSERNAME="vbrick";
	 final String MONGOPASSWORD="Changeme1";

	 public static final int LABEL=1,BUTTON=2,A=3,H4=4,SPAN=5,DIV=6,STAR=7,XPATH=8;
	 
	//Browser Configuration
	public static final String SBROWSER="chrome";/*{"FireFox","Chrome","IE","Safari","Android","IPhone"}*/;
	public static final String SVERSION="";
	//Url COnfiguration
	public final String BASE_URL = "http://ec2-54-237-14-70.compute-1.amazonaws.com/#/login";
	//http://10.10.4.132:8181/vbadmin.cgi
	public final String MAILINATOR_URL="https://www.mailinator.com/";//https://www.mailinator.com/";//"https://www.guerrillamail.com/";
	public final String RIAK_URL="http://qa-u1204-riak-21.lab.vb.loc:8098/";
	// File paths for Test Base class
	public final String TESTDATEFILE = "/src/test/resources/DataSource/TestData.json";
	public final String FIREBUGPATH="/src/test/resources/Browsers_Binaries/BrowsersAddOns/firebug-1.12.0.xpi";
	public final String NETEXPORTPATH ="/src/test/resources/Browsers_Binaries/BrowsersAddOns/netExport-0.9b3.xpi";
	public final String firepath="/src/test/resources/Browsers_Binaries/BrowsersAddOns/firepath-0.9.7-fx.xpi";
    public final String RESPONSEFILEDOWNLOADPATH="/test-output/html";
    public final String CHROMEPATH ="/src/test/resources/Browsers_Binaries/Chrome/chromedriver-windows-32bit.exe"; 
    public final String IEPATH = "/src/test/resources/Browsers_Binaries/IE/IEDriverServer32.exe";
    public final String RESPONSEBODY_FAILEDCASE="/ResponseFolder/";
    public final String BACKUPFOLDER="/BackUp_Folder/";
    public final String HTMLREPORTFOLDER="/test-output/html";
    //Properties File Path 
    public final String EN_PROPERTYFILEPATH="/src/test/TextProperties/TextVerify.properties";
    public final String FRENCH_PROPERTYFILEPATH="/src/test/TextProperties/TextVerify1.properties";
    
      //File Path for Email Code and Zip Code.
    public final String EMAILCONFIG="/src/main/resources/EmailConfig/EmailConfiguration.properties";
    public final String OUTPUT_ZIP_FILE_REPORTS = "/BackupReports";
    public final String OUTPUT_ZIP_FILE = OUTPUT_ZIP_FILE_REPORTS +"/Vbricks_Reports.zip";
    public final String SOURCE_REPORTSFOLDER = "/test-output/html";
    public final String WMVVIDEOSPATH="/src/test/resources/Videos/wave.wmv";
    public final String GAMEVIDEOSPATH="/src/test/resources/Videos/Game.mp4";
    public final String FLVWMVVIDEOSPATH="/src/test/resources/Videos/flvvideo.flv";
    public final String MP4VIDEOSPATH="/src/test/resources/Videos/Agile.mp4";
    public final String replaceVideoPath="/src/test/resources/Videos/mp4video.MP4";
    public final String INVALIDFILEWMVVIDEOSPATH="/src/test/resources/Videos/Acceptance.xlsx";
    public final String VALIDIMAGEPATH_JPG="/src/test/UploadImages/CompuGain_logo.jpg";
    public final String VALIDIMAGEPATH_JPG1 ="/src/test/resources/ImageFiles/pexels-photo-1433052.jpeg";
    public final String VALIDIMAGEPATH_GIF="/src/test/UploadImages/Test1.gif"; 
    public final String VALIDIMAGEPATH_PNG="/src/test/UploadImages/Test1.png";
    public final String VALIDIMAGEPATH_JPG2="/src/test/UploadImages/Tulips.jpg";
    public final String VALIDIMAGEPATH_JPG3="/src/test/UploadImages/Tiger.jpg";
    public final String INVALIDIMAGEPATH_LOGO = "/src/test/UploadImages/Test1.bitmap";
    public final String DOWNLOADSPATH="/src/test/Downloads";
    public final String COMMENTSTEXTPATH="/src/test/Downloads/comments.txt";
    public final String BUILDNUMBER="/src/main/resources/BuildFile/BuildNumber.txt";
    
    public final String INVALIDLOGOPATH="/src/test/resources/ImageFiles/image.png";
    
    public final String NOTIFICATION1PATH="/src/test/resources/Videos/notification2.mp4";
    public final String NOTIFICATION2PATH="/src/test/resources/Videos/mp4video.MP4";
    public final String NOTIFICATION3PATH="/src/test/resources/Videos/flvvideo.flv";
    public final String NOTIFICATION4PATH="/src/test/resources/Videos/solar_eclipse.mp4";
    
    
    //---Accounts
    public final String MANUALACCOUNTSFILE="src/test/TestDataJSON/ManualAccounts.json";
    public final String AUTOMATIONACCOUNTSFILE="src/test/TestDataJSON/AutomationAccounts.json";
    public final String CIACCOUNTSFILE="src/test/TestDataJSON/CIAccounts.json";
    
    
    //- Users
    public final String MANUALUSERSFILE="src/test/TestDataJSON/ManualUsers.json";
    public final String AUTOMATIONUSERSFILE="src/test/TestDataJSON/AutomationUsers.json";
    public final String ACTIVEAUTOMATIONUSERSFILE="src/test/TestDataJSON/ActiveAutomationUsers.json";
    public final String GRIDAUTOMATIONUSERSFILE="src/test/TestDataJSON/GridAutomationUsers.json";
    
    //PPT Path
    public final String SRTFILEPATH3 = "/src/test/resources/FileTypes/SRTfile3.srt";
    public final String SRTFILEPATH2 = "/src/test/resources/FileTypes/SRTfile2.srt";
    public final String SRTFILEPATH = "/src/test/resources/FileTypes/SRTfile.srt";
    public final String EVENTPPTPATH ="/src/test/resources/PPTs/Presentation.ppt";
    public final String EVENTSECONDPPTPATH="/src/test/resources/PPTs/Presentation1.ppt";
    public final String KILLCHROME="/src/test/resources/TaskKillBrowsers/KillChrome.bat";
    public final String KILLIE="/src/test/resources/TaskKillBrowsers/KillIE.bat";
    public final String KILLFIREFOX="/src/test/resources/TaskKillBrowsers/KillFirefox.bat";
    public final String KILLSAFARI="/src/test/resources/TaskKillBrowsers/KillSafari.bat";
    public final String CONHOST="/src/test/resources/TaskKillBrowsers/conhost.bat";
    public final String WORDDOCUMENTPATH ="/src/test/resources/FileTypes/worddocument.docx";
    public final String PDFDOCUMENTPATH ="/src/test/resources/FileTypes/pdfdocument.pdf";
    public final String ZIPDOCUMENTPATH ="/src/test/resources/FileTypes/ZipDocument.zip";
    public final String TEXTDOCUMENTPATH ="/src/test/resources/FileTypes/textdocument.txt";
    
    public final String HTMLFILE="/src/test/resources/EmbedHtml.html";
    public final String EXECUTIONSTATUS="/src/test/resources/ExecutionStatus.csv";
    public final String PIECHARTLOCATION="/src/test/resources/ExecutionReport.jpeg";
    public final String BARCHARTLOCATION="/src/test/resources/GroupsReport.jpeg";
    public final String FAILEDCASES="/src/test/resources/FailedCases.csv";
    public final String PASSEDCASES="/src/test/resources/PassedCases.csv";
    public final String SKIPPEDCASES="/src/test/resources/SkippedCases.csv";
    public final String uservalue="griduser";
    String apiuservalue="apigtest";
    String deletionuser="apiaaudel";
    String deletionuser1="apiaaudel1";
    String deletionuser2="apiaaudel2";
    String deletionuser3="apiaaudel3";
    String deletionuser4="apiaaudel4";
    String deletionuser5="apiaaudel5";
    String deletionuser6="apiaaudel6";
    String deletionuser7="apiaaudel7";
    String deletionuser8="apiaaudel8";
    String deletionuser9="apiaaudel9";
    public final String mediaviewer="mediaviewerapi";
    public final String SANITYUSER="griduser20";
    public final String SANITYUSER2="griduser2";
    public final String MEDIAADMIN_AUTO="mediaadminapi";
    public final String MEDIACONTRIBUTER_AUTO= "mediacontributorapi";
    public final String MEDIAVIEWER_AUTO="mediaviewer_auto";
    public final String EVENTADMIN_AUTO="eventadmin_auto";
    public final String SANITYPASSWORD="Password@123";
    //public final String SANITYPASSWORD="vbqavbqa";
    public final String DYNAMICSUITE = "/CustomSuite.xml";
    public final int CLASSNAME=1,VALUE=2,INNERHTML=3,OUTERHTML=4;
    public static String CiscoSpark_URL="https://web.ciscospark.com/#/signin";

	public static String AutomationUserName ="kalyan";
	public static String AutomationPassword ="Password@123";
	//Multinode URL:http://qa-u1204-ha-31.lab.vb.loc/
	public static  String browser ="chrome";
	public static  String language ="en";
	public static  String grid ="";
    public static String sEnvironment="Automation";
    public static String sRandomData="YES";
//    public static String testcasefile = "C:/Users/praviteja.CSILHYD1/Desktop/RegressionTestCases.xlsx";
    public static String seleniumjar="2.44.0";
    public static String log4jjar="1.2.15";
    public static String REVCREATE_URL="http://vbrick.com/revcreate/index.html";
    public static String ACCOUNTUSER="griduser1";
    public static String testcasefile = "/src/test/resources/Sanity.xls";
    public static String CREATEXMlFILE="reusable.xml";
    public static String ROLESENVSETUPUSER="RolesUser";

	String defaultEmailID="kalyan.golla@Compugain.com,jayanthi.mucharla@compugain.com,jayanthi.mucharla@vbrick.com,ntemkar@vbrick.com,rakesh.gudafwar@vbrick.com";


    //public Map<String,String> runtimeEnvironment();
    //Roles Data    
    public static String ROLESVIDEO="PublicVideo";
    public static String ROLESPUBLICEVENT="PublicEvent";
    public static String ROLESPRIVATEEVENT="PrivateEvent";
    public static String ROLESINACTIVEVIDEO="InactiveVideo";
    public static String ROLESDELETEEVENT="DeleteEvent";
    public static String ROLESEDITEVENT="EditEvent";
    public static String ROLESPRIVATEEVENTACCESS="PrivateEventAccess";
    public static boolean sanityrunstatus=true;
    
}
