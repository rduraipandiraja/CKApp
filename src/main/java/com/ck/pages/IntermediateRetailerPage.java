package com.ck.pages;

import static io.appium.java_client.touch.offset.PointOption.point;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;

public class IntermediateRetailerPage extends WrapperMethods {

	//Constructor call
	public IntermediateRetailerPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		try {
			wait.until(ExpectedConditions.visibilityOf(intermediatePageURL));
			reportStep("Successfully landed on the Redirectional Intermediate  page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the Redirectional Intermediate page", "FAIL");

		}
	}

	//variable initialization
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'cashkaro.iamsavings.co.uk')]|//android.widget.TextView[contains(@text,'staging.cashkaro.com')]")
	MobileElement intermediatePageURL;
	@FindBy(how = How.ID, using = "com.cashkaro:id/ref_rate_icon")
	MobileElement bottomLinkRates;
	@FindBy(how = How.ID, using = "com.cashkaro:id/ref_coupon_icon")
	MobileElement bottomLinkCoupons;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Terms & Conditions']")
	MobileElement bottomLinkTermsAndCondition;
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView)[1]")
	MobileElement ratePrimaryCashbackValue;
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView)[2]")
	MobileElement ratePrimaryCashbackDetails;
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView)[3]")
	MobileElement rateSecondaryCashbackValue;
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView)[4]")
	MobileElement rateSecondaryCashbackDetails;
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView)[5]")
	MobileElement rateSecondSecondaryCashbackValue;
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView)[6]")
	MobileElement rateSecondSecondaryCashbackDetails;
	@FindBy(how = How.ID, using = "com.cashkaro:id/coupon_code")
	MobileElement couponsCode;
	@FindBy(how = How.ID, using = "com.cashkaro:id/coupon_title")
	MobileElement couponsCodeTitle;
	@FindBy(how = How.XPATH, using = "//android.widget.Button[@text='COPY CODE']")
	MobileElement couponsCopyCodeButton;
	@FindBy(how = How.ID, using = "com.cashkaro:id/coupon_expiry")
	MobileElement couponsExpiryTime;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Important Steps to ensure your Cashback Tracks']")
	MobileElement termsAndConditionStaticText;
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView)[2]")
	MobileElement termsAndConditionData;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'myntra.com')]|//android.widget.EditText[contains(@text,'myntra.com')]") //ID =com.android.chrome:id/url_bar
	MobileElement retailerRediretionalURL;
	@FindBy(how = How.XPATH, using = "//android.widget.Button[@text='ALLOW']|//android.widget.Button[@text='Allow']")
	MobileElement redirectionalPageAllowPopup;
	@FindBy(how = How.ID, using = "com.android.chrome:id/close_button")
	MobileElement retilerPageBackButton;


	public void validateRetailerIntermediateStoreDetails_dummy(String storeName) {

		MobileElement storeName_local = driver.findElementByXPath("//android.view.View[@text='"+storeName+"']");
		MobileElement CashKarologo = driver.findElementById("imgIntermediateCKLogo");
		MobileElement primaryCashbackValue = driver.findElementByXPath("//android.view.View[@text='1125.00%'");
		MobileElement activated = driver.findElementByXPath("//android.view.View[@text='Activated!'");
		MobileElement cashback = driver.findElementByXPath("//android.view.View[@text='Cashback'");

		validateTheElementPresence(storeName_local);
		validateTheElementPresence(CashKarologo);
		validateTheElementPresence(primaryCashbackValue);
		validateTheElementPresence(activated);
		validateTheElementPresence(cashback);


	}

	public void validateRetailerIntermediateStoreDetails(String storeName) {

		System.out.println("List by class name");

		List<MobileElement> list = driver.findElementsByClassName("");
		for(int i=0;i<=list.size();i++) {

			list.get(i);
		}

		System.out.println("list by class name is : "+list.size() );

		//		MobileElement storeName_local = null;
		//		MobileElement CashKarologo = null;
		//		MobileElement primaryCashbackValue = null;
		//		
		//		try {
		//		 storeName_local = driver.findElementByXPath("//android.view.View[@text='"+storeName+"']");
		//	}catch (Exception e) {
		//		System.out.println("Not able to locate the store name element 1");
		//	}
		//		
		//		try {
		//			 CashKarologo = driver.findElementById("imgIntermediateCKLogo");
		//		}catch (Exception e) {
		//			System.out.println("Not able to locate the cashkaro logo element 2");
		//		}
		//		
		//		try {
		//			primaryCashbackValue = driver.findElementByXPath("//android.view.View[@text='1125.00%'");
		//			
		//		}catch (Exception e) {
		//			System.out.println("Not able to locate the primaryCashbackValue 3");
		//		}
		//		
		//		try {
		//			primaryCashbackValue = driver.findElementByXPath("//android.view.View[@text='Activated!'");
		//			
		//		}catch (Exception e) {
		//			System.out.println("Not able to locate the activated ! text 4");
		//		}
		//		
		//		try {
		//			primaryCashbackValue = driver.findElementByXPath("//android.view.View[@text='Cashback'");
		//			
		//		}catch (Exception e) {
		//			System.out.println("Not able to locate the cashback text 5");
		//		}
		//		
		//		//MobileElement primaryCashbackValue = driver.findElementByXPath("//android.view.View[@text='1125.00%'");
		//		MobileElement activated = driver.findElementByXPath("//android.view.View[@text='Activated!'");
		//		MobileElement cashback = driver.findElementByXPath("//android.view.View[@text='Cashback'");
		//		
		//		validateTheElementPresence(storeName_local);
		//		validateTheElementPresence(CashKarologo);
		//		validateTheElementPresence(primaryCashbackValue);
		//		validateTheElementPresence(activated);
		//		validateTheElementPresence(cashback);
		//		


	}

	public IntermediateRetailerPage clickOnBottomRatesIcon() {

		reportStep("About click on the Rates icon at the bottom ", "INFO");

		if(click(bottomLinkRates)) {

			reportStep("Successfully clicked on the bottom  link Rate icon ", "PASS");

		}else {

			reportStep("Fail to click on the Bottom link Rate icon Rate", "FAIL");
		}

		return this;

	}

	public IntermediateRetailerPage clickOnBottomCouponsIcon() {

		reportStep("About click on the Coupons icon at the bottom ", "INFO");

		if(click(bottomLinkCoupons)) {

			reportStep("Successfully clicked on the bottom  link Coupons icon ", "PASS");

		}else {

			reportStep("Fail to click on the Bottom link Coupons icon Rate", "FAIL");
		}

		return this;

	}

	public IntermediateRetailerPage clickOnBottomTermsConditionIcon() {

		reportStep("About click on the TermsAndCondition icon at the bottom ", "INFO");

		if(click(bottomLinkTermsAndCondition)) {

			reportStep("Successfully clicked on the bottom  link TermsAndCondition icon ", "PASS");

		}else {

			reportStep("Fail to click on the Bottom link TermsAndCondition icon Rate", "FAIL");
		}

		return this;
	}

	public IntermediateRetailerPage validateBottomRateIconPrimaryCashbackPercentageValue(String storeName,String expected) {

		reportStep("About to validate the store "+storeName+" 's Normal Primary cashback rate in percentage ", "INFO");

		String actual = getText(ratePrimaryCashbackValue);

		if(actual.contains("%")) {

			actual = actual.replaceAll("%", "").trim();

			reportStep("Successfully validated the Percentage symbol in the cashback "
					+ "rate for the store : "+storeName , "PASS");
		}else {

			reportStep("Fail  to validate the Percentage symbol in the cashback "
					+ "rate for the store : "+storeName , "FAIL");

		}
		actual = Utilities.extractOnlyDigitFromString(actual);
		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}

	public IntermediateRetailerPage validateBottomRateIconPrimaryCashbackCurrencyValue(String storeName,String expected) {

		reportStep("About to validate the store "+storeName+" 's Normal Primary cashback rate in currency ", "INFO");

		String actual = getText(ratePrimaryCashbackValue);

		actual = Utilities.extractOnlyDigitFromString(actual);
		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}


	public IntermediateRetailerPage validateBottomRateIconSecondaryCashbackPercentageValue(String storeName,String expected) {

		reportStep("About to validate the store "+storeName+" 's Normal Secondary cashback rate in percentage  ", "INFO");

		String actual = getText(rateSecondaryCashbackValue);

		if(actual.contains("%")) {

			actual = actual.replaceAll("%", "").trim();

			reportStep("Successfully validated the Percentage symbol in the cashback "
					+ "rate for the store : "+storeName , "PASS");
		}else {

			reportStep("Fail  to validate the Percentage symbol in the cashback "
					+ "rate for the store : "+storeName , "FAIL");

		}
		actual = Utilities.extractOnlyDigitFromString(actual);
		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public IntermediateRetailerPage validateBottomRateIconSecondSecondaryCashbackPercentageValue(String storeName,String expected) {

		reportStep("About to validate the store "+storeName+" 's Normal Secondary cashback rate in percentage  ", "INFO");

		String actual = getText(rateSecondaryCashbackValue);

		if(actual.contains("%")) {

			actual = actual.replaceAll("%", "").trim();

			reportStep("Successfully validated the Percentage symbol in the cashback "
					+ "rate for the store : "+storeName , "PASS");
		}else {

			reportStep("Fail  to validate the Percentage symbol in the cashback "
					+ "rate for the store : "+storeName , "FAIL");

		}
		actual = Utilities.extractOnlyDigitFromString(actual);
		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}


	public IntermediateRetailerPage validateBottomRateIconSecondaryCashbackCurrencyValue(String storeName,String expected) {

		reportStep("About to validate the store "+storeName+" 's Normal Secondary cashback rate in Currency  ", "INFO");

		String actual = getText(rateSecondaryCashbackValue);

		actual = Utilities.extractOnlyDigitFromString(actual);
		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public IntermediateRetailerPage validateBottomRateIconPrimaryCashbackRate_Details(String storeName,String expected) {

		reportStep("About to validate the store "+storeName+" 's Normal Primary cashback rate details ", "INFO");

		String actual = getText(ratePrimaryCashbackDetails);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public IntermediateRetailerPage validateBottomRateIconSecondaryCashbackRate_Details(String storeName,String expected) {

		reportStep("About to validate the store "+storeName+" 's Normal Secondary cashback rate details ", "INFO");

		String actual = getText(rateSecondaryCashbackDetails);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public IntermediateRetailerPage validateBottomRateIconSecondSecondaryCashbackRate_Details(String storeName,String expected) {

		reportStep("About to validate the store "+storeName+" 's Normal Secondary cashback rate details ", "INFO");

		String actual = getText(rateSecondSecondaryCashbackDetails);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}


	public IntermediateRetailerPage validateCouponCodeValue(String expected) {

		reportStep("About to validate the Coupon Code value in the Bottom coupon section ", "INFO");

		String actual = getText(couponsCode);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}

	public IntermediateRetailerPage validateCouponTitle(String expected) {

		reportStep("About to validate the Coupon Code Title in the Bottom coupon section ", "INFO");

		String actual = getText(couponsCodeTitle);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}  

	public IntermediateRetailerPage validateVoucherExpiry(String expected) {

		reportStep("About to validate VoucherExpiry  ", "INFO");

		String actual = getText(couponsExpiryTime);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;

	}

	public IntermediateRetailerPage clickOnCopyCode() {

		reportStep("About to click on the  CouponCode  ", "INFO");

		couponsCode.click();

		return this;

	}

	public IntermediateRetailerPage validateTermsAndCondition() {

		reportStep("About to validate the terms and condition texts in the terms and condition icon  ", "INFO");

		validateTheElementPresence(termsAndConditionStaticText);

		return this;


	}


	public IntermediateRetailerPage validateTermsAndCondition(String termsAndConditions) {

		reportStep("About to validate the terms and condition texts in the terms and condition icon  ", "INFO");

		MobileElement termsAndCondition = driver.findElement(By.xpath("//android.widget.TextView[@text='"+termsAndConditions+"']"));

		validateTheElementPresence(termsAndCondition);

		return this;


	}

	public IntermediateRetailerPage tapOnApp() {

		reportStep("About to tap on the screen  to close the - info display", "INFO");
		try {
			Dimension size = driver.manage().window().getSize();
			int x0 = (int) (size.getWidth() * 0.2);
			int y0 = (int) (size.getHeight() * 0.2);
			MultiTouchAction touch = new MultiTouchAction(driver);
			touch.add(new TouchAction(driver).press(point(x0, y0)).release())
			.perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
		reportStep("Successfully tapped on the screen to close the Popup", "INFO");

		return this;
	}

	public IntermediateRetailerPage validateRetailerPage(String expected) {

		try {

			reportStep("About close the retailer Popup,Specially Myntra retailer  , If it comes ", "INFO");

			WebDriverWait wait = new WebDriverWait(driver, 40); // Note : Don't change the Wait seconds from 40 to below any values - scripts gettting failed due to less time.
			//If its necessary to change please do through Unit testing and change

			wait.until(ExpectedConditions.visibilityOf(redirectionalPageAllowPopup));

			if(redirectionalPageAllowPopup.isDisplayed()){

				redirectionalPageAllowPopup.click();

				reportStep("Myntra retailer page - Permisson notification allow popup has been clicked and handled","INFO");
			}

		}catch(Exception e) {

			reportStep("Even after waiting for 40 seconds the allow popup has not come in the Myntra page, So One more time clicking On Myntra ","INFO");

		}


		String actual = getText(retailerRediretionalURL);
		validateTheActualValueContainsExpectedValue(actual,expected);


		return this;

	}

	public IntermediateRetailerPage validateRetailerPageDontHandlePopup(String expected) {	

		String actual = getText(retailerRediretionalURL);
		validateTheActualValueContainsExpectedValue(expected, actual);


		return this;

	}

	public IntermediateRetailerPage validateRetailerPage(String expected, String retailerName) {


		try {

			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.visibilityOf(redirectionalPageAllowPopup));

			if(redirectionalPageAllowPopup.isDisplayed()){

				redirectionalPageAllowPopup.click();

				reportStep(""+retailerName+" retailer page - Permisson notification allow popup has been clicked and handled","INFO");
			}

		}catch(Exception e) {
			reportStep("Even after waiting for 40 seconds the allow popup has not come in the "+retailerName+" page","INFO");

		}

		String retailerRedirectionalURLXpath = "//android.widget.TextView[contains(@text,'"+retailerName+"')]|//android.widget.EditText[contains(@text,'"+retailerName+"')]";

		if(isElementLocatedByXpathPresent(retailerRedirectionalURLXpath)) {

			reportStep("Verified URL "+retailerName+"", "PASS");

		}else {
			reportStep("Not able to verify URL "+retailerName+"", "FAIL");

		}


		MobileElement retailerRediretionalURL = driver.findElement(By.xpath(retailerRedirectionalURLXpath));

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(retailerRediretionalURL));

		String actual = getText(retailerRediretionalURL);
		validateTheActualValueContainsExpectedValue(actual, expected);


		return this;

	}

	public IntermediateRetailerPage validateRetailerPageDontHandlePopup(String expected, String retailerName) {


		MobileElement retailerRediretionalURL = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+retailerName+"')]"));

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(retailerRediretionalURL));

		String actual = getText(retailerRediretionalURL);
		validateTheActualValueContainsExpectedValue(actual, expected);


		return this;

	}

	public StorePage clickOnIntermediateBackbutton() {

		if(clickAfterWait(retilerPageBackButton)){

			reportStep("Successfully clicked on the back button from the Retailer page", "PASS");
		}else {

			reportStep("Fail to click on the back button at the retialer page ", "FAIL");
		}

		return new StorePage(driver, testInfo);
	}

	public StoreCategoryPage clickOnIntermediateBackbuttonForStoreCat() {

		if(click(retilerPageBackButton)){

			reportStep("Successfully clicked on the back button from the Retailer page", "PASS");
		}else {

			reportStep("Fail to click on the back button at the retialer page ", "FAIL");
		}

		return new StoreCategoryPage(driver, testInfo);
	}

	public IntermediateRetailerPage waitForRedirectionalURL() {

		waitForElementPresence(retailerRediretionalURL);

		return this;

	}

	public IntermediateRetailerPage waitForRedirectionalURL(String retailerName) {

		System.out.println("retailerRediretionalURL: "+retailerName);

		String retailerRedirectionalURLXpath = "//android.widget.TextView[contains(@text,'"+retailerName+"')]|//android.widget.EditText[contains(@text,'"+retailerName+"')]";

		if(isElementLocatedByXpathPresent(retailerRedirectionalURLXpath)) {
			reportStep("Verified URL "+retailerName+"", "PASS");

		}else {
			reportStep("Not able to verify URL "+retailerName+"", "FAIL");

		}

		MobileElement retailerRedirectionalURL = driver.findElement(By.xpath(retailerRedirectionalURLXpath));

		waitForElementPresence(retailerRedirectionalURL);

		return this;

	}

	public IntermediateRetailerPage disableBottomCouponsIcon() {

		reportStep("About to verify bottom coupon icon is not visible", "INFO");

		String copyCodeButton = "//android.widget.Button[@text='COPY CODE']";

		validateTheElementAbsence(copyCodeButton);

		return this;

	}

	public IntermediateRetailerPage disableBottomRatesIcon() {

		reportStep("About to verify bottom rates icon is not visible", "INFO");

		String cashbackDetails = "com.cashkaro:id/cashback_desc";

		validateTheElementAbsenceUsingId(cashbackDetails);

		return this;

	}



}