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
import com.seleniumeasy.framework.reporting.ReportUtility;
import com.seleniumeasy.framework.web.WebUIBaseTest;

public class AjaxFormSubmitTest extends WebUIBaseTest {

	private static AjaxFormPage ajaxform;

	public AjaxFormSubmitTest() throws FileNotFoundException, IOException {
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
	public void ajaxFormSubmit() throws Exception {

		ReportUtility.setReportingData("ajaxFormSubmit", "UI_TC_01_Ajax Form Submission",
				"Verify on clicking submit spinner is shown and shows a success message once the submission is complete",
				"Ajax Form Submit", "Succesful Ajax Form Submission", "New Page edited successfully",
				"Failed to edit a page");

		SeleniumBrowser.launchBrowsernLoadURL();
		ajaxform = new AjaxFormPage();
		ajaxform.waitForElement(By.linkText(ajaxform.inputFormsLink));
		ajaxform.CloseAdPopupIfDisplayed();
		ajaxform.clickInputForms();
		ajaxform.clickAjaxSubmitFormLink();
		ajaxform.enterTitle();
		ajaxform.enterComments();
		ajaxform.clickSubmitButton();
		ajaxform.VerifyIfSpinnerIsDisplayed();
	}

	@AfterClass(alwaysRun = true)
	public static void AfterTestScript() throws Exception {
		SeleniumBrowser.deleteFolder();
		SeleniumBrowser.driver.quit();
	}
}
