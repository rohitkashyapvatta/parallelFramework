package com.steps;

import com.config.DriverFactory;
import com.pages.LoginPage;
import com.pages.SearchPage;
import com.utils.SeleniumScreenSnapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;
import org.openqa.selenium.WebDriver;

import static com.pages.LoginPage.getPathOfSignInButton;


public class MyStepdefs {

    WebDriver driver = DriverFactory.getInstance().getDriver();
    SeleniumScreenSnapper seleniumScreenSnapper = new SeleniumScreenSnapper(driver);

    @Then("User is on application")
    public void userIsOnApplication() {
        String title = driver.getTitle();
        System.out.println("Title is " + title);
    }

    @When("User open the {string} application")
    public void amzuserOpenTheApplication(String website) {
        driver.navigate().to("https://www." + website + ".com/");
        /*try {
//            Thread.sleep(240000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    @And("User maximize the window")
    public void userMaximizeTheWindow() throws InterruptedException {
        driver.manage().window().maximize();
    }

    @When("User search {string} on {string} search bar")
    public void userSearchOnSearchBar(String searchBox, String entityName) {
        SearchPage.getPathOfSearchBox(entityName).sendKeys(searchBox);
        SearchPage.getPathOfSearchBoxBUtton(entityName).submit();
    }

    @And("User waits for a while")
    public void userWaitsForAWhile() throws InterruptedException {
        Thread.sleep(10000);
    }

    @And("User click on {string} Button")
    public void userClickOnButton(String expectedButton) {
        getPathOfSignInButton(expectedButton).click();
    }

    @And("User enter {string} on {string} field")
    public void userEnterOnField(String expectedEntity, String entityName) {
        LoginPage.getPathOfInputField(entityName).click();
        LoginPage.getPathOfInputField(entityName).sendKeys(expectedEntity);
    }

    @And("User click on {string} logo")
    public void userClickOnLogo(String logo) {
        seleniumScreenSnapper.createSnapAndThumbnail(LoginPage.getPathOfLogo(logo).findElement());
        LoginPage.getPathOfLogo(logo).click();

    }
}
