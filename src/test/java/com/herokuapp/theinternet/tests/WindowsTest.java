package com.herokuapp.theinternet.tests;

import com.herokuapp.theinternet.base.Utilities;
import com.herokuapp.theinternet.pages.NewWindowPage;
import com.herokuapp.theinternet.pages.StartPage;
import com.herokuapp.theinternet.pages.WindowPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WindowsTest extends Utilities {

    @Test(description = "Testing switching browsers window")
    public void openNewWindow() {
        StartPage startPage = new StartPage(driver, log);
        startPage.openStartPage();

        WindowPage windowPage = startPage.openMultipleWindowsPage();
        windowPage.openNewWindow();
        sleep(1000);
        NewWindowPage newWindowPage = windowPage.switchToNewWindowPage();
        String pageSrc = newWindowPage.getCurrentPageSource();
        Assert.assertTrue(pageSrc.contains("New Window"), "New page src doesn't have expected txt");
    }
}
