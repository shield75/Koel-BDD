package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {

            // Define download path (Absolute path)
            String downloadPath = "F:\\SQA\\Koel-BDD\\downloads";
            System.out.println("Download Path: " + downloadPath);

            // Setup WebDriverManager
            WebDriverManager.chromedriver().setup();

            // Configure Chrome options
            ChromeOptions options = new ChromeOptions();

            // Set preferences for automatic downloads
            Map<String, Object> prefs = new HashMap<>();

            // Set download directory in Chrome preferences
            prefs.put("download.default_directory", downloadPath);
            prefs.put("profile.default_content_settings.popups", 0);  // Disable popups
            prefs.put("savefile.default_directory", downloadPath);  // Ensure saving to the path

            // Disable download prompt
            prefs.put("download.prompt_for_download", false);
            prefs.put("safebrowsing.enabled", "false");  // Disable safe browsing
            prefs.put("safebrowsing.disable_download_protection", true);  // Disable download protection

            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--start-maximized");
            options.addArguments("--disable-popup-blocking");

            // You can optionally add user-data-dir for a custom profile
            // options.addArguments("user-data-dir=C:/path/to/fresh/chrome/profile");

            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
