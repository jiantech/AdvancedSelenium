package com.sdet.common;

import com.sdet.utils.PropertiesHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Browser {
    private static WebDriver driver;
    private static String driverPath;

    public enum Type{
        CHROME,
        CHROME_HEADLESS,
        BROWSERSTACK
    }

    static{
        driverPath = "target\\test-classes\\drivers\\chromedriver.exe";
        driverPath = new File(driverPath).getPath();
        driver = null;
    }

    public static WebDriver createDriver(Type type)
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
                headlessOptions.addArguments("--headless", "--disable-gpu", "--window-size=1366,768","--ignore-certificate-errors");
                System.setProperty("webdriver.chrome.driver", driverPath);
                driver = new ChromeDriver(headlessOptions);
                break;
            case BROWSERSTACK:
                String remoteUrl = PropertiesHelper.getString("BSUrl")
                        .replace("USERNAME", PropertiesHelper.getDecodedString("BSUserName"))
                        .replace("AUTOMATE_KEY",PropertiesHelper.getDecodedString("BSAccessKey"));              DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "10");
                caps.setCapability("browser", "Chrome");
                caps.setCapability("browser_version", "81");
                caps.setCapability("name", "SDET Online Training - Tests");
                System.out.println(remoteUrl);
                try {
                    driver = new RemoteWebDriver(new URL(remoteUrl), caps);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
        }
        return driver;
    }
}
