package com.ck.test;

import static io.appium.java_client.touch.offset.PointOption.point;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.app.ck.base.Base;
import com.app.ck.base.AppDynamicVariables;
import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;
import com.ck.pages.SeeVideoToEarnCashbackPage;
import com.ck.pages.HomePage;
import com.ck.pages.JoinFreePage;
import com.ck.pages.OTPPage;
import com.ck.pages.StorePage;
//import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.touch.WaitOptions;


public class CKTestLoginValidation extends WrapperMethods {

	////@Test
	public void aaPreRequsiteFacebookLogin() {
		
		String OtpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
				clickOnGoToHomePage().
				clickOnProfileIcon().
				clickOnJoinFreeButton().
				clickOnJoinWithFacebook().
				enterFBUserEmail().
				enterFBPassword().
				clickOnFBLoginButton().
				clickOnContinueForNewUser().
				enterMobileNumberAndGetTheOTP();

		//creating the new object for the OTP page

		OTPPage  objOtpPage = new OTPPage(driver, testInfo);
		
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the Clickon verify OTP , it should navigate to the Home page
		new HomePage(driver, testInfo);


	}
	
	@Test
	public void validGetStartedPageLogin_TC001() {

		reportStep("TC001 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnLoginJoinButton().
		enterUserName().
		enterPassword().
		clickSignInButtonForSuccess();

		reportStep("TC001 is completed", "INFO");

	}

	@Test
	public void validatingTheErrorMessageOnUserNameField_And_Password_TC002() {

		/*testInfo.log(LogStatus.INFO, "Validating the error message on username and password -");*/

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnLoginJoinButton().
		clickOnSignInWithEmailForFailure().
		validateUserNameFieldErrorMessage().
		validatePasswordFieldErrorMessage().
		clickOnBackButton_SecondTimeNavigationToTheGetStartedPage().
		clickOnLoginJoinButton_ForSecondTimeTotheSignINPage().
		enterInvalidUserName().
		enterInvalidPassword().
		clickOnSignInWithEmailForFailure().
		validateAuthenticationFailedErrorMessage().
		clickOnBackButton_SecondTimeNavigationToTheGetStartedPage().
		clickOnLoginJoinButton_ForSecondTimeTotheSignINPage().
		enterUserName().
		enterPasswordwithAsNull().
		clickOnSignInWithEmailForFailure().
		validatePasswordFieldErrorMessage().
		clickOnBackButton_SecondTimeNavigationToTheGetStartedPage().
		clickOnLoginJoinButton_ForSecondTimeTotheSignINPage().
		enterPassword().
		clickOnSignInWithEmailForFailure().
		validateUserNameFieldErrorMessage().
		clickOnBackButton_SecondTimeNavigationToTheGetStartedPage().
		clickOnLoginJoinButton_ForSecondTimeTotheSignINPage().
		enterUserName().
		enterLessNumberOfCharacterToThePassword().
		clickOnSignInWithEmailForFailure().
		validatePasswordFieldLessNumberOfCharachetersError().
		clickOnBackButton_SecondTimeNavigationToTheGetStartedPage().
		clickOnLoginJoinButton_ForSecondTimeTotheSignINPage().
		enterNumericIntoUserNameField().
		clickOnSignInWithEmailForFailure().
		validateUserNameFieldErrorMessage();
//		clickOnBackButton().
//		ClickOnGetStartedButton().
//		enterExcessBoundaryValueToUsernameField().
//		enterExcessBoundaryValueToPasswordField().
//		clickOnSignInWithEmailForInvalidScenario().
//		validateExcessBoundaryValueErrorMessage();
//

	}

	@Test
	public void validateGetStartedPageFacebookLogin_TC003() {

		reportStep("TC004 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnLoginJoinButton().
		clickOnSignInWithFacebook().
		enterFBUserEmail().
		enterFBPassword().
		clickOnFBLoginButton().
		clickOnContinue();

		reportStep("TC004 is  ended", "INFO");

	}

	@Test
	public void validateProfilePageLogin_TC004() {

		reportStep("TC005 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnSignInButton().
		enterUserName().
		enterPassword().
		clickSignInButtonForSuccess();

		reportStep("TC005 is  ended", "INFO");
	}

	@Test
	public void validateProfilePageFacebookLogin_TC005() {

		reportStep("TC006 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnSignInButton().
		clickOnSignInWithFacebook().
		enterFBUserEmail().
		enterFBPassword().
		clickOnFBLoginButton().
		clickOnContinue();
		reportStep("TC006 is  ended", "INFO");
	}

	@Test
	public void validateHomePageLogin_TC006() {

		reportStep("TC006 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnStoreCardButton().
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation();

	}

	@Test
	public void validateHomePageFacebookLogin_TC007() {

		reportStep("TC006 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnStoreCardButton().
		clickOnSignInWithFacebook().
		enterFBUserEmail().
		enterFBPassword().
		clickOnFBLoginButton().
		clickOnContinueForIntermediatePageNavigation();

		reportStep("TC006 is  ended", "INFO");
	}

	@Test
	public void validateStorePageLogin_TC008() {

		reportStep("TC006 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar().
		clickOnSearchedStoreCardImage().
		clickOnStoreMainCTA().
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation();

		reportStep("TC006 is  ended", "INFO");


	}

	@Test
	public void validateStorePageFacebookLogin_TC009() {

		reportStep("TC009 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar().
		clickOnSearchedStoreCardImage().
		clickOnStoreMainCTA().
		clickOnSignInWithFacebook().
		enterFBUserEmail().
		enterFBPassword().
		clickOnFBLoginButton().
		clickOnContinueForIntermediatePageNavigation();

		reportStep("TC009 is  ended", "INFO");


	}

	@Test
	public void validateStoreCategoryLogin_TC010() {

		reportStep("TC009 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnHamburgerIcon().
		clickOntheHighestCashbackRates().
		clickOnStoreCatStoreButton().
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation();


		reportStep("TC009 is  ended", "INFO");


	}

	@Test
	public void validateStoreCategoryFacebookLogin_TC011() {

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnHamburgerIcon().
		clickOntheHighestCashbackRates().
		clickOnStoreCatStoreButton().clickOnSignInWithFacebook().
		enterFBUserEmail().
		enterFBPassword().
		clickOnFBLoginButton().clickOnContinueForIntermediatePageNavigation();

		reportStep("TC010 is  ended", "INFO");


	}

	@Test
	public void validateSearchLogin_TC012() {

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().clickOnSearchIcon().
		enterTextIntoTheSearchBar().
		clickOnStoreCardButton().
		enterUserName().enterPassword().
		clickSignInButtoForRetailerNavigation();
	}

	@Test
	public void validateSearchFacebookLogin_TC013() {

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar().
		clickOnStoreCardButton().
		clickOnSignInWithFacebook().
		enterFBUserEmail().
		enterFBPassword().
		clickOnFBLoginButton().
		clickOnContinueForIntermediatePageNavigation();
		
	}

	@Test
	public void validateSearchViewAllLogin_TC014() {

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar().
		clickOnViewAll().
		clickOnStoreCardButton().
		clickOnSignInWithFacebook().
		enterFBUserEmail().
		enterFBPassword().
		clickOnFBLoginButton().
		clickOnContinueForIntermediatePageNavigation();

	}

	@Test
	public void validateSearchResultInProductsLogin_TC015() {

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar("off").
		clickOnSearchResultsInProducts().
		clickOnGrabDeal().
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation();

	}

	@Test
	public void validateSearchResultInCouponsLogin_TC016() {

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar().
		clickOnSearchResultsInCoupons().
		clickOnVoucherMainCTAForUnsignedUser().
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation();



	}

	@Test
	public void validateSignInPage_TC017() {

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnLoginJoinButton().
		validateSignInPage();

	}

	@Test
	public void validateVoucherCTALogin_TC0018() {

		AppDynamicVariables objAppDynamicVariables = new AppDynamicVariables(driver, testInfo);

		objAppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("CB_Store_One");

		String storeOne = objAppDynamicVariables.getRequiredStoreName("str_Store_One_Name");
		
		reportStep("TC006 is   ", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		clickMoreOffersBtn().
		clickOnVoucherButtonForUnsignedUser(0, storeOne).
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation();


	}
		
}