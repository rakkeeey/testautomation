package com.seleniumeasy.framework.reporting;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

/**
 * This has all the implementations to do Custom Reporting
 * 
 * @author Rakesh
 *
 */
public class ReportUtility {
	private static Log logger = new Log4JLogger("ReportUtility");

	public static String getOS() {
		return System.getProperty("os.name");
	}

	public static String getSlashType() {
		String sOSName = getOS();
		if (sOSName.equalsIgnoreCase("Linux")) {
			return "/";
		}
		return "\\";
	}
	
	public static void reportingResults(String sStatus, String sStepName, String sStepDesc, String sExptResut) {
		TestResult.Report(sStepDesc, sStatus, sStepName, sExptResut);
	}

	/**
	 * This takes care in setting Data for Reporting
	 * 
	 * @param sScriptName
	 * @param useCaseName
	 * @param useCaseDescription
	 * @param curHeading
	 * @param erValue
	 * @param earValue
	 * @param uearValue
	 */
	public static void setReportingData(String sScriptName, String useCaseName, String useCaseDescription,
			String curHeading, String erValue, String earValue, String uearValue) {
		Global.sScriptName = sScriptName;
		Global.useCaseName = useCaseName;
		Global.useCaseDescription = useCaseDescription;
		Global.curHighLight = true;
		Global.curHeading = curHeading;
		Global.ER = erValue;
		Global.EAR = earValue;
		Global.UEAR = uearValue;

		ReportUtility.reportingResults("Pass", Global.curHeading, Global.EAR, Global.ER);
		Global.curHighLight = false;
		Global.prevHeading = Global.curHeading;
		Global.PER = Global.ER;
		Global.PEAR = Global.EAR;
		Global.PUEAR = Global.UEAR;
		Global.curBC = "";
		logger.info(String.format("Reporting initiated for ::%s", sScriptName));
	}

}
