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

public class VoucherDetailsPage extends WrapperMethods {


	//Constructor call
	public VoucherDetailsPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		reportStep("Waiting for the Voucher Detailed page ", "INFO");

		try {

			wait.until(ExpectedConditions.visibilityOf(voucherDetailsPageTitle));
			reportStep("Successfully landed on the Voucher Details  page ", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the Voucher Details  Page  ", "FAIL");
		}

	}


	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='ACTIVATE CASHBACK']|//android.widget.TextView[@text='ACTIVATE REWARDS']")
	MobileElement voucherDetailsMainCTA ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text  = 'Voucher Details']")
	MobileElement voucherDetailsPageTitle ;
	@FindBy(how = How.XPATH, using = "//android.widget.ImageView[@content-desc=\"img_voucherDetails_logo\"]")
	MobileElement voucherDetailedPageLogo ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='COUPON CODE']")
	MobileElement couponCodeText ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Copy Code']")
	MobileElement copyCode ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text=\"CLICK TO SEE MORE OFFERS\"]")
	MobileElement clickToSeeMoreOffersBtn ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='This is Test Automation entering app paragraph description for the voucher']")
	MobileElement voucherOffersDesc ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='OFFER DESCRIPTION']")
	MobileElement voucherOffersDescLabel ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='OFFER TERMS']")
	MobileElement voucherCardOffersTermsLabel ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Terms and conditions']")
	MobileElement voucherCardOffersTerms ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='ACTIVATE CASHBACK']")
	MobileElement voucherDetailsMainCTACashbackStore ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='ACTIVATE REWARDS']")
	MobileElement voucherDetailsMainCTARewardsStore ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='GRAB DEAL']")
	MobileElement voucherDetailsMainCTAN18Store ;
	
	//Method Creation :
	
	//clicking on VoucherDetails page Main CTA button
	//This function is for Unsigned users only 
	public SignInPage clickOnVoucherMainCTAForUnsignedUser() {

		reportStep("About to click the Voucher Details page Main CTA ", "INFO");

		if(click(voucherDetailsMainCTA)) {

			reportStep("Successfully clicked on voucher Details Page Main CTA Btn ", "PASS");

		} else {

			reportStep("Failed to click on voucher Details Page Main CTA Btn ", "FAIL");

		}

		return new SignInPage(driver, testInfo);
	}
	
	public IntermediateRetailerPage clickOnVoucherMainCTAForSignedUser() {

		reportStep("About to click the Voucher Details page Main CTA ", "INFO");

		if(click(voucherDetailsMainCTA)) {

			reportStep("Successfully clicked on voucher Details Page Main CTA Btn ", "PASS");

		} else {

			reportStep("Failed to click on voucher Details Page Main CTA Btn ", "FAIL");

		}

		return new IntermediateRetailerPage(driver, testInfo);
	}
	
	//Validate Voucher Logo
	public VoucherDetailsPage validateVoucherLogo() {
		
		validateTheElementPresence(voucherDetailedPageLogo, "\"Voucher details Page Logo\"");
		
		return this;
		
		
	}
	
	//Validate Coupon code  Presence
	public VoucherDetailsPage validateAndthenClickCouponCodeText() {
		
		validateTheElementPresence(couponCodeText, "\"Voucher details Page Coupon Code \"");
		click(couponCodeText);
		
		return this;
		
	}
	
	//Validate Copy Code Text Presence
	public VoucherDetailsPage validateCopyCode() {
		
		validateTheElementPresence(copyCode, "\"Voucher details Page Copy Code \"");
		
		return this;
		
	}
	
	//Click on - ClickToSeeMoreOffersButton
	public StorePage clickToSeeMoreOffersBtn() {
		
		reportStep("About to click See More Offers Button in vouchers details page ", "INFO");
		
		if(click(clickToSeeMoreOffersBtn)) {
			
			reportStep("Successfully clicked on See More Offers Button in Voucher Details Page ", "PASS");
		} else {
			
			reportStep("Failed to click on See More Offers Buttonn in Voucher Details Page ", "FAIL");
		}
		
		return new StorePage(driver, testInfo);
		
	}

	//validate short desc for the voucher detailed page with code
	public VoucherDetailsPage validatevoucherShortDescForVoucherWithCode(String storeName) {
		
		try {
		reportStep("About to Verify the voucher short desc for voucher With Code ", "INFO");
		String xpath = "//android.widget.TextView[@text='Voucher With code for :"+storeName+"']";
		MobileElement element = driver.findElement(By.xpath(xpath));
		validateTheElementPresence(element);
		}catch (Exception e) {
			
			reportStep("Failed to validate the voucher short desc , where voucher has got coupon code", "FAIL");
		}
		
		return this;
	}

	//validate short desc for the voucher detailed page without code
	public void validatevoucherShortDescForVoucherWithOutCode(String storeName) {
		
		try {
		reportStep("About to Verify the voucher short desc for voucher With Out Code ", "INFO");
		String xpath = "//android.widget.TextView[@text='Voucher With code for :"+storeName+"']";
		MobileElement element = driver.findElement(By.xpath(xpath));
		validateTheElementPresence(element);
		}catch (Exception e) {
			
			reportStep("Failed to validate the voucher short desc , where voucher has got coupon code", "FAIL");
		}
		
	}

	//validate Offers Desc
	public VoucherDetailsPage validateOffersDescription() {
		
		validateTheElementPresence(voucherOffersDescLabel, "Offers Desc Label in Voucher Details page ");
		validateTheElementPresence(voucherOffersDesc, "Offers Desc in Voucher Details page ");
		return this;
	}
	
	//ValidateOffersTerms
	public VoucherDetailsPage validateOffersTerms() {
		
		validateTheElementPresence(voucherCardOffersTerms, "Offers Terms in Voucher Details page ");		
		validateTheElementPresence(voucherCardOffersTermsLabel, "Offers Terms label in Voucher Details page ");
		return this;
	}

	public VoucherDetailsPage validateVoucherDetailsPageMainCTA(String storeType) {

		reportStep("About to validate the Voucher Main CTA button label with respect to StoreType ", "INFO");

		if(storeType.equalsIgnoreCase("cashback")) {

			validateTheElementPresence(voucherDetailsMainCTACashbackStore);

		}else if(storeType.equalsIgnoreCase("rewards")) {
			validateTheElementPresence(voucherDetailsMainCTARewardsStore);

		} else if(storeType.equalsIgnoreCase("n18")) {
			validateTheElementPresence(voucherDetailsMainCTAN18Store);

		}
		
		return this;
		
	}


}
