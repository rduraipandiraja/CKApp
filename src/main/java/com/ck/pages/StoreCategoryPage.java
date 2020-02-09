package com.ck.pages;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
	
	//Contructor call
	public class StoreCategoryPage extends WrapperMethods {

	//Variable initialization
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Highest Cashback Stores']") 
	MobileElement sliderHighestCashbackRates; 

	@FindBy(how = How.XPATH, using = "(//android.view.ViewGroup[@content-desc=\"btn_storeCard_button\"])[1]") 
	MobileElement storeCatStoreBtn;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='txt_storeCategory_title']") 
	MobileElement storeCategoryTitle;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Popular')]") 
	MobileElement popularTab;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Percent')]") 
	MobileElement percentTab;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Amount')]") 
	MobileElement AmountTab;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'A-Z')]") 
	MobileElement atozTab;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'App Exclusive')]") 
	List<MobileElement> appExclusivesText;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Highest Cashback Stores')]") 
	List<MobileElement> highestCashbackStoresText;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'PPS ATM APP SPECIFIC SUBCATEGORY')]") 
	List<MobileElement> ppsAtmAppSpecificSubcategory;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Highest Cashback Stores')]") 
	List<MobileElement> retailerCategoryText;
	
	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView)[1]") 
	MobileElement backButtonClick;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'PPS ATM APP SPECIFIC SUBCATEGORY ONE Offer')]") 
	MobileElement automationSpecificBannerTile;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='PPS ATM APP SPECIFIC SUBCATEGORY ONE']") 
	MobileElement automationSpecificHeaderTile;
	
	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@text='PPS ATM APP SPECIFIC SUBCATEGORY ONE'])[2]") 
	MobileElement automationSpecificDropdown;
	
	@FindBy(how = How.XPATH, using = "//android.widget.CheckedTextView[@text='PPS ATM APP SPECIFIC SUBCATEGORY ONE']") 
	MobileElement automationSpecificDropdownClick;

	@FindBy(how = How.XPATH, using = "(//android.widget.TextView[@content-desc='btn_storeCard_button_txt'])[1]") 
	MobileElement firstCardShortDescription;
	
	String firstStoreCardDescription = "(//android.widget.TextView[@content-desc='btn_storeCard_button_txt'])[1]";
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='btn_storeCard_button_txt']") 
	List<MobileElement> shortDescription;
	
	@FindBy(how = How.XPATH, using = "(//android.view.ViewGroup[@content-desc='btn_storeCard_button'])[1]") 
	MobileElement shortDesc;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"txt_storeCard_cshbackRatesTerms\"]")
	MobileElement cashbackOrRewardsRatesAndTerms ;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView[@content-desc='img_storeCard_imageValue'])[1]")
	MobileElement searchResultsInStoresImage;
	
	//Method creation
	public StoreCategoryPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		reportStep("Waiting for the StoreCategory page", "INFO");
		try {
			
			scrollFromUpToDowninApp();
			scrollFromUpToDowninApp();
			scrollFromUpToDowninApp();
			
			wait.until(ExpectedConditions.visibilityOf(storeCategoryTitle));
			reportStep("Successfully landed on the StoreCategory page", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the StoreCategory page", "FAIL");
		}

	}

	public SignInPage clickOnStoreCatStoreButton() {

		reportStep("About to click on the store button at the store category page", "INFO");

		if(click(storeCatStoreBtn)) {

			reportStep("Sucessfully clicked on the  Store category - Store button ", "PASS");
		}else {

			reportStep("Failed to click on the  Store category - Store button ", "FAIL");
		}

		return new SignInPage(driver, testInfo);
	}

	public StoreCategoryPage verifyAppExclusivesPageNavigation() {

		reportStep("About to verify the text app exlusives in app exclusives store category page", "INFO");
			
		int appExclusivesCategoryNameCount = getListOfElementsSize(appExclusivesText);
		
		String appExclusivesCatNameCount = Integer.toString(appExclusivesCategoryNameCount);
		
		validateTheActualValueWithExpectedValue(appExclusivesCatNameCount, getTestData(9, "CountThree"));
		
		return this;
			
	}

	public StoreCategoryPage verifyHighestCashbackStorePageNavigation() {

		reportStep("About to verify the text highest cashback stores in highest cashback stores in store category page", "INFO");
		
		int highestCashbackStoresCategoryNameCount = getListOfElementsSize(highestCashbackStoresText);
		
		String highestCashbackStoresCatNameCount = Integer.toString(highestCashbackStoresCategoryNameCount);
		
		validateTheActualValueWithExpectedValue(highestCashbackStoresCatNameCount, getTestData(9, "CountThree"));
		
		return this;
			
	}

	public StoreCategoryPage verifyAutomationSpecificSubCategoryNavigation() {

		reportStep("About to verify the text PPS ATM APP SPECIFIC SUBCATEGORY in store category page", "INFO");
		
		int ppsAtmAppSpecificSubcategoryCount = getListOfElementsSize(ppsAtmAppSpecificSubcategory);
		
		String ppsAtmAppSpecificSubcatCount = Integer.toString(ppsAtmAppSpecificSubcategoryCount);
		
		validateTheActualValueWithExpectedValue(ppsAtmAppSpecificSubcatCount, getTestData(9, "CountThree"));
		
		return this;
			
	}
	
	public HomePage clickBackButton() {

		reportStep("About to click on back button in store category page", "INFO");

//		if(click(backButtonClick)) {
//
//			reportStep("Successfully clicked on the back button in store cat page", "PASS");
//		}else {
//
//			reportStep("Failed to  click on the back button in store cat page", "FAIL");
//		}
		
		driver.navigate().back();

		return new HomePage(driver, testInfo);
		
	}
	
	public RetailerCategoryPage clickBackButtonForRetailerCategoryPageNavigation() {

		reportStep("About to click on back button in store category page", "INFO");

//		if(click(backButtonClick)) {
//
//			reportStep("Successfully clicked on the back button in store cat page", "PASS");
//		}else {
//
//			reportStep("Failed to  click on the back button in store cat page", "FAIL");
//		}
		
		driver.navigate().back();

		return new RetailerCategoryPage(driver, testInfo);
		
	}

	public StoreCategoryPage verifyStoreCategoryPageBannerTitle() {

		reportStep("About to verify banner title", "INFO");
		
		validateTheElementPresence(automationSpecificBannerTile);

		reportStep("Successfully verified banner title", "INFO");
		
		return this;
			
	}
	
	public StoreCategoryPage verifyStoreCategoryPageHeaderTitle() {

		reportStep("About to verify header title", "INFO");
		
		validateTheElementPresence(automationSpecificHeaderTile);

		reportStep("Successfully verified header title", "INFO");
		
		return this;
			
	}
	
	public StoreCategoryPage verifyStoreCategoryPageDropdown() {

		reportStep("About to click dropdown  in store category page", "INFO");
		
		if(click(automationSpecificDropdown)){
			
			reportStep("Successfully clicked dropdown  in store category page", "PASS");
			
		}else {
			
			reportStep("Failed to click dropdown  in store category page", "FAIL");
		}
		
		if(click(automationSpecificDropdownClick)){
			
			reportStep("Successfully selected value from the dropdown", "PASS");
			
		}else {
			
			reportStep("Failed to select value from dropdown", "FAIL");
		}
		
		return this;
			
	}

	public StoreCategoryPage clickPopularTab(String deviceName) {

		reportStep("About to click popularity tab in store category page", "INFO");
		
		scrollFromUpToDowninAppFromTheMiddleOfTheScreen();
		scrollFromUpToDowninAppFromTheMiddleOfTheScreen();
		scrollFromUpToDowninAppFromTheMiddleOfTheScreen();
		scrollFromUpToDowninAppFromTheMiddleOfTheScreen();
		
		if(click(popularTab)){
			
			reportStep("Successfully clicked popularity tab", "PASS");
			
		}else {
			
			reportStep("Failed to click popularity tab", "FAIL");
		}
		
		return this;
			
	}
	
	
	public StoreCategoryPage clickPercentTab(String deviceName) {

		reportStep("About to click percent tab in store category page", "INFO");
		
		scrollFromUpToDowninAppFromTheMiddleOfTheScreen();
		scrollFromUpToDowninAppFromTheMiddleOfTheScreen();
		scrollFromUpToDowninAppFromTheMiddleOfTheScreen();
		scrollFromUpToDowninAppFromTheMiddleOfTheScreen();
		
		if(click(percentTab)){
			
			reportStep("Successfully clicked percent tab", "PASS");
			
		}else {
			
			reportStep("Failed to click percent tab", "FAIL");
		}
		
		return this;
			
	}
	
	public StoreCategoryPage clickAmountTab(String deviceName) {

		reportStep("About to click amount tab in store category page", "INFO");
		
		scrollFromUpToDowninAppFromTheMiddleOfTheScreen();
		scrollFromUpToDowninAppFromTheMiddleOfTheScreen();
		scrollFromUpToDowninAppFromTheMiddleOfTheScreen();
		scrollFromUpToDowninAppFromTheMiddleOfTheScreen();
		
		if(click(AmountTab)){
			
			reportStep("Successfully clicked amount tab", "PASS");
			
		}else {
			
			reportStep("Failed to click amount tab", "FAIL");
		}
		
		return this;
			
	}
	
	public StoreCategoryPage clickAzTab(String deviceName) {

		reportStep("About to click AtoZ tab in store category page", "INFO");

		scrollFromUpToDowninAppFromTheMiddleOfTheScreen();
		scrollFromUpToDowninAppFromTheMiddleOfTheScreen();
		scrollFromUpToDowninAppFromTheMiddleOfTheScreen();
		scrollFromUpToDowninAppFromTheMiddleOfTheScreen();
		
		if(click(atozTab)){
			
			reportStep("Successfully clicked AtoZ tab", "PASS");
			
		}else {
			
			reportStep("Failed to click AtoZ tab", "FAIL");
		}
		
		return this;
			
	}

	public StoreCategoryPage verifyStoreCategoryPageObjectPresence() {

		reportStep("About to validate the object presence in store category page", "INFO");
		
		scrollFromUpToDowninApp();
		scrollFromUpToDowninApp();
		scrollFromUpToDowninApp();
		
		validateTheElementPresence(popularTab);
		validateTheElementPresence(atozTab);
		validateTheElementPresence(AmountTab);
		validateTheElementPresence(percentTab);
		validateTheElementPresence(shortDesc);

		reportStep("Successfully validated the object presence in store category page", "INFO");
		
		return this;
			
	}
	
	public StoreCategoryPage verifyFirstCardDescription(String expected) {

		reportStep("About to verify first card short description in store category page", "INFO");
		
		scrollFromDownToUpTillRequiredElementIsVisible(firstStoreCardDescription);
		
		String shortDescription = getText(firstCardShortDescription);
		
		validateTheActualValueWithExpectedValue(shortDescription, expected);
		
		return this;
			
	}
	
	public StoreCategoryPage verifyLastCardDescription(String expected, String deviceName) {

		reportStep("About to verify last card short description in store category page", "INFO");

		deviceName = deviceName.toLowerCase();

		switch (deviceName) {

		case "samsung":

			reportStep("Started scrolling for samsung device", "INFO");

			scrollFromDownToUpinApp_FourSecDuration();
			scrollFromDownToUpinApp_FourSecDuration();
			scrollFromDownToUpinApp_FourSecDuration();
			scrollFromDownToUpinApp_FourSecDuration();
			scrollFromDownToUpinApp_FourSecDuration();
			scrollFromDownToUpinApp_FourSecDuration();
			
			break;

		case "redmi":

			reportStep("Started scrolling for redmi device", "INFO");

			scrollFromDownToUpinApp_FourSecDuration();
			scrollFromDownToUpinApp_FourSecDuration();
			scrollFromDownToUpinApp_FourSecDuration();
			scrollFromDownToUpinApp_FourSecDuration();
			scrollFromDownToUpinApp_FourSecDuration();
			scrollFromDownToUpinApp_FourSecDuration();
			
			break;

		case "moto":

			reportStep("Started scrolling for moto device", "INFO");

			scrollFromDownToUpinApp();
			scrollFromDownToUpinApp();
			scrollFromDownToUpinApp();
			scrollFromDownToUpinApp();
			scrollFromDownToUpinApp();
			
			break;

		}
		
		String one = getTestData(9, "CountOne");
		
		int oneValue = Integer.parseInt(one);
		
		int countOfVisibleDescription = shortDescription.size();
		
		int subtractCountOfVisibleDescription = countOfVisibleDescription - oneValue;
		
		MobileElement lastCardShortdescription = shortDescription.get(subtractCountOfVisibleDescription);
		
		String shortDescription = getText(lastCardShortdescription);
		
		validateTheActualValueWithExpectedValue(shortDescription, expected);
		
		return this;
			
	}
	
	public StorePage clickStoreCardImage(String storeName, String deviceName) {

		reportStep("About to click store card image in store category page", "INFO");
		
		String storeCardImage = "//android.widget.TextView[@text='"+storeName+"']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.view.ViewGroup[@content-desc='img_storeCard_imageValue']";		

		deviceName = deviceName.toLowerCase();

		switch (deviceName) {

		case "samsung":

			reportStep("Started scrolling for samsung device", "INFO");

			scrollTillRequiredElementIsVisible(storeCardImage);
			
			break;

		case "redmi":

			reportStep("Started scrolling for redmi device", "INFO");

			scrollTillRequiredElementIsVisible(storeCardImage);
			
			break;

		case "moto":

			reportStep("Started scrolling for moto device", "INFO");

			scrollTillRequiredElementIsVisibleFromDownToUp_noFourSec(storeCardImage);
			
			break;

		}
		
		if(click(driver.findElement(By.xpath(storeCardImage)))){
			
			reportStep("Successfully clicked image using "+shortDesc+"", "PASS");
			
		}else {
			
			reportStep("Failed to click image using "+shortDesc+"", "FAIL");
		}
		
		return new StorePage(driver, testInfo);
			
	}

	public StorePage clickRecommendedStoreCardImage(String storeName, String deviceName) {

		reportStep("About to click store card image in store category page", "INFO");
		
		String storeCardImage = "//android.widget.TextView[@text='"+storeName+"']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView[@content-desc='img_storeCategory_recommendedCard_image']";		

		deviceName = deviceName.toLowerCase();

		switch (deviceName) {

		case "samsung":

			reportStep("Started scrolling for samsung device", "INFO");

			scrollTillRequiredElementIsVisible(storeCardImage);
			
			break;

		case "redmi":

			reportStep("Started scrolling for redmi device", "INFO");

			scrollTillRequiredElementIsVisible(storeCardImage);
			
			break;

		case "moto":

			reportStep("Started scrolling for moto device", "INFO");

			scrollTillRequiredElementIsVisibleFromDownToUp_noFourSec(storeCardImage);
			
			break;

		}
		
		if(click(driver.findElement(By.xpath(storeCardImage)))){
			
			reportStep("Successfully clicked image using "+shortDesc+"", "PASS");
			
		}else {
			
			reportStep("Failed to click image using "+shortDesc+"", "FAIL");
		}
		
		return new StorePage(driver, testInfo);
			
	}

	public SignInPage clickStoreCardCTAButton(String ctaLabel, String deviceName) {

		reportStep("About to click store card CTA button in store category page", "INFO");
		
		String storeCardCTAButtonClick = "//android.widget.TextView[@text='"+ctaLabel+"']";		

		deviceName = deviceName.toLowerCase();

		switch (deviceName) {

		case "samsung":

			reportStep("Started scrolling for samsung device", "INFO");

			scrollTillRequiredElementIsVisible(storeCardCTAButtonClick);
			
			break;

		case "redmi":

			reportStep("Started scrolling for redmi device", "INFO");

			scrollTillRequiredElementIsVisible(storeCardCTAButtonClick);
			
			break;

		case "moto":

			reportStep("Started scrolling for moto device", "INFO");

			scrollTillRequiredElementIsVisibleFromDownToUp_noFourSec(storeCardCTAButtonClick);
			
			break;

		}
		
		if(click(driver.findElement(By.xpath(storeCardCTAButtonClick)))){
			
			reportStep("Successfully clicked "+ctaLabel+" in the store card", "PASS");
			
		}else {
			
			reportStep("Failed to click "+ctaLabel+" in the store card", "FAIL");
		}
		
		return new SignInPage(driver, testInfo);
			
	}
	
	public StorePage clickStoreCardViewAllOffersLink(String shortDesc, String viewAllOffers, String deviceName) {

		reportStep("About to click store card view all offers link in store category page", "INFO");
		
		String storeCardViewAllOffersClick = "//android.widget.TextView[@text='"+shortDesc+"']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+viewAllOffers+"')]|//android.widget.TextView[@text='"+shortDesc+"']/parent::android.view.View/following-sibling::android.view.View/android.widget.TextView[contains(@text,'"+viewAllOffers+"')]";		

		deviceName = deviceName.toLowerCase();

		switch (deviceName) {

		case "samsung":

			reportStep("Started scrolling for samsung device", "INFO");

			scrollTillRequiredElementIsVisible(storeCardViewAllOffersClick);
			
			break;

		case "redmi":

			reportStep("Started scrolling for redmi device", "INFO");

			scrollTillRequiredElementIsVisible(storeCardViewAllOffersClick);
			
			break;

		case "moto":

			reportStep("Started scrolling for moto device", "INFO");

			scrollTillRequiredElementIsVisibleFromDownToUp_noFourSec(storeCardViewAllOffersClick);
			
			break;

		}
		
		if(click(driver.findElement(By.xpath(storeCardViewAllOffersClick)))){
			
			reportStep("Successfully clicked "+viewAllOffers+" in the store card", "PASS");
			
		}else {
			
			reportStep("Failed to click "+viewAllOffers+" in the store card", "FAIL");
		}
		
		return new StorePage(driver, testInfo);
			
	}

	public StorePage clickStoreCardRecommendedStoresViewAllOffersLink(String shortDesc, String viewAllOffers, String deviceName) {

		reportStep("About to click store card view all offers link in store category page", "INFO");
		
		String storeCardViewAllOffersClick = "//android.widget.TextView[@text='"+shortDesc+"']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView[contains(@text,'"+viewAllOffers+"')]";		

		deviceName = deviceName.toLowerCase();

		switch (deviceName) {

		case "samsung":

			reportStep("Started scrolling for samsung device", "INFO");

			scrollTillRequiredElementIsVisible(storeCardViewAllOffersClick);
			
			break;

		case "redmi":

			reportStep("Started scrolling for redmi device", "INFO");

			scrollTillRequiredElementIsVisible(storeCardViewAllOffersClick);
			
			break;

		case "moto":

			reportStep("Started scrolling for moto device", "INFO");

			scrollTillRequiredElementIsVisibleFromDownToUp_noFourSec(storeCardViewAllOffersClick);
			
			break;

		}
		
		if(click(driver.findElement(By.xpath(storeCardViewAllOffersClick)))){
			
			reportStep("Successfully clicked "+viewAllOffers+" in the store card", "PASS");
			
		}else {
			
			reportStep("Failed to click "+viewAllOffers+" in the store card", "FAIL");
		}
		
		return new StorePage(driver, testInfo);
			
	}
	
	public IntermediateRetailerPage verifyRedirectedToIntermediatePageAfterClickingStoreCardCTAButtonAsASignedUser(String ctaLabel, String deviceName) {

		reportStep("About to click store card CTA button or view all offers link in store category page", "INFO");
		
		String storeCardCTAButtonClick = "//android.widget.TextView[@text='"+ctaLabel+"']";		
		
		deviceName = deviceName.toLowerCase();

		switch (deviceName) {

		case "samsung":

			reportStep("Started scrolling for samsung device", "INFO");

			scrollTillRequiredElementIsVisible(storeCardCTAButtonClick);
			
			break;

		case "redmi":

			reportStep("Started scrolling for redmi device", "INFO");

			scrollTillRequiredElementIsVisible(storeCardCTAButtonClick);
			
			break;

		case "moto":

			reportStep("Started scrolling for moto device", "INFO");

			scrollTillRequiredElementIsVisibleFromDownToUp_noFourSec(storeCardCTAButtonClick);
			
			break;

		}
		
		if(click(driver.findElement(By.xpath(storeCardCTAButtonClick)))){
			
			reportStep("Successfully clicked "+ctaLabel+" in the store card", "PASS");
			
		}else {
			
			reportStep("Failed to click "+ctaLabel+" in the store card", "FAIL");
		}
		
		return new IntermediateRetailerPage(driver, testInfo);
			
	}

	public int verifyExtractCountFromBannerTitle() {

		reportStep("About to extract banner title count", "INFO");
		
		validateTheElementPresence(automationSpecificBannerTile);
		String bannerTitle = getText(automationSpecificBannerTile);
		
		String bannerTitleCount = StringUtils.substringBetween(bannerTitle, getTestData(7, "startBannertitle"), getTestData(7, "endBannertitle"));
		int bannerCount = Integer.parseInt(bannerTitleCount);

		reportStep("Successfully extracted banner title count", "INFO");
		
		return bannerCount;
			
	}

	public StoreCategoryPage verifyStoreCardViewAllOffersAbsence(String shortDesc, String viewAllOffers) {

		reportStep("About to click store card view all offers link in store category page", "INFO");
		

		String shortDescription = "//android.widget.TextView[@text='"+shortDesc.trim()+"']";
		scrollTillRequiredElementIsVisible(shortDescription);
		
		String storeCardViewAllOffersClick = "//android.widget.TextView[@text='"+shortDesc+"']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView[contains(@text,'"+viewAllOffers+"')]";				
		validateTheElementAbsence(storeCardViewAllOffersClick);
		
		return this;
			
	}

	public StorePage clickAndVerifyStoreCardShortDescription(String shortDesc, String deviceName) {

		reportStep("About to click store card short description in store category page", "INFO");
		
		String shortDescription = "//android.widget.TextView[@text='"+shortDesc.trim()+"']";

		deviceName = deviceName.toLowerCase();

		switch (deviceName) {

		case "samsung":

			reportStep("Started scrolling for samsung device", "INFO");

			scrollTillRequiredElementIsVisible(shortDescription);
			
			break;

		case "redmi":

			reportStep("Started scrolling for redmi device", "INFO");

			scrollTillRequiredElementIsVisible(shortDescription);
			
			break;

		case "moto":

			reportStep("Started scrolling for moto device", "INFO");

			scrollTillRequiredElementIsVisibleFromDownToUp_noFourSec(shortDescription);
			
			break;

		}
		
		if(click(driver.findElement(By.xpath(shortDescription)))){
			
			reportStep("Successfully clicked short description "+shortDesc+"", "PASS");
			
		}else {
			
			reportStep("Failed to click short description "+shortDesc+"", "FAIL");
		}
		
		return new StorePage(driver, testInfo);
			
	}

	public String clickCashbackRateOrRewardsRateAndTermsThenReturnTypeOfStore() {

		reportStep("About to click Cashback Or Rewards & Terms  ", "INFO");
		
		scrollFromDownToUpinApp();
		
		String strCashbackRate = cashbackOrRewardsRatesAndTerms.getText();
		if(click(cashbackOrRewardsRatesAndTerms)) {

			reportStep("Successfully clicked on Cashback Or Rewards Rates and Terms and Conditaion ", "PASS");
		} else {

			reportStep("Failed to  click on Cashback Or Rewards Rates and Terms and Conditaion ", "FAIL");
		}

		return strCashbackRate;
	}

	public StorePage clickOnStoreCardImage() {

		reportStep("About to click on Store Card Image ", "INFO");

		if(clickAfterWait(searchResultsInStoresImage)) {

			reportStep("Successfully clicked on the store Card Image ", "PASS");

		} else {

			reportStep("Fail to click on the store Card Image ","FAIL");
		}

		return new StorePage(driver, testInfo);
	}

	
	
	
	
	
	
	
	
	
	}
