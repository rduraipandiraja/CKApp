package com.ck.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.app.ck.base.AppDynamicVariables;
import com.app.ck.base.WrapperMethods;
import com.ck.pages.CashbackRewardsRateAndTerms;
import com.ck.pages.HomePage;
import com.ck.pages.SeeVideoToEarnCashbackPage;

import io.appium.java_client.clipboard.HasClipboard;

public class StoreVoucherEnhancements extends WrapperMethods {
	
	
	@Test
	public void validateStorePageForCashbackStore() {


		reportStep("Validating the CashbackStorePage ", "INFO");
		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Eleven("CB_Store_Eleven");
		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_Eleven_Name");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeName).
		clickOnSearchedStoreCardImage().
		validateWhatIsCashKaroCashbackLnkText().
		clickWhatIsCashKaroCashback().
		validateRetailerPayUs().
		validateWatchVideoBtnLink().
		clickCloseBtnOnWhatisCashKaroCBOrRWPopup().
		validateSeeMoreOffersBtnTxt().
		clickMoreOffersBtn().
		validateExistingUserText().
		validateNewUsersText().
		validateCategory().
		validateVoucherCountLabelInStorePage(storeName, 7).
		validateVoucherCardTitleTextInStorePage();

	}

	@Test
	public void validateStorePageForRewardsStore() {


		reportStep("Validating the RewardsStorePage ", "INFO");
		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Eleven("RW_Store_Eleven");

		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_Eleven_Name");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeName).
		clickOnSearchedStoreCardImage().
		validateWhatAreCashKaroRewardsLnkText().
		clickWhatAreCashKaroRewards().
		validateRetailerPayUs().
		validateWatchVideoBtnLink().
		clickCloseBtnOnWhatisCashKaroCBOrRWPopup().
		validateSeeMoreOffersBtnTxt().
		clickMoreOffersBtn().
		validateExistingUserText().
		validateNewUsersText().
		validateCategory().
		validateVoucherCountLabelInStorePage(storeName, 7).
		validateVoucherCardTitleTextInStorePage();

	}

	@Test
	public void validateStorePageForNetwork18Store() {


		reportStep("Validating the Network 18 store ", "INFO");
		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Eleven("N18_Store_Eleven");

		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_Eleven_Name");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeName).
		clickOnSearchedStoreCardImage().
		validateN18NoCashbackText().
		validateSeeMoreOffersBtnTxt().
		clickMoreOffersBtn().
		validateExistingUserText().
		validateNewUsersText().
		validateCategory().
		validateVoucherCountLabelInStorePage(storeName, 7).
		validateVoucherCardTitleTextInStorePage();


	}
	
	@Test
	public void validateVoucherDetailsPageWhenStoreIsCashbackAndVoucherIsWithoutCouponCode() {

		reportStep("Validating voucher details page when store is cashback type and voucher is not having coupon ", "INFO");
		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("CB_Store_One");
		String storeType = "cashback";
		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
		String[] storeDigits = storeName.split(":");
		String searchIt = storeDigits[storeDigits.length-1];

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(searchIt).
		clickOnVoucherWithOutCodeCardImage(storeName).
		validateVoucherLogo().
		validatevoucherShortDescForVoucherWithCode(storeName).
		validateVoucherDetailsPageMainCTA(storeType).
		validateOffersDescription().
		validateOffersTerms().
		clickToSeeMoreOffersBtn().
		validateStoreName(storeName).
		clickBackButtonToVoucherDetailsPage();
		
		
	}

	@Test
	public void validateVoucherDetailsPageWhenStoreIsRewardsAndVoucherIsWithoutCouponCode() {

		reportStep("Validating voucher details page when store is rewards type and voucher is not having coupon ", "INFO");
		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("RW_Store_One");
		String storeType = "rewards";
		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
		String[] storeDigits = storeName.split(":");
		String searchIt = storeDigits[storeDigits.length-1];

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(searchIt).
		clickOnVoucherWithOutCodeCardImage(storeName).
		validateVoucherLogo().
		validatevoucherShortDescForVoucherWithCode(storeName).
		validateVoucherDetailsPageMainCTA(storeType).
		validateOffersDescription().
		validateOffersTerms().
		clickToSeeMoreOffersBtn().
		validateStoreName(storeName).
		clickBackButtonToVoucherDetailsPage();
		
	}

	@Test
	public void validateVoucherDetailsPageWhenStoreIsN18AndVoucherIsWithoutCouponCode() {
		
		reportStep("Validating voucher details page when store is Network18 type and voucher is not having coupon ", "INFO");
		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("N18_Store_One");
		String storeType = "rewards";
		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
		String[] storeDigits = storeName.split(":");
		String searchIt = storeDigits[storeDigits.length-1];


		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(searchIt).
		clickOnVoucherWithOutCodeCardImage(storeName).
		validateVoucherLogo().
		validatevoucherShortDescForVoucherWithCode(storeName).
		validateVoucherDetailsPageMainCTA(storeType).
		validateOffersDescription().
		validateOffersTerms().
		clickToSeeMoreOffersBtn().
		validateStoreName(storeName).
		clickBackButtonToVoucherDetailsPage();
		
	}
	
	@Test
	public void validateVoucherDetailsPageWhenStoreIsCashbackAndVoucherIsWithCouponCode() {

		reportStep("Validating voucher details page when store is cashback type and voucher is  having coupon ", "INFO");
		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("CB_Store_One");
		String storeType = "cashback";
		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
		String[] storeDigits = storeName.split(":");
		String searchIt = storeDigits[storeDigits.length-1];

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(searchIt).
		clickOnVoucherWithCodeCardImage(storeName).
		validateAndthenClickCouponCodeText().
		validateCopyCode().
		validateVoucherLogo().
		validatevoucherShortDescForVoucherWithCode(storeName).
		validateVoucherDetailsPageMainCTA(storeType).
		validateOffersDescription().
		validateOffersTerms().
		clickToSeeMoreOffersBtn().
		validateStoreName(storeName).
		clickBackButtonToVoucherDetailsPage();
		
		String clipBoradValue= ((HasClipboard) driver).getClipboardText();
		
		//Assert the COUPON CODE WITH ACTUAL COPIED VALUE
		try {
		Assert.assertEquals(clipBoradValue, "COUPON CODE"); 
		}catch (Exception e) {
			reportStep("Assertion failed for copied code ", "FAIL");
		}
		
	}

	@Test
	public void validateVoucherDetailsPageWhenStoreIsRewardsAndVoucherIsWithCouponCode() {

		reportStep("Validating the voucher Details page for the rewards store", "INFO");
		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("RW_Store_One");
		String storeType = "rewards";
		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
		String[] storeDigits = storeName.split(":");
		String searchIt = storeDigits[storeDigits.length-1];

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(searchIt).
		clickOnVoucherWithCodeCardImage(storeName).
		validateVoucherLogo().
		validateAndthenClickCouponCodeText().
		validateCopyCode().
		validatevoucherShortDescForVoucherWithCode(storeName).
		validateVoucherDetailsPageMainCTA(storeType).
		validateOffersDescription().
		validateOffersTerms().
		clickToSeeMoreOffersBtn().
		validateStoreName(storeName).
		clickBackButtonToVoucherDetailsPage();
		
		String clipBoradValue= ((HasClipboard) driver).getClipboardText();
		//Assert the COUPON CODE WITH ACTUAL COPIED VALUE
		try {
		Assert.assertEquals(clipBoradValue, "COUPON CODE"); 
		}catch (Exception e) {
			reportStep("Assertion failed for copied code ", "FAIL");
		}
		
	}

	@Test
	public void validateVoucherDetailsPageWhenStoreIsNetworkEighteenAndVoucherIsWithCouponCode() {
		
		reportStep("Validating the voucher Details page for the N18 store", "INFO");
		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("N18_Store_One");
		String storeType = "rewards";
		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
		String[] storeDigits = storeName.split(":");
		String searchIt = storeDigits[storeDigits.length-1];


		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(searchIt).
		clickOnVoucherWithCodeCardImage(storeName).
		validateVoucherLogo().
		validateAndthenClickCouponCodeText().
		validateCopyCode().
		validatevoucherShortDescForVoucherWithCode(storeName).
		validateVoucherDetailsPageMainCTA(storeType).
		validateOffersDescription().
		validateOffersTerms().
		clickToSeeMoreOffersBtn().
		validateStoreName(storeName).
		clickBackButtonToVoucherDetailsPage();
		
		
		String clipBoradValue= ((HasClipboard) driver).getClipboardText();
		//Assert the COUPON CODE WITH ACTUAL COPIED VALUE
		try {
		Assert.assertEquals(clipBoradValue, "COUPON CODE"); 
		}catch (Exception e) {
			reportStep("Assertion failed for copied code ", "FAIL");
		}
		

	}
	
	@Test
	public void homepageStoreCardRatesTermsLinkValidation() {

		reportStep("Validating homepage StoreCard CashbackRates Link", "INFO");

		String cashbackType = 
				new SeeVideoToEarnCashbackPage(driver,testInfo).
				clickOnGoToHomePage().
				clickCashbackRateOrRewardsRateAndTermsThenReturnTypeOfStore();
		
		new CashbackRewardsRateAndTerms(driver,testInfo).
		validateCashbackRateOrRewardsRateAndTerms(cashbackType);
		

	}

	/*Normal primary cashback and normal secondary cashback - percentage*/
	@Test
	public void searchpageCashbackStoreCardRatesTermsLinkValidation_TC001() {

		reportStep("Validating storeCategorypage StoreCard CashbackRates Link", "INFO");

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("CB_Store_One");

		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_One_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_One_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_One_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_One_Secondary_Cashback_Details");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeName).
		clickCashbackRateOrRewardsRate();
		
		new CashbackRewardsRateAndTerms(driver,testInfo).
		validateCashbackRateOrRewardsRateAndTerms(testdata.get(7).get("Cashback")).
		validateNormalPrimaryCashbackRateInPercentage(storeName, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeName, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInPercentage(storeName, secondaryCashbackRateValue, 1).
		validateNormalSecondaryCashbackRate_Details(storeName, secondaryCashbackRateDetails, 1).
		validateOfferTerms().
		clickCloseBtnOnPopup();
		

	}

	/*Normal primary cashback and normal secondary cashback - percentage*/
	@Test
	public void searchpageRewardsStoreCardRatesTermsLinkValidation_TC002() {

		reportStep("Validating storeCategorypage StoreCard CashbackRates Link", "INFO");

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("RW_Store_One");

		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_One_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_One_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_One_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_One_Secondary_Cashback_Details");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeName).
		clickCashbackRateOrRewardsRate();
		
		new CashbackRewardsRateAndTerms(driver,testInfo).
		validateCashbackRateOrRewardsRateAndTerms(testdata.get(7).get("Rewards")).
		validateNormalPrimaryCashbackRateInPercentage(storeName, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeName, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInPercentage(storeName, secondaryCashbackRateValue, 1).
		validateNormalSecondaryCashbackRate_Details(storeName, secondaryCashbackRateDetails, 1).
		validateOfferTerms().
		clickCloseBtnOnPopup();
		

	}
	
	/*Calender primary cashback and calender secondary cashback - percentage*/
	@Test
	public void searchpageCashbackStoreCardRatesTermsLinkValidation_TC003() {

		reportStep("Validating storeCategorypage StoreCard CashbackRates Link", "INFO");

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Two("CB_Store_Two");

		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_Two_Name");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Two_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Two_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Two_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Two_Secondary_Cashback_Details");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeName).
		clickCashbackRateOrRewardsRate();
		
		new CashbackRewardsRateAndTerms(driver,testInfo).
		validateCashbackRateOrRewardsRateAndTerms(testdata.get(7).get("Cashback")).
		validateNormalPrimaryCashbackRateInPercentage(storeName, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeName, primaryCashbackRateDetails).
		validateThePrimaryCashbackExpiryTag(0, getTestData(4, "PrimaryCashbackExpiryTag"), storeName).
		validateNormalSecondaryCashbackRateInPercentage(storeName, secondaryCashbackRateValue, 1).
		validateNormalSecondaryCashbackRate_Details(storeName, secondaryCashbackRateDetails, 1).
		validateTheSecondaryCashbackExpiryTag(0, getTestData(4, "SecondaryCashbackExpiryTag"), storeName).
		validateOfferTerms().
		clickCloseBtnOnPopup();

	}

	/*Calender primary cashback and calender secondary cashback - percentage*/
	@Test
	public void searchpageRewardsStoreCardRatesTermsLinkValidation_TC004() {

		reportStep("Validating storeCategorypage StoreCard CashbackRates Link", "INFO");

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Two("RW_Store_Two");

		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_Two_Name");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Two_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Two_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Two_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Two_Secondary_Cashback_Details");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeName).
		clickCashbackRateOrRewardsRate();
		
		new CashbackRewardsRateAndTerms(driver,testInfo).
		validateCashbackRateOrRewardsRateAndTerms(testdata.get(7).get("Rewards")).
		validateNormalPrimaryCashbackRateInPercentage(storeName, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeName, primaryCashbackRateDetails).
		validateThePrimaryCashbackExpiryTag(0, getTestData(4, "PrimaryCashbackExpiryTag"), storeName).
		validateNormalSecondaryCashbackRateInPercentage(storeName, secondaryCashbackRateValue, 1).
		validateNormalSecondaryCashbackRate_Details(storeName, secondaryCashbackRateDetails, 1).
		validateTheSecondaryCashbackExpiryTag(0, getTestData(4, "SecondaryCashbackExpiryTag"), storeName).
		validateOfferTerms().
		clickCloseBtnOnPopup();
		

	}

	/*Normal primary cashback and normal secondary cashback - currency*/
	@Test
	public void searchpageCashbackStoreCardRatesTermsLinkValidation_TC005() {

		reportStep("Validating storeCategorypage StoreCard CashbackRates Link", "INFO");

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);		
		
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Eight("CB_Store_Eight");

		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_Eight_Name");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Eight_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Eight_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Eight_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Eight_Secondary_Cashback_Details");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeName).
		clickCashbackRateOrRewardsRate();
		
		new CashbackRewardsRateAndTerms(driver,testInfo).
		validateCashbackRateOrRewardsRateAndTerms(testdata.get(7).get("Cashback")).
		validateNormalPrimaryCashbackRateInRupees(storeName, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeName, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInRupees(storeName, secondaryCashbackRateValue, 1).
		validateNormalSecondaryCashbackRate_Details(storeName, secondaryCashbackRateDetails, 1).
		validateOfferTerms().
		clickCloseBtnOnPopup();
		

	}

	/*Normal primary cashback and normal secondary cashback - currency*/
	@Test
	public void searchpageCashbackStoreCardRatesTermsLinkValidation_TC006() {

		reportStep("Validating storeCategorypage StoreCard CashbackRates Link", "INFO");

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);		
		
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Eight("RW_Store_Eight");

		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_Eight_Name");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Eight_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Eight_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Eight_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Eight_Secondary_Cashback_Details");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeName).
		clickCashbackRateOrRewardsRate();
		
		new CashbackRewardsRateAndTerms(driver,testInfo).
		validateCashbackRateOrRewardsRateAndTerms(testdata.get(7).get("Rewards")).
		validateNormalPrimaryCashbackRateInRupees(storeName, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeName, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInRupees(storeName, secondaryCashbackRateValue, 1).
		validateNormalSecondaryCashbackRate_Details(storeName, secondaryCashbackRateDetails, 1).
		validateOfferTerms().
		clickCloseBtnOnPopup();
		

	}
	
	/*Calender primary cashback and calender secondary cashback - currency*/
	@Test
	public void searchpageCashbackStoreCardRatesTermsLinkValidation_TC007() {

		reportStep("Validating storeCategorypage StoreCard CashbackRates Link", "INFO");

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Nine("CB_Store_Nine");

		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_Nine_Name");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Nine_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Nine_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Nine_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Nine_Secondary_Cashback_Details");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeName).
		clickCashbackRateOrRewardsRate();
		
		new CashbackRewardsRateAndTerms(driver,testInfo).
		validateCashbackRateOrRewardsRateAndTerms(testdata.get(7).get("Cashback")).
		validateNormalPrimaryCashbackRateInPercentage(storeName, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeName, primaryCashbackRateDetails).
		validateThePrimaryCashbackExpiryTag(0, getTestData(4, "PrimaryCashbackExpiryTag"), storeName).
		validateNormalSecondaryCashbackRateInRupees(storeName, secondaryCashbackRateValue, 1).
		validateNormalSecondaryCashbackRate_Details(storeName, secondaryCashbackRateDetails, 1).
		validateTheSecondaryCashbackExpiryTag(0, getTestData(4, "SecondaryCashbackExpiryTag"), storeName).
		validateNormalSecondaryCashbackRateInRupees(storeName, "", 2).
		validateNormalSecondaryCashbackRate_Details(storeName, "", 2).
		validateTheSecondaryCashbackExpiryTag(1, getTestData(4, "SecondaryCashbackExpiryTag"), storeName).
		validateOfferTerms().
		clickCloseBtnOnPopup();
		

	}

	/*Calender primary cashback and calender secondary cashback - currency*/
	@Test
	public void searchpageCashbackStoreCardRatesTermsLinkValidation_TC008() {

		reportStep("Validating storeCategorypage StoreCard CashbackRates Link", "INFO");

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Nine("RW_Store_Nine");

		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_Nine_Name");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Nine_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Nine_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Nine_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Nine_Secondary_Cashback_Details");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeName).
		clickCashbackRateOrRewardsRate();
		
		new CashbackRewardsRateAndTerms(driver,testInfo).
		validateCashbackRateOrRewardsRateAndTerms(testdata.get(7).get("Rewards")).
		validateNormalPrimaryCashbackRateInPercentage(storeName, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeName, primaryCashbackRateDetails).
		validateThePrimaryCashbackExpiryTag(0, getTestData(4, "PrimaryCashbackExpiryTag"), storeName).
		validateNormalSecondaryCashbackRateInPercentage(storeName, secondaryCashbackRateValue, 1).
		validateNormalSecondaryCashbackRate_Details(storeName, secondaryCashbackRateDetails, 1).
		validateTheSecondaryCashbackExpiryTag(0, getTestData(4, "SecondaryCashbackExpiryTag"), storeName).
		validateNormalSecondaryCashbackRateInPercentage(storeName, "", 2).
		validateNormalSecondaryCashbackRate_Details(storeName, "", 2).
		validateTheSecondaryCashbackExpiryTag(1, getTestData(4, "SecondaryCashbackExpiryTag"), storeName).
		validateOfferTerms().
		clickCloseBtnOnPopup();
		

	}

	/*Normal primary cashback with multiple normal secondary cashback - percentage*/
	@Test
	public void searchpageCashbackStoreCardRatesTermsLinkValidation_TC009() {

		reportStep("Validating storeCategorypage StoreCard CashbackRates Link", "INFO");

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Three("CB_Store_Three");

		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_Three_Name");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Three_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Three_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Three_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Three_Secondary_Cashback_Details");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeName).
		clickCashbackRateOrRewardsRate();
		
		new CashbackRewardsRateAndTerms(driver,testInfo).
		validateCashbackRateOrRewardsRateAndTerms(testdata.get(7).get("Cashback")).
		validateNormalPrimaryCashbackRateInPercentage(storeName, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeName, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInPercentage(storeName, secondaryCashbackRateValue, 1).
		validateNormalSecondaryCashbackRate_Details(storeName, secondaryCashbackRateDetails, 1).
		validateNormalSecondaryCashbackRateInPercentage(storeName, secondaryCashbackRateValue, 2).
		validateNormalSecondaryCashbackRate_Details(storeName, secondaryCashbackRateValue, 2).
		validateOfferTerms().
		clickCloseBtnOnPopup();
		

	}

	/*Normal primary cashback and multiple normal secondary cashback - percentage*/
	@Test
	public void searchpageRewardsStoreCardRatesTermsLinkValidation_TC010() {

		reportStep("Validating storeCategorypage StoreCard CashbackRates Link", "INFO");

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_Three("RW_Store_Three");

		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_Three_Name");
		String primaryCashbackRateValue = objAppiumVariables.getRequiredPrimaryCashbackValue("str_Store_Three_Primary_Cashback_Value");
		String primaryCashbackRateDetails = objAppiumVariables.getRequiredPrimaryCashbackDetails("str_Store_Three_Primary_Cashback_Details");
		String secondaryCashbackRateValue = objAppiumVariables.getRequiredSecondaryCashbackValue("str_Store_Three_Secondary_Cashback_Value");
		String secondaryCashbackRateDetails = objAppiumVariables.getRequiredSecondaryCashbackDetails("str_Store_Three_Secondary_Cashback_Details");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeName).
		clickCashbackRateOrRewardsRate();
		
		new CashbackRewardsRateAndTerms(driver,testInfo).
		validateCashbackRateOrRewardsRateAndTerms(testdata.get(7).get("Rewards")).
		validateNormalPrimaryCashbackRateInPercentage(storeName, primaryCashbackRateValue).
		validateNormalPrimaryCashbackRate_Details(storeName, primaryCashbackRateDetails).
		validateNormalSecondaryCashbackRateInPercentage(storeName, secondaryCashbackRateValue, 1).
		validateNormalSecondaryCashbackRate_Details(storeName, secondaryCashbackRateDetails, 1).
		validateNormalSecondaryCashbackRateInPercentage(storeName, secondaryCashbackRateValue, 2).
		validateNormalSecondaryCashbackRate_Details(storeName, secondaryCashbackRateValue, 2).
		validateOfferTerms().
		clickCloseBtnOnPopup();
		
	}

	/*No cashback Offer*/
	@Test
	public void searchpageRewardsStoreCardRatesTermsLinkValidation_TC011() {

		reportStep("Validating storeCategorypage StoreCard CashbackRates Link", "INFO");

		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("N18_Store_One");

		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeName).
		validateNoCashbackOffersText();
		
	}
	
	@Test
	public void validateStoreCardCashbackRewardsRateAndTermsInStoreCatPage() {
		
		reportStep("validate Store Category Page, Cashback Terms and Condition ", "INFO");
		
		
		String storeType = 
				new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnHamburgerIcon().
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		verifyAutomationSpecificSubCategoryNavigation().
		clickPopularTab(deviceName).
		clickCashbackRateOrRewardsRateAndTermsThenReturnTypeOfStore();
		
		storeType = new CashbackRewardsRateAndTerms(driver,testInfo).
		validateCashbackRateOrRewardsRateAndTerms(storeType).
		validateOfferTermsLabel().
		clickCloseBtnOnPopupThenValidateStoreCatPage().
		clickPercentTab(deviceName).
		clickCashbackRateOrRewardsRateAndTermsThenReturnTypeOfStore();
		
		new CashbackRewardsRateAndTerms(driver,testInfo).
		validateCashbackRateOrRewardsRateAndTerms(storeType).
		validateOfferTermsLabel().
		clickCloseBtnOnPopupThenValidateStoreCatPage();

		
	}

	@Test
	public void validateClickingStoreCardImageInHomePageIsNotRedirectingToStorePage() {

		reportStep("validate that clicking store image in home page should not redirect the user to store Page", "INFO");


		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnStoreCardImage();

		new HomePage(driver, testInfo);

	}

	@Test
	public void validateClickingStoreCardImgInSearchResultPage() {

		reportStep("validate that clicking on Store Card image in Search Results page should redirect to Store Page", "INFO");
		AppDynamicVariables objAppiumVariables = new AppDynamicVariables(driver,testInfo);
		AppDynamicVariables.extract_Values_From_Json_And_Load_Internal_Variables_Store_One("CB_Store_One");
		String storeName = objAppiumVariables.getRequiredStoreName("str_Store_One_Name");

		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnSearchIcon().
		enterTextIntoTheSearchBar(storeName).
		clickOnSearchedStoreImage();
	}
	
	@Test
	public void validateClickingStoreCardImgInStoreCatPage() {
		
		reportStep("validate that clicking on Store Card image in Store Category  page should redirect to Store Page", "INFO");
	
		new SeeVideoToEarnCashbackPage(driver,testInfo).
		clickOnGoToHomePage().
		clickOnHamburgerIcon().
		clickRetailerCategory().
		verifyRetailerCategoryMenuNavigation().
		clickAutomationSpecificCategoryInRetailerCategoryMenuUntilAllSubCategoryVisible().
		clickAutomationSpecificSubCategoryOne().
		verifyAutomationSpecificSubCategoryNavigation().
		clickOnStoreCardImage();

		
	}





}
