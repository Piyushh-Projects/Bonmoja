package com.bonmoja.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.bonmoja.base.TestBase;

public class LoginPage extends TestBase{
	
	@FindBy(linkText="LOG IN")
	@CacheLookup
	WebElement loginBtn;
	
	@FindBy(xpath="//input[@type='number']")
	WebElement inputMobile;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement inputPassword;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement BtnLogin;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public HomePage login(String mobile, String password) {
		
		 wait(ExpectedConditions.elementToBeClickable(loginBtn)).click();
		 wait(ExpectedConditions.elementToBeClickable(inputMobile)).click();
	     inputMobile.sendKeys(mobile);
	     wait(ExpectedConditions.elementToBeClickable(inputPassword)).click();
	     inputPassword.sendKeys(password);
	     wait(ExpectedConditions.elementToBeClickable(BtnLogin)).click();
	     
	     return new HomePage();
	}

}
