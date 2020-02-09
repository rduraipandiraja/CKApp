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

public class RecentClicksPage extends WrapperMethods {

	//Constructor call
	public RecentClicksPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the Recent Click Page ", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(recentClickBodyName));

			reportStep("Successfully landed on the Recent Click Page ", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the Recent click Page ", "FAIL");
		}

	}

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Recent Clicks']")
	MobileElement recentClickBodyName ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text ='RAISE TICKET' ]")
	MobileElement raiseTicketButton ;
	@FindBy(how = How.XPATH, using = "//android.widget.ImageView")
	MobileElement recentClickImage ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please login to see your recent clicks!']")
	MobileElement recentClickUnsignedUserBodyText ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Whoops!']/parent::android.view.ViewGroup/android.widget.TextView[@text='You do not have any clicks from CashKaro yet.']/parent::android.view.ViewGroup/android.widget.TextView[@text='Saving are just one click away.']")
	MobileElement recentClickSignedUserBodyText ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='VIEW TICKET']")
	MobileElement viewDetailsButton ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Search']") 
	MobileElement searchIcon;


	public AddTicketPage clickOnRaiseTicketButton() {

		reportStep("About to click on the Raise ticket button from the Recent clicks screen ", "INFO");

		if(click(raiseTicketButton)) {

			reportStep("Successfully clicked on the Raise ticket button from the Recent Click Page ", "PASS");

		}else {

			reportStep("Not Able to click on the Raise ticket button from the RECENT CLICK PAGE ", "FAIL");
		}

		return new AddTicketPage(driver, testInfo);

	}

	public HomePage validateObjectsUnSignedUserRecentClicksPage() {

		reportStep("About to validate the objects in recent clicks page as a un-signed user ", "INFO");

		validateTheElementPresence(recentClickImage);
		validateTheElementPresence(recentClickUnsignedUserBodyText);

		return new HomePage(driver, testInfo);

	}

	public HomePage validateObjectsSignedUserRecentClicksPage() {

		reportStep("About to validate the objects in recent clicks page as a signed user ", "INFO");

		validateTheElementPresence(recentClickImage);
		validateTheElementPresence(recentClickSignedUserBodyText);

		return new HomePage(driver, testInfo);

	}

	public RecentClicksPage clickDisabledRaiseTicket(String retailer) {

		reportStep("About to clickDisabledRaiseTicket button in recent clicks page as a signed user ", "INFO");

		String raiseTicketButtonXpath = "//android.widget.TextView[contains(@text,'"+retailer+"')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'')]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.TextView[@text='Awaiting Tracking Details']/parent::android.view.ViewGroup/android.widget.TextView[@text='Pending']/parent::android.view.ViewGroup/android.widget.TextView[@text='Confirmed']/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='icon_myAct_step1']/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='icon_myAct_step2']/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='icon_myAct_step3']/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='txt_myAct_step1_progress']/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='txt_myAct_step2_progress']/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='txt_myAct_step3_progress']/parent::android.view.ViewGroup/android.widget.TextView[@text='*Raise a ticket if your Cashback is not tracked within 72 hours']/parent::android.view.ViewGroup/android.widget.TextView[@text='RAISE TICKET']|//android.widget.TextView[contains(@text,'"+retailer+"')]/parent::android.view.View/android.widget.TextView[contains(@text,'')]/parent::android.view.View/parent::android.view.View/android.widget.TextView[@text='Awaiting Tracking Details']/parent::android.view.View/android.widget.TextView[@text='Pending']/parent::android.view.View/android.widget.TextView[@text='Confirmed']/parent::android.view.View/android.widget.TextView[@content-desc='icon_myAct_step1']/parent::android.view.View/android.widget.TextView[@content-desc='icon_myAct_step2']/parent::android.view.View/android.widget.TextView[@content-desc='icon_myAct_step3']/parent::android.view.View/android.widget.TextView[@content-desc='txt_myAct_step1_progress']/parent::android.view.View/android.widget.TextView[@content-desc='txt_myAct_step2_progress']/parent::android.view.View/android.widget.TextView[@content-desc='txt_myAct_step3_progress']/parent::android.view.View/android.widget.TextView[@text='*Raise a ticket if your Cashback is not tracked within 72 hours']/parent::android.view.View/android.widget.TextView[@text='RAISE TICKET']";

		if(clickByXpath(raiseTicketButtonXpath)) {
			reportStep("Successfully Clicked on Raise Ticket Button in Recent click Page  ", "PASS");

		}else {
			reportStep("Failed to Click on Raise Ticket Button on Recent click Page ", "FAIL");

		}		


		return this;

	}

	public RecentClicksPage validateAbsenceOfRaiseTicketButton() {

		reportStep("About to clickDisabledRaiseTicket button in recent clicks page as a signed user ", "INFO");

		String raiseTicketButtonXpath = "//android.widget.TextView[@text='RAISE TICKET']";

		if(driver.findElements(By.xpath(raiseTicketButtonXpath)).size()==0) {

			reportStep("As Expected There is no Raise Ticket button presence in Recent Click page  ", "PASS");

		}else {
			reportStep(" Fail - Raise Ticket Button should not be present for the Store for Which the Exit click is made   ", "Fail");

		}		

		return this;

	}


	public AddTicketPage clickEnabledRaiseTicket(String retailer) {

		reportStep("About to clickEnabledRaiseTicket button in recent clicks page as a signed user ", "INFO");

		scrollFromUpToDowninApp();
		scrollFromUpToDowninApp();

		String raiseTicketButtonXpath = "//android.widget.TextView[contains(@text,'"+retailer+"')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'4 days ago')]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.TextView[@text='Awaiting Tracking Details']/parent::android.view.ViewGroup/android.widget.TextView[@text='Pending']/parent::android.view.ViewGroup/android.widget.TextView[@text='Confirmed']/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='icon_myAct_step1']/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='icon_myAct_step2']/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='icon_myAct_step3']/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='txt_myAct_step1_progress']/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='txt_myAct_step2_progress']/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='txt_myAct_step3_progress']/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='RAISE TICKET']|//android.widget.TextView[contains(@text,'\"+retailer+\"')]/parent::android.view.View/android.widget.TextView[contains(@text,'4 days ago')]/parent::android.view.View/parent::android.view.View/android.widget.TextView[@text='Awaiting Tracking Details']/parent::android.view.View/android.widget.TextView[@text='Pending']/parent::android.view.View/android.widget.TextView[@text='Confirmed']/parent::android.view.View/android.widget.TextView[@content-desc='icon_myAct_step1']/parent::android.view.View/android.widget.TextView[@content-desc='icon_myAct_step2']/parent::android.view.View/android.widget.TextView[@content-desc='icon_myAct_step3']/parent::android.view.View/android.widget.TextView[@content-desc='txt_myAct_step1_progress']/parent::android.view.View/android.widget.TextView[@content-desc='txt_myAct_step2_progress']/parent::android.view.View/android.widget.TextView[@content-desc='txt_myAct_step3_progress']/parent::android.view.View/android.view.View/android.widget.TextView[@text='RAISE TICKET']";

		if(isElementLocatedByXpathPresent(raiseTicketButtonXpath)) {
			reportStep("Verified the raise ticket button in recent clicks page ", "PASS");

		}else {
			reportStep("Not able to verify the the raise ticket button in recent clicks page ", "FAIL");

		}	

		MobileElement raiseTicketButton = driver.findElement(By.xpath(raiseTicketButtonXpath));

		if(clickAfterWait(raiseTicketButton)) {
			reportStep("Verified the raise ticket button in recent clicks page is enabled ", "PASS");

		}else {
			reportStep("Not able to verify the raise ticket button in recent clicks page is enabled ", "FAIL");

		}

		return new AddTicketPage(driver, testInfo);

	}

	public MyEarningsPage clickEnabledSeeDetails(String retailer, String amount) {

		reportStep("About to clickEnabledSeeDetails button in recent clicks page as a signed user ", "INFO");

		String seeDetailsButtonXpath = "//android.widget.TextView[contains(@text,'"+retailer+"')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'4 days ago')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+amount+"')]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.TextView[@text='Awaiting Tracking Details']/parent::android.view.ViewGroup/android.widget.TextView[@text='Pending']/parent::android.view.ViewGroup/android.widget.TextView[@text='Confirmed']/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='icon_myAct_step1']/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='icon_myAct_step2']/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='icon_myAct_step3']/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='txt_myAct_step1_progress']/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='txt_myAct_step2_progress']/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='txt_myAct_step3_progress']/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='SEE DETAILS']|//android.widget.TextView[contains(@text,'"+retailer+"')]/parent::android.view.View/android.widget.TextView[contains(@text,'4 days ago')]/parent::android.view.View/android.widget.TextView[contains(@text,'"+amount+"')]/parent::android.view.View/parent::android.view.View/android.widget.TextView[@text='Awaiting Tracking Details']/parent::android.view.View/android.widget.TextView[@text='Pending']/parent::android.view.View/android.widget.TextView[@text='Confirmed']/parent::android.view.View/android.widget.TextView[@content-desc='icon_myAct_step1']/parent::android.view.View/android.widget.TextView[@content-desc='icon_myAct_step2']/parent::android.view.View/android.widget.TextView[@content-desc='icon_myAct_step3']/parent::android.view.View/android.widget.TextView[@content-desc='txt_myAct_step1_progress']/parent::android.view.View/android.widget.TextView[@content-desc='txt_myAct_step2_progress']/parent::android.view.View/android.widget.TextView[@content-desc='txt_myAct_step3_progress']/parent::android.view.View/android.view.View/android.widget.TextView[@text='SEE DETAILS']";

		if(isElementLocatedByXpathPresent(seeDetailsButtonXpath)) {
			reportStep("Verified the SeeDetails button in recent clicks page ", "PASS");

		}else {
			reportStep("Not able to verify the the SeeDetails button in recent clicks page ", "FAIL");

		}	

		MobileElement seeDetailsButton = driver.findElement(By.xpath(seeDetailsButtonXpath));

		if(clickAfterWait(seeDetailsButton)) {
			reportStep("Verified the SeeDetails button in recent clicks page is enabled ", "PASS");

		}else {
			reportStep("Not able to verify the SeeDetails button in recent clicks page is enabled ", "FAIL");

		}

		return new MyEarningsPage(driver, testInfo);

	}

	public HomePage clickBackButton() {

		reportStep("About to click on back button in RecentClicksPage", "INFO");

		//		if(click(backButtonClick)) {
		//
		//			reportStep("Successfully clicked on the back button MyEarningsPage", "PASS");
		//		}else {
		//
		//			reportStep("Failed to  click on the back button in MyEarningsPage", "FAIL");
		//		}

		driver.navigate().back();

		return new HomePage(driver, testInfo);

	}

	public TicketDetailsPage clickViewDetailsButton() {

		reportStep("About to validate the View Details button in Recent click Page", "INFO");

		if(click(viewDetailsButton)) {

			reportStep("Successfully click on the ReportStep ", "PASS");
		} else {

			reportStep("Failed to click on the Report Step ", "FAIL");
		}

		return new TicketDetailsPage(driver, testInfo);
	}

	public SearchPage clickOnSearchIcon() {

		reportStep("About to click on the Search Icon from the Home page ", "INFO");

		if(click(searchIcon)){

			reportStep("Sucessfully clicked on the Search icon ", "PASS");

		}else {

			reportStep("Failed to click on the Search icon", "FAIL");
		}

		return new SearchPage(driver, testInfo);
	}
	
}
