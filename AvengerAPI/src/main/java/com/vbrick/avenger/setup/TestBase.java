package com.vbrick.avenger.setup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vbrick.avenger.funUtil.FileOperation;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase implements Setup, Epic_Group, EnviromentAccounts {

	public int grid = 1;
//	public boolean enviromentFlag=true;
	public WebDriver driver;
	public WebDriver secondDriver, thirdDriver;
	public FirefoxProfile profile;
	public String sUserName, sPassword, sEnvironment, sversion1, sbrowser;
	public String surl, surlA, surlB, surlC, surlD, surlE, surlF;
	public static String language = "en";
	private static Logger logger = Logger.getLogger(TestBase.class);
	private static String versionvalue = "";
	public String DBSTRING = null;

	public void Mongodetails() {
		if (Configuration.getAutomationURL().contains("31"))
			DBSTRING = "qa-u1204-mongo-31a.lab.vb.loc";
		else
			DBSTRING = "qa-u1204-mongo-22.lab.vb.loc";
	}

	public static String getVersionvalue() {
		return versionvalue;
	}

	public static void setVersionvalue(String versionvalue) {
		TestBase.versionvalue = versionvalue;
	}

	public WebDriver initializeDriver(String sbrowser, String sgrid) {
		WebDriver driver = null;
		logger.info("Test Base Version is" + Configuration.getGrid());
		logger.info("Test Base Browser is" + sbrowser);
		sgrid = Configuration.getGrid();
		if (sgrid.equalsIgnoreCase("")) {
			sbrowser = Configuration.getAutomationBrowser();
			return driver = initializeDriver(sbrowser);
		} else if (!sgrid.equalsIgnoreCase("") && !sbrowser.equalsIgnoreCase("")) {
			logger.info("I am in Grid Intialization");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			URL hubUrl = null;
			try {
				hubUrl = new URL("http://localhost:4444/wb/hub");
				if (sbrowser.equalsIgnoreCase("firefox")) {
					FirefoxProfile profile = new FirefoxProfile();
					profile.setPreference("browser.download.folderList", 2);
					capabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
					capabilities.setPlatform(Platform.WINDOWS);
					driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
					// remoteWebDriver.setFileDetector(new LocalFileDetector());
				}
				if (sbrowser.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", getFilePath(CHROMEPATH));
					capabilities.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
					// capabilities.setVersion(sversion1);
					capabilities.setPlatform(Platform.WINDOWS);
					driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		/*
		 * else if(!sversion1.equalsIgnoreCase("")&&!sbrowser.equalsIgnoreCase("")) {
		 * logger.info("Jenkins Grid Intialization"); LocalDriverFactory
		 * localDriverFactory = new LocalDriverFactory(); try { driver =
		 * localDriverFactory.createInstance(sbrowser); } catch (MalformedURLException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * logger.info("I am in jenkins intialization"); LocalDriverManager
		 * localDriverManager= new LocalDriverManager();
		 * localDriverManager.setWebDriver(driver); return
		 * localDriverManager.getDriver(); } else {
		 * logger.error("Configuration problem with version"); }
		 */
		return driver;

	}

	/*
	 * public WebDriver initializeDriver(String sbrowser, String version) throws
	 * MalformedURLException { URL hubUrl = null; DesiredCapabilities capabilities =
	 * new DesiredCapabilities(); try { //hubUrl = new
	 * URL("http://localhost:4444/wb/hub"); if (sbrowser.equalsIgnoreCase("chrome"))
	 * {
	 * 
	 * System.setProperty("webdriver.chrome.driver",getFilePath(chromePath));
	 * capabilities.setBrowserName(sbrowser); //capabilities.setVersion(version);
	 * capabilities.setPlatform(Platform.WINDOWS); driver= new RemoteWebDriver(new
	 * URL("http://localhost:4444/wb/hub"), capabilities); }
	 * 
	 * else if (sbrowser.equalsIgnoreCase("firefox")) { FirefoxProfile profile = new
	 * FirefoxProfile(); profile.setPreference("browser.download.folderList", 2);
	 * capabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
	 * capabilities.setVersion(version); driver = new RemoteWebDriver(hubUrl,
	 * capabilities); }
	 * 
	 * else if (sbrowser.equalsIgnoreCase("safari")) {
	 * capabilities.setBrowserName(DesiredCapabilities.safari().getBrowserName());
	 * capabilities.setVersion(version); driver = new RemoteWebDriver(hubUrl,
	 * capabilities); }
	 * 
	 * else if (sbrowser.equalsIgnoreCase("IE")) { if
	 * (System.getProperty("os.arch").contains("86")) {
	 * System.setProperty("webdriver.ie.driver", getFilePath(IEPath)); } else if
	 * (System.getProperty("os.arch").contains("64")){
	 * System.setProperty("webdriver.ie.driver", getFilePath(IEPath)); } } } catch
	 * (MalformedURLException e) { e.printStackTrace(); } return driver; }
	 */

	/*
	 * public String testDataPresent(String stext) { if(stext!=null) {
	 * logger.info("The value is present in  Resource Bundle File is"+stext); } else
	 * { logger.
	 * error("************The value is NOT present in  Resource Bundle File*************"
	 * ); } return stext; }
	 */

	/*
	 * private String start31 ="http://qa-u1204-ha-31"; private String
	 * end31=".lab.vb.loc"; private String start22 ="http://qa-w12-avg-22"; private
	 * String end22=".lab.vb.loc"; private LinkedList<String> hostNames=null;
	 * private String shostName_sub[]= {"a","b","c","d","e","f"};
	 * 
	 * private LinkedList<String> setHostNames() { hostNames = new
	 * LinkedList<String>();
	 * 
	 * for (int i = 0; i < shostName_sub.length; i++) { if
	 * (Configuration.getAutomationURL().contains("31")) { if (i == 0)
	 * hostNames.add(start31 + end31); hostNames.add(start31 + shostName_sub[i] +
	 * end31); } else { if (i == 0) hostNames.add(start22 + end22);
	 * hostNames.add(start22 + shostName_sub[i] + end22);
	 * 
	 * } } if(Configuration.getMutipleEnv().equals("NO")) {
	 * if(Configuration.getAutomationURL().contains("31")){ surl = start31+end31;
	 * surlA = start31+end31; surlB = start31+end31; surlC =start31+end31; surlD =
	 * start31+end31; surlE = start31+end31; surlF = start31+end31; } else { surl =
	 * start22+end22; surlA = start22+end22; surlB = start22+end22; surlC
	 * =start22+end22; surlD = start22+end22; surlE = start22+end22; surlF =
	 * start22+end22; } } else { surl = AutomationURL; surlA = hostNames.get(1);
	 * surlB = hostNames.get(2); surlC = hostNames.get(3); surlD = hostNames.get(4);
	 * surlE = hostNames.get(5); surlF = hostNames.get(6); }
	 * 
	 * return hostNames; }
	 */

	// private String env31="http://qa-w12-avg-31.lab.vb.loc/";
	// private String guest31="#/guest";
	// private String env22="http://qa-w12-avg-22.lab.vb.loc/";

	public void setAccountHostNames() {
		LinkedList<String> accounts = setAccounts();
		surlA = accounts.get(0);
		surlB = accounts.get(1);
		surlC = accounts.get(2);
		surlD = accounts.get(3);
		surlE = accounts.get(4);
		surlF = accounts.get(5);
		logger.info("@@@@@The Urls are" + surlA);
		logger.info("#####The Urls are" + surlB);
		logger.info("The Urls are" + surlC);
		logger.info("The Urls are" + surlD);
		logger.info("The Urls are" + surlE);
		logger.info("The Urls are" + surlF);
	}

	public LinkedList<String> setAccounts() {
		LinkedList<String> accounthostNames = null;
		accounthostNames = new LinkedList<String>();
		String automationurl = Configuration.getAutomationURL();
		if (AccountsFlag.equalsIgnoreCase("Yes")) {
			if (automationurl.contains("31")) {
				accounthostNames.add(surlA31);
				accounthostNames.add(surlB31);
				accounthostNames.add(surlC31);
				accounthostNames.add(surlD31);
				accounthostNames.add(surlE31);
				accounthostNames.add(surlF31);

			} else if (automationurl.contains("22")) {
				accounthostNames.add(surlA22);
				accounthostNames.add(surlB22);
				accounthostNames.add(surlC22);
				accounthostNames.add(surlD22);
				accounthostNames.add(surlE22);
				accounthostNames.add(surlF22);
			}

			else if (automationurl.contains("207")) {
				accounthostNames.add(surlA207);
				accounthostNames.add(surlB207);
				accounthostNames.add(surlC207);
				accounthostNames.add(surlD207);
				accounthostNames.add(surlE207);
				accounthostNames.add(surlF207);

			}

			else {
				accounthostNames.add(automationurl);
				accounthostNames.add(automationurl);
				accounthostNames.add(automationurl);
				accounthostNames.add(automationurl);
				accounthostNames.add(automationurl);
				accounthostNames.add(automationurl);
			}

		} else {
			accounthostNames.add(automationurl);
			accounthostNames.add(automationurl);
			accounthostNames.add(automationurl);
			accounthostNames.add(automationurl);
			accounthostNames.add(automationurl);
			accounthostNames.add(automationurl);
		}
		return accounthostNames;
	}

	public String getGuestURL() {
		String guestName = null;
		String envUrl = Configuration.AutomationURL;
		if (envUrl.contains("31"))
			guestName = Guest31URL;
		else if (envUrl.contains("22"))
			guestName = Guest22URL;
		else if (envUrl.contains("207"))
			guestName = Guest207URL;

		logger.info("The Guest Url is" + guestName);
		return guestName;
	}

	/*
	 * Basic Run Time Environment are Captured Here
	 */
	Configuration Configuration;

	public TestBase() {
		surl = Configuration.getAutomationURL();
		setAccountHostNames();
		Mongodetails();
		sUserName = Configuration.getAutomationUsername();
		sPassword = Configuration.getAutomationPassword();
		sEnvironment = Configuration.getEnvironment();
		sbrowser = Configuration.getAutomationBrowser();
		sversion1 = Configuration.getGrid();
		logger.info("Url is" + surl);
		logger.info("Environment is" + sEnvironment);
		logger.info("Browser is" + sbrowser);
		logger.info("Version value is" + sversion1);
	}

	/**
	 * Initializes web driver object based on the browser type
	 * 
	 * @param sbrowser : THe browser we need to run
	 * @param surl     : the URL of the application
	 * @return : Webdriver object
	 */
	public WebDriver initializeDriver(String sbrowser/* ,String surl */) {
		WebDriver driver = null;
		logger.info("Browser Name:" + sbrowser);
		// logger.info(" Application surl:"+surl);
		if (driver == null) {
			if (sbrowser.equalsIgnoreCase("firefox")) {

				/*
				 * logger.info("The value of Path  is------------->"+System.getProperty(
				 * "user.dir")+firebugPath); File firebug = new File(getFilePath(firebugPath));
				 * File firepath_new = new File(getFilePath(firepath));
				 */
				// File netExport = new File(getFilePath(netExportPath));

				/*
				 * Capabilities cap = dri.getCapabilities(); String browserName =
				 * cap.getBrowserName().toLowerCase();
				 * System.out.println("Browser Name"+browserName); String os =
				 * cap.getPlatform().toString(); System.out.println("operating system"+os);
				 * String v = cap.getVersion().toString(); System.out.println("Version is"+v);
				 *//*
					 * try { profile.addExtension(firebug); profile.addExtension(firepath_new);
					 * //profile.addExtension(netExport); } catch (IOException e) {
					 * e.printStackTrace(); }
					 */
				/*
				 * profile.setPreference("app.update.enabled", false);
				 * profile.setPreference("extensions.firebug.currentVersion", "2.0");
				 * profile.setPreference("extensions.firebug.addonBarOpened", true);
				 * profile.setPreference("extensions.firebug.console.enableSites", true);
				 * profile.setPreference("extensions.firebug.script.enableSites", true);
				 * profile.setPreference("extensions.firebug.net.enableSites", true);
				 * profile.setPreference("extensions.firebug.previousPlacement", 1);
				 * profile.setPreference("extensions.firebug.allPagesActivation", "on");
				 * profile.setPreference("extensions.firebug.onByDefault", true);
				 * profile.setPreference("extensions.firebug.defaultPanelName", "net");
				 * 
				 * profile.setPreference( "extensions.firebug.netexport.alwaysEnableAutoExport",
				 * true);
				 * profile.setPreference("extensions.firebug.netexport.autoExportToFile",true);
				 * profile.setPreference("extensions.firebug.netexport.Automation", true);
				 * profile.setPreference("extensions.firebug.netexport.showPreview", false);
				 * logger.info("The path of the response folder is "+System.getProperty(
				 * "user.dir")+"\\ResponseFolder\\");
				 * 
				 * profile.setPreference("extensions.firebug.netexport.defaultLogDir",
				 * getFilePath(responseFileDownloadpath));
				 */
				// FirefoxProfile profile = new FirefoxProfile();
				// profile.setEnableNativeEvents(true);
				// logger.info("enabled events are"+profile.areNativeEventsEnabled());
				/*
				 * DesiredCapabilities capabilities = new DesiredCapabilities();
				 * capabilities.setBrowserName(sbrowser);
				 * capabilities.setPlatform(org.openqa.selenium.Platform.ANY);
				 * capabilities.setCapability(FirefoxDriver.PROFILE, profile);
				 * 
				 */
				String downloadPath = FileOperation.getFilePath(DOWNLOADSPATH);
				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("browser.download.folderList", 2);
				profile.setPreference("browser.download.manager.showWhenStarting", false);
				profile.setPreference("browser.download.dir", downloadPath);
				profile.setPreference("browser.helperApps.neverAsk.openFile",
						"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
						"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
				profile.setPreference("browser.helperApps.alwaysAsk.force", false);
				profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
				profile.setPreference("browser.download.manager.focusWhenStarting", false);
				profile.setPreference("browser.download.manager.useWindow", false);
				profile.setPreference("browser.download.manager.showAlertOnComplete", false);
				profile.setPreference("browser.download.manager.closeWhenDone", false);
				profile.setPreference("xpinstall.signatures.required", false);
				logger.info("The Profile Setup Data");
				driver = new FirefoxDriver(profile);
				/*
				 * webdriver= new EventFiringWebDriver(driver); EventListerners
				 * eventListerners=new EventListerners(); webdriver.register(eventListerners);
				 */ String s = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
				logger.info("Browser name : " + s);
			}

			else if (sbrowser.equalsIgnoreCase("Chrome")) {
				/*
				 * logger.info("The Profile set up started @@@@@@@@@@@@@@@@@@@@@@@@@@@");
				 * System.setProperty("webdriver.chrome.driver", getFilePath(CHROMEPATH));
				 * HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				 * chromePrefs.put("profile.default_content_settings.popups", 0);
				 * chromePrefs.put("download.default_directory",getFilePath(DOWNLOADSPATH));
				 * ChromeOptions options = new ChromeOptions(); HashMap<String, Object>
				 * chromeOptionsMap = new HashMap<String, Object>();
				 * options.setExperimentalOption("prefs", chromePrefs);
				 * options.addArguments("--test-type"); DesiredCapabilities cap =
				 * DesiredCapabilities.chrome(); //cap.setCapability(ChromeOptions.CAPABILITY,
				 * chromeOptionsMap); cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				 * cap.setCapability(ChromeOptions.CAPABILITY, options);
				 * logger.info("Initializing chrome webDriver"); driver = new ChromeDriver(cap);
				 */
				/*
				 * System.out.println("I am into driver code@@@@@@@@@@@@@@@"); ChromeOptions
				 * options = new ChromeOptions(); options.
				 * setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"
				 * ); System.out.println("@@@@@@chrome path is"+getFilePath(CHROMEPATH));
				 * System.out.println("@@@@ Options value is"+options);
				 * //System.setProperty("webdriver.chrome.driver",
				 * "C:\\Users\\TemkarNa\\git\\Avenger-Automation-API\\AvengerAPI\\drivers\\chromedriver-windows-32bit.exe"
				 * ); System.setProperty("webdriver.chrome.driver", getFilePath(CHROMEPATH));
				 * 
				 * driver =new ChromeDriver(options); //driver = new
				 * ChromeDriver(chromeProfileSetUp());
				 * logger.info("Driver value is #####################"+driver);
				 * logger.info("The Profile set up Ended #####################");
				 * 
				 * 
				 * //driver = new ChromeDriver(chromeProfileSetUp()); webdriver= new
				 * EventFiringWebDriver(driver); EventListerners eventListerners=new
				 * EventListerners(); webdriver.register(eventListerners);
				 * 
				 * String s = (String) ((JavascriptExecutor)
				 * driver).executeScript("return navigator.userAgent;");
				 * System.out.println("Browser name : " + s.split("Chrome")); String[] result =
				 * s.split("Chrome"); logger.info("Result value is"+result[1].substring(1,6));
				 * versionvalue=result[1].substring(1,6); setVersionvalue(versionvalue);
				 */
				WebDriverManager.chromedriver().setup();
				 driver = new ChromeDriver();
				 logger.info("@@@@@@@@@@@@Chrome browser intialized"+driver);
			} else if (sbrowser.equalsIgnoreCase("IE")) {
				/*
				 * if (System.getProperty("os.arch").contains("86")) {
				 * logger.info("System is---"+System.getProperty("os.arch")+"---bit");
				 * System.setProperty("webdriver.ie.driver",
				 * getFilePath("/src/test/resources/Browsers_Binaries/IE/IEDriverServer64.exe"))
				 * ; } else if (System.getProperty("os.arch").contains("64")) {
				 * logger.info("System is---"+System.getProperty("os.arch")+"---bit");
				 * System.setProperty("webdriver.ie.driver",
				 * getFilePath("/src/test/resources/Browsers_Binaries/IE/IEDriverServer64.exe"))
				 * ; }
				 */

				driver = new InternetExplorerDriver(ieProfileSetUp());
				/*
				 * webdriver= new EventFiringWebDriver(driver); EventListerners
				 * eventListerners=new EventListerners(); webdriver.register(eventListerners);
				 */ } else if (sbrowser.equalsIgnoreCase("Safari")) {
				DesiredCapabilities capabilities = DesiredCapabilities.safari();
				driver = new SafariDriver(capabilities);
				/*
				 * webdriver= new EventFiringWebDriver(driver); EventListerners
				 * eventListerners=new EventListerners(); webdriver.register(eventListerners);
				 */ }

		}
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		System.out.println("@2@@@driver value is" + driver);
		return driver;
	}

	public WebDriver secondDriverInitialization(String sbrowser/* ,String surl */) {

		sbrowser = Configuration.getAutomationBrowser();
		logger.info("Browser Name:" + sbrowser);
		// logger.info(" Application surl:"+surl);
		if (secondDriver == null) {
			if (sbrowser.equalsIgnoreCase("firefox")) {
				String downloadPath = FileOperation.getFilePath(DOWNLOADSPATH);
				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("browser.download.folderList", 2);
				profile.setPreference("browser.download.manager.showWhenStarting", false);
				profile.setPreference("browser.download.dir", downloadPath);
				profile.setPreference("browser.helperApps.neverAsk.openFile",
						"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
						"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
				profile.setPreference("browser.helperApps.alwaysAsk.force", false);
				profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
				profile.setPreference("browser.download.manager.focusWhenStarting", false);
				profile.setPreference("browser.download.manager.useWindow", false);
				profile.setPreference("browser.download.manager.showAlertOnComplete", false);
				profile.setPreference("browser.download.manager.closeWhenDone", false);
				profile.setPreference("xpinstall.signatures.required", false);
				logger.info("The Profile Setup Data");
				secondDriver = new FirefoxDriver(profile);
				// secondDriver = new FirefoxDriver(firefoxProfileSetUp());
				/*
				 * webdriver= new EventFiringWebDriver(driver); EventListerners
				 * eventListerners=new EventListerners(); webdriver.register(eventListerners);
				 */
			} else if (sbrowser.equalsIgnoreCase("Chrome")) {
				/*
				 * System.setProperty("webdriver.chrome.driver", getFilePath(CHROMEPATH));
				 * HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				 * chromePrefs.put("profile.default_content_settings.popups", 0);
				 * chromePrefs.put("download.default_directory",getFilePath(DOWNLOADSPATH));
				 * ChromeOptions options = new ChromeOptions(); HashMap<String, Object>
				 * chromeOptionsMap = new HashMap<String, Object>();
				 * options.setExperimentalOption("prefs", chromePrefs);
				 * options.addArguments("--test-type"); DesiredCapabilities cap =
				 * DesiredCapabilities.chrome(); cap.setCapability(ChromeOptions.CAPABILITY,
				 * chromeOptionsMap); cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				 * cap.setCapability(ChromeOptions.CAPABILITY, options);
				 */ secondDriver = new ChromeDriver(chromeProfileSetUp());
				// secondDriver = new ChromeDriver(chromeProfileSetUp());
				/*
				 * webdriver= new EventFiringWebDriver(driver); EventListerners
				 * eventListerners=new EventListerners(); webdriver.register(eventListerners);
				 */ } else if (sbrowser.equalsIgnoreCase("IE")) {
				/*
				 * if (System.getProperty("os.arch").contains("86")) {
				 * logger.info("System is---"+System.getProperty("os.arch")+"---bit");
				 * System.setProperty("webdriver.ie.driver",
				 * getFilePath("/src/test/resources/Browsers_Binaries/IE/IEDriverServer64.exe"))
				 * ; } else if (System.getProperty("os.arch").contains("64")) {
				 * logger.info("System is---"+System.getProperty("os.arch")+"---bit");
				 * System.setProperty("webdriver.ie.driver",
				 * getFilePath("/src/test/resources/Browsers_Binaries/IE/IEDriverServer64.exe"))
				 * ; }
				 */
				secondDriver = new InternetExplorerDriver(ieProfileSetUp());
				/*
				 * webdriver= new EventFiringWebDriver(driver); EventListerners
				 * eventListerners=new EventListerners(); webdriver.register(eventListerners);
				 */} else if (sbrowser.equalsIgnoreCase("Safari")) {
				DesiredCapabilities capabilities = DesiredCapabilities.safari();
				secondDriver = new SafariDriver(capabilities);
				/*
				 * webdriver= new EventFiringWebDriver(driver); EventListerners
				 * eventListerners=new EventListerners(); webdriver.register(eventListerners);
				 */}

		}
		secondDriver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		return secondDriver;
	}

	public WebDriver thirdDriverInitialization(String sbrowser/* ,String surl */) {
		sbrowser = Configuration.getAutomationBrowser();
		logger.info("Browser Name:" + sbrowser);
		// logger.info(" Application surl:"+surl);
		if (thirdDriver == null) {
			if (sbrowser.equalsIgnoreCase("firefox")) {
				String downloadPath = FileOperation.getFilePath(DOWNLOADSPATH);
				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("browser.download.folderList", 2);
				profile.setPreference("browser.download.manager.showWhenStarting", false);
				profile.setPreference("browser.download.dir", downloadPath);
				profile.setPreference("browser.helperApps.neverAsk.openFile",
						"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
						"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
				profile.setPreference("browser.helperApps.alwaysAsk.force", false);
				profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
				profile.setPreference("browser.download.manager.focusWhenStarting", false);
				profile.setPreference("browser.download.manager.useWindow", false);
				profile.setPreference("browser.download.manager.showAlertOnComplete", false);
				profile.setPreference("browser.download.manager.closeWhenDone", false);
				profile.setPreference("xpinstall.signatures.required", false);
				logger.info("The Profile Setup Data");
				thirdDriver = new FirefoxDriver(profile);

				// thirdDriver = new FirefoxDriver(firefoxProfileSetUp());
				/*
				 * webdriver= new EventFiringWebDriver(driver); EventListerners
				 * eventListerners=new EventListerners(); webdriver.register(eventListerners);
				 */} else if (sbrowser.equalsIgnoreCase("Chrome")) {
				thirdDriver = new ChromeDriver(chromeProfileSetUp());
			} else if (sbrowser.equalsIgnoreCase("IE")) {
				thirdDriver = new InternetExplorerDriver(ieProfileSetUp());
			} else if (sbrowser.equalsIgnoreCase("Safari")) {
				DesiredCapabilities capabilities = DesiredCapabilities.safari();
				thirdDriver = new SafariDriver(capabilities);
			}

		}
		thirdDriver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		return thirdDriver;
		// return webdriver;
	}

	public WebDriver driverIntialization(String sbrowser) {
		WebDriver fourthdriver = null;
		sbrowser = Configuration.getAutomationBrowser();
		logger.info("Browser Name:" + sbrowser);
		if (fourthdriver == null) {
			if (sbrowser.equalsIgnoreCase("firefox")) {
				String downloadPath = FileOperation.getFilePath(DOWNLOADSPATH);
				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("browser.download.folderList", 2);
				profile.setPreference("browser.download.manager.showWhenStarting", false);
				profile.setPreference("browser.download.dir", downloadPath);
				profile.setPreference("browser.helperApps.neverAsk.openFile",
						"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
						"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
				profile.setPreference("browser.helperApps.alwaysAsk.force", false);
				profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
				profile.setPreference("browser.download.manager.focusWhenStarting", false);
				profile.setPreference("browser.download.manager.useWindow", false);
				profile.setPreference("browser.download.manager.showAlertOnComplete", false);
				profile.setPreference("browser.download.manager.closeWhenDone", false);
				profile.setPreference("xpinstall.signatures.required", false);
				logger.info("The Profile Setup Data");
				fourthdriver = new FirefoxDriver(profile);
			} else if (sbrowser.equalsIgnoreCase("Chrome")) {
				fourthdriver = new ChromeDriver(chromeProfileSetUp());
			} else if (sbrowser.equalsIgnoreCase("IE")) {
				fourthdriver = new InternetExplorerDriver(ieProfileSetUp());
			} else if (sbrowser.equalsIgnoreCase("Safari")) {
				DesiredCapabilities capabilities = DesiredCapabilities.safari();
				fourthdriver = new SafariDriver(capabilities);
			}

		}
		fourthdriver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		return fourthdriver;
	}

	public void navigateTo(String surl, WebDriver driver) {

		driver.manage().deleteAllCookies();
		driver.navigate().to(surl);
	}

	public void navigateTo(String surl) {

		driver.manage().deleteAllCookies();
		driver.navigate().to(surl);
	}

	public void navigateTo_DeleteCookie(String surl, WebDriver driver) {
		driver.manage().deleteAllCookies();
		driver.navigate().to(surl);
	}

	public void launchURL(String surl) {
		driver.get(surl);
		logger.info("Application lauched with URL" + surl);
	}

	public void launchURL(String surl, WebDriver driver) {
		driver.get(surl);
		logger.info("Application lauched with URL" + surl);
	}

	public void browserQuit() {
		logout(driver);
		driver.close();
		driver = null;
	}

	public void browserQuit(WebDriver driver) {
		try {
			driver.quit();
			driver = null;
		} catch (Exception e) {
			logger.info("Event Driver is Not Initialized");
		}

	}

	public void browserQuit_WithoutLogout() {
		driver.quit();
		driver = null;
	}

	public String getFilePath(String sFilepath) {
		char cforwardslash = (char) 47;
		char cbackslash = (char) 92;
		logger.info("File path is " + sFilepath);
		String sPath = System.getProperty("user.dir").replace(cbackslash, cforwardslash) + sFilepath;

		File file = new File(sPath);
		if (file.exists()) {
			sPath = file.getAbsolutePath();
			logger.info("The File Path is " + sPath);
		} else {
		}
		return sPath;
	}

	/**
	 * Loads the Property File
	 * 
	 * @param prop
	 * @return:Returns the Property Object
	 */
	public void loadPropertiesFile(Properties prop, String language) {

		logger.info("In the Load Properties File");
		com.vbrick.avenger.funUtil.LanguageSelect lanSelect = new com.vbrick.avenger.funUtil.LanguageSelect();
		try {
			lanSelect.getLanguage(prop, language);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * try { logger.info("In the Properties File Method"); String
		 * spropPath="/src/test/TextProperties/TextVerify.properties"; prop.load(new
		 * FileInputStream(getFilePath(spropPath))); } catch (FileNotFoundException e) {
		 * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
		 */

	}

	/**
	 * 
	 * @return : The language present in the HTML.
	 */
	public String getLanguagevalue(WebDriver driver) {
		String sflag = "";
		logger.info("message" + driver.findElement(By.xpath("//html[@class='ng-scope']")).getAttribute("lang"));
		if (driver.findElement(By.xpath("//html[@class='ng-scope']")).getAttribute("lang") != null) {
			sflag = driver.findElement(By.xpath("//html[@class='ng-scope']")).getAttribute("lang");
		} else {
			sflag = "en";
		}
		return sflag;
	}

	/**
	 * Determining Runtime Environment From Pom and Load Particular files on a Map
	 */
	public Map<String, String> runtimeEnvironment() {

		Map<String, String> runTimeFilesForEnvironment = new HashMap<String, String>();

		if (sEnvironment.equalsIgnoreCase("Manual")) {
			logger.info("Run Time Environment is Manual");
			runTimeFilesForEnvironment.put("users", MANUALUSERSFILE);
			runTimeFilesForEnvironment.put("accounts", MANUALACCOUNTSFILE);
		} else if (sEnvironment.equalsIgnoreCase("Automation")) {
			logger.info("Run Time Environment is Automation");
			runTimeFilesForEnvironment.put("users", AUTOMATIONUSERSFILE);
			runTimeFilesForEnvironment.put("accounts", AUTOMATIONACCOUNTSFILE);

		} else if (sEnvironment.equalsIgnoreCase("CI")) {
			logger.info("Run Time Environment is CI");
			runTimeFilesForEnvironment.put("users", AUTOMATIONUSERSFILE);
			runTimeFilesForEnvironment.put("accounts", CIACCOUNTSFILE);

		} else if (sEnvironment.equalsIgnoreCase("Manual2")) {
			logger.info("Run Time Environment is Manual2");
			runTimeFilesForEnvironment.put("users", MANUALUSERSFILE);
			runTimeFilesForEnvironment.put("accounts", MANUALACCOUNTSFILE);

		}

		else if (sEnvironment.equalsIgnoreCase("Any")) {
			logger.info("Run Time Environment is Any");
			runTimeFilesForEnvironment.put("users", AUTOMATIONUSERSFILE);
			runTimeFilesForEnvironment.put("accounts", AUTOMATIONACCOUNTSFILE);

		}

		return runTimeFilesForEnvironment;
	}

	public void logout(WebDriver driver) {
		try {
			if (driver.findElement(By.xpath("//*[contains(@class,'glyphicons user')]/..")).isDisplayed()) {
				WebDriverWait driverwait = new WebDriverWait(driver, 60);
				driver.findElement(By.xpath("//*[contains(@class,'glyphicons user')]/..")).click();
				driver.findElement(By.xpath("//*[contains(@class,'glyphicons power')]/..")).click();
			}
		} catch (Exception e) {
			logger.info("Logout button is not found!!!!!");
		}
	}

	public void resetLicense(WebDriver driver) {
		/*
		 * logger.info(surl.replaceAll("/$", "")); driver.get(surl.replaceAll("/$",
		 * "")+":5555/ResetUserLicense"); driver.get(surl.replaceAll("/$",
		 * "")+":5555/UserLicenseAvailableCount.json"); driver.quit(); driver=null;
		 */
	}

	public void resetLicense(String sbrowser) {
		/*
		 * WebDriver driver; WindowsUtil windowutil=new WindowsUtil(); if
		 * (sbrowser.equalsIgnoreCase("firefox")) {
		 * logger.info("firefox driver intialized"); driver = new FirefoxDriver();
		 * resetLicense(driver); windowutil.runBatchFile(KILLFIREFOX); } else
		 * if(sbrowser.equalsIgnoreCase("Chrome")){
		 * System.setProperty("webdriver.chrome.driver", getFilePath(CHROMEPATH));
		 * logger.info("Setting a Chrome Driver."); driver= new ChromeDriver();
		 * resetLicense(driver); windowutil.runBatchFile(KILLCHROME);
		 * windowutil.runBatchFile(KILLFIREFOX); } else
		 * if(sbrowser.equalsIgnoreCase("IE")) { logger.info("IE driver intialized");
		 * System.setProperty("webdriver.ie.driver", getFilePath(IEPATH));
		 * DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		 * caps.setCapability(
		 * InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
		 * true); caps.setCapability("ignoreZoomSetting", true);
		 * caps.setCapability("ignoreProtectedModeSettings" , true); try {
		 * Runtime.getRuntime().
		 * exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255"); } catch
		 * (IOException e) { e.printStackTrace(); } driver= new
		 * InternetExplorerDriver(caps); resetLicense(driver);
		 * windowutil.runBatchFile(KILLIE); windowutil.runBatchFile(KILLFIREFOX); } else
		 * if(sbrowser.equalsIgnoreCase("Safari")) {
		 * logger.info("IE driver intialized"); DesiredCapabilities capabilities =
		 * DesiredCapabilities.safari(); driver = new SafariDriver(capabilities);
		 * resetLicense(driver); windowutil.runBatchFile(KILLSAFARI); }
		 */
	}

	public void eventBrowserQuit() {
		try {
			secondDriver.quit();
			secondDriver = null;
		} catch (Exception e) {
			logger.info("Event Driver is Not Initialized");
		}
	}

	public void secondBrowserQuit() {
		try {
			thirdDriver.quit();
			thirdDriver = null;
		} catch (Exception e) {
			logger.info("Third driver is Not Initialized");
		}
	}

	public Vector<String> getSanityuserslist() {
		Vector<String> userslist = new Vector<String>();
		String sbrowser = Configuration.getAutomationBrowser();
		// userslist.add(uservalue);
		for (int i = 0; i < 10; i++) {
			userslist.add(SANITYUSER + i);
		}
		logger.info("User list is" + userslist);
		return userslist;
	}

	/*
	 * public FirefoxProfile firefoxProfileSetUp() { FirefoxProfile firefoxProfile =
	 * new FirefoxProfile();
	 * firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
	 * "text/plain");
	 * firefoxProfile.setPreference("security.mixed_content.block_active_content",
	 * false);
	 * firefoxProfile.setPreference("security.mixed_content.block_display_content",
	 * true); firefoxProfile.setPreference("browser.download.folderList", 2);
	 * firefoxProfile.setPreference("browser.download.dir",FileOperation.getFilePath
	 * (DOWNLOADSPATH));
	 * //System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+
	 * File.separator+"resources"+File.separator+"AutoIT"+File.separator+"Downloads"
	 * ); firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
	 * "image/jpg,text/csv,text/xml,text/plain,application/xml,application/json,application/vnd.ms-excel,application/octet-stream, application/x-excel,application/x-msexcel,application/excel,application/pdf"
	 * ); return firefoxProfile; }
	 */

	public FirefoxProfile firefoxProfileSetUp() {
		FirefoxProfile profile = new FirefoxProfile();
		String downloadFilepath = getFilePath(DOWNLOADSPATH);
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("browser.download.dir", downloadFilepath);
		profile.setPreference("browser.helperApps.neverAsk.openFile",
				"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		profile.setPreference("browser.download.manager.focusWhenStarting", false);
		profile.setPreference("browser.download.manager.useWindow", false);
		profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		profile.setPreference("browser.download.manager.closeWhenDone", false);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
		logger.info("Profile Setup done for firefox");
		return profile;
	}

	public DesiredCapabilities chromeProfileSetUp() {
		System.out.println("------------------------" + System.getProperty("user.dir"));
		logger.info("--------------------------------" + System.getProperty("user.dir"));
		System.setProperty("webdriver.chrome.driver", getFilePath(CHROMEPATH));
		// System.setProperty("webdriver.chrome.driver", getFilePath(CHROMEPATH));
		String downloadFilepath = getFilePath(DOWNLOADSPATH);
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--test-type");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		options.addArguments("--always-authorize-plugins");
		options.addArguments("--ignore-certificate-errors");
		options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		logger.info("The Chrome Profile is setup");
		return cap;
	}

	public DesiredCapabilities ieProfileSetUp() {
		System.setProperty("webdriver.ie.driver", getFilePath(IEPATH));
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		caps.setCapability("ignoreZoomSetting", true);
		caps.setCapability("ignoreProtectedModeSettings", true);
		caps.setCapability("enablePersistentHover", true);
		caps.setCapability("nativeEvents", false);
		try {
			Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return caps;
	}

}
