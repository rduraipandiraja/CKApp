package com.ck.pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.app.ck.base.PropertyFile;
import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AdminPaymentSettingsPage extends WrapperMethods {

	public AdminPaymentSettingsPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);


	}

	@FindBy(how = How.XPATH, using = "//td[.='cashkaro.iamsavings.co.uk']/following::td/a[@data-original-title='Edit Payment Methods']") 
	MobileElement paymentMethodEditButton_beta;

	@FindBy(how = How.XPATH, using = "//td[.='cashkaro.iamsavings.co.uk']/following::td/a[@data-original-title='Edit Partner Details']") 
	MobileElement editPartnerDetailsButton_beta;

	@FindBy(how = How.XPATH, using = "//td[.='staging.cashkaro.com']/following::td/a[@data-original-title='Edit Partner Details']") 
	MobileElement editPartnerDetailsButton_staging;

	@FindBy(how = How.XPATH, using = "//td[.='staging.cashkaro.com']/following::td/a[@data-original-title='Edit Payment Methods']") 
	MobileElement paymentMethodEditButton_staging;

	@FindBy(how = How.ID, using = "btn_Submit") 
	MobileElement updatePaymentSettings;

	@FindBy(how = How.ID, using = "sign_up_bonus") 
	MobileElement signUpBonusTextField;

	@FindBy(how = How.ID, using = "referral_signup_bonus") 
	MobileElement referralBonusTextField;

	@FindBy(how = How.ID, using = "cashout_limit") 
	MobileElement cashoutLimitTextField;

	@FindBy(how = How.ID, using = "consecutive_payment_limit") 
	MobileElement consecutivePaymentLimitTextField;


	//click Payment Edit Button
	public void clickOnPartnerPaymentEditButton() {

		reportStep("About to click on the Partner Payment edit button ", "INFO");

		if (PropertyFile.ENVIRONMENT.equalsIgnoreCase("beta")) {

			if(clickAfterWait(paymentMethodEditButton_beta)) {

				reportStep("successfully clicked on Partner Payment edit button in Admin ", "PASS");

			} else {

				reportStep("Not able to click on Partner Payment edit button in Admin ", "FAIL");
			}

		} else if (PropertyFile.ENVIRONMENT.equalsIgnoreCase("staging")) {

			if(clickAfterWait(paymentMethodEditButton_staging)) {

				reportStep("successfully clicked on Partner Payment edit button in Admin ", "PASS");

			} else {

				reportStep("Not able to click on Partner Payment edit button in Admin ", "FAIL");
			}
		}
	}

	//Click edit Partner  button
	public void clickOnEditPartnerButton() {

		reportStep("About to click on Edit Partner button at the admin  ", "INFO");

		if (PropertyFile.ENVIRONMENT.equalsIgnoreCase("beta")) {

			if(clickAfterWait(editPartnerDetailsButton_beta)) {

				reportStep("Successfully clicked on Edit Partner button at the admin  ", "PASS");

			}else {

				reportStep("Not able to click on Edit Partner button at the admin ", "FAIL");
			}

		} else if (PropertyFile.ENVIRONMENT.equalsIgnoreCase("staging")) {

			if(clickAfterWait(editPartnerDetailsButton_staging)) {

				reportStep("successfully clicked on Edit Partner button in Admin ", "PASS");

			}else {

				reportStep("Not able to click on Edit Partner button in Admin ", "FAIL");
			}
		}
	}

	/* editPaymentMethod_Select_Status in Partner_Settings Menu */
	public void editPaymentMethod_Select_Status(int paymentID, String status) {

		reportStep("About to select the Payment method number "+ paymentID +  " Status as: "+ status , "INFO");

		if(select_Dropdown(driver, "//select[@id='status"+paymentID+"']", status)) {

			reportStep("Successfully selected payment method ID "+ paymentID + " 's status as :"+ status , "PASS");

		}else {

			reportStep("Failed to select the payment method ID  "+ paymentID + " 's  status as :"+ status , "FAIL");
		}

	}

	/* editPaymentMethod_Select_CashbackType in Partner_Settings Menu */
	public void dropDown_Select_CashbackType(int paymentID, String cashbackType) {

		reportStep("About to select cashback type - editPaymentMethod in Partner_Settings", "INFO");

		if(select_Dropdown(driver, "//select[@id='cashback_type_"+paymentID+"']", cashbackType)) {

			reportStep("Successfully selected payment method ID "+ paymentID + " 's Cashback type  as :"+ cashbackType , "PASS");
		}else {

			reportStep("Failed to  select  payment method ID "+ paymentID + " 's Cashback type  as :"+ cashbackType , "FAIL");
		}



	}

	public void clickOnUpdatePaymentSettingsButton() {

		reportStep("About to click on the Update Payment  Setting  button ", "INFO");

		if(clickAfterWait(updatePaymentSettings)) {

			reportStep("Successfully clicked on the Update Payment settigs button ", "PASS");

		}else {

			reportStep("Failed to  click on the Update Payment settigs button ", "FAIL");
		}

	}

	public void setSignUpBonusAsZero() {

		reportStep("About to set the payment signUp bounus as 0 ", "INFO");

		if(enterText(signUpBonusTextField, 0)) {

			reportStep("Successfully entered the SignUP Bonus as Zero ", "PASS");

		} else {

			reportStep("Not able to enter the SignUp bonus as Zero ", "FAIL");
		}

	}

	public void setReferralBonusAsZero() {

		reportStep("About to set the payment Referral bounus as 0 ", "INFO");

		if(enterText(referralBonusTextField, 0)) {

			reportStep("Successfully entered the Referral Bonus as Zero ", "PASS");

		} else {

			reportStep("Not able to enter the Referral bonus as Zero in Admin", "FAIL");
		}

	}

	public void setCashOutLimitAsTwoFifty() {

		reportStep("About to set the CashOut limit as Two Fifty  ", "INFO");

		if(enterText(cashoutLimitTextField, 250)) {

			reportStep("About to set the CashOut limit as 250 ", "PASS");

		} else {
			reportStep("Failed to set the CashOut limit as 250 in admin  ", "FAIL");

		}
	}

	public void setConsecutiveLimitAsTwoFifty() {

		reportStep("About to set the consecutive Payment limit as Two Fifty   ", "INFO");

		if(enterText(consecutivePaymentLimitTextField, 250)) {

			reportStep("About to set the consecutive Payment limit as 250 ", "PASS");

		} else {
			reportStep("Failed to set the consecutive Payment limit as 250 in admin  ", "FAIL");

		}
	}



}