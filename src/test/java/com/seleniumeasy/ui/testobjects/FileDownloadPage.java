package com.seleniumeasy.ui.testobjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.seleniumeasy.framework.config.Property;
import com.seleniumeasy.framework.web.PageAction;
import com.seleniumeasy.framework.web.SeleniumBrowser;

public class FileDownloadPage extends PageAction {

	public Log logger;
	public String alertsAndModalsLinkk = "Alerts & Modals";

	@FindBy(linkText = "File Download")
	WebElement fileDownloadLink;

	@FindBy(xpath = "//button[@onclick='myAlertFunction()']")
	WebElement clickMeButton;

	@FindBy(xpath = "//textarea[@id='textbox']")
	WebElement textArea;

	@FindBy(xpath = "//button[@id='create']")
	WebElement createButton;

	@FindBy(xpath = "//a[@id='link-to-download']")
	WebElement downloadLink;

	public FileDownloadPage() throws FileNotFoundException, IOException {
		logger = new Log4JLogger("MultipleWindowsPageLogger");
		PageFactory.initElements(driver, this);
	}

	public void clickCreateButton() throws FileNotFoundException, IOException, InterruptedException {
		clickButton("Create", createButton);
	}

	public void clickLinkToDownload() throws FileNotFoundException, IOException, InterruptedException {
		clickButton("Download Link", downloadLink);
	}

	public void clickFileDownloadLink() throws FileNotFoundException, IOException, InterruptedException {
		clickButton("File Download Link", fileDownloadLink);
	}

	public void enterTextContent() throws FileNotFoundException, IOException, InterruptedException {
		enterText("Text Area", Property.getProperty("TextContent"), textArea);
	}

	public void clickClickMeButton() throws FileNotFoundException, IOException, InterruptedException {
		clickButtonJS("Click Me Button", clickMeButton);
	}

	public void readAndVerifyDownloadedTextFile() throws Exception {
		Thread.sleep(3000);
		File listOfFiles[] = SeleniumBrowser.folder.listFiles();
		Assert.assertTrue(listOfFiles.length > 0);
		for (File file : listOfFiles) {
			Assert.assertTrue(file.length() > 0);
		}
		readAndVerifyDownloadedTextFile(
				SeleniumBrowser.folder.getAbsolutePath() + Property.getProperty("downloadedfilename"),
				Property.getProperty("TextContent"));
	}

}
