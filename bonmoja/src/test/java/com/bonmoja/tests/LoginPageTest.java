package com.bonmoja.tests;

import com.bonmoja.base.TestBase;
import com.bonmoja.pages.HomePage;
import com.bonmoja.pages.LoginPage;

import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class LoginPageTest extends TestBase {
	
	LoginPage login;
	HomePage home;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		login = new LoginPage();
	}
	
	@Test
	public void LoginTest() {
		home = login.login(prop.getProperty("number"), prop.getProperty("password"));
		Assert.assertTrue(home.sportsMenuIsPresent());
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}