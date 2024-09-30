package com.bonmoja.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.bonmoja.base.TestBase;

public class BetsPage extends TestBase {
	
	SportsPage sports = new SportsPage();
	
	String betId = sports.getBetId();
	
	@FindBy(xpath="//a[contains(text(),'')]")
	WebElement linkSports;
	
	public BetsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyBetId() {
		boolean flag;
		try {
	           wait(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//a[contains(text(),'" + betId + "')]"), betId));
	           flag = true;
	       } catch (Exception e) {
	           flag = false;
	       }
		return flag;
	}

}