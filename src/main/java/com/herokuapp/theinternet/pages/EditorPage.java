package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditorPage extends BaseObjects {
    private final By editor = By.id("tinymce");
    private final By frame = By.tagName("iframe");

    public EditorPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public String getEditorText() {
        switchToFrame(frame);
        String text = find(editor).getText();
        log.info("Text from editor : " + text);
        return text;
    }
}
