package com.herokuapp.theinternet.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseObjects {
    protected WebDriver driver;
    protected Logger log;

    public BaseObjects(final WebDriver driver, final Logger log) {
        this.driver = driver;
        this.log = log;
    }

    // Open page w/ given URL
    protected void openUrl(final String url) {
        driver.get(url);
    }

    protected WebElement find(final By locator) {
        return driver.findElement(locator);
    }

    protected List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    protected void click(final By locator) {
        waitForVisibilityOf(locator, 5);
        find(locator).click();
    }

    protected void type(final String text, final By locator) {
        waitForVisibilityOf(locator, 5);
        find(locator).sendKeys(text);
    }

    public String readText(By locator) {
        return driver.findElement(locator).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getCurrentpageTitle() {
        return driver.getTitle();
    }

    public String getCurrentPageSource() {
        return driver.getPageSource();
    }

    private void waitFor(final ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(condition);
    }

    protected void waitForVisibilityOf(final By locator, final Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator), (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                break;
            } catch (final StaleElementReferenceException e) {}
            attempts++;
        }
    }

    protected Alert switchToAllert() {
        final WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }

    public void switchToWindowWithTitle(final String expectedTitle) {
        // sw to new window
        final String firstWindow = driver.getWindowHandle();

        final Set<String> allWindows = driver.getWindowHandles();
        final Iterator<String> windowsIterator = allWindows.iterator();

        while (windowsIterator.hasNext()) {
            final String windowHandle = windowsIterator.next().toString();
            if (!windowHandle.equals(firstWindow)) {
                driver.switchTo().window(windowHandle);
                if (getCurrentpageTitle().equals(expectedTitle)) {
                    break;
                }
            }
        }
    }

    // TODO switchToFrame()
    protected void switchToFrame(final By frameLocator) {
        driver.switchTo().frame(find(frameLocator));
    }

    protected void keyPress(final By locator, final Keys key) {
        find(locator).sendKeys(key);
    }

    public void pressKeyWithActions(final Keys key) {
        log.info("Pressing " + key.name() + " using Actions class");
        final Actions action = new Actions(driver);
        action.sendKeys(key).build().perform();
    }

    public void scrollToBottom() {
        log.info("Scrolling to the bottom of the page");
        final JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    protected void hoverOverElement(final WebElement element) {
        final Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public void setCookie(Cookie kkie) {
        log.info("cookie adding" + kkie.getName());
        driver.manage().addCookie(kkie);
        log.info("Cookie added");
    }

    public String getCookie(String name) {
        log.info("Getting cookie info" + name);
        return driver.manage().getCookieNamed(name).getValue();
    }
}
