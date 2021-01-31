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
import com.seleniumeasy.ui.testobjects.FileDownloadPage;
import com.seleniumeasy.ui.testobjects.MultipleWindowsPage;
import com.seleniumeasy.framework.reporting.ReportUtility;
import com.seleniumeasy.framework.web.WebUIBaseTest;

public class FileDownloadTest extends WebUIBaseTest {

	private static AjaxFormPage ajaxform;
	private static MultipleWindowsPage multiplewindows;
	private static FileDownloadPage filedownload;

	public FileDownloadTest() throws FileNotFoundException, IOException {
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
	public void fileDownload() throws Exception {

		ReportUtility.setReportingData("fileDownload", "UI_TC_07_File Download And Verify",
				"Verify file download anf if the content in the file matches with the content entered",
				"Verify File Download", "Succesful verification of downloaded file",
				"Downloaded file and verified the same successfully", "Failed to download the file");

		SeleniumBrowser.launchBrowsernLoadURL();
		ajaxform = new AjaxFormPage();
		filedownload = new FileDownloadPage();
		multiplewindows = new MultipleWindowsPage();
		multiplewindows.waitForElement(By.linkText(multiplewindows.alertsAndModalsLinkk));
		ajaxform.CloseAdPopupIfDisplayed();
		multiplewindows.clickAlertsAndModalsLink();
		filedownload.clickFileDownloadLink();
		filedownload.enterTextContent();
		filedownload.clickCreateButton();
		filedownload.clickLinkToDownload();
		filedownload.readAndVerifyDownloadedTextFile();

	}

	@AfterClass(alwaysRun = true)
	public static void afterClassScript() throws Exception {
		SeleniumBrowser.deleteFolder();
		SeleniumBrowser.driver.quit();
	}
}
