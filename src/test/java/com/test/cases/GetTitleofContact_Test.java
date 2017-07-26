package com.test.cases;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.test.core.CreateDriver;
import com.test.pom.contactpage;
import com.test.utility.CommonFunctions;

public class GetTitleofContact_Test extends CreateDriver {

	// Get read from the properties file
	ResourceBundle rb = ResourceBundle.getBundle("Elements"); 
	ResourceBundle basictitle = ResourceBundle.getBundle("Titles");
	
	Logger log = Logger.getLogger("devpinoyLogger"); // To generate the log file

	contactpage cm;
	CommonFunctions cf = new CommonFunctions();

	@Test(priority = 1)
	public void Get_Title_of_Contact_Page() {

		try {
			log.debug("Test Case Started: Get_Title_of_Contact_Page");
			fd.get(rb.getString("url"));
			cm = new contactpage(fd);
			cm.clickonmenu();
			cf.ImplicitWait_inSec(fd, 5);
			cm.clickoncontact();
			String sTitle = cm.gettitle();
			Assert.assertEquals(sTitle, basictitle.getString("ContactPage"));
			log.debug("Test Case Completed: Get_Title_of_Contact_Page");
		} catch (Exception ex) {
			log.debug("Exception occured: " + ex);
		}
	}

	@Test(priority = 2)
	public void Get_Title_of_Home_Page() {

		fd.get(rb.getString("url"));
		System.out.println("Title is: " + fd.getTitle());

	}

	@Test(priority = 3)
	public void Get_Title_of_About_Page() {
		fd.get(rb.getString("url"));
	}

	@Test(priority = 4)
	public void Get_Title_of_About_Page1() {
		fd.get(rb.getString("url"));
	}

}
