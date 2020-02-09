package com.ck.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.Base;
import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductBrowserEditModePage extends WrapperMethods {
	
	public ProductBrowserEditModePage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {
		
		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	

	@FindBy(how = How.ID, using = "btn_Tools") 
	MobileElement solarUpdate;

	@FindBy(how = How.ID, using = "solrLiveRefershPrimaryCashback") 
	MobileElement refreshPrimaryCashback;

	@FindBy(how = How.ID, using = "solrLiveRefreshSecondaryCashback") 
	MobileElement refreshSecondaryCashback;

	@FindBy(how = How.ID, using = "solrLiveRefreshVouchers") 
	MobileElement refreshVoucher;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Refresh')]") 
	MobileElement refreshButton;

	@FindBy(how = How.XPATH, using = "//button[text()='OK']") 
	MobileElement okButton;
	
	public void clickSolarUpdate() {

		reportStep("About to click solar update", "INFO");

		if(jsClickID("btn_Tools")) {

			reportStep("Successfully clicked solar update", "PASS");
		}else {

			reportStep("Not able to click solar update", "FAIL");
		}
		
	}

	public void clickRefreshPrimaryCashaback() {

		reportStep("About to click primary cashback", "INFO");

		if(jsClickID("solrLiveRefershPrimaryCashback")) {

			reportStep("Successfully clicked primary cashback", "PASS");
		}else {

			reportStep("Not able to click primary cashback", "FAIL");
		}
		
	}

	public void clickRefreshSecondaryCashaback() {

		reportStep("About to click secondary cashback", "INFO");

		if(jsClickID("solrLiveRefreshSecondaryCashback")) {

			reportStep("Successfully clicked secondary cashback", "PASS");
		}else {

			reportStep("Not able to click secondary cashback", "FAIL");
		}
		
	}

	public void clickRefreshVoucher() {

		reportStep("About to click refresh voucher", "INFO");

		if(jsClickID("solrLiveRefreshVouchers")) {

			reportStep("Successfully clicked refresh voucher", "PASS");
		}else {

			reportStep("Not able to click refresh voucher", "FAIL");
		}
		
	}

	public void clickRefreshButton() {

		reportStep("About to click refresh button", "INFO");

		if(clickAfterWait(refreshButton)) {

			reportStep("Successfully clicked refresh button", "PASS");
		}else {

			reportStep("Not able to click refresh button", "FAIL");
		}
		
	}

	public void clickOkButton(AppiumDriver<MobileElement> driver) {
		
		reportStep("About to click Ok button ", "INFO");
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='OK']")));

			okButton.click();
			reportStep("Successfully clicked ok button", "PASS");
			
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("Not able to click ok button", "FAIL");
		}
		
	}
	
	
	
}





