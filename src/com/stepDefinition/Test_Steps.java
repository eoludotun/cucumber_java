package com.stepDefinition;










import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.esotericsoftware.yamlbeans.YamlReader;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import utility.pom.AppPagesPom;
import utility.pom.AppTry;

public class Test_Steps extends BaseClass {
	
	
	
	//AppPagesPom apppage;
	AppTry tryapp;
	 

	@Given("^User is on Home Page$")
	public void user_is_on_Home_Page() throws Throwable {
		StartTesting();  
		
	}

	@When("^User Navigate to Billing Information Page$")
	public void user_Navigate_to_LogIn_Page() throws Throwable {
		tryapp = new AppTry(driver); 
		
		tryapp.clickCheckOutButton().click();
		tryapp.typeEmail().sendKeys("sendkeys");
		tryapp.typeUserName().sendKeys("sendkeys");
	
	}

	@When("^User enters UserName and PassWord$")
	public void user_enters_UserName_and_PassWord() throws Throwable {	
	/*	apppage = new AppPagesPom(driver);
		Thread.sleep(10);
		apppage.userEmail("testuser_1","username");*/
		
		 // apppage.userName("Test@123");


	}
	@When("^print yaml file$")
	public void print_yaml_file() throws Throwable {
		//YamlReader reader = new YamlReader(new FileReader("C://Test_Project//SeleniumTest//src//com//stepDefinition//data.yaml"));
	   // Object object = reader.read();
	    //System.out.println(object);
	   // Map map = (Map)object;
	    ///System.out.println(map.get("age"));
		
		
		  // The path of your YAML file.
	      final String fileName = "C://Test_Project//SeleniumTest//src//com//stepDefinition//data2.yaml";
	      Yaml yaml = new Yaml();
	      
	      try {
	         InputStream ios = new FileInputStream(new File(fileName));
	         
	         // Parse the YAML file and return the output as a series of Maps and Lists
	         Map<String,Object> result = (Map<String,Object>)yaml.load(ios);
	         System.out.println(result.toString());
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         
	      
		
		/*YamlReader reader = new YamlReader(new FileReader("C://Test_Project//SeleniumTest//src//com//stepDefinition//data2.yaml"));
		Object object = reader.read();
		System.out.println(object);
		Map map = (Map)object;
		System.out.println(map.get("address"));*/
	      }
	}
}


