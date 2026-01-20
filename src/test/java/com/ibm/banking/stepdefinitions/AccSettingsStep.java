package com.ibm.banking.stepdefinitions;

import java.util.List;
import java.util.Map;
import org.testng.Assert;
import com.ibm.banking.pages.AccSettingsPage;
import com.ibm.banking.pages.DashboardPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccSettingsStep {

    private AccSettingsPage accSettingsPage = new AccSettingsPage();
    private DashboardPage dashboardPage = new DashboardPage();
    
    @Given("the user is on the dashboard")
    public void the_user_is_on_the_dashboard() {
        
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "User is not on the dashboard!");
    }

    @When("the user clicks on the {string} navbar dropdown")
    public void the_user_clicks_on_the_navbar_dropdown(String username) {
        accSettingsPage.clickNavbarDropdown(username);
    }

    @When("the user selects {string} from the dropdown menu")
    public void the_user_selects_from_the_dropdown_menu(String option) {
        accSettingsPage.selectDropdownOption(option);
    }

    @Then("the user should be redirected to the settings page")
    public void the_user_should_be_redirected_to_the_settings_page() {
        Assert.assertTrue(accSettingsPage.isSettingsPageOpened(), 
            "Redirection to Account Settings page failed!");
    }

    @Then("the following sections should be visible:")
    public void the_following_sections_should_be_visible(io.cucumber.datatable.DataTable dataTable) {
        
        List<Map<String, String>> sections = dataTable.asMaps(String.class, String.class);
        
        for (Map<String, String> row : sections) {
            String sectionName = row.get("Section Name");
            
            
            boolean isVisible = accSettingsPage.isSectionVisible(sectionName);
            
            Assert.assertTrue(isVisible, "UI Section '" + sectionName + "' was not found on the page!");
        }
    }
}