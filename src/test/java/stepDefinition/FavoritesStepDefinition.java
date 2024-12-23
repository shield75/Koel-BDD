package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.PlayListPage;
import utils.DriverManager;
import utils.FileUtils;

public class FavoritesStepDefinition {
    public WebDriver driver;
    public PlayListPage playListPage;
    private static final String DOWNLOAD_PATH = System.getProperty("user.dir") + "/downloads";

    @Given("The user click on All songs")
    public void theUserClickOnAllSongs() throws InterruptedException {
        driver = DriverManager.getDriver();
        playListPage = new PlayListPage(driver);
        playListPage.goToAllSong();
        Thread.sleep(3000);

    }

    @When("The user click on favorites under playlist")
    public void theUserClickOnFavoritesUnderPlaylist() throws InterruptedException {
        driver = DriverManager.getDriver();
        playListPage = new PlayListPage(driver);
        playListPage.goToFavorite();

    }


    @When("The user should add song to favorite")
    public void theUserShouldAddSongToFavorite() throws InterruptedException {
        playListPage.addSongToFavorite();

    }

    @Then("The user will show the added song to favorite")
    public void theUserWillShowTheAddedSongToFavorite() throws InterruptedException {
        playListPage.goToFavorite();
        playListPage.checkTotalAddedSong();
        Thread.sleep(10000);
    }

    @Then("The user will show {string} message.")
    public void theUserWillShowMessage(String arg0) {
        playListPage.checkEmptyList(arg0);
    }

    @When("The user should delete song from favorite")
    public void theUserShouldDeleteSongFromFavorite() throws InterruptedException {
        playListPage.goToFavorite();
        playListPage.deleteSongsFromFavorite();

    }

    @When("The user should go to favorite Page and download the first song")
    public void theUserShouldGoToFavoritePageAndDownloadTheFirstSong() throws InterruptedException {
        playListPage.goToFavorite();
        playListPage.downloadSong();
    }

    @Then("the first song {string} will be downloaded")
    public void theFirstSongWillBeDownloaded(String songName) throws InterruptedException {
        boolean isDownloaded = FileUtils.isFileDownloaded(DOWNLOAD_PATH, songName, 30);
        if (isDownloaded) {
            System.out.println("File downloaded successfully: " + songName);
        } else {
            throw new AssertionError("File was not downloaded: " + songName);
        }
    }
}
