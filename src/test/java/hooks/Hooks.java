package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.DriverManager;

public class Hooks {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = DriverManager.getDriver(); // Initialize the WebDriver
        driver.manage().window().fullscreen();
        System.out.printf("hi");// Maximize the browser window
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver(); // Quit the driver after the test
    }
}
