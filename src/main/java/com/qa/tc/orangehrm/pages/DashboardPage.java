package com.qa.tc.orangehrm.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.qa.tc.orangehrm.base.PredefinedActions;
import com.qa.tc.orangehrm.constant.ConstantPath;
import com.qa.tc.orangehrm.utils.PropertiesFileOperation;

public class DashboardPage extends PredefinedActions {

	Logger log = Logger.getLogger(DashboardPage.class);
	private static DashboardPage dashboardPage;
	private PropertiesFileOperation properties;

	private DashboardPage() {
		properties = new PropertiesFileOperation(ConstantPath.DASHBOARDPAGE);
		log.debug("Dashboard Page Object created & Elements loaded in memory");
	}

	public static DashboardPage getPageInstance() {
		if (dashboardPage == null)
			dashboardPage = new DashboardPage();
		return dashboardPage;
	}

	public boolean isUSerProfileDisplayed() {
		log.debug("checking profile display");
		return isElementDisplayed(properties.getValue("dashboardPageProfile"));
	}

	public void clickOnArrowIcon() {
		clickOnElement(properties.getValue("dashboardPageArrowDown"));
		log.info("click on page down arrow");
	}

	public boolean verifyUserMenuDisplayed() {
		log.debug("getting menu list");
		return isElementDisplayed(properties.getValue("dashboardPageUserMenuList"));
	}

	public void clickOnAboutLink() {
		clickOnElement(properties.getValue("dashboardPageAboutLink"));
		log.info("click on about link");
	}

	public String getEmployeeNumbers() {
		log.debug("getting employee count");
		return getElementText(properties.getValue("dashboardPageEmpployeeNumber"));
	}

	public void clickOnOkyButtonOnPopup() {
		clickOnElement(properties.getValue("dashboardPagePopupOKButton"));
		log.info("click on Okay button");
	}

	public boolean verifyAllSectionAreCollapsible() {
		List<WebElement> collesibleList = getAllElements(properties.getValue("dashboardPageCollapsibleList"), true);
		log.debug("checking all section collapsoble");
		for (WebElement element : collesibleList)
			if (!element.getAttribute("class").equals("collapsible-body"))
				return false;
		return true;
	}

	public boolean verifyAllWidgetDisplayed() {
		List<WebElement> list = getAllElements(properties.getValue("dashboardPageWidgetMenu"), true);
		log.debug("checking all widget displayed");
		for (WebElement widget : list) {
			if (!widget.isDisplayed())
				return false;
		}
		return true;
	}

	public List<WebElement> verifyAllWidgetList() {
		log.debug("checking widget menus");
		return getAllElements(properties.getValue("dashboardPageWidgetMenu"), true);
	}

	public void clickOnAdminAndThenClickOn(String sectionName, String subSection) {
		clickOnElement(properties.getValue("dashBoardPageAdminMenu"));
		log.debug("click on Admin tab");
		switch (sectionName.toLowerCase()) {
		case "user management":
			clickOnElement(properties.getValue("dashboardPageUserManagment"));
			log.trace("click on user managment tab");
			switch (subSection.toLowerCase()) {
			case "users":
				clickOnElement(properties.getValue("dashboardPageUsers"));
				log.trace("click on users tab");
				break;

			default:
				break;
			}
			break;

		default:
			break;
		}
		log.info("click on Admin Tab then " + sectionName + " and " + subSection);
	}

	public void clickOnPIMAndThenClickOn(String sectionName) {
		clickOnElement(properties.getValue("dashboardPagePIMMenu"));
		log.debug("click on dashboard tab");
		switch (sectionName.toLowerCase()) {
		case "add employee":
			clickOnElement(properties.getValue("dashboardPageAddEmployeeSeubMenu"));
			log.trace("click on add employee tab");
			break;
		case "employee list":
			clickOnElement(properties.getValue("dashboardPageEmployeeListSubMenu"));
			log.trace("click on employee list tab");
			break;
		default:
			break;
		}
		log.info("click on PIM tab and " + sectionName);
	}

}
