package com.dataTable.automation.pages;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {

	private WebDriver driver;
	WebDriverWait wait;

	private By loginTextBox = By.cssSelector("input[name='login']");

	private By passwordTextBox = By.cssSelector("input[name='password']");

	private By loginbutton = By.xpath("//button[@type='submit' and normalize-space()='Login']");

	private By profileLink = By.xpath("//a[normalize-space()='Profile']");

	private By genderList = By.cssSelector("input#gender");

	private By ageTextbox = By.cssSelector("input#age");

	private By addressTextbox = By.cssSelector("textarea#address");

	private By phoneTextbox = By.cssSelector("input#phone");

	private By hobbyDropdown = By.cssSelector("select#hobby");

	private By saveButton = By.cssSelector("button[type='submit']");

	private By successMessage = By.xpath("//div[contains(@class, 'alert-success') and not(contains(@class, 'hidden-md-down'))]");

	public ProfilePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	}

	public void login() {

		driver.findElement(loginTextBox).sendKeys("testdesk");
		driver.findElement(passwordTextBox).sendKeys("Pass@1234");
		driver.findElement(loginbutton).click();
	}

	public void clickProfileLink() {
		wait.until(ExpectedConditions.elementToBeClickable(profileLink)).click();
	}

	public void enterProfileDetails(Map<String, String> userData) {
		if (userData.containsKey("Gender")) {

			wait.until(ExpectedConditions.elementToBeClickable(genderList)).clear();

			driver.findElement(genderList).sendKeys(userData.get("Gender"));
			;
		}

		if (userData.containsKey("Age")) {
			driver.findElement(ageTextbox).clear();
			driver.findElement(ageTextbox).sendKeys(userData.get("Age"));
		}

		if (userData.containsKey("Address")) {
			driver.findElement(addressTextbox).clear();
			driver.findElement(addressTextbox).sendKeys(userData.get("Address"));
		}

		if (userData.containsKey("Phone")) {
			driver.findElement(phoneTextbox).clear();
			driver.findElement(phoneTextbox).sendKeys(userData.get("Phone"));
		}

		if (userData.containsKey("Hobby")) {
			new Select(driver.findElement(hobbyDropdown)).selectByVisibleText(userData.get("Hobby"));
		}
	}

	public void clickSaveButton() {
		driver.findElement(saveButton).click();
	}

	public boolean isProfileUpdateSuccessful() {
		return wait.until(driver -> driver.findElements(successMessage).size() > 0);
	}

	public String getProfileUpdateSuccessMessage() {
		
		return driver.findElement(successMessage).getText().trim();
	}

}
