package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) { // If driver is not already initialized
            WebDriverManager.chromedriver().setup(); // Set up ChromeDriver
            driver = new ChromeDriver();
            // Initialize the driver
            System.out.println("WebDriver initialized");
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver.close();
            driver = null;
        }
    }
}
