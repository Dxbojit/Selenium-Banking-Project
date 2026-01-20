package com.ibm.banking.stepdefinitions;

import org.testng.Assert;

import com.ibm.banking.pages.LoginPage;
import com.ibm.banking.pages.TransactionPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class TransactionSteps {
	
	 private LoginPage loginPage = new LoginPage();
	 private TransactionPage transPage=new TransactionPage();
	 
	@Given("the user is logged into the application")
	public void the_user_is_logged_into_the_application() {
	    loginPage.open();
	    loginPage.login("user@tddbank.com","password");
	}

	@Given("the user is on the Money Transfer page")
	public void the_user_is_on_the_money_transfer_page() {
	    transPage.clickMoveMoneyPage();
	}

	@When("the user selects a saved beneficiary")
	public void the_user_selects_a_saved_beneficiary() {
	   transPage.clickBeneficiary();
	}

	@When("the user enters amount {string}")
	public void the_user_enters_amount(String amount) {
		transPage.enterAmount(amount);
	    
	}

	@When("the user clicks on Transfer Now")
	public void the_user_clicks_on() {
		transPage.clickTransfer();
	  
	}

	@Then("the transfer result should be {string}")
	public void the_transfer_result_should_be(String result) {
		Assert.assertTrue(transPage.verifyTransfer(result),"Transfer unsuccessful");
	}

	@When("the user navigates to {string}")
	public void the_user_navigates_to(String tab) {
	   transPage.clickTab(tab);
	}

	@When("the user selects {string}")
	public void the_user_selects(String option) {
	    transPage.clickOps(option);
	}

	@When("the user enters {string} beneficiary details")
	public void the_user_enters_beneficiary_details(String valueType) {
	    transPage.enterDetails(valueType);
	}
	
	@When("the user clicks on Confirm & Transfer")
	public void user_clicks_confirmTransfer() {
		transPage.clickConfirm();
	}
	
	@When("the user selects a secondary account")
	public void the_user_selects_a_secondary_account() {
	    transPage.selectAccount();
	}
	
	@When("the user does not select any account")
	public void the_user_doesNotSelectAccount() {
		transPage.noSelection();
	}
	
	@When("the user clicks on Save")
	public void the_user_clickSave() {
		transPage.clickSave();
	}
	
	@When("the user clicks on Add New")
	public void the_user_clicksAddNew() {
		transPage.clickAddNew();
	}
	
	@When("the user enters {string} new beneficiary details")
	public void the_user_entersNewDetails(String valueType) {
		transPage.enterNewDetails(valueType);
	}
	
	@Then("the beneficiary creation result should be {string}")
	public void the_beneficiary_creation_result_should_be(String result) {
		Assert.assertTrue(transPage.verifyNewBeneficiary(result),"Filed to add new Beneficiary");
	}
	
	@When("the user clicks on Delete button")
	public void user_clicksDelete() {
		transPage.clickDelete();
	}
	
	@Then("the beneficiary name is not visible in the list")
	public void name_notInList() {
		Assert.assertTrue(transPage.verifyNameinList(),"Failed to delete");
	}
	
	@When("the user clicks on Transfer button")
	public void user_clicksTransfer() {
		transPage.clickTransfertoQuickPay();
	}
	
	@Then("the user is redirected to Quick Transfer tab with the beneficiary name selected")
	public void user_redirect_toQuickPay() {
		Assert.assertTrue(transPage.verifyRedirection(),"Failed redirection to Quick Pay");
	}
}
