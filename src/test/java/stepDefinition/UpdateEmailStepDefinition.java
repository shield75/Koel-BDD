package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfileAndPreferences;
import utils.DriverManager;

public class UpdateEmailStepDefinition {
    public WebDriver driver;
    public LoginPage loginPage;
    public HomePage homePage;
    ProfileAndPreferences profileAndPreferences;


    @Given("The user is logged into the koel app using and is in the profile and preference page")
    public void theUserIsLoggedIntoTheKoelAppUsingAndIsInTheProfileAndPreferencePage() throws InterruptedException {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        profileAndPreferences = new ProfileAndPreferences(driver);

        loginPage.provideEmail("rumenul.rimon@testpro.io")
                .providePassword("27041575")
                .clickSubmit();
        Thread.sleep(5000);
        homePage.clickOnAvatar();
    }

    @When("Enter a new email address {string} and save")
    public void enterANewEmailAddressAndSave(String email) throws InterruptedException {
        Thread.sleep(5000);
        profileAndPreferences.provideCurrentPassword("27041575");
        profileAndPreferences.provideNewEmailAddress(email);
        profileAndPreferences.clickOnSaveButton();
    }

    @Then("the mail should be validated with {string}")
    public void theMailShouldBeValidatedWith(String expectedMessage) {
        profileAndPreferences.verifyResponseMessage(expectedMessage);
    }
}
