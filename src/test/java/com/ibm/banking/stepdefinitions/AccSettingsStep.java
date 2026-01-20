package com.ibm.banking.stepdefinitions;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.ibm.banking.pages.AccSettingsPage;
import com.ibm.banking.pages.DashboardPage;
import com.ibm.banking.pages.LoginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccSettingsStep {

    private AccSettingsPage accSettingsPage = new AccSettingsPage();
    private DashboardPage dashboardPage = new DashboardPage();
    
    @Given("the user is logged into TDDBank")
    public void the_user_is_on_the_tdd_bank_login_page() {
    	 LoginPage loginPage = new LoginPage();
         loginPage.open();
         loginPage.login("user@tddbank.com", "password");

         Assert.assertTrue(
                 dashboardPage.isDashboardDisplayed(),
                 "Dashboard not displayed after login"
         );
     }

    
  
    @And("the user navigates to {string} from the navbar")
    public void navigate_to_settings_via_navbar(String pageName) {
        
        accSettingsPage.clickNavbarDropdown("Test User");
        accSettingsPage.selectDropdownOption(pageName);
        Assert.assertTrue(accSettingsPage.isSettingsPageOpened(), "Failed to navigate to " + pageName);
    }
    
    @Then("the following sections should be visible:")
    public void the_following_sections_should_be_visible(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> sections = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : sections) {
            String sectionName = row.get("Section Name");
            boolean isVisible = accSettingsPage.isSectionVisible(sectionName);
            Assert.assertTrue(isVisible, "UI Section '" + sectionName + "' was not found!");
        }
    }
    
    @When("the user clicks the {string} option for {string}")
    public void the_user_clicks_the_option_for(String action, String section) {
        accSettingsPage.clickEditForSection(section);
    }

    @When("the user updates the {string} field with {string}")
    public void the_user_updates_the_field_with(String field, String newValue) {
        accSettingsPage.updateField(field, newValue);
    }

    
    @When("the user clicks the {string} button")
    public void the_user_clicks_the_button(String buttonName) {
        if(buttonName.equalsIgnoreCase("Save Changes")) {
            accSettingsPage.clickSave();
        }
    }

    @Then("a success message {string} should be displayed")
    public void a_success_message_should_be_displayed(String expectedMessage) {
        String actualMessage = accSettingsPage.getSuccessMessage1();
        Assert.assertEquals(actualMessage, expectedMessage, "Success message did not match!");
    }

    @Then("the {string} section should display {string}")
    public void the_section_should_display(String field, String expectedValue) {
        String actualValue = accSettingsPage.getFieldValue(field);
        Assert.assertTrue(actualValue.contains(expectedValue), 
            "The field " + field + " does not display the updated value!");
    }
    
}