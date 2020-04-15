package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NestedFramesPage extends BaseObjects {
    private final By bottom = By.name("frame-bottom");
    private final By left = By.name("frame-left");
    private final By middle = By.name("frame-middle");
    private final By right = By.name("frame-right");

    public NestedFramesPage(WebDriver driver, Logger log) {
        super(driver, log);
        //TODO Auto-generated constructor stub
    }

    public String getFrameText(By frameLocator) {
        switchToFrame(frameLocator);
        log.info("getting text from: " + frameLocator);
        String text = find(frameLocator).getText();
        return text;
    }
}
