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

public class TicketDetailsPage extends WrapperMethods {
	
	//Constructor call
	public TicketDetailsPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the TicketDetails Page  ", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(ticketDetails));
			reportStep("Successfully landed on the TicketDetailsPage ", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on theTicketDetailsPage  ", "FAIL");
		}

	}

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Ticket Details']")
	MobileElement ticketDetails ;
	
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@text='Date of Query']/following-sibling::android.widget.TextView)[1]")
	MobileElement dateOfQuery ;
	
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@text='Merchant Name']/following-sibling::android.widget.TextView)[1]")
	MobileElement merchantnNme ;
	
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@text='Transaction Amount']/following-sibling::android.widget.TextView)[1]")
	MobileElement TransactionAmount ;
	
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@text='Transaction Reference']/following-sibling::android.widget.TextView)[1]")
	MobileElement TrasactionReference ;
	
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@text='Coupon Code Used']/following-sibling::android.widget.TextView)[1]")
	MobileElement CouponCodeUsed ;
	
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@text='Details']/following-sibling::android.widget.TextView)[1]")
	MobileElement details ;
	
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@text='Status']/following-sibling::android.widget.TextView)[1]")
	MobileElement status;
	
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@text='Date of Status Update']/following-sibling::android.widget.TextView)[1]")
	MobileElement dateOfStatusUpdate ;
	
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@text='User Reply']/following-sibling::android.widget.TextView)[1]")
	MobileElement userReply ;

	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@text='Customer services Reply']/following-sibling::android.widget.TextView)[1]")
	MobileElement customerServiceReply ;


	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='et_missingCashView_replyMessage']")
	MobileElement addYourComments ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='SUBMIT']")
	MobileElement submit ;

	
	public TicketDetailsPage validateTicketNumber(String ticketNumber) {
		
		reportStep("About to validate the Ticket number in Ticket Details page ", "INFO");
		
		String xpath = "//android.widget.TextView[@text='Ticket No : "+ticketNumber+"']" ;
		
		isElementLocatedByXpathPresent(xpath);
		
		MobileElement ticketNumber_Text = driver.findElementByXPath(xpath);
		
		validateTheElementPresence(ticketNumber_Text);
		
		return this;
	}
	
	public TicketDetailsPage validateDateOfQuery(String expected) {
		
		reportStep("About to validate the Date of Query ", "INFO");
		String actual =  getText(dateOfQuery);
		validateTheActualValueContainsExpectedValue(actual, expected);
		return this;
		
	}
	
	public TicketDetailsPage validateDateOfStatusUpdate(String expected) {
		
		reportStep("About to validate the Date of Query ", "INFO");
		String actual =  getText(dateOfQuery);
		validateTheActualValueContainsExpectedValue(actual, expected);
		return this;
		
	}
	
	public TicketDetailsPage validateMerchantname(String expected) {
		
		reportStep("About to validate the merchant name ", "INFO");
		String actual =  getText(merchantnNme);
		validateTheActualValueContainsExpectedValue(actual, expected);
		return this;
		
	}
	
	public TicketDetailsPage validateTransactionAmount(String expected) {
		
		reportStep("About to validate the transaction amount  ", "INFO");
		String actual =  getText(TransactionAmount);
		validateTheActualValueContainsExpectedValue(actual, expected);
		return this;
	}
	
	public TicketDetailsPage validateTranactionReference(String expected) {
		
		reportStep("About to validate the Date of Query ", "INFO");
		String actual =  getText(TrasactionReference);
		validateTheActualValueContainsExpectedValue(actual, expected);
		return this;
		
	}
	
	public TicketDetailsPage validateCouponCode(String expected) {
		
		reportStep("About to validate the coupon code ", "INFO");
		String actual =  getText(CouponCodeUsed);
		validateTheActualValueContainsExpectedValue(actual, expected);
		return this;
	}
	public TicketDetailsPage validateDetails(String expected) {
		
		reportStep("About to validate the Date of Query ", "INFO");
		String actual =  getText(details);
		validateTheActualValueContainsExpectedValue(actual, expected);
		return this;
		
	}
	
	public TicketDetailsPage validateStaus(String expected) {
		
		reportStep("About to validate the Date of Query ", "INFO");
		String actual =  getText(status);
		validateTheActualValueContainsExpectedValue(actual, expected);
		return this;
		
	}
	
	public TicketDetailsPage validteDateOfStatusUpdate(String expected) {
		
		reportStep("About to validate the Date of Query ", "INFO");
		String actual =  getText(dateOfStatusUpdate);
		validateTheActualValueContainsExpectedValue(actual, expected);
		return this;
	}
	
	public TicketDetailsPage validateUserReply(String expected) {
		
		reportStep("About to validate the Date of Query ", "INFO");
		String actual =  getText(userReply);
		validateTheActualValueContainsExpectedValue(actual, expected);
		return this;
		
	}
	
	public TicketDetailsPage validateCustomerServicesReply(String expected) {
		
		reportStep("About to validate the Date of Query ", "INFO");
		String actual =  getText(customerServiceReply);
		validateTheActualValueContainsExpectedValue(actual, expected);
		return this;
		
	}
	
	public TicketDetailsPage enterUserReply(String userReply) {
		
		reportStep("About enter the User Reply as : "+userReply + " into the text field ", "INFO");
		
		if (enterText(addYourComments, userReply)) {
			
			reportStep("Successfully added the User as " +userReply+ "  in Add Reply Field ", "PASS");
			
		}else {
			
			
			reportStep("Failed to enter the user reply as  " +userReply+ "  in Add Reply Field ", "FAIL");
			
		}
		
		return this;
	}
	
	public MissingCashbackPage clickOnSubmitButton() {
		
		reportStep("About to click on The Submit button at Ticket Details page ", "INFO");
		
		if (click(submit)) {
			
			reportStep("Successfully clicked on the Submit button  ", "PASS");
			
		}else {
			
			reportStep("Failed to click on the Submit button ", "FAIL");
			
			
		}
		
		return new MissingCashbackPage(driver, testInfo);
		
	}
}
