package com.ibm.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import com.ibm.framework.base.BasePage;
import com.ibm.framework.driver.DriverFactory;
import java.util.List;

public class HelpPage extends BasePage {

	// --- Locators ---
	private By pageHeader = By.xpath("//h2[contains(text(),'Frequently Asked Questions')]");
	private By faqHeader = By.xpath("//h2[contains(text(),'Frequently Asked Questions')]");
	private By searchBar = By.xpath("//input[@placeholder='Search FAQs']");
	private By liveChatBtn = By.xpath("//button[contains(.,'Live Chat')]");
	private By callNowBtn = By.xpath("//button[contains(.,'Call Now')]");
	private By chatWidget = By.id("chat-widget-container"); 
	private By visibleFaqQuestions = By.xpath("//div[contains(@class,'faq-item') and not(contains(@style,'display: none'))]");

	// --- Actions ---

	public void clickNavbarDropdown(String name) {

		By dropdown = By.xpath("//nav//button[contains(normalize-space(), '" + name + "')]");
		click(dropdown);
	}
	
	public void selectDropdownOption(String option) {
	       
        By menuOption = By.xpath("//a[.//span[contains(text(),'" + option + "')]]");
        click(menuOption);
    }
	
	public boolean isHelpPageOpened() {
        // This will now wait for the header to appear regardless of if it's an h1 or h2
        return isDisplayed(pageHeader);
    }

	public boolean isFaqSectionDisplayed() {
		return isDisplayed(faqHeader);
	}

	public void typeInSearch(String text) {
		WebElement element = DriverFactory.getDriver().findElement(searchBar);
		element.clear(); // Using standard Selenium clear
		element.sendKeys(text);
	}

	public void clickFaqDropdown(String questionPart) {
		By dropdown = By.xpath("//div[contains(text(),'" + questionPart + "')]/following-sibling::button");
		click(dropdown);
	}

	public boolean isAnswerVisible(String questionPart) {
		By answer = By.xpath("//div[contains(text(),'" + questionPart + "')]/parent::div/following-sibling::div");
		return isDisplayed(answer);
	}

	public List<WebElement> getVisibleFaqItems() {
		// Using standard Selenium findElements via DriverFactory
		return DriverFactory.getDriver().findElements(visibleFaqQuestions);
	}

	public void scrollToSupportButtons() {
		WebElement element = DriverFactory.getDriver().findElement(liveChatBtn);
		// Direct JavascriptExecutor for scrolling since BasePage lacks the method
		((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public boolean isSupportButtonVisible(String btnName) {
		return isDisplayed(By.xpath("//button[contains(.,'" + btnName + "')]"));
	}

	public void clickSupportButton(String btnName) {
		click(By.xpath("//button[contains(.,'" + btnName + "')]"));
	}

	public boolean isChatWidgetOpen() {
		return isDisplayed(chatWidget);
	}
}