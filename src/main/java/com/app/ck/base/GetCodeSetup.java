package com.app.ck.base;

public class GetCodeSetup {

	//GetCode Var Declaration :
	public static String CKBASEURL 		= null;
	public static String CKLINKURL 		= null;
	public static String CKAPIBASEURI 	= null;

	public static String ADMINURL 		 = null;

	//latest
	public static String SIGNUPGETCODE 		= null;
	public static String FBACCOUNTRESET 	= null;
	public static String BACKDATEEXITCLICK 	= null;
	public static String ADDBONUS 		    = null;
	public static String CASHOUT 			= null;
	public static String REFERRALLINK		= null;

	//Others  :
	public static String NOTIFICATIONLARGEICON 	= "https://dummyimage.com/72x72/0a6a73/303bdb.png&text=Test+Image";
	public static String URL  					= "https://cashkaro.iamsavings.co.uk/" + "getcode.php?mode=atm_store_json&partner=cashkaro&file=latest";
	public static String ONELINK		 		= "http://cashkaro.iamsavings.co.uk/onelink.html";

	//Live Environment SetUp
	//public static final String CKBASEURL 		 = "https://cashkaro.com/";
	//public static final String CKLINKURL 		 = "cashkaro.com";
	//public static final String CKAPIBASEURI 	 = ""; 

	//public static final String ADMINURL 		 = "";
	//public static final String ADMINURL_STAGING  = "";

	static {

		if(PropertyFile.ENVIRONMENT.equalsIgnoreCase("beta")) {

			CKBASEURL 		= "https://cashkaro.iamsavings.co.uk/";
			CKLINKURL 		= "cashkaro.iamsavings.co.uk";
			CKAPIBASEURI 	= "http://cashkaroapi.iamsavings.co.uk/"; 
			ADMINURL 		= "https://admin.iamsavings.co.uk";

		} else if(PropertyFile.ENVIRONMENT.equalsIgnoreCase("staging")) {

			CKBASEURL 		 = "https://staging.cashkaro.com/";
			CKLINKURL 		 = "staging.cashkaro.com";
			CKAPIBASEURI 	 = "http://apibackendstaging.cashkaro.com/"; 
			ADMINURL  		 = "https://stagingadmin.pouringpounds.com";
		}


		//latest
		SIGNUPGETCODE 		= CKBASEURL + "getcode.php?page=signupotp_app&mobile=";
		FBACCOUNTRESET 		= CKBASEURL + "getcode.php?page=reset_fbid&email=";

		BACKDATEEXITCLICK 	= CKBASEURL + "getcode.php?page=ticket&id=";
		ADDBONUS 		    = CKBASEURL + "getcode.php?page=add_bonus&email=";
		CASHOUT 			= CKBASEURL + "getcode.php?page=create_cashout&email=";
		REFERRALLINK		= CKBASEURL + "getcode.php?page=app_referral_link&userid=";



	} 

}