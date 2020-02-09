package com.ck.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.app.ck.base.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class SeeVideoToEarnCashbackPage extends WrapperMethods {
	

	public SeeVideoToEarnCashbackPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		this.driver = driver;
		this.testInfo = testInfo;
		
		 PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the On boarding Page in Cashkaro ..!!", "INFO");
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		try {

			try {
				
				allowThePermissionPopup();
				reportStep("Handled first permission popup", "INFO");
			} catch (Exception e) {

				reportStep(e.getMessage(), "INFO");
				reportStep("First permission popup is not shown", "FAIL");
			}

			wait.until(ExpectedConditions.visibilityOf(loginJoinButton));
			reportStep("Successfully landed on the Onboarding page", "PASS");

		} catch (Exception e) {
			reportStep("Not able to land on the Onboarding page", "FAIL");
		}
	}

	public SeeVideoToEarnCashbackPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo,int popUpCounter) {
		
		this.driver = driver;
		this.testInfo = testInfo;
		
		System.out.println(" Login page counter " + popUpCounter);
		
		reportStep("Second time onwards this constructore is being called to avoid Waiting for permission Popup  ", "INFO");
		reportStep(popUpCounter + " th time Entering into the OnBorading page ", "INFO");
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
		reportStep("Waiting for the OnBorading button Element ..!!", "INFO");
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		try {
			
			wait.until(ExpectedConditions.visibilityOf(loginJoinButton));
			reportStep("Successfully landed on the OnBorading page", "PASS");

		} catch (Exception e) {
			
			reportStep("Not able to land on the OnBorading page ( In Second Catch block )", "FAIL");

		}
	}
	
	
	// Get started page - variable
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Login / Join']")
	MobileElement loginJoinButton;

	@FindBy(how = How.ID, using = "com.android.packageinstaller:id/permission_allow_button")
	MobileElement allowLinkInPermissionPopup;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Go To Homepage']")
	MobileElement GoToHomePageButton;

	String seeVideoToEarnCashbackText = "//android.widget.TextView[@text='SEE VIDEO TO EARN CASHBACK']";

	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='btn_intro_hindi']/android.widget.TextView")
	MobileElement inhindiButton;

	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='btn_intro_english']/android.widget.TextView")
	MobileElement inEnglishButton;

	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='txt_makeProfit_closemodal']")
	MobileElement closeButton;

	// Method creation :

	public SignInPage clickOnLoginJoinButton() {

		reportStep("About click on the OnBorading button at the OnBorading page ", "INFO");

		if (click(loginJoinButton)) {

			reportStep("Clicked on the OnBorading button" + loginJoinButton + " successfully ", "PASS");
		}

		return new SignInPage(driver, testInfo);
	}
	
	public SignInPage clickOnLoginJoinButton_ForSecondTimeTotheSignINPage() {

		reportStep("About click on the OnBorading button at the OnBorading page ", "INFO");

		if (click(loginJoinButton)) {

			reportStep("Clicked on the OnBorading button" + loginJoinButton + " successfully ", "PASS");
		}

		return new SignInPage(driver, testInfo,0);
	}

	public SeeVideoToEarnCashbackPage allowThePermissionPopup() {

		System.out.println("About to click onn the Allow on the permissionn popup in OnBorading page");

		try {

			if (click_WaitFor25Sec(allowLinkInPermissionPopup)){

				reportStep("Cliked on the Allow link " + allowLinkInPermissionPopup + " on the permission popup at the OnBorading page", "INFO");

			} else {

				reportStep("Not able to click on the Allow link on the permission popup at the OnBorading  page", "INFO");
			}

		}catch (Exception ee) {

			reportStep(ee.getMessage(), "INFO");

		}


		return this;

	} 

	public HomePage clickOnGoToHomePage() {

		reportStep("About click on the GoToHomePageButton  at the OnBorading page ", "INFO");

		if (clickAfterWait(GoToHomePageButton)) {

			reportStep("Clicked on the GoToHomePageButton " + GoToHomePageButton + " successfully ", "PASS");

		}else {
			
			reportStep("Failed Click on the GoToHomePageButton " + GoToHomePageButton , "FAIL");
		}
		
		return new HomePage(driver, testInfo);
	}

	public SeeVideoToEarnCashbackPage clickOnInHindiButton() {

		reportStep("About click on the InHindiButton  at the OnBorading page ", "INFO");

		if (clickAfterWait(inhindiButton)) {

			reportStep("Clicked on the InHindiButton " + inhindiButton + " successfully ", "PASS");

		}else {
			
			reportStep("Failed Click on the InHindiButton " + inhindiButton , "FAIL");
		}
		
		return this;
	}

	public SeeVideoToEarnCashbackPage clickOnInEnglishButton() {

		reportStep("About click on the InEnglishButton  at the OnBorading page ", "INFO");

		if (clickAfterWait(inEnglishButton)) {

			reportStep("Clicked on the InEnglishButton " + inEnglishButton + " successfully ", "PASS");

		}else {
			
			reportStep("Failed Click on the InEnglishButton " + inEnglishButton , "FAIL");
		}
		
		return this;
	}

	public SeeVideoToEarnCashbackPage clickOnCloseButton() {

		reportStep("About click on the CloseButton  at the OnBorading page ", "INFO");

		if (clickAfterWait(closeButton)) {

			reportStep("Clicked on the CloseButton " + closeButton + " successfully ", "PASS");

		}else {
			
			reportStep("Failed Click on the CloseButton " + closeButton , "FAIL");
		}
		
		if (isElementLocatedByXpathPresent(seeVideoToEarnCashbackText)) {

			reportStep("verified seeVideoToEarnCashbackText successfully ", "PASS");

		}else {
			
			reportStep("Not able to verify seeVideoToEarnCashbackText", "FAIL");
		}
		
		return this;
	}

	public SeeVideoToEarnCashbackPage haltScriptTillVideoPlayCompletes() {

		reportStep("Video is in play so halting script for 1.48 seconds", "INFO");

		sleep(100000);
		
		reportStep("Video play has been stopped played resuming scripts", "INFO");
		
		return this;
	}

	public SeeVideoToEarnCashbackPage validateSeeVideoText() {
		
		if (isElementLocatedByXpathPresent(seeVideoToEarnCashbackText)) {

			reportStep("verified seeVideoToEarnCashbackText successfully ", "PASS");

		}else {
			
			reportStep("Not able to verify seeVideoToEarnCashbackText", "FAIL");
		}
		
		return this;
	}

}
