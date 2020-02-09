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

public class EarningsInfoPage extends WrapperMethods{
	
	public EarningsInfoPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the EarningsInfo Page", "INFO");

		try {
			wait.until(ExpectedConditions.visibilityOf(EarningsInfo));

			reportStep("Successfully landed on the EarningsInfo page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the  EarningsInfo page", "FAIL");
		}

	}
	
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Earnings Info']")
	MobileElement EarningsInfo ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='You can request for a payment only if you have Rs.250 Confirmed Cashback or Rewards.']")
	MobileElement youCanReq_Text ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='If your Confirmed Cashback & Rewards add up to Rs.250 or more you can redeem them as Gift Cards.']")
	MobileElement ifYour_Text ;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Rewards can never be transferred to your Bank Account.']")
	MobileElement rewardsCanNever_Text ;
	
	@FindBy(how = How.XPATH, using = "//android.widget.ImageView") 
	MobileElement earningsInfoBackButton ;

	public EarningsInfoPage validateEarningsInfoPoints() {
		
		reportStep("About to validate the Payment earnings info 3 Points ", "INFO");
		validateTheElementPresence(youCanReq_Text);
		validateTheElementPresence(ifYour_Text);
		validateTheElementPresence(rewardsCanNever_Text);
		reportStep("Successfully validated the Payment earnings info 3 Points ", "INFO");
		return this;
	}
	
	public PaymentPage clickOnBackButton() {

		reportStep("About to click on the earningsInfo BackButton button ", "INFO");
		
		driver.navigate().back();

		return new PaymentPage(driver, testInfo);
	}

	public MyEarningsPage clickOnBackButtonForMyEarningsPage(){

		reportStep("About to click on the earningsInfo BackButton button ", "INFO");


		driver.navigate().back();
		
		return new MyEarningsPage(driver, testInfo);
	}

	
}
