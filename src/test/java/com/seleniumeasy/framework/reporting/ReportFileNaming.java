package com.seleniumeasy.framework.reporting;

import java.util.Calendar;

/**
 * This generates Unique Report name
 * 
 * @author Rakesh
 *
 */
public class ReportFileNaming 
{
	/**
	 * Create Unique report Name by appending timeStamp
	 * @param String sReportName
	 * @return String
	 */
	public static String getUniqueReportName(String sReportName) {
		Calendar rightNow = Calendar.getInstance();
		if (sReportName == "") {
			return sReportName;
		} else {
			String sNewReportName = sReportName + rightNow.get(Calendar.YEAR)+"-"
					+ (rightNow.get(Calendar.MONTH)+1)+"-"
					+ rightNow.get(Calendar.DAY_OF_MONTH)+"-"
					+ rightNow.get(Calendar.HOUR)+"-"
					+ rightNow.get(Calendar.MINUTE)+"-"
					+ rightNow.get(Calendar.SECOND);
			return sNewReportName;
		}
	}
}
