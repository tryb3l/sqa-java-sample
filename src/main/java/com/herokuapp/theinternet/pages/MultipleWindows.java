package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MultipleWindows extends BaseObjects {
    private By clickHereLink = By.linkText("Click Here");

    public MultipleWindows(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openNewWindow() {
        log.info("Clicking 'Click Here' link");
        click(clickHereLink);
    }

    public NewWindowPage switchToNewWindowPage() {
        log.info("Looking for 'New Window' ");
        switchToWindowWithTitle("New Window");
        return new NewWindowPage(driver, log);
    }
}
