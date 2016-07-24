package utility.pom;

import java.io.File;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClassPOM {
	
	public WebDriver driver;

	public BaseClassPOM(WebDriver driver)  
	{
		this.driver = driver;
	}
	
	    
	    private static Logger _logger;

	    //Singleton Logger
	    public static Logger log()
	    {
	    	if(_logger == null)
	    		_logger = LogManager.getLogger("utility.pom.BaseLogger");
	    	return _logger;
	    }
	    
  	public void CompareAndVerify(String actualText, String expectedText) throws InterruptedException
  	{
  		Thread.sleep(200);
  		if(actualText.compareTo(expectedText)== 0)
  			
  			log().info("(Compare And Assert) Was Expecting *** " + expectedText + " *** But Found *** " + actualText + " ***  (Compare Result Appeared) ***PASSED***");
  		else
  			log().info("(Compare And Assert) Was Expecting *** " + expectedText + " *** But Found *** " + actualText +  " *** (Compare Result Appeared) ***FAILED***");
  	}
    public void scriptLoad() throws Exception {
        
    	Thread.sleep(600);
        try
        {
        	driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS); 
        }
		catch (TimeoutException timeout)
		{
			driver.manage().timeouts().setScriptTimeout(200, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			Thread.sleep(600);
		 } 
    	   catch (Exception e) 
        {
    		   
    		   log().debug(" scriptLoad " + e.getMessage());
    		   throw(e);
        }
    
    		   
  	}
  	public void waitForElement(final String locator, int timeout, WebDriver driver)
  	{
  		new WebDriverWait(driver,timeout)
  		{
  			
  		}.until (new ExpectedCondition<Boolean>()
  		{
  			
  			public Boolean apply (WebDriver driverObject)
  			{
  				return isElementPresent(locator, driverObject);
  			}
  		});
  	}
  	
    
	
	protected Boolean isElementPresent(String locator, WebDriver driverObject) {
		
		return null;
	}

	public void waitForElementPresent(By locator) 
	{     
	try
	{         
		WebDriverWait wait=new WebDriverWait(driver,50); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); 
	}
	catch(TimeoutException timeout)
	{
		for (int i = 0; i <= 60; i++) 
	{             
	 
			try 
	 {                 
	 boolean exists=false;                
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);                 
	  driver.findElement(locator);                 
	  exists=true;                 
	  if (exists) 
	  {                     
	  break;                 
	  }                  
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);            
	   } 
	   catch (NoSuchElementException e) 
	   {                 
	   if(i==60)
	   {                     
	   throw e; 
	   }
	   }
	}
	}
	}
  	
  	public  void Take_Screenshot( String folder, String filePrefix) 
  	{
  		try
  		{
  		    Thread.sleep(3000);
  			File scrFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
  			String fileName = folder+ "/" + filePrefix + UUID.randomUUID()+ ".png";
  			FileUtils.copyFile(scrFile, new File(fileName),true);
  			System.out.println("GREAT !!! A Screenshot for: " + filePrefix + " taken on  " + new Date() + "  is Stored in "  + folder );
  			Thread.sleep(3000);
  		}
  		catch (Exception ex)
  		{
  			System.out.println("ERROR !!! Screenshot failure at  " + new Date() + " for: " + filePrefix + " => Check your system settings!!!" );
  		}
	}}


