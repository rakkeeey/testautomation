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
		clickButton("Ajax Submit Form Link", ajaxFormSubmitLink);

	}

	public void clickSubmitButton() {
		clickButton("Submit", submitButton);

	}

	public void VerifyifSpinnerIsDisplayed() {
		Assert.assertTrue(isElementDisplayed("Spinner", spinner));
	}

	public void enterTitle() throws FileNotFoundException, IOException {
		enterText("Title", Property.getProperty("title"), titleTextField);

	}

	public void clickInputForms() throws InterruptedException {
		clickButtonJS("Input Forms Link", inputForms);
		CloseAdPopupIfDisplayed();

	}

	public void enterComments() throws FileNotFoundException, IOException {
		enterText("Comments", Property.getProperty("description"), descriptionTextField);
	}

	public void CloseAdPopupIfDisplayed() throws InterruptedException {
		Thread.sleep(3000);
		if (driver.findElement(By.cssSelector(adhocPopup_closeBtn)).isDisplayed()) {
			Thread.sleep(3000);
			clickButtonJS("Close Ad Button", closeButton);
		}
	}

}
