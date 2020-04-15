package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StartPage extends BaseObjects {
    private final String startPageUrl = "http://the-internet.herokuapp.com/";
    private final By formAuthLink = By.linkText("Form Authentication");
    private final By framesLink = By.linkText("Frames");
    private final By checkboxLink = By.linkText("Checkboxes");
    private final By jsAlertLink = By.linkText("JavaScript Alerts");
    private final By multipleWindowsLink = By.linkText("Multiple Windows");
    private final By editorLink = By.linkText("WYSIWYG Editor");
    private final By shadowDomLink = By.linkText("Shadow DOM");

    public StartPage(WebDriver driver, Logger log) {
        super(driver, log);
        //TODO Auto-generated constructor stub
    }

    // Exec launching start page
    public void openStartPage() {
        log.info(startPageUrl + "Is opened ");
        openUrl(startPageUrl);
    }

    public LoginPage openFormAuthLink() {
        log.info("clicking form auth on startpage ");
        click(formAuthLink);
        return new LoginPage(driver, log);
    }

    public ShadowDOM openShadowDOMPage() {
        log.info("clicking link that navigate to shadow DOM page " + shadowDomLink);
        click(shadowDomLink);
        return new ShadowDOM(driver, log);
    }

    public FramesPage openFramesPage() {
        log.info("Clicking link to open frame page" + framesLink);
        click(framesLink);
        return new FramesPage(driver, log);
    }

    public Checkboxes openCheckboxes() {
        log.info("clicking checkboxes link " + checkboxLink);
        click(checkboxLink);
        return new Checkboxes(driver, log);
    }

    public JSAlerts openJSAlertsPage() {
        log.info("clicking link to open a js alerts page " + jsAlertLink);
        click(jsAlertLink);
        return new JSAlerts(driver, log);
    }

    public WindowPage openMultipleWindowsPage() {
        log.info("Clicking link to open a multiple windows page " + multipleWindowsLink);
        click(multipleWindowsLink);
        return new WindowPage(driver, log);
    }

    public EditorPage openEditorPage() {
        log.info("Clicking link to open editor" + editorLink);
        click(editorLink);
        return new EditorPage(driver, log);
    }
}
