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
import com.seleniumeasy.framework.reporting.ReportUtility;
import com.seleniumeasy.framework.web.WebUIBaseTest;

public class ProgressBarsTest extends WebUIBaseTest {

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

		ReportUtility.setReportingData("progressBars", "UI_TC_03_Progress Bars",
				"Verify it proceeds till 100% and lo the Total time taken for download (in seconds)",
				"Calculate Download Bar Progress Time", "Succesful download bar progress verification",
				"Download bar progress verified successfully", "Failed to validate the progress");

		SeleniumBrowser.launchBrowsernLoadURL();
		ajaxform = new AjaxFormPage();
		progressbars = new ProgressBarsPage();
		progressbars.waitForElement(By.linkText(progressbars.progressBarsLink));
		ajaxform.CloseAdPopupIfDisplayed();
		progressbars.clickProgressBars();
		progressbars.clickbootStrapProgressFormsLink();
		progressbars.clickDownloadButton();
		progressbars.startStopwatch();
		progressbars.startAndVerifyDownloadProgress();
		progressbars.stopStopwatch();

	}

	@AfterClass(alwaysRun = true)
	public static void afterClassScript() throws Exception {

		SeleniumBrowser.deleteFolder();
		SeleniumBrowser.driver.quit();
	}
}
