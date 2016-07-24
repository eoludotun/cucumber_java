package utility.pom;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClassPOM_new {

	private WebDriver _driver =  null;

	public BaseClassPOM_new()  
	{
		
	}
	//This is use for the getting the method name, not a big deal
    // just for reporting purposes
    private static String name;
    
    //This is for recounting the test case steps, not a big deal. but good for report purposes.(Log4j)
    private static int i = 1;
	private static int x = 0;
	
	
	public WebDriver getDriver() {
		return _driver;
	  }

	  public void setDriver(WebDriver _driver) {
		this._driver = _driver;
	  }
	  public  void ResetStepCounter()
		{
			i = 1;
			x = 0;
		}
		
	    
	    private static Logger _logger;

	    //Singleton Logger
	    public static Logger log()
	    {
	    	if(_logger == null)
	    		_logger = LogManager.getLogger("utility.pom.BaseLogger");
	    	return _logger;
	    }
	
	    
  protected void CompareAndVerify(String actualText, String expectedText) throws InterruptedException
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
        	_driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS); 
        }
		catch (TimeoutException timeout)
		{
			_driver.manage().timeouts().setScriptTimeout(200, TimeUnit.SECONDS);
			_driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			Thread.sleep(600);
		 } 
    	   catch (Exception e) 
        {
    		   
    		   log().debug(" scriptLoad " + e.getMessage());
    		   throw(e);
        }
    
    		   
  	}
  	protected void waitForElement(final String locator, int timeout, WebDriver driver)
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
		WebDriverWait wait=new WebDriverWait(_driver,50); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); 
	}
	catch(TimeoutException timeout)
	{
		for (int i = 0; i <= 60; i++) 
	{             
	 
			try 
	 {                 
	 boolean exists=false;                
	  _driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);                 
	  _driver.findElement(locator);                 
	  exists=true;                 
	  if (exists) 
	  {                     
	  break;                 
	  }                  
	  _driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);            
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
  	
  	private  void Take_Screenshot( String folder, String filePrefix) 
  	{
  		try
  		{
  		    Thread.sleep(300);
  			File scrFile= ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
  			String fileName = folder+ "/" + filePrefix + UUID.randomUUID()+ ".png";
  			FileUtils.copyFile(scrFile, new File(fileName),true);
  			System.out.println("GREAT !!! A Screenshot for: " + filePrefix + " taken on  " + new Date() + "  is Stored in "  + folder );
  			Thread.sleep(3000);
  		}
  		catch (Exception ex)
  		{
  			System.out.println("ERROR !!! Screenshot failure at  " + new Date() + " for: " + filePrefix + " => Check your system settings!!!" );
  		}
  	}
  	protected boolean waitForPageElement(By locator) {
  		WebDriverWait wait = new WebDriverWait(_driver, 30);

  		ArrayList<java.lang.Class<? extends java.lang.RuntimeException>> exceptions = new ArrayList<java.lang.Class<? extends java.lang.RuntimeException>>();
  		exceptions.add(org.openqa.selenium.NoSuchElementException.class);
  		wait.ignoreAll(exceptions);
  		try {
  			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  			return true;
  		} catch (Exception e) {
  			 //e.printStackTrace();
  		}
  		return false;
  	}
  
  	protected WebElement clickElement(By locator,String name) throws Exception
    {
  	  x =i++;
 	   try
 	   {  
 		  log().debug("Step: " + x +" " + name  + "  => Successful");
 		  waitForPageElement(locator);
 	      return _driver.findElement(locator); 
 	      
       }catch (Exception e){
       	Take_Screenshot( Constant.Screenshot, "Failed at " + name);
             log().debug("Error in Step "+x+" When: " + name  + " => because " + e.getMessage());
             throw(e);
       }
    }
	protected WebElement selectDropDownElement(By locator,By locator2,By locator3, String name,String value) throws Exception
    {
  	  x =i++;
 	   try
 	   {  
 		  log().debug("Step: " + x +" " + name  + "  => Successful");
 		  waitForPageElement(locator);
 		  WebElement dropdown = _driver.findElement(locator);
 		 waitForPageElement(locator2);
 		  List<WebElement> options = dropdown.findElements(locator2);
 		  String selection = "";
 		  for (WebElement option : options) {
 			if (option.getText().contains(value)) {
 				selection = option.getText();
 				break;
 			}
 		}
 		Select subnet = new Select(dropdown);
 		subnet.selectByVisibleText(selection);
 		waitForPageElement(locator3);
 	      return _driver.findElement(locator3); 
 	      
       }catch (Exception e){
       	Take_Screenshot( Constant.Screenshot, "Failed at " + name);
             log().debug("Error in Step "+x+" When: " + name  + " => because " + e.getMessage());
             throw(e);
       }
       }
  	
}
  		



