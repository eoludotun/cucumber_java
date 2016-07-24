package utility.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class AppPagesPom  extends BaseClassPOM{
	
	  //This declears all the possible webelement, which in turns represent
	   // all the functions users are expected to perform on the page.
	 
	   /* @FindBy(xpath=".//*[@id='account']/a")
	 
	    private WebElement navigateLogin;
	 
	     
	    @FindBy(xpath="//*[@id='button-1062-btnInnerEl']")
	 
	    private WebElement selectAll;
	    
	    @FindBy(xpath="//*[@id='button-1063-btnInnerEl']")
		 
	    private WebElement deSelectAll;
	    
	    @FindBy(xpath="//*[@id='button-1065-btnInnerEl']")
		 
	    private WebElement refresh;
	    
	    @FindBy(xpath="//*[@id='button-1080-btnEl']")
		 
	    private WebElement assigmentBack;*/
	
	    
	 
	 //this is instantiat the Webdriver from the inheritance class
	  
	  public AppPagesPom(WebDriver driver) 
	  {
			super(driver);
	//This initElements method will create all WebElements
			 
	  //PageFactory.initElements(driver, this);
	  }
		//This is use for the getting the method name, not a big deal
	    // just for reporting purposes
	    private static String name;
	    
	    //This is for recounting the test case steps, not a big deal. but good for report purposes.(Log4j)
	    private static int i = 1;
		private static int x = 0;
		
		
		//this is nothing, but provide a way to write a meaningful message to the log. for debug purposes
		public  void WriteToLog(String msg)
		{
			log().debug(msg);
		}
		public  void ResetStepCounter()
		{
			i = 1;
			x = 0;
		}
		
	   
	    public void clickBillingButton() throws Exception
	    {
	    	x =i++;
	    	name = new Object(){}.getClass().getEnclosingMethod().getName();
	    	try{ 
	    	WebDriverWait wait = new WebDriverWait(driver, 90);	 
	    	
	         wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Check Out")));
	         
	         WebElement navigateLogin = driver.findElement(By.linkText("Check Out"));
	    	if (navigateLogin.isDisplayed()&& navigateLogin.isEnabled())
	    	{
	    		navigateLogin.click();
	    	}
	    	
	        log().debug("Step: " + x +" " + name  + "  => Successful");
	        }catch (Exception e){
	        	Take_Screenshot( Constant.Screenshot, "Failed at " + name);
	              log().debug("Error in Step "+x+" When: " + name  + " => because " + e.getMessage());
	              throw(e);
	        }
	        }
	    	
	    	public void userEmail(String userEmail, String userName) throws Exception
		    {
		    	x =i++;
		    	name = new Object(){}.getClass().getEnclosingMethod().getName();
		    	try{ 
		    	WebDriverWait wait = new WebDriverWait(driver, 90);	 
		    	
		         wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		         
		         WebElement email = driver.findElement(By.id("email"));
		         WebElement name = driver.findElement(By.id("name"));
		    	if (email.isDisplayed()&& email.isEnabled() || name.isDisplayed() && name.isEnabled())
		    	{
		    		email.sendKeys(userEmail);
		    		name.sendKeys(userName);
		    	}
		    	
		        log().debug("Step: " + x +" " + name  + "  => Successful");
		        }catch (Exception e){
		        	Take_Screenshot( Constant.Screenshot, "Failed at " + name);
		              log().debug("Error in Step "+x+" When: " + name  + " => because " + e.getMessage());
		              throw(e);
		        }
		    	
	        }
	    	public void userName(String userName) throws Exception
		    {
		    	x =i++;
		    	name = new Object(){}.getClass().getEnclosingMethod().getName();
		    	try{ 
		    	WebDriverWait wait = new WebDriverWait(driver, 90);	 
		    	
		         wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
		         
		         WebElement name = driver.findElement(By.id("name"));
		    	if (name.isDisplayed()&& name.isEnabled())
		    	{
		    		name.sendKeys(userName);
		    	}
		    	
		        log().debug("Step: " + x +" " + name  + "  => Successful");
		        }catch (Exception e){
		        	Take_Screenshot( Constant.Screenshot, "Failed at " + name);
		              log().debug("Error in Step "+x+" When: " + name  + " => because " + e.getMessage());
		              throw(e);
		        }
		    }
	    }