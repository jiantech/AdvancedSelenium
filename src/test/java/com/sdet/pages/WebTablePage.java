package com.sdet.pages;

import com.sdet.common.BasePage;
import com.sdet.utils.PropertiesHelper;
import com.sdet.utils.WebTable;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebTablePage extends BasePage {
    private WebDriver driver;

    private final String XPath_Table1 = "//table[@id='bodytitle']";
    private final String XPath_Table2 = "//table[@id='titlethead']";
    private final String XPath_Table3 = "//table[@id='titletheadrow2']";
    private final String XPath_Table4 = "//table[@id='body2']";
    private final String XPath_Table5 = "//table[@id='tfooter']";

    public WebTablePage()
    {
        driver = getDriver();
        //PageFactory.initElements(driver, this);
        driver.get("file:///C:/Users/bhara/Desktop/webtables.html");
    }

    public WebTable getTable1()
    {
        return new WebTable(driver, WebTable.TableType.TBODYWITHTITLE,XPath_Table1,0);
    }

    public WebTable getTable2()
    {
        return new WebTable(driver, WebTable.TableType.THEADANDTBODY,XPath_Table2,0);
    }

    public WebTable getTable3()
    {
        return new WebTable(driver, WebTable.TableType.TBODYANDTHEADWITHTITLEROWNO,XPath_Table3,2);
    }

    public WebTable getTable4()
    {
        return new WebTable(driver, WebTable.TableType.THEADANDTBODYWITHNO,XPath_Table4,2);
    }

    public WebTable getTable5()
    {
        return new WebTable(driver, WebTable.TableType.TBODYTHEADTFOOT,XPath_Table5,0);
    }

}
