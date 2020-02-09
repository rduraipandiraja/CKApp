package com.ck.pages.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.Base;
import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.Utilities;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AdminReportsPage extends WrapperMethods {
	
	public AdminReportsPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {
		
		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(how = How.ID, using = "SubMenu_ExitClicks") 
	MobileElement subMenuExitClick;

	@FindBy(how = How.ID, using = "sby") 
	MobileElement searchByDropDown;

	@FindBy(how = How.ID, using = "sterm") 
	MobileElement keywordTextbox;
	
	@FindBy(how = How.ID, using = "btn_Submit") 
	MobileElement submitButton;

	@FindBy(how = How.ID, using = "btn_Clear") 
	MobileElement clearButton;
	
	public void clickExitClickSubMenu() {

		reportStep("About to click submenu ExitClick in admin homepage", "INFO");
		
		if(clickAfterWait(subMenuExitClick)) {

			reportStep("Successfully clicked on the submenu ExitClick", "PASS");
		}else {

			reportStep("Not able to click the submenu ExitClick", "FAIL");
		}
		
	}
	
	public void selectSearchByDropDown(String dropDownValue) {
		
	    int attempts = 0;
	    while(attempts < 2) {
	        try {
	            driver.findElement(By.id("sby"));
	            break;
	        } catch(Exception e) {
	        	
	        }
	        attempts++;
	    }
	    
	    try {
		reportStep("About to select value "+dropDownValue+" from drop down", "INFO");
		MobileElement searchByDropDown = driver.findElement(By.id("sby"));
		selectValuesFromDropdown(searchByDropDown, dropDownValue);
		reportStep("Successfully selected value "+dropDownValue+" from the dropdown", "PASS");
		
	    } catch (Exception e) {
	    	
	    	reportStep("Not able to select value "+dropDownValue+" from dropdown" + e.getMessage(), "FAIL");
	    	
		}
	}

	public void enterKeyword(String keyword) {

		reportStep("About to enter keyword in keyword text box", "INFO");

		if(enterTextInChrome(keywordTextbox, keyword)) {

			reportStep("Successfully entered keyword "+keyword+" in keyword text box", "PASS");
		}else {

			reportStep("Not able to enter keyword "+keyword+" in keyword text box", "FAIL");
		}
		
	}
	
	public void clickSubmit() {

		reportStep("About to click submit in all cashback page", "INFO");
		
		if(clickAfterWait(submitButton)) {

			reportStep("Successfully clicked on the submit in all cashback page", "PASS");
		}else {

			reportStep("Not able to click on the submit in all cashback page", "FAIL");
		}
		
	}

	public void clickClear() {

		reportStep("About to click clear in all cashback page", "INFO");
		
		if(clickAfterWait(clearButton)) {

			reportStep("Successfully clicked on the clear in all Admin cashback page", "PASS");
		}else {

			reportStep("Not able to click on the clear in all Admin cashback page", "FAIL");
		}
		
	}

	public String extractExitClickValueFromResultstableFirstRow(){

		reportStep("About to extract exit id from reports page", "INFO");
		
		String searchResultsTable= "//table[@id='all_list']/tbody/tr[2]/td[1]";
		
		if(isElementLocatedByXpathPresent(searchResultsTable)) {

			reportStep("Presence exit id from reports page", "INFO");
		}else {

			reportStep("Failed to extract the Exit click from the Result Table ", "Fail");
		}
	
		String reqExitClick = driver.findElement(By.xpath(searchResultsTable)).getText();

		reportStep("Successfully retrieved the extract exit id from reports page" + reqExitClick, "INFO");
		
		return reqExitClick;

	}

	public String extractStoreNameValueFromResultstableFirstRow(){

		reportStep("About to extract exit id from reports page", "INFO");
		
		String searchResultsTable= "//table[@id='all_list']/tbody/tr[2]/td[6]";
		
		if(isElementLocatedByXpathPresent(searchResultsTable)) {

			reportStep("Presence exit id from reports page", "INFO");
		}else {

			reportStep("Successfully retrieved the extract exit id from reports page", "INFO");
		}
	
		String reqStoreName = driver.findElement(By.xpath(searchResultsTable)).getText();

		reportStep("Successfully retrieved the extract store names from reports page", "INFO");
		
		return reqStoreName;

	}

	public String extractRequiredExitId(String value){

		reportStep("About to extract UserId from User page", "INFO");
		
		MobileElement reqExitClick = driver.findElement(By.xpath("//table[@id='all_list']/tbody/tr["+value+"]/td[1]"));

		validateTheElementPresence(reqExitClick);
		String userIdValue = driver.findElement(By.xpath("//table[@id='all_list']/tbody/tr["+value+"]/td[1]")).getText();

		reportStep("Successfully retrieved the extract UserId from User page", "INFO");

		return userIdValue;

	}

}





