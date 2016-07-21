package com.CommonAction.Test;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot 
{
	public static  void Take_Screenshot(WebDriver driver, String folder, String filePrefix) 
{
	try
	{
	    Thread.sleep(3000);
		File scrFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String fileName = folder+"/"+filePrefix +UUID.randomUUID()+".png";
		FileUtils.copyFile(scrFile, new File(fileName),true);
		System.out.println("GREAT !!! A Screenshot for: " + filePrefix + " taken on  " + new Date() + "  is Stored in "  + folder );
		Thread.sleep(3000);
	}
	catch (Exception ex)
	{
		System.out.println("ERROR !!! taking a Screenshot on  " + new Date() + " for: " + filePrefix + " => Check your system settings!!!" );
	}
	
}

}