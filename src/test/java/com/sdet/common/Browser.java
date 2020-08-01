package com.sdet.common;

import com.sdet.utils.PropertiesHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Browser {
    private WebDriver driver;
    private String driverPath;

    public enum Type{
        CHROME,
        CHROME_HEADLESS,
        BROWSERSTACK
    }

    public Browser(){
        driverPath = this.getClass().getClassLoader().getResource("drivers/chromedriver.exe").getPath();
        driver = null;
    }

    public WebDriver createDriver(Type type)
    {
        switch (type)
        {
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                System.setProperty("webdriver.chrome.driver", driverPath);
                driver = new ChromeDriver(options);
                break;
            case CHROME_HEADLESS:
                ChromeOptions headlessOptions = new ChromeOptions();
                headlessOptions.addArguments("start-maximized");
                headlessOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
                driver = new ChromeDriver(headlessOptions);
                break;
            case BROWSERSTACK:
                String remoteUrl = PropertiesHelper.getString("BSUrl")
                        .replace("USERNAME", PropertiesHelper.getDecodedString("BSUserName")
                        .replace("AUTOMATE_KEY",PropertiesHelper.getDecodedString("BSAccessKey")));
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "10");
                caps.setCapability("browser", "Chrome");
                caps.setCapability("browser_version", "81");
                caps.setCapability("name", "SDET Online Training - Tests");
                try {
                    driver = new RemoteWebDriver(new URL(remoteUrl), caps);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
        }
        return driver;
    }

    public void quit()
    {
        driver.close();
        driver.quit();
    }
}
