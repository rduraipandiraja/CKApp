package com.ck.pages;

import java.util.List;

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

public class CashbackRewardsRateAndTerms extends WrapperMethods {

	//Constructor call 
	public CashbackRewardsRateAndTerms(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the Cashback or Rewards Rate and Terms Popup", "INFO");

		try {
			wait.until(ExpectedConditions.visibilityOf(cashbackRewardsRateClosePopup));
			reportStep("Successfully landed on the Cashback or Rewards Rate and Terms Popup page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the  Cashback or Rewards Rate and Terms Popup page", "FAIL");
		}

	}

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='CASHBACK RATES']")
	MobileElement cashbackRatesPopupTitle ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='REWARDS RATES']")
	MobileElement rewardsRatesPopupTitle ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"icon_storeCashback_offer_close_icon\"]")
	MobileElement cashbackRewardsRateClosePopup ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Search Results')]")
	MobileElement searchResultsText ;
	

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_storeCashback_offer_primaryCashbackAmount']")
	MobileElement normalPrimaryCashbackValue ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_storeCashback_offer_secondaryCashbackAmount']")
	MobileElement normalSecondaryCashbackValue ;

	@FindBy(how = How.XPATH, using = "	//android.widget.TextView[@content-desc=\"txt_storeCashback_offer_primaryCashbackDetails\"]")
	MobileElement normalPrimaryCashbackRateDetails ;
	@FindBy(how = How.XPATH, using = "	//android.widget.TextView[@content-desc=\"txt_storeCashback_offer_secondaryCashbackDetails\"]")
	MobileElement normalSecondaryCashbackRateDetails ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'calender Primary')]/following-sibling::android.widget.TextView[contains(@text,'Expires')]") 
	List<MobileElement> primaryCalendarCashbckExpiryTag;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'calender Secondary')]/following-sibling::android.widget.TextView[contains(@text,'Expires')]") 
	List<MobileElement> secondaryCalendaryCashbackExpiryTag;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Terms and conditions']")
	MobileElement offerTerms ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='OFFER TERMS']")
	MobileElement offerTermsLabel ;
	
	//This is to validate the Cashback or Rewards Rate , based on the cashback or Rewards
	public CashbackRewardsRateAndTerms validateCashbackRateOrRewardsRateAndTerms(String storeType) {

		reportStep("About to validate the Cashback or Rewards Rate and Terms Popup  ", "INFO");

		if(storeType.contains("Cashback")) {
			if(validateTheElementPresence(cashbackRatesPopupTitle))
				reportStep("Successfully validated the Cashback Rate PopUp Title  ", "PASS");
			else
				reportStep("Failed to validate the Cashback Rate PopUp Title  ", "FAIL");

		} else if(storeType.contains("Rewards")) {
			if(validateTheElementPresence(rewardsRatesPopupTitle))
				reportStep("Successfully validated the Rewards Rate PopUp Title  ", "PASS");
			else
				reportStep("Failed to validate the Rewards Rate PopUp Title  ", "FAIL");
		}

		return this;

	}

	//This is to validate the CashbackRateAndTerms
	public CashbackRewardsRateAndTerms validateCashbackRateAndTerms() {

		reportStep("About to validate the Cashback Rate and Terms Popup  ", "INFO");

		if(validateTheElementPresence(cashbackRatesPopupTitle))
			reportStep("Successfully validated the Cashback Rate PopUp Title  ", "PASS");
		else
			reportStep("Failed to validate the Cashback Rate PopUp Title  ", "FAIL");

		return this;

	}

	//This is to validate the RewardsRateAndTerms
	public CashbackRewardsRateAndTerms validateRewardsRateAndTerms() {

		reportStep("About to validate the Rewards Rate and Terms Popup  ", "INFO");

		if(validateTheElementPresence(rewardsRatesPopupTitle))
			reportStep("Successfully validated the Rewards Rate PopUp Title  ", "PASS");
		else
			reportStep("Failed to validate the Rewards Rate PopUp Title  ", "FAIL");

		return this;

	}

	//Close the Terms & Codition Popup
	public void clickCloseBtnOnPopup() {

		reportStep("About to close the Cashback And Rewards Cashback Rate and Terms Popup", "INFO");

		if (click(cashbackRewardsRateClosePopup)) {
			
			reportStep("Successfully closed the Cashback And Rewards Popup ", "PASS");

		} else {

			reportStep("Failed to close the cashback and Rewards Popup", "FAIL");

		}

		validateTheElementPresence(searchResultsText);

	}
	
	//Close the Terms & Codition Popup
	public StoreCategoryPage clickCloseBtnOnPopupThenValidateStoreCatPage() {

		reportStep("About to close the Cashback And Rewards Cashback Rate and Terms Popup", "INFO");

		if (click(cashbackRewardsRateClosePopup)) {
			
			reportStep("Successfully closed the Cashback And Rewards Popup ", "PASS");

		} else {

			reportStep("Failed to close the cashback and Rewards Popup", "FAIL");

		}

		return new StoreCategoryPage(driver, testInfo);

	}

	public CashbackRewardsRateAndTerms validateNormalPrimaryCashbackRateInPercentage(String storeName,String expected) {

		reportStep("About to validate the store "+storeName+" 's Normal Primary cashback rate in percentage ", "INFO");

		String actual = getText(normalPrimaryCashbackValue);

		if(actual.contains("%")) {

			actual = actual.replaceAll("%", "").trim();

			reportStep("Successfully validated the Percentage symbol in the cashback "
					+ "rate for the store : "+storeName , "PASS");
		}else {

			reportStep("Fail  to validate the Percentage symbol in the cashback "
					+ "rate for the store : "+storeName , "FAIL");

		}

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public CashbackRewardsRateAndTerms validateNormalSecondaryCashbackRateInPercentage(String storeName,String expected, int index) {

		reportStep("About to validate the store "+storeName+" 's Normal Secondary cashback rate in percentage  ", "INFO");
		
		String xpath = "(//android.widget.TextView[@content-desc='txt_storeCashback_offer_secondaryCashbackAmount'])["+index+"]";
		
		scrollFromDownToUp_StorePage();

		String actual = getTextByXpath(xpath);

		if(actual.contains("%")) {

			actual = actual.replaceAll("%", "").trim();

			reportStep("Successfully validated the Percentage symbol in the cashback "
					+ "rate for the store : "+storeName , "PASS");
		}else {

			reportStep("Fail  to validate the Percentage symbol in the cashback "
					+ "rate for the store : "+storeName , "FAIL");

		}

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public CashbackRewardsRateAndTerms validateNormalPrimaryCashbackRateInRupees(String storeName,String expected) {

		reportStep("About to validate the store "+storeName+" 's Normal Primary cashback rate in percentage ", "INFO");

		String actual = getText(normalPrimaryCashbackValue);

		if(actual.contains(testdata.get(11).get("Rupee"))) {

			actual = actual.replaceAll(testdata.get(11).get("Rupee"), "").trim();

			reportStep("Successfully validated the Rupee symbol in the cashback "
					+ "rate for the store : "+storeName , "PASS");
		}else {

			reportStep("Fail  to validate the Rupee symbol in the cashback "
					+ "rate for the store : "+storeName , "FAIL");

		}

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public CashbackRewardsRateAndTerms validateNormalSecondaryCashbackRateInRupees(String storeName,String expected, int index) {

		reportStep("About to validate the store "+storeName+" 's Normal Secondary cashback rate in percentage  ", "INFO");
		
		String xpath = "(//android.widget.TextView[@content-desc='txt_storeCashback_offer_secondaryCashbackAmount'])["+index+"]";

		scrollFromDownToUp_StorePage();

		String actual = getTextByXpath(xpath);

		if(actual.contains(testdata.get(11).get("Rupee"))) {

			actual = actual.replaceAll(testdata.get(11).get("Rupee"), "").trim();

			reportStep("Successfully validated the Rupee symbol in the cashback "
					+ "rate for the store : "+storeName , "PASS");
		}else {

			reportStep("Fail  to validate the Rupee symbol in the cashback "
					+ "rate for the store : "+storeName , "FAIL");

		}

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public CashbackRewardsRateAndTerms validateNormalPrimaryCashbackRate_Details(String storeName,String expected) {

		reportStep("About to validate the store "+storeName+" 's Normal Primary cashback rate details ", "INFO");

		String actual = getText(normalPrimaryCashbackRateDetails);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public CashbackRewardsRateAndTerms validateNormalSecondaryCashbackRate_Details(String storeName,String expected, int index) {

		reportStep("About to validate the store "+storeName+" 's Normal Secondary cashback rate details ", "INFO");

		String xpath = "(//android.widget.TextView[@content-desc='txt_storeCashback_offer_secondaryCashbackDetails'])["+index+"]";

		String actual = getTextByXpath(xpath);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;
	}

	public CashbackRewardsRateAndTerms validateThePrimaryCashbackExpiryTag(int index,String expected,String storeName) {

		reportStep("About to validate the Primary Cashback Expiry Tag for the Store " + storeName , "INFO");

		String actual = getText(primaryCalendarCashbckExpiryTag.get(index));

		validateTheActualValueContainsExpectedValue(actual, expected);

		return this;

	}

	public CashbackRewardsRateAndTerms validateTheSecondaryCashbackExpiryTag(int index,String expected,String storeName) {


		// This is temporarily commented.

		reportStep("About to validate the Secondary Cashback Expiry Tag for the Store " + storeName , "INFO");

		String actual =getText(secondaryCalendaryCashbackExpiryTag.get(index));
		validateTheActualValueContainsExpectedValue(actual, expected);

		return this;


	}

	public CashbackRewardsRateAndTerms validateOfferTerms() {

		reportStep("About to validate the OFFERS TERMS  ", "INFO");
		validateTheElementPresence(offerTermsLabel);
		validateTheElementPresence(offerTerms);
		return this;
	}

	public CashbackRewardsRateAndTerms validateOfferTermsLabel() {

		reportStep("About to validate the OFFERS TERMS Label  ", "INFO");
		validateTheElementPresence(offerTermsLabel);
		return this;
	}


}
