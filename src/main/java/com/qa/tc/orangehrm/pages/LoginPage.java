package com.qa.tc.orangehrm.pages;

import org.apache.log4j.Logger;

import com.qa.tc.orangehrm.base.PredefinedActions;
import com.qa.tc.orangehrm.constant.ConstantPath;
import com.qa.tc.orangehrm.utils.PropertiesFileOperation;

public class LoginPage extends PredefinedActions {

	Logger log = Logger.getLogger(LoginPage.class);
	private static LoginPage loginPage;
	private PropertiesFileOperation properties;

	private LoginPage() {
		properties = new PropertiesFileOperation(ConstantPath.LOGINPAGE);
		log.debug("Login Page Object created & Elements loaded in memory");
	}

	public static LoginPage getPageInstance() {
		if (loginPage == null)
			loginPage = new LoginPage();
		return loginPage;
	}

	public void enterUserName(String userName) {
		enterText(properties.getValue("loginPageUserName"), userName);
		log.info("enter text as " + userName);
	}

	public void enterPassword(String password) {
		enterText(properties.getValue("loginPagePassword"), password);
		log.info("enter text as " + password);
	}

	public void clickOnSignIn() {
		clickOnElement(properties.getValue("loginPageSignInButton"));
		log.info("click on sign in button");
	}

	public void login(String userName, String password) {
		enterUserName(userName);
		enterPassword(password);
		clickOnSignIn();
	}

	public String getTitleOfpage() {
		log.debug("getting title for current page Login");
		return getCurrentPageTitle();
	}

	public boolean isLogoDisplayed() {
		log.debug("checking Logo display");
		return isElementDisplayed(properties.getValue("loginPageLogo"));
	}
}