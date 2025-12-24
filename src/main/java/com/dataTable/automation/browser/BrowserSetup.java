package com.dataTable.automation.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserSetup {

	public static WebDriver driver;

	public static WebDriver getDriver() {
		if (driver == null) {

			WebDriverManager.chromedriver().setup();
			
			ChromeOptions options = new ChromeOptions();

			options.addArguments("--headless"); 
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");

			driver = new ChromeDriver();

			driver.manage().window().maximize();
		}
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}

	}

}