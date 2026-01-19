package com.ibm.framework.driver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.ibm.framework.config.ConfigReader;

public final class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {}

    public static void initDriver() {

        String browser = System.getProperty(
                "browser",
                ConfigReader.get("browser")
        ).trim();

        boolean headless = Boolean.parseBoolean(
                System.getProperty(
                        "headless",
                        ConfigReader.get("headless")
                )
        );

        if (!browser.equalsIgnoreCase("chrome")) {
            throw new RuntimeException("Only Chrome is supported currently");
        }

        ChromeOptions options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        driver.set(new ChromeDriver(options));

        getDriver().manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(
                        Integer.parseInt(
                                ConfigReader.get("pageLoadTimeout")
                        )
                )
        );
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new RuntimeException("Driver not initialized. Call initDriver() first.");
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
    

}
