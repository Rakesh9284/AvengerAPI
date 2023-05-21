package com.vbrick.avenger.dao;

import java.util.ArrayList;

import org.apache.commons.lang.RandomStringUtils;

import com.vbrick.avenger.funUtil.DateTime;
import com.vbrick.avenger.setup.Configuration;

public abstract class FieldValidations {
	
	private DateTime dateTime;
	public FieldValidations()
	{
		dateTime = new DateTime();

	}
	
	final String validateData = "!,@,#,$,%,^,&";
	

	private ArrayList<String> fieldValidateData;

	public ArrayList<String> getFieldValidateData() {
		return fieldValidateData;
	}

	public void setFieldValidateData(ArrayList<String> fieldValidateData) {
		this.fieldValidateData = fieldValidateData;
	}

	/**
	 * 
	 * @return : Array with special charaters
	 */
	public ArrayList<String> validateField() {
		ArrayList<String> valDat = new ArrayList<String>();
		String[] values = validateData.split(",");
		for (int i = 0; i < values.length; i++) {
			{
				valDat.add(values[i]);
			}
		}
		return valDat;
	}

	private String srandomData;
	
	public String randomNumeric(){
	String srandomData = null;
	if (Configuration.randomData().equalsIgnoreCase("Yes")) {
		srandomData = RandomStringUtils.randomNumeric(6);
	} else {
		srandomData = "123";
	}
	return srandomData;
}
	
	
	public String randomNumeric_12()
	{
		String srandomData = null;
		if (Configuration.randomData().equalsIgnoreCase("Yes")) {
			srandomData = RandomStringUtils.randomNumeric(12);
		} else {
			srandomData = "12345678901";
		}
		return srandomData;
	}
	public String randomAlphabetic() {
		String srandomData = null;
		if (Configuration.randomData().equalsIgnoreCase("Yes")) {
			srandomData = RandomStringUtils.randomAlphabetic(5);
		} else {
			srandomData = "Sizes";
		}
		return srandomData;
	}

	public String yyyyMMDDHHmmssTime() {
		String srandomData = null;
		if (Configuration.randomData().equalsIgnoreCase("Yes")) {
			srandomData = dateTime.yyyyMMDDHHmmssTime();
		} else {
			srandomData = "12345";
		}
		return srandomData;
	}
	
	
}
