package com.ck.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.app.ck.base.AppDynamicVariables;
import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.ck.pages.SeeVideoToEarnCashbackPage;
import com.ck.pages.StorePage;
import com.ck.pages.admin.AdminCoreFunction;
import com.ck.pages.admin.ProductBrowserEditModePage;
import com.ck.pages.admin.AdminStoresPage;
import com.ck.pages.admin.AdminVoucherPage;

public class CKTestStoreValidation extends WrapperMethods {
	
	@Test
	public void storePage_ValidateCbStoreNormalPrimarySecondaryCashbackInPercentageValuesInStorePageRetailerPageAndVouchersWithCodeWithoutCodeValuesInStorePageRetailerPage_TC001() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("CB_Store_One");

		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
		String storeShortDesc = objAppiumVariables.getRequiredShortDescription("str_Store_One_Short_Desc_App");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_One_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_One_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_One_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_One_Secondary_Cashback_Details");
		String secondaryCashbackCouponCodeValue = objAppiumVariables.getRequiredVoucherCode("str_Store_One_Voucher_Two_CODE");
		String secondaryCashbackCouponCodeTitle = objAppiumVariables.getRequiredVoucherName("str_Store_One_Voucher_Two_Name");
		String retailerURL = objAppiumVariables.getRequiredLinkUrl("str_Store_One_linkURL");
		String storeTermsCondition = objAppiumVariables.getRequiredTermsConditions("str_Store_One_Final_Terms_condition");

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(storeOne, storeShortDesc).
		clickOnShowCashbackRates().
		validateNormalPrimaryCashbackRateInPercentage(storeOne, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInPercentage(storeOne, secondaryCashbackRateValue).
		validateNormalSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		clickOnHideCashbackRates().
		clickOnShowTermsAndCondition().
		validteTermsAndConditionValue(storeTermsCondition, storeOne).
		clickOnHideTermsAndCondition().
		validateVoucherCountInStorePage(Integer.parseInt(getTestData(4, "StoreOneVoucherCount"))).
		clickOnStoreMainCTA().
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation().
		validateRetailerPage(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackPercentageValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackPercentageValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton().
		clickOnVoucherButtonForSignedUser(0, storeOne).
		validateRetailerPageDontHandlePopup(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackPercentageValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackPercentageValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton().
		clickOnVoucherButtonForSignedUser(1, storeOne).
		validateRetailerPageDontHandlePopup(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackPercentageValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackPercentageValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton();

	}

	@Test
	public void storePage_ValidateCbStoreCalendarPrimarySecondaryCashbackInPercentageValuesInStorePageRetailerPageAndVouchersWithCodeWithoutCodeValuesInStorePageRetailerPage_TC002() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Two("CB_Store_Two");

		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_Two_Name");
		String storeShortDesc = objAppiumVariables.getRequiredShortDescription("str_Store_Two_Short_Desc_App");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Two_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Two_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Two_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Two_Secondary_Cashback_Details");
		String secondaryCashbackCouponCodeValue = objAppiumVariables.getRequiredVoucherCode("str_Store_Two_Voucher_Two_CODE");
		String secondaryCashbackCouponCodeTitle = objAppiumVariables.getRequiredVoucherName("str_Store_Two_Voucher_Two_Name");
		String retailerURL = objAppiumVariables.getRequiredLinkUrl("str_Store_Two_linkURL");

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(storeOne, storeShortDesc).
		clickOnShowCashbackRates().
		validateThePrimaryCashbackExpiryTag(0, getTestData(4, "PrimaryCashbackExpiryTag"), storeOne).
		validateTheSecondaryCashbackExpiryTag(0, getTestData(4, "SecondaryCashbackExpiryTag"), storeOne).
		validateNormalPrimaryCashbackRateInPercentage(storeOne, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInPercentage(storeOne, secondaryCashbackRateValue).
		validateNormalSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		clickOnHideCashbackRates().
		validateVoucherCountInStorePage(Integer.parseInt(getTestData(4, "StoreTwoVoucherCount"))).
		clickOnStoreMainCTA().
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation().
		validateRetailerPage(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackPercentageValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackPercentageValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton().
		clickOnVoucherButtonForSignedUser(0, storeOne).
		validateRetailerPageDontHandlePopup(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackPercentageValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackPercentageValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton().
		clickOnVoucherButtonForSignedUser(1, storeOne).
		validateRetailerPageDontHandlePopup(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackPercentageValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackPercentageValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton();



	}

	@Test
	public void storePage_ValidateCbStoreNormalPrimaryMultipleSecondaryCashbackInPercentageValuesInStorePageRetailerPageAndVoucherWithoutCodeValuesInStorePageRetailerPage_TC003() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Three("CB_Store_Three");

		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_Three_Name");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Three_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Three_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Three_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Three_Secondary_Cashback_Details");
		String retailerURL = objAppiumVariables.getRequiredLinkUrl("str_Store_Three_linkURL");

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		clickOnShowCashbackRates().
		validateNormalPrimaryCashbackRateInPercentage(storeOne, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInPercentage(storeOne, secondaryCashbackRateValue).
		validateNormalSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		validateSecondSecodaryCashbackDetailsTitle(secondaryCashbackRateDetails).
		validateSecondSecodaryCashbackRate(secondaryCashbackRateValue).
		clickOnHideCashbackRates().
		validateVoucherCountInStorePage(Integer.parseInt(getTestData(4, "StoreThreeVoucherCount"))).
		clickOnStoreMainCTA().
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation().
		validateRetailerPage(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackPercentageValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackPercentageValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton().
		clickOnVoucherButtonForSignedUser(0, storeOne).
		validateRetailerPageDontHandlePopup(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackPercentageValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackPercentageValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		validateBottomRateIconSecondSecondaryCashbackPercentageValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton();


	}

	@Test
	@Parameters(value= {"udid","port","systemPort","automationName","deviceName","androidVersion","APILevel"})
	public void storePage_ValidateStoresVouchersWithInactiveStatusAndChangeToActiveStatusForStoresVouchersAndValidate_TC004(String udid,String port,Integer systemPort,
			String automationName,String deviceName,String androidVersion,
			String APILevel){
		
		reportStep("apiLevel is:  "+APILevel+"", "INFO");

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);		
		objAppiumVariables.passStoreFourIndexAccordingToApiLevel(APILevel);
		String storeFourName =  objAppiumVariables.getRequiredStoreName("str_Store_Four_Name");
		
		reportStep("TC004 is:  "+storeFourName+"", "INFO");
		
		reportStep("TC004 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeFourName).
		validateInvisibilityOfStore(storeFourName);

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
		
		AdminStoresPage strPage = new AdminStoresPage(driver, testInfo);		
		strPage.enterStoreName(storeFourName);
		strPage.clickSubmitButton();
		strPage.clickEditButton(1);
		strPage.selectStatusFromDropDown("Active");
		strPage.clickSubmitButton();
		
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickVouchersSubMenu();
		
		AdminVoucherPage voucherPage = new AdminVoucherPage(driver, testInfo);
		voucherPage.clickAddNewButton();
		voucherPage.selectStoreNameFromDropDown(" "+storeFourName+"   (D, M, T, A) ");
		voucherPage.clickCheckBoxDesktop();
		voucherPage.clickCheckBoxMobile();
		voucherPage.clickCheckBoxTablet();
		voucherPage.clickCheckBoxApp();
		voucherPage.enterVoucherName("Voucher WithOut Code: "+storeFourName);
		voucherPage.enterAppVoucherName("App voucher WithOut Code: "+storeFourName);
		voucherPage.selectVoucherTypeFromDropDown("Promotion");
		voucherPage.selectStatusFromDropDown("In-Active");
		voucherPage.setIssueDate();
		voucherPage.setExpiryDate();
		voucherPage.clickSubmitButton();
		

		voucherPage.clickAddNewButton();
		voucherPage.selectStoreNameFromDropDown(" "+storeFourName+"   (D, M, T, A) ");
		voucherPage.clickCheckBoxDesktop();
		voucherPage.clickCheckBoxMobile();
		voucherPage.clickCheckBoxTablet();
		voucherPage.clickCheckBoxApp();
		voucherPage.enterVoucherName("Voucher With Code: "+storeFourName);
		voucherPage.enterAppVoucherName("App voucher With Code: "+storeFourName);
		voucherPage.selectVoucherTypeFromDropDown("Code");
		voucherPage.enterCode("Code");
		voucherPage.selectStatusFromDropDown("In-Active");
		voucherPage.setIssueDate();
		voucherPage.setExpiryDate();
		voucherPage.clickSubmitButton();
		
		ProductBrowserEditModePage pdtBrowserEdit = new ProductBrowserEditModePage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickProductFeedMenu();
		adminFunctions.clickProductBrowserEditModeSubMenu();
		pdtBrowserEdit.clickSolarUpdate();
		pdtBrowserEdit.clickRefreshPrimaryCashaback();
		pdtBrowserEdit.clickRefreshButton();
		pdtBrowserEdit.clickOkButton(driver);
		pdtBrowserEdit.clickRefreshSecondaryCashaback();
		pdtBrowserEdit.clickRefreshButton();
		pdtBrowserEdit.clickOkButton(driver);
		pdtBrowserEdit.clickRefreshVoucher();
		pdtBrowserEdit.clickRefreshButton();
		pdtBrowserEdit.clickOkButton(driver);
		
		CashKaroUtility reopenCKApp = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKApp.launchCashKaroApp(driver);

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeFourName).
		clickOnSearchedStoreImage().
		clickMoreOffersBtn().
		validteNovoucherSection(storeFourName);
		
		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctionsOne = new AdminCoreFunction(driver, testInfo);		
		adminFunctions = adminFunctionsOne;
		AdminVoucherPage voucherPageOne = new AdminVoucherPage(driver, testInfo);
		voucherPage = voucherPageOne;
		ProductBrowserEditModePage pdtBrowserEditOne = new ProductBrowserEditModePage(driver, testInfo);
		pdtBrowserEdit = pdtBrowserEditOne;
		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickVouchersSubMenu();
		
		voucherPage.dropdownSearchBy("Store Name");
		voucherPage.enterKeyword(storeFourName);
		voucherPage.clickSubmitButton();
		voucherPage.clickEditButton(1);
		voucherPage.selectStatusFromDropDown("Active");
		voucherPage.clickSubmitButton();
		

		voucherPage.dropdownSearchBy("Store Name");
		voucherPage.enterKeyword(storeFourName);
		voucherPage.clickSubmitButton();
		voucherPage.clickEditButton(2);
		voucherPage.selectStatusFromDropDown("Active");
		voucherPage.clickSubmitButton();
		
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickProductFeedMenu();
		adminFunctions.clickProductBrowserEditModeSubMenu();
		pdtBrowserEdit.clickSolarUpdate();
		pdtBrowserEdit.clickRefreshVoucher();
		pdtBrowserEdit.clickRefreshButton();
		pdtBrowserEdit.clickOkButton(driver);
		
		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppAgain.launchCashKaroApp(driver);

		String str = getTheStoreNumber(storeFourName);
		
		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeFourName).
		clickOnSearchedStoreImage();
				
		new StorePage(driver, testInfo).
		validateVoucherCountInStorePage(2);
		

	}

	@Test
	@Parameters(value= {"udid","port","systemPort","automationName","deviceName","androidVersion","APILevel"})
	public void storePage_ValidateStoresVouchersWithActiveStatusAndChangeToInactiveStatusForStoresVouchersAndValidate_TC005(String udid,String port,Integer systemPort,
			String automationName,String deviceName,String androidVersion,
			String APILevel){
		
		reportStep("API level is:  "+APILevel+"", "INFO");

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		objAppiumVariables.passStoreFiveIndexAccordingToApiLevel(APILevel);
		String storeFiveName = objAppiumVariables.getRequiredStoreName("str_Store_Five_Name");
		
		reportStep("TC005 is:  "+storeFiveName+"", "INFO");
		
		reportStep("TC005 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeFiveName).
		clickOnSearchedStoreImage();

		new StorePage(driver, testInfo).
		validateVoucherCountInStorePage(2);

		CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickStoresMenu();
		adminFunctions.clickVouchersSubMenu();
		
		AdminVoucherPage voucherPage = new AdminVoucherPage(driver, testInfo);
		voucherPage.dropdownSearchBy("Store Name");
		voucherPage.enterKeyword(storeFiveName);
		voucherPage.clickSubmitButton();
		voucherPage.clickEditButton(1);
		voucherPage.selectStatusFromDropDown("In-Active");
		voucherPage.clickSubmitButton();
		
		voucherPage.clickClearButton();
		voucherPage.dropdownSearchBy("Store Name");
		voucherPage.enterKeyword(storeFiveName);
		voucherPage.clickSubmitButton();
		voucherPage.clickEditButton(2);
		voucherPage.selectStatusFromDropDown("In-Active");
		voucherPage.clickSubmitButton();
		
		ProductBrowserEditModePage pdtBrowserEdit = new ProductBrowserEditModePage(driver, testInfo);
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickProductFeedMenu();
		adminFunctions.clickProductBrowserEditModeSubMenu();
		pdtBrowserEdit.clickSolarUpdate();
		pdtBrowserEdit.clickRefreshPrimaryCashaback();
		pdtBrowserEdit.clickRefreshButton();
		pdtBrowserEdit.clickOkButton(driver);
		pdtBrowserEdit.clickRefreshSecondaryCashaback();
		pdtBrowserEdit.clickRefreshButton();
		pdtBrowserEdit.clickOkButton(driver);
		pdtBrowserEdit.clickRefreshVoucher();
		pdtBrowserEdit.clickRefreshButton();
		pdtBrowserEdit.clickOkButton(driver);
		
		CashKaroUtility reopenCKAppAgain = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppAgain.launchCashKaroApp(driver);

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeFiveName).
		clickOnSearchedStoreImage().
		clickMoreOffersBtn().
		validteNovoucherSection(storeFiveName);
		
		CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cuOne.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctionsOne = new AdminCoreFunction(driver, testInfo);		
		adminFunctions = adminFunctionsOne;
		AdminStoresPage strPage = new AdminStoresPage(driver, testInfo);	
		ProductBrowserEditModePage pdtBrowserEditOne = new ProductBrowserEditModePage(driver, testInfo);
		pdtBrowserEdit = pdtBrowserEditOne;
				
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickStoresMenu();
		adminFunctions.clickStoresSubMenu();
			
		strPage.enterStoreName(storeFiveName);
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
		pdtBrowserEdit.clickRefreshSecondaryCashaback();
		pdtBrowserEdit.clickRefreshButton();
		pdtBrowserEdit.clickOkButton(driver);
		
		CashKaroUtility reopenCKAppAgainOne = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = reopenCKAppAgainOne.launchCashKaroApp(driver);

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeFiveName).
		validateInvisibilityOfStore(storeFiveName);
		
		//driver.quit();
		

	}

	@Test
	public void storePage_ValidateNoVoucherSectionInCbStoreHavingScheduledVoucherForNextDay_TC006() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Six("CB_Store_Six");

		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_Six_Name");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Six_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Six_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Six_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Six_Secondary_Cashback_Details");
		
		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		clickOnShowCashbackRates().
		validateThePrimaryCashbackExpiryTag(0, getTestData(4, "PrimaryCashbackExpiryTag"), storeOne).
		validateTheSecondaryCashbackExpiryTag(0, getTestData(4, "SecondaryCashbackExpiryTag"), storeOne).
		validateNormalPrimaryCashbackRateInPercentage(storeOne, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInPercentage(storeOne, secondaryCashbackRateValue).
		validateNormalSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		clickOnHideCashbackRates().
		clickMoreOffersBtn().
		validteNovoucherSection(storeOne);

	}

	@Test
	public void storePage_ValidateNoVoucherSectionInCbStoreHavingExpiredVoucherForNextDay_TC007() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Seven("CB_Store_Seven");

		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_Seven_Name");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Seven_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Seven_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Seven_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Seven_Secondary_Cashback_Details");

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		clickOnShowCashbackRates().
		validateThePrimaryCashbackExpiryTag(0, getTestData(4, "PrimaryCashbackExpiryTag"), storeOne).
		validateTheSecondaryCashbackExpiryTag(0, getTestData(4, "SecondaryCashbackExpiryTag"), storeOne).
		validateNormalPrimaryCashbackRateInCurrency(storeOne, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInCurrency(storeOne, secondaryCashbackRateValue).
		validateNormalSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		clickOnHideCashbackRates().
		clickMoreOffersBtn().
		validteNovoucherSection(storeOne);

	}

	@Test
	public void storePage_ValidateCbStoreNormalPrimarySecondaryCashbackInCurrencyValuesInStorePageRetailerPageAndVouchersWithCodeWithoutCodeValuesInStorePageRetailerPage_TC008() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Eight("CB_Store_Eight");

		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_Eight_Name");
		String storeShortDesc = objAppiumVariables.getRequiredShortDescription("str_Store_Eight_Short_Desc_App");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Eight_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Eight_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Eight_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Eight_Secondary_Cashback_Details");
		String secondaryCashbackCouponCodeValue = objAppiumVariables.getRequiredVoucherCode("str_Store_Eight_Voucher_Two_CODE");
		String secondaryCashbackCouponCodeTitle = objAppiumVariables.getRequiredVoucherName("str_Store_Eight_Voucher_Two_Name");
		String retailerURL = objAppiumVariables.getRequiredLinkUrl("str_Store_Eight_linkURL");

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(storeOne, storeShortDesc).
		clickOnShowCashbackRates().
		validateNormalPrimaryCashbackRateInCurrency(storeOne, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInCurrency(storeOne, secondaryCashbackRateValue).
		validateNormalSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		clickOnHideCashbackRates().
		validateVoucherCountInStorePage(Integer.parseInt(getTestData(4, "StoreOneVoucherCount"))).
		clickOnStoreMainCTA().
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation().
		validateRetailerPage(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackCurrencyValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackCurrencyValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton().
		clickOnVoucherButtonForSignedUser(0, storeOne).

		validateRetailerPageDontHandlePopup(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackCurrencyValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackCurrencyValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton().
		clickOnVoucherButtonForSignedUser(1, storeOne).
		validateRetailerPageDontHandlePopup(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackCurrencyValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackCurrencyValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton();

	}

	@Test
	public void storePage_ValidateCbStoreCalendarPrimarySecondaryCashbackInCurrencyValuesInStorePageRetailerPageAndVouchersWithCodeWithoutCodeValuesInStorePageRetailerPage_TC009() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Nine("CB_Store_Nine");

		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_Nine_Name");
		String storeShortDesc = objAppiumVariables.getRequiredShortDescription("str_Store_Nine_Short_Desc_App");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Nine_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Nine_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Nine_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Nine_Secondary_Cashback_Details");
		String secondaryCashbackCouponCodeValue = objAppiumVariables.getRequiredVoucherCode("str_Store_Nine_Voucher_Two_CODE");
		String secondaryCashbackCouponCodeTitle = objAppiumVariables.getRequiredVoucherName("str_Store_Nine_Voucher_Two_Name");
		String retailerURL = objAppiumVariables.getRequiredLinkUrl("str_Store_Nine_linkURL");


		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(storeOne, storeShortDesc).
		clickOnShowCashbackRates().
		validateThePrimaryCashbackExpiryTag(0, getTestData(4, "PrimaryCashbackExpiryTag"), storeOne).
		validateTheSecondaryCashbackExpiryTag(0, getTestData(4, "SecondaryCashbackExpiryTag"), storeOne).
		validateNormalPrimaryCashbackRateInCurrency(storeOne, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInCurrency(storeOne, secondaryCashbackRateValue).
		validateNormalSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		clickOnHideCashbackRates().
		validateVoucherCountInStorePage(Integer.parseInt(getTestData(4, "StoreTwoVoucherCount"))).
		clickOnStoreMainCTA().
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation().
		validateRetailerPage(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackCurrencyValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackCurrencyValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton().
		clickOnVoucherButtonForSignedUser(0, storeOne).
		validateRetailerPageDontHandlePopup(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackCurrencyValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackCurrencyValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton().
		clickOnVoucherButtonForSignedUser(1, storeOne).
		validateRetailerPageDontHandlePopup(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackCurrencyValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackCurrencyValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton();



	}

	@Test
	public void storePage_ValidateCbStoreCalendarPrimarySecondaryCashbackInPercentageValuesInStorePageAndSevenVouchersValuesInStorePage_TC0011() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Eleven("CB_Store_Eleven");
		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_Eleven_Name");

		String storeNum = getTheStoreNumber(storeOne);

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		validateStoreMainCTALabel("ACTIVATE CASHBACK").
		clickOnAllUserRadioButton().
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Three')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher One')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Four')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Five')]").
		
		//clickOnSeeDetailsAndValidateVoucherDescription("Voucher One").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Two')]").
	
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Six')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Seven')]").
		clickOnNewRadioUserButton().
	
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Three')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Four')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Five')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Two')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Six')]").
		clickOnSelectCategoryDropdown().
		validteSelectCategoryDropDown();

	}

	@Test
	public void storePage_ValidateDesktopDeviceSpecificCbStore_TC0012() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Twelve("CB_Store_Twelve");
		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_Twelve_Name");


		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		validateInvisibilityOfStore(storeOne);



	}

	@Test
	public void storePage_ValidateMobileDeviceSpecificCbStore_TC0013() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Thirteen("CB_Store_Thirteen");
		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_Thirteen_Name");

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		validateInvisibilityOfStore(storeOne);



	}

	@Test
	public void storePage_ValidateTabletDeviceSpecificCbStore_TC0014() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Fourteen("CB_Store_Fourteen");
		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_Fourteen_Name");

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		validateInvisibilityOfStore(storeOne);



	}

	@Test
	public void storePage_ValidateAppDeviceSpecificCbStore_TC0015() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Fifteen("CB_Store_Fifteen");

		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_Fifteen_Name");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Fifteen_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Fifteen_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Fifteen_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Fifteen_Secondary_Cashback_Details");

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		clickOnShowCashbackRates().
		//validateThePrimaryCashbackExpiryTag(0, getTestData(4, "PrimaryCashbackExpiryTag"), storeOne).
		//validateTheSecondaryCashbackExpiryTag(0, getTestData(4, "SecondaryCashbackExpiryTag"), storeOne).
		validateNormalPrimaryCashbackRateInCurrency(storeOne, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInCurrency(storeOne, secondaryCashbackRateValue).
		validateNormalSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		validateSecondSecodaryCashbackDetailsTitle(secondaryCashbackRateDetails).
		validateSecondSecodaryCashbackRate(secondaryCashbackRateValue).
		clickOnHideCashbackRates();


	}

	@Test
	public void storePage_ValidateRwStoreNormalPrimarySecondaryCashbackInPercentageValuesInStorePageRetailerPageAndVouchersWithCodeWithoutCodeValuesInStorePageRetailerPage_TC016() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("RW_Store_One");

		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
		String storeShortDesc = objAppiumVariables.getRequiredShortDescription("str_Store_One_Short_Desc_App");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_One_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_One_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_One_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_One_Secondary_Cashback_Details");
		String secondaryCashbackCouponCodeValue = objAppiumVariables.getRequiredVoucherCode("str_Store_One_Voucher_Two_CODE");
		String secondaryCashbackCouponCodeTitle = objAppiumVariables.getRequiredVoucherName("str_Store_One_Voucher_Two_Name");
		String retailerURL = objAppiumVariables.getRequiredLinkUrl("str_Store_One_linkURL");


		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(storeOne, storeShortDesc).
		clickOnShowCashbackRates().
		validateNormalPrimaryCashbackRateInPercentage(storeOne, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInPercentage(storeOne, secondaryCashbackRateValue).
		validateNormalSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		clickOnHideCashbackRates().
		validateVoucherCountInStorePage(Integer.parseInt(getTestData(4, "StoreOneVoucherCount"))).
		clickOnStoreMainCTA().
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation().
		validateRetailerPage(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackPercentageValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackPercentageValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton().
		clickOnVoucherButtonForSignedUser(0, storeOne).
		validateRetailerPageDontHandlePopup(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackPercentageValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackPercentageValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton().
		clickOnVoucherButtonForSignedUser(1, storeOne).
		validateRetailerPageDontHandlePopup(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackPercentageValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackPercentageValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton();

	}

	@Test
	public void storePage_ValidateRwStoreCalendarPrimarySecondaryCashbackInPercentageValuesInStorePageRetailerPageAndVouchersWithCodeWithoutCodeValuesInStorePageRetailerPage_TC017() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Two("RW_Store_Two");

		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_Two_Name");
		String storeShortDesc = objAppiumVariables.getRequiredShortDescription("str_Store_Two_Short_Desc_App");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Two_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Two_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Two_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Two_Secondary_Cashback_Details");
		String secondaryCashbackCouponCodeValue = objAppiumVariables.getRequiredVoucherCode("str_Store_Two_Voucher_Two_CODE");
		String secondaryCashbackCouponCodeTitle = objAppiumVariables.getRequiredVoucherName("str_Store_Two_Voucher_Two_Name");
		String retailerURL = objAppiumVariables.getRequiredLinkUrl("str_Store_Two_linkURL");

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(storeOne, storeShortDesc).
		clickOnShowCashbackRates().
		validateThePrimaryCashbackExpiryTag(0, getTestData(4, "PrimaryCashbackExpiryTag"), storeOne).
		validateTheSecondaryCashbackExpiryTag(0, getTestData(4, "SecondaryCashbackExpiryTag"), storeOne).
		validateNormalPrimaryCashbackRateInPercentage(storeOne, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInPercentage(storeOne, secondaryCashbackRateValue).
		validateNormalSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		clickOnHideCashbackRates().
		validateVoucherCountInStorePage(Integer.parseInt(getTestData(4, "StoreTwoVoucherCount"))).
		clickOnStoreMainCTA().
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation().
		validateRetailerPage(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackPercentageValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackPercentageValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton().
		clickOnVoucherButtonForSignedUser(0, storeOne).
		validateRetailerPageDontHandlePopup(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackPercentageValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackPercentageValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton().
		clickOnVoucherButtonForSignedUser(1, storeOne).
		validateRetailerPageDontHandlePopup(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackPercentageValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackPercentageValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton();



	}

	@Test
	public void storePage_ValidateRwStoreNormalPrimaryMultipleSecondaryCashbackInPercentageValuesInStorePageRetailerPageAndVoucherWithoutCodeValuesInStorePageRetailerPage_TC003TC018() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Three("RW_Store_Three");

		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_Three_Name");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Three_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Three_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Three_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Three_Secondary_Cashback_Details");
		String retailerURL = objAppiumVariables.getRequiredLinkUrl("str_Store_Three_linkURL");

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		clickOnShowCashbackRates().
		validateNormalPrimaryCashbackRateInPercentage(storeOne, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInPercentage(storeOne, secondaryCashbackRateValue).
		validateNormalSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		validateSecondSecodaryCashbackDetailsTitle(secondaryCashbackRateDetails).
		validateSecondSecodaryCashbackRate(secondaryCashbackRateValue).
		clickOnHideCashbackRates().
		validateVoucherCountInStorePage(Integer.parseInt(getTestData(4, "StoreThreeVoucherCount"))).
		clickOnStoreMainCTA().
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation().
		validateRetailerPage(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackPercentageValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackPercentageValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton().
		clickOnVoucherButtonForSignedUser(0, storeOne).
		validateRetailerPageDontHandlePopup(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackPercentageValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackPercentageValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		validateBottomRateIconSecondSecondaryCashbackPercentageValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton();


	}
	
	@Test
	public void storePage_ValidateNoVoucherSectionInCbStoreHavingScheduledVoucherForNextDay_TC021() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Six("RW_Store_Six");

		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_Six_Name");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Six_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Six_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Six_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Six_Secondary_Cashback_Details");

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		clickOnShowCashbackRates().
		validateThePrimaryCashbackExpiryTag(0, getTestData(4, "PrimaryCashbackExpiryTag"), storeOne).
		validateTheSecondaryCashbackExpiryTag(0, getTestData(4, "SecondaryCashbackExpiryTag"), storeOne).
		validateNormalPrimaryCashbackRateInPercentage(storeOne, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInPercentage(storeOne, secondaryCashbackRateValue).
		validateNormalSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		clickOnHideCashbackRates().
		clickMoreOffersBtn().
		validteNovoucherSection(storeOne);

	}

	@Test
	public void storePage_ValidateNoVoucherSectionInCbStoreHavingExpiredVoucherForNextDay_TC022() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Seven("RW_Store_Seven");

		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_Seven_Name");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Seven_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Seven_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Seven_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Seven_Secondary_Cashback_Details");

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		clickOnShowCashbackRates().
		validateThePrimaryCashbackExpiryTag(0, getTestData(4, "PrimaryCashbackExpiryTag"), storeOne).
		validateTheSecondaryCashbackExpiryTag(0, getTestData(4, "SecondaryCashbackExpiryTag"), storeOne).
		validateNormalPrimaryCashbackRateInCurrency(storeOne, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInCurrency(storeOne, secondaryCashbackRateValue).
		validateNormalSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		clickOnHideCashbackRates().
		clickMoreOffersBtn().
		validteNovoucherSection(storeOne);

	}

	@Test
	public void storePage_ValidateRwStoreNormalPrimarySecondaryCashbackInCurrencyValuesInStorePageRetailerPageAndVouchersWithCodeWithoutCodeValuesInStorePageRetailerPage_TC023() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Eight("RW_Store_Eight");

		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_Eight_Name");
		String storeShortDesc = objAppiumVariables.getRequiredShortDescription("str_Store_Eight_Short_Desc_App");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Eight_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Eight_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Eight_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Eight_Secondary_Cashback_Details");
		String secondaryCashbackCouponCodeValue = objAppiumVariables.getRequiredVoucherCode("str_Store_Eight_Voucher_Two_CODE");
		String secondaryCashbackCouponCodeTitle = objAppiumVariables.getRequiredVoucherName("str_Store_Eight_Voucher_Two_Name");
		String retailerURL = objAppiumVariables.getRequiredLinkUrl("str_Store_Eight_linkURL");

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(storeOne, storeShortDesc).
		clickOnShowCashbackRates().
		validateNormalPrimaryCashbackRateInCurrency(storeOne, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInCurrency(storeOne, secondaryCashbackRateValue).
		validateNormalSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		clickOnHideCashbackRates().
		validateVoucherCountInStorePage(Integer.parseInt(getTestData(4, "StoreOneVoucherCount"))).
		clickOnStoreMainCTA().
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation().
		validateRetailerPage(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackCurrencyValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackCurrencyValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton().
		clickOnVoucherButtonForSignedUser(0, storeOne).
		validateRetailerPageDontHandlePopup(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackCurrencyValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackCurrencyValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton().
		clickOnVoucherButtonForSignedUser(1, storeOne).
		validateRetailerPageDontHandlePopup(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackCurrencyValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackCurrencyValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton();





	}

	@Test
	public void storePage_ValidateRwStoreCalendarPrimarySecondaryCashbackInCurrencyValuesInStorePageRetailerPageAndVouchersWithCodeWithoutCodeValuesInStorePageRetailerPage_TC024() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Nine("RW_Store_Nine");

		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_Nine_Name");
		String storeShortDesc = objAppiumVariables.getRequiredShortDescription("str_Store_Nine_Short_Desc_App");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Nine_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Nine_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Nine_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Nine_Secondary_Cashback_Details");
		String secondaryCashbackCouponCodeValue = objAppiumVariables.getRequiredVoucherCode("str_Store_Nine_Voucher_Two_CODE");
		String secondaryCashbackCouponCodeTitle = objAppiumVariables.getRequiredVoucherName("str_Store_Nine_Voucher_Two_Name");
		String retailerURL = objAppiumVariables.getRequiredLinkUrl("str_Store_Nine_linkURL");

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(storeOne, storeShortDesc).
		clickOnShowCashbackRates().
		validateThePrimaryCashbackExpiryTag(0, getTestData(4, "PrimaryCashbackExpiryTag"), storeOne).
		validateTheSecondaryCashbackExpiryTag(0, getTestData(4, "SecondaryCashbackExpiryTag"), storeOne).
		validateNormalPrimaryCashbackRateInCurrency(storeOne, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInCurrency(storeOne, secondaryCashbackRateValue).
		validateNormalSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		clickOnHideCashbackRates().
		validateVoucherCountInStorePage(Integer.parseInt(getTestData(4, "StoreTwoVoucherCount"))).
		clickOnStoreMainCTA().
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation().
		validateRetailerPage(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackCurrencyValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackCurrencyValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton().
		clickOnVoucherButtonForSignedUser(0, storeOne).
		validateRetailerPageDontHandlePopup(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackCurrencyValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackCurrencyValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton().
		clickOnVoucherButtonForSignedUser(1, storeOne).
		validateRetailerPageDontHandlePopup(retailerURL).
		clickOnBottomRatesIcon().
		validateBottomRateIconPrimaryCashbackCurrencyValue(storeOne, primaryCashbackRateValue).
		validateBottomRateIconPrimaryCashbackRate_Details(storeOne, primaryCashbackRateDetails).
		validateBottomRateIconSecondaryCashbackCurrencyValue(storeOne, secondaryCashbackRateValue).
		validateBottomRateIconSecondaryCashbackRate_Details(storeOne, secondaryCashbackRateDetails).
		tapOnApp().
		clickOnBottomCouponsIcon().
		validateCouponCodeValue(secondaryCashbackCouponCodeValue).
		validateCouponTitle(secondaryCashbackCouponCodeTitle).
		clickOnCopyCode().
		tapOnApp().
		clickOnBottomTermsConditionIcon().
		validateTermsAndCondition().
		tapOnApp().
		clickOnIntermediateBackbutton();



	}

	@Test
	public void storePage_ValidateRWStoreCalendarPrimarySecondaryCashbackInPercentageValuesInStorePageAndSevenVouchersValuesInStorePage_TC0026() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Eleven("RW_Store_Eleven");
		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_Eleven_Name");

		String storeNum = getTheStoreNumber(storeOne);

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		//validateStoreShortDescription(storeOne, storeShortDesc).
		validateStoreMainCTALabel("ACTIVATE REWARDS").
		clickOnAllUserRadioButton().
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher One')]").
		//clickOnSeeDetailsAndValidateVoucherDescription("Voucher One").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Two')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Three')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Four')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Five')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Six')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Seven')]").
		clickOnNewRadioUserButton().
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Four')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Two')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Five')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Three')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Six')]").
		clickOnSelectCategoryDropdown().
		validteSelectCategoryDropDown();
		
	}

	@Test
	public void storePage_ValidateN18StoresStoreAndVoucherWithCodeWithoutCodeTC0031() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("N18_Store_One");

		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
		String storeShortDesc = objAppiumVariables.getRequiredShortDescription("str_Store_One_Short_Desc_App");
		String retailerURL = objAppiumVariables.getRequiredLinkUrl("str_Store_One_linkURL");


		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		validateStoreShortDescription(storeOne, storeShortDesc).
		clickOnStoreGrabDealMainCTA().
		enterUserName().
		enterPassword().
		clickSignInButtoForRetailerNavigation().
		validateRetailerPage(retailerURL);



	}

	@Test
	public void storePage_ValidateN18StoresStoreAndSevenVouchersValuesInStorePage_TC0032() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Eleven("N18_Store_Eleven");
		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_Eleven_Name");

		String storeNum = getTheStoreNumber(storeOne);

		reportStep("TC010 is  started", "INFO");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		//validateStoreShortDescription(storeOne, storeShortDesc).
		validateStoreMainCTALabel("GRAB DEAL").
		clickOnAllUserRadioButton().
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher One')]").
		//clickOnSeeDetailsAndValidateVoucherDescription("Voucher One").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Two')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Three')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Four')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Five')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Six')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Seven')]").
		clickOnNewRadioUserButton().
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Four')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Two')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Five')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Three')]").
		validateVoucherTitleContainsExpectedText("//android.widget.TextView[contains(@text,'Voucher Six')]").
		clickOnSelectCategoryDropdown().
		validteSelectCategoryDropDown();

	}

}