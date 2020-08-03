package com.sdet.listeners;

import com.sdet.common.BasePage;
import com.sdet.common.Browser;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    public void onTestStart(ITestResult result) {
        BasePage.threadLocalDriver.set(Browser.createDriver(Browser.Type.CHROME));
    }

    public void onTestSuccess(ITestResult result) {
        WebDriver driver = BasePage.threadLocalDriver.get();
        if(driver != null)
        {
            driver.close();
            driver.quit();
        }
    }

    public void onTestFailure(ITestResult result) {
        WebDriver driver = BasePage.threadLocalDriver.get();
        if(driver != null)
        {
            driver.close();
            driver.quit();
        }
    }
}
