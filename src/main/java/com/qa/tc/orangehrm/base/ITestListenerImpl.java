package com.qa.tc.orangehrm.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.google.common.base.Strings;
import com.qa.tc.orangehrm.reports.ExtentReportClass;

public class ITestListenerImpl extends PredefinedActions implements ITestListener, IAnnotationTransformer {

	Logger log = Logger.getLogger(ITestListenerImpl.class);
	private String defalultBrowser = "chrome";
	private String defaultEnv = "qa";
	private String browser;
	private String env;

	public static void main(String[] args) {
		System.out.println(System.getProperty(""));
	}
	
	
	@Override
	public void onTestStart(ITestResult result) {
		ExtentReportClass.createTest(result.getMethod().getMethodName());
		start(browser, env);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentReportClass.pass(result);
		tearDown();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentReportClass.fail(result);
		tearDown();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentReportClass.skip(result);
		tearDown();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		browser = Strings.isNullOrEmpty(System.getProperty("browser")) ? defalultBrowser
				: System.getProperty("browser");
		env = Strings.isNullOrEmpty(System.getProperty("env")) ? defaultEnv : System.getProperty("env");
		ExtentReportClass.initReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentReportClass.flushReports();
	}

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryTestCase.class);
	}

}
