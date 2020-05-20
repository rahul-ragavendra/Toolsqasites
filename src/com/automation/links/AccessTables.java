package com.automation.links;

import org.testng.annotations.Test;

import com.automation.driver.Driver;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;

public class AccessTables  {
	
 public AccessTables() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}


 
  @Test
  public void tables() throws InterruptedException {
	  TimeUnit.SECONDS.sleep(3);
	  System.out.println(Driver.driver.getTitle());
	  Driver.driver.findElement(Driver.getPageElement("tables", "demo")).click();
	  Driver.driver.findElement(Driver.getPageElement("tables", "table")).click();
  }
  
  
 

  @AfterMethod
  public void afterMethod() {
  }

}
