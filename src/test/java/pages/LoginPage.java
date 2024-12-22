package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy(css = "input[type='email']")
    WebElement emailField;
    @FindBy(css = "input[type='password']")
    WebElement passwordField;
    @FindBy(css = "button[type='submit']")
    WebElement submitBtn;
    @FindBy(css = "a[href='registration']")
    WebElement registrationLink;

    public String fromProfileUrl = "https://qa.koel.app/#!/profile";

    public LoginPage provideEmail(String email) {

        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    public LoginPage providePassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    public void clickSubmit() {
        submitBtn.click();
    }

    public void login() {
        provideEmail("rumenul.rimon@testpro.io");
        providePassword("27041575");
        clickSubmit();
    }

    public void clickOnRegistrationLink() {
        registrationLink.click();
    }
}