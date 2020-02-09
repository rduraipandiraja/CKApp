package com.ck.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TotalCashbackEarnedPage extends WrapperMethods{
	
	public TotalCashbackEarnedPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the totalCashbackEarned Page", "INFO");

		try {
			wait.until(ExpectedConditions.visibilityOf(totalCashbackEarned));

			reportStep("Successfully landed on the totalCashbackEarned page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the  totalCashbackEarned page", "FAIL");
		}

	}
		
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Total Cashbacks Earned']")
	MobileElement totalCashbackEarned;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView)[1]") 
	MobileElement backButtonClick;

	public TotalCashbackEarnedPage validateTotalCashbackEarnedValue(String totalCashbackEarnedValue) {

		reportStep("About to verify the value total cashback earned "+totalCashbackEarnedValue+" in my earnings page ", "INFO");
		
		String totalCbEarnedValueXpath = "//android.widget.TextView[@text='Total Cashbacks Earned']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+totalCashbackEarnedValue+")]|//android.widget.TextView[@text='Total Cashbacks Earned']/parent::android.view.View/android.widget.TextView[contains(@text,"+totalCashbackEarnedValue+")]";

		if(isElementLocatedByXpathPresent(totalCbEarnedValueXpath)) {
			reportStep("Verified the value total cashback earned "+totalCashbackEarnedValue+" in my earnings page ", "PASS");

		}else {
			reportStep("Not able to verify the value total cashback earned "+totalCashbackEarnedValue+" in my earnings page ", "FAIL");

		}
		
		MobileElement totalCbEarnedValue = driver.findElement(By.xpath(totalCbEarnedValueXpath));

		validateTheElementPresence(totalCbEarnedValue);

		reportStep("Successfully verified the value total cashback earned "+totalCashbackEarnedValue+" in my earnings page ", "INFO");

		return new TotalCashbackEarnedPage(driver, testInfo);

		
	}
	
	public TotalCashbackEarnedPage validatePaidCashbacksValue(String paidCashbackValue) {

		reportStep("About to verify the value paid cashback value "+paidCashbackValue+" in total cashback earned page ", "INFO");
		
		String paidCbValueXpath = "//android.widget.TextView[contains(@text,'Paid Cashbacks')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+paidCashbackValue+"')]|//android.widget.TextView[contains(@text,'Paid Cashbacks')]/parent::android.view.View/android.widget.TextView[contains(@text,'"+paidCashbackValue+"')]";
	
		if(isElementLocatedByXpathPresent(paidCbValueXpath)) {
			reportStep("Verified the value paid cashback value "+paidCashbackValue+" in total cashback earned page ", "PASS");

		}else {
			reportStep("Not able to verify the value paid cashback value "+paidCashbackValue+" in total cashback earned page ", "FAIl");

		}
		
		MobileElement paidCbValue = driver.findElement(By.xpath(paidCbValueXpath));

		validateTheElementPresence(paidCbValue);

		reportStep("Successfully verified the value paid cashback value "+paidCashbackValue+" in total cashback earned page ", "INFO");

		return new TotalCashbackEarnedPage(driver, testInfo);

		
	}
	
	public TotalCashbackEarnedPage validatePendingCashbackValue(String pendingCashbackValue) {

		reportStep("About to verify the value pending cashback value "+pendingCashbackValue+" in total cashback earned page ", "INFO");
		
		String pendingCbValueXpath = "//android.widget.TextView[contains(@text,'Pending Cashbacks')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+pendingCashbackValue+"')]|//android.widget.TextView[contains(@text,'Pending Cashbacks')]/parent::android.view.View/android.widget.TextView[contains(@text,'"+pendingCashbackValue+"')]";

		if(isElementLocatedByXpathPresent(pendingCbValueXpath)) {
			reportStep("Verified the value pending cashback value "+pendingCashbackValue+" in total cashback earned page ", "PASS");

		}else {
			reportStep("Not able to verify the value pending cashback value "+pendingCashbackValue+" in total cashback earned page ", "FAIL");

		}
		
		MobileElement pendingCbValue = driver.findElement(By.xpath(pendingCbValueXpath));

		validateTheElementPresence(pendingCbValue);

		reportStep("Successfully verified the value pending cashback value "+pendingCashbackValue+" in total cashback earned page ", "INFO");

		return new TotalCashbackEarnedPage(driver, testInfo);

		
	}
	
	public TotalCashbackEarnedPage validateReferralCashbackValue(String referralCashbackValue) {

		reportStep("About to verify the value referral cashback value "+referralCashbackValue+" in total cashback earned page ", "INFO");

		String referralCbValueXpath = "//android.widget.TextView[contains(@text,'Referral Cashbacks')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+referralCashbackValue+"')]|//android.widget.TextView[contains(@text,'Referral Cashbacks')]/parent::android.view.View/android.widget.TextView[contains(@text,'"+referralCashbackValue+"')]";

		if(isElementLocatedByXpathPresent(referralCbValueXpath)) {
			reportStep("Verify the value referral cashback value "+referralCashbackValue+" in total cashback earned page ", "PASS");

		}else {
			reportStep("Not able to verify the value referral cashback value "+referralCashbackValue+" in total cashback earned page ", "FAIL");

		}
		
		MobileElement referralCbValue = driver.findElement(By.xpath(referralCbValueXpath));

		validateTheElementPresence(referralCbValue);

		reportStep("Successfully verified the value referral cashback value "+referralCashbackValue+" in total cashback earned page ", "INFO");

		return new TotalCashbackEarnedPage(driver, testInfo);

		
	}
	
	public TotalCashbackEarnedPage validateAvailableCashbackValue(String availCashbackValue) {

		reportStep("About to verify the value available cashback "+availCashbackValue+" in total cashback earned page ", "INFO");
		
		String availCbValueXpath = "//android.widget.TextView[contains(@text,'Available Cashbacks')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+availCashbackValue+"')]|//android.widget.TextView[contains(@text,'Available Cashbacks')]/parent::android.view.View/android.widget.TextView[contains(@text,'"+availCashbackValue+"')]";

		if(isElementLocatedByXpathPresent(availCbValueXpath)) {
			reportStep("Verify the value available cashback "+availCashbackValue+" in total cashback earned page ", "PASS");

		}else {
			reportStep("Not able to verify the value available cashback "+availCashbackValue+" in total cashback earned page ", "FAIL");

		}
		
		MobileElement availCbValue = driver.findElement(By.xpath(availCbValueXpath));

		validateTheElementPresence(availCbValue);

		reportStep("Successfully verified the value available cashback "+availCashbackValue+" in total cashback earned page ", "INFO");

		return new TotalCashbackEarnedPage(driver, testInfo);

		
	}

	public MyEarningsPage clickBackButton() {

		reportStep("About to click on back button in my earnings page", "INFO");

//		if(click(backButtonClick)) {
//
//			reportStep("Successfully clicked on the back button in my earnings page", "PASS");
//		}else {
//
//			reportStep("Failed to  click on the back button in my earnings page", "FAIL");
//		}
		
		driver.navigate().back();

		return new MyEarningsPage(driver, testInfo);
		
	}

}
