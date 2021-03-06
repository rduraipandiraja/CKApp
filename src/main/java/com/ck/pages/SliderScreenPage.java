package com.ck.pages;

import java.util.List;
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

public class SliderScreenPage extends WrapperMethods {
	
	//Constructor call
	public SliderScreenPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		

		reportStep("Waiting for the slider", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(sliderMenuHighestCashbackStores));
			reportStep("Successfully landed on the Slider ", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the Slider ", "FAIL");
		}
		
	}
	
	//Variable initialzation
	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='txt_mainMenu_highestCashbackTop']/android.widget.TextView[@text='Highest Cashback Stores']") 
	MobileElement sliderMenuHighestCashbackStores;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='App Exclusives']") 
	MobileElement sliderMenuAppExclusives;

	@FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='txt_mainMenu_retailerCategoryTop']|//android.widget.TextView[@text='Retailer Category']") 
	MobileElement sliderMenuRetailerCategory;

	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@text='Home'])[2]|//android.view.ViewGroup[@content-desc=\"txt_mainMenu_home\"]") 
	MobileElement sliderMenuHomeIcon;
	
	//Method creation
	public StoreCategoryPage clickOntheHighestCashbackRates() {
		
		reportStep("About to click on the Highest Cashback rates from the slider", "INFO");
		
		if(click(sliderMenuHighestCashbackStores)){
			
			reportStep("Successfully clicked on the Highest Slider Cashback from the slider screen", "PASS");
			
		}else {
			
			reportStep("Failed to click on the Highest Slider Cashback from the slider screen", "FAIL");
		}
		
		return new StoreCategoryPage(driver, testInfo);
	}

	//Method creation
	public SliderScreenPage presenceOfHighestCashbackRates() {
		
		reportStep("About to verify presence of Highest Cashback rates from the slider", "INFO");
		
		validateTheElementPresence(sliderMenuHighestCashbackStores);
		
		return this;
	}

	public SliderScreenPage presenceOfAppExclusiveCategory() {
		
		reportStep("About to verify presence of app exclusive menu in slide menu", "INFO");
		
		validateTheElementPresence(sliderMenuAppExclusives);
		
		return this;
		
	}

	public SliderScreenPage presenceOfRetailerCategory() {
		
		reportStep("About to verify presence of retailer category in slide menu", "INFO");
		
		validateTheElementPresence(sliderMenuRetailerCategory);
		
		return this;
		
	}	

	public SliderScreenPage presenceOfHomeIcon() {
		
		reportStep("About to verify the home icon in slide menu", "INFO");
		
		validateTheElementPresence(sliderMenuHomeIcon);
		
		return this;
		
	}	
	
	public HomePage clickHomeIcon() {
		
		reportStep("About to click the home icon in slide menu", "INFO");
		
		if(click(sliderMenuHomeIcon)){
			
			reportStep("Successfully clicked on the home icon in slider screen", "PASS");
			
		}else {
			
			reportStep("Failed to click on the home icon in slider screen", "FAIL");
		}

		return new HomePage(driver, testInfo);
		
	}

	public StoreCategoryPage clickHighestCashbackRates() {
		
		reportStep("About to click on the Highest Cashback rates from the slider", "INFO");
		
		if(clickAfterWait(sliderMenuHighestCashbackStores)){
			
			reportStep("Successfully clicked on the Highest Cashback rates from the slider screen", "PASS");
			
		}else {
			
			reportStep("Failed to click on the Highest Cashback rates from the slider screen", "FAIL");
		}
		
		return new StoreCategoryPage(driver, testInfo);
	}

	public StoreCategoryPage clickAppExclusiveCategory() {
		
		reportStep("About to verify the app exclusive menu in slide menu", "INFO");
		
		if(clickAfterWait(sliderMenuAppExclusives)){
			
			reportStep("Successfully clicked on the app exclusive from the slider screen", "PASS");
			
		}else {
			
			reportStep("Failed to click on the app exclusive from the slider screen", "FAIL");
		}
		
		return new StoreCategoryPage(driver, testInfo);
		
	}

	public RetailerCategoryPage clickRetailerCategory() {
		
		reportStep("About to verify the retailer category in slide menu", "INFO");
		
		if(clickAfterWait(sliderMenuRetailerCategory)){
			
			reportStep("Successfully clicked on the retailer category from the slider screen", "PASS");
			
		}else {
			
			reportStep("Failed to click on the retailer category from the slider screen", "FAIL");
		}
		
		return new RetailerCategoryPage(driver, testInfo);
		
	}	
			
}
