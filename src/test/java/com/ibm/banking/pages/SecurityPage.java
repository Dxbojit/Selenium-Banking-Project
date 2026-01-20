package com.ibm.banking.pages;

import org.openqa.selenium.By;


import com.ibm.framework.base.BasePage;



public class SecurityPage extends BasePage
{
	private By TestUser = By.xpath("//span[@class='text-gray-900 font-medium']");
	private By SecPriv = By.xpath("//span[normalize-space()='Security & Privacy']");
	private By ValSec = By.xpath("//h2[@class='text-2xl font-bold text-primary flex items-center gap-2']");
	private By Button1 = By.xpath("//div[@class='absolute top-1 w-4 h-4 bg-white rounded-full transition-all left-1']");
	//private By onButton = By.xpath("//button[@class='w-12 h-6 rounded-full transition-colors relative bg-blue-600']/div[@class='absolute top-1 w-4 h-4 bg-white rounded-full transition-all left-7']");
	private By Button2 = By.xpath("/html[1]/body[1]/main[1]/div[1]/div[2]/section[2]/div[2]/div[2]//button");
	//private By offButton = By.xpath("absolute top-1 w-4 h-4 bg-white rounded-full transition-all left-1");
	private By Button3 = By.xpath("/html[1]/body[1]/main[1]/div[1]/div[2]/section[2]/div[2]/div[3]/button");	
	boolean checks1, checks2, checks3;
	private By deactivateButton = By.xpath("//button[@class='bg-rose-600 hover:bg-rose-700 text-white px-6 py-2.5 rounded-xl font-bold text-sm transition-all shadow-md shadow-rose-200 active:scale-95']");
	private By signinmessage = By.xpath("//h1[@class='text-3xl font-bold text-gray-900']");
	
	public boolean verifySecurityPageReached() throws InterruptedException 
	{
		Thread.sleep(3000);
		click(TestUser);
		Thread.sleep(3000);
		click(SecPriv);
		boolean check1 = waitForVisible(ValSec).isDisplayed();
		return check1;
	}
	
	public boolean verifyButtons() throws InterruptedException 
	{
		checks1 = waitForClickable(Button1).isEnabled();
		checks2 = waitForClickable(Button2).isEnabled();
		checks3 = waitForClickable(Button3).isEnabled();
		Thread.sleep(3000);
		if(checks1 == true && checks2 == true && checks3 == true)
			return true;
		else
			return false;
	}



	public void buttonclicker() throws InterruptedException 
	{
		
		click(Button1);
		Thread.sleep(3000);
		click(Button2);
		Thread.sleep(3000);
		click(Button3);
		Thread.sleep(3000);
	}

	public boolean buttonClicked() 
	{
		if(checks1 == true && checks2 == true && checks3 == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean VisibleDeactivate() 
	{
		boolean deactivatecheck = waitForClickable(deactivateButton).isEnabled();
		return deactivatecheck;
	}

	public void DeactivateClick() throws InterruptedException 
	{
		click(deactivateButton);
		Thread.sleep(3000);
	}

	public boolean AlertTextVisible() 
	{
		String msg = handleAlert();
		boolean msgcheck = msg.equals("Are you sure? This will log you out and clear your local session data permanently.");
		return msgcheck;
	}

	public boolean Deactivated() 
	{
		boolean signincheck = waitForVisible(signinmessage).isEnabled();
		return signincheck;
	}
}
