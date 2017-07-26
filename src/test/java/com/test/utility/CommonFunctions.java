package com.test.utility;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class CommonFunctions {

	Logger log = Logger.getLogger("devpinoyLogger");

	public void ImplicitWait_inSec(WebDriver webdriver, long time) {
		webdriver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		log.debug("Implicit Wait: " + time + "sec.");
	}

}
