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
import com.seleniumeasy.framework.reporting.ReportUtility;
import com.seleniumeasy.framework.web.WebUIBaseTest;

public class JQueryDatePickerTest extends WebUIBaseTest {

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

		ReportUtility.setReportingData("selectDates", "UI_TC_02_JQuery Date Picker",
				"Verify you should not be able to select From date beyond Jan 1 and To date beyond Feb 1",
				"Select Dates", "Succesful dates selection", "Date selected successfully", "Failed to select dates");

		SeleniumBrowser.launchBrowsernLoadURL();
		ajaxform = new AjaxFormPage();
		jquerydatepicker = new JQueryDatePickerPage();
		jquerydatepicker.waitForElement(By.linkText(jquerydatepicker.datePickersLink));
		ajaxform.CloseAdPopupIfDisplayed();
		jquerydatepicker.clickDatePickers();
		jquerydatepicker.clickJqueryDatePicker();
		jquerydatepicker.verifyDateSelection();

	}

	@AfterClass(alwaysRun = true)
	public static void afterClassScript() throws Exception {
		SeleniumBrowser.deleteFolder();
		SeleniumBrowser.driver.quit();

	}
}
