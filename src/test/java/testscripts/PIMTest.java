package testscripts;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.tc.orangehrm.pages.LoginPage;
import com.qa.tc.orangehrm.pages.PimPage;

public class PIMTest extends TestBase {

	Logger log = Logger.getLogger(PIMTest.class);

	@Test
	public void count_employees_gender_wise() {
		LoginPage loginPage = LoginPage.getPageInstance();
		loginPage.login("admin", "A@1BomfBL3");
		log.info("username & password entered");
		PimPage pimPage = PimPage.getPageInstance();
		pimPage.clickOnPimAndClickOn("reports");
		log.info("click on report");
		Assert.assertTrue(pimPage.verifyReportNameDisplayed(), "Report Name Not Displayed on PIM Page");
		log.info("Report displayed");
		pimPage.clickOnReportName("Gender");
		log.info("click on report Gender");
		Assert.assertEquals(pimPage.verifyCorrectIndividualReportNameDisplayed("Gender"), "Gender");
		log.info("Report title matches");
		Assert.assertTrue(pimPage.verifyAllChartsDisplayed(), "Chart not visible");
		log.info("chart displayed");
		Assert.assertEquals(pimPage.getChartValue().size(), pimPage.getTableValue().size());
		log.info("chart & table data is same");
	}

	@Test
	public void verify_reporting_methods() {
		LoginPage loginPage = LoginPage.getPageInstance();
		loginPage.login("admin", "A@1BomfBL3");
		log.info("username & password entered");
		PimPage pimPage = PimPage.getPageInstance();
		pimPage.clickOnPimAndClickOn("configuration");
		log.info("click on configuration");
		pimPage.clickOnReportingMethods();
		log.info("click on reporting methods");
		Assert.assertTrue(pimPage.verifyTitledisplayed("Reporting Methods"));
		log.info("title matches");
		Assert.assertTrue(pimPage.verifyAllCheckBoxesAreEnabled(), "Check boxes not Enabled");
		log.info("all checkbox enabled");

		int totalCheckBoxes = pimPage.getAllCheckBoxList().size();
		log.info("total checkbox are :" + totalCheckBoxes);
		pimPage.clickOnMoreAndClickOn("Select All");
		log.info("Click on Select All");
		int checkedCheckbox = pimPage.getAllCheckboxWhicChecked().size();
		Assert.assertEquals(checkedCheckbox, totalCheckBoxes, "Selecte All function working fine");
		pimPage.clickOnMoreAndClickOn("Deselect All");
		log.info("Click on Deselect All");
		pimPage.clickOnAddOption();
		pimPage.addReportingMethod("Flat Structure");
		log.info("text entered Flat Stucture");
		pimPage.clickOnSaveButtonOnreporting();
		Assert.assertTrue(pimPage.validateAddedMethodDisplayed("Flat Structure"),
				"Newly added reporting method is not present");

	}

}
