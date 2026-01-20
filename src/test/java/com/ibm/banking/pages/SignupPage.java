package com.ibm.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.ibm.framework.base.BasePage;
import com.ibm.framework.driver.DriverFactory;
import java.time.LocalDate;

public class SignupPage extends BasePage {

    // --- Locators ---
    private By fullNameInput      = By.id("fullName");
    private By emailInput         = By.id("email");
    private By pincodeInput       = By.id("pincode");
    private By passwordInput      = By.id("password");
    private By confirmPasswordInput = By.id("confirmPassword");
    private By dobInput           = By.id("dateOfBirth");
    private By termsCheckbox      = By.xpath("//input[@type='checkbox']");
    private By createAccountBtn   = By.xpath("//button[contains(text(), 'Create Account')]");
    
    // Error message locator
    private By mismatchError      = By.xpath("//p[contains(text(),'Passwords do not match')]");
    
    private By requestAccess	  = By.xpath("//a[text()='Request Access']");

    // --- Actions ---
    
    public void clickRequestAccess() {
    	click(requestAccess);
    }

    public void enterFullName(String name) {
        enterText(fullNameInput, name);
    }

    public void enterEmail(String email) {
        enterText(emailInput, email);
    }

    public void enterPinCode(String pin) {
        enterText(pincodeInput, pin);
    }

    public void enterPassword(String password) {
        enterText(passwordInput, password);
    }

    public void enterConfirmPassword(String password) {
        enterText(confirmPasswordInput, password);
    }

    public void enterDateOfBirth(int age) {
        String dob = LocalDate.now().minusYears(age).toString();
        enterText(dobInput, dob);
    }

    public void clickTermsCheckbox() {
        // Wait for it to be visible, then click
        click(termsCheckbox);
    }

    public void clickCreateAccount() {
        click(createAccountBtn);
    }

    // --- Verification ---

    public boolean isRedirectedToLogin() {
        return DriverFactory.getDriver().getCurrentUrl().contains("signin");
    }

    public boolean isPasswordMismatchErrorDisplayed() {
        try {
            return waitForVisible(mismatchError).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    

    // --- Helper Method ---
    
    /**
     * CRITICAL FIX:
     * This method waits for the element to actually appear on screen
     * before trying to type. This prevents 'NoSuchElementException'.
     */
    private void enterText(By locator, String text) {
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }
}