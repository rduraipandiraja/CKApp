package com.ck.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.Utilities;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AddTicketPage extends WrapperMethods {
	
	//Constructor call
	public AddTicketPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		 PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the Add Ticket  page ", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(addTicketPage));
			reportStep("Successfully landed on the Add ticket  page ", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the Add ticket Page  ", "FAIL");
		}

	}

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Add Ticket']")
	MobileElement addTicketPage ;
	
	@FindBy(how = How.XPATH, using = "(//android.widget.EditText)[1]")
	MobileElement dateOfTransactionField ;	
	
//	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please Select']")
//	MobileElement pleaseSelectRetailer ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@text='Please Select']")
	MobileElement pleaseSelectRetailer ;
	
	@FindBy(how = How.XPATH, using = "(//android.widget.CheckedTextView)[2]")
	MobileElement selectFirstReatilerOption ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.CheckedTextView[contains(@text,'Cashback Store :')]")
	List<MobileElement> multipleRetailerOptions;
	
	@FindBy(how = How.XPATH, using = "//android.widget.CheckedTextView[contains(@text,'Reward Store :')]")
	List<MobileElement> multipleRetailerOptions_RewardsStore;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_missingCashAdd_transactionDate']")
	MobileElement transactionDetailsField ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_missingCashAdd_transactionDate']")
	List<MobileElement> multipleTransactionDetails ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text = 'ID : ']")
	List<MobileElement> multipleTransactionDetails_ID ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text = 'Transaction Amount :']")
	List<MobileElement> multipleTransactionDetails_TransactionAmount ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_missingCashAdd_transactionId']")
	MobileElement transactionID ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_missingCashAdd_totalAmountPaid']")
	MobileElement totalAmountPaid ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_missingCashAdd_couponCode']")
	MobileElement couponCodeUsed ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_missingCashAdd_transactionDetails']")
	MobileElement transactionDetails ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='SUBMIT']")
	MobileElement submitButton ;

	@FindBy(how = How.XPATH, using = "//android.widget.Button[@text='OK']")
	MobileElement datePopupOK ;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView)[1]") 
	MobileElement backButton;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='I have read and accept all Terms & Conditions']") 
	MobileElement termsAndCondition;
	
	@FindBy(how = How.XPATH, using = "//com.android.internal.widget.ViewPager/android.view.View") 
	MobileElement datePopUpSwipe;
	
	
	String termsAndCondtionXpath =  "//android.widget.TextView[@text='I have read and accept all Terms & Conditions']";
	String termsAndCondtionPageTitle = "//android.widget.TextView[@text='Terms & Condition']";
	
	//Negative validation :
	

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please allow upto 72 hours for your cashback to track. If it does not track in your CashKaro account within 72 hours, you should raise a Missing Cashback Ticket. Our customer care team shall help resolve this for you at the earliest.']") 
	MobileElement LessThan72HoursError;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Oops! Unfortunately, during the date of your transaction we were not offering Cashback on the retailer that you have selected. Please email us at')]|//android.widget.TextView[contains(@text,'for any enquiries')] ") 
	MobileElement N18Error;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please Select Retailer']") 
	MobileElement pleaseSelectRetailer_Error;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please Select Transaction Details']") 
	MobileElement pleaseSelectTransactionDetails_Error;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Transaction ID']") 
	MobileElement transactionID_Error;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Total Amount Paid (Without Discount)']") 
	MobileElement totalAmountPaid_Error;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please Enter Transaction ID']") 
	MobileElement pleaseEnterTransactionID;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please Enter Valid Amount']") 
	MobileElement pleaseEnterValidAmount;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Transaction Details']") 
	MobileElement trasactionDetails_Error;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please Enter Transaction Details']") 
	MobileElement pleaseEnterTrasactionDetails_Error;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='The total amount paid must be between 1 and 999999999.']") 
	MobileElement totalAmountNegativeOrZero_Error;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='The transaction id may not be greater than 25 characters.']") 
	MobileElement transactionID_NotMoreThan_25Char;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Please select the Date']") 
	MobileElement pleaseSelectDate;
	
	

	public  String getTheDateForMissingCashbackContentDesc(int numberOfDaysToBackDate) {

		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd MMMM yyyy");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("UTC"));

		Calendar objCalender  = Calendar.getInstance();

		objCalender.add(Calendar.DATE, - numberOfDaysToBackDate);
		Date currentDate    = objCalender.getTime();

		String strNumber_Days_To_BackDated_Exit_Clicks  = dateFormatGmt.format(currentDate);

		System.out.println(strNumber_Days_To_BackDated_Exit_Clicks);

		return strNumber_Days_To_BackDated_Exit_Clicks;

	}

	public AddTicketPage clickOnDateOfTransactionField() {

		reportStep("About to click on the Date of Trnasaction filed ", "INFO");

		if(clickOnElementVisibility(dateOfTransactionField)) {

			reportStep("Successfully clicked on the dateOfTransaction field in the Missing cashback field  ", "PASS");
		}else {

			reportStep("Failed to click on the Date Of Transaction field in the Missing Cashback field  ", "FAIL");
		}
		
		
		if(Utilities.fetchTheCurrentDate() < 5 ) {
			
			reportStep("Todays date is less than 5  - Test autoamtion exit click date will not be found in this month it should be four days back on Previous month"
					+ " So Swipe to left", "INFO"); 
			
			swipeFromLeftToRight();
			
		}
		return this;
	}
	
	public AddTicketPage clickOnDateOfTransactionField_ExitClickOn5Days() {

		reportStep("About to click on the Date of Trnasaction filed ", "INFO");

		if(clickOnElementVisibility(dateOfTransactionField)) {

			reportStep("Successfully clicked on the dateOfTransaction field in the Missing cashback field  ", "PASS");
		}else {

			reportStep("Failed to click on the Date Of Transaction field in the Missing Cashback field  ", "FAIL");
		}
		
		
		if(Utilities.fetchTheCurrentDate() < 6 ) {
			
			reportStep("Todays date is less than 6  - Test autoamtion exit click date will not be found in this month it should be four days back on Previous month"
					+ " So Swipe to left", "INFO"); 
			
			swipeFromLeftToRight();
			
		}
		
		return this;
	}
		
	public AddTicketPage clickOnDateOfTransactionField_ExitClickOn2Days() {


		reportStep("About to click on the Date of Trnasaction filed ", "INFO");

		if(clickOnElementVisibility(dateOfTransactionField)) {

			reportStep("Successfully clicked on the dateOfTransaction field in the Missing cashback field  ", "PASS");
		}else {

			reportStep("Failed to click on the Date Of Transaction field in the Missing Cashback field  ", "FAIL");
		}


		if(Utilities.fetchTheCurrentDate() < 3 ) {

			reportStep("Todays date is less than 3  - Test autoamtion exit click date will not be found in this month it should be four days back on Previous month"
					+ " So Swipe to left", "INFO"); 

			swipeFromLeftToRight();

		}

		return this;

	}

	public AddTicketPage selectDate(int numberOfDaysToBackDate) {

		String datexpath = getTheDateForMissingCashbackContentDesc(numberOfDaysToBackDate);

		reportStep("Select Date  "+datexpath+" in the Date PopUp at Add Ticket page ", "PASS"); // Don't Change the report status to INFO or Fail - Intentionally kept PASS to get the SCreen shot
		
		MobileElement element = driver.findElementByXPath("//android.view.View[@content-desc='"+datexpath+"']");

		if(clickOnElementVisibility(element)){

			reportStep("Successfully clicked on the Date in the Date PopUp at Add Ticket page ", "PASS");

		}else{

			reportStep("Not able to click on the Date in the Date PopUp at Add Ticket page ", "FAIL");
		}

		return this;
	}

	public AddTicketPage selectRetailerOption(String storeName) {

		reportStep("About to select the First retailer option from the Option ", "INFO");
		
		String xpath = "//android.widget.CheckedTextView[@text='"+storeName+"']";

		isElementLocatedByXpathPresent(xpath);
		
		if(click(driver.findElementByXPath(xpath))) {

			reportStep("Successfully selected drop down value as  " + storeName , "PASS");

		}else {

			reportStep("Failed to select the  retailer option as " +storeName + " From the  drop down at Add Ticket Page", "FAIL");
		}

		return this;
	}
	
	public AddTicketPage selectRetailerOption(int index) {

		reportStep("About to select the  retailer option depends on the index passed ", "INFO");

		if(click_WaitFor25Sec(multipleRetailerOptions.get(index))) {

			reportStep("Successfully selected the retailer drop down option from the Retailer select drop down ", "PASS");

		}else {

			reportStep("Failed to select the "+ (index+ 1 )+" ST/ND  retailer option From the  drop down at Add Ticket Page", "FAIL");
		}

		return this;
	}

	public AddTicketPage clickOnPleaseSelectDropDown() {

		reportStep("About to select the Please Select drop down ", "INFO");

		if(clickOnElementVisibility(pleaseSelectRetailer)) {

			reportStep("Successfully selected on the Please Select Retailer drop down ", "PASS");

		}else {

			reportStep("Failed to select on the Please Select Retailer drop down ", "FAIL");
		}

		return this;
	}

	public AddTicketPage clickOnMissingCashbackDatePopUpOKButton() {
		
		reportStep("About to click OK button on date popup ", "INFO");

		if(click(datePopupOK)) {

			reportStep("Successfully clicked on the Date OK button  ", "INFO");

		}else {

			reportStep("Failed to click on the date OK button at Missing cashback popup", "FAIL");
		}

		return this;
		
		
	}
	
	public AddTicketPage clickOnTransactionDetails() {
		
		reportStep("About to select the First  Transaction details radio button ", "INFO");
		
		if (click_WaitFor25Sec(transactionDetailsField)) {
			
			reportStep("Successfully selected the Transaction details First radio button  ", "PASS");
			
		}else {
			
			reportStep("Failed to  select  the Transaction details First radio button  ", "FAIL");
		}
		
		return this;
	}
	
	public AddTicketPage clickOnTransactionDetails(int index) {
		
		reportStep("About to select transaction details block depends on index passed ", "INFO");
		
		if (click(multipleTransactionDetails.get(index))) {
			
			reportStep("Successfully selected the Transaction details depends on index passed  ", "PASS");
			
		}else {
			
			reportStep("Failed to  select  the Transaction details  " +  (index+1) + " th/nd transaction details block ", "FAIL");
		}
		
		return this;
	}

	public AddTicketPage enterTransactionId(String transactionID_Value) {
		
		reportStep("About to enter the Transaction ID at Add ticket page Details section ", "INFO");
		
		scrollFromDownToUpinApp_FourSecDuration();
		scrollFromDownToUpinApp_FourSecDuration();
		
		if (enterText(transactionID, transactionID_Value)) {
			
		reportStep("Successfully Enterd the Transaction ID as  "+ transactionID_Value, "PASS");
			
		scrollFromDownToUpinApp_FourSecDuration();
		scrollFromDownToUpinApp_FourSecDuration();
			
		}else {
			
		reportStep("Failed to enter the Transaction ID as " + transactionID_Value, "FAIL");
			
		}
		
		return this;
	}

	public AddTicketPage enterTransactionId(String transactionID_Value,String sampleFormat) {

		reportStep("About to enter the Transaction ID at Add ticket page Details section ", "INFO");

		scrollFromDownToUpinApp_FourSecDuration();
		scrollFromDownToUpinApp_FourSecDuration();

		if (enterText(transactionID, transactionID_Value)) {
			reportStep("Successfully Enterd the Transaction ID as  "+ transactionID_Value, "PASS");
			
			
			reportStep("About to validate the Tranasaction Sample Format " + sampleFormat , "Info");
			String sampleFormatXpath = "//android.widget.TextView[contains(@text,'"+sampleFormat+"')]";
			if(isElementLocatedByXpathPresent(sampleFormatXpath)) {
				reportStep("Successfully validated the Transacion SampleFormat in Missing Cashback page: " + sampleFormat, "Pass");
			} else {
				reportStep("Failed to Validate Transacion SampleFormat in Missing Cashback page As : " + sampleFormat, "Fail");
			}

			scrollFromDownToUpinApp_FourSecDuration();
			scrollFromDownToUpinApp_FourSecDuration();

		}else {

			reportStep("Failed to enter the Transaction ID as " + transactionID_Value, "FAIL");

		}

		return this;
	}

	public AddTicketPage enterTransactionAmountPaid(String totalAmount) {
		
		reportStep("About to enter the Transaction ID at Add ticket page Details section ", "INFO");
		
		if (enterText(totalAmountPaid, totalAmount)) {
			
			reportStep("Successfully Enterd the Total amount paid as  "+ totalAmount, "PASS");
			
		}else {
			
			reportStep("Failed to enter the Transaction ID as " + totalAmount, "FAIL");
			
		}
		
		return this;
	}

	public AddTicketPage enterCouponCodeUsed(String couponCode) {
		
	reportStep("About to enter the Coupon Code Used at Add ticket page Details section ", "INFO");
		
		if (enterText(couponCodeUsed, couponCode)) {
			
			reportStep("Successfully Enterd the Coupon Code as  "+ couponCode, "PASS");
			
		} else {
			
			reportStep("Failed to enter the Coupon Code as " + couponCode, "FAIL");
			
		}
		
		return this;
	}

	public AddTicketPage enterTransactionDetails(String transactionDetails_Value) {

		reportStep("About to enter the Transaction Details in the Add ticket page ", "INFO");
		
		if (enterText(transactionDetails, transactionDetails_Value)) {

			reportStep("Successfully Enterd the Transaction details  as  "+ transactionDetails, "PASS");

		}else {

			reportStep("Failed to enter the Transaction details as " + transactionDetails, "FAIL");

		}

		return this;
	}

	public MissingCashbackPage clickOnSubmitButton() {

		reportStep("About to click on the Submit button at the Add ticket page ", "INFO");

		if(click_WaitFor25Sec(submitButton)) {

			reportStep("Successfully clicked on the Submit button at the Add Ticket page ", "PASS");

		}else {

			reportStep("Failed to click on the Submit button at the Add Ticket page ", "FAIL");
		}

		return new MissingCashbackPage(driver, testInfo);

	}
	
	public AddTicketPage clickOnSubmitButtonForFailure() {

		reportStep("About to click on the Submit button at the Add ticket page ", "INFO");

		if(click(submitButton)) {

			reportStep("Successfully clicked on the Submit button at the Add Ticket page ", "PASS");

		}else {

			reportStep("Failed to click on the Submit button at the Add Ticket page ", "FAIL");
		}

		return new AddTicketPage(driver, testInfo);

	}

	public MyEarningsPage clickOnSubmitButton_ForMyEarningsPage() {

		reportStep("About to click on the Submit button at the Add ticket page ", "INFO");

		if(click(submitButton)) {

			reportStep("Successfully clicked on the Submit button at the Add Ticket page ", "PASS");

		}else {

			reportStep("Failed to click on the Submit button at the Add Ticket page ", "FAIL");
		}

		return new MyEarningsPage(driver, testInfo);

	}

	public RecentClicksPage clickOnSubmitButton_ForRecentClickPage() {

		reportStep("About to click on the Submit button at the Add ticket page ", "INFO");

		if(click_WaitFor25Sec(submitButton)) {

			reportStep("Successfully clicked on the Submit button at the Add Ticket page ", "PASS");

		}else {

			reportStep("Failed to click on the Submit button at the Add Ticket page ", "FAIL");
		}

		return new RecentClicksPage(driver, testInfo);

	}
	
	public MyEarningsPage clickBackButton() {

		reportStep("About to click on back button in Add ticket page", "INFO");

		driver.navigate().back();

		return new MyEarningsPage(driver, testInfo);

	}
	
	public MissingCashbackPage clickBackButton_ForMissingCashbackPage() {

		reportStep("About to click on back button in my earnings page", "INFO");

		driver.navigate().back();

		return new MissingCashbackPage(driver, testInfo);

	}

	public AddTicketPage validateSizeOfMultipleRetailerOption(int expected) {

		int actual = getListOfElementsSize(multipleRetailerOptions);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}
	
	public AddTicketPage validateSizeOfMultipleRetailerOption_RewardsStore(int expected) {

		int actual = getListOfElementsSize(multipleRetailerOptions_RewardsStore);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}

	public AddTicketPage validateSizeOfMultipleTransactionDetails_Date(int expected) {

		int actual = getListOfElementsSize(multipleTransactionDetails);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}

	public AddTicketPage validateSizeOfMultipleTransactionDetails_ID(int expected) {

		int actual = getListOfElementsSize(multipleTransactionDetails_ID);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}

	public AddTicketPage validateSizeOfMultipleTransactionDetails_TransactionAmount(int expected) {

		int actual = getListOfElementsSize(multipleTransactionDetails_TransactionAmount);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}

	public AddTicketPage validateTransactionDetailsInfoblocks(int expected,int numberOfDaysToBeBackDate) {

		reportStep("About to validate the Trasactional details blocks for multiple transactions ", "INFO");

		this.validateSizeOfMultipleTransactionDetails_Date(expected);
		this.validateSizeOfMultipleTransactionDetails_ID(expected);
		this.validateSizeOfMultipleTransactionDetails_TransactionAmount(expected);

		if(expected > 1) {
		
		if(getText(multipleTransactionDetails.get(0)).contains(Utilities.customBackDate_dd_MMMM_yyyy(numberOfDaysToBeBackDate)) && getText(multipleTransactionDetails.get(1)).contains(Utilities.customBackDate_dd_MMMM_yyyy(numberOfDaysToBeBackDate))) {

			reportStep("Transactinal details has the date current date (correct formate) in the Transactional details info block ", "PASS");
		}else {

			reportStep("Transactional details block has not printed with the correct date formate , so please  look into this  - Actual "
					+ "is ="+ getText(multipleTransactionDetails.get(0)) + "And Expected is = "+ Utilities.customBackDate_dd_MMMM_yyyy(numberOfDaysToBeBackDate) , "FAIL");
			
		}
		}else {
			
			if(getText(multipleTransactionDetails.get(0)).contains(Utilities.customBackDate_dd_MMMM_yyyy(numberOfDaysToBeBackDate))) {

				reportStep("Transactinal details has the date current date (correct formate) in the Transactional details info block ", "PASS");
			}else {

				reportStep("Transactional details block has not printed with the correct date formate , so please  look into this  - Actual "
						+ "is ="+ getText(multipleTransactionDetails.get(0)) , "FAIL");
				
			}
			
		}


		return this;

	}

	public AddTicketPage validateStatusAndTicketIDInTransactionalDetails(String ticketId,String ticketStatus) {

		reportStep("About to validate the Missing cashback status on Transactional Details Info block ", "INFO");

		String xpath = "//android.widget.TextView[@text ='Status : ("+ticketId+") "+ticketStatus+"']";
		
		reportStep("locator xpath is  : " + "//android.widget.TextView[@text ='Status : ("+ticketId+") "+ticketStatus+"']" , "INFO");
		
		MobileElement element = driver.findElementByXPath(xpath);
		
		validateTheElementPresence(element);

		return this;

	}

	//Missing Cashback Negative Validations :
	
	public void validteTheErrorMessageForTheDateOnWhichExitClickIsNotMade() {
		
		reportStep("About to validate the error message as Sorry, we dont see any "
				+ "clicks from your account on this date. Please select a valid date of transaction or you can reach us at xyz@pouringpounds.com", "INFO");
		
		String sorryText = getTestData(10, "SorryText");
		
		String xpath = "//android.widget.TextView[contains(@text,'"+sorryText+"')]";
		
		isElementLocatedByXpathPresent(xpath);
		
		MobileElement errorOnInvalidDateSelection = driver.findElement(By.xpath(xpath));
		
		validateTheElementPresence(errorOnInvalidDateSelection);
		
		
	}
	
	public AddTicketPage valiadateN18Error() {
		
		reportStep("About to validate error Message for the N18 Store submission ", "INFO");
		
		validateTheElementPresence(N18Error);
		
		return this;
		
		
	}
	
	public AddTicketPage validateLessThan72HourErrorMessage() {
		
		reportStep("About to validate error Message for less than 72 hours - Raising the Ticket ", "INFO");
		
		validateTheElementPresence(LessThan72HoursError);
		
		return this;
		
		
	}
	
	public AddTicketPage validatePleaseSelectDateError() {
		
		reportStep("About to validate Please select date Error Message - ", "INFO");
		
		validateTheElementPresence(pleaseSelectDate);
		
		return this;
		
	}
	
	public AddTicketPage validatePleaseSelectRetailer() {

		reportStep("About to validate Please Select Retailer Error Validation ", "INFO");
		
		validateTheElementPresence(pleaseSelectRetailer_Error);
		return this;
		
		

	}

	public AddTicketPage validatePleaseSelectTransactionDetails() {

		reportStep("About to validate Please Select Transaction Details Error Message ", "INFO");
		validateTheElementPresence(pleaseSelectTransactionDetails_Error);
		return this;

	}
	
	public AddTicketPage validatePleaseEnterTransactionID() {
		
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();

		reportStep("About to validate Please Enter Transaction Id Error Message ", "INFO");
		validateTheElementPresence(pleaseEnterTransactionID);
		validateTheElementPresence(transactionID);
		return this;

	}

	public AddTicketPage validatePleaseEnterValidAmount() {
		
		reportStep("About to Enter Please enter Valid Amount Error message ", "INFO");
		validateTheElementPresence(pleaseEnterValidAmount);
		validateTheElementPresence(totalAmountPaid_Error);
		return this;


	}

	public AddTicketPage validatePleaseEnterTransactionDetails() {
		
		reportStep("About to validate Please Enter Transaction Details Error Message ", "INFO");
		validateTheElementPresence(pleaseEnterTrasactionDetails_Error);
		validateTheElementPresence(trasactionDetails_Error);
		
		return this;


	}
	
	public TermsAndConditionPage clickOnTermsAndCondition() {
		
		if(Integer.parseInt(apiLevel) > 23) {
			
		scrollFromDownToUpinApp();
		scrollFromDownToUpinApp();
		
		clickMultipleXCoordinatesByXpath(termsAndCondtionXpath, 25, termsAndCondtionPageTitle);
		
		return new TermsAndConditionPage(driver, testInfo);
		
		}else {
		
		return null;
		}
		
	}

	public AddTicketPage validateTransactionDetails(String date4DaysBack, String exitClick, String orderAmount, String status) {
		
		String transactionDetailsXpath = "//android.widget.TextView[contains(@text,'"+date4DaysBack+"')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+exitClick+"')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+orderAmount+"')]/parent::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+status+"')]|//android.widget.TextView[contains(@text,'"+date4DaysBack+"')]/parent::android.view.View/android.widget.TextView[contains(@text,'"+exitClick+"')]/parent::android.view.View/android.widget.TextView[contains(@text,'"+orderAmount+"')]/parent::android.view.View/android.widget.TextView[contains(@text,'"+status+"')]";

		if(isElementLocatedByXpathPresent(transactionDetailsXpath)) {
			reportStep("Validate transaction details in add ticket page", "PASS");

		}else {
			reportStep("Not able to validate transaction details in add ticket page", "FAIL");

		}
		
		MobileElement transactionDetails = driver.findElement(By.xpath(transactionDetailsXpath));

		validateTheElementPresence(transactionDetails);
		
		return this;

	}

	public AddTicketPage validateDateOfTransactionField(String date) {

		reportStep("About to validate the Date of Transaction presence ", "INFO");

		String dateOfTransactionXpath = "//android.widget.TextView[@text='Date of Transaction']/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='Date of Query']/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[@text='"+date+"']|//android.widget.TextView[@text='Date of Transaction']/parent::android.view.View/android.view.View/android.widget.TextView[@text='Date of Query']/parent::android.view.View/android.view.View/android.widget.EditText[@text='"+date+"']";

		if(isElementLocatedByXpathPresent(dateOfTransactionXpath)) {
			reportStep("Verified the Date of Transaction presence in add ticket page ", "PASS");

		}else {
			reportStep("Not able to verify the Date of Transaction presence in add ticket page ", "FAIL");

		}	

		MobileElement dateOfTransaction = driver.findElement(By.xpath(dateOfTransactionXpath));
		
		validateTheElementPresence(dateOfTransaction);
		
		return this;

	}
	
	public AddTicketPage validateRetailerDetailsField(String retailer) {

		reportStep("About to validate the retailer details presence ", "INFO");

		String retailerDetailsXpath = "//android.widget.TextView[@text='Retailer Details']/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='Select Retailer']/parent::android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[@text='"+retailer+"']|//android.widget.TextView[@text='Retailer Details']/parent::android.view.View/android.view.View/android.widget.TextView[@text='Select Retailer']/parent::android.view.View/android.view.View/android.widget.EditText[@text='"+retailer+"']";

		if(isElementLocatedByXpathPresent(retailerDetailsXpath)) {
			reportStep("Verified the retailerDetails presence in add ticket page ", "PASS");

		}else {
			reportStep("Not able to verify the retailerDetails presence in add ticket page ", "FAIL");

		}	

		MobileElement retailerDetails = driver.findElement(By.xpath(retailerDetailsXpath));
		
		validateTheElementPresence(retailerDetails);
		
		return this;

	}

	public AddTicketPage validateTransactionDetailsDate(String date4DaysBack) {
		
		String transactionDetailsXpath = "//android.widget.TextView[contains(@text,'"+date4DaysBack+"')]";

		if(isElementLocatedByXpathPresent(transactionDetailsXpath)) {
			reportStep("Validate transaction details date in add ticket page", "INFO");

		}else {
			reportStep("Not able to validate transaction details date in add ticket page", "INFO");

		}
		
		MobileElement transactionDetails = driver.findElement(By.xpath(transactionDetailsXpath));

		validateTheElementPresence(transactionDetails);
		
		return this;

	}
	
	public RecentClicksPage clickBackButtonToRedirectToRecentClicks() {

		reportStep("About to click on back button in AddTicketPage", "INFO");

		driver.navigate().back();

		return new RecentClicksPage(driver, testInfo);
		
	}

}
