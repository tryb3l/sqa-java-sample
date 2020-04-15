package com.herokuapp.theinternet.tests;

import com.herokuapp.theinternet.base.Utilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.SecureAreaPage;
import com.herokuapp.theinternet.pages.StartPage;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends Utilities {

    @Step("Sign in Test")
    @Test(description = "testing sign in process w/ cookies manipulations.")
    public void loginTest() {
        StartPage startPage = new StartPage(driver, log);
        startPage.openStartPage();
        takeScreenshot("StartPage");

        LoginPage loginPage = startPage.openFormAuthLink();
        takeScreenshot("SignIn page");

        Cookie kkie = new Cookie("username", "tomsmith", "the-internet.herokuapp.com", "/", null);
        loginPage.setCookie(kkie);

        SecureAreaPage secureAreaPage = loginPage.logIn("tomsmith", "SuperSecretPassword!");
        takeScreenshot("SecureArea");

        String username = secureAreaPage.getCookie("username");
        log.info("Username cookie " + username);
        String session = secureAreaPage.getCookie("rack.session");
        log.info("Session cookie: " + session);

        // expected new page url
        Assert.assertEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getPageUrl());
        //SignOut button is visible.
        Assert.assertTrue(secureAreaPage.isLogOutButtonVisible(), "LogOut Button is not visible");

        String expectedSuccessMessage = "You logged into a secure area!";
        String actualSuccessMessage = secureAreaPage.getSuccessMessageText();
        Assert.assertTrue(
            actualSuccessMessage.contains(expectedSuccessMessage),
            "actualSuccessMessage does not contain expectedSuccessMessage\nexpectedSuccessMessage: " +
            expectedSuccessMessage +
            "\nactualSuccessMessage: " +
            actualSuccessMessage
        );
    }
}
