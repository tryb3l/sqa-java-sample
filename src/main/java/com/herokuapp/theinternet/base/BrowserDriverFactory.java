package com.herokuapp.theinternet.base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserDriverFactory {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private final String browser;
    private final Logger log;

    private final String chromeDriverPath = "src/main/resources/chromedriver";
    private final String firefoxDriverPath = "src/main/resources/geckodriver";
    private final String phantomJSPath = "src/main/resources/phantomjs";
    private final String geckoDriver = "webdriver.gecko.driver";
    private final String chromeDriver = "webdriver.chrome.driver";
    private final String phantomJS = "phantomjs.binary.path";
    private final String hubUrl = "http://192.168.0.2:4444/wd/hub";

    public BrowserDriverFactory(final String browser, final Logger log) {
        this.browser = browser.toLowerCase();
        this.log = log;
    }

    public WebDriver createDriver() {
        log.info("Created driver: " + browser + " locally");

        // Creating driver
        switch (browser) {
            case "chrome":
                System.setProperty(chromeDriver, chromeDriverPath);
                ChromeOptions chromeOptions = new ChromeOptions();
                Map<String, Object> chromePreferences = new HashMap<>();
                chromePreferences.put("profile.default_content_settings.geolocation", 2);
                chromePreferences.put("download.prompt_for_download", false);
                chromePreferences.put("download.directory_upgrade", true);
                chromePreferences.put("download.default_directory", getDownloadsPath());
                chromePreferences.put("credentials_enable_service", false);
                chromePreferences.put("password_manager_enabled", false);
                chromePreferences.put("safebrowsing.enabled", "true");
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.setExperimentalOption("prefs", chromePreferences);
                driver.set(new ChromeDriver(chromeOptions));
                break;
            case "firefox":
                System.setProperty(geckoDriver, firefoxDriverPath);
                final FirefoxProfile firefoxProfile = new FirefoxProfile();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addPreference("app.update.enabled", false);
                firefoxOptions.addPreference("browser.download.folderList", 2);
                firefoxOptions.addPreference("browser.download.manager.showWhenStarting", false);
                firefoxOptions.addPreference(
                    "browser.helperApps.neverAsk.saveToDisk",
                    "application/zip;application/octet-stream;application/x-zip;application/x-zip-compressed;text/css;text/html;text/plain;text/xml;text/comma-separated-values"
                );
                firefoxOptions.addPreference(
                    "browser.helperApps.neverAsk.openFile",
                    "application/zip;application/octet-stream;application/x-zip;application/x-zip-compressed;text/css;text/html;text/plain;text/xml;text/comma-separated-values"
                );
                firefoxOptions.addPreference("browser.helperApps.alwaysAsk.force", false);
                driver.set(new FirefoxDriver(firefoxOptions));
                break;
            case "headlessChrome":
                System.setProperty(chromeDriver, chromeDriverPath);
                final ChromeOptions chromeOptionsHeadless = new ChromeOptions();
                chromeOptionsHeadless.addArguments("--headless");
                chromeOptionsHeadless.addArguments("--window-size=1920,1080");
                chromeOptionsHeadless.addArguments("--disable-gpu");
                driver.set(new ChromeDriver(chromeOptionsHeadless));
                break;
            case "headlessFirefox":
                System.setProperty(geckoDriver, firefoxDriverPath);
                final FirefoxBinary firefoxBinary = new FirefoxBinary();
                firefoxBinary.addCommandLineOptions("--headless");
                final FirefoxOptions firefoxOptionsHeadless = new FirefoxOptions();
                firefoxOptionsHeadless.setBinary(firefoxBinary);
                driver.set(new FirefoxDriver(firefoxOptionsHeadless));
                break;
            case "phantomjs":
                System.setProperty(phantomJS, phantomJSPath);
                driver.set(new PhantomJSDriver());
                break;
            case "htmlUnit":
                driver.set(new HtmlUnitDriver());
                break;
            default:
                log.debug("cant start browser " + browser + " starting firefox as default");
                System.setProperty(geckoDriver, firefoxDriverPath);
                driver.set(new FirefoxDriver());
                break;
        }

        return driver.get();
    }

    public WebDriver createDriverGrid() {
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        log.info("Starting " + browser + " on grid");

        // Creating driver
        switch (browser) {
            case "chrome":
                capabilities.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
                break;
            case "firefox":
                capabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
                break;
            default:
                log.debug("cant find browser, statring default: " + browser);
                capabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
                break;
        }

        try {
            driver.set(new RemoteWebDriver(new URL(hubUrl), capabilities));
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }

        return driver.get();
    }

    private static String getDownloadsPath() {
        return System.getProperty("user.dir") + String.format("%1$ssrc%1$stest%1$sresources%1$sdownloads%1$s", File.separator);
    }
}
