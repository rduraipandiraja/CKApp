package com.ck.pages;


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

public class ReferAndEarn extends WrapperMethods{

	public ReferAndEarn(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the ReferAndEarn Page", "INFO");

		try {
			wait.until(ExpectedConditions.visibilityOf(referAndEarn));

			reportStep("Successfully landed on the ReferAndEarn page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the  ReferAndEarn page", "FAIL");
		}

	}

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Refer & Earn']")
	MobileElement referAndEarn ;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView)[1]") 
	MobileElement backButtonClick;

	@FindBy(how = How.XPATH, using = "//android.widget.ImageView[@content-desc=\"img_referAndEarn_referIMG\"]") 
	MobileElement referAndEarnImg;

	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Invite friends & earn 10% Cashback every time your friend shops via CashKaro')]") 
	MobileElement inviteFriendText;

	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Your Referral Link')]") 
	MobileElement yourReferralText;

	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Copy the link below and share with your friends!')]") 
	MobileElement copyTheLinkText;

	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Tap to Copy')]") 
	MobileElement tapToCopyText;

	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Facebook')]") 
	MobileElement facebookText;

	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Twitter')]") 
	MobileElement twitterText;

	@FindBy(how = How.XPATH, using = "//*[contains(@text,'WhatsApp')]") 
	MobileElement whatsAppText;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"icon_referAndEarn_shareViaFacebook\"]") 
	MobileElement facebookShareIcon;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"icon_referAndEarn_shareViaTwitter\"]") 
	MobileElement twitterShareIcon;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"icon_referAndEarn_shareViaWhatsApp\"]") 
	MobileElement whatAppShareIcon;

	@FindBy(how = How.XPATH, using = "//*[contains(@text,'https://cashkaro.onelink.me')]") 
	MobileElement referralLinkURL;

	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Invite by Social Media')]") 
	MobileElement inviteBySocialMedia;



	//Methods:

	public MyEarningsPage clickBackButton() {

		reportStep("About to click on back button in refer & earn page", "INFO");

		if(click(backButtonClick)) {

			reportStep("Successfully clicked on the back button in refer & earn page", "PASS");
		}else {

			reportStep("Failed to  click on the back button in refer & earn page", "FAIL");
		}

		return new MyEarningsPage(driver, testInfo);

	}

	public ReferralNetworkPage clickBackButtonRedirectToReferralNetwork() {

		reportStep("About to click on back button in refer & earn page", "INFO");

		if(click(backButtonClick)) {

			reportStep("Successfully clicked on the back button in refer & earn page", "PASS");
		}else {

			reportStep("Failed to  click on the back button in refer & earn page", "FAIL");
		}

		return new ReferralNetworkPage(driver, testInfo);

	}

	public void validateReferAndEarnPage() {

		reportStep("About to validate the Refer and Earn Page ", "INFO");

		//Icon validation
		validateTheElementPresence(whatAppShareIcon);
		validateTheElementPresence(twitterShareIcon);
		validateTheElementPresence(facebookShareIcon);
		//Icon Text validation :


		validateTheElementPresence(whatsAppText);
		validateTheElementPresence(twitterText);
		validateTheElementPresence(facebookText);
		//Other elements :

		validateTheElementPresence(tapToCopyText);
		validateTheElementPresence(copyTheLinkText);
		validateTheElementPresence(yourReferralText);
		validateTheElementPresence(inviteFriendText);
		validateTheElementPresence(referAndEarnImg);
		validateTheElementPresence(backButtonClick);
		validateTheElementPresence(referAndEarn);
		validateTheElementPresence(referralLinkURL);
		validateTheElementPresence(inviteBySocialMedia);

	}















}
