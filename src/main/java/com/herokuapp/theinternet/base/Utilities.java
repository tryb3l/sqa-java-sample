package com.herokuapp.theinternet.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

public class Utilities extends BaseTest {

    protected List<LogEntry> getBrowserLogs() {
        LogEntries consoleLog = driver.manage().logs().get("browser");
        List<LogEntry> logs = consoleLog.getAll();
        return logs; // get console.log
    }

    protected void sleep(long millsec) {
        try {
            log.debug("waiting for: " + millsec);
            Thread.sleep(millsec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getSysTime() {
        return (new SimpleDateFormat("HHmmssSSS").format(new Date()));
    }

    //Get Curr Date.
    private static String getCurDate() {
        return (new SimpleDateFormat("yyMMdd").format(new Date()));
    }

    protected void takeScreenshot(String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path =
            System.getProperty("user.dir") +
            File.separator +
            "test-output" +
            File.separator +
            "screenshots" +
            File.separator +
            getCurDate() +
            File.separator +
            testSuiteName +
            File.separator +
            testName +
            File.separator +
            testMethodName +
            File.separator +
            getSysTime() +
            " " +
            fileName +
            ".png";
        try {
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
