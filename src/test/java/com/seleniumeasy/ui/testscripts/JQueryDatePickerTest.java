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
import com.seleniumeasy.ui.testobjects.JQueryDatePickerPage;
import com.seleniumeasy.framework.reporting.Global;
import com.seleniumeasy.framework.reporting.Utility;
import com.seleniumeasy.framework.web.PageAction;
import com.seleniumeasy.framework.web.WebUIBaseTest;

public class JQueryDatePickerTest extends WebUIBaseTest {

	private static PageAction pageAction;
	private static AjaxFormPage ajaxform;
	private static JQueryDatePickerPage jquerydatepicker;

	public JQueryDatePickerTest() throws FileNotFoundException, IOException {
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
	public void selectDates() throws Exception {

		Global.sScriptName = "selectDates";
		Global.useCaseName = "TC_02_JQuery Date Picker";
		Global.useCaseDescription = "Verify you should not be able to select From date beyond Jan 1 and To date beyond Feb 1.";
		Global.curHighLight = true;
		Global.curHeading = "Select Dates";
		Global.ER = "Succesful dates selection";
		Global.EAR = "Date selected successfully";
		Global.UEAR = "Failed to select dates";

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
		jquerydatepicker = new JQueryDatePickerPage();
		currentTime = pageAction.getCurrentTimestamp();
		pageAction.waitForElement(By.linkText(jquerydatepicker.datePickersLink));
		ajaxform.CloseAdPopupIfDisplayed();
		jquerydatepicker.clickDatePickers();
		jquerydatepicker.clickJqueryDatePicker();
		jquerydatepicker.verifyDateSelection();

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
