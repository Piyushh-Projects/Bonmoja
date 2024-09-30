package com.bonmoja.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.bonmoja.utils.TestUtil;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	ExtentSparkReporter extentSparkReporter;
	ExtentReports extentReports;
	ExtentTest extentTest;
	
	public TestBase() {
		
		try {
			prop = new Properties();
			FileInputStream input = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/bonmoja/configs/config.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.page_load_timeout));
		driver.get(prop.getProperty("url"));
	}
	
    public static <U> U wait(ExpectedCondition<U> condition) {

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(TestBase.driver).ignoring(RuntimeException.class)
                .withTimeout(Duration.ofSeconds(TestUtil.page_load_timeout));
        try {
            return wait.until(condition);
        } catch (TimeoutException err) {
            String errMessage = "Bot encountered a timeout while waiting for a condition, "
                    + err.getLocalizedMessage();
            throw new RuntimeException(errMessage);
        }
    }

}
