package com.sdet.tests;

import com.sdet.pages.WebTablePage;
import com.sdet.utils.WebTable;
import org.testng.annotations.Test;

public class WebTableTest {
    @Test
    public void test()
    {
        WebTablePage webTablePage = new WebTablePage();
        WebTable table = webTablePage.getTable5();
        System.out.println(table.searchData("94"));
        System.out.println(table.searchData("Last Name","Johnson"));
        System.out.println(table.getFooterText(1,2));
    }
}
