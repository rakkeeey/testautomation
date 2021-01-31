package com.seleniumeasy.framework.web;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.logging.impl.Log4JLogger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.seleniumeasy.framework.web.SeleniumBrowser;
import com.seleniumeasy.framework.reporting.FrameworkException;
import com.seleniumeasy.framework.reporting.ReportUtility;

/**
 * This has the Wrapper Methods to perform actions in UI
 * 
 * @author Rakesh
 *
 */
public class PageAction extends SeleniumBrowser {

	JavascriptExecutor jsExe;
	public WebDriverWait wait = new WebDriverWait(SeleniumBrowser.driver, TIMEOUT);
	public StopWatch sw;

	// Seconds to wait for timeout
	public static final int TIMEOUT = 60;

	public PageAction() throws FileNotFoundException, IOException {
		jsExe = (JavascriptExecutor) SeleniumBrowser.driver;
		logger = new Log4JLogger("PageAction");
		sw = new StopWatch();
	}

	public void readAndVerifyDownloadedTextFile(String fileLocation, String ExpectedText) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader(fileLocation));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String everything = sb.toString().trim();
			logger.debug("The content present in the file is " + everything);

			try {
				Assert.assertTrue(everything.equals(ExpectedText));
			} catch (AssertionError e) {
				ReportUtility.reportingResults("Fail", "Content of the file",
						"The content present in the file is " + everything,
						"Content in the file and input provided by the user should be the same");
				throw e;
			}
			ReportUtility.reportingResults("Pass", "Content of the file",
					"The content present in the file is " + everything,
					"Content in the file and input provided by the user should be the same");
		} finally {
			br.close();
		}

	}

	public void datePicker(String date, String month, String year) {
		try {
			Actions a = new Actions(driver);
			Assert.assertEquals(driver.findElement(By.cssSelector(".ui-datepicker-year")).getText(), year);
			Select MonthOne = new Select(driver.findElement(By.cssSelector(".ui-datepicker-month")));
			MonthOne.selectByVisibleText(month);
			Thread.sleep(2000);
			a.moveToElement(driver.findElement(By.linkText(date))).click().build().perform();
			ReportUtility.reportingResults("Pass", "Select Date",
					"Successfully selected the date " + date + "-" + month + "-" + year,
					"User should be able to select the date");
		} catch (Exception exception) {
			ReportUtility.reportingResults("Fail", "Select Date",
					"Failed to select the date " + date + "-" + month + "-" + year,
					"User should be able to select the date");
			new FrameworkException(exception, "datePicker");
		}
	}

	public boolean isAlertPresentAndAcceptAlert() throws InterruptedException {
		try {
			Thread.sleep(2000);
			Alert alert = driver.switchTo().alert();
			alert.accept();

			return true;
		} catch (Exception exception) {
			new FrameworkException(exception, "isAlertPresentAndAcceptAlert");
			return false;
		}
	}

	public void assertTruePercentage(WebElement Element, String Expected) {
		
		try {
			Assert.assertTrue(Element.getText().contains(Expected));

		} catch (AssertionError e) {
			ReportUtility.reportingResults("Fail", "The expected download percentage is" + Expected,
					"Displayed download and expected download percentage is not equal" ,
					"Displayed download and expected download percentage should be  equal to " + Expected);
			throw e;
		}
		ReportUtility.reportingResults("Pass", "Verify if the expected slide range is" + Expected,
				"Displayed slide range and expected slider range is equal to " + Expected,
				"Displayed slide range and expected slider range should be  equal to " + Expected);

	}

	public void assertRangeSliderPositions(WebElement Element, String Expected) {

		try {
			Assert.assertTrue(Element.getText().contains(Expected));

		} catch (AssertionError e) {
			ReportUtility.reportingResults("Fail", "Verify if the expected slide range is " + Expected,
					"Displayed slide range and expected slider range is not equal to " + Expected,
					"Displayed slide range and expected slider range should be  equal to " + Expected);
			throw e;
		}
		ReportUtility.reportingResults("Pass", "Verify if the expected slide range is" + Expected,
				"Displayed slide range and expected slider range is equal to " + Expected,
				"Displayed slide range and expected slider range should be  equal to " + Expected);
	}

	public void startStopwatch() {
		sw.start();
	}

	public boolean isElementPresent(By locatorKey) {
		try {
			driver.findElement(locatorKey);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void stopStopwatch() throws Exception {
		sw.stop();
		ReportUtility.reportingResults("Pass", "Calculate the time taken for the Download ",
				"The time taken for the download progress is " + sw.getTime(TimeUnit.SECONDS) + " seconds",
				"User should be able to calculate the response time");
	}

	/**
	 * Enter value in the text field
	 * 
	 * @param fieldName
	 * @param value
	 * @param element
	 */
	public void enterText(String fieldName, String value, WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			element.clear();
			element.sendKeys(value);
			ReportUtility.reportingResults("Pass", "Enter the value '" + value + "' in " + fieldName + " field",
					"Successfully enter the value '" + value + "' in " + fieldName + " field",
					"User should be able to enter the value");
		} catch (Exception exception) {
			ReportUtility.reportingResults("Fail", "Enter the value '" + value + "' in " + fieldName + " field",
					"Failed to enter the value '" + value + "' in " + fieldName + " field",
					"User should be able to enter the value");
			new FrameworkException(exception, "enterText");
		}
	}

	/**
	 * To Click Button Action
	 * 
	 * @param fieldName
	 * @param element
	 */
	public void clickButton(String fieldName, WebElement element) {

		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();
			ReportUtility.reportingResults("Pass", "Click  '" + fieldName + "' button",
					"Successfully clicked the '" + fieldName + "' button",
					"User should be able to click on the button");
		} catch (Exception exception) {
			ReportUtility.reportingResults("Fail", "Click  '" + fieldName + "' button",
					"Failed to click the '" + fieldName + "'  button", "User should be able to click on the button");
			new FrameworkException(exception, "clickButton");
		}
	}

	public void clickButtonJS(String fieldName, WebElement element) {

		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			ReportUtility.reportingResults("Pass", "Click  '" + fieldName + "' button",
					"Successfully clicked the '" + fieldName + "' button",
					"User should be able to click on the button");
		} catch (Exception exception) {
			ReportUtility.reportingResults("Fail", "Click  '" + fieldName + "' button",
					"Failed to click the '" + fieldName + "'  button", "User should be able to click on the button");
			new FrameworkException(exception, "clickButtonJS");
		}
	}

	public static boolean isElementDisplayed(String fieldName, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(SeleniumBrowser.driver, 1);
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();

		} catch (NoSuchElementException | StaleElementReferenceException | TimeoutException exception) {
			new FrameworkException(exception, "isElementDisplayed");
			return false;
		}
	}

	/**
	 * Wait for one element to be clickable with max time=TIMEOUT
	 * 
	 * @param name
	 * @return
	 */
	public WebElement waitForElement(By name) {
		WebElement tmp = wait.until(ExpectedConditions.presenceOfElementLocated(name));
		return tmp;
	}

	/**
	 * Method to return current timestamp
	 * 
	 * @return string timestamp
	 */
	public String getCurrentTimestamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

}