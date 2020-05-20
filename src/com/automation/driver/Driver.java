package com.automation.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Driver {
	
	public Driver() throws IOException {
		System.out.println("Driver initialised");
		init();
	}

	public static ChromeDriver driver = null;
	public static void init() throws IOException 
	{	  
		Properties property = new Properties();
		FileInputStream file = new FileInputStream("src//data//config.properties");
		property.load(file); 
		System.out.println(property);
		String browser = property.getProperty("browser");
//		if(browser.equalsIgnoreCase("ff"))
//		{
//			 driver = new FirefoxDriver();
//			 driver.manage().window().maximize();
//		}
		if(browser.equalsIgnoreCase("chrome"))
		{
			 String path = property.getProperty("chromepath");
			 System.setProperty("webdriver.chrome.driver", path);
			driver=new ChromeDriver();
		}
//		else if(browser.equalsIgnoreCase("ie"))
//		{
//			 String path = property.getProperty("iepath");
//			 System.setProperty("webdriver.ie.driver", path);
//				driver=new InternetExplorerDriver();
//		}
	}
	public static By getPageElement(String pageName, String locatorName) {
		By objectName = null;
		try {
			File fXmlFile = new File("src//repository//objects.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = (Document) builder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList pageNameList = doc.getElementsByTagName(pageName);
			for (int i = 0; i < pageNameList.getLength(); ++i) {
				Element ElementName = (Element) pageNameList.item(i);
				NodeList locatorList = ElementName
						.getElementsByTagName("locator");
				for (int j = 0; j < locatorList.getLength(); ++j) {
					Element locator = (Element) locatorList.item(j);
					String name = locator.getAttribute("name");
					if (name.equals(locatorName)) {

						String value = locator.getAttribute("value");
						String type = locator.getAttribute("type");
						if (type.equalsIgnoreCase("id")) {
							objectName = By.id(value);
						} else if (type.equalsIgnoreCase("css")) {
							objectName = By.cssSelector(value);
						} else if (type.equalsIgnoreCase("className")) {
							objectName = By.className(value);
						} else if (type.equalsIgnoreCase("xpath")) {
							objectName = By.xpath(value);
						} else if (type.equalsIgnoreCase("linkText")) {
							objectName = By.linkText(value);
						} else if (type.equalsIgnoreCase("name")) {
							objectName = By.name(value);
							System.out.println(objectName);
						} else if (type.equalsIgnoreCase("partialLinkText")) {
							objectName = By.partialLinkText(value);
						} else if (type.equalsIgnoreCase("tagName")) {
							objectName = By.tagName(value);
						}

						break;
					}
				}
				if (objectName == null) {
					Assert.fail("Element not found in object file");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectName;
	}
	public static String getData(String key) throws IOException 
	{
		Properties property = new Properties();
		try {
			property.load(new FileInputStream("src//data//data.properties"));
		} catch (IOException e) {
			System.out.println("Unable to load Data");
		}
		return property.getProperty(key);
	}

}
