package com.ibm.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        
        By dropdown = By.xpath("//nav//button[contains(normalize-space(), '" + name + "')]");
        click(dropdown);
    }

    public void selectDropdownOption(String option) {
       
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
    
    public void clickEditForSection(String sectionName) {
        // Finds a button containing 'Edit' that is a sibling or child of the section header
        By editBtn = By.xpath("/html/body/main/div/div[2]/div[1]/div[1]/button");
        click(editBtn);
    }

   
    public void updateField(String fieldLabel, String value) {
        // This XPath looks for a label with the field name and finds the input following it
        By inputField = By.xpath("//label[contains(text(),'" + fieldLabel + "')]/following-sibling::input");
        
        
        type(inputField, value);
    }

    public void clickSave() {
        By saveBtn = By.xpath("//button[contains(text(),'Save')]");
        click(saveBtn);
    }

    
    public String getFieldValue(String fieldLabel) {
        By valueLabel = By.xpath("//*[contains(text(),'" + fieldLabel + "')]/following-sibling::*");
        return getText(valueLabel);
    }
    
    public String getSuccessMessage1() {
        try {
            // Wait up to 5 seconds for the alert to pop up
            WebDriverWait wait = new WebDriverWait(com.ibm.framework.driver.DriverFactory.getDriver(), java.time.Duration.ofSeconds(5));
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent());

            // Switch to the alert and get the text
            org.openqa.selenium.Alert alert = com.ibm.framework.driver.DriverFactory.getDriver().switchTo().alert();
            String alertText = alert.getText();

            // Print to console so you can see it in the logs
            System.out.println("Alert detected: " + alertText);

            // Accept (Click OK) so the driver can keep working
            alert.accept();

            return alertText;
        } catch (org.openqa.selenium.TimeoutException e) {
            // If no alert appeared, maybe it's a regular web element on the page?
            return "No alert detected";
        }
    }
}