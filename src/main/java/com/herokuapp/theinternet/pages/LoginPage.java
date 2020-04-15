package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BaseObjects {
    private By userNameLoc = By.id("username");
    private By passwordLoc = By.name("password");
    private By loginBtn = By.tagName("button");
    private By errorMessageLoc = By.id("flash");

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
        // TODO Auto-generated constructor stub
    }

    public SecureAreaPage logIn(String username, String password) {
        log.info("Running with username [" + username + "] and password [" + password + "]");
        type(username, userNameLoc);
        type(password, passwordLoc);
        click(loginBtn);
        return new SecureAreaPage(driver, log);
    }

    public void negativeLogIn(String username, String password) {
        log.info("Executing Negative LogIn with username [" + username + "] and password [" + password + "]");
        type(username, userNameLoc);
        type(password, passwordLoc);
        click(loginBtn);
    }

    public void waitForErrorMessage() {
        waitForVisibilityOf(errorMessageLoc, 5);
    }

    public String getErrorMessageText() {
        return find(errorMessageLoc).getText();
    }
}
