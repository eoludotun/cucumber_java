package utility.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class AppTry extends BaseClassPOM_new{

	public AppTry (WebDriver driver) 
	  {
		this.setDriver(driver);
	  }
		
	
		//This is use for the getting the method name, not a big deal
	    // just for reporting purposes
	    private static String name;
	    
	  
		public WebElement clickCheckOutButton() throws Exception
	       {
			name = new Object(){}.getClass().getEnclosingMethod().getName();
	       
	    	   return this.clickElement(By.linkText("Check Out"),name);
	       }

		
		public WebElement typeEmail() throws Exception
	       {
			name = new Object(){}.getClass().getEnclosingMethod().getName();
	       
	    	   return this.clickElement(By.id("email"),name);
	       }
		public WebElement typeUserName() throws Exception
	       {
			name = new Object(){}.getClass().getEnclosingMethod().getName();
	       
	    	   return this.clickElement(By.id("nam"),name);
	       }
}

		