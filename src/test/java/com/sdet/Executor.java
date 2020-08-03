package com.sdet;

import com.sdet.common.Browser;
import com.sdet.utils.PropertiesHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Executor {
    public static void main(String[] args) {
        Browser browser = new Browser();
        WebDriver driver = browser.createDriver(Browser.Type.BROWSERSTACK);
        driver.get(PropertiesHelper.getString("baseURL"));
        driver.findElement(By.name("q")).sendKeys("Wikipedia", Keys.RETURN);
        driver.findElement(By.xpath("//h3[text()='Wikipedia']")).click();
        System.out.println(driver.getTitle());

    }
}
