package com.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public class BrowserFactory {

    public WebDriver createBrowserInstance() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL("http://192.168.22.1:4444/wd/hub"), capabilities);
        return remoteWebDriver;
    }
}
