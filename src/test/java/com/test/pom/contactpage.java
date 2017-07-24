package com.test.pom;

import java.util.ResourceBundle;

//import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class contactpage {
	
	ResourceBundle rb= ResourceBundle.getBundle("Elements");
	//Logger log = Logger.getLogger("devpinoyLogger");
		WebDriver wdr;
		
		public contactpage(WebDriver any_wdr)
		{
			wdr=any_wdr;
		}

		
		public void clickoncontact()
		{
			wdr.findElement(By.xpath(rb.getString("contact_xpath"))).click();;
			//log.debug("Click on Contact link");
		}
		
		public void clickonmenu()
		{
			wdr.findElement(By.xpath(rb.getString("menu_xpath"))).click();;
			//log.debug("Click on Menu link");
		}

		public String gettitle()
		{
			String title= wdr.getTitle();
			return title;
		}

}
