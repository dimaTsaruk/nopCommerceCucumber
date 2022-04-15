package testRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        ////features = "src/test/java/Features",
        features = "src/test/java/Features/Customers.feature",
        //features = "src/test/java/Features/Login.feature",
        glue = "stepDefinitions",
        dryRun = false,
        monochrome = true,
        plugin = {"pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json"},
        tags = "@SearchByEmail or @SearchByName"

)
public class TestRun {
}