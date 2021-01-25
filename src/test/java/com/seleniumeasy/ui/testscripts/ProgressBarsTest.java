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
import com.seleniumeasy.ui.testobjects.ProgressBarsPage;
import com.seleniumeasy.framework.reporting.Global;
import com.seleniumeasy.framework.reporting.Utility;
import com.seleniumeasy.framework.web.PageAction;
import com.seleniumeasy.framework.web.WebUIBaseTest;

public class ProgressBarsTest extends WebUIBaseTest {

	private static PageAction pageAction;
	private static AjaxFormPage ajaxform;
	private static ProgressBarsPage progressbars;

	public ProgressBarsTest() throws FileNotFoundException, IOException {
		logger = new Log4JLogger("JQueryDatePickerPageLogger");
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
	public void progressBars() throws Exception {

		Global.sScriptName = "progressBars";
		Global.useCaseName = "UI_TC_03_Progress Bars";
		Global.useCaseDescription = "Verify it proceeds till 100% and lo the Total time taken for download (in seconds)";
		Global.curHighLight = true;
		Global.curHeading = "Calculate Download Bar Progress Time";
		Global.ER = "Succesful download bar progress verification";
		Global.EAR = "Download bar progress verified successfully";
		Global.UEAR = "Failed to validate the progress";

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
		progressbars = new ProgressBarsPage();
		currentTime = pageAction.getCurrentTimestamp();

		pageAction.waitForElement(By.linkText(progressbars.progressBarsLink));
		ajaxform.CloseAdPopupIfDisplayed();
		progressbars.clickProgressBars();
		progressbars.clickbootStrapProgressFormsLink();
		progressbars.clickDownloadButton();
		pageAction.startStopwatch();
		progressbars.startAndVerifyDownloadProgress();
		pageAction.stopStopwatch();
		

	}

	@AfterClass(alwaysRun = true)
	public static void afterClassScript() throws Exception {

		SeleniumBrowser.deleteFolder();
		SeleniumBrowser.driver.quit();
	}
}
