package com.sdet;

import com.sdet.common.Browser;
import com.sdet.utils.PropertiesHelper;
import org.openqa.selenium.WebDriver;

public class Executor {
    public static void main(String[] args) {
        Browser browser = new Browser();
        WebDriver driver = browser.createDriver(Browser.Type.CHROME);
        driver.get(PropertiesHelper.getString("baseURLs"));
    }
}
