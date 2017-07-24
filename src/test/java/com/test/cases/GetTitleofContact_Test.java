package com.test.cases;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.test.core.CreateDriver;
import com.test.pom.contactpage;

public class GetTitleofContact_Test extends CreateDriver{

	ResourceBundle rb = ResourceBundle.getBundle("Elements"); // Get elements of web page from property file
	Logger log = Logger.getLogger("devpinoyLogger"); // To generate the log file
	String dir = System.getProperty("user.dir");
	String file_path = dir+"\\datafile\\Form.xls";
	String ScreenShot_path = dir+"\\ScreenShot\\";

	contactpage cm;


	@Test(priority = 2)
	public void getTitleofContactPage() {

		try{
			log.debug("Test Case Started: getTitleofContactPage");
			cm =new contactpage(fd);
			fd.get(rb.getString("url"));
			cm.clickonmenu();
			fd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			cm.clickoncontact();
			String sTitle=cm.gettitle();
			System.out.println("Contact Page Title is: "+sTitle);
			log.debug("getTitleofContactPage completed.");
		}
		catch(Exception ex)
		{
			log.debug("Exception occured: "+ex);
		}
	}

	/*@Test(priority=1)
	public void getTitleofHomePage()
	{
		//log.debug("getTitleofHomePage started.");
		fd.get(rb.getString("url"));
		Homepage_methods hm=new Homepage_methods(fd);
		String sTitlehome=hm.gettitleofhome();
		System.out.println("Home Page title is: "+sTitlehome);
		log.debug("getTitleofHomePage completed.");
	}
	 */

}
