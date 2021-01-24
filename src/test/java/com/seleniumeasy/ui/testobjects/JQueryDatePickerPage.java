package com.seleniumeasy.ui.testobjects;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.seleniumeasy.framework.web.PageAction;


public class JQueryDatePickerPage extends PageAction {

	public Log logger;
	private static AjaxFormPage ajaxform;
	public String datePickersLink = "Date pickers";
	public String year = "2021";

	@FindBy(linkText = "Date pickers")
	WebElement datePickers;
	
	@FindBy(linkText = "JQuery Date Picker")
	WebElement jqueryDatePicker;
	
	@FindBy(xpath = "//input[@id='from']")
	WebElement fromDateField;
	
	@FindBy(xpath = "//input[@id='to']")
	WebElement toDateField;
	
	@FindBy(css = ".ui-datepicker-month")
	WebElement monthDropdown;
	
	@FindBy(css = ".ui-datepicker-year")
	WebElement yearLabel;
	
	@FindBy(css = ".ui-icon.ui-icon-circle-triangle-w")
	WebElement monthLeftNavigation;
	
	@FindBy(css = ".ui-icon.ui-icon-circle-triangle-e")
	WebElement monthRightNavigation;

	public JQueryDatePickerPage() throws FileNotFoundException, IOException {
		logger = new Log4JLogger("AjaxFormPageLogger");
		PageFactory.initElements(driver, this);
		ajaxform = new AjaxFormPage();
	}

	/**
	 * Clicks Input Forms link
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */
	
	public void clickDatePickers() throws InterruptedException {
		clickButtonJS("Date Pickers Link", datePickers);
		//ajaxform.CloseAdPopupIfDisplayed();
	}
	
	public void clickJqueryDatePicker() throws InterruptedException {
		clickButtonJS("JQuery Date Picker", jqueryDatePicker);
	}
	
	public void clickFromDate() throws InterruptedException {
		clickButtonJS("From Date Field", fromDateField);
	}
	
	public void clickToDate() throws InterruptedException {
		clickButtonJS("To Date Field", toDateField);
	}

	public void verifyToDateSelection() throws InterruptedException {

		toDateField.click();
		Thread.sleep(1000);

		Select MonthOne = new Select(monthDropdown);
		WebElement option = MonthOne.getFirstSelectedOption();
		String defaultItem = option.getText();
		Assert.assertTrue(defaultItem.equals("Feb"));
		Thread.sleep(1000);

		monthLeftNavigation.click();
		Select MonthOnes = new Select(monthDropdown);
		WebElement options = MonthOnes.getFirstSelectedOption();
		String defaultItems = options.getText();
		Assert.assertTrue(defaultItems.equals("Jan"));

		monthLeftNavigation.click();
		Assert.assertTrue(!defaultItems.equals("Dec"));
		Assert.assertEquals(yearLabel.getText(), year);

	}

	public void verifyFromDateSelection() throws InterruptedException {

		fromDateField.click();
		Thread.sleep(1000);

		Select MonthOne = new Select(monthDropdown);
		WebElement option = MonthOne.getFirstSelectedOption();
		String defaultItem = option.getText();
		Assert.assertTrue(defaultItem.equals("Jan"));
		Thread.sleep(3000);

		monthRightNavigation.click();
		Select MonthOnes = new Select(monthDropdown);
		WebElement options = MonthOnes.getFirstSelectedOption();
		String defaultItems = options.getText();
		Assert.assertTrue(defaultItems.equals("Feb"));
		monthRightNavigation.click();
		Assert.assertTrue(!defaultItems.equals("Mar"));
		Assert.assertEquals(yearLabel.getText(), year);
		for (int i = 2; i <= 28; i++) {
			Assert.assertFalse(isElementPresent((By.linkText(Integer.toString(i)))));
		}
	}

}
