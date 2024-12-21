package cucumberRunner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/features",  // Path to feature files
        glue = "stepDefinition",                   // Path to step definition classes
        plugin = {
                "pretty",                             // Prints the console output in a readable format
                "html:target/cucumber-reports/cucumber.html",   // Generates an HTML report in the specified folder
                "json:target/cucumber-reports/cucumber.json",  // Generates a JSON report
                "junit:target/cucumber-reports/cucumber.xml"    // Generates a JUnit XML report
        },
        monochrome = true, // Makes the console output more readable
        dryRun = false     // Ensures tests are executed, not just validated
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
        // The TestNG runner class will execute the Cucumber tests based on this configuration
}
