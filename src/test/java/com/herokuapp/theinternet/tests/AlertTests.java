package com.herokuapp.theinternet.tests;

import com.herokuapp.theinternet.base.Utilities;
import com.herokuapp.theinternet.pages.JSAlerts;
import com.herokuapp.theinternet.pages.StartPage;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AlertTests extends Utilities {

    @Test(description = "Test open up defined URI and accept JavaScript alert.")
    public void testAlertJS() {
        SoftAssert softAssert = new SoftAssert();
        StartPage startPage = new StartPage(driver, log);

        startPage.openStartPage();

        JSAlerts alertsPage = startPage.openJSAlertsPage();
        alertsPage.clickForJSAlert();
        sleep(5000);

        String alertMessage = alertsPage.getAlertText();

        alertsPage.acceptJSAlert();

        String result = alertsPage.getResultText();
        sleep(5000);

        softAssert.assertTrue(
            alertMessage.equals("I am a JS Alert"),
            "Alert message is not expected. \nShould be 'I am a JS Alert', but it is'" + alertMessage + "''"
        );

        softAssert.assertTrue(
            result.equals("You successfuly clicked an alert"),
            "result is not expected. \nShould be 'You successfuly clicked an alert', but it is '" + result + "'"
        );
        softAssert.assertAll();
    }

    @Step("Sec. test")
    @Test(description = "Test opens up a js alert page and checks dismiss alert functionality")
    public void JSAlertDismiss() {
        SoftAssert softAssert = new SoftAssert();

        // open main page
        StartPage startPage = new StartPage(driver, log);
        startPage.openStartPage();

        // Click on JavaScript Alerts link
        JSAlerts alertsPage = startPage.openJSAlertsPage();

        // Click JS Confirm
        alertsPage.openJSConfirm();
        sleep(1000);
        // Get alert text
        String alertMessage = alertsPage.getAlertText();

        // Click Cancel
        alertsPage.dismissAlert();

        // Get Results text
        String result = alertsPage.getResultText();
        sleep(1000);

        softAssert.assertTrue(
            alertMessage.equals("I am a JS Confirm"),
            "Alert message is not expected. \nShould be 'I am a JS Confirm', but it is '" + alertMessage + "'"
        );

        softAssert.assertTrue(
            result.equals("You clicked: Cancel"),
            "result is not expected. \nShould be 'You clicked: Cancel', but it is '" + result + "'"
        );
        softAssert.assertAll();
    }

    @Step("Third test")
    @Test(description = "Test checks out prompt support and dealing with it.")
    public void JSPromptCheck() {
        SoftAssert softAssert = new SoftAssert();

        // open main page
        StartPage startPage = new StartPage(driver, log);
        startPage.openStartPage();

        // Click on JavaScript Alerts link
        JSAlerts alertsPage = startPage.openJSAlertsPage();

        // Click JS Prompt button
        alertsPage.clickJSPrompt();
        sleep(1000);
        // Get alert text
        String alertMessage = alertsPage.getAlertText();

        // Type text into alert
        alertsPage.typeTextIntoJSAlert("Test");
        sleep(1000);
        // Get Results text
        String result = alertsPage.getResultText();
        sleep(1000);
        // Verifications
        // 1 - Alert text is expected
        softAssert.assertTrue(
            alertMessage.equals("I am a JS prompt"),
            "Alert message is not expected. \nShould be 'I am a JS prompt', but it is '" + alertMessage + "'"
        );

        // 2 - Result text is expected
        softAssert.assertTrue(result.equals("You entered: Test"), "result is not expected. \nShould be 'Test', but it is '" + result + "'");
        softAssert.assertAll();
    }
}
