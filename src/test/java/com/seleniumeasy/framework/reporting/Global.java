package com.seleniumeasy.framework.reporting;

import java.util.Map;

public class Global 
{	
	
	public static String Domain="";
	public static String gBrowserType = "firefox";
	public static String gURL = "";
	public static String gApplicationErrMsg = null;
	public static String gErrMsg = null;
	public static Map<String, String> loginObjMap = null;
	public static String sScriptName;
	public static String sUseCaseName;
	public static boolean bAssertion = false;
	public static String sScreenShot="";
	public static String gFileSize = null;
	public static String gQuotaUsed = null;
	public static String sDownloadPath ="";
	public static String gDateFormat="EEE MMM dd yyyy hh:mma";
	
	//Screen names and error messages
	public static String sAccessAdminScreen = "Access Administration";
	
	//Use case report variables
	public static String useCaseName ="";
	public static String useCaseDescription="";
	
	//For New Template
	public static boolean curHighLight=false;
	public static String curHeading="";
	public static String prevHeading="";
	public static String ER="";
	public static String EAR="";
	public static String UEAR="";
	public static String curBC="";
	public static String prevBC="";
	public static String PER="";
	public static String PEAR="";
	public static String PUEAR="";

	public static String sFilePath = Constants.sReportFolderPath + Utility.getSlashType()
	+Application.getUniqueReportName("TestAutomationReport")+".html";
}
