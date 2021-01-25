package com.seleniumeasy.ui.testobjects;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.seleniumeasy.framework.reporting.Utility;
import com.seleniumeasy.framework.web.PageAction;

public class MultipleWindowsPage extends PageAction {

	public Log logger;
	public String rangeLabell = "//output[@id='range']";
	public String alertsAndModalsLinkk = "Alerts & Modals";
	public String followTwitAndFbButtonn = "//*[contains(text(), 'Follow Twitter & Facebook')]";
	public String parentWindowHandle;
	public String childWindowHandle;

	@FindBy(linkText = "Alerts & Modals")
	WebElement alertsAndModalsLink;

	@FindBy(linkText = "Window Popup Modal")
	WebElement WindowPopUpModalLink;

	@FindBy(xpath = "//*[contains(text(), 'Follow Twitter & Facebook')]")
	WebElement followTwitAndFbButton;

	public void verifyOpenWindowsAndGetTitles() throws FileNotFoundException, IOException, InterruptedException {

		parentWindowHandle = driver.getWindowHandle();
		clickFollowTwitAndFacebookButton();
		Set<String> allWindowHandles = driver.getWindowHandles();
		int noofwindows = driver.getWindowHandles().size();
		Assert.assertTrue(noofwindows == 3);
		Utility.reportingResults("Pass", "The expected no of open windows are" + noofwindows,
				"The no of windows opened are " + noofwindows,
				"Opened windows and expected windows should be  equal to " + noofwindows);
		logger.info("The no of windows opened are " + noofwindows);
		Iterator<String> itr = allWindowHandles.iterator();
		while (itr.hasNext()) {
			childWindowHandle = itr.next();
			if (!parentWindowHandle.equals(childWindowHandle)) {
				driver.switchTo().window(childWindowHandle);
				logger.info("Title of the active window is " + driver.switchTo().window(childWindowHandle).getTitle());
				Utility.reportingResults("Pass",
						"The expected title of the window is" + driver.switchTo().window(childWindowHandle).getTitle(),
						"Title of the active window is " + driver.switchTo().window(childWindowHandle).getTitle(),
						"The title of the window should be " + driver.switchTo().window(childWindowHandle).getTitle());
				driver.close();
			}
		}
		driver.switchTo().window(parentWindowHandle);
		Utility.reportingResults("Pass",
				"The expected title of the active window after closing both the popups is" + driver.getTitle(),
				"The title of the active window after closing both the popups is " + driver.getTitle(),
				"The title of the active window should be " + driver.getTitle());
		logger.info("The title of the active window after closing both the popups is " + driver.getTitle());
	}

	public MultipleWindowsPage() throws FileNotFoundException, IOException {
		logger = new Log4JLogger("MultipleWindowsPageLogger");
		PageFactory.initElements(driver, this);
	}

	/**
	 * Clicks Input Forms link
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * 
	 */

	public void clickAlertsAndModalsLink() throws FileNotFoundException, IOException {
		clickButton("Alerts and Modals Link", alertsAndModalsLink);

	}

	public void clickWindowPopUpModalLink() throws FileNotFoundException, IOException, InterruptedException {
		clickButton("Window Popup Modal Link", WindowPopUpModalLink);

	}

	public void clickFollowTwitAndFacebookButton() throws FileNotFoundException, IOException, InterruptedException {
		clickButton("Follow Twitter And Facebook", followTwitAndFbButton);
	}

}
