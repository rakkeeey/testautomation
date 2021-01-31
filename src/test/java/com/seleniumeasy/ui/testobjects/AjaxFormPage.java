package com.seleniumeasy.ui.testobjects;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.seleniumeasy.framework.config.Property;
import com.seleniumeasy.framework.reporting.ReportUtility;
import com.seleniumeasy.framework.web.PageAction;

public class AjaxFormPage extends PageAction {

	public Log logger;
	public String inputFormsLink = "Input Forms";
	private static String adhocPopup_closeBtn = "#at-cv-lightbox-close";
	public String ajaxFormsSubmitLink = "Ajax Form Submit";

	@FindBy(linkText = "Input Forms")
	WebElement inputForms;

	@FindBy(linkText = "Ajax Form Submit")
	WebElement ajaxFormSubmitLink;

	@FindBy(id = "title")
	WebElement titleTextField;

	@FindBy(id = "description")
	WebElement descriptionTextField;

	@FindBy(id = "btn-submit")
	WebElement submitButton;

	@FindBy(css = "#at-cv-lightbox-close")
	WebElement closeButton;

	@FindBy(xpath = "//img[@src='LoaderIcon.gif']")
	WebElement spinner;

	public AjaxFormPage() throws FileNotFoundException, IOException {
		logger = new Log4JLogger("AjaxFormPageLogger");
		PageFactory.initElements(driver, this);
	}

	/**
	 * Clicks Input Forms link
	 * 
	 */

	public void clickAjaxSubmitFormLink() {
		clickButtonJS("Ajax Submit Form Link", ajaxFormSubmitLink);
	}

	public void clickSubmitButton() {
		clickButton("Submit", submitButton);

	}

	public void VerifyIfSpinnerIsDisplayed() {
		
		try {
			Assert.assertTrue(isElementDisplayed("Spinner", spinner));
			
		} catch (AssertionError e) {
			ReportUtility.reportingResults("Fail", "Check the presence of spinner element",
					"Failed to check the presence of spinner element",
					"User should be able to see the element");
		    throw e;
		}
		ReportUtility.reportingResults("Pass", "Check the presence of spinner element",
				"Successfully checked the presence of spinner element",
				"User should be able to see the element");
		
	}

	public void enterTitle() throws FileNotFoundException, IOException {
		enterText("Title", Property.getProperty("title"), titleTextField);

	}

	public void clickInputForms() throws InterruptedException {
		clickButtonJS("Input Forms Link", inputForms);
	}

	public void enterComments() throws FileNotFoundException, IOException {
		enterText("Comments", Property.getProperty("description"), descriptionTextField);
	}

	public void CloseAdPopupIfDisplayed() throws InterruptedException {
		Thread.sleep(3000);
		if (isElementPresent(By.cssSelector(adhocPopup_closeBtn)) == true) {
			Thread.sleep(3000);
			clickButtonJS("Close Ad Button", closeButton);
		}
	}

}
