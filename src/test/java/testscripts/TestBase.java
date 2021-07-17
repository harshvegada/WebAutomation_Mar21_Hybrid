package testscripts;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeSuite;

import com.qa.tc.orangehrm.constant.ConstantPath;

public class TestBase {

	@BeforeSuite
	public void loadPropertiesFile() {
		//PropertyConfigurator.configure(ConstantPath.LOG4J);
		BasicConfigurator.configure();
		
	}

}
