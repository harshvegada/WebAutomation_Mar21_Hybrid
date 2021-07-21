package testscripts;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.tc.orangehrm.pages.DashboardPage;
import com.qa.tc.orangehrm.pages.LoginPage;

public class LoginTest extends TestBase {

	static Logger log = Logger.getLogger(LoginTest.class);

	@Test
	public void verify_user_able_to_login_and_11_quick_access_is_displayed() {
		LoginPage loginPage = LoginPage.getPageInstance();
		Assert.assertTrue(loginPage.isLogoDisplayed(), "Logo not displayed on Login Page");
		log.info("Logo displayed");
		loginPage.login("admin", "A@1BomfBL3");
		log.info("Username & password enter");
		DashboardPage dashboardPage = DashboardPage.getPageInstance();
		Assert.assertEquals(11, dashboardPage.verifyAllWidgetList().size());
		log.info("All the widgets displayed");
		Assert.assertTrue(dashboardPage.verifyAllWidgetDisplayed(), "Some Widgets not displayed on Dashboard Page");
		Assert.assertTrue(dashboardPage.isUSerProfileDisplayed(), "User Profile not displayed on Dashboard Page");
		log.info("user profile displayed");
	}

}
