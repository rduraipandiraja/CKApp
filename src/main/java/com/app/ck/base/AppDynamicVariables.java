package com.app.ck.base;

import java.io.FileReader;
import javax.annotation.CheckForNull;

import org.apache.commons.logging.impl.Log4JLogger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebElement;

import com.app.ck.utilities.Utilities;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.diagnostics.logging.Logger;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import java.util.logging.Level; 
import java.util.logging.*; 

public class AppDynamicVariables extends WrapperMethods {

	static String  url = GetCodeSetup.URL ;

	public AppDynamicVariables(AppiumDriver<MobileElement> driver, ExtentTest testInfo) {

		this.driver = driver;
		this.testInfo = testInfo;

	}


	//*************************************************************************************************************************************************************************************//
	//*************************************************************************************************************************************************************************************//
	//*************************************************************************************************************************************************************************************//
	//** 											    	  		Stores - Variables Declaration starts here							   			     							 	 **//
	//*************************************************************************************************************************************************************************************//
	//*************************************************************************************************************************************************************************************//
	//*************************************************************************************************************************************************************************************//

	public static JSONParser ObjJparser = new JSONParser();

	// Store_One_ - Core details
	private static String str_Store_One_Name;
	private static String str_Store_One_Short_Desc_App;
	private static String str_Store_One_Primary_Cashback_Details;
	private static String str_Store_One_Primary_Cashback_Value;
	private static String str_Store_One_Secondary_Cashback_Details;
	private static String str_Store_One_Secondary_Cashback_Value;
	private static String str_Store_One_linkURL;
	private static String str_Store_One_Voucher_Two_CODE;
	private static String str_Store_One_Voucher_Two_Name;
	private static String str_Store_One_Final_Terms_condition;

	// Store_Two_ - Core details
	private static String str_Store_Two_Name;
	private static String str_Store_Two_Short_Desc_App;
	private static String str_Store_Two_Primary_Cashback_Value;
	private static String str_Store_Two_Primary_Cashback_Details;
	private static String str_Store_Two_Secondary_Cashback_Details;
	private static String str_Store_Two_Secondary_Cashback_Value;
	private static String str_Store_Two_Voucher_Two_CODE;
	private static String str_Store_Two_Voucher_Two_Name;
	private static String str_Store_Two_linkURL;

	// Store_Three - Core details
	private static String str_Store_Three_Name;
	private static String str_Store_Three_Primary_Cashback_Value;
	private static String str_Store_Three_Primary_Cashback_Details;
	private static String str_Store_Three_Secondary_Cashback_Value;
	private static String str_Store_Three_Secondary_Cashback_Details;
	private static String str_Store_Three_linkURL;

	// Store_Four - Core details
	private static String str_Store_Four_Name;

	// Store_Five - Core details
	private static String str_Store_Five_Name;

	// Store_Six - Core details
	private static String str_Store_Six_Name;
	private static String str_Store_Six_Primary_Cashback_Value;
	private static String str_Store_Six_Primary_Cashback_Details;
	private static String str_Store_Six_Secondary_Cashback_Value;
	private static String str_Store_Six_Secondary_Cashback_Details;

	// Store_Seven - Core details
	private static String str_Store_Seven_Name;
	private static String str_Store_Seven_Primary_Cashback_Value;
	private static String str_Store_Seven_Primary_Cashback_Details;
	private static String str_Store_Seven_Secondary_Cashback_Value;
	private static String str_Store_Seven_Secondary_Cashback_Details;

	// Store_Eight - Core details
	private static String str_Store_Eight_Name;
	private static String str_Store_Eight_Short_Desc_App;
	private static String str_Store_Eight_Primary_Cashback_Details;
	private static String str_Store_Eight_Primary_Cashback_Value;
	private static String str_Store_Eight_Secondary_Cashback_Details;
	private static String str_Store_Eight_Secondary_Cashback_Value;
	private static String str_Store_Eight_linkURL;
	private static String str_Store_Eight_Voucher_Two_CODE;
	private static String str_Store_Eight_Voucher_Two_Name;

	// Store_Nine_ - Core details
	private static String str_Store_Nine_Name;
	private static String str_Store_Nine_Short_Desc_App;
	private static String str_Store_Nine_Primary_Cashback_Details;
	private static String str_Store_Nine_Primary_Cashback_Value;
	private static String str_Store_Nine_Secondary_Cashback_Details;
	private static String str_Store_Nine_Secondary_Cashback_Value;
	private static String str_Store_Nine_Voucher_Two_CODE;
	private static String str_Store_Nine_Voucher_Two_Name;
	private static String str_Store_Nine_linkURL;

	// Store_Eleven - Core details
	private static String str_Store_Eleven_Name;  	

	// Store_Twelve - Core details
	private static String str_Store_Twelve_Name;

	// Store_Thirteen - Core details
	private static String str_Store_Thirteen_Name; 

	// Store_Fourteen - Core details
	private static String str_Store_Fourteen_Name; 	

	// Store_Twelve - Core details
	private static String str_Store_Fifteen_Name; 	
	private static String str_Store_Fifteen_Primary_Cashback_Details;
	private static String str_Store_Fifteen_Primary_Cashback_Value;
	private static String str_Store_Fifteen_Secondary_Cashback_Details;
	private static String str_Store_Fifteen_Secondary_Cashback_Value;

	//*************************************************************************************************************************************************************************************//
	//** 											    					 set Methods												   			    								 **//
	//*************************************************************************************************************************************************************************************//

	// extract_Values_From_Json_And_Load_Internal_Variables
	@CheckForNull
	public static String extract_Values_From_Json_And_Load_Internal_Variables_Store_One(String strStore_Name) {

		String strStatus_Of_Complete_Variables_Loading ="Passed";
		JSONObject objStoreSpecific = new JSONObject();

		try
		{
			// Creating the Json object to the entire json data which is retrived from API
			JSONObject objJsonObject 			= (JSONObject) ObjJparser.parse(new FileReader(System.getProperty("user.dir") + "/dynamic_json_test_data/Ck_Store_Creation_Json_Dynamic_Data_UI.json"));
			//this returns the Object(super most class) ,so down casting
			
			//Based on Store type , assigning the store specific object
			if(strStore_Name.contains("CB_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("CB_Store");
			} else if(strStore_Name.contains("RW_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("RW_Store");
			}else if(strStore_Name.contains("N18_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("N18_Store");
			}

			//Fetch the required value from the required store type
			JSONObject objMainNode 				= (JSONObject) objStoreSpecific.get(strStore_Name);

			/********************************************************************* Store -1 Details Assigning *********************************************************************************/

			/*  Normal Primary / Secondary Cash back with Percentage */
			// Store_One - Core details
			str_Store_One_Name  												=  objMainNode.get(strStore_Name+"_Name").toString().trim();
			str_Store_One_linkURL 												=  objMainNode.get(strStore_Name+"_linkURL").toString().trim();					
			str_Store_One_linkURL 												=  objMainNode.get(strStore_Name+"_linkURL").toString().trim();					
			str_Store_One_Short_Desc_App 										=  objMainNode.get(strStore_Name+"_Short_Desc_App").toString().trim(); 				
			str_Store_One_Primary_Cashback_Details 								=  objMainNode.get(strStore_Name+"_Primary_Cashback_Details").toString().trim(); 	 
			str_Store_One_Primary_Cashback_Value  					    		=  objMainNode.get(strStore_Name+"_Primary_Cashback_Value").toString().trim();
			str_Store_One_Secondary_Cashback_Details 							=  objMainNode.get(strStore_Name+"_Secondary_Cashback_Details").toString().trim();
			str_Store_One_Secondary_Cashback_Value  							=  objMainNode.get(strStore_Name+"_Secondary_Cashback_Value").toString().trim();
			str_Store_One_Voucher_Two_Name 										=  objMainNode.get(strStore_Name+"_Voucher_Two_Name").toString().trim();
			str_Store_One_Voucher_Two_CODE 										=  objMainNode.get(strStore_Name+"_Voucher_Two_CODE").toString().trim();
			str_Store_One_Final_Terms_condition									=  objMainNode.get(strStore_Name+"_Final_Terms_condition").toString().trim();


		}
		catch(Exception objError){

			strStatus_Of_Complete_Variables_Loading = "Failed";
			//objError.printStackTrace();
			
		}

		return strStatus_Of_Complete_Variables_Loading;


		/*************************************************************************************End of Stores Details Assigning*******************************************************************************/
	}

	// extract_Values_From_Json_And_Load_Internal_Variables
	public static String extract_Values_From_Json_And_Load_Internal_Variables_Store_Two(String strStore_Name) {

		String strStatus_Of_Complete_Variables_Loading ="Passed";
		JSONObject objStoreSpecific = new JSONObject();

		try
		{
			// Creating the Json object to the entire json data which is retrived from API
			JSONObject objJsonObject 			= (JSONObject) ObjJparser.parse(new FileReader(System.getProperty("user.dir") + "/dynamic_json_test_data/Ck_Store_Creation_Json_Dynamic_Data_UI.json"));//this returns the Object(super most class) ,so down casting
			
			//Based on Store type , assigning the store specific object
			if(strStore_Name.contains("CB_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("CB_Store");
			} else if(strStore_Name.contains("RW_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("RW_Store");
			}else if(strStore_Name.contains("N18_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("N18_Store");
			}

			//Fetch the required value from the required store type
			JSONObject objMainNode 				= (JSONObject) objStoreSpecific.get(strStore_Name);

			/********************************************************************* Store -2 Details Assigning *********************************************************************************/

			/*  Normal Primary / Secondary Cash back with Percentage */
			// Store_Two_ - Core details
			str_Store_Two_Name  												=  objMainNode.get(strStore_Name+"_Name").toString().trim();
			str_Store_Two_linkURL 												=  objMainNode.get(strStore_Name+"_linkURL").toString().trim();					
			str_Store_Two_Short_Desc_App 										=  objMainNode.get(strStore_Name+"_Short_Desc_App").toString().trim(); 				
			str_Store_Two_Primary_Cashback_Details 								= objMainNode.get(strStore_Name+"_Primary_Cashback_Details").toString().trim(); 	 
			str_Store_Two_Primary_Cashback_Value  					    		= objMainNode.get(strStore_Name+"_Primary_Cashback_Value").toString().trim();
			str_Store_Two_Secondary_Cashback_Details 							= objMainNode.get(strStore_Name+"_Secondary_Cashback_Details").toString().trim();
			str_Store_Two_Secondary_Cashback_Value  							= objMainNode.get(strStore_Name+"_Secondary_Cashback_Value").toString().trim();
			str_Store_Two_Voucher_Two_Name 										= objMainNode.get(strStore_Name+"_Voucher_Two_Name").toString().trim();
			str_Store_Two_Voucher_Two_CODE 										= objMainNode.get(strStore_Name+"_Voucher_Two_CODE").toString().trim();


		}
		catch(Exception objError){
			strStatus_Of_Complete_Variables_Loading = "Failed";
			////ExtentTestManager.getTest().log(LogStatus.FAIL, "Sorry,Internal Memory Variables are not loaded Properly,So Up coming dependant execution are going to be Skipped");
			//objError.printStackTrace();
		}

		return strStatus_Of_Complete_Variables_Loading;


		/*************************************************************************************End of Stores Details Assigning*******************************************************************************/
	}

	// extract_Values_From_Json_And_Load_Internal_Variables
	public static String extract_Values_From_Json_And_Load_Internal_Variables_Store_Three(String strStore_Name) {

		String strStatus_Of_Complete_Variables_Loading ="Passed";
		JSONObject objStoreSpecific = new JSONObject();

		try
		{
			// Creating the Json object to the entire json data which is retrived from API
			JSONObject objJsonObject 			= (JSONObject) ObjJparser.parse(new FileReader(System.getProperty("user.dir") + "/dynamic_json_test_data/Ck_Store_Creation_Json_Dynamic_Data_UI.json"));//this returns the Object(super most class) ,so down casting
			
			//Based on Store type , assigning the store specific object
			if(strStore_Name.contains("CB_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("CB_Store");
			} else if(strStore_Name.contains("RW_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("RW_Store");
			}else if(strStore_Name.contains("N18_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("N18_Store");
			}

			//Fetch the required value from the required store type
			JSONObject objMainNode 				= (JSONObject) objStoreSpecific.get(strStore_Name);

			/********************************************************************* Store -3 Details Assigning *********************************************************************************/

			/*  Normal Primary / Secondary Cash back with Percentage */
			// Store_Three - Core details
			str_Store_Three_Name  								=  objMainNode.get(strStore_Name+"_Name").toString().trim();	
			str_Store_Three_linkURL  							=  objMainNode.get(strStore_Name+"_linkURL").toString().trim();
			str_Store_Three_Primary_Cashback_Value  			=  objMainNode.get(strStore_Name+"_Primary_Cashback_Value").toString().trim();
			str_Store_Three_Primary_Cashback_Details  			=  objMainNode.get(strStore_Name+"_Primary_Cashback_Details").toString().trim();
			str_Store_Three_Secondary_Cashback_Value  			=  objMainNode.get(strStore_Name+"_Secondary_Cashback_Value").toString().trim();
			str_Store_Three_Secondary_Cashback_Details  		=  objMainNode.get(strStore_Name+"_Secondary_Cashback_Details").toString().trim();

		}
		catch(Exception objError){
			strStatus_Of_Complete_Variables_Loading = "Failed";
			////ExtentTestManager.getTest().log(LogStatus.FAIL, "Sorry,Internal Memory Variables are not loaded Properly,So Up coming dependant execution are going to be Skipped");
			//objError.printStackTrace();
		}

		return strStatus_Of_Complete_Variables_Loading;


		/*************************************************************************************End of Stores Details Assigning*******************************************************************************/
	}

	// extract_Values_From_Json_And_Load_Internal_Variables
	public static String extract_Values_From_Json_And_Load_Internal_Variables_Store_Four(String strStore_Name) {

		String strStatus_Of_Complete_Variables_Loading ="Passed";
		JSONObject objStoreSpecific = new JSONObject();

		try
		{
			// Creating the Json object to the entire json data which is retrived from API
			JSONObject objJsonObject 			= (JSONObject) ObjJparser.parse(new FileReader(System.getProperty("user.dir") + "/dynamic_json_test_data/Ck_Store_Creation_Json_Dynamic_Data_UI.json"));//this returns the Object(super most class) ,so down casting
			
			//Based on Store type , assigning the store specific object
			if(strStore_Name.contains("CB_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("CB_Store");
			} else if(strStore_Name.contains("RW_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("RW_Store");
			}else if(strStore_Name.contains("N18_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("N18_Store");
			}

			//Fetch the required value from the required store type
			JSONObject objMainNode 				= (JSONObject) objStoreSpecific.get(strStore_Name);

			/********************************************************************* Store -4 Details Assigning *********************************************************************************/

			/*  Normal Primary / Secondary Cash back with Percentage */
			// Store_Four - Core details
			str_Store_Four_Name  								=  objMainNode.get(strStore_Name+"_Name").toString().trim();

		}

		catch(Exception objError){
			strStatus_Of_Complete_Variables_Loading = "Failed";
			//ExtentTestManager.getTest().log(LogStatus.FAIL, "Sorry,Internal Memory Variables are not loaded Properly,So Up coming dependant execution are going to be Skipped");
			//objError.printStackTrace();
		}

		return strStatus_Of_Complete_Variables_Loading;


		/*************************************************************************************End of Stores Details Assigning*******************************************************************************/
	}

	// extract_Values_From_Json_And_Load_Internal_Variables
	public static String extract_Values_From_Json_And_Load_Internal_Variables_Store_Five(String strStore_Name) {

		String strStatus_Of_Complete_Variables_Loading ="Passed";
		JSONObject objStoreSpecific = new JSONObject();

		try
		{
			// Creating the Json object to the entire json data which is retrived from API
			JSONObject objJsonObject 			= (JSONObject) ObjJparser.parse(new FileReader(System.getProperty("user.dir") + "/dynamic_json_test_data/Ck_Store_Creation_Json_Dynamic_Data_UI.json"));//this returns the Object(super most class) ,so down casting
			
			//Based on Store type , assigning the store specific object
			if(strStore_Name.contains("CB_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("CB_Store");
			} else if(strStore_Name.contains("RW_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("RW_Store");
			}else if(strStore_Name.contains("N18_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("N18_Store");
			}

			//Fetch the required value from the required store type
			JSONObject objMainNode 				= (JSONObject) objStoreSpecific.get(strStore_Name);

			/********************************************************************* Store -4 Details Assigning *********************************************************************************/

			/*  Normal Primary / Secondary Cash back with Percentage */
			// Store_Five - Core details
			str_Store_Five_Name  										=  objMainNode.get(strStore_Name+"_Name").toString().trim();	

		}

		catch(Exception objError){
			strStatus_Of_Complete_Variables_Loading = "Failed";
			//ExtentTestManager.getTest().log(LogStatus.FAIL, "Sorry,Internal Memory Variables are not loaded Properly,So Up coming dependant execution are going to be Skipped");
			//objError.printStackTrace();
		}

		return strStatus_Of_Complete_Variables_Loading;


		/*************************************************************************************End of Stores Details Assigning*******************************************************************************/
	}

	// extract_Values_From_Json_And_Load_Internal_Variables
	public static String extract_Values_From_Json_And_Load_Internal_Variables_Store_Six(String strStore_Name) {

		String strStatus_Of_Complete_Variables_Loading ="Passed";
		JSONObject objStoreSpecific = new JSONObject();

		try
		{
			// Creating the Json object to the entire json data which is retrived from API
			JSONObject objJsonObject 			= (JSONObject) ObjJparser.parse(new FileReader(System.getProperty("user.dir") + "/dynamic_json_test_data/Ck_Store_Creation_Json_Dynamic_Data_UI.json"));//this returns the Object(super most class) ,so down casting
			
			//Based on Store type , assigning the store specific object
			if(strStore_Name.contains("CB_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("CB_Store");
			} else if(strStore_Name.contains("RW_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("RW_Store");
			}else if(strStore_Name.contains("N18_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("N18_Store");
			}

			//Fetch the required value from the required store type
			JSONObject objMainNode 				= (JSONObject) objStoreSpecific.get(strStore_Name);

			/********************************************************************* Store -4 Details Assigning *********************************************************************************/

			/*  Normal Primary / Secondary Cash back with Percentage */
			// Store_Six - Core details
			str_Store_Six_Name  										=  objMainNode.get(strStore_Name+"_Name").toString().trim();	
			str_Store_Six_Primary_Cashback_Value  						=  objMainNode.get(strStore_Name+"_Primary_Cashback_Value").toString().trim();	
			str_Store_Six_Primary_Cashback_Details  					=  objMainNode.get(strStore_Name+"_Primary_Cashback_Details").toString().trim();	
			str_Store_Six_Secondary_Cashback_Value  					=  objMainNode.get(strStore_Name+"_Secondary_Cashback_Value").toString().trim();	
			str_Store_Six_Secondary_Cashback_Details  					=  objMainNode.get(strStore_Name+"_Secondary_Cashback_Details").toString().trim();	

		}

		catch(Exception objError){
			strStatus_Of_Complete_Variables_Loading = "Failed";
			//ExtentTestManager.getTest().log(LogStatus.FAIL, "Sorry,Internal Memory Variables are not loaded Properly,So Up coming dependant execution are going to be Skipped");
			//objError.printStackTrace();
		}

		return strStatus_Of_Complete_Variables_Loading;


		/*************************************************************************************End of Stores Details Assigning*******************************************************************************/
	}

	// extract_Values_From_Json_And_Load_Internal_Variables
	public static String extract_Values_From_Json_And_Load_Internal_Variables_Store_Seven(String strStore_Name) {

		String strStatus_Of_Complete_Variables_Loading ="Passed";
		JSONObject objStoreSpecific = new JSONObject();

		try
		{
			// Creating the Json object to the entire json data which is retrived from API
			JSONObject objJsonObject 			= (JSONObject) ObjJparser.parse(new FileReader(System.getProperty("user.dir") + "/dynamic_json_test_data/Ck_Store_Creation_Json_Dynamic_Data_UI.json"));//this returns the Object(super most class) ,so down casting
			
			//Based on Store type , assigning the store specific object
			if(strStore_Name.contains("CB_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("CB_Store");
			} else if(strStore_Name.contains("RW_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("RW_Store");
			}else if(strStore_Name.contains("N18_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("N18_Store");
			}

			//Fetch the required value from the required store type
			JSONObject objMainNode 				= (JSONObject) objStoreSpecific.get(strStore_Name);

			/********************************************************************* Store -4 Details Assigning *********************************************************************************/

			/*  Normal Primary / Secondary Cash back with Percentage */
			// Store_Seven - Core details
			str_Store_Seven_Name  										=  objMainNode.get(strStore_Name+"_Name").toString().trim();	
			str_Store_Seven_Primary_Cashback_Value  					=  objMainNode.get(strStore_Name+"_Primary_Cashback_Value").toString().trim();	
			str_Store_Seven_Primary_Cashback_Details  					=  objMainNode.get(strStore_Name+"_Primary_Cashback_Details").toString().trim();	
			str_Store_Seven_Secondary_Cashback_Value  					=  objMainNode.get(strStore_Name+"_Secondary_Cashback_Value").toString().trim();	
			str_Store_Seven_Secondary_Cashback_Details  				=  objMainNode.get(strStore_Name+"_Secondary_Cashback_Details").toString().trim();	

		}

		catch(Exception objError){
			strStatus_Of_Complete_Variables_Loading = "Failed";
			//ExtentTestManager.getTest().log(LogStatus.FAIL, "Sorry,Internal Memory Variables are not loaded Properly,So Up coming dependant execution are going to be Skipped");
			//objError.printStackTrace();
		}

		return strStatus_Of_Complete_Variables_Loading;


		/*************************************************************************************End of Stores Details Assigning*******************************************************************************/
	}

	// extract_Values_From_Json_And_Load_Internal_Variables
	public static String extract_Values_From_Json_And_Load_Internal_Variables_Store_Eight(String strStore_Name) {

		String strStatus_Of_Complete_Variables_Loading ="Passed";
		JSONObject objStoreSpecific = new JSONObject();

		try
		{
			// Creating the Json object to the entire json data which is retrived from API
			JSONObject objJsonObject 			= (JSONObject) ObjJparser.parse(new FileReader(System.getProperty("user.dir") + "/dynamic_json_test_data/Ck_Store_Creation_Json_Dynamic_Data_UI.json"));//this returns the Object(super most class) ,so down casting
			
			//Based on Store type , assigning the store specific object
			if(strStore_Name.contains("CB_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("CB_Store");
			} else if(strStore_Name.contains("RW_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("RW_Store");
			}else if(strStore_Name.contains("N18_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("N18_Store");
			}

			//Fetch the required value from the required store type
			JSONObject objMainNode 				= (JSONObject) objStoreSpecific.get(strStore_Name);

			/********************************************************************* Store -4 Details Assigning *********************************************************************************/

			/*  Normal Primary / Secondary Cash back with Percentage */
			// Store_Eight - Core details  
			str_Store_Eight_Name  												=  objMainNode.get(strStore_Name+"_Name").toString().trim();
			str_Store_Eight_linkURL 											=  objMainNode.get(strStore_Name+"_linkURL").toString().trim();					
			str_Store_Eight_Short_Desc_App 										=  objMainNode.get(strStore_Name+"_Short_Desc_App").toString().trim(); 				
			str_Store_Eight_Primary_Cashback_Details 							= objMainNode.get(strStore_Name+"_Primary_Cashback_Details").toString().trim(); 	 
			str_Store_Eight_Primary_Cashback_Value  					    	= objMainNode.get(strStore_Name+"_Primary_Cashback_Value").toString().trim();
			str_Store_Eight_Secondary_Cashback_Details 							= objMainNode.get(strStore_Name+"_Secondary_Cashback_Details").toString().trim();
			str_Store_Eight_Secondary_Cashback_Value  							= objMainNode.get(strStore_Name+"_Secondary_Cashback_Value").toString().trim();
			str_Store_Eight_Voucher_Two_Name 									= objMainNode.get(strStore_Name+"_Voucher_Two_Name").toString().trim();
			str_Store_Eight_Voucher_Two_CODE 									= objMainNode.get(strStore_Name+"_Voucher_Two_CODE").toString().trim();
			

		}

		catch(Exception objError){
			strStatus_Of_Complete_Variables_Loading = "Failed";
			//ExtentTestManager.getTest().log(LogStatus.FAIL, "Sorry,Internal Memory Variables are not loaded Properly,So Up coming dependant execution are going to be Skipped");
			//objError.printStackTrace();
		}

		return strStatus_Of_Complete_Variables_Loading;


		/*************************************************************************************End of Stores Details Assigning*******************************************************************************/
	}

	// extract_Values_From_Json_And_Load_Internal_Variables
	public static String extract_Values_From_Json_And_Load_Internal_Variables_Store_Nine(String strStore_Name) {

		String strStatus_Of_Complete_Variables_Loading ="Passed";
		JSONObject objStoreSpecific = new JSONObject();

		try
		{
			// Creating the Json object to the entire json data which is retrived from API
			JSONObject objJsonObject 			= (JSONObject) ObjJparser.parse(new FileReader(System.getProperty("user.dir") + "/dynamic_json_test_data/Ck_Store_Creation_Json_Dynamic_Data_UI.json"));//this returns the Object(super most class) ,so down casting
			
			//Based on Store type , assigning the store specific object
			if(strStore_Name.contains("CB_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("CB_Store");
			} else if(strStore_Name.contains("RW_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("RW_Store");
			}else if(strStore_Name.contains("N18_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("N18_Store");
			}

			//Fetch the required value from the required store type
			JSONObject objMainNode 				= (JSONObject) objStoreSpecific.get(strStore_Name);

			/********************************************************************* Store -4 Details Assigning *********************************************************************************/

			/*  Normal Primary / Secondary Cash back with Percentage */
			// Store_Two_ - Core details
			str_Store_Nine_Name  												=  objMainNode.get(strStore_Name+"_Name").toString().trim();
			str_Store_Nine_linkURL 												=  objMainNode.get(strStore_Name+"_linkURL").toString().trim();					
			str_Store_Nine_Short_Desc_App 										=  objMainNode.get(strStore_Name+"_Short_Desc_App").toString().trim(); 				
			str_Store_Nine_Primary_Cashback_Details 							= objMainNode.get(strStore_Name+"_Primary_Cashback_Details").toString().trim(); 	 
			str_Store_Nine_Primary_Cashback_Value  					    	= objMainNode.get(strStore_Name+"_Primary_Cashback_Value").toString().trim();
			str_Store_Nine_Secondary_Cashback_Details 							= objMainNode.get(strStore_Name+"_Secondary_Cashback_Details").toString().trim();
			str_Store_Nine_Secondary_Cashback_Value  							= objMainNode.get(strStore_Name+"_Secondary_Cashback_Value").toString().trim();
			str_Store_Nine_Voucher_Two_Name 									= objMainNode.get(strStore_Name+"_Voucher_Two_Name").toString().trim();
			str_Store_Nine_Voucher_Two_CODE 									= objMainNode.get(strStore_Name+"_Voucher_Two_CODE").toString().trim();

		}

		catch(Exception objError){
			strStatus_Of_Complete_Variables_Loading = "Failed";
			//ExtentTestManager.getTest().log(LogStatus.FAIL, "Sorry,Internal Memory Variables are not loaded Properly,So Up coming dependant execution are going to be Skipped");
			//objError.printStackTrace();
		}

		return strStatus_Of_Complete_Variables_Loading;


		/*************************************************************************************End of Stores Details Assigning*******************************************************************************/
	}

	// extract_Values_From_Json_And_Load_Internal_Variables
	public static String extract_Values_From_Json_And_Load_Internal_Variables_Store_Eleven(String strStore_Name) {

		String strStatus_Of_Complete_Variables_Loading ="Passed";
		JSONObject objStoreSpecific = new JSONObject();

		try
		{
			// Creating the Json object to the entire json data which is retrived from API
			JSONObject objJsonObject 			= (JSONObject) ObjJparser.parse(new FileReader(System.getProperty("user.dir") + "/dynamic_json_test_data/Ck_Store_Creation_Json_Dynamic_Data_UI.json"));//this returns the Object(super most class) ,so down casting
			
			//Based on Store type , assigning the store specific object
			if(strStore_Name.contains("CB_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("CB_Store");
			} else if(strStore_Name.contains("RW_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("RW_Store");
			}else if(strStore_Name.contains("N18_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("N18_Store");
			}

			//Fetch the required value from the required store type
			JSONObject objMainNode 				= (JSONObject) objStoreSpecific.get(strStore_Name);

			/********************************************************************* Store -4 Details Assigning *********************************************************************************/

			/*  Normal Primary / Secondary Cash back with Percentage */
			// Store_Nine - Core details            
			str_Store_Eleven_Name  								=  objMainNode.get(strStore_Name+"_Name").toString().trim();

		}
		catch(Exception objError){
			strStatus_Of_Complete_Variables_Loading = "Failed";
			//ExtentTestManager.getTest().log(LogStatus.FAIL, "Sorry,Internal Memory Variables are not loaded Properly,So Up coming dependant execution are going to be Skipped");
			//objError.printStackTrace();
		}

		return strStatus_Of_Complete_Variables_Loading;


		/*************************************************************************************End of Stores Details Assigning*******************************************************************************/
	}

	// extract_Values_From_Json_And_Load_Internal_Variables
	public static String extract_Values_From_Json_And_Load_Internal_Variables_Store_Twelve(String strStore_Name) {

		String strStatus_Of_Complete_Variables_Loading ="Passed";
		JSONObject objStoreSpecific = new JSONObject();

		try
		{
			// Creating the Json object to the entire json data which is retrived from API
			JSONObject objJsonObject 			= (JSONObject) ObjJparser.parse(new FileReader(System.getProperty("user.dir") + "/dynamic_json_test_data/Ck_Store_Creation_Json_Dynamic_Data_UI.json"));//this returns the Object(super most class) ,so down casting
			
			//Based on Store type , assigning the store specific object
			if(strStore_Name.contains("CB_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("CB_Store");
			} else if(strStore_Name.contains("RW_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("RW_Store");
			}else if(strStore_Name.contains("N18_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("N18_Store");
			}

			//Fetch the required value from the required store type
			JSONObject objMainNode 				= (JSONObject) objStoreSpecific.get(strStore_Name);

			/********************************************************************* Store -4 Details Assigning *********************************************************************************/

			/*  Normal Primary / Secondary Cash back with Percentage */
			// Store_Twelve - Core details            
			str_Store_Twelve_Name  													=  objMainNode.get(strStore_Name+"_Name").toString().trim();	


		}
		catch(Exception objError){
			strStatus_Of_Complete_Variables_Loading = "Failed";
			//ExtentTestManager.getTest().log(LogStatus.FAIL, "Sorry,Internal Memory Variables are not loaded Properly,So Up coming dependant execution are going to be Skipped");
			//objError.printStackTrace();
		}

		return strStatus_Of_Complete_Variables_Loading;


		/*************************************************************************************End of Stores Details Assigning*******************************************************************************/
	}

	// extract_Values_From_Json_And_Load_Internal_Variables
	public static String extract_Values_From_Json_And_Load_Internal_Variables_Store_Thirteen(String strStore_Name) {

		String strStatus_Of_Complete_Variables_Loading ="Passed";
		JSONObject objStoreSpecific = new JSONObject();

		try
		{
			// Creating the Json object to the entire json data which is retrived from API
			JSONObject objJsonObject 			= (JSONObject) ObjJparser.parse(new FileReader(System.getProperty("user.dir") + "/dynamic_json_test_data/Ck_Store_Creation_Json_Dynamic_Data_UI.json"));//this returns the Object(super most class) ,so down casting
			
			//Based on Store type , assigning the store specific object
			if(strStore_Name.contains("CB_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("CB_Store");
			} else if(strStore_Name.contains("RW_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("RW_Store");
			}else if(strStore_Name.contains("N18_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("N18_Store");
			}

			//Fetch the required value from the required store type
			JSONObject objMainNode 				= (JSONObject) objStoreSpecific.get(strStore_Name);

			/********************************************************************* Store -4 Details Assigning *********************************************************************************/

			/*  Normal Primary / Secondary Cash back with Percentage */
			// Store_Thirteen - Core details            
			str_Store_Thirteen_Name  												=  objMainNode.get(strStore_Name+"_Name").toString().trim();	


		}
		catch(Exception objError){
			strStatus_Of_Complete_Variables_Loading = "Failed";
			//ExtentTestManager.getTest().log(LogStatus.FAIL, "Sorry,Internal Memory Variables are not loaded Properly,So Up coming dependant execution are going to be Skipped");
			//objError.printStackTrace();
		}

		return strStatus_Of_Complete_Variables_Loading;


		/*************************************************************************************End of Stores Details Assigning*******************************************************************************/
	}

	// extract_Values_From_Json_And_Load_Internal_Variables
	public static String extract_Values_From_Json_And_Load_Internal_Variables_Store_Fourteen(String strStore_Name) {

		String strStatus_Of_Complete_Variables_Loading ="Passed";
		JSONObject objStoreSpecific = new JSONObject();

		try
		{
			// Creating the Json object to the entire json data which is retrived from API
			JSONObject objJsonObject 			= (JSONObject) ObjJparser.parse(new FileReader(System.getProperty("user.dir") + "/dynamic_json_test_data/Ck_Store_Creation_Json_Dynamic_Data_UI.json"));//this returns the Object(super most class) ,so down casting
			
			//Based on Store type , assigning the store specific object
			if(strStore_Name.contains("CB_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("CB_Store");
			} else if(strStore_Name.contains("RW_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("RW_Store");
			}else if(strStore_Name.contains("N18_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("N18_Store");
			}

			//Fetch the required value from the required store type
			JSONObject objMainNode 				= (JSONObject) objStoreSpecific.get(strStore_Name);

			/********************************************************************* Store -4 Details Assigning *********************************************************************************/

			/*  Normal Primary / Secondary Cash back with Percentage */	
			// Store_Fourteen - Core details            
			str_Store_Fourteen_Name  												=  objMainNode.get(strStore_Name+"_Name").toString().trim();	


		}
		catch(Exception objError){
			strStatus_Of_Complete_Variables_Loading = "Failed";
			//ExtentTestManager.getTest().log(LogStatus.FAIL, "Sorry,Internal Memory Variables are not loaded Properly,So Up coming dependant execution are going to be Skipped");
			//objError.printStackTrace();
		}

		return strStatus_Of_Complete_Variables_Loading;


		/*************************************************************************************End of Stores Details Assigning*******************************************************************************/
	}

	// extract_Values_From_Json_And_Load_Internal_Variables
	public static String extract_Values_From_Json_And_Load_Internal_Variables_Store_Fifteen(String strStore_Name) {

		String strStatus_Of_Complete_Variables_Loading ="Passed";
		JSONObject objStoreSpecific = new JSONObject();

		try
		{
			// Creating the Json object to the entire json data which is retrived from API
			JSONObject objJsonObject 			= (JSONObject) ObjJparser.parse(new FileReader(System.getProperty("user.dir") + "/dynamic_json_test_data/Ck_Store_Creation_Json_Dynamic_Data_UI.json"));//this returns the Object(super most class) ,so down casting
			
			//Based on Store type , assigning the store specific object
			if(strStore_Name.contains("CB_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("CB_Store");
			} else if(strStore_Name.contains("RW_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("RW_Store");
			}else if(strStore_Name.contains("N18_Store")) {
				objStoreSpecific 				= (JSONObject) objJsonObject.get("N18_Store");
			}

			//Fetch the required value from the required store type
			JSONObject objMainNode 				= (JSONObject) objStoreSpecific.get(strStore_Name);

			/********************************************************************* Store -4 Details Assigning *********************************************************************************/

			/*  Normal Primary / Secondary Cash back with Percentage */
			// Store_Fifteen - Core details            
			str_Store_Fifteen_Name  												=  objMainNode.get(strStore_Name+"_Name").toString().trim();	
			str_Store_Fifteen_Primary_Cashback_Value  								=  objMainNode.get(strStore_Name+"_Primary_Cashback_Value").toString().trim();
			str_Store_Fifteen_Primary_Cashback_Details  							=  objMainNode.get(strStore_Name+"_Primary_Cashback_Details").toString().trim();
			str_Store_Fifteen_Secondary_Cashback_Value  							=  objMainNode.get(strStore_Name+"_Secondary_Cashback_Value").toString().trim();
			str_Store_Fifteen_Secondary_Cashback_Details  							=  objMainNode.get(strStore_Name+"_Secondary_Cashback_Details").toString().trim();
			str_Store_Fifteen_Name  												=  objMainNode.get(strStore_Name+"_Name").toString().trim();
			str_Store_Fifteen_Primary_Cashback_Details 							= objMainNode.get(strStore_Name+"_Primary_Cashback_Details").toString().trim(); 	 
			str_Store_Fifteen_Primary_Cashback_Value  					    	= objMainNode.get(strStore_Name+"_Primary_Cashback_Value").toString().trim();
			str_Store_Fifteen_Secondary_Cashback_Details 							= objMainNode.get(strStore_Name+"_Secondary_Cashback_Details").toString().trim();
			str_Store_Fifteen_Secondary_Cashback_Value  							= objMainNode.get(strStore_Name+"_Secondary_Cashback_Value").toString().trim();


		}
		catch(Exception objError){
			strStatus_Of_Complete_Variables_Loading = "Failed";
			//ExtentTestManager.getTest().log(LogStatus.FAIL, "Sorry,Internal Memory Variables are not loaded Properly,So Up coming dependant execution are going to be Skipped");
			objError.printStackTrace();
		}

		return strStatus_Of_Complete_Variables_Loading;

	}


	//*************************************************************************************************************************************************************************************//
	//** 											    					 get Methods												   			    								 **//
	//*************************************************************************************************************************************************************************************//

	public String getRequiredStoreName(String requiredValue) {

		String string = "";

		switch (requiredValue) {

		case "str_Store_One_Name":

			reportStep("str_Store_One_Name: "+str_Store_One_Name, "INFO");
			return str_Store_One_Name;

		case "str_Store_Two_Name":

			reportStep("str_Store_Two_Name: "+str_Store_Two_Name, "INFO");
			return str_Store_Two_Name;

		case "str_Store_Three_Name":

			reportStep("str_Store_Three_Name: "+str_Store_Three_Name, "INFO");
			return str_Store_Three_Name;

		case "str_Store_Four_Name":

			reportStep("str_Store_Four_Name: "+str_Store_Four_Name, "INFO");
			return str_Store_Four_Name;

		case "str_Store_Five_Name":

			reportStep("str_Store_Five_Name: "+str_Store_Five_Name, "INFO");
			return str_Store_Five_Name;

		case "str_Store_Six_Name":

			reportStep("str_Store_Six_Name: "+str_Store_Six_Name, "INFO");
			return str_Store_Six_Name;

		case "str_Store_Seven_Name":

			reportStep("str_Store_Seven_Name: "+str_Store_Seven_Name, "INFO");
			return str_Store_Seven_Name;

		case "str_Store_Eight_Name":

			reportStep("str_Store_Eight_Name: "+str_Store_Eight_Name, "INFO");
			return str_Store_Eight_Name;

		case "str_Store_Nine_Name":

			reportStep("str_Store_Nine_Name: "+str_Store_Nine_Name, "INFO");
			return str_Store_Nine_Name;

		case "str_Store_Eleven_Name":

			reportStep("str_Store_Eleven_Name: "+str_Store_Eleven_Name, "INFO");
			return str_Store_Eleven_Name;

		case "str_Store_Twelve_Name":

			reportStep("str_Store_Twelve_Name: "+str_Store_Twelve_Name, "INFO");
			return str_Store_Twelve_Name;

		case "str_Store_Thirteen_Name":

			reportStep("str_Store_Thirteen_Name: "+str_Store_Thirteen_Name, "INFO");
			return str_Store_Thirteen_Name;

		case "str_Store_Fourteen_Name":

			reportStep("str_Store_Fourteen_Name: "+str_Store_Fourteen_Name, "INFO");
			return str_Store_Fourteen_Name;

		case "str_Store_Fifteen_Name":

			reportStep("str_Store_Fifteen_Name: "+str_Store_Fifteen_Name, "INFO");
			return str_Store_Fifteen_Name;

		}

		return string;
	}

	public String getRequiredShortDescription(String requiredValue) {

		String string = "";

		switch (requiredValue) {

		case "str_Store_One_Short_Desc_App":

			reportStep("str_Store_One_Short_Desc_App: "+str_Store_One_Short_Desc_App, "INFO");
			return str_Store_One_Short_Desc_App;

		case "str_Store_Two_Short_Desc_App":

			reportStep("str_Store_Two_Short_Desc_App: "+str_Store_Two_Short_Desc_App, "INFO");
			return str_Store_Two_Short_Desc_App;

		case "str_Store_Eight_Short_Desc_App":

			reportStep("str_Store_Eight_Short_Desc_App: "+str_Store_Eight_Short_Desc_App, "INFO");
			return str_Store_Eight_Short_Desc_App;

		case "str_Store_Nine_Short_Desc_App":

			reportStep("str_Store_Nine_Short_Desc_App: "+str_Store_Nine_Short_Desc_App, "INFO");
			return str_Store_Nine_Short_Desc_App;

		}

		return string;
	}

	public String getRequiredPrimaryCashbackDetails(String requiredValue) {

		String string = "";

		switch (requiredValue) {

		case "str_Store_One_Primary_Cashback_Details":

			reportStep("str_Store_One_Primary_Cashback_Details: "+str_Store_One_Primary_Cashback_Details, "INFO");
			return str_Store_One_Primary_Cashback_Details;

		case "str_Store_Two_Primary_Cashback_Details":

			reportStep("str_Store_Two_Primary_Cashback_Details: "+str_Store_Two_Primary_Cashback_Details, "INFO");
			return str_Store_Two_Primary_Cashback_Details;

		case "str_Store_Three_Primary_Cashback_Details":

			reportStep("str_Store_Three_Primary_Cashback_Details: "+str_Store_Three_Primary_Cashback_Details, "INFO");
			return str_Store_Three_Primary_Cashback_Details;

		case "str_Store_Six_Primary_Cashback_Details":

			reportStep("str_Store_Six_Primary_Cashback_Details: "+str_Store_Six_Primary_Cashback_Details, "INFO");
			return str_Store_Six_Primary_Cashback_Details;

		case "str_Store_Seven_Primary_Cashback_Details":

			reportStep("str_Store_Seven_Primary_Cashback_Details: "+str_Store_Seven_Primary_Cashback_Details, "INFO");
			return str_Store_Seven_Primary_Cashback_Details;

		case "str_Store_Eight_Primary_Cashback_Details":

			reportStep("str_Store_Eight_Primary_Cashback_Details: "+str_Store_Eight_Primary_Cashback_Details, "INFO");
			return str_Store_Eight_Primary_Cashback_Details;

		case "str_Store_Nine_Primary_Cashback_Details":

			reportStep("str_Store_Nine_Primary_Cashback_Details: "+str_Store_Nine_Primary_Cashback_Details, "INFO");
			return str_Store_Nine_Primary_Cashback_Details;

		case "str_Store_Fifteen_Primary_Cashback_Details":

			reportStep("str_Store_Fifteen_Primary_Cashback_Details: "+str_Store_Fifteen_Primary_Cashback_Details, "INFO");
			return str_Store_Fifteen_Primary_Cashback_Details;

		}

		return string;
	}

	public String getRequiredPrimaryCashbackValue(String requiredValue) {

		String string = "";

		switch (requiredValue) {

		case "str_Store_One_Primary_Cashback_Value":

			reportStep("str_Store_One_Primary_Cashback_Value: "+str_Store_One_Primary_Cashback_Value, "INFO");
			return str_Store_One_Primary_Cashback_Value;

		case "str_Store_Two_Primary_Cashback_Value":

			reportStep("str_Store_Two_Primary_Cashback_Value: "+str_Store_Two_Primary_Cashback_Value, "INFO");
			return str_Store_Two_Primary_Cashback_Value;

		case "str_Store_Three_Primary_Cashback_Value":

			reportStep("str_Store_Three_Primary_Cashback_Value: "+str_Store_Three_Primary_Cashback_Value, "INFO");
			return str_Store_Three_Primary_Cashback_Value;

		case "str_Store_Six_Primary_Cashback_Value":

			reportStep("str_Store_Six_Primary_Cashback_Value: "+str_Store_Six_Primary_Cashback_Value, "INFO");
			return str_Store_Six_Primary_Cashback_Value;

		case "str_Store_Seven_Primary_Cashback_Value":

			reportStep("str_Store_Seven_Primary_Cashback_Value: "+str_Store_Seven_Primary_Cashback_Value, "INFO");
			return str_Store_Seven_Primary_Cashback_Value;

		case "str_Store_Eight_Primary_Cashback_Value":

			reportStep("str_Store_Eight_Primary_Cashback_Value: "+str_Store_Eight_Primary_Cashback_Value, "INFO");
			return str_Store_Eight_Primary_Cashback_Value;

		case "str_Store_Nine_Primary_Cashback_Value":

			reportStep("str_Store_Nine_Primary_Cashback_Value: "+str_Store_Nine_Primary_Cashback_Value, "INFO");
			return str_Store_Nine_Primary_Cashback_Value;

		case "str_Store_Fifteen_Primary_Cashback_Value":

			reportStep("str_Store_Fifteen_Primary_Cashback_Value: "+str_Store_Fifteen_Primary_Cashback_Value, "INFO");
			return str_Store_Fifteen_Primary_Cashback_Value;

		}

		return string;
	}

	public String getRequiredSecondaryCashbackDetails(String requiredValue) {

		String string = "";

		switch (requiredValue) {

		case "str_Store_One_Secondary_Cashback_Details":

			reportStep("str_Store_One_Secondary_Cashback_Details: "+str_Store_One_Secondary_Cashback_Details, "INFO");
			return str_Store_One_Secondary_Cashback_Details;

		case "str_Store_Two_Secondary_Cashback_Details":

			reportStep("str_Store_Two_Secondary_Cashback_Details: "+str_Store_Two_Secondary_Cashback_Details, "INFO");
			return str_Store_Two_Secondary_Cashback_Details;

		case "str_Store_Three_Secondary_Cashback_Details":

			reportStep("str_Store_Three_Secondary_Cashback_Details: "+str_Store_Three_Secondary_Cashback_Details, "INFO");
			return str_Store_Three_Secondary_Cashback_Details;

		case "str_Store_Six_Secondary_Cashback_Details":

			reportStep("str_Store_Six_Secondary_Cashback_Details: "+str_Store_Six_Secondary_Cashback_Details, "INFO");
			return str_Store_Six_Secondary_Cashback_Details;

		case "str_Store_Seven_Secondary_Cashback_Details":

			reportStep("str_Store_Seven_Secondary_Cashback_Details: "+str_Store_Seven_Secondary_Cashback_Details, "INFO");
			return str_Store_Seven_Secondary_Cashback_Details;

		case "str_Store_Eight_Secondary_Cashback_Details":

			reportStep("str_Store_Eight_Secondary_Cashback_Details: "+str_Store_Eight_Secondary_Cashback_Details, "INFO");
			return str_Store_Eight_Secondary_Cashback_Details;

		case "str_Store_Nine_Secondary_Cashback_Details":

			reportStep("str_Store_Nine_Secondary_Cashback_Details: "+str_Store_Nine_Secondary_Cashback_Details, "INFO");
			return str_Store_Nine_Secondary_Cashback_Details;

		case "str_Store_Fifteen_Secondary_Cashback_Details":

			reportStep("str_Store_Fifteen_Secondary_Cashback_Details: "+str_Store_Fifteen_Secondary_Cashback_Details, "INFO");
			return str_Store_Fifteen_Secondary_Cashback_Details;

		}

		return string;
	}

	public String getRequiredSecondaryCashbackValue(String requiredValue) {

		String string = "";

		switch (requiredValue) {

		case "str_Store_One_Secondary_Cashback_Value":

			reportStep("str_Store_One_Secondary_Cashback_Value: "+str_Store_One_Secondary_Cashback_Value, "INFO");
			return str_Store_One_Secondary_Cashback_Value;

		case "str_Store_Two_Secondary_Cashback_Value":

			reportStep("str_Store_Two_Secondary_Cashback_Value: "+str_Store_Two_Secondary_Cashback_Value, "INFO");
			return str_Store_Two_Secondary_Cashback_Value;

		case "str_Store_Three_Secondary_Cashback_Value":

			reportStep("str_Store_Three_Secondary_Cashback_Value: "+str_Store_Three_Secondary_Cashback_Value, "INFO");
			return str_Store_Three_Secondary_Cashback_Value;

		case "str_Store_Six_Secondary_Cashback_Value":

			reportStep("str_Store_Six_Secondary_Cashback_Value: "+str_Store_Six_Secondary_Cashback_Value, "INFO");
			return str_Store_Six_Secondary_Cashback_Value;

		case "str_Store_Seven_Secondary_Cashback_Value":

			reportStep("str_Store_Seven_Secondary_Cashback_Value: "+str_Store_Seven_Secondary_Cashback_Value, "INFO");
			return str_Store_Seven_Secondary_Cashback_Value;

		case "str_Store_Eight_Secondary_Cashback_Value":

			reportStep("str_Store_Eight_Secondary_Cashback_Value: "+str_Store_Eight_Secondary_Cashback_Value, "INFO");
			return str_Store_Eight_Secondary_Cashback_Value;

		case "str_Store_Nine_Secondary_Cashback_Value":

			reportStep("str_Store_Nine_Secondary_Cashback_Value: "+str_Store_Nine_Secondary_Cashback_Value, "INFO");
			return str_Store_Nine_Secondary_Cashback_Value;

		case "str_Store_Fifteen_Secondary_Cashback_Value":

			reportStep("str_Store_Fifteen_Secondary_Cashback_Value: "+str_Store_Fifteen_Secondary_Cashback_Value, "INFO");
			return str_Store_Fifteen_Secondary_Cashback_Value;

		}

		return string;
	}

	public String getRequiredLinkUrl(String requiredValue) {

		String string = "";

		switch (requiredValue) {

		case "str_Store_One_linkURL":

			reportStep("str_Store_One_linkURL: "+str_Store_One_linkURL, "INFO");
			return str_Store_One_linkURL;

		case "str_Store_Two_linkURL":

			reportStep("str_Store_Two_linkURL: "+str_Store_Two_linkURL, "INFO");
			return str_Store_Two_linkURL;

		case "str_Store_Three_linkURL":

			reportStep("str_Store_Three_linkURL: "+str_Store_Three_linkURL, "INFO");
			return str_Store_Three_linkURL;

		case "str_Store_Eight_linkURL":

			reportStep("str_Store_Eight_linkURL: "+str_Store_Eight_linkURL, "INFO");
			return str_Store_Eight_linkURL;

		case "str_Store_Nine_linkURL":

			reportStep("str_Store_Nine_linkURL: "+str_Store_Nine_linkURL, "INFO");
			return str_Store_Nine_linkURL;
		}

		return string;
	}

	public String getRequiredVoucherCode(String requiredValue) {

		String string = "";

		switch (requiredValue) {

		case "str_Store_One_Voucher_Two_CODE":

			reportStep("str_Store_One_Voucher_Two_CODE: "+str_Store_One_Voucher_Two_CODE, "INFO");
			return str_Store_One_Voucher_Two_CODE;

		case "str_Store_Two_Voucher_Two_CODE":

			reportStep("str_Store_Two_Voucher_Two_CODE: "+str_Store_Two_Voucher_Two_CODE, "INFO");
			return str_Store_Two_Voucher_Two_CODE;

		case "str_Store_Eight_Voucher_Two_CODE":

			reportStep("str_Store_Eight_Voucher_Two_CODE: "+str_Store_Eight_Voucher_Two_CODE, "INFO");
			return str_Store_Eight_Voucher_Two_CODE;

		case "str_Store_Nine_Voucher_Two_CODE":

			reportStep("str_Store_Nine_Voucher_Two_CODE: "+str_Store_Nine_Voucher_Two_CODE, "INFO");
			return str_Store_Nine_Voucher_Two_CODE;
		}

		return string;
	}

	public String getRequiredVoucherName(String requiredValue) {

		String string = "";

		switch (requiredValue) {

		case "str_Store_One_Voucher_Two_Name":

			reportStep("str_Store_One_Voucher_Two_Name: "+str_Store_One_Voucher_Two_Name, "INFO");
			return str_Store_One_Voucher_Two_Name;

		case "str_Store_Two_Voucher_Two_Name":

			reportStep("str_Store_Two_Voucher_Two_Name: "+str_Store_Two_Voucher_Two_Name, "INFO");
			return str_Store_Two_Voucher_Two_Name;

		case "str_Store_Eight_Voucher_Two_Name":

			reportStep("str_Store_Eight_Voucher_Two_Name: "+str_Store_Eight_Voucher_Two_Name, "INFO");
			return str_Store_Eight_Voucher_Two_Name;

		case "str_Store_Nine_Voucher_Two_Name":

			reportStep("str_Store_Nine_Voucher_Two_Name: "+str_Store_Nine_Voucher_Two_Name, "INFO");
			return str_Store_Nine_Voucher_Two_Name;
		}

		return string;
	}

	public void passStoreFourIndexAccordingToApiLevel(String apiLevel) {

		switch (apiLevel) {
		case "21":
			extract_Values_From_Json_And_Load_Internal_Variables_Store_Four("TAB_CB_Store_Four");

			break;

		case "22":
			extract_Values_From_Json_And_Load_Internal_Variables_Store_Four("MOB_N18_Store_Four");

			break;

		case "23":
			extract_Values_From_Json_And_Load_Internal_Variables_Store_Four("MOB_RW_Store_Four");

			break;

		case "24":
			extract_Values_From_Json_And_Load_Internal_Variables_Store_Four("MOB_CB_Store_Four");

			break;

		case "25":
			extract_Values_From_Json_And_Load_Internal_Variables_Store_Four("N18_Store_Four");

			break;

		case "26":
			extract_Values_From_Json_And_Load_Internal_Variables_Store_Four("RW_Store_Four");

			break;

		case "27":
			extract_Values_From_Json_And_Load_Internal_Variables_Store_Four("CB_Store_Four");

			break;
		}



	}

	public void passStoreFiveIndexAccordingToApiLevel(String apiLevel) {

		switch (apiLevel) {
		case "21":
			extract_Values_From_Json_And_Load_Internal_Variables_Store_Five("TAB_CB_Store_Five");

			break;

		case "22":
			extract_Values_From_Json_And_Load_Internal_Variables_Store_Five("MOB_N18_Store_Five");

			break;

		case "23":
			extract_Values_From_Json_And_Load_Internal_Variables_Store_Five("MOB_RW_Store_Five");

			break;

		case "24":
			extract_Values_From_Json_And_Load_Internal_Variables_Store_Five("MOB_CB_Store_Five");

			break;

		case "25":
			extract_Values_From_Json_And_Load_Internal_Variables_Store_Five("N18_Store_Five");

			break;

		case "26":
			extract_Values_From_Json_And_Load_Internal_Variables_Store_Five("RW_Store_Five");

			break;

		case "27":
			extract_Values_From_Json_And_Load_Internal_Variables_Store_Five("CB_Store_Five");

			break;
		}



	}

	public String getRequiredTermsConditions(String requiredValue) {

		String string = "";

		switch (requiredValue) {

		case "str_Store_One_Final_Terms_condition":

			reportStep("str_Store_One_Final_Terms_condition: "+str_Store_One_Final_Terms_condition, "INFO");
			return str_Store_One_Final_Terms_condition;

		}

		return string;
	}

}