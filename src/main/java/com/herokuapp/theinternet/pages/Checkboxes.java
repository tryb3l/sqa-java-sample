package com.herokuapp.theinternet.pages;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Checkboxes extends BaseObjects {
    private By checkbox = By.xpath("//input[@type='checkbox']");

    public Checkboxes(WebDriver driver, Logger log) {
        super(driver, log);
    }

    //Check all checkboxes if they're unchecked
    public void selectAllBoxes() {
        log.info("Taping on unchecked checkboxes");
        List<WebElement> checkboxes = findAll(checkbox);
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }
}
