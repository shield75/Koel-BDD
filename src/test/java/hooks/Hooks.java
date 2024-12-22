package hooks;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverManager;

public class Hooks {

    // Initialize WebDriver before each scenario
    @BeforeAll
    public static void setup() throws InterruptedException {
        System.out.println("Setting up WebDriver...");
        DriverManager.getDriver();  // Initialize the driver
        DriverManager.getDriver().get("https://qa.koel.app/");  // Open the URL
        System.out.println("Navigating to URL: https://qa.koel.app/");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        HomePage homePage = new HomePage(DriverManager.getDriver());

        loginPage.provideEmail("rumenul.rimon@testpro.io")
                .providePassword("27041575")
                .clickSubmit();
        Thread.sleep(5000);
        homePage.clickOnAvatar();
    }

    // Quit WebDriver after each scenario
    @AfterAll
    public static void tearDown() {
        System.out.println("Tearing down WebDriver...");
        DriverManager.quitDriver();  // Quit the driver
    }
}
