package com.ck.test;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.MoveToOffsetAction;
import org.testng.annotations.Test;

import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;
import com.ck.pages.SeeVideoToEarnCashbackPage;
import com.ck.pages.HomePage;
import com.ck.pages.MyEarningsPage;
import com.ck.pages.OTPPage;
import com.ck.pages.PaymentOTPPage;
import com.ck.pages.admin.AdminCoreFunction;
import com.ck.pages.admin.AdminPaymentSettingsPage;
import com.ck.pages.admin.AdminPendingCashoutsPage;

public class CKTestPaymentValidation extends WrapperMethods {

	@Test
	public void aaa_setAllPaymentMethod_CashbackTypeAs_All_And_PaymentMethod_StatusAs_Active(){

		reportStep("TC004 is  started", "INFO");


		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnSettings();
		adminFunctions.clickOnPartnerSettings();

		AdminPaymentSettingsPage adminPaymentSettingsPage = new AdminPaymentSettingsPage(driver, testInfo);

		adminPaymentSettingsPage.clickOnPartnerPaymentEditButton();

		for(int counter = 1 ;counter<=17;counter++) {

			if (counter == 4 || counter == 5 || counter == 6) {

				System.out.println("NO Payment method available to this Payment status ID " + counter + "So skipping to set the status ");

			}else if(counter == 1 || counter == 2 || counter == 9 || counter == 10 || counter == 11 || counter == 15 || counter == 16 || counter == 17){

				adminPaymentSettingsPage.editPaymentMethod_Select_Status(counter, "In-Active");

			}else{

				adminPaymentSettingsPage.editPaymentMethod_Select_Status(counter, "Active");
				adminPaymentSettingsPage.dropDown_Select_CashbackType(counter, "All");

			}
		}

		try {
			driver.findElement(By.id("isdefaultCashback_3")).click();
			driver.findElement(By.id("isDefaultRewards_12")).click();
			}catch (Exception e) {
				e.getMessage();
			}

			adminPaymentSettingsPage.clickOnUpdatePaymentSettingsButton();
			
			
			adminPaymentSettingsPage.clickOnEditPartnerButton();
			adminPaymentSettingsPage.setSignUpBonusAsZero();
			adminPaymentSettingsPage.setReferralBonusAsZero();
			adminPaymentSettingsPage.setCashOutLimitAsTwoFifty();
			adminPaymentSettingsPage.setConsecutiveLimitAsTwoFifty();
			
			//Click on Save button in the admin
			adminPaymentSettingsPage.clickOnUpdatePaymentSettingsButton();


	}

	//Payment scenarios :
	@Test
	public void payment_CB_0_RW_0_ReqCB_TC001() {

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

		OTPPage objOtpPage = 
				new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).
		clickOnVerifyOTP();

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage =
				new HomePage(driver, testInfo);

		objHomePage.clickOnProfileIconForSignedUser().
		clickOnPaymentsForThresholdNotReached();

	}

	@Test
	public void payment_CB_0_RW_0_ReqRW_TC002() {

		String email =  Utilities.generateEmailWithTimeStamp();
		String mobNum = Utilities.generateRandomNumber(10); 

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

		OTPPage objOtpPage = 
				new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).
		clickOnVerifyOTP();

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage =
				new HomePage(driver, testInfo);

		objHomePage.clickOnProfileIconForSignedUser().
		clickOnPaymentsForThresholdNotReached();

	}

	@Test
	public void payment_CB_250_RW_0_ReqCB_TC003() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestCashbackPaymentButton().
				paymentRequestPaymentAvailabilityValidation("Only_Cashback", getTestData(6, "TwoFifty"), getTestData(6, "Zero")).
				clickOnPaymentMethodDropDown().
				selectBankPaymentNEFTFromDropDown().
				enterBankNEFTPaymentDetails().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validatePaymentAlreadyPendingAlert();


	}

	@Test
	public void payment_CB_250_RW_0_ReqRW_Via_AmazonGC_TC004() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestRewardsPaymentButton().
				paymentRequestPaymentAvailabilityValidation("BothCashback_Rewards", getTestData(6, "TwoFifty"), getTestData(6, "Zero")).
				clickOnPaymentMethodDropDown().
				selectAmazonGiftCardFromDropDown().
				enterAmazonGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_250_RW_0_ReqRW_ViaFlipKartGC_TC004() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestRewardsPaymentButton().
				paymentRequestPaymentAvailabilityValidation("BothCashback_Rewards", getTestData(6, "TwoFifty"), getTestData(6, "Zero")).
				clickOnPaymentMethodDropDown().
				selectFlipKartGiftCardFromDropDown().
				enterFlipKartGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_0_RW_250_ReqCB_Via_AmazonGC_TC005() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestCashbackPaymentButtonForPaymentPopup().
				validateSorryTextForCashbackRequest().
				clickOnCombineCashbackAndRewardsButton().
				paymentRequestPaymentAvailabilityValidation("BothCashback_Rewards", getTestData(6, "Zero"), getTestData(6, "TwoFifty")).
				clickOnPaymentMethodDropDown().
				selectAmazonGiftCardFromDropDown().
				enterAmazonGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP

		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_0_RW_250_ReqCB_Via_FlipKartGC_TC005() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestCashbackPaymentButtonForPaymentPopup().
				validateSorryTextForCashbackRequest().
				clickOnCombineCashbackAndRewardsButton().
				paymentRequestPaymentAvailabilityValidation("BothCashback_Rewards", getTestData(6, "Zero"), getTestData(6, "TwoFifty")).
				clickOnPaymentMethodDropDown().
				selectFlipKartGiftCardFromDropDown().
				enterFlipKartGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP

		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_0_RW_250_ReqRW_Via_AmazonGC_TC006() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestRewardsPaymentButton().
				paymentRequestPaymentAvailabilityValidation("Only_Rewards", getTestData(6, "Zero"), getTestData(6, "TwoFifty")).
				clickOnPaymentMethodDropDown().
				selectAmazonGiftCardFromDropDown().
				enterAmazonGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_0_RW_250_ReqRW_Via_FlipKartGC_TC006() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestRewardsPaymentButton().
				paymentRequestPaymentAvailabilityValidation("Only_Rewards", getTestData(6, "Zero"), getTestData(6, "TwoFifty")).
				clickOnPaymentMethodDropDown().
				selectFlipKartGiftCardFromDropDown().
				enterFlipKartGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_10_RW_240_ReqCB_Via_AmazonGC_TC007() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "Ten"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoForty"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestCashbackPaymentButtonForPaymentPopup().
				validateSorryTextForCashbackRequest(Float.parseFloat(getTestData(6, "Ten"))+Float.parseFloat(getTestData(6, "TwoForty"))).
				clickOnCombineCashbackAndRewardsButton().
				paymentRequestPaymentAvailabilityValidation("BothCashback_Rewards", getTestData(6, "Ten"), getTestData(6, "TwoForty")).
				clickOnPaymentMethodDropDown().
				selectAmazonGiftCardFromDropDown().
				enterAmazonGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_10_RW_240_ReqCB_ViaFlipKartGC_TC007() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "Ten"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoForty"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestCashbackPaymentButtonForPaymentPopup().
				validateSorryTextForCashbackRequest(Float.parseFloat(getTestData(6, "Ten"))+Float.parseFloat(getTestData(6, "TwoForty"))).
				clickOnCombineCashbackAndRewardsButton().
				paymentRequestPaymentAvailabilityValidation("BothCashback_Rewards", getTestData(6, "Ten"), getTestData(6, "TwoForty")).
				clickOnPaymentMethodDropDown().
				selectFlipKartGiftCardFromDropDown().
				enterFlipKartGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_10_RW_240_ReqRW_Via_PaytmWallet_TC008() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "Ten"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoForty"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestRewardsPaymentButtonForPaymentPopup().
				validateThanksTextForRewardRequestWhenTheRewardAmountIsInsufficient(Float.parseFloat(getTestData(6, "TwoForty")), Float.parseFloat(getTestData(6, "Ten"))).
				clickOnCombineCashbackAndRewardsButton().
				paymentRequestPaymentAvailabilityValidation("BothCashback_Rewards", getTestData(6, "Ten"), getTestData(6, "TwoForty")).
				clickOnPaymentMethodDropDown().
				selectPaytmWalletCardFromDropDown().
				enterPaytmWalletMobileNumber(mobNum).
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_10_RW_240_ReqRW_Via_AmazonGC_TC008() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "Ten"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoForty"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestRewardsPaymentButtonForPaymentPopup().
				validateThanksTextForRewardRequestWhenTheRewardAmountIsInsufficient(Float.parseFloat(getTestData(6, "TwoForty")), Float.parseFloat(getTestData(6, "Ten"))).
				clickOnCombineCashbackAndRewardsButton().
				paymentRequestPaymentAvailabilityValidation("BothCashback_Rewards", getTestData(6, "Ten"), getTestData(6, "TwoForty")).
				clickOnPaymentMethodDropDown().
				selectAmazonGiftCardFromDropDown().
				enterAmazonGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_10_RW_240_ReqRW_Via_FlipKartGC_TC008() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "Ten"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoForty"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestRewardsPaymentButtonForPaymentPopup().
				validateThanksTextForRewardRequestWhenTheRewardAmountIsInsufficient(Float.parseFloat(getTestData(6, "TwoForty")), Float.parseFloat(getTestData(6, "Ten"))).
				clickOnCombineCashbackAndRewardsButton().
				paymentRequestPaymentAvailabilityValidation("BothCashback_Rewards", getTestData(6, "Ten"), getTestData(6, "TwoForty")).
				clickOnPaymentMethodDropDown().
				selectFlipKartGiftCardFromDropDown().
				enterFlipKartGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_240_RW_10_ReqCB_Via_AmazonGC_TC009() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoForty"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "Ten"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestCashbackPaymentButtonForPaymentPopup().
				validateSorryTextForCashbackRequest(Float.parseFloat(getTestData(6, "Ten"))+Float.parseFloat(getTestData(6, "TwoForty"))).
				clickOnCombineCashbackAndRewardsButton().
				paymentRequestPaymentAvailabilityValidation("BothCashback_Rewards", getTestData(6, "TwoForty"), getTestData(6, "Ten")).
				clickOnPaymentMethodDropDown().
				selectAmazonGiftCardFromDropDown().
				enterAmazonGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_240_RW_10_ReqCB_Via_FlipKartGC_TC009() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoForty"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "Ten"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestCashbackPaymentButtonForPaymentPopup().
				validateSorryTextForCashbackRequest(Float.parseFloat(getTestData(6, "Ten"))+Float.parseFloat(getTestData(6, "TwoForty"))).
				clickOnCombineCashbackAndRewardsButton().
				paymentRequestPaymentAvailabilityValidation("BothCashback_Rewards", getTestData(6, "TwoForty"), getTestData(6, "Ten")).
				clickOnPaymentMethodDropDown().
				selectFlipKartGiftCardFromDropDown().
				enterFlipKartGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_240_RW_10_ReqRW_Via_AmazonGC_TC011() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoForty"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "Ten"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestRewardsPaymentButtonForPaymentPopup().
				validateThanksTextForRewardRequestWhenTheRewardAmountIsInsufficient(Float.parseFloat(getTestData(6, "Ten")), Float.parseFloat(getTestData(6, "TwoForty"))).
				clickOnCombineCashbackAndRewardsButton().
				paymentRequestPaymentAvailabilityValidation("BothCashback_Rewards", getTestData(6, "TwoForty"), getTestData(6, "Ten")).
				clickOnPaymentMethodDropDown().
				selectAmazonGiftCardFromDropDown().
				enterAmazonGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_240_RW_10_ReqRW_Via_FlipKartGC_TC011() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoForty"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "Ten"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestRewardsPaymentButtonForPaymentPopup().
				validateThanksTextForRewardRequestWhenTheRewardAmountIsInsufficient(Float.parseFloat(getTestData(6, "Ten")), Float.parseFloat(getTestData(6, "TwoForty"))).
				clickOnCombineCashbackAndRewardsButton().
				paymentRequestPaymentAvailabilityValidation("BothCashback_Rewards", getTestData(6, "TwoForty"), getTestData(6, "Ten")).
				clickOnPaymentMethodDropDown().
				selectFlipKartGiftCardFromDropDown().
				enterFlipKartGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_250_RW_250_ReqCB_Combine_Via_AmazonGC_TC012() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestCashbackPaymentButtonForPaymentPopup().
				validateThanksTextForCashbackRequest_WhenTheRewardAmount_And_CasbhbackAmount_IsSufficient(Float.parseFloat(getTestData(6, "TwoFifty"))).
				clickOnCombineCashbackAndRewardsButton().
				paymentRequestPaymentAvailabilityValidation("BothCashback_Rewards", getTestData(6, "TwoFifty"), getTestData(6, "TwoFifty")).
				clickOnPaymentMethodDropDown().
				selectAmazonGiftCardFromDropDown().
				enterAmazonGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_250_RW_250_ReqCB_Combine_Via_FlipKartGC_TC012() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestCashbackPaymentButtonForPaymentPopup().
				validateThanksTextForCashbackRequest_WhenTheRewardAmount_And_CasbhbackAmount_IsSufficient(Float.parseFloat(getTestData(6, "TwoFifty"))).
				clickOnCombineCashbackAndRewardsButton().
				paymentRequestPaymentAvailabilityValidation("BothCashback_Rewards", getTestData(6, "TwoFifty"), getTestData(6, "TwoFifty")).
				clickOnPaymentMethodDropDown().
				selectFlipKartGiftCardFromDropDown().
				enterFlipKartGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_250_RW_250_ReqCB_OnlyRedeemCashback_TC013() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestCashbackPaymentButtonForPaymentPopup().
				validateThanksTextForCashbackRequest_WhenTheRewardAmount_And_CasbhbackAmount_IsSufficient(Float.parseFloat(getTestData(6, "TwoFifty"))).
				clickOnOnlyRedeemCashbackButton().
				paymentRequestPaymentAvailabilityValidation("Only_Cashback", getTestData(6, "TwoFifty"), getTestData(6, "TwoFifty")).
				clickOnPaymentMethodDropDown().
				selectBankPaymentNEFTFromDropDown().
				enterBankNEFTPaymentDetails().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_250_RW_250_ReqRW_Combine_Via_Amazon_TC014() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestRewardsPaymentButtonForPaymentPopup().
				validateThanksTextForRewardsRequest_WhenTheRewardAmount_And_CasbhbackAmount_IsSufficient(Float.parseFloat(getTestData(6, "TwoFifty"))).
				clickOnCombineCashbackAndRewardsButton().
				paymentRequestPaymentAvailabilityValidation("BothCashback_Rewards", getTestData(6, "TwoFifty"), getTestData(6, "TwoFifty")).
				clickOnPaymentMethodDropDown().
				selectFlipKartGiftCardFromDropDown().
				enterFlipKartGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_250_RW_250_ReqRW_Combine_Via_FlipKart_TC014() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestRewardsPaymentButtonForPaymentPopup().
				validateThanksTextForRewardsRequest_WhenTheRewardAmount_And_CasbhbackAmount_IsSufficient(Float.parseFloat(getTestData(6, "TwoFifty"))).
				clickOnCombineCashbackAndRewardsButton().
				paymentRequestPaymentAvailabilityValidation("BothCashback_Rewards", getTestData(6, "TwoFifty"), getTestData(6, "TwoFifty")).
				clickOnPaymentMethodDropDown().
				selectAmazonGiftCardFromDropDown().
				enterAmazonGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_250_RW_250_ReqRW_OnlyRedeemRewards_Via_AmazonGC_TC015() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestRewardsPaymentButtonForPaymentPopup().
				validateThanksTextForRewardsRequest_WhenTheRewardAmount_And_CasbhbackAmount_IsSufficient(Float.parseFloat(getTestData(6, "TwoFifty"))).
				clickOnOnlyRedeemRewardsButton().
				paymentRequestPaymentAvailabilityValidation("Only_Rewards", getTestData(6, "TwoFifty"), getTestData(6, "TwoFifty")).
				clickOnPaymentMethodDropDown().
				selectFlipKartGiftCardFromDropDown().
				enterFlipKartGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	@Test
	public void payment_CB_250_RW_250_ReqRW_OnlyRedeemRewards_Via_FlipKartGC_TC015() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestRewardsPaymentButtonForPaymentPopup().
				validateThanksTextForRewardsRequest_WhenTheRewardAmount_And_CasbhbackAmount_IsSufficient(Float.parseFloat(getTestData(6, "TwoFifty"))).
				clickOnOnlyRedeemRewardsButton().
				paymentRequestPaymentAvailabilityValidation("Only_Rewards", getTestData(6, "TwoFifty"), getTestData(6, "TwoFifty")).
				clickOnPaymentMethodDropDown().
				selectAmazonGiftCardFromDropDown().
				enterAmazonGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();


	}

	//Consecutive payment
	//@Test
	public void consecutivePayment_CB_10_RW_10_ReqCB_TC016() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestCashbackPaymentButton().
				clickOnPaymentMethodDropDown().
				selectBankPaymentNEFTFromDropDown().
				enterBankNEFTPaymentDetails().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		MyEarningsPage objMyEarnings = objPaymentOTP.
				enterOTP(otpValue).
				clickOnVerifyOTPAndValidateThePaymentSuccessMessage();

		/********************************************************************************/
		/*************************************
		 * 		CASHOUT
		 ************************************/
		/********************************************************************************/
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(email);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(email);
		PendingCashoutsPage.clickCreateCashout();
		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();


		//Add bonus
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "Ten"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "Ten"), getTestData(7, "Rewards"));

		//Navigate to My earnings page
		//Click on Request cashback button
		//Validate Threshold not reached message
		objMyEarnings.
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached();



	}

	//@Test
	public void consecutivePayment_CB_10_RW_10_ReqRW_TC017() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestCashbackPaymentButton().
				clickOnPaymentMethodDropDown().
				selectBankPaymentNEFTFromDropDown().
				enterBankNEFTPaymentDetails().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		MyEarningsPage objMyEarnings = objPaymentOTP.
				enterOTP(otpValue).
				clickOnVerifyOTPAndValidateThePaymentSuccessMessage();

		/********************************************************************************/
		/*************************************
		 * 		CASHOUT
		 ************************************/
		/********************************************************************************/
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(email);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(email);
		PendingCashoutsPage.clickCreateCashout();
		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();



		//Add bonus
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "Ten"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "Ten"), getTestData(7, "Rewards"));

		//Navigate to My earnings page
		//Click on Request cashback button
		//Validate Threshold not reached message
		objMyEarnings.
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached();


	}

	//@Test
	public void consecutivePayment_CB_250_RW_10_ReqCB_OnlyRedeemCashback_TC018() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestRewardsPaymentButtonForPaymentPopup().
				validateThanksTextForRewardsRequest_WhenTheRewardAmount_And_CasbhbackAmount_IsSufficient(Float.parseFloat(getTestData(6, "TwoFifty"))).
				clickOnCombineCashbackAndRewardsButton().
				clickOnPaymentMethodDropDown().
				selectAmazonGiftCardFromDropDown().
				enterAmazonGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		MyEarningsPage objMyEarnings = objPaymentOTP.
				enterOTP(otpValue).
				clickOnVerifyOTPAndValidateThePaymentSuccessMessage();

		/********************************************************************************/
		/*************************************
		 * 		CASHOUT
		 ************************************/
		/********************************************************************************/
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(email);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(email);
		PendingCashoutsPage.clickCreateCashout();
		sleep(2000);
		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();



		//Add bonus
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "Ten"), getTestData(7, "Rewards"));

		//Navigate to My earnings page
		//Click on Request cashback button
		//Validate Threshold not reached message
		objMyEarnings.
		clickOnRequestCashbackPaymentButtonForPaymentPopup().
		validateThanksTextForCashbackRequest_WhenTheRewardAmount_IsInSufficient_And_CasbhbackAmount_IsSufficient(Float.parseFloat(getTestData(6, "Ten"))).
		clickOnOnlyRedeemCashbackButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterBankNEFTPaymentDetails().
		clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();

	}

	//@Test
	public void consecutivePayment_CB_10_RW_250_ReqRW_TC019() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Rewards"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestRewardsPaymentButtonForPaymentPopup().
				validateThanksTextForRewardsRequest_WhenTheRewardAmount_And_CasbhbackAmount_IsSufficient(Float.parseFloat(getTestData(6, "TwoFifty"))).
				clickOnOnlyRedeemRewardsButton().
				clickOnPaymentMethodDropDown().
				selectAmazonGiftCardFromDropDown().
				enterAmazonGiftCardEmailID().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		MyEarningsPage objMyEarnings = objPaymentOTP.
				enterOTP(otpValue).
				clickOnVerifyOTPAndValidateThePaymentSuccessMessage();

		/********************************************************************************/
		/*************************************
		 * 		CASHOUT
		 ************************************/
		/********************************************************************************/
		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(email);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(email);
		PendingCashoutsPage.clickCreateCashout();
		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();



		//Navigate to My earnings page
		//Click on Request cashback button
		//Validate Threshold not reached message
		objMyEarnings.
		clickOnRequestRewardsPaymentButton().
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID().
		clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();

	}

	@Test
	public void paymentPageValidation_TCO20() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		objHomePage.clickOnProfileIconForSignedUser().
		clickOnPayments().
		validateCashbackEarningsAmount(getTestData(6, "TwoFifty")).
		validateTotalCashbackEarningsAmount(getTestData(6, "TwoFifty")).
		validateTotalRewardsEarningsAmount(getTestData(6, "Zero")).
		validateCashbackRewardsEarningAmount(getTestData(6, "TwoFiftyZero"),getTestData(6, "ZeroZero")).
		validateBelowSectionRulesAndOtherTextLabels().
		clickOnPaymentEarningsInfoIcon().
		validateEarningsInfoPoints().
		clickOnBackButton();


	}

	//With Charity
	@Test
	public void payment_CB_250_RW_0_ReqCBWithNEFTCharityAmount_TC010_A() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestCashbackPaymentButton().
				clickOnPaymentMethodDropDown().
				selectBankPaymentNEFTFromDropDown().
				enterBankNEFTPaymentDetails().
				clickOnYescharityButton().
				clickOnCharityCloseButton().
				clickOnYescharityButton().
				clickOnChooseCharity().
				clickOnFirstCharityOption().
				enterCharityAmountAndvalidateCharityLeftOutAmount().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndvalidateCharityDonationAmountSuccessMessage();


	}

	@Test
	public void payment_CB_250_RW_0_ReqRWWithAmazonCharityAmount_TC010_B() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestRewardsPaymentButton().
				clickOnPaymentMethodDropDown().
				selectAmazonGiftCardFromDropDown().
				enterAmazonGiftCardEmailID().
				clickOnYescharityButton().
				clickOnCharityCloseButton().
				clickOnYescharityButton().
				clickOnChooseCharity().
				clickOnFirstCharityOption().
				enterCharityAmountAndvalidateCharityLeftOutAmount().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndvalidateCharityDonationAmountSuccessMessage();


	}

	@Test
	public void payment_CB_250_RW_0_ReqRWWithFlipKartCharityAmount_TC010_B() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestRewardsPaymentButton().
				clickOnPaymentMethodDropDown().
				selectFlipKartGiftCardFromDropDown().
				enterFlipKartGiftCardEmailID().
				clickOnYescharityButton().
				clickOnCharityCloseButton().
				clickOnYescharityButton().
				clickOnChooseCharity().
				clickOnFirstCharityOption().
				enterCharityAmountAndvalidateCharityLeftOutAmount().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndvalidateCharityDonationAmountSuccessMessage();


	}

	@Test
	public void payment_CB_250_RW_0_ReqRWWithPaytmWalletCharityAmount_TC010_C() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestRewardsPaymentButton().
				clickOnPaymentMethodDropDown().
				selectPaytmWalletCardFromDropDown().
				enterPaytmWalletMobileNumber(mobNum).
				clickOnYescharityButton().
				clickOnCharityCloseButton().
				clickOnYescharityButton().
				clickOnChooseCharity().
				clickOnFirstCharityOption().
				enterCharityAmountAndvalidateCharityLeftOutAmount().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndvalidateCharityDonationAmountSuccessMessage();


	}

	//Negative validations : 
	@Test
	public void payment_negativeErrorValidationForNEFT_TC021() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		objHomePage.clickOnProfileIconForSignedUser().
		clickOnPayments().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		clickOnGETPAIDForFailure().
		validateNEFTFieldErrorMessages().   // All the field error messages
		clickOnBackButton().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterAlphabetsIntoAccountNumberAndValidateErrorMessage(). // alpha into Account Number field
		clickOnBackButton().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterSpecialCharacterIntoAccountNumberAndValidateErrorMessage(). // specialchar into ac number field
		clickOnBackButton().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		validatePleaseEnterIFSC(). //ifsc serverside error validation
		clickOnBackButton().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterNumbersIntoAccountHolderNameAndvalidateErrorMessage(). // Number into  into ac ac holder name
		clickOnBackButton().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterNumbersIntoBankNameAndvalidateErrorMessage().
		clickOnBackButton().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterSpecialCharacterIntoBankNameAndvalidateErrorMessage(). //special char into bank name
		clickOnBackButton().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		validatePleaseEnterBranchNameError(); //server side validation into Bank branch name


	}

	@Test
	public void payment_negativeErrorValidationForAmazonGiftCard_TC022() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));


		objHomePage.clickOnProfileIconForSignedUser().
		clickOnPayments().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		clickOnGETPAIDForFailure().
		validateAmazonGiftCardErrorValidation_AllPossible();


	}

	@Test
	public void payment_negativeErrorValidationForFlipKartGiftCard_TC022() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));


		objHomePage.clickOnProfileIconForSignedUser().
		clickOnPayments().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectFlipKartGiftCardFromDropDown().
		clickOnGETPAIDForFailure().
		validateFlipKartGiftCardErrorValidation_AllPossible();


	}

	@Test
	public void payment_negativeErrorValidationForCharitySection_TC023() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.
		addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		objHomePage.clickOnProfileIconForSignedUser().
		clickOnPayments().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterBankNEFTPaymentDetails().
		clickOnYescharityButton().
		enterCharityAmountAndvalidateCharityLeftOutAmount(). //Enter charity amount and validate leftout amount
		clickOnGETPAIDForFailure().
		validatePleaseSelectCharityErrorMessage(). //Without selecting charity
		clickOnChooseCharity().
		clickOnFirstCharityOption().
		enterCharityAmount(getTestData(6, "CharityExceedAmount")).
		clickOnGETPAIDForFailure().
		validateExceedCharityErrorMessage().// Validating exceed amount error in charity
		enterCharityAmount(getTestData(6, "MultipleZeroCharityAmount")).
		clickOnGETPAIDForFailure().
		validatePleaseEnterSomeAmount().
		enterCharityAmountAndvalidateCharityLeftOutAmount(getTestData(6, "TwoFifty")).
		enterCharityAmount("InvalidStringText").
		clickOnGETPAIDForFailure().
		validatePleaseEnterSomeAmount().
		//enterCharityAmount("51*a.,:").
		//clickOnGETPAIDForFailure().
		//validatePleaseEnterSomeAmount().
		enterCharityAmount("xyz@Test.com").
		validateInvalidDonationAmountErrorMessage();




	}

	@Test
	public void paymentOTP_NegativeValidation_TC024() {

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

		//Once after clicking on the Click on verify OTP , it should navigate to the Home page
		HomePage objHomePage = new HomePage(driver, testInfo);

		//Adding the bonus using getcode.php - contact dev team for the getcode
		objCashKaroUtility.addCashbackBonus(email, getTestData(6, "TwoFifty"), getTestData(7, "Cashback"));

		PaymentOTPPage objPaymentOTP = objHomePage.clickOnProfileIconForSignedUser().
				clickOnPayments().
				clickOnRequestCashbackPaymentButton().
				paymentRequestPaymentAvailabilityValidation("Only_Cashback", getTestData(6, "TwoFifty"), getTestData(6, "Zero")).
				clickOnPaymentMethodDropDown().
				selectBankPaymentNEFTFromDropDown().
				enterBankNEFTPaymentDetails().
				clickOnGETPAID();

		//SignUp OTP and payment OTP are same  , so Sign up OTP has been used to get the Payment OTP
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		verifyReSendCodeIn().
		enterOTP(getTestData(6, "InvalidOTP")).
		clickOnVerifyOTPforFailure().
		verifyInValidOTP().
		waitFor3Minutes().
		enterOTP(otpValue).
		clickOnVerifyOTPforFailure().
		validateOTPExpiredvalidation().
		clickOnResendCode();

		sleep(5000);
		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.
		clickOnBackButton().
		clickOnGETPAID().
		enterOTP(otpValue).
		clickOnVerifyOTPforFailure().
		verifyInValidOTP().
		waitFor30Seconds().
		clickOnResendCode();

		otpValue = objCashKaroUtility.generatePaymentOTP(mobNum);

		objPaymentOTP.enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage();



	}

}