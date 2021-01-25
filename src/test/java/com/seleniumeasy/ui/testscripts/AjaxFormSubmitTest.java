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
import com.seleniumeasy.framework.reporting.Global;
import com.seleniumeasy.framework.reporting.Utility;
import com.seleniumeasy.framework.web.PageAction;
import com.seleniumeasy.framework.web.WebUIBaseTest;

public class AjaxFormSubmitTest extends WebUIBaseTest {

	private static PageAction pageAction;
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

		Global.sScriptName = "ajaxFormSubmit";
		Global.useCaseName = "UI_TC_01_Ajax Form Submission";
		Global.useCaseDescription = "Verify on clicking submit spinner is shown and shows a success message once the submission is complete";
		Global.curHighLight = true;
		Global.curHeading = "Ajax Form Submit";
		Global.ER = "Succesful Ajax Form Submission";
		Global.EAR = "New Page edited successfully";
		Global.UEAR = "Failed to edit a page";

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
		currentTime = pageAction.getCurrentTimestamp();

		pageAction.waitForElement(By.linkText(ajaxform.inputFormsLink));
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
