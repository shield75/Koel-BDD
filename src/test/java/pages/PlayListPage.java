package pages;

import io.cucumber.java.zh_cn.假如;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class PlayListPage extends BasePage {
    public PlayListPage(WebDriver givenDriver) {

        super(givenDriver);
    }

    public String playListName = "Automation";
    public String renamePlayList = "Rename PLayList";

    public String errorMessage =
            "No favorites yet.\n" +
                    "Click the icon to mark a song as favorite.";
    @FindBy(xpath = "/html/body/div/div/div/nav/section[2]/h1/i")
    public By addPlayListButton = By.xpath("/html/body/div/div/div/nav/section[2]/h1/i");

    @FindBy(css = "li[data-testid='playlist-context-menu-create-simple']")
    public WebElement newPlayListButton;
    @FindBy(css = "input[name='name']")
    public WebElement inputForPlayListName;

    public void CreatePlayList() {
        hoverOnElement(addPlayListButton);
        wait.until(ExpectedConditions.elementToBeClickable(addPlayListButton)).click();
        findElement(newPlayListButton).click();
        findElement(inputForPlayListName).click();
        findElement(inputForPlayListName).sendKeys(playListName);
        inputForPlayListName.sendKeys(Keys.ENTER);

    }

    public WebElement playListSong() {
        return driver.findElement(By.xpath(String.format("//section[@id='playlists']//*[text()='%s']", playListName)));
    }

    public WebElement renamePlayListSong() {
        return driver.findElement(By.xpath(String.format("//section[@id='playlists']//*[text()='%s']", renamePlayList)));
    }

    @FindBy(css = "button[title='Delete this playlist']")
    public WebElement deleteButton;

    @FindBy(css = ".success.show")
    public WebElement alertSuccessText;

    public void deleteAPlaylist() {
        WebElement selectedPlayList = wait.until(ExpectedConditions.visibilityOf(renamePlayListSong()));
        String playlistName = selectedPlayList.getText();
        selectedPlayList.click();
        WebElement deletePlaylistButton = wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deletePlaylistButton.click();
        WebElement alert = wait.until(ExpectedConditions.visibilityOf(alertSuccessText));
        String alertText = alert.getText();
        String finalAlertText = "Deleted playlist " + '"' + playlistName + '.' + '"';
        Assert.assertEquals(alertText, finalAlertText);
    }


    @FindBy(css = "input[name='name']")
    public WebElement inputFieldName;

    @FindBy(css = ".success.show")
    public WebElement alertTextMessage;

    public void renamePlayList() {
        WebElement selectedPlayList = wait.until(ExpectedConditions.visibilityOf(playListSong()));
        actions.doubleClick(selectedPlayList).perform();
        WebElement inputField = findElement(inputFieldName);
        inputField.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        inputField.sendKeys(renamePlayList);
        inputField.sendKeys(Keys.ENTER);
        WebElement alert = findElement(alertTextMessage);
        String alertText = alert.getText();
        String finalAlertText = "Updated playlist " + '"' + renamePlayList + '.' + '"';
        Assert.assertEquals(alertText, finalAlertText);
    }

    @FindBy(css = "a[href='#!/songs']")
    public WebElement allSongButton;
    @FindBy(css = "#playlists > ul > li.playlist.favorites > a")
    public WebElement favoriteButton;
    @FindBy(xpath = "//section[7]//div[1]//div[1]//div[1]")
    public WebElement message;
    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[1]/section[1]/section[7]/div[1]/div[1]/div[1]/table[1]/tr[1]/td[6]")
    public WebElement songFavButton;
    @FindBy(xpath = "//li[normalize-space()='Download']")
    public WebElement downloadButton;


    public void goToAllSong() throws InterruptedException {
        findElement(allSongButton).click();
        Thread.sleep(3000);

    }
    public void goToFavorite() throws InterruptedException{
        findElement(favoriteButton).click();}
    public void checkEmptyList(String errorMessage){
        String actualMessage = findElement(message).getText();
        Assert.assertEquals(actualMessage,errorMessage, "Massage mismatch");
    }
    int totalSongToAdd =4;
    public void addSongToFavorite() throws InterruptedException {
        Thread.sleep(3000);
        for (int i =1; i<=totalSongToAdd; i++){
            driver.findElement(By.xpath("//body//div//div//div//section//section//div//div//div//table/tr["+i+"]/td[6]")).click();
        }
        Thread.sleep(3000);
    }
    public void checkTotalAddedSong(){
        int songs = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/section[1]/section[7]/div[1]/div[1]/div[1]/table[1]")).findElements(By.xpath("./*")).size();
        if (songs==totalSongToAdd){
            System.out.println("All songs are showing");
        }
    }
    public void deleteSongsFromFavorite() throws InterruptedException {
        Thread.sleep(3000);
        for (int i =1; i<=totalSongToAdd; i++){
            driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/section[1]/section[7]/div[1]/div[1]/div[1]/table[1]/tr[1]/td[6]")).click();
        }
        Thread.sleep(3000);

    }
    public void downloadSong(){
        actions.contextClick(songFavButton).perform();
        findElement(downloadButton).click();

    }
}
