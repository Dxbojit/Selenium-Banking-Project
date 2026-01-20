package com.ibm.banking.stepdefinitions;

import org.testng.Assert;

import com.ibm.banking.pages.DashboardPage;
import com.ibm.banking.pages.SignupPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignupSteps {

    private SignupPage signupPage = new SignupPage();

    @Given("the user is on the Sign Up page")
    public void user_is_on_signup_page() {
      signupPage.clickRequestAccess();
    }

    @When("the user enters {string} as Full Name")
    public void enter_fullname(String name) {
        signupPage.enterFullName(name);
    }

    @When("the user enters {string} as Email")
    public void enter_email(String email) {
        signupPage.enterEmail(email);
    }

    @When("the user enters {string} as Pin Code")
    public void enter_pincode(String pin) {
        signupPage.enterPinCode(pin);
    }

    @When("the user enters {string} as Password")
    public void enter_password(String password) {
        signupPage.enterPassword(password);
    }

    @When("the user enters {string} as Confirm Password")
    public void enter_confirm_password(String password) {
        signupPage.enterConfirmPassword(password);
    }

    @When("the user selects a Date of Birth for age {int}")
    public void select_dob(int age) {
        signupPage.enterDateOfBirth(age);
    }

    @When("the user checks the Terms and Conditions")
    public void check_terms() {
        signupPage.clickTermsCheckbox();
    }

    @When("the user clicks Create Account")
    public void click_create() {
        signupPage.clickCreateAccount();
    }

    @Then("the user should be redirected to the Dashboard Page")
    public void check_redirect() throws InterruptedException {
        // Small wait for the redirect animation/logic
        DashboardPage dashboardPage = new DashboardPage();
        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard was not displayed after signup"
        );
    }

    @Then("the user should see a password mismatch error")
    public void check_error() {
        Assert.assertTrue(signupPage.isPasswordMismatchErrorDisplayed(), "Error message missing");
    }
    
    @Then("the user should remain on the Sign Up page")
    public void check_stay_on_page() throws InterruptedException {
        // Wait 2 seconds to see if the app redirects us
        Thread.sleep(2000); 
        
        // We assert FALSE. 
        // If isRedirectedToLogin() is true, the test FAILS (which is what we want to prove).
        Assert.assertTrue(
            signupPage.isRedirectedToLogin(), 
            "TEST FAILURE (BUG FOUND): The application allowed account creation without a Pin Code!"
        );
    }
}