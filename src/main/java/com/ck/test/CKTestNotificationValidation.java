package com.ck.test;

import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.app.ck.base.GetCodeSetup;
import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;
import com.ck.pages.SeeVideoToEarnCashbackPage;
import com.ck.pages.HomePage;
import com.ck.pages.JoinFreePage;
import com.ck.pages.MyEarningsPage;
import com.ck.pages.NotificationPage;
import com.ck.pages.OTPPage;
import com.ck.pages.PaymentOTPPage;
import com.ck.pages.SignInPage;
import com.ck.pages.admin.AdminCashbackPage;
import com.ck.pages.admin.AdminCoreFunction;
import com.ck.pages.admin.AdminPendingCashoutsPage;
import com.ck.pages.admin.AdminReportsPage;
import com.ck.pages.admin.AdminUsersPage;
import com.ck.pages.admin.EmailMasterBrowserPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class CKTestNotificationValidation extends WrapperMethods {
	

	/**
	 * 
	=================================================================
					 Notification Document 
	-------------------------------------------------------------
	Read Me,Before You start automating with Notification
	----------------------------------------------------------
	Author : Mallikarjunaswamy B
	Date of Automation script development : 01_05_2019 to 7_05_2019
	Reviewed By : Jayakumar (Developer)
	----------------------------------------------------------------
	Points to follow strictly ,while automating the Notification :
	==================================================================

	1. While Automating notification , We usually login with the User "cktest@gmail.com"  and Password : "ppounds" ,
	   make sure this user is added into the Email Master.If you change any other user to login from the test data
	   We should add the user to the Email Master .Then only we start receive the notification
	   In order to add the User to the Email master , Kindly contact any developer.

	2. Notification Automation -  mostly 2 types one is With Template and other one is Without Template .
	   Without Template is not a problem for automation but with Template is a dependency  which means Templates are hard coded.
	   If you forget to create the Template as per the script written , the test case might get failed . I repeat again,that Template must be 
	   created as the script is being written.

   3. While doing Parallel execution , If you use the same user to login  then it will be Problem and it is not right to use same user login 
      in Parallel devices for the Notification testing. Kindly ensure that different user login in different devices for  Parallel execution.


    4. The challenging Part while automating Notification is that device specific issues - make sure you have right set of devices to run Notification automation
        and do check device stability for Notification automation before you start with Notification Automation.

	5. For Notification Redirection testing some hard coded store test data is created , Ensure that the required store Page is available.

	6. For Transactional Notification - We SignUp with Date stamp created user details running the Transactional notification in Parallel is not a Problem. Which in terms
	  You can execute the Transaction Notification in parallel.

	 *
	 */
	
	
	//Upadted date : 08_05_2019

	/*******************************************************************/
	/************************* Without Template  ********************/
	/*******************************************************************/

	//Positive flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon(String deviceName,String user) {

		try {
			//  TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new HomePage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new HomePage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithoutLargeIcon(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validateTheAbsenceoflargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithoutLargeIcon(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validateTheAbsenceoflargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithoutRedirectionURL(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new HomePage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithoutRedirectionURL(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new HomePage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithoutBigImage(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateTheAbsenceofBigImage().
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnNotificationTitle().
			validateAjioStoreRedirection();

		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithoutBigImage(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnNotificationTitle().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	//After logout Negative scenarios : Negative flow

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);

			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);

			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new HomePage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new SeeVideoToEarnCashbackPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithoutLargeIcon_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);

			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validateTheAbsenceoflargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithoutLargeIcon_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validateTheAbsenceoflargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();


		} catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithoutRedirectionURL_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new HomePage(driver, testInfo);

		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithoutRedirectionURL_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new HomePage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithoutBigImage_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateTheAbsenceofBigImage().
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnNotificationTitle().
			validateAjioStoreRedirection();

		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithoutBigImage_LogoutThenClickNotificaiton(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();


			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateTheAbsenceofBigImage().
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnNotificationTitle().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}


	/*******************************************************************/
	/************************* With Template ********************/
	/*******************************************************************/

	//Positive flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithTemplate(String deviceName,String user) {

		try {

			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new  MyEarningsPage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithTemplate(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new  MyEarningsPage(driver, testInfo);

		}catch (Exception e) {

			closeNotification();
			closeNotification();

			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	//Negative flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithTemplate_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon(String deviceName,String user) {

		try {

			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_WithoutRedirctURL_WithoutLargeIcon_WithoutBigImage();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL_WithoutBigImage_WithoutLargeIcon().
			validateBigText().
			clickOnNotificationTitle_WithoutRedirection_WithoutLargeIcon_WithoutBigImage();

			new HomePage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithTemplate_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_WithoutRedirctURL_WithoutLargeIcon_WithoutBigImage();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL_WithoutBigImage_WithoutLargeIcon().

			validateBigText().
			clickOnNotificationTitle_WithoutRedirection_WithoutLargeIcon_WithoutBigImage();

			new HomePage(driver, testInfo);

		}catch (Exception e) {

			closeNotification();
			closeNotification();

			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	//Negative flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithTemplate_WithoutRedirectionURL(String deviceName,String user) {

		try {

			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_WithoutRedirctURL();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new HomePage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithTemplate_WithoutRedirectionURL(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_WithoutRedirctURL();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new HomePage(driver, testInfo);

		}catch (Exception e) {

			closeNotification();
			closeNotification();

			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	//After logout Negative scenarios : Negative flow

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_LogoutThenClickNotificaiton_WithTemplate(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);

			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new SignInPage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_LogoutThenClickNotificaiton_WithTemplate(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);

			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new SignInPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon_LogoutThenClickNotificaiton_WithTemplate(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_WithoutRedirctURL_WithoutLargeIcon_WithoutBigImage();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL_WithoutBigImage_WithoutLargeIcon().

			validateBigText().
			clickOnNotificationTitle_WithoutRedirection_WithoutLargeIcon_WithoutBigImage();

			new HomePage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon_LogoutThenClickNotificaiton_WithTemplate(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_WithoutRedirctURL_WithoutLargeIcon_WithoutBigImage();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL_WithoutBigImage_WithoutLargeIcon().
			validateBigText().
			clickOnNotificationTitle_WithoutRedirection_WithoutLargeIcon_WithoutBigImage();

			new SeeVideoToEarnCashbackPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithoutRedirectionURL_LogoutThenClickNotificaiton_WithTemplate(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_WithoutRedirctURL();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new SeeVideoToEarnCashbackPage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithoutRedirectionURL_LogoutThenClickNotificaiton_WithTemplate(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_WithoutRedirctURL();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new SeeVideoToEarnCashbackPage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			closeNotification();

			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	/*******************************************************************/
	/************* Redirectional URL in different Pages **************/
	/*******************************************************************/

	@Parameters({"deviceName","user"})
	@Test
	public void clickingRedirectionURLFromHomePage(String deviceName,String user) {

		try {

			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_RedirectionalURLAtDifferentPage();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Now CashKaro app should be in the Home page 
			toggleApp(driver);
			clickCashKaroApp(driver);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_withDifferentRedirectionTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();

		}catch (Exception e) {

			closeNotification();
			closeNotification();

			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void clickingRedirectionURLFromMyEarningsPage(String deviceName,String user) {

		try {

			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_RedirectionalURLAtDifferentPage();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Now CashKaro app should be in the Home page 
			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_withDifferentRedirectionTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();


		}catch (Exception e) {

			closeNotification();
			closeNotification();

			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void clickingRedirectionURLFromSignedInprofilePage(String deviceName,String user) {

		try {

			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_RedirectionalURLAtDifferentPage();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Now CashKaro app should be in the Home page 
			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_withDifferentRedirectionTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();


		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void clickingRedirectionURLFromStorePage(String deviceName,String user) {

		try {

			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectHighPriorityWithNotificationTemplate_RedirectionalURLAtDifferentPage();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();


			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Now CashKaro app should be in the Home page 
			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser();

			//Open the Notification bar :
			openNotification();

			
					new NotificationPage(driver, testInfo).
					validateNotificationTitle_withDifferentRedirectionTitle().
					validateNotificationBody(deviceName).
					clickOnExpand(deviceName).
					validateBigImage(deviceName).
					validateBigText().
					validatelargeIcon(deviceName).
					clickOnBigImage().
					validateNotificationTestStorePage();

		}catch (Exception e) {

			closeNotification();
			closeNotification();

			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	/*******************************************************************/
	/************* Transactional Notification ********************/
	/*******************************************************************/

	@Test
	public void zzz_referralNotificationValidation(){

		try {

			reportStep("Referral Cashback - notification test started", "INFO");

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
			clickOnLogout().
			clickOnProfileIcon().
			clickOnSignInButton().
			enterUserName(emailAddress).
			enterPassword("ppounds").
			clickSignInButtonForSuccess().
			clickOnProfileIconForSignedUser();


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

			///Validate Confirm Notification here : 
			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp();
			closeCashKaroApp(deviceName);

			clickChromeApp();
			scrollFromDownToUpinApp();

			driver.context((String) contextNames.toArray()[1]);
			cashbackPage.clickSaveCashbackUsingAppium();
			driver.context("NATIVE_APP");

			openNotification();

			new NotificationPage(driver, testInfo).
			validateReferralConfirmedCashbackNotification().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateReferralConfirmed_BigText().
			clickOnReferralCashbackNotification();

			new MyEarningsPage(driver,testInfo);

			reportStep("Referral Cashback - notification test completed", "PASS");

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void pending_Cancel_Notification_AppBackGround(String deviceName,String user) {

		try {

			reportStep("TC004 is  started", "INFO");

			String cbStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
			String cbShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
			String cbLinkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

			String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
			String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();
			String currentMonthYear = Utilities.currentMonthYear().trim();

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

			CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cuOne.launchChromeWebView(driver);

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

			Set<String> contextNames = driver.getContextHandles();
			driver.context((String) contextNames.toArray()[1]);
			driver.context("NATIVE_APP");


			toggleApp();
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			cashbackPage.clickSaveCashbackUsingAppium();
			driver.context("NATIVE_APP");

			openNotification();

			new NotificationPage(driver, testInfo).
			validatePendingNotification().
			validatePendingNotification_BigText().
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validatelargeIcon(deviceName).
			clickPendingNotification();
			new MyEarningsPage(driver, testInfo);


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
			cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Cancelled"));
			cashbackPage.setFailDate();

			contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp();
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();
			scrollFromDownToUpinApp();

			driver.context((String) contextNames.toArray()[1]);
			cashbackPage.clickSaveCashbackUsingAppium();
			driver.context("NATIVE_APP");

			openNotification();

			new NotificationPage(driver, testInfo).
			validateCancelledNotification().
			clickOnExpand(deviceName).
			validateCancelledNotification_BigText().
			validateBigImage(deviceName).
			validatelargeIcon(deviceName).
			clickCancelledNotificaiton();

			new MyEarningsPage(driver, testInfo);

		}catch (Exception e) {

			closeNotification();
			closeNotification();

			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");
		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void confirmed_PaidNotification_AppBackGround(String deviceName,String user) {

		try {

			reportStep("TC002 is  started", "INFO");

			String cbStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
			String cbShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
			String cbLinkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

			String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
			String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();
			String currentMonthYear = Utilities.currentMonthYear().trim();

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

			CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cuOne.launchChromeWebView(driver);

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


			//Change the Status from Pending to Confirm :
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

			//Validate Confirm Notification here : 
			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp();
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();
			scrollFromDownToUpinApp();

			driver.context((String) contextNames.toArray()[1]);
			cashbackPage.clickSaveCashbackUsingAppium();
			driver.context("NATIVE_APP");

			openNotification();

			new NotificationPage(driver, testInfo).
			validateConfirmedNotification().
			clickOnExpand(deviceName).
			validateConfirmedNotification_BigText().
			validateBigImage(deviceName).
			validatelargeIcon(deviceName).
			clickOnConfirmedCashbackNotification();

			PaymentOTPPage objPaymentOTP =
					new MyEarningsPage(driver,testInfo).
					clickOnRequestCashbackPaymentButton().
					clickOnPaymentMethodDropDown().
					selectBankPaymentNEFTFromDropDown().
					enterBankNEFTPaymentDetails().
					clickOnGETPAID();

			otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);

			try {
				objPaymentOTP.
				enterOTP(otpValue).
				clickOnVerifyOTPAndValidateThePaymentSuccessMessage();
			}catch (Exception e) {

				System.out.println("Handled the exception - start the cashout process");
				e.printStackTrace();
			}

			/********************************************************************************/
			/************************************** CASHOUT**********************************/
			/********************************************************************************/

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);

			AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
			adminFunctions = adminFunctions2;
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
			PendingCashoutsPage.
			validateSuccessMessage();

			//Validate Confirm Notification here : 
			contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			openNotification();

			new NotificationPage(driver, testInfo).
			validatePaidNotification().
			validatePaidNotification_BigText().
			clickOnPaidNotification();

			new MyEarningsPage(driver,testInfo);

		}catch (Exception e) {

			closeNotification();
			closeNotification();

			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void pending_Cancel_Notification_AppClosed(String deviceName,String user) {

		try {

			reportStep("TC004 is  started", "INFO");

			String cbStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
			String cbShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
			String cbLinkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

			String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
			String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();
			String currentMonthYear = Utilities.currentMonthYear().trim();

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

			CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cuOne.launchChromeWebView(driver);

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

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");


			toggleApp();
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeCashKaroApp(deviceName);

			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			cashbackPage.clickSaveCashbackUsingAppium();
			driver.context("NATIVE_APP");

			openNotification();

			new NotificationPage(driver, testInfo).
			validatePendingNotification().
			validatePendingNotification_BigText().
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validatelargeIcon(deviceName).
			clickPendingNotification();

			new MyEarningsPage(driver, testInfo);


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
			cashbackPage.selectCashbackStatusFromDropDown(getTestData(11, "Cancelled"));
			cashbackPage.setFailDate();

			contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");


			toggleApp();
			clickCashKaroApp(driver);
			new MyEarningsPage(driver, testInfo);
			toggleApp();
			closeCashKaroApp(deviceName);

			clickChromeApp();
			scrollFromDownToUpinApp();

			driver.context((String) contextNames.toArray()[1]);
			cashbackPage.clickSaveCashbackUsingAppium();
			driver.context("NATIVE_APP");

			openNotification();

			new NotificationPage(driver, testInfo).
			validateCancelledNotification().
			clickOnExpand(deviceName).
			validateCancelledNotification_BigText().
			validateBigImage(deviceName).
			validatelargeIcon(deviceName).
			clickCancelledNotificaiton();

			new MyEarningsPage(driver, testInfo);

		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");
		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void confirmed_PaidNotification_AppClosed(String deviceName,String user) {

		try {

			reportStep("TC002 is  started", "INFO");

			String cbStoreName = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_Name");
			String cbShortDescription = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_App_Short_Description");
			String cbLinkURL = CashKaroUtility.extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore("CB_Store_One", 0, "CB_Store_One_linkURL");

			String currentDate = Utilities.today_dd_MMM_yyyy_ISTZone().trim();
			String nintyDaysAhead = Utilities.generic_AheadDate_dd_MMM_yyyy(90).trim();
			String currentMonthYear = Utilities.currentMonthYear().trim();

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

			CashKaroUtility cuOne = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cuOne.launchChromeWebView(driver);

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


			//Change the Status from Pending to Confirm :
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

			//Validate Confirm Notification here : 
			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp();
			closeCashKaroApp(deviceName);

			clickChromeApp();
			scrollFromDownToUpinApp();

			driver.context((String) contextNames.toArray()[1]);
			cashbackPage.clickSaveCashbackUsingAppium();
			driver.context("NATIVE_APP");

			openNotification();

			new NotificationPage(driver, testInfo).
			validateConfirmedNotification().
			validateConfirmedNotification_BigText().
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validatelargeIcon(deviceName).
			clickOnConfirmedCashbackNotification();

			PaymentOTPPage objPaymentOTP =
					new MyEarningsPage(driver,testInfo).
					clickOnRequestCashbackPaymentButton().
					clickOnPaymentMethodDropDown().
					selectBankPaymentNEFTFromDropDown().
					enterBankNEFTPaymentDetails().
					clickOnGETPAID();

			otpValue = objCashKaroUtility.generatePaymentOTP(mobileNumber);

			try {
				objPaymentOTP.
				enterOTP(otpValue).
				clickOnVerifyOTPAndValidateThePaymentSuccessMessage();
			}catch (Exception e) {

				System.out.println("Handled the exception - start the cashout process");
				e.printStackTrace();
			}

			toggleApp();
			closeCashKaroApp(deviceName);
			toggleApp();

			/********************************************************************************/
			/************************************** CASHOUT**********************************/
			/********************************************************************************/

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);

			AdminCoreFunction adminFunctions2 = new AdminCoreFunction(driver, testInfo);
			adminFunctions = adminFunctions2;
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
			PendingCashoutsPage.
			validateSuccessMessage();

			//Validate Confirm Notification here : 
			contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			openNotification();

			new NotificationPage(driver, testInfo).
			validatePaidNotification().
			validatePaidNotification_BigText().
			clickOnPaidNotification();

			new MyEarningsPage(driver,testInfo);

		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}


	/*******************************************************************/
	/************* Normal Priority Test Cases Without Template *********/
	/*******************************************************************/

	//Positive flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithNormalPriority(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.clickOnNormalPriority();
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithNormalPriority(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.clickOnNormalPriority();
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon_WithNormalPriority(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.clickOnNormalPriority();
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new HomePage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithOutImageURL_WithoutRedirectionURL_WithoutLargeIcon_WithNormalPriority(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.clickOnNormalPriority();
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new HomePage(driver, testInfo);

		} catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_LogoutThenClickNotificaiton_WithNormalPriority(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.clickOnNormalPriority();
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);

			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();


		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_LogoutThenClickNotificaiton_WithNormalPriority(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.clickOnNormalPriority();
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);

			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();


		} catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	/*******************************************************************/
	/************************* Normal Priority With Template  ********************/
	/*******************************************************************/

	//Positive flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_WithTemplate_NormalPriority(String deviceName,String user) {

		try {

			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectNormalPriorityWithNotificationTemplate();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateNormalPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();

		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_WithTemplate_NormalPriority(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectNormalPriorityWithNotificationTemplate();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateNormalPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();

		}catch (Exception e) {

			closeNotification();
			closeNotification();

			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	//After logout Negative scenarios : Negative flow

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppBackGround_LogoutThenClickNotificaiton_WithTemplate_NormalPriority(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectNormalPriorityWithNotificationTemplate();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);

			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateNormalPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();



		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_AppClosed_LogoutThenClickNotificaiton_WithTemplate_NormalPriority(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);
			closeNotification();


			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithNotificationTemplate();
			emailMasterBrowserpage.selectNormalPriorityWithNotificationTemplate();
			emailMasterBrowserpage.selectSiteTitleAsUserName();
			emailMasterBrowserpage.clickOnSendNotificatonButtonForWithTemplate();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);

			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateNormalPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();

		} catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	/*******************************************************************/
	/************************* Exceptional Scenarios********************/
	/*******************************************************************/

	//Positive flow :
	@Parameters({"deviceName","user"})
	@Test
	public void pushNotificationOnceAfterLogout_AppBackGround(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();
			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationIsnotAppearedForUnsignedUser();

		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void pushNotificationOnceAfterLogout_AppClosed(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationIsnotAppearedForUnsignedUser();

		} catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void clickOnOtherUserNotificaton_AppBackGround(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);

			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout().
			clickOnProfileIcon().
			clickOnSignInButton().
			enterUserName(testdata.get(0).get("TC001_ValidLoginEmail")).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

			//			new StoreDetailPage(driver, testInfo).
			//			clickOnProfileIconForSignedUser().
			//			clickOnLogout();


		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void clickOnOtherUserNotificaton_AppClosed(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);

			toggleApp();
			clickChromeApp();

			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);

			new HomePage(driver, testInfo).
			clickOnProfileIconForSignedUser().
			clickOnLogout().
			clickOnProfileIcon().
			clickOnSignInButton().
			enterUserName(testdata.get(0).get("TC001_ValidLoginEmail")).
			enterPassword().
			clickSignInButtonForSuccess();

			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

			//			new StoreDetailPage(driver, testInfo).
			//			clickOnProfileIconForSignedUser().
			//			clickOnLogout();

		} catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void multipleNotificationValidation(String deviceName,String user) {

		try {
			// TEST STEPS :
			new SeeVideoToEarnCashbackPage(driver, testInfo).
			clickOnLoginJoinButton().
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			CashKaroUtility cu = new CashKaroUtility(udid, port, automationName, deviceName);
			driver = cu.launchChromeWebView(driver);
			AdminCoreFunction adminFunctions = new AdminCoreFunction(driver, testInfo);
			adminFunctions.naviagate_AdminPage();
			adminFunctions.enterUsername(getTestData(8, "AdminUserName"));
			adminFunctions.enterPassword(getTestData(8, "AdminPassword"));
			adminFunctions.clickSubmit();

			adminFunctions.clickOnHamburgerMenu();
			adminFunctions.clickOnEmailMaster();
			adminFunctions.clickOnEmailMasterBrowser();
			adminFunctions.selectEmailFromSearchByDropDown_EmailMaster();
			adminFunctions.enterUserEmailAsKeyForSearchEmailMaster(user);
			adminFunctions.clickOnSearchButtonEmailMaster();

			EmailMasterBrowserPage emailMasterBrowserpage = new EmailMasterBrowserPage(driver, testInfo);

			emailMasterBrowserpage.clickOnSendNotification();
			emailMasterBrowserpage.clickOnWithoutNotificationTemplate();
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();

			Set<String> contextNames = driver.getContextHandles();
			driver.context("NATIVE_APP");

			toggleApp(driver);
			clickCashKaroApp(driver);
			new HomePage(driver, testInfo);
			toggleApp();
			closeCashKaroApp(deviceName);
			clickChromeApp();

			//1st time push notification :
			driver.context((String) contextNames.toArray()[1]);
			emailMasterBrowserpage.clickOnConfirmButton();


			//2nd time push notification :
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();
			emailMasterBrowserpage.clickOnConfirmButton();

			//3rd time push notification :
			emailMasterBrowserpage.enterAppNotificationTitle(testdata.get(13).get("NotificationTitle"));
			emailMasterBrowserpage.enterImageURL(testdata.get(13).get("BigImage"));
			emailMasterBrowserpage.enterAppNotificationBody(testdata.get(13).get("NotificationBody"));
			emailMasterBrowserpage.enterBigText(testdata.get(13).get("BigText"));
			emailMasterBrowserpage.enterRedirectionalURL(testdata.get(13).get("RedirectionalURL"));
			emailMasterBrowserpage.enterLargeIcon(GetCodeSetup.NOTIFICATIONLARGEICON);
			emailMasterBrowserpage.enterSubText(testdata.get(13).get("SubText"));
			emailMasterBrowserpage.clickOnSubmit();
			emailMasterBrowserpage.clickOnConfirmButton();

			//Chnage to Native App
			driver.context("NATIVE_APP");

			//Open the Notification bar :
			openNotification();


			NotificationPage objNotification = new NotificationPage(driver, testInfo).
					validateTheMultipleNotificationOnNotificationBar(3).
					validateNotificationBody(deviceName).
					clickOnExpand(deviceName).
					validateBigImage(deviceName).
					validateBigText().
					validatelargeIcon(deviceName).
					clickOnBigImage().
					validateAjioStoreRedirection();

			//Open the Notification bar :
			openNotification();

			objNotification.
			validateTheMultipleNotificationOnNotificationBar(2).
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

			//Open the Notification bar :
			openNotification();

			objNotification.
			validateTheMultipleNotificationOnNotificationBar(1).
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

			//Open the Notification bar :
			openNotification();

			objNotification.
			validateNotificationIsnotAppearedForUnsignedUser();

			closeNotification();


		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}


	/*******************************************************************/
	/*************** Fore Ground Notification - High priority  ********************/
	/*******************************************************************/

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromHomePage(String deviceName,String user) throws InterruptedException {

		try {

			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificationAPICall(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromMyEarningsPage(String deviceName,String user) throws InterruptedException {

		try {

			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificationAPICall(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	//Without Redirection Page ,Without Big Image , Without Large ICon
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromHomePage_WithoutRedirectionURL_WithoutLargeIcon_WithoutBigImage(String deviceName,String user) throws InterruptedException {

		try {

			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificataionAPICall_WithoutRedirectionURL_WithoutBigImage_WithoutLargeIcon(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new HomePage(driver, testInfo);

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromMyEarningsPage_WithoutRedirectionURL_WithoutLargeIcon_WithoutBigImage(String deviceName,String user) throws InterruptedException {


		try {
			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificataionAPICall_WithoutRedirectionURL_WithoutBigImage_WithoutLargeIcon(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new MyEarningsPage(driver, testInfo);
			
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

	//Without Big_Image :
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromHomePage_WithoutBigImage(String deviceName,String user) throws InterruptedException {

		try {
			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificataionAPICall_WithoutBigImage(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateTheAbsenceofBigImage().
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnNotificationTitle().
			validateAjioStoreRedirection();

			new HomePage(driver, testInfo);

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromMyEarningsPage_WithoutBigImage(String deviceName,String user) throws InterruptedException {

		try {
			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificataionAPICall_WithoutBigImage(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateTheAbsenceofBigImage().
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnNotificationTitle().
			validateAjioStoreRedirection();

			new HomePage(driver, testInfo);
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}


	//Exceptional Scenarios :

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromHomePage_LogoutThenClickNotificaiton(String deviceName,String user) throws InterruptedException {

		try {
			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			HomePage objHomePage = signInPage.
					enterUserName(user).
					enterPassword().
					clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificationAPICall(fcmID);

			objHomePage.
			clickOnProfileIconForSignedUser().
			clickOnLogout();

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromMyEarningsPage_LogoutThenClickNotificaiton(String deviceName,String user) throws InterruptedException {

		try {
			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			MyEarningsPage objMyEarningsPage = 
					signInPage.
					enterUserName(user).
					enterPassword().
					clickSignInButtonForSuccess().
					clickOnProfileIconForSignedUser().
					clickOnMyEarnings();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificationAPICall(fcmID);

			objMyEarningsPage.
			clickBackButton().
			clickOnLogout();


			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();


		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_MultipleNotificationRedirectionalValidation(String deviceName,String user) {

		try {
			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);
			closeNotification();
			ckUtility.notificationAPICall(fcmID);
			ckUtility.notifationAPICall_WithRedirectionalURLMyEarningsPage(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new MyEarningsPage(driver, testInfo);

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

			ckUtility.notifationAPICall_WithRedirectionalURLMyEarningsPage(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new MyEarningsPage(driver, testInfo);

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromHomePage_AppClosed(String deviceName,String user) throws InterruptedException {

		try {
			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			//Close the CashKaro App :
			toggleApp();
			closeCashKaroApp(deviceName);

			ckUtility.notificationAPICall(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromMyEarningsPage_AppClosed(String deviceName,String user) throws InterruptedException {

		try  {
			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			//Close the CashKaro App :
			toggleApp();
			closeCashKaroApp(deviceName);

			ckUtility.notifationAPICall_WithRedirectionalURLMyEarningsPage(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new MyEarningsPage(driver, testInfo);
		}catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	/*******************************************************************/
	/*********** Fore Ground Notification  - Normal priority**********/
	/*******************************************************************/

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromHomePage_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {
			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notifationAPICall_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromMyEarningsPage_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {
			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notifationAPICall_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}
	}

	//Without Redirection Page ,Without Big Image , Without Large ICon
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromHomePage_WithoutRedirectionURL_WithoutLargeIcon_WithoutBigImage_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {

			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificataionAPICall_WithoutRedirectionURL_WithoutBigImage_WithoutLargeIcon_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new HomePage(driver, testInfo);

		}

		catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromMyEarningsPage_WithoutRedirectionURL_WithoutLargeIcon_WithoutBigImage_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {
			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificataionAPICall_WithoutRedirectionURL_WithoutBigImage_WithoutLargeIcon_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateBigText().
			clickOnNotificationTitle();

			new MyEarningsPage(driver, testInfo);

		} 
		catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	//Without Big_Image :
	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromHomePage_WithoutBigImage_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {

			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificataionAPICall_WithoutBigImage_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateTheAbsenceofBigImage().
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnNotificationTitle().
			validateAjioStoreRedirection();

			new HomePage(driver, testInfo);

		}  catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromMyEarningsPage_WithoutBigImage_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {
			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			ckUtility.notificataionAPICall_WithoutBigImage_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateTheAbsenceofBigImage().
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnNotificationTitle().
			validateAjioStoreRedirection();

			new HomePage(driver, testInfo);
		}

		catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	//Exceptional Scenarios :

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromHomePage_LogoutThenClickNotificaiton_NormalPriority(String deviceName,String user) throws InterruptedException {

		try { 
		SignInPage signInPage = 
				new SeeVideoToEarnCashbackPage(driver, testInfo).
				clickOnLoginJoinButton();

		CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
		String fcmID =  ckUtility.getFCMID();

		HomePage objHomePage = signInPage.
				enterUserName(user).
				enterPassword().
				clickSignInButtonForSuccess();

		//Open the Notification bar :
		openNotification();

		//Clear the Notification
		clearNotificationFromNotificationBar(deviceName);

		closeNotification();

		ckUtility.notifationAPICall_NormalPriority(fcmID);

		objHomePage.
		clickOnProfileIconForSignedUser().
		clickOnLogout();

		//Open the Notification bar :
		openNotification();

		new NotificationPage(driver, testInfo).
		validateNotificationTitle().
		validateNotificationBody(deviceName).
		clickOnExpand(deviceName).
		validateBigImage(deviceName).
		validateBigText().
		validatelargeIcon(deviceName).
		clickOnBigImage().
		validateNotificationTestStorePage();
		
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");
			
		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromMyEarningsPage_LogoutThenClickNotificaiton_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {
		SignInPage signInPage = 
				new SeeVideoToEarnCashbackPage(driver, testInfo).
				clickOnLoginJoinButton();

		CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
		String fcmID =  ckUtility.getFCMID();

		MyEarningsPage objMyEarningsPage = 
				signInPage.
				enterUserName(user).
				enterPassword().
				clickSignInButtonForSuccess().
				clickOnProfileIconForSignedUser().
				clickOnMyEarnings();

		//Open the Notification bar :
		openNotification();

		//Clear the Notification
		clearNotificationFromNotificationBar(deviceName);

		closeNotification();

		ckUtility.notifationAPICall_NormalPriority(fcmID);

		objMyEarningsPage.
		clickBackButton().
		clickOnLogout();


		//Open the Notification bar :
		openNotification();

		new NotificationPage(driver, testInfo).
		validateNotificationTitle().
		validateNotificationBody(deviceName).
		clickOnExpand(deviceName).
		validateBigImage(deviceName).
		validateBigText().
		validatelargeIcon(deviceName).
		clickOnBigImage().
		validateNotificationTestStorePage();
		
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void multipleNotificationRedirectionalValidation_NormalPriority(String deviceName,String user) {

		try {
		SignInPage signInPage = 
				new SeeVideoToEarnCashbackPage(driver, testInfo).
				clickOnLoginJoinButton();

		CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
		String fcmID =  ckUtility.getFCMID();

		signInPage.
		enterUserName(user).
		enterPassword().
		clickSignInButtonForSuccess();

		//Open the Notification bar :
		openNotification();

		//Clear the Notification
		clearNotificationFromNotificationBar(deviceName);
		closeNotification();
		ckUtility.notifationAPICall_NormalPriority(fcmID);
		ckUtility.notifationAPICall_WithRedirectionalURLMyEarningsPage_NormalPriority(fcmID);

		//Open the Notification bar :
		openNotification();

		new NotificationPage(driver, testInfo).
		validateNotificationTitle().
		validateNotificationBody(deviceName).
		clickOnExpand(deviceName).
		validateBigImage(deviceName).
		validateBigText().
		validatelargeIcon(deviceName).
		clickOnBigImage();

		new MyEarningsPage(driver, testInfo);

		new NotificationPage(driver, testInfo).
		validateNotificationTitle().
		validateNotificationBody(deviceName).
		clickOnExpand(deviceName).
		validateBigImage(deviceName).
		validateBigText().
		validatelargeIcon(deviceName).
		clickOnBigImage().
		validateAjioStoreRedirection();

		ckUtility.notifationAPICall_WithRedirectionalURLMyEarningsPage(fcmID);

		//Open the Notification bar :
		openNotification();

		new NotificationPage(driver, testInfo).
		validateNotificationTitle().
		validateNotificationBody(deviceName).
		clickOnExpand(deviceName).
		validateBigImage(deviceName).
		validateBigText().
		validatelargeIcon(deviceName).
		clickOnBigImage();

		new MyEarningsPage(driver, testInfo);
		
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

			
		}


	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromHomePage_AppClosed_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {
			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			//Close the CashKaro App :
			toggleApp();
			closeCashKaroApp(deviceName);

			ckUtility.notifationAPICall_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateAjioStoreRedirection();

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}



	}

	@Parameters({"deviceName","user"})
	@Test
	public void notificationTest_ForeGround_FromMyEarningsPage_AppClosed_NormalPriority(String deviceName,String user) throws InterruptedException {

		try {
			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.
			enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess().
			clickOnProfileIconForSignedUser().
			clickOnMyEarnings();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);

			closeNotification();

			//Close the CashKaro App :
			toggleApp();
			closeCashKaroApp(deviceName);

			ckUtility.notifationAPICall_WithRedirectionalURLMyEarningsPage_NormalPriority(fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new MyEarningsPage(driver, testInfo);
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}



	}

	/*******************************************************************/
	/*********** WithTemplate  - Fore Ground **********/
	/*******************************************************************/
	//Positive flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificaitonForeGround_WithTemplate_HighPriority_TemplateId257(String deviceName,String user) {

		try {

			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);
			closeNotification();

			ckUtility.notifationAPICall_WithTemplate(getTestData(13, "HighPriorityNotificaitonTemplateID"),fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new  MyEarningsPage(driver, testInfo);


		}catch (Exception e) {

			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}
	//Positive flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificaitonForeGround_WithTemplate_HighPriority_TemplateId257_MutipleNotification(String deviceName,String user) {

		try {

			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);
			closeNotification();

			ckUtility.notifationAPICall_WithTemplate(getTestData(13, "HighPriorityNotificaitonTemplateID"),fcmID);
			ckUtility.notifationAPICall_WithTemplate(getTestData(13, "HighPriorityNotificaitonTemplateID"),fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new  MyEarningsPage(driver, testInfo);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new  MyEarningsPage(driver, testInfo);



		}catch (Exception e) {

			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}
	//Positive flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificaitonForeGround_WithTemplate_HighPriority_TemplateId257_WithoutRedirectURL(String deviceName,String user) {

		try {

			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);
			closeNotification();

			ckUtility.notifationAPICall_WithTemplate(getTestData(13, "HighPriorityWithoutRedirectURLTemplateID"),fcmID);

			//Open the Notification bar :
			openNotification();


			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateHighPriority_WithoutRedirectURL().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage();

			new HomePage(driver, testInfo);

		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}
	//Positive flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificaitonForeGround_WithTemplate_NormalPriority_TemplateId258(String deviceName,String user) {

		try {

			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);
			closeNotification();

			ckUtility.notifationAPICall_WithTemplate(getTestData(13, "NormalPriorityNotificationTemplateID"),fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateNormalPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();


		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}
	//Positive flow :
	@Parameters({"deviceName","user"})
	@Test
	public void notificaitonForeGround_WithTemplate_NormalPriority_TemplateId258_MultipleNotificaition(String deviceName,String user) {

		try {

			SignInPage signInPage = 
					new SeeVideoToEarnCashbackPage(driver, testInfo).
					clickOnLoginJoinButton();

			CashKaroUtility ckUtility = new CashKaroUtility(driver, testInfo);
			String fcmID =  ckUtility.getFCMID();

			signInPage.enterUserName(user).
			enterPassword().
			clickSignInButtonForSuccess();

			//Open the Notification bar :
			openNotification();

			//Clear the Notification
			clearNotificationFromNotificationBar(deviceName);
			closeNotification();

			ckUtility.notifationAPICall_WithTemplate(getTestData(13, "NormalPriorityNotificationTemplateID"),fcmID);
			ckUtility.notifationAPICall_WithTemplate(getTestData(13, "NormalPriorityNotificationTemplateID"),fcmID);

			//Open the Notification bar :
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateNormalPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();

			//Open the Notification bar : 
			openNotification();

			new NotificationPage(driver, testInfo).
			validateNotificationTitle_WithTemplateNormalPriority().
			validateNotificationBody(deviceName).
			clickOnExpand(deviceName).
			validateBigImage(deviceName).
			validateBigText().
			validatelargeIcon(deviceName).
			clickOnBigImage().
			validateNotificationTestStorePage();

		}catch (Exception e) {

			closeNotification();
			closeNotification();
			reportStep("Test failed , unexpectedly - This is not the actual Error Please scroll up one step and then  see the actual Error in the execution :(" + e.getMessage(), "FAIL");

		}

	}

}	