package com.test.pom;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.elements.HyperLinks;

public class contactpage {

	Logger log = Logger.getLogger("devpinoyLogger");
	WebDriver wdr;

	@FindBy(xpath = HyperLinks.contact_xpath)
	public WebElement contact_link;

	@FindBy(xpath = HyperLinks.menu_link)
	public WebElement menu_link;

	public contactpage(WebDriver any_wdr) {
		wdr = any_wdr;
		PageFactory.initElements(any_wdr, this);
	}

	public void clickoncontact() {
		log.debug("Click on Contact link");
		contact_link.click();
	}

	public void clickonmenu() {
		log.debug("Click on Menu link");
		menu_link.click();
	}

	public String gettitle() {
		String title = wdr.getTitle();
		return title;
	}

}
