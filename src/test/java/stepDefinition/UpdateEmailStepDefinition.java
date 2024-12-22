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

    public String updatedEmail;
    public boolean status = true;


    @Given("The user is logged into the koel app using and is in the profile and preference page")
    public void theUserIsLoggedIntoTheKoelAppUsingAndIsInTheProfileAndPreferencePage() throws InterruptedException {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        profileAndPreferences = new ProfileAndPreferences(driver);
    }

    @When("Enter a new email address {string} and save")
    public void enterANewEmailAddressAndSave(String email) throws InterruptedException {
        Thread.sleep(5000);
        profileAndPreferences.provideCurrentPassword("27041575");
        if(!status){
            profileAndPreferences.provideNewEmailAddress(updatedEmail);
        }
        else{
            profileAndPreferences.provideNewEmailAddress(email);
        }
        profileAndPreferences.clickOnSaveButton();
    }

    @Then("The mail {string} should not be updated and validated with {string}")
    public void theMailShouldBeValidatedWith(String email, String expectedMessage) {
        status = profileAndPreferences.verifyResponseMessage(expectedMessage);
        if(!status){
            updatedEmail = email;
        }
    }

    @Then("the mail should be updated and message {string} should be displayed")
    public void theMailShouldBeUpdatedAndMessageShouldBeDisplayed(String expectedMessage) {
        profileAndPreferences.verifyResponseMessage(expectedMessage);
    }
}
