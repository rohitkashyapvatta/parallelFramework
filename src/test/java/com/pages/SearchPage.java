package com.pages;

import com.config.DriverFactory;
import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class SearchPage {


    public static final Map<String, String> SEARCH_BOX_MAP = ImmutableMap.<String, String>builder()
            .put("Amazon", "twotabsearchtextbox")
            .put("Snapdeal", "inputValEnter")
            .put("pepperfry", "search")
            .build();

    public static final Map<String, String> SEARCH_BOX_BUTTON = ImmutableMap.<String, String>builder()
            .put("Amazon", "nav-search-submit-button")
            .put("Snapdeal", "inputValEnter")
            .put("pepperfry", "[@id=\"searchButton\"]")
            .build();

    public static WebElement getPathOfSearchBox(String searchBox) {
        return DriverFactory.getInstance().getDriver().findElement(By.xpath("//input[@id='" + SEARCH_BOX_MAP.get(searchBox) + "']"));
    }

    public static WebElement getPathOfSearchBoxBUtton(String searchBox) {
        return DriverFactory.getInstance().getDriver().findElement(By.xpath("//input[@id='" + SEARCH_BOX_MAP.get(searchBox) + "']"));
    }
}
