package com.cukes;

import com.config.BrowserFactory;
import com.config.DriverFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.net.MalformedURLException;

@CucumberOptions(glue = {"com.steps"},
        features = "src/test/resources/features",
        tags = "@Tester123"
)

public class CukeTestNgRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    BrowserFactory browserFactory = new BrowserFactory();

    @BeforeMethod
    public void setup() {
        try {
            DriverFactory.getInstance().setDriver(browserFactory.createBrowserInstance());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.getInstance().closeBrowser();
    }
}
