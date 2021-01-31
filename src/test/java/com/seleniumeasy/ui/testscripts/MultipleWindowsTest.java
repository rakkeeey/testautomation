package com.seleniumeasy.ui.testscripts;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.logging.impl.Log4JLogger;
import org.openqa.selenium.By;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.seleniumeasy.framework.web.SeleniumBrowser;
import com.seleniumeasy.ui.testobjects.AjaxFormPage;
import com.seleniumeasy.ui.testobjects.MultipleWindowsPage;
import com.seleniumeasy.framework.reporting.ReportUtility;
import com.seleniumeasy.framework.web.WebUIBaseTest;

public class MultipleWindowsTest extends WebUIBaseTest {

	private static AjaxFormPage ajaxform;
	private static MultipleWindowsPage multiplewindows;

	public MultipleWindowsTest() throws FileNotFoundException, IOException {
		logger = new Log4JLogger("AjaxFormPageLogger");
		// TODO Auto-generated constructor stub
	}

	@BeforeClass(alwaysRun = true)
	public static void oneTimeSetup() throws Exception {

	}

	@BeforeMethod(alwaysRun = true)
	public void setup() throws Exception {

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {

	}

	@Test // (groups = { "sanity" })
	public void multipleWindowsHandling() throws Exception {

		ReportUtility.setReportingData("multipleWindowsHandling", "UI_TC_05_Multiple Window Handling",
				"Verify both windows are popped up and log the page titles of both the pop-up windows and come back to the test page and print the page title",
				"Verify active window and log the page title", "Succesful verification of active windows",
				"Verified active window and logged the page title successfully", "Failed to verify active windows");

		SeleniumBrowser.launchBrowsernLoadURL();
		ajaxform = new AjaxFormPage();
		multiplewindows = new MultipleWindowsPage();
		multiplewindows.waitForElement(By.linkText(multiplewindows.alertsAndModalsLinkk));
		ajaxform.CloseAdPopupIfDisplayed();
		multiplewindows.clickAlertsAndModalsLink();
		multiplewindows.clickWindowPopUpModalLink();
		multiplewindows.verifyOpenWindowsAndGetTitles();

	}

	@AfterClass(alwaysRun = true)
	public static void afterClassScript() throws Exception {
		SeleniumBrowser.deleteFolder();
		SeleniumBrowser.driver.quit();

	}
}
