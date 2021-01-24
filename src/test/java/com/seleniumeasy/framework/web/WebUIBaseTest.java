package com.seleniumeasy.framework.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.seleniumeasy.framework.config.Property;


public class WebUIBaseTest {
	
	protected Log logger;
	protected static Property prop;
	protected static String currentTime = "";


  public WebUIBaseTest(){

	  logger = new Log4JLogger("WebUIBaseTestLogger");
	 		
				//Property.instantiateProperties();
	
	
  }
	
  @BeforeClass (alwaysRun = true)
  public void beforeClass() throws FileNotFoundException, IOException {
	  
  }
  
  @AfterClass (alwaysRun = true)
  public void afterClass() throws IOException, Exception {
	  
  }

  
  @BeforeMethod (alwaysRun = true)
  public void beforeMethod(Method m) {
	  logger.info(" ");
	  logger.info("*********BEGINNING TEST METHOD " + m.getDeclaringClass().getName() + "." + m.getName() + "()***********");
	  logger.info(" ");
  }

  
  @AfterMethod (alwaysRun = true)
  public void afterMethod(Method m) {
	  logger.info(" ");
	  logger.info("*********EXITING TEST METHOD " + m.getDeclaringClass().getName() + "." + m.getName() + "()***********");
	  logger.info(" ");
  }

  @BeforeSuite (alwaysRun = true)
  public void beforeSuite() throws IOException {
	  
	  logger.info(" ");
	  logger.info("*********ENTERING TEST SUITE***********");
	  logger.info(" ");
  }

  @AfterSuite (alwaysRun = true)
  public void afterSuite() {
	  
	  logger.info(" ");
	  logger.info("*********EXITING TEST SUITE***********");
	  logger.info(" ");
	  
	  logger = null;
  }
  
  @BeforeTest (alwaysRun = true)
  public void beforeTest() {
	  
  }
  
  @AfterTest (alwaysRun = true)
  public void afterTest() {
	  
  }
  
  protected void threadSleep(long milliSecond){
	  try {
		Thread.sleep(milliSecond);
	} catch (InterruptedException e) {
		logger.warn(e);
	}
  }
  	
}
  
