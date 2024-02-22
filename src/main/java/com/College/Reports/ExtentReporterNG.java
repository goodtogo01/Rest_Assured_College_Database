package com.College.Reports;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.College.Utils.Utilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporterNG extends Utilities implements IReporter  {
	private ExtentReports extent;
	public 	ExtentTest test;
	String filePath = System.getProperty("user.dir")+"\\Reports\\"+reportName();

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		extent = new ExtentReports(filePath + ".html", true);
		for (ISuite suite : suites) {

			Map<String, ISuiteResult> result = suite.getResults();
			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();
				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			 
				
				
			}
		}
		
		test.getDescription();
		extent.flush();
		extent.close();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status) {
		//ExtentTest test;
		
		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());
				/*
				 * test.getTest(). = getTime(result.getStartMillis()); test.getTest().endedTime
				 * = getTime(result.getEndMillis());
				 */
				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);
				String message = "This Test is " + status.toString().toLowerCase() + "ed!";
				if (result.getThrowable() != null)
					message = result.getThrowable().getMessage();
				test.log(status, message);
				extent.endTest(test);
			}
			
		}
	}
	

}
