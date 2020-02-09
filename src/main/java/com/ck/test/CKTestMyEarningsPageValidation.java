package com.ck.test;

import org.testng.annotations.Test;
import com.app.ck.base.AppDynamicVariables;
import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;
import com.ck.pages.SeeVideoToEarnCashbackPage;
import com.ck.pages.HomePage;
import com.ck.pages.JoinFreePage;
import com.ck.pages.MyEarningsPage;
import com.ck.pages.OTPPage;
import com.ck.pages.PaymentOTPPage;
import com.ck.pages.StoreCategoryPage;
import com.ck.pages.admin.AdminCashbackPage;
import com.ck.pages.admin.AdminCoreFunction;
import com.ck.pages.admin.AdminPartnerSettingsPage;
import com.ck.pages.admin.AdminPendingCashoutsPage;
import com.ck.pages.admin.AdminReportsPage;
import com.ck.pages.admin.AdminStoresPage;
import com.ck.pages.admin.AdminUsersPage;
import com.ck.pages.admin.ProductBrowserEditModePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.PressesKey;

public class CKTestMyEarningsPageValidation extends WrapperMethods {

	//@Test
	public void myEarningsPage_TC001() {
		
		reportStep("TC001 is  started", "INFO");

		String OtpValue	=	new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnLoginJoinButton().
		clickOnJoinFreeLink().
		enterAllJoinFreeFieldsAndVerifyOTP();

		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.
		enterOTP(OtpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickInfoIcon().
		validateEarningsInfoPoints().
		clickOnBackButtonForMyEarningsPage().
		validateRaiseTicketCashbackTab().
		clickRaiseTicketCashbackTab().
		clickBackButton().
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickRewardsTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickInfoIconRewardsTab().
		validateEarningsInfoPoints().
		clickOnBackButtonForMyEarningsPage().
		validateRaiseTicketRewardsTab().
		clickRaiseTicketRewardsTab().
		clickBackButton().
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickReferralTabClick().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		validateBottomTextInAllTab().
		clickReferNow().
		clickBackButton().
		clickBackButton().
		clickBackButton();
			
		reportStep("TC001 is  ended", "INFO");
		
	}

	//@Test
	public void myEarningsPage_TC002() {
		
		reportStep("TC002 is  started", "INFO");
		
		String cbStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String cbShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
		String cbLinkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");
	
		String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();
		String currentMonthYear= Utilities.currentMonthYear().trim();
		String fourDaysBackDate = Utilities.customBackDate_dd_MMMM_yyyy(4);		
		String fourDaysBackedDate = Utilities.customBackDate_dd_MM_yyyy(4);
		
		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		
		String otpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnRecentClicks().
		validateObjectsUnSignedUserRecentClicksPage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);
		
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnRecentClicks().
		validateObjectsSignedUserRecentClicksPage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreName).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(cbStoreName, cbShortDescription).
		clickOnStoreMainCTAForSignedInUser().
		
		validateRetailerPage(cbLinkURL, cbLinkURL).
		clickOnIntermediateBackbutton();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddress);
		reportsPage.clickSubmit();
		String exitClick = reportsPage.extractExitClickValueFromResultstableFirstRow();
				
		CashKaroUtility reopenCKAppOne = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppOne.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).	
		clickOnRecentClicks().
		clickDisabledRaiseTicket(cbStoreName);
		
		objCashKaroUtility.backDateTheExitClickForGivenDate(exitClick, 4);

		new HomePage(driver, testInfo).
		clickOnRecentClicks().
		clickEnabledRaiseTicket(cbStoreName).
		validateDateOfTransactionField(fourDaysBackedDate).
		validateRetailerDetailsField(cbStoreName).
		validateTransactionDetailsDate(fourDaysBackDate).
		clickBackButtonToRedirectToRecentClicks().
		clickBackButton();
		
		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctionsOne = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctionsOne;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminCashbackPage cashbackPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClick);
		cashbackPage.entertransactionId(exitClick);
		cashbackPage.enterOrderId(exitClick);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "FiveHundered"));
		cashbackPage.entercashback(getTestData(11, "TwoFifty"));
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClick);
		cashbackPage.clickSubmit();
		
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).	
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(11, "TwoFiftyZeroZero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(11, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(cbStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Pending")).
		validateExpected(nintyDaysAhead).
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickCashbackTab().
		validateRaiseTicketCashbackTab().
		clickRaiseTicketCashbackTab().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(cbStoreName).
		validateTransactionDetails(fourDaysBackDate, exitClick, getTestData(11, "Thousand"), getTestData(11, "Pending")).
		clickBackButton().
		clickBackButton().
		clickBackButton().
		clickOnRecentClickBottomIcon().
		clickEnabledSeeDetails(cbStoreName, "250.0");

		CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuTwo.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);		
		AdminCashbackPage cashbackPageTwo = new AdminCashbackPage(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		cashbackPage = cashbackPageTwo;
		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClick);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		
		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppAgain.launchCashKaroApp(driver);
		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(11, "TwoFiftyZeroZero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(11, "TwoFiftyZeroZero")).
		clickMonthYearDropdown(currentMonthYear).	
		validateDate(currentDate).
		validateRetailer(cbStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Confirmed")).
		validateAbsenceExpected(nintyDaysAhead).
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickCashbackTab().
		validateRaiseTicketCashbackTab().
		clickRaiseTicketCashbackTab().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(cbStoreName).
		validateTransactionDetails(fourDaysBackDate, exitClick, getTestData(11, "Thousand"), getTestData(11, "Confirmed")).	
		clickBackButton().
		clickBackButton().
		clickBackButton().
		clickOnRecentClickBottomIcon().
		clickEnabledSeeDetails(cbStoreName, "250.0").
		clickCashbackTab().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterBankNEFTPaymentDetails().
		clickOnGETPAID();
		
		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);
		
		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		
		validateDate(currentDate).
		validateRetailer(cbStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Requested")).
		validateAbsenceExpected(nintyDaysAhead).
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickCashbackTab().
		validateRaiseTicketCashbackTab().
		clickRaiseTicketCashbackTab().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(cbStoreName).
		validateTransactionDetails(fourDaysBackDate, exitClick, getTestData(11, "Thousand"), getTestData(11, "Requested")).
		clickBackButton();
		
		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu=cu2;
		driver = cu.launchChromeWebView(driver);
		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions=adminFunctions2;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(emailAddress);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddress);
		PendingCashoutsPage.clickCreateCashout();
		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();
	
		CashKaroUtility reopenCKApp2 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp=reopenCKApp2;
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).	
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(cbStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Paid")).
		validateAbsenceExpected(nintyDaysAhead).
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickCashbackTab().
		validateRaiseTicketCashbackTab().
		clickRaiseTicketCashbackTab().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(cbStoreName).
		validateTransactionDetails(fourDaysBackDate, exitClick, getTestData(11, "Thousand"), getTestData(11, "Paid"));
				
		reportStep("TC002 is  ended", "INFO");
		
	}

	//@Test
	public void myEarningsPage_TC003(){
		
		reportStep("TC003 is  started", "INFO");
		
		String rwStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_Name");
		String rwShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_App_Short_Description");
		String rwLinkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_linkURL");
		
		String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();
		String currentMonthYear= Utilities.currentMonthYear().trim();
		String fourDaysBackDate = Utilities.customBackDate_dd_MMMM_yyyy(4);	
		String fourDaysBackedDate = Utilities.customBackDate_dd_MM_yyyy(4);

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		
		String otpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnRecentClicks().
		validateObjectsUnSignedUserRecentClicksPage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);
		
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnRecentClicks().
		validateObjectsSignedUserRecentClicksPage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(rwStoreName).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(rwStoreName, rwShortDescription).
		clickOnStoreMainCTAForSignedInUser().
		waitForRedirectionalURL(rwLinkURL).
		validateRetailerPage(rwLinkURL, rwLinkURL).
		clickOnIntermediateBackbutton();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddress);
		reportsPage.clickSubmit();
		String exitClick = reportsPage.extractExitClickValueFromResultstableFirstRow();
		
		CashKaroUtility reopenCKAppOne = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppOne.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).	
		clickOnRecentClicks().
		clickDisabledRaiseTicket(rwStoreName);
		
		objCashKaroUtility.backDateTheExitClickForGivenDate(exitClick, 4);
		
		new HomePage(driver, testInfo).
		clickOnRecentClicks().
		clickEnabledRaiseTicket(rwStoreName).
		validateDateOfTransactionField(fourDaysBackedDate).
		validateRetailerDetailsField(rwStoreName).
		validateTransactionDetailsDate(fourDaysBackDate).
		clickBackButtonToRedirectToRecentClicks().
		clickBackButton();
		
		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctionsOne = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctionsOne;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminCashbackPage cashbackPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClick);
		cashbackPage.entertransactionId(exitClick);
		cashbackPage.enterOrderId(exitClick);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "FiveHundered"));
		cashbackPage.entercashback(getTestData(11, "TwoFifty"));
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Rewards"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClick);
		cashbackPage.clickSubmit();
		
		objCashKaroUtility.backDateTheExitClickForGivenDate(exitClick, 4);
				
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().

		
		clickRewardsTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalRewardsEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(11, "TwoFiftyZeroZero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(11, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickMonthYearDropdown(currentMonthYear).	
		validateDate(currentDate).
		validateRetailer(rwStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Pending")).
		validateExpected(nintyDaysAhead).
		
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		
		
		clickRewardsTab().
		validateRaiseTicketRewardsTab().
		clickRaiseTicketRewardsTab().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(rwStoreName).
		validateTransactionDetails(fourDaysBackDate, exitClick, getTestData(11, "Thousand"), getTestData(11, "Pending")).
		clickBackButton().
		clickBackButton().
		clickBackButton().
		clickOnRecentClickBottomIcon().
		clickEnabledSeeDetails(rwStoreName, "250.0");;

		CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuTwo.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);		
		AdminCashbackPage cashbackPageTwo = new AdminCashbackPage(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		cashbackPage = cashbackPageTwo;
		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClick);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		
		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppAgain.launchCashKaroApp(driver);
		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		
		clickRewardsTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalRewardsEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(11, "TwoFiftyZeroZero")).
		clickBackButton().
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(11, "TwoFiftyZeroZero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(rwStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Confirmed")).
		validateAbsenceExpected(nintyDaysAhead).
		clickRewardsTab().
		validateRaiseTicketRewardsTab().
		clickRaiseTicketRewardsTab().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(rwStoreName).
		validateTransactionDetails(fourDaysBackDate, exitClick, getTestData(11, "Thousand"), getTestData(11, "Confirmed")).
		clickBackButton().
		clickBackButton().
		clickBackButton().
		clickOnRecentClickBottomIcon().
		clickEnabledSeeDetails(rwStoreName, "250.0").
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		
		clickRewardsTab().
		clickOnRequestRewardsPaymentButton().
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID().
		clickOnGETPAID();
		
		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);
		
		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		
		clickRewardsTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalRewardsEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidRewardsValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickRewardsTab().
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(rwStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Requested")).
		validateAbsenceExpected(nintyDaysAhead).
		clickRewardsTab().
		validateRaiseTicketRewardsTab().
		clickRaiseTicketRewardsTab().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(rwStoreName).
		validateTransactionDetails(fourDaysBackDate, exitClick, getTestData(11, "Thousand"), getTestData(11, "Requested")).
		clickBackButton().
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));
		
		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu=cu2;
		driver = cu.launchChromeWebView(driver);
		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions=adminFunctions2;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(emailAddress);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddress);
		PendingCashoutsPage.clickCreateCashout();
		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();
	
		CashKaroUtility reopenCKApp2 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp=reopenCKApp2;
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).	
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		
		clickRewardsTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalRewardsEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidRewardsValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(rwStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Paid")).
		validateAbsenceExpected(nintyDaysAhead).
		clickRewardsTab().
		validateRaiseTicketRewardsTab().
		clickRaiseTicketRewardsTab().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(rwStoreName).
		validateTransactionDetails(fourDaysBackDate, exitClick, getTestData(11, "Thousand"), getTestData(11, "Paid")).
		clickBackButton().
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));
		
		
		reportStep("TC003 is  ended", "INFO");
		
	}

	//@Test
	public void myEarningsPage_TC004() {
		
		reportStep("TC004 is  started", "INFO");
		
		String cbStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String cbShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
		String cbLinkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();
		String currentMonthYear= Utilities.currentMonthYear().trim();
		String fourDaysBackDate = Utilities.customBackDate_dd_MMMM_yyyy(4);

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		
		String otpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);
		
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreName).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(cbStoreName, cbShortDescription).
		clickOnStoreMainCTAForSignedInUser().
		
		validateRetailerPage(cbLinkURL, cbLinkURL).
		clickOnIntermediateBackbutton();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddress);
		reportsPage.clickSubmit();
		String exitClick = reportsPage.extractExitClickValueFromResultstableFirstRow();
		
		AdminCashbackPage cashbackPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClick);
		cashbackPage.entertransactionId(exitClick);
		cashbackPage.enterOrderId(exitClick);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "FiveHundered"));
		cashbackPage.entercashback(getTestData(11, "TwoFifty"));
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClick);
		cashbackPage.clickSubmit();
		
		objCashKaroUtility.backDateTheExitClickForGivenDate(exitClick, 4);
				
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(11, "TwoFiftyZeroZero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(11, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickMonthYearDropdown(currentMonthYear).
		
		validateDate(currentDate).
		validateRetailer(cbStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Pending")).
		validateExpected(nintyDaysAhead).
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickCashbackTab();

		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);		
		AdminCashbackPage cashbackPageTwo = new AdminCashbackPage(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		cashbackPage = cashbackPageTwo;
		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClick);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Cancelled"));
		cashbackPage.setFailDate();
		cashbackPage.clickSubmit();
		
		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppAgain.launchCashKaroApp(driver);
		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalRewardsEarnedValue(getTestData(6, "Zero")).
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(cbStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Cancelled")).
		validateAbsenceExpected(nintyDaysAhead).
		clickCashbackTab().
		validateRaiseTicketCashbackTab().
		clickRaiseTicketCashbackTab().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(cbStoreName).
		validateTransactionDetails(fourDaysBackDate, exitClick, getTestData(11, "Thousand"), getTestData(11, "Cancelled")).
		clickBackButton().
		
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));
		
		
		
		reportStep("TC004 is  ended", "INFO");
		
	}

	//@Test
	public void myEarningsPage_TC005(){
		
		reportStep("TC005 is  started", "INFO");
		
		String rwStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_Name");
		String rwShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_App_Short_Description");
		String rwLinkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_linkURL");
		
		String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();
		String currentMonthYear= Utilities.currentMonthYear().trim();
		String fourDaysBackDate = Utilities.customBackDate_dd_MMMM_yyyy(4);

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		
		String otpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);
		
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(rwStoreName).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(rwStoreName, rwShortDescription).
		clickOnStoreMainCTAForSignedInUser().
		waitForRedirectionalURL(rwLinkURL).
		validateRetailerPage(rwLinkURL, rwLinkURL).
		clickOnIntermediateBackbutton();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddress);
		reportsPage.clickSubmit();
		String exitClick = reportsPage.extractExitClickValueFromResultstableFirstRow();
		
		AdminCashbackPage cashbackPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClick);
		cashbackPage.entertransactionId(exitClick);
		cashbackPage.enterOrderId(exitClick);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "FiveHundered"));
		cashbackPage.entercashback(getTestData(11, "TwoFifty"));
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Rewards"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClick);
		cashbackPage.clickSubmit();
		
		objCashKaroUtility.backDateTheExitClickForGivenDate(exitClick, 4);
				
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().

		
		clickRewardsTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalRewardsEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(11, "TwoFiftyZeroZero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(11, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickMonthYearDropdown(currentMonthYear).	
		validateDate(currentDate).
		validateRetailer(rwStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Pending")).
		validateExpected(nintyDaysAhead).
		
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickCashbackTab();

		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);		
		AdminCashbackPage cashbackPageTwo = new AdminCashbackPage(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		cashbackPage = cashbackPageTwo;
		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClick);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Cancelled"));
		cashbackPage.setFailDate();
		cashbackPage.clickSubmit();
		
		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppAgain.launchCashKaroApp(driver);
		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		
		clickRewardsTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalRewardsEarnedValue(getTestData(6, "Zero")).
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(rwStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Cancelled")).
		validateAbsenceExpected(nintyDaysAhead).
		clickRewardsTab().
		validateRaiseTicketRewardsTab().
		clickRaiseTicketRewardsTab().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(rwStoreName).
		validateTransactionDetails(fourDaysBackDate, exitClick, getTestData(11, "Thousand"), getTestData(11, "Cancelled")).
		clickBackButton().
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));
		
		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);
				

		reportStep("TC005 is  ended", "INFO");
		
	}

	//@Test
	public void myEarningsPage_TC006() {
		
		reportStep("TC006 is  started", "INFO");
		
		String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();
		String currentMonthYear= Utilities.currentMonthYear().trim();

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		
		String otpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);
		
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).
		clickOnVerifyOTP();
		
		new HomePage(driver, testInfo);
			
		objCashKaroUtility.addCashbackBonus(emailAddress, getTestData(11, "TwoFifty"), getTestData(11, "Cashback"));

		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(11, "TwoFiftyZeroZero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(11, "TwoFiftyZeroZero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateAbsenceOrderAmount().
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Confirmed")).
		validateAbsenceExpected(nintyDaysAhead).
		
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickCashbackTab().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID().
		clickOnGETPAID();
		
		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);
		
		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateAbsenceOrderAmount().
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Requested")).
		validateAbsenceExpected(nintyDaysAhead).
		
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));
		
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
		PendingCashoutsPage.enterKeyword(emailAddress);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddress);
		PendingCashoutsPage.clickCreateCashout();
		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();
	
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).	
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateAbsenceOrderAmount().
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Paid")).
		validateAbsenceExpected(nintyDaysAhead).
		
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));
		
		
		reportStep("TC006 is  ended", "INFO");
		
	}

	//@Test
	public void myEarningsPage_TC007(){
		
		reportStep("TC007 is  started", "INFO");
		
		String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();
		String currentMonthYear= Utilities.currentMonthYear().trim();

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		
		String otpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);
		
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).
		clickOnVerifyOTP();
		
		new HomePage(driver, testInfo);
		
		objCashKaroUtility.addCashbackBonus(emailAddress, getTestData(11, "TwoFifty"), getTestData(11, "Rewards"));

		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		
		clickRewardsTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalRewardsEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(11, "TwoFiftyZeroZero")).
		clickBackButton().
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(11, "TwoFiftyZeroZero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateAbsenceOrderAmount().
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Confirmed")).
		validateAbsenceExpected(nintyDaysAhead).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		
		clickRewardsTab().
		clickOnRequestRewardsPaymentButton().
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID().
		clickOnGETPAID();
		
		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);
		
		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		
		clickRewardsTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalRewardsEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidRewardsValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickRewardsTab().
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateAbsenceOrderAmount().
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Requested")).
		validateAbsenceExpected(nintyDaysAhead).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));
		
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
		PendingCashoutsPage.enterKeyword(emailAddress);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddress);
		PendingCashoutsPage.clickCreateCashout();
		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();
	
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).	
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		
		clickRewardsTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalRewardsEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidRewardsValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateAbsenceOrderAmount().
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Paid")).
		validateAbsenceExpected(nintyDaysAhead).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));
		
		
		reportStep("TC007 is  ended", "INFO");
		
	}

	////@Test
	public void myEarningsPage_TC008() {
		
		reportStep("TC008 is  started", "INFO");
		
		String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();
		String currentMonthYear= Utilities.currentMonthYear().trim();

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminPartnerSettingsPage partnerSettingsPage = new AdminPartnerSettingsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnSettings();
		adminFunctions.clickOnPartnerSettings();
		partnerSettingsPage.clickEditPartnerDetails();
		partnerSettingsPage.enterSignUpBonus(getTestData(11, "TwoFiftyZeroZero"));
		partnerSettingsPage.clickSubmit();
		
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		String otpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);
		
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).
		clickOnVerifyOTP();
		
		new HomePage(driver, testInfo);

		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(11, "TwoFiftyZeroZero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(11, "TwoFiftyZeroZero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "signUpBonusStore")).
		validateAbsenceOrderAmount().
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Confirmed")).
		validateAbsenceExpected(nintyDaysAhead).
		
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickCashbackTab().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID().
		clickOnGETPAID();
		
		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);
		
		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "signUpBonusStore")).
		validateAbsenceOrderAmount().
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Requested")).
		validateAbsenceExpected(nintyDaysAhead).
		
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));
		
		objCashKaroUtility.cashOut(emailAddress);
		
		scrollFromUpToDowninApp();
		scrollFromUpToDowninApp();
		scrollFromUpToDowninApp();
		
		new MyEarningsPage(driver, testInfo).
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "signUpBonusStore")).
		validateAbsenceOrderAmount().
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Paid")).
		validateAbsenceExpected(nintyDaysAhead).
		
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));

		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);		
		AdminPartnerSettingsPage partnerSettingsPageTwo = new AdminPartnerSettingsPage(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		partnerSettingsPage = partnerSettingsPageTwo;
		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnSettings();
		adminFunctions.clickOnPartnerSettings();
		partnerSettingsPage.clickEditPartnerDetails();
		partnerSettingsPage.enterSignUpBonus(getTestData(6, "Zero"));
		partnerSettingsPage.clickSubmit();
				
		reportStep("TC008 is  ended", "INFO");
		
	}

	//@Test
	public void myEarningsPage_TC009(){
		
		reportStep("TC009 is  started", "INFO");
	
		String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
		String currentMonthYear= Utilities.currentMonthYear().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		
		String otpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);
		
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).
		clickOnVerifyOTP();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnReferralNetwork().
		validateObjectsMyReferrals().
		validateTotalReferralCashbackEarnedValue("0").
		validateFriendsJoinedValue("0").
		clickReferNow().
		clickBackButtonRedirectToReferralNetwork().
		clickBackButton().
		clickOnLogout().
		clickOnProfileIcon();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminPartnerSettingsPage partnerSettingsPage = new AdminPartnerSettingsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnSettings();
		adminFunctions.clickOnPartnerSettings();
		partnerSettingsPage.clickEditPartnerDetails();
		partnerSettingsPage.enterReferralSignUpBonus(getTestData(11, "TwoFiftyZeroZero"));
		partnerSettingsPage.clickSubmit();

		AdminUsersPage usersPage = new AdminUsersPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickUsersMenu();
		adminFunctions.clickUsersSubMenu();
		usersPage.selectSearchByDropDown("User Email");
		usersPage.enterKeyword(emailAddress);
		usersPage.clickSubmitButton();
		String userId = usersPage.extractUserIdValueFromResultstableFirstRow();
		String userName = usersPage.extractUserNameValueFromResultstableFirstRow();
		
		CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp1.launchCashKaroApp(driver);
		
		new SeeVideoToEarnCashbackPage(driver, testInfo);

		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = objcashKaroutility.launchChrome();
		driver= driver2;
		
		String url = Utilities.referralLinkGetCode(userId, userName);
		
		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);
		objcashKaroutility.clickJoinFreeLink(driver);
		
		String emailAddressReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberReferralJoinee = objCashKaroUtility.generateMobileNumber();
		String name = "SignUpReferral";

		String OtpValue = new JoinFreePage(driver, testInfo).
		validateReferralByUserAtJoinFree(userName).
		enterFullName(name).
		enterEmail(emailAddressReferralJoinee).
		enterPassword().
		enterMobileNumber(mobileNumberReferralJoinee).
		clickOnGetOTPButton(mobileNumberReferralJoinee);
		
		new OTPPage(driver, testInfo).
		enterOTP(OtpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo);

		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(11, "TwoFiftyZeroZero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(11, "TwoFiftyZeroZero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "signUpBonusStore")).
		validateAbsenceOrderAmount().
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Confirmed")).
		validateAbsenceExpected(nintyDaysAhead).
		
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickCashbackTab().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID().
		clickOnGETPAID();
		
		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumberReferralJoinee);
		
		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "signUpBonusStore")).
		validateAbsenceOrderAmount().
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Requested")).
		validateAbsenceExpected(nintyDaysAhead).
		
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));
				
		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu=cu2;
		driver = cu.launchChromeWebView(driver);
		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions=adminFunctions2;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(emailAddressReferralJoinee);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddressReferralJoinee);
		PendingCashoutsPage.clickCreateCashout();
		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();
	
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).	
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "signUpBonusStore")).
		validateAbsenceOrderAmount().
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Paid")).
		validateAbsenceExpected(nintyDaysAhead).
		
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));

		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);		
		AdminPartnerSettingsPage partnerSettingsPageTwo = new AdminPartnerSettingsPage(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		partnerSettingsPage = partnerSettingsPageTwo;
		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnSettings();
		adminFunctions.clickOnPartnerSettings();
		partnerSettingsPage.clickEditPartnerDetails();
		partnerSettingsPage.enterReferralSignUpBonus(getTestData(6, "Zero"));
		partnerSettingsPage.clickSubmit();

		
		reportStep("TC009 is  completed", "PASS");
			
	}

	//@Test
	public void myEarningsPage_TC010(){
		
		reportStep("TC009 is  started", "INFO");
	
		String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
		String currentMonthYear= Utilities.currentMonthYear().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		
		String otpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);
		
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).
		clickOnVerifyOTP();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnReferralNetwork().
		validateObjectsMyReferrals().
		validateTotalReferralCashbackEarnedValue("0").
		validateFriendsJoinedValue("0").
		clickReferNow().
		clickBackButtonRedirectToReferralNetwork().
		clickBackButton().
		clickOnLogout().
		clickOnProfileIcon();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminUsersPage usersPage = new AdminUsersPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickUsersMenu();
		adminFunctions.clickUsersSubMenu();
		usersPage.selectSearchByDropDown("User Email");
		usersPage.enterKeyword(emailAddress);
		usersPage.clickSubmitButton();
		String userId = usersPage.extractUserIdValueFromResultstableFirstRow();
		String userName = usersPage.extractUserNameValueFromResultstableFirstRow();
		usersPage.clickEditButton();
		usersPage.enterReferralBonus("250.0");
		usersPage.clickSubmitButtonUserForm();
		
		CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp1.launchCashKaroApp(driver);
		
		new SeeVideoToEarnCashbackPage(driver, testInfo);

		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = objcashKaroutility.launchChrome();
		driver= driver2;
		
		String url = Utilities.referralLinkGetCode(userId, userName);
		
		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);
		objcashKaroutility.clickJoinFreeLink(driver);
		
		String emailAddressReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberReferralJoinee = objCashKaroUtility.generateMobileNumber();
		String name = "UserReferral";

		String OtpValue = new JoinFreePage(driver, testInfo).
		validateReferralByUserAtJoinFree(userName).
		enterFullName(name).
		enterEmail(emailAddressReferralJoinee).
		enterPassword().
		enterMobileNumber(mobileNumberReferralJoinee).
		clickOnGetOTPButton(mobileNumberReferralJoinee);
		
		new OTPPage(driver, testInfo).
		enterOTP(OtpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo);

		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(11, "TwoFiftyZeroZero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(11, "TwoFiftyZeroZero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "signUpBonusStore")).
		validateAbsenceOrderAmount().
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Confirmed")).
		validateAbsenceExpected(nintyDaysAhead).
		
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickCashbackTab().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID().
		clickOnGETPAID();
		
		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumberReferralJoinee);
		
		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "signUpBonusStore")).
		validateAbsenceOrderAmount().
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Requested")).
		validateAbsenceExpected(nintyDaysAhead).
		
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));
				
		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu=cu2;
		driver = cu.launchChromeWebView(driver);
		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions=adminFunctions2;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(emailAddressReferralJoinee);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddressReferralJoinee);
		PendingCashoutsPage.clickCreateCashout();
		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();
	
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).	
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "signUpBonusStore")).
		validateAbsenceOrderAmount().
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Paid")).
		validateAbsenceExpected(nintyDaysAhead).
		
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));

		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);		
		adminFunctions = adminFunctionsTwo;
		AdminUsersPage usersPageOne = new AdminUsersPage(driver, testInfo);
		usersPage = usersPageOne;
		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickUsersMenu();
		adminFunctions.clickUsersSubMenu();
		usersPage.selectSearchByDropDown("User Email");
		usersPage.enterKeyword(emailAddress);
		usersPage.clickSubmitButton();
		usersPage.clickEditButton();
		usersPage.enterReferralBonus(getTestData(6, "Zero"));
		usersPage.clickSubmitButtonUserForm();

		
		reportStep("TC009 is  completed", "PASS");
			
	}

	//@Test
	public void myEarningsPage_TC011(){
		
		reportStep("TC009 is  started", "INFO");
		
		String cbStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String cbShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
		String cbLinkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
		String currentMonthYear= Utilities.currentMonthYear().trim();

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		
		String otpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);
		
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).
		clickOnVerifyOTP();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnReferralNetwork().
		validateObjectsMyReferrals().
		validateTotalReferralCashbackEarnedValue("0").
		validateFriendsJoinedValue("0").
		clickReferNow().
		clickBackButtonRedirectToReferralNetwork().
		clickBackButton().
		clickOnLogout().
		clickOnProfileIcon();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminUsersPage usersPage = new AdminUsersPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickUsersMenu();
		adminFunctions.clickUsersSubMenu();
		usersPage.selectSearchByDropDown("User Email");
		usersPage.enterKeyword(emailAddress);
		usersPage.clickSubmitButton();
		String userId = usersPage.extractUserIdValueFromResultstableFirstRow();
		String userName = usersPage.extractUserNameValueFromResultstableFirstRow();
		
		CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp1.launchCashKaroApp(driver);
		
		new SeeVideoToEarnCashbackPage(driver, testInfo);

		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = objcashKaroutility.launchChrome();
		driver= driver2;
		
		String url = Utilities.referralLinkGetCode(userId, userName);
		
		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);
		objcashKaroutility.clickJoinFreeLink(driver);
		
		String emailAddressReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberReferralJoinee = objCashKaroUtility.generateMobileNumber();
		String name = "QAengineer";

		String OtpValue = new JoinFreePage(driver, testInfo).
		validateReferralByUserAtJoinFree(userName).
		enterFullName(name).
		enterEmail(emailAddressReferralJoinee).
		enterPassword().
		enterMobileNumber(mobileNumberReferralJoinee).
		clickOnGetOTPButton(mobileNumberReferralJoinee);
		
		new OTPPage(driver, testInfo).
		enterOTP(OtpValue).
		clickOnVerifyOTP();
		
		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreName).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(cbStoreName, cbShortDescription).
		clickOnStoreMainCTAForSignedInUser().
		validateRetailerPage(cbLinkURL, cbLinkURL).
		clickOnIntermediateBackbutton().
		clickBackButtonSearchPage().
		clickOnProfileIconForSignedUser().
		clickOnLogout();
		
		new HomePage(driver, testInfo).
		clickOnProfileIcon().
		clickOnSignInButton().
		enterUserName(emailAddress).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnReferralNetwork().
		validateTotalReferralCashbackEarnedValue("0.0").
		validateFriendsJoinedValue("1").
		validateDateJoined(currentDate).
		validateReferralName(name).
		validateStatus("Active").
		clickReferNow().
		clickBackButtonRedirectToReferralNetwork().
		clickBackButton().
		clickOnLogout();

		CashKaroUtility cu1 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu1;
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions1 = new AdminCoreFunction(driver, testInfo);		
		adminFunctions = adminFunctions1;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddressReferralJoinee);
		reportsPage.clickSubmit();
		String exitClick = reportsPage.extractExitClickValueFromResultstableFirstRow();
		
		AdminCashbackPage cashbackPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClick);
		cashbackPage.entertransactionId(exitClick);
		cashbackPage.enterOrderId(exitClick);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "FiveHundered"));
		cashbackPage.entercashback(getTestData(11, "TwoFifty"));
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClick);
		cashbackPage.clickSubmit();
				
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnSignInButton().
		enterUserName(emailAddressReferralJoinee).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateStatus(getTestData(11, "Pending"));

		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);		
		AdminCashbackPage cashbackPageTwo = new AdminCashbackPage(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		cashbackPage = cashbackPageTwo;
		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClick);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		
		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppAgain.launchCashKaroApp(driver);
		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateStatus(getTestData(11, "Confirmed")).
		clickBackButton().
		clickOnLogout();
		
		new HomePage(driver, testInfo).
		clickOnProfileIcon().
		clickOnSignInButton().
		enterUserName(emailAddress).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFive")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFive")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFive")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(11, "TwoFive")).
		validateAvailableCashbackValue(getTestData(11, "TwoFive")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(11, "TwoFive")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(name).
		validateCashbackReferralTab(getTestData(11, "TwoFive")).
		validateStatusReferralTab(getTestData(11, "Confirmed")).
		clickBackButton().
		clickOnReferralNetwork().
		validateTotalReferralCashbackEarnedValue("25.0").
		validateFriendsJoinedValue("1").
		validateDateJoined(currentDate).
		validateReferralName(name).
		validateStatus("Active").
		clickReferNow().
		clickBackButtonRedirectToReferralNetwork().
		clickBackButton();

		CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuTwo.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctionsThree = new AdminCoreFunction(driver, testInfo);		
		adminFunctions = adminFunctionsThree;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminUsersPage usersPageThree = new AdminUsersPage(driver, testInfo);
		usersPage = usersPageThree;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickUsersMenu();
		adminFunctions.clickUsersSubMenu();
		usersPage.selectSearchByDropDown("User Email");
		usersPage.enterKeyword(emailAddressReferralJoinee);
		usersPage.clickSubmitButton();
		usersPage.clickEditButton();
		usersPage.selectStatusFromDropDown("In-Active");
		usersPage.clickSubmitButtonUserForm();
		
		CashKaroUtility reopenCKAppAgainTwo = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppAgainTwo.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnReferralNetwork().
		validateTotalReferralCashbackEarnedValue("25.0").
		validateFriendsJoinedValue("1").
		validateDateJoined(currentDate).
		validateReferralName(name).
		validateStatus("In Active").
		clickReferNow().
		clickBackButtonRedirectToReferralNetwork().
		clickBackButton().
		clickOnLogout();
		
		CashKaroUtility reopenCKApp2 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp2.launchCashKaroApp(driver);
		
		new SeeVideoToEarnCashbackPage(driver, testInfo);

		CashKaroUtility objcashKaroutility1 = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver3 = objcashKaroutility1.launchChrome();
		driver= driver3;
		
		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);
		objcashKaroutility.clickJoinFreeLink(driver);
		
		String emailAddressAnotherReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberAnotherReferralJoinee = objCashKaroUtility.generateMobileNumber();
		String anotherName = "QAanalyst";

		String OtpValue1 = new JoinFreePage(driver, testInfo).
		validateReferralByUserAtJoinFree(userName).
		enterFullName(anotherName).
		enterEmail(emailAddressAnotherReferralJoinee).
		enterPassword().
		enterMobileNumber(mobileNumberAnotherReferralJoinee).
		clickOnGetOTPButton(mobileNumberAnotherReferralJoinee);

		OTPPage objOtpPage1 = new OTPPage(driver, testInfo);
		
		new OTPPage(driver, testInfo);
		objOtpPage1.
		enterOTP(OtpValue1).
		clickOnVerifyOTP();
		
		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreName).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(cbStoreName, cbShortDescription).
		clickOnStoreMainCTAForSignedInUser().
		validateRetailerPage(cbLinkURL, cbLinkURL).
		clickOnIntermediateBackbutton().
		clickBackButtonSearchPage().
		clickOnProfileIconForSignedUser().
		clickOnLogout();
		
		new HomePage(driver, testInfo).
		clickOnProfileIcon().
		clickOnSignInButton().
		enterUserName(emailAddress).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnReferralNetwork().
		validateTotalReferralCashbackEarnedValue("25.0").
		validateFriendsJoinedValue("2").
		validateDateJoined(currentDate).
		validateReferralName(anotherName).
		validateStatus("Active").
		validateDateJoined(currentDate).
		validateReferralName(name).
		validateStatus("In Active").
		clickReferNow().
		clickBackButtonRedirectToReferralNetwork().
		clickBackButton().
		clickOnLogout();

		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu2;
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);		
		adminFunctions = adminFunctions2;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminReportsPage reportsPage1 = new AdminReportsPage(driver, testInfo);
		reportsPage = reportsPage1;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddressAnotherReferralJoinee);
		reportsPage.clickSubmit();
		String exitClick1 = reportsPage.extractExitClickValueFromResultstableFirstRow();
		
		AdminCashbackPage cashbackPage1 = new AdminCashbackPage(driver, testInfo);
		cashbackPage = cashbackPage1;
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClick1);
		cashbackPage.entertransactionId(exitClick1);
		cashbackPage.enterOrderId(exitClick1);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("6000");
		cashbackPage.enterconfirmCommisionNetwork("4000");
		cashbackPage.entercashback("2250");
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClick1);
		cashbackPage.clickSubmit();
				
		CashKaroUtility reopenCKApp3 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp3.launchCashKaroApp(driver);
		
		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnSignInButton().
		enterUserName(emailAddressAnotherReferralJoinee).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateStatus(getTestData(11, "Pending"));

		CashKaroUtility cuOne1 = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne1.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsFour = new AdminCoreFunction(driver, testInfo);		
		AdminCashbackPage cashbackPageThree = new AdminCashbackPage(driver, testInfo);
		adminFunctions = adminFunctionsFour;
		cashbackPage = cashbackPageThree;
		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClick1);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		
		CashKaroUtility reopenCKAppAgain1 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppAgain1.launchCashKaroApp(driver);
		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateStatus(getTestData(11, "Confirmed")).
		clickBackButton().
		clickOnLogout();
		
		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).
		clickOnProfileIcon().
		clickOnSignInButton().
		enterUserName(emailAddress).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFive")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFive")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFive")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue("250.0").
		validateAvailableCashbackValue("250.0").
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab("250.0").
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(name).
		validateCashbackReferralTab(getTestData(11, "TwoFive")).
		validateStatusReferralTab(getTestData(11, "Confirmed")).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab("250.0").
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(anotherName).
		validateCashbackReferralTab("225.0").
		validateStatusReferralTab(getTestData(11, "Confirmed")).
			
		clickBackButton().
		clickOnReferralNetwork().
		validateTotalReferralCashbackEarnedValue("250.0").
		validateFriendsJoinedValue("2").
		validateDateJoined(currentDate).
		validateReferralName(name).
		validateStatus("In Active").
		validateDateJoined(currentDate).
		validateReferralName(name).
		validateStatus("Active").
		clickReferNow().
		clickBackButtonRedirectToReferralNetwork().
		clickBackButton().
		clickOnMyEarnings().
		clickReferralTabClick().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterBankNEFTPaymentDetails().
		clickOnGETPAID();
		
		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);
				
		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickCashbackTab().
		validateTotalCashbackEarnedValue("250.0").
		clickTotalCashbackEarnedValueHavingValueMoreThanZero("250.0").
		validateTotalCashbackEarnedValue("250.0").
		validatePaidCashbacksValue("250.0").
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(name).
		validateCashbackReferralTab(getTestData(11, "TwoFive")).
		validateStatusReferralTab(getTestData(11, "Requested")).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(anotherName).
		validateCashbackReferralTab("225.0").
		validateStatusReferralTab(getTestData(11, "Requested"));
					
		CashKaroUtility cu3 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu=cu3;
		driver = cu.launchChromeWebView(driver);
		AdminCoreFunction adminFunctions3 = new AdminCoreFunction(driver, testInfo);
		adminFunctions=adminFunctions3;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(emailAddress);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddress);
		PendingCashoutsPage.clickCreateCashout();
		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();
	
		CashKaroUtility reopenCKApp4 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp4.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).	
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue("250.0").
		clickTotalCashbackEarnedValueHavingValueMoreThanZero("250.0").
		validateTotalCashbackEarnedValue("250.0").
		validatePaidCashbacksValue("250.0").
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(name).
		validateCashbackReferralTab(getTestData(11, "TwoFive")).
		validateStatusReferralTab(getTestData(11, "Paid")).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(anotherName).
		validateCashbackReferralTab("225.0").
		validateStatusReferralTab(getTestData(11, "Paid"));
		
		reportStep("TC009 is  completed", "PASS");
			
	}

	//@Test
	public void myEarningsPage_TC012(){
		
		reportStep("TC010 is  started", "INFO");
		
		String cbStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String cbShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
		String cbLinkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

		String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
		String currentMonthYear= Utilities.currentMonthYear().trim();

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		
		String otpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);
		
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).
		clickOnVerifyOTP();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnReferralNetwork().
		validateObjectsMyReferrals().
		validateTotalReferralCashbackEarnedValue("0").
		validateFriendsJoinedValue("0").
		clickReferNow().
		clickBackButtonRedirectToReferralNetwork().
		clickBackButton().
		clickOnLogout().
		clickOnProfileIcon();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminUsersPage usersPage = new AdminUsersPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickUsersMenu();
		adminFunctions.clickUsersSubMenu();
		usersPage.selectSearchByDropDown("User Email");
		usersPage.enterKeyword(emailAddress);
		usersPage.clickSubmitButton();
		String userId = usersPage.extractUserIdValueFromResultstableFirstRow();
		String userName = usersPage.extractUserNameValueFromResultstableFirstRow();
		
		CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp1.launchCashKaroApp(driver);
		
		new SeeVideoToEarnCashbackPage(driver, testInfo);

		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = objcashKaroutility.launchChrome();
		driver= driver2;
		
		String url = Utilities.referralLinkGetCode(userId, userName);
		
		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);
		objcashKaroutility.clickJoinFreeLink(driver);
		
		String emailAddressReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberReferralJoinee = objCashKaroUtility.generateMobileNumber();
		String name = "QAengineer";

		String OtpValue = new JoinFreePage(driver, testInfo).
		validateReferralByUserAtJoinFree(userName).
		enterFullName(name).
		enterEmail(emailAddressReferralJoinee).
		enterPassword().
		enterMobileNumber(mobileNumberReferralJoinee).
		clickOnGetOTPButton(mobileNumberReferralJoinee);
		
		new OTPPage(driver, testInfo).
		enterOTP(OtpValue).
		clickOnVerifyOTP();
		
		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreName).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(cbStoreName, cbShortDescription).
		clickOnStoreMainCTAForSignedInUser().
		validateRetailerPage(cbLinkURL, cbLinkURL).
		clickOnIntermediateBackbutton().
		clickBackButtonSearchPage().
		clickOnProfileIconForSignedUser().
		clickOnLogout();
		
		new HomePage(driver, testInfo).
		clickOnProfileIcon().
		clickOnSignInButton().
		enterUserName(emailAddress).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnReferralNetwork().
		validateTotalReferralCashbackEarnedValue("0.0").
		validateFriendsJoinedValue("1").
		validateDateJoined(currentDate).
		validateReferralName(name).
		validateStatus("Active").
		clickReferNow().
		clickBackButtonRedirectToReferralNetwork().
		clickBackButton().
		clickOnLogout();

		CashKaroUtility cu1 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu1;
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions1 = new AdminCoreFunction(driver, testInfo);		
		adminFunctions = adminFunctions1;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddressReferralJoinee);
		reportsPage.clickSubmit();
		String exitClick = reportsPage.extractExitClickValueFromResultstableFirstRow();
		
		AdminCashbackPage cashbackPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClick);
		cashbackPage.entertransactionId(exitClick);
		cashbackPage.enterOrderId(exitClick);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "FiveHundered"));
		cashbackPage.entercashback(getTestData(11, "TwoFifty"));
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClick);
		cashbackPage.clickSubmit();
				
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnSignInButton().
		enterUserName(emailAddressReferralJoinee).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateStatus(getTestData(11, "Pending"));

		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);		
		AdminCashbackPage cashbackPageTwo = new AdminCashbackPage(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		cashbackPage = cashbackPageTwo;
		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClick);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		
		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppAgain.launchCashKaroApp(driver);
		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateStatus(getTestData(11, "Confirmed")).
		clickBackButton().
		clickOnLogout();
		
		objCashKaroUtility.addCashbackBonus(emailAddress, "225", getTestData(11, "Cashback"));
		
		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).
		clickOnProfileIcon().
		clickOnSignInButton().
		enterUserName(emailAddress).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFive")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(11, "TwoFive")).
		validateAvailableCashbackValue(getTestData(11, "TwoFiftyZeroZero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab("225").
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateAbsenceOrderAmount().
		validateCashbackAmount("225").
		validateStatus(getTestData(11, "Confirmed")).
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(11, "TwoFive")).
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(name).
		validateCashbackReferralTab(getTestData(11, "TwoFive")).
		validateStatusReferralTab(getTestData(11, "Confirmed")).
		clickReferralTabClick().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterBankNEFTPaymentDetails().
		clickOnGETPAID();
		
		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);
		
		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFive")).
		validatePaidCashbacksValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateAbsenceOrderAmount().
		validateCashbackAmount("225").
		validateStatus(getTestData(11, "Requested")).
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(name).
		validateCashbackReferralTab(getTestData(11, "TwoFive")).
		validateStatusReferralTab(getTestData(11, "Requested"));
				
		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu=cu2;
		driver = cu.launchChromeWebView(driver);
		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions=adminFunctions2;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(emailAddress);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddress);
		PendingCashoutsPage.clickCreateCashout();
		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();
	
		CashKaroUtility reopenCKApp2 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp=reopenCKApp2;
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).	
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFive")).
		validatePaidCashbacksValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(getTestData(5, "bonusStore")).
		validateAbsenceOrderAmount().
		validateCashbackAmount("225").
		validateStatus(getTestData(11, "Paid")).
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		validateDateReferralTab(currentDate).
		validateReferralNameReferralTab(name).
		validateCashbackReferralTab(getTestData(11, "TwoFive")).
		validateStatusReferralTab(getTestData(11, "Paid"));
		
		reportStep("TC010 is  completed", "PASS");
			
	}

	//@Test
	public void myEarningsPage_TC013(){
		
		reportStep("TC013 is  started", "INFO");
		
		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		
		String cbStoreNameOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String cbShortDescriptionOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");

		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("CB_Store_One");
		String cbStoreNameTwo = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
		String cbShortDescriptionTwo = objAppiumVariables.getRequiredShortDescription("str_Store_One_Short_Desc_App");

		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Two("CB_Store_Two");
		String cbStoreNameThree = objAppiumVariables.getRequiredStoreName("str_Store_Two_Name");
		String cbShortDescriptionThree = objAppiumVariables.getRequiredShortDescription("str_Store_Two_Short_Desc_App");

		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Eight("CB_Store_Eight");		
		String cbStoreNameFour = objAppiumVariables.getRequiredStoreName("str_Store_Eight_Name");
		String cbShortDescriptionFour = objAppiumVariables.getRequiredShortDescription("str_Store_Eight_Short_Desc_App");

		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Nine("CB_Store_Nine");
		String cbStoreNameFive = objAppiumVariables.getRequiredStoreName("str_Store_Nine_Name");
		String cbShortDescriptionFive = objAppiumVariables.getRequiredShortDescription("str_Store_Nine_Short_Desc_App");

		
		String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
		String currentMonthYear= Utilities.currentMonthYear().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		
		String otpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);
		
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).
		clickOnVerifyOTP();
		
		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnLogout();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		AdminUsersPage usersPage = new AdminUsersPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickUsersMenu();
		adminFunctions.clickUsersSubMenu();
		usersPage.selectSearchByDropDown("User Email");
		usersPage.enterKeyword(emailAddress);
		usersPage.clickSubmitButton();
		String userId = usersPage.extractUserIdValueFromResultstableFirstRow();
		String userName = usersPage.extractUserNameValueFromResultstableFirstRow();
		
		CashKaroUtility reopenCKApp1 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp1.launchCashKaroApp(driver);
		
		new SeeVideoToEarnCashbackPage(driver, testInfo);

		CashKaroUtility objcashKaroutility = new CashKaroUtility(udid, port, automationName, deviceName);
		AppiumDriver<MobileElement> driver2 = objcashKaroutility.launchChrome();
		driver= driver2;
		
		String url = Utilities.referralLinkGetCode(userId, userName);
		
		objcashKaroutility.closeAllTabsAndOneTabInChromeBrowser(driver);
		objcashKaroutility.clickAndEnterURLInSearchOrTypeWebAddress(driver, url);
		objcashKaroutility.clickJoinFreeLink(driver);
		
		String emailAddressReferralJoinee = objCashKaroUtility.generateEmail();
		String mobileNumberReferralJoinee = objCashKaroUtility.generateMobileNumber();
		String name = "QAengineer";

		String OtpValue = new JoinFreePage(driver, testInfo).
		validateReferralByUserAtJoinFree(userName).
		enterFullName(name).
		enterEmail(emailAddressReferralJoinee).
		enterPassword().
		enterMobileNumber(mobileNumberReferralJoinee).
		clickOnGetOTPButton(mobileNumberReferralJoinee);
		
		new OTPPage(driver, testInfo).
		enterOTP(OtpValue).
		clickOnVerifyOTP();
		
		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreNameOne).
		clickOnSearchedStoreImage().
		//validateStoreShortDescription(cbStoreNameOne, cbShortDescriptionOne).
		clickOnStoreMainCTAForSignedInUser().
		clickOnIntermediateBackbutton().
		clickBackButtonSearchPage().
		

		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreNameTwo).
		clickOnSearchedStoreImage().
		//validateStoreShortDescription(cbStoreNameTwo, cbShortDescriptionTwo).
		clickOnStoreMainCTAForSignedInUser().
		clickOnIntermediateBackbutton().
		clickBackButtonSearchPage().
		

		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreNameThree).
		clickOnSearchedStoreImage().
		//validateStoreShortDescription(cbStoreNameThree, cbShortDescriptionThree).
		clickOnStoreMainCTAForSignedInUser().
		clickOnIntermediateBackbutton().
		clickBackButtonSearchPage().
		

		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreNameFour).
		clickOnSearchedStoreImage().
		//validateStoreShortDescription(cbStoreNameFour, cbShortDescriptionFour).
		clickOnStoreMainCTAForSignedInUser().
		clickOnIntermediateBackbutton().
		clickBackButtonSearchPage().
		

		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreNameFive).
		clickOnSearchedStoreImage().
		//validateStoreShortDescription(cbStoreNameFive, cbShortDescriptionFive).
		clickOnStoreMainCTAForSignedInUser().
		clickOnIntermediateBackbutton().
		clickBackButtonSearchPage();

		CashKaroUtility cu1 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu = cu1;
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions1 = new AdminCoreFunction(driver, testInfo);		
		adminFunctions = adminFunctions1;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddressReferralJoinee);
		reportsPage.clickSubmit();
		String exitClickOne = reportsPage.extractRequiredExitId("2");
		String exitClickTwo = reportsPage.extractRequiredExitId("3");
		String exitClickThree = reportsPage.extractRequiredExitId("4");
		String exitClickFour = reportsPage.extractRequiredExitId("5");
		String exitClickFive = reportsPage.extractRequiredExitId("6");
		
		AdminCashbackPage cashbackPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		
		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClickOne);
		cashbackPage.entertransactionId(exitClickOne);
		cashbackPage.enterOrderId(exitClickOne);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		cashbackPage.entercashback("700");
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClickOne);
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
		
		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClickTwo);
		cashbackPage.entertransactionId(exitClickTwo);
		cashbackPage.enterOrderId(exitClickTwo);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		cashbackPage.entercashback("600");
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClickTwo);
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
		
		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClickThree);
		cashbackPage.entertransactionId(exitClickThree);
		cashbackPage.enterOrderId(exitClickThree);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		cashbackPage.entercashback("500");
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClickThree);
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
		
		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClickFour);
		cashbackPage.entertransactionId(exitClickFour);
		cashbackPage.enterOrderId(exitClickFour);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		cashbackPage.entercashback("400");
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClickFour);
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
		
		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClickFive);
		cashbackPage.entertransactionId(exitClickFive);
		cashbackPage.enterOrderId(exitClickFive);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		cashbackPage.entercashback("300");
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClickFive);
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
				
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchCashKaroApp(driver);
				
		new HomePage(driver, testInfo).	
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue("2500.0").
		clickTotalCashbackEarnedValueHavingValueMoreThanZero("2500.0").
		validateTotalCashbackEarnedValue("2500.0").
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue("2500.0").
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab("0.0").
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickMonthYearDropdown(currentMonthYear).	

		clickViewMore().
		clickCashbackTab().
		
		validateEntireBlockBelowRespectiveTab(currentDate, cbStoreNameOne, getTestData(11, "OneZeroZeroZero"), "300", getTestData(11, "Pending"), nintyDaysAhead).
		validateEntireBlockBelowRespectiveTab(currentDate, cbStoreNameTwo, getTestData(11, "OneZeroZeroZero"), "400", getTestData(11, "Pending"), nintyDaysAhead).
		validateEntireBlockBelowRespectiveTab(currentDate, cbStoreNameThree, getTestData(11, "OneZeroZeroZero"), "500", getTestData(11, "Pending"), nintyDaysAhead).
		validateEntireBlockBelowRespectiveTab(currentDate, cbStoreNameFour, getTestData(11, "OneZeroZeroZero"), "600", getTestData(11, "Pending"), nintyDaysAhead).
		validateEntireBlockBelowRespectiveTab(currentDate, cbStoreNameFive, getTestData(11, "OneZeroZeroZero"), "700", getTestData(11, "Pending"), nintyDaysAhead).

		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached();
		
		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);		
		AdminCashbackPage cashbackPageTwo = new AdminCashbackPage(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		cashbackPage = cashbackPageTwo;
		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		
		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClickOne);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
		
		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClickTwo);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
		
		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClickThree);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
		
		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClickFour);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
		
		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClickFive);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
		
		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppAgain.launchCashKaroApp(driver);
		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue("2500.0").
		clickTotalCashbackEarnedValueHavingValueMoreThanZero("2500.0").
		validateTotalCashbackEarnedValue("2500.0").
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue("2500.0").
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab("2500.0").
		clickMonthYearDropdown(currentMonthYear).	

		clickViewMore().
		clickCashbackTab().
		
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameOne, getTestData(11, "OneZeroZeroZero"), "300", getTestData(11, "Confirmed")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameTwo, getTestData(11, "OneZeroZeroZero"), "400", getTestData(11, "Confirmed")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameThree, getTestData(11, "OneZeroZeroZero"), "500", getTestData(11, "Confirmed")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameFour, getTestData(11, "OneZeroZeroZero"), "600", getTestData(11, "Confirmed")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameFive, getTestData(11, "OneZeroZeroZero"), "700", getTestData(11, "Confirmed")).

		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickCashbackTab().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterBankNEFTPaymentDetails().
		clickOnGETPAID();
		
		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumberReferralJoinee);
		
		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		
		clickCashbackTab().
		validateTotalCashbackEarnedValue("2500.0").
		clickTotalCashbackEarnedValueHavingValueMoreThanZero("2500.0").
		validateTotalCashbackEarnedValue("2500.0").
		validatePaidCashbacksValue("2500.0").
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		
		clickViewMore().
		clickCashbackTab().
		
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameOne, getTestData(11, "OneZeroZeroZero"), "300", getTestData(11, "Requested")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameTwo, getTestData(11, "OneZeroZeroZero"), "400", getTestData(11, "Requested")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameThree, getTestData(11, "OneZeroZeroZero"), "500", getTestData(11, "Requested")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameFour, getTestData(11, "OneZeroZeroZero"), "600", getTestData(11, "Requested")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameFive, getTestData(11, "OneZeroZeroZero"), "700", getTestData(11, "Requested"));
				
		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu=cu2;
		driver = cu.launchChromeWebView(driver);
		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions=adminFunctions2;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(emailAddressReferralJoinee);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddressReferralJoinee);
		PendingCashoutsPage.clickCreateCashout();
		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();
	
		CashKaroUtility reopenCKApp2 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp=reopenCKApp2;
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).	
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue("2500.0").
		clickTotalCashbackEarnedValueHavingValueMoreThanZero("2500.0").
		validateTotalCashbackEarnedValue("2500.0").
		validatePaidCashbacksValue("2500.0").
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).	

		clickViewMore().
		clickCashbackTab().
		
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameOne, getTestData(11, "OneZeroZeroZero"), "300", getTestData(11, "Paid")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameTwo, getTestData(11, "OneZeroZeroZero"), "400", getTestData(11, "Paid")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameThree, getTestData(11, "OneZeroZeroZero"), "500", getTestData(11, "Paid")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameFour, getTestData(11, "OneZeroZeroZero"), "600", getTestData(11, "Paid")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, cbStoreNameFive, getTestData(11, "OneZeroZeroZero"), "700", getTestData(11, "Paid")).
			
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickBackButton().
		clickOnLogout().
		
		
		clickOnProfileIcon().
		clickOnSignInButton().
		enterUserName(emailAddress).
		enterPassword("ppounds").
		clickSignInButtonForSuccess().
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue("250.0").
		clickTotalCashbackEarnedValueHavingValueMoreThanZero("250.0").
		validateTotalCashbackEarnedValue("250.0").
		validatePaidCashbacksValue("0.0").
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue("250.0").
		validateAvailableCashbackValue("250.0").
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab("250.0").
		clickMonthYearDropdown(currentMonthYear).
		
		clickViewMore().
		validateEntireBlockBelowReferralTab(currentDate, name, "30.0", getTestData(11, "Confirmed")).
		validateEntireBlockBelowReferralTab(currentDate, name, "40.0", getTestData(11, "Confirmed")).
		validateEntireBlockBelowReferralTab(currentDate, name, "50.0", getTestData(11, "Confirmed")).
		validateEntireBlockBelowReferralTab(currentDate, name, "50.0", getTestData(11, "Confirmed")).
		validateEntireBlockBelowReferralTab(currentDate, name, "70.0", getTestData(11, "Confirmed")).
		
		clickCashbackTab().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterBankNEFTPaymentDetails().
		clickOnGETPAID();
		
		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);
		
		new PaymentOTPPage(driver2, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		
		clickCashbackTab().
		validateTotalCashbackEarnedValue("50.0").
		clickTotalCashbackEarnedValueHavingValueMoreThanZero("50.0").
		validateTotalCashbackEarnedValue("50.0").
		validatePaidCashbacksValue("50.0").
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).

		clickViewMore().
		clickReferralTabClick().
		
		validateEntireBlockBelowReferralTab(currentDate, name, "30.0", getTestData(11, "Requested")).
		validateEntireBlockBelowReferralTab(currentDate, name, "40.0", getTestData(11, "Requested")).
		validateEntireBlockBelowReferralTab(currentDate, name, "50.0", getTestData(11, "Requested")).
		validateEntireBlockBelowReferralTab(currentDate, name, "60.0", getTestData(11, "Requested")).
		validateEntireBlockBelowReferralTab(currentDate, name, "70.0", getTestData(11, "Requested"));
				
		CashKaroUtility cu3 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu=cu3;
		driver = cu.launchChromeWebView(driver);
		AdminCoreFunction adminFunctions3 = new AdminCoreFunction(driver, testInfo);
		adminFunctions=adminFunctions3;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage1 = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage = PendingCashoutsPage1;
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(emailAddress);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddress);
		PendingCashoutsPage.clickCreateCashout();
		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();
	
		CashKaroUtility reopenCKApp3 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp=reopenCKApp3;
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).	
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue("50.0").
		clickTotalCashbackEarnedValueHavingValueMoreThanZero("50.0").
		validateTotalCashbackEarnedValue("50.0").
		validatePaidCashbacksValue("50.0").
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).	

		clickViewMore().
		clickReferralTabClick().
		
		validateEntireBlockBelowReferralTab(currentDate, name, "30.0", getTestData(11, "Paid")).
		validateEntireBlockBelowReferralTab(currentDate, name, "40.0", getTestData(11, "Paid")).
		validateEntireBlockBelowReferralTab(currentDate, name, "50.0", getTestData(11, "Paid")).
		validateEntireBlockBelowReferralTab(currentDate, name, "60.0", getTestData(11, "Paid")).
		validateEntireBlockBelowReferralTab(currentDate, name, "70.0", getTestData(11, "Paid")).
			
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickBackButton().
		clickOnLogout().
		
		/*******************************************************************************************************************/		
	
		reportStep("TC013 is  completed", "PASS");
			
	}

	//@Test
	public void myEarningsPage_TC014(){
		
		reportStep("TC014 is  started", "INFO");
		
		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		
		String rwStoreNameOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_Name");
		String rwShortDescriptionOne = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_App_Short_Description");

		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("RW_Store_One");
		String rwStoreNameTwo = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
		String rwShortDescriptionTwo = objAppiumVariables.getRequiredShortDescription("str_Store_One_Short_Desc_App");

		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Two("RW_Store_Two");
		String rwStoreNameThree = objAppiumVariables.getRequiredStoreName("str_Store_Two_Name");
		String rwShortDescriptionThree = objAppiumVariables.getRequiredShortDescription("str_Store_Two_Short_Desc_App");

		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Eight("RW_Store_Eight");		
		String rwStoreNameFour = objAppiumVariables.getRequiredStoreName("str_Store_Eight_Name");
		String rwShortDescriptionFour = objAppiumVariables.getRequiredShortDescription("str_Store_Eight_Short_Desc_App");

		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Nine("RW_Store_Nine");
		String rwStoreNameFive = objAppiumVariables.getRequiredStoreName("str_Store_Nine_Name");
		String rwShortDescriptionFive = objAppiumVariables.getRequiredShortDescription("str_Store_Nine_Short_Desc_App");

		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		String name = "QAengineer";
		
		String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
		String currentMonthYear= Utilities.currentMonthYear().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();
		
		String otpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName(name).
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);
		

		OTPPage objOtpPage = new OTPPage(driver, testInfo).
		enterOTP(otpValue).
		clickOnVerifyOTP();
		
		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(rwStoreNameOne).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(rwStoreNameOne, rwShortDescriptionOne).
		clickOnStoreMainCTAForSignedInUser().
		clickOnIntermediateBackbutton().
		clickBackButtonSearchPage().
		
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(rwStoreNameTwo).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(rwStoreNameTwo, rwShortDescriptionTwo).
		clickOnStoreMainCTAForSignedInUser().
		clickOnIntermediateBackbutton().
		clickBackButtonSearchPage().
		

		clickOnSearchIcon().
		enterTextIntoTheSearchBar(rwStoreNameThree).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(rwStoreNameThree, rwShortDescriptionThree).
		clickOnStoreMainCTAForSignedInUser().
		clickOnIntermediateBackbutton().
		clickBackButtonSearchPage().
		

		clickOnSearchIcon().
		enterTextIntoTheSearchBar(rwStoreNameFour).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(rwStoreNameFour, rwShortDescriptionFour).
		clickOnStoreMainCTAForSignedInUser().
		clickOnIntermediateBackbutton().
		clickBackButtonSearchPage().
		

		clickOnSearchIcon().
		enterTextIntoTheSearchBar(rwStoreNameFive).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(rwStoreNameFive, rwShortDescriptionFive).
		clickOnStoreMainCTAForSignedInUser().
		clickOnIntermediateBackbutton().
		clickBackButtonSearchPage();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddress);
		reportsPage.clickSubmit();
		String exitClickOne = reportsPage.extractRequiredExitId("2");
		String exitClickTwo = reportsPage.extractRequiredExitId("3");
		String exitClickThree = reportsPage.extractRequiredExitId("4");
		String exitClickFour = reportsPage.extractRequiredExitId("5");
		String exitClickFive = reportsPage.extractRequiredExitId("6");
		
		AdminCashbackPage cashbackPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		
		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClickOne);
		cashbackPage.entertransactionId(exitClickOne);
		cashbackPage.enterOrderId(exitClickOne);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		cashbackPage.entercashback("700");
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Rewards"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClickOne);
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
		
		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClickTwo);
		cashbackPage.entertransactionId(exitClickTwo);
		cashbackPage.enterOrderId(exitClickTwo);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		cashbackPage.entercashback("600");
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Rewards"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClickTwo);
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
		
		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClickThree);
		cashbackPage.entertransactionId(exitClickThree);
		cashbackPage.enterOrderId(exitClickThree);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		cashbackPage.entercashback("500");
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Rewards"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClickThree);
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
		
		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClickFour);
		cashbackPage.entertransactionId(exitClickFour);
		cashbackPage.enterOrderId(exitClickFour);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		cashbackPage.entercashback("400");
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Rewards"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClickFour);
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
		
		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClickFive);
		cashbackPage.entertransactionId(exitClickFive);
		cashbackPage.enterOrderId(exitClickFive);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "EightHundered"));
		cashbackPage.entercashback("300");
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Rewards"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClickFive);
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
				
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchCashKaroApp(driver);
				
		new HomePage(driver, testInfo).	
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero("2500.0").
		validateTotalRewardsEarnedValue("2500.0").
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue("2500.0").
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab("0.0").
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickMonthYearDropdown(currentMonthYear).	

		clickViewMore().
		clickRewardsTab().
		
		validateEntireBlockBelowRespectiveTab(currentDate, rwStoreNameOne, getTestData(11, "OneZeroZeroZero"), "300", getTestData(11, "Pending"), nintyDaysAhead).
		validateEntireBlockBelowRespectiveTab(currentDate, rwStoreNameTwo, getTestData(11, "OneZeroZeroZero"), "400", getTestData(11, "Pending"), nintyDaysAhead).
		validateEntireBlockBelowRespectiveTab(currentDate, rwStoreNameThree, getTestData(11, "OneZeroZeroZero"), "500", getTestData(11, "Pending"), nintyDaysAhead).
		validateEntireBlockBelowRespectiveTab(currentDate, rwStoreNameFour, getTestData(11, "OneZeroZeroZero"), "600", getTestData(11, "Pending"), nintyDaysAhead).
		validateEntireBlockBelowRespectiveTab(currentDate, rwStoreNameFive, getTestData(11, "OneZeroZeroZero"), "700", getTestData(11, "Pending"), nintyDaysAhead).

		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached();
		
		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);		
		AdminCashbackPage cashbackPageTwo = new AdminCashbackPage(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		cashbackPage = cashbackPageTwo;
		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		
		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClickOne);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
		
		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClickTwo);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
		
		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClickThree);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
		
		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClickFour);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
		
		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClickFive);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		cashbackPage.clickClear();
		
		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppAgain.launchCashKaroApp(driver);
		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero("2500.0").
		validateTotalRewardsEarnedValue("2500.0").
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue("2500.0").
		clickBackButton().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).

		clickRewardsTab().
		clickViewMore().
		clickRewardsTab().
		
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, rwStoreNameOne, getTestData(11, "OneZeroZeroZero"), "300", getTestData(11, "Confirmed")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, rwStoreNameTwo, getTestData(11, "OneZeroZeroZero"), "400", getTestData(11, "Confirmed")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, rwStoreNameThree, getTestData(11, "OneZeroZeroZero"), "500", getTestData(11, "Confirmed")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, rwStoreNameFour, getTestData(11, "OneZeroZeroZero"), "600", getTestData(11, "Confirmed")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, rwStoreNameFive, getTestData(11, "OneZeroZeroZero"), "700", getTestData(11, "Confirmed")).

		clickRewardsTab().
		clickMonthYearDropdown(currentMonthYear).	
		validateRewardEarningsValueRewardsTab("2500.0").
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		
		clickRewardsTab().
		clickOnRequestRewardsPaymentButton().
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID().
		clickOnGETPAID();
		
		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);
		
		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero("2500.0").
		validateTotalRewardsEarnedValue("2500.0").
		validatePaidRewardsValue("2500.0").
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		
		clickViewMore().
		clickRewardsTab().
		
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, rwStoreNameOne, getTestData(11, "OneZeroZeroZero"), "300", getTestData(11, "Requested")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, rwStoreNameTwo, getTestData(11, "OneZeroZeroZero"), "400", getTestData(11, "Requested")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, rwStoreNameThree, getTestData(11, "OneZeroZeroZero"), "500", getTestData(11, "Requested")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, rwStoreNameFour, getTestData(11, "OneZeroZeroZero"), "600", getTestData(11, "Requested")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, rwStoreNameFive, getTestData(11, "OneZeroZeroZero"), "700", getTestData(11, "Requested"));
		
		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu=cu2;
		driver = cu.launchChromeWebView(driver);
		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions=adminFunctions2;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(emailAddress);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddress);
		PendingCashoutsPage.clickCreateCashout();
		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();
	
		CashKaroUtility reopenCKApp2 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp=reopenCKApp2;
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).	
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero("2500.0").
		validateTotalRewardsEarnedValue("2500.0").
		validatePaidRewardsValue("2500.0").
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).

		clickRewardsTab().
		clickMonthYearDropdown(currentMonthYear).	

		clickViewMore().
		clickRewardsTab().
		
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, rwStoreNameOne, getTestData(11, "OneZeroZeroZero"), "300", getTestData(11, "Paid")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, rwStoreNameTwo, getTestData(11, "OneZeroZeroZero"), "400", getTestData(11, "Paid")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, rwStoreNameThree, getTestData(11, "OneZeroZeroZero"), "500", getTestData(11, "Paid")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, rwStoreNameFour, getTestData(11, "OneZeroZeroZero"), "600", getTestData(11, "Paid")).
		validateEntireBlockBelowRespectiveTabExceptExpected(currentDate, rwStoreNameFive, getTestData(11, "OneZeroZeroZero"), "700", getTestData(11, "Paid")).
			
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickBackButton().
		clickOnLogout().
				
		/*******************************************************************************************************************/		
	
		reportStep("TC014 is  completed", "PASS");
			
	}

	
	//@Test
	public void myEarningsPage_TC003_Updated(){
		
		reportStep("TC003 is  started", "INFO");
		
		String rwStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_Name");
		String rwShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_App_Short_Description");
		String rwLinkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_linkURL");
		
		String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();
		String currentMonthYear= Utilities.currentMonthYear().trim();
		String fourDaysBackDate = Utilities.customBackDate_dd_MMMM_yyyy(4);	
		String fourDaysBackedDate = Utilities.customBackDate_dd_MM_yyyy(4);

		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		
		String otpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnRecentClicks().
		validateObjectsUnSignedUserRecentClicksPage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);
		
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnRecentClicks().
		validateObjectsSignedUserRecentClicksPage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(rwStoreName).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(rwStoreName, rwShortDescription).
		clickOnStoreMainCTAForSignedInUser().
		waitForRedirectionalURL(rwLinkURL).
		validateRetailerPage(rwLinkURL, rwLinkURL).
		clickOnIntermediateBackbutton();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddress);
		reportsPage.clickSubmit();
		String exitClick = reportsPage.extractExitClickValueFromResultstableFirstRow();
		
		CashKaroUtility reopenCKAppOne = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppOne.launchCashKaroApp(driver);

		
		objCashKaroUtility.backDateTheExitClickForGivenDate(exitClick, 4);
		
		new HomePage(driver, testInfo).
		clickOnRecentClicks().
		clickEnabledRaiseTicket(rwStoreName).
		validateDateOfTransactionField(fourDaysBackedDate).
		validateRetailerDetailsField(rwStoreName).
		validateTransactionDetailsDate(fourDaysBackDate).
		clickBackButtonToRedirectToRecentClicks().
		clickBackButton();
		
		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctionsOne = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctionsOne;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminCashbackPage cashbackPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClick);
		cashbackPage.entertransactionId(exitClick);
		cashbackPage.enterOrderId(exitClick);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "FiveHundered"));
		cashbackPage.entercashback(getTestData(11, "TwoFifty"));
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Rewards"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClick);
		cashbackPage.clickSubmit();
		
		objCashKaroUtility.backDateTheExitClickForGivenDate(exitClick, 4);
				
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().

		
		clickRewardsTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalRewardsEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(11, "TwoFiftyZeroZero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(11, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickMonthYearDropdown(currentMonthYear).	
		validateDate(currentDate).
		validateRetailer(rwStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateOrderId(exitClick).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Pending")).
		validateExpected(nintyDaysAhead).
		
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		
		
		clickRewardsTab().
		validateRaiseTicketRewardsTab().
		clickRaiseTicketRewardsTab().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(rwStoreName).
		validateTransactionDetails(fourDaysBackDate, exitClick, getTestData(11, "Thousand"), getTestData(11, "Pending")).
		clickBackButton().
		clickBackButton().
		clickBackButton().
		clickOnRecentClickBottomIcon().
		clickEnabledSeeDetails(rwStoreName, "250.0");;

		CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuTwo.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);		
		AdminCashbackPage cashbackPageTwo = new AdminCashbackPage(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		cashbackPage = cashbackPageTwo;
		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClick);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		
		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppAgain.launchCashKaroApp(driver);
		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		
		clickRewardsTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalRewardsEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(11, "TwoFiftyZeroZero")).
		clickBackButton().
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(11, "TwoFiftyZeroZero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(rwStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateOrderId(exitClick).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Confirmed")).
		validateAbsenceExpected(nintyDaysAhead).
		clickRewardsTab().
		validateRaiseTicketRewardsTab().
		clickRaiseTicketRewardsTab().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(rwStoreName).
		validateTransactionDetails(fourDaysBackDate, exitClick, getTestData(11, "Thousand"), getTestData(11, "Confirmed")).
		clickBackButton().
		clickBackButton().
		clickBackButton().
		clickOnRecentClickBottomIcon().
		clickEnabledSeeDetails(rwStoreName, "250.0").
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		
		clickRewardsTab().
		clickOnRequestRewardsPaymentButton().
		clickOnPaymentMethodDropDown().
		selectAmazonGiftCardFromDropDown().
		enterAmazonGiftCardEmailID().
		clickOnGETPAID();
		
		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);
		
		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		
		clickRewardsTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalRewardsEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidRewardsValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickRewardsTab().
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(rwStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateOrderId(exitClick).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Requested")).
		validateAbsenceExpected(nintyDaysAhead).
		clickRewardsTab().
		validateRaiseTicketRewardsTab().
		clickRaiseTicketRewardsTab().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(rwStoreName).
		validateTransactionDetails(fourDaysBackDate, exitClick, getTestData(11, "Thousand"), getTestData(11, "Requested")).
		clickBackButton().
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));
		
		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu=cu2;
		driver = cu.launchChromeWebView(driver);
		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions=adminFunctions2;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(emailAddress);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddress);
		PendingCashoutsPage.clickCreateCashout();
		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();
	
		CashKaroUtility reopenCKApp2 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp=reopenCKApp2;
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).	
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		
		clickRewardsTab().
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalCashbackEarnedValue(getTestData(6, "Zero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalRewardsEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidRewardsValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(rwStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateOrderId(exitClick).
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Paid")).
		validateAbsenceExpected(nintyDaysAhead).
		clickRewardsTab().
		validateRaiseTicketRewardsTab().
		clickRaiseTicketRewardsTab().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(rwStoreName).
		validateTransactionDetails(fourDaysBackDate, exitClick, getTestData(11, "Thousand"), getTestData(11, "Paid")).
		clickBackButton().
		
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero"));
		
		
		reportStep("TC003 is  ended", "INFO");
		
	}
	
	@Test
	public void myEarningsPage_TC002_Updated() {
		
		reportStep("TC002 is  started", "INFO");
		
		String cbStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String cbShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
		String cbLinkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");
	
		String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
		String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();
		String currentMonthYear= Utilities.currentMonthYear().trim();
		String fourDaysBackDate = Utilities.customBackDate_dd_MMMM_yyyy(4);		
		String fourDaysBackedDate = Utilities.customBackDate_dd_MM_yyyy(4);
		
		CashKaroUtility objCashKaroUtility = new CashKaroUtility(driver, testInfo); 
		String emailAddress = objCashKaroUtility.generateEmail();
		String mobileNumber = objCashKaroUtility.generateMobileNumber();
		
		String otpValue = new SeeVideoToEarnCashbackPage(driver, testInfo).
		clickOnGoToHomePage().
		clickOnRecentClicks().
		validateObjectsUnSignedUserRecentClicksPage().
		clickOnProfileIcon().
		clickOnJoinFreeButton().
		enterFullName().
		enterEmail(emailAddress).
		enterPassword().
		enterMobileNumber(mobileNumber).
		clickOnGetOTPButton(mobileNumber);
		
		OTPPage objOtpPage = new OTPPage(driver, testInfo);
		objOtpPage.enterOTP(otpValue).
		clickOnVerifyOTP();

		new HomePage(driver, testInfo).
		clickOnRecentClicks().
		validateObjectsSignedUserRecentClicksPage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreName).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(cbStoreName, cbShortDescription).
		clickOnStoreMainCTAForSignedInUser().
		
		validateRetailerPage(cbLinkURL, cbLinkURL).
		clickOnIntermediateBackbutton();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminReportsPage reportsPage = new AdminReportsPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnReports();
		reportsPage.clickExitClickSubMenu();
		reportsPage.clickClear();
		reportsPage.selectSearchByDropDown("User Email");
		reportsPage.enterKeyword(emailAddress);
		reportsPage.clickSubmit();
		String exitClick = reportsPage.extractExitClickValueFromResultstableFirstRow();
				
		CashKaroUtility reopenCKAppOne = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppOne.launchCashKaroApp(driver);
		
		objCashKaroUtility.backDateTheExitClickForGivenDate(exitClick, 4);

		new HomePage(driver, testInfo).
		clickOnRecentClicks().
		clickEnabledRaiseTicket(cbStoreName).
		validateDateOfTransactionField(fourDaysBackedDate).
		validateRetailerDetailsField(cbStoreName).
		validateTransactionDetailsDate(fourDaysBackDate).
		clickBackButtonToRedirectToRecentClicks().
		clickBackButton();
		
		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctionsOne = new AdminCoreFunction(driver, testInfo);
		adminFunctions = adminFunctionsOne;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		AdminCashbackPage cashbackPage = new AdminCashbackPage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		cashbackPage.clickAddNewButton();
		cashbackPage.enterExitId(exitClick);
		cashbackPage.enterOrderId(exitClick);
		cashbackPage.setOrderDate();
		cashbackPage.enterOrderValue("1000");
		cashbackPage.enterconfirmCommisionNetwork(getTestData(11, "FiveHundered"));
		cashbackPage.entercashback(getTestData(11, "TwoFifty"));
		cashbackPage.selectCashbackTypeFromDropDown(getTestData(11, "Cashback"));
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.setPendingDate();
		cashbackPage.enterDetails(exitClick);
		cashbackPage.clickSubmit();
		
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).	
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(11, "TwoFiftyZeroZero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(11, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(cbStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateOrderIdAsNotAvailable().
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Pending")).
		validateExpected(nintyDaysAhead).
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickOnRequestCashbackPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickOnRequestRewardsPaymentButtonForThresholdNotReached().
		validateThresholdNotReached().
		clickCashbackTab().
		validateRaiseTicketCashbackTab().
		clickRaiseTicketCashbackTab().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(cbStoreName).
		validateTransactionDetails(fourDaysBackDate, exitClick, getTestData(11, "Thousand"), getTestData(11, "Pending")).
		clickBackButton().
		clickBackButton().
		clickBackButton().
		clickOnRecentClickBottomIcon().
		clickEnabledSeeDetails(cbStoreName, "250.0");

		CashKaroUtility cuTwo = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuTwo.launchChromeWebView(driver);
		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);		
		AdminCashbackPage cashbackPageTwo = new AdminCashbackPage(driver, testInfo);
		adminFunctions = adminFunctionsTwo;
		cashbackPage = cashbackPageTwo;
		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		cashbackPage.clickCashbackSubMenu();
		cashbackPage.selectSearchByStatusFromDropDown("Exit ID");
		cashbackPage.enterKeyword(exitClick);
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Pending"));
		cashbackPage.clickSubmit();
		cashbackPage.clickEdit();
		cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Confirmed"));
		cashbackPage.setConfirmDate();
		cashbackPage.clickSubmit();
		
		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppAgain.launchCashKaroApp(driver);
		PaymentOTPPage objPaymentOTP = new HomePage(driver, testInfo).		
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(6, "Zero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(11, "TwoFiftyZeroZero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(11, "TwoFiftyZeroZero")).
		clickMonthYearDropdown(currentMonthYear).	
		validateDate(currentDate).
		validateRetailer(cbStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateOrderIdAsNotAvailable().
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Confirmed")).
		validateAbsenceExpected(nintyDaysAhead).
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickCashbackTab().
		validateRaiseTicketCashbackTab().
		clickRaiseTicketCashbackTab().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(cbStoreName).
		validateTransactionDetails(fourDaysBackDate, exitClick, getTestData(11, "Thousand"), getTestData(11, "Confirmed")).	
		clickBackButton().
		clickBackButton().
		clickBackButton().
		clickOnRecentClickBottomIcon().
		clickEnabledSeeDetails(cbStoreName, "250.0").
		clickCashbackTab().
		clickOnRequestCashbackPaymentButton().
		clickOnPaymentMethodDropDown().
		selectBankPaymentNEFTFromDropDown().
		enterBankNEFTPaymentDetails().
		clickOnGETPAID();
		
		otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);
		
		objPaymentOTP.
		enterOTP(otpValue).
		clickOnVerifyOTPAndValidateThePaymentSuccessMessage().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).
		
		validateDate(currentDate).
		validateRetailer(cbStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateOrderIdAsNotAvailable().
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Requested")).
		validateAbsenceExpected(nintyDaysAhead).
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickCashbackTab().
		validateRaiseTicketCashbackTab().
		clickRaiseTicketCashbackTab().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(cbStoreName).
		validateTransactionDetails(fourDaysBackDate, exitClick, getTestData(11, "Thousand"), getTestData(11, "Requested")).
		clickBackButton();
		
		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		cu=cu2;
		driver = cu.launchChromeWebView(driver);
		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
		adminFunctions=adminFunctions2;
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();

		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickCashbackMenu();
		adminFunctions.clickPendingCashoutsSubMenu();

		AdminPendingCashoutsPage PendingCashoutsPage = new AdminPendingCashoutsPage(driver, testInfo);
		PendingCashoutsPage.selectSearchByDropDown("User Email");
		PendingCashoutsPage.enterKeyword(emailAddress);
		PendingCashoutsPage.clickSubmit();
		PendingCashoutsPage.clickEmail(emailAddress);
		PendingCashoutsPage.clickCreateCashout();
		driver.switchTo().alert().accept();
		PendingCashoutsPage.validateSuccessMessage();
	
		CashKaroUtility reopenCKApp2 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		reopenCKApp=reopenCKApp2;
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new HomePage(driver, testInfo).	
		clickOnProfileIconForSignedUser().
		clickOnMyEarnings().
		clickCashbackTab().
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		clickTotalCashbackEarnedValueHavingValueMoreThanZero(getTestData(11, "TwoFiftyZeroZero")).
		validateTotalCashbackEarnedValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePaidCashbacksValue(getTestData(11, "TwoFiftyZeroZero")).
		validatePendingCashbackValue(getTestData(6, "Zero")).
		validateReferralCashbackValue(getTestData(6, "Zero")).
		validateAvailableCashbackValue(getTestData(6, "Zero")).
		clickBackButton().
		clickTotalRewardsEarnedValueHavingValueMoreThanZero(getTestData(6, "Zero")).
		validateTotalRewardsEarnedValue(getTestData(6, "Zero")).
		validatePaidRewardsValue(getTestData(6, "Zero")).
		validatePendingRewardsValue(getTestData(6, "Zero")).
		validateAvailableRewardsValue(getTestData(6, "Zero")).
		clickBackButton().
		clickCashbackTab().
		validateCashbackEarningsValueCashbackTab(getTestData(6, "Zero")).
		clickMonthYearDropdown(currentMonthYear).		
		validateDate(currentDate).
		validateRetailer(cbStoreName).
		validateOrderAmount(getTestData(11, "OneZeroZeroZero")).
		validateOrderIdAsNotAvailable().
		validateCashbackAmount(getTestData(11, "TwoFifty")).
		validateStatus(getTestData(11, "Paid")).
		validateAbsenceExpected(nintyDaysAhead).
		clickRewardsTab().
		validateRewardEarningsValueRewardsTab(getTestData(6, "Zero")).
		clickReferralTabClick().
		validateReferralEarningsValueReferralTab(getTestData(6, "Zero")).
		clickCashbackTab().
		validateRaiseTicketCashbackTab().
		clickRaiseTicketCashbackTab().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(cbStoreName).
		validateTransactionDetails(fourDaysBackDate, exitClick, getTestData(11, "Thousand"), getTestData(11, "Paid"));
				
		reportStep("TC002 is  ended", "INFO");
		
	}
	
	
}