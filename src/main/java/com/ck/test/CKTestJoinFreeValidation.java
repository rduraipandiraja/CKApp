package com.ck.test;

import static io.appium.java_client.touch.offset.PointOption.point;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import com.app.ck.base.Base;
import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;
import com.ck.pages.SeeVideoToEarnCashbackPage;
import com.ck.pages.HomePage;
import com.ck.pages.IntermediateRetailerPage;
import com.ck.pages.JoinFreePage;
import com.ck.pages.OTPPage;
import com.ck.pages.ProfilePage;
import com.ck.pages.StorePage;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.touch.WaitOptions;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CKTestJoinFreeValidation extends WrapperMethods {

	@Test
	public void aaPreRequisiteForFacebookSignUpUserReset() {

		new CashKaroUtility(driver, testInfo).
		resetFacebookAccount();

	}

	@Test
	public void joinFromProfilePage_TC001() {

		String OtpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
				clickOnGoToHomePage().
				clickOnProfileIcon().
				clickOnJoinFreeButton().
				enterAllJoinFreeFieldsAndVerifyOTP();

		//creating the new object for the OTP page

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the Clickon verify OTP , it should navigate to the Home page
		new HomePage(driver, testInfo);

	}

	@Test
	public void joinFromGetStartedPage_TC002() {

		reportStep("TC001 is  started", "INFO");
		String OtpValue = new SeeVideoToEarnCashbackPage(driver,testInfo).
				clickOnLoginJoinButton().
				clickOnJoinFreeLink().
				enterAllJoinFreeFieldsAndVerifyOTP();

		//creating the new object for the OTP page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the ClickOn verify OTP , it should navigate to the Home page
		new HomePage(driver, testInfo);

		reportStep("TC001 is completed", "INFO");

	}

	@Test
	public void joinFromHomePage_TC003() {

		reportStep("TC006 is  started", "INFO");

		String OtpValue = new SeeVideoToEarnCashbackPage(driver,testInfo).
				clickOnGoToHomePage().
				clickOnStoreCardButton().
				clickOnJoinFreeLink().
				enterAllJoinFreeFieldsAndVerifyOTP();

		//creating the new object for the OTP page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the ClickOn verify OTP , it should navigate to the Home page
		new IntermediateRetailerPage(driver, testInfo);

		reportStep("TC001 is completed", "INFO");

	}

	@Test
	public void joinFromStorePage_TC004() {

		reportStep("TC006 is  started", "INFO");

		String OtpValue = new SeeVideoToEarnCashbackPage(driver,testInfo).
				clickOnGoToHomePage().
				clickOnSearchIcon().
				enterTextIntoTheSearchBar().
				clickOnSearchedStoreCardImage().
				clickOnStoreMainCTA().
				clickOnJoinFreeLink().
				enterAllJoinFreeFieldsAndVerifyOTP();

		//creating the new object for the OTP page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the ClickOn verify OTP , it should navigate to the Home page
		new IntermediateRetailerPage(driver, testInfo);

		reportStep("TC006 is  ended", "INFO");


	}

	@Test
	public void joinFromStoreCategory_TC005() {

		reportStep("TC009 is  started", "INFO");

		String OtpValue = 
				new SeeVideoToEarnCashbackPage(driver,testInfo).
				clickOnGoToHomePage().
				clickOnHamburgerIcon().
				clickOntheHighestCashbackRates().
				clickOnStoreCatStoreButton().
				clickOnJoinFreeLink().
				enterAllJoinFreeFieldsAndVerifyOTP();

		//creating the new object for the OTP page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the ClickOn verify OTP , it should navigate to the Home page
		new IntermediateRetailerPage(driver, testInfo);

		reportStep("TC006 is  ended", "INFO");




	}

	@Test
	public void joinFromSearch_TC006() {

		reportStep("TC010 is  started", "INFO");

		String OtpValue = new SeeVideoToEarnCashbackPage(driver,testInfo).
				clickOnGoToHomePage().
				clickOnSearchIcon().
				enterTextIntoTheSearchBar().
				clickOnStoreCardButton().
				clickOnJoinFreeLink().
				enterAllJoinFreeFieldsAndVerifyOTP();

		//creating the new object for the OTP page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the ClickOn verify OTP , it should navigate to the Home page
		new IntermediateRetailerPage(driver, testInfo);

		reportStep("TC006 is  ended", "INFO");


	}

	@Test
	public void joinFromSearchViewAll_TC007() {

		reportStep("TC010 is  started", "INFO");

		String OtpValue = new SeeVideoToEarnCashbackPage(driver,testInfo).
				clickOnGoToHomePage().
				clickOnSearchIcon().
				enterTextIntoTheSearchBar().
				clickOnViewAll().
				clickOnStoreCardButton().
				clickOnJoinFreeLink().
				enterAllJoinFreeFieldsAndVerifyOTP();

		//creating the new object for the OTP page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the ClickOn verify OTP , it should navigate to the Home page
		new IntermediateRetailerPage(driver, testInfo);

		reportStep("TC006 is  ended", "INFO");


	}

	@Test
	public void joinFromSearchResultsInProducts_TC008() {

		reportStep("TC010 is  started", "INFO");

		String OtpValue = new SeeVideoToEarnCashbackPage(driver,testInfo).
				clickOnGoToHomePage().
				clickOnSearchIcon().
				enterTextIntoTheSearchBar("off").
				clickOnSearchResultsInProducts().
				clickOnGrabDeal().
				clickOnJoinFreeLink().
				enterAllJoinFreeFieldsAndVerifyOTP();

		//creating the new object for the OTP page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the ClickOn verify OTP , it should navigate to the Home page
		new IntermediateRetailerPage(driver, testInfo);

		reportStep("TC006 is  ended", "INFO");

	}

	@Test
	public void joinFromSearchResultsInCoupons_TC009() {

		reportStep("TC010 is  started", "INFO");

		String OtpValue = 
				new SeeVideoToEarnCashbackPage(driver,testInfo).
				clickOnGoToHomePage().
				clickOnSearchIcon().
				enterTextIntoTheSearchBar().
				clickOnSearchResultsInCoupons().
				clickOnVoucherMainCTAForUnsignedUser().
				clickOnJoinFreeLink().
				enterAllJoinFreeFieldsAndVerifyOTP();

		//creating the new object for the OTP page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the ClickOn verify OTP , it should navigate to the Home page
		new IntermediateRetailerPage(driver, testInfo);

		reportStep("TC006 is  ended", "INFO");

	}

	@Test
	public void validateJoinFreePageErrorMessage_TC010() {

		JoinFreePage objJoinFree = 
				new SeeVideoToEarnCashbackPage(driver, testInfo).
				clickOnGoToHomePage().
				clickOnProfileIcon().
				clickOnJoinFreeButton().
				clickOnGetOTPButton();

		//Error validation : 
		objJoinFree.validateFullNameErrorMessage();

		objJoinFree.validateEmailAddressErrorMessage();

		objJoinFree.validatePasswordFieldErrorMessage();

		objJoinFree.validateMobileNumberFieldErrorValidation();


	}

	@Test
	public void validateJoinFreePage_TC011() {

		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		validateJoinFreePage();  


	}

	@Test
	public void validateExistingEmailIdErrorValidation_TC012() throws IOException {

		String email =
				new SeeVideoToEarnCashbackPage(driver, testInfo).
				clickOnGoToHomePage().
				clickOnProfileIcon().
				clickOnJoinFreeButton().
				enterFullName().
				returnEnteredEmail();

		String mobileNumber =
				new JoinFreePage(driver, testInfo).
				enterPassword().
				enterMobileNumber();

		String OtpValue= "";

		OtpValue =
				new JoinFreePage(driver, testInfo).
				clickOnGetOTPButton(mobileNumber);

		//creating the new object for the OTP page
		OTPPage objOtpPage =
				new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(OtpValue).
		clickOnVerifyOTP();

		//Once after clicking on the Clickon verify OTP , it should navigate to the Home page

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();

		mobileNumber= new HomePage(driver, testInfo).
				clickOnProfileIcon().
				clickOnJoinFreeButton().
				enterFullName().
				enterEmail(email).
				enterPassword().
				enterMobileNumber();

		OtpValue = 
				new JoinFreePage(driver, testInfo).
				clickOnGetOTPButton(mobileNumber);

		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		new JoinFreePage(driver, testInfo).validateExistingEmailIdErrorMessage();

	}

	@Test
	public void validateExistingMobileNumberErrorValidation_TC013() {

		String mobileNumber = new SeeVideoToEarnCashbackPage(driver, testInfo).
				clickOnGoToHomePage().
				clickOnProfileIcon().
				clickOnJoinFreeButton().
				enterFullName().
				enterEmail().
				enterPassword().
				enterMobileNumber();

		String OtpValue= "";

		OtpValue = 
				new JoinFreePage(driver, testInfo).
				clickOnGetOTPButton(mobileNumber);

		//creating the new object for the OTP page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the Clickon verify OTP , it should navigate to the Home page
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();

		new HomePage(driver, testInfo).
		clickOnProfileIcon().
		clickOnJoinFreeButton()
		.enterFullName().
		enterEmail().
		enterPassword().
		enterMobileNumber(mobileNumber);

		new JoinFreePage(driver, testInfo).
		clickOnGetOTPButton().
		validateThisMobileNumberBeenTaken();

	}	

	@Test
	public void enterNumericToUserFullNameAndValidateUserFullName_TC014() throws IOException {

		String mobileNumber = 
				new SeeVideoToEarnCashbackPage(driver, testInfo).
				clickOnGoToHomePage().
				clickOnProfileIcon().
				clickOnJoinFreeButton().
				enterFullName("258625").
				enterEmail().
				enterPassword().
				enterMobileNumber();

		new JoinFreePage(driver, testInfo).
		clickOnGetOTPButtonForFailure(mobileNumber).
		validateFullNameErrorMessageForNumericEntry();

	}	

	@Test
	public void enterNumericSpecialCharactersIntoEmailAddress_TC015() throws IOException {

		String mobileNumber = new SeeVideoToEarnCashbackPage(driver, testInfo).
				clickOnGoToHomePage().
				clickOnProfileIcon().
				clickOnJoinFreeButton().
				enterFullName().enterEmail("##12458@Test.com").enterPassword().enterMobileNumber();

		new JoinFreePage(driver, testInfo).
		clickOnGetOTPButtonForFailure(mobileNumber).
		validateInvalidEmailErrorMessage();

	}	

	@Test
	public void enterInvalidMobileNumber_TC016() throws IOException {

		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().enterEmail().enterPassword().enterMobileNumber("5555522563");

		new JoinFreePage(driver, testInfo).clickOnGetOTPButton().inValidMobileNumberErrorValidation();


	}	

	@Test
	public void validatePasswordFieldMinBoundaryValueError_TC017() throws IOException {

		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().
		enterEmail().
		enterPassword("pp").
		enterMobileNumber();

		new JoinFreePage(driver, testInfo).clickOnGetOTPButton().
		validateMinimumBoundaryValidationForPassword();


	}	

	@Test
	public void validateUserFullNameMinBoundaryValueError_TC018() throws IOException {

		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName("C").
		enterEmail().
		enterPassword().
		enterMobileNumber();

		new JoinFreePage(driver, testInfo).
		clickOnGetOTPButton().
		validateMinimumBoundaryValidationForFullName();


	}

	@Test
	public void validateEmailFieldMinBoundaryValueError_TC019() throws IOException {

		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().
		enterEmail("@co").
		enterPassword().
		enterMobileNumber();

		new JoinFreePage(driver, testInfo).clickOnGetOTPButton().
		validateMinimumBoundaryValidationForEmailAddress();


	}

	@Test
	public void validateMobileFieldMinBoundaryValueError_TC020() throws IOException {

		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().
		enterEmail().
		enterPassword().
		enterMobileNumber("78");

		new JoinFreePage(driver, testInfo).
		clickOnGetOTPButton().
		validateMinimumBoundaryValidationForMobileNumber();;

	}

	@Test
	public void validateInvalidOTPAndThenEnterValidOTP_TC021() {

		String InvalidOtpValue = "999999";

		String validOtpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
				clickOnGoToHomePage().
				clickOnProfileIcon().
				clickOnJoinFreeButton().
				enterAllJoinFreeFieldsAndVerifyOTP();

		//Creating the new object for the OTP Page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(InvalidOtpValue).
		clickOnVerifyOTP().
		verifyInValidOTP().
		enterOTP(validOtpValue).
		clickOnVerifyOTP();

		//Once after clicking on the Click_On Verify OTP , It should navigate to the Home Page
		new HomePage(driver, testInfo);


	}	

	@Test
	public void validateOTPpage_TC022() {

		String validFirstOtpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
				clickOnGoToHomePage().
				clickOnProfileIcon().
				clickOnJoinFreeButton().
				enterAllJoinFreeFieldsAndVerifyOTP();

		//Creating the new object for the OTP Page
		String mobileNumber = new OTPPage(driver, testInfo).
				validateOTPPage().
				clickOnChange().
				clickOnGetOTPButtonForSuccess().
				enterOTP(validFirstOtpValue).
				clickOnVerifyOTP().
				verifyInValidOTP().
				clickOnResendCode().
				getTheMobileNumberFromOTPPage().
				replaceAll("[^0-9]", "");

		String OTPvalue = Utilities.getSignUPOTP(mobileNumber); 

		new OTPPage(driver, testInfo).enterOTP(OTPvalue).clickOnVerifyOTP();


		//Once after clicking on the Click_On Verify OTP , It should navigate to the Home Page
		new HomePage(driver, testInfo);


	}

	@Test
	public void OTPExpiredValidation_TC023() {

		String validFirstOtpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
				clickOnGoToHomePage().
				clickOnProfileIcon().
				clickOnJoinFreeButton().
				enterAllJoinFreeFieldsAndVerifyOTP();

		//Creating the new object for the OTP Page
		String mobileNumber = new OTPPage(driver, testInfo).validateOTPPage().
				clickOnChange().
				clickOnGetOTPButtonForSuccess().
				enterOTP(validFirstOtpValue).
				clickOnVerifyOTP().
				verifyInValidOTP().
				clickOnResendCode().
				getTheMobileNumberFromOTPPage().
				replaceAll("[^0-9]", "");

		String OTPvalue = Utilities.getSignUPOTP(mobileNumber); 

		new OTPPage(driver, testInfo).
		waitFor3Minutes().
		enterOTP(OTPvalue).
		clickOnVerifyOTP().
		validateOTPExpiredvalidation().
		clickOnBackButton_JF();

	}

	//Facebook signUp test cased starts here : 

	@Test
	public void FBSignUpFromProfilePage_TC024() {

		CashKaroUtility utils = new CashKaroUtility(driver, testInfo);
		utils.resetFacebookAccount();

		String OtpValue =
				new SeeVideoToEarnCashbackPage(driver, testInfo).
				clickOnGoToHomePage().
				clickOnProfileIcon().
				clickOnJoinFreeButton().
				clickOnJoinWithFacebook().
				enterFBNewUserEmail().
				enterNewFBUserPassword().
				clickOnFBLoginButton().
				clickOnContinueForNewUser().
				enterMobileNumberAndGetTheOTP();

		//creating the new object for the OTP page

		OTPPage  objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the Clickon verify OTP , it should navigate to the Home page
		new HomePage(driver, testInfo);

		utils.resetFacebookAccount();


	}

	@Test
	public void FBSignUpFromGetStartedPage_TC025() {

		CashKaroUtility utils = new CashKaroUtility(driver, testInfo);
		utils.resetFacebookAccount();


		reportStep("TC001 is  started", "INFO");
		String OtpValue = new SeeVideoToEarnCashbackPage(driver,testInfo).
				clickOnLoginJoinButton().
				clickOnJoinFreeLink().
				clickOnJoinWithFacebook().
				enterFBNewUserEmail().
				enterNewFBUserPassword().
				clickOnFBLoginButton().
				clickOnContinueForNewUser().
				enterMobileNumberAndGetTheOTP();


		//creating the new object for the OTP page

		OTPPage  objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the Clickon verify OTP , it should navigate to the Home page
		new HomePage(driver, testInfo);

		utils.resetFacebookAccount();


	}

	@Test
	public void FBSignUPFromHomePage_TC026() {

		CashKaroUtility utils = new CashKaroUtility(driver, testInfo);
		utils.resetFacebookAccount();


		reportStep("TC006 is  started", "INFO");

		String OtpValue = new SeeVideoToEarnCashbackPage(driver,testInfo).
				clickOnGoToHomePage().
				clickOnStoreCardButton().
				clickOnJoinFreeLink().
				clickOnJoinWithFacebook().
				enterFBNewUserEmail().
				enterNewFBUserPassword().
				clickOnFBLoginButton().
				clickOnContinueForNewUser().
				enterMobileNumberAndGetTheOTP();


		//creating the new object for the OTP page

		OTPPage  objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the Clickon verify OTP , it should navigate to the Home page
		new IntermediateRetailerPage(driver, testInfo);

		utils.resetFacebookAccount();


	}

	@Test
	public void FBSignUpFromStorePage_TC027() {

		CashKaroUtility utils = new CashKaroUtility(driver, testInfo);
		utils.resetFacebookAccount();


		reportStep("TC006 is  started", "INFO");

		String OtpValue = new SeeVideoToEarnCashbackPage(driver,testInfo).
				clickOnGoToHomePage().
				clickOnSearchIcon().
				enterTextIntoTheSearchBar().
				clickOnSearchedStoreCardImage().
				clickOnStoreMainCTA().
				clickOnJoinFreeLink().
				clickOnJoinWithFacebook().
				enterFBNewUserEmail().
				enterNewFBUserPassword().
				clickOnFBLoginButton().
				clickOnContinueForNewUser().
				enterMobileNumberAndGetTheOTP();


		//creating the new object for the OTP page

		OTPPage  objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the Clickon verify OTP , it should navigate to the Home page
		new IntermediateRetailerPage(driver, testInfo);

		utils.resetFacebookAccount();

	}

	@Test
	public void FBSignUpFromStoreCategoryPage_TC028() {

		CashKaroUtility utils = new CashKaroUtility(driver, testInfo);
		utils.resetFacebookAccount();


		reportStep("TC009 is  started", "INFO");

		String OtpValue = new SeeVideoToEarnCashbackPage(driver,testInfo).
				clickOnGoToHomePage().
				clickOnHamburgerIcon().
				clickOntheHighestCashbackRates().
				clickOnStoreCatStoreButton().
				clickOnJoinFreeLink().
				clickOnJoinWithFacebook().
				enterFBNewUserEmail().
				enterNewFBUserPassword().
				clickOnFBLoginButton().
				clickOnContinueForNewUser().
				enterMobileNumberAndGetTheOTP();


		//creating the new object for the OTP page

		OTPPage  objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the Clickon verify OTP , it should navigate to the Home page
		new IntermediateRetailerPage(driver, testInfo);

		utils.resetFacebookAccount();

	}

	@Test
	public void FBSignUpFromSearch_TC029() {

		CashKaroUtility utils = new CashKaroUtility(driver, testInfo);
		utils.resetFacebookAccount();

		reportStep("TC010 is  started", "INFO");

		String OtpValue = new SeeVideoToEarnCashbackPage(driver,testInfo).
				clickOnGoToHomePage().
				clickOnSearchIcon().
				enterTextIntoTheSearchBar().
				clickOnStoreCardButton().
				clickOnJoinFreeLink().
				clickOnJoinWithFacebook().
				enterFBNewUserEmail().
				enterNewFBUserPassword().
				clickOnFBLoginButton().
				clickOnContinueForNewUser().
				enterMobileNumberAndGetTheOTP();


		//creating the new object for the OTP page

		OTPPage  objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the Clickon verify OTP , it should navigate to the Home page
		new IntermediateRetailerPage(driver, testInfo);

		utils.resetFacebookAccount();


	}

	@Test
	public void FSignUpFromSearchViewAll_TC030() {

		CashKaroUtility utils = new CashKaroUtility(driver, testInfo);
		utils.resetFacebookAccount();


		reportStep("TC010 is  started", "INFO");

		String OtpValue = new SeeVideoToEarnCashbackPage(driver,testInfo).
				clickOnGoToHomePage().
				clickOnSearchIcon().
				enterTextIntoTheSearchBar().
				clickOnViewAll().
				clickOnStoreCardButton().
				clickOnJoinFreeLink().
				clickOnJoinWithFacebook().
				enterFBNewUserEmail().
				enterNewFBUserPassword().
				clickOnFBLoginButton().
				clickOnContinueForNewUser().
				enterMobileNumberAndGetTheOTP();


		//creating the new object for the OTP page

		OTPPage  objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the Clickon verify OTP , it should navigate to the Home page
		new IntermediateRetailerPage(driver, testInfo);

		utils.resetFacebookAccount();


	}

	@Test
	public void FBSignUpFromSearchResultsFromProduts_TC031() {

		CashKaroUtility utils = new CashKaroUtility(driver, testInfo);
		utils.resetFacebookAccount();

		reportStep("TC010 is  started", "INFO");

		String OtpValue = new SeeVideoToEarnCashbackPage(driver,testInfo).
				clickOnGoToHomePage().
				clickOnSearchIcon().
				enterTextIntoTheSearchBar("off").
				clickOnSearchResultsInProducts().
				clickOnGrabDeal().
				clickOnJoinFreeLink().
				clickOnJoinWithFacebook().
				enterFBNewUserEmail().
				enterNewFBUserPassword().
				clickOnFBLoginButton().
				clickOnContinueForNewUser().
				enterMobileNumberAndGetTheOTP();


		//creating the new object for the OTP page

		OTPPage  objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the Clickon verify OTP , it should navigate to the Home page
		new IntermediateRetailerPage(driver, testInfo);

		utils.resetFacebookAccount();


	}

	@Test
	public void FBSignUpFromSearchResultsInCoupons_TC032() {

		CashKaroUtility utils = new CashKaroUtility(driver, testInfo);
		utils.resetFacebookAccount();


		reportStep("TC010 is  started", "INFO");

		String OtpValue = new SeeVideoToEarnCashbackPage(driver,testInfo).
				clickOnGoToHomePage().
				clickOnSearchIcon().
				enterTextIntoTheSearchBar().
				clickOnSearchResultsInCoupons().
				clickOnVoucherMainCTAForUnsignedUser().
				clickOnJoinFreeLink().
				clickOnJoinWithFacebook().
				enterFBNewUserEmail().
				enterNewFBUserPassword().
				clickOnFBLoginButton().
				clickOnContinueForNewUser().
				enterMobileNumberAndGetTheOTP();

		//creating the new object for the OTP page

		OTPPage  objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the Clickon verify OTP , it should navigate to the Home page
		new IntermediateRetailerPage(driver, testInfo);

		utils.resetFacebookAccount();

	}

	//Negative FB sign up validation : 

	@Test
	public void FBSignUPExistingMobileNumErrorValidation_TC033() {

		CashKaroUtility utils = new CashKaroUtility(driver, testInfo);
		utils.resetFacebookAccount();


		String mobileNumber = new SeeVideoToEarnCashbackPage(driver, testInfo).
				clickOnGoToHomePage().
				clickOnProfileIcon().
				clickOnJoinFreeButton().
				enterFullName().
				enterEmail().
				enterPassword().
				enterMobileNumber();

		String OtpValue= "";

		OtpValue = new JoinFreePage(driver, testInfo).
				clickOnGetOTPButton(mobileNumber);

		//creating the new object for the OTP page
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		//Once after clicking on the Clickon verify OTP , it should navigate to the Home page
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();

		new HomePage(driver, testInfo).
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		clickOnJoinWithFacebook().
		enterFBNewUserEmail().
		enterNewFBUserPassword().
		clickOnFBLoginButton().
		clickOnContinueForNewUser().
		enterMobileNumberAndClickOnGetOTPButton(mobileNumber).
		validateExistingMobileNumErrorAtFBSignUpIntermediatePage();



	}

	@Test
	public void FBSignUPinValidMobileNumErrorValidation_TC034() {

		CashKaroUtility utils = new CashKaroUtility(driver, testInfo);
		utils.resetFacebookAccount();

		String invalidMobileNumber = "2355556666";

		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		clickOnJoinWithFacebook().
		enterFBNewUserEmail().
		enterNewFBUserPassword().
		clickOnFBLoginButton().
		clickOnContinueForNewUser().
		enterMobileNumberAndClickOnGetOTPButton(invalidMobileNumber).
		inValidMobileNumberErrorAndMobilenumberIntermediatePageTextValidataion();


	}

	@Test
	public void FBSignUpClickingBackbuttonFromOTPScreen_TC035() {


		CashKaroUtility utils = new CashKaroUtility(driver, testInfo);
		utils.resetFacebookAccount();

		String OtpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
				clickOnGoToHomePage().
				clickOnProfileIcon().
				clickOnJoinFreeButton().
				clickOnJoinWithFacebook().
				enterFBNewUserEmail().
				enterNewFBUserPassword().
				clickOnFBLoginButton().
				clickOnContinueForNewUser().
				enterMobileNumberAndGetTheOTP();

		//creating the new object for the OTP page

		OTPPage  objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnBackButton();

	}

	@Test
	public void FBSignUpClickingBackbuttonFromMobileNumberEntryScreen_TC036() {

		CashKaroUtility utils = new CashKaroUtility(driver, testInfo);
		utils.resetFacebookAccount();

		String strMobileNumber =  "2222222222";

		reportStep("TC001 is  started", "INFO");
		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnLoginJoinButton().
		clickOnJoinFreeLink().
		clickOnJoinWithFacebook().
		enterFBNewUserEmail().
		enterNewFBUserPassword().
		clickOnFBLoginButton().
		clickOnContinueForNewUser().
		enterMobileNumberAndClickOnGetOTPButton(strMobileNumber).
		clickOnBackButton();

	}


}