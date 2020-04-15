package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage extends BaseObjects {
    private String pageUrl = "http://the-internet.herokuapp.com/secure";
    private By logOutButton = By.xpath("//a[@class='button secondary radius']");
    private By message = By.id("flash-messages");

    public SecureAreaPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public boolean isLogOutButtonVisible() {
        return find(logOutButton).isDisplayed();
    }

    public String getSuccessMessageText() {
        return find(message).getText();
    }
}
