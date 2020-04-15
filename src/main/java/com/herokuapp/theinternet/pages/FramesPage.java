package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FramesPage extends BaseObjects {
    private final By nested = By.linkText("Nested Frames");
    private final By iFrames = By.linkText("iFrame");

    public FramesPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public NestedFramesPage openNestedFramesPage() {
        click(nested);
        log.info("Navigating to: " + nested);
        return new NestedFramesPage(driver, log);
    }

    public IframePage openiFramesPage() {
        click(iFrames);
        log.info("Navigating to: " + iFrames);
        return new IframePage(driver, log);
    }
}
