package com.ck.pages;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.ck.base.AppDynamicVariables;
import com.app.ck.base.WrapperMethods;
import com.app.ck.utilities.Utilities;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SearchPage extends WrapperMethods {

	//Constructor call
	public SearchPage(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);


		reportStep("Waiting for the search page", "INFO");
		try {
			wait.until(ExpectedConditions.visibilityOf(searchbar));
			reportStep("Successfully landed on the Search page ", "PASS");

		}catch(Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the search page ", "FAIL");
		}

	}

	//Variable initialization
	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@text='What do you want to buy today?']") 
	MobileElement searchbar;

	@FindBy(how = How.XPATH, using = "(//android.view.ViewGroup[@content-desc=\"btn_storeCard_button\"])[1]")
	MobileElement storeCardButton;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView[@content-desc='img_storeCard_imageValue'])[1]")
	MobileElement searchResutlsStoreCardImg;


	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"txt_searchResults_storeViewAll\"]")
	MobileElement ViewAllLink;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView[@content-desc=\"img_product_image\"])[1]")
	MobileElement searchResultsInProducts;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text= 'SEE DETAILS']")
	MobileElement voucherCardBtn_SeeDetails;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView[@content-desc='img_storeCard_imageValue'])[1]")
	MobileElement searchResultsInStoresImage;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"icon_search_goBack\"]") 
	MobileElement backButtonClick;

	@FindBy(how = How.XPATH, using = "(//android.widget.ImageView[@content-desc=\"img_couponCard_image\"])[1]")
	MobileElement voucherCardImg;
	@FindBy(how = How.XPATH, using = "(//android.view.ViewGroup[@content-desc=\"btn_couponCard_seeDetails\"])[1]")
	MobileElement voucherCardBtn;
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc=\"txt_storeCard_cshbackRatesTerms\"]")
	MobileElement cashbackOrRewardsRatesAndTerms ;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'GRAB DEAL')]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.TextView[@content-desc='txt_storeCard_noOffers']")
	MobileElement nocashbackOffers ;

	//Method creation

	public SearchPage enterTextIntoTheSearchBar() {

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("CB_Store_One");
		String searchItem = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");

		reportStep("Entering the text into the searchbar", "INFO");

		if(enterText(searchbar,searchItem)){

			reportStep("Successfully enter the text "+searchItem +  " on the searchbar  ", "PASS");

		}else {

			reportStep("Failed to enter the text "+searchItem +  " on the searchbar  ", "FAIL");
		}

		pressEnter();


		return this;

	}

	public SearchPage enterTextIntoTheSearchBar(String searchItem ) {

		reportStep("Entering the textValue " + searchItem + "  into the searchbar", "INFO");

		if(enterTextWithOutHideKeyboard(searchbar,searchItem)){

			reportStep("Successfully enter the text "+searchItem +  " on the searchbar  ", "PASS");

		}else {

			reportStep("Failed to enter the text "+searchItem +  " on the searchbar  ", "FAIL");
		}

		pressEnter();


		return this;
	}

	public SignInPage clickOnStoreCardButton() {

		reportStep("About to click on store card button", "INFO");

		if(click(storeCardButton)) {

			reportStep("Successfully clicked on the store card button", "PASS");
		}else {

			reportStep("Failed to click on the store card button", "FAIL");
		}

		return new SignInPage(driver, testInfo);
	}

	public SearchPage clickOnViewAll() {

		reportStep("About to click on the View all link on the search in results  page", "INFO");

		if(click(ViewAllLink)) {

			reportStep("Successfully clicked on the View all link on the search in store result page ", "PASS");
		}else {

			reportStep("Failed to click on the View all link on the search in store result page", "FAIL");
		}

		return this;
	}

	public ProductPage clickOnSearchResultsInProducts() {

		reportStep("About to click Search Results in Products ", "INFO");

		if(click(searchResultsInProducts)) {

			reportStep("Successfully clicked on the search results in Products ", "PASS");
		}else {

			reportStep("Failed to click on the searchResultsInProducts ", "FAIL");
		}

		return new ProductPage(driver, testInfo);
	}

	public VoucherDetailsPage clickOnSearchResultsInCoupons() {

		reportStep("About to click Search Results in Coupons ", "INFO");

		scrollFromDownToUpinApp();

		scrollFromDownToUpinApp();

		if(click(voucherCardBtn_SeeDetails)) {

			reportStep("Successfully clicked on the search results in Coupons ", "PASS");
		}else {

			reportStep("Failed to click on the searchResultsIn Coupons ", "FAIL");
		}

		return new VoucherDetailsPage(driver, testInfo);
	}

	public StorePage clickOnSearchedStoreImage() {

		reportStep("About to click on the searched store image at the search page ", "INFO");

		if(clickAfterWait(searchResultsInStoresImage)) {

			reportStep("Successfully clicked on the store searched image which navigate to the store page ", "PASS");

		}else {

			reportStep("Fail to click on the store searched at the search page ","FAIL");
		}

		return new StorePage(driver, testInfo);
	}

	public void validateInvisibilityOfStore(String storeName) {


		reportStep("About to click on the store image card bases on the store short description number", "INFO");
		storeName= storeName.substring(storeName.indexOf(":")+1);
		System.out.println(storeName);
		this.clickOnSearchedStoreCardImage();

		int intStoreSize = driver.findElementsByXPath("//android.widget.TextView[contains(@text,'description:"+storeName+"')]").size();

		if(intStoreSize==0) {

			reportStep("The store mapped for the Desktop/Mobile/Tablet is not applicable for App", "PASS");

		}else {

			reportStep("The store mapped for the Desktop/Mobile/Tablet is  applicable for App please look into this", "FAIL");
		}




	}

	public String getViewAllOffersVouchersCount(String storeNum) {

		String xpath = "//android.widget.TextView[contains(@text,'"+storeNum+"')]/../following-sibling::android.view.ViewGroup/android.widget.TextView[contains(@text,'View All Offers')]|//android.widget.TextView[contains(@text,'View All Offers (7)')]";

		isElementLocatedByXpathPresent(xpath);

		MobileElement ViewAllOffersLink = driver.findElementByXPath(xpath);

		String str = getText(ViewAllOffersLink);

		String onlyDigitFromTheString = Utilities.extractOnlyDigitFromString(str);

		reportStep("The voucher count in the store card is  :"+  onlyDigitFromTheString,"INFO");

		System.out.println("The voucher count in the store card is  : " + onlyDigitFromTheString);

		return onlyDigitFromTheString;

	}

	public SearchPage validateTheNumberOfVoucherCountInStoreCardff(String expected,String storeNum) {


		reportStep("About to validate the store card voucher count ", "INFO");

		String actual = getViewAllOffersVouchersCount(storeNum);

		validateTheActualValueWithExpectedValue(actual, expected);

		return this;



	}

	public RetailerCategoryPage clickBackButton() {

		reportStep("About to click on back button in search page", "INFO");

		if(click(backButtonClick)) {

			reportStep("Successfully clicked on the back button in search page", "PASS");
		}else {

			reportStep("Failed to  click on the back button in search page", "FAIL");
		}

		//	driver.navigate().back();

		return new RetailerCategoryPage(driver, testInfo);

	}

	public StorePage clickStoreCardShortDescription(String shortDesc) {

		reportStep("About to click store card short description in store page", "INFO");

		String shortDescription = "//android.widget.TextView[@text='"+shortDesc.trim()+"']";

		scrollTillRequiredElementIsVisible(shortDescription);

		if(click(driver.findElement(By.xpath(shortDescription)))){

			reportStep("Successfully clicked short description "+shortDesc+"", "PASS");

		}else {

			reportStep("Failed to click short description "+shortDesc+"", "FAIL");
		}

		return new StorePage(driver, testInfo);

	}

	public StorePage clickOnSearchedStoreCardImage() {

		reportStep("About to click on Store Card image in Search Results Page", "INFO");

		if(click(searchResutlsStoreCardImg)) {

			reportStep("Successfully clicked on the search results store card image", "PASS");
		}else {

			reportStep("Failed to click on searchResutlsStoreCardImg ", "FAIL");
		}

		return new StorePage(driver, testInfo);
	}


	public VoucherDetailsPage clickOnVoucherWithCodeCardImage(String storeName) {

		reportStep("about to click on Voucher With Code - Voucher Card Image ", "INFO");

		String xpath = "//android.widget.TextView[@text='Voucher With code for :"+storeName+"']";

		if(clickByXpath(xpath)){

			reportStep("Successfully clicked on the Voucher Card Image - Voucher With Code ", "PASS");

		} else {

			reportStep("Failed to click on Voucher Card Image - Voucher With Code ", "FAIL");
		}

		return new VoucherDetailsPage(driver, testInfo);
	}

	public VoucherDetailsPage clickOnVoucherWithOutCodeCardImage(String storeName) {

		String xpath = "//android.widget.TextView[@text='Voucher With Out code for :"+storeName+"']";
		reportStep("About to click on the voucher Card Image ", "INFO");

		try {

			isElementLocatedByXpathPresent(xpath);
			clickByXpath(xpath);
			reportStep("Successfully clicked On the Voucher Card Image - Voucher Without Code ", "PASS");

		} catch (Exception e) {

			reportStep("Failed to click On Voucher Card Image - Voucher Without Code ", "FAIL");

		}

		return new VoucherDetailsPage(driver, testInfo);
	}

	public SignInPage clickOnVoucherCardButtonForUnsignedUser() {

		reportStep("About to click on the voucher Card Button ", "INFO");

		if(click(voucherCardBtn)) {

			reportStep("Successfully clicked on the Voucher Card Button ", "PASS");

		} else {

			reportStep("Failed to click on Voucher Card Image ", "FAIL");
		}

		return new SignInPage(driver, testInfo);
	}

	public IntermediateRetailerPage clickOnVoucherCardButtonForSignedUser() {

		reportStep("About to click on the voucher Card Button ", "INFO");

		if(click(voucherCardBtn)) {

			reportStep("Successfully clicked on the Voucher Card Button ", "PASS");

		} else {

			reportStep("Failed to click on Voucher Card Image ", "FAIL");
		}

		return new IntermediateRetailerPage(driver, testInfo);
	}

	public void clickCashbackRateOrRewardsRate() {

		reportStep("About to click Cashback Or Rewards & Terms  ", "INFO");

		if(click(cashbackOrRewardsRatesAndTerms)) {

			reportStep("Successfully clicked on Cashback Or Rewards Rates and Terms and Conditaion ", "PASS");
		} else {

			reportStep("Failed to  click on Cashback Or Rewards Rates and Terms and Conditaion ", "FAIL");
		}

		
	}

	public void validateNoCashbackOffersText() {

		reportStep("About to validate the presence of no cashback offer text", "INFO");

		validateTheElementPresence(nocashbackOffers);
		
		reportStep("Validated the presence of no cashback offer text", "PASS");
		
	}


}
