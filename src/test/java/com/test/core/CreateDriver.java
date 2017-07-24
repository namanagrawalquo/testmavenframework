package com.test.core;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class CreateDriver {
	
	ResourceBundle rb=ResourceBundle.getBundle("Elements");
	public WebDriver fd;
	Logger log = Logger.getLogger("devpinoyLogger");
	
	@Parameters("browser")
	@BeforeTest
	public void start(String browser) throws InterruptedException
	{
		String dir = System.getProperty("user.dir");
		String userprofile2=System.getProperty("user.name");
		System.out.println("Script is running by: "+userprofile2);
		String file_path = dir+"\\src\\test\\resources\\Drivers";
		String ChromeDriverPath= file_path+"\\chromedriver.exe";
		String FirefoxDriverPath = file_path+"\\geckodriver.exe";
		String IEDriverPath = file_path+"\\IEDriverServer.exe";
		if(browser.equalsIgnoreCase("firefox"))
		{
			//Open Firefox browser
			System.setProperty("webdriver.gecko.driver", FirefoxDriverPath);
			fd = new FirefoxDriver();
			log.debug("Firefox browser started.");
		
		}
		else if(browser.equalsIgnoreCase("chrome"))
		{
			//Open Chrome browser
			System.setProperty("webdriver.chrome.driver", ChromeDriverPath);
			fd = new ChromeDriver();
			fd.manage().window().maximize();
			log.debug("Chrome browser started.");
		}
		else if(browser.equalsIgnoreCase("ie")){
			//Open IE
			System.setProperty("webdriver.ie.driver", IEDriverPath);
			fd = new InternetExplorerDriver();
			fd.manage().window().maximize();
			log.debug("Internet explorer browser started.");
		}
	}
	
	@AfterTest
	public void end()
	{
		fd.close();
		log.debug("Browser closed.");
	}
	

}
