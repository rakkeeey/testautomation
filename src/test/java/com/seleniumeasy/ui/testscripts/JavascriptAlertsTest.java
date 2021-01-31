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
import com.seleniumeasy.ui.testobjects.JavscriptAlertsPage;
import com.seleniumeasy.ui.testobjects.MultipleWindowsPage;
import com.seleniumeasy.framework.reporting.ReportUtility;
import com.seleniumeasy.framework.web.WebUIBaseTest;

public class JavascriptAlertsTest extends WebUIBaseTest {

	private static AjaxFormPage ajaxform;
	private static MultipleWindowsPage multiplewindows;
	private static JavscriptAlertsPage javascriptalerts;

	public JavascriptAlertsTest() throws FileNotFoundException, IOException {
		logger = new Log4JLogger("AjaxFormPageLogger");
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
	public void javascriptAlertsHandling() throws Exception {

		ReportUtility.setReportingData("javascriptAlertsHandling", "UI_TC_06_Javascript Alerts Handling",
				"Verify if the Javascript alert is shown and close the alert", "Handle Javascript Alerts",
				"Succesful handling of Javascript alerts", "Handled javascript alerts successfully",
				"Failed to handle Javascript alerts");

		SeleniumBrowser.launchBrowsernLoadURL();
		ajaxform = new AjaxFormPage();
		javascriptalerts = new JavscriptAlertsPage();
		multiplewindows = new MultipleWindowsPage();
		multiplewindows.waitForElement(By.linkText(multiplewindows.alertsAndModalsLinkk));
		ajaxform.CloseAdPopupIfDisplayed();
		multiplewindows.clickAlertsAndModalsLink();
		javascriptalerts.clickjavascriptAlertsLink();
		javascriptalerts.clickClickMeButton();
		javascriptalerts.handleAlerts();

	}

	@AfterClass(alwaysRun = true)
	public static void afterClassScript() throws Exception {
		SeleniumBrowser.deleteFolder();
		SeleniumBrowser.driver.quit();

	}
}
