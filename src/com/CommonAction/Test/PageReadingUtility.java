package com.CommonAction.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import com.thoughtworks.selenium.Selenium;

public class PageReadingUtility 
{
	
	WebDriver driver;
	

	public void click(String args) {
		String[] items = args.split(" ");
		System.out.println(items.length);
		if (items.length >= 2) {
			try {
				By find = null;
				if (items[0].toLowerCase().contains("xpath")) {
					String xpath = "";
					System.out.println(items.length);
					for(int i = 1;i< items.length;i++) { 
						xpath += items[i] + " ";
					}
					find = By.xpath(xpath);
				} else if (items[0].toLowerCase().contains("id")) {
					find = By.id(items[1]);
				} else if (items[0].toLowerCase().contains("name")) {
					find = By.name(items[1]);
				}
				WebElement element = driver.findElement(find);
				element.click();
			} catch (Exception e) {
				System.out.println("Exception " + e.getMessage());
			}
		} else {
			System.out
					.println("Incorrect parameters expect : click how(xpath,id,name) identifier \nexample click xpath //button[@name='something'] ");
		}
	}

	public void sendKeys(String args) {
		String[] items = args.split(" ");
		System.out.println(items.length);
		if (items.length >= 3) {
			try {
				By find = null;
				if (items[0].toLowerCase().contains("xpath")) {
					find = By.xpath(items[1]);
				} else if (items[0].toLowerCase().contains("id")) {
					find = By.id(items[1]);
				} else if (items[0].toLowerCase().contains("name")) {
					find = By.name(items[1]);
				}
				WebElement element = driver.findElement(find);
				element.sendKeys(items[2]);
			} catch (Exception e) {
				System.out.println("Exception " + e.getMessage());
			}
		} else {
			System.out
					.println("Incorrect parameters expect : sendKeys how(xpath,id,name) identifier keysToSend \nexample sendKeys xpath //input[] something");
		}
	}
	public void printLinks() { 
		List<WebElement> elements = driver.findElements(By.tagName("a"));
		for(WebElement current:elements) { 
			System.out.println(current.getText());
		}
	}

	public void mouseOver(String elementId) {
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.id(elementId)));
		act.perform();
	}

	public void getAttribute(String args) {
		String[] arg = args.split("[ ]");
		WebElement element = driver.findElement(By.xpath(arg[0]));
		System.out.println(element.getAttribute(arg[1]));
	}
	public void getText(String args) {
		String[] arg = args.split("[ ]");
		WebElement element = driver.findElement(By.xpath(arg[0]));
		System.out.println(element.getText());
	}
	public void isEnabled(String args) {
		String[] arg = args.split("[ ]");
		WebElement element = driver.findElement(By.xpath(arg[0]));
		System.out.println(element.isEnabled());
	}
	
	public void switchFrame(String frameName) { 
		driver.switchTo().frame(frameName);
	}

	public void select(String args) {
		String[] arg = args.split("[ ]");
		/*if(arg.length < 2) { 
			System.out.println("Useage : select id(of selector) textToSelect(visble text)");
		}*/
		WebElement element = driver.findElement(By.xpath(arg[0]));
		Select sel = new Select(element);
		sel.deselectByIndex(4);
		System.out.println(element.getAttribute(arg[1]));
	}
	
	/**
	 * This will take a id,name, or xpath value and get and then print the
	 * element details.
	 * 
	 * @param args
	 */

	public void waitForEntry() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String userName = br.readLine();
			if (userName.equals("quit")) {
				driver.quit();
				System.exit(0);
			}  else if(userName.contains("acceptPopup"))  { 
				acceptPopup();
			
			
				}///RequestCenter/refactor/common/images/default/s.gif
				//click xpath //img[contains(@src,'s.gif')]
				//click xpath //table[contains(@class,'row-table')]//div[contains(@class,'Date')] 

			else if (userName.contains("switchWindow ")) {
				String arg = userName.replace("switchWindow ", "");
				selectWindow(arg);
			} else if (userName.contains("switchFrame")) {
				String arg = userName.replace("switchFrame", "");
				selectFrame(arg);
			} else if (userName.contains("getWindowNames")) {
				Set<String> windowSet = driver.getWindowHandles();
				for (String cur : windowSet) {
					System.out
							.println(driver.switchTo().window(cur).getTitle());
				}
			} else if (userName.contains("sendKeys")) {
				String withoutCommand = userName.replace("sendKeys ", "");
				sendKeys(withoutCommand);
			} else if (userName.contains("source")) {
				try { 
				System.out.println(driver.getPageSource());
					PrintWriter out = new PrintWriter("/Desktop/sourceCode.html");
				    out.println(driver.getPageSource());
				    out.close();
				} catch(Exception e) { 
					
				}
			} else if (userName.toLowerCase().contains("click")) {
				String withoutCommand = userName.replace("click ", "");
				click(withoutCommand);
			}else if (userName.equals("getDivs")) {
					getDivs();
		   
			} else if (userName.toLowerCase().contains("printElementInfo")) {
				//String withoutCommand = userName.replace("click ", "");

			} else if (userName.toLowerCase().contains("script")) {
				String withoutCommand = userName.replace("script", "");
				executeJavaScript(withoutCommand);

			
			} else if (userName.contains("mouseOver")) {
				String withoutCommand = userName.replace("mouseOver ", "");
				this.mouseOver(withoutCommand);

			} else if (userName.contains("getAttribute")) {
				String withoutCommand = userName.replace("getAttribute ", "");
				getAttribute(withoutCommand);
			}else if (userName.contains("getText")) {
				String withoutCommand = userName.replace("getText ", "");
				getText(withoutCommand);
			} else if (userName.contains("isEnabled")) {
				String withoutCommand = userName.replace("isEnabled ", "");
				this.isEnabled(withoutCommand);
			
			} else if (userName.contains("select")) {
				String withoutCommand = userName.replace("select ", "");
				this.select(withoutCommand);
//aproptargetPrimaryInstIDDIV class=""
			} else if (userName.contains("printLinks")) { 
				printLinks();
			}else if (userName.contains("switchFrame")) {
				String withoutCommand = userName.replace("switchFrame ", "");
				switchFrame(withoutCommand);
			} else {
				System.out
						.println("Unknown Command, if you need help, use help command");
			}
		} catch (IOException ex) {
			System.exit(1);
		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
	}//serviceForm.Grid  document.getElementById('Control.Debug')

	private void executeJavaScript(String script) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			System.out.println(js.executeScript(script));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void acceptPopup() { 
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	private void selectWindow(String windowName) {
		WebDriver popup = null;
		Set<String> windowSet = driver.getWindowHandles();
		for (String window : windowSet) {
			popup = driver.switchTo().window(window);
			if (popup.getTitle().equals(windowName)) {
				break;
			}
		}
	}
	
	private void selectFrame(String windowName) {
		WebElement elem = driver.findElement(By.xpath(windowName));
		driver.switchTo().frame(elem);
	}
	

	private void getDivs() {
		List<WebElement> divs = driver.findElements(By.tagName("div"));
		System.out.println(divs.size());
		for(WebElement current:divs) { 
			if(current.getText().contains("1/25/2011")) { 
				current.click();
			}
			}
			}
		
		}