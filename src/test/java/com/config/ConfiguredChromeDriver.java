package com.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

public class ConfiguredChromeDriver {

    public WebDriver initiateDriver() {
        String chromeDriverExe = Objects.requireNonNull(ConfiguredChromeDriver.class.getClassLoader().getResource("chromedriver.exe")).getPath();
        System.setProperty("webdriver.chrome.driver", chromeDriverExe);

        return new ChromeDriver();
    }
}
