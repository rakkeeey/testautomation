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
import com.seleniumeasy.framework.reporting.Global;
import com.seleniumeasy.framework.reporting.Utility;
import com.seleniumeasy.framework.web.PageAction;
import com.seleniumeasy.framework.web.WebUIBaseTest;

public class FileDownloadTest extends WebUIBaseTest {

	private static PageAction pageAction;
	private static AjaxFormPage ajaxform;
	private static MultipleWindowsPage multiplewindows;
	private static FileDownloadPage filedownload;

	public FileDownloadTest() throws FileNotFoundException, IOException {
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
	public void fileDownload() throws Exception {

		Global.sScriptName = "fileDownload";
		Global.useCaseName = "TC_07_File Download And Verify";
		Global.useCaseDescription = "Verify file download anf if the content in the file matches with the content entered";
		Global.curHighLight = true;
		Global.curHeading = "Verify File Download";
		Global.ER = "Succesful verification of downloaded file";
		Global.EAR = "Downloaded file and verified the same successfully";
		Global.UEAR = "Failed to download the file";

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
		filedownload = new FileDownloadPage();
		currentTime = pageAction.getCurrentTimestamp();
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
		Global.sScriptName = "afterClassScript";
		Global.useCaseName = "After Class Script";
		Global.useCaseDescription = "";
		Global.curHighLight = true;
		Global.curHeading = "Stop script";
		Global.ER = "Succesful execution of after suite method";
		Global.EAR = "After suite method executed successfully";
		Global.UEAR = "After suite method failed to execute";

		Utility.reportingResults("Pass", Global.curHeading, Global.EAR, Global.ER);
		Global.curHighLight = false;
		Global.prevHeading = Global.curHeading;
		Global.PER = Global.ER;
		Global.PEAR = Global.EAR;
		Global.PUEAR = Global.UEAR;

	}
}
