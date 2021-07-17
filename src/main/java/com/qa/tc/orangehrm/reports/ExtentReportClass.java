package com.qa.tc.orangehrm.reports;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.tc.orangehrm.base.PredefinedActions;
import com.qa.tc.orangehrm.constant.ConstantPath;

public final class ExtentReportClass {

	private Logger log = Logger.getLogger(this.getClass().getName());

	private ExtentReportClass() {

	}

	private static ExtentReports extent;
	public static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	public static void initReport() {
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("./extent-reports/extent-report.html");
		spark.config().setTheme(Theme.STANDARD);
		try {
			spark.loadXMLConfig(ConstantPath.EXTENT_REPORT_CONFIG);
		} catch (IOException e) {
			ExtentReportClass.extentTest.get().fail("Extentent config file missing");
		}
		// spark.config().setDocumentTitle("Extent Reports");
		// spark.config().setReportName("Extent Report Practice");
		// spark.config().setDocumentTitle("Technocredits Extent Report");
		extent.attachReporter(spark);
	}

	public static void flushReports() {
		extent.flush();
	}

	public static void createTest(String testcaseName) {
		extentTest.set(extent.createTest(testcaseName));
		extentTest.set(extentTest.get().createNode("Test Case : " + testcaseName));
	}

	public static void pass(ITestResult result) {
		ExtentTest test = extentTest.get();
		test.log(Status.PASS, "Test Case Passed : " + result.getMethod().getMethodName());
	}

	public static void skip(ITestResult result) {
		ExtentTest test = extentTest.get();
		test.addScreenCaptureFromBase64String(PredefinedActions.takesScreenShot());
		test.log(Status.SKIP, result.getThrowable());
	}

	public static void fail(ITestResult result) {
		ExtentTest test = extentTest.get();
		test.addScreenCaptureFromBase64String(PredefinedActions.takesScreenShot());
		test.log(Status.FAIL, result.getThrowable());
	}

}
