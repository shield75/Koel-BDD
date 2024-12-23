package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfileAndPreferences;
import utils.DatabaseManager;
import utils.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateEmailStepDefinition {
    public WebDriver driver;
    public LoginPage loginPage;
    public HomePage homePage;
    ProfileAndPreferences profileAndPreferences;

    public String updatedEmail;
    public boolean status = true;


    @Given("The user is logged into the koel app using and is in the profile and preference page")
    public void theUserIsLoggedIntoTheKoelAppUsingAndIsInTheProfileAndPreferencePage() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        profileAndPreferences = new ProfileAndPreferences(driver);
        homePage.clickOnAvatar();
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

    @When("The user logs out from the Koel app")
    public void theUserLogsOutFromTheKoelApp() throws InterruptedException {
        Thread.sleep(9000);
        homePage.clickOnLogOut();
    }

    @Then("The user should be on the login page")
    public void theUserShouldBeOnTheLoginPage() throws InterruptedException {
        Thread.sleep(2000);
    }

    @When("The user tries to log in with the old email {string}")
    public void theUserTriesToLogInWithTheOldEmail(String email) {
        loginPage.provideEmail(email)
                .providePassword("27041575")
                .clickSubmit();
    }

    @Then("The user should not be able to log in and remain on the login page")
    public void theUserShouldNotBeAbleToLogInAndRemainOnTheLoginPage() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(),loginPage.fromProfileUrl, "User is not in the login page");
    }

    @When("The user tries to log in with the new email {string}")
    public void theUserTriesToLogInWithTheNewEmail(String email) {
        loginPage.provideEmail(email)
                .providePassword("27041575")
                .clickSubmit();
    }

    @Then("The user should be able to log in successfully")
    public void theUserShouldBeAbleToLogInSuccessfully() throws InterruptedException {
        Thread.sleep(2000);
        homePage.checkAvatar();
    }

    @And("the updated email {string} should be saved in the database")
    public void theUpdatedMailShouldBeSavedInTheDatabase(String email) {
        String query = "SELECT COUNT(*) AS count FROM users WHERE email = '"+email+"'";

        try {
            // Execute the query
            ResultSet resultSet = DatabaseManager.executeQuery(query);

            // Process the result
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                Assert.assertEquals( 1, count, "Email is not saved in the database!");
            } else {
                throw new RuntimeException("Failed to retrieve data from the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database query execution failed.");
        }
    }
}
