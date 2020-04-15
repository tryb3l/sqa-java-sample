package com.herokuapp.theinternet.base;

import java.lang.reflect.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

	protected WebDriver driver;
	protected Logger log;
	protected String testMethodName;
	protected String testName;
	protected String testSuiteName;

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser", "environment" })
	protected void setUp(final Method method, @Optional("chrome") final String browser, @Optional("local") final String environment, final ITestContext ctx) {
	
		final var testName = ctx.getCurrentXmlTest().getName();
		log = LogManager.getLogger(testName);

		// Create Driver
		final BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
		if (environment.equals("grid")) {
			driver = factory.createDriverGrid();
		} else {
			driver = factory.createDriver();
		}
		setCurrentThreadName();
		// maximize browser window
		driver.manage().window().maximize();

		// Set up test name and Logger
		this.testSuiteName = ctx.getSuite().getName(); 
		this.testName = testName;
		this.testMethodName = method.getName();

	}

	@AfterMethod(alwaysRun = true)
	protected void tearDown() {
		// Closing driver
		log.info("[Closing driver]");
		driver.quit();
	}

	/** Sets thread name which includes thread id */
	private void setCurrentThreadName() {
		final Thread thread = Thread.currentThread();
		final String threadName = thread.getName();
		final String threadId = String.valueOf(thread.getId());
		if (!threadName.contains(threadId)) {
			thread.setName(threadName + " " + threadId);
		}
	}

}
