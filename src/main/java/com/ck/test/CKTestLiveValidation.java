package com.ck.test;

import org.testng.annotations.Test;

import com.app.ck.base.AppDynamicVariables;
import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;
import com.ck.pages.HomePage;
import com.ck.pages.MyEarningsPage;
import com.ck.pages.OTPPage;
import com.ck.pages.PaymentOTPPage;
import com.ck.pages.SeeVideoToEarnCashbackPage;
import com.ck.pages.admin.AdminBonusCreditPage;
import com.ck.pages.admin.AdminCashbackPage;
import com.ck.pages.admin.AdminCoreFunction;
import com.ck.pages.admin.AdminReportsPage;

public class CKTestLiveValidation extends WrapperMethods {
	
	/*********************************************************************/
	/***************** 	   Normal/ FaceBook Sign In		 *****************/
	/*********************************************************************/

	@Test
	public void login_GetStartedPage_TC001() {

		reportStep("Started normal sign-in after clicking login/ Join", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnLoginJoinButton().
		enterUserName(getLiveTestData(0, "NormalEmail")).
		enterPassword(getLiveTestData(0, "Password")).
		clickSignInButtonForSuccess();

		reportStep("Completed normal sign-in after clicking login/ Join", "INFO");
	}
	
	@Test
	public void login_GetStartedPageFacebook_TC002() {

		reportStep("Started facebook sign-in after clicking login/ Join", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnLoginJoinButton().
		clickOnSignInWithFacebook().
		enterFBUserEmail(getLiveTestData(1, "FacebookEmail")).
		enterFBPassword(getLiveTestData(1, "Password")).
		clickOnFBLoginButton().
		clickOnContinue();

		reportStep("Completed facebook sign-in after clicking login/ Join", "INFO");
	}

	@Test
	public void login_ProfilePage_TC003() {

		reportStep("Started normal sign-in from profile page", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnSignInButton().
		enterUserName(getLiveTestData(0, "NormalEmail")).
		enterPassword(getLiveTestData(0, "Password")).
		clickSignInButtonForSuccess();

		reportStep("Completed normal sign-in from profile page", "INFO");
	}

	@Test
	public void login_ProfilePageFacebook_TC004() {

		reportStep("Started facebook sign-in from profile page", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnSignInButton().
		clickOnSignInWithFacebook().
		enterFBUserEmail(getLiveTestData(1, "FacebookEmail")).
		enterFBPassword(getLiveTestData(1, "Password")).
		clickOnFBLoginButton().
		clickOnContinue();

		reportStep("Completed facebook sign-in from profile page", "INFO");
	}

	@Test
	public void login_HomePage_TC005() {

		reportStep("Started normal sign-in from home page", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnStoreCardButton().
		enterUserName(getLiveTestData(0, "NormalEmail")).
		enterPassword(getLiveTestData(0, "Password")).
		clickSignInButtoForRetailerNavigation();
		
		reportStep("Completed normal sign-in from home page", "INFO");

	}

	@Test
	public void login_HomePageFacebook_TC006() {

		reportStep("Started facebook sign-in from home page", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnStoreCardButton().clickOnSignInWithFacebook().
		enterFBUserEmail(getLiveTestData(1, "FacebookEmail")).
		enterFBPassword(getLiveTestData(1, "Password")).
		clickOnFBLoginButton().
		clickOnContinueForIntermediatePageNavigation();

		reportStep("Completed facebook sign-in from home page", "INFO");
	}
//
//	@Test
//	public void login_StorePage_TC007() {
//
//		reportStep("Started normal sign-in from store page after clicking store CTA button", "INFO");
//
//		new SeeVideoToEarnCashbackPage(driver,testInfo).
//		clickOnGoToHomePage().
//		clickOnStoreCardImage().
//		//clickOnStoreMainCTA().
//		enterUserName(getLiveTestData(0, "NormalEmail")).
//		enterPassword(getLiveTestData(0, "Password")).
//		clickSignInButtoForRetailerNavigation();
//
//		reportStep("Completed normal sign-in from store page after clicking store CTA button", "INFO");
//
//
//	}
//
//	@Test
//	public void login_StorePageFacebook_TC008() {
//
//		reportStep("Started facebook sign-in from store page after clicking store CTA button", "INFO");
//
//		new SeeVideoToEarnCashbackPage(driver,testInfo).
//		clickOnGoToHomePage().
//		clickOnStoreCardImage().
//		clickOnStoreMainCTA().
//		clickOnSignInWithFacebook().
//		enterFBUserEmail(getLiveTestData(1, "FacebookEmail")).
//		enterFBPassword(getLiveTestData(1, "Password")).
//		clickOnFBLoginButton().
//		clickOnContinueForIntermediatePageNavigation();
//
//		reportStep("Completed facebook sign-in from store page after clicking store CTA button", "INFO");
//
//
//	}

	@Test
	public void login_VoucherCTA_TC009() {

		reportStep("Started normal sign-in from store page after clicking voucher CTA button", "INFO");

		String cbStoreName = getLiveTestData(3, "StoreName");
		
		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreName).
		clickOnSearchedStoreImage().		
		clickOnVoucherButtonForUnsignedUser(0, "").
		enterUserName(getLiveTestData(0, "NormalEmail")).
		enterPassword(getLiveTestData(0, "Password")).
		clickSignInButtoForRetailerNavigation();

		reportStep("Completed normal sign-in from store page after clicking voucher CTA button", "INFO");

	}
	
	@Test
	public void login_VoucherCTAFacebook_TC010() {

		reportStep("Started facebook sign-in from store page after clicking voucher CTA button", "INFO");

		String cbStoreName = getLiveTestData(3, "StoreName");
		
		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreName).
		clickOnSearchedStoreImage().		
		clickOnVoucherButtonForUnsignedUser(0, "").
		clickOnSignInWithFacebook().
		enterFBUserEmail(getLiveTestData(1, "FacebookEmail")).
		enterFBPassword(getLiveTestData(1, "Password")).
		clickOnFBLoginButton().
		clickOnContinueForIntermediatePageNavigation();

		reportStep("Completed facebook sign-in from store page after clicking voucher CTA button", "INFO");

	}

	@Test
	public void login_StoreCategory_TC011() {

		reportStep("Started normal sign-in from store category page", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnHamburgerIcon().
		clickOntheHighestCashbackRates().
		clickOnStoreCatStoreButton().
		enterUserName(getLiveTestData(0, "NormalEmail")).
		enterPassword(getLiveTestData(0, "Password")).
		clickSignInButtoForRetailerNavigation();

		reportStep("Completed normal sign-in from store category page", "INFO");


	}

	@Test
	public void login_StoreCategoryFacebook_TC012() {

		reportStep("Started facebook sign-in from store category page", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnHamburgerIcon().
		clickOntheHighestCashbackRates().
		clickOnStoreCatStoreButton().clickOnSignInWithFacebook().
		enterFBUserEmail(getLiveTestData(1, "FacebookEmail")).
		enterFBPassword(getLiveTestData(1, "Password")).
		clickOnFBLoginButton().clickOnContinueForIntermediatePageNavigation();

		reportStep("Completed facebook sign-in from store category page", "INFO");


	}

	@Test
	public void login_Search_TC013() {

		reportStep("Started normal sign-in from search page", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar("Amazon india").
		clickOnStoreCardButton().
		enterUserName(getLiveTestData(0, "NormalEmail")).
		enterPassword(getLiveTestData(0, "Password")).
		clickSignInButtoForRetailerNavigation();

		reportStep("Completed normal sign-in from search page", "INFO");
	}

	@Test
	public void login_SearchFacebook_TC014() {

		reportStep("Started facebook sign-in from search page", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar("Amazon india").
		clickOnStoreCardButton().
		clickOnSignInWithFacebook().
		enterFBUserEmail(getLiveTestData(1, "FacebookEmail")).
		enterFBPassword(getLiveTestData(1, "Password")).
		clickOnFBLoginButton().
		clickOnContinueForIntermediatePageNavigation();

		reportStep("Completed facebook sign-in from search page", "INFO");
	}

	@Test
	public void login_SearchViewAll_TC015() {

		reportStep("Started normal sign-in from search page after clicking view all", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar().
		clickOnViewAll().
		clickOnStoreCardButton().
		clickOnSignInWithFacebook().
		enterFBUserEmail(getLiveTestData(1, "FacebookEmail")).
		enterFBPassword(getLiveTestData(1, "Password")).
		clickOnFBLoginButton().
		clickOnContinueForIntermediatePageNavigation();

		reportStep("Completed normal sign-in from search page after clicking view all", "INFO");
	}

	@Test
	public void login_SearchResultInProducts_TC016() {

		reportStep("Started normal sign-in from search results in products", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar().
		clickOnSearchResultsInProducts().
		clickOnGrabDeal().
		enterUserName(getLiveTestData(0, "NormalEmail")).
		enterPassword(getLiveTestData(0, "Password")).
		clickSignInButtoForRetailerNavigation();

		reportStep("Completed normal sign-in from search results in products", "INFO");
	}

	@Test
	public void login_SearchResultInCoupons_TC017() {

		reportStep("Started normal sign-in from search results in coupons", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar().
		clickOnSearchResultsInCoupons().
		clickOnVoucherMainCTAForUnsignedUser().
		enterUserName(getLiveTestData(0, "NormalEmail")).
		enterPassword(getLiveTestData(0, "Password")).
		clickSignInButtoForRetailerNavigation();

		reportStep("Completed normal sign-in from search results in coupons", "INFO");
	}

	/*********************************************************************/
	/***************** 	   			   Bonus		 		 *************/
	/*********************************************************************/

	@Test
	public void myearningsPage_AddCashbackBonus_ValidateStatus_ConfirmedRequested_TC0018() {
		
		reportStep("Started my earnings page confirmed & requested status validation", "INFO");
		
		String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
		String currentMonthYear= Utilities.currentMonthYear().trim();
		String otpValue = "000000";
		
		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnLoginJoinButton().
		enterUserName(getLiveTestData(0, "NormalEmail")).
		enterPassword(getLiveTestData(0, "Password")).
		clickSignInButtonForSuccess();

		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		AdminBonusCreditPage adminBonusCredit = new AdminBonusCreditPage(driver, testInfo);
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getLiveTestData(2, "UserName"));
		adminFunctions.enterPassword(getLiveTestData(2, "Password"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminBonusCredit.clickBonusCreditsSubMenu();
		adminBonusCredit.enterUser(getLiveTestData(0, "NormalEmail"));
		adminBonusCredit.enterBonusValue("250");
		adminBonusCredit.selectCashbackTypeFromDropDown("Cashback");
		adminBonusCredit.setDateRangeDate();
		adminBonusCredit.clickSubmit();
		
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchCashKaroApp(driver);

		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		
		clickCashbackTab().
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Confirmed")).
		
		clickCashbackTab().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID().
		clickOnGETPAID();
		
		objPaymentOTP.
		enterOTP_Live(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		
		clickCashbackTab().
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Requested"));

		reportStep("Completed my earnings page confirmed & requested status validation", "INFO");
		
	}

	/*********************************************************************/
	/***************** 	   			   Bonus		 		 *************/
	/*********************************************************************/

	@Test
	public void myearningsPage_AddRewardsBonus_ValidateStatus_ConfirmedRequested_TC0018() {
		
		reportStep("Started my earnings page confirmed & requested status validation", "INFO");
		
		String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
		String currentMonthYear= Utilities.currentMonthYear().trim();
		String otpValue = "000000";
		
		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnLoginJoinButton().
		enterUserName(getLiveTestData(0, "NormalEmail")).
		enterPassword(getLiveTestData(0, "Password")).
		clickSignInButtonForSuccess();

		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
		AdminBonusCreditPage adminBonusCredit = new AdminBonusCreditPage(driver, testInfo);
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getLiveTestData(2, "UserName"));
		adminFunctions.enterPassword(getLiveTestData(2, "Password"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminBonusCredit.clickBonusCreditsSubMenu();
		adminBonusCredit.enterUser(getLiveTestData(0, "NormalEmail"));
		adminBonusCredit.enterBonusValue("250");
		adminBonusCredit.selectCashbackTypeFromDropDown("Rewards");
		adminBonusCredit.setDateRangeDate();
		adminBonusCredit.clickSubmit();
		
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchCashKaroApp(driver);

		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		
		clickRewardsTab().
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Confirmed")).
		
		clickRewardsTab().
		clickOnRequestRewardsPaymentButton().
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID().
		clickOnGETPAID();
		
		objPaymentOTP.
		enterOTP_Live(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		
		clickRewardsTab().
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Requested"));

		reportStep("Completed my earnings page confirmed & requested status validation", "INFO");
		
	}

	/*********************************************************************/
	/***************** 	   			Exit Click		 	 *****************/
	/*********************************************************************/

	@Test
	public void storePage_Validate_ExitClick_AfterRedirection_TC0019() {
		
		reportStep("Started exit click validation", "INFO");
		
		String cbStoreName = getLiveTestData(3, "StoreName");
		String cbLinkURL = getLiveTestData(3, "StoreUrl");
		
		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnLoginJoinButton().
		enterUserName(getLiveTestData(0, "NormalEmail")).
		enterPassword(getLiveTestData(0, "Password")).
		clickSignInButtonForSuccess().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreName).
		clickOnSearchedStoreImage().
		clickOnStoreMainCTAForSignedInUser().
		
		validateRetailerPage(cbLinkURL, cbLinkURL).
		clickOnIntermediateBackbutton();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getLiveTestData(2, "UserName"));
		adminFunctions.enterPassword(getLiveTestData(2, "Password"));
		adminFunctions.clickSubmit();
		
		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(getLiveTestData(0, "NormalEmail"));
		reportsPage.clickSubmit();
		String exitClick = reportsPage.extractExitClickValueFromResultstableFirstRow();
		String storeName = reportsPage.extractStoreNameValueFromResultstableFirstRow();
		
		reportStep("Created Exit ID: "+exitClick, "INFO");
		reportStep("Created Store name: "+storeName, "INFO");

		reportStep("Sucessfuly completed exit click validation", "INFO");
		
	}

	/*********************************************************************/
	/***************** 	   			Re-Direction	 	 *****************/
	/*********************************************************************/

	@Test
	public void storePage_Redirection_Verification_TC020() {

		reportStep("Started retailer redirection verification", "INFO");
		
		String cbStoreName = getLiveTestData(3, "StoreName");
		String cbLinkURL = getLiveTestData(3, "StoreUrl");
		
		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnLoginJoinButton().
		enterUserName(getLiveTestData(0, "NormalEmail")).
		enterPassword(getLiveTestData(0, "Password")).
		clickSignInButtonForSuccess().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreName).
		clickOnSearchedStoreImage().
		clickOnStoreMainCTAForSignedInUser().
		
		validateRetailerPage(cbLinkURL, cbLinkURL).
		clickOnIntermediateBackbutton();

		reportStep("Sucessfuly completed retailer redirection verification", "INFO");
		
	}

	/*********************************************************************/
	/***************** 	   			Add Ticket		 	 *****************/
	/*********************************************************************/
	
}