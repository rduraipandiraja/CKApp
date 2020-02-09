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

public class PaymentPage extends WrapperMethods {

	public PaymentPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the Payment Page", "INFO");

		try {
			wait.until(ExpectedConditions.visibilityOf(Payment));

			reportStep("Successfully landed on the Payment page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the  Payment page", "FAIL");
		}

	}

	
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Payment']")
	MobileElement Payment ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='REQUEST CASHBACK PAYMENT']")
	MobileElement requestCashbackPaymentButton ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='REQUEST REWARDS PAYMENT']")
	MobileElement requestRewardsPaymentButton ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Payment threshold not reached')]") 
	MobileElement paymentThresholdNotReached ;
	@FindBy(how = How.XPATH, using = "//android.widget.Button[@text='OK']") 
	MobileElement paymentThresholdNotReachedAlertPopupOKButton ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Alert']") 
	MobileElement paymentThresholdNotReachedAlertText ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='btn_myAccEarnings_totalCashbackAmount']") 
	MobileElement buttonTotalCashbackAmount ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_myAccEarnings_totalRewardsAmount']") 
	MobileElement buttonTotalRewardsAmount ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_payment_cashbackEarningsAmount']")
	MobileElement cashbackEarningsAmount ;
	
	
	//Texts :
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Total Cashback Earned']")
	MobileElement totalCashbackEarnedTextLabel ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Total Rewards Earned']")
	MobileElement  totalRewardsEarnedTextLabel;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Tap to see details')]")
	MobileElement tapToSeeDetails ;
	

	//Below section rules :
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'You can request payment as per below:')]")
	MobileElement youCanReqPaymentAsPerBelow ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'If sum confirmed Cashback + Confirmed Rewards is over Rs.250, you can request payment.')]")
	MobileElement ifSumConfirmed_Text ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Rewards cannot be paid as Cash via NEFT.')]")
	MobileElement rewardsCantBePaid_Text ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'However, if total Confirmed Rewards + Cashback is over Rs.250, they can be paid via Gift Cards or  Wallet transfer')]")
	MobileElement howEverIfTotal_text ;
	
	
	//new
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_payment_earningsInfo']|//android.widget.TextView[@content-desc='accessible={true}']")
	MobileElement infoIcon ;
	
	public PaymentRequestPage clickOnRequestCashbackPaymentButton() {

		reportStep("About to click on Request Cashback payment button ", "INFO");

		if(click(requestCashbackPaymentButton)){

			reportStep("Successfully clicked on the Request Cashback Payment Button at the My Earnings page ", "PASS");

		}else {

			reportStep("Not able to click on the Request  Cashback Payment button  ", "FAIL");
		}

		return new PaymentRequestPage(driver, testInfo);

	}
	
	public PaymentPopupPage clickOnRequestCashbackPaymentButtonForPaymentPopup() {

		reportStep("About to click on Request Cashback payment button for intermediate  Payment popup ", "INFO");

		if(click(requestCashbackPaymentButton)){

			reportStep("Successfully clicked on the Request Cashback Payment Button at the My Earnings page ", "PASS");

		}else {

			reportStep("Not able to click on the Request  Cashback Payment button  ", "FAIL");
		}

		return new PaymentPopupPage(driver, testInfo);

	}

	public PaymentPage clickOnRequestCashbackPaymentButtonForThresholdNotReached() {

		reportStep("About to click on Request Cashback payment button to validate the Threshold not reached  ", "INFO");

		if(click(requestCashbackPaymentButton)){

			reportStep("Successfully clicked on the Request Cashback Payment Button at the My Earnings page ", "PASS");

		}else {

			reportStep("Not able to click on the Request  Cashback Payment button  ", "FAIL");
		}

		return this;

	}

	public PaymentRequestPage clickOnRequestRewardsPaymentButton() {

		reportStep("About to click on Request Rewards payment button ", "INFO");

		if(click(requestRewardsPaymentButton)){

			reportStep("Successfully clicked on the Requesst RewardsPaymentPayment Button at the My Earnings page ", "PASS");

		}else {

			reportStep("Not able to click on the Request Rewards Payment button  ", "FAIL");
		}
		
		return new PaymentRequestPage(driver, testInfo);

	}
	
	public PaymentPopupPage clickOnRequestRewardsPaymentButtonForPaymentPopup() {

		reportStep("About to click on Request Rewards payment button for Payment Popup", "INFO");

		if(click(requestRewardsPaymentButton)){

			reportStep("Successfully clicked on the Requesst RewardsPaymentPayment Button at the My Earnings page ", "PASS");

		}else {

			reportStep("Not able to click on the Request Rewards Payment button  ", "FAIL");
		}
		
		return new PaymentPopupPage(driver, testInfo);

	}

	public PaymentPage clickOnRequestRewardsPaymentButtonForThresholdNotReached() {

		reportStep("About to click on Request Rewards payment button to validate the Threshold not reached  ", "INFO");

		if(click(requestRewardsPaymentButton)){

			reportStep("Successfully clicked on the Requesst RewardsPaymentPayment Button at the My Earnings page ", "PASS");

		}else {

			reportStep("Not able to click on the Request Rewards Payment button  ", "FAIL");
		}

		return this;
	}

	public MyEarningsPage validateThresholdNotReached() {

		reportStep("About to validate the Threshold not reached popup ", "INFO");
		
		validateTheElementPresence(paymentThresholdNotReached);
		validateTheElementPresence(paymentThresholdNotReachedAlertPopupOKButton);
		validateTheElementPresence(paymentThresholdNotReachedAlertText);

		if(click(paymentThresholdNotReachedAlertPopupOKButton)) {

			reportStep("Successfully clicked on the Ok button at the alert popup", "INFO");
		}else {
			reportStep("Failed to click on the Ok button at the alert popup", "FAIL");
		}

		return new MyEarningsPage(driver, testInfo);
	}

	public PaymentPage validateCashbackEarningsAmount(String expected){

		reportStep("About to validate the cashbackEarnings amount in the MyEarnings page ", "INFO");

		String actual = getText(cashbackEarningsAmount);

		if(actual.substring(actual.indexOf(".")+1).length()==2) {

			reportStep("Successfully validated the decimal point length : After the decimal point it  contains 2 digits  ", "INFO"); 

		}else {

			reportStep("After the decimal pointer , More than 2 digits are present - this is not the requirement  ", "FAIL"); 

		}
		validateTheActualValueContainsExpectedValue(actual, expected);

		return this;
	}

	public PaymentPage validateTotalCashbackEarningsAmount(String expected){

		reportStep("About to validate the Total cashback earnings amount in the MyEarnings page ", "INFO");

		String actual = getText(buttonTotalCashbackAmount);

		if(actual.substring(actual.indexOf(".")+1).length()==1) {

			reportStep("Successfully validated the decimal point length : After the decimal point it  contains 1 digits  ", "INFO"); 

		}else {

			reportStep("After the decimal pointer , More than 1 digits are present - this is not the requirement  ", "FAIL"); 

		}

		validateTheActualValueContainsExpectedValue(actual, expected);

		return this;

	}

	public PaymentPage validateTotalRewardsEarningsAmount(String expected){

		reportStep("About to validate the Total Rewards earnings amount in the MyEarnings page ", "INFO");

		String actual = getText(buttonTotalRewardsAmount);

		if(actual.substring(actual.indexOf(".")+1).length()==1) {

			reportStep("Successfully validated the decimal point length : After the decimal point it  contains 1 digits  ", "INFO"); 

		}else {

			reportStep("After the decimal pointer , More than 1 digits are present - this is not the requirement  ", "FAIL"); 

		}

		validateTheActualValueContainsExpectedValue(actual, expected);

		return this;

	}

	public EarningsInfoPage clickOnPaymentEarningsInfoIcon() {
		
		reportStep("About to click on the Payment Earnings info icon ", "INFO");
		
		if (click(infoIcon)) {
			
			reportStep("Successfully clicked on the Payment Earnings info Icon ", "PASS");
			
		}else {
			
			reportStep("Failed to  click on the Payment Earnings info Icon ", "FAIL");
		}
		
		return new EarningsInfoPage(driver, testInfo);
		
	}
	
	public PaymentPage validateCashbackRewardsEarningAmount(String cashback,String rewards) {
		
		reportStep("About to validate the Cashback And Reward Earnings amount  ", "INFO");
		
		String xpath = "//android.widget.TextView[contains(@text,'Cashback : ₹"+cashback+"  Rewards : ₹"+rewards+"')]";
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).isDisplayed();

		} catch (Exception e) {

			System.err.println(e.getMessage());
		}

		MobileElement text = driver.findElementByXPath(xpath);

		validateTheElementPresence(text);

		return this;

	}

	public PaymentPage validateBelowSectionRulesAndOtherTextLabels() {
		
		reportStep("About to validate the below section Earning rules ", "INFO");
		
		validateTheElementPresence(youCanReqPaymentAsPerBelow);
		validateTheElementPresence(ifSumConfirmed_Text);
		validateTheElementPresence(rewardsCantBePaid_Text);
		validateTheElementPresence(howEverIfTotal_text);
		validateTheElementPresence(totalCashbackEarnedTextLabel);
		validateTheElementPresence(totalRewardsEarnedTextLabel);
		
		
		return this;
	}

}
