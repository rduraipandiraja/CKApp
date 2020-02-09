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

public class HowItWorks extends WrapperMethods {

	//Constructor call
	public HowItWorks(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the How It Works Page ", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(howItWorks));
			reportStep("Successfully landed on the How it Works Page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the How it Works Page", "FAIL");
		}

	}
	
	//Variable declaration
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='How It Works']") 
	MobileElement howItWorks;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'SELECT TOPIC')]") 
	MobileElement selectTopic;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'How To Use CashKaro')]") 
	MobileElement howToUseCashKaro;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'How We Pay You')]") 
	MobileElement howWePayYou;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Cashback Related')]") 
	MobileElement cashbackRelated;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Cashback Status Questions')]") 
	MobileElement cashbackStatusQuestion;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Cashback Best Practices')]") 
	MobileElement cashbackBestPractices;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Missing Cashback? See here')]") 
	MobileElement missingCashbackSeeHere;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Rewards Related')]") 
	MobileElement rewardsRelated;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'What are Rewards?')]") 
	MobileElement whatAreRewards;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Rewards Status Questions')]") 
	MobileElement rewardsStatusQuestions;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Best practices to ensure Rewards track')]") 
	MobileElement bestPracticesToEnsureRewardsTrack;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Missing Rewards? See here')]") 
	MobileElement missingRewardsSeeHere;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Payments')]") 
	MobileElement payments;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"txt_howItWorksSideMenu_closeDrawer\"]") 
	MobileElement closeButton;
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView)[3]") 
	MobileElement hamburgerButton;
	
	
	// Page wise texts :
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'EARN CASHBACK')]") 
	MobileElement earnCashbackText;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'RETAILERS PAY US')]") 
	MobileElement retailerPayUsText;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Cashback Status Questions')]") 
	MobileElement cashbackStatusQuestionsText;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Cashback Best Practices')]") 
	MobileElement cashbackBestPracticesText;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Missing Cashback? See Here')]") 
	MobileElement missingCbSeeHereText;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'What are Rewards')]") 
	MobileElement whatAreRewardsText;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Rewards Status Question')]") 
	MobileElement rewardsStatusQuestionText;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Rewards Best Practices')]") 
	MobileElement rewardsBestPracticesText;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Missing Rewards? See Here')]") 
	MobileElement missingRewardsSeeHereText;
	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Payments Related')]") 
	MobileElement paymentRelated_Text;

	
	
	
	
	
	//Methods :
	
	public HowItWorks clickOnHowToUseCashKaro() {
		
		reportStep("About to click How to use Cashkaro ", "INFO");
		
		if(clickAfterWait(howToUseCashKaro)) {
			
			reportStep("Successfully clicked on How to Use CashKaro link ", "PASS");
		}else {
			
			reportStep("Failed  to click on how to use Cashkaro ", "FAIL");
		}
		
		
		validateTheElementPresence(earnCashbackText);
		
		return this;
	}
	
	public HowItWorks clickOnHowWePayYou() {
		
		reportStep("About to click how We Pay you  ", "INFO");
		
		if(clickAfterWait(howWePayYou)) {
			
			reportStep("Successfully clicked on How we pay you link ", "PASS");
		}else {
			
			reportStep("Failed  to click on How We Pay You ", "FAIL");
		}
		
		validateTheElementPresence(retailerPayUsText);
		
		return this;
	}

	public HowItWorks clickOnCashbackRelated() {
		
		reportStep("About to click on Cashback Related ", "INFO");
		
		if (clickAfterWait(cashbackRelated)) {
			
			reportStep("Successfully clicked on the Cashback Related", "PASS");
			
		}else {
			
			reportStep("Failed to click on the Cashback Related", "FAIL");
		}
		
		validateTheElementPresence(cashbackStatusQuestionsText);
		
		return this;
	}

	public HowItWorks clickOnCashbackStatusQuestions() {
		
		reportStep("About to click on Cashback Status Questions ", "INFO");
		
		if (clickAfterWait(cashbackStatusQuestion)) {
			
			reportStep("Successfully clicked on the Cashback Status Questions", "PASS");
		}else {
			
			reportStep("Failed to click on the Cashback Status Questions ", "FAIL");
		}
		
		validateTheElementPresence(cashbackStatusQuestionsText);
		
		return this;
	}

	public HowItWorks clickOnCashbackBestPractice() {

		reportStep("About to click on Cashback Best Practices ", "INFO");

		if (clickAfterWait(cashbackBestPractices)) {

			reportStep("Successfully clicked on the Cashback Best Practices ", "PASS");
		}else {

			reportStep("Failed to click on the Cashback Best Practices  ", "FAIL");
		}
		
		validateTheElementPresence(cashbackBestPracticesText);
		
		return this;
	}

	public HowItWorks clickOnMissingCashbackSeeHere() {

		reportStep("About to click on Missing Cashback See here", "INFO");

		if (clickAfterWait(missingCashbackSeeHere)) {

			reportStep("Successfully clicked on the Missing Cashback see here ", "PASS");
		}else {

			reportStep("Failed to click on the Missing Cashback See here", "FAIL");
		}
		
		validateTheElementPresence(missingCbSeeHereText);
		
		return this;
	}
	
	public HowItWorks clickOnRewardRelated() {

		reportStep("About to click on Rewards Related", "INFO");

		if (clickAfterWait(rewardsRelated)) {

			reportStep("Successfully clicked on the Rewards Related ", "PASS");
		}else {

			reportStep("Failed to click on the Rewards Related", "FAIL");
		}
		
		validateTheElementPresence(whatAreRewardsText);
		
		return this;
	}

	public HowItWorks clickOnwhatAreRewards() {

		reportStep("About to click What Are Rewards ? ", "INFO");

		if (clickAfterWait(whatAreRewards)) {

			reportStep("Successfully clicked on the what are Rewards ? ", "PASS");
		}else {

			reportStep("Failed to click on the What are Rewards ?", "FAIL");
		}
		
		validateTheElementPresence(whatAreRewardsText);
		
		return this;
	}

	public HowItWorks clickOnRewardsstatusQuestions () {

		reportStep("About to click Rewards Status Questions ", "INFO");

		if (clickAfterWait(rewardsStatusQuestions)) {

			reportStep("Successfully clicked on Rewards Status Question  ", "PASS");
		}else {

			reportStep("Failed to click on the Rewards Status Question ", "FAIL");
		}
		
		validateTheElementPresence(rewardsStatusQuestionText);
		return this;
	}

	public HowItWorks clickOnBestPracticeToEnsureRewardsTrack() {
		
		reportStep("About to click on the Best Practice to Ensure Rewards Track ", "INFO");
		
		if (clickAfterWait(bestPracticesToEnsureRewardsTrack)) {
			
			reportStep("Successfully clicked on the Best practices to Ensure Rewards ", "PASS");
		}else {
			
			reportStep("Failed to click on the Best Practices to Ensure Rewards ", "FAIL");
		}
		
		validateTheElementPresence(rewardsBestPracticesText);
		
		return this;
	}

	public HowItWorks clickOnMissingRewardsHere() {
		
		reportStep("About to click on the Missing Rewards Here ", "INFO");
		
		if (clickAfterWait(missingRewardsSeeHere)) {
			
			reportStep("Successfully clicked on the Missing Rewards See here link ", "PASS");
		}else {
			
			reportStep("Failed to click on the Missing Rewards See here link ", "FAIL");
		}
		
		validateTheElementPresence(missingRewardsSeeHereText);
		return this;
	}

	public HowItWorks clickOnPayments() {
		
		reportStep("About to click on the Payment link ", "INFO");
		
		if (clickAfterWait(payments)) {
			
			reportStep("Successfuly clicked on the Payment ", "PASS");
			
		}else {
			
			reportStep("Failed to click on the Payment ", "FAIL");
		}
		
		validateTheElementPresence(paymentRelated_Text);
		return this;
	}

	public HowItWorks clickOnCloseButton() {
		
		reportStep("About to clickOn close Button in the How it works slider ", "INFO");
		
		if (clickAfterWait(closeButton)) {
			
			reportStep("Successfully clicked on the Close button , in the how it works slider ", "PASS");
		}else {
			
			reportStep("Failed to click on Close Button ", "FAIL");
		}
		
		return this;
	}

	public HowItWorks clickOnHamburgerButton() {
		
		reportStep("About to clickOn Hamburger button  in the How it works slider ", "INFO");
		
		if (clickAfterWait(hamburgerButton)) {
			
			reportStep("Successfully clicked on Hamburger button, in the how it works slider ", "PASS");
		}else {
			
			reportStep("Failed to click on Hamburger button , in How it works slider  ", "FAIL");
		}
		
		return this;
	}

	public HowItWorks validateSlider() {
		
		reportStep("About to validate the Slider element and its text ", "INFO");
		
		validateTheElementPresence(selectTopic);
		validateTheElementPresence(howToUseCashKaro);
		validateTheElementPresence(howWePayYou);
		validateTheElementPresence(cashbackRelated);
		validateTheElementPresence(cashbackStatusQuestion);
		validateTheElementPresence(cashbackBestPractices);
		validateTheElementPresence(missingCashbackSeeHere);
		validateTheElementPresence(rewardsRelated);
		validateTheElementPresence(whatAreRewards);
		validateTheElementPresence(rewardsStatusQuestions);
		validateTheElementPresence(bestPracticesToEnsureRewardsTrack);
		validateTheElementPresence(missingRewardsSeeHere);
		validateTheElementPresence(payments);
		
		clickOnCloseButton();
		
		validateTheElementPresence(earnCashbackText);
		
		return this;
	}


}
