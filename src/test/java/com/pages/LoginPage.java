package com.pages;

import com.Wrapper.WebElementWrapper;
import com.config.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.Wrapper.WebElementWrapper.byXpath;

public class LoginPage {

    public static WebElementWrapper getPathOfSignInButton(String signIn) {
        return byXpath("//span[contains(string(.), '"+signIn+"')]");
    }

    public static WebElement getPathOfInputField(String entityName) {
        return DriverFactory.getInstance().getDriver().findElement(By.xpath("//input[@name='"+entityName+"']"));
    }

    public static WebElementWrapper getPathOfLogo(String id) {
        return byXpath("//a[@id='"+id+"']");
    }
}
