package com.bonmoja.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.bonmoja.base.TestBase;
import com.bonmoja.pages.BetsPage;
import com.bonmoja.pages.HomePage;
import com.bonmoja.pages.LoginPage;
import com.bonmoja.pages.SportsPage;
import com.bonmoja.utils.TestUtil;

public class SportsPageTest extends TestBase {
	
	LoginPage login;
	HomePage home;
	SportsPage sports;
	BetsPage bets;
	
	String sheetName = "Data";
	
	SoftAssert sassert;
	
	public SportsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		login = new LoginPage();
		sassert = new SoftAssert();
		home = login.login(prop.getProperty("number"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getTestData() {
		Object data [][] = TestUtil.getTestdata(sheetName);
		return data;
	}
	
	@Test(priority = 1)
	public void placePositiveBet() {
		sports = home.clickSportsMenu();
		sports.clickOdd();
		sassert.assertEquals(sports.getExpectedSport(), sports.getActualSport(), "Sports name not matched");
		sassert.assertEquals(sports.getExpectedFirstTeam(), sports.getActualFirstTeam(), "First team name not matched");
		sassert.assertEquals(sports.getExpectedSecondTeam(), sports.getActualSecondTeam(), "Second team name not matched");
		sassert.assertEquals(sports.getExpectedOdd(), sports.getActualOdd(), "Odds did not match");
		
		sports.placePositiveBet();
		sassert.assertTrue(sports.successfulBetMsg().contains(prop.getProperty("successBetMsg").trim()), "Success message is not displayed");
		sports.clickCloseMsg();
		bets = sports.clickMyBets();
		sassert.assertTrue(bets.verifyBetId(), "BetId is not present");
		sassert.assertAll();
	}
	
	@Test(priority=2)
	public void placeNegativeBet() {
		sports = home.clickSportsMenu();
		sports.clickOdd();
		sassert.assertEquals(sports.getExpectedSport(), sports.getActualSport(), "Sports name not matched");
		sassert.assertEquals(sports.getExpectedFirstTeam(), sports.getActualFirstTeam(), "First team name not matched");
		sassert.assertEquals(sports.getExpectedSecondTeam(), sports.getActualSecondTeam(), "Second team name not matched");
		sassert.assertEquals(sports.getExpectedOdd(), sports.getActualOdd(), "Odds did not match");
		
		sports.placeNegativeBet();
		sassert.assertTrue(sports.errorBetMsg().contains(prop.getProperty("errorBetMsg").trim()), "Error message is not displayed");
		sassert.assertAll();
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
