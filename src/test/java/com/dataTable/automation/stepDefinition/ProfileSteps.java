package com.dataTable.automation.stepDefinition;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.dataTable.automation.browser.BrowserSetup;
import com.dataTable.automation.pages.ProfilePage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProfileSteps {

	private WebDriver driver;
	private ProfilePage profilePage;;

	public ProfileSteps() {

		driver = BrowserSetup.getDriver();
		profilePage = new ProfilePage(driver);
	}

	private static final Logger log = LogManager.getLogger(ProfileSteps.class);

	@Given("User is logged in")
	public void user_is_logged_in() {

		driver.get("https://buggy.justtestit.org/");
		profilePage.login();

		log.info("User logged in successfully");
	}

	@And("User is naviagted to Profile page")
	public void user_is_naviagted_to_profile_page() {
		profilePage.clickProfileLink();
	}

	@When("User enters the following updated details:")
	public void user_enters_the_following_updated_details(DataTable dataTable) {

		Map<String, String> userData = dataTable.asMap(String.class, String.class);
		profilePage.enterProfileDetails(userData);

		log.info("User entered updated data");
	}

	@When("User clicks on the Save button")
	public void user_clicks_on_the_save_button() {
		profilePage.clickSaveButton();

		log.info("Updated data saved");
	}

	@Then("the profile should be updated successfully")
	public void the_profile_should_be_updated_successfully() {

		Assert.assertTrue("Profile Update failed: Success message was not displayed",
				profilePage.isProfileUpdateSuccessful());

		String expectedMessage = "The profile has been saved successful";
		String actualMessage = profilePage.getProfileUpdateSuccessMessage();

		Assert.assertEquals("Message text mismatch!", expectedMessage, actualMessage);

		log.info("Success message verified: {}", actualMessage);
	}

}
