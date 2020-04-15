package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JSAlerts extends BaseObjects {
    private By resultText = By.id("result");
    private By clickJSAlertButton = By.xpath("//button[text()='Click for JS Alert']");
    private By clickConfirmButton = By.xpath("//button[text()='Click for JS Confirm']");
    private By clickPromptButton = By.xpath("//button[text()='Click for JS Prompt']");

    public JSAlerts(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void clickForJSAlert() {
        log.info("pushing btn 'click for js alert'");
        click(clickJSAlertButton);
    }

    public void clickJSPrompt() {
        log.info("Pushing btn 'click for JS Prompt'");
        click(clickPromptButton);
    }

    public String getAlertText() {
        Alert alert = switchToAllert();
        String alertText = alert.getText();
        log.info("Alert text: " + alertText);
        return alertText;
    }

    public void acceptJSAlert() {
        log.info("Switching to alert and accepting it");
        Alert alert = switchToAllert();
        alert.accept();
    }

    public void dismissAlert() {
        log.info("switching to alert and rejecting it");
        Alert alert = switchToAllert();
        alert.dismiss();
    }

    //typing text in allert
    public void typeTextIntoJSAlert(String txt) {
        log.info("Typing string into alert and pushing OK");
        Alert alert = switchToAllert();
        alert.sendKeys(txt);
        alert.accept();
    }

    public void openJSConfirm() {
        log.info("Pushing on button to open alert and click for js confirm");
        click(clickConfirmButton);
    }

    public String getResultText() {
        String result = find(resultText).getText();
        log.info("Result text is: " + result);
        return result;
    }
}
