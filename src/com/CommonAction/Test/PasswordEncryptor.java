package com.CommonAction.Test;

import java.util.Base64;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.stepDefinition.BaseClass;

import utility.pom.Constant;

public class PasswordEncryptor  extends BaseClass {

	WebDriver driver;
	static String decodepassword ="aGFwcHltYW4xNjM=";
	public static String getDecodedpassword()
	{
	return new String(Base64.getDecoder().decode(decodepassword.getBytes()));
	}

	@Test
	public void test1()
	{
	System.out.println(getDecodedpassword());
	System.setProperty("webdriver.chrome.driver", Constant.ThirdPartyChromeDriver);
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://accounts.google.com&#8221");
	driver.findElement(By.id("Email")).sendKeys("testerTest@gmail.com");
	driver.findElement(By.id("Passwd")).sendKeys(getDecodedpassword());
	driver.findElement(By.id("signIn")).click();
		

	}

	}
