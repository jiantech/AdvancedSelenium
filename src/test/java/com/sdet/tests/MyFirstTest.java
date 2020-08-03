package com.sdet.tests;

import com.sdet.pages.Google;
import org.testng.annotations.Test;

public class MyFirstTest {

    @Test
    public void test1()
    {
        Google google = new Google();
        google.search("Wikipedia");
    }

    @Test
    public void test2()
    {
        Google google = new Google();
        google.search("Browserstack");
    }
}
