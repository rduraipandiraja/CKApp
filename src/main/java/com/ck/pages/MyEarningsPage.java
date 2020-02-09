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

public class MyEarningsPage extends WrapperMethods {

	//Constructor call
	public MyEarningsPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the My Earnings page ", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(myEarningsPageName));
			reportStep("Successfully landed on the My Earnings page ", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the My Earnings page ", "FAIL");
		}

	}

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='My Earnings']")
	MobileElement myEarningsPageName ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Total Cashback Earned']")
	MobileElement totalCashbackEarnedText ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='REQUEST CASHBACK PAYMENT']")
	MobileElement requestCashbackPaymentButton ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='REQUEST REWARDS PAYMENT']")
	MobileElement requestRewardsPaymentButton ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_myEarnings_cashbackEarningsAmount']")
	MobileElement cashbackEarningsAmount ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='btn_myAccEarnings_totalCashbackAmount']") 
	MobileElement buttonTotalCashbackAmount ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_myAccEarnings_totalRewardsAmount']") 
	MobileElement buttonTotalRewardsAmount ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Payment threshold not reached')]") 
	MobileElement paymentThresholdNotReached ;
	@FindBy(how = How.XPATH, using = "//android.widget.Button[@text='OK']") 
	MobileElement paymentThresholdNotReachedAlertPopupOKButton ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Alert']") 
	MobileElement paymentThresholdNotReachedAlertText ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='A payment request from you is already Pending. This will be processed shortly. Once this is paid, you can request for another payment.']") 
	MobileElement paymentReqAlreadyPendingMsg ;
	@FindBy(how = How.XPATH, using = "//android.widget.Button[@text='OK']") 
	MobileElement paymentPopupOKButton ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Alert']") 
	MobileElement paymentPopupAlertText ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Cashback']")
	MobileElement cashbackTab;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Rewards']")
	MobileElement rewardsTab;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Referral']")
	MobileElement referralTab;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_myEarnings_cashbackEarningsInfo']")
	MobileElement infoIcon;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_myEarnings_rewardsEarningsInfo']")
	MobileElement infoIconRewardsTab;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_myEarnings_mc']")
	MobileElement raiseTicketCashback;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_myEarnings_mr']")
	MobileElement raiseTicketRewards;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='You haven’t earned any Cashback Yet!']")
	MobileElement youHaventtextCashbackTab;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Shop today to start your Cashback Journey.']")
	MobileElement shopTodayTextCashbackTab;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='You haven’t earned any Rewards Yet!']")
	MobileElement youHaventtextRewardsTab;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Shop today to start your Rewards Journey.']")
	MobileElement shopTodayTextRewardsTab;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='You Have No Friends On CashKaro!']")
	MobileElement youHaveNoTextReferralTab;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Cashback is more fun with friends,')]")
	MobileElement cashbackIsMoreTextReferralTab;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='invite some friends to your Cashback party today.']")
	MobileElement inviteSomeFriendsTextReferralTab;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView)[1]") 
	MobileElement backButtonClick;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='REFER NOW']") 
	MobileElement referNow;

	@FindBy(how = How.XPATH, using = "//*[contains(@text,'View more')]") 
	MobileElement viewMore;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Profile']")
	MobileElement profileIcon;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Order Id']/following-sibling::android.widget.TextView[@text='Not Available']")
	MobileElement orderIdNotAvailable;
	
	
	
	public MyEarningsPage validateCashbackEarningsAmount(String expected){

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

	public MyEarningsPage validateTotalCashbackEarningsAmount(String expected){

		reportStep("About to validate the Total cashback earnings amount in the MyEarnings page ", "INFO");

		String actual = getText(buttonTotalCashbackAmount);

		if(actual.substring(actual.indexOf(".")+1).length()==2) {

			reportStep("Successfully validated the decimal point length : After the decimal point it  contains 2 digits  ", "INFO"); 

		}else {

			reportStep("After the decimal pointer , More than 2 digits are present - this is not the requirement  ", "FAIL"); 

		}

		validateTheActualValueContainsExpectedValue(actual, expected);

		return this;

	}

	public MyEarningsPage validateTotalRewardskEarningsAmount(String expected){

		reportStep("About to validate the Total Rewards earnings amount in the MyEarnings page ", "INFO");

		String actual = getText(buttonTotalRewardsAmount);

		if(actual.substring(actual.indexOf(".")+1).length()==2) {

			reportStep("Successfully validated the decimal point length : After the decimal point it  contains 2 digits  ", "INFO"); 

		}else {

			reportStep("After the decimal pointer , More than 2 digits are present - this is not the requirement  ", "FAIL"); 

		}

		validateTheActualValueContainsExpectedValue(actual, expected);

		return this;

	}

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

	public MyEarningsPage clickOnRequestCashbackPaymentButtonForThresholdNotReached() {

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

	public MyEarningsPage clickOnRequestRewardsPaymentButtonForThresholdNotReached() {

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

		return this;
	}

	public MyEarningsPage validatePaymentAlreadyPendingAlert() {


		reportStep("About to validate : A payment request from you is already Pending. This will be processed shortly. Once this is paid, you can request for another payment ", "INFO");

		validateTheElementPresence(paymentReqAlreadyPendingMsg);
		validateTheElementPresence(paymentPopupAlertText);

		if(click(paymentPopupOKButton)) {

			reportStep("Successfully clicked on the Ok button at the alert popup", "INFO");
		}else {
			reportStep("Failed to click on the Ok button at the alert popup", "FAIL");
		}

		return this;
	}

	public MyEarningsPage validateTotalCashbackEarnedValue(String totalCashbackEarnedValue) {

		reportStep("About to verify the value total cashback earned "+totalCashbackEarnedValue+" in my earnings page ", "INFO");
		
		String totalCbEarnedValueXpath = "//android.widget.TextView[@text='Total Cashback Earned']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+totalCashbackEarnedValue+")]|//android.widget.TextView[@text='Total Cashback Earned']/parent::android.view.View/android.widget.TextView[contains(@text,"+totalCashbackEarnedValue+")]";
		
		if(isElementLocatedByXpathPresent(totalCbEarnedValueXpath)) {
			
			reportStep("Validated value total cashback earned "+totalCashbackEarnedValue+" in my earnings page ", "PASS");

		}else {
			reportStep("Not able to locate value total cashback earned "+totalCashbackEarnedValue+" in my earnings page ", "FAIL");

		}
		
		MobileElement totalCbEarnedValue = driver.findElement(By.xpath(totalCbEarnedValueXpath));

		validateTheElementPresence(totalCbEarnedValue);

		reportStep("Successfully verified the value total cashback earned "+totalCashbackEarnedValue+" in my earnings page ", "INFO");

		return this;

		
	}
	
	public MyEarningsPage validateTotalRewardsEarnedValue(String totalRewardsEarnedValue) {

		reportStep("About to verify the value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "INFO");

		String totalRwEarnedValueXpath= "//android.widget.TextView[@text='Total Rewards Earned']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+totalRewardsEarnedValue+")]|//android.widget.TextView[@text='Total Rewards Earned']/parent::android.view.View/android.widget.TextView[contains(@text,"+totalRewardsEarnedValue+")]";

		if(isElementLocatedByXpathPresent(totalRwEarnedValueXpath)) {
			reportStep("Validated value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "PASS");

		}else {
			reportStep("Not able to verify the value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "FAIL");

		}
		
		MobileElement totalRwEarnedValue = driver.findElement(By.xpath(totalRwEarnedValueXpath));

		validateTheElementPresence(totalRwEarnedValue);

		reportStep("Successfully verified the value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "INFO");

		return this;

		
	}

	public MyEarningsPage clickTotalCashbackEarnedValue(String totalCashbackEarnedValue) {

		reportStep("About to click the value total cashback earned "+totalCashbackEarnedValue+" in my earnings page ", "INFO");
		
		String totalCbEarnedValueXpath = "//android.widget.TextView[@text='Total Cashback Earned']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+totalCashbackEarnedValue+")]|//android.widget.TextView[@text='Total Cashback Earned']/parent::android.view.View/android.widget.TextView[contains(@text,"+totalCashbackEarnedValue+")]";

		if(isElementLocatedByXpathPresent(totalCbEarnedValueXpath)){
			
			reportStep("Validated value total cashback earned "+totalCashbackEarnedValue+" in my earnings page ", "PASS");

		}else {
			
			reportStep("Not able to verify value total cashback earned "+totalCashbackEarnedValue+" in my earnings page ", "FAIL");

		}
		
		MobileElement totalCbEarnedValue = driver.findElement(By.xpath(totalCbEarnedValueXpath));
		
		if(click(totalCbEarnedValue)) {
			
			reportStep("Sucessfully clicked on cashback tab", "PASS");
			
		}else {
			
			reportStep("Not able to click on cashback tab", "FAIL");
		}

		reportStep("Successfully clicked the value total cashback earned "+totalCashbackEarnedValue+" in my earnings page ", "INFO");

		return new MyEarningsPage(driver, testInfo);

		
	}
	
	public MyEarningsPage clickTotalRewardsEarnedValue(String totalRewardsEarnedValue) {

		reportStep("About to click the value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "INFO");

		String totalRwEarnedValueXpath = "//android.widget.TextView[@text='Total Rewards Earned']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+totalRewardsEarnedValue+")]|//android.widget.TextView[@text='Total Rewards Earned']/parent::android.view.View/android.widget.TextView[contains(@text,"+totalRewardsEarnedValue+")]";
		
		if(isElementLocatedByXpathPresent(totalRwEarnedValueXpath)){
			
			reportStep("Clicked the value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "PASS");

		}else {
			reportStep("Not able to click the value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "FAIL");

		}
		
		MobileElement totalRwEarnedValue = driver.findElement(By.xpath(totalRwEarnedValueXpath));
		
		if(click(totalRwEarnedValue)) {
			
			reportStep("Sucessfully clicked on cashback tab", "PASS");
			
		}else {
			
			reportStep("Not able to click on cashback tab", "FAIL");
		}

		reportStep("Successfully clicked the value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "INFO");

		return new MyEarningsPage(driver, testInfo);

		
	}
	
	public TotalCashbackEarnedPage clickTotalCashbackEarnedValueHavingValueMoreThanZero(String totalCashbackEarnedValue) {

		reportStep("About to click the value total cashback earned "+totalCashbackEarnedValue+" in my earnings page ", "INFO");

		String totalCbEarnedValueXpath = "//android.widget.TextView[@text='Total Cashback Earned']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+totalCashbackEarnedValue+")]|//android.widget.TextView[@text='Total Cashback Earned']/parent::android.view.View/android.widget.TextView[contains(@text,"+totalCashbackEarnedValue+")]";
	
		if(isElementLocatedByXpathPresent(totalCbEarnedValueXpath)){
			
			reportStep("Clicked the value total cashback earned "+totalCashbackEarnedValue+" in my earnings page ", "PASS");

		}else {
			reportStep("Not able to click the value total cashback earned "+totalCashbackEarnedValue+" in my earnings page ", "FAIL");

		}
		
		MobileElement totalCbEarnedValue = driver.findElement(By.xpath(totalCbEarnedValueXpath));
		
		if(click(totalCbEarnedValue)) {
			
			reportStep("Sucessfully clicked on cashback tab", "PASS");
			
		}else {
			
			reportStep("Not able to click on cashback tab", "FAIL");
		}

		reportStep("Successfully clicked the value total cashback earned "+totalCashbackEarnedValue+" in my earnings page ", "INFO");

		return new TotalCashbackEarnedPage(driver, testInfo);

		
	}
	
	public TotalRewardsEarnedPage clickTotalRewardsEarnedValueHavingValueMoreThanZero(String totalRewardsEarnedValue) {

		reportStep("About to click the value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "INFO");
		
		String totalRwEarnedValueXpath = "//android.widget.TextView[@text='Total Rewards Earned']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+totalRewardsEarnedValue+")]|//android.widget.TextView[@text='Total Rewards Earned']/parent::android.view.View/android.widget.TextView[contains(@text,"+totalRewardsEarnedValue+")]";

		if(isElementLocatedByXpathPresent(totalRwEarnedValueXpath)) {
			
			reportStep("Clicked the value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "PASS");

		}else {
			reportStep("Not able to click the value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "FAIL");

		}
		
		MobileElement totalRwEarnedValue = driver.findElement(By.xpath(totalRwEarnedValueXpath));

		if(click(totalRwEarnedValue)) {
			
			reportStep("Sucessfully clicked on cashback tab", "PASS");
			
		}else {
			
			reportStep("Not able to click on cashback tab", "FAIL");
		}

		reportStep("Successfully clicked the value total rewards earned "+totalRewardsEarnedValue+" in my earnings page ", "INFO");

		return new TotalRewardsEarnedPage(driver, testInfo);

		
	}

	public MyEarningsPage clickCashbackTab() {

		reportStep("About to click cashback tab", "INFO");
		
		String cashbackTabXpath = "//android.widget.TextView[@text='Cashback']";
		
		scrollTillRequiredElementIsVisibleFromUpToDown(cashbackTabXpath);	
				
		if(click(cashbackTab)) {
			
			reportStep("Sucessfully clicked on cashback tab", "PASS");
			
		}else {
			
			reportStep("Not able to click on cashback tab", "FAIL");
		}
		
		return this;
		
	}
	
	public MyEarningsPage clickRewardsTab() {

		reportStep("About to click rewards tab", "INFO");
		
		String RewardsTabXpath = "//android.widget.TextView[@text='Rewards']";

		scrollTillRequiredElementIsVisibleFromUpToDown(RewardsTabXpath);		
				
		if(click(rewardsTab)) {
			
			reportStep("Sucessfully clicked on rewards tab", "PASS");
			
		}else {
			
			reportStep("Not able to click on rewards tab", "FAIL");
		}
		
		return this;
		
	}
	
	public MyEarningsPage clickReferralTabClick() {

		reportStep("About to click referral tab", "INFO");
		
		String ReferralTabXpath = "//android.widget.TextView[@text='Referral']";

		scrollTillRequiredElementIsVisibleFromUpToDown(ReferralTabXpath);	
				
		if(click(referralTab)) {
			
			reportStep("Sucessfully clicked on referral tab", "PASS");
			
		}else {
			
			reportStep("Not able to click on referral tab", "FAIL");
		}
		
		return this;
		
	}
	
	public EarningsInfoPage clickInfoIcon() {

		reportStep("About to click infoIcon", "INFO");
		
		scrollFromDownToUpinApp();
				
		if(click(infoIcon)) {
			
			reportStep("Sucessfully clicked on infoIcon", "PASS");
			
		}else {
			
			reportStep("Not able to click on infoIcon", "FAIL");
		}
		
		return new EarningsInfoPage(driver, testInfo);
		
	}

	public EarningsInfoPage clickInfoIconRewardsTab() {

		reportStep("About to click infoIcon", "INFO");
		
		scrollFromDownToUpinApp();
				
		if(click(infoIconRewardsTab)) {
			
			reportStep("Sucessfully clicked on infoIcon", "PASS");
			
		}else {
			
			reportStep("Not able to click on infoIcon", "FAIL");
		}
		
		return new EarningsInfoPage(driver, testInfo);
		
	}

	public AddTicketPage clickRaiseTicketCashbackTab() {

		reportStep("About to click raiseTicket", "INFO");
		
		String raiseTicketText = getText(raiseTicketCashback);
		
		validateTheActualValueContainsExpectedValue(raiseTicketText.trim(), getTestData(7, "raiseTicketCashback").trim());
		validateTheActualValueContainsExpectedValue(raiseTicketText.trim(), getTestData(7, "raiseTicketCashback2").trim());
				
		if(click(raiseTicketCashback)) {
			
			reportStep("Sucessfully clicked on raiseTicketCashback", "PASS");
			
		}else {
			
			reportStep("Not able to click on raiseTicketCashback", "FAIL");
		}
		
		return new AddTicketPage(driver, testInfo);
		
	}

	public AddTicketPage clickRaiseTicketRewardsTab() {

		reportStep("About to click raiseTicket", "INFO");
		
		String raiseTicketText = getText(raiseTicketRewards);
		
		validateTheActualValueContainsExpectedValue(raiseTicketText.trim(), getTestData(7, "raiseTicketRewards").trim());
		validateTheActualValueContainsExpectedValue(raiseTicketText.trim(), getTestData(7, "raiseTicketRewards2").trim());
				
		if(click(raiseTicketRewards)) {
			
			reportStep("Sucessfully clicked on raiseTicketRewards", "PASS");
			
		}else {
			
			reportStep("Not able to click on raiseTicketRewards", "FAIL");
		}
		
		return new AddTicketPage(driver, testInfo);
		
	}

	public MyEarningsPage validateRaiseTicketCashbackTab() {

		reportStep("About to click raiseTicket", "INFO");
		
		String raiseTicketText = getText(raiseTicketCashback);
		
		validateTheActualValueContainsExpectedValue(raiseTicketText.trim(), getTestData(7, "raiseTicketCashback").trim());
		validateTheActualValueContainsExpectedValue(raiseTicketText.trim(), getTestData(7, "raiseTicketCashback2").trim());
		
		return this;
		
	}

	public MyEarningsPage validateRaiseTicketRewardsTab() {

		reportStep("About to click raiseTicket", "INFO");
		
		String raiseTicketText = getText(raiseTicketRewards);
		
		validateTheActualValueContainsExpectedValue(raiseTicketText.trim(), getTestData(7, "raiseTicketRewards").trim());
		validateTheActualValueContainsExpectedValue(raiseTicketText.trim(), getTestData(7, "raiseTicketRewards2").trim());
		
		return this;
		
	}

	public MyEarningsPage validateCashbackEarningsValueCashbackTab(String cashbackEarning) {

		reportStep("About to verify the value cashbackEarning "+cashbackEarning+" in cashback tab ", "INFO");
		
		String totalCbEarnedValueXpath = "//android.widget.TextView[@text='Cashback Earnings']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+cashbackEarning+")]|//android.widget.TextView[@text='Cashback Earnings']/parent::android.view.View/android.widget.TextView[contains(@text,"+cashbackEarning+")]";

		if(isElementLocatedByXpathPresent(totalCbEarnedValueXpath)) {
			
			reportStep("Validated xpath "+totalCbEarnedValueXpath+" presence", "PASS");

		}else {
			reportStep("Not able to validate xpath "+totalCbEarnedValueXpath+" presence", "FAIL");

		}
		

		return this;

		
	}
	
	public MyEarningsPage validateRewardEarningsValueRewardsTab(String rewardEarning) {

		reportStep("About to verify the value rewardEarning "+rewardEarning+" in rewards tab ", "INFO");

		String totalRwEarnedValueXpath = "//android.widget.TextView[@text='Reward Earnings']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+rewardEarning+")]|//android.widget.TextView[@text='Reward Earnings']/parent::android.view.View/android.widget.TextView[contains(@text,"+rewardEarning+")]";
			
		if(isElementLocatedByXpathPresent(totalRwEarnedValueXpath)) {
			reportStep("Validated xpath "+totalRwEarnedValueXpath+" presence", "PASS");

		}else {
			reportStep("Not able to validate xpath "+totalRwEarnedValueXpath+" presence", "FAIL");

		}

		return this;

		
	}

	public MyEarningsPage validateReferralEarningsValueReferralTab(String referralEarning) {

		reportStep("About to verify the value referralEarning "+referralEarning+" in referral tab ", "INFO");
				
		String totalReferralEarnedValueXpath = "//android.widget.TextView[@text='Your Referral Earnings']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,"+referralEarning+")]|//android.widget.TextView[@text='Your Referral Earnings']/parent::android.view.View/android.widget.TextView[contains(@text,"+referralEarning+")]";

		if(isElementLocatedByXpathPresent(totalReferralEarnedValueXpath)) {
			
			reportStep("Validated xpath "+totalReferralEarnedValueXpath+" presence", "PASS");

		}else {
			reportStep("Not able to validate xpath "+totalReferralEarnedValueXpath+" presence", "FAIL");

		}

		return this;

		
	}

	public MyEarningsPage validateBottomTextInAllTab() {

		reportStep("About to bottom text in cashback tab, rewards tab & referral tab", "INFO");
		
		clickCashbackTab();
		validateTheElementPresence(youHaventtextCashbackTab);
		validateTheElementPresence(shopTodayTextCashbackTab);
		
		clickRewardsTab();
		validateTheElementPresence(youHaventtextRewardsTab);
		validateTheElementPresence(shopTodayTextRewardsTab);
		
		clickReferralTabClick();		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();		
		validateTheElementPresence(youHaveNoTextReferralTab);
		validateTheElementPresence(cashbackIsMoreTextReferralTab);
		validateTheElementPresence(inviteSomeFriendsTextReferralTab);

		reportStep("Validated the bottom text in cashback tab, rewards tab & referral tab", "PASS");

		return this;

		
	}

	public SignedInProfilePage clickBackButton() {

		reportStep("About to click on back button in MyEarningsPage", "INFO");

		driver.navigate().back();

		return new SignedInProfilePage(driver, testInfo);
		
	}

	public ReferAndEarn clickReferNow() {

		reportStep("About to click on refer now", "INFO");

		if(click(referNow)){

			reportStep("Successfully clicked on the refer now", "PASS");

		}else {

			reportStep("Not able to click refer now", "FAIL");
		}

		return new ReferAndEarn(driver, testInfo);

	}
	
	public MyEarningsPage clickMonthYearDropdown(String date) {

		reportStep("About to click the month year dropdown "+date+" in respective tab ", "INFO");
		
		String dateValueXpath = "//android.widget.TextView[@text='Order Details']/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[@text='"+date+"']|//android.widget.TextView[@text='Order Details']/parent::android.view.View/android.view.View/android.widget.EditText[@text='"+date+"']";

		if(isElementLocatedByXpathPresent(dateValueXpath)) {
			
			reportStep("Clicked the month year dropdown "+date+" in respective tab ", "PASS");

		}else {
			reportStep("Not able to click the month year dropdown "+date+" in respective tab ", "FAIL");

		}
		
		MobileElement dateValue = driver.findElement(By.xpath(dateValueXpath));

		if(click(dateValue)){

			reportStep("Successfully clicked month year dropdown in respective tab", "PASS");

		}else {

			reportStep("Not able to click month year dropdown in respective tab", "FAIL");
		}

		reportStep("Successfully clicked the month year dropdown "+date+" in respective tab ", "INFO");
		
		clickDateAfterClickingMonthYearDropdown(date);

		return this;

		
	}

	public MyEarningsPage clickDateAfterClickingMonthYearDropdown(String monthYear) {

		reportStep("About to click on date in respective tab", "INFO");
		
		String dropDownValueXpath = "//android.widget.CheckedTextView[@text='"+monthYear+"']";
		
		if(isElementLocatedByXpathPresent(dropDownValueXpath)) {
			
			reportStep("Click on date in respective tab", "PASS");

		}else {
			reportStep("Not able to click on date in respective tab", "FAIL");

		}
		
		MobileElement dropDownValue = driver.findElement(By.xpath(dropDownValueXpath));

		if(click(dropDownValue)){

			reportStep("Successfully clicked date in respective tab", "PASS");

		}else {

			reportStep("Not able to click date in respective tab", "FAIL");
		}

		return this;

	}

	public MyEarningsPage validateDate(String date) {

		reportStep("About to verify the date "+date+" in respective tab ", "INFO");
		
		String dateValueXpath = "//android.widget.TextView[@text='Date']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+date+"']|//android.widget.TextView[@text='Date']/parent::android.view.View/android.widget.TextView[@text='"+date+"']";
		
		//scrollTillRequiredElementIsVisible(dateValueXpath);
		
		if(isElementLocatedByXpathPresent(dateValueXpath)) {
			
			reportStep("Verify the date "+date+" in respective tab ", "PASS");

		}else {
			reportStep("Not able to verify the date "+date+" in respective tab ", "FAIL");

		}

		reportStep("Successfully verified the date "+date+" in respective tab ", "INFO");

		return this;

		
	}
	
	public MyEarningsPage validateRetailer(String retailer) {

		reportStep("About to verify the Retailer "+retailer+" in respective tab ", "INFO");
		
		String retailerValueXpath = "//android.widget.TextView[@text='Retailer']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+retailer+"']|//android.widget.TextView[@text='Retailer']/parent::android.view.View/android.widget.TextView[@text='"+retailer+"']";

		//scrollTillRequiredElementIsVisible(retailerValueXpath);
		
		if(isElementLocatedByXpathPresent(retailerValueXpath)) {
			reportStep("Verified the Retailer "+retailer+" in respective tab ", "PASS");

		}else {
			reportStep("Not able to verify the Retailer "+retailer+" in respective tab ", "FAIL");

		}

		return this;

		
	}
	
	public MyEarningsPage validateOrderAmount(String orderAmount) {

		reportStep("About to verify the orderAmount "+orderAmount+" in respective tab ", "INFO");
		
		String orderAmountValueXpath = "//android.widget.TextView[@text='Order Amount']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+orderAmount+"')]|//android.widget.TextView[@text='Order Amount']/parent::android.view.View/android.widget.TextView[contains(@text,'"+orderAmount+"')]";

		//scrollTillRequiredElementIsVisible(orderAmountValueXpath);
		
		if(isElementLocatedByXpathPresent(orderAmountValueXpath)) {
			
			reportStep("Verified the orderAmount "+orderAmount+" in respective tab ", "PASS");

		}else {
			reportStep("Not able to verify the orderAmount "+orderAmount+" in respective tab ", "FAIL");

		}

		return this;
		
	}
	
	
	public MyEarningsPage validateOrderId(String orderId) {

		reportStep("About to verify the OrderId is not Avaialble  ", "INFO");
		
		String xpath = "//android.widget.TextView[@text='Order Id']/following-sibling::android.widget.TextView[@text='"+orderId+"']";
		
		try {
		validateTheElementPresence(driver.findElement(By.xpath(xpath)));
		}catch (Exception e) {
			
			reportStep("Not able to find the OrderId in MyEarnings Page", "Fail");
		}
		
		return this;
		
	}
	
	public MyEarningsPage validateOrderIdAsNotAvailable() {

		reportStep("About to verify the OrderId is not Avaialble  ", "INFO");
		
		validateTheElementPresence(orderIdNotAvailable);
		
		return this;
		
	}
	
	
	
	public MyEarningsPage validateCashbackAmount(String cashbackAmount) {

		reportStep("About to verify the CashbackAmount "+cashbackAmount+" in respective tab ", "INFO");

		String statusValueXpath = "//android.widget.TextView[@text='CashBack Amount']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+cashbackAmount+"')]|//android.widget.TextView[@text='CashBack Amount']/parent::android.view.View/android.widget.TextView[contains(@text,'"+cashbackAmount+"')]";

		//scrollTillRequiredElementIsVisible(statusValueXpath);
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		
		if(isElementLocatedByXpathPresent(statusValueXpath)) {
			
			reportStep("Verified the CashbackAmount "+cashbackAmount+" in respective tab ", "PASS");

		}else {
			reportStep("Not able to verify the CashbackAmount "+cashbackAmount+" in respective tab ", "FAIL");

		}

		return this;

		
	}

	public MyEarningsPage validateStatus(String status) {

		reportStep("About to verify the status "+status+" in respective tab ", "INFO");
		
		String statusValueXpath = "//android.widget.TextView[@text='Status']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+status+"')]|//android.widget.TextView[@text='Status']/parent::android.view.View/android.widget.TextView[contains(@text,'"+status+"')]";

		//scrollTillRequiredElementIsVisible(statusValueXpath);
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		
		if(isElementLocatedByXpathPresent(statusValueXpath)) {
			reportStep("Verify the status "+status+" in respective tab ", "PASS");

		}else {
			reportStep("Not to verify the status "+status+" in respective tab ", "FAIL");
		
		}

		reportStep("Successfully verified the status "+status+" in respective tab ", "INFO");

		return this;

		
	}

	public MyEarningsPage validateExpected(String expected) {

		reportStep("About to verify the expected "+expected+" in respective tab ", "INFO");

		String expectedValueXpath = "//android.widget.TextView[@text='Expected']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+expected+"']|//android.widget.TextView[@text='Expected']/parent::android.view.View/android.widget.TextView[@text='"+expected+"']";

		//scrollTillRequiredElementIsVisible(expectedValueXpath);
			
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		
		if(isElementLocatedByXpathPresent(expectedValueXpath)) {
			
			reportStep("About to verify the expected "+expected+" in respective tab ", "PASS");

		}else {
			reportStep("Not able to verify the expected "+expected+" in respective tab ", "FAIL");

		}

		return this;

		
	}
	
	public MyEarningsPage validateAbsenceExpected(String expected) {

		reportStep("About to verify the absence of expected "+expected+" in respective tab ", "INFO");
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();

		String expectedValue = "//android.widget.TextView[@text='Expected']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+expected+"']|//android.widget.TextView[@text='Expected']/parent::android.view.View/android.widget.TextView[@text='"+expected+"']";

		validateTheElementAbsence(expectedValue);

		reportStep("Successfully verified the absence of expected "+expected+" in respective tab ", "INFO");

		return this;

		
	}

	public MyEarningsPage validateAbsenceOrderAmount() {

		reportStep("About to verify the absence orderAmount in respective tab ", "INFO");
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();

		String orderAmount = "//android.widget.TextView[@text='Order Amount']";

		validateTheElementAbsence(orderAmount);

		reportStep("Successfully verified the absence orderAmount in respective tab ", "INFO");

		return this;

		
	}

	public MyEarningsPage validateDateReferralTab(String date) {

		reportStep("About to verify the date "+date+" in referral tab ", "INFO");
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		
		String dateXpath= "//android.widget.TextView[@text='Date']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='"+date+"']|//android.widget.TextView[@text='Date']/parent::android.view.View/parent::android.view.View/android.view.View/android.widget.TextView[@text='"+date+"']";
	
		if(isElementLocatedByXpathPresent(dateXpath)) {

			reportStep("Verify the date "+date+" in referral tab ", "INFO");
		}else {

			reportStep("Not able to verify the date "+date+" in referral tab ", "FAIL");
		}

		reportStep("Successfully verified the date "+date+" in referral tab ", "INFO");

		return this;

		
	}
	
	public MyEarningsPage validateReferralNameReferralTab(String referralName) {

		reportStep("About to verify the referral name "+referralName+" in referral tab ", "INFO");
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();

		String referalNameXpath = "//android.widget.TextView[@text='Referral Name']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='"+referralName+"']|//android.widget.TextView[@text='Referral Name']/parent::android.view.View/parent::android.view.View/android.view.View/android.widget.TextView[@text='"+referralName+"']";

		if(isElementLocatedByXpathPresent(referalNameXpath)) {
			reportStep("Verify the referral name "+referralName+" in referral tab ", "INFO");

		}else {
			reportStep("Not able to verify the referral name "+referralName+" in referral tab ", "FAIL");

		}

		reportStep("Successfully verified the referral name "+referralName+" in referral tab ", "INFO");

		return this;

		
	}
	
	public MyEarningsPage validateCashbackReferralTab(String status) {

		reportStep("About to verify the cb "+status+" in referral tab ", "INFO");
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();

		String statusValueXpath = "//android.widget.TextView[@text='Cashback']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[contains(@text,'"+status+"')]|//android.widget.TextView[@text='Cashback']/parent::android.view.View/parent::android.view.View/android.view.View/android.widget.TextView[contains(@text,'"+status+"')]";
		
		if(isElementLocatedByXpathPresent(statusValueXpath)) {
			
			reportStep("Verify the cb "+status+" in referral tab ", "INFO");

		}else {
			reportStep("Not able to verify the cb "+status+" in referral tab ", "FAIL");

		}
		
		reportStep("Successfully verified the cb "+status+" in referral tab ", "INFO");

		return this;

		
	}
	
	public MyEarningsPage validateStatusReferralTab(String status) {

		reportStep("About to verify the status "+status+" in referral tab ", "INFO");
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();

		String statusValueXpath = "//android.widget.TextView[@text='Status']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[contains(@text,'"+status+"')]|//android.widget.TextView[@text='Status']/parent::android.view.View/parent::android.view.View/android.view.View/android.widget.TextView[contains(@text,'"+status+"')]";
		
		if(isElementLocatedByXpathPresent(statusValueXpath)){
			reportStep("Verify the status "+status+" in referral tab ", "INFO");

		}else {
			reportStep("Not able to verify the status "+status+" in referral tab ", "FAIL");

		}

		reportStep("Successfully verified the status "+status+" in referral tab ", "INFO");

		return this;

		
	}

	public MyEarningsPage validateEntireBlockBelowRespectiveTab(String currentDate, String retailer, String orderAmount, String cashbackAmount, String status, String ninetyDaysFromCurrentDate) {

		reportStep("About to verify the currentDate "+currentDate+", retailer "+retailer+", orderAmount "+orderAmount+", cashbackAmount "+cashbackAmount+", status "+status+", ninetyDaysFromCurrentDate "+ninetyDaysFromCurrentDate+" in respective tab ", "INFO");

		String entireBlockXpath = "//android.widget.TextView[@text='Date']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+currentDate+"']/parent::android.view.ViewGroup/android.widget.TextView[@text='Retailer']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+retailer+"']/parent::android.view.ViewGroup/android.widget.TextView[@text='Order Amount']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+orderAmount+"')]/parent::android.view.ViewGroup/android.widget.TextView[@text='CashBack Amount']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+cashbackAmount+"')]/parent::android.view.ViewGroup/android.widget.TextView[@text='Status']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+status+"')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'Expected')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+ninetyDaysFromCurrentDate+"')]";

		scrollTillRequiredElementIsVisibleFromDownToUp(entireBlockXpath);

		reportStep("Successfully verified the the currentDate "+currentDate+", retailer "+retailer+", orderAmount "+orderAmount+", cashbackAmount "+cashbackAmount+", status "+status+", ninetyDaysFromCurrentDate "+ninetyDaysFromCurrentDate+" in respective tab ", "INFO");

		return this;

		
	}

	public MyEarningsPage validateEntireBlockBelowRespectiveTabExceptExpected(String currentDate, String retailer, String orderAmount, String cashbackAmount, String status) {

		reportStep("About to verify the currentDate "+currentDate+", retailer "+retailer+", orderAmount "+orderAmount+", cashbackAmount "+cashbackAmount+", status "+status+" in respective tab ", "INFO");

		String entireBlockXpath = "//android.widget.TextView[@text='Date']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+currentDate+"']/parent::android.view.ViewGroup/android.widget.TextView[@text='Retailer']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+retailer+"']/parent::android.view.ViewGroup/android.widget.TextView[@text='Order Amount']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+orderAmount+"')]/parent::android.view.ViewGroup/android.widget.TextView[@text='CashBack Amount']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+cashbackAmount+"')]/parent::android.view.ViewGroup/android.widget.TextView[@text='Status']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+status+"')]";

		scrollTillRequiredElementIsVisibleFromDownToUp(entireBlockXpath);

		return this;

		
	}

	public MyEarningsPage validateEntireBlockBelowReferralTab(String currentDate, String referralName, String cashbackAmount, String status) {

		reportStep("About to verify the currentDate "+currentDate+", referralName "+referralName+", cashbackAmount "+cashbackAmount+", status "+status+" in referral tab ", "INFO");

		String entireBlockXpath = "//android.widget.TextView[@text='"+currentDate+"']/parent::android.view.ViewGroup/android.widget.TextView[@text='"+referralName+"']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+cashbackAmount+"')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+status+"')]";
		
		scrollTillRequiredElementIsVisibleFromDownToUp(entireBlockXpath);

		reportStep("Successfully verify the currentDate "+currentDate+", referralName "+referralName+", cashbackAmount "+cashbackAmount+", status "+status+" in referral tab ", "INFO");

		return this;

		
	}

	public MyEarningsPage clickViewMore() {

		reportStep("About to click on ViewMore", "INFO");

		String viewMore = "//*[contains(@text,'View more')]|//android.widget.TextView[@content-desc='txt_earningsReferral_viewMore']";

		scrollTillRequiredElementIsVisibleFromDownToUp(viewMore);

		MobileElement viewMoreLink = driver.findElement(By.xpath(viewMore));

		if(click(viewMoreLink)){

			reportStep("Successfully clicked on the ViewMore", "PASS");

		}else {

			reportStep("Not able to click ViewMore", "FAIL");
		}

		validateTheElementAbsence(viewMore);

		return this;

	}

	public SignedInProfilePage clickOnProfileIconForSignedUser() {

		reportStep("About to click on the Profile icon for  Signed user ", "INFO");

		if(click(profileIcon)) {

			reportStep("Successfully clicked on the Profile icon at the Home page for  Signed user ", "PASS");
		}else {

			reportStep("Failed to  click on the Profile icon at the Home page for  Signed user", "FAIL");
		}

		return new SignedInProfilePage(driver, testInfo);
	}

}
