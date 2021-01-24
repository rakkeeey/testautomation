package com.seleniumeasy.framework.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


import com.seleniumeasy.framework.config.Property;

public class SeleniumBrowser {

	protected static String browser;
	protected static String os;
	private static String hostname;
	public static WebDriver driver;
	public static File folder;

	public SeleniumBrowser() throws FileNotFoundException, IOException {

		// setupSeleniumEnvironment();
	}

	private static String getOSVersion() {
		//System.out.println("Operating System=" + System.getProperty("os.name"));
		return System.getProperty("os.name");
	}

	private static void setupSeleniumEnvironment() throws FileNotFoundException, IOException {
		SeleniumBrowser.hostname = Property.getProperty("webapp.seleniumeasy.url");
		SeleniumBrowser.os = getOSVersion();
		//System.out.println("OS Version = " + SeleniumBrowser.os + ", os.arch = " + System.getProperty("os.arch"));
		String passedBrowser = Property.getProperty("browser");
		//System.out.println("browser to run = " + passedBrowser);
		if (passedBrowser != null) {
			SeleniumBrowser.browser = passedBrowser;
		} else {
			SeleniumBrowser.browser = "firefox";
		}
		printEnvInfo();
	}

	

	public static void loadURL() throws Exception {
		driver.get(SeleniumBrowser.hostname);
	}

	public static void BrowserWindowMax() throws Exception {
		driver.manage().window().maximize();

	}

	public static void createFolder() throws Exception {

		folder = new File(UUID.randomUUID().toString());
		folder.mkdir();
	}

	public static void deleteFolder() throws Exception {

		for (File file : folder.listFiles()) {
			file.delete();
		}
		folder.delete();
	}

	// checks the browser input, and defaults to IE if there are any issues.
	public static void LaunchBrowser() throws FileNotFoundException, IOException, Exception {
		if (browser.toLowerCase().equals("iexplore") || browser.toLowerCase().equals("ie")
				|| browser.toLowerCase().equals("internet explorer")) {
			browser = "iexplore";

			if (os.contains("Windows")) {
				if (System.getProperty("os.arch").contains("86")) {
					System.setProperty("webdriver.ie.driver",
							"src\\test\\resources\\bits\\drivers\\iexplore\\IEDriverServer_win_32_X86.exe");
				} else if (System.getProperty("os.arch").contains("64")) {
					System.setProperty("webdriver.ie.driver",
							"src\\test\\resources\\bits\\drivers\\iexplore\\IEDriverServer_win_X64.exe");
				}
			} else {
				//System.out.println("Unexpected Operating System");

			}

			driver = new InternetExplorerDriver();
		} else if (browser.toLowerCase().equals("firefox") || browser.toLowerCase().equals("ff")) {
			browser = "firefox";
			driver = new FirefoxDriver();
		} else if (browser.toLowerCase().equals("chrome") || browser.toLowerCase().equals("google")
				|| browser.toLowerCase().equals("google chrome") || browser.toLowerCase().equals("googlechrome")) {

			browser = "googlechrome";
			if (os.contains("Windows")) {
				System.setProperty("webdriver.chrome.driver",
						"src\\test\\resources\\bits\\drivers\\chrome\\chromedriver.exe");
			} else if (os.equals("Linux") && System.getProperty("os.arch").contains("64")) {
				System.setProperty("webdriver.chrome.driver",
						"src//test//resources//bits//drivers//chrome//chrome_linux64");
			} else {
				//System.out.println("Unexpected Operating System");

			}
			
			// Add Chrome Preferences
			createFolder();
			ChromeOptions options = new ChromeOptions();

			Map<String, Object> pref = new HashMap<String, Object>();
			pref.put("profile.default_content_settings.popups", 0);
			pref.put("download.default_directory", folder.getAbsolutePath());
			//System.out.println(folder.getAbsolutePath());

			options.setExperimentalOption("prefs", pref);
			options.addArguments("--always-authorize-plugins");
			driver = new ChromeDriver(options);
		}


	}

	public static String getCurrentBrowserType() {
		return SeleniumBrowser.browser;
	}

	public static void printEnvInfo() {
		System.out.println("You have selected " + System.getProperty("browser") + " as your browser. " + browser
				+ " is being used.");
	}

	public static void launchBrowsernLoadURL() throws Exception {
		setupSeleniumEnvironment();
		LaunchBrowser();
		loadURL();
		BrowserWindowMax();
	}

	public static void loadURL(String hostname) throws Exception {
		driver.get(hostname);
	}
}