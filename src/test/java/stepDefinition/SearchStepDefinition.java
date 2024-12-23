package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import pages.PlayListPage;
import pages.SearchResultPage;
import utils.DriverManager;

import java.io.IOException;

public class SearchStepDefinition {

    public WebDriver driver;
    public LoginPage loginPage;
    public HomePage homePage;
    public SearchResultPage searchResultPage;
    public PlayListPage playListPage;
    
    @Given("The user is logged into the koel app")
    public void theUserIsLoggedIntoTheKoelApp(){
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        playListPage = new PlayListPage(driver);
        searchResultPage = new SearchResultPage(driver);

    }

    @And("The user clicks on All Songs from the left side ber")
    public void theUserClicksOnAllSongsFromTheLeftSideBer() throws InterruptedException {
        playListPage.goToAllSong();
        Thread.sleep(1000);
    }


    @When("The user clicks on search field and User inputs a song name {string} in the search field")
    public void userInputsASongNameInTheSearchField(String song) throws IOException, InterruptedException {
        homePage.searchSong(song);
        Thread.sleep(1000);
    }

    @Then("The searched song {string} results if matched should be populated on the Search results page")
    public void theSearchedSongResultsIfMatchedShouldBePopulatedOnTheSearchResultsPage(String song) {
        searchResultPage.searchResult(song);
        for(int i =1;i<=3;i++){
            Assert.assertTrue( driver.findElement(By.xpath("//div[@class='results']/section["+i+"]/ul")).isDisplayed(),"No data found in sections");
        }
    }


    @Then("The searched song {string} results if not matched it should show no results in every section")
    public void theSearchedSongResultsIfNotMatchedItShouldShowNoResultsInEverySection(String song) {
        searchResultPage.searchResult(song);
        for(int i =1;i<=3;i++){
            try {
                Assert.assertEquals( driver.findElement(By.xpath("//div[@class='results']/section["+i+"]/p")).getText(),"no result","Message did not match");
            } catch (Exception e) {
                throw new RuntimeException("Message did not match");
            }

        }

    }

    @Then("The searched song {string} results should remove the whitespace in trailing\\/heading also case sensitive")
    public void theSearchedSongResultsShouldRemoveTheWhitespaceInTrailingHeadingAlsoCaseSensitive(String song) {
        searchResultPage.searchResult(song);
    }

    @Then("Clear the search field using close button")
    public void clearTheSearchFieldUsingCloseButton() throws InterruptedException {
        searchResultPage.setCloseButton();
    }

}
