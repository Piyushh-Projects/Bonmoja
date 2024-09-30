package com.bonmoja.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.bonmoja.base.TestBase;

public class HomePage extends TestBase {
	
	@FindBy(partialLinkText="Sports")
	WebElement linkSports;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
    public boolean sportsMenuIsPresent() {
        try {
            wait(ExpectedConditions.visibilityOf(linkSports)).isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public SportsPage clickSportsMenu() {
    	try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	wait(ExpectedConditions.elementToBeClickable(linkSports)).click();
    	return new SportsPage();
    }

}