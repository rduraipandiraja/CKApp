package com.app.ck.base;

public interface PropertyFile {
	
	//if you mention the environment as "Staging" It will execute in the staging enviromnet else it will run in beta
	String ENVIRONMENT = "Beta";

	boolean NEED_SCREEN_SHOTS_ON_TEST_PASS = false; //If You need screenshots on Pass report steps make this variable as TRUE else FALSE
	boolean NEED_SCREEN_SHOTS_ON_TEST_FAIL = true; //If You need screenshots on Fail report steps make this variable as TRUE else FALSE
	
	
	
}
