package com.ck.test;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.app.ck.base.AppDynamicVariables;
import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;
import com.ck.pages.SeeVideoToEarnCashbackPage;
import com.ck.pages.HomePage;
import com.ck.pages.JoinFreePage;
import com.ck.pages.OTPPage;
import com.ck.pages.StoreCategoryPage;
import com.ck.pages.admin.AdminCoreFunction;
import com.ck.pages.admin.AdminStoresPage;
import com.ck.pages.admin.ProductBrowserEditModePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.PressesKey;

public class CKTestStoreCategoryPageValidation extends WrapperMethods{

	public int bannerCount;
	public String storename;
	public String shortDescription;

	@Parameters({"deviceName"}) 
	@Test
	public void storeCategoryPage_ValidateHamburgerMenuFieldsCategoriesSubCategoriesNavigation_TC001(String deviceName) {
		
		reportStep("TC001 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		presenceOfAppExclusiveCategory().
		presenceOfHighestCashbackRates().
		presenceOfRetailerCategory();
		
		new HomePage(driver, testInfo).
		clickOnProfileIcon_enhanced().
		clickOnHamburgerIcon().
		clickHomeIcon().
		validateTheAbsenceOfSliderMenu().
		clickOnHamburgerIcon().
		clickAppExclusiveCategory().
		verifyStoreCategoryPageObjectPresence().
		verifyAppExclusivesPageNavigation().
		clickBackButton().
		clickOnHamburgerIcon().
		clickHighestCashbackRates().
		verifyStoreCategoryPageObjectPresence().
		verifyHighestCashbackStorePageNavigation().
		clickBackButton().
		clickOnHamburgerIcon().
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		verifyRetailerCategoryMenuIsGettingMaximizedAndMinimized(getTestData(9, "CountSeven")).
		clickAutomationSpecificCategoryInRetailerCategoryMenu().
		verifyRetailerCategoryMenuIsGettingMaximizedAndMinimized(getTestData(9, "CountOne")).
		clickSearchIcon().
		clickBackButton().
		clickBackButton().
		clickOnHamburgerIcon().
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		verifyAutomationSpecificSubCategoryNavigation().
		clickBackButtonForRetailerCategoryPageNavigation().
		verifyRetailerCategoryMenuNavigation().
		clickBackButton();
			
		reportStep("TC001 is  ended", "INFO");
		
	}

	@Parameters({"deviceName"}) 
	@Test
	public void storeCategoryPage_ValidateTabFunctionalityInStoreCategoryPage_TC002(String deviceName) {
		
		reportStep("TC002 is  started", "INFO");
		
		String popularityTabFirstCardCTALabelText = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_StoreCard_CTAButton_Label");
		String popularityTabLastCardCTALabelText = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_StoreCard_CTAButton_Label");
		
		String percentTabFirstCardCTALabelText = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_StoreCard_CTAButton_Label");
		
		String amountTabFirstCardCTALabelText = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_StoreCard_CTAButton_Label");
		
		String atozTabFirstCardCTALabelText = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_StoreCard_CTAButton_Label");
		String atozTabLastCardCTALabelText = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_StoreCard_CTAButton_Label");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnHamburgerIcon().
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		verifyAutomationSpecificSubCategoryNavigation().
		clickPopularTab(deviceName).
		verifyFirstCardDescription(popularityTabFirstCardCTALabelText).
		verifyLastCardDescription(popularityTabLastCardCTALabelText, deviceName).
		clickPercentTab(deviceName).
		verifyFirstCardDescription(percentTabFirstCardCTALabelText).
		clickAmountTab(deviceName).
		verifyFirstCardDescription(amountTabFirstCardCTALabelText).
		clickAzTab(deviceName).
		verifyFirstCardDescription(atozTabFirstCardCTALabelText).
		verifyLastCardDescription(atozTabLastCardCTALabelText, deviceName);
			
		reportStep("TC002 is  ended", "INFO");
		
	}

	@Parameters({"deviceName"}) 
	@Test
	public void storeCategoryPage_ValidateCbStoreOneStoreCardValuesInStoreCategoryPageStorePageAndRetailerPage_TC003(String deviceName) {

		String storeName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
		String voucherName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Voucher_One_App_Name");
		String shortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
		String voucherCount = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_StorePage_VoucherCount");
		String ctaLabel = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_StoreCard_CTAButton_Label");
		String primaryCashbackValue = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Primary_Cashback_Value");
		String primaryCashbackDetails = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Primary_Cashback_Details");
		String voucherCode = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Voucher_Code");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");
		String termsAndConditions = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_TermsAndCondition");
		
		reportStep("TC003 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		clickStoreCardImage(storeName, deviceName).
		validateStoreName(storeName).
		validateImage(storeName).
		validateStoreMainCTALabel(ctaLabel).
		validateStoreShortDescription(storeName, shortDescription).
		validateVoucherCountInStorePage(Integer.parseInt(voucherCount)).
		clickBackButton().
		clickStoreCardCTAButton(ctaLabel, deviceName).
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation().
		validateRetailerPage(linkURL, linkURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackCurrencyValue(storeName, primaryCashbackValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeName, primaryCashbackDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(voucherCode).
		validateCouponTitle(voucherName).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		validateTermsAndCondition(termsAndConditions).
		tapOnApp().
		clickOnIntermediateBackbuttonForStoreCat().
		verifyRedirectedToIntermediatePageAfterClickingStoreCardCTAButtonAsASignedUser(ctaLabel, deviceName);

	}

	@Parameters({"deviceName"}) 
	@Test
	public void storeCategoryPage_ValidateCbStoreTwoStoreCardValuesInStoreCategoryPageStorePageAndRetailerPage_TC004(String deviceName) {

		String storeName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_Name");
		String shortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_App_Short_Description");
		String voucherCount = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_StorePage_VoucherCount");
		String ctaLabel = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_StoreCard_CTAButton_Label");
		String primaryCashbackValue = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_Primary_Cashback_Value");
		String primaryCashbackDetails = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_Primary_Cashback_Details");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_linkURL");
		String termsAndConditions = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_Two", 1, "CB_Store_Two_TermsAndCondition");
		
		reportStep("TC004 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		clickStoreCardImage(storeName, deviceName).
		validateStoreName(storeName).
		validateImage(storeName).
		validateStoreMainCTALabel(ctaLabel).
		validateStoreShortDescription(storeName, shortDescription).
		validateVoucherCountInStorePage(Integer.parseInt(voucherCount)).
		clickBackButton().
		clickStoreCardCTAButton(ctaLabel, deviceName).
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation().
		validateRetailerPage(linkURL, linkURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackPercentageValue(storeName, primaryCashbackValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeName, primaryCashbackDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		disableBottomCouponsIcon().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		validateTermsAndCondition(termsAndConditions).
		tapOnApp().
		clickOnIntermediateBackbuttonForStoreCat().
		verifyRedirectedToIntermediatePageAfterClickingStoreCardCTAButtonAsASignedUser(ctaLabel, deviceName);

	}
	
	@Parameters({"deviceName"}) 
	@Test
	public void storeCategoryPage_ValidateRwStoreTwoStoreCardValuesInStoreCategoryPageStorePageAndRetailerPage_TC005(String deviceName) {

		String storeName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_Name");
		String voucherName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_Voucher_One_App_Name");
		String shortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_App_Short_Description");
		String voucherCount = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_StorePage_VoucherCount");
		String ctaLabel = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_StoreCard_CTAButton_Label");
		String primaryCashbackValue = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_Primary_Cashback_Value");
		String primaryCashbackDetails = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_Primary_Cashback_Details");
		String voucherCode = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_Voucher_Code");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_linkURL");
		String termsAndConditions = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_Two", 3, "RW_Store_Two_TermsAndCondition");
		
		reportStep("TC005 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		clickStoreCardImage(storeName, deviceName).
		validateStoreName(storeName).
		validateImage(storeName).
		validateStoreMainCTALabel(ctaLabel).
		validateStoreShortDescription(storeName, shortDescription).
		validateVoucherCountInStorePage(Integer.parseInt(voucherCount)).
		clickBackButton().
		clickStoreCardCTAButton(ctaLabel, deviceName).
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation().
		validateRetailerPage(linkURL, linkURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackCurrencyValue(storeName, primaryCashbackValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeName, primaryCashbackDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(voucherCode).
		validateCouponTitle(voucherName).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		validateTermsAndCondition(termsAndConditions).
		tapOnApp().
		clickOnIntermediateBackbuttonForStoreCat().
		verifyRedirectedToIntermediatePageAfterClickingStoreCardCTAButtonAsASignedUser(ctaLabel, deviceName);

	}
	
	@Parameters({"deviceName"}) 
	@Test
	public void storeCategoryPage_ValidateRwStoreOneStoreCardValuesInStoreCategoryPageStorePageAndRetailerPage_TC006(String deviceName) {

		String storeName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_Name");
		String shortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_App_Short_Description");
		String voucherCount = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_StorePage_VoucherCount");
		String ctaLabel = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_StoreCard_CTAButton_Label");
		String primaryCashbackValue = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_Primary_Cashback_Value");
		String primaryCashbackDetails = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_Primary_Cashback_Details");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_linkURL");
		String termsAndConditions = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("RW_Store_One", 2, "RW_Store_One_TermsAndCondition");
		
		reportStep("TC005 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		clickStoreCardImage(storeName, deviceName).
		validateStoreName(storeName).
		validateImage(storeName).
		validateStoreMainCTALabel(ctaLabel).
		validateStoreShortDescription(storeName, shortDescription).
		validateVoucherCountInStorePage(Integer.parseInt(voucherCount)).
		clickBackButton().
		clickStoreCardCTAButton(ctaLabel, deviceName).
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation().
		validateRetailerPage(linkURL, linkURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackCurrencyValue(storeName, primaryCashbackValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeName, primaryCashbackDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		disableBottomCouponsIcon().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		validateTermsAndCondition(termsAndConditions).
		tapOnApp().
		clickOnIntermediateBackbuttonForStoreCat().
		verifyRedirectedToIntermediatePageAfterClickingStoreCardCTAButtonAsASignedUser(ctaLabel, deviceName);

	}
	
	@Parameters({"deviceName"}) 
	@Test
	public void storeCategoryPage_ValidateN18StoreTwoStoreCardValuesInStoreCategoryPageStorePageAndRetailerPage_TC007(String deviceName) {

		String storeName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_Two", 5, "N18_Store_Two_Name");
		String voucherName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_Two", 5, "N18_Store_Two_Voucher_One_App_Name");
		String shortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_Two", 5, "N18_Store_Two_App_Short_Description");
		String voucherCount = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_Two", 5, "N18_Store_Two_StorePage_VoucherCount");
		String ctaLabel = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_Two", 5, "N18_Store_Two_StoreCard_CTAButton_Label");
		String voucherCode = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_Two", 5, "N18_Store_Two_Voucher_Code");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_Two", 5, "N18_Store_Two_linkURL");
		String termsAndConditions = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_Two", 5, "N18_Store_Two_TermsAndCondition");
		
		reportStep("TC007 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		clickStoreCardImage(storeName, deviceName).
		validateStoreName(storeName).
		validateImage(storeName).
		validateStoreMainCTALabel(ctaLabel).
		validateStoreShortDescription(storeName, shortDescription).
		validateN18VoucherCountInStorePage(Integer.parseInt(voucherCount)).
		clickBackButton().
		clickStoreCardCTAButton(ctaLabel, deviceName).
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation().
		validateRetailerPage(linkURL, linkURL).
		clickOnBottomRatesIcon().
		disableBottomRatesIcon().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(voucherCode).
		validateCouponTitle(voucherName).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		validateTermsAndCondition(termsAndConditions).
		tapOnApp().
		clickOnIntermediateBackbuttonForStoreCat().
		verifyRedirectedToIntermediatePageAfterClickingStoreCardCTAButtonAsASignedUser(ctaLabel, deviceName);

	}
	
	@Parameters({"deviceName"}) 
	@Test
	public void storeCategoryPage_ValidateN18StoreOneStoreCardValuesInStoreCategoryPageStorePageAndRetailerPage_TC008(String deviceName) {

		String storeName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_One", 4, "N18_Store_One_Name");
		String shortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_One", 4, "N18_Store_One_App_Short_Description");
		String voucherCount = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_One", 4, "N18_Store_One_StorePage_VoucherCount");
		String ctaLabel = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_One", 4, "N18_Store_One_StoreCard_CTAButton_Label");
		String linkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_One", 4, "N18_Store_One_linkURL");
		String termsAndConditions = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("N18_Store_One", 4, "N18_Store_One_TermsAndCondition");
		
		reportStep("TC008 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnHamburgerIcon().
		presenceOfHomeIcon().
		clickRetailerCategory().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		clickStoreCardImage(storeName, deviceName).
		validateStoreName(storeName).
		validateImage(storeName).
		validateStoreMainCTALabel(ctaLabel).
		validateStoreShortDescription(storeName, shortDescription).
		validateN18VoucherCountInStorePage(Integer.parseInt(voucherCount)).
		clickBackButton().
		clickStoreCardCTAButton(ctaLabel, deviceName).
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation().		
		validateRetailerPage(linkURL, linkURL).
		clickOnBottomRatesIcon().
		disableBottomRatesIcon().
		clickOnBottomCouponsIcon().
		disableBottomCouponsIcon().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		validateTermsAndCondition(termsAndConditions).
		tapOnApp().
		clickOnIntermediateBackbuttonForStoreCat().
		verifyRedirectedToIntermediatePageAfterClickingStoreCardCTAButtonAsASignedUser(ctaLabel, deviceName);

	}
	
	//@Parameters({"deviceName"}) 
	//@Test
	public void storeCategoryPage_ValidateBannerCountInStoreCategoryPageBeforeAndAfterAddingStore_TC009(String deviceName) {
		
		reportStep("TC009 is  started", "INFO");
		
		int bannerCountAfterAddingStore;
		
		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnHamburgerIcon().
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		verifyAutomationSpecificSubCategoryNavigation().
		verifyStoreCategoryPageHeaderTitle().
		verifyStoreCategoryPageBannerTitle().
		verifyStoreCategoryPageDropdown();
		
		StoreCategoryPage objDriver = new StoreCategoryPage(driver, testInfo);
		bannerCount = objDriver.verifyExtractCountFromBannerTitle();

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickStoresMenu();
		adminFunctions.clickStoresSubMenu();
		
		storename = Utilities.datestamp("Cashback Store: ");
		
		AdminStoresPage strPage = new AdminStoresPage(driver, testInfo);	
		strPage.clickAddNewButton();
		strPage.enterName(storename);
		strPage.enterUrlKey(storename);
		strPage.clickCheckBoxApp();
		shortDescription = strPage.enterShortDescription("App short description for temporary store");
		strPage.selectStatusFromDropDown("Active");
		strPage.selectAffiliateNetworkFromDropDown("Adda52");
		strPage.enterExitClick("500000");
		strPage.selectStoreTypeFromDropDown("Cashback");
		strPage.enterLinkURL(getTestData(4, "StoreURL"));
		strPage.clickCategoryTab();
		strPage.clickAutomationSpecificCategory();
		strPage.clickPrimaryCashbackTab();
		strPage.enterNetworkCommision("1000");
		strPage.enterCashback("50");
		strPage.enterCashbackPercentage("5");
		strPage.clickSecondaryCashbackTab();
		strPage.enterSecondaryCashback("1000");
		strPage.enterSecondaryCashbackDetails("Details");
		strPage.clickSecondaryCashbackCheckBoxApp();
		strPage.clickSubmitButton();
		
		ProductBrowserEditModePage pdtBrowserEdit = new ProductBrowserEditModePage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickProductFeedMenu();
		adminFunctions.clickProductBrowserEditModeSubMenu();
		pdtBrowserEdit.clickSolarUpdate();
		pdtBrowserEdit.clickRefreshPrimaryCashaback();
		pdtBrowserEdit.clickRefreshButton();
		pdtBrowserEdit.clickOkButton(driver);
		
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchCashKaroApp(driver);
		
		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnHamburgerIcon().
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		verifyFirstCardDescription(shortDescription).
		verifyStoreCardViewAllOffersAbsence(shortDescription, "(1)").
		clickAndVerifyStoreCardShortDescription(shortDescription, deviceName).
		validateStoreShortDescription(storename, shortDescription).
		validteNovoucherSection(storename.trim()).
		clickBackButton();
		
		StoreCategoryPage objDriver1 = new StoreCategoryPage(driver, testInfo);
		bannerCountAfterAddingStore = objDriver1.verifyExtractCountFromBannerTitle();
		
		validateTheActualValueWithExpectedValue(bannerCount + Integer.parseInt(getTestData(9, "CountOne")), bannerCountAfterAddingStore);

		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);

		AdminCoreFunction adminFunctionsTwo = new AdminCoreFunction(driver, testInfo);	
		adminFunctions = adminFunctionsTwo;
		AdminStoresPage strPageTwo = new AdminStoresPage(driver, testInfo);
		strPage = strPageTwo;
		ProductBrowserEditModePage pdtBrowserEditTwo = new ProductBrowserEditModePage(driver, testInfo);
		pdtBrowserEdit = pdtBrowserEditTwo;
				
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickStoresMenu();
		adminFunctions.clickStoresSubMenu();
				
		strPage.enterStoreName(storename);
		strPage.clickSubmitButton();
		strPage.clickEditButton(1);
		strPage.selectStatusFromDropDown("In-Active");
		strPage.clickSubmitButton();
		
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickProductFeedMenu();
		adminFunctions.clickProductBrowserEditModeSubMenu();
		pdtBrowserEdit.clickSolarUpdate();
		pdtBrowserEdit.clickRefreshPrimaryCashaback();
		pdtBrowserEdit.clickRefreshButton();
		pdtBrowserEdit.clickOkButton(driver);
		
		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppAgain.launchCashKaroApp(driver);
		
		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnHamburgerIcon().
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne();
		
		StoreCategoryPage objDriver2 = new StoreCategoryPage(driver, testInfo);
		bannerCountAfterAddingStore = objDriver2.verifyExtractCountFromBannerTitle();
		
		validateTheActualValueWithExpectedValue(Integer.parseInt(getTestData(9, "CountSix")), bannerCountAfterAddingStore);
			
		reportStep("TC009 is  ended", "INFO");
		
	}
	
	@Parameters({"deviceName"}) 
	@Test
	public void storeCategoryPage_ValidateRecommendedCbStoreStoreCardValuesInStorePage_TC010(String deviceName) {
		
		reportStep("TC010 is  started", "INFO");
	
		String storeName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("Recommended_CB_Store", 6, "Recommended_CB_Store_Name");
		String shortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("Recommended_CB_Store", 6, "Recommended_CB_Store_App_Short_Description");
		String voucherCount = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("Recommended_CB_Store", 6, "Recommended_CB_Store_StorePage_VoucherCount");
		String ViewAllOffersWithCount = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("Recommended_CB_Store", 6, "Recommended_CB_Store_ViewAllOffers_Count");
		String ctaLabel = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("Recommended_CB_Store", 6, "Recommended_CB_Store_StoreCard_CTAButton_Label");
		
		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnHamburgerIcon().
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryTwo().
		verifyAutomationSpecificSubCategoryNavigation().
		clickPopularTab(deviceName).
		clickRecommendedStoreCardImage(shortDescription, deviceName).
		validateStoreName(storeName).
		validateImage(storeName).
		validateStoreMainCTALabel(ctaLabel).
		clickBackButton().
		clickAndVerifyStoreCardShortDescription(shortDescription, deviceName).
		validateStoreShortDescription(storeName, shortDescription).
		clickBackButton().
		clickStoreCardRecommendedStoresViewAllOffersLink(shortDescription, ViewAllOffersWithCount, deviceName).
		validateVoucherCountInStorePage(Integer.parseInt(voucherCount)).
		clickBackButton();
			
		reportStep("TC010 is  ended", "INFO");
		
	}

	@Parameters({"deviceName"}) 
	@Test
	public void storeCategoryPage_ValidateRecommendedRwStoreStoreCardValuesInStorePage_TC011(String deviceName) {
		
		reportStep("TC011 is  started", "INFO");
	
		String storeName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("Recommended_RW_Store", 7, "Recommended_RW_Store_Name");
		String shortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("Recommended_RW_Store", 7, "Recommended_RW_Store_App_Short_Description");
		String voucherCount = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("Recommended_RW_Store", 7, "Recommended_RW_Store_StorePage_VoucherCount");
		String ViewAllOffersWithCount = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("Recommended_RW_Store", 7, "Recommended_RW_Store_ViewAllOffers_Count");
		String ctaLabel = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("Recommended_RW_Store", 7, "Recommended_RW_Store_StoreCard_CTAButton_Label");
		
		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnHamburgerIcon().
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryTwo().
		verifyAutomationSpecificSubCategoryNavigation().
		clickPopularTab(deviceName).
		clickRecommendedStoreCardImage(shortDescription, deviceName).
		validateStoreName(storeName).
		validateImage(storeName).
		validateStoreMainCTALabel(ctaLabel).
		clickBackButton().
		clickAndVerifyStoreCardShortDescription(shortDescription, deviceName).
		validateStoreShortDescription(storeName, shortDescription).
		clickBackButton().
		clickStoreCardRecommendedStoresViewAllOffersLink(shortDescription, ViewAllOffersWithCount, deviceName).
		validateVoucherCountInStorePage(Integer.parseInt(voucherCount)).
		clickBackButton();
			
		reportStep("TC011 is  ended", "INFO");
		
	}

}