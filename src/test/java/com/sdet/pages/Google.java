package com.sdet.pages;

import com.sdet.common.BasePage;
import com.sdet.utils.PropertiesHelper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Google extends BasePage {
    private WebDriver driver;

    @FindBy(name = "q")
    private WebElement tbSearchBox;

    public Google()
    {
        driver = getDriver();
        PageFactory.initElements(driver, this);
        driver.get(PropertiesHelper.getString("baseURL"));
    }

    public void search(String text)
    {
        tbSearchBox.sendKeys(text, Keys.RETURN);
    }
}
