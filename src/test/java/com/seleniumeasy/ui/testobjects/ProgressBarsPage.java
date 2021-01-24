package com.seleniumeasy.ui.testobjects;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.seleniumeasy.framework.web.PageAction;


public class ProgressBarsPage extends PageAction {

	public Log logger;
	private static AjaxFormPage ajaxform;
	public String progressBarsLink = "Progress Bars";
	private static String adhocPopup_closeBtn = "#at-cv-lightbox-close";
	public String ajaxFormsSubmitLink = "Ajax Form Submit";
	
	

	@FindBy(linkText = "Progress Bars")
	WebElement progressBars;
	
	@FindBy(xpath = "//button[contains(text(),'Download')]")
	WebElement downloadButton;
	
	@FindBy(linkText = "Bootstrap Progress bar")
	WebElement bootstrapProgressBarsLink;
	
	@FindBy(css = ".percenttext")
	WebElement downloadPercentageLabel;
	
	

	public ProgressBarsPage() throws FileNotFoundException, IOException {
		logger = new Log4JLogger("ProgressBarsPageLogger");
		PageFactory.initElements(driver, this);
		ajaxform = new AjaxFormPage();
	}

	/**
	 * Clicks Input Forms link
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */

	public void clickbootStrapProgressFormsLink() throws FileNotFoundException, IOException {
		clickButton("Bootstrap Progress Bars Link", bootstrapProgressBarsLink);

	}
	
	public void startAndVerifyDownloadProgress() {
		
		Set<Object> counterSet = new HashSet<>();
		while (!downloadPercentageLabel.getText().contains("100")) {
			if (counterSet.add(downloadPercentageLabel.getText())) {
				logger.info("The download percentage is "+downloadPercentageLabel.getText());
			}
		}
		assertTruePercentage(downloadPercentageLabel,"100");
	}


	public void clickProgressBars() throws InterruptedException {
		clickButtonJS("Progress Bars Link", progressBars);
		//ajaxform.CloseAdPopupIfDisplayed();

	}
	
	public void clickDownloadButton() throws InterruptedException {
		clickButtonJS("Download Button", downloadButton);

	}
}
