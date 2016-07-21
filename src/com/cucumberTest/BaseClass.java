package com.cucumberTest;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import com.CommonAction.Test.Constant;


    public class BaseClass {
	protected WebDriver driver;
	
	
	

    public void StartTesting()
    {
    	// here you need to set to the browser you would like to use in constant class.
   	    String browser = Constant.browserType;
	 

	
		try {
		    
			//Different Browser needed to be tested. This check all the Browser settings
				if (browser.equalsIgnoreCase("firefox")) 
				{
					driver = new FirefoxDriver();
					driver.manage().window().maximize();
					driver.get(Constant.URL);
					driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);	
				} else if (browser.equalsIgnoreCase("html")) 
				{
					driver = new HtmlUnitDriver();
					driver.get(Constant.URL);
					driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
				} else if (browser.equalsIgnoreCase("chrome")) 
				{
					System.setProperty("webdriver.chrome.driver", Constant.ThirdPartyChromeDriver);
					driver = new ChromeDriver();
					driver.manage().window().maximize();
					
					driver.get(Constant.URL);
					driver.manage().deleteAllCookies();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				} else if (browser.equalsIgnoreCase("InternetExplorer"))
				{ 
				
					System.setProperty("webdriver.ie.driver", Constant.ThirdPartyInternetExplorerDriver);
					
					DesiredCapabilities cap = DesiredCapabilities
							.internetExplorer();
					// if your IE is not at 100% setting the tests will fail
					cap.setCapability(
							InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
					cap.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
					cap.setCapability("logFile", "/tmp/server.log");
					cap.setCapability("logLevel", "DEBUG");
					cap.setVersion("11");// set version or you can add version
											// to test properties
					driver = new InternetExplorerDriver(cap);
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
					driver.get(Constant.URL);
				}else if (browser.equalsIgnoreCase("ipad"))
				{
					 System.out.println("Starting Browser... ");
				     DesiredCapabilities cap = DesiredCapabilities.ipad();
				     cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true); // -- in case there aren't any certs yet
				     driver = new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"), cap); // IP of  localhost with the IP where the hub will be running, 
				     driver.manage().window().maximize();
					 driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
					 driver.get(Constant.URL);
				}else if(browser.equalsIgnoreCase("PhantomJS")) 
				{
					DesiredCapabilities caps = new DesiredCapabilities();
					caps.setJavascriptEnabled(true); // not really needed: JS enabled by default
					//caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, Constant.ThirdPartyDriver2);
					caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C://Selenium Maven JAR/phantomjs.exe");
					caps.setCapability("takesScreenshot", true);
					driver = new PhantomJSDriver(caps); 
					driver.get(Constant.URL);
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				}
				else
				{
					System.out.println("NO BROWSER SPECIFIED");
				}
		}
		catch (Exception e) 
		{
			 log().info(  " NO Browser Issue " + e.getMessage());
		}
    }
    public  void Take_Screenshot(String folder, String filePrefix) 
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
	    }
		public static  String DateFormat()
			{
			
				  // this generate a date format of type example ==    Jun 15, 2015
				  Date curDate = new Date();
				  String DateToStr = DateFormat.getInstance().format(curDate);
				  DateToStr = DateFormat.getDateInstance().format(curDate);
				
			       return DateToStr;
		}
		
	

        protected void logout() throws Exception {
        driver.close();
        }
        public void scriptLoad() throws Exception {
           
        	Thread.sleep(700);
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
		              
		              
	    		/*driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
	    		Thread.sleep(6000); */  
            }
	    	   
            
        }

       @AfterMethod
       public void tearDown() throws Exception 
       {
    	  logout();
    	  driver.quit();
           
    	 
    	   
       
       //driver.close();
                 
       /*Thread.sleep(30);
       if (driver != null) {
       driver.quit();
       }*/
       }

       @AfterSuite
       public void shutDown() throws Exception 
       {
    	   
    		   // This is may seems not importnat, but Ben wanted to be sure  Nothing is left behind
    		   driver.manage().deleteAllCookies();
    	   
       }
       
    	    
       
       
    

     private static Logger _logger;

     //Singleton Logger
     public static Logger log()
     {
    	 if(_logger == null)
    	   		_logger = LogManager.getLogger("utility.Navigation.BaseLogger");
    	   return _logger;
     }
   
   	
   
     // This may be use to write passed or failed test to log. name is the testcase name   
     public void WriteResultToLog(String actualText, String expectedText, String TestCasename)
     {
    	
    	 //WebDriver driver = null;
    	 if(actualText.compareTo(expectedText)== 0)
    	 {
    		 log().info( "######################################################");
    	     log().info( "Expected: " + expectedText + " But found " + actualText);
    	     log().info( "**** TestCase: " + TestCasename + " == PASSED *****");
    		 log().info            ( "*****"  + new Date() + "******");
    		 log().info( "######################################################");
    	 }
    	 else
    	 {
    		     
    		    log().info( "######################################################");
    			log().info( " Expected was: *** " + expectedText + " *** But found *** " + actualText);
    			log().info( "**** TestCase: " + TestCasename + " = = FAILED ******");
    		    log().info              ( "*****"  + new Date() + "******");
    		    log().info( "######################################################");
    		    
    		   
    	 }
     }
    }
    		
    		    		
    		    
    		    
    		    
    		    
    	 
    	// Screenshot.Take_Screenshot(driver, Constant.Screenshot, "comfirm User are logout after 15 miuntes");

	
	 
	 
	
		
		
		
	
 
		

