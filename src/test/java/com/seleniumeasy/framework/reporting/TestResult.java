package com.seleniumeasy.framework.reporting;

import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.WebDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.openqa.selenium.By;

public class TestResult {

	private static String testEnvironmentName;
	private static String hostMachineName;
	private static String testPackName;
	private static String buildVersionName;
	public static WebDriver driver;
	public static Log logger = new Log4JLogger("TestResult");

	static String sStatus;
	static String sStep;
	static String sDescription;
	static String sExpectedResult;
	static String sBreadCrumb;
	static String sConcatenate1;
	static String sConcatenate2;
	static String sConcatenate3;
	static String sConcatenate4;
	static BufferedWriter out;
	static BufferedReader in;
	static StringBuilder fileContent = new StringBuilder();
	static String line;
	static String sScriptName;
	static int iReportOffset;
	static int iSummaryOffset;
	static int iLeftOffset1;
	static int iRightOffset1;
	static int iLeftOffset2;
	static int iRightOffset2;
	static int iOffset;
	static boolean bFailUpdated = false;
	static boolean bResultGenerated = false;
	static String sPrevScriptName = "";
	static String sPrevUseCaseName = "";
	static String sScreenShot = "";
	static int iPassCount = 0;
	static int iFailCount = 0;
	static int iBlockedCount = 0;
	static int iReportUpdate;
	static StringBuilder scriptResult;
	static int iStepNo;
	static String dDate;
	static String sOS;
	static String sBrowserType;
	static int stepLevelPassCount = 0;
	static int stepLevelFailCount = 0;
	static int stepLevelBlockCount = 0;
	static int totalStepCount = 0;
	static int testLevelPassCount = 0;
	static int testLevelFailCount = 0;
	static int testLevelBlockCount = 0;
	static int totalTestCount = 0;
	static int useCaseLevelPassCount = 0;
	static int useCaseFailCount = 0;
	static int useCaseBlockCount = 0;
	static int totalUseCaseCount = 0;
	static int stepNo = 0;
	static int testRowNo = 0;
	static int useCaseRowNo = 0;
	static int scriptCount = 0;
	static int useCaseCount = 0;
	static int x = 0;
	static int y = 0;
	static int p = 0;
	static int q = 0;
	static int m = 0;
	static int n = 0;
	static String str1 = "";
	static String str2 = "";
	static String str3 = "";
	static String str4 = "";
	static String sFileName = "";
	static Date testStart = null;
	static Date testEnd = null;
	static Date useCaseStart = null;
	static Date useCaseEnd = null;
	static Date testSuiteStart = null;
	static Date testSuiteEnd = null;
	static long ts;
	static long te;
	static long us;
	static long ue;
	static long tss;
	static long tse;
	static String jkm = "";
	static int mkj = 0;
	static int kjm = 0;
	static String tsst = "";
	static String tset = "";
	static int singleUseCaseTotalTestCount = 0;
	static int singleUseCaseTotalTestLevelPassCount = 0;
	static int singleUseCaseTotalTestLevelFailCount = 0;

	// For New Template

	static int hT = 0;
	static int hP = 0;
	static int hF = 0;
	static int hStrIndex = 0;
	static int hStrLen = 0;
	static double prevSubVersion = 0;

	public TestResult() {
	}

	/**
	 *  Method to create the file if not already existing
	 */
	public static void createFile() {
		try {
			File file = new File(Global.sFilePath);

			file.createNewFile();
			
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

	}

	/**
	 *  Method to capture the screenshot in case of failures
	 * @return String
	 */
	static String capturebreadCrumb() {
		WebElement element = driver.findElement(By.id("sectionheader"));
		if (element.isDisplayed()) {
			String breadCrumb = element.getText();
			sBreadCrumb = breadCrumb;
			return sBreadCrumb;
		}

		else {

			sBreadCrumb = " ";
			return sBreadCrumb;
		}

	}

	static String captureScreen() {

		try {
			// Get the screen size
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension screenSize = toolkit.getScreenSize();
			Rectangle rect = new Rectangle(0, 0, screenSize.width, screenSize.height);
			Robot robot = new Robot();
			BufferedImage image = robot.createScreenCapture(rect);
			File file;

			sFileName = Global.sScriptName + "-" + (new Date().getTime());

			sScreenShot = ReportConstants.sReportFolderPath + ReportUtility.getSlashType() + sFileName + ".jpg";
			logger.info("Screenshot : " + sScreenShot);
			file = new File(sScreenShot);
			ImageIO.write(image, "jpg", file);

			sFileName = sFileName + ".jpg";

		} catch (Exception e) {
			logger.error("Exception happened ::" + e);
		}
		return "<a href=\"" + sFileName + "\" target=\"_blank\">" + sFileName + "</a>";
	}

	/**
	 *  Method to generate the HTML skeleton of the result file
	 */
	public static void generateHTML() {

		try {
			bResultGenerated = true;
			createFile();
			sOS = ReportUtility.getOS();
			sOS = sOS + " OS";
			sBrowserType = Global.gBrowserType + " Browser";

			out = new BufferedWriter(new FileWriter(Global.sFilePath));

			out.write(
					"<html><h1 align=\"center\">Automation Suite Results</h1><script type=\"text/javascript\" src=\"wz_jsgraphics.js\"></script><script type=\"text/javascript\" src=\"pie.js\"></script><body><center><table align=\"center\"><div id=\"pieCanvas\" style=\"overflow: auto; position:relative;height:350px;width:380px;\"></div><script type=\"text/javascript\">var p = new pie();p.add(\"Pass\","
							+ iPassCount + ");p.add(\"Fail\"," + iFailCount
							+ ");p.render(\"pieCanvas\", \"Pie Graph\")</script><h3 align=\"center\">Use Case Execution Summary</h3><table border=\"1\" align=\"center\"><tr bgcolor=\"#C2DFFF\"><th>Total No. of Use Cases</th><th>Use Cases Passed</th><th>Use Cases Failed</th></tr><tr align=\"center\"><td>"
							+ (iPassCount + iFailCount + iBlockedCount) + "</td><td>" + iPassCount + "</td><td>"
							+ iFailCount
							+ "</td></tr></table><br></br><br></br><table class= \"UseCase-TestLevelSummary1\"  border=\"1\"></table><table class= \"Test-StepLevelExplanaton1\"  border=\"1\"></table></center></body></html>");

			out.close();
		} catch (IOException e) {
			logger.error("Exception happened ::" + e);
		}
	}

	public static void Report(String Description, String Status, String Step, String ExpectedResult) {

		sStatus = Status;
		sStep = Step;
		sDescription = Description;
		sExpectedResult = ExpectedResult;

		if (bResultGenerated == false) {
			generateHTML();

		}
		try {
			// ============================
			if (!Global.sScriptName.equals(sPrevScriptName)) {
				stepNo = 0; // prevSubVersion=0;
				fileContent = new StringBuilder();
				in = new BufferedReader(new FileReader(Global.sFilePath));
				while ((line = in.readLine()) != null) {
					fileContent.append(line);
				}
				in.close();
				scriptCount = scriptCount + 1;

				if (scriptCount == 1) {
					tsst = testSuiteStartTime();
					str1 = "<table class= \"Test-StepLevelExplanaton1\"  border=\"1\"></table></center></body></html>";
					x = fileContent.indexOf(str1);
					y = str1.length();
					// stepNo=stepNo+1;
					if (sStatus.equalsIgnoreCase("pass")) {
						stepLevelPassCount = stepLevelPassCount + 1;
						totalStepCount = totalStepCount + 1;
						if (Global.curHighLight) {
							stepNo = stepNo + 1;
							prevSubVersion = Double.parseDouble(Integer.toString(stepNo));
							fileContent.replace(x, x + y,
									"<table class= \"Test-StepLevelExplanaton1\"  border=\"1\" width=\"100%\"><tr bgcolor=\"#FFF8C6\"><th colspan=\"3\"><a name=\""
											+ Global.sScriptName + "\">" + Global.sScriptName
											+ "</a></th><th colspan=\"4\"> Start Time : " + (testStartTime())
											+ "</th></tr><tr bgcolor=\"#C2DFFF\"><th>Step No.</th><th>Step Description</th><th>Expected Result</th><th>Actual Result</th><th>Status</th><th>Screenshots</th><th>Breadcrumb Trail</th></tr><tr style=\"color:#008000\"><td width=\"4%\">"
											+ stepNo + "</td><td width=\"30%\"><B>" + sStep
											+ "</B></td><td width=\"30%\">" + sExpectedResult
											+ "</td><td width=\"30%\">" + sDescription + "</td><td width=\"6%\">"
											+ sStatus
											+ "</td><td>&nbsp;</td><td>&nbsp;</td></tr></table><br></br><br></br></center></body></html>");
							HeaderUpdation();
						} else {
							fileContent.replace(x, x + y,
									"<table class= \"Test-StepLevelExplanaton1\"  border=\"1\" width=\"100%\"><tr bgcolor=\"#FFF8C6\"><th colspan=\"3\"><a name=\""
											+ Global.sScriptName + "\">" + Global.sScriptName
											+ "</a></th><th colspan=\"4\"> Start Time : " + (testStartTime())
											+ "</th></tr><tr bgcolor=\"#C2DFFF\"><th>Step No.</th><th>Step Description</th><th>Expected Result</th><th>Actual Result</th><th>Status</th><th>Screenshots</th><th>Breadcrumb Trail</th></tr><tr style=\"color:#008000\"><td width=\"4%\">"
											+ "</td><td width=\"30%\">" + sStep + "</td><td width=\"30%\">"
											+ sExpectedResult + "</td><td width=\"30%\">" + sDescription
											+ "</td><td width=\"6%\">" + sStatus + "</td><td>&nbsp;</td><td>" + getBC()
											+ "</td></tr></table><br></br><br></br></center></body></html>");
							hP = hP + 1;
							hT = hT + 1;
						}
						updateUseCaseTestLevelSummary1Table();
					} else if (sStatus.equalsIgnoreCase("fail")) {
						stepLevelFailCount = stepLevelFailCount + 1;
						totalStepCount = totalStepCount + 1;
						if (Global.curHighLight) {
							stepNo = stepNo + 1;
							prevSubVersion = Double.parseDouble(Integer.toString(stepNo));
							fileContent.replace(x, x + y,
									"<table class= \"Test-StepLevelExplanaton1\"  border=\"1\" width=\"100%\"><tr bgcolor=\"#FFF8C6\"><th colspan=\"3\"><a name=\""
											+ Global.sScriptName + "\">" + Global.sScriptName
											+ "</a></th><th colspan=\"4\">Start Time : " + (testStartTime())
											+ "</th></tr><tr bgcolor=\"#C2DFFF\"><th>Step No.</th><th>Step Description</th><th>Expected Result</th><th>Actual Result</th><th>Status</th><th>Screenshots</th><th>Breadcrumb Trail</th></tr><tr style=\"color:#FF0000\"><td width=\"4%\">"
											+ stepNo + "</td><td width=\"30%\"><B>" + sStep
											+ "</B></td><td width=\"30%\">" + sExpectedResult
											+ "</td><td width=\"30%\">" + sDescription + "</td><td width=\"6%\">"
											+ sStatus
											+ "</td><td>&nbsp;</td><td>&nbsp;</td></tr></table><br></br><br></br></center></body></html>");
							HeaderUpdation();
						} else {
							fileContent.replace(x, x + y,
									"<table class= \"Test-StepLevelExplanaton1\"  border=\"1\" width=\"100%\"><tr bgcolor=\"#FFF8C6\"><th colspan=\"3\"><a name=\""
											+ Global.sScriptName + "\">" + Global.sScriptName
											+ "</a></th><th colspan=\"4\">Start Time : " + (testStartTime())
											+ "</th></tr><tr bgcolor=\"#C2DFFF\"><th>Step No.</th><th>Step Description</th><th>Expected Result</th><th>Actual Result</th><th>Status</th><th>Screenshots</th><th>Breadcrumb Trail</th></tr><tr style=\"color:#FF0000\"><td width=\"4%\">"
											+ "</td><td width=\"30%\">" + sStep + "</td><td width=\"30%\">"
											+ sExpectedResult + "</td><td width=\"30%\">" + sDescription
											+ "</td><td width=\"6%\">" + sStatus + "</td><td>" + captureScreen()
											+ "</td><td>" + getBC()
											+ "</td></tr></table><br></br><br></br></center></body></html>");
							hF = hF + 1;
							hT = hT + 1;
						}
						updateUseCaseTestLevelSummary1Table();
					} else {
						stepLevelBlockCount = stepLevelBlockCount + 1;
						totalStepCount = totalStepCount + 1;
						fileContent.replace(x, x + y,
								"<table class= \"Test-StepLevelExplanaton1\"  border=\"1\" width=\"100%\"><tr bgcolor=\"#FFF8C6\"><th colspan=\"3\"><a name=\""
										+ Global.sScriptName + "\">" + Global.sScriptName
										+ "</a></th><th colspan=\"4\">Start Time : " + (testStartTime())
										+ "</th></tr><tr bgcolor=\"#C2DFFF\"><th>Step No.</th><th>Step Description</th><th>Expected Result</th><th>Actual Result</th><th>Status</th><th>Screenshots</th><th>Breadcrumb Trail</th></tr><tr style=\"color:#330099\"><td width=\"4%\">"
										+ stepNo + "</td><td width=\"30%\">" + sStep + "</td><td width=\"30%\">"
										+ sExpectedResult + "</td><td width=\"30%\">" + sDescription
										+ "</td><td width=\"6%\">" + sStatus
										+ "</td><td>&nbsp;</td></tr></table><br></br><br></br></center></body></html>");
						updateUseCaseTestLevelSummary1Table();
					}

					sPrevScriptName = Global.sScriptName;
					sPrevUseCaseName = Global.useCaseName;
					useCaseCount = useCaseCount + 1;
				} else {

					// Calling Test Level Summary Report Maker

					insertTestRowInPreviousUseCase();
					if (!((Global.useCaseName).equals(sPrevUseCaseName))) {
						str3 = "</table><br></br><br></br><table class= \"UseCase-TestLevelSummary1\"  border=\"1\">";
						m = fileContent.indexOf(str3);
						n = str3.length();
						if (totalTestCount == testLevelPassCount) {
							fileContent.replace(m, m + n,
									"<tr bgcolor=\"#C2DFFF\"><th align=\"left\" colspan=\"2\"><a href=\"#"
											+ sPrevUseCaseName + "\">" + sPrevUseCaseName
											+ "</a></th><th colspan=\"2\" style=\"color:#008000\">Pass</th></tr></table><br></br><br></br><table class= \"UseCase-TestLevelSummary1\"  border=\"1\">");
							sConcatenate1 = "<div";
							iLeftOffset1 = fileContent.indexOf(sConcatenate1);
							sConcatenate2 = "p.render(\"pieCanvas\", \"Pie Graph\")</script>";
							iRightOffset1 = fileContent.indexOf(sConcatenate2);
							sConcatenate3 = "<table border=\"1\" align=\"center\">";
							iLeftOffset2 = fileContent.indexOf(sConcatenate3);
							sConcatenate4 = "</td></tr>";
							iRightOffset2 = fileContent.indexOf(sConcatenate4);

							iPassCount = iPassCount + 1;

							fileContent.replace(iLeftOffset1, iRightOffset1 + sConcatenate2.length(),
									"<div id=\"pieCanvas\" style=\"overflow: auto; position:relative;height:350px;width:380px;\"></div><script type=\"text/javascript\">var p = new pie();p.add(\"Pass\","
											+ iPassCount + ");p.add(\"Fail\"," + iFailCount
											+ ");p.render(\"pieCanvas\", \"Pie Graph\")</script>");

							fileContent.replace(iLeftOffset2, iRightOffset2 + sConcatenate4.length(),
									"<table border=\"1\" align=\"center\"><tr bgcolor=\"#C2DFFF\"><th>Total No.of Use Cases</th><th>Use Cases Passed</th><th>Use Cases Failed</th></tr><tr align=\"center\"><td><B>"
											+ (iPassCount + iFailCount + iBlockedCount)
											+ "</B></td><td style=\"color:#008000\"><B>" + iPassCount
											+ "</B></td><td style=\"color:#FF0000\"><B>" + iFailCount
											+ "</B></td></tr>");

						} else {
							if ((totalTestCount - testLevelFailCount) == (testLevelPassCount)) {
								fileContent.replace(m, m + n,
										"<tr bgcolor=\"#C2DFFF\"><th align=\"left\" colspan=\"2\"><a href=\"#"
												+ sPrevUseCaseName + "\">" + sPrevUseCaseName
												+ "</a></th><th colspan=\"2\" style=\"color:#FF0000\">Fail</th></tr></table><br></br><br></br><table class= \"UseCase-TestLevelSummary1\"  border=\"1\">");
								sConcatenate1 = "<div";
								iLeftOffset1 = fileContent.indexOf(sConcatenate1);
								sConcatenate2 = "p.render(\"pieCanvas\", \"Pie Graph\")</script>";
								iRightOffset1 = fileContent.indexOf(sConcatenate2);
								sConcatenate3 = "<table border=\"1\" align=\"center\">";
								iLeftOffset2 = fileContent.indexOf(sConcatenate3);
								sConcatenate4 = "</td></tr>";
								iRightOffset2 = fileContent.indexOf(sConcatenate4);

								iFailCount = iFailCount + 1;

								fileContent.replace(iLeftOffset1, iRightOffset1 + sConcatenate2.length(),
										"<div id=\"pieCanvas\" style=\"overflow: auto; position:relative;height:350px;width:380px;\"></div><script type=\"text/javascript\">var p = new pie();p.add(\"Pass\","
												+ iPassCount + ");p.add(\"Fail\"," + iFailCount
												+ ");p.render(\"pieCanvas\", \"Pie Graph\")</script>");

								fileContent.replace(iLeftOffset2, iRightOffset2 + sConcatenate4.length(),
										"<table border=\"1\" align=\"center\"><tr bgcolor=\"#C2DFFF\"><th>Total No.of Use Cases</th><th>Use Cases Passed</th><th>Use Cases Failed</th></tr><tr align=\"center\"><td><B>"
												+ (iPassCount + iFailCount + iBlockedCount)
												+ "</B></td><td style=\"color:#008000\"><B>" + iPassCount
												+ "</B></td><td style=\"color:#FF0000\"><B>" + iFailCount
												+ "</B></td></tr>");

							}
						}

						testLevelPassCount = 0;
						testLevelFailCount = 0;
						testLevelBlockCount = 0;
						totalTestCount = 0;
						useCaseCount = useCaseCount + 1;
						if (!(Global.useCaseName.equalsIgnoreCase("AfterSuite"))) {
							CreateNewUseCaseTestLevelSummaryTable();
						}
						// Test Suite Start and End Time
						else {

							testEnvironmentName = "Production Environment";
							hostMachineName = "";
							testPackName = "Smoke suite";
							buildVersionName = "v11.0";

							tset = testSuiteEndTime();
							String amsr = "<h1 align=\"center\">Automation Suite Results</h1>";
							int rsma = fileContent.indexOf(amsr);
							int msra = amsr.length();
							fileContent.replace(rsma, rsma + msra,
									"<h1 align=\"center\">Automation Suite Results</h1><br><br><br></br>"
											// + " <B>Suite Started at : "+tsst+"</B><br></br><B> Suite Ended at :
											// "+tset+"</B><br></br>");
											+ "<table name = \"Environment_Details\" style=\"background-color:lightgreen;\"><tr><th><h3 align=\"center\">LM Automated Smoke Suite Test Summary</h3></th><th></th></tr>"
											+ "<tr><td>Test Environment</td><td>" + testEnvironmentName + "</td></tr>"
											+ "<tr><td>Host Machine</td><td>" + hostMachineName + "</td></tr>"
											+ "<tr><td>Date of Execution</td><td>" + getCurrentTimestamp()
											+ "</td></tr>" + "<tr><td>Test Pack Name</td><td>" + testPackName
											+ "</td></tr>" + "<tr><td>Build Version</td><td>" + buildVersionName
											+ "</td></tr>" + "</table>");
						}
						sPrevUseCaseName = Global.useCaseName;
					}

					str1 = "</center></body></html>";
					x = fileContent.indexOf(str1);
					y = str1.length();

					if (sStatus.equalsIgnoreCase("pass")) {
						stepLevelPassCount = stepLevelPassCount + 1;
						totalStepCount = totalStepCount + 1;

						if (!(Global.sScriptName.equalsIgnoreCase("AfterSuiteScript"))) {

							if (Global.curHighLight) {
								stepNo = stepNo + 1;
								prevSubVersion = Double.parseDouble(Integer.toString(stepNo));
								fileContent.replace(x, x + y, "<table class= \"Test-StepLevelExplanaton" + scriptCount
										+ "\"  border=\"1\" width=\"100%\"><tr bgcolor=\"#FFF8C6\"><th colspan=\"3\"><a name=\""
										+ Global.sScriptName + "\">" + Global.sScriptName
										+ "</a></th><th colspan=\"4\">Start Time : " + (testStartTime())
										+ "</th></tr><tr bgcolor=\"#C2DFFF\"><th>Step No.</th><th>Step Description</th><th>Expected Result</th><th>Actual Result</th><th>Status</th><th>Screenshots</th><th>Breadcrumb Trail</th></tr><tr style=\"color:#008000\"><td width=\"4%\">"
										+ stepNo + "</td><td width=\"30%\"><B>" + sStep + "</B></td><td width=\"30%\">"
										+ sExpectedResult + "</td><td width=\"30%\">" + sDescription
										+ "</td><td width=\"6%\">" + sStatus
										+ "</td><td>&nbsp;</td><td>&nbsp;</td></tr></table><br></br><br></br></center></body></html>");
								HeaderUpdation();
							} else {
								fileContent.replace(x, x + y, "<table class= \"Test-StepLevelExplanaton" + scriptCount
										+ "\"  border=\"1\" width=\"100%\"><tr bgcolor=\"#FFF8C6\"><th colspan=\"3\"><a name=\""
										+ Global.sScriptName + "\">" + Global.sScriptName
										+ "</a></th><th colspan=\"4\">Start Time : " + (testStartTime())
										+ "</th></tr><tr bgcolor=\"#C2DFFF\"><th>Step No.</th><th>Step Description</th><th>Expected Result</th><th>Actual Result</th><th>Status</th><th>Screenshots</th><th>Breadcrumb Trail</th></tr><tr style=\"color:#008000\"><td width=\"4%\">"
										+ "</td><td width=\"30%\">" + sStep + "</td><td width=\"30%\">"
										+ sExpectedResult + "</td><td width=\"30%\">" + sDescription
										+ "</td><td width=\"6%\">" + sStatus + "</td><td>&nbsp;</td><td>" + getBC()
										+ "</td></tr></table><br></br><br></br></center></body></html>");
								hP = hP + 1;
								hT = hT + 1;
							}

						}
						// ===========================
						else {
							HeaderUpdation();
						}
						// ===========================
					} else if (sStatus.equalsIgnoreCase("fail")) {

						stepLevelFailCount = stepLevelFailCount + 1;
						totalStepCount = totalStepCount + 1;
						if (!(Global.sScriptName.equalsIgnoreCase("AfterSuiteScript"))) {

							if (Global.curHighLight) {
								stepNo = stepNo + 1;
								prevSubVersion = Double.parseDouble(Integer.toString(stepNo));
								fileContent.replace(x, x + y, "<table class= \"Test-StepLevelExplanaton" + scriptCount
										+ "\"  border=\"1\" width=\"100%\"><tr bgcolor=\"#FFF8C6\"><th colspan=\"3\"><a name=\""
										+ Global.sScriptName + "\">" + Global.sScriptName
										+ "</a></th><th colspan=\"4\">Start Time : " + (testStartTime())
										+ "</th></tr><tr bgcolor=\"#C2DFFF\"><th>Step No.</th><th>Step Description</th><th>Expected Result</th><th>Actual Result</th><th>Status</th><th>Screenshots</th><th>Breadcrumb Trail</th></tr><tr style=\"color:#FF0000\"><td width=\"4%\">"
										+ stepNo + "</td><td width=\"30%\"><B>" + sStep + "</B></td><td width=\"30%\">"
										+ sExpectedResult + "</td><td width=\"30%\">" + sDescription
										+ "</td><td width=\"6%\">" + sStatus
										+ "</td><td>&nbsp;</td><td>&nbsp;</td></tr></table><br></br><br></br></center></body></html>");
								HeaderUpdation();
							} else {
								fileContent.replace(x, x + y, "<table class= \"Test-StepLevelExplanaton" + scriptCount
										+ "\"  border=\"1\" width=\"100%\"><tr bgcolor=\"#FFF8C6\"><th colspan=\"3\"><a name=\""
										+ Global.sScriptName + "\">" + Global.sScriptName
										+ "</a></th><th colspan=\"4\">Start Time : " + (testStartTime())
										+ "</th></tr><tr bgcolor=\"#C2DFFF\"><th>Step No.</th><th>Step Description</th><th>Expected Result</th><th>Actual Result</th><th>Status</th><th>Screenshots</th><th>Breadcrumb Trail</th></tr><tr style=\"color:#FF0000\"><td width=\"4%\">"
										+ "</td><td width=\"30%\">" + sStep + "</td><td width=\"30%\">"
										+ sExpectedResult + "</td><td width=\"30%\">" + sDescription
										+ "</td><td width=\"6%\">" + sStatus + "</td><td>" + captureScreen()
										+ "</td><td>" + getBC()
										+ "</td></tr></table><br></br><br></br></center></body></html>");
								hF = hF + 1;
								hT = hT + 1;
							}

						}
					} else {

						stepLevelBlockCount = stepLevelBlockCount + 1;
						totalStepCount = totalStepCount + 1;
						fileContent.replace(x, x + y, "<table class= \"Test-StepLevelExplanaton" + scriptCount
								+ "\"  border=\"1\" width=\"100%\"><tr bgcolor=\"#FFF8C6\"><th colspan=\"3\"><a name=\""
								+ Global.sScriptName + "\">" + Global.sScriptName
								+ "</a></th><th colspan=\"4\">Start Time : " + (testStartTime())
								+ "</th></tr><tr bgcolor=\"#C2DFFF\"><th>Step No.</th><th>Step Description</th><th>Expected Result</th><th>Actual Result</th><th>Status</th><th>Screenshots</th><th>Breadcrumb Trail</th></tr><tr style=\"color:#330099\"><td width=\"4%\">"
								+ stepNo + "</td><td width=\"30%\">" + sStep + "</td><td width=\"30%\">"
								+ sExpectedResult + "</td><td width=\"30%\">" + sDescription + "</td><td width=\"6%\">"
								+ sStatus
								+ "</td><td>&nbsp;</td></tr></table><br></br><br></br></center></body></html>");
					}

					sPrevScriptName = Global.sScriptName;
				}
			} else {
				fileContent = new StringBuilder();
				in = new BufferedReader(new FileReader(Global.sFilePath));
				while ((line = in.readLine()) != null) {
					fileContent.append(line);
				}
				in.close();
				str1 = "</table><br></br><br></br></center></body></html>";
				x = fileContent.indexOf(str1);
				y = str1.length();
				// stepNo=stepNo+1;
				if (sStatus.equalsIgnoreCase("pass")) {
					stepLevelPassCount = stepLevelPassCount + 1;
					totalStepCount = totalStepCount + 1;
					if (Global.curHighLight) {
						stepNo = stepNo + 1;
						prevSubVersion = Double.parseDouble(Integer.toString(stepNo));
						fileContent.replace(x, x + y, "<tr style=\"color:#008000\"><td width=\"4%\">" + stepNo
								+ "</td><td width=\"30%\"><B>" + sStep + "</B></td><td width=\"30%\">" + sExpectedResult
								+ "</td><td width=\"30%\">" + sDescription + "</td><td width=\"6%\">" + sStatus
								+ "</td><td>&nbsp;</td><td>&nbsp;</td></tr></table><br></br><br></br></center></body></html>");
						HeaderUpdation();
					} else {
						prevSubVersion = SubVersion(prevSubVersion);
						fileContent.replace(x, x + y,
								"<tr style=\"color:#008000\"><td width=\"4%\">" + prevSubVersion
										+ "</td><td width=\"30%\">" + sStep + "</td><td width=\"30%\">"
										+ sExpectedResult + "</td><td width=\"30%\">" + sDescription
										+ "</td><td width=\"6%\">" + sStatus + "</td><td>&nbsp;</td><td>" + getBC()
										+ "</td></tr></table><br></br><br></br></center></body></html>");
						hP = hP + 1;
						hT = hT + 1;
					}

				} else if (sStatus.equalsIgnoreCase("fail")) {
					stepLevelFailCount = stepLevelFailCount + 1;
					totalStepCount = totalStepCount + 1;
					if (Global.curHighLight) {
						stepNo = stepNo + 1;
						prevSubVersion = Double.parseDouble(Integer.toString(stepNo));
						fileContent.replace(x, x + y, "<tr style=\"color:#FF0000\"><td width=\"4%\">" + stepNo
								+ "</td><td width=\"30%\"><B>" + sStep + "</B></td><td width=\"30%\">" + sExpectedResult
								+ "</td><td width=\"30%\">" + sDescription + "</td><td width=\"6%\">" + sStatus
								+ "</td><td>&nbsp;</td><td>&nbsp;</td></tr></table><br></br><br></br></center></body></html>");
						HeaderUpdation();
					} else {
						prevSubVersion = SubVersion(prevSubVersion);
						fileContent.replace(x, x + y, "<tr style=\"color:#FF0000\"><td width=\"4%\">" + prevSubVersion
								+ "</td><td width=\"30%\">" + sStep + "</td><td width=\"30%\">" + sExpectedResult
								+ "</td><td width=\"30%\">" + sDescription + "</td><td width=\"6%\">" + sStatus
								+ "</td><td>" + captureScreen() + "</td><td>" + getBC()
								+ "</td></tr></table><br></br><br></br></center></body></html>");
						hF = hF + 1;
						hT = hT + 1;
					}
				} else {
					stepLevelBlockCount = stepLevelBlockCount + 1;
					totalStepCount = totalStepCount + 1;
					fileContent.replace(x, x + y,
							"<tr style=\"color:#330099\"><td width=\"4%\">" + stepNo + "</td><td width=\"30%\">" + sStep
									+ "</td><td width=\"30%\">" + sExpectedResult + "</td><td width=\"30%\">"
									+ sDescription + "</td><td width=\"6%\">" + sStatus
									+ "</td><td>&nbsp;</td></tr></table><br></br><br></br></center></body></html>");
				}
			}
			out = new BufferedWriter(new FileWriter(Global.sFilePath));
			out.write(fileContent.toString());
			out.close();
		} catch (Exception e) {
			logger.error("Exception HAppened ::"+e.getMessage());
		}
		// ============================
	}

	public static void updateUseCaseTestLevelSummary1Table() {

		str1 = "<table class= \"UseCase-TestLevelSummary1\"  border=\"1\"></table>";
		x = fileContent.indexOf(str1);
		y = str1.length();
		fileContent.replace(x, x + y,
				"<table class= \"UseCase-TestLevelSummary1\"  border=\"1\"><tr bgcolor=\"#C2DFFF\"><th align=\"left\" colspan=\"2\"><a name=\""
						+ Global.useCaseName + "\">" + Global.useCaseName + "</a></th><th align=\"left\" colspan=\"2\">"
						+ Global.useCaseDescription
						+ "</th></tr><tr bgcolor=\"#C2DFFF\"><th>Total No.of Test Cases</th><th>Test Cases Passed</th><th>Test Cases Failed</th></tr><tr align=\"center\"><td><B>"
						+ (totalTestCount) + "</B></td><td style=\"color:#008000\"><B>" + testLevelPassCount
						+ "</B></td><td style=\"color:#FF0000\"><B>" + testLevelFailCount
						+ "</B></td></tr></table><br></br><br></br>");
		try {
			out = new BufferedWriter(new FileWriter(Global.sFilePath));
			out.write(fileContent.toString());
			out.close();
		} catch (Exception e) {
			logger.error("Exception HAppened ::"+e.getMessage());
		}
	}

	public static void CreateNewUseCaseTestLevelSummaryTable() {

		str1 = "<table class= \"Test-StepLevelExplanaton1\"  border=\"1\" width=\"100%\">";
		x = fileContent.indexOf(str1);
		y = str1.length();
		fileContent.replace(x, x + y, "<table class= \"UseCase-TestLevelSummary" + useCaseCount
				+ "\"  border=\"1\"><tr bgcolor=\"#C2DFFF\"><th align=\"left\" colspan=\"2\"><a name=\""
				+ Global.useCaseName + "\">" + Global.useCaseName + "</a></th><th align=\"left\" colspan=\"2\">"
				+ Global.useCaseDescription
				+ "</th></tr><tr bgcolor=\"#C2DFFF\"><th>Total No.of Test Cases</th><th>Test Cases Passed</th><th>Test Cases Failed</th></tr><tr align=\"center\"><td><B>"
				+ (totalTestCount) + "</B></td><td style=\"color:#008000\"><B>" + testLevelPassCount
				+ "</B></td><td style=\"color:#FF0000\"><B>" + testLevelFailCount
				+ "</B></td></tr></table><br></br><br></br><table class= \"Test-StepLevelExplanaton1\"  border=\"1\" width=\"100%\">");
		try {
			out = new BufferedWriter(new FileWriter(Global.sFilePath));
			out.write(fileContent.toString());
			out.close();
		} catch (Exception e) {
			logger.error("Exception HAppened ::"+e.getMessage());
		}
	}

	public static void insertTestRowInPreviousUseCase() {

		str1 = "</table><br></br><br></br><table class= \"Test-StepLevelExplanaton1\"  border=\"1\" width=\"100%\">";
		x = fileContent.indexOf(str1);
		y = str1.length();

		if (totalStepCount == stepLevelPassCount) {

			fileContent.replace(x, x + y, "<tr bgcolor=\"#C2DFFF\"><th align=\"left\" colspan=\"2\"><a href=\"#"
					+ sPrevScriptName + "\">" + sPrevScriptName
					+ "</a></th><th colspan=\"2\" style=\"color:#008000\"><B>Pass</B></th></tr></table><br></br><br></br><table class= \"Test-StepLevelExplanaton1\"  border=\"1\" width=\"100%\">");
			str2 = "<tr align=\"center\"><td><B>" + (totalTestCount) + "</B></td><td style=\"color:#008000\"><B>"
					+ testLevelPassCount + "</B></td><td style=\"color:#FF0000\"><B>" + testLevelFailCount
					+ "</B></td></tr>";

			p = fileContent.lastIndexOf(str2);
			q = str2.length();
			testLevelPassCount = testLevelPassCount + 1;
			totalTestCount = totalTestCount + 1;
			singleUseCaseTotalTestCount = singleUseCaseTotalTestCount + 1;
			singleUseCaseTotalTestLevelPassCount = singleUseCaseTotalTestLevelPassCount + 1;
			// fileContent.replace(p, q+p,"<tr
			// align=\"center\"><td>"+(totalTestCount)+"</td><td>"+testLevelPassCount+"</td><td>"+testLevelFailCount+"</td></tr>");
			fileContent.replace(p, q + p,
					"<tr align=\"center\"><td><B>" + (totalTestCount) + "</B></td><td style=\"color:#008000\"><B>"
							+ testLevelPassCount + "</B></td><td style=\"color:#FF0000\"><B>" + testLevelFailCount
							+ "</B></td></tr>");
		} else if ((totalStepCount - stepLevelFailCount) == (stepLevelPassCount)) {
			fileContent.replace(x, x + y, "<tr bgcolor=\"#C2DFFF\"><th align=\"left\" colspan=\"2\"><a href=\"#"
					+ sPrevScriptName + "\">" + sPrevScriptName
					+ "</a></th><th colspan=\"2\" style=\"color:#FF0000\"><B>Fail</B></th></tr></table><br></br><br></br><table class= \"Test-StepLevelExplanaton1\"  border=\"1\" width=\"100%\">");
			// str2="<tr
			// align=\"center\"><td>"+(totalTestCount)+"</td><td>"+testLevelPassCount+"</td><td>"+testLevelFailCount+"</td></tr>";
			str2 = "<tr align=\"center\"><td><B>" + (totalTestCount) + "</B></td><td style=\"color:#008000\"><B>"
					+ testLevelPassCount + "</B></td><td style=\"color:#FF0000\"><B>" + testLevelFailCount
					+ "</B></td></tr>";
			p = fileContent.lastIndexOf(str2);
			q = str2.length();
			testLevelFailCount = testLevelFailCount + 1;
			totalTestCount = totalTestCount + 1;
			singleUseCaseTotalTestCount = singleUseCaseTotalTestCount + 1;
			singleUseCaseTotalTestLevelFailCount = singleUseCaseTotalTestLevelFailCount + 1;
			// fileContent.replace(p, q+p,"<tr
			// align=\"center\"><td>"+(totalTestCount)+"</td><td>"+testLevelPassCount+"</td><td>"+testLevelFailCount+"</td></tr>");
			fileContent.replace(p, q + p,
					"<tr align=\"center\"><td><B>" + (totalTestCount) + "</B></td><td style=\"color:#008000\"><B>"
							+ testLevelPassCount + "</B></td><td style=\"color:#FF0000\"><B>" + testLevelFailCount
							+ "</B></td></tr>");

		} else {
			// fileContent.replace(x, y, "<tr bgcolor=\"#C2DFFF\"><th align=\"left\"
			// colspan=\"2\"><a
			// href=\"#"+sPrevScriptName+"\">"+sPrevScriptName+"</a></th><th
			// colspan=\"2\">Fail</th></tr></table><br></br><br></br><table class=
			// \"Test-StepLevelExplanaton1\" border=\"1\">");
		}

		try {
			out = new BufferedWriter(new FileWriter(Global.sFilePath));
			out.write(fileContent.toString());
			out.close();
		} catch (Exception e) {
			logger.error("Exception HAppened ::"+e.getMessage());
		}

		stepLevelPassCount = 0;
		stepLevelFailCount = 0;
		stepLevelBlockCount = 0;
		totalStepCount = 0;
		// time taken
		jkm = "</table><br></br><br></br></center></body></html>";
		mkj = fileContent.indexOf(jkm);
		kjm = jkm.length();
		fileContent.replace(mkj, mkj + kjm,
				"<tr bgcolor=\"#FFF8C6\"><th colspan=\"3\">End Time : " + (testEndTime()) + "</th><th colspan=\"4\">"
						+ timetaken(ts, te) + "</th></tr></table><br></br><br></br></center></body></html>");
		// time taken
	}

	public static String testStartTime() {
		testStart = new Date();
		ts = testStart.getTime();
		return testStart.toString();
	}

	public static String testEndTime() {
		testEnd = new Date();
		te = testEnd.getTime();
		return testEnd.toString();
	}

	public String useCaseStartTime() {
		useCaseStart = new Date();
		us = useCaseStart.getTime();
		return useCaseStart.toString();
	}

	public String useCaseEndTime() {
		useCaseEnd = new Date();
		ue = useCaseEnd.getTime();
		return useCaseEnd.toString();
	}

	public static String testSuiteStartTime() {
		testSuiteStart = new Date();
		tss = testSuiteStart.getTime();
		return testSuiteStart.toString();
	}

	public static String testSuiteEndTime() {
		testSuiteEnd = new Date();
		tse = testSuiteEnd.getTime();
		return testSuiteEnd.toString();
	}

	public static String timetaken(long s, long e) {

		long diff = e - s;
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000);

		return "Total Time : " + diffHours + " Hours " + diffMinutes + " Minutes " + diffSeconds + " Seconds";

	}

	public static String getCurrentTimestamp() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

	public static void HeaderUpdation() {
		String hStr = "<td width=\"30%\"><B>" + Global.prevHeading + "</B></td><td width=\"30%\">" + Global.PER
				+ "</td><td width=\"30%\">" + Global.PEAR + "</td><td width=\"6%\">" + "Pass";
		logger.info("Going to found index");
		hStrIndex = fileContent.indexOf(hStr);
		logger.info("Index found :" + hStrIndex);
		logger.info("EEEE" + hStr);
		hStrLen = hStr.length();
		if ((!(Global.prevHeading.equals(""))) && (!(Global.curHeading.equals(Global.prevHeading)))) {
			if (hT == hP) {
				fileContent.replace(hStrIndex, hStrIndex + hStrLen,
						"<td width=\"30%\"><B><font color=\"green\">" + Global.prevHeading
								+ "</font></B></td><td width=\"30%\">" + Global.PER + "</td><td width=\"30%\">"
								+ Global.PEAR + "</td><td width=\"6%\">" + "Pass");
				logger.info("Replace Done");
			} else {
				fileContent.replace(hStrIndex, hStrIndex + hStrLen,
						"<td width=\"30%\"><B><font color=\"red\">" + Global.prevHeading
								+ "</font></B></td><td width=\"30%\"><font color=\"red\">" + Global.PER
								+ "</font></td><td width=\"30%\"><font color=\"red\">" + Global.PUEAR
								+ "</font></td><td width=\"6%\"><font color=\"red\">" + "Fail</font>");
			}

		}

		hP = 0;
		hF = 0;
		hT = 0;

	}

	public static void HeaderUpdation1() {
		String hStr = "<th>" + Global.prevHeading + "</th><th>Pass";
		hStrIndex = fileContent.indexOf(hStr);
		hStrLen = hStr.length();
		if ((!(Global.prevHeading.equals(""))) && (!(Global.curHeading.equals(Global.prevHeading)))) {
			if (hT == hP) {
				fileContent.replace(hStrIndex, hStrIndex + hStrLen, "<th>" + Global.prevHeading + "</th><th>Pass");
			} else {
				fileContent.replace(hStrIndex, hStrIndex + hStrLen, "<th>" + Global.prevHeading + "</th><th>Fail");
			}

		}

		hP = 0;
		hF = 0;
		hT = 0;

	}

	public static String getBC() {

		if (!(Global.curBC.equals(Global.prevBC))) {
			Global.prevBC = Global.curBC;
			return Global.curBC;
		} else {
			// return "";
			return "&nbsp;";
		}
	}

	public static double SubVersion(double num) {
		DecimalFormat dc = new DecimalFormat("#0.00");
		double subNum = num + 0.01;
		return Double.parseDouble((dc.format(subNum)));
	}

}
