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
import com.seleniumeasy.ui.testobjects.SliderBarsPage;
import com.seleniumeasy.framework.reporting.Global;
import com.seleniumeasy.framework.reporting.Utility;
import com.seleniumeasy.framework.web.PageAction;
import com.seleniumeasy.framework.web.WebUIBaseTest;

public class SliderBarsTest extends WebUIBaseTest {

	private static PageAction pageAction;
	private static AjaxFormPage ajaxform;
	private static SliderBarsPage sliderbars;
	private static ProgressBarsPage progressbars;

	public SliderBarsTest() throws FileNotFoundException, IOException {
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
	public void setSlidersToFifty() throws Exception {

		Global.sScriptName = "setSlidersToFifty";
		Global.useCaseName = "UI_TC_04_Set Sliders to Fifty";
		Global.useCaseDescription = "Set all sliders to 50% and validate values are set to 50%";
		Global.curHighLight = true;
		Global.curHeading = "Set all Sliders to Fifty";
		Global.ER = "Succesful slider set to Fifty";
		Global.EAR = "All sliders set to Fifty successfully";
		Global.UEAR = "Failed to set all sliders to Fifty";

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
		sliderbars = new SliderBarsPage();
		progressbars = new ProgressBarsPage();

		pageAction.waitForElement(By.linkText(ajaxform.inputFormsLink));
		pageAction.waitForElement(By.linkText(progressbars.progressBarsLink));
		ajaxform.CloseAdPopupIfDisplayed();
		progressbars.clickProgressBars();
		sliderbars.clickDragAndDropSlidersLink();
		pageAction.waitForElement(By.xpath(sliderbars.rangeLabell));
		sliderbars.setAllSliders();

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
