package com.ck.test;

import org.testng.annotations.Test;

import com.app.ck.base.AppDynamicVariables;
import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.ck.pages.AccountSettingsPage;
import com.ck.pages.SeeVideoToEarnCashbackPage;
import com.ck.pages.HomePage;
import com.ck.pages.OTPPage;
import com.ck.pages.PaymentOTPPage;
import com.ck.pages.ReferAndEarn;
import com.ck.pages.SignedInProfilePage;
import com.ck.pages.admin.AdminCoreFunction;
import com.ck.pages.admin.AdminStoresPage;
import com.ck.pages.admin.TestimonialPages;

public class CKTestSupportiveModuleValidation extends WrapperMethods {
	
	//All Possible Positive and Negative flows covered in only one test case
	@Test
	public void PersonalDetailsPage_PositiveNegativeValidation() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String email = objCashKaroUtility.generateEmail();
		String fiftyUserFullName_ExceedChars	 =  getTestData(7, "FiftyPlusUserChar");
		String fiftyUserFullName	 =  getTestData(7, "FiftyUserChar");
		
		String mobNum = objCashKaroUtility.generateMobileNumber();
		String userName = getTestData(3, "UserFullName");
		String testUserName = "CashKaro Test User";

		String otpValue = 
		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnLoginJoinButton().
		clickOnJoinFreeLink().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnAccountSettings().
		validateFullName(userName).  //Valdiate Full name 
		validateEmailAddress(email). // Valdiate User Email 
		validateMobileNumber(mobNum). // validate user Mobile number
		clickOnSaveChangesButton(). 
		validatePleaseEnterDetailsError(). // Without entering details clicing save changes button and validating error
		clickOnReceiveEmailWhenIGetReferralRadioButton(). // clicking receive referral radio button and save changes 
		clickOnSaveChangesButton().
		validateProfileUpdatedSuccessMessage().
		clickOnReceivWeeklyoffersNewsLetterRadioButton(). // clicking weeeky news letter readio button and save changes 
		validateProfileUpdatedSuccessMessage().
		clickOnReceiveEmailWhenIGetReferralRadioButton().
		clickOnSaveChangesButton().
		validateProfileUpdatedSuccessMessage(). 
		clickOnReceivWeeklyoffersNewsLetterRadioButton().
		validateProfileUpdatedSuccessMessage().
		enterFullName(testUserName).    // enter new full and validate the full name in personal details page
		clickOnSaveChangesButton().     
		validateFullName(testUserName).  
		enterFullName("a").  // enter min char in user full name and validate error
		clickOnSaveChangesButton().
		validateMin2CharError(1).
		enterFullName("   ").  // enter space in user full name and validate error
		clickOnSaveChangesButton().
		validateFullNameFieldIsRequired().
		enterFullName(fiftyUserFullName_ExceedChars).
		clickOnSaveChangesButton().
		validateMax50CharError(1).
		enterFullName(fiftyUserFullName).
		clickOnSaveChangesButton().  // enter max 50 plus char in to user name field and validate errror
		validateProfileUpdatedSuccessMessage(). // clicing on edit button and validte new mobile number update		
		clickOnBackButton().
		validateTheUserNameChangesInProfilePage(fiftyUserFullName).
		clickOnLogout(). // Logout and try  Sign with the First old email which does not exists in DB - Authenticatin error in signin page
		clickOnProfileIcon().
		clickOnSignInButton().
		enterUserName(email).
		enterPassword().
		clickSignInButtonForSuccess();


	}

	//All Possible Positive and Negative flows covered in only one test case
	@Test
	public void ChangePasswordPage_PositiveNegativeValidation() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();
		String minPassword = "AA12";
		String maxPassword = "abcdefghijk123456789@####";
		String currentPassword = getTestData(0, "TC001_ValidLoginPassword");
		String password1 = "TestUser@1";
		String password2 = "TestUser@3";
	 

		String otpValue = 
		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnLoginJoinButton().
		clickOnJoinFreeLink().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		
		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnAccountSettings().
		clickOnChangePasswordTab().
		clickOnSaveChangesButton().
		validatePleaseEnterCurrentPassword().
		validiatePleaseEnterNewPassword().
		validatePleaseEnterConfirmPassword().
		enterCurrentPassword(minPassword).
		enterNewPassword(minPassword).
		enterConfirmPassword(minPassword).
		clickOnSaveChangesButton().
		validatePasswordMustBeAtleast6CharError(3).
		enterCurrentPassword(maxPassword).
		enterNewPassword(maxPassword).
		enterConfirmPassword(maxPassword).
		clickOnSaveChangesButton().
		validatePasswordMustBeLessThan20Chars(3).
		enterCurrentPassword(currentPassword).
		enterNewPassword(password1).
		enterConfirmPassword(password2).
		clickOnSaveChangesButton().
		validiatePasswordDoesNotMatchError().
		enterCurrentPassword(password1).
		enterNewPassword(password2).
		enterConfirmPassword(password2).
		clickOnSaveChangesButton().
		validatePasswordDoesNotMatchTryAgain().
		enterCurrentPassword(currentPassword).
		enterNewPassword(password2).
		enterConfirmPassword(password2).
		clickOnSaveChangesButton().
		validateNewPasswordUpdatedSuccessMessage().
		clickOnBackButton().
		clickOnLogout().
		clickOnProfileIcon().
		clickOnSignInButton().
		enterUserName(email).
		enterPassword(currentPassword).
		clickOnSignInWithEmailForFailure().
		validateAuthenticationFailedErrorMessage().
		enterPassword(password2).
		clickSignInButtonForSuccess();
		

	}

	//All Possible Positive and Negative flows covered in only one test case
	@Test
	public void Testimonial_Validations() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String email =  objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();
		String testimonialCount = "";


		String otpValue = 
		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnLoginJoinButton().
		clickOnJoinFreeLink().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		testimonialCount = new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonRateUs().
		clickOnWriteTestimonialPage().
		enterfeedBack(getTestData(12, "OneStarFeedback")).
		clickStarOne().
		clickOnSubmitButton().
		clickOnWriteTestimonialPage().
		enterfeedBack(getTestData(12, "TwoStarFeedback")).
		clickStarTwo().
		clickOnSubmitButton().
		clickOnWriteTestimonialPage().
		enterfeedBack(getTestData(12, "ThreeStarFeedback")).
		clickStarThree().
		clickOnSubmitButton().
		clickOnWriteTestimonialPage().
		enterfeedBack(getTestData(12, "FourStarFeedback")).
		clickStarFour().
		clickOnSubmitButton().
		clickOnWriteTestimonialPage().
		enterfeedBack(getTestData(12, "FiveStarFeedback")).
		clickStarFive().
		clickOnSubmitButton().
		getTestimonialsCount();
		
		
		/*
		 * Step 2 : Navigate to Admin and  and aprove the Testimonial  
		 */
		
		//Admin actions
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnInteractiveMainMenu();
		adminFunctions.clickOnSubMenuTestimonials();
		
		TestimonialPages testimonial = new TestimonialPages(driver, testInfo);
		
		
		testimonial.selectUserEmailInSearchByDropDown();
		testimonial.enterKeyWordAsUserEmail(email);
		testimonial.clickOnSearchButton();
		
		for(int counter= 5 ; counter>=1 ;counter--) {
			
			
		testimonial.clickOnEditButtonBasedOnStarts(email, Integer.toString(counter));
		testimonial.changeStatus("Active");
		testimonial.enterTitle("APPIUM GIVES "+Integer.toString(counter)+" STARS");
		testimonial.clickOnSaveTestimonial();
		}
		
		/*
		 * Step 3 : Come back to app and validte the Active testimonials and its counts
		 */
		
		CashKaroUtility cuForCKApp_1 =  new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = cuForCKApp_1.launchCashKaroApp(driver);
		
		new HomePage(driver,testInfo).
		clickOnProfileIconForSignedUser().
		clickonRateUs().
		validateTestimonialCountIncreased(testimonialCount).
		validateAdminTestimonialTitle_UserFeedBack_PostedTimings(testdata.get(3).get("UserFullName"));
		
		/*
		 * Step 4 : Go back to Admin & then inactive all the testimonials of that particular user 
		 */
		
		//Admin actions
				CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
				driver = cu2.launchChromeWebView(driver);
				
				AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);		
				adminFunctions2.naviagate_AdminPage();
				adminFunctions2.enterUsername(getTestData(8, "AdminUserName"));
				adminFunctions2.enterPassword(getTestData(8, "AdminPassword"));
				adminFunctions2.clickSubmit();
				adminFunctions2.clickOnHamburgerMenu();
				adminFunctions2.clickOnInteractiveMainMenu();
				adminFunctions2.clickOnSubMenuTestimonials();
				
				TestimonialPages testimonial2 = new TestimonialPages(driver, testInfo);
				
				
				testimonial2.selectUserEmailInSearchByDropDown();
				testimonial2.enterKeyWordAsUserEmail(email);
				testimonial2.clickOnSearchButton();
				
				for(int counter= 5 ; counter>=1 ;counter--) {
					
					
				testimonial2.clickOnEditButtonBasedOnStarts(email, Integer.toString(counter));
				testimonial2.changeStatus("In-Active");
				testimonial2.enterTitle("APPIUM GIVES "+Integer.toString(counter)+" STARS");
				testimonial2.clickOnSaveTestimonial();
				
				}
				
		
				/*
				 * Step 5 : Now, Come back to app and validate the Testimonial count decrement by 5
				 */	
				
				CashKaroUtility cuForCKApp_2 =  new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
				driver  = cuForCKApp_2.launchCashKaroApp(driver);
				
				new HomePage(driver,testInfo).
				clickOnProfileIconForSignedUser().
				clickonRateUs().
				validateTestimonialCount_Decreased(testimonialCount);
				
		
		
	}

	//validate How IT Works
	@Test
	public void validateHowCashKaroWorks_StaticPage() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnLoginJoinButton().
		clickOnJoinFreeLink().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();


		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnHowCashKaroWorks().
		clickOnHamburgerButton().
		validateSlider().
		clickOnHamburgerButton().
		clickOnHowToUseCashKaro().
		clickOnHamburgerButton().
		clickOnHowWePayYou().
		clickOnHamburgerButton().
		clickOnCashbackRelated().
		//clickOnHamburgerButton().
		//clickOnCashbackStatusQuestions().
		clickOnHamburgerButton().
		clickOnCashbackBestPractice().
		clickOnHamburgerButton().
		clickOnMissingCashbackSeeHere().
		clickOnHamburgerButton().
		clickOnRewardRelated().
		//clickOnHamburgerButton().
		//clickOnwhatAreRewards().
		clickOnHamburgerButton().
		clickOnRewardsstatusQuestions().
		clickOnHamburgerButton().
		clickOnBestPracticeToEnsureRewardsTrack().
		clickOnHamburgerButton().
		clickOnMissingRewardsHere().
		clickOnHamburgerButton().
		clickOnPayments();


	}
	
	//validate Call Us
	@Test
	public void validateCallUsFunctionality() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnLoginJoinButton().
		clickOnJoinFreeLink().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		
		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickonCallUs();
		
		isElementLocatedByXpathPresent("//*[contains(@text,'+91')]");
		

	}
	
	//Forgot Password validation : 
	@Test
	public void forgotPassword_Validation() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();
		String invalidEmail = "abcdtest@zyxu.kom";
		String numericInvalidEmail = "1825@233.856";
		String specialCharacters = "#@%&+_-=@gmail.com";

		String otpValue = 
		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();

		new HomePage(driver, testInfo).
		clickOnProfileIcon().
		clickOnSignInButton().
		clickOnForgotPasswordLink().
		enterYourEmail(email).
		clickOnSubmitRequestButton(). // Enter a valid email - and click on forgot password button 
		validateTheForgotPasswordSuccessMessage().
		enterYourEmail(invalidEmail). // Enter non existing mail and request for the fogot password
		clickOnSubmitRequestButton().
		validateSorryThereIsNoActiveUserError(invalidEmail). 
		enterYourEmail(numericInvalidEmail). // Enter Numeric chars and validate error
		clickOnSubmitRequestButton().
		validateEmailMustBeAValidEmail().
		enterYourEmail(specialCharacters). // Enter special char and validate the error
		validateEmailMustBeAValidEmail().
		enterYourEmail("a"). // Enter a single char and validate the error
		clickOnSubmitRequestButton().
		validateMin2CharsLongError().
		enterYourEmail("").
		clickOnSubmitRequestButton().
		validatePleaseEnterTheEmail().
		validateAllobjects().
		clickOnLogin().
		enterUserName(email).
		enterPassword().
		clickSignInButtonForFailure().
		validateAuthenticationFailedErrorMessage().
		clickOnForgotPasswordLink().
		clickOnBackButton();
		
		

	}

	//Contact us positive and negative validation : 
	@Test
	public void AskAQuestionForm_TC001() {

		reportStep("TC001 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickContactUsLink().
		validateTheErrorMessage().
		clickBackButton().
		clickContactUsLink().
		validateEnterMin2CharacterErrorMessage("1", "1", 1).
		validateEnterMax50CharacterErrorMessage(getTestData(7, "FiftyPlusUserChar"), getTestData(7, "FiftyPlusUserChar"), 1).
		clickSelectFromListBelow().
		clickGeneralEnquirie().
		enterEnterYourName(getTestData(3, "UserFullName")).
		enterEnterYourEmailId(getTestData(0, "TC001_ValidLoginEmail")).
		enterYourMessage("Message").
		clickSubmitButtonVerifySuccessMessage().
		clickBackButton().
		clickContactUsLink().
		clickSelectFromListBelow().
		clickMyCashbackIsIncorrect().
		enterEnterYourName(getTestData(3, "UserFullName")).
		enterEnterYourEmailId(getTestData(0, "TC001_ValidLoginEmail")).
		enterYourMessage("Message").
		clickSubmitButtonVerifySuccessMessage().
		clickBackButton().
		clickContactUsLink().
		clickSelectFromListBelow().
		clickGetListedOnOurSite().
		enterEnterYourName(getTestData(3, "UserFullName")).
		enterEnterYourEmailId(getTestData(0, "TC001_ValidLoginEmail")).
		enterYourMessage("Message").
		clickSubmitButtonVerifySuccessMessage().
		clickBackButton().
		clickContactUsLink().
		clickSelectFromListBelow().
		clickPartnership().
		enterEnterYourName(getTestData(3, "UserFullName")).
		enterEnterYourEmailId(getTestData(0, "TC001_ValidLoginEmail")).
		enterYourMessage("Message").
		clickSubmitButtonVerifySuccessMessage().
		clickBackButton().
		clickContactUsLink().
		clickSelectFromListBelow().
		clickMedia().
		enterEnterYourName(getTestData(3, "UserFullName")).
		enterEnterYourEmailId(getTestData(0, "TC001_ValidLoginEmail")).
		enterYourMessage("Message").
		clickSubmitButtonVerifySuccessMessage().
		clickBackButton().
		clickContactUsLink().
		clickSelectFromListBelow().
		clickOther().
		enterEnterYourName(getTestData(3, "UserFullName")).
		enterEnterYourEmailId(getTestData(0, "TC001_ValidLoginEmail")).
		enterYourMessage("Message").
		clickSubmitButtonVerifySuccessMessage().
		clickBackButton().
		
		clickOnSignInButton().
		enterUserName().
		enterPassword().
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickContactUsLink().
		validateUserNameDefaultValue(getTestData(0, "TC001_UserName")).
		validateEmailAddressDefaultValue(getTestData(0, "TC001_ValidLoginEmail")).
		validateEnterMin2CharacterErrorMessage("1", "1", 1).
		validateEnterMax50CharacterErrorMessage(getTestData(7, "FiftyPlusUserChar"), getTestData(7, "FiftyPlusUserChar"), 1).
		clickBackButtonSignedUser().
		clickContactUsLink().
		clickSelectFromListBelow().
		clickGeneralEnquirie().
		enterYourMessage("Message").
		clickSubmitButtonVerifySuccessMessage().
		clickBackButtonSignedUser().
		clickContactUsLink().
		clickSelectFromListBelow().
		clickMyCashbackIsIncorrect().
		enterYourMessage("Message").
		clickSubmitButtonVerifySuccessMessage().
		clickBackButtonSignedUser().
		clickContactUsLink().
		clickSelectFromListBelow().
		clickGetListedOnOurSite().
		enterYourMessage("Message").
		clickSubmitButtonVerifySuccessMessage().
		clickBackButtonSignedUser().
		clickContactUsLink().
		clickSelectFromListBelow().
		clickPartnership().
		enterYourMessage("Message").
		clickSubmitButtonVerifySuccessMessage().
		clickBackButtonSignedUser().
		clickContactUsLink().
		clickSelectFromListBelow().
		clickMedia().
		enterYourMessage("Message").
		clickSubmitButtonVerifySuccessMessage().
		clickBackButtonSignedUser().
		clickContactUsLink().
		clickSelectFromListBelow().
		clickOther().
		enterYourMessage("Message").
		clickSubmitButtonVerifySuccessMessage().
		clickBackButtonSignedUser().
		clickOnLogout();
		
		reportStep("TC001 is completed", "INFO");

	}

	//All Possible Positive and Negative flows covered in only one test case
	@Test
	public void PaymentSettings_NEFTValiation() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnLoginJoinButton().
		clickOnJoinFreeLink().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		
		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		AccountSettingsPage objAccountSettings = new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterBankNEFTPaymentDetails().
		clickOnSaveChangesButton().
		validatePaymentSettingsUpdated();
		
		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));
		
		PaymentOTPPage objPaymentOTP = objAccountSettings.
		clickOnBackButton().
		clickOnMyEarnings().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		validateNEFTPaymentSettings().
		clickOnGETPAID();
		
		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);
		
		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickBackButton().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		validateNEFTPaymentSettings().
		enterBankNEFTPaymentDetails().
		clickOnSaveChangesButton().
		validatePaymentSettingsUpdated();
		
	
	}

	//All Possible Positive and Negative flows covered in only one test case
	@Test
	public void PaymentSettings_AmazonGiftCard() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();
		String email_2 = objCashKaroUtility.generateEmail();

		String otpValue = 
		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnLoginJoinButton().
		clickOnJoinFreeLink().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		
		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		AccountSettingsPage objAccountSettings = new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID().
		enterPasswordToConfirm_Amazon().
		clickOnSaveChangesButton().
		validatePaymentSettingsUpdated();
		
		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));
		
		PaymentOTPPage objPaymentOTP = objAccountSettings.
		clickOnBackButton().
		clickOnMyEarnings().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		validateGiftCardEmail().
		clickOnGETPAID();
		
		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);
		
		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickBackButton().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		validateGiftCardEmail().
		enterAmazonGiftCardEmailID(email_2).
		enterPasswordToConfirm_Amazon().
		clickOnSaveChangesButton().
		validatePaymentSettingsUpdated();
		
	
	}
	
	@Test
	public void PaymentSettings_FlipkartGiftCard() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnLoginJoinButton().
		clickOnJoinFreeLink().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		
		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		AccountSettingsPage objAccountSettings = new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectFlipKartGiftCardFromDropDown().
		enterFlipKartGiftCardEmailID().
		enterPasswordToConfirm_FlipKart().
		clickOnSaveChangesButton().
		validatePaymentSettingsUpdated();
		
		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));
		
		PaymentOTPPage objPaymentOTP = objAccountSettings.
		clickOnBackButton().
		clickOnMyEarnings().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectFlipKartGiftCardFromDropDown().
		validateGiftCardEmail().
		clickOnGETPAID();
		
		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);
		
		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickBackButton().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectFlipKartGiftCardFromDropDown().
		validateGiftCardEmail().
		enterFlipKartGiftCardEmailID().
		enterPasswordToConfirm_FlipKart().
		clickOnSaveChangesButton().
		validatePaymentSettingsUpdated();
	
	}

	@Test
	public void PaymentSettings_PaytmWallet() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String email = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		String mobileNum = objCashKaroUtility.generateMobileNumber();
		String otpValue = 
		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnLoginJoinButton().
		clickOnJoinFreeLink().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		
		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		AccountSettingsPage objAccountSettings = new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectPaytmWalletFromDropDown().
		enterPaytmWalletMobileNumber(mobileNumber).
		enterPasswordToConfirm_PaytmWallet().
		clickOnSaveChangesButton().
		validatePaymentSettingsUpdated();
		
		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));
		
		PaymentOTPPage objPaymentOTP = objAccountSettings.
		clickOnBackButton().
		clickOnMyEarnings().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectPaytmWalletCardFromDropDown().
		validateWalletMobileNumber(mobileNumber).
		clickOnGETPAID();
		
		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);
		
		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickBackButton().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectPaytmWalletFromDropDown().
		validateWalletMobileNumber(mobileNum).
		enterPaytmWalletMobileNumber(mobileNumber).
		enterPasswordToConfirm_PaytmWallet().
		clickOnSaveChangesButton().
		validatePaymentSettingsUpdated();
		
	
	}

	//Payment Settings Negative validations :
	//All Possible Positive and Negative flows covered in only one test case
	@Test
	public void PaymentSettings_NEFT_NegativeValidation() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnLoginJoinButton().
		clickOnJoinFreeLink().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		
		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		clickOnSaveChangesButton().
		validateNEFTFieldErrorMessages(). // Validate All fields error message
		clickOnBackButton().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterAlphabetsIntoAccountNumberAndValidateErrorMessage(). // alpha into Account Number field
		clickOnBackButton().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterSpecialCharacterIntoAccountNumberAndValidateErrorMessage().// special char  into Account Number field
		clickOnBackButton().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		validateInvalidIFSCCodeServerSideValidation(mobNum).
		clickOnBackButton().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterNumbersIntoAccountHolderNameAndvalidateErrorMessage().  
		clickOnBackButton().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterNumbersIntoBankNameAndvalidateErrorMessage().
		clickOnBackButton().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterSpecialCharacterIntoBankNameAndvalidateErrorMessage().
		clickOnBackButton().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterSpecialCharacterIntoBankBranchNameAndValidateErrorMessage().
		clickOnBackButton().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterDetailsWithOutPassword_And_ValidateTheError();
		
		
	}
	
	//All Possible Positive and Negative flows covered in only one test case
	@Test
	public void PaymentSettings_AmazonGC_NegativeValidation() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnLoginJoinButton().
		clickOnJoinFreeLink().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		
		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		clickOnSaveChangesButton().
		validateAmazonGiftCardErrorValidation_AllPossible();
		
		
	}

	//All Possible Positive and Negative flows covered in only one test case
	@Test
	public void PaymentSettings_FlipKart_NegativeValidation() {

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnLoginJoinButton().
		clickOnJoinFreeLink().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();


		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnAccountSettings().
		clickOnPaymentSettings().
		clickonPaymentMethodDropDown().
		selectFlipKartGiftCardFromDropDown().
		clickOnSaveChangesButton().
		validateFlipKartGiftCardErrorValidation_AllPossible();


	}
	
	//click on Recent Clicks
	@Test
	public void ViewAllDetails_RecentClickValidation() {
		
		/*
		 * Step 1 : Join As a new User and make an exit click 
		 */
		
		//Get the First Store Name
		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("CB_Store_One");
		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
		String retailerURL = objAppiumVariables.getRequiredLinkUrl("str_Store_One_linkURL");
		
		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		String otpValue = 
		new SeeVideoToEarnCashbackPage(driver, testInfo).
	    clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().
		enterEmail(email).
		enterPassword().
		enterMobileNumber(mobNum).
		clickOnGetOTPButton(mobNum);

		new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();

		//Once after clicking on the Click on verify OTP ,it should navigate to the Home page
		new HomePage(driver, testInfo).clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		clickOnStoreMainCTAForSignedInUser().
		validateRetailerPage(retailerURL).
		clickOnIntermediateBackbutton();
		
		/*
		 * Step 2 : Navigate to Admin and return the Exit click ID
		 */
		
		//Admin actions
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		adminFunctions.clickOnExitClick();
		adminFunctions.selectSearchByEmailFromExitClickPage();
		adminFunctions.enterSearchKeywordFromExitClickPage(email);
		adminFunctions.clickOnSearchButtonFromExitClickPage();
		
		String exitClickID = adminFunctions.extractExitClickFromTable();
		 
		System.out.println("Exit click id is : "+ exitClickID);
		
		objCashKaroUtility.backDateTheExitClickForGivenDate(exitClickID,4);
		
		/*
		 * Step 3 : Back the Exit click for 4 days and Raise the ticket in the Missing cashback page
		 */
		
		CashKaroUtility cuForCKApp_1 =  new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = cuForCKApp_1.launchCashKaroApp(driver);
		
		new HomePage(driver,testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMissingCashback().
		clickOnAddTicketButonForNewUser().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(storeOne).
		clickOnTransactionDetails().
		enterTransactionId(getTestData(10, "TransactionId")).
		enterTransactionAmountPaid(getTestData(10, "TransacationAmountPaid")).
		enterCouponCodeUsed(getTestData(10, "CouponCodeUsed")).
		enterTransactionDetails(getTestData(10, "TransactionDetails")).
		clickOnSubmitButton().
		clickOnBackButton().
		clickRecentClicks().
		clickViewDetailsButton();
	}
	
	@Test
	public void validateHindiEnglishVideoPlay() {

		reportStep("Started validateHindiEnglishVideoPlay", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnInHindiButton().
		clickOnCloseButton().
		clickOnInEnglishButton().
		clickOnCloseButton().
		clickOnInHindiButton().
		haltScriptTillVideoPlayCompletes().
		validateSeeVideoText().
		clickOnInEnglishButton().
		haltScriptTillVideoPlayCompletes().
		validateSeeVideoText();
		
		reportStep("Verified validateHindiEnglishVideoPlay", "INFO");

	}

	//@Test
	public void validateReferAndEarnPage() {
		
		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String email = objCashKaroUtility.generateEmail();
		String mobNum = objCashKaroUtility.generateMobileNumber();

		
		String otpValue = 
				new SeeVideoToEarnCashbackPage(driver, testInfo).
				clickOnLoginJoinButton().
				clickOnJoinFreeLink().
				enterFullName().
				enterEmail(email).
				enterPassword().
				enterMobileNumber(mobNum).
				clickOnGetOTPButton(mobNum);

				new OTPPage(driver, testInfo).
				enterOTP(otpValue).
				clickOnVerifyOTP();
				
				//Once after clicking on the Click on verify OTP , it should navigate to the Home page
				new HomePage(driver, testInfo).
				clickOnProfileIconForSignedUser().
				clickOnreferAndEarn().
				validateReferAndEarnPage();
	
	}
	

}