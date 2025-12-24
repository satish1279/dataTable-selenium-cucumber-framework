package com.dataTable.automation.stepDefinition;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.dataTable.automation.browser.BrowserSetup;
import com.dataTable.automation.utils.ExtentReportManager;
import com.dataTable.automation.utils.ScreenshotHelper;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hooks {

	private static ExtentReports extent;
	private static ExtentTest scenarioTest;


	@BeforeAll
	public static void reportSetup() {
		extent = ExtentReportManager.getExtentReports();
	}

	@Before
	public void browserSetup(Scenario scenario) {
		BrowserSetup.getDriver();

		scenarioTest = extent.createTest(scenario.getName());
	}

	@AfterStep
	public void afterStep(Scenario scenario) {
		if (scenario.isFailed()) {
			
			String path= ScreenshotHelper.takeScreenshot(BrowserSetup.getDriver());
			
			scenarioTest.fail("Step Failed").addScreenCaptureFromPath(path);
		} 
	}


	@After
	public void tearDownBrowser(Scenario scenario) {

		if (scenario.isFailed()) {
			scenarioTest.fail("Scenario FAILED");
		} else {
			scenarioTest.pass("Scenario PASSED");
		}
		
		BrowserSetup.quitDriver();
	}
	

	@AfterAll
	public static void tearDownReport() {
		extent.flush();
	}
}


