package com.vbrick.avenger.funUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;
import org.uncommons.reportng.ReportNGUtils;

import com.vbrick.avenger.setup.Configuration;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;


public class CustomReporter implements IReporter,Setup{
	private static Logger logger = Logger.getLogger(CustomReporter.class);

	String outputFile = Configuration.getAllExecutionStatusFilePath();
	String failedcases=getFilePath(FAILEDCASES);
	String passedcases=getFilePath(PASSEDCASES);
	String skippedcases=getFilePath(SKIPPEDCASES);
	Map<String, Integer> passedgroupsmap= new HashMap<String, Integer>();
	Map<String, Integer> failedgroupmap= new HashMap<String, Integer>();
	Map<String, Integer> skippedgroupmap= new HashMap<String, Integer>();
	
	FileWriter failedwriter=null;
	FileWriter passedwriter=null;
	FileWriter skippedwriter=null;
	FileWriter writer=null;
	
	String BuildNo;
	ReportNGUtils reportng=new ReportNGUtils();
	

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		
		logger.info("Output file path is"+outputFile);
		
	/*	ReportNGUtils  ngutils=new ReportNGUtils();
		ngutils.getDuration(ITestContext);
	*/	// TODO Auto-generated method stub
		TestBase.getVersionvalue();
		logger.info("The Version Value is"+TestBase.getVersionvalue());
		String suiteName=null;
		boolean alreadyExists = new File(outputFile).exists();
		int passedtestcasessize = 0;
		int failedtestcasessize=0;
		int skippedtestcasessize=0;
		int passedsizevalue=0;
		int failesizevalue=0;
		int skippedsizevalue=0;
		
		try {
			try{
			writer = new FileWriter(outputFile,true);
			}catch(FileNotFoundException filenotfound)
			{
				writer = new FileWriter(outputFile,false);
			}
			passedwriter = new FileWriter(passedcases,false);
			failedwriter = new FileWriter(failedcases,false);
			skippedwriter = new FileWriter(skippedcases,false);
			
			csvWritingHeading("ClassName");
			csvWritingHeading(",");
			csvWritingHeading("Test Case Name");
			csvWritingHeading(",");
			csvWritingHeading("Status");
			csvWritingHeading(",");
			csvWritingHeading("Test Case Description");
			csvWritingHeading(",");
			csvWritingHeading("Module Name");
			csvWritingHeading(",");
			csvWritingHeading("Suite Name");
			csvWritingHeading(",");
			csvWritingHeading("Operating System");
			csvWritingHeading(",");
			csvWritingHeading("Browser");
			csvWritingHeading(",");
			csvWritingHeading("Build No");
			csvWritingHeading(",");
			csvWritingHeading("Time in Seconds");
			csvWritingHeading("\n");
			BuildNo=SendMail.BuildNumber(Configuration.getAutomationURL());
			logger.info("THe Build No is"+BuildNo);
			for (ISuite suite : suites) {
				//Following code gets the suite name
				suiteName = suite.getName();
				logger.info("The Suite Name is"+suiteName);
				//Getting the results for the said suite
				
				Map<String, ISuiteResult> suiteResults = suite.getResults();
				for (ISuiteResult sr : suiteResults.values()) {
					ITestContext tc = sr.getTestContext();
					passedtestcasessize=tc.getPassedTests().size();
					passedsizevalue=passedsizevalue+passedtestcasessize;
					// logger.info("Passed test are"+tc.getPassedTests());

					//Writing passed test cases data to csv file.
					for (ITestNGMethod testcasename : tc.getPassedTests().getAllMethods()) {
						logger.info("Passed test case name is"+testcasename.getMethodName());
						//XmlTest name=testcasename.getXmlTest();
						for (ITestResult resultdata : tc.getPassedTests().getResults(testcasename)){
							List<String>reportingdata=Reporter.getOutput(resultdata);
							Set<ITestResult> itestresult1=tc.getPassedTests().getResults(testcasename);
							//logger.info("The duration of test case"+this.getDuration(itestresult1));
							testCasesData(testcasename,passedwriter,reportingdata,suiteName,this.getDuration(itestresult1),"pass");
							testCasesData(testcasename,writer,reportingdata,suiteName,this.getDuration(itestresult1),"pass");
							
						}
					}
					//Writing Failed test cases data to csv file.
					logger.info("Failed tests for suite '" + suiteName +"' is:" +tc.getFailedTests().size());
					failedtestcasessize=tc.getFailedTests().size();
					failesizevalue=failesizevalue+failedtestcasessize;
					logger.info("Failed test are"+tc.getFailedTests());

					for (ITestNGMethod failedtestcasename : tc.getFailedTests().getAllMethods()) {
						logger.info("Failed test case name is"+failedtestcasename.getMethodName());
						//XmlTest name=testcasename.getXmlTest();
						for (ITestResult resultdata : tc.getFailedTests().getResults(failedtestcasename)){
							List<String>reportingdata=Reporter.getOutput(resultdata);
							Set<ITestResult> itestresultfailedtest=tc.getFailedTests().getResults(failedtestcasename);
							logger.info("The duration of test case"+this.getDuration(itestresultfailedtest));
							testCasesData(failedtestcasename,failedwriter,reportingdata,suiteName,this.getDuration(itestresultfailedtest),"fail");
							testCasesData(failedtestcasename,writer,reportingdata,suiteName,this.getDuration(itestresultfailedtest),"fail");
							
						}

					}
					//Writing Skipped test cases data to CSV File
					logger.info("Skipped tests for suite '" + suiteName +
							"' is:" + 
							tc.getSkippedTests().size());
					skippedtestcasessize=tc.getSkippedTests().size();
					skippedsizevalue=skippedsizevalue+skippedtestcasessize;
					for (ITestNGMethod skippedtestcasename : tc.getSkippedTests().getAllMethods()) {
						logger.info("Skipped test case name is"+skippedtestcasename.getMethodName());
						//XmlTest name=testcasename.getXmlTest();
						for (ITestResult resultdata : tc.getSkippedTests().getResults(skippedtestcasename)){
							List<String>reportingdata=Reporter.getOutput(resultdata);
							Set<ITestResult> itestresultskipped=tc.getSkippedTests().getResults(skippedtestcasename);
							logger.info("The duration of test case"+this.getDuration(itestresultskipped));
							testCasesData(skippedtestcasename,skippedwriter,reportingdata,suiteName,this.getDuration(itestresultskipped),"skip");
							testCasesData(skippedtestcasename,writer,reportingdata,suiteName,this.getDuration(itestresultskipped),"skip");
							
						}
					}
				} 
			}

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				System.out.println("THe Passed Values are"+passedsizevalue);
				System.out.println("THe Failed Values are"+failesizevalue);
				System.out.println("THe Skipped Values are"+skippedsizevalue);
				//writer.close();
				failedwriter.close();
				passedwriter.close();
				skippedwriter.close();
				writer.close();
				passedtestcasessize=passedsizevalue;
				failedtestcasessize=failesizevalue;
				skippedtestcasessize=skippedsizevalue;
				PieChartCreation.pieChartReport(passedtestcasessize, failedtestcasessize, skippedtestcasessize);
				int totalnoofcases=passedtestcasessize+failedtestcasessize+skippedtestcasessize;
				double passedpercentage=Math.round(((double)passedtestcasessize/(double)totalnoofcases)*100);
				double failedpercentage=Math.round(((double)failedtestcasessize/(double)totalnoofcases)*100);
				double skippedpercentage=Math.round(((double)skippedtestcasessize/(double)totalnoofcases)*100);
				Map<String,String> reportOutput =new HashMap<String, String>();
				reportOutput.put("Browser","Chrome"); // Need to add code
				reportOutput.put("passedcasessize",String.valueOf(passedtestcasessize));
				reportOutput.put("failedcasessize",String.valueOf(failedtestcasessize));
				reportOutput.put("skippedcasessize",String.valueOf(skippedtestcasessize));
				reportOutput.put("passedpercentage",String.valueOf(passedpercentage));
				reportOutput.put("failedpercentage",String.valueOf(failedpercentage));
				reportOutput.put("skippedpercentage",String.valueOf(skippedpercentage));
				reportOutput.put("SuiteName",suiteName);
				reportOutput.put("totalnoofcases",String.valueOf(totalnoofcases));
				Map<String, Integer> allmap = new HashMap<String, Integer>();
				allmap=BarChart.BarChartCreation(passedgroupsmap,failedgroupmap,skippedgroupmap);
				new SendMail().postMail(reportOutput,passedgroupsmap,failedgroupmap,skippedgroupmap,allmap);
				//new SendMailTest().sendMail();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		logger.info("Passed Group Map Value is"+passedgroupsmap);
		logger.info("Failed Group Map Value is"+failedgroupmap);
		logger.info("Skipped Group Map Value is"+skippedgroupmap);

		/*	Iterator iterator = passedgroupsmap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry mapEntry = (Map.Entry) iterator.next();
			logger.info("The key is: " + mapEntry.getKey()
				+ ",value is :" + mapEntry.getValue());
		}
		 */
	}


	public void groupsPassedMapping(String[] groupslist)
	{
      //logger.info("The passed groups are"+groupslist);
		for (int i = 0; i < groupslist.length; i++) {
			//logger.info("The Passed individual groups are "+groupslist[i]);
			if(!passedgroupsmap.containsKey(groupslist[i]))
				passedgroupsmap.put(groupslist[i], 1);
			else
				passedgroupsmap.put(groupslist[i], passedgroupsmap.get(groupslist[i])+1);
		}
	}

	public void groupsFailedMapping(String[] groupslist)
	{
		// logger.info("The failed groups are"+groupslist);
		for (int i = 0; i < groupslist.length; i++) {
			//logger.info("The Failed individual groups are "+groupslist[i]);
			if(!failedgroupmap.containsKey(groupslist[i]))
				failedgroupmap.put(groupslist[i], 1);
			else
				failedgroupmap.put(groupslist[i], failedgroupmap.get(groupslist[i])+1);
		}
	}
	public void groupsSkippedMapping(String[] groupslist)
	{
		// logger.info("The skipped groups are"+groupslist);
		for (int i = 0; i < groupslist.length; i++) {
			//logger.info("The Skipped individual groups are "+groupslist[i]);
			if(!skippedgroupmap.containsKey(groupslist[i]))
				skippedgroupmap.put(groupslist[i], 1);
			else
				skippedgroupmap.put(groupslist[i], skippedgroupmap.get(groupslist[i])+1);
		}
	}



	public static String getFilePath(String sFilepath) {
		char cforwardslash = (char) 47;
		char cbackslash = (char) 92;
		logger.info("File path is "+sFilepath);
		String sPath = System.getProperty("user.dir").replace(cbackslash,
				cforwardslash)
				+ sFilepath;

		File file = new File(sPath);
		if (file.exists()) {
			sPath = file.getAbsolutePath();
			logger.info("The File Path is " + sPath);
		} else {
		}
		return sPath;
	}
	/**
	 * These method will create the CSV Heading for the file
	 * @param sValue
	 */
	private void csvWritingHeading(String sValue)
	{
		try {
			passedwriter.write(sValue);
			failedwriter.write(sValue);
			skippedwriter.write(sValue);
			writer.write(sValue);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * These method will write the data to particular CSV File
	 * @param filewriter:these is used for writing data to particular csv file.
	 * @param value:Value to be written into the CSV File
	 */
	private void csvWrite(FileWriter filewriter,String value) {
		// TODO Auto-generated method stub
		try {
			filewriter.append(value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * These Method will write the data of the test cases to the CSV File.
	 * @param suiteName 
	 * @param l 
	 * @param testcasename:name of the test case.
	 * @param filewriter:these is used for writing data to particular csv file.
	 * @param reportingdata:these has all the steps associated with the test cases.
	 */
	public void testCasesData(ITestNGMethod testcasename, FileWriter filewriter, List<String> reportingdata, String suiteName, String testcaseDuration,String status)
	{
		logger.info("######The writer is "+filewriter.toString());
		logger.info("Test Case Name:"+testcasename.getXmlTest().getName());
		//these will get the class name of the test case.
		csvWrite(filewriter,testcasename.getRealClass().toString().replace("class ",""));
		csvWrite(filewriter,",");
		//these will get the method name of the test case.
		logger.info("Test Case Method Name:"+testcasename.getMethodName());
		csvWrite(filewriter,testcasename.getMethodName());
		csvWrite(filewriter,",");
		if(status.equalsIgnoreCase("pass"))
		csvWrite(filewriter,"N");
		if(status.equalsIgnoreCase("fail"))
			csvWrite(filewriter,"Y");
		if(status.equalsIgnoreCase("skip"))
			csvWrite(filewriter,"Y");
		csvWrite(filewriter,",");
		//these will get the description of the test case.
		logger.info("Test Case Description:"+testcasename.getDescription());
		csvWrite(filewriter,testcasename.getDescription().replace(","," "));
		csvWrite(filewriter,",");
		//logger.info("The Groups for Test Cases are "+testcasename.getGroups());
		//These method will get groups associated with test case.
		if(status.equalsIgnoreCase("pass"))
		groupsPassedMapping(testcasename.getGroups());
		else if (status.equalsIgnoreCase("fail"))
		groupsFailedMapping(testcasename.getGroups());
		else
			groupsSkippedMapping(testcasename.getGroups());
		for (int i = 0; i < testcasename.getGroups().length; i++) {
			//logger.info("The individual groups are "+testcasename.getGroups()[i]);
			csvWrite(filewriter,testcasename.getGroups()[i]);
			csvWrite(filewriter,"-");
		}
		csvWrite(filewriter,",");
		//passedwriter.append(',');
		/*   for (String string : reportingdata) {
			logger.info("Passed cases data is"+string);
			writer.append(string.replace("<br>","").replace("</br>",""));
			passedwriter.append(string.replace("<br>","").replace("</br>",""));
		}
		 */			//writer.append('\n');
		csvWrite(filewriter,suiteName);
		csvWrite(filewriter,",");
		String OSystem = System.getProperty("os.name").toLowerCase();
	    logger.info("The Operating system is"+OSystem);
		csvWrite(filewriter,OSystem);
		csvWrite(filewriter,",");
		csvWrite(filewriter,browser);
		csvWrite(filewriter,",");
		csvWrite(filewriter,BuildNo);
		csvWrite(filewriter,",");
		csvWrite(filewriter,testcaseDuration);
		csvWrite(filewriter,"\n");
	}
	
	 private String getDuration(Set<ITestResult> results) 
	    { 
		 logger.info("I am in to duration method");
	        long duration = 0; 
	        for (ITestResult result : results) 
	        { 
	            duration = (result.getEndMillis() - result.getStartMillis()); 
	            logger.info(result.getName()+"@@@@@@Time"+reportng.formatDuration(duration));
	        }
	       logger.info("The duration of test case is"+reportng.formatDuration(duration));
	        return reportng.formatDuration(duration); 
	    } 
  
}