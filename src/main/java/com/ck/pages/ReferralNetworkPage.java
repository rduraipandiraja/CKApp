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

public class ReferralNetworkPage extends WrapperMethods  {
	
	//Constructor call
	public ReferralNetworkPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the Referral Network page", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(myReferralsHeader));
			reportStep("Successfully landed on the Referral network  page", "PASS");

		}catch(Exception e) {
			
			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the Referral network page", "FAIL");
		}

	}
	
	//Elements :	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_myReferral_joinedDateValue']") 
	MobileElement dateJoined;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_myReferral_referralNameValue']") 
	MobileElement referralName;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_myReferral_statusValue']") 
	MobileElement status;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='My Referrals']") 
	MobileElement myReferralsHeader;

	@FindBy(how = How.XPATH, using = "//android.widget.ImageView[@content-desc='img_myReferral_noreferral']/parent::android.view.ViewGroup/android.widget.TextView[@text='There is no referral cashback']/parent::android.view.ViewGroup/android.widget.TextView[@text='You are now entitled to 10% Extra Cashback everytime your friend shops via us! You can earn more if you refer us to more people in your network.']|//android.widget.ImageView[@content-desc='img_myReferral_noreferral']/parent::android.view.View/android.widget.TextView[@text='There is no referral cashback']/parent::android.view.View/android.widget.TextView[@text='You are now entitled to 10% Extra Cashback everytime your friend shops via us! You can earn more if you refer us to more people in your network.']") 
	MobileElement myReferralsBodyText;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='My Referrals']") 
	MobileElement referFriends;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='My Referrals']") 
	MobileElement totalReferralCashbackEarned;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='REFER & EARN NOW']") 
	MobileElement referAndEarnNow;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView)[1]") 
	MobileElement backButtonClick;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='My Referral Network']") 
	MobileElement referralNetworkTitle;

	public ReferralNetworkPage validateDateJoined(String date) {
		
		reportStep("About to validate Date Value at Referral network page", "INFO");
		
		validateTheElementPresence(referralNetworkTitle);
		
		String dateXpath = "//android.widget.TextView[@text='Date Joined']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='"+date+"']|//android.widget.TextView[@text='Date Joined']/parent::android.view.View/parent::android.view.View/android.widget.ScrollView/android.view.View/android.view.View/android.widget.TextView[@text='"+date+"']";

		if(isElementLocatedByXpathPresent(dateXpath)) {
			reportStep("Validated referralName in ReferralNetworkPage", "PASS");
			
		}else {
			reportStep("Not able to validate referralName in ReferralNetworkPage", "FAIL");
			
		}
		
		return this;
	}
	
	public ReferralNetworkPage validateReferralName(String referralName) {
		
		reportStep("About to validate referralName at Referral network", "INFO");

		String referralNameXpath = "//android.widget.TextView[@text='Referral Name']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='"+referralName+"']|//android.widget.TextView[@text='Referral Name']/parent::android.view.View/parent::android.view.View/android.widget.ScrollView/android.view.View/android.view.View/android.widget.TextView[@text='"+referralName+"']";

		if(isElementLocatedByXpathPresent(referralNameXpath)) {
			reportStep("Validated referralName in ReferralNetworkPage", "PASS");
			
		}else {
			reportStep("Not able to validate referralName in ReferralNetworkPage", "FAIL");
			
		}
		
		return this;
	}
	
	public ReferralNetworkPage validateStatus(String status) {
				
		reportStep("About to validate status at Referral network", "INFO");

		String statusXpath = "//android.widget.TextView[@text='Status']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='"+status+"']|//android.widget.TextView[@text='Status']/parent::android.view.View/parent::android.view.View/android.widget.ScrollView/android.view.View/android.view.View/android.widget.TextView[@text='"+status+"']";

		if(isElementLocatedByXpathPresent(statusXpath)) {
			reportStep("Validated status in ReferralNetworkPage", "PASS");
			
		}else {
			reportStep("Not able to validate status in ReferralNetworkPage", "FAIL");
			
		}
	
		return this;
	}

	public ReferralNetworkPage validateTotalReferralCashbackEarnedValue(String totalReferralCashbackEarnedValue) {
				
		reportStep("About to validate totalReferralCashbackEarned in my referrals page", "INFO");

		String totalReferralCashbackEarnedXpath = "//android.widget.TextView[@text='Total Referral Cashback Earned']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+totalReferralCashbackEarnedValue+"')]|//android.widget.TextView[@text='Total Referral Cashback Earned']/parent::android.view.View/android.widget.TextView[contains(@text,'"+totalReferralCashbackEarnedValue+"')]";

		if(isElementLocatedByXpathPresent(totalReferralCashbackEarnedXpath)) {
			reportStep("Validated totalReferralCashbackEarnedValue in ReferralNetworkPage", "PASS");
			
		}else {
			reportStep("Not able to validate totalReferralCashbackEarnedValue in ReferralNetworkPage", "FAIL");
			
		}
		
		return this;
	}

	public ReferralNetworkPage validateFriendsJoinedValue(String friendsJoinedValue) {
		
		reportStep("About to validate friendsJoined in my referrals page", "INFO");

		String friendsJoinedXpath = "//android.widget.TextView[@text='Friends Joined']/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+friendsJoinedValue+"')]|//android.widget.TextView[@text='Friends Joined']/parent::android.view.View/android.widget.TextView[contains(@text,'"+friendsJoinedValue+"')]";

		if(isElementLocatedByXpathPresent(friendsJoinedXpath)) {
			reportStep("Validated friendsJoinedValue in ReferralNetworkPage", "PASS");
			
		}else {
			reportStep("Not able to validate friendsJoinedValue in ReferralNetworkPage", "FAIL");
			
		}

		return this;
	}

	public ReferralNetworkPage validateObjectsMyReferrals() {
		
		reportStep("About to validate objects in ReferralNetworkPage", "INFO");

		validateTheElementPresence(myReferralsHeader);
		validateTheElementPresence(myReferralsBodyText);
		
		return this;
	}

	public ReferAndEarn clickReferNow() {

		reportStep("About to click on refer now", "INFO");

		if(click(referAndEarnNow)){

			reportStep("Successfully clicked on the referAndEarnNow in ReferralNetworkPage", "PASS");

		}else {

			reportStep("Not able to click referAndEarnNow in ReferralNetworkPage", "FAIL");
		}

		return new ReferAndEarn(driver, testInfo);

	}

	public SignedInProfilePage clickBackButton() {

		reportStep("About to click on back button in ReferralNetworkPage", "INFO");

		if(click(backButtonClick)) {

			reportStep("Successfully clicked on the back button in ReferralNetworkPage", "PASS");
		}else {

			reportStep("Failed to  click on the back button in ReferralNetworkPage", "FAIL");
		}

		return new SignedInProfilePage(driver, testInfo);
		
	}

}
