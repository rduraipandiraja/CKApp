package com.ck.test;

import org.testng.annotations.Test;

import com.app.ck.base.AppDynamicVariables;
import com.app.ck.base.GetCodeSetup;
import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;
import com.ck.pages.HomePage;
import com.ck.pages.IntermediateRetailerPage;
import com.ck.pages.MyEarningsPage;
import com.ck.pages.OTPPage;
import com.ck.pages.PaymentOTPPage;
import com.ck.pages.ProfilePage;
import com.ck.pages.SeeVideoToEarnCashbackPage;
import com.ck.pages.SignedInProfilePage;
import com.ck.pages.StorePage;
import com.ck.pages.admin.AdminBonusCreditPage;
import com.ck.pages.admin.AdminCashbackPage;
import com.ck.pages.admin.AdminCoreFunction;
import com.ck.pages.admin.AdminReportsPage;


public class CKTestDeferredDeepLinkValidation extends WrapperMethods {
	
	

	/*********************************************************************/
	/*********************************************************************/

	@Test
	public void deferredDLFromStorePage_NormalSignUp() {

		reportStep("Deferred Deep linking Validation and Normal SignUp then validate the Retailer Navigationn", "INFO");
		
		CashKaroUtility cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);

		cu.removeApp(driver,"com.cashkaro");

		driver = cu.launchChrome();
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetup.ONELINK);
		cu.clickOneLink(driver);	
		cu.playStoreRedirectionVerification(driver);

		driver = cu.launchCashKaroApp((System.getProperty("user.dir") + "/apk/ckapp.apk"));
		
		cu.allowLinkInPermissionPopup(driver);

		//Once after signing up, it should navigate to deferred deep link Page (Amazon)
		String OtpValue = new StorePage(driver, testInfo).
		validateAmazonStoreDeferredDL().
		clickOnStoreMainCTA().
		clickOnJoinFreeLink().
		enterAllJoinFreeFieldsAndVerifyOTP();

		new OTPPage(driver, testInfo).
		enterOTP(OtpValue).
		clickOnVerifyOTP();
		
		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage("amazon", "amazon");
		
		reportStep("Deferred Deep linking Validation and Normal SignUp then validate the Retailer Navigation - Completed :)", "INFO");

	}

	@Test
	public void deferredDLFromStorePage_FacebookSignUp() {

		reportStep("Deferred Deep linking Validation and Facebook SignUp then validate the Retailer Navigation - Started :)", "INFO");

		CashKaroUtility cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
		
		
		try {
			if(Utilities.fbAccountReset(getTestData(1, "FBTestUserEmail2")).equals("Success")){

				reportStep("Successfully reset the Facebook account ", "INFO");

			}else {

				reportStep("Not able to reset the Facebook account ", "FAIL");
			}
		}
		catch (Exception e) {

		}

		cu.removeApp(driver,"com.cashkaro");

		driver = cu.launchChrome();
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetup.ONELINK);
		cu.clickOneLink(driver);
		
		cu.playStoreRedirectionVerification(driver);
		driver = cu.launchCashKaroApp((System.getProperty("user.dir") + "/apk/ckapp.apk"));

		cu.allowLinkInPermissionPopup(driver);

		//Once after signing up, it should navigate to deferred deep link Page (Amazon)
		String OtpValue = new StorePage(driver, testInfo).
		validateAmazonStoreDeferredDL().
		clickOnStoreMainCTA().
		clickOnJoinFreeLink().
		clickOnJoinWithFacebook().
		enterFBNewUserEmail().
		enterNewFBUserPassword().
		clickOnFBLoginButton().
		clickOnContinueForNewUser().
		enterMobileNumberAndGetTheOTP();

		//creating the new object for the OTP page
		new OTPPage(driver, testInfo).
		enterOTP(OtpValue).
		clickOnVerifyOTP();

		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage("amazon", "amazon");
		
		try {
			
			if(Utilities.fbAccountReset(getTestData(1, "FBTestUserEmail2")).equals("Success")){

				reportStep("Successfully reset the Facebook account ", "INFO");

			}else {

				reportStep("Not able to reset the Facebook account ", "FAIL");
			}
		}
		catch (Exception e) {

		}

		reportStep("Deferred Deep linking Validation and Facebook SignUp then validate the Retailer Navigation - Completed :)", "INFO");

	}

	@Test
	public void deferredDLFromStorePage_NormalLogin() {
		
		reportStep("Deferred Deep linking Validation and Normal Login then validate the Retailer Navigation - Started :)", "INFO");

		CashKaroUtility cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
		cu.removeApp(driver,"com.cashkaro");
		driver = cu.launchChrome();
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetup.ONELINK);
		cu.clickOneLink(driver);
		
		cu.playStoreRedirectionVerification(driver);
		driver = cu.launchCashKaroApp((System.getProperty("user.dir") + "/apk/ckapp.apk"));

		cu.allowLinkInPermissionPopup(driver);

		//Once after signing up, it should navigate to deferred deep link Page (Amazon)
		new StorePage(driver, testInfo).
		validateAmazonStoreDeferredDL().
		clickOnStoreMainCTA().
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation().
		validateRetailerPage("amazon", "amazon");

		reportStep("Deferred Deep linking Validation and Normal Login then validate the Retailer Navigation - Completed :)", "INFO");

	}

	@Test
	public void deferredDLFromStorePage_FBLogin() {
		
		reportStep("Deferred Deep linking Validation and Facebook Login then validate the Retailer Navigation - Started :)", "INFO");
		
		CashKaroUtility cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);

		cu.removeApp(driver,"com.cashkaro");

		driver = cu.launchChrome();
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetup.ONELINK);
		cu.clickOneLink(driver);
		
		cu.playStoreRedirectionVerification(driver);
		driver = cu.launchCashKaroApp((System.getProperty("user.dir") + "/apk/ckapp.apk"));

		cu.allowLinkInPermissionPopup(driver);

		//Once after signing up, it should navigate to deferred deep link Page (Amazon)
		new StorePage(driver, testInfo).
		validateAmazonStoreDeferredDL().
		clickOnStoreMainCTA().
		clickOnSignInWithFacebook().
		enterFBUserEmail().
		enterFBPassword().
		clickOnFBLoginButton().
		clickOnContinueForIntermediatePageNavigation();

	
		reportStep("Deferred Deep linking Validation and Facebook Login then validate the Retailer Navigation - Completed :)", "INFO");

	}

	@Test
	public void deferredDLFromStorePage_FBSignUpFromLoginPage() {

		reportStep("Deferred Deep linking Validation and Facebook SignUp from Login Page then validate the Retailer Navigation - Started :)", "INFO");

		CashKaroUtility cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);


		try {
			if(Utilities.fbAccountReset(getTestData(1, "FBTestUserEmail2")).equals("Success")){

				reportStep("Successfully reset the Facebook account ", "INFO");

			}else {

				reportStep("Not able to reset the Facebook account ", "FAIL");
			}
		}
		catch (Exception e) {

		}

		cu.removeApp(driver,"com.cashkaro");

		driver = cu.launchChrome();

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetup.ONELINK);
		cu.clickOneLink(driver);

		cu.playStoreRedirectionVerification(driver);
		driver = cu.launchCashKaroApp((System.getProperty("user.dir") + "/apk/ckapp.apk"));

		cu.allowLinkInPermissionPopup(driver);

		//Once after signing up, it should navigate to deferred deep link Page (Amazon)
		String OtpValue = 
				new StorePage(driver, testInfo).
				validateAmazonStoreDeferredDL().
				clickOnStoreMainCTA().
				clickOnSignInWithFacebook().
				enterFBNewUserEmail().
				enterNewFBUserPassword().
				clickOnFBLoginButton().
				clickOnContinueForNewUser().
				enterMobileNumberAndGetTheOTP();

		//creating the new object for the OTP page

		OTPPage  objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		try {

			if(Utilities.fbAccountReset(getTestData(1, "FBTestUserEmail2")).equals("Success")){

				reportStep("Successfully reset the Facebook account ", "INFO");

			}else {

				reportStep("Not able to reset the Facebook account ", "FAIL");
			}
		}
		catch (Exception e) {

		}

		reportStep("Deferred Deep linking Validation and FB S up from Login page then validate the Retailer Navigation - Started :)", "INFO");

	}

	/*********************************************************************/
	/*********************************************************************/
	@Test
	public void deferredDLFromStorePageVoucherbutton_NormalSignUp() {

		reportStep("Deferred Deep linking Validation and Normal Sign UP then validate the Retailer Navigation by clicking Voucher CTA - Started :)", "INFO");
		
		CashKaroUtility cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);

		cu.removeApp(driver,"com.cashkaro");

		driver = cu.launchChrome();
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetup.ONELINK);
		cu.clickOneLink(driver);	
		cu.playStoreRedirectionVerification(driver);

		driver = cu.launchCashKaroApp((System.getProperty("user.dir") + "/apk/ckapp.apk"));
		
		cu.allowLinkInPermissionPopup(driver);

		//Once after signing up, it should navigate to deferred deep link Page (Amazon)
		String OtpValue = new StorePage(driver, testInfo).
		validateAmazonStoreDeferredDL().
		clickOnVoucherButtonForUnsignedUser(0, getTestData(0, "DefferedDeepLinkStore")).
		clickOnJoinFreeLink().
		enterAllJoinFreeFieldsAndVerifyOTP();

		new OTPPage(driver, testInfo).
		enterOTP(OtpValue).
		clickOnVerifyOTP();
		
		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage("amazon", "amazon");
		
		reportStep("Deferred Deep linking Validation and Normal Sign UP then validate the Retailer Navigation by clicking Voucher CTA - Completed :)", "INFO");

	}

	@Test
	public void deferredDLFromStorePageVoucherbutton_FacebookSignUp() {

		reportStep("Deferred Deep linking Validation and Facebook Sign UP then validate the Retailer Navigation by clicking Voucher CTA - Started :)", "INFO");

		CashKaroUtility cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
		
		
		try {
			if(Utilities.fbAccountReset(getTestData(1, "FBTestUserEmail2")).equals("Success")){

				reportStep("Successfully reset the Facebook account ", "INFO");

			}else {

				reportStep("Not able to reset the Facebook account ", "FAIL");
			}
		}
		catch (Exception e) {

		}

		cu.removeApp(driver,"com.cashkaro");

		driver = cu.launchChrome();
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetup.ONELINK);
		cu.clickOneLink(driver);
		
		cu.playStoreRedirectionVerification(driver);
		driver = cu.launchCashKaroApp((System.getProperty("user.dir") + "/apk/ckapp.apk"));

		cu.allowLinkInPermissionPopup(driver);

		//Once after signing up, it should navigate to deferred deep link Page (Amazon)
		String OtpValue = new StorePage(driver, testInfo).
		validateAmazonStoreDeferredDL().
		clickOnVoucherButtonForUnsignedUser(0, getTestData(0, "DefferedDeepLinkStore")).
		clickOnJoinFreeLink().
		clickOnJoinWithFacebook().
		enterFBNewUserEmail().
		enterNewFBUserPassword().
		clickOnFBLoginButton().
		clickOnContinueForNewUser().
		enterMobileNumberAndGetTheOTP();

		//creating the new object for the OTP page
		new OTPPage(driver, testInfo).
		enterOTP(OtpValue).
		clickOnVerifyOTP();

		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage("amazon", "amazon");


		try {
			
			if(Utilities.fbAccountReset(getTestData(1, "FBTestUserEmail2")).equals("Success")){

				reportStep("Successfully reset the Facebook account ", "INFO");

			}else {

				reportStep("Not able to reset the Facebook account ", "FAIL");
			}
		}
		catch (Exception e) {

		}

		reportStep("Deferred Deep linking Validation and Normal Sign UP then validate the Retailer Navigation by clicking Voucher CTA - Completed :)", "INFO");

	}

	@Test
	public void deferredDLFromStorePageVoucherbutton_NormalLogin() {
		
		reportStep("Deferred Deep linking Validation and Normal login then validate the Retailer Navigation by clicking Voucher CTA - Started :)", "INFO");

		CashKaroUtility cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);

		cu.removeApp(driver,"com.cashkaro");

		driver = cu.launchChrome();
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetup.ONELINK);
		cu.clickOneLink(driver);
		
		cu.playStoreRedirectionVerification(driver);
		driver = cu.launchCashKaroApp((System.getProperty("user.dir") + "/apk/ckapp.apk"));

		cu.allowLinkInPermissionPopup(driver);

		//Once after signing up, it should navigate to deferred deep link Page (Amazon)
		new StorePage(driver, testInfo).
		validateAmazonStoreDeferredDL().
		clickOnVoucherButtonForUnsignedUser(0, getTestData(0, "DefferedDeepLinkStore")).
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation().
		validateRetailerPage("amazon", "amazon");

		reportStep("Deferred Deep linking Validation and Normal login then validate the Retailer Navigation by clicking Voucher CTA - Completed :)", "INFO");

	}

	@Test
	public void deferredDLFromStorePageVoucherbutton_FBLogin() {
		
		reportStep("Deferred Deep linking Validation and Facebook login then validate the Retailer Navigation by clicking Voucher CTA - Started :)", "INFO");

		CashKaroUtility cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);

		cu.removeApp(driver,"com.cashkaro");

		driver = cu.launchChrome();
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetup.ONELINK);
		cu.clickOneLink(driver);
		
		cu.playStoreRedirectionVerification(driver);
		driver = cu.launchCashKaroApp((System.getProperty("user.dir") + "/apk/ckapp.apk"));

		cu.allowLinkInPermissionPopup(driver);

		//Once after signing up, it should navigate to deferred deep link Page (Amazon)
		new StorePage(driver, testInfo).
		validateAmazonStoreDeferredDL().
		clickOnVoucherButtonForUnsignedUser(0, getTestData(0, "DefferedDeepLinkStore")).
		clickOnSignInWithFacebook().
		enterFBUserEmail().
		enterFBPassword().
		clickOnFBLoginButton().
		clickOnContinueForIntermediatePageNavigation();

		reportStep("Deferred Deep linking Validation and Facebook login then validate the Retailer Navigation by clicking Voucher CTA - Completed :)", "INFO");

	}

	@Test
	public void deferredDLFromStorePageVoucherbutton_FBSignUpFromLoginPage() {

		reportStep("Deferred Deep linking Validation and Facebook SignUp then validate the Retailer Navigation by clicking Voucher CTA - Started :)", "INFO");

		CashKaroUtility cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);


		try {
			if(Utilities.fbAccountReset(getTestData(1, "FBTestUserEmail2")).equals("Success")){

				reportStep("Successfully reset the Facebook account ", "INFO");

			}else {

				reportStep("Not able to reset the Facebook account ", "FAIL");
			}
		}
		catch (Exception e) {

		}

		cu.removeApp(driver,"com.cashkaro");

		driver = cu.launchChrome();

		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetup.ONELINK);
		cu.clickOneLink(driver);

		cu.playStoreRedirectionVerification(driver);
		driver = cu.launchCashKaroApp((System.getProperty("user.dir") + "/apk/ckapp.apk"));

		cu.allowLinkInPermissionPopup(driver);

		//Once after signing up, it should navigate to deferred deep link Page (Amazon)
		String OtpValue = 
				new StorePage(driver, testInfo).
				validateAmazonStoreDeferredDL().
				clickOnVoucherButtonForUnsignedUser(0, getTestData(0, "DefferedDeepLinkStore")).
				clickOnSignInWithFacebook().
				enterFBNewUserEmail().
				enterNewFBUserPassword().
				clickOnFBLoginButton().
				clickOnContinueForNewUser().
				enterMobileNumberAndGetTheOTP();

		//creating the new object for the OTP page

		OTPPage  objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(OtpValue).clickOnVerifyOTP();

		try {

			if(Utilities.fbAccountReset(getTestData(1, "FBTestUserEmail2")).equals("Success")){

				reportStep("Successfully reset the Facebook account ", "INFO");

			}else {

				reportStep("Not able to reset the Facebook account ", "FAIL");
			}
		}
		catch (Exception e) {

		}

		reportStep("Deferred Deep linking Validation and Facebook SignUp then validate the Retailer Navigation by clicking Voucher CTA - Completed :)", "INFO");

	}


	@Test
	public void reEngagementThroughStoreMainCTA() {

		reportStep("ReEngagement through Main CTA", "INFO");
		
		CashKaroUtility cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);

		cu.removeApp(driver,"com.cashkaro");

		driver = cu.launchChrome();
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetup.ONELINK);
		cu.clickOneLink(driver);	
		cu.playStoreRedirectionVerification(driver);

		driver = cu.launchCashKaroApp((System.getProperty("user.dir") + "/apk/ckapp.apk"));
		
		cu.allowLinkInPermissionPopup(driver);

		//Once after signing up, it should navigate to deferred deep link Page (Amazon)
		String OtpValue = new StorePage(driver, testInfo).
		validateAmazonStoreDeferredDL().
		clickOnStoreMainCTA().
		clickOnJoinFreeLink().
		enterAllJoinFreeFieldsAndVerifyOTP();

		new OTPPage(driver, testInfo).
		enterOTP(OtpValue).
		clickOnVerifyOTP();
		
		new IntermediateRetailerPage(driver, testInfo);
		
		toggleApp();
		closeCashKaroApp(deviceName);
		
		cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
	
		driver = cu.launchChrome();
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetup.ONELINK);
		cu.clickOneLink_ReEngagement(driver);	
		
		new StorePage(driver, testInfo).
		validateAmazonStoreDeferredDL().
		clickOnStoreMainCTAForSignedInUser();
		
		reportStep("ReEngagement through Main CTA - Ended", "INFO");

	}

	@Test
	public void reEngagementThroughVoucherCTA() {

		reportStep("ReEngagement through Voucher CTA", "INFO");
		
		CashKaroUtility cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);

		cu.removeApp(driver,"com.cashkaro");

		driver = cu.launchChrome();
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetup.ONELINK);
		cu.clickOneLink(driver);	
		cu.playStoreRedirectionVerification(driver);

		driver = cu.launchCashKaroApp((System.getProperty("user.dir") + "/apk/ckapp.apk"));
		
		cu.allowLinkInPermissionPopup(driver);

		//Once after signing up, it should navigate to deferred deep link Page (Amazon)
		String OtpValue = new StorePage(driver, testInfo).
		validateAmazonStoreDeferredDL().
		clickOnVoucherButtonForUnsignedUser(0, getTestData(0, "DefferedDeepLinkStore")).
		clickOnJoinFreeLink().
		enterAllJoinFreeFieldsAndVerifyOTP();

		new OTPPage(driver, testInfo).
		enterOTP(OtpValue).
		clickOnVerifyOTP();
		
		new IntermediateRetailerPage(driver, testInfo);
		
		toggleApp();
		closeCashKaroApp(deviceName);

		cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
		driver = cu.launchChrome();
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetup.ONELINK);
		cu.clickOneLink_ReEngagement(driver);	
		
		new StorePage(driver, testInfo).
		validateAmazonStoreDeferredDL().
		clickOnStoreMainCTAForSignedInUser();
		
		reportStep("ReEngagement through Voucher CTA", "INFO");

	}

	@Test
	public void reEngagementThroughStoreMainCTA_ForeGround() {

		reportStep("ReEngagement through Main CTA", "INFO");
		
		CashKaroUtility cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);

		cu.removeApp(driver,"com.cashkaro");

		driver = cu.launchChrome();
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetup.ONELINK);
		cu.clickOneLink(driver);	
		cu.playStoreRedirectionVerification(driver);

		driver = cu.launchCashKaroApp((System.getProperty("user.dir") + "/apk/ckapp.apk"));
		
		cu.allowLinkInPermissionPopup(driver);

		//Once after signing up, it should navigate to deferred deep link Page (Amazon)
		String OtpValue = new StorePage(driver, testInfo).
		validateAmazonStoreDeferredDL().
		clickOnStoreMainCTA().
		clickOnJoinFreeLink().
		enterAllJoinFreeFieldsAndVerifyOTP();

		new OTPPage(driver, testInfo).
		enterOTP(OtpValue).
		clickOnVerifyOTP();
		
		new IntermediateRetailerPage(driver, testInfo);
		
		
		cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);
		driver = cu.launchChrome();
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetup.ONELINK);
		cu.clickOneLink_ReEngagement(driver);	
		
		new StorePage(driver, testInfo).
		validateAmazonStoreDeferredDL().
		clickOnStoreMainCTAForSignedInUser();
		
		reportStep("ReEngagement through Main CTA - Ended", "INFO");

	}

	@Test
	public void navigationbackToHomePage() {

		reportStep("Deferred Deep linking Validation and Normal SignUp then validate the Retailer Navigationn", "INFO");
		
		CashKaroUtility cu = new CashKaroUtility(driver, testInfo, udid, port, automationName, deviceName, systemPort);

		cu.removeApp(driver,"com.cashkaro");

		driver = cu.launchChrome();
		
		cu.closeAllTabsAndOneTabInChromeBrowser(driver);
		cu.clickAndEnterURLInSearchOrTypeWebAddress(driver, GetCodeSetup.ONELINK);
		cu.clickOneLink(driver);	
		cu.playStoreRedirectionVerification(driver);

		driver = cu.launchCashKaroApp((System.getProperty("user.dir") + "/apk/ckapp.apk"));
		
		cu.allowLinkInPermissionPopup(driver);

		//Once after signing up, it should navigate to deferred deep link Page (Amazon)
		String OtpValue = new StorePage(driver, testInfo).
		validateAmazonStoreDeferredDL().
		clickOnStoreMainCTA().
		clickOnJoinFreeLink().
		enterAllJoinFreeFieldsAndVerifyOTP();

		new OTPPage(driver, testInfo).
		enterOTP(OtpValue).
		clickOnVerifyOTP();
		
		new IntermediateRetailerPage(driver, testInfo).
		validateRetailerPage("amazon", "amazon").
		clickOnIntermediateBackbutton().
		clickBackButtonToHomePage();
		
		
		reportStep("Deferred Deep linking Validation and Normal SignUp then validate the Retailer Navigation - Completed :)", "INFO");

	}
}

