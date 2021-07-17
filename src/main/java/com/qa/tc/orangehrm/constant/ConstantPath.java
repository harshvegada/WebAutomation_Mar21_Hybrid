package com.qa.tc.orangehrm.constant;

public class ConstantPath {

	/**
	 * Selenium Configs
	 */
	public static final String CHROMEDRIVER = "webdriver.chrome.driver";
	public static final String CHROMEDRIVEREXE = ".//resources/chromedriver.exe";
	public static final int AVGWAIT = 30;
	public static final String LOG4J = "./src/main/resources/log4j.properties";
	public static final String EXTENT_REPORT_CONFIG = "./src/main/resources/extent-config.xml";

	/**
	 * Test Data Configs
	 */
	public static final String TESTDATA = "./src/test/resources/testData";

	/**
	 * Pages Locator Files
	 */
	private static final String BASEDIR = "./src/main/resources/pagesElementProperties/";
	public static final String DASHBOARDPAGE = BASEDIR + "dashboardPage.properties";
	public static final String LOGINPAGE = BASEDIR + "loginPage.properties";
	public static final String PIMPAGE = BASEDIR + "pimPage.properties";
}
