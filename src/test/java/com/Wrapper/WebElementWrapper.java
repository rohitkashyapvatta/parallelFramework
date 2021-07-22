package com.Wrapper;

import com.config.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.pages.LoginPage.getPathOfSignInButton;

public class WebElementWrapper {

    private By by;

    private WebElementWrapper(By by) {
        this.by = by;
    }

    public static WebElementWrapper byXpath(String xpath) {
        return new WebElementWrapper(By.xpath(xpath));
    }

    public List<WebElement> findElements() {
        return DriverFactory.getInstance().getDriver().findElements(by);
    }

    public WebElement findVisibleElement() {
        List<WebElement> elements = findElements();
        return elements.isEmpty() ? null : elements.stream().filter(WebElement::isDisplayed).findFirst().orElse(null);
    }

    public WebElement findElement() {
        return DriverFactory.getInstance().getDriver().findElement(by);
    }

    public void click() {
        WebElement element = findVisibleElement();
        if (element != null) {
            element.click();
        } else {
            findElement().click();
        }

    }
}
