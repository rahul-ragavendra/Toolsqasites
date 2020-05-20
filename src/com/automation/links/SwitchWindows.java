package com.automation.links;

import org.testng.annotations.Test;

import com.automation.driver.Driver;
import com.thoughtworks.selenium.webdriven.commands.GetText;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.ExpectedExceptions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class SwitchWindows extends Driver {
	WebDriverWait wait = null;
	
	public SwitchWindows() throws IOException {
		super();
	}
	
	@BeforeClass()
	public void prerequisites() throws IOException {

		Driver.driver.get(getData("site"));
		Driver.driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Click here")).click();
		
	}
	
	@Test(priority=1)
	public void switchwin() throws InterruptedException, AWTException, IOException {
		TimeUnit.SECONDS.sleep(3);
		Driver.driver.findElement(getPageElement("basic", "demosite")).click();

		//Declaring parent window
		String parentwindow = driver.getWindowHandle();
		System.out.println(parentwindow);
		driver.findElement(getPageElement("basic", "demo")).click();
		TimeUnit.SECONDS.sleep(2);

		//window handling
		Set<String> windows = driver.getWindowHandles();
		for(String childwindow:windows){
			if(!childwindow.equalsIgnoreCase(parentwindow)){
				driver.switchTo().window(childwindow);
				System.out.println(driver.getTitle());
				driver.findElement(getPageElement("basic", "register")).click();
				TimeUnit.SECONDS.sleep(1);
				driver.findElement(getPageElement("basic", "firstname")).sendKeys(getData("firstname"));
				TimeUnit.SECONDS.sleep(1);
				driver.findElement(getPageElement("basic", "lastname")).sendKeys(getData("lastname"));
				TimeUnit.SECONDS.sleep(1);
				driver.findElement(getPageElement("basic", "marital")).click();
				TimeUnit.SECONDS.sleep(1);
				driver.findElement(getPageElement("basic", "hobby")).click();
				TimeUnit.SECONDS.sleep(1);
				driver.findElement(getPageElement("basic", "country")).click();
				TimeUnit.SECONDS.sleep(1);
				driver.findElement(getPageElement("basic", "selectcont")).click();
				TimeUnit.SECONDS.sleep(1);
				driver.findElement(getPageElement("basic", "date")).click();
     			TimeUnit.SECONDS.sleep(1);
				driver.findElement(getPageElement("basic", "dateselect")).click();
				TimeUnit.SECONDS.sleep(1);
				driver.findElement(getPageElement("basic", "month")).click();
				TimeUnit.SECONDS.sleep(1);
				driver.findElement(getPageElement("basic", "monthselect")).click();
				TimeUnit.SECONDS.sleep(1);
				driver.findElement(getPageElement("basic", "year")).click();
				TimeUnit.SECONDS.sleep(1);
				driver.findElement(getPageElement("basic", "yearselect")).click();
				TimeUnit.SECONDS.sleep(1);
				String num = getData("Phone");
				driver.findElement(getPageElement("basic", "phone")).sendKeys(num);
				TimeUnit.SECONDS.sleep(1);
				driver.findElement(getPageElement("basic", "username")).sendKeys(getData("username"));
				TimeUnit.SECONDS.sleep(1);
				driver.findElement(getPageElement("basic", "email")).sendKeys(getData("email"));
				TimeUnit.SECONDS.sleep(1);
				String parent = driver.getWindowHandle();
				driver.findElement(getPageElement("basic", "browse")).click();
				TimeUnit.SECONDS.sleep(1);
				String path = getData("path");
				StringSelection stringselection = new StringSelection(path);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselection,null);
				Robot robot = new Robot();
				robot.mouseMove(233,416);
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				driver.findElement(getPageElement("basic", "desc")).sendKeys(getData("desc"));
				TimeUnit.SECONDS.sleep(1);
				driver.findElement(getPageElement("basic", "password")).sendKeys(getData("password"));
				TimeUnit.SECONDS.sleep(1);
				driver.findElement(getPageElement("basic", "repasswd")).sendKeys(getData("repassword"));
				TimeUnit.SECONDS.sleep(1);
				driver.findElement(getPageElement("basic", "submit")).click();
				TimeUnit.SECONDS.sleep(1);
				driver.close();
				break;
			
			}
		}
		driver.switchTo().window(parentwindow);
		System.out.println(parentwindow);
	}


	 @Test(priority=2)
	  public void switchwindows() throws InterruptedException {
		  TimeUnit.SECONDS.sleep(5);
		  String window = driver.getWindowHandle();
		  System.out.println(Driver.driver.getTitle());
		  driver.findElement(getPageElement("switch", "demo")).click();
		  TimeUnit.SECONDS.sleep(2);
		  driver.findElement(getPageElement("switch", "clickswitch")).click();
		  TimeUnit.SECONDS.sleep(2);
		  driver.findElement(getPageElement("switch", "newbrowser")).click();
		  Set<String> windows = driver.getWindowHandles();
			for(String childwindow:windows){
				if(!childwindow.equalsIgnoreCase(window)){
		          driver.manage().window().maximize();
		          TimeUnit.SECONDS.sleep(5);
		          driver.switchTo().window(window);
		          break;
				}
			}
			
			
	 }
	 

	@AfterMethod
	public void afterMethod() {
		
		
	}

}
