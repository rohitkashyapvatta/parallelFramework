package com.listeners;

import com.config.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScenarioListener implements ITestListener {

    public WebDriver initiateDriver() {
        return DriverFactory.getInstance().getDriver();
    }

    public void onTestSuccess(ITestResult iTestResult) {

        File screenshotAs = ((TakesScreenshot) initiateDriver()).getScreenshotAs(OutputType.FILE);
        try {
            BufferedImage read = ImageIO.read(screenshotAs);
            ImageIO.write(read, "png", new File("C:/Users/USER/Test" + initiateDriver().hashCode() + ".png"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

