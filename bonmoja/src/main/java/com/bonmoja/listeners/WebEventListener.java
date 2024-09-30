package com.bonmoja.listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.bonmoja.base.TestBase;

public class WebEventListener extends TestBase implements WebDriverListener, ITestListener {
	
	
	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.println("Before Navigating to: '" + url + "'");
	}
	
	public void afterNavigateTo(String url, WebDriver driver) {
		System.out.println("Navigated to: '" + url + "'");
	}
	
	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		System.out.println("Value of the : " + element.toString() + "Before any changes made");
	}
	
	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		System.out.println("Element value changes to : " + element.toString());
	}
	
	public void beforeClickOn(WebElement element, WebDriver driver) {
		System.out.println("Trying to click on : " + element.toString());
	}
	
	public void afterClickOn(WebElement element, WebDriver driver) {
		System.out.println("Clicked on : " + element.toString());
	}
	
	public void beforeNavigateBack(WebDriver driver) {
		System.out.println("Navigating back to previous page");
	}
	
	public void afterNavigateBack(WebDriver driver) {
		System.out.println("Navigated back to previous page");
	}
	
	public void beforeNavigateForward(WebDriver driver) {
		System.out.println("Navigating forward to next page");
	}
	
	public void afterNavigateForward(WebDriver driver) {
		System.out.println("Navigated forward to next page");
	}
	
	public void onException(Throwable error, WebDriver driver) {
		System.out.println("Exception occured : " + error);
	}
	
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Trying to find element by : " + by.toString());
	}
	
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Found element by : " + by.toString());
	}
	
	public void beforeScript(String script, WebDriver driver) {}
	
	public void afterScript(String script, WebDriver driver) {}
	
	@Override		
    public void onFinish(ITestContext context) {}		

    @Override		
    public void onStart(ITestContext context) {
    	System.out.println("New Test Started " + context.getName());
    }		

    @Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}		

    @Override
    public void onTestFailure(ITestResult result) {
    	String fileWithPath = System.getProperty("user.dir") + "/screenshots/" + result.getName() + System.currentTimeMillis() +".png";
    	TakesScreenshot scrShot =((TakesScreenshot)driver);
    	File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
    	File DestFile = new File(fileWithPath);
    	try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @Override		
    public void onTestSkipped(ITestResult result) {
    	System.out.println("onTestSkipped Method " + result.getName());
    }		

    @Override		
    public void onTestStart(ITestResult result) {}		

    @Override		
    public void onTestSuccess(ITestResult result) {
    	System.out.println("onTestSuccess Method " + result.getName());
    }

}