package com.test.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.test.utility.ExtentManager;

public class CreateDriver {

	ResourceBundle rb = ResourceBundle.getBundle("Elements");
	public WebDriver fd;
	Logger log = Logger.getLogger("devpinoyLogger");
	String dir = System.getProperty("user.dir");
	Path sceenshot = null;
	
	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public ExtentHtmlReporter htmlReporter;

	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.createInstance("extent.html");
		htmlReporter = new ExtentHtmlReporter("extent.html");
		extent.attachReporter(htmlReporter);
	}

	@BeforeClass
	public void beforeClass() {
		ExtentTest parent = extent.createTest(getClass().getName());
		parentTest.set(parent);
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		ExtentTest child = (parentTest.get()).createNode(method.getName());
		test.set(child);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			((ExtentTest) test.get()).fail(result.getThrowable());
			captureScreenShot(result,"fail");
		} else if (result.getStatus() == ITestResult.SKIP) {
			((ExtentTest) test.get()).skip(result.getThrowable());
		} else {
			((ExtentTest) test.get()).pass("Test Passed");
			captureScreenShot(result,"pass");
		}
		extent.flush();
	}

	@Parameters("browser")
	@BeforeTest
	public void start(String browser) throws InterruptedException {
		String dir = System.getProperty("user.dir");
		String userprofile2 = System.getProperty("user.name");
		System.out.println("Script is running by: " + userprofile2);
		String file_path = dir + "\\src\\test\\resources\\Drivers";
		String ChromeDriverPath = file_path + "\\chromedriver.exe";
		String FirefoxDriverPath = file_path + "\\geckodriver.exe";
		String IEDriverPath = file_path + "\\IEDriverServer.exe";
		if (browser.equalsIgnoreCase("firefox")) {
			// Open Firefox browser
			System.setProperty("webdriver.gecko.driver", FirefoxDriverPath);
			fd = new FirefoxDriver();
			log.debug("Firefox browser started.");

		} else if (browser.equalsIgnoreCase("chrome")) {
			// Open Chrome browser
			System.setProperty("webdriver.chrome.driver", ChromeDriverPath);
			fd = new ChromeDriver();
			fd.manage().window().maximize();
			log.debug("Chrome browser started.");
		} else if (browser.equalsIgnoreCase("ie")) {
			// Open IE
			System.setProperty("webdriver.ie.driver", IEDriverPath);
			fd = new InternetExplorerDriver();
			fd.manage().window().maximize();
			log.debug("Internet explorer browser started.");
		}
	}

	@AfterTest
	public void end() {
		fd.close();
		log.debug("Browser closed.");
	}

	public void captureScreenShot(ITestResult result, String status) {
		try {
			String destDir = "";
			String passfailMethod = result.getMethod().getRealClass().getSimpleName() + "."
					+ result.getMethod().getMethodName();
			// To capture screenshot.
			File scrFile = ((TakesScreenshot) fd).getScreenshotAs(OutputType.FILE);
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
			// If status = fail then set folder name "screenshots/Failures"
			if (status.equalsIgnoreCase("fail")) {
				destDir = "ScreenShots/Failures";
			}
			// If status = pass then set folder name "screenshots/Success"
			else if (status.equalsIgnoreCase("pass")) {
				destDir = "ScreenShots/Success";
			}

			// To create folder to store screenshots
			new File(destDir).mkdirs();
			// Set file name with combination of test class name + date time.
			String destFile = passfailMethod + " - " + dateFormat.format(new Date()) + ".png";

			// Store file at destination folder location
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
