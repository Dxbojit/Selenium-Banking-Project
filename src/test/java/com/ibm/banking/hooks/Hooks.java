package com.ibm.banking.hooks;

import org.openqa.selenium.Cookie;

import com.ibm.framework.config.ConfigReader;
import com.ibm.framework.driver.DriverFactory;
import com.ibm.framework.utils.ScreenshotUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	@Before
	public void setUp() {
		DriverFactory.initDriver();
		 DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
	        Cookie bypassCookie = new Cookie(
	                "_vercel_share",
	                ConfigReader.get("vercelCookie")
	        );

	        DriverFactory.getDriver().manage().addCookie(bypassCookie);

	        // Refresh so cookie takes effect
	        DriverFactory.getDriver().navigate().refresh();
	}
	
	@After
	public void tearDown(Scenario scenario) {
		 if (scenario.isFailed()) {
	            System.out.println(
	                "[INFO] Scenario failed. Taking screenshot: "
	                + scenario.getName()
	            );

	            ScreenshotUtils.takeScreenshot(
	                scenario.getName().replaceAll(" ", "_")
	            );
	        }

	        DriverFactory.quitDriver();
	}
}
