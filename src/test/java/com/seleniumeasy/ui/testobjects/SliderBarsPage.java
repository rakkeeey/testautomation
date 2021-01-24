package com.seleniumeasy.ui.testobjects;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.seleniumeasy.framework.web.PageAction;


public class SliderBarsPage extends PageAction {

	public Log logger;
	private static AjaxFormPage ajaxform;
	public String rangeLabell = "//output[@id='range']";
	public String ajaxFormsSubmitLink = "Ajax Form Submit";
	
	

	@FindBy(linkText = "Drag & Drop Sliders")
	WebElement dragAndDropSlidersLink;
	
	@FindBy(xpath = "//output[@id='range']")
	WebElement rangeLabel;
	
	@FindBy(xpath = "//input[contains(@onchange,'range.value=value')]")
	WebElement sliderOne;
	
	@FindBy(xpath = "//output[@id='range']")
	WebElement rangeOne;
	
	@FindBy(xpath = "//output[@id='rangePrimary']")
	WebElement rangeTwo;
	
	@FindBy(xpath = "//input[contains(@onchange,'rangePrimary.value=value')]")
	WebElement sliderTwo ;
	
	@FindBy(xpath = "//input[contains(@onchange,'rangeSuccess.value=value')]")
	WebElement sliderThree;
	
	@FindBy(xpath = "//output[@id='rangeSuccess']")
	WebElement rangeThree ;
	
	@FindBy(xpath = "//input[contains(@onchange,'rangeInfo.value=value')]")
	WebElement sliderFour;
	
	@FindBy(xpath = "//output[@id='rangeInfo']")
	WebElement rangeFour;
	
	@FindBy(xpath = "//output[@id='rangeWarning']")
	WebElement rangeFive;

	@FindBy(xpath = "//input[contains(@onchange,'rangeWarning.value=value')]")
	WebElement sliderFive;
	
	@FindBy(xpath = "//output[@id='rangeDanger']")
	WebElement rangeSix;
	
	@FindBy(xpath = "//input[contains(@onchange,'rangeDanger.value=value')]")
	WebElement sliderSix;
	

	

	public SliderBarsPage() throws FileNotFoundException, IOException {
		logger = new Log4JLogger("SliderBarsPageLogger");
		PageFactory.initElements(driver, this);
		ajaxform = new AjaxFormPage();
	}

	/**
	 * Clicks Input Forms link
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */

	public void clickDragAndDropSlidersLink() throws FileNotFoundException, IOException {
		clickButton("Drop and Drop Sliders Link", dragAndDropSlidersLink);

	}
	
	public void setAllSliders() {
		
		setAndVerifyRangeSlidersToFifty(sliderOne, rangeOne);
		setAndVerifyRangeSlidersToFifty(sliderTwo, rangeTwo);
		setAndVerifyRangeSlidersToFifty(sliderThree, rangeThree);
		setAndVerifyRangeSlidersToFifty(sliderFour, rangeFour);
		setAndVerifyRangeSlidersToFifty(sliderFive, rangeFive);
		setAndVerifyRangeSlidersToFifty(sliderSix, rangeSix);
		
	}
	
	public void setAndVerifyRangeSlidersToFifty(WebElement slider, WebElement value) {

		/*
		 * while (!value.getText().equals("1")) { slider.sendKeys(Keys.ARROW_LEFT); }
		 */

		while (!value.getText().equals("50")) {
			slider.sendKeys(Keys.ARROW_RIGHT);
		}
		assertRangeSliderPositions(value, "50");
	}
}
