package com.vbrick.avenger.setup;

public interface EnviromentAccounts {

	// Accounts for 31 env
	public final String surlA31 = "https://qa-u1204-ha-31a.lab.vbrick.com/";
	public final String surlB31 = "https://qa-u1204-ha-31b.lab.vbrick.com/";
	public final String surlC31 = "https://qa-u1204-ha-31c.lab.vbrick.com/";
	public final String surlD31 = "https://qa-u1204-ha-31d.lab.vbrick.com/";
	public final String surlE31 = "https://qa-u1204-ha-31e.lab.vbrick.com/";
	public final String surlF31 = "https://qa-u1204-ha-31f.lab.vbrick.com/";

	// Accounts for 22 env
	public final String surlA22 = "http://qa-w12-avg-22a.lab.vb.loc/";
	public final String surlB22 = "http://qa-w12-avg-22b.lab.vb.loc/";
	public final String surlC22 = "http://qa-w12-avg-22c.lab.vb.loc/";
	public final String surlD22 = "http://qa-w12-avg-22d.lab.vb.loc/";
	public final String surlE22 = "http://qa-w12-avg-22e.lab.vb.loc/";
	public final String surlF22 = "http://qa-w12-avg-22f.lab.vb.loc/";

	// Accounts for 10.150.1.207
	public final String surlD207 = "http://jgc-w12-test-01.lab.vb.loc/";
	public final String surlA207 = "http://jgc-w12-test-01a.lab.vb.loc/";
	public final String surlB207 = "http://jgc-w12-test-01b.lab.vb.loc/";
	public final String surlC207 = "http://jgc-w12-test-01c.lab.vb.loc/";
	public final String surlE207 = "http://jgc-w12-test-01a.lab.vb.loc/";
	public final String surlF207 = "http://jgc-w12-test-01b.lab.vb.loc/";

	final String URLTYPE = "https:";
	//public String AutomationURL ="http://qa-w12-avg-22a.lab.vb.loc/";
	// previous public String AutomationURL = "http://qa-w12-rev-03a.lab.vb.loc/";
	 public String AutomationURL = "https://autoapi.rev-qa.vbrick.com/";  //API CLOUD
	//public String AutomationURL = "https://qa-rev-03a.lab.vbrick.com/"; //API 03a
	//public String AutomationURL = "https://qa-rev-03f.lab.vbrick.com/"; //API 03f
	//public String AutomationURL = "https://api.rev-na.demo.vbrick.com/"; //API EU
   // public String AutomationURL = "https://qaapi.rev.vbrick.com/"; //API US 
   //public String AutomationURL = "https://autoqa.rev-eu.demo.vbrick.com/";//API production environment
 
	 
	
   public String REVDMEURL = "https://qa-u1204-ha-31e.lab.vbrick.com/";
	//public String AutomationURL = "https://autoapi.rev-qa.vbrick.com/";

	public String JenkinsProductionURL = "http://jenkins.lab.vb.loc:8080/";
	// public String AutomationURL="http://10.150.1.207/";
	public String AccountsFlag = "Yes";
	public boolean AccountFlag= false;

	public String Guest22URL = "http://qa-w12-avg-22.lab.vb.loc/#/guest";
	public String Guest31URL = "http://qa-u1204-ha-31.lab.vbrick.com/#/guest";
	public String Guest207URL = "http://10.150.1.207/#/guest";
	public String GuestURL = "https://autoapi.rev-qa.vbrick.com/#/guest";

	
	public boolean AccountsFlags = true;

}
