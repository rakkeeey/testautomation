package com.seleniumeasy.ui.testscripts;

import org.testng.Assert;
import org.testng.annotations.*;

import com.seleniumeasy.framework.web.SeleniumBrowser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;

import java.util.*;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

public class TestOne {
	
	private static Log logger = new Log4JLogger("TestOne");

	public static void main(String[] args) throws InterruptedException {
	}

	public static WebDriver driver;


	 @Test
	public void useCaseOne() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\bits\\drivers\\chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.seleniumeasy.com/test/");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Input Forms")));
	/*	if (driver.findElement(By.xpath("//*[contains(text(), 'No, thanks!')]")).isDisplayed()) {

			driver.findElement(By.xpath("//*[contains(text(), 'No, thanks!')]")).click();

		} */
		
		if (driver.findElement(By.cssSelector("#at-cv-lightbox-close")).isDisplayed()) {

			driver.findElement(By.cssSelector("#at-cv-lightbox-close")).click();

		}
		
		
		driver.findElement(By.linkText("Input Forms")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Ajax Form Submit")));
		driver.findElement(By.linkText("Ajax Form Submit")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title")));
		driver.findElement(By.id("title")).sendKeys("clear");
		driver.findElement(By.id("title")).sendKeys("testname");
		driver.findElement(By.id("description")).clear();
		driver.findElement(By.id("description")).sendKeys("testcomment");
		driver.findElement(By.id("btn-submit")).click();
		isElementDisplayed(driver.findElement(By.xpath("//img[@src='LoaderIcon.gif']")));

	}

	//@Test
	public void useCaseTwo() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\bits\\drivers\\chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.seleniumeasy.com/test/");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Date pickers")));
		if (driver.findElement(By.xpath("//*[contains(text(), 'No, thanks!')]")).isDisplayed()) {

			driver.findElement(By.xpath("//*[contains(text(), 'No, thanks!')]")).click();

		}
		driver.findElement(By.linkText("Date pickers")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("JQuery Date Picker")));
		driver.findElement(By.linkText("JQuery Date Picker")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='from']")));
		driver.findElement(By.xpath("//input[@id='from']")).click();
		Thread.sleep(1000);
		datePicker(driver.findElement(By.cssSelector(".ui-datepicker-calendar")), "13", "Jan", "2021");
		driver.findElement(By.xpath("//input[@id='to']")).click();
		Thread.sleep(1000);
		datePicker(driver.findElement(By.cssSelector(".ui-datepicker-calendar")), "1", "Feb", "2021");
		Thread.sleep(1000);

		verifyFromDateSelection();
		verifyToDateSelection();

	}

	// @Test
	public void useCaseThree() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\bits\\drivers\\chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.seleniumeasy.com/test/");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Progress Bars")));
		if (isElementDisplayed(driver.findElement(By.xpath("//*[contains(text(), 'No, thanks!')]"))) == true) {

			driver.findElement(By.xpath("//*[contains(text(), 'No, thanks!')]")).click();

		}
		driver.findElement(By.linkText("Progress Bars")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Bootstrap Progress bar")));
		driver.findElement(By.linkText("Bootstrap Progress bar")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Download')]")));
		WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Download')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", button);

		StopWatch sw = new StopWatch();
		logger.info("Stopwatch time: " + sw);
		logger.info("Starting Stopwatch");
		sw.start();
		Set<Object> counterSet = new HashSet<>();
		while (!driver.findElement(By.cssSelector(".percenttext")).getText().contains("100")) {
			if (counterSet.add(driver.findElement(By.cssSelector(".percenttext")).getText())) {
				logger.info(driver.findElement(By.cssSelector(".percenttext")).getText());
			}
		}
		Assert.assertTrue(driver.findElement(By.cssSelector(".percenttext")).getText().contains("100"));
		logger.info("Stopping Stopwatch");
		logger.info("Total time taken for the timer to reach 100% is " + sw + " seconds");
		sw.stop();
	}

	// @Test
	public void useCaseFour() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\bits\\drivers\\chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.seleniumeasy.com/test/");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Progress Bars")));
		if (isElementDisplayed(driver.findElement(By.xpath("//*[contains(text(), 'No, thanks!')]"))) == true) {

			driver.findElement(By.xpath("//*[contains(text(), 'No, thanks!')]")).click();

		}
		driver.findElement(By.linkText("Progress Bars")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Drag & Drop Sliders")));
		driver.findElement(By.linkText("Drag & Drop Sliders")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//output[@id='range']")));

		WebElement sliderOne = driver.findElement(By.xpath("//input[contains(@onchange,'range.value=value')]"));
		WebElement rangeOne = driver.findElement(By.xpath("//output[@id='range']"));

		WebElement sliderTwo = driver.findElement(By.xpath("//input[contains(@onchange,'rangePrimary.value=value')]"));
		WebElement rangeTwo = driver.findElement(By.xpath("//output[@id='rangePrimary']"));

		WebElement sliderThree = driver
				.findElement(By.xpath("//input[contains(@onchange,'rangeSuccess.value=value')]"));
		WebElement rangeThree = driver.findElement(By.xpath("//output[@id='rangeSuccess']"));

		WebElement sliderFour = driver.findElement(By.xpath("//input[contains(@onchange,'rangeInfo.value=value')]"));
		WebElement rangeFour = driver.findElement(By.xpath("//output[@id='rangeInfo']"));

		WebElement sliderFive = driver.findElement(By.xpath("//input[contains(@onchange,'rangeWarning.value=value')]"));
		WebElement rangeFive = driver.findElement(By.xpath("//output[@id='rangeWarning']"));

		WebElement sliderSix = driver.findElement(By.xpath("//input[contains(@onchange,'rangeDanger.value=value')]"));
		WebElement rangeSix = driver.findElement(By.xpath("//output[@id='rangeDanger']"));

		setRangeSlidersToFifty(sliderOne, rangeOne);
		setRangeSlidersToFifty(sliderTwo, rangeTwo);
		setRangeSlidersToFifty(sliderThree, rangeThree);
		setRangeSlidersToFifty(sliderFour, rangeFour);
		setRangeSlidersToFifty(sliderFive, rangeFive);
		setRangeSlidersToFifty(sliderSix, rangeSix);

	}

	// @Test
	public void useCaseFive() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\bits\\drivers\\chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.seleniumeasy.com/test/");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Alerts & Modals")));
		if (isElementDisplayed(driver.findElement(By.xpath("//*[contains(text(), 'No, thanks!')]"))) == true) {

			driver.findElement(By.xpath("//*[contains(text(), 'No, thanks!')]")).click();

		}

		driver.findElement(By.linkText("Alerts & Modals")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Window Popup Modal")));
		driver.findElement(By.linkText("Window Popup Modal")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Follow Twitter & Facebook')]")));

		String parent = driver.getWindowHandle();
		driver.findElement(By.xpath("//*[contains(text(), 'Follow Twitter & Facebook')]")).click();

		Set<String> allWindowHandles = driver.getWindowHandles();
		int noofwindows = driver.getWindowHandles().size();
		Assert.assertTrue(noofwindows == 3);
		logger.info("The no of windows opened are " + noofwindows);

		Iterator<String> itr = allWindowHandles.iterator();
		while (itr.hasNext()) {
			String childWindow = itr.next();
			if (!parent.equals(childWindow)) {
				driver.switchTo().window(childWindow);
				logger.info("Title of the active window is " + driver.switchTo().window(childWindow).getTitle());
				driver.close();
			}
		}

		driver.switchTo().window(parent);
		logger.info("The title of the active window after closing both the popups is " + driver.getTitle());
	}

	// @Test
	public void useCaseSix() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\bits\\drivers\\chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.seleniumeasy.com/test/");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Alerts & Modals")));
		if (isElementDisplayed(driver.findElement(By.xpath("//*[contains(text(), 'No, thanks!')]")))==true) {

			driver.findElement(By.xpath("//*[contains(text(), 'No, thanks!')]")).click();

		}

		driver.findElement(By.linkText("Alerts & Modals")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Javascript Alerts")));
		driver.findElement(By.linkText("Javascript Alerts")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='myAlertFunction()']")));
		driver.findElement(By.xpath("//button[@onclick='myAlertFunction()']")).click();

		Assert.assertTrue(isAlertPresentAndAcceptAlert());
	}

	// @Test
	public void useCaseSeven() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\bits\\drivers\\chrome\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();

		Map<String, Object> pref = new HashMap<String, Object>();
		pref.put("profile.default_content_settings.popups", 0);
		pref.put("download.default_directory", SeleniumBrowser.folder.getAbsolutePath());
		logger.info(SeleniumBrowser.folder.getAbsolutePath());

		options.setExperimentalOption("prefs", pref);

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.seleniumeasy.com/test/");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Alerts & Modals")));
		if (isElementDisplayed(driver.findElement(By.xpath("//*[contains(text(), 'No, thanks!')]"))) == true) {

			driver.findElement(By.xpath("//*[contains(text(), 'No, thanks!')]")).click();

		}

		driver.findElement(By.linkText("Alerts & Modals")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("File Download")));
		driver.findElement(By.linkText("File Download")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='textbox']")));
		driver.findElement(By.xpath("//textarea[@id='textbox']")).clear();

		driver.findElement(By.xpath("//textarea[@id='textbox']")).sendKeys("Test Automation Content 12345@ abc./=-[]");

		driver.findElement(By.xpath("//button[@id='create']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='link-to-download']")));
		driver.findElement(By.xpath("//a[@id='link-to-download']")).click();
		Thread.sleep(5000);

		File listOfFiles[] = SeleniumBrowser.folder.listFiles();

		Assert.assertTrue(listOfFiles.length > 0);

		for (File file : listOfFiles) {

			Assert.assertTrue(file.length() > 0);

		}

		readAndVerifyDownloadedTextFile(SeleniumBrowser.folder.getAbsolutePath() + "\\easyinfo.txt",
				"Test Automation Content 12345@ abc./=-[]");

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
			logger.info("everything is " + everything);
			Assert.assertTrue(everything.equals(ExpectedText));
		} finally {
			br.close();
		}

	}

	@AfterTest
	public void close() {

		driver.quit();
	}

	public void setRangeSlidersToFifty(WebElement slider, WebElement value) {

		/*
		 * while (!value.getText().equals("1")) {
		 * 
		 * slider.sendKeys(Keys.ARROW_LEFT);
		 * 
		 * }
		 */

		while (!value.getText().equals("50")) {
			slider.sendKeys(Keys.ARROW_RIGHT);
		}

		Assert.assertTrue(value.getText().equals("50"));
	}

	public boolean isAlertPresentAndAcceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	public void datePicker(WebElement element, String date, String month, String year) {

		Actions a = new Actions(driver);
		Assert.assertEquals(driver.findElement(By.cssSelector(".ui-datepicker-year")).getText(), year);
		Select MonthOne = new Select(driver.findElement(By.cssSelector(".ui-datepicker-month")));
		MonthOne.selectByVisibleText(month);
		a.moveToElement(driver.findElement(By.linkText(date))).click().build().perform();
		/*
		 * for (WebElement tr : trs) { String locator = "td"; List<WebElement> columns =
		 * tr.findElements(By.tagName(locator)); for (WebElement cell : columns) { if
		 * (cell.getText().equals(date)) {
		 * a.moveToElement(cell.findElement(By.linkText(date))).click().build().perform(
		 * ); // cell.findElement(By.linkText(date)).click(); flag = 1;// Set to 1 when
		 * the required element was found and clicked. break; }
		 * 
		 * } if (flag == 1) break; // Since the element was found, come out of the
		 * second for-loop. }
		 */
	}

	public void verifyToDateSelection() throws InterruptedException {

		driver.findElement(By.xpath("//input[@id='to']")).click();
		Thread.sleep(1000);

		Select MonthOne = new Select(driver.findElement(By.cssSelector(".ui-datepicker-month")));
		WebElement option = MonthOne.getFirstSelectedOption();
		String defaultItem = option.getText();
		Assert.assertTrue(defaultItem.equals("Feb"));
		Thread.sleep(1000);

		driver.findElement(By.cssSelector(".ui-icon.ui-icon-circle-triangle-w")).click();
		Select MonthOnes = new Select(driver.findElement(By.cssSelector(".ui-datepicker-month")));
		WebElement options = MonthOnes.getFirstSelectedOption();
		String defaultItems = options.getText();
		Assert.assertTrue(defaultItems.equals("Jan"));

		driver.findElement(By.cssSelector(".ui-icon.ui-icon-circle-triangle-w")).click();
		Assert.assertTrue(!defaultItems.equals("Dec"));
		Assert.assertEquals(driver.findElement(By.cssSelector(".ui-datepicker-year")).getText(), "2021");

	}

	public void verifyFromDateSelection() throws InterruptedException {

		driver.findElement(By.xpath("//input[@id='from']")).click();
		Thread.sleep(1000);

		Select MonthOne = new Select(driver.findElement(By.cssSelector(".ui-datepicker-month")));
		WebElement option = MonthOne.getFirstSelectedOption();
		String defaultItem = option.getText();
		Assert.assertTrue(defaultItem.equals("Jan"));
		Thread.sleep(3000);

		driver.findElement(By.cssSelector(".ui-icon.ui-icon-circle-triangle-e")).click();
		Select MonthOnes = new Select(driver.findElement(By.cssSelector(".ui-datepicker-month")));
		WebElement options = MonthOnes.getFirstSelectedOption();
		String defaultItems = options.getText();
		Assert.assertTrue(defaultItems.equals("Feb"));
		driver.findElement(By.cssSelector(".ui-icon.ui-icon-circle-triangle-e")).click();
		Assert.assertTrue(!defaultItems.equals("Mar"));
		Assert.assertEquals(driver.findElement(By.cssSelector(".ui-datepicker-year")).getText(), "2021");
		for (int i = 2; i <= 28; i++) {
			Assert.assertFalse(isElementPresent((By.linkText(Integer.toString(i)))));
		}
	}

	public boolean isElementPresent(By locatorKey) {
		try {
			driver.findElement(locatorKey);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static boolean isElementDisplayed(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 1);
			wait.until(ExpectedConditions.visibilityOf(element));
			logger.info("the loading button is displayed");
			return element.isDisplayed();

		} catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
			logger.error("the loading button is not displayed");
			return false;
		}
	}
}
