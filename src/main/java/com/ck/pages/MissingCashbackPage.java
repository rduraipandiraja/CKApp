package com.ck.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;
import com.ck.pages.admin.AdminCoreFunction;
import com.ck.pages.admin.AdminMissingCashback;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MissingCashbackPage extends WrapperMethods {

	//Constructor call
	public MissingCashbackPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the Missing Cashback page ", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(missingCashback));
			reportStep("Successfully landed on the Missing Cashback page ", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the Missing Cashback Page  ", "FAIL");
		}

	}

	//For Existing user :
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Missing CashBack']")
	MobileElement missingCashback ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='+ ADD TICKET']")
	MobileElement addTicketButton ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='If you did a transaction via CashKaro & did not get the qualifying Cashback, Don’t worry! Just raise a Missing Cashback ticket & our team will escalate your transaction to the retailer. Please note that retailers only accept Missing Cashback claims for the last 10 days.']")
	MobileElement missingCashbackPageInfo ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Order Details']")
	MobileElement orderDetailsText ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Information']")
	MobileElement informationText ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Status']")
	MobileElement statusText ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Info']")
	MobileElement infoText ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Cashback Store :2019.01.25.19.01.011536']/following-sibling::android.widget.TextView[@text='View']")
	MobileElement viewLink ; // it works depens on store name

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text = 'ADD A TICKET']")
	MobileElement addTicketButtonForNewUser ;

	@FindBy(how = How.XPATH, using = "//android.widget.ImageView") 
	MobileElement MissingCashbackBackButton ;

	//For New User :
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'have any Missing Tickets')]")
	MobileElement youDontHvMissingTickets ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text = 'Missing Cashback or Rewards?']")
	MobileElement missingCashbackRewards ;
	@FindBy(how = How.XPATH, using = "//android.widget.ImageView[@content-desc='img_missingCash_nocashback']")
	MobileElement missingCashbackImg ;


	public AddTicketPage clickOnAddTicketButonForNewUser() {

		reportStep("About to click on the Add a ticket button from the Missing cashback page ", "INFO");

		if (click(addTicketButtonForNewUser)) {

			reportStep("Successfully clicked on the Add A ticket button from Missing Cashback page for New User ", "PASS");

		}else {

			reportStep("Failed to click on the Add A Ticket button from the Missing Cashback page for New User ", "FAIL");
		}

		return new AddTicketPage(driver, testInfo);
	}

	public AddTicketPage clickOnAddticketButtonForExistingUser() {

		reportStep("About to click on the + Add ticket button at the Missing cashback page ", "INFO");

		if(click(addTicketButton)) {

			reportStep("Successfully clicked on the Add ticket button at the Missing cashback Page ", "PASS");

		}else {

			reportStep("Failed to click on the Add ticket button at the Missing cashback page for the Existing user ", "FAIL");

		}

		return new AddTicketPage(driver, testInfo);
	}

	public void validateMissingCashbackInfo() {

		reportStep("About to validate the  Missing cashback Info  ", "INFO");

		validateTheElementPresence(missingCashbackPageInfo);

	}

	public MissingCashbackPage validateTheTicketRaisedStoreName(String storeName) {

		reportStep("About to validate the Store Name Once after raising the ticket in Missing cashback page ", "INFO");

		MobileElement element = driver.findElementByXPath("//android.widget.TextView[@text='"+storeName+"']");

		validateTheElementPresence(element);

		return this;

	}

	public MissingCashbackPage validateTheTicketRaisedDate() {

		reportStep("About to validate the date  Once after raising the ticket in Missing cashback page ", "INFO");

		String today = Utilities.today_dd_MMM_yyyy_ISTZone();

		isElementLocatedByXpathPresent("//android.widget.TextView[@text='"+today+"']");

		MobileElement element = driver.findElementByXPath("//android.widget.TextView[@text='"+today+"']");

		validateTheElementPresence(element);

		return this;

	}

	public MissingCashbackPage validateTransactionAmountAtMissingCashback(String transactionAmount) {

		reportStep("About to validate the tranasaction  Once after raising the ticket in Missing cashback page ", "INFO");

		MobileElement element = driver.findElementByXPath("//android.widget.TextView[@text='Transaction Amount : ₹"+transactionAmount+"']");

		validateTheElementPresence(element);

		return this;

	}

	public MissingCashbackPage valdiateTheRaisedTicketStatus(String status) {

		reportStep("About to validate the ticket status  Once after raising the ticket in Missing cashback page ", "INFO");

		MobileElement element = driver.findElementByXPath("//android.widget.TextView[contains(@text,'"+status+"')]");

		validateTheElementPresence(element);

		return this;

	}

	public TicketDetailsPage clickOnViewLink(String storeName) {

		//String xpath = "//android.widget.TextView[@text='"+storeName+"']/following-sibling::android.widget.TextView[@text='View']";				

		String xpath = "//android.widget.TextView[@text='"+storeName+"']/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='View']";

		MobileElement element = driver.findElementByXPath(xpath);

		reportStep("About to click on the View link in the Missing cashbck ticket page ", "INFO");

		if (click(element)) {

			reportStep("Successfully clicked on the View link at the Missing cashback ticket page ", "PASS");

		}else {

			reportStep(" Failed to click on the view link popup link at the Missing cashback  page  ", "FAIL");
		}


		return new TicketDetailsPage(driver, testInfo);
	}

	public String addAdminReplyAndChangeTicketStatus(String exitClickID,String visibleTextForTicketStatus) {


		reportStep("About to add the Admin reply for the Missinng cashback ticket raised ", "INFO");

		//Admin actions
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);


		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);	
		AdminMissingCashback adminMissingCashback = new AdminMissingCashback(driver, testInfo);

		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnInteractiveMainMenu();
		adminFunctions.clickOnsubMenuUserTickets();
		adminMissingCashback.selectSearchbyExitClickIDFromDropDown();
		adminMissingCashback.enerKeyWordatAdminMissingCashbackPage(exitClickID);
		adminMissingCashback.clickOnSearchButton();
		String ticketID = adminMissingCashback.fetchTicketID(exitClickID);
		adminMissingCashback.clickOnEditButton(exitClickID);
		adminMissingCashback.enterAdminReply(getTestData(10, "AdminReply"));
		adminMissingCashback.selectTicketStatus(visibleTextForTicketStatus);
		adminMissingCashback.clickOnSubmitButton();

		return ticketID;

	}

	public SignedInProfilePage clickOnBackButton() {

		reportStep("About to click on the Missing cashback page back button ", "INFO");

		//				if(click(MissingCashbackBackButton)) {
		//
		//					reportStep("Successfully clicked on the JoinFree back button  ", "Pass");
		//
		//				}else {
		//
		//					reportStep("Failed to  click on the JoinFree back button ", "FAIL");
		//
		//				}

		driver.navigate().back();

		return new SignedInProfilePage(driver, testInfo);
	}

	public MissingCashbackPage validateStaticTextsInMissingCashbackPageForExistingUser() {

		reportStep("About to validate the Missing Cashback Page for the Existing User ", "INFO");

		validateTheElementPresence(missingCashbackPageInfo);
		validateTheElementPresence(orderDetailsText);
		validateTheElementPresence(statusText);
		validateTheElementPresence(infoText);
		validateTheElementPresence(informationText);

		return this;

	}

	public MissingCashbackPage validateStaticTextForNewUser() {

		reportStep("About to validate the Missing Cashback Page texts for the New user ", "INFO");

		validateTheElementPresence(youDontHvMissingTickets);
		validateTheElementPresence(missingCashbackRewards);
		validateTheElementPresence(missingCashbackImg);

		return this;

	}


}
