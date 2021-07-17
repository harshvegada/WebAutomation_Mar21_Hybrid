package com.qa.tc.orangehrm.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.log4testng.Logger;

import com.qa.tc.orangehrm.base.PredefinedActions;
import com.qa.tc.orangehrm.constant.ConstantPath;
import com.qa.tc.orangehrm.utils.PropertiesFileOperation;

public class PimPage extends PredefinedActions {

	Logger log = Logger.getLogger(PimPage.class);

	private static PimPage pimPage;
	private PropertiesFileOperation properties;

	private PimPage() {
		properties = new PropertiesFileOperation(ConstantPath.PIMPAGE);
		log.debug("Pim Page Object created & Elements loaded in memory");
	}

	public static PimPage getPageInstance() {
		if (pimPage == null)
			pimPage = new PimPage();
		return pimPage;
	}

	public void clickOnPimAndClickOn(String sectionName) {
		clickOnElement(properties.getValue("pimTab"));
		log.debug("click On Pim Tab");
		switch (sectionName.toLowerCase()) {
		case "reports":
			clickOnElement(properties.getValue("reports"));
			log.debug("click on reports");
			break;

		case "configuration":
			clickOnElement(properties.getValue("configuration"));
			log.debug("click on configuration");
			break;

		default:
			break;
		}
		log.info("click on pim tab & " + sectionName);
	}

	public void clickOnReportingMethods() {
		clickOnElement(properties.getValue("reportingMethods"));
		log.info("click on reporting methods");
	}

	public boolean verifyReportNameDisplayed() {
		log.info("report title getting display");
		return isElementDisplayed(properties.getValue("reportTitleName"));
	}

	public void clickOnReportName(String reportName) {
		clickOnElement(String.format(properties.getValue("reportName"), reportName));
		log.info("click on " + reportName);
	}

	public String verifyCorrectIndividualReportNameDisplayed(String reportName) {
		log.info("getting report title for " + reportName);
		return getElementText(String.format(properties.getValue("individualReportTitle"), reportName));
	}

	public boolean verifyAllChartsDisplayed() {
		List<WebElement> list = getAllElements(properties.getValue("chartElement"), true);
		log.debug("all chart displayed");
		for (WebElement element : list)
			if (!element.isDisplayed())
				return false;
		return true;
	}

	public HashMap<String, Integer> getChartValue() {
		scrollToElement(getElement(properties.getValue("reportNameOnChart"), true));
		log.debug("scrolling till chart name");
		int numberOfBars = getAllElements(properties.getValue("chartValues"), true).size();
		log.info("total number of chart present on UI " + numberOfBars);
		HashMap<String, Integer> mapOfGenderCount = new HashMap<String, Integer>();
		for (int index = 1; index <= numberOfBars; index++) {
			Actions a = new Actions(driverThread.get());
			a.moveToElement(driverThread.get()
					.findElement(By.xpath("//*[name()='g'][@class='c3-chart-bars']//*[name()='path'][" + index + "]")))
					.build().perform();
			String genderValue = driverThread.get()
					.findElement(By.xpath("//div[@class='bar-chart-oxd-tooltip']/span[@class='title']")).getText();
			String genderCount = driverThread.get()
					.findElement(By.xpath("//div[@class='bar-chart-oxd-tooltip']/span[@class='value']")).getText();
			mapOfGenderCount.put(genderValue, Integer.parseInt(genderCount));
		}
		log.debug(mapOfGenderCount);
		return mapOfGenderCount;
	}

	public HashMap<String, Integer> getTableValue() {
		int numberOfRows = getAllElements(properties.getValue("tableValue"), true).size();
		log.info("total number of table rows on UI " + numberOfRows);
		HashMap<String, Integer> mapOfGenderFromTable = new HashMap<String, Integer>();
		String keyGender = "";
		int valueGender = 0;
		for (int index = 1; index <= numberOfRows; index++) {
			keyGender = driverThread.get().findElement(By.xpath(
					"//table[@class='bordered classic-table table-fixed']//tbody//tr[" + index + "]//td[1]//span"))
					.getText();
			valueGender = Integer.parseInt(driverThread.get().findElement(By.xpath(
					"//table[@class='bordered classic-table table-fixed']//tbody//tr[" + index + "]//td[2]//span"))
					.getText());
			mapOfGenderFromTable.put(keyGender, valueGender);
		}
		log.debug(mapOfGenderFromTable);
		return mapOfGenderFromTable;
	}

	public boolean verifyTitledisplayed(String title) {
		log.debug("checking report title");
		return isElementDisplayed(String.format(properties.getValue("reportTitle"), title));
	}

	public boolean verifyAllCheckBoxesAreEnabled() {
		List<WebElement> list = getAllElements(properties.getValue("checkBoxesEnabled"), true);
		log.debug("checking all checkbox are enabled");
		for (WebElement element : list)
			if (!element.isDisplayed())
				return false;
		return true;
	}

	public List<WebElement> getAllCheckBoxList() {
		log.debug("checking all checkbox enabled");
		return getAllElements(properties.getValue("checkBoxesEnabled"), true);
	}

	public void clickOnMoreAndClickOn(String action) {
		clickOnElement(properties.getValue("moreOptions"));
		log.debug("click on moreoption");
		clickOnElement(String.format(properties.getValue("buttonAction"), action));
		log.info("click on action button " + action);
	}

	public List<WebElement> getAllCheckboxWhicChecked() {
		log.debug("getting all checkboxes enabled");
		return getAllElements(properties.getValue("allCheckedBoxed"), true);
	}

	public void clickOnAddOption() {
		log.info("click on add option");
		clickOnElement(properties.getValue("addOption"));
	}

	public void addReportingMethod(String methodName) {
		enterText(properties.getValue("reportingMethod"), methodName);
		log.info("text " + methodName + " set on element text area");
	}

	public void clickOnSaveButtonOnreporting() {
		clickOnElement(properties.getValue("saveButtonOnReportMethod"));
		log.info("click on save button");
	}

	public boolean validateAddedMethodDisplayed(String methodsName) {
		log.debug("checking method name display");
		return getElement(String.format(properties.getValue("reportingMethodElement"), methodsName), true)
				.isDisplayed();
	}

}