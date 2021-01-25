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
import com.seleniumeasy.framework.reporting.Global;
import com.seleniumeasy.framework.reporting.Utility;
import com.seleniumeasy.framework.web.PageAction;
import com.seleniumeasy.framework.web.WebUIBaseTest;

public class JavascriptAlertsTest extends WebUIBaseTest {

	private static PageAction pageAction;
	private static AjaxFormPage ajaxform;
	private static MultipleWindowsPage multiplewindows;
	private static JavscriptAlertsPage javascriptalerts;

	public JavascriptAlertsTest() throws FileNotFoundException, IOException {
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
	public void javascriptAlertsHandling() throws Exception {

		Global.sScriptName = "javascriptAlertsHandling";
		Global.useCaseName = "UI_TC_06_Javascript Alerts Handling";
		Global.useCaseDescription = "Verify if the Javascript alert is shown and close the alert";
		Global.curHighLight = true;
		Global.curHeading = "Handle Javascript Alerts";
		Global.ER = "Succesful handling of Javascript alerts";
		Global.EAR = "Handled javascript alerts successfully";
		Global.UEAR = "Failed to handle Javascript alerts";

		Utility.reportingResults("Pass", Global.curHeading, Global.EAR, Global.ER);
		Global.curHighLight = false;
		Global.prevHeading = Global.curHeading;
		Global.PER = Global.ER;
		Global.PEAR = Global.EAR;
		Global.PUEAR = Global.UEAR;
		Global.curBC = "";

		SeleniumBrowser.launchBrowsernLoadURL();
		pageAction = new PageAction();
		ajaxform = new AjaxFormPage();
		javascriptalerts = new JavscriptAlertsPage();
		currentTime = pageAction.getCurrentTimestamp();
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
