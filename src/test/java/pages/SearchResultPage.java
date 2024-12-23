package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class SearchResultPage extends BasePage {
    public SearchResultPage(WebDriver givenDriver) {
        super(givenDriver);
    }


    @FindBy(css = "div[class='heading-wrapper'] h1 span strong")
    WebElement searchResult;

    @FindBy(className = "dirty")
    WebElement closeButton;

    public void setCloseButton(){
        findElement(closeButton).click();
    }


    public void searchResult(String song) {
        Assert.assertEquals(searchResult.getText(),song,"Song not found");
    }



}
