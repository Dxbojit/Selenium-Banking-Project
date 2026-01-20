package com.ibm.banking.pages;

import org.openqa.selenium.By;
import com.ibm.framework.base.BasePage;

public class MyCardsPage extends BasePage {

	// --- Locators ---
	// Changed access modifier to 'private' to match InsightsPage style
	private By totalSpendingText = By.xpath("//div[text()='Total Spending']/following-sibling::div");
	private By availableCreditText = By.xpath("/html/body/div[2]/main/div/div[2]");

	// Add/Manage Card Elements
	private By addDebitButton = By.xpath("//div[text()='Available Credit']/following-sibling::div");
	private By provisionCardBtn = By.xpath("/html/body/div[2]/main/section[1]/div[2]/div[2]/div[2]/form/button");

	// Inputs
	private By cardNumberInput = By.cssSelector("input[placeholder='CARD NUMBER']");
	private By cardHolderInput = By.cssSelector("input[placeholder='CARDHOLDER FULL NAME']");
	private By expiryInput     = By.cssSelector("input[placeholder='MM/YY']");
	private By cvvInput        = By.cssSelector("input[placeholder='CVV']");

	// Error & Delete
	private By errorMessage    = By.xpath("//p[@class='text-");
	private By deleteCreditBtn = By.xpath("(//button[@class='absolute -top-3 -right-3 p-2.5 bg-red-500 text-white rounded-full shadow-xl opacity-0 group-hover:opacity-100 transition-all hover:bg-red-600 focus:opacity-100 scale-75 group-hover:scale-100 z-20 border-4 border-white'])[1]");
	private By addCreditSlot   = By.xpath("//button[@class='w-full aspect-/50 transition-all group overflow-hidden relative']");

	// Navigation (If not already in DashboardPage)
	private By myCardsLink     = By.xpath("//body//main//div[4]");

	// --- Actions ---

	public void clickMyCardsLink() {
		click(myCardsLink);
	}

	public String getTotalSpending() {
		// Using BasePage method for text retrieval
		return getText(totalSpendingText);
	}

	public String getAvailableCredit() {
		return getText(availableCreditText);
	}

	public void clickAddDebit() {
		click(addDebitButton);
	}

	// Combined method for filling details (cleaner)
	public void fillCardDetails(String number, String holder, String expiry, String cvv) {
		type(cardNumberInput, number);

		if (holder != null && !holder.isEmpty()) {
			type(cardHolderInput, holder);
		}

		type(expiryInput, expiry);

		if (cvv != null && !cvv.isEmpty()) {
			type(cvvInput, cvv);
		}
	}

	public void clickProvisionButton() {
		click(provisionCardBtn);
	}

	public String getErrorMessage() {
		if (isDisplayed(errorMessage)) {
			return getText(errorMessage);
		}
		return null;
	}

	public void clickDeleteCreditCard() {
		// Your framework likely handles waits inside 'click'. 
		// If the button is hidden/hover-only, you might need a JS click.
		// If BasePage has a 'clickJS' method, use that. Otherwise use standard click.
		click(deleteCreditBtn); 
	}

	public boolean isCreditSlotEmpty() {
		return isDisplayed(addCreditSlot);
	}
}