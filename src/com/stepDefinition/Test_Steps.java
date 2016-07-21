package com.stepDefinition;


import org.openqa.selenium.By;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import utility.pom.AppPagesPom;

public class Test_Steps extends BaseClass {
	
	AppPagesPom apppage;

	@Given("^User is on Home Page$")
	public void user_is_on_Home_Page() throws Throwable {
		StartTesting();    
	}

	@When("^User Navigate to LogIn Page$")
	public void user_Navigate_to_LogIn_Page() throws Throwable {
		// Find the element that's ID attribute is 'account'(My Account) 
		apppage = new AppPagesPom(driver);
		apppage.clickLoginButton();
	}

	@When("^User enters UserName and Password$")
	public void user_enters_UserName_and_Password() throws Throwable {
		  driver.findElement(By.id("log")).sendKeys("testuser_1"); 

		    // Find the element that's ID attribute is 'pwd' (Password)

		    // Enter Password on the element found by the above desc.

		    driver.findElement(By.id("pwd")).sendKeys("Test@123");
	}


	@When("^User LogOut from the Application$")
	public void user_LogOut_from_the_Application() throws Throwable {
	   driver.quit();
	   driver.close();
	
	}
}


