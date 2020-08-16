package com.sdet.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WebTable {
    public enum TableType{
        TBODYWITHTITLE,
        THEADANDTBODY,
        THEADANDTBODYWITHNO,
        TBODYANDTHEADWITHTITLEROWNO,
        TBODYTHEADTFOOT
    }

    private WebDriver driver;
    private String basePath;
    private String rowTitlePath;
    private String rowDataPath;
    private String rowFooterPath;

    private int rows;
    private int columns;

    private List<String> titles;
    private TableType type;


    public WebTable(WebDriver driver, TableType type, String xPath, int titleRowOrBodyRow)
    {
        this.driver = driver;
        this.type = type;
        basePath = xPath;
        switch (type)
        {
            case TBODYWITHTITLE:
                rowTitlePath = basePath + "/tbody/tr[1]";
                rowDataPath = basePath + "/tbody/tr";
                break;
            case THEADANDTBODY:
                rowTitlePath = basePath + "/thead/tr[1]";
                rowDataPath = basePath + "/tbody/tr";
                break;
            case TBODYANDTHEADWITHTITLEROWNO:
                rowTitlePath = basePath + "/thead/tr[" + Integer.toString(titleRowOrBodyRow) + "]";
                rowDataPath = basePath + "/tbody/tr";
                break;
            case TBODYTHEADTFOOT:
                rowTitlePath = basePath + "/thead/tr[1]";
                rowDataPath = basePath + "/tbody/tr";
                rowFooterPath = basePath + "/tfoot/tr";
                break;
            case THEADANDTBODYWITHNO:
                rowTitlePath = basePath + "/thead/tr[1]";
                rowDataPath = basePath + "/tbody[" + Integer.toString(titleRowOrBodyRow) + "]/tr";
                break;
        }
        setTitles();
        rows = getRows();
        columns = getColumns();
    }

    private void setTitles()
    {
        /*titles = new ArrayList<>();
        List<WebElement> items = driver.findElements(By.xpath(rowTitlePath + "/th"));
        for(WebElement elm:items)
        {
            titles.add(elm.getText());
        }*/
        titles = driver.findElements(By.xpath(rowTitlePath + "/th")).stream().map(p->p.getText()).collect(Collectors.toList());
    }

    public List<String> getTitles()
    {
        return titles;
    }

    public int getRows()
    {
        return driver.findElements(By.xpath(rowDataPath)).size();
    }

    public int getColumns()
    {
        return driver.findElements(By.xpath(rowTitlePath + "/th")).size();
    }

    public int getColumns(int row)
    {
        return driver.findElements(By.xpath(rowDataPath + "[" + Integer.toString(row) + "]/td")).size();
    }

    public int getColumn(String title)
    {
        return titles.indexOf(title)+1;
    }

    public boolean searchData(String data)
    {
        return getPosition(data) != null;
    }

    public boolean searchData(int columnNo, String data)
    {
        String dataPath = rowDataPath + "[ROWNO]/td[" + Integer.toString(columnNo) + "]";
        for(int i = 1; i<= rows; i++)
        {
            if(type == TableType.TBODYWITHTITLE && i == 1)
                continue;
            if(driver.findElement(By.xpath(dataPath.replace("ROWNO",Integer.toString(i)))).getText().equals(data))
                return true;
        }
        return false;
    }

    public boolean searchData(String columnTitle, String data)
    {
        return searchData(getColumn(columnTitle), data);
    }

    public Point getPosition(String data)
    {
        String dataPath = rowDataPath + "[ROWNO]/td[COLUMNNO]";
        for(int i = 1; i<= rows; i++)
        {
            if(type == TableType.TBODYWITHTITLE && i == 1)
                continue;
            for(int j = 1; j <= columns; j++)
            {
                if(driver.findElement(By.xpath(dataPath.replace("ROWNO", Integer.toString(i)).replace("COLUMNNO",Integer.toString(j)))).getText().equals(data))
                    return new Point(i,j);
            }
        }
        return null;
    }

    public String getText(int row, int column)
    {
        String dataPath = rowDataPath + "[" + Integer.toString(row ) + "]/td[" + Integer.toString(column) + "]";
        return driver.findElement(By.xpath(dataPath)).getText();
    }

    public String getFooterText(int row, int column)
    {
        String datapath = rowFooterPath + "[" + Integer.toString(row) + "]/th[" + Integer.toString(column) + "]";
        return driver.findElement(By.xpath(datapath)).getText();
    }
}
