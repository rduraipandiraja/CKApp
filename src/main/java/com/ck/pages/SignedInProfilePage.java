package com.ck.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.Base;
import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignedInProfilePage extends WrapperMethods {

	//Constructor call
	public SignedInProfilePage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the SignedIn ProfilePage", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(accountSettings));
			reportStep("Successfully landed on the  SignedIn ProfilePage page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the SignedIn ProfilePage page", "FAIL");
		}

	}

	//Variable declaration
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='JOIN FREE']") 
	MobileElement joinFreeButton;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='SIGN IN']") 
	MobileElement signInButton;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Account Settings']") 
	MobileElement accountSettings;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Logout']") 
	MobileElement logout;
	
	@FindBy(how = How.XPATH, using = "//android.widget.Button[@text='OK']") 
	MobileElement logOutOK;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='icon_profileMenu_withLogin_myReferral']") 
	MobileElement referralNetwork;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='My Earnings']")
	MobileElement myEarnings ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Missing Cashback']")
	MobileElement missingCashback ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Payments']")
	MobileElement payment ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Refer And Earn']")
	MobileElement referAndEarn ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Payment threshold not reached')]") 
	MobileElement paymentThresholdNotReached ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.Button[@text='OK']") 
	MobileElement paymentThresholdNotReachedAlertPopupOKButton ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Alert']") 
	MobileElement paymentThresholdNotReachedAlertText ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='icon_profileMenu_withLogin_goBackHome']") 
	MobileElement backButtonClick;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Search']") 
	MobileElement searchIcon;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text  = 'Account Settings']")
	MobileElement AccountSettings ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Rate Us']")
	MobileElement rateUs ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='How CashKaro Works']")
	MobileElement howCKWorks ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Call Us']")
	MobileElement callUs ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Contact Us']") 
	MobileElement contactUsLink;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Profile']")
	MobileElement profileIcon;


	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Recent Clicks']")
	MobileElement recentClicks ;
	
	//Method reusability
	public SignInPage clickOnSignInButton() {

		reportStep("About to login from the profile page", "INFO");

		if(click(signInButton)) {

			reportStep("Successfully clicked on the SignIN button at the profile page","PASS");

		}else {

			reportStep("Failed to click on the SignIN button at the profile page","FAIL");
		}

		return new SignInPage(driver, testInfo);
	}

	public JoinFreePage clickOnJoinFreeButton() {

		reportStep("About to SignUp from the Profile page", "INFO");

		if(click(joinFreeButton)) {

			reportStep("Successfully clicked on the JoinFree button at the profile page","PASS");

		}else {

			reportStep("Failed to click on the JoinFree button at the profile page","FAIL");
		}

		return new JoinFreePage(driver, testInfo);
	}

	public HomePage clickOnLogout() {

		reportStep("About to logout the user ", "INFO");
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
				
		if(click(logout)) {

			reportStep("Sucessfully clicked on the Logout button ", "PASS");

		}else {

			reportStep("Not able to click on the Logout button ", "FAIL");
		}

		if(click(logOutOK)) {

			reportStep("Sucessfully clicked on the Logout OK button ", "PASS");

		}else {

			reportStep("Not able to click on the Logout OK button ", "FAIL");
		}
		return new HomePage(driver, testInfo);
		
	}

	public StorePage clickOnLogout_FromStorePage() {

		reportStep("About to logout the user ", "INFO");
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
				
		if(click(logout)) {

			reportStep("Sucessfully clicked on the Logout button ", "PASS");

		}else {

			reportStep("Not able to click on the Logout button ", "FAIL");
		}

		if(click(logOutOK)) {

			reportStep("Sucessfully clicked on the Logout OK button ", "PASS");

		}else {

			reportStep("Not able to click on the Logout OK button ", "FAIL");
		}
		return new StorePage(driver, testInfo);
		
	}

	public ReferralNetworkPage clickOnReferralNetwork() {
		
		reportStep("About to click on the Referral Network on the Profile Page", "INFO");
		
		if(click(referralNetwork)) {
			
			
			reportStep("Successfully clicked on the Referral Netowork", "PASS");
		}else {
			
			reportStep("Failed to click on the Referral Network ", "FAIL");
		}
		
		return new ReferralNetworkPage(driver, testInfo);
		
	}

	public MyEarningsPage clickOnMyEarnings() {

		reportStep("About to click on the MyEarnings link from the Profile Page ", "INFO");

		if(click(myEarnings)) {

			reportStep("Successfully clicked on the MyEarnings from the profile slider ", "PASS");

		}else {

			reportStep("Fail to click on the MyEarnings icon from the Profile slider ", "FAIL");
		}

		return new MyEarningsPage(driver, testInfo);
	}
	
	
	public RecentClicksPage clickRecentClicks() {

		reportStep("About to click on Recent Clicks link from the Profile Page ", "INFO");

		if(click(recentClicks)) {

			reportStep("Successfully clicked Recent Clicks link from the Profile Page ", "PASS");

		}else {

			reportStep("Fail to click on Recent Clicks link from the Profile Page", "FAIL");
		}

		return new RecentClicksPage(driver, testInfo);
	}
	
	public MissingCashbackPage clickOnMissingCashback() {

		reportStep("About to click on the Missing Cashback link from the Profile Page ", "INFO");

		if(click(missingCashback)) {

			reportStep("Successfully clicked on the Missing Cashback from the profile slider ", "PASS");

		}else {

			reportStep("Fail to click on the Missing Cashback icon from the Profile slider ", "FAIL");
		}

		return new MissingCashbackPage(driver, testInfo);
	}

	public PaymentPage clickOnPayments() {

		reportStep("About to click on the Payment link from the Profile Page ", "INFO");

		if(click(payment)) {

			reportStep("Successfully clicked on the Payment from the profile slider ", "PASS");

		}else {

			reportStep("Fail to click on the MyEarnings icon from the Profile slider ", "FAIL");
		}

		return new PaymentPage(driver, testInfo);
	}
	
	public ReferAndEarn clickOnreferAndEarn() {

		reportStep("About to click on the Refer and Earn Link  ", "INFO");

		if(click(referAndEarn)) {

			reportStep("Successfully clicked on the Refer and Earn Link  ", "PASS");

		}else {

			reportStep("Fail to click on the Refer and Earn Link  ", "FAIL");
		}

		return new ReferAndEarn(driver, testInfo);
	}

	public MyEarningsPage clickOnPaymentsForThresholdNotReached() {

		reportStep("About to click on the Payment link from the Profile Page for threshold not reached ", "INFO");

		if(click(payment)) {

			reportStep("Successfully clicked on the Payment from the profile slider ", "PASS");

		}else {

			reportStep("Fail to click on the MyEarnings icon from the Profile slider ", "FAIL");
		}

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

	public HomePage clickBackButton() {

		reportStep("About to click on back button in SignedInProfilePage", "INFO");

//		if(click(backButtonClick)) {
//
//			reportStep("Successfully clicked on the back button SignedInProfilePage", "PASS");
//		}else {
//
//			reportStep("Failed to  click on the back button in SignedInProfilePage", "FAIL");
//		}
		
		driver.navigate().back();

		return new HomePage(driver, testInfo);

	}

	public SearchPage clickOnSearchIcon() {
		
		reportStep("About to click on the Search Icon from the Profile page ", "INFO");

		if(click(searchIcon)){

			reportStep("Sucessfully clicked on the Search icon ", "PASS");

		}else {

			reportStep("Failed to click on the Search icon", "FAIL");
		}

		return new SearchPage(driver, testInfo);
	}

	public AccountSettingsPage clickOnAccountSettings() {
		
		reportStep("About to click on the Account settings from the Profile menu  ", "INFO");
		
		if (click(accountSettings)) {
			
			reportStep("Successfully clicked on the Account Setting Profile page menu Option ", "PASS");
		}else {
			
			reportStep("Failed to click on the Profile Page Account settings Profile Menu Option ", "FAIL");
		}
		
		return new AccountSettingsPage(driver, testInfo);
	}

	public SignedInProfilePage validateTheUserNameChangesInProfilePage(String userFullName) {
		
		reportStep("About to valdiate the User full name in Signed In Profile page, once after changing the User Full Name  ", "INFO");
		
		String xpath =  "//*[@text='Hello, "+userFullName+"']";
		
		if(isElementLocatedByXpathPresent(xpath)) {
			
			reportStep("Successfully validated the User Full name in Profile page,  once after changing the user full name in Account settings", "PASS");
		}else {
			
			reportStep("Failed to validate  the User Full name in Profile page,  once after changing the user full name in Account settings", "FAIL");
		}
		
		return this;
		
	}
	
	public TestimonialsPage clickonRateUs() {
		
		reportStep("About to click on Rate us link from the Profile menu ", "INFO");
		scrollFromDownToUpinApp();
		if(click(rateUs)) {
			
			reportStep("Successfully clicked on the Rate us link from the profile menu ", "PASS");
			
		}else {
			
			reportStep("Failed - Not able to click on the Rate Us Profile menu ", "FAIL");
		}
		
		return new TestimonialsPage(driver, testInfo);
	}
	
	public void clickonCallUs() {
		
		reportStep("About to click on Call us link from the Profile menu ", "INFO");
		
		scrollFromDownToUpinApp();
		
		if(click(callUs)) {
			
			reportStep("Successfully clicked on the Call us link from the profile menu ", "PASS");
			
		}else {
			
			reportStep("Failed - Not able to click Call Us from the Profile menu", "FAIL");
		}
		
		
	}
	
	public HowItWorks clickOnHowCashKaroWorks() {
		
		reportStep("About to click How CashKaro Works link ", "INFO");
		
		if(click(howCKWorks)) {
			
			reportStep("Successfully clicked How CashKaro Works ", "PASS");
			
		}else {
			
			reportStep("Failed - Not able to click on the How Cashkaro Works ", "FAIL");
		}
		
		return new HowItWorks(driver, testInfo);
	}

	public AskAQuestionPage clickContactUsLink() {

		reportStep("About to click contact us link in profile page", "INFO");
		
		if(click(contactUsLink)) {
			
			reportStep("Sucessfully clicked on the contact us link in profile page", "PASS");
			
		}else {
			
			reportStep("Not able to click on the contact us link in profile page", "FAIL");
		}

		return new AskAQuestionPage(driver, testInfo);
		
	
		
	}

	public ProfilePage clickOnProfileIcon() {

		reportStep("About to click on the Profile icon ", "INFO");

		if(click(profileIcon)) {

			reportStep("Successfully clicked on the Profile icon at the Home page", "PASS");
		}else {

			reportStep("Failed to  click on the Profile icon at the Home page", "FAIL");
		}

		return new ProfilePage(driver, testInfo);
	}


	
}
