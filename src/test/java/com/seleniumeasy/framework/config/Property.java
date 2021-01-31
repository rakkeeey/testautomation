package com.seleniumeasy.framework.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.testng.Assert;

/**
 * Class to read the properties from property file
 * 
 * @author Rakesh
 *
 */
public class Property {

	private static Properties bp;

	protected static Log logger = new Log4JLogger("seleniumeasyPropertyLogger");

	public static String getProperty(String property) throws FileNotFoundException, IOException {

		instantiateProperties();

		String value = bp.getProperty(property);
		return value;
	}

	public static void instantiateProperties() {

		bp = new Properties();
		String fileName = "";
		String passedProperty = System.getProperty("env");

		if (passedProperty != null) {

			logger.info(" ");
			logger.info("Found environment property \"" + passedProperty + "\" passed in, using " + passedProperty
					+ ".properties");
			logger.info(" ");

			fileName = "src//test//resources//configs//" + passedProperty + ".properties";

		} else {

			logger.info(" ");
			logger.info("No environment property passed in, defaulting to default.properties");
			logger.info(" ");

			fileName = "src//test//resources//configs//default.properties";
		}

		InputStream is;

		try {

			is = new FileInputStream(fileName);
			bp.load(is);
			is.close();

		} catch (IOException e) {
			logger.error("Exception happened :: " + e.getMessage());
			Assert.fail("File not found: " + fileName);
		}
	}

}
