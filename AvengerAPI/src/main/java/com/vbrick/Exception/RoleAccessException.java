package com.vbrick.Exception;

import org.openqa.selenium.TimeoutException;

public class RoleAccessException extends TimeoutException{

	/**
	 * @param args
	 */
	
	public RoleAccessException(String message) {
		 super(message);
	}

	/*@Override
	public Throwable fillInStackTrace() {
		return null;
	}*/
	private String createMessage(String originalMessageString) {
		return "";
	   	  }

	  public String getSystemInformation() {
		  return ""; 
	  }
	
	  public String toString() {
		    return "";
		  }
	  
}
