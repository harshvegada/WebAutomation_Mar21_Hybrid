package com.qa.tc.orangehrm.base;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.tc.orangehrm.constant.ConstantPath;
import com.qa.tc.orangehrm.reports.ExtentReportClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PredefinedActions {

	Logger log = Logger.getLogger(PredefinedActions.class);
	static protected ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
	static private ThreadLocal<WebDriverWait> waitThread = new ThreadLocal<>();

	public void start(String browser, String env) {

		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driverThread.set(new ChromeDriver());
			break;

		default:
			Assert.fail("browser not available " + browser);
		}
		driverThread.get().manage().window().maximize();
		switch (env.toLowerCase()) {
		case "qa":
			driverThread.get().get("https://webautomation-trials71.orangehrmlive.com");
			break;
		case "prod":
			driverThread.get().get("https://webautomation-trials71.orangehrmlive.com");
			break;
		default:
			break;
		}
		waitThread.set(new WebDriverWait(driverThread.get(), ConstantPath.AVGWAIT));
		ExtentReportClass.extentTest.get().pass("Browser Launch Scuessfully");
		log.trace("Browser Launch Scuessfully");
	}

	private String getLocatorType(String locator) {
		String[] arr = locator.split("]:-");
		String locatorType = arr[0].substring(1);
		log.trace("Locator Type : " + locatorType);
		return locatorType;
	}

	private String getLocatorValue(String locator) {
		String[] arr = locator.split("]:-");
		log.trace("Locator Value : " + arr[1].trim());
		return arr[1].trim();
	}

	protected WebElement getElement(String locator, boolean isWaitRequired) {
		String locatorType = getLocatorType(locator);
		String locatorValue = getLocatorValue(locator);
		WebElement element;
		switch (locatorType.toUpperCase()) {
		case "XPATH":
			if (isWaitRequired)
				element = waitThread.get().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			else
				element = driverThread.get().findElement(By.xpath(locatorValue));
			break;

		case "ID":
			if (isWaitRequired)
				element = waitThread.get().until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			else
				element = driverThread.get().findElement(By.id(locatorValue));
			break;

		case "LINKTEXT":
			if (isWaitRequired)
				element = waitThread.get()
						.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
			else
				element = driverThread.get().findElement(By.linkText(locatorValue));
			break;

		case "CSS":
			if (isWaitRequired)
				element = waitThread.get()
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
			else
				element = driverThread.get().findElement(By.cssSelector(locatorValue));
			break;

		default:
			element = null;
			System.out.println("Invalid LocatorType");
		}
		log.trace("Locator found : " + locatorValue);
		ExtentReportClass.extentTest.get().pass("Locator found : " + locatorValue);
		return element;
	}

	protected WebElement getElement(String locatorType, String locator, boolean isWaitRequired) {
		WebElement element;
		switch (locatorType.toUpperCase()) {
		case "XPATH":
			if (isWaitRequired)
				element = waitThread.get().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			else
				element = driverThread.get().findElement(By.xpath(locator));
			break;

		case "ID":
			if (isWaitRequired)
				element = waitThread.get().until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
			else
				element = driverThread.get().findElement(By.id(locator));
			break;

		case "LINKTEXT":
			if (isWaitRequired)
				element = waitThread.get().until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locator)));
			else
				element = driverThread.get().findElement(By.linkText(locator));
			break;

		default:
			element = null;
			log.fatal("Element locator not valid : " + locatorType);
		}
		log.trace("Locator found : " + locator);
		ExtentReportClass.extentTest.get().pass("Locator found : " + locator);
		return element;
	}

	protected WebElement getElementInsideElement(WebElement parentElement, String parentLocator, String locatorType,
			String locator, boolean isWaitRequired) {
		WebElement element;
		boolean flag = false;
		switch (locatorType.toUpperCase()) {
		case "XPATH":
			if (isWaitRequired) {
				element = waitThread.get()
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(parentLocator + "/" + locator)));
				flag = waitThread.get().until(ExpectedConditions.textMatches(By.xpath(parentLocator + "/" + locator),
						Pattern.compile("^[^0]")));
			} else
				element = parentElement.findElement(By.xpath(locator));
			break;

		default:
			element = null;
			log.fatal("Invalid LocatorType");
		}
		log.trace("Locator found : " + locator);
		ExtentReportClass.extentTest.get().pass("Locator found : " + locator);
		return element;
	}

	protected List<WebElement> getAllElements(String locator, boolean isWaitRequired) {
		List<WebElement> element;
		String locatorType = getLocatorType(locator);
		String locatorValue = getLocatorValue(locator);

		switch (locatorType.toUpperCase()) {
		case "XPATH":
			if (isWaitRequired)
				element = waitThread.get()
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locatorValue)));
			else
				element = driverThread.get().findElements(By.xpath(locatorValue));
			break;

		case "ID":
			if (isWaitRequired)
				element = waitThread.get()
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locatorValue)));
			else
				element = driverThread.get().findElements(By.id(locatorValue));
			break;

		default:
			element = null;
			log.fatal("Invalid LocatorType");
		}
		log.trace("Locator found : " + locator);
		ExtentReportClass.extentTest.get().pass("Locator found : " + locator);
		return element;
	}

	protected List<WebElement> getAllElements(String locatorType, String locator, boolean isWaitRequired) {
		List<WebElement> element;
		switch (locatorType.toUpperCase()) {
		case "XPATH":
			if (isWaitRequired)
				element = waitThread.get()
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
			else
				element = driverThread.get().findElements(By.xpath(locator));
			break;

		case "ID":
			if (isWaitRequired)
				element = waitThread.get().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locator)));
			else
				element = driverThread.get().findElements(By.id(locator));
			break;

		default:
			element = null;
			log.info("Invalid LocatorType");
		}
		log.trace("Locator found : " + locator);
		ExtentReportClass.extentTest.get().pass("Locator found : " + locator);
		return element;
	}

	protected void scrollToElement(WebElement element) {
		JavascriptExecutor je = (JavascriptExecutor) driverThread.get();
		je.executeScript("arguments[0].scrollIntoView(true)", element);
		log.trace("element scrolled");
		ExtentReportClass.extentTest.get().pass("element scrolled");
	}

	protected boolean isElementDisplayed(WebElement element) {
		if (element.isDisplayed()) {
			return true;
		} else {
			scrollToElement(element);
			return element.isDisplayed();
		}
	}

	protected boolean isElementDisplayed(String locator) {
		try {
			WebElement element = getElement(locator, true);
			return isElementDisplayed(element);
		} catch (Exception e) {
			return false;
		}
	}

	protected boolean isElementDisplayed(String locatorType, String locator) {
		try {
			WebElement element = getElement(locatorType, locator, true);
			return isElementDisplayed(element);
		} catch (Exception e) {
			return false;
		}
	}

	protected String getElementText(WebElement element) {
		scrollToElement(element);
		log.trace("element text : " + element.getText());
		ExtentReportClass.extentTest.get().pass("element text : " + element.getText());
		return element.getText();
	}

	protected String getElementText(String locatorType, String locator) {
		log.trace("trying to get text : " + locator);
		ExtentReportClass.extentTest.get().pass("trying to get text " + locator);
		return getElement(locatorType, locator, true).getText();
	}

	protected String getElementText(String locator) {
		log.trace("trying to get text : " + locator);
		ExtentReportClass.extentTest.get().pass("trying to get text " + locator);
		return getElement(locator, true).getText();
	}

	protected void enterText(String locator, String text) {
		WebElement element = getElement(locator, true);
		if (element.isEnabled())
			element.sendKeys(text);
		log.trace("text set on element : " + text);
		ExtentReportClass.extentTest.get().pass("text set on element : " + text);
	}

	protected void clickOnElement(String locator) {
		WebElement element = getElement(locator, true);
		waitThread.get().until(ExpectedConditions.elementToBeClickable(element)).click();
		log.trace("click on  : " + locator);
		ExtentReportClass.extentTest.get().pass("click on  : " + locator);
	}

	protected void clickOnElement(String locatorType, String locator) {
		WebElement element = getElement(locatorType, locator, true);
		waitThread.get().until(ExpectedConditions.elementToBeClickable(element)).click();
		log.trace("click on  : " + locator);
		ExtentReportClass.extentTest.get().pass("click on  : " + locator);
	}

	protected String getElementAttribute(String locatorType, String locator, String attribute) {
		WebElement element = getElement(locatorType, locator, true);
		log.trace("getting element value : " + locator);
		ExtentReportClass.extentTest.get().pass("getting element value : " + locator);
		return element.getAttribute(attribute);
	}

	protected String getElementAttribute(String locator, String attribute) {
		WebElement element = getElement(locator, true);
		log.trace("getting element value : " + locator);
		ExtentReportClass.extentTest.get().pass("getting element value : " + locator);
		return element.getAttribute(attribute);
	}

	protected String getCurrentPageUrl() {
		return driverThread.get().getCurrentUrl();
	}

	protected void staticWait(int waitTime) {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String takesScreenShot() {
		TakesScreenshot ts = (TakesScreenshot) driverThread.get();
		return ts.getScreenshotAs(OutputType.BASE64);
	}

	public void tearDown() {
		log.trace("browser closed");
		ExtentReportClass.extentTest.get().pass("browser closed");
		driverThread.get().close();
	}

	public String getCurrentPageTitle() {
		log.trace("getting page title");
		ExtentReportClass.extentTest.get().pass("getting page title");
		return driverThread.get().getTitle();
	}
}
