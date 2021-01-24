package com.seleniumeasy.framework.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
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
import com.seleniumeasy.framework.reporting.Utility;

public class PageAction extends SeleniumBrowser{
	public Log logger;
	JavascriptExecutor jsExe;
	public WebDriverWait wait = new WebDriverWait(SeleniumBrowser.driver,TIMEOUT);
	public StopWatch sw; 
	
	// Seconds to wait for timeout
	public static final int TIMEOUT = 60;
	/**
	 * @throws IOException
	 * @throws FileNotFoundException
	 * 
	 */
	public PageAction() throws FileNotFoundException, IOException {
		jsExe = (JavascriptExecutor) SeleniumBrowser.driver;
		logger = new Log4JLogger("PageAction");
		sw = new StopWatch();

	} 
	public void datePicker(String date, String month, String year) {
		try {
		Thread.sleep(2000);
		Actions a = new Actions(driver);
		Assert.assertEquals(driver.findElement(By.cssSelector(".ui-datepicker-year")).getText(), year);
		Select MonthOne = new Select(driver.findElement(By.cssSelector(".ui-datepicker-month")));
		MonthOne.selectByVisibleText(month);
		Thread.sleep(2000);
		a.moveToElement(driver.findElement(By.linkText(date))).click().build().perform();
		Utility.reportingResults("Pass", "Select Date",
				"Successfully selected the date "+ date+"-"+month+"-"+ year,
				"User should be able to select the date");
	} catch (Exception exception) {
		Utility.reportingResults("Fail", "Select Date",
				"Failed to select the date "+ date+"-"+month+"-"+ year,
				"User should be able to select the date");
		new FrameworkException(exception, "SeleniumDriver:set()");
	}
	}
	
	public void assertTruePercentage(WebElement Element, String Expected) {

		try {
			Assert.assertTrue(Element.getText().contains(Expected));
			Utility.reportingResults("Pass", "The expected download percentage is" + Expected,
					"Displayed download and expected download percentage is equal to " + Expected,
					"Displayed download and expected download percentage should be  equal to " + Expected);
		} catch (Exception exception) {
			Utility.reportingResults("Fail", "he expected download percentage is" + Expected,
					"Displayed download and expected download percentage is equal to " + Expected,
					"Displayed download and expected download percentage should be  equal to " + Expected);
			new FrameworkException(exception, "SeleniumDriver:select(" + Expected + ")");
		}

	}
	
	public void assertRangeSliderPositions(WebElement Element, String Expected) {

		try {
			Assert.assertTrue(Element.getText().contains(Expected));
			Utility.reportingResults("Pass", "Verify if the expected slide range is" + Expected,
					"Displayed slide range and expected slider range is equal to " + Expected,
					"Displayed slide range and expected slider range should be  equal to " + Expected);
		} catch (Exception exception) {
			Utility.reportingResults("Fail", "Verify if the expected slide range is " + Expected,
					"Displayed slide range and expected slider range is not equal to " + Expected,
					"Displayed slide range and expected slider range should be  equal to " + Expected);
			new FrameworkException(exception, "SeleniumDriver:select(" + Expected + ")");
		}

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
		Utility.reportingResults("Pass", "Calculate the time taken for the Download ",
				"The time taken for the download progress is" + sw + " seconds",
				"User should be able to calculate the response time");
	}
	// Enter value in the text field
	public void enterText(String fieldName, String value,WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			element.clear();
			element.sendKeys(value);
			Utility.reportingResults("Pass", "Enter the value '" + value + "' in " + fieldName + " field",
					"Successfully enter the value '" + value + "' in " + fieldName + " field",
					"User should be able to enter the value");
		} catch (Exception exception) {
			Utility.reportingResults("Fail", "Enter the value '" + value + "' in " + fieldName + " field",
					"Failed to enter the value '" + value + "' in " + fieldName + " field",
					"User should be able to enter the value");
			new FrameworkException(exception, "SeleniumDriver:set(" + fieldName + ")");
		}
	}

	// Click a button
	public void clickButton(String fieldName, WebElement element) {

		try {	
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();
			Utility.reportingResults("Pass", "Click  '" + fieldName + "' button",
					"Successfully clicked the '" + fieldName + "' button",
					"User should be able to click on the button");
		} catch (Exception exception) {
			Utility.reportingResults("Fail", "Click  '" + fieldName + "' button",
					"Failed to click the '" + fieldName + "'  button", "User should be able to click on the button");
			new FrameworkException(exception, "SeleniumDriver:click(" + fieldName + ")");
		}
	}
	
	public void clickButtonJS(String fieldName, WebElement element) {

		try {	
			wait.until(ExpectedConditions.visibilityOf(element));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			Utility.reportingResults("Pass", "Click  '" + fieldName + "' button",
					"Successfully clicked the '" + fieldName + "' button",
					"User should be able to click on the button");
		} catch (Exception exception) {
			Utility.reportingResults("Fail", "Click  '" + fieldName + "' button",
					"Failed to click the '" + fieldName + "'  button", "User should be able to click on the button");
			new FrameworkException(exception, "SeleniumDriver:click(" + fieldName + ")");
		}
	}

	public static boolean isElementDisplayed(String fieldName,WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(SeleniumBrowser.driver, 1);
			wait.until(ExpectedConditions.visibilityOf(element));		
			Utility.reportingResults("Pass", "Check the presence of " + fieldName + " element",
					"Successfully checked the presence of " + fieldName + " element",
					"User should be able to see the element");
			return element.isDisplayed();

		} catch (NoSuchElementException | StaleElementReferenceException | TimeoutException exception) {
			Utility.reportingResults("Fail", "Check the presence of " + fieldName + " element",
					"Failed to check the presence of " + fieldName + " element",
					"User should be able to see the element");
			new FrameworkException(exception, "SeleniumDriver:element(" + fieldName + ")");
			return false;
		}
	}

	/**
	 * Wait for one element to be clickable with max time=TIMEOUT <br>
	 * <b>Example:</b> waitForElement(By.id("submit"));
	 * 
	 * @param name
	 * @return
	 */
	public WebElement waitForElement(By name) {
		WebElement tmp = wait.until(ExpectedConditions
				.presenceOfElementLocated(name));
		return tmp;
	}

	/**
	 * Method to just return current timestamp
	 * 
	 * @return string timestamp
	 */
	public String getCurrentTimestamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}
	
}