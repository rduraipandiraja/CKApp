package com.ck.test;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.app.ck.base.AppDynamicVariables;
import com.app.ck.base.GetCodeSetup;
import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.CashKaroUtility;
import com.app.ck.utilities.Utilities;
import com.ck.pages.HomePage;
import com.ck.pages.IntermediateRetailerPage;
import com.ck.pages.JoinFreePage;
import com.ck.pages.MyEarningsPage;
import com.ck.pages.NotificationPage;
import com.ck.pages.OTPPage;
import com.ck.pages.PaymentOTPPage;
import com.ck.pages.ReferAndEarn;
import com.ck.pages.SeeVideoToEarnCashbackPage;
import com.ck.pages.SignInPage;
import com.ck.pages.StorePage;
import com.ck.pages.admin.AdminCashbackPage;
import com.ck.pages.admin.AdminCoreFunction;
import com.ck.pages.admin.AdminMissingCashback;
import com.ck.pages.admin.AdminPaymentSettingsPage;
import com.ck.pages.admin.AdminPendingCashoutsPage;
import com.ck.pages.admin.AdminReportsPage;
import com.ck.pages.admin.AdminStoresPage;
import com.ck.pages.admin.AdminUsersPage;
import com.ck.pages.admin.AdminVoucherPage;
import com.ck.pages.admin.EmailMasterBrowserPage;
import com.ck.pages.admin.ProductBrowserEditModePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DevArea  extends WrapperMethods {


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

	
	
}
