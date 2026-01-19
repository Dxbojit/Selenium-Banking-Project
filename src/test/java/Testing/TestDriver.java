package Testing;

import org.testng.annotations.Test;

import com.ibm.framework.driver.DriverFactory;

public class TestDriver {
	
	@Test
	public void openSite() {
		DriverFactory.initDriver();
		DriverFactory.getDriver().get("https://google.com");
		DriverFactory.quitDriver();
	}
}
