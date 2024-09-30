package com.bonmoja.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.bonmoja.base.TestBase;

public class SportsPage extends TestBase {
	
	@FindBy(xpath="//*[@id='test']/div/div[1]/div[1]/ul/li/div")
	WebElement expLblSport;
	
	@FindBy(xpath="//*[@id='test']/div/div[2]/div/div[1]/a/div[1]/div[1]")
	WebElement expLblFirstTeam;
	
	@FindBy(xpath="//*[@id='test']/div/div[2]/div/div[1]/a/div[1]/div[2]")
	WebElement expLblSecondTeam;
	
	@FindBy(xpath="//div[@class='market-odds-oddsname uk-width-expand uk-flex uk-flex-middle']/span[2]")
	WebElement expLblOdds;
	
	@FindBy(xpath="//div[@class='betslip-item']//div[@class='overflow-300 uk-width-expand']")
	WebElement actLblSport;
	
	@FindBy(xpath="//div[@class='betslip-item']//div[@class='uk-inline-block']/div[1]")
	WebElement actLblFirstTeam;
	
	@FindBy(xpath="//div[@class='betslip-item']//div[@class='uk-inline-block']/div[2]")
	WebElement actLblSecondTeam;
	
	@FindBy(xpath="//div[@class='betslip-item']//span[@class='betslip-odds']")
	WebElement actLblOdds;
	
	@FindBy(xpath="//span[@class='bs-cash uk-text-small']//following::input[1]")
	WebElement boxCash;
	
	@FindBy(xpath="//span[@class='bs-cash uk-text-small']//following::input[2]")
	WebElement boxFreeBet;
	
	@FindBy(xpath="//button[contains(text(),'Place bet')]")
	WebElement btnPlaceBet;
	
	@FindBy(xpath="//p[@class='uk-text-center uk-animation-slide-bottom']")
	WebElement lblSuccessfulBet;
	
	@FindBy(xpath="//button[@class='uk-button uk-button-primary uk-button-small uk-width-1-1']")
	WebElement btnCloseMsg;
	
	@FindBy(xpath="//div[@id='sports-navbar']//a[6]")
	WebElement lblMyBets;
	
	@FindBy(xpath="//div[@class='uk-alert uk-alert-warning uk-animation-slide-right']")
	WebElement lblErrorMsg;
	
	private static String betId;
	
	public SportsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOdd() {
		wait(ExpectedConditions.elementToBeClickable(expLblOdds)).click();
	}
	
	public String getExpectedSport() {
		return wait(ExpectedConditions.visibilityOf(expLblSport)).getText();
	}
	
	public String getExpectedFirstTeam() {
		return wait(ExpectedConditions.visibilityOf(expLblFirstTeam)).getText();
	}
	
	public String getExpectedSecondTeam() {
		return wait(ExpectedConditions.visibilityOf(expLblSecondTeam)).getText();
	}
	
	public String getExpectedOdd() {
		return wait(ExpectedConditions.visibilityOf(expLblOdds)).getText();
	}
	
	public String getActualSport() {
		return wait(ExpectedConditions.visibilityOf(actLblSport)).getText();
	}
	
	public String getActualFirstTeam() {
		return wait(ExpectedConditions.visibilityOf(actLblFirstTeam)).getText();
	}
	
	public String getActualSecondTeam() {
		return wait(ExpectedConditions.visibilityOf(actLblSecondTeam)).getText();
	}
	
	public String getActualOdd() {
		return wait(ExpectedConditions.visibilityOf(actLblOdds)).getText();
	}
	
	public void placePositiveBet() {
		wait(ExpectedConditions.elementToBeClickable(boxCash)).click();
		boxCash.sendKeys(prop.getProperty("betPositive"));
		wait(ExpectedConditions.elementToBeClickable(boxFreeBet)).click();
		boxFreeBet.sendKeys(prop.getProperty("free"));
		wait(ExpectedConditions.elementToBeClickable(btnPlaceBet)).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void placeNegativeBet() {
		wait(ExpectedConditions.elementToBeClickable(boxCash)).click();
		boxCash.sendKeys(prop.getProperty("betNegative"));
		wait(ExpectedConditions.elementToBeClickable(boxFreeBet)).click();
		boxFreeBet.sendKeys(prop.getProperty("free"));
		wait(ExpectedConditions.elementToBeClickable(btnPlaceBet)).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String successfulBetMsg() {
		String msg = wait(ExpectedConditions.elementToBeClickable(lblSuccessfulBet)).getText();
		String id = msg.trim().replaceAll("[^0-9]", "").trim();
		betId = id;
		return msg;
	}
	
	public String errorBetMsg() {
		String msg = wait(ExpectedConditions.visibilityOf(lblErrorMsg)).getText();
		return msg.trim();
	}
	
	public String getBetId() {
		return betId;
	}
	
	public void clickCloseMsg() {
		wait(ExpectedConditions.elementToBeClickable(btnCloseMsg)).click();
	}
	
	public BetsPage clickMyBets() {
		wait(ExpectedConditions.elementToBeClickable(lblMyBets)).click();
		return new BetsPage();
	}
	
}