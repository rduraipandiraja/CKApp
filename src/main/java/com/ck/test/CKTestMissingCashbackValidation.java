package com.ck.test;

import org.testng.annotations.Test;

import com.app.ck.base.AppDynamicVariables;
import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;
import com.ck.pages.SeeVideoToEarnCashbackPage;
import com.ck.pages.HomePage;
import com.ck.pages.OTPPage;
import com.ck.pages.admin.AdminCoreFunction;
import com.ck.pages.admin.AdminMissingCashback;
import com.ck.pages.admin.AdminStoresPage;

public class CKTestMissingCashbackValidation extends WrapperMethods {
	
	@Test
	public void AAA_preRequisiteRun() {
		

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);		
		
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("CB_Store_One");
		String cbStoreOne = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
		
		//Get the Second Store Name
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Two("CB_Store_Two");
		String cbStoreTwo = objAppiumVariables.getRequiredStoreName("str_Store_Two_Name");
		
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
		strPage.enterStoreNameUsingJS(cbStoreOne);
		strPage.clickSubmitButton();
		strPage.clickEditButton(1);
		strPage.enterOrderIdFormat(getTestData(10, "TransactionIdSampleFormat_1"));
		strPage.clickSubmitButton();
		
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickStoresMenu();
		adminFunctions.clickStoresSubMenu();
		
		strPage.enterStoreNameUsingJS(cbStoreTwo);
		strPage.clickSubmitButton();
		strPage.clickEditButton(1);
		strPage.enterOrderIdFormat(getTestData(10, "TransactionIdSampleFormat_2"));
		strPage.clickSubmitButton();		
		
		
	}

	@Test
	public void missingCashbackValidateTrasactionSampleFormateForTwoDifferentStoreOnSameDate_TC005() {
		
		/*
		 * Step 1 : Join As a new User and make an exit click 
		 */
		
		//Get the First Store Name
		
		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("CB_Store_One");
		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
		
		
		//Get the Second Store Name
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Two("CB_Store_Two");
		String storeTwo = objAppiumVariables.getRequiredStoreName("str_Store_Two_Name");
		
		//Store one and Store 2 common redirection URL
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
		new HomePage(driver, testInfo).
		clickOnSearchIcon().
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
		 
		System.out.println("Exit click id for the First store : "+ exitClickID);
		
		/*
		 * Step 3 : Back date the Exit click for 5 days and Raise the ticket in the Missing cashback page
		 */
		
		objCashKaroUtility.backDateTheExitClickForGivenDate(exitClickID,4);
		
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
		enterTransactionId(getTestData(10, "TransactionId"),getTestData(10, "TransactionIdSampleFormat_1")).
		enterTransactionAmountPaid(getTestData(10, "TransacationAmountPaid")).
		enterCouponCodeUsed(getTestData(10, "CouponCodeUsed")).
		enterTransactionDetails(getTestData(10, "TransactionDetails")).
		clickOnSubmitButton().
		validateTheTicketRaisedStoreName(storeOne).
		validateTheTicketRaisedDate().
		validateTransactionAmountAtMissingCashback(getTestData(10, "TransacationAmountPaid")).
		valdiateTheRaisedTicketStatus(getTestData(10, "ReceivedStatus")).
		clickOnBackButton().
		
		/*
		 * Step 4 : Repeat the step 1,2 and 3 for the different store the validate the Multiple Missing Cashback ticket raised for the same user 
		 */
		
		/*
		 * Step 1 : Join As a new User and make an exit click for the second different store
		 */
		
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeTwo).
		clickOnSearchedStoreImage().
		clickOnStoreMainCTAForSignedInUser().
		validateRetailerPage(retailerURL).
		clickOnIntermediateBackbutton();
		
		/*
		 * Step 2 Second time : Navigate to Admin and return the Exit click ID
		 * 
		 */
		
		//Admin actions
		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu2.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);	
		adminFunctions = adminFunctions2;


		//Admin actions :
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
		exitClickID = adminFunctions.extractExitClickFromTable();
		 
		System.out.println("Exit click for second store is  : "+ exitClickID);
		
		/*
		 * Step 3 : Back date the Exit click for 5 days and Raise the ticket in the Missing cashback page
		 * 
		 */
		
		objCashKaroUtility.backDateTheExitClickForGivenDate(exitClickID,4);
		
		CashKaroUtility cuForCKApp_2 =  new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = cuForCKApp_2.launchCashKaroApp(driver);
		
		new HomePage(driver,testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMissingCashback().
		clickOnAddticketButtonForExistingUser().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		validateSizeOfMultipleRetailerOption(2).
		selectRetailerOption(storeTwo).
		clickOnTransactionDetails().
		enterTransactionId(getTestData(10, "TransactionId"),getTestData(10, "TransactionIdSampleFormat_2")).
		enterTransactionAmountPaid(getTestData(10, "TransacationAmountPaid")).
		enterCouponCodeUsed(getTestData(10, "CouponCodeUsed")).
		enterTransactionDetails(getTestData(10, "TransactionDetails")).
		clickOnSubmitButton().
		validateTheTicketRaisedStoreName(storeOne).
		validateTheTicketRaisedDate().
		validateTransactionAmountAtMissingCashback(getTestData(10, "TransacationAmountPaid")).
		valdiateTheRaisedTicketStatus(getTestData(10, "ReceivedStatus"));
		

	}

	@Test
	public void MissingCashbackWithReceivedStatusValidation_TC001() {
		
		/*
		 * Step 1 : Join As a new User and make an exit click 
		 */
		
		//Get the First Store Name
		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("CB_Store_One");
		String storeOne 	= objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
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
		validateTheTicketRaisedStoreName(storeOne).
		validateTheTicketRaisedDate().
		validateTransactionAmountAtMissingCashback(getTestData(10, "TransacationAmountPaid")).
		valdiateTheRaisedTicketStatus(getTestData(10, "ReceivedStatus")).
		validateStaticTextsInMissingCashbackPageForExistingUser().
		clickOnViewLink(storeOne).
		enterUserReply(getTestData(10, "UserReply")).
		clickOnSubmitButton();
		
		//Admin actions
		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu2.launchChromeWebView(driver);
	
		AdminMissingCashback adminMissingCashback = new AdminMissingCashback(driver, testInfo);
		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);	
		adminFunctions = adminFunctions2;
		
		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnInteractiveMainMenu();
		adminFunctions.clickOnsubMenuUserTickets();
		adminMissingCashback.selectSearchbyExitClickIDFromDropDown();
		adminMissingCashback.enerKeyWordatAdminMissingCashbackPage(exitClickID);
		adminMissingCashback.clickOnSearchButton();
		String ticketId = adminMissingCashback.fetchTicketID(exitClickID);
		adminMissingCashback.clickOnEditButton(exitClickID);
		adminMissingCashback.enterAdminReply(getTestData(10, "AdminReply"));
		adminMissingCashback.selectTicketStatus(getTestData(10, "ReceivedStatus"));
		adminMissingCashback.clickOnSubmitButton();
		isElementLocatedByIDPresent("adminMessageSuccess");
		
		CashKaroUtility cuForCKApp_2 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = cuForCKApp_2.launchCashKaroApp(driver);
		
		new HomePage(driver,testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMissingCashback().
		clickOnViewLink(storeOne).
		validateTicketNumber(ticketId).
		validateDateOfQuery(Utilities.today_dd_MMM_yyyy()).
		validateMerchantname(storeOne).
		validateTransactionAmount(getTestData(10, "TransacationAmountPaid")).
		validateTranactionReference(getTestData(10, "TransactionId")).
		validateCouponCode(getTestData(10, "CouponCodeUsed")).
		validateDetails(getTestData(10, "TransactionDetails")).
		validateStaus(getTestData(10, "ReceivedStatus")).
		validateDateOfStatusUpdate(Utilities.today_dd_MMM_yyyy()).
		validateUserReply(getTestData(10, "UserReply")).
		validateCustomerServicesReply(getTestData(10, "AdminReply"));

	}

	@Test
	public void MissingCashbackWithResolvedStatusValidation_TC002() {

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
		validateTheTicketRaisedStoreName(storeOne).
		validateTheTicketRaisedDate().
		validateTransactionAmountAtMissingCashback(getTestData(10, "TransacationAmountPaid")).
		valdiateTheRaisedTicketStatus(getTestData(10, "ReceivedStatus")).clickOnViewLink(storeOne).
		enterUserReply(getTestData(10, "UserReply")).
		clickOnSubmitButton();

		//Admin actions
		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu2.launchChromeWebView(driver);

		AdminMissingCashback adminMissingCashback = new AdminMissingCashback(driver, testInfo);
		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);	
		adminFunctions = adminFunctions2;

		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnInteractiveMainMenu();
		adminFunctions.clickOnsubMenuUserTickets();
		adminMissingCashback.selectSearchbyExitClickIDFromDropDown();
		adminMissingCashback.enerKeyWordatAdminMissingCashbackPage(exitClickID);
		adminMissingCashback.clickOnSearchButton();
		String ticketId = adminMissingCashback.fetchTicketID(exitClickID);
		adminMissingCashback.clickOnEditButton(exitClickID);
		adminMissingCashback.enterAdminReply(getTestData(10, "AdminReply"));
		adminMissingCashback.selectTicketStatus(getTestData(10, "SentToRetailerStatus"));
		adminMissingCashback.clickOnSubmitButton();
		isElementLocatedByIDPresent("adminMessageSuccess");


		CashKaroUtility cuForCKApp_2 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = cuForCKApp_2.launchCashKaroApp(driver);
		new HomePage(driver,testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMissingCashback().
		clickOnViewLink(storeOne).
		validateTicketNumber(ticketId).
		validateDateOfQuery(Utilities.today_dd_MMM_yyyy()).
		validateMerchantname(storeOne).
		validateTransactionAmount(getTestData(10, "TransacationAmountPaid")).
		validateTranactionReference(getTestData(10, "TransactionId")).
		validateCouponCode(getTestData(10, "CouponCodeUsed")).
		validateDetails(getTestData(10, "TransactionDetails")).
		validateStaus(getTestData(10, "SentToRetailerStatus")).
		validateDateOfStatusUpdate(Utilities.today_dd_MMM_yyyy()).
		validateUserReply(getTestData(10, "UserReply")).
		validateCustomerServicesReply(getTestData(10, "AdminReply"));

	}

	@Test
	public void MissingCashbackWithSentToRetailerStatusValidation_TC003() {
		
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
		validateTheTicketRaisedStoreName(storeOne).
		validateTheTicketRaisedDate().
		validateTransactionAmountAtMissingCashback(getTestData(10, "TransacationAmountPaid")).
		valdiateTheRaisedTicketStatus(getTestData(10, "ReceivedStatus")).clickOnViewLink(storeOne).
		enterUserReply(getTestData(10, "UserReply")).
		clickOnSubmitButton();

		//Admin actions
		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu2.launchChromeWebView(driver);

		AdminMissingCashback adminMissingCashback = new AdminMissingCashback(driver, testInfo);
		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);	
		adminFunctions = adminFunctions2;

		adminFunctions.naviagate_AdminPage();
		adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
		adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
		adminFunctions.clickSubmit();
		adminFunctions.clickOnHamburgerMenu();
		adminFunctions.clickOnInteractiveMainMenu();
		adminFunctions.clickOnsubMenuUserTickets();
		adminMissingCashback.selectSearchbyExitClickIDFromDropDown();
		adminMissingCashback.enerKeyWordatAdminMissingCashbackPage(exitClickID);
		adminMissingCashback.clickOnSearchButton();
		String ticketId = adminMissingCashback.fetchTicketID(exitClickID);
		adminMissingCashback.clickOnEditButton(exitClickID);
		adminMissingCashback.enterAdminReply(getTestData(10, "AdminReply"));
		adminMissingCashback.selectTicketStatus(getTestData(10, "ResolvedStatus"));
		adminMissingCashback.clickOnSubmitButton();
		isElementLocatedByIDPresent("adminMessageSuccess");


		CashKaroUtility cuForCKApp_2 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = cuForCKApp_2.launchCashKaroApp(driver);
		new HomePage(driver,testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMissingCashback().
		clickOnViewLink(storeOne).
		validateTicketNumber(ticketId).
		validateDateOfQuery(Utilities.today_dd_MMM_yyyy()).
		validateMerchantname(storeOne).
		validateTransactionAmount(getTestData(10, "TransacationAmountPaid")).
		validateTranactionReference(getTestData(10, "TransactionId")).
		validateCouponCode(getTestData(10, "CouponCodeUsed")).
		validateDetails(getTestData(10, "TransactionDetails")).
		validateStaus(getTestData(10, "ResolvedStatus")).
		validateDateOfStatusUpdate(Utilities.today_dd_MMM_yyyy()).
		validateUserReply(getTestData(10, "UserReply")).
		validateCustomerServicesReply(getTestData(10, "AdminReply"));

	}
	
	@Test
	public void MissingCashbackWithTwoDifferentRetailersOnDifferentDate_TC004() {
		
		/*
		 * Step 1 : Join As a new User and make an exit click 
		 */
		
		//Get the First Store Name
		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("CB_Store_One");
		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
		
		//Get the Second Store Name
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Two("CB_Store_Two");
		String storeTwo = objAppiumVariables.getRequiredStoreName("str_Store_Two_Name");
		
		//Store one and Store 2 common redirection URL
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
		new HomePage(driver, testInfo).
		clickOnSearchIcon().
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
		 
		System.out.println("Exit click id for the First store : "+ exitClickID);
		
		/*
		 * Step 3 : Back date the Exit click for 5 days and Raise the ticket in the Missing cashback page
		 */
		
		objCashKaroUtility.backDateTheExitClickForGivenDate(exitClickID,5);
		
		CashKaroUtility cuForCKApp_1 =  new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = cuForCKApp_1.launchCashKaroApp(driver);
		
		new HomePage(driver,testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMissingCashback().
		clickOnAddTicketButonForNewUser().
		clickOnDateOfTransactionField_ExitClickOn5Days().
		selectDate(5).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(storeOne).
		clickOnTransactionDetails().
		enterTransactionId(getTestData(10, "TransactionId")).
		enterTransactionAmountPaid(getTestData(10, "TransacationAmountPaid")).
		enterCouponCodeUsed(getTestData(10, "CouponCodeUsed")).
		enterTransactionDetails(getTestData(10, "TransactionDetails")).
		clickOnSubmitButton().
		validateTheTicketRaisedStoreName(storeOne).
		validateTheTicketRaisedDate().
		validateTransactionAmountAtMissingCashback(getTestData(10, "TransacationAmountPaid")).
		valdiateTheRaisedTicketStatus(getTestData(10, "ReceivedStatus")).
		clickOnBackButton().
		
		/*
		 * Step 4 : Repeat the step 1,2 and 3 for the different store the validate the Multiple Missing Cashback ticket raised for the same user 
		 */
		
		/*
		 * Step 1 : Join As a new User and make an exit click for the second different store
		 */
		
		clickOnSearchIcon(). 
		enterTextIntoTheSearchBar(storeTwo).
		clickOnSearchedStoreImage().
		clickOnStoreMainCTAForSignedInUser().
		validateRetailerPage(retailerURL).
		clickOnIntermediateBackbutton();
		
		/*
		 * Step 2 Second time : Navigate to Admin and return the Exit click ID
		 */

		//Admin actions
		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu2.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);	
		adminFunctions = adminFunctions2;

		//Admin actions :
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
		exitClickID = adminFunctions.extractExitClickFromTable();
		 
		System.out.println("Exit click for second store is  : "+ exitClickID);
		
		/*
		 * Step 3 : Back date the Exit click for 5 days and Raise the ticket in the Missing cashback page
		 */
		
		objCashKaroUtility.backDateTheExitClickForGivenDate(exitClickID,4);
		
		CashKaroUtility cuForCKApp_2 =  new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = cuForCKApp_2.launchCashKaroApp(driver);
		
		new HomePage(driver,testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMissingCashback().
		clickOnAddticketButtonForExistingUser().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(storeTwo).
		clickOnTransactionDetails().
		enterTransactionId(getTestData(10, "TransactionId")).
		enterTransactionAmountPaid(getTestData(10, "TransacationAmountPaid")).
		enterCouponCodeUsed(getTestData(10, "CouponCodeUsed")).
		enterTransactionDetails(getTestData(10, "TransactionDetails")).
		clickOnSubmitButton().
		validateTheTicketRaisedStoreName(storeOne).
		validateTheTicketRaisedDate().
		validateTransactionAmountAtMissingCashback(getTestData(10, "TransacationAmountPaid")).
		valdiateTheRaisedTicketStatus(getTestData(10, "ReceivedStatus"));
		

	}

	@Test
	public void MissingCashbackWithMutipleTrasactionForSameRetailerOnSameDate_TC006() {

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
		 new HomePage(driver, testInfo).
		clickOnSearchIcon().
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
		//Launch chrome in web view
		driver = cu.launchChromeWebView(driver);
		
		AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);		

		//Admin actions :
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

		System.out.println("Exit click id for the First store : "+ exitClickID);

		/*
		 * Step 3 : Back date the Exit click for 5 days and Raise the ticket in the Missing cashback page
		 */

		objCashKaroUtility.backDateTheExitClickForGivenDate(exitClickID,4);

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
		validateTheTicketRaisedStoreName(storeOne).
		validateTheTicketRaisedDate().
		validateTransactionAmountAtMissingCashback(getTestData(10, "TransacationAmountPaid")).
		valdiateTheRaisedTicketStatus(getTestData(10, "ReceivedStatus")).
		clickOnBackButton().

		/*
		 * Step 4 : Repeat the step 1,2 and 3 for the different store the validate the Multiple Missing Cashback ticket raised for the same user 
		 */

		/*
		 * Step 1 : Join As a new User and make an exit click for the second different store
		 */

		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		clickOnStoreMainCTAForSignedInUser().
		validateRetailerPage(retailerURL).
		clickOnIntermediateBackbutton();

		/*
		 * Step 2 Second time : Navigate to Admin and return the Exit click ID
		 * 
		 */

		//Admin actions
		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu2.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);	
		adminFunctions = adminFunctions2;
		
		//Admin actions :
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
		exitClickID = adminFunctions.extractExitClickFromTable();

		System.out.println("Exit click for second store is  : "+ exitClickID);

		/*
		 * Step 3 : Back date the Exit click for 5 days and Raise the ticket in the Missing cashback page
		 * 
		 */

		objCashKaroUtility.backDateTheExitClickForGivenDate(exitClickID,4);

		CashKaroUtility cuForCKApp_2 =  new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = cuForCKApp_2.launchCashKaroApp(driver);

		new HomePage(driver,testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMissingCashback().
		clickOnAddticketButtonForExistingUser().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(storeOne).
		validateTransactionDetailsInfoblocks(2,4).
		clickOnTransactionDetails(1).
		enterTransactionId(getTestData(10, "TransactionId")).
		enterTransactionAmountPaid(getTestData(10, "TransacationAmountPaid")).
		enterCouponCodeUsed(getTestData(10, "CouponCodeUsed")).
		enterTransactionDetails(getTestData(10, "TransactionDetails")).
		clickOnSubmitButton().
		validateTheTicketRaisedStoreName(storeOne).
		validateTheTicketRaisedDate().
		validateTransactionAmountAtMissingCashback(getTestData(10, "TransacationAmountPaid")).
		valdiateTheRaisedTicketStatus(getTestData(10, "ReceivedStatus"));


	}
	
	@Test
	public void MissingCashbackTicketRaisingFromMyEarningsCashbackTab_TC007() {

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
		clickOnMyEarnings().
		clickRaiseTicketCashbackTab().
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
		clickOnSubmitButton_ForMyEarningsPage().
		clickBackButton().
		clickOnMissingCashback().
		validateTheTicketRaisedStoreName(storeOne).
		validateTheTicketRaisedDate().
		validateTransactionAmountAtMissingCashback(getTestData(10, "TransacationAmountPaid")).
		valdiateTheRaisedTicketStatus(getTestData(10, "ReceivedStatus"));

	}

	@Test
	public void MissingCashbackTicketRaisingFromMyEarningsRewardsTab_TC008() {

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
		new HomePage(driver, testInfo).
		clickOnSearchIcon().
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
		clickOnMyEarnings().
		clickRewardsTab().
		clickRaiseTicketRewardsTab().
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
		clickOnSubmitButton_ForMyEarningsPage().
		clickBackButton().
		clickOnMissingCashback().
		validateTheTicketRaisedStoreName(storeOne).
		validateTheTicketRaisedDate().
		validateTransactionAmountAtMissingCashback(getTestData(10, "TransacationAmountPaid")).
		valdiateTheRaisedTicketStatus(getTestData(10, "ReceivedStatus"));

	}

	@Test
	public void MissingCashbackTicketRaisingFromRecentClickScreen_And_Validating_AutoPopulatedDetails_TC009() {

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
		new HomePage(driver, testInfo).
		clickOnSearchIcon().
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
		clickOnRecentClickBottomIcon().
		clickOnRaiseTicketButton().
		enterTransactionId(getTestData(10, "TransactionId")).
		enterTransactionAmountPaid(getTestData(10, "TransacationAmountPaid")).
		enterCouponCodeUsed(getTestData(10, "CouponCodeUsed")).
		enterTransactionDetails(getTestData(10, "TransactionDetails")).
		clickOnSubmitButton_ForRecentClickPage();
		
		
	}
	
	@Test
	public void ValidateTransactionalDetailsAfterRaisingTheTicket_TC010() {
		
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
		validateTheTicketRaisedStoreName(storeOne).
		validateTheTicketRaisedDate().
		validateTransactionAmountAtMissingCashback(getTestData(10, "TransacationAmountPaid")).
		valdiateTheRaisedTicketStatus(getTestData(10, "ReceivedStatus")).clickOnViewLink(storeOne).
		enterUserReply(getTestData(10, "UserReply")).
		clickOnSubmitButton();
		
		//Admin actions
				CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
				driver = cu2.launchChromeWebView(driver);
			
				AdminMissingCashback adminMissingCashback = new AdminMissingCashback(driver, testInfo);
				AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);	
				adminFunctions = adminFunctions2;
				
				adminFunctions.naviagate_AdminPage();
				adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
				adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
				adminFunctions.clickSubmit();
				adminFunctions.clickOnHamburgerMenu();
				adminFunctions.clickOnInteractiveMainMenu();
				adminFunctions.clickOnsubMenuUserTickets();
				adminMissingCashback.selectSearchbyExitClickIDFromDropDown();
				adminMissingCashback.enerKeyWordatAdminMissingCashbackPage(exitClickID);
				adminMissingCashback.clickOnSearchButton();
				String ticketId = adminMissingCashback.fetchTicketID(exitClickID);
				adminMissingCashback.clickOnEditButton(exitClickID);
				adminMissingCashback.enterAdminReply(getTestData(10, "AdminReply"));
				adminMissingCashback.selectTicketStatus(getTestData(10, "ReceivedStatus"));
				adminMissingCashback.clickOnSubmitButton();
				isElementLocatedByIDPresent("adminMessageSuccess");

		
		
		
		CashKaroUtility cuForCKApp_2 = new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = cuForCKApp_2.launchCashKaroApp(driver);
		
		new HomePage(driver,testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMissingCashback().
		clickOnAddticketButtonForExistingUser().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		selectRetailerOption(storeOne).
		validateTransactionDetailsInfoblocks(1,4).
		validateStatusAndTicketIDInTransactionalDetails(ticketId, getTestData(10, "ReceivedStatus"));
		

	}
	
	//Missing Cashback Negative Validations :

	@Test
	public void MissingCashback_SelectDateOnwhichExitClickIsNotMade_TC011() {

		/*
		 * Step 1 : Join As a new User and make an exit click 
		 */

		//Get the First Store Name
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


		new HomePage(driver, testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMissingCashback().
		clickOnAddTicketButonForNewUser().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		validteTheErrorMessageForTheDateOnWhichExitClickIsNotMade();



	}

	@Test
	public void MissingCashback_RaiseTicket_NegativeTestCase_TC012() {

		/*
		 * Step 1 : Join As a new User and make an exit click 
		 */

		//Get the First Store Name
		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("N18_Store_One");
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
		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeOne).
		clickOnSearchedStoreImage().
		clickOnStoreGrabDealMainCTA_ForSignedUser().
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
		validateStaticTextForNewUser().
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
		clickOnSubmitButtonForFailure().
		valiadateN18Error(). //N18 Error validation
		clickBackButton_ForMissingCashbackPage().
		clickOnAddTicketButonForNewUser().
		clickOnDateOfTransactionField_ExitClickOn2Days().
		selectDate(2).
		clickOnMissingCashbackDatePopUpOKButton().
		validateLessThan72HourErrorMessage(). //less than 72 hour error validation
		clickBackButton_ForMissingCashbackPage().
		clickOnAddTicketButonForNewUser().
		clickOnSubmitButtonForFailure().
		validatePleaseSelectDateError(). // Please select date error
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnSubmitButtonForFailure().
		validatePleaseSelectRetailer(). //Please select Retailer error
		validatePleaseSelectTransactionDetails(). //Please selecte Tr.ID error
		validatePleaseEnterTransactionID(). //Please enter Tra.Id error
		validatePleaseEnterValidAmount(). //Please Enter Valid amount error
		validatePleaseEnterTransactionDetails().///Please enter Trans Details error
		clickOnTermsAndCondition();				//Finally Terms and condition valdiation


	}

	@Test
	public void missingCashbackAcceptMissingTicketValidation() {
		
		/*
		 * Step 1 : Join As a new User and make an exit click 
		 */
		
		//Get the First Store Name
		
		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);
		
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("RW_Store_One");
		String storeOne = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
		
		
		//Get the Second Store Name
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Two("RW_Store_Two");
		String storeTwo = objAppiumVariables.getRequiredStoreName("str_Store_Two_Name");
		
		//Store one and Store 2 common redirection URL
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
		new HomePage(driver, testInfo).
		clickOnSearchIcon().
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
		 
		System.out.println("Exit click id for the First store : "+ exitClickID);
		
		/*
		 * Step 3 : Back date the Exit click for 5 days and Raise the ticket in the Missing cashback page
		 */
		
		objCashKaroUtility.backDateTheExitClickForGivenDate(exitClickID,4);
		
		CashKaroUtility cuForCKApp_1 =  new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = cuForCKApp_1.launchCashKaroApp(driver);
		
		new HomePage(driver,testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeTwo).
		clickOnSearchedStoreImage().
		clickOnStoreMainCTAForSignedInUser().
		validateRetailerPage(retailerURL).
		clickOnIntermediateBackbutton();
		
		/*
		 * Step 2 Second time : Navigate to Admin and return the Exit click ID
		 * 
		 */
		
		//Admin actions
		CashKaroUtility cu2 = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = cu2.launchChromeWebView(driver);

		AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);	
		adminFunctions = adminFunctions2;

		//Admin actions :
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
		exitClickID = adminFunctions.extractExitClickFromTable();
		 
		System.out.println("Exit click for second store is  : "+ exitClickID);
		
		/*
		 * Step 3 : Back date the Exit click for 5 days and Raise the ticket in the Missing cashback page
		 * 
		 */
		
		objCashKaroUtility.backDateTheExitClickForGivenDate(exitClickID,4);
		
		CashKaroUtility cuForCKApp_2 =  new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = cuForCKApp_2.launchCashKaroApp(driver);
		
		new HomePage(driver,testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMissingCashback().
		clickOnAddTicketButonForNewUser().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		validateSizeOfMultipleRetailerOption_RewardsStore(2);
		
		CashKaroUtility objMcb = new CashKaroUtility(driver,testInfo,udid, port, automationName, deviceName,systemPort);
		//Uncheck the Missing Cashback Raising 
		objMcb.unCheckMissingCashbackFlag();
				
		CashKaroUtility cuForCKApp_3 =  new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = cuForCKApp_3.launchCashKaroApp();
		
		new HomePage(driver,testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMissingCashback().
		clickOnAddTicketButonForNewUser().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		validteTheErrorMessageForTheDateOnWhichExitClickIsNotMade();
		
		CashKaroUtility objMcb1 = new CashKaroUtility(driver,testInfo,udid, port, automationName, deviceName,systemPort);
		//Uncheck the Missing Cashback Raising 
		objMcb1.checkMissingCashbackFlag();
		
		CashKaroUtility cuForCKApp_4 =  new CashKaroUtility(udid, port, automationName, deviceName,systemPort);
		driver  = cuForCKApp_4.launchCashKaroApp();
		
		new HomePage(driver,testInfo).
		clickOnProfileIconForSignedUser().
		clickOnMissingCashback().
		clickOnAddTicketButonForNewUser().
		clickOnDateOfTransactionField().
		selectDate(4).
		clickOnMissingCashbackDatePopUpOKButton().
		clickOnPleaseSelectDropDown().
		validateSizeOfMultipleRetailerOption_RewardsStore(2);
	

	}


	@Test
	public void validateAbsenceOfRaiseTicketInRecentClickPageWhenStoreMissingTicketFlagisOFFOrStoreN18() {


		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver, testInfo);		

		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Three("CB_Store_Three");
		String cbStoreThree = objAppiumVariables.getRequiredStoreName("str_Store_Three_Name");
		String retailerURL = objAppiumVariables.getRequiredLinkUrl("str_Store_Three_linkURL");
		
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("N18_Store_One");
		String n18StoreOne = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
		String n18RetailerURL = objAppiumVariables.getRequiredLinkUrl("str_Store_One_linkURL");

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
		strPage.enterStoreNameUsingJS(cbStoreThree);
		strPage.clickSubmitButton();
		strPage.clickEditButton(1);
		strPage.enterOrderIdFormat(getTestData(10, "TransactionIdSampleFormat_1"));
		strPage.clickOnAcceptsMissingCashbackTicketFlagFromOnToOff();
		strPage.clickSubmitButton();


		CashKaroUtility cuForCKApp_1 =  new CashKaroUtility(driver,testInfo,udid, port, automationName, deviceName,systemPort);
		String email = cuForCKApp_1.generateEmail();
		String mobNum = cuForCKApp_1.generateMobileNumber();
		driver  = cuForCKApp_1.launchCashKaroApp(driver);

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
		new HomePage(driver, testInfo).
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(cbStoreThree).
		clickOnSearchedStoreImage().
		clickOnStoreMainCTAForSignedInUser().
		validateRetailerPage(retailerURL).
		clickOnIntermediateBackbutton().
		clickOnRecentClickBottomIcon().
		validateAbsenceOfRaiseTicketButton().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(n18StoreOne).
		clickOnSearchedStoreImage().
		clickOnStoreMainCTAForSignedInUser().
		validateRetailerPageDontHandlePopup(retailerURL).
		clickOnIntermediateBackbutton().
		clickOnRecentClickBottomIcon().
		validateAbsenceOfRaiseTicketButton();

	}
	
}
