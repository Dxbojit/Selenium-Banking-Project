package com.ibm.banking.stepdefinitions;

import org.testng.Assert;

import com.ibm.banking.pages.LoginPage;
import com.ibm.banking.pages.SecurityPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SecuritySteps 
{
	LoginPage obj = new LoginPage();
	SecurityPage obj2 = new SecurityPage();

@Given("the user is logged in")
public void the_user_is_logged_in() 
{
	obj.open();
	obj.login("user@tddbank.com", "password");
}

@Given("the user is on the Security page")
public void the_user_is_on_the_settings_page() throws InterruptedException 
{
	Assert.assertTrue(obj2.verifySecurityPageReached(), "Not on security page");
}

@Then("the page should display three toggle buttons")
public void the_page_should_display_three_blue_toggle_buttons() throws InterruptedException 
{
	Assert.assertTrue(obj2.verifyButtons(), "invalid");
}

@When("the user clicks on a toggle button")
public void the_user_clicks_on_a_toggle_button() throws InterruptedException 
{
	obj2.buttonclicker();
}

@Then("the button should slide to the Off position")
public void the_button_should_slide_to_the_position() 
{
	Assert.assertTrue(obj2.buttonClicked(), "invalid");
}

@Then("the red Deactivate button should be visible")
public void the_red_deactivate_button_should_be_visible() 
{
	Assert.assertTrue(obj2.VisibleDeactivate(), "invalid");
}

@When("the user clicks on the Deactivate button")
public void the_user_clicks_on_the_deactivate_button() throws InterruptedException 
{
	obj2.DeactivateClick();
}

@Then("the alert text should be clear and legible")
public void the_alert_text_should_be_clear_and_legible() 
{
	Assert.assertTrue(obj2.AlertTextVisible(), "invalid");
}


@Then("the account gets deactivated and the user is redirected to login page")
public void the_account_deactivation_process_should_be_initiated() 
{
	Assert.assertTrue(obj2.Deactivated(), "invalid");
}
}



