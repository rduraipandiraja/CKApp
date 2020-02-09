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

public class HomePage extends WrapperMethods {

	//Constructor initialization
	public HomePage(AppiumDriver<MobileElement> driver, ExtentTest testInfo){

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		WebDriverWait wait = new WebDriverWait(driver, 30);

		reportStep("Waiting for the Home page", "INFO");


		try {

			wait.until(ExpectedConditions.visibilityOf(imageBanner));
			reportStep("Successfully landed on the Home page ", "PASS");

		}catch (Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the Home page", "FAIL");
		} 

	}

	//Variable creation
	@FindBy(how = How.ID, using = "com.android.packageinstaller:id/permission_allow_button") 
	MobileElement allowLinkInPermissionPopup;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Home']")
	MobileElement xpathRecommendedCashback;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView[@content-desc='img_banner_select'])[1]|//android.widget.TextView[@text='Home']")
	MobileElement imageBanner;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Profile']")
	MobileElement profileIcon;


	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Recent Clicks']")
	MobileElement recentClicksIcon;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'CASHBACK')]|//android.widget.TextView[contains(@text,'REWARDS')]")
	MobileElement storeCardButton;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView[@content-desc=\"img_storeCard_imageValue\"])[1]")
	MobileElement storeCardImage;

	@FindBy(how = How.XPATH, using = "(//android.widget.TextView)[1]")
	MobileElement HamburgerIcon;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Search']") 
	MobileElement searchIcon;

	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@content-desc='txt_recommendStoreCard_shortDescription'])[1]") 
	MobileElement storecardShortDesc; ///This xpath is being changed by developer - ask them to give correct xpath

	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@text='Home'])[2]") 
	MobileElement sliderMenuHomeIcon;

	String sliderMenuHomeIconXpath = "(//android.widget.TextView[@text='Home'])[2]";

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView)[1]") 
	MobileElement cashkaroLogo;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='App Exclusives']") 
	MobileElement sliderMenuAppExclusives;

	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@text='Recent Clicks'])[2]|//android.widget.TextView[@text='Recent Clicks']")
	MobileElement recentClickBottom ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"txt_storeCard_cshbackRatesTerms\"]")
	MobileElement cashbackOrRewardsRatesAndTerms ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='CASHBACK RATES']")
	MobileElement cashbackRatesPopupTitle ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='REWARDS RATES']")
	MobileElement rewardsRatesPopupTitle ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"icon_storeCashback_offer_close_icon\"]")
	MobileElement cashbackRewardsRateClosePopup ;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView[@content-desc=\"img_couponCard_image\"])[1]")
	MobileElement voucherCardImg;
	@FindBy(how = How.XPATH, using = "(//android.view.ViewGroup[@content-desc=\"btn_couponCard_seeDetails\"])[1]")
	MobileElement voucherCardBtn;

	//Method creation : 

	public ProfilePage clickOnProfileIcon() {

		reportStep("About to click on the Profile icon ", "INFO");

		if(click(profileIcon)) {

			reportStep("Successfully clicked on the Profile icon at the Home page", "PASS");
		}else {

			reportStep("Failed to  click on the Profile icon at the Home page", "FAIL");
		}

		return new ProfilePage(driver, testInfo);
	}

	public SignedInProfilePage clickOnProfileIconForSignedUser() {

		reportStep("About to click on the Profile icon for  Signed user ", "INFO");

		if(click(profileIcon)) {

			reportStep("Successfully clicked on the Profile icon at the Home page for  Signed user ", "PASS");
		}else {

			reportStep("Failed to  click on the Profile icon at the Home page for  Signed user", "FAIL");
		}

		return new SignedInProfilePage(driver, testInfo);
	}

	public SignInPage clickOnStoreCardButton() {

		reportStep("About to click on the Home page store card button", "INFO");

		scrollFromDownToUpinApp();
		if(click(storeCardButton)) {

			reportStep("Successfully clicked on the store card button from the Home page", "PASS");
		}else {

			reportStep("Failed to click on the store card button from the Home page", "FAIL");
		}

		return new SignInPage(driver, testInfo);
	}

	public HomePage clickOnStoreCardImage() {


		reportStep("About to click on the Home page store card button", "INFO");

		if(click(storeCardImage)) {

			reportStep("Successfully clicked on the store card Image from the Home page", "PASS");
		}else {

			reportStep("Failed to click on the store card Image from the Home page", "FAIL");
		}

		return this;
	}

	public SliderScreenPage clickOnHamburgerIcon() {

		String hamburgerSymbol = getTestData(7, "hamburgerSymbol");

		String hamburgerMenu = "//android.widget.TextView[contains(@text,'"+hamburgerSymbol+"')]";

		MobileElement HamburgerIcon = driver.findElement(By.xpath(hamburgerMenu));

		if(click(HamburgerIcon)){

			reportStep("Sucessfully clicked on the Hamburger icon ", "PASS");

		}else {

			reportStep("Failed to click on the Hamburger icon", "FAIL");
		}

		return new SliderScreenPage(driver, testInfo);
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

	public HomePage clickOnProfileIcon_enhanced() {

		reportStep("About to click on the Profile icon ", "INFO");

		if(click(profileIcon)) {

			reportStep("Successfully clicked on the Profile icon at the Home page", "PASS");
		}else {

			reportStep("Failed to  click on the Profile icon at the Home page", "FAIL");
		}

		return this;
	}

	public HomePage validateTheAbsenceOfSliderMenu() {

		reportStep("About to validate the absence of slider menu ", "INFO");

		validateTheElementAbsence(sliderMenuHomeIconXpath);

		return this;

	}

	public void validateThePresenceOfCashkaroLogo() {

		reportStep("About to validate the presence of cashkaro logo", "INFO");

		validateTheElementPresence(cashkaroLogo);

	}

	public RecentClicksPage clickOnRecentClickBottomIcon() {

		reportStep("About to click  the botton Recent click ICON ", "INFO");

		if(click(recentClicksIcon)) {

			reportStep("Successfully clicked on the Recent click bottom Icon", "PASS");
		}else {

			reportStep("Failed to click on the Recent Click bottom Icon", "FAIL");
		}

		return new RecentClicksPage(driver, testInfo);
	}

	public RecentClicksPage clickOnRecentClicks() {

		reportStep("About to click on Recent clicks in bottom ", "INFO");

		if(click(recentClickBottom)) {

			reportStep("Successfully clicked on Recent Clicks in bottom ", "PASS");

		}else {

			reportStep("Not Able to click on Recent Clicks in bottom ", "FAIL");
		}

		return new RecentClicksPage(driver, testInfo);

	}

	//Enhancements :

	public String clickCashbackRateOrRewardsRateAndTermsThenReturnTypeOfStore() {

		reportStep("About to click Cashback Or Rewards & Terms  ", "INFO");
		
		String strCashbackRate = cashbackOrRewardsRatesAndTerms.getText();
		if(click(cashbackOrRewardsRatesAndTerms)) {

			reportStep("Successfully clicked on Cashback Or Rewards Rates and Terms and Conditaion ", "PASS");
		} else {

			reportStep("Failed to  click on Cashback Or Rewards Rates and Terms and Conditaion ", "FAIL");
		}

		return strCashbackRate;
	}

	public VoucherDetailsPage clickOnVoucherCardImage() {

		reportStep("About to click on the voucher Card Image ", "INFO");

		if(click(voucherCardImg)) {

			reportStep("Successfully clicked on the Voucher Card Image ", "PASS");

		} else {

			reportStep("Failed to click on Voucher Card Image ", "FAIL");
		}

		return new VoucherDetailsPage(driver, testInfo);
	}

	public SignInPage clickOnVoucherCardButton() {

		reportStep("About to click on the voucher Card Button ", "INFO");

		if(click(voucherCardBtn)) {

			reportStep("Successfully clicked on the Voucher Card Button ", "PASS");

		} else {

			reportStep("Failed to click on Voucher Card Image ", "FAIL");
		}

		return new SignInPage(driver, testInfo);
	}





}
