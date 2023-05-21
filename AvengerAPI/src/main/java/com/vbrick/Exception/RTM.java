package com.vbrick.Exception;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import com.vbrick.avenger.funUtil.DateTime;

public class RTM {

	FileWriter fw = null;
	PrintWriter pw = null;

	public void headingOfCSV() throws IOException {
		//VerifyValidusernamepassword_Sprint17_RequirmentID01_TestcaseID01();
		// Write to file for the second row
		String sRTMFile="D:/RTM"+new DateTime().yyyyMMDDHHmmssTime()+".csv";
		System.out.println("THe file name"+sRTMFile);
		File file = new File(sRTMFile);
		file.createNewFile();
		System.out.println("File Created "+file.exists());
		fw = new FileWriter(sRTMFile);
		pw = new PrintWriter(fw);
		pw.print("TestCase Title");
		pw.print(",");
		pw.print("Sprint No");
		pw.print(",");
		pw.print("Requirement ID");
		pw.print(",");
		pw.print("TestCase ID");
		pw.print(",");
		pw.print("Status");
		pw.println();
	}
	String sTestcaseName;
	public void VerifyValidusernamepassword_Sprint17_RequirmentID01_TestcaseID01() throws IOException {
//		String sTestcaseName = new Object().getClass().getEnclosingMethod().getName();
		 sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		// Note the "\\" used in the path of the file
		// instead of "\", this is required to read
		// the path in the String format.
		String sRTMFile="D:/RTM"+new Date().getSeconds()+".csv";
		System.out.println("THe file name"+sRTMFile);
		File file = new File(sRTMFile);
		file.createNewFile();
		System.out.println("File Created "+file.exists());
		fw = new FileWriter(sRTMFile);
		pw = new PrintWriter(fw);
		// Write to file for the first row
	}

	public void testcaseRTM(String sTestcaseName) throws IOException {
//		String TCName = "Verify_Validusernamepassword_Sprint17_TC01_Req01";
		
		String[] strings = sTestcaseName.split("_");
		for (int i = 0; i < strings.length; i++) {
			System.out.println("The strings[i]" + strings[i]);
			pw.print(strings[i]);
			pw.print(",");
			//pw.append(strings[i]);
		}
		pw.println();

	}

	public void close() throws IOException {
	
		pw.flush();
		// Close the Print Writer
		pw.close();
		// Close the File Writer
		fw.close();
	}

	public void append(String stext)
	{
		pw.append(stext);
		pw.append(",");
	}
	
	
/*	public static void main(String[] args) throws IOException {
		RTM rtm = new RTM();
		rtm.headingOfCSV();
		rtm.testcaseRTM("VerifyValidusernamepassword_Sprint17_RequirmentID01_TestcaseID01");
		rtm.close();
		rtm.testcaseRTM("VerifyValidusernamepassword_Sprint17_RequirmentID01_TestcaseID01");
		rtm.testcaseRTM("VerifyValidusernamepassword_Sprint17_RequirmentID01_TestcaseID01");
		rtm.testcaseRTM("VerifyValidusernamepassword_Sprint17_RequirmentID01_TestcaseID01");
		
	}*/

}