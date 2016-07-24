package com.stepDefinition;










import java.io.FileReader;
import java.util.Map;

import com.esotericsoftware.yamlbeans.YamlReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import utility.pom.AppPagesPom;

public class Test_Steps extends BaseClass {
	
	AppPagesPom apppage;
	

	@Given("^User is on Home Page$")
	public void user_is_on_Home_Page() throws Throwable {
		StartTesting();  
		
	}

	@When("^User Navigate to Billing Information Page$")
	public void user_Navigate_to_LogIn_Page() throws Throwable {
		// Find the element that's ID attribute is 'account'(My Account) 
		apppage = new AppPagesPom(driver);
		apppage.clickBillingButton();
	}

	@When("^User enters UserName and PassWord$")
	public void user_enters_UserName_and_PassWord() throws Throwable {	
		apppage = new AppPagesPom(driver);
		Thread.sleep(10);
		apppage.userEmail("testuser_1","username");
		
		 // apppage.userName("Test@123");


	}


	@When("^User LogOut from the Application$")
	public void user_LogOut_from_the_Application() throws Throwable {
		YamlReader reader = new YamlReader(new FileReader("C://Test_Project//SeleniumTest//src//com//stepDefinition//data.yaml"));
	    Object object = reader.read();
	    System.out.println(object);
	   // Map map = (Map)object;
	    ///System.out.println(map.get("age"));
	   driver.quit();
	   driver.close();
	
	}
}


