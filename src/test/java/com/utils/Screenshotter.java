package com.utils;

import org.openqa.selenium.Point;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.apache.commons.io.FileUtils.*;

public class Screenshotter {

    private final String screenshotFolder;
    private WebDriver driver;

    public Screenshotter(WebDriver driver, String screenshotFolder) {
        this.driver = driver;
        this.screenshotFolder = screenshotFolder;
    }

    public File screenshotAndHighlight(WebElement element) {
        return screenshotAndHighlight(element);
    }

    public File screenshotAndHighlight(List<WebElement> elements) {
        try {
            return takeScreenshotAndOverlay(elements);
        } catch (IOException e) {
            System.out.println("Error taking createSnapAndThumbnail and highlight... " + e);
        }
        return null;
    }

    private File takeScreenshotAndOverlay(List<WebElement> elements) throws IOException {
        File tempScreenShotFile = createTempScreenshotFile();
        File fileToReturn = null;
        if (tempScreenShotFile.isFile() && tempScreenShotFile.canRead()) {
            File overlayedFile = overlayWebElementBorders(elements, tempScreenShotFile);
            fileToReturn = moveFileToOutputDirectory(overlayedFile);
            System.out.println("Screenshot: " + tempScreenShotFile.getAbsolutePath());
        } else {
            System.out.println("Skipping image capture");
        }
        return fileToReturn;
    }

    private File moveFileToOutputDirectory(File fileToMove) {
        File destinationFile = new File(screenshotFolder, fileToMove.getName());
        try {
            copyFile(fileToMove, destinationFile);
            forceDelete(fileToMove);
        } catch (IOException e) {
            System.out.println("Error while moving '" + fileToMove + "' to '" + destinationFile.getAbsolutePath() + "'");
        }
        return destinationFile;
    }

    private File overlayWebElementBorders(List<WebElement> elements, File screenShotFile) throws IOException {
        BufferedImage image = ImageIO.read(screenShotFile);
        Graphics2D graphics = image.createGraphics();

        for (WebElement element : elements) {
            Point elementTopLeft = LocationFinder.getLocation(element);
//            if (LocationFinder.isMobile(element)) {
//                elementTopLeft = padLocationToAccountForScrolling(elementTopLeft);
//            }
            org.openqa.selenium.Dimension elementSize = element.getSize();
            createHighlightAroundElement(graphics, elementTopLeft, elementSize);
        }
        ImageIO.write(image, "png", screenShotFile);
        return screenShotFile;
    }

    private Point padLocationToAccountForScrolling(Point elementTopLeft) {
        int scrollHeight = 0;
        try {
            JavascriptExecutor je = (JavascriptExecutor) driver;
            Object pageYOffset = je.executeScript("return window.pageYOffset;");
            scrollHeight = ((Long) pageYOffset).intValue();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

        return new Point(elementTopLeft.getX(), elementTopLeft.getY() + scrollHeight);
    }

    private static void createHighlightAroundElement(Graphics2D graphics, Point location, org.openqa.selenium.Dimension size) {
        final int x = location.getX() - 2;
        final int y = location.getY() - 2;
        final int width = size.getWidth() + 4;
        final int height = size.getHeight() + 4;
        System.out.println("Drawing rectangle at " + x + ", " + y + ", " + width + ", " + height);
        graphics.setColor(Color.RED);
        graphics.setStroke(new BasicStroke(4.0f));
        graphics.drawRect(x, y, width, height);
    }

    private File createTempScreenshotFile() throws IOException {
        try {
            driver = new Augmenter().augment(driver);
        } catch (Exception ignored) {
        }
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        File renamedFile = new File("C:/Users/USER", new Date().getTime() + ".png");
        moveFile(scrFile, renamedFile);

        return renamedFile;
    }
}