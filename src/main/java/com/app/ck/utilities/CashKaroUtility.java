package com.app.ck.utilities;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.AppDynamicVariables;
import com.app.ck.base.Base;
import com.app.ck.base.GetCodeSetup;
import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;
import com.ck.pages.admin.AdminCoreFunction;
import com.ck.pages.admin.AdminStoresPage;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;

public class CashKaroUtility extends WrapperMethods {

	@FindBy(how = How.XPATH, using = "//android.view.View[@text='JOIN FREE']")
	MobileElement joinFreeLinkXpath ;

	String joinFreeLink 	= "//android.view.View[@text='JOIN FREE']";
	String oneLink 			= "//android.view.View[@text='AppsFlyer OneLink']";
	String tabSwitcherIcon 	= "com.android.chrome:id/tab_switcher_button";
	String menuButton 		= "com.android.chrome:id/menu_button"; 
	String closeAlltabsLink = "Close all tabs";
	String newTabIcon 		= "com.android.chrome:id/new_tab_button";
	String searchBox 		= "com.android.chrome:id/search_box_text";
	String urlBar 			= "com.android.chrome:id/url_bar";
	String buttonInstall	= "//android.widget.Button[contains(@text ,'INSTALL')]";

	//	@FindBy(how = How.ID, using = "com.android.packageinstaller:id/permission_allow_button")
	//	MobileElement allowLinkInPermissionPopup;
	String allowLinkInPermissionPopup = "com.android.packageinstaller:id/permission_allow_button";

	public static final JSONParser objJparser = new JSONParser();

	public CashKaroUtility(String udid,String port,String automationName,String deviceName){

		this.udid = udid;
		this.port = port;
		this.automationName = automationName;
		this.deviceName = deviceName;

	}

	public CashKaroUtility(String udid,String port,String automationName,String deviceName,Integer systemPort){

		this.udid = udid;
		this.port = port;
		this.automationName = automationName;
		this.deviceName = deviceName;
		this.systemPort = systemPort;

	}

	public CashKaroUtility(AppiumDriver<MobileElement> driver, ExtentTest testInfo){
		this.driver = driver;
		this.testInfo = testInfo;
	}

	public CashKaroUtility(AppiumDriver<MobileElement> driver, ExtentTest testInfo,String udid,String port,String automationName,String deviceName,Integer systemPort){

		this.driver = driver;
		this.testInfo = testInfo;
		this.udid = udid;
		this.port = port;
		this.automationName = automationName;
		this.deviceName = deviceName;
		this.systemPort = systemPort;

	}

	public AppiumDriver<MobileElement> launchChrome() {

		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid );

		//Set the Desired Capabilities for chrome browser
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("platformName", "Android");
		caps.setCapability("automationName", this.automationName);
		caps.setCapability("appPackage", "com.android.chrome");
		caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
		caps.setCapability("deviceName", this.deviceName);
		caps.setCapability("newCommandTimeout", 30000);
		caps.setCapability("udid", this.udid);
		caps.setCapability("noReset", true);
		caps.setCapability("systemPort", this.systemPort);
		caps.setCapability("ignoreUnimportantViews", true);
		caps.setCapability("skipServerInstallation", true);

		AndroidDriver<MobileElement> driver1 = null;

		try {

			driver1 = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:"+this.port+"/wd/hub"), caps);

			System.out.println("Session has been started in the port : http://0.0.0.0:"+this.port+"/wd/hub");

		} catch (MalformedURLException e) {

			System.out.println(e.getMessage());
		}


		System.out.println("End - Base port is "+ this.port + " & Base udid is " + this.udid );

		return driver1;
	}

	public AppiumDriver<MobileElement> launchChromeWebView(AppiumDriver<MobileElement> driver)  {

		this.driver = driver;
		driver.closeApp();

		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid );

		//Set the Desired Capabilities for chrome browser
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("browserName", "chrome");
		caps.setCapability("deviceName", this.deviceName);
		caps.setCapability("udid", this.udid); 
		caps.setCapability("platformName", "Android");
		caps.setCapability("automationName", this.automationName);
		caps.setCapability("newCommandTimeout", 20000);
		caps.setCapability("noReset", true);
		caps.setCapability("ignoreUnimportantViews", true);
		caps.setCapability("skipServerInstallation", true);
		caps.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));

		AppiumDriver<MobileElement> driver1 = null;

		try {

			driver1 = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:"+this.port+"/wd/hub"), caps);

			System.out.println("Session has been started in the port : http://0.0.0.0:"+this.port+"/wd/hub");

		} catch (MalformedURLException e) {

			System.out.println(e.getMessage());
		}

		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid );

		return driver1;
	}

	public AppiumDriver<MobileElement> launchCashKaroApp(AppiumDriver<MobileElement> driver) {

		this.driver = driver;
		driver.closeApp();

		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid  + " & System port is : "+ this.systemPort + "& Device Name is : "+ this.deviceName );


		//Set the Desired Capabilities for chrome browser
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("platformName", "Android");
		caps.setCapability("automationName", this.automationName);
		caps.setCapability("appPackage", "com.cashkaro");
		caps.setCapability("appActivity", "com.cashkaro.MainActivity");
		caps.setCapability("deviceName", this.deviceName);
		caps.setCapability("newCommandTimeout", 30000);
		caps.setCapability("udid", this.udid);
		caps.setCapability("noReset", true);
		caps.setCapability("systemPort", this.systemPort);
		caps.setCapability("ignoreUnimportantViews", true);
		caps.setCapability("skipServerInstallation", true);

		AppiumDriver<MobileElement> driver1 = null;

		try {

			driver1 = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:"+this.port+"/wd/hub"), caps);

			System.out.println("Session has been started in the port : http://0.0.0.0:"+this.port+"/wd/hub");

		} catch (MalformedURLException e) {

			System.out.println(e.getMessage());
		}


		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid );

		return driver1;
	}

	public AppiumDriver<MobileElement> launchCashKaroApp() {

		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid  + " & System port is : "+ this.systemPort + "& Device Name is : "+ this.deviceName );


		//Set the Desired Capabilities for chrome browser
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("platformName", "Android");
		caps.setCapability("automationName", this.automationName);
		caps.setCapability("appPackage", "com.cashkaro");
		caps.setCapability("appActivity", "com.cashkaro.MainActivity");
		caps.setCapability("deviceName", this.deviceName);
		caps.setCapability("newCommandTimeout", 30000);
		caps.setCapability("udid", this.udid);
		caps.setCapability("noReset", true);
		caps.setCapability("systemPort", this.systemPort);
		caps.setCapability("skipServerInstallation", true);
		caps.setCapability("ignoreUnimportantViews", true);

		AndroidDriver<MobileElement> driver1 = null;

		try {

			driver1 = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:"+this.port+"/wd/hub"), caps);

			System.out.println("Session has been started in the port : http://0.0.0.0:"+this.port+"/wd/hub");

		} catch (MalformedURLException e) {

			System.out.println(e.getMessage());
		}


		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid );

		return driver1;
	}

	public AppiumDriver<MobileElement> launchCashKaroApp(String appPath) {

		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid  + " & System port is : "+ this.systemPort + "& Device Name is : "+ this.deviceName );


		//Set the Desired Capabilities for chrome browser
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("platformName", "Android");
		caps.setCapability("automationName", this.automationName);
		caps.setCapability("app", appPath);
		caps.setCapability("deviceName", this.deviceName);
		caps.setCapability("newCommandTimeout", 30000);
		caps.setCapability("udid", this.udid);
		//caps.setCapability("noReset", true);
		caps.setCapability("systemPort", this.systemPort);

		AppiumDriver<MobileElement> driver1 = null;

		try {

			driver1 = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:"+this.port+"/wd/hub"), caps);

			System.out.println("Session has been started in the port : http://0.0.0.0:"+this.port+"/wd/hub");

		} catch (MalformedURLException e) {

			System.out.println(e.getMessage());
		}


		System.out.println("Base port is "+ this.port + " & Base udid is " + this.udid );

		return driver1;
	}

	public  String generateMobileNumber() {

		reportStep("About to generate the Mobile number ", "INFO");

		String mobNum = "";

		try {
			mobNum = Utilities.generateRandomNumber(10); 

			reportStep("Successfully generated the Mobile number : the mobile number is "+ mobNum, "INFO");

		}catch (Exception e) {

			String exceptionMessage = e.getMessage();

			reportStep("Failed to  generate the Mobile number and get the exception message as :   "+ exceptionMessage , "FAIL");
		}

		return mobNum;


	}

	public  String generateEmail() {

		reportStep("About to generate the Email address witht the time stamp : ", "INFO");

		String email = "";

		try {
			email = Utilities.generateEmailWithTimeStamp();

			reportStep("Successfully generated the Email   : the Email  is "+ email, "INFO");

		}catch (Exception e) {

			String exceptionMessage = e.getMessage();

			reportStep("Failed to  generate the Email and get the exception message as :   "+ exceptionMessage , "FAIL");
		}

		return email;


	}

	public String generatePaymentOTP(String mobNum) {

		String otp = "";

		try {
			reportStep("About to generate Payment OTP : ", "INFO");

			otp = Utilities.getSignUPOTP(mobNum);

			reportStep("Successfully fetch the OTP as : "+otp, "INFO");

		}catch (Exception e) {

			e.printStackTrace();
			String exceptionMessage = e.getMessage();

			reportStep("Fetching the Payment OTP had a problem  :   "+ exceptionMessage , "FAIL");

		}

		return otp;


	}

	public String cashOut(String email) {

		String success = "";

		try {
			reportStep("About to cashout for the user  : "+ email , "INFO");

			success = Utilities.cashOut(email);

			reportStep("Successfully created the Cashout  ", "INFO");

		}catch (Exception e) {
			e.printStackTrace();
			String exceptionMessage = e.getMessage();
			reportStep("Creating the Cashout had a problem  :   "+ exceptionMessage , "FAIL");
		}

		return success;


	}

	public String addCashbackBonus(String email,String CashbackValue,String CashbackType) {

		try {

			reportStep("Successfully added the bonus amount as "+ CashbackValue +" to the User : " + email , "INFO");

			return Utilities.addCashbackBonus(email,CashbackValue,CashbackType);


		}catch (Exception e) {

			e.printStackTrace();
			reportStep("Failed at adding the cashback bonus to the User "+ email + " Error is : "+ e.getMessage() , "FAIL");

			return null;
		}



	}

	// extract_Values_From_Json_And_Load_Internal_Variables
	public static String extractValuesFromStoreCategoryJsonAndLoadTheVariablesForStore(String strStore_Name , int intStore_Index_In_Json, String key) {

		String value ="";		

		try
		{
			// Upcasting jsonObject to JSONParser
			//	JSONObject objJsonObject 			=  (JSONObject) objJparser.parse(new FileReader(System.getProperty("user.dir") + "/input/StoreCategoryPage_JsonData.json"));

			JSONObject objJsonObject			=  (JSONObject) objJparser.parse(new InputStreamReader(new FileInputStream(System.getProperty("user.dir") + "/input/StoreCategoryPage_JsonData.json"), "UTF-8"));

			//Reading the array
			JSONArray objRootArray 				= (JSONArray)objJsonObject.get("root");


			//Creating Json object to fetch the exact node from the array 
			JSONObject objTitleNode 			= (JSONObject) objRootArray.get(intStore_Index_In_Json);

			//Creating Json object to fetch the key value pair values
			JSONObject objMainNode 				= (JSONObject) objTitleNode.get(strStore_Name);

			/********************************************************************* Store Details Assigning *********************************************************************************/

			// Store_One - Core details
			value 	= 	objMainNode.get(key).toString().trim();


		}
		catch(Exception objError){
			objError.printStackTrace();
		}

		return value;
		/********************************************************************End of Stores Details Assigning*******************************************************************************/
	}

	public String backDateTheExitClickForGivenDate(String exitClickID,int numberOfDaysToBackDate) {

		reportStep("About to hit the end point call to back date the Exit click ", "INFO");

		String backDatedDate = Utilities.strNumber_Days_To_BackDated_Exit_Clicks(numberOfDaysToBackDate);

		String success= Utilities.backDateTheExitclick(exitClickID, backDatedDate);

		reportStep("The Exit Click for back date is : "+exitClickID + "Number of days to be back dated is : " + numberOfDaysToBackDate ,"Info");

		reportStep("Back Date URL Used :"+ GetCodeSetup.CKBASEURL +"/GetCodeSetup.php?page=ticket&id="+exitClickID+"&date="+backDatedDate+" ", "INFO");

		return  success;


	}

	public  void closeAllTabsAndOneTabInChromeBrowser(AppiumDriver<MobileElement> driver) {

		clickByID(driver, tabSwitcherIcon);
		clickByID(driver, menuButton);
		clickByAccessebilityID(driver, closeAlltabsLink);
		clickByID(driver, newTabIcon);

	}

	public  void clickAndEnterURLInSearchOrTypeWebAddress(AppiumDriver<MobileElement> driver, String getCodeUrl) {

		clickByID(driver, searchBox);
		clickByID(driver, urlBar);
		enterTextByID(driver, urlBar, getCodeUrl);
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
	}

	public void clickJoinFreeLink(AppiumDriver<MobileElement> driver) {

		clickByXpath(driver, joinFreeLink);

	}

	public void clickOneLink(AppiumDriver<MobileElement> driver) {

		reportStep("About to click on the Onelink for Deferred deep Linking", "INFO");

		if (clickByXpath(driver, oneLink)) {

			reportStep("Successfully clicked on the OneLink", "PASS");

		} else {

			reportStep("FAiled to click on the One LInk ", "FAIL");
		}

	}

	public void clickOneLink_ReEngagement(AppiumDriver<MobileElement> driver) {

		reportStep("About to click on the Onelink for Deferred deep Linking", "INFO");

		if (clickByXpath(driver, oneLink)) {

			reportStep("Successfully clicked on the OneLink", "PASS");

		} else {

			reportStep("FAiled to click on the One LInk ", "FAIL");
		}

		if (isElementLocatedByXpathPresent(driver, "//android.widget.TextView[@text='Amazon India']")) {

			reportStep("ReEnagement -Is Working fine", "PASS");

		} else {
			reportStep("ReEnagement is not working - It is not redirected to the Amazon Store", "FAIL");

		}

	}

	public void playStoreRedirectionVerification(AppiumDriver<MobileElement> driver) {

		if (isElementLocatedByXpathPresent(driver, buttonInstall)) {

			reportStep("Redirected to playstore", "PASS");

		} else {
			reportStep("Not redirected to playstore", "FAIL");

		}

	}

	public void removeApp(AppiumDriver<MobileElement> driver,String appPackage) {

		reportStep("About to uninstall cashkaro App from the device", "INFO");

		try {

			driver.removeApp(appPackage); 

			reportStep("Successfully uninstall CashKaro app from the device", "PASS");

		} catch (Exception e) {

			reportStep("Failed to uninstall CashKaro app from the device", "FAIL");
		}


	}

	//Notification methods: 


	//This return true, if the API call works fine without an error.
	public boolean notificationAPICall(String fcmID) {

		reportStep("About to Hit the Post API call to Push the Notification ", "INFO");

		if(Utilities.notificationPostAPICall(fcmID).equals("success")){

			reportStep("Successfully hit the Post End Point :" + GetCodeSetup.CKAPIBASEURI+ "v1/notification/app_group_notification and Get "
					+ " Expected respone. ", "PASS");

			return true;

		} else {

			reportStep("Failed to  hit the Post End Point :" + GetCodeSetup.CKAPIBASEURI+ "v1/notification/app_group_notification and then failed to get "
					+ " the  Expected respone . ", "FAIL");

			return false;
		}

	}

	//This return true, if the API call works fine without an error.
	public boolean notifationAPICall_NormalPriority(String fcmID) {

		reportStep("About to Hit the Post API call to Push the Notification ", "INFO");

		if(Utilities.notificationPostAPICall_NormalPriority(fcmID).equals("success")){

			reportStep("Successfully hit the Post End Point :" + GetCodeSetup.CKAPIBASEURI+ "v1/notification/app_group_notification and Get "
					+ " Expected respone. ", "PASS");

			return true;

		} else {

			reportStep("Failed to  hit the Post End Point :" + GetCodeSetup.CKAPIBASEURI+ "v1/notification/app_group_notification and then failed to get "
					+ " the  Expected respone . ", "FAIL");

			return false;
		}



	}

	//This return true, if the API call works fine without an error.
	public boolean notifationAPICall_WithRedirectionalURLMyEarningsPage(String fcmID) {

		reportStep("About to Hit the Post API call to Push the Notification WIth Redirectional URL to MyEarnings Page ", "INFO");

		if(Utilities.notificationPostAPICall_WithRedirectionalURLToMyEarningsPage(fcmID).equals("success")){

			reportStep("Successfully hit the Post End Point :" + GetCodeSetup.CKAPIBASEURI+ "v1/notification/app_group_notification and Get "
					+ " Expected respone. ", "PASS");

			return true;

		} else {

			reportStep("Failed to  hit the Post End Point :" + GetCodeSetup.CKAPIBASEURI+ "v1/notification/app_group_notification and then failed to get "
					+ " the  Expected respone . ", "FAIL");

			return false;
		}



	}

	//This return true, if the API call works fine without an error.
	public boolean notifationAPICall_WithRedirectionalURLMyEarningsPage_NormalPriority(String fcmID) {

		reportStep("About to Hit the Post API call to Push the Notification WIth Redirectional URL to MyEarnings Page ", "INFO");

		if(Utilities.notificationPostAPICall_WithRedirectionalURLToMyEarningsPage_NormalPriority(fcmID).equals("success")){

			reportStep("Successfully hit the Post End Point :" + GetCodeSetup.CKAPIBASEURI+ "v1/notification/app_group_notification and Get "
					+ " Expected respone. ", "PASS");

			return true;

		} else {

			reportStep("Failed to  hit the Post End Point :" + GetCodeSetup.CKAPIBASEURI+ "v1/notification/app_group_notification and then failed to get "
					+ " the  Expected respone . ", "FAIL");

			return false;
		}



	}

	//This return true, if the API call works fine without an error.
	public boolean notificataionAPICall_WithoutRedirectionURL_WithoutBigImage_WithoutLargeIcon(String fcmID) {

		reportStep("About to Hit the Post API call to Push the Notification which is - without Big image, large icon and Redirectional URL ", "INFO");

		if(Utilities.notificationPostAPICall_WithoutLargeIcon_WithoutBigImage_WithOutReDirectURL(fcmID).equals("success")){

			reportStep("Successfully hit the Post End Point :" + GetCodeSetup.CKAPIBASEURI+ "v1/notification/app_group_notification and Get "
					+ " Expected respone. ", "PASS");

			return true;

		} else {

			reportStep("Failed to  hit the Post End Point :" + GetCodeSetup.CKAPIBASEURI+ "v1/notification/app_group_notification and then failed to get "
					+ " the  Expected respone . ", "FAIL");

			return false;
		}

	}

	//This return true, if the API call works fine without an error.
	public boolean notificataionAPICall_WithoutRedirectionURL_WithoutBigImage_WithoutLargeIcon_NormalPriority(String fcmID) {

		reportStep("About to Hit the Post API call to Push the Notification which is - without Big image, large icon and Redirectional URL ", "INFO");

		if(Utilities.notificationPostAPICall_WithoutLargeIcon_WithoutBigImage_WithOutReDirectURL_NormalPriority(fcmID).equals("success")){

			reportStep("Successfully hit the Post End Point :" + GetCodeSetup.CKAPIBASEURI+ "v1/notification/app_group_notification and Get "
					+ " Expected respone. ", "PASS");

			return true;

		} else {

			reportStep("Failed to  hit the Post End Point :" + GetCodeSetup.CKAPIBASEURI+ "v1/notification/app_group_notification and then failed to get "
					+ " the  Expected respone . ", "FAIL");

			return false;
		}

	}

	//This return true, if the API call works fine without an error.
	public boolean notificataionAPICall_WithoutBigImage(String fcmID) {

		reportStep("About to Hit the Post API call to Push the Notification Without Big Image ", "INFO");

		if(Utilities.notificationPostAPICall_WithOutBigImage(fcmID).equals("success")){

			reportStep("Successfully hit the Post End Point :" + GetCodeSetup.CKAPIBASEURI+ "v1/notification/app_group_notification and Get "
					+ " Expected respone. ", "PASS");

			return true;

		} else {

			reportStep("Failed to  hit the Post End Point :" + GetCodeSetup.CKAPIBASEURI+ "v1/notification/app_group_notification and then failed to get "
					+ " the  Expected respone . ", "FAIL");

			return false;
		}

	}


	//This return true, if the API call works fine without an error.
	public boolean notificataionAPICall_WithoutBigImage_NormalPriority(String fcmID) {

		reportStep("About to Hit the Post API call to Push the Notification Without Big Image ", "INFO");

		if(Utilities.notificationPostAPICall_WithOutBigImage_NormalPriority(fcmID).equals("success")){

			reportStep("Successfully hit the Post End Point :" + GetCodeSetup.CKAPIBASEURI+ "v1/notification/app_group_notification and Get "
					+ " Expected respone. ", "PASS");

			return true;

		} else {

			reportStep("Failed to  hit the Post End Point :" + GetCodeSetup.CKAPIBASEURI+ "v1/notification/app_group_notification and then failed to get "
					+ " the  Expected respone . ", "FAIL");

			return false;
		}

	}

	public String getFCMID()  {

		reportStep("About ot get the FCM ID ", "INFO");

		String fireBaseToken = Utilities.fetchFireBaseTokenFCMID(driver);

		reportStep("FireBase Token Value : ", fireBaseToken);

		return	fireBaseToken ;

	}

	//This return true, if the API call works fine without an error.
	public boolean notifationAPICall_WithTemplate(String templateId,String fcmID) {

		reportStep("About to Hit the Post API call to With Template ", "INFO");

		if(Utilities.notificationPostAPICall_WithTemplate(templateId,fcmID).equals("success")) {

			reportStep("Successfully hit the Post End Point :" + GetCodeSetup.CKAPIBASEURI+ "v1/notification/notification and Get "
					+ " Expected respone. ", "PASS");

			return true;

		} else {

			reportStep("Failed to  hit the Post End Point :" + GetCodeSetup.CKAPIBASEURI+ "v1/notification/notification and then failed to get "
					+ " the  Expected respone . ", "FAIL");

			return false;
		}



	}


	public void allowLinkInPermissionPopup(AppiumDriver<MobileElement> driver) {

		reportStep("About to click Allow button in Permission popup", "INFO");

		try {

			if (clickByID(driver, allowLinkInPermissionPopup)){

				reportStep("Clicked on the Allow link on the permission popup ", "INFO");

			} else {

				reportStep("Not able to click on the Allow link on the permission popup ", "FAIL");
			}

		} catch (Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Permission popup is not shown", "FAIL");
		}
	}

	public  void resetFacebookAccount() {

		try {

			if(Utilities.fbAccountReset(getTestData(1, "FBTestUserEmail2")).equals("Success")) {

				reportStep("Successfully reset the Facebook account ", "INFO");

			}else {

				reportStep("Not able to reset the Facebook account ", "FAIL");
			}
		}
		catch (Exception e) {

			reportStep("Not able to reset the Facebook account ", "FAIL");

		}

	}

	public void checkMissingCashbackFlag() {

		AppDynamicVariables objTestData = new AppDynamicVariables(driver, testInfo);		
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("RW_Store_One");
		String rwStoreOne =  objTestData.getRequiredStoreName("str_Store_One_Name");

		//Get the Second Store Name
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Two("RW_Store_Two");
		String rwStoreTwo = objTestData.getRequiredStoreName("str_Store_Two_Name");

		CashKaroUtility objCU = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = objCU.launchChromeWebView(driver);

		AdminCoreFunction objAdminFun = new AdminCoreFunction(driver, testInfo);		
		objAdminFun.naviagate_AdminPage();
		objAdminFun.enterUsername(getTestData(8, "AdminUserName"));
		objAdminFun.enterPassword(getTestData(8, "AdminPassword"));
		objAdminFun.clickSubmit();

		objAdminFun.clickOnHamburgerMenu();
		objAdminFun.clickStoresMenu();
		objAdminFun.clickStoresSubMenu();

		AdminStoresPage strPage = new AdminStoresPage(driver, testInfo);		
		strPage.enterStoreNameUsingJS(rwStoreOne);
		strPage.clickSubmitButton();
		strPage.clickEditButton(1);
		strPage.clickOnAcceptsMissingCashbackTicketFlagFromOnToOff();
		strPage.enterOrderIdFormat("Test-1234-STR1");
		strPage.clickSubmitButton();		

		objAdminFun.clickOnHamburgerMenu();
		objAdminFun.clickStoresMenu();
		objAdminFun.clickStoresSubMenu();

		strPage.enterStoreNameUsingJS(rwStoreTwo);
		strPage.clickSubmitButton();
		strPage.clickEditButton(1);
		strPage.clickOnAcceptsMissingCashbackTicketFlagFromOnToOff();
		strPage.enterOrderIdFormat("Test-4321-STR2");
		strPage.clickSubmitButton();		



	}

	
	
	public void unCheckMissingCashbackFlag() {

		AppDynamicVariables objTestData = new AppDynamicVariables(driver, testInfo);		
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("RW_Store_One");
		String rwStoreOne =  objTestData.getRequiredStoreName("str_Store_One_Name");

		//Get the Second Store Name
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Two("RW_Store_Two");
		String rwStoreTwo = objTestData.getRequiredStoreName("str_Store_Two_Name");

		CashKaroUtility objCU = new CashKaroUtility(udid, port, automationName, deviceName);
		driver = objCU.launchChromeWebView(driver);

		AdminCoreFunction objAdminFun = new AdminCoreFunction(driver, testInfo);		
		objAdminFun.naviagate_AdminPage();
		objAdminFun.enterUsername(getTestData(8, "AdminUserName"));
		objAdminFun.enterPassword(getTestData(8, "AdminPassword"));
		objAdminFun.clickSubmit();

		objAdminFun.clickOnHamburgerMenu();
		objAdminFun.clickStoresMenu();
		objAdminFun.clickStoresSubMenu();

		AdminStoresPage strPage = new AdminStoresPage(driver, testInfo);		
		strPage.enterStoreNameUsingJS(rwStoreOne);
		strPage.clickSubmitButton();
		strPage.clickEditButton(1);
		strPage.clickOnAcceptsMissingCashbackTicketFlagFromOnToOff();
		strPage.enterOrderIdFormat("Test-1234-STR1");
		strPage.clickSubmitButton();		

		objAdminFun.clickOnHamburgerMenu();
		objAdminFun.clickStoresMenu();
		objAdminFun.clickStoresSubMenu();

		strPage.enterStoreNameUsingJS(rwStoreTwo);
		strPage.clickSubmitButton();
		strPage.clickEditButton(1);
		strPage.clickOnAcceptsMissingCashbackTicketFlagFromOnToOff();
		strPage.enterOrderIdFormat("Test-4321-STR2");
		strPage.clickSubmitButton();		



	}





}