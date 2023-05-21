package com.vbrick.avenger.setup;

import java.util.ArrayList;

import org.openqa.selenium.By;



public interface PageAction {

	public String getFilePath(String sFilepath);
	public void click(By locator);
	public void enterText(By locator,String stextValue);
	public boolean isEnabled(By locator);
	public void selectValuefromDropDown(By locator,String selectedValue);
	public String getAttribute(By locator,String locatorName);
    public ArrayList<String> getDropdownValue(By locator); 
    public void clearWebElementText(By locator);
    public void check_Checkbox(By locator);
    public void uncheck_Checkbox(By locator);
    public boolean isSelected(By locator);
    public void enterText_FileUpload(By locator,String stextValue);
    public boolean isDisplayed(By locator);
    public String getDropDownSelectedOption(By locator);

}
