package runners;
//This is runner class to run tests and lets run
//As you can see Post and put and patch are failing since the data is not being created on database for further verification
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "./src/test/resources/apiFeatures",
        glue={"apiAutomation.stepDefinitions"},
        dryRun = false,
        monochrome = true,
        tags = { "@ApiRegression" },
        plugin = { "pretty", "html:target/cucumber-html-report", "json:target/cucumber.json" }
)

public class ApiRunner {
}
