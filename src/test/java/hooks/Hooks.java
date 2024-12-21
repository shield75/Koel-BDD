package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import utils.DriverManager;

public class Hooks {

    // Initialize WebDriver before each scenario
    @Before
    public void setup() {
        System.out.println("Setting up WebDriver...");
        DriverManager.getDriver();  // Initialize the driver
        DriverManager.getDriver().get("https://qa.koel.app/");  // Open the URL
        System.out.println("Navigating to URL: https://qa.koel.app/");
    }

    // Quit WebDriver after each scenario
    @After
    public void tearDown() {
        System.out.println("Tearing down WebDriver...");
        DriverManager.quitDriver();  // Quit the driver
    }
}
