package com.ibm.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ibm.framework.base.BasePage;

public class TransactionPage extends BasePage {

	//Navigation
	private By moveMoneyLink=By.xpath("//section[@class='mt-10']/div/div[2]");
	private By newTransferTab=By.xpath("//div[@class='flex border-b border-gray-200']/button[2]");
	private By manageTab=By.xpath("//div[@class='flex border-b border-gray-200']/button[3]");
	
	
	//Quick Transfer Tab
	private By savedBeneficiary=By.xpath("//h4[text()='Jane Smith']/ancestor::div[@class='p-4 rounded-lg border-2 cursor-pointer transition border-gray-200 hover:border-gray-300']");
	private By amountField=By.xpath("//input[@placeholder='Enter amount'or @placeholder='Amount']");
	private By transferNowButton=By.xpath("//button[text()='Transfer Now']");
	private By selectSymbol=By.xpath("//div[@class='p-4 rounded-lg border-2 cursor-pointer transition border-blue-600 bg-blue-50']//div[@class='flex items-start gap-3']//*[name()='svg']");
	
	//New Transfer Tab
	private By ownTab=By.xpath("//div[@class='grid grid-cols-2 gap-4']/button[1]");
	private By otherTab=By.xpath("//div[@class='grid grid-cols-2 gap-4']/button[2]");
	private By bNameField=By.xpath("//input[@placeholder='Beneficiary Name']");
	private By ifscCode=By.xpath("//input[@placeholder='IFSC Code']");
	private By acNumber=By.xpath("//input[@placeholder='Account Number']");
	private By confirmButton=By.xpath("//button[text()='Confirm & Transfer']");
	private By accDropdown=By.xpath("//select");
	
	//Manage Beneficiary Tab
	private By addNewButton=By.xpath("//button[text()=' Add New']");
	private By saveButton=By.xpath("//button[text()='Save Beneficiary']");
	private By fullName=By.xpath("//input[@placeholder='Full Name']");
	private By newAccNo=By.xpath("//input[@placeholder='Account Number']");
	private By newIfsc=By.xpath("//input[@placeholder='IFSC Code']");
	private By bankName=By.xpath("//input[@placeholder='Bank Name']");
	private By nameCard=By.xpath("//h4[text()='Vinay Kumar']");
	private By deleteButton=By.xpath("//div[@class='space-y-3']/div[1]//button[2]");
	private By deleteCard=By.xpath("//h4[text()='John Doe']");
	private By transferButton=By.xpath("//div[@class='space-y-3']/div[1]//button[1]");
	
	//Success Modal
	private By successMsg=By.xpath("//div/h3");
	
	
	//Actions
	public void clickMoveMoneyPage() {
		click(moveMoneyLink);
		
	}


	public void clickBeneficiary() {
		click(savedBeneficiary);
		
	}


	public void enterAmount(String amt) {
		type(amountField,amt);
		
	}


	public void clickTransfer() {
		click(transferNowButton);
		
	}


	public boolean verifyTransfer(String result) {
		String currResult="failure";
		if(isDisplayed(successMsg))
			currResult="success";
		return (currResult.equals(result));	
		
	}


	public void clickTab(String tab) {
		
		switch(tab) {
			case "New Transfer":
				click(newTransferTab);
			break;
			case "Manage Beneficiaries":
				click(manageTab);
			break;
			default:
				System.out.println("Invalid");
		
		}
			
	}


	public void clickOps(String option) {
		switch(option) {
		case "Other Bank":
			click(otherTab);
		break;
		case "Own Account":
			click(ownTab);
		break;
		default:
			System.out.println("Invalid");
	
	}
		
	}


	public void enterDetails(String valueType) {
		if(valueType.equals("valid")) {
			type(bNameField,"Vinay Kumar");
			type(ifscCode,"SBIN0001234");
			type(acNumber,"5566778899");
		}
		else if(valueType.equals("invalid")) {
			type(bNameField,"Vinay Kumar");
			type(ifscCode,"SBIN0001234");
			type(acNumber,"");
		}
		
	}


	public void clickConfirm() {
		click(confirmButton);
		
	}


	public void selectAccount() {
		Select options=selectDropdown(accDropdown);
		options.selectByIndex(2);
		
	}


	public void clickSave() {
		click(saveButton);
		
	}


	public void clickAddNew() {
		click(addNewButton);
		
	}


	public void enterNewDetails(String valueType) {
		if(valueType.equals("valid")) {
			type(fullName,"Vinay Kumar");
			type(newAccNo,"5566778899");
			type(newIfsc,"SBIN0001234");
			type(bankName,"SBI");
		}
		else if(valueType.equals("invalid")) {
			type(fullName,"");
			type(newIfsc,"SBIN0001234");
			type(newAccNo,"5566778899");
			type(bankName,"SBI");
			
		}
		
	}


	public boolean verifyNewBeneficiary(String result) {
		
		String currResult="failure";
		if(isDisplayed(nameCard))
			currResult="success";
		return(currResult.equals(result));
	}


	public void clickDelete() {
		click(deleteButton);
		
	}


	public boolean verifyNameinList() {
		
		return(!isDisplayed(deleteCard));
	}


	public void clickTransfertoQuickPay() {
		click(transferButton);
		
	}


	public boolean verifyRedirection() {
		return(isDisplayed(selectSymbol));
		
	}


	public void noSelection() {
		Select options=selectDropdown(accDropdown);
		options.selectByIndex(0);
		
	}


	
}
