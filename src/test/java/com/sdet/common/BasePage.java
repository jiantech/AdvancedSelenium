package com.sdet.common;

import org.openqa.selenium.WebDriver;

public class BasePage {
    public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    protected WebDriver getDriver()
    {
        return threadLocalDriver.get();
    }

    public void setDriver(WebDriver driver)
    {
        threadLocalDriver.set(driver);
    }
}
