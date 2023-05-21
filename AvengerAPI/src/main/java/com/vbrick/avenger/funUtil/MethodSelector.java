/*package com.vbrick.avenger.funUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlMethodSelector;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class MethodSelector {
	private static Logger logger = Logger.getLogger(MethodSelector.class);

	public Map<String, String> config_XMLParameters(String sURL,
			String sPlatFrom, String sbrowserVersion, String sTestName,
			String sbrowser) {
		Map<String, String> testngParams = new HashMap<String, String>();
		testngParams.put("TestCaseName", sTestName);
		testngParams.put("sbrowser", sbrowser);
		testngParams.put("sversion", sbrowserVersion);
		testngParams.put("splatform", sPlatFrom);
		testngParams.put("surl", sURL);
		testngParams.put("sUserName", "arjun@cg.com");
		testngParams.put("sPassword", "Password1");

		return testngParams; // allParameters.add(testngParams);
		
		 * Map<String, String> test2Params = new HashMap<String, String>();
		 * test2Params.put("TestCaseName", "ChromeExecution");
		 * test2Params.put("sbrowser", "Chrome"); test2Params.put("sversion",
		 * "28.0"); test2Params.put("splatform", "WIN7");
		 * test2Params.put("surl", "http://abhesheke.avenger.qa.vb.loc");
		 * test2Params.put("sUserName", "arjun@cg.com");
		 * test2Params.put("sPassword", "Password1");
		 
	}

	public List<XmlSuite> getSuite(Map<String, String> getMethodsfromClass,
			List<Map<String, String>> allParameters, List<String> ls) {
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		XmlSuite eachSuite = new XmlSuite();
		eachSuite.setName("VBricks Suite");
		eachSuite.setParallel("tests");
		eachSuite.setThreadCount(8);
		eachSuite.setTests(getTest(eachSuite, getMethodsfromClass, allParameters,ls));
		//eachSuite.setTests(getTest(eachSuite, getMethodsfromClass, testngParams));
		logger.info("Each Class " + eachSuite.toXml()); 
		suites.add(eachSuite);
		return suites;
	}

	public List<XmlTest> getTest(XmlSuite suite, Map<String, String> getMethodsfromClass,
			List<Map<String,String>> allParameters, List<String> ls) {
		List<XmlTest> tests = new ArrayList<XmlTest>();
	
		//System.out.println("getmethods from class value "+getMethodsfromClass.isEmpty());
		for (int i = 0; i < allParameters.size(); i++) {
			//System.out.println("All Parameter value"+allParameters.get(i).get("TestCaseName"));
			XmlTest eachTest = new XmlTest();
			tests.add(eachTest);
			eachTest.setName(allParameters.get(i).get("TestCaseName"));
		    eachTest.setParameters(allParameters.get(i));
			//System.out.println(allParameters.get(i));
			eachTest.setXmlClasses(getXmlClasses(eachTest, getMethodsfromClass,ls));
			eachTest.setSuite(suite);
		
		}
		
		XmlTest eachTest = new XmlTest();
		tests.add(eachTest);
		eachTest.setName("My test");
		 eachTest.setParameters(allParameters.get(0));
		//eachTest.setMethodSelectors(getMethodSelectors(methodS));
		eachTest.setXmlClasses(getXmlClasses(eachTest, getMethodsfromClass));
		eachTest.setSuite(suite);
		XmlTest eachTest1 = new XmlTest();
		tests.add(eachTest1);
		eachTest1.setName("ChromeTest");
     	eachTest1.setParameters(allParameters.get(1));
		//eachTest.setMethodSelectors(getMethodSelectors(methodS));
		eachTest1.setXmlClasses(getXmlClasses(eachTest, getMethodsfromClass));
		eachTest1.setSuite(suite); 
		return tests;
	}

	public List<XmlClass> getXmlClasses(XmlTest test,Map<String, String> getMethodsfromClass, List<String> ls) {
		List<XmlClass> classes = new ArrayList<XmlClass>();
		 ArrayList<XmlInclude> methodsToRun = new  ArrayList<XmlInclude>(); 
		 if(getMethodsfromClass.isEmpty()==true)
			{
				try
				{
				File folder = new File(
						"D:\\Vbrick_TeamCity__Aug26\\vBricksTest-1\\src\\test\\java\\com\\vbrick\\avenger\\test");
				File[] listOfFiles = folder.listFiles();
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						// System.out.println("File " +
						// listOfFiles[i].getName().replace(".java",""));
						String name = listOfFiles[i].getName().replace(".java", "");
						String path = "com.vbrick.avenger.test." + name;
						 System.out.println("path is" +path);
						Class c =Class.forName(path);
						 System.out.println("value of c is" +c.getSimpleName());
						 XmlClass eachClass = new XmlClass(path);
						 classes.add(eachClass);
					     eachClass.setExcludedMethods(ls);
						 eachClass.setXmlTest(test);
					
			        }
				}
				}
				catch(Exception e)
				{
					
				}
			}
		 
		 else
		 {
		try
		{
		int counter=0;
		for (Map.Entry<String, String> entry : getMethodsfromClass.entrySet()) {
			System.out.println("value of entry key is" +entry.getKey() +"value is" +entry.getValue() );
			String[] splits = entry.getValue().split(":");
			System.out.println("splits.size: " + splits.length);
			System.out.println("value 1 is" +splits[0]);
			System.out.println("value 1 is" +splits[1]);
			String path = "com.vbrick.avenger.test." + entry.getValue();
			XmlClass eachClass = new XmlClass(path);
			 classes.add(eachClass);
			 List<String> ls = new ArrayList<String>();
				ls.add("smokeTest1");
				 eachClass.setExcludedMethods(ls);	
			 methodsToRun.add(new XmlInclude(entry.getKey()));
			 eachClass.setIncludedMethods(methodsToRun);
			// eachClass.setClass(AddUserTest.class);
			 eachClass.setXmlTest(test);
			// System.out.println("value of entry key is" +entry.getKey());
			 counter++;
			//System.out.println("Value of counter After increment"+counter);
		}
		
		}
		catch(Exception e)
		{
			
		}
		 }
		return classes;
	
		
		// myClasses.add(new XmlClass("com.vbrick.avenger.test.AddUserTest"));
		XmlClass eachClass = new XmlClass("com.vbrick.avenger.test.AddUserTest");
		 classes.add(eachClass1);
		 classes.add(eachClass);
		List<String> ls = new ArrayList<String>();
		ls.add("verify_Creation_of_User_Sucessfull1");
			
		for (int i = 0; i < getMethodsfromClass.size(); i++) {
				        methodsToRun.add(new XmlInclude(getMethodsfromClass.get(i)));
				        System.out.println("values in method to run" +methodsToRun);
		}
		eachClass.setIncludedMethods(methodsToRun);
	   //eachClass.setExcludedMethods(ls);
		eachClass1.setIncludedMethods(methodsToRun);
		//eachClass2.setIncludedMethods(methodsToRun);
		eachClass.setClass(AddUserTest.class);
		 eachClass1.setClass(SampleTest1.class);
		 //eachClass2.setClass(MyTestClass.class);
		 
		eachClass.setXmlTest(test);
		 eachClass1.setXmlTest(test);
		 //eachClass2.setXmlTest(test);
		 
		
	}

	public List<XmlMethodSelector> getMethodSelectors(List<String> list) {
		List<XmlMethodSelector> methodSelectors = new ArrayList<XmlMethodSelector>();
		XmlMethodSelector selector = new XmlMethodSelector();

		XmlScript script = new XmlScript();
		String s = "";
		selector.setScript(script);
		script.setLanguage("beanshell");
		for (int i = 0; i < list.size(); i++) {
			logger.info("list size is " + list.size());
			logger.info("value of list are" + list.get(i));
			int value = list.size();

			if (i == value - 1 || value == 0) {
				s = s.concat("!testngMethod.getMethodName().equals(\""
						+ list.get(i) + "\")");
			} else {
				s = s.concat("!testngMethod.getMethodName().equals(\""
						+ list.get(i) + "\")".concat(" && "));

			}
			logger.info("values in selector are" + selector);
			logger.info("value of  s is" + s);
		}
		script.setScript(s);
		methodSelectors.add(selector);

		logger.info("values in methodSelectors"
				+ methodSelectors.getClass().toString());

		return methodSelectors;
	}


}
*/