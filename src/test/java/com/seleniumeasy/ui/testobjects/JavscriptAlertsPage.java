package com.seleniumeasy.ui.testobjects;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.seleniumeasy.framework.reporting.ReportUtility;
import com.seleniumeasy.framework.web.PageAction;

public class JavscriptAlertsPage extends PageAction {

	public Log logger;
	public String alertsAndModalsLinkk = "Alerts & Modals";

	@FindBy(linkText = "Javascript Alerts")
	WebElement javascriptAlertsLink;

	@FindBy(xpath = "//button[@onclick='myAlertFunction()']")
	WebElement clickMeButton;

	public JavscriptAlertsPage() throws FileNotFoundException, IOException {
		logger = new Log4JLogger("MultipleWindowsPageLogger");
		PageFactory.initElements(driver, this);
	}

	public void clickjavascriptAlertsLink() throws FileNotFoundException, IOException, InterruptedException {
		clickButton("Javascript Alerts Link", javascriptAlertsLink);
	}

	public void clickClickMeButton() throws FileNotFoundException, IOException, InterruptedException {
		clickButtonJS("Click Me Button", clickMeButton);
	}

	public void handleAlerts() throws FileNotFoundException, IOException, InterruptedException {
		try {
			Assert.assertTrue(isAlertPresentAndAcceptAlert());
		} catch (AssertionError e) {
			ReportUtility.reportingResults("Fail", "Verify if The javascript alert is shown and handled by the script",
					"Java script alert is not shown and not accepted by the script ",
					"The javascript alert should be shown and handled by the script");
			throw e;
		}
		ReportUtility.reportingResults("Pass", "Verify if The javascript alert is shown and handled by the script",
				"Java script alert is shown and accepted by the script ",
				"The javascript alert should be shown and handled by the script");
	}

}
