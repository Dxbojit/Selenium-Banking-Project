package com.ibm.banking.pages;

import org.openqa.selenium.By;
import com.ibm.framework.base.BasePage;

public class AccSettingsPage extends BasePage {

    // --- Robust Locators ---
    
    // This looks for "Account Settings" in ANY tag (h1, h2, or div) to avoid tag mismatches
    private By settingsHeader = By.xpath("//*[self::h1 or self::h2 or self::h3][contains(text(),'Account Settings')]");

    // Use Relative XPaths instead of absolute paths
    private By personalDetailsHeader = By.xpath("//h3[contains(text(),'Personal & Contact Details')]");
    private By securityHeader        = By.xpath("//h3[contains(text(),'Security')]");
    private By accountTypeHeader     = By.xpath("//h4[contains(text(),'Account Type')]");

    // --- Actions ---

    public void clickNavbarDropdown(String name) {
        // Use normalize-space to handle hidden tabs/spaces in the button text
        By dropdown = By.xpath("//nav//button[contains(normalize-space(), '" + name + "')]");
        click(dropdown);
    }

    public void selectDropdownOption(String option) {
        // Absolute XPaths are likely why the click is 'happening' but not 'registering' correctly
        // Use a relative path to the anchor tag
        By menuOption = By.xpath("//a[.//span[contains(text(),'" + option + "')]]");
        click(menuOption);
    }

    public boolean isSettingsPageOpened() {
        // This will now wait for the header to appear regardless of if it's an h1 or h2
        return isDisplayed(settingsHeader);
    }

    public boolean isSectionVisible(String sectionName) {
        By locator;
        switch (sectionName) {
            case "Personal Details":
                locator = personalDetailsHeader;
                break;
            case "Security":
                locator = securityHeader;
                break;
            case "Account Type":
                locator = accountTypeHeader;
                break;
            default:
                // Universal locator for any other sections
                locator = By.xpath("//*[contains(text(),'" + sectionName + "')]");
                break;
        }
        return isDisplayed(locator);
    }
}