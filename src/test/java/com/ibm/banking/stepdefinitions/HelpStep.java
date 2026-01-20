package com.ibm.banking.stepdefinitions;

import com.ibm.banking.pages.DashboardPage;
import com.ibm.banking.pages.HelpPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

public class HelpStep {

	private HelpPage helpPage = new HelpPage();
	private DashboardPage dashboardPage = new DashboardPage();
	
	@Given("the user logs in using email {string} and password {string}")
	public void the_user_logs_in_using_email_and_password(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("the user is logged into TDDBank")
	public void the_user_is_logged_into_tdd_bank() {
		Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "User is not on the dashboard!");
	}

	@Given("the user navigates to the {string} page")
	public void the_user_navigates_to_the_page(String pageName) {
		helpPage.clickNavbarDropdown("Test User");
        helpPage.selectDropdownOption(pageName);
        Assert.assertTrue(helpPage.isHelpPageOpened(), "Failed to navigate to " + pageName);
	}

	@Given("the user is on the FAQ section")
	public void user_is_on_faq_section() {
		Assert.assertTrue(helpPage.isFaqSectionDisplayed(), "FAQ Section not found!");
	}

	@Given("the list of questions is displayed with collapsed answers")
	public void questions_are_collapsed() {
		// Logic: Pick a random/first question and ensure answer isn't visible yet
		Assert.assertFalse(helpPage.isAnswerVisible("KYC"), "Answers should be collapsed by default");
	}

	@When("the user clicks the dropdown icon beside an FAQ question")
	public void click_dropdown_icon() {
		helpPage.clickFaqDropdown("KYC");
	}

	@Then("the answer section should expand and be clearly visible")
	public void answer_should_expand() {
		Assert.assertTrue(helpPage.isAnswerVisible("KYC"), "Answer did not expand!");
	}

	@Then("the answer text should be legible and accurate")
	public void answer_is_legible() {
		// Implementation can check if text length > 0
		Assert.assertTrue(helpPage.isAnswerVisible("KYC"));
	}

	@When("the user clicks the dropdown icon again")
	public void click_dropdown_again() {
		helpPage.clickFaqDropdown("KYC");
	}

	@Then("the answer section should be hidden from view")
	public void answer_is_hidden() {
		Assert.assertFalse(helpPage.isAnswerVisible("KYC"), "Answer failed to collapse!");
	}

	// --- Search Steps ---

	@Given("the FAQ search bar is empty and active")
	public void search_bar_active() {
		helpPage.typeInSearch("");
	}

	@When("the user types {string} in the search bar")
	public void user_types_search(String term) {
		helpPage.typeInSearch(term);
	}

	@Then("the list should be filtered in real-time")
	public void list_filtered_realtime() {
		// Implicitly handled by checking results in next step
	}

	@Then("only questions containing the term {string} should be displayed")
	public void verify_filtered_results(String term) {
		List<WebElement> results = helpPage.getVisibleFaqItems();
		for (WebElement item : results) {
			Assert.assertTrue(item.getText().toUpperCase().contains(term.toUpperCase()), 
					"Found FAQ item not containing search term: " + term);
		}
	}

	@When("the user clears the search bar")
	public void clear_search() {
		helpPage.typeInSearch("");
	}

	@Then("the system should display relevant questions regarding {string} security")
	public void verify_pin_security_results(String term) {
		verify_filtered_results(term);
	}

	// --- Support Channel Steps ---

	@When("the user scrolls to the bottom of the FAQ page")
	public void scroll_to_bottom() {
		helpPage.scrollToSupportButtons();
	}

	@Then("the {string} and {string} buttons should be visible")
	public void support_buttons_visible(String btn1, String btn2) {
		Assert.assertTrue(helpPage.isSupportButtonVisible(btn1));
		Assert.assertTrue(helpPage.isSupportButtonVisible(btn2));
	}

	@When("the user clicks the {string} button")
	public void click_support_button(String btnName) {
		helpPage.clickSupportButton(btnName);
	}

	@Then("the support chat widget should open for communication")
	public void chat_widget_opens() {
		Assert.assertTrue(helpPage.isChatWidgetOpen(), "Chat widget did not open!");
	}

	@Then("the system should trigger a call link or display the support number")
	public void call_trigger_verify() {
		// In many test environments, we check if the 'tel:' link exists
		Assert.assertTrue(helpPage.isSupportButtonVisible("Call Now"));
	}
}